package laboratorio.strategies.impl;

import java.util.Random;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

/**
 * Lo que esta implementado aca me lo robe de
 * https://robowiki.net/wiki/User_talk:Realmoonstruck
 */

public class AggressiveStrategy extends ParentStrategy implements Strategy {

	public AggressiveStrategy() {
		super();
	}

	public void applyFirstConfigurations(LoboRobot robot) {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations(robot);
			// Gira el robot para mirar arriba
			robot.turnTo(0);
		}
	}

	public void nextStep(LoboRobot robot) {
		Random rand = new Random();
		int randomInteger = rand.nextInt(340-1) + 1;
		robot.turnTo(randomInteger);
		robot.ahead(moveAmount);
	}

	@Override
	public void onScannedRobot(LoboRobot robot) {
		double firepower = 3d - 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(robot.scannedVelocity
		        * Math.sin(Math.toRadians(robot.scannedHeading - robot.scannedAngle)) / bulletVelocity));
		robot.turnGunTo((int) (robot.scannedAngle + offset));
		robot.fire(firepower);
		robot.turnGunTo(robot.hitByBulletAngle);
	}

	@Override
	public void onHitByBullet(LoboRobot robot) {
		robot.turnGunTo(robot.hitByBulletAngle);
	}

	@Override
	public void onHitWall(LoboRobot robot) {
		// Si le pega a la pared gira el cuerpo y el arma 90 grados
		robot.bearGunTo(90);
		robot.turnRight(90);
	}

	@Override
	public void onHitRobot(LoboRobot robot) {
		robot.turnRight(90);
		if (robot.scannedBearing > -90 && robot.scannedBearing < 90)
			robot.back(100);
		else
			robot.ahead(100);
	}

}
