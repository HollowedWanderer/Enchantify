package net.hollowed.enchantify.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AxeCategory extends Enchantment {

    public static EnchantmentCategory AXE =
            EnchantmentCategory.create("AXE", item -> item instanceof AxeItem || item == Items.ENCHANTED_BOOK);

    protected AxeCategory(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }
}
