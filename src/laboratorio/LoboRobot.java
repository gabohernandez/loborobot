package laboratorio;

import java.util.HashMap;
import java.util.Map;

import laboratorio.strategies.Strategy;
import laboratorio.strategies.StrategyEnum;
import laboratorio.strategies.impl.LowStrategy;
import laboratorio.strategies.impl.MiddleStrategy;
import laboratorio.strategies.impl.AggressiveStrategy;
import robocode.JuniorRobot;
import robocode.ScannedRobotEvent;
import strategist.Strategist;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html

public class LoboRobot extends JuniorRobot {

	
	public LoboRobot() {
		
	}

	@Override
	public void run() {
		setColors(white, blue, white, blue, white);
		while (true) {
			// Si tenemos que hacer algo antes de los próximos pasos podemos meter un first
			// step. Algo así:
			Strategist.getstrategist().getStrategy(this).applyFirstConfigurations(this);
			// Cada estrategía debería ejecutar esos pasos una única vez, por ende
			// tendriamos un booleano en false y cuando se ejecuta por primera vez pasa a
			// true
			Strategist.getstrategist().getStrategy(this).nextStep(this);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		Strategist.getstrategist().getStrategy(this).onScannedRobot(this);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall() {
		Strategist.getstrategist().getStrategy(this).onHitWall(this);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet() {
		Strategist.getstrategist().getStrategy(this).onHitByBullet(this);
	}

	/**
	 * onHitRobot: What to do when you're hit by a robot
	 */
	public void onHitRobot() {
		Strategist.getstrategist().getStrategy(this).onHitRobot(this);
	}

}