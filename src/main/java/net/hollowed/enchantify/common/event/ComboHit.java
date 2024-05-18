package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.EnchantifyMod;
import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;
import java.util.Objects;

@Mod.EventBusSubscriber
public class ComboHit {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getSource().getDirectEntity());
        }
    }

    public static void execute(Entity immediatesourceentity) {
        execute(null, immediatesourceentity);
    }

    private static void execute(@Nullable Event event, Entity immediatesourceentity) {
        if (!(immediatesourceentity instanceof Player))
            return;
        ItemStack mainHandItem = ((LivingEntity)immediatesourceentity).getMainHandItem();
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.COMBO.get(), mainHandItem) != 0
                && (immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).healthKeeper == 0) {
            if ((immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).combo < 0.5) {
                double _setval = (immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).combo + 0.2;
                immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.combo = _setval;
                    capability.syncPlayerVariables(immediatesourceentity);
                });
            }
            {
                if (!immediatesourceentity.level().isClientSide() && immediatesourceentity.getServer() != null) {
                    immediatesourceentity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, immediatesourceentity.position(), immediatesourceentity.getRotationVector(), immediatesourceentity.level() instanceof ServerLevel ? (ServerLevel) immediatesourceentity.level() : null, 4,
                            immediatesourceentity.getName().getString(), immediatesourceentity.getDisplayName(), Objects.requireNonNull(immediatesourceentity.level().getServer()), immediatesourceentity), "/attribute @s minecraft:generic.attack_damage modifier add 1-1-1-1-1 Damage " +
                            ((immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).combo + 1) + " multiply");
                }
            }
            {
                double _setval = 1;
                immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.healthKeeper = _setval;
                    capability.syncPlayerVariables(immediatesourceentity);
                });
            }
            EnchantifyMod.queueServerWork(40, () -> {
                {
                    double _setval = 0;
                    immediatesourceentity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.healthKeeper = _setval;
                        capability.syncPlayerVariables(immediatesourceentity);
                    });
                }
            });
        }
    }
}







