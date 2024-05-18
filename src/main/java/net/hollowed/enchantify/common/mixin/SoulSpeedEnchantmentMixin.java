package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DigDurabilityEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.SoulSpeedEnchantment;
import net.minecraft.world.level.block.SoulSandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SoulSpeedEnchantment.class)
public abstract class SoulSpeedEnchantmentMixin extends Enchantment {

    protected SoulSpeedEnchantmentMixin(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
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
