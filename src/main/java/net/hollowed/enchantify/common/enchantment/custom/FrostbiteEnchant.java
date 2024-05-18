package net.hollowed.enchantify.common.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FrostbiteEnchant extends Enchantment {
    public FrostbiteEnchant(EquipmentSlot... slots) {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, slots);
    }


    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return this != ench && !Objects.equals(Enchantments.FIRE_ASPECT, ench);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }
}
