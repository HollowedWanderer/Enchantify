package net.hollowed.enchantify.common.enchantment.custom;

import net.hollowed.enchantify.common.enchantment.AxeCategory;
import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LeechEnchant extends Enchantment {
    public LeechEnchant(EquipmentSlot... slots) {
        super(Rarity.RARE, AxeCategory.AXE, slots);
    }

    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return this != ench && !Objects.equals(ModEnchantments.CLEAVING, ench);
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
