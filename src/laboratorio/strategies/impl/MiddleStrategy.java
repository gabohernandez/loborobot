package laboratorio.strategies.impl;

import java.util.Random;

import laboratorio.LaboRobot22;
import laboratorio.strategies.Strategy;

public class MiddleStrategy extends ParentStrategy implements Strategy {

	static int corner = 0;

	public MiddleStrategy() {
		super();
	}

	@Override
	public void applyFirstConfigurations(LaboRobot22 robot) {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations(robot);
			robot.turnTo(45);
		}
	}

	@Override
	public void nextStep(LaboRobot22 robot) {
		Random rand = new Random();
		int randomInteger = rand.nextInt(340-1) + 1;
		robot.turnTo(randomInteger);
		robot.ahead(250);
	}

	@Override
	public void onScannedRobot(LaboRobot22 robot) {
		double firepower = 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(robot.scannedVelocity
		        * Math.sin(Math.toRadians(robot.scannedHeading - robot.scannedAngle)) / bulletVelocity));
		robot.turnGunTo((int) (robot.scannedAngle + offset));
		robot.fire(firepower);
		
	}

	@Override
	public void onHitByBullet(LaboRobot22 robot) {
		robot.turnGunTo(robot.hitByBulletAngle);
	}

	@Override
	public void onHitWall(LaboRobot22 robot) {
		robot.bearGunTo(90);
		robot.turnRight(90);
	}

	@Override
	public void onHitRobot(LaboRobot22 robot) {
		robot.turnLeft(90);
		if (robot.scannedBearing > -90 && robot.scannedBearing < 90)
			robot.back(100);
	}

}
