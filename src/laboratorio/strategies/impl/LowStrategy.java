package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public class LowStrategy implements Strategy {

	private LoboRobot robot;

	public LowStrategy(LoboRobot robot) {
		this.robot = robot;
	}

	@Override
	public void run() {
		setColors(white, blue, white, blue, white);
		turnCounter = 0;
		setGunRotationRate(15);
		while (true) {
			if (turnCounter % 64 == 0) {
				// Straighten out, if we were hit by a bullet and are turning
				setTurnRate(0);
				// Go forward with a velocity of 4
				setVelocityRate(4);
			}
			if (turnCounter % 64 == 32) {
				// Go backwards, faster
				setVelocityRate(-6);
			}
			turnCounter++;
			execute();
		}
	}

	@Override
	public void onScannedRobot() {
		this.robot.fire(1);
	}

	@Override
	public void onHitByBullet() {
		// Turn to confuse the other robot
		setTurnRate(5);
	}

	@Override
	public void onHitWall() {
		if (movingForward) {
			setBack(500);
			movingForward = false;
		} else {
			setAhead(500);
			movingForward = true;
		}
	}

	@Override
	public void onHitRobot() {
		// Esto es cuando chocas contra un robo mepa no cuando le pegamos un balazo
		// Basicamente a cierta distancia se va para atrás y dsp vuelve.
		// No sé si tiene sentido. Quizá podamos girar 45 grados para que no nos peguen.
		if (this.robot.scannedBearing > -90 && this.robot.scannedBearing < 90)
			this.robot.back(100);
		else
			this.robot.ahead(100);
	}
}