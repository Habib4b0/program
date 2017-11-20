package com.stpl.gtn.gtn2o.ws.baseline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsGeneralCalculationService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsPeriodCalculationService;

public class GtnWsRollingAnnualTrendBaseline implements GtnWsBaseline {

	/**
	 * Method used to calculate the baseline values. In Rolling Annual Trend
	 * Methodology, number of baseline will be based on the selected frequency
	 * where as in other methodologies we will get a single baseline value after
	 * the baseline calculation. In this methodology, We will always get
	 * baseline for a single complete year,irrespective of the selected period
	 * or frequency.
	 * 
	 * eg: frequency - quarterly, baseline values -
	 * Q1-2014,Q2-2014,Q3-2014,Q4-2014.
	 */
	@Override
	public void calculateBaseline(GtnWsMethodologyBean methodologyBean) throws Exception {

		GtnWsPeriodCalculationService periodCalculationService = GtnWsPeriodCalculationService.getInstance();
		int columnLength = methodologyBean.getActualFileHeaders().length;

		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();
		Map<String, Number[]> formulaComponentMap = generalCalculationService
				.getFormulaComponentIndex(methodologyBean.getBaselineFormula(), methodologyBean.getActualFileHeaders());

		Map<Integer, List<Double>> baselineMap = new HashMap<>();

		for (int indexValue : methodologyBean.getDataIndex()) {
			List<Object> list = methodologyBean.getActualFileList().get(indexValue);
			List<Double> rollingAnnualTrendBaselineList = generateList(12);

			for (String period : methodologyBean.getBaselinePeriodList()) {

				for (Integer[] periodArray : periodCalculationService.getMonthFromPeriod(methodologyBean.getFrequency(),
						period)) {

					for (String key : formulaComponentMap.keySet()) {

						if (formulaComponentMap.get(key)[1].equals(0.0)) {
							formulaComponentMap.get(key)[1] = 0.0;
						}
						int index = generalCalculationService.getIndex(periodArray[0],
								(periodArray[1] - methodologyBean.getActualStartYear()), columnLength,
								(Integer) formulaComponentMap.get(key)[0]);
						formulaComponentMap.get(key)[1] = (double) formulaComponentMap.get(key)[1]
								+ generalCalculationService.doubleConversion(String.valueOf(list.get(index)));
					}
					double baselineValue = generalCalculationService.calculate(methodologyBean,
							methodologyBean.getBaselineFormula(), formulaComponentMap);
					rollingAnnualTrendBaselineList.set(periodArray[0] - 1, baselineValue);
					resetMapValues(formulaComponentMap);
				}

			}
			baselineMap.put(indexValue, rollingAnnualTrendBaselineList);
		}

		methodologyBean.setRollingAnnualTrendBaselineMap(baselineMap);

	}

	/**
	 * Method resets the map after the baseline calculation for a particular
	 * period is completed.
	 * 
	 * @param formulaDetailsMap
	 */
	private void resetMapValues(Map<String, Number[]> formulaDetailsMap) {

		for (Map.Entry<String, Number[]> entry : formulaDetailsMap.entrySet()) {
			entry.getValue()[1] = 0.0;
		}

	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	private List<Double> generateList(int size) {
		List<Double> list = new ArrayList<>(size);
		for (int i = size; i > 0; i--) {
			list.add(0d);
		}
		return list;
	}

}
