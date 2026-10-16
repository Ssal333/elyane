package ssal333.elyane.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.item.ModItems;
import ssal333.elyane.misc.ModGameRules;

import java.util.function.Consumer;


public class Aitras extends Item {



    public static int calculateDistanceFromGround(Entity entity, Level level){

        BlockPos onPos = entity.getOnPos();
        BlockPos newPos = onPos;
        int count = 0;



        while (count < 500) {
            if (count == 0){
                if (level.getBlockState(onPos).is(Blocks.AIR)){
                    count+=1;
                    newPos = onPos.below();
                }else{
                    break;
                }
            } else if (count > 0) {
                if (level.getBlockState(newPos).is(Blocks.AIR)){
                    count+=1;
                    newPos = newPos.below();
                }else{
                    break;
                }

            }
        }

        if (level.isEmptyBlock(newPos)){
            count = -1;
        }
        return count;
    }


    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {

        if (owner instanceof Player player && !level.isClientSide()) {

            boolean isEquipped = player.getItemBySlot(EquipmentSlot.CHEST) == itemStack;
            Vec3 forward = player.getForward();
            int charge = itemStack.getOrDefault(ModComponents.CHARGE_COMPONENT,0);


            if ((player.onGround())){
                if (calculateDistanceFromGround(owner, level) != -1) {
                    itemStack.set(ModComponents.ACTIVE_WINGS, false);
                    itemStack.set(ModComponents.FLYING_POWER, 1);
                }
            }


            if (Boolean.FALSE.equals(itemStack.get(ModComponents.ACTIVE_WINGS)) && isEquipped && charge>0) {
                float speed = player.getSpeed();
                if(level instanceof ServerLevel serverLevel) {
                    double takeOffSpeed = serverLevel.getGameRules().get(ModGameRules.TAKE_OFF_SPEED_DOUBLE_GAMERULE);
                    if (speed > takeOffSpeed && player.getItemBySlot(EquipmentSlot.FEET).is(ModItems.FIRE_BOOTS)) {

                        itemStack.set(ModComponents.ACTIVE_WINGS, true);
                        player.setDeltaMovement(new Vec3(forward.x() * 10, 2, forward.z() * 10));
                        player.hurtMarked = true;


                    }
                }
            }else if(!isEquipped){
                itemStack.set(ModComponents.ACTIVE_WINGS, false);
            }





        }
        super.inventoryTick(itemStack, level, owner, slot);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        int charge = itemStack.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
        builder.accept(Component.literal(charge + "/2000"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    public Aitras(Properties properties) {
        super(properties);
    }
}
