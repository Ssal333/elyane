package ssal333.elyane.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import ssal333.elyane.block.ModBlocks;

import java.util.*;

public class MusicRemote extends Item {

    private SoundInstance soundInstance;

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        boolean play = false;
        SoundEvent soundEvent;
        SimpleSoundInstance soundInstance = null;

        if (this.soundInstance != null && !level.isClientSide()){
            Minecraft.getInstance().getSoundManager().stop(this.soundInstance);
            this.soundInstance = null;
            return InteractionResult.SUCCESS;
        }

        if (!level.isClientSide()) {

            ItemStack itemStack = player.getOffhandItem();

            if (itemStack.has(DataComponents.JUKEBOX_PLAYABLE) && player.getItemBySlot(EquipmentSlot.HEAD).is(ModBlocks.CARVED_JUKEBOX.asItem())) {
                soundEvent = Objects.requireNonNull(itemStack.get(DataComponents.JUKEBOX_PLAYABLE)).song().value().soundEvent().value();
                soundInstance = SimpleSoundInstance.forUI(soundEvent, 1.0f, 2.0f);
                this.soundInstance = soundInstance;
                play = true;
            }
        }

        if (play){
            Minecraft.getInstance().getSoundManager().play(soundInstance);
        }





        return InteractionResult.SUCCESS;
    }


    public MusicRemote(Properties properties) {
        super(properties);
    }
}
