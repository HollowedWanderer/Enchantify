package net.hollowed.enchantify.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FrostWalkerEnchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FrostWalkerEnchantment.class)
public abstract class FrostWalkerEnchantmentMixin extends Enchantment {

    protected FrostWalkerEnchantmentMixin(Rarity rarityIn, EnchantmentCategory typeIn, EquipmentSlot[] slots) {
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
    public static void onEntityMoved(LivingEntity p_45019_, Level p_45020_, BlockPos p_45021_, int p_45022_) {
        if (p_45019_.onGround()) {
            BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
            int i = Math.min(32, 2 + (p_45022_ * 2)); // Double the range
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos : BlockPos.betweenClosed(p_45021_.offset(-i, -1, -i), p_45021_.offset(i, -1, i))) {
                if (blockpos.closerToCenterThan(p_45019_.position(), (double)i)) {
                    blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
                    BlockState blockstate1 = p_45020_.getBlockState(blockpos$mutableblockpos);
                    if (blockstate1.isAir()) {
                        BlockState blockstate2 = p_45020_.getBlockState(blockpos);
                        if (blockstate2 == FrostedIceBlock.meltsInto() && blockstate.canSurvive(p_45020_, blockpos) && p_45020_.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(p_45019_, net.minecraftforge.common.util.BlockSnapshot.create(p_45020_.dimension(), p_45020_, blockpos), net.minecraft.core.Direction.UP)) {
                            p_45020_.setBlockAndUpdate(blockpos, blockstate);
                            p_45020_.scheduleTick(blockpos, Blocks.FROSTED_ICE, Mth.nextInt(p_45019_.getRandom(), 60, 120));
                        }
                    }
                }
            }
        }
    }
}
