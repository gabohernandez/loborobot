package strategist;

import java.util.HashMap;
import java.util.Map;

import laboratorio.LoboRobot;
import laboratorio.strategies.Strategy;
import laboratorio.strategies.StrategyEnum;
import laboratorio.strategies.impl.AggressiveStrategy;
import laboratorio.strategies.impl.LowStrategy;
import laboratorio.strategies.impl.MiddleStrategy;

public class Strategist {
	
	private static Strategist myStrategist = new Strategist();
	
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
	
	public Strategy getStrategy(LoboRobot robot) {
		if (robot.others > 4) {
			LifeStrategist lifeStrategist = new LifeStrategist();
			return lifeStrategist.analyzeStrategy(robot);
		}
		else {
			EnemiesStrategist enemiesStrategist = new EnemiesStrategist();
			return enemiesStrategist.analyzeStrategy(robot);
		}
	}
	
	class LifeStrategist implements StrategistInterface{
		@Override
		public Strategy analyzeStrategy(LoboRobot robot) {
			if (robot.energy > 70) {
				return strategies.get(StrategyEnum.AGGRESSIVE_STRATEGY);
			}
			if (robot.energy < 40) {
				return strategies.get(StrategyEnum.LOW_STRATEGY);
			}
			return strategies.get(StrategyEnum.MIDDLE_STRATEGY);
		}
	}
	
	class EnemiesStrategist implements StrategistInterface{
		@Override
		public Strategy analyzeStrategy(LoboRobot robot) {
			if (robot.others == 1) {
				return strategies.get(StrategyEnum.AGGRESSIVE_STRATEGY);
			}
			if (robot.energy >= 3) {
				return strategies.get(StrategyEnum.LOW_STRATEGY);
			}
			return strategies.get(StrategyEnum.MIDDLE_STRATEGY);
		}
	}

}

