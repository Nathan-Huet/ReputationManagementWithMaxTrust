package strategy;

import java.util.Random;

import agent.Agent;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent qui fait de son mieux pour produire des résultats positifs 
 *
 */
public class GoodAgentStrategy extends StrategyAbstract {
	private final double probabilityOfPositiveResult = 0.95;
	
	@Override
	public boolean getInteractionResult() {
		Random random = new Random();
		return random.nextDouble() <= probabilityOfPositiveResult;
	}

	@Override
	public boolean evaluate(Agent other, boolean interactionResult) {
		return interactionResult;
	}

	
}
