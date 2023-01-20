package main;

import java.util.Arrays;

import model_Tropical.*;

public class Application {
	public static void main(String[] args) {
		Application app = new Application();
		app.launch();
	}

	public void launch() {
		int numberOfAgent = 5;
		
		int convergence = 1;
		TropicalMatrix tm = new TropicalMatrix(numberOfAgent, convergence, true);

		/*System.out.println("Position in colums :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixColumn());
		System.out.println("Position in rows :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixRow());
		System.out.println();
		printTrustMatrix(tm.getTrustMatrix());
		tm.swapColumn(0, 4);
		System.out.println();
		System.out.println();
		System.out.println("Position in colums :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixColumn());
		System.out.println("Position in rows :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixRow());
		System.out.println();
		printTrustMatrix(tm.getTrustMatrix());
		
		tm.swapColumn(1, 3);
		System.out.println();
		System.out.println();
		System.out.println("Position in colums :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixColumn());
		System.out.println("Position in rows :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixRow());
		System.out.println();
		printTrustMatrix(tm.getTrustMatrix());
		
		
		tm.swapRow(1, 3);
		System.out.println();
		System.out.println();
		System.out.println("Position in colums :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixColumn());
		System.out.println("Position in rows :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixRow());
		System.out.println();
		printTrustMatrix(tm.getTrustMatrix());
		
		tm.swapRow(4, 3);
		System.out.println();
		System.out.println();
		System.out.println("Position in colums :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixColumn());
		System.out.println("Position in rows :");
		printPositionOfAgents(tm.getPositionOfAgentInTrustMatrixRow());
		System.out.println();
		printTrustMatrix(tm.getTrustMatrix());
		*/
		/*
		printTrustMatrix(tm.getTrustMatrix());
		System.out.println();
		TropicalMatrix transposed = new TropicalMatrix(numberOfAgent, convergence, tm.getTranspose());
		printTrustMatrix(transposed.getTrustMatrix());
		*/
		// test tropical Addition et multiplication
		/*
		TropicalAtom[][] trustMat = tm.getTrustMatrix();
		System.out.println();
		System.out.println("tropicalAddition");
		System.out.println("[0][0] et [0][1] : "+trustMat[0][0].tropicalAddition(trustMat[0][1]).toString());
		System.out.println("[0][1] et [0][0] : "+trustMat[0][1].tropicalAddition(trustMat[0][0]).toString());
		System.out.println("[0][0] et [1][1] : "+trustMat[0][0].tropicalAddition(trustMat[1][1]).toString());
		System.out.println();
		System.out.println("tropicalMultiplication");
		System.out.println("[0][0] et [0][1] : "+trustMat[0][0].tropicalMultiplication(trustMat[0][1]).toString());
		System.out.println("[0][1] et [0][0] : "+trustMat[0][1].tropicalMultiplication(trustMat[0][0]).toString());
		System.out.println("[0][0] et [1][1] : "+trustMat[0][0].tropicalMultiplication(trustMat[1][1]).toString());
		System.out.println("[1][0] et [0][1] : "+trustMat[1][0].tropicalMultiplication(trustMat[0][1]).toString());
		System.out.println("[0][2] et [0][3] : "+trustMat[0][2].tropicalMultiplication(trustMat[0][3]).toString());
		*/

        // Matrice à trigonaliser
		double[][] matriceExemple1 = {{-1, 1, -1, -1, 1, 1},
									{-2, 2, -1, 5, 1, 0},
									{-2, 1, 0, 3, 1, -1},
									{0, 0, 0, 2, 0, 0},
									{-4, 1, -1, 0, 3, 3},
									{0, 0, 0, 1, 0, 2}};
		double[] valeurPropre1 = {1, 1, 1, 1, 2, 2};

		double[][] matriceExemple2 = {{2, -3, 1, 1, -1},
									{1, -2, 1, 1, 2},
									{-1, 3, 0, -1, 2},
									{2, -9, 2, 4, 7},
									{0, 0, 0, 0, 2}};
		double[] valeurPropre2 = {1, 1, 1, 1, 2};
		/*
		// trigonalisation version double[][]
		double[][] m = tm.trigonalisation(matriceExemple2,valeurPropre2);
		System.out.println("----final");
		for (int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }*/

		TropicalAtom[][] tropicalMatrice2Exemple = new TropicalAtom[5][5];
		TropicalAtom[] tropicalEgeinVector2Exemple = new TropicalAtom[5];
		for (int i = 0; i < tropicalMatrice2Exemple.length; i++) {
			for (int j = 0; j < tropicalMatrice2Exemple.length; j++) {
				tropicalMatrice2Exemple[i][j] = new TropicalAtom(matriceExemple2[i][j]);
			}
		}
		for (int i = 0; i < valeurPropre2.length; i++) {
			tropicalEgeinVector2Exemple[i] = new TropicalAtom(valeurPropre2[i]);
		}
		TropicalMatrix tmMatriceExemple2 = new TropicalMatrix(5, 1, tropicalMatrice2Exemple);
		tmMatriceExemple2.tropicalTrigonalisation(tropicalEgeinVector2Exemple);
		printTrustMatrix(tmMatriceExemple2.getTrustMatrix());
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
	public static void printBooleanMatrix(boolean[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("[");
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] );
				if (j == matrix[i].length -1) 
					System.out.println("]");
				else 
					System.out.print(",\t");
			}

		}

	}
	public static void printTrustVector(TropicalAtom[] trustVector) {
		System.out.print("[");
		for (int i = 0; i < trustVector.length; i++) {
			System.out.print(trustVector[i] );
			if (i == trustVector.length -1) 
				System.out.println("]");
			else 
				System.out.print(",\t");
		}

	}



	public void printPositionOfAgents(int[] positionOfAgents) {
		System.out.print("[");
		for (int i = 0; i < positionOfAgents.length; i++) {
			System.out.print(positionOfAgents[i]);
			if (i == positionOfAgents.length -1) 
				System.out.println("]");
			else 
				System.out.print(",\t");
		}
	}


}