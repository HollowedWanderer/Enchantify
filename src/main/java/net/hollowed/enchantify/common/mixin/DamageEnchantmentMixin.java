package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin extends Enchantment {

    public DamageEnchantmentMixin(Enchantment.Rarity p_44628_, int p_44629_, EquipmentSlot... p_44630_) {
        super(p_44628_, EnchantmentCategory.WEAPON, p_44630_);
    }

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public int getMaxLevel() {
        return 1;
    }

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public float getDamageBonus(int p_44635_, @NotNull MobType p_44636_) {
        int currentLevel = Math.max(0, p_44635_ - 1); // Get the current level of the enchantment
        int additionalLevel = 4; // Additional level to add to the current level
        if (this == Enchantments.SHARPNESS) {
            return 1.0F + (float)(currentLevel + additionalLevel) * 0.5F; // Multiply the damage modifier by the current level plus 4
        } else if (this == Enchantments.SMITE && p_44636_ == MobType.UNDEAD) {
            return (float)(currentLevel + additionalLevel + 1) * 2.5F; // Multiply the damage modifier by the current level plus 4
        } else if (this == Enchantments.BANE_OF_ARTHROPODS && p_44636_ == MobType.ARTHROPOD) {
            return (float)(currentLevel + additionalLevel + 1) * 2.5F; // Multiply the damage modifier by the current level plus 4
        } else {
            return 0.0F;
        }
    }
}

