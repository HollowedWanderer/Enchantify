package net.hollowed.enchantify.common.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SculksenseEnchant extends Enchantment {
    public SculksenseEnchant(EquipmentSlot... slots) {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_HEAD, slots);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}
