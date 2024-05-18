package net.hollowed.enchantify.common.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownTrident.class)
public abstract class TridentMixin extends AbstractArrow {

    @Shadow protected abstract boolean isAcceptibleReturnOwner();

    // Unique fields for client-side tick count and dealt damage

    @Shadow public int clientSideReturnTridentTickCount;
    @Shadow private boolean dealtDamage;

    // Constructor Mixin
    protected TridentMixin(EntityType<? extends AbstractArrow> entityType, Level level, double x, double y, double z, boolean dealtDamage, int clientSideReturnTridentTickCount) {
        super(entityType, x, y, z, level);
        this.dealtDamage = dealtDamage;
        this.clientSideReturnTridentTickCount = clientSideReturnTridentTickCount;
    }



    // Accessor for ID_LOYALTY

    @Final
    @Shadow private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(TridentMixin.class, EntityDataSerializers.BYTE);

    // Invoke mixin to modify tick method behavior
    /**
     * @author Hollowed
     * @reason funny
     */
    @Overwrite
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int i = this.entityData.get(ID_LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015D * (double)i, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                double d0 = 0.18D * (double)i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(d0)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }
}