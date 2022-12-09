package factory;

import agent.Agent;

/**
 * Cette interface est utilisée afin de permettre la génération d'Agents
 *
 */
public interface AgentFactory {
	/**
	 * Créé nouvel Agent
	 * @param name le nom de l'Agent
	 * @return un nouvel Agent
	 */
	public Agent getAgent(String name);
}
