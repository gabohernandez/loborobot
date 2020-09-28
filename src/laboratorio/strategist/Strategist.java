package laboratorio.strategist;

import java.util.HashMap;
import java.util.Map;

import laboratorio.LaboRobot22;
import laboratorio.strategies.Strategy;
import laboratorio.strategies.StrategyEnum;
import laboratorio.strategies.impl.AggressiveStrategy;
import laboratorio.strategies.impl.LowStrategy;
import laboratorio.strategies.impl.MiddleStrategy;

public class Strategist {
	
	private static Strategist myStrategist = new Strategist();
	private LifeStrategist lifeStrategist = new LifeStrategist();
	private EnemiesStrategist enemiesStrategist = new EnemiesStrategist();
	
	private  Map<StrategyEnum, Strategy> strategies = new HashMap();
	
	public Strategist () {
		this.strategies.put(StrategyEnum.AGGRESSIVE_STRATEGY, new AggressiveStrategy());
		this.strategies.put(StrategyEnum.MIDDLE_STRATEGY, new MiddleStrategy());
		this.strategies.put(StrategyEnum.LOW_STRATEGY, new LowStrategy());
	}
	
	public static Strategist getstrategist() {
		return myStrategist;
	}
	
	public Map<StrategyEnum, Strategy> getStrategies() {
		return strategies;
	}
	
	public Strategy getStrategy(LaboRobot22 robot) {
		if (robot.others > 4) {
			return lifeStrategist.analyzeStrategy(robot);
		}
		else {
			return enemiesStrategist.analyzeStrategy(robot);
		}
	}
	
	private class LifeStrategist implements StrategistInterface{
		@Override
		public Strategy analyzeStrategy(LaboRobot22 robot) {
			if (robot.energy > 70) {
				return strategies.get(StrategyEnum.AGGRESSIVE_STRATEGY);
			}
			if (robot.energy < 40) {
				return strategies.get(StrategyEnum.LOW_STRATEGY);
			}
			return strategies.get(StrategyEnum.MIDDLE_STRATEGY);
		}
	}
	
	private class EnemiesStrategist implements StrategistInterface{
		@Override
		public Strategy analyzeStrategy(LaboRobot22 robot) {
			if (robot.others == 1) {
				return strategies.get(StrategyEnum.LOW_STRATEGY);
			}
			if (robot.energy >= 3) {
				return strategies.get(StrategyEnum.AGGRESSIVE_STRATEGY);
			}
			return strategies.get(StrategyEnum.MIDDLE_STRATEGY);
		}
	}

}

