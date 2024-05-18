package net.hollowed.enchantify.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ShieldCategory extends Enchantment {

    public static EnchantmentCategory SHIELD =
            EnchantmentCategory.create("SHIELD", item -> item instanceof ShieldItem || item == Items.ENCHANTED_BOOK);

    protected ShieldCategory(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }
}
