package net.hollowed.enchantify.common.enchantment.custom;

import net.hollowed.enchantify.common.enchantment.ShieldCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BashEnchant extends Enchantment {
    public BashEnchant(EquipmentSlot... slots) {
        super(Rarity.RARE, ShieldCategory.SHIELD, slots);
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
