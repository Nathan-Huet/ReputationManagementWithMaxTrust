package strategy;

import java.util.LinkedList;
import java.util.Random;

import agent.ThreatAgent;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent produisant
 * un pourcentage de résultats positifs pour contrebalancer les résultats négatifs qu'il produit
 * appartenant à un collectif malveillant (dans les faits la méthode evaluateResult n'est pas utilisée 
 * car les Agents du collectif s'évaluent positivement entre eux et évaluent les autres négativement)
 */
public class ThreatCStrategy implements Strategy{
	private double probabilityOfPositiveResult;
	
	/**
	 * Construction d'une Strategy malveillante produisant un pourcentage de résultats positifs
	 * @param probabilityOfPositiveResult le pourcentage de résultats positifs de la Strategy
	 */
	public ThreatCStrategy(double probabilityOfPositiveResult) {
		this.probabilityOfPositiveResult = probabilityOfPositiveResult;
	}

	@Override
	public boolean getInteractionResult() {
		Random random = new Random();
		return random.nextDouble() <= probabilityOfPositiveResult;
	}

	@Override
	public boolean evaluateResult(boolean interactionResult) {
		return false;
	}
}
