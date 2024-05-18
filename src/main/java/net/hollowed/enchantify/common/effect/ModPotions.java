package net.hollowed.enchantify.common.effect;

import net.hollowed.enchantify.EnchantifyMod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {
    public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, EnchantifyMod.MOD_ID);

    public static final RegistryObject<Potion> FREEZING = REGISTRY.register("freezing", () -> new Potion(new MobEffectInstance(ModEffects.FROZEN.get(), 320, 0, false, true)));

    public static final RegistryObject<Potion> LEVITATION = REGISTRY.register("levitation", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 200, 0, false, true)));
    public static final RegistryObject<Potion> LEVITATIONII = REGISTRY.register("levitationii", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 160, 1, false, true)));
    public static final RegistryObject<Potion> LEVITATION_EXTEND = REGISTRY.register("levitation_extend", () -> new Potion(new MobEffectInstance(MobEffects.LEVITATION, 400, 0, false, true)));

    public static final RegistryObject<Potion> KNIVES = REGISTRY.register("knives", () -> new Potion(new MobEffectInstance(MobEffects.HARM, 1, 0, false, true),
            new MobEffectInstance(ModEffects.BLEEDING.get(), 600, 0, false, true)));
}

