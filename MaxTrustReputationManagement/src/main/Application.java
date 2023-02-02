package main;


import model_Tropical.*;

public class Application {
	
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