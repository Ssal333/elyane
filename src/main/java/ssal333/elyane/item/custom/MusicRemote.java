package ssal333.elyane.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackLinkedSet;
import net.minecraft.world.level.Level;
import org.lwjgl.system.ffm.mapping.Mapping;
import ssal333.elyane.block.ModBlocks;

import java.util.*;

public class MusicRemote extends Item {

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            Set<ItemStack> set = ItemStackLinkedSet.createTypeAndComponentsSet();
            for (int i = 0; i <= 45; i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.has(DataComponents.JUKEBOX_PLAYABLE)) {
                    set.add(itemStack);
                }
            }

            if (!set.isEmpty() && player.getItemBySlot(EquipmentSlot.HEAD).is(ModBlocks.CARVED_JUKEBOX.asItem())) {
                SoundEvent soundEvent = set.stream().findAny().get().get(DataComponents.JUKEBOX_PLAYABLE).song().value().soundEvent().value();
                level.playSound(null, player, soundEvent, SoundSource.PLAYERS, 2.0f, 1f);
            }
        }



        return InteractionResult.SUCCESS;
    }


    public MusicRemote(Properties properties) {
        super(properties);
    }
}
