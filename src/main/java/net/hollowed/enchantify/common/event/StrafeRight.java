package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.world.entity.Entity;

public class StrafeRight {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        {
            boolean _setval = true;
            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.strafeRight = _setval;
                capability.syncPlayerVariables(entity);
            });
        }
    }
}
