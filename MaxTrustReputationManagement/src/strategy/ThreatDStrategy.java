package strategy;

import java.util.LinkedList;

import agent.Agent;
import agent.MaliciousCollectiveAgent;
import agent.ThreatAgent;

public class ThreatDStrategy extends MaliciousCollectiveStrategy {
	protected LinkedList<MaliciousCollectiveAgent> maliciousCollectiveB = null;
	
	public void setMaliciousCollectiveB(LinkedList<MaliciousCollectiveAgent> maliciousCollectiveB) {
		this.maliciousCollectiveB = maliciousCollectiveB;
	}

	@Override
	public boolean getInteractionResult() {
		return true;
	}

	@Override
	public boolean evaluateResult(Agent other) {
		for (ThreatAgent i : maliciousCollectiveB) {
			if (i.getId() == other.getId()) {
				return true;
			}
		}
		return false;
	}

}
