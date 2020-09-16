package laboratorio.strategies;

/*
 * Cree esta clase cómo para obligar a que cada estrategia tenga que si o si entender estos métodos (podemos agregar más)
 */
public interface Strategy {

	public void onScannedRobot();

	public void onHitByBullet();

	public void onHitWall();

	public void onHitRobot();
}
