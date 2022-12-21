package main;

import model_Tropical.TropicalAtom;
import model_Tropical.TropicalMatrix;

public class TestMaxTrust {
	public static void main(String[] args) {
		TestMaxTrust tmt = new TestMaxTrust();
		tmt.launch();
	}

	public void launch() {
		int numberAgents = 3;
		int convergence = 1;
		TropicalMatrix tm = new TropicalMatrix(numberAgents, convergence, true);
		TropicalAtom[] r =  tm.getTrustMatrix()[0];
		double lambda = tm.maxPower(r);
		System.out.println(lambda);
	}
}
