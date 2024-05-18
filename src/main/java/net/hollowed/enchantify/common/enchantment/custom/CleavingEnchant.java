package net.hollowed.enchantify.common.enchantment.custom;

import net.hollowed.enchantify.common.enchantment.AxeCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CleavingEnchant extends Enchantment {
    public CleavingEnchant(EquipmentSlot... slots) {
        super(Rarity.VERY_RARE, AxeCategory.AXE, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }
}