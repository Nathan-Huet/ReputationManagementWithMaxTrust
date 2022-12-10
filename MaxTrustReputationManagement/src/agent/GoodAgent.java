package agent;

import strategy.GoodAgentStrategy;

/**
 * Une instance de cette classe est utilisée pour modéliser un Agent 
 * utilisant la GoodAgentStrategy
 */
public class GoodAgent extends Agent{

	/**
	 * Construction d'un Agent utilisant GoodAgentStrategy
	 * @param id identifiant de l'Agent
	 * @param numberOfAgents nombre d'Agents
	 */
	public GoodAgent(int id, int numberOfAgents) {
		super(id, numberOfAgents);
		this.agentStrategy = new GoodAgentStrategy();
	}

}
