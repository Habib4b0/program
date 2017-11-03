package com.stpl.app.cff.ui.dataSelection.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.service.FileReadWriteService;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;

public class RelationShipFilterLogic {

	private GtnFrameworkEntityMasterBean masterBean = GtnFrameworkEntityMasterBean.getInstance();
	private static final RelationShipFilterLogic instance = new RelationShipFilterLogic();
	private CommonDAO daoImpl = new CommonDAOImpl();
	private FileReadWriteService fileReadWriteService = new FileReadWriteService();
        private static final String ITEM_MASTER_TABLE_COLUMN_NAME = "ITEM_MASTER.ITEM_MASTER_SID";

	private RelationShipFilterLogic() {
		// Singleton constructor
	}

	public static RelationShipFilterLogic getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getHierarchyLevelDefinition(int hierarchyDefinitionSid) {
		List<String> input = new ArrayList<>();
		List<Leveldto> resultDtoList;
		input.add(String.valueOf(hierarchyDefinitionSid));
		List<Object[]> results = CommonQueryUtils.getAppData(input, "getHierarchyLevelVlaues", null);
		resultDtoList = cutomizeHierarchyBean(results);
		Collections.sort(resultDtoList);
		return resultDtoList;
	}

	public List<Leveldto> cutomizeHierarchyBean(List<Object[]> results) {
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
			resultDtoList.add(levelDto);
		}
		return resultDtoList;
	}

	public List<Leveldto> loadAvailableCustomerlevel(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList)
			throws CloneNotSupportedException, ClassNotFoundException, IOException {
		if (selectedHierarchyLevelDto.isUserDefined()) {
			return getUserDefinedData(selectedHierarchyLevelDto, relationshipSid);
		}
		return getLinkedLevelData(selectedHierarchyLevelDto, relationshipSid, groupFilteredCompanies,
				levelHierarchyLevelDefinitionList);
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getLinkedLevelData(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList)
			throws CloneNotSupportedException, ClassNotFoundException, IOException {
		List<Leveldto> resultList = new ArrayList<>();
		String finalQuery = getQueryForLinkedLevelCustomer(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredCompanies, levelHierarchyLevelDefinitionList);
		CommonDAOImpl commonDao = new CommonDAOImpl();
		List<Object[]> resultsRelationList = getRelationshipList(selectedHierarchyLevelDto, relationshipSid);
		List<Object[]> resultsDataList = (List<Object[]>) commonDao.executeSelectQuery(finalQuery, null, null);
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
			List<String> groupFilteredCompanies, List<Leveldto> levelHierarchyLevelDefinitionList)
			throws ClassNotFoundException, IOException {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(selectedHierarchyLevelDto,
				groupFilteredCompanies);
		List<String> whereQueries = getRelationQueries(relationshipSid,
				levelHierarchyLevelDefinitionList.toArray(new Leveldto[levelHierarchyLevelDefinitionList.size()]));
		return CommonQueryUtils.getQuery(queryBean.generateQuery(), whereQueries);
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
		return (List<Object[]>) CommonQueryUtils.getAppData(relationInput, "selectQueryForLevelLoadingForRelation",
				null);
	}

	@SuppressWarnings("unchecked")
	public List<Leveldto> getUserDefinedData(Leveldto selectedHierarchyLevelDto, int relationshipSid)
			throws CloneNotSupportedException {
		String finalQuery = buildQueryForUserDefinedLevel(selectedHierarchyLevelDto, relationshipSid);
		List<Leveldto> resultList = new ArrayList<>();
		CommonDAOImpl commonDao = new CommonDAOImpl();
		List<Object[]> resultsDataList = (List<Object[]>) commonDao.executeSelectQuery(finalQuery, null, null);
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
			List<Leveldto> levelHierarchyLevelDefinitionList, List<Leveldto> customerHierarchyLevelList)
			throws CloneNotSupportedException, ClassNotFoundException, IOException {

		if (selectedHierarchyLevelDto.isUserDefined()) {
			return getUserDefinedData(selectedHierarchyLevelDto, relationshipSid);
		}
		return getLinkedLevelDataForProduct(selectedHierarchyLevelDto, relationshipSid, groupFilteredItems,
				selectedCustomerContractList, isNdc, levelHierarchyLevelDefinitionList, customerHierarchyLevelList);

	}

	@SuppressWarnings("unchecked")
	private List<Leveldto> getLinkedLevelDataForProduct(Leveldto selectedHierarchyLevelDto, int relationshipSid,
			List<String> groupFilteredItems, List<Leveldto> selectedCustomerContractList, boolean isNdc,
			List<Leveldto> levelHierarchyLevelDefinitionList, List<Leveldto> customerHierarchyLevelList)
			throws CloneNotSupportedException, ClassNotFoundException, IOException {

		List<Leveldto> resultList = new ArrayList<>();
		String finalQuery = buildQueryForLinkedLevelForProduct(selectedHierarchyLevelDto, relationshipSid,
				groupFilteredItems, selectedCustomerContractList, isNdc, levelHierarchyLevelDefinitionList,
				customerHierarchyLevelList);
		CommonDAOImpl commonDao = new CommonDAOImpl();
		List<Object[]> resultsRelationList = getRelationshipList(selectedHierarchyLevelDto, relationshipSid);
		List<Object[]> resultsDataList = (List<Object[]>) commonDao.executeSelectQuery(finalQuery, null, null);
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
			List<Leveldto> levelHierarchyLevelDefinitionList, List<Leveldto> customerHierarchyLevelList)
			throws ClassNotFoundException, IOException {
		GtnFrameworkQueryGeneratorBean queryBean = getQueryForLinkedLevel(selectedHierarchyLevelDto,
				groupFilteredItems);
		List<String> whereQueries = getRelationQueries(relationshipSid,
				levelHierarchyLevelDefinitionList.toArray(new Leveldto[levelHierarchyLevelDefinitionList.size()]));
		getRscontractJoinForProduct(queryBean);
		addNdcSelectAndJoin(isNdc, queryBean);
		List<Set> sidList = getCustomerConractSid(selectedCustomerContractList, customerHierarchyLevelList);
		getWhereQueryForProductLevel(sidList, queryBean);
		return CommonQueryUtils.getQuery(queryBean.generateQuery(), whereQueries);
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
			queryBean.addSelectClauseBean("STr.DESCRIPTION");
			queryBean.addSelectClauseBean("FORm.DESCRIPTION");

		}
	}

	public void getRscontractJoinForProduct(GtnFrameworkQueryGeneratorBean queryBean) {
		GtnFrameworkJoinClauseBean ccpJoin = queryBean.addJoinClauseBean("CCP_DETAILS", "CCP_DETAILS",
				GtnFrameworkJoinType.JOIN);
		ccpJoin.addConditionBean(ITEM_MASTER_TABLE_COLUMN_NAME, "CCP_DETAILS.ITEM_MASTER_SID",
				GtnFrameworkOperatorType.EQUAL_TO);
	}

	public void replaceQuery(String joinQuery, String beforeText, StringBuilder query) {
		int whereIndex = query.indexOf(beforeText);
		query.replace(whereIndex, whereIndex, joinQuery);
	}

	public GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(Leveldto selectedHierarchyLevelDto,
			List<String> groupFilteredItems) {
		StringBuilder query = new StringBuilder();
		GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyId(), selectedHierarchyLevelDto.getHierarchyLevelDefnId());
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
						GtnFrameworkDataType.LIST, customerSidSet);
			}
			if (!contractSidSet.isEmpty()) {
				queryBean.addWhereClauseBean("CCP_DETAILS.CONTRACT_MASTER_SID", null, GtnFrameworkOperatorType.IN,
						GtnFrameworkDataType.LIST, customerSidSet);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Set> getCustomerConractSid(List<Leveldto> selectedCustomerContractList,
			List<Leveldto> customerHierarchyLevelDefinitionList) {
		GtnFrameworkQueryGeneratorBean queryBean = getCustomerContractSidQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, Boolean.FALSE);
		if (queryBean == null) {
			return Collections.emptyList();
		}
		List<Set> finalList = new ArrayList<>();
		Set<Integer> customerSidSet = new HashSet<>();
		Set<Integer> contractSidSet = new HashSet<>();
		List<Object[]> results = (List<Object[]>) daoImpl.executeSelectQuery(queryBean.generateQuery(), null, null);
		for (Object[] object : results) {
			customerSidSet.add(getIntegerValue(object, 2));
			contractSidSet.add(getIntegerValue(object, 3));
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
		if (isProduct) {
			queryBean.addSelectClauseBean(ITEM_MASTER_TABLE_COLUMN_NAME);
			return queryBean;
		}
		queryBean.addSelectClauseBean("COMPANY_MASTER.COMPANY_MASTER_SID");
		queryBean.addSelectClauseBean("CONTRACT_MASTER.CONTRACT_MASTER_SID");
		return queryBean;
	}

	private void getWhereQueryForCustomerAndContract(List<Leveldto> selectedCustomerContractList,
			List<Leveldto> customerHierarchyLevelDefinitionList, GtnFrameworkQueryGeneratorBean queryBean) {
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
					selectedCustomerContractList.get(0).getRelationShipBuilderId(), maxlevelNo);
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
	private List<Leveldto> getRelationShipValuesByRelationShipBuilderSid(String relationShipBuilderId, int maxlevelNo) {
		List<Object> input = new ArrayList<>();
		input.add(relationShipBuilderId);
		input.add(maxlevelNo);
		List<Object[]> resultsDataList = CommonQueryUtils.getAppData(input,
				"selectRelationQueryByRelationShipandLevelNos", null);
		return customizeRelationData(resultsDataList);
	}

	private String buildQueryForUserDefinedLevel(Leveldto selectedHierarchyLevelDto, int relationshipSid) {
		List<Object> input = new ArrayList<>();
		input.add(relationshipSid);
		input.add(selectedHierarchyLevelDto.getLevelNo().toString());
		return CommonQueryUtils.getAppQuery(input, "SelectValuesFromUserDefinedHierarchy");
	}

	private List<String> getRelationQueries(int relationshipSid, Leveldto... levelHierarchyLevelDefinitionList) {
		List<String> queryList = new ArrayList<>();
		List<Object> input = new ArrayList<>();
		for (Leveldto levelDto : levelHierarchyLevelDefinitionList) {
			if (!levelDto.isUserDefined()) {
				input.add(levelDto.getLevelNo());
				input.add(relationshipSid);
				String relationQuery = CommonQueryUtils.getAppQuery(input, "relationShipSubQuery");
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
		query.append(StringConstantsUtil.SPACE_AND_SPACE);
		query.append(aliasName + ".LEVEL_NO =");
		query.append(levelNo);
		return query;
	}

	private void getWhereQueryBasedOnHierarchyType(String hieType, List<String> groupFilteredCompanies,
			GtnFrameworkQueryGeneratorBean finalQueryBean) {
		if (groupFilteredCompanies.isEmpty())
			return;
		if ("Customer Hierarchy".equals(hieType)) {
			finalQueryBean.addWhereClauseBean("COMPANY_MASTER.COMPANY_MASTER_SID", null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		} else {
			finalQueryBean.addWhereClauseBean(ITEM_MASTER_TABLE_COLUMN_NAME, null, GtnFrameworkOperatorType.IN,
					GtnFrameworkDataType.LIST, groupFilteredCompanies);
		}
	}

	/**
	 * To insert the selected customer and product hierarchy in CCP_HIERARCHY
	 * 
	 * @param tempTableNames
	 * @param productHierarchyLevelDefinitionList
	 * @param customerHierarchyLevelDefinitionList
	 * @param dsDTO
	 * @param customerSelection
	 * @param productSelection
	 * @param isDataSelectionTab
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void ccpHierarchyInsert(final Map<String, String> tempTableNames,
			final List<Leveldto> selectedCustomerContractList, final List<Leveldto> selectedProductList,
			List<Leveldto> customerHierarchyLevelDefinitionList, List<Leveldto> productHierarchyLevelDefinitionList)
			throws ClassNotFoundException, IOException {
		String customerHierarchyQuery = getCustomerAndContractHierarchyQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, "SELECTED_CUST_HIERARCHY_NO", Boolean.FALSE);
		String productHierarchyQuery = getCustomerAndContractHierarchyQuery(selectedProductList,
				productHierarchyLevelDefinitionList, "SELECTED_PROD_HIERARCHY_NO", Boolean.TRUE);

		StringBuilder cusHieNoQuery = getHieNoQuery(customerHierarchyLevelDefinitionList, selectedCustomerContractList);
		StringBuilder prodHieNoQuery = getHieNoQuery(productHierarchyLevelDefinitionList, selectedProductList);
		List<String> input = new ArrayList<>();
		input.add(cusHieNoQuery.toString());
		input.add(customerHierarchyQuery);
		input.add(prodHieNoQuery.toString());
		input.add(productHierarchyQuery);
		String withTableNameQuery = QueryUtil.replaceTableNames(CommonQueryUtils.getAppQuery(input, "ccpInsertQuery"),
				tempTableNames);
		daoImpl.executeBulkUpdateQuery(withTableNameQuery, null, null);

	}

	private StringBuilder getHieNoQuery(List<Leveldto> customerHierarchyLevelDefinitionList,
			List<Leveldto> selectedCustomerContractList) {
		String relationShipBuilderId = selectedCustomerContractList.get(0).getRelationShipBuilderId();
		int lastLevelNo = customerHierarchyLevelDefinitionList.get(customerHierarchyLevelDefinitionList.size() - 1)
				.getLevelNo();
		List<Leveldto> lastLevelCustomerHieararchy = getRelationShipValuesByRelationShipBuilderSid(
				relationShipBuilderId, lastLevelNo - 1);
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

	private String getCustomerAndContractHierarchyQuery(List<Leveldto> selectedCustomerContractList,
			List<Leveldto> customerHierarchyLevelDefinitionList, String tempTableName, boolean isProduct) {
		int relationSid = Integer.parseInt(selectedCustomerContractList.get(0).getRelationShipBuilderId());
		List<Object> input = new ArrayList<>();
		StringBuilder finalQuery = getParentHierarchyNo(customerHierarchyLevelDefinitionList,
				customerHierarchyLevelDefinitionList.get(customerHierarchyLevelDefinitionList.size() - 1));
		input.add(finalQuery);
		input.addAll(getRelationQueries(relationSid, customerHierarchyLevelDefinitionList
				.toArray(new Leveldto[customerHierarchyLevelDefinitionList.size()])));
		GtnFrameworkQueryGeneratorBean queryBean = getCustomerContractSidQuery(selectedCustomerContractList,
				customerHierarchyLevelDefinitionList, isProduct);
		queryBean.addSelectClauseBean("TEMP.HIERARCHY_NO");
		getParentHierarchyCondition(queryBean, tempTableName);
		getWhereQueryForCustomerAndContract(selectedCustomerContractList, customerHierarchyLevelDefinitionList,
				queryBean);
		return CommonQueryUtils.getQuery(queryBean.generateQuery(), input);
	}

	private void getParentHierarchyCondition(GtnFrameworkQueryGeneratorBean queryBean, String tempTableName) {
		GtnFrameworkJoinClauseBean relationJoin = queryBean.addJoinClauseBean("RELATIONSHIP_LEVEL_DEFINITION",
				"RELATIONSHIP_LEVEL_DEFINITION", GtnFrameworkJoinType.JOIN);
		relationJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.PARENT_HIERARCHY_NO", null,
				GtnFrameworkOperatorType.LIKE);
		GtnFrameworkJoinClauseBean hierarchyJoin = queryBean.addJoinClauseBean("#" + tempTableName, "TEMP",
				GtnFrameworkJoinType.JOIN);
		hierarchyJoin.addConditionBean("RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO", "TEMP.HIERARCHY_NO+'%'",
				GtnFrameworkOperatorType.LIKE);
	}

	public StringBuilder getParentHierarchyNo(List<Leveldto> customerHierarchyLevelDefinitionList,
			Leveldto selectedCustomerHierarchyLevelDto) {
		StringBuilder query = new StringBuilder();
		StringBuilder finalQuery = new StringBuilder();
		for (int i = 0; i < selectedCustomerHierarchyLevelDto.getLevelNo(); i++) {
			Leveldto leveldto = customerHierarchyLevelDefinitionList.get(i);
			query.append(",'~',");
			if (leveldto.getTableName().isEmpty()) {
				query.append("'%'");

				continue;
			}
			GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = masterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			query.append(singleColumnRelationBean.getActualTtableName() + "."
					+ singleColumnRelationBean.getWhereClauseColumn());
		}
		finalQuery.append("concat( RELATIONSHIP_BUILDER_SID");
		finalQuery.append(query);
		finalQuery.append(")");
		return finalQuery;
	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID, int hierarchyBuilderSid)
			throws CloneNotSupportedException, IOException, ClassNotFoundException {
		Map<String, Object> input = new HashMap<>();
		input.put("?RBSID", relationshipBuilderSID);
		List<Leveldto> hierarchyLevelDefinitionList = getHierarchyLevelDefinition(hierarchyBuilderSid);
		Map<String, String> relationMap = new HashMap<>();
		List<Leveldto> levelHierarchyLevelDefinitionList;
		for (Leveldto leveldto : hierarchyLevelDefinitionList) {
			levelHierarchyLevelDefinitionList = hierarchyLevelDefinitionList.subList(0, leveldto.getLevelNo());
			List<Leveldto> data = loadAvailableCustomerlevel(leveldto,
					Integer.valueOf(relationshipBuilderSID.toString()), Collections.<String>emptyList(),
					levelHierarchyLevelDefinitionList);
			for (Leveldto leveldto2 : data) {
				relationMap.put(leveldto2.getHierarchyNo(), leveldto2.getLevel());
			}

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

		List<Object[]> resultDataList = CommonQueryUtils.getAppData(input, queryName, null);
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

}
