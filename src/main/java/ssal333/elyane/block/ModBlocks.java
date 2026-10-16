package ssal333.elyane.block;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import ssal333.elyane.block.custom.AtcBlock;

import java.util.function.Function;

public class ModBlocks {

    public static final Block ATC = register(
            ModBlockItemIds.ATC,
            AtcBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(3)
                    .requiresCorrectToolForDrops()
                    .instrument(NoteBlockInstrument.COW_BELL),
            false



    );

    public static final Block CARVED_JUKEBOX = register(
            ModBlockItemIds.CARVED_JUKEBOX,
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(2),
            true
    );




    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean equippable) {
        // Create the block instance
        Block block = register(id.block(), blockFactory, properties);

        BlockItem blockItem;
        // Create the block item instance
        if (equippable) {
            blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()).component(
                    DataComponents.EQUIPPABLE,
                    Equippable.builder(EquipmentSlot.HEAD).setEquipSound(SoundEvents.NOTE_BLOCK_HAT).setDamageOnHurt(false).build()));
        }else{
            blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        }
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);


        return block;
    }

    private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties){
        Block block = blockFactory.apply(properties.setId(id));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }


    public static void registerModBlocks(){

    }
}
