package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.effect.ModEffects;
import net.hollowed.enchantify.common.enchantment.ModEnchantments;
import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class DoubleJumpPressed {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(ModEffects.COMBAT_LOCKED.get())) {
            if (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.DOUBLE_JUMP.get(), _livEnt0.getItemBySlot(EquipmentSlot.LEGS)) != 0 && !entity.onGround()
                    && !(entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).jumpCooldown) {
                if ((entity instanceof Player _plr ? _plr.getFoodData().getFoodLevel() : 0) > 0) {
                    Player _plr = (Player) entity;
                    if (_plr.getFoodData().getSaturationLevel() > 0) {
                        Player _player = (Player) entity;
                        if (Mth.nextInt(RandomSource.create(), 1, 5) == 1) {
                            _player.getFoodData().setSaturation((int) (_plr.getFoodData().getSaturationLevel() - 2));
                        }
                        {
                            boolean _setval = true;
                            entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.jumpCooldown = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                        entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.8), 0.5, (entity.getDeltaMovement().z() * 1.8)));
                        LivingEntity _entity = (LivingEntity) entity;
                        if (!_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModEffects.FEATHER_FALL.get(), 20, 0, false, false));
                        {
                            if (!entity.level().isClientSide() && entity.getServer() != null) {
                                entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                        entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/particle minecraft:cloud ~ ~ ~ 0 0 0 0.05 10");
                            }
                        }
                        {
                            if (!entity.level().isClientSide() && entity.getServer() != null) {
                                entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                        entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/playsound minecraft:block.moss_carpet.step player @a");
                            }
                        }
                    } else {
                        if (_plr.getFoodData().getFoodLevel() > 6) {
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
                                        _player.getFoodData().setFoodLevel((int) (_plr.getFoodData().getFoodLevel() - 2));
                                }
                            }
                            {
                                boolean _setval = true;
                                entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                    capability.jumpCooldown = _setval;
                                    capability.syncPlayerVariables(entity);
                                });
                            }
                            entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.8), 0.5, (entity.getDeltaMovement().z() * 1.8)));
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
                            {
                                if (!entity.level().isClientSide() && entity.getServer() != null) {
                                    entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                            entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/particle minecraft:cloud ~ ~ ~ 0 0 0 0.05 10");
                                }
                            }
                            {
                                if (!entity.level().isClientSide() && entity.getServer() != null) {
                                    entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                            entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/playsound minecraft:block.moss_carpet.step player @a");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.DOUBLE_JUMP.get(), (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY)) != 0 && !entity.onGround()
                    && !(entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ModVariables.PlayerVariables())).jumpCooldown) {
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
                                capability.jumpCooldown = _setval;
                                capability.syncPlayerVariables(entity);
                            });
                        }
                        entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.8), 0.5, (entity.getDeltaMovement().z() * 1.8)));
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
                        {
                            if (!entity.level().isClientSide() && entity.getServer() != null) {
                                entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                        entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/particle minecraft:cloud ~ ~ ~ 0 0 0 0.05 10");
                            }
                        }
                        {
                            if (!entity.level().isClientSide() && entity.getServer() != null) {
                                entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                        entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/playsound minecraft:block.moss_carpet.step player @a");
                            }
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
                                    capability.jumpCooldown = _setval;
                                    capability.syncPlayerVariables(entity);
                                });
                            }
                            entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.8), 0.5, (entity.getDeltaMovement().z() * 1.8)));
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
                            {
                                if (!entity.level().isClientSide() && entity.getServer() != null) {
                                    entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                            entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/particle minecraft:cloud ~ ~ ~ 0 0 0 0.05 10");
                                }
                            }
                            {
                                if (!entity.level().isClientSide() && entity.getServer() != null) {
                                    entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null, 4,
                                            entity.getName().getString(), entity.getDisplayName(), Objects.requireNonNull(entity.level().getServer()), entity), "/playsound minecraft:block.moss_carpet.step player @a");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}