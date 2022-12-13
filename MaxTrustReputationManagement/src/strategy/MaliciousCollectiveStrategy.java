package strategy;

import java.util.LinkedList;

import agent.Agent;
import agent.MaliciousCollectiveAgent;
import agent.ThreatAgent;

public abstract class MaliciousCollectiveStrategy implements Strategy {	
	protected LinkedList<MaliciousCollectiveAgent> maliciousCollective = null;

	/**
	 * Donne à l'Agent les autres Agents appartenant à son collectif malveillant
	 * @param maliciousCollective le collectif
	 */
	public void setCollective(LinkedList<MaliciousCollectiveAgent> maliciousCollective) {
		this.maliciousCollective = maliciousCollective;
	}
}
