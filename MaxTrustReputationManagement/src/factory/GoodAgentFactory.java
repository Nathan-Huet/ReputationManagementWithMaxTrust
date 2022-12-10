package factory;

import agent.Agent;
import agent.GoodAgent;
import agent.IdCreator;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la GoodAgentStrategy
 */
public class GoodAgentFactory implements AgentFactory {

	@Override
	public Agent getAgent(int numberOfAgents) {
		return new GoodAgent(IdCreator.getIdCreator().getAgentId(), numberOfAgents);
	}

}
