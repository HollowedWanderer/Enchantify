package net.hollowed.enchantify;

import net.hollowed.enchantify.common.effect.ModEffects;
import net.hollowed.enchantify.common.effect.ModPotions;
import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.hollowed.enchantify.common.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EnchantifyMod.MOD_ID)
public class EnchantifyMod
{
    private static final int OFFHAND_SLOT = -1;
    private static final int NOT_FOUND_SLOT = -2;

    public static void onItemUseFinish(Player player, ItemStack itemStack, int duration) {
        if (itemStack.getItem() instanceof TridentItem && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.LOYALTY, itemStack) > 0 && itemStack.getItem().getUseDuration(itemStack) - duration >= 10) {
            int j = EnchantmentHelper.getRiptide(itemStack);
            if ((j <= 0 || player.isInWaterOrRain()) && !player.level().isClientSide() && j == 0) {
                if (itemStack.getTag() == null) {
                    itemStack.setTag(new CompoundTag());
                }
                int slot = getSlotFor(player.getInventory(), itemStack);
                itemStack.getTag().putInt("slot_thrown_from", slot);
            }
        }
    }

    public static void onPlayerTick(Player player) {
        for(ItemStack stack : player.getInventory().items) {
            checkStack(player, stack);
        }
    }

    public static void checkStack(Player player, ItemStack stack) {
        if(stack.getItem() instanceof TridentItem && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.LOYALTY, stack) > 0) {
            if(stack.getTag() != null) {
                if(stack.getTag().contains("slot_thrown_from", Tag.TAG_INT)) {
                    int slot = stack.getTag().getInt("slot_thrown_from");
                    int curSlot = getSlotFor(player.getInventory(), stack);
                    if(slot != curSlot) {
                        if(slot == OFFHAND_SLOT) {
                            ItemStack fromSlot = player.getOffhandItem();
                            if(fromSlot.isEmpty()) {
                                player.getInventory().removeItemNoUpdate(curSlot);
                                stack.getTag().remove("slot_thrown_from");
                                player.getInventory().offhand.set(0, stack);
                            }
                        } else if(slot != NOT_FOUND_SLOT) {
                            ItemStack fromSlot = player.getInventory().getItem(slot);
                            if(fromSlot.isEmpty()) {
                                player.getInventory().removeItemNoUpdate(curSlot);
                                stack.getTag().remove("slot_thrown_from");
                                player.getInventory().setItem(slot, stack);
                            }
                        }
                    }
                }
            }
        }
    }

    public static int getSlotFor(Inventory inv, ItemStack stack) {
        for(int i = 0; i < inv.items.size(); ++i) {
            if (!inv.items.get(i).isEmpty() && stackEqualExact(stack, inv.items.get(i))) {
                return i;
            }
        }
        if(!inv.offhand.get(0).isEmpty() && stackEqualExact(stack, inv.offhand.get(0))) {
            return OFFHAND_SLOT;
        }


        return NOT_FOUND_SLOT;
    }

    private static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && ItemStack.matches(stack1, stack2);
    }

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "enchantify";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";

    private static int messageID = 0;

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    actions.add(work);
            });
            actions.forEach(e -> e.getKey().run());
            workQueue.removeAll(actions);
        }
    }

    public static final TagKey<Item> DONT_AFFECT = ItemTags.create(new ResourceLocation("enchantify", "dont_affect"));

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public EnchantifyMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEnchantments.REGISTRY.register(modEventBus);
        ModPotions.REGISTRY.register(modEventBus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");


    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder,
                                             Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.EXPERIENCE_JAR);
        }
    }
}
