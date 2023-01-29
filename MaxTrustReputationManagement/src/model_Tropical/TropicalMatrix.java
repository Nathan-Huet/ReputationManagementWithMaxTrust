package model_Tropical;

import java.util.ArrayList;

import main.Application;
import world.World;


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
	 * retourne le résultat d'une multiplication tropical de matrice par un vecteur
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
	 * retourne le résultat d'une multiplication de matrice par un vecteur
	 * @param matrix matrice
	 * @param vector vecteur
	 * @return résultat d'une multiplication de matrice par un vecteur
	 */
	public TropicalAtom[] matrixMultiplicationByVector(TropicalAtom[][] matrix, TropicalAtom[] vector) {
		TropicalAtom[] result = new TropicalAtom[vector.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = new TropicalAtom();
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				result[i] = result[i].addition(matrix[i][j].multiplication(vector[i]));
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

	/**
	 * Multiplication tropicale vectorielle de deux vecteurs
	 * @param vectorLeft vecteur à gauche de l'addition tropicale
	 * @param vectorRight vecteur à droite de l'addition tropicale
	 * @return somme vectorielle tropicale
	 */
	public TropicalAtom[] tropicalVectorMultiplication(TropicalAtom[] vectorLeft, TropicalAtom[] vectorRight) {
		TropicalAtom[] result = new TropicalAtom[vectorLeft.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = vectorLeft[i].tropicalMultiplication(vectorRight[i]);
		}
		
		return result;
	}
	
	public Pair maxPower(TropicalAtom[] r) {
		int p = 0;
		TropicalAtom convergenceAtomPlus = new TropicalAtom(convergence);
		TropicalAtom convergenceAtomMinus = new TropicalAtom(-convergence);
		int q = -1;
		ArrayList<TropicalAtom[]> vArray = new ArrayList<>();
		vArray.add(r);

		boolean conditionContinue = false;
		do {
			vArray.add(tropicalMatrixMultiplicationByVector(getTranspose(), vArray.get(p)));
			p++;
			TropicalAtom[] vp = vArray.get(p);
			for (q = 0; q <= p; q++) {
				conditionContinue = false;
				TropicalAtom[] vq = vArray.get(q);
				/*
				for (TropicalAtom tropicalAtom : vq) {
					System.out.print(tropicalAtom.getValue()+", ");
				}
				System.out.println("");
				*/
				//printTrustMatrix(trustMatrix);
				for(int i = 0; i<vq.length; i++){
					
					if(!vq[i].between(vp[i].tropicalMultiplication(convergenceAtomMinus), vp[i].tropicalMultiplication(convergenceAtomPlus))){
						conditionContinue = true;
					}
				}
				if (!conditionContinue) {
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
			TropicalAtom[] vqPlusIMinus1 = vArray.get(q+i-1);
			double lambdaPower = 1;
			for (int j = 0; j < p-q-i; j++) {
				lambdaPower *= lambda;
			}
			TropicalAtom[] vectorMultiplied = tropicalVectorMultiplicationByValue(vqPlusIMinus1, lambdaPower);
			v = tropicalVectorAddition(v,vectorMultiplied);
		}
		
		return new Pair(lambda,v);
	}


	public TropicalAtom[] maxTrust(TropicalAtom[] w, int T){
        int n = trustMatrix.length;
        Pair[] lambda = new Pair[n];
        double[] epsi = new double[n];
        tropicalFormNormalJordan();
        lambda[n-1] = maxPower(w);
        epsi[n-1] = lambda[n-1].getDominantEigenValue();
        TropicalAtom[] v = lambda[n-1].getDominantEigenVector();
        int j = n-2;
		
        while(j>0){
            lambda[j] = maxPower(w);
            TropicalAtom lambdaValue = lambda[j].getDominantEigenVector()[0];
            for(int i = 1; i<j; i++){
                lambdaValue = lambdaValue.tropicalMultiplication(lambda[j].getDominantEigenVector()[i]);
            }
            v[j-1] = trustMatrix[j][1].tropicalMultiplication(w[1].tropicalMultiplication(lambdaValue));
            
            lambdaValue = lambda[j].getDominantEigenVector()[0];
            for(int i = 0; i<j; i++){
                lambdaValue = lambdaValue.tropicalMultiplication(lambda[j].getDominantEigenVector()[i]);
            }
            for (int k = 0; k < n; k++) {
                v[j-1] = v[j-1].tropicalAddition(trustMatrix[j][k].tropicalMultiplication(w[k].tropicalMultiplication(lambdaValue))); 
            }
            if (lambda[j].getDominantEigenValue()>epsi[j+1]) {
                epsi[j] = lambda[j].getDominantEigenValue();
            }else{
                epsi[j] = lambda[j+1].getDominantEigenValue();
                v[j-1] = new TropicalAtom(1/epsi[j]).tropicalMultiplication(v[j-1]);
            }
            j--;
        }
        TropicalAtom epsiValue = new TropicalAtom(epsi[0]);
        for (int i = 0; i < T; i++) {
            epsiValue.tropicalMultiplication(new TropicalAtom(epsi[0]));
        }
        TropicalAtom[] fin = tropicalVectorMultiplicationByValue(v,epsiValue.getValue());
        return fin;
    }

	//-------------------------------------------
	//-------------------------------------------
	// trigonalisation sur des matrices tropical
	//-------------------------------------------
	//-------------------------------------------

	public void tropicalTrigonalisation() {
		TropicalAtom[] v = getDiagonal();
		int n = numberOfAgent;
		for (int k = 0; k < v.length; k++) {
			TropicalAtom lambda = v[k];
			tropicalMatrixSoustraction(tropicalIdentityMatrix(n).tropicalMatrixMultiplicationByValue(lambda).getTrustMatrix());
			
			for (int j = n-1; j > k; j--) {
				int i = k;
				while(i<n && (trustMatrix[i][j].equals(new TropicalAtom(0)))){
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
						Double top = trustMatrix[i][jj].getValue();
						Double bot = trustMatrix[i][j].getValue();
						
						if (top == null || bot == null) {
							continue;
						}
						double coef = (-top)/bot;
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
	public TropicalAtom[] getDiagonal(){
		TropicalAtom[] dia = new TropicalAtom[trustMatrix.length];
		for (int i = 0; i < trustMatrix.length; i++) {
			dia[i] = trustMatrix[i][i];
		}
		return dia;
	}
	public void addColTropicalMatrix(int i, int ii, double coef){
		for (int k = 0; k < numberOfAgent; k++) { 
			TropicalAtom val = trustMatrix[k][i].tropicalMultiplication(new TropicalAtom(coef));
			trustMatrix[k][ii].tropicalAddition(val);
		}
	}

	public void addRowTropicalMatrix(int i, int ii, double coef){
		for (int k = 0; k < numberOfAgent; k++) {
			TropicalAtom val = trustMatrix[i][k].tropicalMultiplication(new TropicalAtom(coef));
			trustMatrix[ii][k].tropicalAddition(val);
		}
	}
	
	public void tropicalMatrixAddition(TropicalAtom[][] m){
		int n = m.length;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trustMatrix[i][j].tropicalAddition(m[i][j]);
            }
        }
	}
	public void tropicalMatrixSoustraction(TropicalAtom[][] m){
		int n = m.length;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trustMatrix[i][j].tropicalAddition(new TropicalAtom(-m[i][j].getValue()));
            }
        }
	}
	public TropicalMatrix tropicalMatrixMultiplicationByValue(TropicalAtom value) {
		int n = trustMatrix.length;
		for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
				trustMatrix[i][j].tropicalMultiplication(value);
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
					m[i][j] = new TropicalAtom(Double.NEGATIVE_INFINITY);
				}
			}
		}
		return new TropicalMatrix(n, 1, m);
	}
	//-------------------------------------------
	//-------------------------------------------
	// bloc independant sur des matrices tropical
	//-------------------------------------------
	//-------------------------------------------

	public void tropicalIndependentBloc(){
		int n = numberOfAgent;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n-k; i++) {
				int j = i + k;
				Double top = -trustMatrix[i][j].getValue();
				Double bot = trustMatrix[i][i].getValue()-trustMatrix[j][j].getValue();
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

	//-------------------------------------------
	//-------------------------------------------
	// jordanisation sur des matrices tropical
	//-------------------------------------------
	//-------------------------------------------

	public void tropicalJordanBlocs(){
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
		int l = -1;
		for (int i = 0; i < k; i++) {
			if (trustMatrix[i][k].getValue() != 0) {
				l = i;
				break;
			}
		}
		if (l > -1) {
			for (int j = k; j > l+1; j--) {
				//System.out.println("deplacement");
				swapColumn(j, j-1);
				swapRow(j, j-1);
				//System.out.println("["+j+","+(j-1)+"]");
			}
		}
	}

	private void tropicalConfrontation_coefs(int k) {
		int debut_bloc1 = -1;
		int fin_bloc1 = -1;
		int debut_bloc2 = -1;
		int fin_bloc2 = -1;
		for (int i = 0; i < k-1; i++) {
			if (trustMatrix[i][k].getValue() != 0) {
				fin_bloc1 = i;
				break;
			}
		}
		if (fin_bloc1 > -1) {
			debut_bloc1 = tropicalLimite_bloc_jordan(fin_bloc1);
			for (int i = fin_bloc1+1; i < k; i++) {
				if (trustMatrix[i][k].getValue()!=0) {
					fin_bloc2 = i;
					debut_bloc2 = tropicalLimite_bloc_jordan(fin_bloc2);
					//System.out.println("Confrontation");
					if (fin_bloc2-debut_bloc2>=fin_bloc1-debut_bloc1) {
						for (int ii = fin_bloc1; ii >= ((debut_bloc1-1)<0?debut_bloc1:debut_bloc1-1); ii--) {
							addColTropicalMatrix(ii, fin_bloc2+ii-fin_bloc1, 1);
							addRowTropicalMatrix(fin_bloc2+ii-fin_bloc1, ii, -1);
							//System.out.println("["+(ii)+","+(fin_bloc2+ii-fin_bloc1)+","+1+"]");
						}
						debut_bloc1 = debut_bloc2;
						fin_bloc1 = fin_bloc2;
					}else{
						for (int ii = fin_bloc2; ii >= ((debut_bloc2-1)<0?debut_bloc2:debut_bloc2-1); ii--) {
							addColTropicalMatrix(ii, fin_bloc1+ii-fin_bloc2, 1);
							addRowTropicalMatrix(fin_bloc1+ii-fin_bloc2, ii, -1);
							//System.out.println("["+(ii)+","+(fin_bloc1+ii-fin_bloc2)+","+1+"]");
						}
					}
				}
			}
		}
	}

	private void tropicalNormaliser_coefs_colonne(int k) {
		for (int fin_bloc = 0; fin_bloc < k; fin_bloc++) {
			double coef = trustMatrix[fin_bloc][k].getValue();
			if (coef != 0 && coef != 1) {
				int debut_bloc = tropicalLimite_bloc_jordan(fin_bloc);
				for (int i = fin_bloc; i >= ((debut_bloc-1)<0?debut_bloc:debut_bloc-1); i--) {
					//System.out.println("Normalisation");
					//System.out.println(i);
					tropicalMulCol(i,coef);
					tropicalMulRow(i,1/coef);
					//System.out.println("["+(i)+","+(i)+","+coef+"]");
				}
			}
		}
	}

	private int tropicalLimite_bloc_jordan(int l) {
		int debut_bloc = l;
        for (int i = l-2; i >= 0; i--) {
			if (trustMatrix[i][i+1].getValue() != 0) {
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
			if(trustMatrix[j][i].getValue() != 0.0){
				trustMatrix[j][i].tropicalMultiplication(new TropicalAtom(coef));
			}
        }
	}

	private void tropicalMulRow(int i, double coef) {
		int n = numberOfAgent;
        for(int j = 0; j < n; j++) {
			if(trustMatrix[i][j].getValue() != 0.0){
				trustMatrix[i][j].tropicalMultiplication(new TropicalAtom(coef));
			}
		}
	}

	private void tropicalAnnuler_coefs_colonne(int k) {
		for (int i = 0; i < k-1; i++) {
			if (trustMatrix[i][k].getValue() != 0 && trustMatrix[i][i+1].getValue() != 0) {
				//System.out.println("Annulation");
				double coef = (-trustMatrix[i][k].getValue())/trustMatrix[i][i+1].getValue();
				addColTropicalMatrix(i+1, k, coef);
				addRowTropicalMatrix(k, i+1, -coef);
				//System.out.println("["+(i+1)+","+(k)+","+coef+"]");
			}
		}
	}

	public void tropicalFormNormalJordan(){
		tropicalTrigonalisation();
		tropicalIndependentBloc();
		tropicalJordanBlocs();
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
}
