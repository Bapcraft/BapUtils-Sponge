package org.bapcraft.baputilssponge;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Plugin(id = "baputils", name = "BapUtils-Sponge", version = "69.69.69")
public class BapUtils {

	@Inject
	private Game game;

	@Inject
	private EventManager eventManager;

	@Inject
	private Logger logger;

	private Path configPath;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> configLoader;

	private BapUtilsConfig config;

	@Inject
	public BapUtils(@DefaultConfig(sharedRoot = true) final Path configPath) {
		this.configPath = configPath;
	}

	@Listener
	public void onInit(GameInitializationEvent event) {

		this.logger.info("Bapcraft: What is dead may never die.");

		// Load config.
		Asset cfgAsset = this.game.getAssetManager().getAsset(this, "default.conf").get();
		try {

			File configFile = this.configPath.toFile();
			if (!configFile.exists()) {
				cfgAsset.copyToFile(this.configPath);
			}

			ConfigurationNode root = this.configLoader.load();
			this.config = root.getValue(TypeToken.of(BapUtilsConfig.class));

		} catch (IOException e) {
			this.logger.error("Unable to load config!", e);
			return;
		} catch (ObjectMappingException e) {
			this.logger.error("Unable to parse config!", e);
			return;
		}

		if (this.config == null) {
			this.logger.error("Config is null!  This is a bug!");
			return;
		}

		// Setup listeners.
		if (this.config.greentext) {
			this.eventManager.registerListeners(this, new MemeArrowsListener());
		}

		// TODO All the other stuff.

	}

}
