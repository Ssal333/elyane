package ssal333.elyane.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import ssal333.elyane.Elyane;

public class ModItemsIds {

    public static final ResourceKey<Item> FIRE_BOOTS = create("fire_boots");
    public static final ResourceKey<Item> AITRAS = create("aitras");
    public static final ResourceKey<Item> MUSIC_REMOTE = create("music_remote");
    public static final ResourceKey<Item> FLIGHT_CODES = create("flight_codes");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Elyane.MOD_ID, name));
    }
}
