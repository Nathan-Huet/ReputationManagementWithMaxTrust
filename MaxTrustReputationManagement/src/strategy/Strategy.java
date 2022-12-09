package strategy;

/**
 * Cette interface est utilisée pour modéliser la Strategy d'un Agent et sa manière d'évaluer ses pairs
 *
 */
public interface Strategy {

	/**
	 * calcule si le résultat est positif lorsque la Strategy est appliquée
	 * @return true si le résultat est positif et false sinon
	 */
	public boolean getInteractionResult();

	/**
	 * évaluation du résultat selon le point de vue de la Strategy
	 * @param interactionResult résultat d'une interaction true si l'interaction a produit un bon résultat et false sinon
	 * @return true si la Strategy est satisfaite du résultat et false sinon
	 */
	public boolean evaluateResult(boolean interactionResult);

}
