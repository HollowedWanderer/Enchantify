package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.effect.ModEffects;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHealEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BleedTick {
    @SubscribeEvent
    public static void onEntityHealed(LivingHealEvent event) {
        execute(event, event.getEntity());
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModEffects.BLEEDING.get())) {
            if (!_livEnt0.hasEffect(MobEffects.REGENERATION) || !_livEnt0.hasEffect(MobEffects.HEAL)) {
                if (event != null && event.isCancelable()) {
                    event.setCanceled(true);
                }
            }
        }
    }
}


