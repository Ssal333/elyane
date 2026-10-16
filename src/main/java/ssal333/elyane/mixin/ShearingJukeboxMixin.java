package ssal333.elyane.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ssal333.elyane.block.ModBlocks;

@Mixin(ShearsItem.class)
public class ShearingJukeboxMixin {
    @Inject(at = @At("HEAD"), method = "useOn", cancellable = true)
    private void init(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockState = level.getBlockState(pos);
        if (blockState.is(Blocks.JUKEBOX)){
            level.setBlockAndUpdate(pos, ModBlocks.CARVED_JUKEBOX.defaultBlockState());
            level.playSound(context.getPlayer(), pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}