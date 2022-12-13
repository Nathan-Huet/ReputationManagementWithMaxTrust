package factory;

import java.util.LinkedList;

import agent.Agent;
import agent.IdCreator;
import agent.MaliciousCollectiveAgent;
import strategy.ThreatDStrategy;

public class ThreatDFactory extends MaliciousCollectiveFactory {
	private LinkedList<MaliciousCollectiveAgent> maliciousCollectiveB;
	
	public ThreatDFactory(LinkedList<MaliciousCollectiveAgent> maliciousCollectiveB) {
		this.maliciousCollectiveB = maliciousCollectiveB;
	}

	@Override
	public MaliciousCollectiveAgent getAgent(int numberOfAgents) {
		ThreatDStrategy strategy = new ThreatDStrategy();
		strategy.setMaliciousCollectiveB(maliciousCollectiveB);
		return new MaliciousCollectiveAgent(IdCreator.getIdCreator().getAgentId(), numberOfAgents, strategy);
	}	

}
