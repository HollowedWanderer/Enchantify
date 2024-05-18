package net.hollowed.enchantify.common.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DoubleJumpEnchant extends Enchantment {
    public DoubleJumpEnchant(EquipmentSlot... slots) {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_LEGS, slots);
    }
}
