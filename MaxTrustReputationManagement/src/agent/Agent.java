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
	protected int numberOfAgents;
	protected Strategy agentStrategy;
	protected int id;
	protected int[] numberOfSuccessfulInteractions;
	protected int[] numberOfUnsuccessfulInteractions;
	private int[] realNumberOfUnsuccessfulInteractions;
	private int[] realNumberOfSuccessfulInteractions;


	/**
	 * Construction par défaut d'un Agent
	 * @param id identifiant de l'Agent
	 * @param numberOfAgents nombre d'Agents
	 */
	public Agent(int id, int numberOfAgents ) {
		this.id = id;
		this.numberOfAgents = numberOfAgents;
		this.numberOfSuccessfulInteractions = new int[numberOfAgents];
		this.numberOfUnsuccessfulInteractions = new int[numberOfAgents];
		this.realNumberOfSuccessfulInteractions = new int[numberOfAgents];
		this.realNumberOfUnsuccessfulInteractions = new int[numberOfAgents];

		for (int i = 0; i < numberOfAgents; i++) {
			numberOfSuccessfulInteractions[i] = 0;
			numberOfUnsuccessfulInteractions[i] = 0;
			realNumberOfSuccessfulInteractions[i] = 0;
			realNumberOfUnsuccessfulInteractions[i] = 0;
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
	 * Retourne le nombre d'interactions réussies
	 * @return le nombre d'interactions réussies
	 */
	public int[] getNumberOfSuccessfulInteractions() {
		return numberOfSuccessfulInteractions;
	}

	/**
	 * Retourne le nombre d'interactions non réussies
	 * @return le nombre d'interactions non réussies
	 */
	public int[] getNumberOfUnsuccessfulInteractions() {
		return numberOfUnsuccessfulInteractions;
	}

	/**
	 * Retourne le nombre d'interactions réussies
	 * @return le nombre d'interactions réussies
	 */
	public int[] getRealNumberOfSuccessfulInteractions() {
		return realNumberOfSuccessfulInteractions;
	}

	/**
	 * Retourne le nombre d'interactions non réussies
	 * @return le nombre d'interactions non réussies
	 */
	public int[] getRealNumberOfUnsuccessfulInteractions() {
		return realNumberOfUnsuccessfulInteractions;
	}

	/**
	 * retourne un tableau de booleen true si l'agent connait l'agent de la position dans le tableau, false sinon 
	 * @return tableau de booleen true si l'agent connait l'agent de la position dans le tableau, false sinon
	 */
	public boolean[] getUnknownAgents(){
		TropicalAtom[] trustVector = getTrustVector();
		boolean[] unknownAgents = new boolean[trustVector.length];
		for (int i = 0; i < trustVector.length; i++) {
			if (trustVector[i].isNegativeInfinite()) {
				unknownAgents[i] = true;
			}else{
				unknownAgents[i] = false;
			}
		}
		return unknownAgents;
	}

	/**
	 * méthode évaluant le résultat d'une interaction avec un autre Agent 
	 * la façon d'évaluer si le résultat est satisfaisant dépend de la Strategy de l'Agent courant
	 * @param other l'autre Agent
	 * @return true si la Strategy de l'Agent évalue le résultat comme positif et false sinon
	 */
	public boolean interactsWith(Agent other) {
		boolean interactionResult = other.getInteractionResult();
		boolean result = agentStrategy.evaluateResult(other,interactionResult);
		if (result) {
			numberOfSuccessfulInteractions[other.id] ++;
		}else {
			numberOfUnsuccessfulInteractions[other.id] ++;
		}

		if (interactionResult) {
			realNumberOfSuccessfulInteractions[other.id] ++;
		}else {
			realNumberOfUnsuccessfulInteractions[other.id] ++;
		}

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
		double sik = 0;
		TropicalAtom[] trustVector = new TropicalAtom[numberOfAgents];
		for (int k = 0; k < numberOfAgents; k++) {
			sik += Math.max(0, numberOfSuccessfulInteractions[k] - numberOfUnsuccessfulInteractions[k]);
		}
		for (int j = 0; j < numberOfAgents; j++) {
			if (numberOfSuccessfulInteractions[j] == 0 && numberOfUnsuccessfulInteractions[j] == 0) {
				trustVector[j] = new TropicalAtom(); 
			}
			else {
				double sij = Math.max(0, numberOfSuccessfulInteractions[j] - numberOfUnsuccessfulInteractions[j]);
				double cij = sij/sik;
				if (Double.isNaN(cij)) {
					cij = 0.0;
				}
				trustVector[j] = new TropicalAtom(cij);
			}
		}
		return trustVector;
	}

	@Override
	public String toString() {
		return "A" + getId() + " " + Arrays.toString(getTrustVector());
	}	


}
