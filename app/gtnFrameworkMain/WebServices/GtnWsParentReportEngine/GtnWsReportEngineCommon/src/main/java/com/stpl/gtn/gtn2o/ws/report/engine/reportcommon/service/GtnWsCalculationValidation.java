package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnWsCalculationValidation {

    private static volatile GtnWsCalculationValidation gtnWsCalculationValidation;
    private static final Map<String, List<String[]>> COMPARISON_BASIS = new HashMap<>();
    private static final String DECIMAL_DEFAULT = "0.0";

    private GtnWsCalculationValidation() {
        super();
    }

    public static synchronized GtnWsCalculationValidation getInstance() {
        if (gtnWsCalculationValidation == null) {
            gtnWsCalculationValidation = new GtnWsCalculationValidation();
            loadComparisonBasisMap();
        }
        return gtnWsCalculationValidation;
    }

    public boolean doubleValidation(Double value) {
        return !(value == null || Double.isInfinite(value) || Double.isNaN(value));
    }

    public Double getDoubleValue(Double value) {
        if (value == null || Double.isInfinite(value) || Double.isNaN(value)) {
            return 0.0;
        }
        return value;
    }

    public String getDoubleValue(Object value) {
        if (value == null) {
            return DECIMAL_DEFAULT;
        }
        String doubleValue = String.valueOf(value);
        try {
            if (Double.isInfinite(Double.valueOf(doubleValue))) {
                return DECIMAL_DEFAULT;
            }
            return doubleValue;
        } catch (NumberFormatException ex) {
            return DECIMAL_DEFAULT;
        }
    }

    private static void loadComparisonBasisMap() {
        if (COMPARISON_BASIS.isEmpty()) {
            List<String[]> actuals = new ArrayList<>();
            actuals.add(new String[]{"Actuals", "Projection"});
            COMPARISON_BASIS.put("Actuals", actuals);
            List<String[]> accruals = new ArrayList<>();
            accruals.add(new String[]{"Accruals", "Projection"});
            COMPARISON_BASIS.put("Accruals", accruals);
            List<String[]> projection = new ArrayList<>();
            projection.add(new String[]{"Projection", "Actuals"});
            projection.add(new String[]{"Projection", "Accruals"});
            projection.add(new String[]{"Projection", "Projection"});
            COMPARISON_BASIS.put("Projection", projection);
            List<String[]> value = new ArrayList<>();
            value.add(new String[]{"Actuals", "Accruals", "Projection"});
            COMPARISON_BASIS.put("Value", value);
        }
    }

    public List<String[]> getComparisonBasis(String key) {
        return this.COMPARISON_BASIS.get(key);
    }

}
