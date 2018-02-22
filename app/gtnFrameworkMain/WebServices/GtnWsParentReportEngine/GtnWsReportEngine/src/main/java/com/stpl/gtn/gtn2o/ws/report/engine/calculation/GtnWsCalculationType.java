package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

public enum GtnWsCalculationType {

    VALUE(new GtnWsValueCalculation()),
    VARIANCE(new GtnWsVarianceCalculation()),
    PER_CHANGE(new GtnWsPerChangeCalculation());
    private final GtnWsCalculation gtnCalculation;

    private GtnWsCalculationType(GtnWsCalculation gtnCalculation) {
        this.gtnCalculation = gtnCalculation;
    }

    public GtnWsCalculation getCalculation() {
        return this.gtnCalculation;
    }

}
