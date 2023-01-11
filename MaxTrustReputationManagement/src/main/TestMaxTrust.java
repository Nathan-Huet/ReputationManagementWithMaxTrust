package main;

import java.util.Arrays;

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
		//System.out.println(pair.getDominantEigenValue());
		//Application.printTrustVector(pair.getDominantEigenVector());


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
		
		// Matrice version double[][]
		//double[][] m = tm.trigonalisation(matriceExemple2Trigo,valeurPropre2);
		//double[][] m = tm.formNormalJordan(matriceExemple2Trigo, valeurPropre2);
		//double[][] m = tm.independentBloc(matriceExemple3MatriceIndeBloc);
		afficheMatriceDouble(matriceExemple4MatriceJordaBloc);
		double[][] m = tm.jordaniser_blocs(matriceExemple4MatriceJordaBloc);
		afficheMatriceDouble(m);
		

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
		//TropicalMatrix tmMatriceExemple2 = new TropicalMatrix(5, 1, tropicalMatrice2Exemple);
		//tmMatriceExemple2.tropicalFormNormalJordan(tropicalEgeinVector2Exemple);
		//System.out.println("--- Matrice tropical J");
		//printTrustMatrix(tmMatriceExemple2.getTrustMatrix());

		TropicalAtom[][] tropicalMatrice3Exemple = new TropicalAtom[6][6];
		for (int i = 0; i < tropicalMatrice3Exemple.length; i++) {
			for (int j = 0; j < tropicalMatrice3Exemple.length; j++) {
				tropicalMatrice3Exemple[i][j] = new TropicalAtom(matriceExemple3BlocInde[i][j]);
			}
		}
		//TropicalMatrix tmMatriceExemple3 = new TropicalMatrix(6, 1, tropicalMatrice3Exemple);
		//tmMatriceExemple3.tropicalIndependentBloc();;
		//printTrustMatrix(tmMatriceExemple3.getTrustMatrix());

	}

	public static void printTrustMatrix(TropicalAtom[][] trustMatrix) {
		for (int i = 0; i < trustMatrix.length; i++) {
			System.out.print("[");
			for (int j = 0; j < trustMatrix[i].length; j++) {
				System.out.print(trustMatrix[i][j] );
				if (j == trustMatrix[i].length -1) 
					System.out.println("]");
				else 
					System.out.print(",\t");
			}
		}
	}

	public void afficheMatriceDouble(double[][] m){
		for (int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }
	}
}