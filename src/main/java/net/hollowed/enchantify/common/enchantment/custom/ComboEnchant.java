package net.hollowed.enchantify.common.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ComboEnchant extends Enchantment {
    public ComboEnchant(EquipmentSlot... slots) {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, slots);
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return this != ench && !Objects.equals(Enchantments.SHARPNESS, ench)
                && !Objects.equals(Enchantments.BANE_OF_ARTHROPODS, ench)
                && !Objects.equals(Enchantments.SMITE, ench);
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
