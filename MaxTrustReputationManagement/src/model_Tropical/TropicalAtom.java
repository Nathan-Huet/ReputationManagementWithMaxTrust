package model_Tropical;

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

	public TropicalAtom TropicalAddition(TropicalAtom atom){
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

	public TropicalAtom TropicalMultiplication(TropicalAtom atom) {
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
	
	
}
