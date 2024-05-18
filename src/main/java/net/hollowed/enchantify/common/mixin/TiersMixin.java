package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.item.Tiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Tiers.class)
public abstract class TiersMixin {

    @Inject(method = "getSpeed", at = @At("HEAD"), cancellable = true)
    private void modifyMiningSpeed(CallbackInfoReturnable<Float> info) {
        // Adjust the mining speed dynamically based on tier
        Tiers tier = (Tiers) (Object) this;
        switch (tier) {
            case WOOD -> info.setReturnValue(2.5F); // Adjust the multiplier for WOOD tier
            case STONE -> info.setReturnValue(5.0F); // Adjust the multiplier for STONE tier
            case IRON -> info.setReturnValue(7.5F); // Adjust the multiplier for IRON tier
            case DIAMOND -> info.setReturnValue(10.5F); // Adjust the multiplier for DIAMOND tier
            case GOLD -> info.setReturnValue(32.0F); // Adjust the multiplier for GOLD tier
            case NETHERITE -> info.setReturnValue(12.0F); // Adjust the multiplier for NETHERITE tier
            default -> {
            }
        }
    }
}