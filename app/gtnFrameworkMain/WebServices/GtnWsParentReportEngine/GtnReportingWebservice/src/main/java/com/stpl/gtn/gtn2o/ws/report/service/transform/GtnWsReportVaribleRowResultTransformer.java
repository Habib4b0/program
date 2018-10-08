package com.stpl.gtn.gtn2o.ws.report.service.transform;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service()
@Scope(value = "singleton")
public class GtnWsReportVaribleRowResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 5906633080624933927L;

	private boolean isAnnual = false;

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, GtnWsReportRightTableData> variableBasedData = new HashMap<>(16);

		String hierarchyNo = (String) tuple[0];
		for (int k = 4; k < aliases.length; k++) {

			String[] variableVariableCategory = aliases[k].split("#");
			String variableName;
			String variableCategory;
			if (variableVariableCategory.length == 2) {
				variableName = variableVariableCategory[0];
				variableCategory = variableVariableCategory[1];
			} else {
				variableName = "";
				variableCategory = variableVariableCategory[0];
			}

			GtnWsReportRightTableData rowData = variableBasedData.get(hierarchyNo + variableName);
			if (rowData == null) {
				rowData = new GtnWsReportRightTableData();
				rowData.setHierarchyNo(hierarchyNo);
				Short year = (Short) tuple[3];
				Short periodData = (Short) tuple[2];
				rowData.setYear(year.intValue());
				rowData.setPeriod(periodData.intValue());
				Map<String, Double> rowDataMap = new HashMap<>();
				rowData.setDataMap(rowDataMap);
				variableBasedData.put(hierarchyNo + variableName, rowData);
			}
			Double doubleData = tuple[k] == null ? 0D : ((BigDecimal) tuple[k]).doubleValue();
			String key = isAnnual ? rowData.getYear() + variableCategory
					: Integer.toString(rowData.getPeriod()) + rowData.getYear() + variableCategory;
			rowData.getDataMap().put(key, doubleData);
		}

		return variableBasedData;
	}

	@Override
	public List transformList(List collection) {
		Map<String, Map<String, Double>> hierarchyDataMap = new HashMap<>();
		for (Object data : collection) {
			Map<String, GtnWsReportRightTableData> rowData = (Map<String, GtnWsReportRightTableData>) data;
			for (Map.Entry<String, GtnWsReportRightTableData> variableBasedData : rowData.entrySet()) {
				if (hierarchyDataMap.get(variableBasedData.getKey()) == null) {
					hierarchyDataMap.put(variableBasedData.getKey(), new HashMap<>());
				}
				hierarchyDataMap.get(variableBasedData.getKey()).putAll(variableBasedData.getValue().getDataMap());
			}

		}
		return Arrays.asList(hierarchyDataMap);
	}

	public void setAnnual(boolean isAnnual) {
		this.isAnnual = isAnnual;
	}

}
