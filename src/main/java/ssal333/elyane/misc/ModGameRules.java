package ssal333.elyane.misc;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import ssal333.elyane.Elyane;

public class ModGameRules {
    public static final GameRule<Double> TAKE_OFF_SPEED_DOUBLE_GAMERULE = GameRuleBuilder
            .forDouble(3.0)
            .category(GameRuleCategory.MISC)
            .range(0.5, 100.0)
            .buildAndRegister(Identifier.fromNamespaceAndPath(Elyane.MOD_ID, "take_off_speed"));

    public static void registerGameRules(){

    }
}
