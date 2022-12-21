package model_Tropical;

import java.util.Objects;

public class TropicalAtom {
	private Integer value = null;
	
	public TropicalAtom() {}
	
	public TropicalAtom(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean isNegativeInfinite() {
		return value == null;
	}

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
		//TODO voir si equals ne cause pas de problèmes
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TropicalAtom other = (TropicalAtom) obj;
		return Objects.equals(value, other.value);
	}
	
	

	/*
	public TropicalAtom substraction(TropicalAtom tropicalAtom) {
		if (isNegativeInfinite() || tropicalAtom.isNegativeInfinite()) {
			return new TropicalAtom();
		}
		return new TropicalAtom(value - tropicalAtom.getValue());
	}
	*/
	
}
