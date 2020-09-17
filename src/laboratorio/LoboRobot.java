package laboratorio;

import java.util.ArrayList;
import laboratorio.strategies.Strategy;
import laboratorio.strategies.impl.LowStrategy;
import laboratorio.strategies.impl.MiddleStrategy;
import laboratorio.strategies.impl.StolenStrategy;
import robocode.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html

/* 
 * Lo que esta implementado aca me lo robe de https://robowiki.net/wiki/User_talk:Realmoonstruck
 * 
 */
public class LoboRobot extends JuniorRobot {

	// Aca vamos a guardar las estrategias (clases que implementen "Strategy")
	// Cuando nos pegan y cuando disparamos, perdemos energia. Creo que esos serian
	// los momentos donde deberiamos cambiar de estrategia.
	// O podriaamos tener estrategias para buscar enemigos o para huir
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private int currentPositionStrategy = 0;

	public LoboRobot() {
		StolenStrategy stolenStrategy = new StolenStrategy(this);
		this.strategies.add(stolenStrategy);
		MiddleStrategy middleStrategy = new MiddleStrategy(this);
		this.strategies.add(middleStrategy);
		LowStrategy lowStrategy = new LowStrategy(this);
		this.strategies.add(lowStrategy);
		System.out.println(strategies);
	}
	
		
	public ArrayList<Strategy> getStrategies() {
		return strategies;
	}


	public void setStrategies(ArrayList<Strategy> strategies) {
		this.strategies = strategies;
	}


	public int getCurrentPositionStrategy() {
		return currentPositionStrategy;
	}


	public void setCurrentPositionStrategy(int currentPositionStrategy) {
		this.currentPositionStrategy = currentPositionStrategy;
	}


	@Override
	public void run() {
		setColors(white, blue, white, blue, white);
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
		return this.strategies.get(this.getCurrentPositionStrategy());
	}

}