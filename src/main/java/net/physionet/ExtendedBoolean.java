package net.physionet;

public enum ExtendedBoolean {
	Unknow(0),
	Yes(1),
	No(-1);
	private int value;
	private ExtendedBoolean(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
