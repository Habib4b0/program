package com.stpl.gtn.gtn2o.ws.constants;

public enum GtnWsFrequencyConstants {

	MONTHLY("Monthly", 1),

	QUARTERLY("Quarterly", 3),

	SEMIANNUAL("Semi-Annually", 6),

	ANNUALLY("Annually", 12);

	private String frequency;
	private int frequencyNumber;

	private GtnWsFrequencyConstants(String frequency, int frequencyNumber) {
		this.frequency = frequency;
		this.frequencyNumber = frequencyNumber;
	}

	public GtnWsFrequencyConstants getFrequency() {
		return this;
	}

	public String getConstant() {
		return this.frequency;
	}

	public int getFrequencyNumber() {
		return frequencyNumber;
	}

}
