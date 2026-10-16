package ssal333.elyane.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import ssal333.elyane.armor.FireBootsArmor;
import ssal333.elyane.item.ModItemsIds;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(FireBootsArmor.REPAIRS_FIRE_BOOTS).add(ItemIds.RESIN_BRICK);
        builder(ItemTags.FOOT_ARMOR).add(ModItemsIds.FIRE_BOOTS);
    }
}
