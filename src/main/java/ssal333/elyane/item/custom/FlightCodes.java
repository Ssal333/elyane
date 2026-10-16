package ssal333.elyane.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import ssal333.elyane.component.AdvancedCustomComponent;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.item.ModItems;

public class FlightCodes extends Item {

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {

            ItemStack chestItem = player.getItemBySlot(EquipmentSlot.CHEST);
            ItemStack usedItem = player.getActiveItem();

            if (chestItem.is(ModItems.AITRA)) {
                AdvancedCustomComponent component = usedItem.get(ModComponents.ARRIVAL_POS);
                int x = component.x();
                int y = component.y();
                int z = component.z();

                chestItem.set(ModComponents.ARRIVAL_POS, new AdvancedCustomComponent(x, y, z));
                player.setItemSlot(hand.asEquipmentSlot(), new ItemStack(Items.PAPER));




            }

        }


        return InteractionResult.SUCCESS;
    }

    public FlightCodes(Properties properties) {
        super(properties);
    }
}
