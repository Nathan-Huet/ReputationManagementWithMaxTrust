package agent;

import strategy.Strategy;

/**
 * Une instance de cette classe est utilisée afin de modéliser des interactions 
 * entre Agent dans un environnement où certains Agents ont des intentions malveillantes
 *
 */
public abstract class Agent {

	protected Strategy agentStrategy;
	protected String name;
	
	/**
	 * 
	 * @param name nom de l'Agent
	 */
	public Agent(String name) {
		this.name = name;
	}
	
	/**
	 * méthode évaluant le résultat d'une interaction avec un autre Agent 
	 * la façon d'évaluer si le résultat est satisfaisant dépend de la Strategy de l'Agent courant
	 * @param other l'autre Agent
	 * @return true si la Strategy de l'Agent évalue le résultat comme positif et false sinon
	 */
	public boolean interactsWith(Agent other) {
		return agentStrategy.evaluateResult(other.agentStrategy.getInteractionResult());
	}	
}
