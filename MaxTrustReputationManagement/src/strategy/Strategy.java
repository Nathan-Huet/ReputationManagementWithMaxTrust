package strategy;

import agent.Agent;

public interface Strategy {

    /**
	 * calcule si le résultat est positif lorsque la Strategy est appliquée
	 * @return true si le résultat est positif et false sinon
	 */
	public abstract boolean getInteractionResult();


	/**
	 * évaluation du résultat selon le point de vue de la Strategy
	 * @param other un Agent avec qui la Strategy interagit
	 * @return true si la Strategy est satisfaite du résultat et false sinon
	 */
	public boolean evaluateResult(Agent thisAgent, Agent other);
}
