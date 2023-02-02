package strategy;

import agent.Agent;
import world.SimulationLogger;

/**
 * Cette classe abstraite est utilisée pour modéliser la Strategy d'un Agent et sa manière d'évaluer ses pairs
 *
 */
public abstract class StrategyAbstract implements Strategy{

	/**
	 * calcule si le résultat est positif lorsque la Strategy est appliquée
	 * @return true si le résultat est positif et false sinon
	 */
	public abstract boolean getInteractionResult();

	protected abstract boolean evaluate(Agent other, boolean interactionResult);

	/**
	 * évaluation du résultat selon le point de vue de la Strategy
	 * @param other un Agent avec qui la Strategy interagit
	 * @return true si la Strategy est satisfaite du résultat et false sinon
	 */
	public boolean evaluateResult(Agent thisAgent, Agent other){
		boolean interactionResult = other.getInteractionResult();
		SimulationLogger.getSimulationLogger().realEvaluation(thisAgent, other, interactionResult);

		return evaluate(other, interactionResult);
	}
}
