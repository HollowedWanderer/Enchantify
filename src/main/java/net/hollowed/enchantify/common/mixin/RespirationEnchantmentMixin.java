package net.hollowed.enchantify.common.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.OxygenEnchantment;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OxygenEnchantment.class)
public class RespirationEnchantmentMixin {

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

