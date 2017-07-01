package me.elietgm.solomon.utils.internals;

import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

public class LoggerUtility {

	/**
	 * A class to log literally everything.
	 */	
	public static void log(Plugin p, Level l, String prefix, String message) {
		p.getLogger().log(l, prefix + " | " + message);
	}
	
}
