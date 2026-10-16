package ssal333.elyane;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ssal333.elyane.block.ModBlocks;
import ssal333.elyane.effect.ModEffects;
import ssal333.elyane.event.*;
import ssal333.elyane.item.ModCreativeModeTab;
import ssal333.elyane.item.ModItems;


public class Elyane implements ModInitializer {
	public static final String MOD_ID = "elyane";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModEffects.registerModEffets();
		ModItems.registerModItems();
		ModCreativeModeTab.registerCreativeModTab();
		ModBlocks.registerModBlocks();
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new DamageEvent());
		EntityElytraEvents.CUSTOM.register(new ElytraOpen());
		EntityElytraEvents.ALLOW.register(new ElytraOpen());



		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
