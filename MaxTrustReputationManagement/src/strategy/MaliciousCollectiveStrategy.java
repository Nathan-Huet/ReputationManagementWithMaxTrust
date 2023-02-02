package strategy;

import java.util.LinkedList;

import agent.MaliciousCollectiveAgent;

public abstract class MaliciousCollectiveStrategy extends StrategyAbstract {	
	protected LinkedList<MaliciousCollectiveAgent> maliciousCollective = null;

	/**
	 * Donne à l'Agent les autres Agents appartenant à son collectif malveillant
	 * @param maliciousCollective le collectif
	 */
	public void setCollective(LinkedList<MaliciousCollectiveAgent> maliciousCollective) {
		this.maliciousCollective = maliciousCollective;
	}
}
