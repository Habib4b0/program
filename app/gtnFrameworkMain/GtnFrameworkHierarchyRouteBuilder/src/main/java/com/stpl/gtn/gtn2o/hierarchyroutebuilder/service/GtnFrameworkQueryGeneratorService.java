package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.querygenerator.GtnFrameworkHierarchyQueryGenerator;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkQueryGeneratorService {
	@Autowired
	private GtnFrameworkFileReadWriteService fileReadWriteService;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	private static final String COMPANY_MASTER_SID = "COMPANY_MASTER.COMPANY_MASTER_SID";
	private static final String ITEM_MASTER_SID = "ITEM_MASTER.ITEM_MASTER_SID";

	@Autowired
	private GtnFrameworkHierarchyQueryGenerator queryGenerator;

	public GtnFrameworkQueryGeneratorService() {
		super();
	}

	public GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyDefinitionSid(),
				selectedHierarchyLevelDto.getHierarchyLevelDefinitionSid(), selectedHierarchyLevelDto.getVersionNo());
		GtnFrameworkQueryGeneratorBean queryGeneratorBean = queryBaen.getQuery();
		queryGeneratorBean.removeAllWhereClauseConfigList();
		queryGeneratorBean.removeAllSelectClause();
		gtnHierarchyServiceBuilder.getInboundRestrictionQueryForAutoUpdate(queryGeneratorBean);

		return queryGeneratorBean;

	}

	public GtnFrameworkQueryGeneratorBean getQuerybySituationNameAndLevel(
			HierarchyLevelDefinitionBean currnetHierarchyLevelBean,
			String situationName, List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(currnetHierarchyLevelBean);
		queryGenerator.generateQuery(situationName, queryBean);
		queryGenerator.replaceWhereClauseColumns(currnetHierarchyLevelBean, hierarchyLevelDefinitionList,
				queryBean);
		queryGenerator.replaceSelectClauseColumns(currnetHierarchyLevelBean, hierarchyLevelDefinitionList, queryBean);

		return queryBean;
	}

	public String getHierarchyNo(List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList,
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		StringBuilder custProdQuery = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		HierarchyLevelDefinitionBean previousHierarchyBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(customerHierarchyLevelDefinitionList, selectedHierarchyLevelDto);
		int i;
		if (previousHierarchyBean == null)
			i = selectedHierarchyLevelDto.getLevelNo() - 1;
		else
			i = previousHierarchyBean.getLevelNo();
		for (; i < selectedHierarchyLevelDto.getLevelNo(); i++) {
			HierarchyLevelDefinitionBean leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				custProdQuery.append(", '%'");
				custProdQuery.append(",'.'");
				continue;
			}
			custProdQuery.append(",");
			GtnFrameworkSelectColumnRelationBean singleColumnRelationBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			custProdQuery.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			custProdQuery.append(",'.'");
		}
		finalQuery.append(" concat(RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO");
		finalQuery.append(custProdQuery);
		finalQuery.append(")");
		return finalQuery.toString();
	}

	public void getWhereQueryForCustomerAndContract(
			List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
			GtnFrameworkQueryGeneratorBean queryBean) {
		List<GtnFrameworkRelationshipLevelDefintionBean> modifiableList = new ArrayList<>(selectedCustomerContractList);
		Collections.sort(modifiableList);

		int maxlevelNo = 0;
		for (GtnFrameworkRelationshipLevelDefintionBean leveldto : modifiableList) {
			if (maxlevelNo < leveldto.getLevelNo())
				maxlevelNo = leveldto.getLevelNo();
		}
		getWhereQueryByAllRelationShip(modifiableList, maxlevelNo, 0, queryBean);
	}

	@SuppressWarnings("unchecked")
	private void getWhereQueryByAllRelationShip(List<GtnFrameworkRelationshipLevelDefintionBean> modifiableList,
			int maxlevelNo, int startPosition, GtnFrameworkQueryGeneratorBean queryBean) {
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

			GtnFrameworkSelectColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(tableName, fieldName);

			whereClauseFieldName = keyListBean.getWhereClauseColumn();
			queryBean.addWhereClauseBean(keyListBean.getActualTtableName() + "." + whereClauseFieldName, null,
					GtnFrameworkOperatorType.IN, GtnFrameworkDataType.LIST, new ArrayList<>(masterSids));
		}

	}

	public void getWhereQueryBasedOnHierarchyType(String hieType, List<String> groupFilteredCompanies,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		if (groupFilteredCompanies == null || groupFilteredCompanies.isEmpty())
			return;
		if ("Customer Hierarchy".equals(hieType)) {
			finalQueryBean.addWhereClauseBean(COMPANY_MASTER_SID, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		} else {
			finalQueryBean.addWhereClauseBean(ITEM_MASTER_SID, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		}
	}
}
