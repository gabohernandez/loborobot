package laboratorio.strategies.impl;

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
		robot.ahead(moveAmount);
	}

	@Override
	public void onScannedRobot() {
		double firepower = 3d - 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		this.robot.fire(firepower);
		this.robot.bearGunTo(robot.scannedHeading);
		this.robot.ahead(moveAmount);
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
