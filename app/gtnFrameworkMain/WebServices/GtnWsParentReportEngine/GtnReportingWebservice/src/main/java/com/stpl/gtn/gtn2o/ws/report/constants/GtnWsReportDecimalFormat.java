package com.stpl.gtn.gtn2o.ws.report.constants;

import java.text.DecimalFormat;

public enum GtnWsReportDecimalFormat {

	DOLLAR(new DecimalFormat("$###0.00")), PERCENT(new DecimalFormat("###0.00")), UNITS(new DecimalFormat("###0.00"));

	private final DecimalFormat format;

	private GtnWsReportDecimalFormat(DecimalFormat format) {
		this.format = format;
	}

	public String getFormattedValue(Double value) {
		return format.format(value);
	}

}
