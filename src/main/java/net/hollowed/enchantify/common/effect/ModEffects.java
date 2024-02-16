package net.hollowed.enchantify.common.effect;

import net.hollowed.enchantify.EnchantifyMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EnchantifyMod.MOD_ID);

    public static final RegistryObject<MobEffect> FEATHER_FALL = MOB_EFFECTS.register("feather_fall",
            () -> new FeatherFall(MobEffectCategory.BENEFICIAL, -7877432));

    public static final RegistryObject<MobEffect> FROZEN = MOB_EFFECTS.register("frozen",
            () -> new FeatherFall(MobEffectCategory.HARMFUL, 5688575));

    public static final RegistryObject<MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding",
            () -> new Bleeding(MobEffectCategory.HARMFUL, 4456448));

    public static final RegistryObject<MobEffect> COMBAT_LOCKED = MOB_EFFECTS.register("combat_locked",
            () -> new CombatLocked(MobEffectCategory.HARMFUL, 16724838));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
