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



