package laboratorio.strategies.impl;

import laboratorio.LaboRobot22;
import laboratorio.strategies.Strategy;

public class LowStrategy extends ParentStrategy implements Strategy {

	public LowStrategy() {
		super();
	}

	@Override
	public void applyFirstConfigurations(LaboRobot22 robot) {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations(robot);
			robot.turnTo(90);
		}
	}

	@Override
	public void nextStep(LaboRobot22 robot) {
		int angle = robot.heading + 90;
		robot.turnTo(angle);
		robot.turnGunTo(angle);
		robot.ahead(150);
	}

	@Override
	public void onScannedRobot(LaboRobot22 robot) {
		double firepower = 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(robot.scannedVelocity
		        * Math.sin(Math.toRadians(robot.scannedHeading - robot.scannedAngle)) / bulletVelocity));
		robot.turnGunTo((int) (robot.scannedAngle + offset));
		robot.fire(1);
	}

	@Override
	public void onHitByBullet(LaboRobot22 robot) {
		robot.turnGunTo(robot.hitByBulletAngle);
		robot.turnTo(5);
		robot.ahead(moveAmount);
	}

	@Override
	public void onHitWall(LaboRobot22 robot) {
		robot.bearGunTo(90);
		robot.turnRight(90);
	}

	@Override
	public void onHitRobot(LaboRobot22 robot) {
		robot.turnTo(5);
	}

}