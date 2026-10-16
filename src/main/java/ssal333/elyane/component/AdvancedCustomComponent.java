package ssal333.elyane.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record AdvancedCustomComponent(int x, int y, int z) {

    public static final Codec<AdvancedCustomComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.INT.fieldOf("x").forGetter(AdvancedCustomComponent::x),
                Codec.INT.fieldOf("y").forGetter(AdvancedCustomComponent::y),
                Codec.INT.fieldOf("z").forGetter(AdvancedCustomComponent::z)
        ).apply(builder, AdvancedCustomComponent::new);

    });

    public static final StreamCodec<FriendlyByteBuf, AdvancedCustomComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, AdvancedCustomComponent::x,
            ByteBufCodecs.VAR_INT, AdvancedCustomComponent::y,
            ByteBufCodecs.VAR_INT, AdvancedCustomComponent::z,
            AdvancedCustomComponent::new
    );
}
