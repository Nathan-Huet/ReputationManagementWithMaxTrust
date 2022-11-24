package model_Tropical;

public class TropicalMatrix {
	private int numberOfAgent;
	private int[][] trustMatrix;
	private int[] positionOfAgentInTrustMatrixColumn;
	private int[] positionOfAgentInTrustMatrixRow;
	
	public TropicalMatrix(int numberOfAgent) {
		this.numberOfAgent = numberOfAgent;
		this.positionOfAgentInTrustMatrixColumn = new int[numberOfAgent];
		this.positionOfAgentInTrustMatrixRow = new int[numberOfAgent];
		
		this.trustMatrix = new int[numberOfAgent][numberOfAgent];
		for (int i = 0; i < numberOfAgent; i++) {
			positionOfAgentInTrustMatrixColumn[i] = i;
			positionOfAgentInTrustMatrixRow[i] = i;
			for (int j = 0; j < numberOfAgent; j++) {
				trustMatrix[i][j] = j - i;
			}
		}
	}

	public int[][] getTrustMatrix() {
		return trustMatrix;
	}

	public int[] getPositionOfAgentInTrustMatrixColumn() {
		return positionOfAgentInTrustMatrixColumn;
	}

	public int[] getPositionOfAgentInTrustMatrixRow() {
		return positionOfAgentInTrustMatrixRow;
	}
	
	private boolean checkValidSwap(int i, int j) {
		return !(i == j || i < 0 || j < 0 || i >= trustMatrix.length || j >= trustMatrix.length);
	}
	
	public void swapRow(int i, int j) {
		if (checkValidSwap(i, j)) {
			int[] rowI = new int[numberOfAgent];
			for (int k = 0; k < numberOfAgent; k++) {
				rowI[k] = trustMatrix[i][k]; 
				trustMatrix[i][k] = trustMatrix[j][k];
				trustMatrix[j][k] = rowI[k];
			}
		}
	}
	
	public void swapColumn(int i, int j) {
		if (checkValidSwap(i, j)) {
			int[] columnI = new int[numberOfAgent];
			for (int k = 0; k < numberOfAgent; k++) {
				columnI[k] = trustMatrix[k][i]; 
				trustMatrix[k][i] = trustMatrix[k][j];
				trustMatrix[k][j] = columnI[k];
			}
		}
	}
	
}
