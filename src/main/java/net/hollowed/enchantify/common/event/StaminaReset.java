package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class StaminaReset {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        execute(event, event.getEntity(), event.getEntity().level());
    }

    public static void execute(Entity entity, Level level) {
        execute(null, entity, level);
    }

    private static void execute(@Nullable Event event, Entity entity, Level level) {
        if (entity == null)
            return;
        {
            double _setval = 20;
            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.stamina = _setval;
                capability.syncPlayerVariables(entity);
            });
        }
        {
            double _setval = 0;
            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.combo = _setval;
                capability.syncPlayerVariables(entity);
            });
        }
        {
            double _setval = 0;
            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.healthKeeper = _setval;
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
        //StaminaConstant.execute(level, entity);
    }
}
