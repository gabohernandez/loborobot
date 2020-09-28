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
			moveAmount = Math.max(robot.fieldWidth, robot.fieldHeight);
		}
	}

	public void nextStep(LoboRobot robot) {
		robot.ahead(moveAmount);
	}

	@Override
	public void onScannedRobot(LoboRobot robot) {
		double firepower = (double) robot.scannedDistance ;
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(robot.scannedVelocity
		        * Math.sin(Math.toRadians(robot.scannedHeading - robot.scannedAngle)) / bulletVelocity));
		robot.turnGunTo((int) (robot.scannedAngle + offset));
		robot.fire(firepower);
	}

	@Override
	public void onHitByBullet(LoboRobot robot) {
		int bearing = robot.hitByBulletBearing; 
	    robot.turnRight(-bearing); 
	    robot.ahead(360);
	}

	@Override
	public void onHitWall(LoboRobot robot) {
		int bearing = robot.hitWallBearing; 
	    robot.turnRight(-bearing);
	    robot.ahead(100);
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
