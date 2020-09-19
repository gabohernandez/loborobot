package laboratorio.strategies.impl;

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
		}
	}

	@Override
	public void nextStep() {
		robot.turnRight((int) normalRelativeAngleDegrees((Math.random() * 4) - robot.heading));
		robot.ahead(moveAmount);
	}

	public static double normalRelativeAngleDegrees(double angle) {
		return (angle %= 360) >= 0 ? (angle < 180) ? angle : angle - 360 : (angle >= -180) ? angle : angle + 360;
	}

	@Override
	public void onScannedRobot() {
		double firepower = 3d - 2d * ((double) robot.scannedDistance / (double) this.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(this.robot.scannedVelocity
		        * Math.sin(Math.toRadians(this.robot.scannedHeading - this.robot.scannedAngle)) / bulletVelocity));
		this.robot.turnGunTo((int) (robot.scannedAngle + offset));
		this.robot.fire(firepower);
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
