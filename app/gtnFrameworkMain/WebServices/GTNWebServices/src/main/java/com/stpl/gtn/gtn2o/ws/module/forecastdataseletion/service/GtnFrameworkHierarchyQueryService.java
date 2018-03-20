package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.discount.GtnFrameworkDiscountQueryGenerator;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkHierarchyQueryService {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

	@Autowired
	private GtnFrameworkDiscountQueryGenerator queryGeneratorService;

	public String queryFormationForLoadingDdlb(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {

		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService
				.getHierarchyBuilderBasedOnProjectionId(inputBean.getProjectionId(), inputBean.getHierarchyIndicator());
		HierarchyLevelDefinitionBean selectedHierarchyLevelDto = HierarchyLevelDefinitionBean
				.getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
		return queryGeneratorService.getQueryForLoadingDiscount(inputBean, selectedHierarchyLevelDto);
	}



}
