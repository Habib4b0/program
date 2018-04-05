package com.stpl.gtn.gtn2o.ws.report.engine.bean;

import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsCalculationInterface;
import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsPerChangeCalculationServiceImpl;
import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsValueCalculationServiceImpl;
import com.stpl.gtn.gtn2o.ws.report.engine.calculation.GtnWsVarianceCalculationServiceImpl;

public enum GtnWsCalculationType {

	VALUE(new GtnWsValueCalculationServiceImpl()), VARIANCE(new GtnWsVarianceCalculationServiceImpl()), PER_CHANGE(
			new GtnWsPerChangeCalculationServiceImpl());
	private final GtnWsCalculationInterface gtnCalculation;

	private GtnWsCalculationType(GtnWsCalculationInterface gtnCalculation) {
		this.gtnCalculation = gtnCalculation;
	}

	public GtnWsCalculationInterface getCalculation() {
		return this.gtnCalculation;
	}

}
