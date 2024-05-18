package net.hollowed.enchantify.common.mixin;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    /**
     * @author Hollowed
     * @reason Remove durability
     */
    @Overwrite
    public boolean isDamageableItem() {
        return false;
    }
}