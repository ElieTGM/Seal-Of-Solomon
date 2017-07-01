package me.elietgm.solomon.player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.elietgm.solomon.utils.internals.LoggerUtility;

public class PlayerConfiguration {

	Plugin p;
	String uuid;
	File playerFile;
	FileConfiguration playerConfiguration;
	
	/**
	 * Let's create a PlayerConfiguration instance using PlayerConfiguration(uuid);
	 * @param uuid
	 */
	public PlayerConfiguration(String uuid, Plugin p) {
		this.uuid = uuid.toLowerCase();
		this.p = p;
		this.playerFile = new File(p.getDataFolder() + File.separator + "playerData", uuid + "_data.yml");
	}
	
	/**
	 * Create a configuration if he doesn't have one already.
	 */
	public void createConfiguration() {
		
		if(!playerFile.exists()) {		
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Let's go ahead and reload the configuration.
			reloadConfig();
			
			List<String> colorValues = Arrays.asList("lime", "yellow", "brown",
					"orange", "white", "light_blue",
					"cyan", "magenta", "black");
			
			for(String values : colorValues) {
				playerConfiguration.set(values + ".world", null);
				playerConfiguration.set(values + ".x", null);
				playerConfiguration.set(values + ".y", null);	
				playerConfiguration.set(values + ".z", null);	
			}		
			
			saveConfig();
		}
	}
	
	/**
	 * Check if exists.
	 */
	public boolean exists() {
		boolean exists = false;
		
		if(!playerFile.exists()) {
			exists = false;
		} else {
			exists = true;
		}
		
		return exists;
	}
	
	/**
	 * Let's get the config of the player.
	 * @return
	 */
	public FileConfiguration getConfig() {
	    return playerConfiguration;
	}
	
	/**
	 * Let's save the custom-player config file by using this following method
	 */	
	public void saveConfig() {
	    try {
	        getConfig().save(playerFile);
	    } catch (IOException ex) {
	        LoggerUtility.log(p,  Level.SEVERE, "Solomon", "Could not save config to " + playerFile + " :" + ex);
	    }
	}
	
	/**
	 * Let's reload the config using this method
	 */
	public void reloadConfig() {
	    playerConfiguration = YamlConfiguration.loadConfiguration(playerFile);
	}
	
}
