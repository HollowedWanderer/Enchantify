package net.hollowed.enchantify.common.mixin;

import net.hollowed.enchantify.EnchantifyMod;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.util.Mth.ceil;
import static net.minecraft.util.Mth.floor;


@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {
    public AnvilMenuMixin(int p_39005_, Inventory p_39006_) {
        super(null, 0, null, null);
    }

    private static final Logger LOGGER = LogManager.getLogger();

    //defaults to 4 in vanilla
    private static final int REPAIR_ITEMS = 1;

    @Shadow
    @Final
    private DataSlot cost;

    @Shadow
    private int repairItemCountCost;

    //cap repair cost at the total levels worth of enchants applied
    @Inject(method = "createResult", at = @At("TAIL"), cancellable = true)
    private void setCost(CallbackInfo ci) {
        ItemStack input1 = inputSlots.getItem(0);
        ItemStack input2 = inputSlots.getItem(1);
        ItemStack output = resultSlots.getItem(0);

        boolean overrideCost = false;
        //boolean isDontAffect = ItemTags.getAllTags().getTag(new ResourceLocation("endofmending:dont_affect")).contains(input1.getItem());
        boolean isDontAffect = input1.is(EnchantifyMod.DONT_AFFECT);
        //boolean isDontAffect = false;
        int damageValue = input1.getDamageValue();
        if (!isDontAffect) {
            //only triggers when a material is used to repair a tool (e.g diamond pickaxe and a diamond)
            if (input1.isDamageableItem() && input1.getItem().isValidRepairItem(input1, input2)) {
                overrideCost = true;
                int l2 = Math.min(damageValue, input1.getMaxDamage() / REPAIR_ITEMS);
                if (l2 <= 0) {
                    this.resultSlots.setItem(0, ItemStack.EMPTY);
                    this.cost.set(0);
                    return;
                }

                int i3;
                for (i3 = 0; l2 > 0 && i3 < input2.getCount(); ++i3) {
                    int j3 = damageValue - l2;
                    damageValue = j3;
                    l2 = Math.min(damageValue, input1.getMaxDamage() / REPAIR_ITEMS);
                }

                output.setDamageValue(damageValue);
                ((AnvilMenu) (Object) this).repairItemCountCost = i3;
            }
            float costMult = 1;
            if (input1.isDamageableItem() && input2.getItem() == Items.ENCHANTED_BOOK && !EnchantedBookItem.getEnchantments(input2).isEmpty() && !isDontAffect) {
                overrideCost = true;
                costMult = REPAIR_ITEMS;
            }
            if (input1.isDamageableItem() && input1.is(input2.getItem()) && !isDontAffect) {
                overrideCost = true;
                costMult = REPAIR_ITEMS;
            }

            ListTag enchants = output.getEnchantmentTags();
            int totallevel = 0;
            //get total levels of enchants on tool
            for (int c = 0; c < enchants.size(); c++) {
                String lvl = enchants.getCompound(c).get("lvl").toString();
                int lvlnum = Integer.parseInt(lvl.substring(0, lvl.length() - 1));
                totallevel += lvlnum;
            }
            int repaircost = input1.getBaseRepairCost() + 1;
            int enchantBasedRepairCost = floor(totallevel * 0.8);
            if (enchantBasedRepairCost < input1.getBaseRepairCost()) {
                repaircost = enchantBasedRepairCost + 1;
            }
            if (overrideCost) {
                if (repaircost < 3) {
                    repaircost = 3;
                }
                repaircost = enchantBasedRepairCost + 1;
            }

            repaircost *= costMult;
            if (this.repairItemCountCost > 0) {
                cost.set(repaircost * this.repairItemCountCost);
            } else {
                cost.set(repaircost);
            }
        }
    }

    //prevent the base repair cost from making an item too expensive to repair
    @Redirect(
            method = "createResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/DataSlot;get()I"
            )
    )
    private int returnUnderForty(DataSlot instance)  {
        int returnValue = cost.get();
        if (returnValue > 40) {
            returnValue = 39;
        }
        return returnValue;
    }
}
