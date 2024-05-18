package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "canEnchant", at = @At("RETURN"), cancellable = true)
    private void canEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment == Enchantments.MENDING)
            callbackInfoReturnable.setReturnValue(false);
        if (enchantment == Enchantments.UNBREAKING)
            callbackInfoReturnable.setReturnValue(false);
        if (enchantment == Enchantments.THORNS)
            callbackInfoReturnable.setReturnValue(false);
    }

    @Inject(method = "canApplyAtEnchantingTable", at = @At("RETURN"), cancellable = true, remap = false)
    private void canApplyAtEnchantingTable(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment == Enchantments.MENDING)
            callbackInfoReturnable.setReturnValue(false);
        if (enchantment == Enchantments.UNBREAKING)
            callbackInfoReturnable.setReturnValue(false);
        if (enchantment == Enchantments.THORNS)
            callbackInfoReturnable.setReturnValue(false);
    }
}
