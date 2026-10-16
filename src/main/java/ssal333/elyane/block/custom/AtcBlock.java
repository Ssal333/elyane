package ssal333.elyane.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import ssal333.elyane.component.AdvancedCustomComponent;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.item.ModItems;


public class AtcBlock extends Block {

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            ItemStack offhandItem = player.getOffhandItem();
            ItemStack mainHandItem = player.getMainHandItem();
            LodestoneTracker dataComponent = null;

            if ((offhandItem.is(Items.COMPASS) && mainHandItem.is(Items.PAPER)) || (mainHandItem.is(Items.COMPASS) && offhandItem.is(Items.PAPER))) {
                if (mainHandItem.is(Items.PAPER)) {

                    if (offhandItem.has(DataComponents.LODESTONE_TRACKER)) {
                        dataComponent = offhandItem.get(DataComponents.LODESTONE_TRACKER);

                    }
                    mainHandItem.consume(1, player);

                } else {
                    if (mainHandItem.has(DataComponents.LODESTONE_TRACKER)) {
                        dataComponent = mainHandItem.get(DataComponents.LODESTONE_TRACKER);
                    }
                    offhandItem.consume(1, player);
                }

                ItemStack flightCodes = new ItemStack(ModItems.FLIGHT_CODES);
                BlockPos arrivalPos = new BlockPos(0, 0, 0);

                if (dataComponent != null) {
                    arrivalPos = dataComponent.target().isPresent() ? dataComponent.target().get().pos() : new BlockPos(0, 0, 0);
                }

                flightCodes.set(ModComponents.ARRIVAL_POS, new AdvancedCustomComponent(arrivalPos.getX(), arrivalPos.getY(), arrivalPos.getZ()));

                player.addItem(flightCodes);

                return InteractionResult.SUCCESS;
            } else {
                player.sendOverlayMessage(Component.translatable("elyane.text.use_paper_compass"));
            }
        }

        return InteractionResult.SUCCESS;
    }


    public AtcBlock(Properties properties) {
        super(properties);
    }
}
