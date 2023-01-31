package strategy;

import agent.Agent;
import agent.ThreatAgent;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent produisant toujours des résultats négatifs
 * appartenant à un collectif malveillant 
 */
public class ThreatBStrategy extends MaliciousCollectiveStrategy{
		
	@Override
	public boolean getInteractionResult() {
		return false;
	}

	@Override
	public boolean evaluate(Agent other, boolean interactionResult) {
		for (ThreatAgent i : maliciousCollective) {
			if (i.getId() == other.getId()) {
				return true;
			}
		}
		return false;
	}

	
	
}
