package factory;

import agent.IdCreator;
import agent.MaliciousCollectiveAgent;
import strategy.ThreatCStrategy;

/**
 * Cette classe est utilisée afin de permettre la génération d'Agents utilisant la ThreatCStrategy
 */
public class ThreatCFactory extends MaliciousCollectiveFactory{
	private double probabilityOfPositiveResults;
	
	/**
	 * Construction d'une Factory générant des Agents utilisant la ThreatCStrategy avec une probabilité de produire des résultats positifs donnée
	 * @param probabilityOfPositiveResults probabilité de produire des résultats positifs pour les Agents générés
	 */
	public ThreatCFactory(double probabilityOfPositiveResults) {
		this.probabilityOfPositiveResults = probabilityOfPositiveResults;
	}

	@Override
	public MaliciousCollectiveAgent getAgent(int numberOfAgents) {
		return new MaliciousCollectiveAgent(IdCreator.getIdCreator().getAgentId(), numberOfAgents, new ThreatCStrategy(probabilityOfPositiveResults));
	}

}
