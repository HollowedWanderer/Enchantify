package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BashBlock {
    @SubscribeEvent
    public static void whenEntityBlocksWithShield(ShieldBlockEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity(), event.getDamageSource().getDirectEntity());
        }
    }

    public static void execute(Entity entity, Entity immediatesourceentity) {
        execute(null, entity, immediatesourceentity);
    }

    private static void execute(@Nullable Event event, Entity entity, Entity immediatesourceentity) {
        if (entity == null || immediatesourceentity == null)
            return;
        if (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.BASH.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)) != 0
                || EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.BASH.get(), (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)) != 0) {
            immediatesourceentity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 0.9), (entity.getLookAngle().y * 0.4), (entity.getLookAngle().z * 0.9)));
        }
    }
}




