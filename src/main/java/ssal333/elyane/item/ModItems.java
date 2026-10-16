package ssal333.elyane.item;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import ssal333.elyane.armor.FireBootsArmor;
import ssal333.elyane.component.AdvancedCustomComponent;
import ssal333.elyane.component.ModComponents;
import ssal333.elyane.item.custom.Aitras;
import ssal333.elyane.item.custom.FireBoots;
import ssal333.elyane.item.custom.FlightCodes;
import ssal333.elyane.item.custom.MusicRemote;

import java.util.function.Function;

public class ModItems extends Item {

    public static final Item FIRE_BOOTS = register(
            ModItemsIds.FIRE_BOOTS,
            FireBoots::new,
            new Item.Properties().humanoidArmor(FireBootsArmor.INSTANCE, ArmorType.BOOTS)
                    .durability(FireBootsArmor.BASE_DURABILITY)
                    .component(ModComponents.CHARGE_COMPONENT, 0)
                    .stacksTo(1)
    );

    public static final Item AITRA = register(
            ModItemsIds.AITRA,
            Aitras::new,
            new Item.Properties()
                    .component(
                            DataComponents.EQUIPPABLE,
                            Equippable.builder(
                                    EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                    .setAsset(EquipmentAssets.ELYTRA)
                                    .setDamageOnHurt(false)
                                    .build()
                    )
                    .component(ModComponents.ACTIVE_WINGS, false)
                    .component(ModComponents.FLYING_POWER, 1)
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .stacksTo(1)
                    .durability(200)
                    .component(ModComponents.ARRIVAL_POS, new AdvancedCustomComponent(0,0,0))
                    .component(ModComponents.CHARGE_COMPONENT, 2000)

    );

    public static final Item MUSIC_REMOTE = register(
            ModItemsIds.MUSIC_REMOTE,
            MusicRemote::new,
            new Item.Properties()
                    .stacksTo(1)
    );

    public static final Item FLIGHT_CODES = register(
            ModItemsIds.FLIGHT_CODES,
            FlightCodes::new,
            new Item.Properties()
                    .stacksTo(1)
                    .component(
                            DataComponents.ENCHANTMENT_GLINT_OVERRIDE,
                            true
                    )

    );






    public static Item register(ResourceKey<Item> itemKey, Function<Properties, Item> itemFactory, Item.Properties settings) {

        Item item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModItems(){

    }

    public ModItems(Properties properties) {
        super(properties);
    }
}
