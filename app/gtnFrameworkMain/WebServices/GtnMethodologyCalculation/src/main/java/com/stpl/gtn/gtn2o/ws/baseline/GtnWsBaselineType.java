package com.stpl.gtn.gtn2o.ws.baseline;

public enum GtnWsBaselineType {

	SINGLE_PERIOD_BASELINE(new GtnWsSinglePeriodBaseline()), 
	AVERAGE_BASELINE(new GtnWsAverageBaseline()),
	ROLLING_ANNUAL_TREND_BASELINE(new GtnWsRollingAnnualTrendBaseline()),
	PERCENTAGE_OF_EXFACTORY(new GtnWsPercentageOfExfactoryBaseline()),
	QUARTILE_BASELINE(new GtnWsQuartileBaseline()),
	TIER_ONE_BASELINE(new GtnWsTierOneBaseline()),
	TIER_TWO_BASELINE(new GtnWsTierTwoBaseline());

	private final GtnWsBaseline baseline;

	private GtnWsBaselineType(GtnWsBaseline baseline) {
		this.baseline = baseline;
	}

	public GtnWsBaseline getBaseline() {
		return this.baseline;
	}

}