package laboratorio.strategies;

import laboratorio.LaboRobot22;

/*
 * Cree esta clase cómo para obligar a que cada estrategia tenga que si o si entender estos métodos (podemos agregar más)
 */
public interface Strategy {

	public void nextStep(LaboRobot22 robot);
	
	public void onScannedRobot(LaboRobot22 robot);

	public void onHitByBullet(LaboRobot22 robot);

	public void onHitWall(LaboRobot22 robot);

	public void onHitRobot(LaboRobot22 robot);

	public void applyFirstConfigurations(LaboRobot22 robot);
}
