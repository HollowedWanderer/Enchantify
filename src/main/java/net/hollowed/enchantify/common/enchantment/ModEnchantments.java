package net.hollowed.enchantify.common.enchantment;

import net.hollowed.enchantify.EnchantifyMod;
import net.hollowed.enchantify.common.enchantment.custom.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, EnchantifyMod.MOD_ID);
    public static final RegistryObject<Enchantment> DOUBLE_JUMP = REGISTRY.register("double_jump", DoubleJumpEnchant::new);
    public static final RegistryObject<Enchantment> DASH = REGISTRY.register("dash", DashEnchant::new);

    public static final RegistryObject<Enchantment> DARKSIGHT = REGISTRY.register("darksight", DarksightEnchant::new);

    public static final RegistryObject<Enchantment> STRAFE = REGISTRY.register("strafe", StrafeEnchant::new);

    public static final RegistryObject<Enchantment> CLEAVING = REGISTRY.register("cleaving", CleavingEnchant::new);

    public static final RegistryObject<Enchantment> COMBUSTIBILITY = REGISTRY.register("combustibility", CombustibilityEnchant::new);

    public static final RegistryObject<Enchantment> COMBO = REGISTRY.register("combo", ComboEnchant::new);

    public static final RegistryObject<Enchantment> FROSTBITE = REGISTRY.register("frostbite", FrostbiteEnchant::new);

    public static final RegistryObject<Enchantment> LEECH = REGISTRY.register("leech", LeechEnchant::new);

    public static final RegistryObject<Enchantment> PARRY = REGISTRY.register("parry", ParryEnchant::new);

    public static final RegistryObject<Enchantment> BASH = REGISTRY.register("bash", BashEnchant::new);
}
