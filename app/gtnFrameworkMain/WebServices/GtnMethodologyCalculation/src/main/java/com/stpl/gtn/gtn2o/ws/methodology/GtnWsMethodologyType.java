package com.stpl.gtn.gtn2o.ws.methodology;

public enum GtnWsMethodologyType {

	SINGLE_PERIOD(new GtnWsSinglePeriodMethodology()),

	AVERAGE(new GtnWsAverageMethodology()),

	ROLLING_ANNUAL_TREND(new GtnWsRollingAnnualTrendMethodology()),

	PERCENTAGE_OF_EXFACTORY(new GtnWsPercentageOfExfactoryMethodology()),

	QUARTILE(new GtnWsQuartileMethodology()),

	TIER_ONE(new GtnWsTierOneMethodology()),

	TIER_TWO(new GtnWsTierTwoMethodology());

	private final GtnWsMethodology methodology;

	private GtnWsMethodologyType(GtnWsMethodology methodology) {
		this.methodology = methodology;
	}

	public GtnWsMethodology getMethdology() {
		return this.methodology;
	}

}
