package strategy;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent prduisant toujours des résultats négatifs
 *
 */
public class ThreatAStrategy implements Strategy{

	@Override
	public boolean getInteractionResult() {
		return false;
	}

	@Override
	public boolean evaluateResult(boolean interactionResult) {
		return !interactionResult;
	}
}
