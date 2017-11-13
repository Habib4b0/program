package com.stpl.gtn.gtn2o.ws.baseline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsMethodologyBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsGeneralCalculationService;

public class GtnWsQuartileBaseline implements GtnWsBaseline {

	@Override
	public void calculateBaseline(GtnWsMethodologyBean methodologyBean) throws Exception {

		List<List<Object>> actualFileList = methodologyBean.getActualFileList();
		int columnLength = methodologyBean.getActualFileHeaders().length;
		List<Integer> dataIndex = methodologyBean.getDataIndex();

		Map<Integer, Double> baselineMap = new HashMap<>();
		GtnWsGeneralCalculationService gtnWsGeneralCalculationService = GtnWsGeneralCalculationService.getInstance();

		int index = gtnWsGeneralCalculationService.getColumnIndex(methodologyBean.getBaselineFormula(),
				methodologyBean.getActualFileHeaders()) + 1;

		double baseline = 0d;
		for (Integer indexOfListInFile : dataIndex) {
			List<Object> list = actualFileList.get(indexOfListInFile);
			for (int i = list.size() - columnLength - 1; i >= 0; i -= columnLength) {
				if (list.get(i + index) != null) {
					Double currentExpectedReturn = Double.valueOf(String.valueOf(list.get(i + index)));
					if (currentExpectedReturn > 0d) {
						baseline = currentExpectedReturn;
						break;
					}
				}
			}
			baselineMap.put(indexOfListInFile, baseline);
		}
		methodologyBean.setBaselineMap(baselineMap);
	}

}
