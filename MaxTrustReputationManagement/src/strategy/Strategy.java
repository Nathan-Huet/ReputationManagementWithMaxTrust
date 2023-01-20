package strategy;

import agent.Agent;

/**
 * Cette interface est utilisée pour modéliser la Strategy d'un Agent et sa manière d'évaluer ses pairs
 *
 */
public interface Strategy {

	/**
	 * calcule si le résultat est positif lorsque la Strategy est appliquée
	 * @return true si le résultat est positif et false sinon
	 */
	public boolean getInteractionResult();

	/**
	 * évaluation du résultat selon le point de vue de la Strategy
	 * @param other un Agent avec qui la Strategy interagit
	 * @param interactionResult resultat de l'interaction avec l'autre agent
	 * @return true si la Strategy est satisfaite du résultat et false sinon
	 */
	public abstract boolean evaluateResult(Agent other, boolean interactionResult);
}
