package factory;

import agent.Agent;

/**
 * Cette interface est utilisée afin de permettre la génération d'Agents
 *
 */
public interface AgentFactory {
	/**
	 * Créé nouvel Agent
	 * @param id identifiant de l'Agent
	 * @param numberOfAgents nombre d'Agents
	 * @return un nouvel Agent
	 */
	public Agent getAgent(int numberOfAgents);
}
