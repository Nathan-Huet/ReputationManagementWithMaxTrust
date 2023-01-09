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


	//-------------------------------------------
	//-------------------------------------------
	// trigonalisation sur des matrice tropical
	//-------------------------------------------
	//-------------------------------------------

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
							continue;
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


	public void tropicalIndependentBloc(){
		int n = numberOfAgent;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n-k; i++) {
				int j = i + k;
				double top = -trustMatrix[i][j].getValue();
				double bot = trustMatrix[i][i].getValue()-trustMatrix[j][j].getValue();
				if (trustMatrix[i][i].getValue()==trustMatrix[j][j].getValue() || trustMatrix[i][j].getValue()==0 || bot==0) {
					continue;
				}else{
					double coef = top/bot;
					addColTropicalMatrix(i,j,coef);
					addRowTropicalMatrix(j,i,-coef);
				}
			}
		}
	}

	public void tropicalJordaniser_blocs(){
		int n = numberOfAgent;
		for (int k = 1; k < n; k++) {
			tropicalTraiter_colonne(k);
		}
	}

	private void tropicalTraiter_colonne(int k) {
		tropicalAnnuler_coefs_colonne(k);
		tropicalNormaliser_coefs_colonne(k);
		tropicalConfrontation_coefs(k);
		tropicalDeplace_colonnes(k);
	}

	private void tropicalDeplace_colonnes(int k) {
		int l=0;
		for (int i = 0; i < k; i++) {
			if (trustMatrix[i][k].getValue()!=0) {
				l = i;
				break;
			}
		}
		if (l>0) {
			for (int j = k; j >= l+2; j--) {
				swapColumn(j, j-1);
				swapRow(j, j-1);
			}
		}
	}

	private void tropicalConfrontation_coefs(int k) {
		int debut_bloc1 = 0;
		int fin_bloc1 = 0;
		int debut_bloc2 = 0;
		int fin_bloc2 = 0;
		for (int i = 0; i < k; i++) {
			if (trustMatrix[i][k].getValue()!=0) {
				fin_bloc1 = i;
				break;
			}
		}
		if (fin_bloc1>0) {
			debut_bloc1 = tropicalLimite_bloc_jordan(fin_bloc1);
			for (int i = fin_bloc1+1; i < k-1; i++) {
				if (trustMatrix[i][k].getValue()!=0) {
					fin_bloc2 = i;
					debut_bloc2 = tropicalLimite_bloc_jordan(fin_bloc2);
					if (fin_bloc2-debut_bloc2>=fin_bloc1-debut_bloc1) {
						for (int ii = fin_bloc1; ii >= debut_bloc1; ii--) {
							addColTropicalMatrix(ii, fin_bloc2+ii-fin_bloc1, 1);
							addRowTropicalMatrix(fin_bloc2+ii-fin_bloc1, ii, -1);
						}
						debut_bloc1 = debut_bloc2;
						fin_bloc1 = fin_bloc2;
					}else{
						for (int ii = fin_bloc2; ii >= debut_bloc2; ii--) {
							addColTropicalMatrix(ii, fin_bloc1+ii-fin_bloc2, 1);
							addRowTropicalMatrix(fin_bloc1+ii-fin_bloc2, ii, -1);
						}
					}
				}
			}
		}
	}

	private void tropicalNormaliser_coefs_colonne(int k) {
		int n = numberOfAgent;
		for (int fin_bloc = 0; fin_bloc < n; fin_bloc++) {
			double coef = trustMatrix[fin_bloc][k].getValue();
			if (coef !=0 && coef!=1) {
				int debut_bloc = tropicalLimite_bloc_jordan(fin_bloc);
				for (int i = fin_bloc; i > debut_bloc; i--) {
					tropicalMulCol(i,coef);
					tropicalMulRow(i,1/coef);
				}
			}
		}
	}

	private int tropicalLimite_bloc_jordan(int l) {
		int debut_bloc = l;
        for (int i = l-1; i > 1; i--) {
			if (trustMatrix[i][i+1].getValue()!=0) {
				debut_bloc = i;
			}else{
				break;
			}
		}
		return debut_bloc;
	}

	private void tropicalMulCol(int i, double coef) {
		int n = numberOfAgent;
        for(int j = 0; j < n; j++) {
			trustMatrix[j][i].multiplication(new TropicalAtom(coef));
        }
	}

	private void tropicalMulRow(int i, double coef) {
		int n = numberOfAgent;
        for(int j = 0; j < n; j++) {
			trustMatrix[i][j].multiplication(new TropicalAtom(coef));
        }
	}

	private void tropicalAnnuler_coefs_colonne(int k) {
		int n = numberOfAgent;
		for (int i = 0; i < k-1; i++) {
			if (trustMatrix[i][k].getValue()!=0 && trustMatrix[i][i+1].getValue()!=0) {
				double coef = (-trustMatrix[i][k].getValue())/trustMatrix[i][i+1].getValue();
				addColTropicalMatrix(i+1, k, coef);
				addRowTropicalMatrix(k, i+1, coef);
			}
		}
	}

	public void tropicalFormNormalJordan(TropicalAtom[] vecteur){
		System.out.println("--- Matrice tropical");
		tropicalTrigonalisation(vecteur);
		tropicalIndependentBloc();
		tropicalJordaniser_blocs();
		printTrustMatrix(trustMatrix);
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


	//-------------------------------------------------
	//-------------------------------------------------
	// test trigonalisation sur des matrice double[][]
	//-------------------------------------------------
	//-------------------------------------------------

	public double[][] trigonalisation(double[][] matrix, double[] v) {
		int n = matrix.length;
		for (int k = 0; k < v.length; k++) {
			double lambda = v[k];
			double[][] m = soustractionMatrixMatrix(matrix,multiplicationValueMatrix(lambda, identityMatrix(n)));
			/*
			System.out.println("-------------");
			System.out.println("matrice de base");
			for (int l = 0; l < m.length; l++) {
				System.out.println(Arrays.toString(m[l]));
			}*/
			
			for (int j = n-1; j > k; j--) {
				int i = k;
				while(i<n && m[i][j]==0){
					i ++;
				}
				if (i>n-1) {
					if (k!=j) {
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
							continue;
						}
						m = addColMatrix(m,j,jj,coef);
						m = addRowMatrix(m,jj,j,-coef);
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
		System.out.println(n);
		double[][] m = matrix;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n-k; i++) {
				int j = i + k;
				double top = -m[i][j];
				double bot = m[i][i]-m[j][j];
				if (m[i][i]==m[j][j] || m[i][j]==0 || bot==0) {
					continue;
				}else{
					double coef = top/bot;
					m = addColMatrix(m,i,j,coef);
					m = addRowMatrix(m,j,i,-coef);
				}
			}
		}
		return m;
	}

	public double[][] jordaniser_blocs(double[][] matrix){
		int n = matrix.length;
		double[][] m = matrix;
		for (int k = 1; k < n; k++) {
			m = traiter_colonne(m,k);
		}
		return m;
	}

	private double[][] traiter_colonne(double[][] matrix, int k) {
		double[][] m = matrix;
		m = annuler_coefs_colonne(m,k);
		m = normaliser_coefs_colonne(m,k);
		m = confrontation_coefs(m,k);
		m = deplace_colonnes(m,k);
		return m;
	}

	private double[][] deplace_colonnes(double[][] matrix, int k) {
		double[][] m = matrix;
		int l=0;
		for (int i = 0; i < k; i++) {
			if (m[i][k]!=0) {
				l = i;
				break;
			}
		}
		if (l>0) {
			for (int j = k; j >= l+2; j--) {
				m = swapColMatrix(m, j, j-1);
				m = swapRowMatrix(m, j, j-1);
			}
		}
		return m;
	}

	private double[][] confrontation_coefs(double[][] matrix, int k) {
		int n = matrix.length;
		double[][] m = matrix;
		int debut_bloc1 = 0;
		int fin_bloc1 = 0;
		int debut_bloc2 = 0;
		int fin_bloc2 = 0;
		for (int i = 0; i < k; i++) {
			if (m[i][k]!=0) {
				fin_bloc1 = i;
				break;
			}
		}
		if (fin_bloc1>0) {
			debut_bloc1 = limite_bloc_jordan(m, fin_bloc1);
			for (int i = fin_bloc1+1; i < k; i++) {
				if (m[i][k]!=0) {
					fin_bloc2 = i;
					debut_bloc2 = limite_bloc_jordan(m, fin_bloc2);
					if (fin_bloc2-debut_bloc2>=fin_bloc1-debut_bloc1) {
						for (int ii = fin_bloc1; ii >= debut_bloc1; ii--) {
							m = addColMatrix(m, ii, fin_bloc2+ii-fin_bloc1, 1);
							m = addRowMatrix(m, fin_bloc2+ii-fin_bloc1, ii, -1);
						}
						debut_bloc1 = debut_bloc2;
						fin_bloc1 = fin_bloc2;
					}else{
						for (int ii = fin_bloc2; ii >= debut_bloc2; ii--) {
							m = addColMatrix(m, ii, fin_bloc1+ii-fin_bloc2, 1);
							m = addRowMatrix(m, fin_bloc1+ii-fin_bloc2, ii,-1);
						}
					}
				}
			}
		}
		return m;
	}

	private double[][] normaliser_coefs_colonne(double[][] matrix, int k) {
		int n = matrix.length;
		double[][] m = matrix;
		for (int fin_bloc = 0; fin_bloc < k; fin_bloc++) {
			double coef = m[fin_bloc][k];
			if (coef !=0 && coef!=1) {
				int debut_bloc = limite_bloc_jordan(m,fin_bloc);
				for (int i = fin_bloc; i > debut_bloc; i--) {
					m = mulCol(m,i,coef);
					m = mulRow(m,i,1/coef);
				}
			}
		}
		return m;
	}

	private int limite_bloc_jordan(double[][] m, int l) {
		int debut_bloc = l;
        for (int i = l-1; i > 1; i--) {
			if (m[i][i+1]!=0) {
				debut_bloc = i;
			}else{
				break;
			}
		}
		return debut_bloc;
	}

	private double[][] mulCol(double[][] m, int i, double coef) {
		int n = m.length;
        for(int j = 0; j < n; j++) {
            m[j][i] *=coef;
        }
		return m;
	}

	private double[][] mulRow(double[][] m, int i, double coef) {
		int n = m.length;
        for(int j = 0; j < n; j++) {
            m[i][j] *=coef;
        }
		return m;
	}

	private double[][] annuler_coefs_colonne(double[][] matrix, int k) {
		double[][] m = matrix;
		for (int i = 0; i < k-1; i++) {
			if (m[i][k]!=0 && m[i][i+1]!=0) {
				double coef = (-m[i][k])/m[i][i+1];
				m = addColMatrix(m, i+1, k, coef);
				m = addRowMatrix(m, k, i+1, -coef);
			}
		}
		return m;
	}

	public double[][] formNormalJordan(double[][] matrix, double[] vecteur){
		System.out.println("--- Matrice double");
		double[][] T = trigonalisation(matrix, vecteur);
		double[][] T2 = trigonalisation(T, vecteur);
		System.out.println("--- Matrice T");
		afficheMatriceDouble(T2);
		double[][] U = independentBloc(T2);
		System.out.println("--- Matrice U");
		afficheMatriceDouble(U);
		double[][] J = jordaniser_blocs(U);
		System.out.println("--- Matrice J");
		afficheMatriceDouble(J);
		
		return J;
	}
	public void afficheMatriceDouble(double[][] m){
		for (int i = 0; i < m.length; i++) {
            System.out.println(Arrays.toString(m[i]));
        }
	}
}
