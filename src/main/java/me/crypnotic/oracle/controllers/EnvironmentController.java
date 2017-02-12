package me.crypnotic.oracle.controllers;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

import lombok.Getter;
import me.crypnotic.oracle.Oracle;
import me.crypnotic.oracle.api.IController;

public class EnvironmentController implements IController {

	private final Oracle oracle;
	private ConfigurationSection section;
	@Getter
	private boolean active;

	public EnvironmentController(final Oracle oracle) {
		this.oracle = oracle;
	}

	@Override
	public void init() {
		FileConfiguration config = oracle.getConfig();
		if (config.isConfigurationSection("environment")) {
			config.set("environment.active", true);
		}
		section = oracle.getConfig().getConfigurationSection("environment");

		active = section.getBoolean("active");

		oracle.getServer().getPluginManager().registerEvents(this, oracle);
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if (active) {
			event.setCancelled(event.toWeatherState());
		}
	}

	@Override
	public String getName() {
		return "Environment";
	}

	public void setActive(boolean active) {
		this.active = active;
		section.set("active", active);

		if (active) {
			oracle.getServer().getWorlds().forEach((world) -> {
				world.setThundering(false);
			});
		}
	}
}
