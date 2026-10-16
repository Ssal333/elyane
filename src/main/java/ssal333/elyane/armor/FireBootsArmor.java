package ssal333.elyane.armor;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.*;
import ssal333.elyane.Elyane;

import java.util.Map;

public class FireBootsArmor {
    public static final int BASE_DURABILITY = 100;

    public static final ResourceKey<EquipmentAsset> FIRE_BOOTS_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "fire_boots"));

    public static final TagKey<Item> REPAIRS_FIRE_BOOTS = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "repairs_fire_boots"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.BOOTS, 2,
                    ArmorType.LEGGINGS, 5,
                    ArmorType.CHESTPLATE, 6,
                    ArmorType.HELMET, 2

            ),
            8,
            SoundEvents.ARMOR_EQUIP_CHAIN,
            0,
            0,
            REPAIRS_FIRE_BOOTS,
            FIRE_BOOTS_ARMOR_MATERIAL_KEY
    );



}



