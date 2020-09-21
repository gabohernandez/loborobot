package laboratorio.strategies.impl;

import java.util.Random;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;
import laboratorio.strategies.StrategyEnum;

/**
 * Lo que esta implementado aca me lo robe de
 * https://robowiki.net/wiki/User_talk:Realmoonstruck
 */

public class AggressiveStrategy extends ParentStrategy implements Strategy {

	public AggressiveStrategy(LoboRobot robot) {
		super(robot);
	}

	public void applyFirstConfigurations() {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations();
			// Gira el robot para mirar arriba
			robot.turnTo(0);
		}
	}

	public void nextStep() {
		Random rand = new Random();
		int random_integer = rand.nextInt(340-1) + 1;
		robot.turnTo(random_integer);
		robot.ahead(moveAmount);
	}

	@Override
	public void onScannedRobot() {
		double firepower = 3d - 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(this.robot.scannedVelocity
		        * Math.sin(Math.toRadians(this.robot.scannedHeading - this.robot.scannedAngle)) / bulletVelocity));
		this.robot.turnGunTo((int) (robot.scannedAngle + offset));
		this.robot.fire(firepower);
		this.robot.turnGunTo(this.robot.hitByBulletAngle);
		if (this.robot.energy < 70) {
			this.robot.setCurrentStrategy(StrategyEnum.MIDDLE_STRATEGY);
		}
	}

	@Override
	public void onHitByBullet() {
		this.robot.turnGunTo(this.robot.hitByBulletAngle);
		if (this.robot.energy < 70) {
			this.robot.setCurrentStrategy(StrategyEnum.MIDDLE_STRATEGY);
		}
	}

	@Override
	public void onHitWall() {
		// Si le pega a la pared gira el cuerpo y el arma 90 grados
		this.robot.bearGunTo(90);
		this.robot.turnRight(90);
		if (this.robot.energy < 70) {
			this.robot.setCurrentStrategy(StrategyEnum.MIDDLE_STRATEGY);
		}
	}

	@Override
	public void onHitRobot() {
		this.robot.turnRight(90);
		if (this.robot.scannedBearing > -90 && this.robot.scannedBearing < 90)
			this.robot.back(100);
		else
			this.robot.ahead(100);
		if (this.robot.energy < 70) {
			this.robot.setCurrentStrategy(StrategyEnum.MIDDLE_STRATEGY);
		}
	}

}
