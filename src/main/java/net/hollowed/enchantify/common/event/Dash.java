package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.effect.ModEffects;
import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;


public class Dash {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModEffects.COMBAT_LOCKED.get())) {
            if (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.DASH.get(), _livEnt0.getItemBySlot(EquipmentSlot.CHEST)) != 0 && !entity.onGround()
                    && !(entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).dashCooldown) {
                if ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) > 0) {
                    Player _plr = (Player) entity;
                    if (_plr.getFoodData().getSaturationLevel() > 0) {
                        Player _player = (Player) entity;
                        if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                            if (!(new Object() {
                                public boolean checkGamemode(Entity _ent) {
                                    if (_ent instanceof ServerPlayer _serverPlayer) {
                                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                                    } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                        return Objects.requireNonNull(Minecraft.getInstance().getConnection()).getPlayerInfo(_player.getGameProfile().getId()) != null && Objects.requireNonNull(Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())).getGameMode() == GameType.CREATIVE;
                                    }
                                    return false;
                                }
                            }.checkGamemode(entity) || new Object() {
                                public boolean checkGamemode(Entity _ent) {
                                    if (_ent instanceof ServerPlayer _serverPlayer) {
                                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                                    } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                        return Objects.requireNonNull(Minecraft.getInstance().getConnection()).getPlayerInfo(_player.getGameProfile().getId()) != null && Objects.requireNonNull(Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())).getGameMode() == GameType.SPECTATOR;
                                    }
                                    return false;
                                }
                            }.checkGamemode(entity))) {
                                LivingEntity _entity = (LivingEntity) entity;
                                if (!_entity.level().isClientSide())
                                    _player.getFoodData().setSaturation((int) (_plr.getFoodData().getSaturationLevel() - 2));
                            }
                        }
                        {
                            boolean _setval = true;
                            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.dashCooldown = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                        entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * 1), (entity.getDeltaMovement().y() + entity.getLookAngle().y * 1), (entity.getDeltaMovement().z() + entity.getLookAngle().z * 1)));
                        LivingEntity _entity = (LivingEntity) entity;
                        if (!_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModEffects.FEATHER_FALL.get(), 20, 0, false, false));
                        {
                            double _setval = (entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).stamina + 20;
                            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.stamina = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                    } else {
                        if (_plr.getFoodData().getFoodLevel() > 6) {
                            Player _player = (Player) entity;
                            if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                                _player.getFoodData().setFoodLevel((int) (_plr.getFoodData().getFoodLevel() - 2));
                            }
                            {
                                boolean _setval = true;
                                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                    capability.dashCooldown = _setval;
                                    capability.syncPlayerVariables(entity);
                                });
                            }
                            entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * 1), (entity.getDeltaMovement().y() + entity.getLookAngle().y * 1), (entity.getDeltaMovement().z() + entity.getLookAngle().z * 1)));
                            LivingEntity _entity = (LivingEntity) entity;
                            if (!_entity.level().isClientSide())
                                _entity.addEffect(new MobEffectInstance(ModEffects.FEATHER_FALL.get(), 20, 0, false, false));
                        }
                    }
                }
            }
        } else {
            if (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.DASH.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY)) != 0 && !entity.onGround()
                    && !(entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).dashCooldown) {
                if ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) > 0) {
                    Player _plr = (Player) entity;
                    if (_plr.getFoodData().getSaturationLevel() > 0) {
                        Player _player = (Player) entity;
                        if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                            if (!(new Object() {
                                public boolean checkGamemode(Entity _ent) {
                                    if (_ent instanceof ServerPlayer _serverPlayer) {
                                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                                    } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                        return Objects.requireNonNull(Minecraft.getInstance().getConnection()).getPlayerInfo(_player.getGameProfile().getId()) != null && Objects.requireNonNull(Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())).getGameMode() == GameType.CREATIVE;
                                    }
                                    return false;
                                }
                            }.checkGamemode(entity) || new Object() {
                                public boolean checkGamemode(Entity _ent) {
                                    if (_ent instanceof ServerPlayer _serverPlayer) {
                                        return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
                                    } else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
                                        return Objects.requireNonNull(Minecraft.getInstance().getConnection()).getPlayerInfo(_player.getGameProfile().getId()) != null && Objects.requireNonNull(Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId())).getGameMode() == GameType.SPECTATOR;
                                    }
                                    return false;
                                }
                            }.checkGamemode(entity))) {
                                LivingEntity _entity = (LivingEntity) entity;
                                if (!_entity.level().isClientSide())
                                    _player.getFoodData().setSaturation((int) (_plr.getFoodData().getSaturationLevel() - 1));
                            }
                        }
                        {
                            boolean _setval = true;
                            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.dashCooldown = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                        entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * 1), (entity.getDeltaMovement().y() + entity.getLookAngle().y * 1), (entity.getDeltaMovement().z() + entity.getLookAngle().z * 1)));
                        LivingEntity _entity = (LivingEntity) entity;
                        if (!_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModEffects.FEATHER_FALL.get(), 20, 0, false, false));
                        {
                            double _setval = (entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).stamina + 20;
                            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.stamina = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                    } else {
                        if (_plr.getFoodData().getFoodLevel() > 6) {
                            Player _player = (Player) entity;
                            if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                                _player.getFoodData().setFoodLevel((int) (_plr.getFoodData().getFoodLevel() - 1));
                            }
                            {
                                boolean _setval = true;
                                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                    capability.dashCooldown = _setval;
                                    capability.syncPlayerVariables(entity);
                                });
                            }
                            entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * 1), (entity.getDeltaMovement().y() + entity.getLookAngle().y * 1), (entity.getDeltaMovement().z() + entity.getLookAngle().z * 1)));
                            LivingEntity _entity = (LivingEntity) entity;
                            if (!_entity.level().isClientSide())
                                _entity.addEffect(new MobEffectInstance(ModEffects.FEATHER_FALL.get(), 20, 0, false, false));
                        }
                    }
                }
            }
        }
    }
}
