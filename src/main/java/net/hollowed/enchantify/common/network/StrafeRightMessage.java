package net.hollowed.enchantify.common.network;

import net.hollowed.enchantify.EnchantifyMod;
import net.hollowed.enchantify.common.event.StrafeRight;
import net.hollowed.enchantify.common.event.StrafeRightRelease;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StrafeRightMessage {
    int type, pressedms;

    public StrafeRightMessage(int type, int pressedms) {
        this.type = type;
        this.pressedms = pressedms;
    }

    public StrafeRightMessage(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressedms = buffer.readInt();
    }

    public static void buffer(StrafeRightMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressedms);
    }

    public static void handler(StrafeRightMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(Objects.requireNonNull(context.getSender()), message.type, message.pressedms);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        // security measure to prevent arbitrary chunk generation
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {

            StrafeRight.execute(entity);
        }
        if (type == 1) {

            StrafeRightRelease.execute(entity);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        EnchantifyMod.addNetworkMessage(StrafeRightMessage.class, StrafeRightMessage::buffer, StrafeRightMessage::new, StrafeRightMessage::handler);
    }
}
