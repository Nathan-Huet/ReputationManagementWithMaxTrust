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

		System.out.println("Position in colums :");
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



