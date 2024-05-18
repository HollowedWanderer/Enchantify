package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.effect.ModEffects;
import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ComboReset {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.player != null && !isItemWithComboEnchantment(event.player)) {
                resetCombo(event.player);
            }
        }
    }

    private static boolean isItemWithComboEnchantment(LivingEntity player) {
        return player.getMainHandItem().isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.COMBO.get(), player.getMainHandItem()) > 0;
    }

    private static void resetCombo(LivingEntity player) {
        double _setval = 0;
        player.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.combo = _setval;
            capability.syncPlayerVariables(player);
        });

        {
            if (!player.level().isClientSide() && player.getServer() != null) {
                player.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, player.position(), player.getRotationVector(), player.level() instanceof ServerLevel ? (ServerLevel) player.level() : null, 4,
                        player.getName().getString(), player.getDisplayName(), Objects.requireNonNull(player.level().getServer()), player), "/attribute @s minecraft:generic.attack_damage modifier remove 1-1-1-1-1");
            }
        }
    }
}
