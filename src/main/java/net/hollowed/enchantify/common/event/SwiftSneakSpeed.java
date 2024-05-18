package net.hollowed.enchantify.common.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SwiftSneakSpeed {

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.isShiftKeyDown()) {
                // Check if the player has the Swift Sneak enchantment
                if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.SWIFT_SNEAK, player.getItemBySlot(EquipmentSlot.LEGS)) > 0) {
                    // Apply ambient speed for 2 ticks
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 2, true, false));
                }
            }
        }
    }
}
