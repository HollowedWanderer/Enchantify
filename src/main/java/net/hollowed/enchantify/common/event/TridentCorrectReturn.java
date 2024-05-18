package net.hollowed.enchantify.common.event;

import net.hollowed.enchantify.EnchantifyMod;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "enchantify", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TridentCorrectReturn {
    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Stop event) {
        if (event.getEntity() instanceof Player)
            EnchantifyMod.onItemUseFinish((Player) event.getEntity(), event.getItem(), event.getDuration());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EnchantifyMod.onPlayerTick(event.player);
    }
}
