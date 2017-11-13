package com.stpl.gtn.gtn2o.ws.baseline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsGeneralCalculationService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsPeriodCalculationService;

public class GtnWsSinglePeriodBaseline implements GtnWsBaseline {

	/**
	 * Method calculates the baseline and stores it in the GtnWsMethodologyBean
	 * object. Stores the baseline value for each line in the file in a map.
	 * GtnWsMethodologyBean - baselineMap - Map<Integer, Double>.
	 */
	@Override
	public void calculateBaseline(GtnWsMethodologyBean methodologyBean) throws Exception {

		List<Integer[]> periodList = getBaselinePeriodList(methodologyBean);
		int columnLength = methodologyBean.getActualFileHeaders().length;
		Map<String, Number[]> formulaComponentMap = getFormulaComponentMap(methodologyBean);

		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();
		Map<Integer, Double> baselineMap = new HashMap<>();

		for (int indexValue : methodologyBean.getDataIndex()) {
			List<Object> list = methodologyBean.getActualFileList().get(indexValue);

			for (Integer[] period : periodList) {

				for (String key : formulaComponentMap.keySet()) {
					if (formulaComponentMap.get(key)[1].equals(0.0)) {
						formulaComponentMap.get(key)[1] = 0.0;
					}
					int index = generalCalculationService.getIndex(period[0],
							(period[1] - methodologyBean.getActualStartYear()), columnLength,
							(Integer) formulaComponentMap.get(key)[0]);
					formulaComponentMap.get(key)[1] = (double) formulaComponentMap.get(key)[1]
							+ generalCalculationService.doubleConversion(String.valueOf(list.get(index)));
				}
			}

			double baseLineValue = generalCalculationService.calculate(methodologyBean,
					methodologyBean.getBaselineFormula(), formulaComponentMap);
			baselineMap.put(indexValue, baseLineValue);
			resetMapValues(formulaComponentMap);
		}
		methodologyBean.setBaselineMap(baselineMap);

	}

	/**
	 * Method returns the list of periods selected as baseline. Returns a List
	 * of Integer array that contains month and year.
	 * 
	 * @param methodologyBean
	 * @return List<Integer[]>
	 *         <p>
	 *         Example:
	 *         <p>
	 *         array[0] - 1, array[1] - 2015
	 * @throws Exception
	 */
	private List<Integer[]> getBaselinePeriodList(GtnWsMethodologyBean methodologyBean) throws Exception {
		GtnWsPeriodCalculationService periodCalculationService = GtnWsPeriodCalculationService.getInstance();
		return periodCalculationService.getMonthFromPeriod(methodologyBean.getFrequency(),
				methodologyBean.getBaselinePeriodList().get(0));
	}

	/**
	 * Method returns the Map of the components used in the formula and their
	 * column index in the file.Value in the map is number array that contains
	 * column index in file and default value for the column.
	 * 
	 * @param methodologyBean
	 * @return Map<String, Number[]>
	 *         <p>
	 *         Example:
	 *         <p>
	 *         Key - RETURNS_PROJECTION_AMOUNT
	 *         <p>
	 *         Value - number[0] = 3, number[1] = 0.0
	 */
	private Map<String, Number[]> getFormulaComponentMap(GtnWsMethodologyBean methodologyBean) {
		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();
		return generalCalculationService.getFormulaComponentIndex(methodologyBean.getBaselineFormula(),
				methodologyBean.getActualFileHeaders());
	}

	/**
	 * Method resets the value of the formula components to the default value.
	 * 
	 * @param formulaDetailsMap
	 */
	private void resetMapValues(Map<String, Number[]> formulaDetailsMap) {

		for (Map.Entry<String, Number[]> entry : formulaDetailsMap.entrySet()) {
			entry.getValue()[1] = 0.0;
		}

	}

}
