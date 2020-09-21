package laboratorio.strategies.impl;

import java.util.Random;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;
import laboratorio.strategies.StrategyEnum;

public class MiddleStrategy extends ParentStrategy implements Strategy {

	static int corner = 0;

	public MiddleStrategy(LoboRobot robot) {
		super(robot);
	}

	@Override
	public void applyFirstConfigurations() {
		if (!firstConfigurationsApplied) {
			firstConfigurationsApplied = true;
			super.applyFirstConfigurations();
			robot.turnTo(45);
		}
	}

	@Override
	public void nextStep() {
		Random rand = new Random();
		int random_integer = rand.nextInt(340-1) + 1;
		robot.turnTo(random_integer);
		robot.ahead(250);
	}

	@Override
	public void onScannedRobot() {
		double firepower = 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(this.robot.scannedVelocity
		        * Math.sin(Math.toRadians(this.robot.scannedHeading - this.robot.scannedAngle)) / bulletVelocity));
		this.robot.turnGunTo((int) (robot.scannedAngle + offset));
		this.robot.fire(firepower);
		this.robot.turnGunTo(this.robot.hitByBulletAngle);
		if (this.robot.energy < 40) {
			this.robot.setCurrentStrategy(StrategyEnum.LOW_STRATEGY);
		}
	}

	@Override
	public void onHitByBullet() {
		this.robot.turnGunTo(this.robot.hitByBulletAngle);
		if (this.robot.energy < 40) {
			this.robot.setCurrentStrategy(StrategyEnum.LOW_STRATEGY);
		}
	}

	@Override
	public void onHitWall() {
		this.robot.bearGunTo(90);
		this.robot.turnRight(90);
		if (this.robot.energy < 40) {
			this.robot.setCurrentStrategy(StrategyEnum.LOW_STRATEGY);
		}
	}

	@Override
	public void onHitRobot() {
		this.robot.turnLeft(90);
		if (this.robot.scannedBearing > -90 && this.robot.scannedBearing < 90)
			this.robot.back(100);
		if (this.robot.energy < 40) {
			this.robot.setCurrentStrategy(StrategyEnum.LOW_STRATEGY);
		}
	}

}
