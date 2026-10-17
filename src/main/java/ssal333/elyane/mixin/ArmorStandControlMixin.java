package ssal333.elyane.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.item.ModItems;

@Mixin(Entity.class)
public abstract class ArmorStandControlMixin {

    @Shadow
    public abstract BlockState getBlockStateOn();


    @Shadow
    public int tickCount;

    @Inject(at = @At("HEAD"), method = "tick")
    private void init(CallbackInfo ci){
        if ((Object) this instanceof ArmorStand armorStand){
            if (getBlockStateOn().is(Blocks.MAGMA_BLOCK)){
                ItemStack chestItem = armorStand.getItemBySlot(EquipmentSlot.CHEST);
                ItemStack feetItem = armorStand.getItemBySlot(EquipmentSlot.FEET);
                if (tickCount % 20 == 0) {
                    if (chestItem.is(ModItems.AITRAS)) {
                        int charge = chestItem.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
                        if (charge < 2000) {
                            chestItem.set(ModComponents.CHARGE_COMPONENT, charge + 1);
                        }
                    }
                    if (feetItem.is(ModItems.FIRE_BOOTS)){
                        int charge = feetItem.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
                        if (charge < 4){
                            feetItem.set(ModComponents.CHARGE_COMPONENT, charge + 1);
                        }
                    }
                }
            }
        }

    }

}