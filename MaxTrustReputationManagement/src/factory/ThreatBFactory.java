package factory;

import agent.IdCreator;
import agent.MaliciousCollectiveAgent;
import strategy.ThreatBStrategy;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la ThreatBStrategy
 */
public class ThreatBFactory extends MaliciousCollectiveFactory{
	
	@Override
	public MaliciousCollectiveAgent getAgent(int numberOfAgents) {
		return new MaliciousCollectiveAgent(IdCreator.getIdCreator().getAgentId(), numberOfAgents, new ThreatBStrategy());
	}
	
}
