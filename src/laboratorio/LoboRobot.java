package laboratorio;

import java.util.HashMap;
import java.util.Map;

import laboratorio.strategies.Strategy;
import laboratorio.strategies.StrategyEnum;
import laboratorio.strategies.impl.LowStrategy;
import laboratorio.strategies.impl.MiddleStrategy;
import laboratorio.strategies.impl.StolenStrategy;
import robocode.JuniorRobot;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html

public class LoboRobot extends JuniorRobot {

	private Map<StrategyEnum, Strategy> strategies = new HashMap();
	private StrategyEnum currentStrategy;

	public LoboRobot() {
		this.strategies.put(StrategyEnum.STOLEN_STRATEGY, new StolenStrategy(this));
		this.strategies.put(StrategyEnum.MIDDLE_STRATEGY, new MiddleStrategy(this));
		this.strategies.put(StrategyEnum.LOW_STRATEGY, new LowStrategy(this));
		this.currentStrategy = StrategyEnum.STOLEN_STRATEGY;
	}

	public Map<StrategyEnum, Strategy> getStrategies() {
		return strategies;
	}

	public StrategyEnum getCurrentStrategy() {
		return currentStrategy;
	}

	public void setCurrentStrategy(StrategyEnum currentStrategy) {
		this.currentStrategy = currentStrategy;
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		setColors(white, blue, white, blue, white);
		while (true) {
			// Si tenemos que hacer algo antes de los próximos pasos podemos meter un first
			// step. Algo así:
			// this.getStrategy().firstSteap();
			// Cada estrategía debería ejecutar esos pasos una única vez, por ende
			// tendriamos un booleano en false y cuando se ejecuta por primera vez pasa a
			// true
			this.getStrategy().nextStep();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		this.getStrategy().onScannedRobot();
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall() {
		this.getStrategy().onHitWall();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet() {
		this.getStrategy().onHitByBullet();
	}

	/**
	 * onHitRobot: What to do when you're hit by a robot
	 */
	public void onHitRobot() {
		this.getStrategy().onHitRobot();
	}

	/**
	 * 
	 * @return la estrategia actual
	 */
	private Strategy getStrategy() {
		return this.strategies.get(this.getCurrentStrategy());
	}

}