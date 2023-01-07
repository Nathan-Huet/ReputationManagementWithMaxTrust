package model_Tropical;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TropicalMatrix {
	private int numberOfAgent;
	private TropicalAtom[][] trustMatrix;
	private int[] positionOfAgentInTrustMatrixColumn;
	private int[] positionOfAgentInTrustMatrixRow;
	private double convergence;
	
	public TropicalMatrix(int numberOfAgent, double convergence) {
		this.numberOfAgent = numberOfAgent;
		this.convergence = convergence;
		this.positionOfAgentInTrustMatrixColumn = new int[numberOfAgent];
		this.positionOfAgentInTrustMatrixRow = new int[numberOfAgent];
		this.trustMatrix = new TropicalAtom[numberOfAgent][numberOfAgent];
		
	}
	
	public TropicalMatrix(int numberOfAgent, double convergence, TropicalAtom[][] trustMatrix) {
		this(numberOfAgent, convergence);
		this.trustMatrix = trustMatrix;		
	}
	
	public TropicalMatrix(int numberOfAgent,double convergence, boolean initArbitraryTrustMatrix) {
		this(numberOfAgent, convergence);
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
	
	/**
	 * retourne la transposée de la matrice de confiance
	 * @return transposée de la matrice de confiance
	 */
	public TropicalAtom[][] getTranspose(){
		TropicalAtom[][] transposedMatrix = new TropicalAtom[numberOfAgent][numberOfAgent];
		for (int i = 0; i < trustMatrix.length; i++) {
			for (int j = 0; j < trustMatrix[i].length; j++) {
				transposedMatrix[j][i] = trustMatrix[i][j];
			}
		}
		return transposedMatrix;
	}
	
	/**
	 * retourne le résultat d'une multiplication de matrice par un vecteur
	 * @param matrix matrice
	 * @param vector vecteur
	 * @return résultat d'une multiplication de matrice par un vecteur
	 */
	public TropicalAtom[] tropicalMatrixMultiplicationByVector(TropicalAtom[][] matrix, TropicalAtom[] vector) {
		TropicalAtom[] result = new TropicalAtom[vector.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = new TropicalAtom();
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				result[i] = result[i].tropicalAddition(matrix[i][j].tropicalMultiplication(vector[i]));
			}
		}
		return result;
	}
	
	/**
	 * retourne le résultat d'une multiplication d'un vecteur par un entier
	 * @param vector vecteur
	 * @param integer entier
	 * @return résultat d'une multiplication d'un vecteur par un entier
	 */
	public TropicalAtom[] tropicalVectorMultiplicationByValue(TropicalAtom[] vector, double value) {
		TropicalAtom[] result = new TropicalAtom[vector.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = new TropicalAtom();
		}
		for (int i = 0; i < vector.length; i++) {
			result[i] = vector[i].tropicalMultiplication(new TropicalAtom(value));
		}
		return result;
	}
	
	/**
	 * Addition tropicale vectorielle de deux vecteurs
	 * @param vectorLeft vecteur à gauche de l'addition tropicale
	 * @param vectorRight vecteur à droite de l'addition tropicale
	 * @return somme vectorielle tropicale
	 */
	public TropicalAtom[] tropicalVectorAddition(TropicalAtom[] vectorLeft, TropicalAtom[] vectorRight) {
		TropicalAtom[] result = new TropicalAtom[vectorLeft.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = vectorLeft[i].tropicalAddition(vectorRight[i]);
		}
		
		return result;
	}
	
	public Pair maxPower(TropicalAtom[] r) {
		int p = 0;
		TropicalAtom convergenceAtom = new TropicalAtom(convergence);
		int q = -1;
		ArrayList<TropicalAtom[]> listOfEigenVector = new ArrayList<>();
		listOfEigenVector.add(r);
				
		boolean conditionContinue = true;
		do {
			listOfEigenVector.add(tropicalMatrixMultiplicationByVector(getTranspose(), listOfEigenVector.get(p)));
			p++;
			TropicalAtom[] vp = listOfEigenVector.get(p);
			
			for (int i = 0; i < p; i++) {
				TropicalAtom[] vi = listOfEigenVector.get(i);
				int index = 0;
				while (index < vi.length && vi[index].tropicalMultiplication(convergenceAtom).equals(vp[index])) {					
					index++;
					if (index == vi.length - 1) {
						conditionContinue = false;
						q = i;
					}
				}
				if (q >= 0) {
					break;
				}
			}
		}while(conditionContinue);
		double lambda = convergence / (p-q);
		
		TropicalAtom[] v = new TropicalAtom[numberOfAgent];
		for (int i = 0; i < v.length; i++) {
			v[i] = new TropicalAtom();
		}
		
		for (int i = 1; i < p-q; i++) {
			TropicalAtom[] vqPlusIMoins1 = listOfEigenVector.get(q+i-1);
			double lambdaPower = 1;
			for (int j = 0; j < p-q-i; j++) {
				lambdaPower *= lambda;
			}
			TropicalAtom[] vectorMultiplied = tropicalVectorMultiplicationByValue(vqPlusIMoins1, lambdaPower);
			v = tropicalVectorAddition(v,vectorMultiplied);
		}
		
		return new Pair(lambda,v);
	}

	public void tropicalTrigonalisation(TropicalAtom[] v) {
		int n = numberOfAgent;
		for (int k = 0; k < v.length; k++) {
			TropicalAtom lambda = v[k];
			tropicalMatrixSoustraction(tropicalIdentityMatrix(n).tropicalMatrixMultiplicationByValue(lambda).getTrustMatrix());
			for (int j = n-1; j > k; j--) {
				int i = k;
				while(i<n && trustMatrix[i][j].getValue()==0){
					i ++;
				}
				if (i>n-1) {
					if (k!=j) {
						swapColumn(k, j);
						swapRow(k, j);
						break;
					}
				}else{
					for (int jj = k; jj < j; jj++) {
						double top = -trustMatrix[i][jj].getValue();
						double bot = trustMatrix[i][j].getValue();
						double coef = top/bot;
						if (coef==0 || bot == 0) {
							break;
						}
						addColTropicalMatrix(j,jj,coef);
						addRowTropicalMatrix(jj,j,-coef);
					}
				}
			}
			tropicalMatrixAddition(tropicalIdentityMatrix(n).tropicalMatrixMultiplicationByValue(lambda).getTrustMatrix());
			
		}
	}
	public void addColTropicalMatrix(int i, int ii, double coef){
		for (int k = 0; k < numberOfAgent; k++) { 
			trustMatrix[k][ii].addition(new TropicalAtom(trustMatrix[k][i].getValue()*coef));
		}
	}

	public void addRowTropicalMatrix(int i, int ii, double coef){
		for (int k = 0; k < numberOfAgent; k++) {
			trustMatrix[ii][k].addition(new TropicalAtom(trustMatrix[i][k].getValue()*coef));
		}
	}
	
	public void tropicalMatrixAddition(TropicalAtom[][] m){
		int n = m.length;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trustMatrix[i][j].addition(m[i][j]);
            }
        }
	}
	public void tropicalMatrixSoustraction(TropicalAtom[][] m){
		int n = m.length;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trustMatrix[i][j].soustraction(m[i][j]);
            }
        }
	}
	public TropicalMatrix tropicalMatrixMultiplicationByValue(TropicalAtom value) {
		int n = trustMatrix.length;
		for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trustMatrix[i][j].multiplication(value);
            }
        }
		return this;
	}

	public TropicalMatrix tropicalIdentityMatrix(int n){
		TropicalAtom[][] m = new TropicalAtom[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (i==j) {
					m[i][j] = new TropicalAtom(1);
				}else{
					m[i][j] = new TropicalAtom(0);
				}
			}
		}
		return new TropicalMatrix(n, 1, m);
	}



	//-------------------------------------------

	// test trigonalisation sur des matrice double[][]
	public double[][] trigonalisation(double[][] matrix, double[] v) {
		int n = matrix.length;
		for (int k = 0; k < v.length; k++) {
			double lambda = v[k];
			double[][] m = soustractionMatrixMatrix(matrix,multiplicationValueMatrix(lambda, identityMatrix(n)));
			System.out.println("-------------");
			System.out.println("matrice de base");
			for (int l = 0; l < m.length; l++) {
				System.out.println(Arrays.toString(m[l]));
			}
			
			for (int j = n-1; j > k; j--) {
				int i = k;
				while(i<n && m[i][j]==0){
					i ++;
				}
				if (i>n-1) {
					if (k!=j) {
						System.out.println("["+(k+1)+","+(j+1)+"]");
						m = swapColMatrix(m, k, j);
						m = swapRowMatrix(m, k, j);
						break;
					}
				}else{
					for (int jj = k; jj < j; jj++) {
						double top = -m[i][jj];
						double bot = m[i][j];
						double coef = top/bot;
						if (coef==0 || bot == 0) {
							break;
						}
						System.out.println("top : "+top);
						System.out.println("bot : "+bot);
						System.out.println("i,j,jj : "+i+","+j+","+jj);
						System.out.println("["+(j+1)+","+(jj+1)+","+coef+"]");
						m = addColMatrix(m,j,jj,coef);
						m = addRowMatrix(m,jj,j,-coef);
						for (int l = 0; l < matrix.length; l++) {
							System.out.println(Arrays.toString(m[l]));
						}
					}
				}
			}
			matrix = additionMatrixMatrix(m,multiplicationValueMatrix(lambda, identityMatrix(n)));
			
		}
		return matrix;
	}

	public double[][] addColMatrix(double[][] m, int i, int ii, double coef){
		for (int k = 0; k < m.length; k++) {
			m[k][ii] = m[k][i]*coef + m[k][ii]; 
		}
		return m;
	}

	public double[][] addRowMatrix(double[][] m, int i, int ii, double coef){
		for (int k = 0; k < m.length; k++) {
			m[ii][k] = m[i][k]*coef + m[ii][k]; 
		}
		return m;
	}

	public double[][] swapRowMatrix(double[][] m, int i, int j){
		double[] tmp = m[i];
		m[i] = m[j];
		m[j] = tmp;
		return m;
	}

	public double[][] swapColMatrix(double[][] m, int i, int j){
		double[] tmp = new double[m.length];
		for (int k = 0; k < m.length; k++) {
			tmp[k] = m[k][i];
		}
		for (int k = 0; k < m.length; k++) {
			m[k][i] = m[k][j];
		}
		for (int k = 0; k < m.length; k++) {
			m[k][j] = tmp[k];
		}
		return m;
	}
	public double[][] additionMatrixMatrix(double[][] m1, double[][] m2){
		int n = m1.length;
		double[][] s = new double[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = m1[i][j]+m2[i][j];
            }
        }
		return s;
	}
	public double[][] soustractionMatrixMatrix(double[][] m1, double[][] m2){
		int n = m1.length;
		double[][] s = new double[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = m1[i][j]-m2[i][j];
            }
        }
		return s;
	}

	public double[][] multiplicationValueMatrix(double lambda, double[][] m){
		int n = m.length;
		double[][] s = new double[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = m[i][j]*lambda;
            }
        }
		return s;
	}

	public double[][] identityMatrix(int n){
		double[][] m = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (i==j) {
					m[i][j] = 1;
				}else{
					m[i][j] = 0;
				}
			}
		}
		return m;
	}

	public double[][] independentBloc(double[][] matrix){
		int n = matrix.length;
		double[][] m = matrix;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n-k; i++) {
				int j = n + k;
				if (m[i][i]==m[j][j] || m[i][j]==0) {
					break;
				}else{
					double coef = -m[i][j]/(m[i][i]-m[j][j]);
					m = addColMatrix(m,i,j,coef);
					m = addRowMatrix(m,j,i,-coef);
				}
			}
		}
		return m;
	}
}
