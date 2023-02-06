package model_Tropical;

import java.util.Objects;

/**
 * Atome tropicale
 */
public class TropicalAtom {
	private Double value = Double.NEGATIVE_INFINITY;
	public static double precision = 0.000001;
	
	public TropicalAtom() {}
	
	public TropicalAtom(Double value) {
		this.value = value;
	}
	
	public TropicalAtom(Integer value) {
		this.value = value.doubleValue();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * Vérifie si l'atome et négatif infini
	 * @return
	 */
	public boolean isNegativeInfinite() {
		return (value == null)||(value == Double.NEGATIVE_INFINITY);
	}

	/**
	 * Addition tropicale entre deux atomes tropicaux
	 * @param atom second atome tropicale
	 * @return atome tropicale
	 */
	public TropicalAtom tropicalAddition(TropicalAtom atom){
		if (isNegativeInfinite()) {
			return atom;
		}
		if (atom.isNegativeInfinite()) {
			return this;
		}
		if (atom.getValue()>this.value) {
			return atom;
		}
		return this;
	}

	/**
	 * Multiplciation tropicale entre deux atomes
	 * @param atom second atome tropicale
	 * @return atome tropicale
	 */
	public TropicalAtom tropicalMultiplication(TropicalAtom atom) {
		if (isNegativeInfinite()) {
			return new TropicalAtom(atom.getValue());
		}
		if (atom.isNegativeInfinite()) {
			return new TropicalAtom(this.value);
		}
		return new TropicalAtom(this.value + atom.getValue());
	}	

	@Override
	public String toString() {
		if (isNegativeInfinite()) {
			return "-∞";
		}
		return value.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TropicalAtom other = (TropicalAtom) obj;
		if (other.isNegativeInfinite() && isNegativeInfinite()) {
			return true;
		}else if (isNegativeInfinite()) {
			return false;
		}else if (other.isNegativeInfinite()) {
			return false;
		}
		Double otherValue = other.value;
		return value >= otherValue - precision && value <= otherValue + precision;
		
	}

	/**
	 * Renvoie true si la valeur d'un atome est compris strictement entre la valeur d'un atome minimal et la valeur d'un atome maximal
	 * @param boundBot atome minimal
	 * @param boundTop atome maximal
	 * @return true si l'atome est compris entre les deux autres et false sinon (ou si les bornes sont inversées)
	 */
    public boolean between(TropicalAtom boundBot, TropicalAtom boundTop) {
		if (isNegativeInfinite() && boundTop.isNegativeInfinite() && boundBot.isNegativeInfinite()) {
			return true;
		}
		if (isNegativeInfinite() || boundTop.isNegativeInfinite()) {
			return false;
		}
		if (!boundBot.isNegativeInfinite() && boundBot.value > boundTop.value) {
			return false;
		}
		if (boundBot.isNegativeInfinite()) {
			return value <= boundTop.value;
		}else{
			return boundBot.value <= value && value <= boundTop.value;
		}
    }

   
}
