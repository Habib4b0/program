package com.stpl.app.gtnforecasting.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.service.FileReadWriteService;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.QueryUtils;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.dataselectionedit.GtnWsForecastHierarchyInsertRequest;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.server.VaadinSession;

public class RelationShipFilterLogic {

	private GtnFrameworkEntityMasterBean masterBean = GtnFrameworkEntityMasterBean.getInstance();
	private static final RelationShipFilterLogic instance = new RelationShipFilterLogic();
	private CommonDAO daoImpl = new CommonDAOImpl();
	private FileReadWriteService fileReadWriteService = new FileReadWriteService();
	private static final String RS_CONTRACT = "RS_CONTRACT";
	private static final String CFP_CONTRACT_SID = "CFP_CONTRACT.CFP_CONTRACT_SID";
	private static final String RS_CONTRACT_DETAILS = "RS_CONTRACT_DETAILS";
	private static final String COMPANY_MASTER_SID = "COMPANY_MASTER.COMPANY_MASTER_SID";
	private static final String ITEM_MASTER_SID = "ITEM_MASTER.ITEM_MASTER_SID";
	private static final String RELATIONSHIP_BUILD_VERSION = "RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO";
	private static final String RELATIONSHIP_BUILD_HIERARCHY_NO = "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO";
	private static final String RELATIONSHIP_LEVEL_DEFN = "RELATIONSHIP_LEVEL_DEFINITION";
	private static final String RELATION_HIERARCHY_JOIN = "HIERARCHY_NO_JOIN.HIERARCHY_NO";

	private RelationShipFilterLogic() {
		// Singleton constructor
	}

	public static RelationShipFilterLogic getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getHierarchyLevelDefinition(int hierarchyDefinitionSid, int hierarchyVersionNo) {
		List<String> input = new ArrayList<>();
		List<Leveldto> resultDtoList;
		input.add(String.valueOf(hierarchyDefinitionSid));
		input.add(String.valueOf(hierarchyVersionNo));
		List<Object[]> results = QueryUtils.getAppData(input, "getHierarchyLevelVlaues", null);
		resultDtoList = cutomizeHierarchyBean(results, hierarchyVersionNo);
		Collections.sort(resultDtoList);
		return resultDtoList;
	}

	public List<Leveldto> cutomizeHierarchyBean(List<Object[]> results, int hierarchyVersionNo) {
		List<Leveldto> resultDtoList = new ArrayList<>();
		for (Object[] objects : results) {
			Leveldto levelDto = new Leveldto();
			levelDto.setLevelNo(getIntegerValue(objects, 0));
			levelDto.setLevelValueReference(getStringValue(objects, 1));
			levelDto.setTableName(objects[2] == null ? "" : objects[2].toString());
			levelDto.setFieldName(objects[3] == null ? "" : objects[3].toString());
			levelDto.setLevel(objects[4] == null ? "" : objects[4].toString());
			levelDto.setHierarchyLevelDefnId(objects[5] == null ? "0" : objects[5].toString());
			levelDto.setHierarchyId((Integer.valueOf(objects[6] == null ? "0" : objects[6].toString())));
			levelDto.setHierarchyType(objects[7] == null ? "" : objects[7].toString());
			levelDto.setHierarchyVersionNo(hierarchyVersionNo);
			resultDtoList.add(levelDto);
		}
		return resultDtoList;
	}

	public List<Leveldto> loadAvailableCustomerlevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList, String dedLevel,
			String dedValue, int relationVersionNo) throws CloneNotSupportedException {
		if (selectedHierarchyLevelDto.isUserDefined()) {
			return getUserDefinedData(selectedHierarchyLevelDto, relationshipSid, relationVersionNo);
		}
		return getLinkedLevelData(selectedHierarchyLevelDto, relationshipSid, groupFilteredCompanies,
				levelHierarchyLevelDefinitionList, dedLevel, dedValue, relationVersionNo);
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getLinkedLevelData(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList, String dedLevel,
			String dedValue, int relationVersionNo) throws CloneNotSupportedException {
		List<Leveldto> resultList = new ArrayList<>();
		String finalQuery = getQueryForLinkedLevelCustomer(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredCompanies, levelHierarchyLevelDefinitionList, dedLevel, dedValue, relationVersionNo);
		List<Object[]> resultsRelationList = getRelationshipList(selectedHierarchyLevelDto, relationshipSid);
		List<Object[]> resultsDataList = (List<Object[]>) daoImpl.executeSelectQuery(finalQuery, null, null);
		if (resultsDataList != null && !resultsDataList.isEmpty()) {
			for (int i = 0; i < resultsDataList.size(); i++) {
				Leveldto dto = (Leveldto) selectedHierarchyLevelDto.clone();
				Object[] obj = resultsDataList.get(i);
				Object[] relationData = getProperRelationData(
						Integer.parseInt(String.valueOf(obj[NumericConstants.ONE])), resultsRelationList);
				if (relationData.length > 0) {
					dto.setRelationshipLevelValue(String.valueOf(relationData[0]));
					dto.setLevelNo(Integer.parseInt(String.valueOf(relationData[1])));
					dto.setParentNode(String.valueOf(relationData[NumericConstants.TWO]));
					dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(relationData[NumericConstants.THREE])));
					dto.setHierarchyNo(String.valueOf(relationData[NumericConstants.FOUR]));
					dto.setRelationShipBuilderId(String.valueOf(relationData[NumericConstants.FIVE]));
					dto.setLevel(String.valueOf(obj[NumericConstants.ZERO]));
					dto.setDisplayValue(String.valueOf(obj[NumericConstants.ZERO]));
					resultList.add(dto);
				}
			}
		}
		return resultList;
	}

	private String getQueryForLinkedLevelCustomer(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList, String dedLevel,
			String dedValue, int relationVersionNo) {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(selectedHierarchyLevelDto,
				groupFilteredCompanies);
		List<String> whereQueries = getRelationQueries(relationshipSid, relationVersionNo,
				levelHierarchyLevelDefinitionList.toArray(new Leveldto[levelHierarchyLevelDefinitionList.size()]));
		getDeductionJoin(dedLevel, dedValue, queryBean);
		return QueryUtils.getQuery(queryBean.generateQuery(), whereQueries);
	}

	public void getDeductionJoin(String dedLevel, String dedValue, GtnFrameworkQueryGeneratorBean queryBean) {
		if (!dedLevel.isEmpty() && !dedValue.isEmpty()) {
			GtnFrameworkJoinClauseBean rsContractJoin = queryBean.addJoinClauseBean(RS_CONTRACT, RS_CONTRACT,
					GtnFrameworkJoinType.JOIN);
			rsContractJoin.addConditionBean("RS_CONTRACT.CONTRACT_MASTER_SID", "CONTRACT_MASTER.CONTRACT_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractJoin.addConditionBean(CFP_CONTRACT_SID, "RS_CONTRACT.CFP_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractJoin.addConditionBean("RS_CONTRACT." + dedLevel, dedValue, GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean rsContractDetailsJoin = queryBean.addJoinClauseBean(RS_CONTRACT_DETAILS,
					RS_CONTRACT_DETAILS, GtnFrameworkJoinType.JOIN);
			rsContractDetailsJoin.addConditionBean("RS_CONTRACT_DETAILS.RS_CONTRACT_SID", "RS_CONTRACT.RS_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractDetailsJoin.addConditionBean(COMPANY_MASTER_SID, "CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
		}
	}

	private Object[] getProperRelationData(int masterSid, List<Object[]> resultsRelationList) {

		for (Object[] objects : resultsRelationList) {
			if (masterSid == Integer.parseInt(String.valueOf(objects[NumericConstants.ZERO]))) {
				return objects;
			}
		}

		return new Object[0];
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getRelationshipList(Leveldto selectedHierarchyLevelDto, int relationshipSid) {
		List<Object> relationInput = new ArrayList<>();
		relationInput.add(relationshipSid);
		relationInput.add(selectedHierarchyLevelDto.getLevelNo());
		return (List<Object[]>) QueryUtils.getAppData(relationInput, "selectQueryForLevelLoadingForRelation", null);
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getUserDefinedData(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			int relationVersionNo) throws CloneNotSupportedException {
		String finalQuery = buildQueryForUserDefinedLevel(selectedHierarchyLevelDto, relationshipSid,
				relationVersionNo);
		List<Leveldto> resultList = new ArrayList<>();
		List<Object[]> resultsDataList = (List<Object[]>) daoImpl.executeSelectQuery(finalQuery, null, null);
		if (resultsDataList != null && !resultsDataList.isEmpty()) {
			for (int i = 0; i < resultsDataList.size(); i++) {
				Leveldto dto = (Leveldto) selectedHierarchyLevelDto.clone();
				Object[] obj = resultsDataList.get(i);
				dto.setRelationshipLevelValue(String.valueOf(obj[0]));
				dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
				dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
				dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
				dto.setHierarchyNo(String.valueOf(obj[NumericConstants.FOUR]));
				dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.FIVE]));
				dto.setLevel(String.valueOf(obj[NumericConstants.SIX]));
				dto.setDisplayValue(String.valueOf(obj[NumericConstants.SIX]));
				resultList.add(dto);
			}
		}
		return resultList;
	}

	public List<Leveldto> loadAvailableProductlevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredItems, List<Leveldto> selectedCustomerContractList, boolean isNdc,
			List<Leveldto> levelHierarchyLevelDefinitionList, List<Leveldto> customerHierarchyLevelList,
			String dedLevel, String dedValue, int relationVersionNo, int customerRelationVersionNo,
			Object businessUnitValue) throws CloneNotSupportedException {

		if (selectedHierarchyLevelDto.isUserDefined()) {
			return getUserDefinedData(selectedHierarchyLevelDto, relationshipSid, relationVersionNo);
		}
		return getLinkedLevelDataForProduct(selectedHierarchyLevelDto, relationshipSid, groupFilteredItems,
				selectedCustomerContractList, isNdc, levelHierarchyLevelDefinitionList, customerHierarchyLevelList,
				dedLevel, dedValue, relationVersionNo, customerRelationVersionNo, businessUnitValue);

	}

	@SuppressWarnings("unchecked")
	private List<Leveldto> getLinkedLevelDataForProduct(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredItems, List<Leveldto> selectedCustomerContractList, boolean isNdc,
			List<Leveldto> levelHierarchyLevelDefinitionList, List<Leveldto> customerHierarchyLevelList,
			String dedLevel, String dedValue, int relationVersionNo, int customerRelationVersionNo,
			Object businessUnitValue) throws CloneNotSupportedException {

		List<Leveldto> resultList = new ArrayList<>();
		String finalQuery = buildQueryForLinkedLevelForProduct(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredItems, selectedCustomerContractList, isNdc, levelHierarchyLevelDefinitionList,
				customerHierarchyLevelList, dedLevel, dedValue, relationVersionNo, customerRelationVersionNo,
				businessUnitValue);
		List<Object[]> resultsRelationList = getRelationshipList(selectedHierarchyLevelDto, relationshipSid);
		List<Object[]> resultsDataList = (List<Object[]>) daoImpl.executeSelectQuery(finalQuery, null, null);
		if (resultsDataList != null && !resultsDataList.isEmpty()) {
			for (int i = 0; i < resultsDataList.size(); i++) {
				Leveldto dto = (Leveldto) selectedHierarchyLevelDto.clone();
				Object[] obj = resultsDataList.get(i);
				Object[] relationData = getProperRelationData(
						Integer.parseInt(String.valueOf(obj[NumericConstants.ONE])), resultsRelationList);
				if (relationData.length > 0) {
					dto.setRelationshipLevelValue(String.valueOf(relationData[0]));
					dto.setLevelNo(Integer.parseInt(String.valueOf(relationData[1])));
					dto.setParentNode(String.valueOf(relationData[NumericConstants.TWO]));
					dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(relationData[NumericConstants.THREE])));
					dto.setHierarchyNo(String.valueOf(relationData[NumericConstants.FOUR]));
					dto.setRelationShipBuilderId(String.valueOf(relationData[NumericConstants.FIVE]));
					dto.setLevel(String.valueOf(obj[NumericConstants.ZERO]));
					dto.setDisplayValue(String.valueOf(obj[NumericConstants.ZERO]));
					if (isNdc) {
						dto.setStrength(obj[NumericConstants.TWO].toString());
						dto.setForm(obj[NumericConstants.THREE].toString());
					}
					resultList.add(dto);
				}
			}
		}
		return resultList;
	}

	@SuppressWarnings("rawtypes")
	private String buildQueryForLinkedLevelForProduct(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredItems, List<Leveldto> selectedCustomerContractList, boolean isNdc,
			List<Leveldto> levelHierarchyLevelDefinitionList, List<Leveldto> customerHierarchyLevelList,
			String dedLevel, String dedValue, int relationVersionNo, int customerRelationVersionNo,
			Object businessUnitValue) {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(selectedHierarchyLevelDto,
				groupFilteredItems);
		List<String> whereQueries = getRelationQueries(relationshipSid, relationVersionNo,
				levelHierarchyLevelDefinitionList.toArray(new Leveldto[levelHierarchyLevelDefinitionList.size()]));
		getRscontractJoinForProduct(dedLevel, dedValue, queryBean);
		addNdcSelectAndJoin(isNdc, queryBean);
		List<Set> sidList = getCustomerConractSid(selectedCustomerContractList, customerHierarchyLevelList,
				customerRelationVersionNo);
		getWhereQueryForProductLevel(sidList, queryBean);
		if (!String.valueOf(businessUnitValue).equals("null") && !String.valueOf(businessUnitValue).equals("0")
				&& !String.valueOf(businessUnitValue).isEmpty()) {
			queryBean.addWhereClauseBean("ITEM_MASTER.ORGANIZATION_KEY", null, GtnFrameworkOperatorType.EQUAL_TO,
					GtnFrameworkDataType.STRING, String.valueOf(businessUnitValue));
		}
		return QueryUtils.getQuery(queryBean.generateQuery(), whereQueries);
	}

	public void addNdcSelectAndJoin(boolean isNdc, GtnFrameworkQueryGeneratorBean queryBean) {
		if (isNdc) {
			GtnFrameworkJoinClauseBean strengthJoin = queryBean.addJoinClauseBean("HELPER_TABLE", "STR",
					GtnFrameworkJoinType.JOIN);
			strengthJoin.addConditionBean("STr.HELPER_TABLE_SID", "ITEM_MASTER.STRENGTH",
					GtnFrameworkOperatorType.EQUAL_TO);
			GtnFrameworkJoinClauseBean formJoin = queryBean.addJoinClauseBean("HELPER_TABLE", "FORM",
					GtnFrameworkJoinType.JOIN);
			formJoin.addConditionBean("FORm.HELPER_TABLE_SID", "ITEM_MASTER.FORM", GtnFrameworkOperatorType.EQUAL_TO);
			queryBean.addSelectClauseBean("STr.DESCRIPTION", null, Boolean.TRUE, null);
			queryBean.addSelectClauseBean("FORm.DESCRIPTION", null, Boolean.TRUE, null);

		}
	}

	public void getRscontractJoinForProduct(String dedLevel, String dedValue,
			GtnFrameworkQueryGeneratorBean queryBean) {
		GtnFrameworkJoinClauseBean ccpJoin = queryBean.addJoinClauseBean("CCP_DETAILS", "CCP_DETAILS",
				GtnFrameworkJoinType.JOIN);
		ccpJoin.addConditionBean(ITEM_MASTER_SID, "CCP_DETAILS.ITEM_MASTER_SID", GtnFrameworkOperatorType.EQUAL_TO);
		if (!dedLevel.isEmpty() && !dedValue.isEmpty()) {
			GtnFrameworkJoinClauseBean rsContracDetailstJoin = queryBean.addJoinClauseBean(RS_CONTRACT_DETAILS,
					RS_CONTRACT_DETAILS, GtnFrameworkJoinType.JOIN);
			rsContracDetailstJoin.addConditionBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", ITEM_MASTER_SID,
					GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean rsContractJoin = queryBean.addJoinClauseBean(RS_CONTRACT, RS_CONTRACT,
					GtnFrameworkJoinType.JOIN);
			rsContractJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			rsContractJoin.addConditionBean("RS_CONTRACT." + dedLevel, dedValue, GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean cfpContractDetailsJoin = queryBean.addJoinClauseBean("CFP_CONTRACT_DETAILS",
					"CFP_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
			cfpContractDetailsJoin.addConditionBean("CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID",
					"CCP_DETAILS.COMPANY_MASTER_SID", GtnFrameworkOperatorType.EQUAL_TO);

			GtnFrameworkJoinClauseBean cfpContractJoin = queryBean.addJoinClauseBean("CFP_CONTRACT", "CFP_CONTRACT",
					GtnFrameworkJoinType.JOIN);
			cfpContractJoin.addConditionBean(CFP_CONTRACT_SID, "CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			cfpContractJoin.addConditionBean("RS_CONTRACT.CFP_CONTRACT_SID", CFP_CONTRACT_SID,
					GtnFrameworkOperatorType.EQUAL_TO);
		}
	}

	public void replaceQuery(String joinQuery, String beforeText, StringBuilder query) {
		int whereIndex = query.indexOf(beforeText);
		query.replace(whereIndex, whereIndex, joinQuery);
	}

	public GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(Leveldto selectedHierarchyLevelDto,
			List<String> groupFilteredItems) {
		StringBuilder query = new StringBuilder();
		GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyId(), selectedHierarchyLevelDto.getHierarchyLevelDefnId(),
				selectedHierarchyLevelDto.getHierarchyVersionNo());
		GtnFrameworkQueryGeneratorBean finalQueryBean = queryBaen.getQuery();

		GtnFrameworkSingleColumnRelationBean keyRealtionBean = masterBean.getKeyRelationBeanUsingTableIdAndColumnName(
				selectedHierarchyLevelDto.getTableName(), selectedHierarchyLevelDto.getFieldName());
		query.append(keyRealtionBean.getActualTtableName());
		query.append(".");
		query.append(keyRealtionBean.getWhereClauseColumn());
		finalQueryBean.addWhereClauseBean(query.toString(), null, GtnFrameworkOperatorType.IN,
				GtnFrameworkDataType.NULL_ALLOWED, "?");
		getWhereQueryBasedOnHierarchyType(selectedHierarchyLevelDto.getHierarchyType(), groupFilteredItems,
				finalQueryBean);
		return finalQueryBean;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Set> getCustomerConractSid(List<Leveldto> selectedCustomerContractList,
			List<Leveldto> customerHierarchyLevelDefinitionList, int customerRelationVersionNo) {
		GtnFrameworkQueryGeneratorBean queryBean = getCustomerContractSidQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, Boolean.FALSE);
		if (queryBean == null || customerRelationVersionNo == 0) {
			return Collections.emptyList();
		}
		int relationshipSid = Integer.parseInt(selectedCustomerContractList.get(0).getRelationShipBuilderId());
		List<String> whereQueries = getRelationQueries(relationshipSid, customerRelationVersionNo,
				customerHierarchyLevelDefinitionList
						.toArray(new Leveldto[customerHierarchyLevelDefinitionList.size()]));
		List<Set> finalList = new ArrayList<>();
		Set<Integer> customerSidSet = new HashSet<>();
		Set<Integer> contractSidSet = new HashSet<>();

		getWhereQueryForCustomerAndContract(selectedCustomerContractList, customerHierarchyLevelDefinitionList,
				queryBean, customerRelationVersionNo);
		String finalQuery = QueryUtils.getQuery(queryBean.generateQuery(), whereQueries);
		List<Object[]> results = (List<Object[]>) daoImpl.executeSelectQuery(finalQuery, null, null);
		for (Object[] object : results) {
			customerSidSet.add(getIntegerValue(object, 0));
			contractSidSet.add(getIntegerValue(object, 1));
		}
		finalList.add(customerSidSet);
		finalList.add(contractSidSet);
		return finalList;
	}

	public GtnFrameworkQueryGeneratorBean getCustomerContractSidQuery(List<Leveldto> selectedCustomerContractList,
			List<Leveldto> customerHierarchyLevelDefinitionList, boolean isProduct) {
		if (selectedCustomerContractList == null || selectedCustomerContractList.isEmpty())
			return null;
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(
				Leveldto.getLastLinkedLevel(customerHierarchyLevelDefinitionList), Collections.<String>emptyList());
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

	private void getWhereQueryForCustomerAndContract(List<Leveldto> selectedCustomerContractList,
			List<Leveldto> customerHierarchyLevelDefinitionList, GtnFrameworkQueryGeneratorBean queryBean,
			int relationVersionNo) {
		List<Leveldto> modifiableList = new ArrayList<>(selectedCustomerContractList);
		Collections.sort(modifiableList);
		int maxlevelNo = 0;
		for (Leveldto leveldto : modifiableList) {
			if (maxlevelNo < leveldto.getLevelNo())
				maxlevelNo = leveldto.getLevelNo();
		}
		getWhereQueryByAllRelationShip(modifiableList, maxlevelNo, 0, queryBean);
		Leveldto lastLevelDto = customerHierarchyLevelDefinitionList
				.get(customerHierarchyLevelDefinitionList.size() - 1);
		if (maxlevelNo < lastLevelDto.getLevelNo()) {
			List<Leveldto> relationList = getRelationShipValuesByRelationShipBuilderSid(
					selectedCustomerContractList.get(0).getRelationShipBuilderId(), maxlevelNo, relationVersionNo);
			getWhereQueryByAllRelationShip(relationList, lastLevelDto.getLevelNo(), maxlevelNo - 1, queryBean);
		}
	}

	@SuppressWarnings("unchecked")
	public void getWhereQueryByAllRelationShip(List<Leveldto> modifiableList, int maxlevelNo, int startPosition,
			GtnFrameworkQueryGeneratorBean queryBean) {
		String whereClauseFieldName = "";
		for (int i = startPosition; i < maxlevelNo; i++) {
			List<Object> dataList = Leveldto.getBeanByLevelNo(modifiableList, i + 1);

			List<String> masterSids = (List<String>) dataList.get(0);
			List<Leveldto> levelBeanList = (List<Leveldto>) dataList.get(1);
			if (levelBeanList.isEmpty())
				continue;

			String tableName = levelBeanList.get(0).getTableName();
			String fieldName = levelBeanList.get(0).getFieldName();

			GtnFrameworkSingleColumnRelationBean keyListBean = masterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(tableName, fieldName);

			whereClauseFieldName = keyListBean.getWhereClauseColumn();
			queryBean.addWhereClauseBean(keyListBean.getActualTtableName() + "." + whereClauseFieldName, null,
					GtnFrameworkOperatorType.IN, GtnFrameworkDataType.LIST, masterSids);
		}
	}

	@SuppressWarnings("unchecked")
	private List<Leveldto> getRelationShipValuesByRelationShipBuilderSid(String relationShipBuilderId, int maxlevelNo,
			int relationVersionNo) {
		List<Object> input = new ArrayList<>();
		input.add(relationShipBuilderId);
		input.add(maxlevelNo);
		input.add(relationVersionNo);
		List<Object[]> resultsDataList = QueryUtils.getAppData(input, "selectRelationQueryByRelationShipandLevelNos",
				null);
		return customizeRelationData(resultsDataList);
	}

	public StringBuilder appendWhereCondition1(StringBuilder tempQuery) {
		StringBuilder whereQuery = new StringBuilder();
		if (!tempQuery.toString().isEmpty()) {
			whereQuery.append(Constant.WHERE_SPACE).append(tempQuery);
			int index = whereQuery.lastIndexOf("AND");
			whereQuery.delete(index, whereQuery.length());
		}
		return whereQuery;
	}

	private String buildQueryForUserDefinedLevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			int relationVersionNo) {
		List<Object> input = new ArrayList<>();
		input.add(relationshipSid);
		input.add(selectedHierarchyLevelDto.getLevelNo().toString());
		input.add(relationVersionNo);
		return QueryUtils.getQuery(input, "SelectValuesFromUserDefinedHierarchy");
	}

	private List<String> getRelationQueries(int relationshipSid, int relationVersionNo,
			Leveldto... levelHierarchyLevelDefinitionList) {
		List<String> queryList = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		for (Leveldto levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(levelDto.getLevelNo());
				input.add(relationshipSid);
				input.add(relationVersionNo);
				String relationQuery = QueryUtils.getQuery(input, "relationShipSubQuery");
				queryList.add(relationQuery);
				input.clear();
			}
		}
		return queryList;
	}

	public StringBuilder getRelationShipWhereCondition(int relationshipSid, String aliasName, int levelNo) {
		StringBuilder query = new StringBuilder();
		query.append(aliasName + ".RELATIONSHIP_BUILDER_SID =");
		query.append(relationshipSid);
		query.append(Constant.SPACE_AND_SPACE);
		query.append(aliasName + ".LEVEL_NO =");
		query.append(levelNo);
		return query;
	}

	private void getWhereQueryBasedOnHierarchyType(String hieType, List<String> groupFilteredCompanies,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		if (groupFilteredCompanies.isEmpty())
			return;
		if ("Customer Hierarchy".equals(hieType)) {
			finalQueryBean.addWhereClauseBean(COMPANY_MASTER_SID, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		} else {
			finalQueryBean.addWhereClauseBean(ITEM_MASTER_SID, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		}
	}

	/**
	 * To insert the selected customer and product hierarchy in CCP_HIERARCHY
	 * 
	 * @param tempTableNames
	 * @param productHierarchyLevelDefinitionList
	 * @param customerHierarchyLevelDefinitionList
	 * @param productRelationVersionNo
	 * @param customerRelationVersionNo
	 * @param dsDTO
	 * @param customerSelection
	 * @param productSelection
	 * @param isDataSelectionTab
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void ccpHierarchyInsert(final GtnSmallHashMap tempTableNames,
			final List<Leveldto> selectedCustomerContractList, final List<Leveldto> selectedProductList,
			List<Leveldto> customerHierarchyLevelDefinitionList, List<Leveldto> productHierarchyLevelDefinitionList,
			int customerRelationVersionNo, int productRelationVersionNo) {
		String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, "SELECTED_CUST_HIERARCHY_NO", Boolean.FALSE,
				customerRelationVersionNo);
		String productHierarchyQuery = getCustomerAndContractHierarchyQuery(selectedProductList,
				productHierarchyLevelDefinitionList, "SELECTED_PROD_HIERARCHY_NO", Boolean.TRUE,
				productRelationVersionNo);

		StringBuilder cusHieNoQuery = getHieNoQuery(customerHierarchyLevelDefinitionList, selectedCustomerContractList,
				customerRelationVersionNo);
		StringBuilder prodHieNoQuery = getHieNoQuery(productHierarchyLevelDefinitionList, selectedProductList,
				productRelationVersionNo);
		List<String> input = new ArrayList<>();
		input.add(cusHieNoQuery.toString());
		input.add(customerHierarchyQuery);
		input.add(prodHieNoQuery.toString());
		input.add(productHierarchyQuery);
		String withTableNameQuery = QueryUtil.replaceTableNames(QueryUtils.getQuery(input, "ccpInsertQuery"),
				tempTableNames);
		daoImpl.executeBulkUpdateQuery(withTableNameQuery, null, null);

	}

	/**
	 * To insert the selected customer and product hierarchy in CCP_HIERARCHY
	 * 
	 * @param tempTableNames
	 * @param productHierarchyLevelDefinitionList
	 * @param customerHierarchyLevelDefinitionList
	 * @param dedValue
	 * @param productRelationVersionNo
	 * @param customerRelationVersionNo
	 * @param dataSelectionDTO
	 * @param dsDTO
	 * @param customerSelection
	 * @param productSelection
	 * @param isDataSelectionTab
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void ccpHierarchyInsertARP(final GtnSmallHashMap tempTableNames,
			final List<Leveldto> selectedCustomerContractList, final List<Leveldto> selectedProductList,
			List<Leveldto> customerHierarchyLevelDefinitionList, List<Leveldto> productHierarchyLevelDefinitionList,
			int projectionId, String deductionLevel, String dedValue, int customerRelationVersionNo,
			int productRelationVersionNo) {
		String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, "SELECTED_CUST_HIERARCHY_NO", Boolean.FALSE,
				customerRelationVersionNo);
		String productHierarchyQuery = getCustomerAndContractHierarchyQuery(selectedProductList,
				productHierarchyLevelDefinitionList, "SELECTED_PROD_HIERARCHY_NO", Boolean.TRUE,
				productRelationVersionNo);
		StringBuilder cusHieNoQuery = getHieNoQuery(customerHierarchyLevelDefinitionList, selectedCustomerContractList,
				customerRelationVersionNo);
		StringBuilder prodHieNoQuery = getHieNoQuery(productHierarchyLevelDefinitionList, selectedProductList,
				productRelationVersionNo);
		List<Object> input = new ArrayList<>();
		input.add(cusHieNoQuery);
		input.add(customerHierarchyQuery);
		input.add(prodHieNoQuery);
		input.add(productHierarchyQuery);
		input.add(projectionId);
		input.add(deductionLevel);
		input.add(dedValue);

		String withTableNameQuery = QueryUtil.replaceTableNames(QueryUtils.getQuery(input, "ccpInsertQueryForARP"),
				tempTableNames);
		daoImpl.executeBulkUpdateQuery(withTableNameQuery, null, null);

	}

	private StringBuilder getHieNoQuery(List<Leveldto> customerHierarchyLevelDefinitionList,
			List<Leveldto> selectedCustomerContractList, int relationVersionNo) {
		String relationShipBuilderId = selectedCustomerContractList.get(0).getRelationShipBuilderId();
		int lastLevelNo = customerHierarchyLevelDefinitionList.get(customerHierarchyLevelDefinitionList.size() - 1)
				.getLevelNo();
		List<Leveldto> lastLevelCustomerHieararchy = getRelationShipValuesByRelationShipBuilderSid(
				relationShipBuilderId, lastLevelNo - 1, relationVersionNo);
		List<String> cusHierarchyNosList = Leveldto.getLastLevelHierarchyNo(lastLevelCustomerHieararchy, lastLevelNo);
		return getHierarchyQuery(cusHierarchyNosList);
	}

	private StringBuilder getHierarchyQuery(List<String> cusHierarchyNosList) {
		StringBuilder query = new StringBuilder();
		for (String hierarchyNo : cusHierarchyNosList) {
			query.append("('");
			query.append(hierarchyNo);
			query.append("'),");
		}
		query.deleteCharAt(query.length() - 1);
		return query;
	}

	private String getCustomerAndContractHierarchyQuery(List<Leveldto> selectedRelationLevelList,
			List<Leveldto> hierarchyLevelDefinitionList, String tempTableName, boolean isProduct,
			int relationVersionNo) {
		int relationSid = Integer.parseInt(selectedRelationLevelList.get(0).getRelationShipBuilderId());
		List<Object> input = new ArrayList<>();
		StringBuilder finalQuery = getParentHierarchyNo(hierarchyLevelDefinitionList,
				hierarchyLevelDefinitionList.get(hierarchyLevelDefinitionList.size() - 1));

		input.add(finalQuery);
		input.add(relationVersionNo);
		input.addAll(getRelationQueries(relationSid, relationVersionNo,
				hierarchyLevelDefinitionList.toArray(new Leveldto[hierarchyLevelDefinitionList.size()])));
		GtnFrameworkQueryGeneratorBean queryBean = getCustomerContractSidQuery(selectedRelationLevelList,
				hierarchyLevelDefinitionList, isProduct);
		queryBean.addSelectClauseBean("TEMP.HIERARCHY_NO", null, Boolean.TRUE, null);
		getParentHierarchyCondition(queryBean, tempTableName);
		getWhereQueryForCustomerAndContract(selectedRelationLevelList, hierarchyLevelDefinitionList, queryBean,
				relationVersionNo);
		return QueryUtils.getQuery(queryBean.generateQuery(), input);
	}

	private void getParentHierarchyCondition(GtnFrameworkQueryGeneratorBean queryBean, String tempTableName) {
		GtnFrameworkJoinClauseBean relationJoin = queryBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFN,
				RELATIONSHIP_LEVEL_DEFN, GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO", null,
				GtnFrameworkOperatorType.LIKE);
		GtnFrameworkJoinClauseBean hierarchyJoin = queryBean.addJoinClauseBean("#" + tempTableName, "TEMP",
				GtnFrameworkJoinType.JOIN);
		hierarchyJoin.addConditionBean(RELATIONSHIP_BUILD_HIERARCHY_NO, "TEMP.HIERARCHY_NO+'%'",
				GtnFrameworkOperatorType.LIKE);
		hierarchyJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO", null,
				GtnFrameworkOperatorType.EQUAL_TO);
	}

	public StringBuilder getParentHierarchyNo(List<Leveldto> customerHierarchyLevelDefinitionList,
			Leveldto selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			Leveldto leveldto = customerHierarchyLevelDefinitionList.get(i);
			if (leveldto.getTableName().isEmpty()) {
				query.append(",'%'");
				query.append(",'.'");
				continue;
			}
			query.append(",");
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = masterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			query.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
			query.append(",'.'");
		}
		finalQuery.append("concat( RELATIONSHIP_BUILDER_SID,'-'");
		finalQuery.append(query);
		finalQuery.append(")");
		return finalQuery;
	}

	public Map<String, String> getLevelValueMap1(Object relationshipBuilderSID, int hierarchyBuilderSid,
			int hierarchyVersionNo) throws CloneNotSupportedException {
		Map<String, Object> input = new HashMap<>();
		input.put("?RBSID", relationshipBuilderSID);
		List<Leveldto> hierarchyLevelDefinitionList = getHierarchyLevelDefinition(hierarchyBuilderSid,
				hierarchyVersionNo);
		Map<String, String> relationMap = new HashMap<>();
		List<Leveldto> levelHierarchyLevelDefinitionList;
		for (Leveldto leveldto : hierarchyLevelDefinitionList) {
			levelHierarchyLevelDefinitionList = hierarchyLevelDefinitionList.subList(0, leveldto.getLevelNo());
			List<Leveldto> data = loadAvailableCustomerlevel(leveldto,
					Integer.valueOf(relationshipBuilderSID.toString()), Collections.<String>emptyList(),
					levelHierarchyLevelDefinitionList, StringUtils.EMPTY, StringUtils.EMPTY, 1);
			for (Leveldto leveldto2 : data) {
				relationMap.put(leveldto2.getHierarchyNo(), leveldto2.getLevel());
			}

		}
		return relationMap;
	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID, int hierarchyBuilderSid,
			int hierarchyVersionNo, int relationVersionNo) {
		List<Leveldto> hierarchyLevelDefinitionList = getHierarchyLevelDefinition(hierarchyBuilderSid,
				hierarchyVersionNo);
		Map<String, String> relationMap = new HashMap<>();
		List<GtnFrameworkQueryGeneratorBean> queryBeanList = new ArrayList<>();
		StringBuilder finalQuery = new StringBuilder();
		for (Leveldto leveldto : hierarchyLevelDefinitionList) {
			if (leveldto.isUserDefined()) {
				List<Object> input = new ArrayList<>();
				input.add(relationshipBuilderSID);
				input.add(relationVersionNo);
				finalQuery.append(QueryUtils.getQuery(input, "RelationShipUserDefinedLoading"));
				finalQuery.append(" union ");
				continue;
			}

			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			GtnFrameworkSingleColumnRelationBean keyBean = masterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			queryBean.addSelectClauseBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, Boolean.TRUE, null);
			queryBean.addSelectClauseBean(keyBean.getJoinColumnTable() + "." + keyBean.getDescriptionClauseColumn(),
					null, Boolean.TRUE, null);
			queryBean.setFromTableNameWithAlies("RELATIONSHIP_LEVEL_DEFINITION", "RELATIONSHIP_LEVEL_DEFINITION");
			GtnFrameworkJoinClauseBean tableJoin = queryBean.addJoinClauseBean(keyBean.getJoinColumnTable(),
					keyBean.getJoinColumnTable(), GtnFrameworkJoinType.JOIN);
			tableJoin.addConditionBean(keyBean.getJoinColumnTable() + "." + keyBean.getMasterSidColumn(),
					"RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES", GtnFrameworkOperatorType.EQUAL_TO);
			queryBean.addWhereClauseBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID", null,
					GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.STRING, relationshipBuilderSID);
			queryBean.addWhereClauseBean("RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO", null,
					GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER, leveldto.getLevelNo());
			queryBean.addWhereClauseBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO,
					GtnFrameworkDataType.INTEGER, relationVersionNo);
			queryBeanList.add(queryBean);

		}

		for (GtnFrameworkQueryGeneratorBean queryBean : queryBeanList) {
			finalQuery.append(queryBean.generateQuery());
			finalQuery.append(" union ");
		}

		finalQuery.replace(finalQuery.lastIndexOf("union"), finalQuery.length() - 1, "");
		List<Object[]> list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
		for (Object[] leveldto2 : list) {
			if (leveldto2[0] != null && leveldto2[1] != null)
				relationMap.put(leveldto2[0].toString(), leveldto2[1].toString());
		}
		return relationMap;
	}

	/**
	 * Gets the relation ship values.
	 *
	 * @param projectionId
	 * @param isCustomer
	 * @return the relation ship values
	 */
	@SuppressWarnings("unchecked")
	public List<Leveldto> getRelationShipValues(final int projectionId, final boolean isCustomer, final Object levelNo,
			final Map<String, String> descriptionMap) {

		List<Leveldto> resultList;
		String queryName;
		List<Object> input = new ArrayList<>();
		input.add(projectionId);
		if (levelNo == null) {
			input.add(" ");
		} else {
			input.add(" AND RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO <= '" + levelNo + "'");
		}
		if (isCustomer) {
			queryName = "selectCustomerQueryForViewCustomization";
		} else {
			queryName = "selectProductQueryForViewCustomization";
		}

		List<Object[]> resultDataList = QueryUtils.getAppData(input, queryName, null);
		resultList = customizeRelationAndHierarchyData(descriptionMap, resultDataList);
		return resultList;
	}

	public List<Leveldto> customizeRelationAndHierarchyData(final Map<String, String> descriptionMap,
			List<Object[]> resultDataList) {
		List<Leveldto> resultList = new ArrayList<>();
		for (Object[] objects : resultDataList) {
			Leveldto levelDto = new Leveldto();
			levelDto.setLevelNo(getIntegerValue(objects, 0));
			levelDto.setLevelValueReference(getStringValue(objects, 1));
			levelDto.setTableName(getStringValue(objects, 2));
			levelDto.setFieldName(getStringValue(objects, 3));
			levelDto.setLevel(getStringValue(objects, 4));
			levelDto.setHierarchyLevelDefnId(getStringValue(objects, 5));
			levelDto.setHierarchyId(getIntegerValue(objects, 6));
			levelDto.setRelationshipLevelValue(String.valueOf(objects[7]));
			levelDto.setParentNode(String.valueOf(objects[NumericConstants.EIGHT]));
			levelDto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.NINE])));
			levelDto.setHierarchyNo(String.valueOf(objects[NumericConstants.TEN]));
			levelDto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.ELEVEN]));
			if (descriptionMap != null) {
				levelDto.setLevel(descriptionMap.get(levelDto.getHierarchyNo()));
				levelDto.setDisplayValue(descriptionMap.get(levelDto.getHierarchyNo()));
			}
			resultList.add(levelDto);

		}
		return resultList;
	}

	public String getStringValue(Object[] objects, int index) {
		return objects[index] == null ? "" : objects[index].toString();
	}

	public Integer getIntegerValue(Object[] objects, int index) {
		return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
	}

	public List<Leveldto> customizeRelationData(List<Object[]> resultDataList) {
		List<Leveldto> resultList = new ArrayList<>();
		for (Object[] objects : resultDataList) {
			Leveldto levelDto = new Leveldto();
			levelDto.setLevelNo(getIntegerValue(objects, 0));
			levelDto.setLevelValueReference(getStringValue(objects, 1));
			levelDto.setTableName(objects[2] == null ? "" : objects[2].toString());
			levelDto.setFieldName(objects[3] == null ? "" : objects[3].toString());
			levelDto.setLevel(objects[4] == null ? "" : objects[4].toString());
			levelDto.setHierarchyLevelDefnId(objects[5] == null ? "0" : objects[5].toString());
			levelDto.setHierarchyId((Integer.valueOf(objects[6] == null ? "0" : objects[6].toString())));
			levelDto.setRelationshipLevelValue(String.valueOf(objects[7]));
			levelDto.setParentNode(String.valueOf(objects[NumericConstants.EIGHT]));
			levelDto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.NINE])));
			levelDto.setHierarchyNo(String.valueOf(objects[NumericConstants.TEN]));
			levelDto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.ELEVEN]));
			resultList.add(levelDto);

		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getVersionNoList(Object selectedProductRelation) {
		int relationShipBuilderSid = Integer.parseInt(selectedProductRelation.toString());
		List<Integer> input = new ArrayList<>();
		input.add(relationShipBuilderSid);
		return (List<Object[]>) QueryUtils.getAppData(input, "getRelationshipVersionNo", null);
	}

	public String getChildLevelQueryForProduct(Leveldto selectedHierarchyLevelDto, int relationVersionNo,
			String businessUnitValue, int lowestLevelNo, int subListIndex) {
		GtnFrameworkHierarchyQueryBean queryBean = fileReadWriteService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyId(), selectedHierarchyLevelDto.getHierarchyLevelDefnId(),
				selectedHierarchyLevelDto.getHierarchyVersionNo());
		List<Leveldto> hierarchyLevelDefinitionList = getHierarchyLevelDefinition(
				selectedHierarchyLevelDto.getHierarchyId(), selectedHierarchyLevelDto.getHierarchyVersionNo());
		List<String> input = new ArrayList<>();
		input.add(String.valueOf(relationVersionNo));
		input.add("'" + selectedHierarchyLevelDto.getHierarchyNo() + "'");
		input.add(String.valueOf(relationVersionNo));
		input.add(String.valueOf(lowestLevelNo + 1));
		input.add("'" + selectedHierarchyLevelDto.getHierarchyNo() + "'");
		List<String> whereQuerys = getRelationQueries(
				Integer.parseInt(selectedHierarchyLevelDto.getRelationShipBuilderId()), relationVersionNo,
				hierarchyLevelDefinitionList.toArray(new Leveldto[hierarchyLevelDefinitionList.size()]));
		if (!whereQuerys.isEmpty()) {
			input.addAll(whereQuerys.subList(0, subListIndex - 1));
		}
		GtnFrameworkQueryGeneratorBean finalQueryBean = queryBean.getQuery();
		finalQueryBean.removeSelectClauseByIndex(0);
		finalQueryBean.removeSelectClauseByIndex(0);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.LEVEL_NO", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.RELATIONSHIP_LEVEL_VALUES", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.PARENT_NODE", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.LEVEL_NAME", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_LEVEL_DEFINITION.LEVEL_VALUE_REFERENCE", null, Boolean.TRUE,
				null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_LEVEL_DEFINITION.TABLE_NAME", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_LEVEL_DEFINITION.FIELD_NAME", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.RELATIONSHIP_LEVEL_SID", null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean(RELATION_HIERARCHY_JOIN, null, Boolean.TRUE, null);
		finalQueryBean.addSelectClauseBean("HIERARCHY_NO_JOIN.RELATIONSHIP_BUILDER_SID", null, Boolean.TRUE, null);
		GtnFrameworkJoinClauseBean relationTableJoin = finalQueryBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFN,
				RELATIONSHIP_LEVEL_DEFN, GtnFrameworkJoinType.JOIN);
		GtnFrameworkSingleColumnRelationBean keyBean = masterBean.getKeyRelationBeanUsingTableIdAndColumnName(
				selectedHierarchyLevelDto.getTableName(), selectedHierarchyLevelDto.getFieldName());
		relationTableJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES",
				keyBean.getActualTtableName() + "." + keyBean.getWhereClauseColumn(),
				GtnFrameworkOperatorType.EQUAL_TO);
		relationTableJoin.addConditionBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO);
		relationTableJoin.addConditionBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, GtnFrameworkOperatorType.LIKE);
		GtnFrameworkJoinClauseBean hierarchyTableJoin = finalQueryBean.addJoinClauseBean(RELATIONSHIP_LEVEL_DEFN,
				"HIERARCHY_NO_JOIN", GtnFrameworkJoinType.JOIN);
		hierarchyTableJoin.addConditionBean(RELATION_HIERARCHY_JOIN, "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO+'%'",
				GtnFrameworkOperatorType.LIKE);
		hierarchyTableJoin.addConditionBean("HIERARCHY_NO_JOIN.VERSION_NO", null, GtnFrameworkOperatorType.EQUAL_TO);
		hierarchyTableJoin.addConditionBean("HIERARCHY_NO_JOIN.LEVEL_NO", null, GtnFrameworkOperatorType.LESSTHAN);
		hierarchyTableJoin.addConditionBean(RELATION_HIERARCHY_JOIN, null, GtnFrameworkOperatorType.NOT_IN);
		GtnFrameworkJoinClauseBean hierarchyLevelDefnTableJoin = finalQueryBean.addJoinClauseBean(
				"HIERARCHY_LEVEL_DEFINITION", "HIERARCHY_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		hierarchyLevelDefnTableJoin.addConditionBean("HIERARCHY_LEVEL_DEFINITION.HIERARCHY_LEVEL_DEFINITION_SID",
				"HIERARCHY_NO_JOIN.HIERARCHY_LEVEL_DEFINITION_SID", GtnFrameworkOperatorType.EQUAL_TO);
		finalQueryBean.addWhereClauseBean("ITEM_MASTER.ORGANIZATION_KEY", null, GtnFrameworkOperatorType.EQUAL_TO,
				GtnFrameworkDataType.STRING, businessUnitValue);
                finalQueryBean.addOrderByClauseBean("HIERARCHY_NO_JOIN.LEVEL_NO", "ASC");
		return QueryUtils.getQuery(finalQueryBean.generateQuery(), input);
	}

	public void checkAndUpdateHierarchy(DataSelectionDTO dto) {
		GtnWsForecastHierarchyInsertRequest forecastRequest = new GtnWsForecastHierarchyInsertRequest();
		forecastRequest.setUserId(forecastRequest.getUserId());
		forecastRequest.setSessionId(forecastRequest.getSessionId());
		forecastRequest.setProjectionMasterSid(dto.getProjectionId());
		forecastRequest.setCustHierarchySid(Integer.parseInt(dto.getCustomerHierSid()));
		forecastRequest.setCustRelationShipBuilderSid(Integer.parseInt(dto.getCustRelationshipBuilderSid()));
		forecastRequest.setProjectionHierarchyCustVersionNo(dto.getCustomerHierVersionNo());
		forecastRequest.setProjectionRelationshipCustVersionNo(dto.getCustomerRelationShipVersionNo());

		forecastRequest.setProdHierarchySid(Integer.parseInt(dto.getProdHierSid()));
		forecastRequest.setProdRelationShipBuilderSid(Integer.parseInt(dto.getProdRelationshipBuilderSid()));
		forecastRequest.setProjectionHierarchyProdVersionNo(dto.getProductHierVersionNo());
		forecastRequest.setProjectionRelationshipProdVersionNo((dto.getProductRelationShipVersionNo()));

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWshirarchyInsertRequest(forecastRequest);
		client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_EDIT_CUSTHIERARCHY_INSERT,
				request, getGsnWsSecurityToken());

	}

	private static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		String userId = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}
}
