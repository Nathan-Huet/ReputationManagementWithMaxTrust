package factory;

import agent.Agent;
import agent.GoodAgent;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la GoodAgentStrategy
 */
public class GoodAgentFactory implements AgentFactory {

	@Override
	public Agent getAgent(String name) {
		return new GoodAgent(name);
	}

}
