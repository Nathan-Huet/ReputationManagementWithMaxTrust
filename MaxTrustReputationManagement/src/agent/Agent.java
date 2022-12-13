package agent;

import java.util.Arrays;

import model_Tropical.TropicalAtom;
import strategy.Strategy;

/**
 * Une instance de cette classe est utilisée afin de modéliser des interactions 
 * entre Agent dans un environnement où certains Agents ont des intentions malveillantes
 *
 */
public abstract class Agent {

	protected Strategy agentStrategy;
	protected int id;
	protected TropicalAtom[] trustVector;
	protected int[] numberOfSuccessfulInteractions;
	protected int[] numberOfUnsuccessfulInteractions;
	
	
	/**
	 * Construction par défaut d'un Agent
	 * @param id identifiant de l'Agent
	 * @param numberOfAgents nombre d'Agents
	 */
	public Agent(int id, int numberOfAgents ) {
		this.id = id;
		this.trustVector = new TropicalAtom[numberOfAgents];
		this.numberOfSuccessfulInteractions = new int[numberOfAgents];
		this.numberOfUnsuccessfulInteractions = new int[numberOfAgents];
		
		for (int i = 0; i < trustVector.length; i++) {
			if (i == id) {
				trustVector[i] = new TropicalAtom(0);
			}else {
				trustVector[i] = new TropicalAtom();
			}
			numberOfSuccessfulInteractions[i] = 0;
			numberOfUnsuccessfulInteractions[i] = 0;
		}
	}
	
	
	/**
	 * retourne l'identifiant de l'agent
	 * @return identifiant de l'agent
	 */
	public int getId() {
		return id;
	}

	
	/**
	 * méthode évaluant le résultat d'une interaction avec un autre Agent 
	 * la façon d'évaluer si le résultat est satisfaisant dépend de la Strategy de l'Agent courant
	 * @param other l'autre Agent
	 * @return true si la Strategy de l'Agent évalue le résultat comme positif et false sinon
	 */
	public boolean interactsWith(Agent other) {
		boolean result = agentStrategy.evaluateResult(other);
		if (result) {
			numberOfSuccessfulInteractions[other.id] ++;
		}else {
			numberOfUnsuccessfulInteractions[other.id] ++;
		}
		this.trustVector[other.id] = new TropicalAtom(numberOfSuccessfulInteractions[other.id] - numberOfUnsuccessfulInteractions[other.id]);
		return result;
	}
	
	/**
	 * retourne le résultat d'une interaction selon la Strategy de l'Agent
	 * @return résultat d'une interaction selon la Strategy de l'Agent
	 */
	public boolean getInteractionResult() {
		return this.agentStrategy.getInteractionResult();
	}
	
	/**
	 * Retourne le vecteur de confiance de l'Agent
	 * @return le vecteur de confiance de l'Agent
	 */
	public TropicalAtom[] getTrustVector() {
		return trustVector;
	}
	
	@Override
	public String toString() {
		return "A" + id + " " + Arrays.toString(trustVector);
	}	
	
	
}
