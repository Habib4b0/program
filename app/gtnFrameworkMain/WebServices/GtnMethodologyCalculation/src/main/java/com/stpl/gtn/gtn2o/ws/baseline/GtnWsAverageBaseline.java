package com.stpl.gtn.gtn2o.ws.baseline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsGeneralCalculationService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsPeriodCalculationService;

public class GtnWsAverageBaseline implements GtnWsBaseline {

	@Override
	public void calculateBaseline(GtnWsMethodologyBean methodologyBean) throws Exception {

		GtnWsPeriodCalculationService periodCalculationService = GtnWsPeriodCalculationService.getInstance();

		int columnLength = methodologyBean.getActualFileHeaders().length;

		GtnWsGeneralCalculationService generalCalculationService = GtnWsGeneralCalculationService.getInstance();
		Map<String, Number[]> formulaComponentMap = generalCalculationService
				.getFormulaComponentIndex(methodologyBean.getBaselineFormula(), methodologyBean.getActualFileHeaders());

		Map<Integer,Double> baselineMap = new HashMap<>();
		
		for (int indexValue : methodologyBean.getDataIndex()) {
			List<Object> list = methodologyBean.getActualFileList().get(indexValue);
			double baseLineValue = 0;
			for (String period : methodologyBean.getBaselinePeriodList()) {

				for (Integer[] periodArray : periodCalculationService.getMonthFromPeriod(methodologyBean.getFrequency(),
						period)) {

					for (String key : formulaComponentMap.keySet()) {
						if (formulaComponentMap.get(key) == null) {
							formulaComponentMap.get(key)[1] = 0.0;
						}
						int index = generalCalculationService.getIndex(periodArray[0],
								(periodArray[1] - methodologyBean.getActualStartYear()), columnLength,
								(Integer) formulaComponentMap.get(key)[0]);
						
						formulaComponentMap.get(key)[1] = (double) formulaComponentMap.get(key)[1]
								+ generalCalculationService.doubleConversion(String.valueOf(list.get(index)));
					}
				}
				baseLineValue += generalCalculationService.calculate(methodologyBean,
						methodologyBean.getBaselineFormula(), formulaComponentMap);
			}

			baselineMap.put(indexValue,
					baseLineValue / methodologyBean.getBaselinePeriodList().size());			
			resetMapValues(formulaComponentMap);
		}
		methodologyBean.setBaselineMap(baselineMap);
	}

	private void resetMapValues(Map<String, Number[]> formulaDetailsMap) {

		for (Map.Entry<String, Number[]> entry : formulaDetailsMap.entrySet()) {
			entry.getValue()[1] = 0.0;
		}

	}

}
