package net.hollowed.enchantify.common.item;

import net.hollowed.enchantify.EnchantifyMod;
import net.hollowed.enchantify.common.item.custom.ExperienceJarItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EnchantifyMod.MOD_ID);

    public static final RegistryObject<Item> EXPERIENCE_JAR = ITEMS.register("experience_jar",
            () -> new ExperienceJarItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
