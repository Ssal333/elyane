package ssal333.elyane.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import ssal333.elyane.Elyane;

public class ModEffects{

    public static final Holder<MobEffect> INCREASING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "increasing"), new IncreasingEffect());

    public static void registerModEffets(){

    }
}
