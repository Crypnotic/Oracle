package me.crypnotic.oracle.managers;

import java.util.HashSet;
import java.util.Optional;

import me.crypnotic.oracle.Oracle;
import me.crypnotic.oracle.api.IController;
import me.crypnotic.oracle.controllers.EnvironmentController;

public class ControllerManager {

	private final Oracle oracle;
	private final HashSet<IController> controllers;

	public ControllerManager(final Oracle oracle) {
		this.oracle = oracle;
		this.controllers = new HashSet<IController>();
	}

	public void load() {
		this.controllers.add(new EnvironmentController(oracle));
	}

	public void init() {
		this.controllers.forEach((controller) -> {
			controller.init();
		});
	}

	public void shutdown() {
		this.controllers.forEach((controller) -> {
			controller.shutdown();
		});
	}

	public <T extends IController> Optional<T> get(Class<T> clazz) {
		for (IController controller : controllers) {
			if (controller.getClass().isAssignableFrom(clazz)) {
				return Optional.of(clazz.cast(controller));
			}
		}
		return Optional.empty();
	}
}
