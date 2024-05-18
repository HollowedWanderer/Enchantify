package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "getXpNeededForNextLevel", at = @At("HEAD"), cancellable = true)
    private void adjustXpNeededForNextLevel(CallbackInfoReturnable<Integer> cir) {
        Player player = (Player) (Object) this;
        int level = player.experienceLevel;
        // Linear scaling: Experience needed for next level = level * constant
        int constant = 35; // Adjust this constant as needed for your desired scaling
        cir.setReturnValue(constant);
    }
}