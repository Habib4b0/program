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

	private volatile boolean isAnnual = false;

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		GtnWsReportRightTableData rowData = new GtnWsReportRightTableData();
		rowData.setHierarchyNo((String) tuple[0]);
		Short year = (Short) tuple[3];
		Short periodData = (Short) tuple[2];
		rowData.setYear(year.intValue());
		rowData.setPeriod(periodData.intValue());

		Map<String, Double> dataMap = new HashMap<>((aliases.length - 4));
		for (int k = 4; k < aliases.length; k++) {
			Double doubleData = tuple[k] == null ? 0D : ((BigDecimal) tuple[k]).doubleValue();
			String key = isAnnual ? rowData.getYear() + aliases[k]
					: Integer.toString(rowData.getPeriod()) + rowData.getYear() + aliases[k];
			dataMap.put(key, doubleData);
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

	public synchronized void setAnnual(boolean isAnnual) {
		this.isAnnual = isAnnual;
	}

}
