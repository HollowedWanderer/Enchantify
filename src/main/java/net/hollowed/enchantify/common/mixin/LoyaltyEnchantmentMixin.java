package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FishingSpeedEnchantment;
import net.minecraft.world.item.enchantment.TridentLoyaltyEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TridentLoyaltyEnchantment.class)
public abstract class LoyaltyEnchantmentMixin extends Enchantment {

    protected LoyaltyEnchantmentMixin(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
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
