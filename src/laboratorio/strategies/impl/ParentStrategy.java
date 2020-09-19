package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public abstract class ParentStrategy implements Strategy {

	protected LoboRobot robot;
	protected int moveAmount;
	protected boolean firstConfigurationsApplied = false;

	public ParentStrategy(LoboRobot robot) {
		this.robot = robot;
	}

	public void applyFirstConfigurations() {
		// Calculo cuanto me muevo para el el campo de batalla
		moveAmount = Math.max(robot.fieldWidth, robot.fieldHeight);
	}
}
