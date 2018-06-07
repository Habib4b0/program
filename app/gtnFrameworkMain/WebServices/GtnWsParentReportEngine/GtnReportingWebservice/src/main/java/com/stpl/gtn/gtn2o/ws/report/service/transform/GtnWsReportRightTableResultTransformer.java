package com.stpl.gtn.gtn2o.ws.report.service.transform;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
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
		Pair<List<String>, List<Double>> dataAliasPair;
		List<String> aliasString = new ArrayList<>((aliases.length - 4));
		List<Double> dataList = new ArrayList<>((aliases.length - 4));

		for (int k = 4; k < aliases.length; k++) {
			aliasString.add(rowData.getPeriod() + "" + rowData.getYear() + aliases[k] + rowData.getProjectionName());
			dataList.add(((BigDecimal) tuple[k]).doubleValue());
		}
		rowData.setDataAliasPair(Pair.of(aliasString, dataList));
		return rowData;
	}

	@Override
	public List transformList(List collection) {

		Map<String, Pair<List<String>, List<Double>>> hierarchyDataMap = new HashMap<>();
		for (Object data : collection) {
			GtnWsReportRightTableData rowData = (GtnWsReportRightTableData) data;
			if (hierarchyDataMap.get(rowData.getHierarchyNo()) == null) {

				hierarchyDataMap.put(rowData.getHierarchyNo(), Pair.of(new ArrayList<>(), new ArrayList<>()));
			}
			hierarchyDataMap.get(rowData.getHierarchyNo()).getLeft().addAll(rowData.getDataAliasPair().getLeft());
			hierarchyDataMap.get(rowData.getHierarchyNo()).getRight().addAll(rowData.getDataAliasPair().getRight());
		}
		return Arrays.asList(hierarchyDataMap);
	}

}
