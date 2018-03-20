package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl.GtnFrameworkCustLevelQueryGenerator;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkCustomerLevelLoadService {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkCustLevelQueryGenerator customerQueryGenerator;
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	public String getQueryForLinkedLevelCustomer(GtnForecastHierarchyInputBean inputBean,
			List<HierarchyLevelDefinitionBean> hierarchyDefinitionList,
			HierarchyLevelDefinitionBean selectedHierarchyBean) {

		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		HierarchyLevelDefinitionBean selectedHierarchyLevelDto=HierarchyLevelDefinitionBean.getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = customerQueryGenerator.getAvailableTableLoadQuery(inputBean,
				lastLevelDto, hierarchyDefinitionList);
		StringBuilder finalQuery = new StringBuilder(queryBean.generateQuery());
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputBean.getRelationShipBuilderSid());
		inputList.add(String.valueOf(lastLevelDto.getLevelNo()));
		inputList.add(inputBean.getRelationVersionNo());
		inputList.add(inputBean.getRelationVersionNo());
		inputList.add(String.valueOf(selectedHierarchyLevelDto.getLevelNo()));
		if (inputBean.getForecastEligibleDate() != null) {
			inputList.add(dateFormat.format(inputBean.getForecastEligibleDate()));
			inputList.add(dateFormat.format(inputBean.getForecastEligibleDate()));
			finalQuery.append("AND (CONTRACT_ELIGIBLE_DATE >= '?' OR CONTRACT_ELIGIBLE_DATE IS NULL)");
			finalQuery.append("AND (CFP_ELIGIBLE_DATE >= '?' OR CFP_ELIGIBLE_DATE IS NULL)");
		}
		return gtnWsSqlService.getReplacedQuery(inputList, finalQuery.toString());
	}

	public String getCustomerLevelQuery(GtnForecastHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService.getHierarchyBuilder(
				inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
		HierarchyLevelDefinitionBean selectedHierarchyBean = HierarchyLevelDefinitionBean
				.getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
		return getQueryForLinkedLevelCustomer(inputBean, hierarchyDefinitionList, selectedHierarchyBean);
	}
}
