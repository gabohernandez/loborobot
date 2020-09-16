package laboratorio;

import laboratorio.strategies.Strategy;
import laboratorio.strategies.impl.StolenStrategy;
import robocode.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html

/* 
 * Lo que está implementado acá me lo robé de https://robowiki.net/wiki/User_talk:Realmoonstruck
 * 
 */
public class LoboRobot extends JuniorRobot {

	// Aca vamos a guardar las estrategias (clases que implementen "Strategy")
	// Cuando nos pegan y cuando disparamos, perdemos energía. Creo que esos serían
	// los momentos donde deberíamos cambiar de estrategia.
	// O podríamos tener estrategias para buscar enemigos o para huir
	private Strategy[] strategies = { new StolenStrategy(this) };
	private int currentPositionStrategy = 0;

	public int moveAmount;

	@Override
	public void run() {
		// Las siguientes dos líneas las ejecuta una única vez por pelea
		// Esto decide cuanto se va a mover el robot adaptado a cada campo de batalla
		moveAmount = Math.max(fieldWidth, fieldHeight);
		// Gira el robot 0 grados para que mire para arriba
		turnTo(0);

		// Eternamente
		while (true) {
			// Gira el arma 90 grados relativo al cuerpo del robot
			bearGunTo(90);
			// Se mueve hacia adelante
			ahead(moveAmount);
		}

	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		this.getCurrentStrategy().onScannedRobot();
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall() {
		this.getCurrentStrategy().onHitWall();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet() {
		this.getCurrentStrategy().onHitByBullet();
	}

	/**
	 * onHitRobot: What to do when you're hit by a robot
	 */
	public void onHitRobot() {
		this.getCurrentStrategy().onHitRobot();
	}

	/**
	 * 
	 * @return la estrategia actual según la posición en el arreglo. Podríamos
	 *         hacerlo de otra manera
	 */
	private Strategy getCurrentStrategy() {
		return this.strategies[this.currentPositionStrategy];
	}

}