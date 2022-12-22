package model_Tropical;

public class Pair {

	private double dominantEigenValue;
	private TropicalAtom[] dominantEigenVector;

	public Pair(double lambda, TropicalAtom[] v) {
		this.dominantEigenValue = lambda;
		this.dominantEigenVector = v;
	}

	public double getDominantEigenValue() {
		return dominantEigenValue;
	}

	public TropicalAtom[] getDominantEigenVector() {
		return dominantEigenVector;
	}

	
}
