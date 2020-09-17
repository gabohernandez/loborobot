package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public class LowStrategy extends ParentStrategy implements Strategy {

	int turnCounter;

	public LowStrategy(LoboRobot robot) {
		super(robot);
	}

	@Override
	public void nextStep() {
		turnCounter = 0;
		this.robot.bearGunTo(15);
//		setGunRotationRate(15);
		while (true) {
			if (turnCounter % 64 == 0) {
				// Straighten out, if we were hit by a bullet and are turning
				this.robot.turnTo(0);
//				setTurnRate(0);
			}
			if (turnCounter % 64 == 32) {
				// Go backwards, faster
//				setVelocityRate(-6);
			}
			turnCounter++;
			this.robot.run();
			//execute();
		}
	}

	@Override
	public void onScannedRobot() {
		this.robot.fire(1);
	}

	@Override
	public void onHitByBullet() {
		// Turn to confuse the other robot
		this.robot.turnTo(5);
	}

	@Override
	public void onHitWall() {
		// Si le pega a la pared gira el cuerpo y el arma 90 grados
		this.robot.bearGunTo(90);
		this.robot.turnRight(90);
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