package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.constants.CalculationConstants;

@Service
@Scope(value = "singleton")
public class GtnWsCalculationValidationService {

	private static final Map<String, List<String[]>> COMPARISON_BASIS = new HashMap<>();

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
			return CalculationConstants.DECIMAL_DEFAULT;
		}
		String doubleValue = String.valueOf(value);
		try {
			if (Double.isInfinite(Double.valueOf(doubleValue))) {
				return CalculationConstants.DECIMAL_DEFAULT;
			}
			return doubleValue;
		} catch (NumberFormatException ex) {
			return CalculationConstants.DECIMAL_DEFAULT;
		}
	}

	private static void loadComparisonBasisMap() {
		if (COMPARISON_BASIS.isEmpty()) {
			List<String[]> actuals = new ArrayList<>();
			actuals.add(new String[] { CalculationConstants.ACTUALS, CalculationConstants.PROJECTIONS });
			COMPARISON_BASIS.put(CalculationConstants.ACTUALS, actuals);
			List<String[]> accruals = new ArrayList<>();
			accruals.add(new String[] { CalculationConstants.ACCRUALS, "Projection" });
			COMPARISON_BASIS.put(CalculationConstants.ACCRUALS, accruals);
			List<String[]> projection = new ArrayList<>();
			projection.add(new String[] { CalculationConstants.PROJECTIONS, CalculationConstants.ACTUALS });
			projection.add(new String[] { CalculationConstants.PROJECTIONS, CalculationConstants.ACCRUALS });
			projection.add(new String[] { CalculationConstants.PROJECTIONS, CalculationConstants.PROJECTIONS });
			COMPARISON_BASIS.put(CalculationConstants.PROJECTIONS, projection);
			List<String[]> value = new ArrayList<>();
			value.add(new String[] { CalculationConstants.ACTUALS, CalculationConstants.ACCRUALS,
					CalculationConstants.PROJECTIONS });
			COMPARISON_BASIS.put(CalculationConstants.VALUE, value);
		}
	}

	public List<String[]> getComparisonBasis(String key) {
		return this.COMPARISON_BASIS.get(key);
	}

}
