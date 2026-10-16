package ssal333.elyane.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import ssal333.elyane.Elyane;
import ssal333.elyane.block.ModBlocks;

public class ModCreativeModeTab {

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB_RESOURCE_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "creative_tab")
    );

    public static final CreativeModeTab CREATIVE_MODE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.AITRA))
            .displayItems((parameters, output) ->{
                output.accept(ModItems.AITRA);
                output.accept(ModItems.FIRE_BOOTS);
                output.accept(ModItems.MUSIC_REMOTE);
                output.accept(ModBlocks.ATC);
                output.accept(ModBlocks.CARVED_JUKEBOX);
            })
            .title(Component.translatable("creativeTab.elyane"))
            .build();

    public static void registerCreativeModTab(){
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB_RESOURCE_KEY, CREATIVE_MODE_TAB);
    }
}
