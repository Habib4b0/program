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
public class GtnWsReportRightTableResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 2020047174163494804L;

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		GtnWsReportRightTableData rowData = new GtnWsReportRightTableData();
		rowData.setHierarchyNo((String) tuple[0]);
		rowData.setProjectionName((String) tuple[1]);
		rowData.setYear((int) tuple[2]);
		rowData.setPeriod((int) tuple[3]);

		Map<String, Double> dataMap = new HashMap<>((aliases.length - 4));
		for (int k = 4; k < aliases.length; k++) {
			dataMap.put(rowData.getPeriod() + "" + rowData.getYear() + aliases[k] + rowData.getProjectionName(),
					((BigDecimal) tuple[k]).doubleValue());
		}
		rowData.setDataMap(dataMap);
		return rowData;
	}

	@Override
	public List transformList(List collection) {

		Map<String, Map<String, Double>> hierarchyDataMap = new HashMap<>();
		for (Object data : collection) {
			GtnWsReportRightTableData rowData = (GtnWsReportRightTableData) data;
			if (hierarchyDataMap.get(rowData.getHierarchyNo()) == null) {
				hierarchyDataMap.put(rowData.getHierarchyNo(), new HashMap<>());
			}
			hierarchyDataMap.get(rowData.getHierarchyNo()).putAll(rowData.getDataMap());
		}
		return Arrays.asList(hierarchyDataMap);
	}

}
