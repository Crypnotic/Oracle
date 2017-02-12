package me.crypnotic.oracle.api;

import org.bukkit.event.Listener;

public interface IController extends Listener {

	default void init() {
	}

	default void shutdown() {
	}

	String getName();

	boolean isActive();

	void setActive(boolean active);
}
