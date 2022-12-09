package agent;

/**
 * Ce Singleton est utilisée pour numéroter les Agents 
 * dans l'ordre dans lequel ils sont créés
 *
 */
public class IdCreator {
	private static IdCreator idCreator;
	private int numberOfAgents;

	private IdCreator() {}
	
	/**
	 * initialise si besoin et retourne l'instance de IdCreator
	 * @return l'instance du Singleton IdCreator
	 */
	public static IdCreator getIdCreator() {
		if (idCreator == null) {
			idCreator = new IdCreator();
			idCreator.numberOfAgents = 0;
		}
		return idCreator;
	}

	/**
	 * retourne un nouvel identifiant pour un Agent
	 * @return nouvel identifiant pour un Agent
	 */
	public String getAgentId() {
		String id = "A";
		int num = idCreator.numberOfAgents;
		numberOfAgents ++;
		return id + num;
	}
}
