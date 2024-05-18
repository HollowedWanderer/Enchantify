package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.ArrowDamageEnchantment;
import net.minecraft.world.item.enchantment.ArrowKnockbackEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ArrowKnockbackEnchantment.class)
public abstract class PunchEnchantmentMixin extends Enchantment {

    protected PunchEnchantmentMixin(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public int getMaxLevel() {
        return 1;
    }
}
