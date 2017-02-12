package me.crypnotic.oracle.managers;

import java.util.HashSet;
import java.util.Optional;

import me.crypnotic.oracle.Oracle;
import me.crypnotic.oracle.api.AbstractController;
import me.crypnotic.oracle.controllers.EnvironmentController;

public class ControllerManager {

	private final Oracle oracle;
	private final HashSet<AbstractController> controllers;

	public ControllerManager(final Oracle oracle) {
		this.oracle = oracle;
		this.controllers = new HashSet<AbstractController>();
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

	public <T extends AbstractController> Optional<T> get(Class<T> clazz) {
		for (AbstractController controller : controllers) {
			if (controller.getClass().isAssignableFrom(clazz)) {
				return Optional.of(clazz.cast(controller));
			}
		}
		return Optional.empty();
	}
}
