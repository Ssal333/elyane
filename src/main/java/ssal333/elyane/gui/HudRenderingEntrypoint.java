package ssal333.elyane.gui;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3x2fStack;
import ssal333.elyane.Elyane;
import ssal333.elyane.component.AdvancedCustomComponent;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.item.ModItems;
import ssal333.elyane.item.custom.Aitras;

import java.util.Dictionary;
import java.util.Hashtable;

// #region hud
public class HudRenderingEntrypoint implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Attach our rendering code to before the chat hud layer. Our layer will render right before the chat. The API will take care of z spacing.
        HudElementRegistry.attachElementBefore(VanillaHudElements.AIR_BAR, Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "before_air_bar"), HudRenderingEntrypoint::extract);
    }

    public static int calculateSliderX(int minX, int maxX, int flyingPower){

        return minX + (int) ((maxX - minX) * (flyingPower/100.0f));
    }


    private static void extract(GuiGraphicsExtractor graphics, DeltaTracker tickCounter) {
        Identifier textureHotbar = Identifier.withDefaultNamespace("textures/gui/sprites/hud/hotbar.png");
        Identifier textureThrottle = Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "textures/gui/sprites/hud/throttle.png" );
        Identifier textureSlider = Identifier.withDefaultNamespace("textures/gui/sprites/widget/slider.png");
        Identifier textureSliderHandle = Identifier.withDefaultNamespace("textures/gui/sprites/widget/slider_handle.png");
        Identifier textureHotbarSelection = Identifier.withDefaultNamespace("textures/gui/sprites/hud/hotbar_selection.png");
        Identifier textureAltitude = Identifier.withDefaultNamespace("textures/mob_effect/jump_boost.png");

        final int minxX = 535;
        final int maxX = 400;

        Dictionary<Integer, Integer> slotAndPosition = new Hashtable<>();
        slotAndPosition.put(0, 3);
        int i = 0;
        while (i < 9){
            slotAndPosition.put(i, 3 + i*20);
            i++;
        }


        Matrix3x2fStack matrices = graphics.pose();
        LocalPlayer localPlayer = Minecraft.getInstance().player;

        assert localPlayer != null;
        if (localPlayer.hasItemInSlot(EquipmentSlot.CHEST)) {
            ItemStack itemStack = localPlayer.getItemBySlot(EquipmentSlot.CHEST);
            if (itemStack.is(ModItems.AITRAS) && itemStack.getOrDefault(ModComponents.ACTIVE_WINGS, false)) {

                int flyingPower = itemStack.getOrDefault(ModComponents.FLYING_POWER, 0);
                double absoluteSpeed = localPlayer.getKnownMovement().length();

                matrices.pushMatrix();
                matrices.rotate((float) Math.toRadians(90));
                matrices.translate(50, -115);
                graphics.blit(RenderPipelines.GUI_TEXTURED, textureHotbar, -46, 90, 0, 0, 182, 22, 182, 22);
                matrices.popMatrix();

                matrices.pushMatrix();
                graphics.item(Items.COAL.getDefaultInstance(), 5, 7);
                graphics.item(Items.WIND_CHARGE.getDefaultInstance(), 6, 27);
                graphics.item(Items.RAIL.getDefaultInstance(), 6, 67);
                graphics.item(Items.OAK_SAPLING.getDefaultInstance(), 5, 87);
                graphics.item(Items.CLOCK.getDefaultInstance(), 6, 107);
                graphics.item(Items.GLAZED_TERRACOTTA.magenta().getDefaultInstance(), 6, 127);
                matrices.popMatrix();

                matrices.pushMatrix();
                matrices.scale(0.85f);
                graphics.blit(RenderPipelines.GUI_TEXTURED, textureAltitude, 7, 56, 0, 0, 18, 18, 18, 18);
                matrices.popMatrix();

                matrices.pushMatrix();
                graphics.blit(RenderPipelines.GUI_TEXTURED, textureThrottle, 390, 5, 0, 0, 86, 165, 86, 165);
                matrices.popMatrix();

                matrices.pushMatrix();
                matrices.scaleAround(0.7f, 390, 5);
                matrices.rotateAbout((float) Math.toRadians(90), 390, 5);
                matrices.translate(17, -71);
                graphics.blit(RenderPipelines.GUI_TEXTURED, textureSlider, 390, 5, 0, 0, 200, 20, 200, 20);
                matrices.popMatrix();

                matrices.pushMatrix();
                matrices.rotateAbout((float) Math.toRadians(90), 390, 5);
                graphics.blit(RenderPipelines.GUI_TEXTURED, textureSliderHandle, calculateSliderX(minxX,maxX, flyingPower), -48, 0, 0, 8, 20, 8, 20);
                matrices.popMatrix();

                matrices.pushMatrix();
                graphics.text(Minecraft.getInstance().font, "100%", 398, 20, 0xFFF00000, false);
                graphics.text(Minecraft.getInstance().font, "50%", 400, 80, 0xFFF00000, false);
                graphics.text(Minecraft.getInstance().font, "0%", 403, 148, 0xFFF00000, false);
                matrices.popMatrix();



                matrices.pushMatrix();
                int slot = localPlayer.getInventory().getSelectedSlot();
                int flyingSpeed = (int) Math.round(absoluteSpeed);
                int altitude = Aitras.calculateDistanceFromGround(localPlayer, localPlayer.level());

                AdvancedCustomComponent component = itemStack.getOrDefault(ModComponents.ARRIVAL_POS, new AdvancedCustomComponent(0,0,0));
                int x = component.x();
                int y = component.y();
                int z = component.z();
                Vec3 pos = localPlayer.position();
                double distance = pos.distanceTo(new Vec3(x, y, z));
                long distanceRounded = Math.round(distance);
                int charge = itemStack.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
                String biomeName = localPlayer.level().getBiome(localPlayer.getOnPos()).getRegisteredName().split(":")[1];
                int fallFlyingTime = localPlayer.getFallFlyingTicks() / 20;

                Vec3 lookAngle = localPlayer.getLookAngle();
                double xL = lookAngle.x();
                double zL = lookAngle.z();
                double yL = lookAngle.y();

                double horizontalDistance = Math.sqrt(xL*xL+zL*zL);
                double angle = Math.toDegrees(Math.atan2(yL, horizontalDistance));


                graphics.blit(RenderPipelines.GUI_TEXTURED, textureHotbarSelection, 2, slotAndPosition.get(slot), 0, 0, 24, 23, 24, 23);



                String text;
                text = switch (slot) {
                    case 0 -> "Energy: " + charge + "/2000";
                    case 1 -> "Flying speed: " + (flyingSpeed == 0 ? "~0" : flyingSpeed)  + " blocks/second";
                    case 2 -> "Altitude from ground: " + (altitude == -1 ? "Too high" : altitude);
                    case 3 -> "Distance to airport: " + distanceRounded + " blocks";
                    case 4 -> "Biome: " + biomeName;
                    case 5 -> "Flight time: " + fallFlyingTime;
                    case 6 -> "Pitch: " + Math.round(angle) + "°";
                    default -> "*random empty slot*";
                };

                graphics.centeredText(Minecraft.getInstance().font, text, 200, 13, 0xFFF00000);
                matrices.popMatrix();

                matrices.pushMatrix();
                graphics.outline(100, 7, 200, 20, 0xFF000040);
                matrices.popMatrix();





            }
        }



    }
}
// #endregion hud
