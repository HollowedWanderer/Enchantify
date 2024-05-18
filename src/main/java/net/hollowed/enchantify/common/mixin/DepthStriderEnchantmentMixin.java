package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.item.enchantment.OxygenEnchantment;
import net.minecraft.world.item.enchantment.WaterWalkerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WaterWalkerEnchantment.class)
public class DepthStriderEnchantmentMixin {

    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public int getMaxLevel() {
        // Ensure the Protection enchantment has a maximum level of 1
        return 1;
    }
}

