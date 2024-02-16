package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

public class Strafe {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (!(entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).strafeCooldown
                && EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.STRAFE.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY)) != 0) {
            if ((entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).strafeLeft) {
                Vec3 lookDirection = entity.getLookAngle();
                double leftX = lookDirection.z;
                double leftZ = -lookDirection.x;
                entity.setDeltaMovement(new Vec3(leftX, 0.3, leftZ));

            } else
            if ((entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).strafeRight) {
                Vec3 lookDirection = entity.getLookAngle();
                double leftX2 = -lookDirection.z;
                double leftZ2 = lookDirection.x;
                entity.setDeltaMovement(new Vec3(leftX2, 0.3, leftZ2));
            } else
            if ((entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).strafeBack) {
                entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * -1), 0.3, (entity.getLookAngle().z * -1)));
            }
            {
                boolean _setval = true;
                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.strafeCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            {
                double _setval = (entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).stamina + 20;
                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.stamina = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
    }
}