package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class StaminaTick {
    private static final int STAMINA_INCREMENT_TICKS = 40; // Ticks to increment stamina
    private static final int STAMINA_INCREMENT_AMOUNT = 2; // Amount to increment stamina by
    private static final int MAX_STAMINA = 20; // Maximum stamina value

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event.player, event);
        }
    }

    public static void execute(Entity entity, Event event) {
        execute(null, entity, event);
    }

    private static void execute(@Nullable Event event, Entity entity, Event tickEvent) {
        if (entity == null)
            return;

        // Get player's stamina from capability
        ModVariables.PlayerVariables playerVariables = entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables());
        double stamina = playerVariables.stamina;

        // Increment stamina every STAMINA_INCREMENT_TICKS ticks
        if (tickEvent instanceof TickEvent && ((TickEvent) tickEvent).type == TickEvent.Type.PLAYER && ((TickEvent.PlayerTickEvent) tickEvent).phase == TickEvent.Phase.END) {
            if (entity.tickCount % STAMINA_INCREMENT_TICKS == 0 && stamina < MAX_STAMINA) {
                stamina += STAMINA_INCREMENT_AMOUNT;
                // Ensure stamina does not exceed MAX_STAMINA
                playerVariables.stamina = Math.min(stamina, MAX_STAMINA);
            }
        }
    }
}


