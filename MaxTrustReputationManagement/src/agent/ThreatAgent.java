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
	 * 
	 * @param name nom de l'Agent
	 * @param strategy Strategy utilisée 
	 */
	public ThreatAgent(String name, Strategy strategy) {
		super(name);
		this.agentStrategy = strategy;
	}


}
