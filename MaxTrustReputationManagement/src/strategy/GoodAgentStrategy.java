package strategy;

import java.util.Random;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent qui fait de son mieux pour produire des résultats positifs 
 *
 */
public class GoodAgentStrategy implements Strategy {
	private final double probabilityOfPositiveResult = 0.95;
	
	@Override
	public boolean getInteractionResult() {
		Random random = new Random();
		return random.nextDouble() <= probabilityOfPositiveResult;
	}

	@Override
	public boolean evaluateResult(boolean interactionResult) {
		return interactionResult;
	}
	
}
