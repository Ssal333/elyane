package ssal333.elyane.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ssal333.elyane.item.ModItems;

@Mixin(FireworkRocketItem.class)
public class BlockFireworkRocketMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void init(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir){
        if (player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.AITRAS)){
            cir.setReturnValue(InteractionResult.FAIL);
        }

    }

}