package laboratorio;

import robocode.JuniorRobot;
import strategist.Strategist;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html

public class LaboRobot22 extends JuniorRobot {

	
	public LaboRobot22() {
		
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
	@Override
	public void onScannedRobot() {
		Strategist.getstrategist().getStrategy(this).onScannedRobot(this);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		Strategist.getstrategist().getStrategy(this).onHitWall(this);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
		Strategist.getstrategist().getStrategy(this).onHitByBullet(this);
	}

	/**
	 * onHitRobot: What to do when you're hit by a robot
	 */
	@Override
	public void onHitRobot() {
		Strategist.getstrategist().getStrategy(this).onHitRobot(this);
	}

}