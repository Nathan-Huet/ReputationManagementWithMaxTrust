package main;


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


        // Matrice à trigonaliser
		double[][] matriceExemple1 = {{-1, 1, -1, -1, 1, 1},
									{-2, 2, -1, 5, 1, 0},
									{-2, 1, 0, 3, 1, -1},
									{0, 0, 0, 2, 0, 0},
									{-4, 1, -1, 0, 3, 3},
									{0, 0, 0, 1, 0, 2}};
		double[] valeurPropre1 = {1, 1, 1, 1, 2, 2};

		double[][] matriceExemple2Trigo = {{2, -3, 1, 1, -1},
									{1, -2, 1, 1, 2},
									{-1, 3, 0, -1, 2},
									{2, -9, 2, 4, 7},
									{0, 0, 0, 0, 2}};
		double[] valeurPropre2 = {1, 1, 1, 1, 2};

		double[][] matriceExemple3BlocInde = {{1, 1, 1, 1, 1, 1},
											  {0, 1, 1, 1, 1, 1},
											  {0, 0, 1, 1, 1, 1},
											  {0, 0, 0, 1, 1, 1},
											  {0, 0, 0, 0, 2, 2},
											  {0, 0, 0, 0, 0, 2}};

		double[][] matriceExemple4MatriceJordaBloc = {{2, 0, 1, 2, 2, 0, 0, 0},
													  {0, 2, 1, 1, 3, 0, 0, 0},
													  {0, 0, 2, 0, 4, 0, 0, 0},
													  {0, 0, 0, 2, 5, 0, 0, 0},
													  {0, 0, 0, 0, 2, 0, 0, 0},
													  {0, 0, 0, 0, 0, 3, 1, 5},
													  {0, 0, 0, 0, 0, 0, 3, 4},
													  {0, 0, 0, 0, 0, 0, 0, 3}};

		// Matrice version tropical
		TropicalAtom[][] tropicalMatrice2Exemple = new TropicalAtom[5][5];
		TropicalAtom[] tropicalEgeinVector2Exemple = new TropicalAtom[5];
		for (int i = 0; i < tropicalMatrice2Exemple.length; i++) {
			for (int j = 0; j < tropicalMatrice2Exemple.length; j++) {
				tropicalMatrice2Exemple[i][j] = new TropicalAtom(matriceExemple2Trigo[i][j]);
			}
		}
		for (int i = 0; i < valeurPropre2.length; i++) {
			tropicalEgeinVector2Exemple[i] = new TropicalAtom(valeurPropre2[i]);
		}

		TropicalAtom[][] tropicalMatrice3Exemple = new TropicalAtom[6][6];
		for (int i = 0; i < tropicalMatrice3Exemple.length; i++) {
			for (int j = 0; j < tropicalMatrice3Exemple.length; j++) {
				tropicalMatrice3Exemple[i][j] = new TropicalAtom(matriceExemple3BlocInde[i][j]);
			}
		}

		TropicalAtom[][] tropicalMatrice4Exemple = new TropicalAtom[8][8];
		for (int i = 0; i < matriceExemple4MatriceJordaBloc.length; i++) {
			for (int j = 0; j < matriceExemple4MatriceJordaBloc.length; j++) {
				tropicalMatrice4Exemple[i][j] = new TropicalAtom(matriceExemple4MatriceJordaBloc[i][j]);
			}
		}
		TropicalMatrix tmMatriceExemple4 = new TropicalMatrix(8, 1, tropicalMatrice4Exemple);
		tmMatriceExemple4.tropicalIndependentBloc();
		Application.printTrustMatrix(tmMatriceExemple4.getTrustMatrix());

	}
	

}