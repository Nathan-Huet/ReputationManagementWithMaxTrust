package factory;

import java.util.LinkedList;

import agent.Agent;
import agent.MaliciousCollectiveAgent;

public abstract class MaliciousCollectiveFactory implements AgentFactory{
	
	@Override
	public abstract MaliciousCollectiveAgent getAgent(int numberOfAgents);
	
	/**
	 * Créé un collectif d'Agents utilisant une Strategy collective
	 * @param numberOfAgents le nombre d'Agents
	 * @param numberInCollective le nombre d'Agents dans le collectif à créer
	 * @return le collectif d'Agent nouvellement créé
	 */
	public LinkedList<Agent> getCollective(int numberOfAgents, int numberInCollective) {
		LinkedList<MaliciousCollectiveAgent> collective = new LinkedList<>();
		LinkedList<Agent> collectiveOfAgent = new LinkedList<>();
		for (int i = 0; i < numberInCollective; i++) {
			MaliciousCollectiveAgent agent = this.getAgent(numberOfAgents);
			collective.add(agent);
			collectiveOfAgent.add(agent);
		}
		for (MaliciousCollectiveAgent agent : collective) {
			agent.setCollective(collective);
		}
		return collectiveOfAgent;
	}
}
