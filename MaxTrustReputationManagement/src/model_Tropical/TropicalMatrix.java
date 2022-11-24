package model_Tropical;

public class TropicalMatrix {
	private int numberOfAgent;
	private TropicalAtom[][] trustMatrix;
	private int[] positionOfAgentInTrustMatrixColumn;
	private int[] positionOfAgentInTrustMatrixRow;
	
	public TropicalMatrix(int numberOfAgent) {
		this.numberOfAgent = numberOfAgent;
		this.positionOfAgentInTrustMatrixColumn = new int[numberOfAgent];
		this.positionOfAgentInTrustMatrixRow = new int[numberOfAgent];
		this.trustMatrix = new TropicalAtom[numberOfAgent][numberOfAgent];
		
	}
	
	public TropicalMatrix(int numberOfAgent, TropicalAtom[][] trustMatrix) {
		this(numberOfAgent);
		this.trustMatrix = trustMatrix;		
	}
	
	public TropicalMatrix(int numberOfAgent, boolean initArbitraryTrustMatrix) {
		this(numberOfAgent);
		for (int i = 0; i < numberOfAgent; i++) {
			positionOfAgentInTrustMatrixColumn[i] = i;
			positionOfAgentInTrustMatrixRow[i] = i;
			for (int j = 0; j < numberOfAgent; j++) {
				if(j-i == 0)
					trustMatrix[i][j] = new TropicalAtom();
				else
					trustMatrix[i][j] = new TropicalAtom(j - i);
			}
		}
	}

	public TropicalAtom[][] getTrustMatrix() {
		return trustMatrix;
	}

	public int[] getPositionOfAgentInTrustMatrixColumn() {
		return positionOfAgentInTrustMatrixColumn;
	}

	public int[] getPositionOfAgentInTrustMatrixRow() {
		return positionOfAgentInTrustMatrixRow;
	}
	
	/**
	 * Vérifie si la valeur i et la valeur j sont différentes et comprises entre 0 et le nombre d'agents
	 * @param i valeur i
	 * @param j valeur j
	 * @return true si i et j sont différentes et comprises entre 0 et le nombre d'agents, false sinon
	 */
	private boolean checkValidSwap(int i, int j) {
		return !(i == j || i < 0 || j < 0 || i >= trustMatrix.length || j >= trustMatrix.length);
	}
	
	/**
	 * échange 2 lignes dans la matrice de confiance et maintient le tableau permettant de savoir a quel agent correspond la ligne
	 * @param i indice de la 1ere ligne à échanger
	 * @param j indice de la 2e ligne à échanger
	 */
	public void swapRow(int i, int j) {
		if (checkValidSwap(i, j)) {
			TropicalAtom tmp;
			for (int k = 0; k < numberOfAgent; k++) {
				tmp = trustMatrix[i][k]; 
				trustMatrix[i][k] = trustMatrix[j][k];
				trustMatrix[j][k] = tmp;
			}
			int positionAgentI = positionOfAgentInTrustMatrixRow[i];
			positionOfAgentInTrustMatrixRow[i] = positionOfAgentInTrustMatrixRow[j];
			positionOfAgentInTrustMatrixRow[j] = positionAgentI;
		}
	}
	
	/**
	 * échange 2 colonnes dans la matrice de confiance et maintient le tableau permettant de savoir a quel agent correspond la colonne
	 * @param i indice de la 1ere colonne à échanger
	 * @param j indice de la 2e colonne à échanger
	 */
	public void swapColumn(int i, int j) {
		if (checkValidSwap(i, j)) {
			TropicalAtom tmp;
			for (int k = 0; k < numberOfAgent; k++) {
				tmp = trustMatrix[k][i]; 
				trustMatrix[k][i] = trustMatrix[k][j];
				trustMatrix[k][j] = tmp;
			}
			int positionAgentI = positionOfAgentInTrustMatrixColumn[i];
			positionOfAgentInTrustMatrixColumn[i] = positionOfAgentInTrustMatrixColumn[j];
			positionOfAgentInTrustMatrixColumn[j] = positionAgentI;
		}
	}
	
	public TropicalAtom[][] getTranspose(){
		TropicalAtom[][] transposedMatrix = new TropicalAtom[numberOfAgent][numberOfAgent];
		for (int i = 0; i < trustMatrix.length; i++) {
			for (int j = 0; j < trustMatrix[i].length; j++) {
				transposedMatrix[j][i] = trustMatrix[i][j];
			}
		}
		return transposedMatrix;
	}
}
