package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.world.entity.Entity;

public class StrafeBackRelease {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        {
            boolean _setval = false;
            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.strafeBack = _setval;
                capability.syncPlayerVariables(entity);
            });
        }
    }
}

