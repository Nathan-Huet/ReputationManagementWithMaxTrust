package factory;

import agent.Agent;
import agent.ThreatAgent;
import strategy.ThreatAStrategy;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la ThreatAStrategy
 */
public class ThreatAFactory implements AgentFactory{

	@Override
	public Agent getAgent(String name) {
		return new ThreatAgent(name, new ThreatAStrategy());
	}
}
