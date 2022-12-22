package main;

import model_Tropical.Pair;
import model_Tropical.TropicalAtom;
import model_Tropical.TropicalMatrix;

public class TestMaxTrust {
	public static void main(String[] args) {
		TestMaxTrust tmt = new TestMaxTrust();
		tmt.launch();
	}

	public void launch() {
		int numberAgents = 2;
		int convergence = 1;
		TropicalAtom[][] tat = new TropicalAtom[2][2];
		tat[0][0] = new TropicalAtom(0.1);
		tat[0][1] = new TropicalAtom(0.9);
		tat[1][0] = new TropicalAtom(0.1);
		tat[1][1] = new TropicalAtom(0.9);
		
		
		TropicalMatrix tm = new TropicalMatrix(numberAgents, convergence, tat);
		TropicalAtom[] r =  tm.getTrustMatrix()[0];
		Pair pair = tm.maxPower(r);
		System.out.println(pair.getDominantEigenValue());
		Application.printTrustVector(pair.getDominantEigenVector());
	}
}
