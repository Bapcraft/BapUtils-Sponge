package org.bapcraft.baputilssonge;

import java.io.IOException;
import java.nio.file.Path;

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
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private Path configPath;
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> configLoader;

	private BapUtilsConfig config;
	
	@Listener
	public void onInit(GameInitializationEvent event) throws Exception {
		
		this.logger.info("ok by cheese");
		
		// Load config.
		Asset cfgAsset = this.game.getAssetManager().getAsset(this, "default.conf").get();
		try {
			
			if (!this.configPath.toFile().exists()) {
				cfgAsset.copyToFile(this.configPath);
			}
			
			ConfigurationNode root = this.configLoader.load();
			this.config = root.getValue(TypeToken.of(BapUtilsConfig.class));
			
		} catch (IOException e) {
			this.logger.error("Unable to load config!");
			throw e;
		} catch (ObjectMappingException e) {
			this.logger.error("Unable to parse config!");
			throw e;
		}
		
		// Setup listeners.
		//this.listener = new FcListener(this.config, this.game, this.logger);
		//this.eventManager.registerListeners(this, this.listener);
		
	}
	
}
