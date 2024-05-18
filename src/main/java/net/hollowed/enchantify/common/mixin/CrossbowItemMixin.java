package net.hollowed.enchantify.common.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public static int getChargeDuration(ItemStack p_40940_) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, p_40940_);
        return i * 3 == 0 ? 25 : 25 - 5 * i;
    }

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    private static AbstractArrow getArrow(Level level, LivingEntity livingEntity, ItemStack crossbow, ItemStack arrow) {
        ArrowItem arrowItem = arrow.getItem() instanceof ArrowItem ? (ArrowItem) arrow.getItem() : (ArrowItem) Items.ARROW;
        AbstractArrow abstractArrow = arrowItem.createArrow(level, arrow, livingEntity);
        if (livingEntity instanceof Player) {
            abstractArrow.setCritArrow(true);
        }

        abstractArrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
        abstractArrow.setShotFromCrossbow(true);
        int piercingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, crossbow);
        if (piercingLevel > 0) {
            abstractArrow.setPierceLevel((byte) (piercingLevel * 3));
        }

        return abstractArrow;
    }
}
