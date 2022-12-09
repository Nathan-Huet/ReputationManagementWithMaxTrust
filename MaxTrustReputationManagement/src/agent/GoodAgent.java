package agent;

import strategy.GoodAgentStrategy;

/**
 * Une instance de cette classe est utilisée pour modéliser un Agent 
 * utilisant la GoodAgentStrategy
 */
public class GoodAgent extends Agent{

	public GoodAgent(String name) {
		super(name);
		this.agentStrategy = new GoodAgentStrategy();
	}

}
