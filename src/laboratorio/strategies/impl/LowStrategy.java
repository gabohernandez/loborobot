package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public class LowStrategy extends ParentStrategy implements Strategy {

	public LowStrategy(LoboRobot robot) {
		super(robot);
	}

	@Override
	public void applyFirstConfigurations() {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations();
			robot.turnTo(90);
		}
	}

	@Override
	public void nextStep() {
		int angle = robot.heading + 90;
		robot.turnTo(angle);
		robot.turnGunTo(angle);
		robot.ahead(150);
	}

	@Override
	public void onScannedRobot() {
		this.robot.fire(1);
	}

	@Override
	public void onHitByBullet() {
		this.robot.turnGunTo(this.robot.hitByBulletAngle);
		this.robot.turnTo(5);
		this.robot.ahead(moveAmount);
	}

	@Override
	public void onHitWall() {
		this.robot.bearGunTo(90);
		this.robot.turnRight(90);
	}

	@Override
	public void onHitRobot() {
		this.robot.turnTo(5);
	}

}