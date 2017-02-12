package me.crypnotic.oracle.controllers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.crypnotic.oracle.Oracle;
import me.crypnotic.oracle.api.AbstractController;

public class EnvironmentController extends AbstractController {

	public EnvironmentController(final Oracle oracle) {
		super(oracle);
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(active && event.toWeatherState());
	}

	@Override
	public String getName() {
		return "environment";
	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);

		if (active) {
			oracle.getServer().getWorlds().forEach((world) -> {
				world.setThundering(false);
			});
		}
	}
}
