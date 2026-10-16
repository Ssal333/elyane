package ssal333.elyane.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import ssal333.elyane.Elyane;
import ssal333.elyane.component.ModComponents;

import java.util.function.Consumer;

public class FireBoots extends Item {

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        int charge = itemStack.getOrDefault(ModComponents.CHARGE_COMPONENT, 0);
        Elyane.LOGGER.info("{}", charge);
        String chargeSchema = switch (charge) {
            case 1 -> "⚡▊▊▊▊";
            case 2 -> "⚡⚡▊▊▊";
            case 3 -> "⚡⚡⚡▊▊";
            case 4 -> "⚡⚡⚡⚡▊";
            default -> "▊▊▊▊▊";
        };

        builder.accept(Component.literal(chargeSchema));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }


    public FireBoots(Properties properties) {
        super(properties);
    }
}
