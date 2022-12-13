package agent;

import strategy.Strategy;

/**
 * Une instance de cette classe est utilisée pour modéliser un Agent 
 * qui agit dans le système selon une Strategy donnée.
 * Elle a été construite sous le principe que la Stategy nuirait au système
 * mais on peut également utiliser une Strategy qui le soutient
 */
public class ThreatAgent extends Agent{

	/**
	 * Construction d'un Agent avec une Strategy donnée
	 * @param id identifiant de l'Agent
	 * @param numberOfAgents nombre d'Agents
	 * @param strategy Strategy utilisée 
	 */
	public ThreatAgent(int id, int numberOfAgents, Strategy strategy) {
		super(id, numberOfAgents);
		this.agentStrategy = strategy;
	}

}
