package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.effect.ModEffects;
import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class JumpCooldown {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player);
        }
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if (entity.onGround()) {
            {
                boolean _setval = false;
                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.jumpCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            {
                boolean _setval = false;
                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.dashCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            {
                boolean _setval = false;
                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.strafeCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
        if (entity instanceof LivingEntity _livEnt1 && _livEnt1.hasEffect(ModEffects.FEATHER_FALL.get())) {
            entity.fallDistance = 0;
        }
    }
}
