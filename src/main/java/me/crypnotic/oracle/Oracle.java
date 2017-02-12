package me.crypnotic.oracle;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.crypnotic.oracle.managers.ControllerManager;

public class Oracle extends JavaPlugin {

	@Getter
	private ControllerManager controllerManager;

	@Override
	public void onLoad() {
		this.controllerManager = new ControllerManager(this);
	}

	@Override
	public void onEnable() {
		saveDefaultConfig();

		this.controllerManager.init();
	}

	@Override
	public void onDisable() {
		this.controllerManager.shutdown();
	}
}
