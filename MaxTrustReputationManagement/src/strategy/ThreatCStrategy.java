package strategy;

import java.util.Random;

import agent.Agent;
import agent.ThreatAgent;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent produisant
 * un pourcentage de résultats positifs pour contrebalancer les résultats négatifs qu'il produit
 * appartenant à un collectif malveillant 
 */
public class ThreatCStrategy extends MaliciousCollectiveStrategy{
	protected double probabilityOfPositiveResult;
	
	/**
	 * Construction d'une Strategy malveillante produisant un pourcentage de résultats positifs
	 * @param probabilityOfPositiveResult le pourcentage de résultats positifs de la Strategy
	 */
	public ThreatCStrategy(double probabilityOfPositiveResult) {
		this.probabilityOfPositiveResult = probabilityOfPositiveResult;
	}

	@Override
	public boolean getInteractionResult() {
		Random random = new Random();
		return random.nextDouble() <= probabilityOfPositiveResult;
	}

	@Override
	public boolean evaluateResult(Agent other) {
		for (ThreatAgent i : maliciousCollective) {
			if (i.getId() == other.getId()) {
				return true;
			}
		}
		return false;
	}
	
	
}
