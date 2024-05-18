package net.hollowed.enchantify.common.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin extends Enchantment {

    public ProtectionEnchantmentMixin(Enchantment.Rarity p_45126_, ProtectionEnchantment.Type p_45127_, EquipmentSlot... p_45128_) {
        super(p_45126_, EnchantmentCategory.ARMOR, p_45128_);
    }

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public int getMaxLevel() {
        // Ensure the Protection enchantment has a maximum level of 1
        return 1;
    }

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public int getDamageProtection(int p_45133_, @NotNull DamageSource p_45134_) {
        if (p_45134_.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return 0;
        } else {
            int currentLevel = Math.max(0, p_45133_ - 1); // Get the current level of the enchantment
            int additionalLevel = 4; // Additional level to add to the current level

            // Compare the enchantment's class with predefined instances of the ProtectionEnchantment class
            if (this == Enchantments.ALL_DAMAGE_PROTECTION) {
                return p_45133_ * (currentLevel + additionalLevel - 1); // Multiply protection by the current level plus 4
            } else if (this == Enchantments.FIRE_PROTECTION && p_45134_.is(DamageTypeTags.IS_FIRE)) {
                return p_45133_ * (currentLevel + additionalLevel + 5); // Multiply protection by the current level plus 6
            } else if (this == Enchantments.BLAST_PROTECTION && p_45134_.is(DamageTypeTags.IS_EXPLOSION)) {
                return p_45133_ * (currentLevel + additionalLevel + 5); // Multiply protection by the current level plus 6
            } else if (this == Enchantments.PROJECTILE_PROTECTION && p_45134_.is(DamageTypeTags.IS_PROJECTILE)) {
                return p_45133_ * (currentLevel + additionalLevel + 5); // Multiply protection by the current level plus 6
            } else if (this == Enchantments.FALL_PROTECTION && p_45134_.is(DamageTypeTags.IS_FALL)) {
                return p_45133_ * 12; // Multiply protection by the current level plus 6
            } else {
                return 0;
            }
        }
    }
}



