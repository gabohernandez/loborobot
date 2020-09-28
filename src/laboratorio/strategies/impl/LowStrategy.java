package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public class LowStrategy extends ParentStrategy implements Strategy {

	public LowStrategy() {
		super();
	}

	@Override
	public void applyFirstConfigurations(LoboRobot robot) {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations(robot);
			robot.turnTo(90);
		}
	}

	@Override
	public void nextStep(LoboRobot robot) {
		int angle = robot.heading + 90;
		robot.turnTo(angle);
		robot.turnGunTo(angle);
		robot.ahead(150);
	}

	@Override
	public void onScannedRobot(LoboRobot robot) {
		double firepower = 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(robot.scannedVelocity
		        * Math.sin(Math.toRadians(robot.scannedHeading - robot.scannedAngle)) / bulletVelocity));
		robot.turnGunTo((int) (robot.scannedAngle + offset));
		robot.fire(1);
	}

	@Override
	public void onHitByBullet(LoboRobot robot) {
		robot.turnGunTo(robot.hitByBulletAngle);
		robot.turnTo(5);
		robot.ahead(moveAmount);
	}

	@Override
	public void onHitWall(LoboRobot robot) {
		robot.bearGunTo(90);
		robot.turnRight(90);
	}

	@Override
	public void onHitRobot(LoboRobot robot) {
		robot.turnTo(5);
	}

}