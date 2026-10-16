package ssal333.elyane.event;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.effect.ModEffects;

public class ElytraOpen implements EntityElytraEvents.Custom, EntityElytraEvents.Allow {
    private static int count = 0;
    private double calculateArrivalSpeed(int flyingPower, double maxSpeed){
        return ((double) flyingPower*maxSpeed)/100.0;
    }

    @Override
    public boolean useCustomElytra(LivingEntity entity, boolean tickElytra) {


        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
        int flyingPower = itemStack.getOrDefault(ModComponents.FLYING_POWER, 0);
        final double maxSpeed = 17.0;


        if (entity instanceof Player player){



            double absoluteSpeed = player.getKnownMovement().length();
            double arrivalSpeed = calculateArrivalSpeed(flyingPower, maxSpeed);
            int charge = itemStack.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
            Level level = player.level();


            if (entity.hasEffect(ModEffects.INCREASING)){
                entity.removeEffect(ModEffects.INCREASING);
                player.setSprinting(false);
            }

            if (itemStack.getOrDefault(ModComponents.ACTIVE_WINGS, false)){

                if (player.getFallFlyingTicks() % 10*20 == 0) {
                    itemStack.set(ModComponents.CHARGE_COMPONENT, charge - 1);
                }

                if (Minecraft.getInstance().options.keyJump.isDown() && player.getFallFlyingTicks() % 2 == 0 && flyingPower< 100) {

                    itemStack.set(ModComponents.FLYING_POWER, flyingPower +1);
                }

                if (Minecraft.getInstance().options.keyShift.isDown() && player.getFallFlyingTicks() % 2 == 0 && flyingPower>0){
                    itemStack.set(ModComponents.FLYING_POWER, flyingPower-1);
                }

                if (flyingPower > 0 && absoluteSpeed<=maxSpeed) {


                    if (absoluteSpeed<arrivalSpeed){
                        player.addDeltaMovement(player.getLookAngle().multiply(0.1,0.1,0.1));

                        player.hurtMarked = true;
                    }


                }



            }
        }
        return true;
    }

    @Override
    public boolean allowElytraFlight(LivingEntity entity) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if ((itemStack.getOrDefault(ModComponents.ACTIVE_WINGS, false)&&itemStack.getOrDefault(ModComponents.CHARGE_COMPONENT, 0)>0) || itemStack.is(Items.ELYTRA)){

            return true;
        }
        return false;
    }
}
