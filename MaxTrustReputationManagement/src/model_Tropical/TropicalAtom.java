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

	@Override
	public String toString() {
		if (isNegativeInfinite()) {
			return "-∞";
		}
		return value.toString();
	}
	
	
}
