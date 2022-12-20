package agent;

import java.util.LinkedList;

import model_Tropical.TropicalAtom;
import strategy.MaliciousCollectiveStrategy;

/**
 * Une instance de cette classe est utilisée pour modéliser un Agent appartenant à un collectif (supposé malveillant)
 * qui agit dans le système selon une Strategy donnée.
 * Elle a été construite sous le principe que la Stategy nuirait au système
 * mais on peut également imaginer que la Strategy qui le soutienne
 */
public class MaliciousCollectiveAgent extends ThreatAgent {

	/**
	 * Construction d'un Agent appartenant à un collectif malveillant
	 * @param id identifiant de l'Agent
	 * @param numberOfAgents nombre d'Agents
	 * @param strategy Strategy utilisée
	 */
	public MaliciousCollectiveAgent(int id, int numberOfAgents, MaliciousCollectiveStrategy strategy) {
		super(id, numberOfAgents, strategy);
	}

	/**
	 * Donne à l'Agent les autres Agents appartenant à son collectif malveillant
	 * @param maliciousCollective le collectif
	 */
	public void setCollective(LinkedList<MaliciousCollectiveAgent> maliciousCollective) {
		((MaliciousCollectiveStrategy)this.agentStrategy).setCollective(maliciousCollective);
	}
	
	/**
	 * méthode évaluant le résultat d'une interaction avec un autre Agent 
	 * un Agent appartenant au collectif de MaliciousCollectiveAgent recevra de la confiance alors que les autres non
	 * @param other l'autre Agent
	 * @return true si la Strategy de l'Agent évalue le résultat comme positif et false sinon
	 */
	@Override
	public boolean interactsWith(Agent other) {
		boolean result = this.agentStrategy.evaluateResult(other);
		
		if (result) {
			numberOfSuccessfulInteractions[other.id] ++;
		}else {
			numberOfUnsuccessfulInteractions[other.id] ++;
		}
		this.trustVector[other.id] = new TropicalAtom(numberOfSuccessfulInteractions[other.id] - numberOfUnsuccessfulInteractions[other.id]);
		return result;
	}
}
