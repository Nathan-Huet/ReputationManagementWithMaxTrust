package strategy;

import java.util.LinkedList;

import agent.ThreatAgent;

/**
 * Cette classe est utilisée pour modéliser la Strategy d'un Agent produisant toujours des résultats négatifs
 * appartenant à un collectif malveillant (dans les faits la méthode evaluateResult n'est pas utilisée car 
 * les Agents du collectif s'évaluent positivement entre eux et évaluent les autres négativement)
 */
public class ThreatBStrategy implements Strategy{
	
	@Override
	public boolean getInteractionResult() {
		return false;
	}

	@Override
	public boolean evaluateResult(boolean interactionResult) {
		return false;
	}
}
