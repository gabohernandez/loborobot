package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public abstract class ParentStrategy implements Strategy {

	protected LoboRobot robot;

	public ParentStrategy(LoboRobot robot) {

		this.robot = robot;
	}

}
