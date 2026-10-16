package ssal333.elyane.block;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import ssal333.elyane.Elyane;

public class ModBlockItemIds {

    public static BlockItemId ATC = create("atc");
    public static BlockItemId CARVED_JUKEBOX = create("carved_jukebox");


    private static BlockItemId create(String name){
        Identifier id = Identifier.fromNamespaceAndPath(Elyane.MOD_ID, name);
        return BlockItemId.create(id, id);
    }
}
