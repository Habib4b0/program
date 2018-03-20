package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.querygenerator.serviceimpl.GtnFrameworkProdLevelQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkProductLevelLoadService {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;


	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	@Autowired
	private GtnFrameworkFileReadWriteService fileService;
	@Autowired
	private GtnFrameworkProdLevelQueryGenerator queryGeneratorService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
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

		List<HierarchyLevelDefinitionBean> customerHierarchyLevelList = relationUpdateService.getHierarchyBuilder(
				inputBean.getSelectedCustomerHierarcySid(), inputBean.getSelectedCustomerHierarchyVersionNo());
		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getAvailableTableLoadQuery(inputBean,
				lastLevelDto, hierarchyDefinitionList);

		List<Set> sidList = getCustomerConractSid(inputBean.getSelectedCustomerList(),
				customerHierarchyLevelList, inputBean.getSelectedCustomerRelationShipBuilderVersionNo());
		getWhereQueryForProductLevel(sidList, queryBean);
		if (!String.valueOf(inputBean.getBusinessUnitValue()).equals("null")
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



	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Set> getCustomerConractSid(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList, int customerRelationVersionNo)
			throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = getCustomerContractSidQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, Boolean.FALSE);
		if (queryBean == null || customerRelationVersionNo == 0) {
			return Collections.emptyList();
		}
		int relationshipSid = selectedCustomerContractList.get(0).getRelationshipBuilderSid();
		List<String> whereQueries = getRelationQueries(relationshipSid, customerRelationVersionNo,
				customerHierarchyLevelDefinitionList
						.toArray(new HierarchyLevelDefinitionBean[customerHierarchyLevelDefinitionList.size()]));
		List<Set> finalList = new ArrayList<>();
		Set<Integer> customerSidSet = new HashSet<>();
		Set<Integer> contractSidSet = new HashSet<>();

		getWhereQueryForCustomerAndContract(selectedCustomerContractList, customerHierarchyLevelDefinitionList,
				queryBean, customerRelationVersionNo);
		String finalQuery = gtnWsSqlService.getReplacedQuery(whereQueries,queryBean.generateQuery() );
		List<Object[]> results = (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(finalQuery);
		for (Object[] object : results) {
			customerSidSet.add(getIntegerValue(object, 0));
			contractSidSet.add(getIntegerValue(object, 1));
		}
		finalList.add(customerSidSet);
		finalList.add(contractSidSet);
		return finalList;
	}

	private GtnFrameworkQueryGeneratorBean getCustomerContractSidQuery(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList, boolean isProduct) {
		if (selectedCustomerContractList == null || selectedCustomerContractList.isEmpty())
			return null;

		HierarchyLevelDefinitionBean lastLinketLevel = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(customerHierarchyLevelDefinitionList);
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(lastLinketLevel,
				Collections.<String>emptyList());
		queryBean.removeAllWhereClauseConfigList();
		queryBean.removeSelectClauseByIndex(0);
		queryBean.removeSelectClauseByIndex(0);
		if (isProduct) {
			queryBean.addSelectClauseBean("ITEM_MASTER.ITEM_MASTER_SID", "ITEM_MASTER_SID1", Boolean.TRUE, null);
			return queryBean;
		}
		queryBean.addSelectClauseBean("COMPANY_MASTER.COMPANY_MASTER_SID", "COMPANY_MASTER_SID1", Boolean.TRUE, null);
		queryBean.addSelectClauseBean("CONTRACT_MASTER.CONTRACT_MASTER_SID", "CONTRACT_MASTER_SID1", Boolean.TRUE,
				null);
		return queryBean;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getWhereQueryForProductLevel(List<Set> sidListb, GtnFrameworkQueryGeneratorBean queryBean) {
		if (!sidListb.isEmpty()) {
			Set<Integer> customerSidSet = sidListb.get(0);
			Set<Integer> contractSidSet = sidListb.get(1);
			if (!customerSidSet.isEmpty()) {
				queryBean.addWhereClauseBean("CCP_DETAILS.COMPANY_MASTER_SID", null, GtnFrameworkOperatorType.IN,
						GtnFrameworkDataType.LIST, new ArrayList<>(customerSidSet));
			}
			if (!contractSidSet.isEmpty()) {
				queryBean.addWhereClauseBean("CCP_DETAILS.CONTRACT_MASTER_SID", null, GtnFrameworkOperatorType.IN,
						GtnFrameworkDataType.LIST, new ArrayList<>(contractSidSet));
			}
		}
	}


	private void getWhereQueryForCustomerAndContract(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
			List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			GtnFrameworkQueryGeneratorBean queryBean, int relationVersionNo) {
		List<GtnFrameworkRelationshipLevelDefintionBean> modifiableList = new ArrayList<>(selectedCustomerContractList);
		Collections.sort(modifiableList);
		int maxlevelNo = 0;
		for (GtnFrameworkRelationshipLevelDefintionBean leveldto : modifiableList) {
			if (maxlevelNo < leveldto.getLevelNo())
				maxlevelNo = leveldto.getLevelNo();
		}
		getWhereQueryByAllRelationShip(modifiableList, maxlevelNo, 0, queryBean);
		// HierarchyLevelDefinitionBean lastLevelDto =
		// customerHierarchyLevelDefinitionList
		// .get(customerHierarchyLevelDefinitionList.size() - 1);
		// if (maxlevelNo < lastLevelDto.getLevelNo()) {
		// List<GtnFrameworkRelationshipLevelDefintionBean> relationList =
		// getRelationShipValuesByRelationShipBuilderSid(
		// selectedCustomerContractList.get(0).getRelationshipBuilderSid(), maxlevelNo,
		// relationVersionNo);
		// getWhereQueryByAllRelationShip(relationList, lastLevelDto.getLevelNo(),
		// maxlevelNo - 1, queryBean);
		// }
	}

	@SuppressWarnings("unchecked")
	private void getWhereQueryByAllRelationShip(List<GtnFrameworkRelationshipLevelDefintionBean> modifiableList,
			int maxlevelNo,
			int startPosition, GtnFrameworkQueryGeneratorBean queryBean) {
		String whereClauseFieldName = "";
		for (int i = startPosition; i < maxlevelNo; i++) {
			List<Object> dataList = GtnFrameworkRelationshipLevelDefintionBean
					.getLinkedLevelListByLevelNo(modifiableList, i + 1);

			Set<String> masterSids = (Set<String>) dataList.get(0);
			List<GtnFrameworkRelationshipLevelDefintionBean> levelBeanList = (List<GtnFrameworkRelationshipLevelDefintionBean>) dataList
					.get(1);
			if (levelBeanList.isEmpty())
				continue;

			String tableName = levelBeanList.get(0).getTableName();
			String fieldName = levelBeanList.get(0).getFieldName();

			GtnFrameworkSingleColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(tableName, fieldName);

			whereClauseFieldName = keyListBean.getWhereClauseColumn();
			queryBean.addWhereClauseBean(keyListBean.getActualTtableName() + "." + whereClauseFieldName, null,
					GtnFrameworkOperatorType.IN, GtnFrameworkDataType.LIST, new ArrayList<>(masterSids));
		}
	}


	private GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto,
			List<String> groupFilteredItems) {
		GtnFrameworkHierarchyQueryBean queryBaen = fileService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyDefinitionSid(),
				selectedHierarchyLevelDto.getHierarchyLevelDefinitionSid(), selectedHierarchyLevelDto.getVersionNo());
		return queryBaen.getQuery();

	}

	private List<String> getRelationQueries(int relationshipSid, int relationVersionNo,
			HierarchyLevelDefinitionBean... levelHierarchyLevelDefinitionList) {
		List<String> queryList = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		for (HierarchyLevelDefinitionBean levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(levelDto.getLevelNo());
				input.add(relationshipSid);
				input.add(relationVersionNo);
				String relationQuery = gtnWsSqlService.getQuery(input, "relationShipSubQuery");
				queryList.add(relationQuery);
				input.clear();
			}
		}
		return queryList;
	}

	private Integer getIntegerValue(Object[] objects, int index) {
		return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
	}

}
