package net.hollowed.enchantify.common;

import net.hollowed.enchantify.EnchantifyMod;
import net.hollowed.enchantify.common.network.*;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModKeymaps {
    public static final KeyMapping DASH_KEY = new KeyMapping("key.enchantify.dash_key", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.movement") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                DASH_KEY_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown) {
                int dt = (int) (System.currentTimeMillis() - DASH_KEY_LASTPRESS);
                EnchantifyMod.PACKET_HANDLER.sendToServer(new DashMessage(1, dt));
                assert Minecraft.getInstance().player != null;
                DashMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };
    public static final KeyMapping DOUBLE_JUMP_KEY = new KeyMapping("key.enchantify.double_jump_key", GLFW.GLFW_KEY_SPACE, "key.categories.movement") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                EnchantifyMod.PACKET_HANDLER.sendToServer(new DoubleJumpMessage(0, 0));
                assert Minecraft.getInstance().player != null;
                DoubleJumpMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };
    private static long DASH_KEY_LASTPRESS = 0;

    public static final KeyMapping STRAFE_LEFT = new KeyMapping("key.enchantify.strafe_left", GLFW.GLFW_KEY_A, "key.categories.movement") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeLeftMessage(0, 0));
                assert Minecraft.getInstance().player != null;
                StrafeLeftMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                STRAFE_LEFT_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown) {
                int dt = (int) (System.currentTimeMillis() - STRAFE_LEFT_LASTPRESS);
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeLeftMessage(1, dt));
                assert Minecraft.getInstance().player != null;
                StrafeLeftMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };

    public static final KeyMapping STRAFE_RIGHT = new KeyMapping("key.enchantify.strafe_right", GLFW.GLFW_KEY_D, "key.categories.movement") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeRightMessage(0, 0));
                assert Minecraft.getInstance().player != null;
                StrafeRightMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                STRAFE_RIGHT_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown) {
                int dt = (int) (System.currentTimeMillis() - STRAFE_RIGHT_LASTPRESS);
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeRightMessage(1, dt));
                assert Minecraft.getInstance().player != null;
                StrafeRightMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };

    public static final KeyMapping STRAFE_BACK = new KeyMapping("key.enchantify.strafe_back", GLFW.GLFW_KEY_S, "key.categories.movement") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeBackMessage(0, 0));
                assert Minecraft.getInstance().player != null;
                StrafeBackMessage.pressAction(Minecraft.getInstance().player, 0, 0);
                STRAFE_BACK_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown) {
                int dt = (int) (System.currentTimeMillis() - STRAFE_BACK_LASTPRESS);
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeBackMessage(1, dt));
                assert Minecraft.getInstance().player != null;
                StrafeBackMessage.pressAction(Minecraft.getInstance().player, 1, dt);
            }
            isDownOld = isDown;
        }
    };

    private static long STRAFE_LEFT_LASTPRESS = 0;

    private static long STRAFE_RIGHT_LASTPRESS = 0;

    private static long STRAFE_BACK_LASTPRESS = 0;
    public static final KeyMapping STRAFE = new KeyMapping("key.enchantify.strafe", GLFW.GLFW_KEY_LEFT_SUPER, "key.categories.movement") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                EnchantifyMod.PACKET_HANDLER.sendToServer(new StrafeMessage(0, 0));
                assert Minecraft.getInstance().player != null;
                StrafeMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };


    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(DASH_KEY);
        event.register(DOUBLE_JUMP_KEY);
        event.register(STRAFE_LEFT);
        event.register(STRAFE);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                DASH_KEY.consumeClick();
                DOUBLE_JUMP_KEY.consumeClick();
                STRAFE_LEFT.consumeClick();
                STRAFE.consumeClick();
            }
        }
    }
}
