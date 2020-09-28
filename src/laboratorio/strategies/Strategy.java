package laboratorio.strategies;

import laboratorio.LoboRobot;

/*
 * Cree esta clase cómo para obligar a que cada estrategia tenga que si o si entender estos métodos (podemos agregar más)
 */
public interface Strategy {

	public void nextStep(LoboRobot robot);
	
	public void onScannedRobot(LoboRobot robot);

	public void onHitByBullet(LoboRobot robot);

	public void onHitWall(LoboRobot robot);

	public void onHitRobot(LoboRobot robot);

	public void applyFirstConfigurations(LoboRobot robot);
}
