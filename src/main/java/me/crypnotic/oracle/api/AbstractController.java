package me.crypnotic.oracle.api;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;

import lombok.Getter;
import me.crypnotic.oracle.Oracle;

public abstract class AbstractController implements Listener {

	protected final Oracle oracle;
	private ConfigurationSection section;
	@Getter
	protected boolean active;

	protected AbstractController(final Oracle oracle) {
		this.oracle = oracle;
	}

	public void init() {
		this.section = oracle.getConfig().getConfigurationSection(getName());
		this.active = section.getBoolean("active", true);

		oracle.getServer().getPluginManager().registerEvents(this, oracle);
	}

	public void shutdown() {
	}

	public ConfigurationSection getConfigurationSection() {
		return section;
	}

	public void setActive(boolean active) {
		section.set("active", active);
	}

	public abstract String getName();
}
