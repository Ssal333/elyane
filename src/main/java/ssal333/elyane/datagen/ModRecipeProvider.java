package ssal333.elyane.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import ssal333.elyane.block.ModBlocks;
import ssal333.elyane.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemRegistryLookup = registries.lookupOrThrow(Registries.ITEM);
                shapeless(RecipeCategory.TOOLS, ModItems.AITRAS)
                        .requires(Items.ELYTRA)
                        .requires(Items.LAPIS_BLOCK, 4)
                        .requires(Items.DIAMOND, 4)
                        .unlockedBy(getHasName(Items.ELYTRA), has(Items.ELYTRA))
                        .save(output);

                shaped(RecipeCategory.MISC, ModItems.MUSIC_REMOTE)
                        .define('n', Items.NETHER_BRICK)
                        .define('b', ItemTags.WOODEN_BUTTONS)
                        .pattern(" nb")
                        .pattern("nnn")
                        .pattern("bn ")
                        .unlockedBy(getHasName(Items.NETHER_BRICK), has(Items.NETHER_BRICK))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FIRE_BOOTS)
                        .define('f', Items.FIRE_CHARGE)
                        .define('i', Items.IRON_BOOTS)
                        .pattern("   ")
                        .pattern(" i ")
                        .pattern("f f")
                        .unlockedBy(getHasName(Items.FIRE_CHARGE), has(Items.FIRE_CHARGE))
                        .save(output);

                shaped(RecipeCategory.MISC, ModBlocks.ATC)
                        .define('b', Items.CONCRETE.black())
                        .define('y', Items.CONCRETE.yellow())
                        .define('c', Items.COMPASS)
                        .pattern("byb")
                        .pattern("ycy")
                        .pattern("byb")
                        .unlockedBy(getHasName(Items.COMPASS), has(Items.COMPASS))
                        .save(output);
            }


        };
    }

    @Override
    public String getName() {
        return "ModRecipeProvider";
    }
}
