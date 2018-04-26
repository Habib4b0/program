package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkProductLevelLoadService {

	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	public GtnFrameworkProductLevelLoadService() {
		super();
	}

	public String getProductLevelQuery(GtnForecastHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService
				.getHierarchyBuilder(inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
		HierarchyLevelDefinitionBean selectedHierarchyBean = HierarchyLevelDefinitionBean
				.getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
		if (selectedHierarchyBean.isUserDefined()) {
			return buildQueryForUserDefinedLevel(inputBean);
		}
		return getQueryForLinkedLevelProduct(inputBean, hierarchyDefinitionList, selectedHierarchyBean);
	}

	private String buildQueryForUserDefinedLevel(GtnForecastHierarchyInputBean inputBean) {
		List<Object> input = new ArrayList<>();
		input.add(inputBean.getRelationShipBuilderSid());
		input.add(inputBean.getLevelNo());
		input.add(inputBean.getRelationVersionNo());
		return gtnWsSqlService.getQuery(input, "SelectValuesFromUserDefinedHierarchy");
	}

	@SuppressWarnings("rawtypes")
	private String getQueryForLinkedLevelProduct(GtnForecastHierarchyInputBean inputBean,
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList,
			HierarchyLevelDefinitionBean selectedHierarchyBean) throws GtnFrameworkGeneralException {

		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		String situationName = "LOAD_AVAILABLE_TABLE_PRODUCT";
		if (inputBean.isNdc()) {
			situationName = "LOAD_AVAILABLE_TABLE_FOR_NDC";
		}
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
				situationName, hierarchyDefinitionList);

		if (!inputBean.getBusinessUnitValue().equals("null")
				&& !String.valueOf(inputBean.getBusinessUnitValue()).equals("0")
				&& !String.valueOf(inputBean.getBusinessUnitValue()).isEmpty()) {
			queryBean.addWhereClauseBean("ITEM_MASTER.ORGANIZATION_KEY", null, GtnFrameworkOperatorType.EQUAL_TO,
					GtnFrameworkDataType.STRING, String.valueOf(inputBean.getBusinessUnitValue()));
		}
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputBean.getRelationShipBuilderSid());
		inputList.add(String.valueOf(lastLevelDto.getLevelNo()));
		inputList.add(inputBean.getRelationVersionNo());
		inputList.add(inputBean.getRelationVersionNo());
		inputList.add(String.valueOf(selectedHierarchyBean.getLevelNo()));
		return gtnWsSqlService.getReplacedQuery(inputList, queryBean.generateQuery());
	}


}
