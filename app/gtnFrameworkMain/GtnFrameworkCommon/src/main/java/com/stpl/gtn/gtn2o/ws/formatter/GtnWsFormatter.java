package com.stpl.gtn.gtn2o.ws.formatter;

import java.text.DecimalFormat;

public enum GtnWsFormatter {

	DECIMAL_FORMATTER(new DecimalFormat());

	private DecimalFormat format;

	private GtnWsFormatter(DecimalFormat format) {
		this.format = format;
	}

	public DecimalFormat getFormatter() {
		return format;
	}
}
