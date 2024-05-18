package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.TridentImpalerEnchantment;
import net.minecraft.world.item.enchantment.TridentLoyaltyEnchantment;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TridentImpalerEnchantment.class)
public abstract class ImpalingEnchantmentMixin extends Enchantment {

    protected ImpalingEnchantmentMixin(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
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

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public float getDamageBonus(int p_45235_, @NotNull MobType p_45236_) {
        return p_45236_ == MobType.WATER ? (float)p_45235_ * 4.0F : 3.0F;
    }
}
