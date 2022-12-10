package factory;

import agent.Agent;
import agent.IdCreator;
import agent.ThreatAgent;
import strategy.ThreatAStrategy;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la ThreatAStrategy
 */
public class ThreatAFactory implements AgentFactory{

	@Override
	public Agent getAgent(int numberOfAgents) {
		return new ThreatAgent(IdCreator.getIdCreator().getAgentId(), numberOfAgents, new ThreatAStrategy());
	}
}
