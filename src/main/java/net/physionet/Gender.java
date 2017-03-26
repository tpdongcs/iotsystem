package net.physionet;

public enum Gender {
	Male(0),
	Female(-1);
	private int value;
	private Gender(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
