package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkLoadDiscountService {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGenerator;


	public GtnFrameworkLoadDiscountService() {
		super();
	}

	public String queryFormationForLoadingDdlb(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {

		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService
				.getHierarchyBuilderBasedOnProjectionId(inputBean.getProjectionId(), inputBean.getHierarchyIndicator());
		HierarchyLevelDefinitionBean selectedHierarchyLevelDto = HierarchyLevelDefinitionBean
				.getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
		return getQueryForLoadingDiscount(inputBean, selectedHierarchyLevelDto, hierarchyDefinitionList);
	}

	public String getQueryForLoadingDiscount(GtnForecastHierarchyInputBean inputBean,
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto, List<HierarchyLevelDefinitionBean> hierarchyDefinitionList) {
		String situationName = "LOAD_DISCOUNT_PRODUCT";
		if (inputBean.getHierarchyIndicator().equals("C")) {
			situationName = "LOAD_DISCOUNT";
		}
		GtnFrameworkQueryGeneratorBean queryBaen = queryGenerator
				.getQuerybySituationNameAndLevel(selectedHierarchyLevelDto, situationName, hierarchyDefinitionList);
		return queryBaen.generateQuery();
	}

}
