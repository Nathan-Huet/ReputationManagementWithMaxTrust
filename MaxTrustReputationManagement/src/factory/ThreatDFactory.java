package factory;

import java.util.LinkedList;

import agent.Agent;
import agent.IdCreator;
import agent.MaliciousCollectiveAgent;
import strategy.ThreatDStrategy;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la ThreatDStrategy
 */
public class ThreatDFactory extends MaliciousCollectiveFactory {
	private LinkedList<MaliciousCollectiveAgent> maliciousCollectiveB;
	
	/**
	 * Construction d'une Factory générant des Agents utilisant la ThreatDStrategy soutenant un collectif d'Agent
	 * @param maliciousCollectiveB collectif soutenu par les Agents créés
	 */
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
