package laboratorio.strategies.impl;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;

public class StolenStrategy implements Strategy {

	private LoboRobot robot;

	public StolenStrategy(LoboRobot robot) {
		this.robot = robot;
	}

	@Override
	public void onScannedRobot() {
		// Esta cuenta la hace para saber cuan cerca está del otro robot.
		// Cuanto más cerca, dispara con más fuerza
		// Hay que entender bien cada cosa
		double firepower = 3d - 2d * ((double) robot.scannedDistance / (double) this.robot.moveAmount);
		double bulletVelocity = 20 - 3 * firepower;
		double offset = Math.toDegrees(Math.asin(this.robot.scannedVelocity
		        * Math.sin(Math.toRadians(this.robot.scannedHeading - this.robot.scannedAngle)) / bulletVelocity));
		/*
		 * Aca se gira el arma para que mire al robot más un offset. Para mi esto
		 * tenemos que mejorarlo de la siguiente manera: Cuando se detecta al robot
		 * contrario podríamos ver si está yendo para adelante o para atŕas y a qué
		 * velocidad. Con esa data podríamos ajustar la mira para que haya más chance de
		 * pegarle. Pensá que cada bala que gastamos nos saca vida
		 */
		this.robot.turnGunTo((int) (robot.scannedAngle + offset));
		this.robot.fire(firepower);
	}

	@Override
	public void onHitByBullet() {
		// Si nos pega un balazo, miramos para donde nos pegaron
		// Quizá esto pueda cambiar según la estrategia. Donde estamos hechos verga, nos
		// las picamos
		this.robot.turnGunTo(this.robot.hitByBulletAngle);

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
