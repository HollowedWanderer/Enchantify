package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.common.network.ModVariables;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class StaminaRenderTick {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player.level(), event.player.getY(), event.player.getZ(), event.player);
        }
    }

    public static String execute(LevelAccessor world, double y, double z, Entity entity) {
        return execute(null, world, y, z, entity);
    }

    private static String execute(@Nullable Event event, LevelAccessor world, double y, double z, Entity entity) {
        if (entity == null)
            return "";

        double stamina = entity.getCapability(ModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new ModVariables.PlayerVariables()).combo;
        long roundedStamina = Math.round(stamina); // Round stamina to the nearest whole number
        return "Stamina: " + roundedStamina;
    }
}
