package main;

import java.util.Iterator;

import model_Tropical.TropicalMatrix;

public class Application {
	public static void main(String[] args) {
		Application app = new Application();
		app.launch();
	}

	public void launch() {
		int numberOfAgent = 5;

		
		TropicalMatrix tm = new TropicalMatrix(numberOfAgent);
		
		printTrustMatrix(tm.getTrustMatrix());
		tm.swapColumn(0, 1);
		System.out.println();
		printTrustMatrix(tm.getTrustMatrix());
		
	}

	public void printTrustMatrix(int[][] trustMatrix) {
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
	
	
}
