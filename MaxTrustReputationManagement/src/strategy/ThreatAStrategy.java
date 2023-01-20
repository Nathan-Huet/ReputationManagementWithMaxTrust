package strategy;

import agent.Agent;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent produisant toujours des résultats négatifs
 *
 */
public class ThreatAStrategy implements Strategy{

	@Override
	public boolean getInteractionResult() {
		return false;
	}

	@Override
	public boolean evaluateResult(Agent other, boolean interactionResult) {
		return !interactionResult;
	}
}
