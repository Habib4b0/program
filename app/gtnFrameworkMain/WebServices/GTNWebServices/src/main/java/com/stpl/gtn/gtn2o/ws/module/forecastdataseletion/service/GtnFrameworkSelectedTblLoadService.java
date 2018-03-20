package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl.GtnFrameworkSelectedTblLoadQueryGenerator;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkSelectedTblLoadService {
	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnFrameworkSelectedTblLoadQueryGenerator service;

	public String getChildLevelQueryForProduct(HierarchyLevelDefinitionBean hierarchyLevelBean, String string) {
		return service.getChildLevelQueryForProduct(hierarchyLevelBean, string);
	}
	public String getQueryForSelectedCustomer(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {
		GtnFrameworkRelationshipLevelDefintionBean selecteHierarchyBean = inputBean.getSelectedHierarchyLevelDto();
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService.getHierarchyBuilder(
				selecteHierarchyBean.getHierarchyDefinitionSid(), selecteHierarchyBean.getHierarchyVersionNo());
		return service.getQueryForSelectedCustomer(inputBean, hierarchyDefinitionList);
	}

}
