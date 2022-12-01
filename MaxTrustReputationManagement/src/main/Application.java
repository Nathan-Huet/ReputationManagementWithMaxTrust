package main;

import java.util.Iterator;

import model_Tropical.TropicalAtom;
import model_Tropical.TropicalMatrix;

public class Application {
	public static void main(String[] args) {
		Application app = new Application();
		app.launch();
	}

	public void launch() {
		int numberOfAgent = 5;


		TropicalMatrix tm = new TropicalMatrix(numberOfAgent, true);

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
		printTrustMatrix(tm.getTrustMatrix());
		System.out.println();
		TropicalMatrix transposed = new TropicalMatrix(numberOfAgent, tm.getTranspose());
		printTrustMatrix(transposed.getTrustMatrix());

		// test tropical Addition et multiplication
		TropicalAtom[][] trustMat = tm.getTrustMatrix();
		System.out.println();
		System.out.println("tropicalAddition");
		System.out.println("[0][0] et [0][1] : "+trustMat[0][0].TropicalAddition(trustMat[0][1]).toString());
		System.out.println("[0][1] et [0][0] : "+trustMat[0][1].TropicalAddition(trustMat[0][0]).toString());
		System.out.println("[0][0] et [1][1] : "+trustMat[0][0].TropicalAddition(trustMat[1][1]).toString());
		System.out.println();
		System.out.println("tropicalMultiplication");
		System.out.println("[0][0] et [0][1] : "+trustMat[0][0].TropicalMultiplication(trustMat[0][1]).toString());
		System.out.println("[0][1] et [0][0] : "+trustMat[0][1].TropicalMultiplication(trustMat[0][0]).toString());
		System.out.println("[0][0] et [1][1] : "+trustMat[0][0].TropicalMultiplication(trustMat[1][1]).toString());
		System.out.println("[1][0] et [0][1] : "+trustMat[1][0].TropicalMultiplication(trustMat[0][1]).toString());
		System.out.println("[0][2] et [0][3] : "+trustMat[0][2].TropicalMultiplication(trustMat[0][3]).toString());
	}

	public void printTrustMatrix(TropicalAtom[][] trustMatrix) {
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



