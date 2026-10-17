package ssal333.elyane.component;

import com.mojang.serialization.Codec;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import ssal333.elyane.Elyane;

public class ModComponents {
    public static final DataComponentType<Integer> CHARGE_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "charge_component"),
            DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build()
    );

    public static final DataComponentType<Boolean> ACTIVE_WINGS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "active_wings"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL).build()
    );

    public static final DataComponentType<Integer> FLYING_POWER = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "flying_power"),
            DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build()
    );



    public static final DataComponentType<AdvancedCustomComponent> ARRIVAL_POS = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "arrival_pos"),
            DataComponentType.<AdvancedCustomComponent>builder().persistent(AdvancedCustomComponent.CODEC).networkSynchronized(AdvancedCustomComponent.STREAM_CODEC).build()
    );




}
