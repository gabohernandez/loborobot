package laboratorio.strategies.impl;

import laboratorio.LaboRobot22;
import laboratorio.strategies.Strategy;

public abstract class ParentStrategy implements Strategy {

	protected int moveAmount;
	protected boolean firstConfigurationsApplied = false;

	public ParentStrategy() {
	}

	public void applyFirstConfigurations(LaboRobot22 robot) {
		// Calculo cuanto me muevo para el el campo de batalla
		moveAmount = Math.max(robot.fieldWidth, robot.fieldHeight);
	}
}
