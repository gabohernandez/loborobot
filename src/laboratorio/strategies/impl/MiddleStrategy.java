package laboratorio.strategies.impl;

import java.util.Random;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public class MiddleStrategy extends ParentStrategy implements Strategy {

	static int corner = 0;

	public MiddleStrategy() {
		super();
	}

	@Override
	public void applyFirstConfigurations(LoboRobot robot) {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations(robot);
			robot.turnTo(45);
		}
	}

	@Override
	public void nextStep(LoboRobot robot) {
		Random rand = new Random();
		int randomInteger = rand.nextInt(340-1) + 1;
		robot.turnTo(randomInteger);
		robot.ahead(250);
	}

	@Override
	public void onScannedRobot(LoboRobot robot) {
		double firepower = 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(robot.scannedVelocity
		        * Math.sin(Math.toRadians(robot.scannedHeading - robot.scannedAngle)) / bulletVelocity));
		robot.turnGunTo((int) (robot.scannedAngle + offset));
		robot.fire(firepower);
		
	}

	@Override
	public void onHitByBullet(LoboRobot robot) {
		robot.turnGunTo(robot.hitByBulletAngle);
	}

	@Override
	public void onHitWall(LoboRobot robot) {
		robot.bearGunTo(90);
		robot.turnRight(90);
	}

	@Override
	public void onHitRobot(LoboRobot robot) {
		robot.turnLeft(90);
		if (robot.scannedBearing > -90 && robot.scannedBearing < 90)
			robot.back(100);
	}

}
