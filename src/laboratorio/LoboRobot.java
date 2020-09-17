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
		this.getCurrentStrategy().run();
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