/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

/**
 *
 * @author Nimisha.Rakesh
 */
@Service
public class GtnWsReturnsWorkflowIOService {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine;

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public GtnFrameworkSqlQueryEngine getGtnFrameworkSqlQueryEngine() {
		return gtnFrameworkSqlQueryEngine;
	}

	public void setGtnFrameworkSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnFrameworkSqlQueryEngine) {
		this.gtnFrameworkSqlQueryEngine = gtnFrameworkSqlQueryEngine;
	}

	@SuppressWarnings("unchecked")
	public GtnForecastBean getProjectionDetails(GtnForecastBean gtnForecastBean) throws GtnFrameworkGeneralException {

		String projectionDetailsQuery = gtnWsSqlService.getQuery("GET_PROJECTION_DETAILS");
		List<Object[]> projectionResultList = (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				projectionDetailsQuery, new Object[] { gtnForecastBean.getProjectionMasterSid() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });
		gtnForecastBean = getDetailsOfProjection(projectionResultList, gtnForecastBean);

		return gtnForecastBean;
	}

	private GtnForecastBean getDetailsOfProjection(List<Object[]> projectionResultList,
			GtnForecastBean gtnForecastBean) {

		Object[] resultRow = projectionResultList.get(0);

		gtnForecastBean.setProjectionName(String.valueOf(resultRow[0]));
		gtnForecastBean.setProjectionDescription(String.valueOf(resultRow[1]));
		gtnForecastBean.setProductForecastLevel(Integer.valueOf(String.valueOf(resultRow[2])));
		gtnForecastBean.setBusinessUnitId(Integer.valueOf(String.valueOf(resultRow[3])));
		gtnForecastBean.setCompanyMasterSid(Integer.valueOf(String.valueOf(resultRow[4])));
		gtnForecastBean.setItemGroupSid(resultRow[5] != null ? Integer.valueOf(String.valueOf(resultRow[5])) : 0);
		gtnForecastBean.setProductHierarchySid(Integer.valueOf(String.valueOf(resultRow[6])));
		gtnForecastBean.setProdRelationshipBuilderSid(Integer.valueOf(String.valueOf(resultRow[7])));
		gtnForecastBean.setProductHierarchyInnerLevel(Integer.valueOf(String.valueOf(resultRow[8])));
		gtnForecastBean.setToPeriod(Integer.valueOf(String.valueOf(resultRow[9])));
		gtnForecastBean.setFromPeriod(Integer.valueOf(String.valueOf(resultRow[10])));

		return gtnForecastBean;
	}

	@SuppressWarnings("unchecked")
	public GtnForecastBean getProjectionSelectionDetails(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {

		String frequencyQuery = gtnWsSqlService.getQuery("FREQUENCY_DETAILS");
		List<Object> frequencyQueryResultList = (List<Object>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				frequencyQuery, new Object[] { gtnForecastBean.getProjectionMasterSid() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		String psSelectQuery = gtnWsSqlService.getQuery("RETURNS_PROJECTION_SELECTION_DETAILS");
		List<Object> psSelectQueryResultList = (List<Object>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				psSelectQuery, new Object[] { gtnForecastBean.getProjectionMasterSid() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		String hierarchyNbrQuery = gtnWsSqlService.getQuery("HIERARCHY_NO_DETAILS");
		List<Object[]> hierarchyNbrResultList = (List<Object[]>) gtnFrameworkSqlQueryEngine.executeSelectQuery(
				hierarchyNbrQuery, new Object[] { gtnForecastBean.getProjectionMasterSid() },
				new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });

		frequencyQueryResultList.addAll(psSelectQueryResultList);
		gtnForecastBean = getDetailsOfProjectionSelection(frequencyQueryResultList, hierarchyNbrResultList,
				gtnForecastBean);

		return gtnForecastBean;
	}

	private GtnForecastBean getDetailsOfProjectionSelection(List<Object> projectionSelectionResultList,
			List<Object[]> hierarchyNbrResultList, GtnForecastBean gtnForecastBean) {
		Map<String, List<String>> selectedHierarchy = new HashMap<>();
		if (projectionSelectionResultList != null) {
			gtnForecastBean.setFrequency(String.valueOf(projectionSelectionResultList.get(0)));
			gtnForecastBean.setHistory(String.valueOf(projectionSelectionResultList.get(1)));
			gtnForecastBean.setForecastType(String.valueOf(projectionSelectionResultList.get(2)));
			gtnForecastBean.setProjectionPeriodOrder(String.valueOf(projectionSelectionResultList.get(3)));

		}
		int listSize = hierarchyNbrResultList.size();
		String hierarchyNo = "";
		String levelNo = "";
		String lastLevelNo = "";
		List<String> rightTableHierarchyList = new ArrayList<>();
		for (int i = 0; i < listSize; i++) {
			levelNo = String.valueOf(hierarchyNbrResultList.get(i)[1]);
			if (lastLevelNo != "" && !lastLevelNo.equals(levelNo)) {
				selectedHierarchy.put(lastLevelNo, rightTableHierarchyList);
				rightTableHierarchyList = new ArrayList<>();
			}
			hierarchyNo += "('" + String.valueOf(hierarchyNbrResultList.get(i)[0]) + "')";
			rightTableHierarchyList.add(String.valueOf(hierarchyNbrResultList.get(i)[0]));
			if (i != listSize - 1) {
				hierarchyNo += ",";
			}

			lastLevelNo = levelNo;
		}
		selectedHierarchy.put(lastLevelNo, rightTableHierarchyList);
		gtnForecastBean.setSelectedHierarchyNo(hierarchyNo);
		gtnForecastBean.setRightTableHierarchy(selectedHierarchy);
		return gtnForecastBean;
	}

}