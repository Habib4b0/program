package com.stpl.gtn.gtn2o.ws.report.service.transform;

import java.math.BigDecimal;
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

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, GtnWsReportRightTableData> variableBasedData = new HashMap<>(16);

		for (int k = 4; k < aliases.length; k++) {

			String[] variableVariableCategory = aliases[k].split("#");
			GtnWsReportRightTableData rowData = variableBasedData.get(variableVariableCategory[0]);
			if (rowData == null) {
				rowData = new GtnWsReportRightTableData();
				rowData.setHierarchyNo((String) tuple[0]);
				rowData.setProjectionName((String) tuple[1]);
				rowData.setYear((int) tuple[2]);
				rowData.setPeriod((int) tuple[3]);
				Map<String, Double> rowDataMap = new HashMap<>();
//				rowData.setDataMap(rowDataMap);
				variableBasedData.put(variableVariableCategory[0], rowData);
			}
//			rowData.getDataMap().put(rowData.getPeriod() + "" + rowData.getYear() + variableVariableCategory[1]
//					+ rowData.getProjectionName(), ((BigDecimal) tuple[k]).doubleValue());
		}

		return variableBasedData;
	}

	@Override
	public List transformList(List collection) {

		return collection;
	}

}
