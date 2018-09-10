/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.DATA_SELECTION_LANDING_SCREEN;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getGovernmentConstant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.displayformat.main.RelationshipLevelValuesMasterBean;
import com.stpl.app.gtnforecasting.dto.CompanyDdlbDto;
import com.stpl.app.gtnforecasting.dto.RelationshipDdlbDto;
import static com.stpl.app.gtnforecasting.logic.RelationShipFilterLogic.getGsnWsSecurityToken;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractFilterLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.app.utils.Constants.IndicatorConstants;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TreeTable;
import java.util.Objects;
import org.asi.ui.extfilteringtable.ExtFilterTable;
/**
 * The Class DataSelectionLogic.
 *
 * @author soundarrajan
 */
@SuppressWarnings("unchecked")
public class DataSelectionLogic {

	/**
	 * The data selection dao.
	 */
	private final DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
	public static final String RBSID = "?RBSID";
	public static final String RBVERSION = "?RBVERSION";
	public static final String SELECT_CAPS = " SELECT ";
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionLogic.class);
	private int discountDdlbCount = 0;
	private final CommonDAO salesProjectionDAO = new CommonDAOImpl();
	public static final String LEVEL_NO = "levelNo";
	public static final String RELATIONSHIP_SID = "relationshipSid";
	public static final String BUSINESS_UNIT_PROPERTY = "businessUnit";
	public static final String SELECTION_AT = "@SELECTION";
	private List companiesList = new ArrayList<>();
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();
	private static final CommonUtil commonUtil = CommonUtil.getInstance();
	public static final String EXEC_WITH_SPACE = "EXEC ";
	private static final String QUERY_CALL_VIEW_INSERT_PROCEDURES = "Query callViewInsertProcedures: {}";
	private static final String CUSTOMER_SID_LITERAL = "custSid";
	private static final String CUSTVER = "custVer";
	private static final String PROD_SID_LITERAL = "prodSid";
	private static final String CUSTOMER_SID_VARIABLE = "@CUSTSID";
	private static final String PRODUCT_SID_VARIABLE = "@PRODSID";
	private static final String PRODVER = "prodVer";
	private static final String CUSTVER_VARIABLE = "@CUSTVER";
	private static final String PRODVER_VARIABLE = "@PRODVER";
	public static final String SALES_SMALL = "Sales";
	private static final String UOM = "UOM";

	/**
	 * Gets the hierarchy group.
	 *
	 * @param hierarchyName
	 *            the hierarchy name
	 * @param hierarchyType
	 *            the hierarchy type
	 * @param customerOrProduct
	 *            the customer or product
	 * @param action
	 *            the action
	 * @return the hierarchy group
	 */
	public List<HierarchyLookupDTO> getHierarchyGroup(String hierarchyName, String hierarchyType,
			final String customerOrProduct, final String action) throws ParseException {

		List<HierarchyLookupDTO> resultList = new ArrayList<>();

		String tempHierarchyName = hierarchyName;
		if (tempHierarchyName.contains(String.valueOf(CommonUtils.CHAR_ASTERISK))) {
			tempHierarchyName = tempHierarchyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		}
		String tempHierarchyType = hierarchyType;
		if (tempHierarchyType.contains(String.valueOf(CommonUtils.CHAR_ASTERISK))) {
			tempHierarchyType = tempHierarchyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		}

		List resList = dataSelectionDao.getHierarchyGroup(tempHierarchyName, tempHierarchyType, customerOrProduct,
				action);

		HierarchyLookupDTO hierDto;
		for (int i = 0; i < resList.size(); i++) {
			hierDto = new HierarchyLookupDTO();
			final Object[] obj = (Object[]) resList.get(i);
			hierDto.setHierarchyId(Integer.parseInt(obj[0].toString()));
			hierDto.setHierarchyName(String.valueOf(obj[1].toString()));
			hierDto.setHighestLevel(String.valueOf(obj[NumericConstants.THREE].toString()));
			hierDto.setLowestLevel(String.valueOf(obj[NumericConstants.FOUR].toString()));
			hierDto.setCreatedDate(String.valueOf(obj[NumericConstants.FIVE].toString()));
			hierDto.setCreatedDateSearch(Converters.parseDate(String.valueOf(obj[NumericConstants.FIVE].toString())));
			if (obj[NumericConstants.SIX] != null) {
				hierDto.setModifiedDate(String.valueOf(obj[NumericConstants.SIX].toString()));
				hierDto.setModifiedDateSearch(
						Converters.parseDate(String.valueOf(obj[NumericConstants.SIX].toString())));
			}
			resultList.add(hierDto);

		}

		return resultList;
	}

	/**
	 * Load customer forecast level.
	 *
	 * @param hierarchyId
	 *            the hierarchy id
	 * @param hierarchyName
	 *            the hierarchy name
	 * @return the list
	 */
	public List<Leveldto> loadCustomerForecastLevel(int hierarchyId, String hierarchyName, int hierarchyVersion) {
		LOGGER.debug(" hierarchyName=  {}", hierarchyName);
		List<Leveldto> resultList = new ArrayList<>();
		Leveldto leveldto;
		List<Object> input = new ArrayList<>();
		input.add(hierarchyId);
		input.add(hierarchyVersion);
		try {
			List<Object[]> returnlist = QueryUtils.getAppData(input, "selectHierarchyDetails", null);
			for (Object[] object : returnlist) {
				leveldto = new Leveldto();
				leveldto.setLevel(object[0] == null ? StringUtils.EMPTY : String.valueOf(object[0]));
				leveldto.setLevelNo(UiUtils.parseStringToInteger(String.valueOf(object[1])));
				leveldto.setLevelValueReference(object[NumericConstants.TWO] == null ? StringUtils.EMPTY
						: String.valueOf(object[NumericConstants.TWO]));
				leveldto.setTableName(object[NumericConstants.THREE] == null ? StringUtils.EMPTY
						: String.valueOf(object[NumericConstants.THREE]));
				leveldto.setFieldName(object[NumericConstants.FOUR] == null ? StringUtils.EMPTY
						: String.valueOf(object[NumericConstants.FOUR]));
				if (object[NumericConstants.THREE] != null
						&& !StringUtils.isEmpty(String.valueOf(object[NumericConstants.THREE]))
						&& !StringUtils.isBlank(String.valueOf(object[NumericConstants.THREE]))) {
					if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromCompany(true);
					} else if (Constant.CONTRACT_MASTER
							.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromContract(true);
					} else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromItem(true);
					} else {
						leveldto.setFromCompany(false);
						leveldto.setFromContract(false);
						leveldto.setFromItem(false);
					}
				}
				resultList.add(leveldto);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultList;
	}

	/**
	 * Load customer forecast level.
	 *
	 * @param hierarchyId
	 *            the hierarchy id
	 * @param hierarchyName
	 *            the hierarchy name
	 * @return the list
	 */
	public List<Leveldto> loadCustomerForecastLevel1(int hierarchyId, String hierarchyName) {
		List<Leveldto> resultList = new ArrayList<>();
		Leveldto leveldto;
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("hierarchyId", hierarchyId);
		if (!StringUtils.EMPTY.equals(hierarchyName)) {
			parameters.put("hierarchyName", hierarchyName);
		}
		try {
			List<Object[]> returnlist = dataSelectionDao.getLevelsFromHierarchy(parameters);
			for (Object[] object : returnlist) {
				leveldto = new Leveldto();
				leveldto.setLevel(object[0] == null ? StringUtils.EMPTY : String.valueOf(object[0]));
				leveldto.setLevelNo(UiUtils.parseStringToInteger(String.valueOf(object[1])));
				leveldto.setLevelValueReference(object[NumericConstants.TWO] == null ? StringUtils.EMPTY
						: String.valueOf(object[NumericConstants.TWO]));
				leveldto.setTableName(object[NumericConstants.THREE] == null ? StringUtils.EMPTY
						: String.valueOf(object[NumericConstants.THREE]));
				leveldto.setFieldName(object[NumericConstants.FOUR] == null ? StringUtils.EMPTY
						: String.valueOf(object[NumericConstants.FOUR]));
				if (object[NumericConstants.THREE] != null
						&& !StringUtils.isEmpty(String.valueOf(object[NumericConstants.THREE]))
						&& !StringUtils.isBlank(String.valueOf(object[NumericConstants.THREE]))) {
					if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromCompany(true);
					} else if (Constant.CONTRACT_MASTER
							.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromContract(true);
					} else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromItem(true);
					} else {
						leveldto.setFromCompany(false);
						leveldto.setFromContract(false);
						leveldto.setFromItem(false);
					}
				}
				resultList.add(leveldto);
			}
		} catch (SystemException e) {
			LOGGER.error(e.getMessage());
		}
		return resultList;
	}

	/**
	 * Load inner customer level.
	 *
	 * @param levelName
	 *            the level name
	 * @param hierarchyId
	 *            the hierarchy id
	 * @param selectedLevelSids
	 * @param isNdc
	 * @param fieldName
	 * @param relationshipSid
	 * @param descriptionMap
	 * @param level
	 * @param screenName
	 * @param discountID
	 * @param levelNo
	 * @param deductionID
	 * @param deductionLevel
	 * @param companySID
	 * @param businessUnitSID
	 * @return the list
	 * @throws java.lang.Exception
	 */
	public List<Leveldto> loadInnerLevel(String levelName, int hierarchyId, List<Integer> selectedLevelSids,
			final boolean isNdc, final String fieldName, final String relationshipSid,
			final Map<String, String> descriptionMap, final String level, final String screenName, int discountID,
			int levelNo, String deductionID, String deductionLevel, final Object companySID,
			final Object businessUnitSID) throws SystemException {
		List<Leveldto> values = new ArrayList<>();
		Map<String, Object> parameters = new HashMap<>();
		Leveldto dto;
		if (isNdc) {
			parameters.put("isNdc", Constant.TRUE);
		} else {
			parameters.put("isNdc", "false");
		}

		parameters.put("glCompId", companySID);
		parameters.put(BUSINESS_UNIT_PROPERTY, businessUnitSID);
		parameters.put(Constant.HIERARCHY_DEFINITION_SID, hierarchyId);
		parameters.put(Constant.LEVEL_NAME, levelName);
		parameters.put("level", level);
		parameters.put("relationshipLevelSidList", selectedLevelSids);
		parameters.put(Constant.FIELD_NAME, fieldName);
		if (StringUtils.isBlank(relationshipSid) || Constant.NULL.equals(String.valueOf(relationshipSid))
				|| DASH.equals(String.valueOf(relationshipSid))) {
			parameters.put(RELATIONSHIP_SID, Constant.NULL);
		} else {
			parameters.put(RELATIONSHIP_SID, relationshipSid);
		}
		parameters.put("discount", discountID);
		parameters.put(LEVEL_NO, levelNo);
		parameters.put(Constant.SCREEN_NAME, screenName);
		parameters.put("deductionValue", StringUtils.EMPTY + deductionID);
		parameters.put("deductionLevel", deductionLevel);
		List result = dataSelectionDao.getInnerLevel(parameters);
		if (!result.isEmpty()) {
			for (int i = 0; i < result.size(); i++) {
				dto = new Leveldto();
				Object[] obj = (Object[]) result.get(i);
				dto.setLevel(String.valueOf(obj[NumericConstants.EIGHT]));
				dto.setRelationshipLevelValue(String.valueOf(obj[0]));
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(obj[1]));
				dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
				dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(obj[NumericConstants.THREE]));
				dto.setTableName(String.valueOf(obj[NumericConstants.FOUR]));
				dto.setFieldName(String.valueOf(obj[NumericConstants.FIVE]));
				dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
				dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.SEVEN]));
				if (obj[NumericConstants.FOUR] != null
						&& !StringUtils.isEmpty(String.valueOf(obj[NumericConstants.FOUR]))
						&& !StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR]))) {
					if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
						dto.setFromCompany(true);
					} else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
						dto.setFromContract(true);
					} else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
						dto.setFromItem(true);
					} else {
						dto.setFromCompany(false);
						dto.setFromContract(false);
						dto.setFromItem(false);
					}
				}
				if (isNdc) {
					dto.setNdc(String.valueOf(obj[NumericConstants.SEVEN]));
					dto.setForm(String.valueOf(obj[NumericConstants.EIGHT]));
					dto.setStrength(String.valueOf(obj[NumericConstants.NINE]));
				}

				if (descriptionMap != null) {
					dto.setDisplayValue(descriptionMap.get(dto.getHierarchyNo()));
				}

				values.add(dto);
			}
		}

		return values;
	}

	/**
	 * Load inner customer level.
	 *
	 * @param levelNo
	 * @param hierarchyId
	 *            the hierarchy id
	 * @return the list
	 */
	public List<String> filterForGroup(List<String> levelNo, int hierarchyId) {
		List<String> values = new ArrayList<>();
		List result = null;
		DynamicQuery dynamicQuery = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(PropertyFactoryUtil.forName(Constant.RELATIONSHIP_BUILDER_SID)
				.in(RelationshipBuilderLocalServiceUtil.dynamicQuery()
						.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHY_DEFINITION_SID, hierarchyId))
						.setProjection(ProjectionFactoryUtil.property(Constant.RELATIONSHIP_BUILDER_SID))));

		dynamicQuery.add(RestrictionsFactoryUtil.in(LEVEL_NO, levelNo));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

		productProjectionList
				.add(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("relationshipLevelValues")));
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.LEVEL_NAME));
		productProjectionList.add(ProjectionFactoryUtil.property("parentNode"));
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.RELATIONSHIP_LEVEL_SID));

		dynamicQuery.setProjection(productProjectionList);
		result = dataSelectionDao.getCustomerForecastLevel(dynamicQuery);
		for (int i = 0; i < result.size(); i++) {

			Object[] obj = (Object[]) result.get(i);

			values.add(String.valueOf(obj[0]));
		}

		return values;
	}

	public List<CompanyMaster> getCompanyFromSids(final List<String> companySids) throws SystemException {

		List<Integer> sids = new ArrayList<>();

		for (String sid : companySids) {
			sids.add(DataTypeConverter.convertStringToInteger(sid));
		}

		final DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, sids));
		return dataSelectionDao.getCompanyMasterList(dynamicQuery);
	}

	/**
	 * Delete and inserts Product hierarchy logic to update
	 *
	 * @param levelList
	 *            the level list
	 * @param projectionId
	 * @throws java.lang.Exception
	 */
	public void updateProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, final List<String> removeLevels, final List<String> addLevels,
			final DataSelectionDTO dataSelectionDTO) throws SystemException {
		if (removeLevels != null && !removeLevels.isEmpty()) {
			List<Object> removeChildLevels = null;
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(Constant.INDICATOR, "getRemovableChildren");
			parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
			parameters.put(Constant.TABLE_NAME, " PROJECTION_PROD_HIERARCHY ");
			parameters.put(Constant.PROJECTION_ID, projectionId);
			removeChildLevels = dataSelectionDao.executeQuery(parameters);
			deleteProductHierarchyLevels(projectionId, removeLevels);
			if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
				deleteProductHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
			}
		}
		saveProductHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, Constant.UPDATE, dataSelectionDTO);
	}

	private void deleteProductHierarchyLevels(final int projectionId, final List<String> removeLevels)
			throws SystemException {
		List<ProjectionProdHierarchy> details;
		final DynamicQuery dynamicQuery = ProjectionProdHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.RELATIONSHIP_LEVEL_SID,
				UiUtils.convertStringListToParsedIngeter(removeLevels)));
		details = dataSelectionDao.findProdHierarchyByProjectionId(dynamicQuery);
		for (final ProjectionProdHierarchy prod : details) {
			dataSelectionDao.deleteProjectionProdHierarchies(prod);
		}
	}

	public void updateProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, final DataSelectionDTO dataSelectionDTO) throws SystemException {
		List<ProjectionProdHierarchy> details;
		final DynamicQuery dynamicQuery = ProjectionProdHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		details = dataSelectionDao.findProdHierarchyByProjectionId(dynamicQuery);
		for (final ProjectionProdHierarchy cust : details) {
			dataSelectionDao.deleteProjectionProdHierarchies(cust);
		}
		saveProductHierarchyLogic(levelList, endLevelSids, projectionId, null, Constant.SAVE, dataSelectionDTO);
	}

	/**
	 * Save Product hierarchy logic.
	 *
	 * @param levelList
	 *            the level list
	 * @throws java.lang.Exception
	 */
	public void saveProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, final List<String> addLevels, final String indicator,
			final DataSelectionDTO dataSelectionDTO) {
		LOGGER.debug("saveProductHierarchyLogic endLevelSids projectionId= {} ", projectionId);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "getChildLevelRLSid");
		parameters.put(RL_SIDS, endLevelSids);
		parameters.put(Constant.PROJECTION_ID, projectionId);
		parameters.put(Constant.TABLE_NAME, PROJECTION_PROD_HIERARCHY);
		parameters.put(BUSINESS_UNIT_PROPERTY, dataSelectionDTO.getBusinessUnitSystemId());
		String insertQuery;
		StringBuilder insertQueryBuilder = new StringBuilder();
		insertQueryBuilder.append(prepareRelationShipQuery(parameters, false));
		String endLevelsQuery = "";
		if (endLevelSids != null && !endLevelSids.isEmpty()) {
			endLevelsQuery = prepareRelationShipQuery(parameters, true);
		}

		try {
			if (Constant.UPDATE.equals(indicator)) {
				int listSize = addLevels.size();
				for (int i = 0; i < listSize; i++) {
					if (i == 0) {
						insertQueryBuilder.append(
								"Insert into PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID)  ");
					}
					insertQueryBuilder.append(SELECT_CAPS).append(projectionId).append(" , ")
							.append(UiUtils.parseStringToInteger(String.valueOf(addLevels.get(i))));
					if (i != listSize - 1) {
						insertQueryBuilder.append(UNION_ALL);
					}
				}
			} else if (Constant.SAVE.equals(indicator)) {
				int listSize = levelList.size();
				for (int i = 0; i < listSize; i++) {
					if (i == 0) {
						insertQueryBuilder.append(
								"Insert into PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) ");
					}
					insertQueryBuilder.append(SELECT_CAPS).append(projectionId).append(" , ")
							.append(levelList.get(i).getRelationshipLevelSid());
					if (i != listSize - 1) {
						insertQueryBuilder.append(UNION_ALL);
					}
				}
				if (insertQueryBuilder.toString().isEmpty()) {
					insertQueryBuilder.append(
							"Insert into PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) ");
				} else {
					insertQueryBuilder.append(UNION_ALL).append(endLevelsQuery);
				}
			}
			insertQuery = insertQueryBuilder.toString();
			if (!insertQuery.isEmpty()) {
				HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	public static final String UNION_ALL = " UNION ALL ";
	public static final String PROJECTION_PROD_HIERARCHY = "PROJECTION_PROD_HIERARCHY";
	public static final String RL_SIDS = "rlSids";

	/**
	 * Delete and inserts customer hierarchy logic to update
	 *
	 * @param levelList
	 *            the level list
	 * @param endLevelSids
	 * @param projectionId
	 * @throws java.lang.Exception
	 */
	public void updateCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, final List<String> removeLevels, final List<String> addLevels)
			throws SystemException {
		if (removeLevels != null && !removeLevels.isEmpty()) {
			List<Object> removeChildLevels = null;
			Map<String, Object> parameters = new HashMap<>();
			parameters.put(Constant.INDICATOR, "getRemovableChildren");
			parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
			parameters.put(Constant.PROJECTION_ID, projectionId);
			parameters.put(Constant.TABLE_NAME, " PROJECTION_CUST_HIERARCHY ");
			removeChildLevels = dataSelectionDao.executeQuery(parameters);
			deleteCustomerHierarchyLevels(projectionId, removeLevels);
			if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
				deleteCustomerHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
			}
		}
		saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, Constant.UPDATE);
	}

	private void deleteCustomerHierarchyLevels(final int projectionId, final List<String> removeLevels)
			throws SystemException {
		List<ProjectionCustHierarchy> details;
		final DynamicQuery dynamicQuery = ProjectionCustHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.RELATIONSHIP_LEVEL_SID,
				UiUtils.convertStringListToParsedIngeter(removeLevels)));
		details = dataSelectionDao.findCustHierarchyByProjectionId(dynamicQuery);
		for (final ProjectionCustHierarchy cust : details) {
			dataSelectionDao.deleteProjectionCustHierarchies(cust);
		}
	}

	public void updateCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId) throws SystemException {
		List<ProjectionCustHierarchy> details;
		final DynamicQuery dynamicQuery = ProjectionCustHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		details = dataSelectionDao.findCustHierarchyByProjectionId(dynamicQuery);
		for (final ProjectionCustHierarchy cust : details) {
			dataSelectionDao.deleteProjectionCustHierarchies(cust);
		}
		saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, null, Constant.SAVE);
	}

	/**
	 * Save customer hierarchy logic.
	 *
	 * @param levelList
	 *            the level list
	 * @param projectionId
	 * @throws java.lang.Exception
	 */
	public void saveCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, final List<String> addLevels, final String indicator) {
		LOGGER.debug("saveCustomerHierarchyLogic endLevelSids size= {}, projectionId= {} ", endLevelSids.size(),
				projectionId);
		String endLevelsQury;
		Map<String, Object> parameters = new HashMap<>();
		String insertQuery = StringUtils.EMPTY;
		parameters.put(Constant.PROJECTION_ID, projectionId);
		parameters.put(RL_SIDS, endLevelSids);
		parameters.put(Constant.TABLE_NAME, "PROJECTION_CUST_HIERARCHY");
		endLevelsQury = prepareRelationShipQuery(parameters, false);
		try {

			if (Constant.UPDATE.equals(indicator)) {
				int listSize = addLevels.size();
				for (int i = 0; i < listSize; i++) {
					if (i == 0) {
						insertQuery = "Insert into PROJECTION_CUST_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID ) ";
					}
					insertQuery += SELECT_CAPS + projectionId + " , "
							+ UiUtils.parseStringToInteger(String.valueOf(addLevels.get(i)));
					if (i != listSize - 1) {
						insertQuery += UNION_ALL;
					}
				}

			} else if (Constant.SAVE.equals(indicator)) {
				int listSize = levelList.size();
				for (int i = 0; i < listSize; i++) {
					if (i == 0) {
						insertQuery = "Insert into PROJECTION_CUST_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) ";
					}
					insertQuery += SELECT_CAPS + projectionId + " , " + levelList.get(i).getRelationshipLevelSid();
					if (i != listSize - 1) {
						insertQuery += UNION_ALL;
					}
				}
			}
			if (insertQuery.isEmpty()) {
				insertQuery = "Insert into PROJECTION_CUST_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) ";
				insertQuery += endLevelsQury;
			} else {
				insertQuery += UNION_ALL + endLevelsQury;
			}

			if (!insertQuery.isEmpty()) {
				HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * To update the ccp in projeciton details
	 *
	 * @param projectionId
	 * @throws Exception
	 */
	public void updateCcpLogic(final List<Leveldto> customerHierarchyEndLevels,
			final String productHierarchyEndLevelsHierNos, final String indicator, final int projectionId)
			throws SystemException {

		saveCcp(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, indicator, String.valueOf(projectionId));
	}

	/**
	 * To update the ccp in projeciton details
	 *
	 * @param projectionId
	 * @throws Exception
	 */
	public void updateCcpLogicView(final List<Leveldto> customerHierarchyEndLevels,
			final String productHierarchyEndLevelsHierNos, final String indicator, final int projectionId)
			throws SystemException {
		deleteProjectionDetails(projectionId);
		saveCcp(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, indicator, String.valueOf(projectionId));
	}

	/**
	 * Deletes projection details records
	 *
	 * @param projectionId
	 */
	private void deleteProjectionDetails(final int projectionId) throws SystemException {
		List<ProjectionDetails> details;
		final DynamicQuery dynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
		details = dataSelectionDao.findProjDetailsByProjectionId(dynamicQuery);
		for (final ProjectionDetails detail : details) {
			dataSelectionDao.deleteProjectionDetails(detail);
		}
	}

	/**
	 * values.
	 *
	 * @return the hierarchy values
	 */
	public List getHierarchyValues() {
		List resultss = new ArrayList();
		final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
		List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto = null;
		final String query = " select HLD.LEVEL_NO,HLD.TABLE_NAME,HLD.FIELD_NAME,HLD.LEVEL_VALUE_REFERENCE,* from dbo.HIERARCHY_LEVEL_DEFINITION HLD WHERE HIERARCHY_LEVEL_DEFINITION_SID IN  "
				+ " (select DISTINCT HIERARCHY_LEVEL_DEFINITION_SID FROM dbo.RELATIONSHIP_LEVEL_DEFINITION "
				+ " WHERE RELATIONSHIP_LEVEL_SID IN(select DISTINCT RELATIONSHIP_LEVEL_SID from dbo.PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID='1') ) ";

		try (Connection connection = dataSourceConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			while (resultSet.next()) {
				dto = new Leveldto();
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(resultSet.getObject(1)));
				dto.setTableName(String.valueOf(resultSet.getObject(NumericConstants.TWO)));
				dto.setFieldName(String.valueOf(resultSet.getObject(NumericConstants.THREE)));
				dto.setLevelValueReference(String.valueOf(resultSet.getObject(NumericConstants.FOUR)));
				resultList.add(dto);
			}

			resultss.add(resultList);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultss;
	}

	/**
	 * Gets the relation ship values.
	 *
	 * @return the relation ship values
	 */
	public List getRelationShipValues() {
		List resultss = new ArrayList();
		final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
		List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto = null;
		final String query = " select LEVEL_NO,RELATIONSHIP_LEVEL_VALUES,PARENT_NODE,LEVEL_NAME "
				+ " FROM dbo.RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_LEVEL_SID IN(select DISTINCT RELATIONSHIP_LEVEL_SID from dbo.PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID='1') ORDER by LEVEL_NO ";

		try (Connection connection = dataSourceConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {

			while (resultSet.next()) {
				dto = new Leveldto();
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(resultSet.getObject(1)));
				dto.setRelationshipLevelValue(String.valueOf(resultSet.getObject(NumericConstants.TWO)));
				dto.setParentNode(String.valueOf(resultSet.getObject(NumericConstants.THREE)));
				dto.setLevel(String.valueOf(resultSet.getObject(NumericConstants.FOUR)));
				resultList.add(dto);
			}

			resultss.add(resultList);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultss;
	}

	public List getParentLevels(final int levelNo, final int relationshipLevelSid) {
		List resultss;
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(LEVEL_NO, levelNo);
		parameters.put(Constant.INDICATOR, StringUtils.EMPTY);
		parameters.put(Constant.RELATIONSHIP_LEVEL_SID, relationshipLevelSid);

		List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto;

		try {
			resultss = dataSelectionDao.getParentLevels(levelNo, relationshipLevelSid, parameters);
			for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
				dto = new Leveldto();
				Object[] objects = (Object[]) resultss.get(loop);
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(objects[0]));
				dto.setRelationshipLevelValue(String.valueOf(objects[1]));
				dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
				dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
				dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
				dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
				dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
				dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.SEVEN]));
				resultList.add(dto);
			}

		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultList;
	}

	public Leveldto getParentLevels(final int levelNo, final int relationshipLevelSid, final String parent) {
		List resultss;
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(LEVEL_NO, levelNo);
		parameters.put(Constant.INDICATOR, StringUtils.EMPTY);
		parameters.put(Constant.PARENT, parent);
		parameters.put(Constant.RELATIONSHIP_LEVEL_SID, relationshipLevelSid);

		Leveldto dto = new Leveldto();

		try {
			resultss = dataSelectionDao.getParentLevels(levelNo, relationshipLevelSid, parameters);
			Object[] objects = (Object[]) resultss.get(0);
			dto.setLevelNo(DataTypeConverter.convertObjectToInt(objects[0]));
			dto.setRelationshipLevelValue(String.valueOf(objects[1]));
			dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
			dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
			dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
			dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
			dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
			dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.SEVEN]));
			dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
			dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(ex.getMessage());

		}
		return dto;
	}

	/**
	 * Method to get User details based on user Id passed
	 *
	 * @param userId
	 * @return Transfer User Object
	 */
	public User getUserByID(String userId) {
		User user = null;
		try {

			user = dataSelectionDao.getUser(Long.valueOf(userId));

		} catch (PortalException | SystemException | NumberFormatException ex) {
			LoggerFactory.getLogger(DataSelectionLogic.class.getName()).error(StringUtils.EMPTY, ex);
		}
		return user;
	}

	public String deleteProjection(int projectionId, String currentUserId, String screenName) {
		LOGGER.debug("Enters deleteProjection with= {} ", projectionId);
		String str = StringUtils.EMPTY;
		try {
			ProjectionMaster projMaster;
			projMaster = dataSelectionDao.getProjectionMaster(projectionId);
			if (!String.valueOf(projMaster.getCreatedBy()).equals(currentUserId)) {
				return "accessDenined";
			}
			if ("Returns".equalsIgnoreCase(screenName)) {
				String deleteQuery = SQlUtil.getQuery("DELETE_RETURN_PROJECTION")
						.replace(Constant.PROJECTION_MASTER_SID_AT, StringUtils.EMPTY + projectionId);
				SalesProjectionDAO salesDAO = new SalesProjectionDAOImpl();

				salesDAO.executeUpdateQuery(deleteQuery);
				str = "Pass";
			} else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
				DSLogic dslogic = new DSLogic();
				dslogic.deleteSelectedProjection(projectionId);
				str = "Pass";
			} else {
				deleteProjection(projectionId);
				str = dataSelectionDao.deleteProjection(screenName, projectionId);
			}

		} catch (PortalException | SystemException e) {
			LOGGER.error(e.getMessage());
			return str;
		}
		return str;
	}

	public List<String> getCustomerGroupDetails(int companyGroupSid) throws SystemException {
		DynamicQuery dynamicQuery = CompanyGroupDetailsLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid", companyGroupSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
		dynamicQuery.setProjection(productProjectionList);
		List resultList = dataSelectionDao.getCustomerGroupDetails(dynamicQuery);

		List<String> returnList = new ArrayList<>();
		for (Object companySid : resultList) {
			returnList.add(String.valueOf(companySid));
		}
		return returnList;
	}

	public List<String> getItemGroupDetails(int itemGroupSid) throws SystemException {
		DynamicQuery dynamicQuery = ItemGroupDetailsLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid", itemGroupSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
		dynamicQuery.setProjection(productProjectionList);
		List resultList = dataSelectionDao.getCustomerGroupDetails(dynamicQuery);

		List<String> returnList = new ArrayList<>();
		for (Object companySid : resultList) {
			returnList.add(String.valueOf(companySid));
		}
		return returnList;
	}

	public List<String> executeQuery(final String query) throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("query", query);
		List resultList = dataSelectionDao.executeQuery(parameters);
		List<String> returnList = new ArrayList<>();
		for (Object value : resultList) {
			returnList.add(String.valueOf(value));
		}
		return returnList;
	}

	public List<CompanyDdlbDto> getCompanyForDdlbFromSids(final List<String> companySids) throws SystemException {

		List<CompanyMaster> companies = getCompanyFromSids(companySids);
		List<CompanyDdlbDto> companiesForDdlb = new ArrayList<>();
		CompanyDdlbDto companyDdlbDto;
		for (CompanyMaster companyMaster : companies) {
			companyDdlbDto = new CompanyDdlbDto(companyMaster.getCompanyMasterSid(), companyMaster.getCompanyName());
			companiesForDdlb.add(companyDdlbDto);
		}
		return companiesForDdlb;
	}

	/**
	 * Gets a list of companies
	 *
	 * @param startIndex
	 * @param endIndex
	 * @param filterText
	 * @param companySids
	 * @param companyDdlbDefault
	 * @param selectedCompanyDdlbDto
	 * @return
	 * @throws Exception
	 */
	public List<CompanyDdlbDto> getCompaniesLazy(int startIndex, int endIndex, String filterText,
			final List<String> companySids, CompanyDdlbDto companyDdlbDefault, CompanyDdlbDto selectedCompanyDdlbDto)
			throws SystemException {
		List<CompanyDdlbDto> companies = new ArrayList<>();
		if (startIndex == 0) {
			companies.add(companyDdlbDefault);
		}
		if (companySids != null && !companySids.isEmpty()) {
			DynamicQuery helper = HelperTableLocalServiceUtil.dynamicQuery();
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMP_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.parseInt(Arrays.toString(companyTypeIds.get(0)));
			DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			String tempFilterText = filterText;
			tempFilterText = StringUtils.trimToEmpty(tempFilterText) + Constant.PERCENT;
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID,
					UiUtils.convertStringListToIngeter(companySids)));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, tempFilterText));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(COMPANY_TYPE_PROPERTY, companyId));
			dynamicQuery.setLimit(startIndex, endIndex);
			List<Object[]> returnlist = dataSelectionDao.getCompanies(dynamicQuery);
			CompanyDdlbDto companyDdlbDto;
			if (selectedCompanyDdlbDto == null) {
				for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
					Object[] objects = returnlist.get(loop);
					companyDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
							String.valueOf(objects[1]));
					companies.add(companyDdlbDto);
				}
			} else {
				for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
					Object[] objects = returnlist.get(loop);
					if ((Integer.parseInt(String.valueOf(objects[0]))) == selectedCompanyDdlbDto
							.getCompanyMasterSid()) {
						selectedCompanyDdlbDto.setCompanyName(String.valueOf(objects[1]));
						companies.add(selectedCompanyDdlbDto);
					} else {
						companyDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
								String.valueOf(objects[1]));
						companies.add(companyDdlbDto);
					}
				}
			}
		}
		return companies;
	}

	public static final String COMPANY_TYPE_PROPERTY = "companyType";
	public static final String GLCOMP = "Glcomp";

	/**
	 * Gets a list of companies
	 *
	 * @param filterText
	 * @param companySids
	 * @return
	 * @throws Exception
	 */
	public int getCompaniesCount(String filterText, final List<String> companySids) throws SystemException {
		int count = 0;
		if (companySids != null && !companySids.isEmpty()) {
			DynamicQuery helper = HelperTableLocalServiceUtil.dynamicQuery();
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMP_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.parseInt(Arrays.toString(companyTypeIds.get(0)));
			DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			String tempFilterText = filterText;
			tempFilterText = StringUtils.trimToEmpty(tempFilterText) + Constant.PERCENT;
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID,
					UiUtils.convertStringListToIngeter(companySids)));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, tempFilterText));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(COMPANY_TYPE_PROPERTY, companyId));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			count = dataSelectionDao.getCompaniesCount(dynamicQuery);
		}
		return count;
	}

	public List<ItemMaster> getItemMasterFromCompanies(final List<String> companySids) throws SystemException {
		List<ItemMaster> resultList = null;
		List<Integer> itemSids = getItemIdFromCompanyInCCp(companySids, 0);
		if (!itemSids.isEmpty()) {
			DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, itemSids));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			resultList = dataSelectionDao.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public List<ItemMaster> getItemMasterFromCompany(final int companySid) throws SystemException {
		List<ItemMaster> resultList = null;
		List<Integer> itemSids = getItemIdFromCompanyInCCp(null, companySid);
		if (!itemSids.isEmpty()) {
			DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, itemSids));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			resultList = dataSelectionDao.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public List<Integer> getItemIdFromCompanyInCCp(final List<String> companySids, final int companySid)
			throws SystemException {
		DynamicQuery dynamicQuery = CcpDetailsLocalServiceUtil.dynamicQuery();
		if (companySids != null && !companySids.isEmpty()) {
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID,
					UiUtils.convertStringListToIngeter(companySids)));
		}
		if (companySid != 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.COMPANYMASTERSID, companySid));
		}
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		List resultList = dataSelectionDao.getItemIdFromCompanyInCCp(dynamicQuery);
		List<Integer> sidList = new ArrayList<>();
		for (Object sid : resultList) {
			sidList.add(DataTypeConverter.convertObjectToInt(sid));
		}
		return sidList;
	}

	public List<Integer> getItemSidsFromAllBrand() throws SystemException {
		DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName(Constant.BRAND_MASTER_SID).in(BrandMasterLocalServiceUtil
				.dynamicQuery().setProjection(ProjectionFactoryUtil.property(Constant.BRAND_MASTER_SID))));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		List resultList = dataSelectionDao.getItemMaster(dynamicQuery);
		List<Integer> returnList = new ArrayList<>();
		for (Object sid : resultList) {
			returnList.add(DataTypeConverter.convertObjectToInt(sid));
		}
		return returnList;
	}

	public List<ItemMaster> getItemMaster(final List<String> itemSidsFromHierarchy) throws SystemException {
		List<ItemMaster> resultList = null;
		if (itemSidsFromHierarchy != null && !itemSidsFromHierarchy.isEmpty()) {
			DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID,
					UiUtils.convertStringListToIngeter(itemSidsFromHierarchy)));
			resultList = dataSelectionDao.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public ForecastConfig getTimePeriod(String screenName) throws PortalException {
		List<ForecastConfig> resultList = null;
		int businessProcessType = 0;
		DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, getCommercialConstant());
		} else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, getGovernmentConstant());
		} else {
			businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, screenName);
		}
		dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
		dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
		resultList = dataSelectionDao.getForecastConfig(dynamicQuery);
		ForecastConfig forecastConfig = null;
		if (resultList != null && !resultList.isEmpty()) {
			forecastConfig = (ForecastConfig) resultList.get(0);
		}
		return forecastConfig;
	}

	public List<String> getEndLevelCCP(final List<Leveldto> customerEndLevels, final String indicator,
			final String projectionId) throws SystemException {
		List<String> customerCcpList = new ArrayList<>();
		if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
			List resultList;
			Map<String, Object> parameters;
			for (Leveldto dto : customerEndLevels) {
				parameters = new HashMap<>();
				parameters.put(Constant.PROJECTION_ID, projectionId);
				parameters.put(Constant.INDICATOR, indicator);
				parameters.put(Constant.RELATIONSHIP_LEVEL_SID, dto.getRelationshipLevelSid());
				parameters.put(Constant.HIERARACHY_NO, dto.getHierarchyNo());
				resultList = dataSelectionDao.getCcpMap(parameters);
				for (Object ccpId : resultList) {
					customerCcpList.add(String.valueOf(ccpId));
				}
			}
		}
		return customerCcpList;
	}

	public List<String> getRbIds(final int hierarchyDefinitionSid) throws SystemException {
		List<String> returnList = new ArrayList<>();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "getRbId");
		parameters.put(Constant.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid);
		List<Object> resultList = dataSelectionDao.getCcpMap(parameters);
		for (Object rbSid : resultList) {
			returnList.add(String.valueOf(rbSid));
		}
		return returnList;
	}

	public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos,
			final String indicator, final String projectionId) throws SystemException {
		if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
			Map<String, Object> parameters;
			for (Leveldto dto : customerEndLevels) {
				parameters = new HashMap<>();
				parameters.put(Constant.PROJECTION_ID, projectionId);
				parameters.put(Constant.INDICATOR, indicator);
				parameters.put(Constant.RELATIONSHIP_LEVEL_SID, dto.getRelationshipLevelSid());
				parameters.put(Constant.HIERARACHY_NO, dto.getHierarchyNo());
				parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
				parameters.put(Constant.INDICATOR, "saveCcp");
				dataSelectionDao.getCcpMap(parameters);
			}
		}
	}

	public List<Leveldto> getParentLevelsWithHierarchyNo(final String hierarchyNos,
			final Map<String, String> descriptionMap, int hierarchyVersion, int relationShipVersion) {
		List resultss;
		List<Leveldto> resultList = null;
		List<Object> inputs = new ArrayList<>();
		inputs.add(hierarchyNos);
		inputs.add(relationShipVersion);
		inputs.add(hierarchyVersion);
		Leveldto dto;

		try {
			resultss = HelperTableLocalServiceUtil
					.executeSelectQuery(QueryUtils.getQuery(inputs, "getParentLevelsWithHierarchyNo_New"));

			if (resultss != null) {
				resultList = new ArrayList<>();
				for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
					Object[] objects = (Object[]) resultss.get(loop);
					dto = new Leveldto();
					dto.setLevelNo(DataTypeConverter.convertObjectToInt(objects[0]));
					dto.setRelationshipLevelValue(String.valueOf(objects[1]));
					dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
					dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
					dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
					dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
					dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
					dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.SEVEN]));
					dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
					dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
					dto.setHierarchyLevelDefnId(String.valueOf(objects[NumericConstants.TEN]));
					dto.setHierarchyId(DataTypeConverter.convertObjectToInt(objects[NumericConstants.ELEVEN]));
					dto.setHierarchyVersionNo(DataTypeConverter.convertObjectToInt(objects[NumericConstants.TWELVE]));
					dto.setRelationShipVersionNo(relationShipVersion);
					if (descriptionMap != null) {
						dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
					}
					resultList.add(dto);
				}
			}
		} catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultList;
	}

	public int getRelationshipSidCount(String filterText, final int hierarchyDefinitionSid)
			throws PortalException {
		int count = dataSelectionDao
				.getRelationshipCount(getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid));
		return count + 1;
	}

	public List<RelationshipDdlbDto> getRelationshipSidLazy(int startIndex, int endIndex,
			final RelationshipDdlbDto defaultRelationshipDdlbDto, String filterText, final int hierarchyDefinitionSid,
			RelationshipDdlbDto selectedRelationshipDdlbDto) throws PortalException {
		List<RelationshipDdlbDto> returnList = new ArrayList<>();
		DynamicQuery dynamicQuery = getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid);
		dynamicQuery.setLimit(startIndex, endIndex);
		List<Object[]> resultList = dataSelectionDao.getRelationship(dynamicQuery);
		RelationshipDdlbDto relationshipDdlbDto;
		if (startIndex == 0) {
			returnList.add(defaultRelationshipDdlbDto);
		}
		if (selectedRelationshipDdlbDto == null) {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				Object[] objects = resultList.get(loop);
				relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
				returnList.add(relationshipDdlbDto);
			}
		} else {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				Object[] objects = resultList.get(loop);
				if ((String.valueOf(objects[0])).equals(selectedRelationshipDdlbDto.getRelationshipBuilderSid())) {
					selectedRelationshipDdlbDto.setRelationshipName(String.valueOf(objects[1]));
					returnList.add(selectedRelationshipDdlbDto);
				} else {
					relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]),
							String.valueOf(objects[1]));
					returnList.add(relationshipDdlbDto);
				}
			}
		}
		return returnList;
	}

	private DynamicQuery getRelationshipSidDynamicQuery(String filterText, final int hierarchyDefinitionSid) {
		DynamicQuery dynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();
		String tempFilterText = filterText;
		tempFilterText = StringUtils.trimToEmpty(tempFilterText) + Constant.PERCENT;
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.RELATIONSHIP_BUILDER_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(RELATIONSHIP_NAME_PROPERTY));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(RELATIONSHIP_NAME_PROPERTY, tempFilterText));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		return dynamicQuery;
	}

	public static final String RELATIONSHIP_NAME_PROPERTY = "relationshipName";

	public List<RelationshipDdlbDto> getRelationshipSids(final RelationshipDdlbDto defaultRelationshipDdlbDto,
			final int hierarchyDefinitionSid) throws PortalException {
		List<RelationshipDdlbDto> returnList = new ArrayList<>();
		DynamicQuery dynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.RELATIONSHIP_BUILDER_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(RELATIONSHIP_NAME_PROPERTY));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		List<Object[]> resultList = dataSelectionDao.getRelationship(dynamicQuery);
		RelationshipDdlbDto relationshipDdlbDto;
		returnList.add(defaultRelationshipDdlbDto);
		for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
			Object[] objects = resultList.get(loop);
			relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
			returnList.add(relationshipDdlbDto);
		}
		return returnList;
	}

	public List<Leveldto> getChildLevelsWithHierarchyNo(int lowestLevelNo, final Map<String, String> descriptionMap,
			Object businessUnit, Leveldto selectedLevelDto, int hierarchyVersion, int relationShipVersion,
			int subListIndex, Date forecastEligibleDate, boolean isProduct) {
		List<Object[]> resultss = null;
		List<Leveldto> resultList = null;
		try {
			Leveldto dto;
			String query;
			if (isProduct) {
				query = relationLogic.getFinalChildLevelQueryForProduct(selectedLevelDto, relationShipVersion,
						String.valueOf(businessUnit), lowestLevelNo, subListIndex);
				resultss = HelperTableLocalServiceUtil.executeSelectQuery(query);
			} else {
				List<Object> inputs = new ArrayList<>();
				List<Leveldto> levelList = relationLogic.getHierarchyLevelDefinition(selectedLevelDto.getHierarchyId(),
						hierarchyVersion);
                                
                      
        
				List<String> relationHierarchy = relationLogic.getSelectedCustomerLevel(selectedLevelDto,
						Integer.parseInt(selectedLevelDto.getRelationShipBuilderId()), companiesList, levelList,
						StringUtils.EMPTY, StringUtils.EMPTY, relationShipVersion, forecastEligibleDate, lowestLevelNo);
				inputs.add(StringUtils.join(relationHierarchy, ","));
				inputs.add(lowestLevelNo);
				inputs.add(relationShipVersion);
				inputs.add(hierarchyVersion);

				if (!relationHierarchy.isEmpty()) {
//					resultss = HelperTableLocalServiceUtil
//							.executeSelectQuery(QueryUtils.getQuery(inputs, "childLevelsHierarchyNo"));
                                    resultss = relationLogic.getChildLevelsHierarchyNo(inputs);
				}
			}

			if (resultss != null) {
				resultList = new ArrayList<>();
				for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
					Object objects[] = resultss.get(loop);
					dto = new Leveldto();
					dto.setLevelNo(DataTypeConverter.convertObjectToInt(objects[0]));
					dto.setRelationshipLevelValue(String.valueOf(objects[1]));
					dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
					dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
					dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
					dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
					dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
					dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.SEVEN]));
					dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
					dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
					dto.setHierarchyLevelDefnId(String.valueOf(objects[NumericConstants.TEN]));
					dto.setHierarchyId(DataTypeConverter.convertObjectToInt(objects[NumericConstants.ELEVEN]));
					dto.setHierarchyVersionNo(DataTypeConverter.convertObjectToInt(objects[NumericConstants.TWELVE]));
					dto.setRelationShipVersionNo(relationShipVersion);
					if (descriptionMap != null) {
						dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
					}
					resultList.add(dto);
				}
			}
		} catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultList;
	}

        public List<Leveldto> getChildLevelsWithHierarchyNoNewArch(int lowestLevelNo, final Map<String, String> descriptionMap,
			Object businessUnit, Leveldto selectedLevelDto, int hierarchyVersion, int relationShipVersion,
			int subListIndex, Date forecastEligibleDate, boolean isProduct,List<Object> queryparameterList,ExtFilterTable availableCustomer) {
		List<Object[]> resultss = null;
		List<Leveldto> resultList = null;
		try {
			Leveldto dto;
			String query;
			if (isProduct) {
//				query = relationLogic.getFinalChildLevelQueryForProduct(selectedLevelDto, relationShipVersion,
//						String.valueOf(businessUnit), lowestLevelNo, subListIndex);
//				resultss = HelperTableLocalServiceUtil.executeSelectQuery(query);
			} else {
				List<Object> inputs = new ArrayList<>();
				List<Leveldto> levelList = relationLogic.getHierarchyLevelDefinition(selectedLevelDto.getHierarchyId(),
						hierarchyVersion);
                                
                                
                           
                                List<GtnWsRecordBean> recordBeanList = new ArrayList<>();
                                 Leveldto levelDtoRecordBean = (Leveldto) availableCustomer.getValue();
                                
                                    ArrayList<Object> recordBeanColList = new ArrayList<>();
                                    
                                    recordBeanColList.add(0,levelDtoRecordBean.getRelationshipLevelValue());
                                    recordBeanColList.add(1,null);
                                    recordBeanColList.add(2,null);
                                    recordBeanColList.add(3,levelDtoRecordBean.getRelationshipLevelSid());
                                    recordBeanColList.add(4,levelDtoRecordBean.getHierarchyNo());
                                    recordBeanColList.add(5,levelDtoRecordBean.getRelationShipBuilderId());
                                    GtnWsRecordBean recordBean = new GtnWsRecordBean(); 
                                    recordBean.setProperties(recordBeanColList);
                                    recordBeanList.add(recordBean);
                                
                                
                                List<GtnReportHierarchyLevelBean> selectedHierarchyBeanList = new ArrayList<>();
                                for(int i=0;i<levelList.size();i++){
                                    selectedHierarchyBeanList.add(getSelectedHierarchyLevelBean(levelList.get(i)));
                                }
                                
                            Leveldto selectedLevelDtoCusomer = (Leveldto) availableCustomer.getValue();
                            GtnReportHierarchyLevelBean selectedHierarchyBean = getSelectedHierarchyLevelBean(selectedLevelDtoCusomer);
                            
                            GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
                            GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
                             
                            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
                             gtnWsSearchRequest.setQueryInputList(queryparameterList);
                             request.setGtnWsSearchRequest(gtnWsSearchRequest);
                                     
                             GtnWsReportRequest reportRequest = new GtnWsReportRequest();
                             reportRequest.setRecordBean(recordBeanList);
                             reportRequest.setHierarchyInputBean(selectedHierarchyBean);
                             reportRequest.setHierarchyLevelList(selectedHierarchyBeanList);
                             request.setGtnWsReportRequest(reportRequest);
                             
                             
                            GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
                            GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
                            serviceRegistryBean.setRegisteredWebContext("/GtnHierarchyAndRelaionshipWebService");
                            serviceRegistryBean.setUrl("/loadCustomerSelectedTable");
                            serviceRegistryBean.setModuleName("hierarchyRelationship");
                            serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);
                            request.setGtnServiceRegistryWsRequest(serviceRegistryRequest);

                            GtnUIFrameworkWebserviceResponse availableTableCustomerLevelResponse = client.callGtnWebServiceUrl(
                                    "/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry",
                                    request, getGsnWsSecurityToken());

        
//				List<String> relationHierarchy = relationLogic.getSelectedCustomerLevel(selectedLevelDto,
//						Integer.parseInt(selectedLevelDto.getRelationShipBuilderId()), companiesList, levelList,
//						StringUtils.EMPTY, StringUtils.EMPTY, relationShipVersion, forecastEligibleDate, lowestLevelNo);
//				inputs.add(StringUtils.join(relationHierarchy, ","));
//				inputs.add(lowestLevelNo);
//				inputs.add(relationShipVersion);
//				inputs.add(hierarchyVersion);

	
                        resultss = availableTableCustomerLevelResponse.getGtnWsForecastResponse().getInputBean().getResultList();
			if (resultss != null) {
				resultList = new ArrayList<>();
				for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
					Object objects[] = (Object[]) resultss.get(loop);
					dto = new Leveldto();
					dto.setLevelNo(DataTypeConverter.convertObjectToInt(objects[0]));
					dto.setRelationshipLevelValue(String.valueOf(objects[1]));
					dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
					dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
					dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
					dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
					dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
					dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.SEVEN]));
					dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
					dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
					dto.setHierarchyLevelDefnId(String.valueOf(objects[NumericConstants.TEN]));
					dto.setHierarchyId(DataTypeConverter.convertObjectToInt(objects[NumericConstants.ELEVEN]));
					dto.setHierarchyVersionNo(DataTypeConverter.convertObjectToInt(objects[NumericConstants.TWELVE]));
					dto.setRelationShipVersionNo(relationShipVersion);
					if (descriptionMap != null) {
						dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
					}
					resultList.add(dto);
				}
			}
		}
                }
                catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultList;
	}
        
	public void getEndLevelRelationshipLevels(final List<String> endLevelSids, final String relationshipSid,
			List<Leveldto> ccList, List<String> availableHierNo) throws SystemException {
		Leveldto dto;
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "getChildLevelRL");
		parameters.put(RELATIONSHIP_SID, relationshipSid);
		parameters.put(RL_SIDS, endLevelSids);
		parameters.put("availableHierNo", availableHierNo);
		List<Object> endLevels = dataSelectionDao.executeQuery(parameters);
		for (int i = 0, j = endLevels.size(); i < j; i++) {
			dto = new Leveldto();
			Object[] obj = (Object[]) endLevels.get(i);
			dto.setRelationshipLevelValue(String.valueOf(obj[0]));
			dto.setLevelNo(DataTypeConverter.convertObjectToInt(obj[1]));
			dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
			dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(obj[NumericConstants.THREE]));
			dto.setTableName(String.valueOf(obj[NumericConstants.FOUR]));
			dto.setFieldName(String.valueOf(obj[NumericConstants.FIVE]));
			dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
			dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.SEVEN]));
			dto.setLevel(String.valueOf(obj[NumericConstants.EIGHT]));
			if (obj[NumericConstants.FOUR] != null && !StringUtils.isEmpty(String.valueOf(obj[NumericConstants.FOUR]))
					&& !StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR]))) {
				if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromCompany(true);
				} else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromContract(true);
				} else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromItem(true);
				} else {
					dto.setFromCompany(false);
					dto.setFromContract(false);
					dto.setFromItem(false);
				}
			}

			ccList.add(dto);
		}
	}

	public void setForcastFileDate(DataSelectionDTO dto) {
		try {
			String query = SQlUtil.getQuery(getClass(), "getFileEndDate");
			query = query.replace("[?BUSINESS_UNIT]", StringUtils.EMPTY + dto.getBusinessUnitSystemId());
			List list = (List) salesProjectionDAO.executeSelectQuery(query);
			if (list != null && !list.isEmpty()) {
				Object[] tempDate = (Object[]) list.get(0);
				dto.setFileEndYear(DataTypeConverter.convertObjectToInt(tempDate[0]));
				dto.setFileEndMonth(DataTypeConverter.convertObjectToInt(tempDate[1]));
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public void deleteTempOnUpdate(final String projectionHierarchyTable, final int projectionId, final String hNos)
			throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "deleteTempOnUpdate");
		parameters.put("projectionHierarchyTable", projectionHierarchyTable);
		parameters.put(Constant.PROJECTION_ID, projectionId);
		parameters.put("hNos", hNos);
		dataSelectionDao.executeQuery(parameters);
	}

	public Object deleteProjection(final int projectionId) throws SystemException {
		Map<String, Object> input = new HashMap<>();
		input.put("?PID", projectionId);
		return dataSelectionDao.tempOperation(input, "deleteProjection");
	}

	public List getFSValue(String relationshipLevelValue, final String fieldName) {
		List list = new ArrayList();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "getFSValue");
		parameters.put("relationshipLevelValue", relationshipLevelValue);
		parameters.put(Constant.FIELD_NAME, fieldName);
		try {
			list = dataSelectionDao.executeQuery(parameters);
			return list;
		} catch (SystemException e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public List<Integer> getItemIdFromCompanyForGlComp(final int companySid) throws SystemException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "companyFilter");
		parameters.put("companySid", companySid);
		List resultList = dataSelectionDao.executeQuery(parameters);
		List<Integer> sidList = new ArrayList<>();
		for (Object sid : resultList) {
			sidList.add(DataTypeConverter.convertObjectToInt(sid));
		}
		return sidList;
	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID) throws SystemException {
		Map<String, Object> input = new HashMap<>();
		input.put(RBSID, relationshipBuilderSID);
		return (Map<String, String>) dataSelectionDao.tempOperation(input, "getHierarchyTableDetails");
	}

	public Map<String, List> getLevelValueDetails(SessionDTO sessionDTO, Object relationshipBuilderSID,
			boolean isCustomerHierarchy) {
		return getRelationshipDetails(sessionDTO, relationshipBuilderSID.toString(), isCustomerHierarchy);
	}

	public List<RelationshipDdlbDto> getRelationshipSid(final int hierarchyDefinitionSid)
			throws PortalException {
		List<RelationshipDdlbDto> returnList = new ArrayList<>();
		DynamicQuery dynamicQuery = getRelationshipSidDynamicQuery(hierarchyDefinitionSid);
		List<Object[]> resultList = dataSelectionDao.getRelationship(dynamicQuery);
		RelationshipDdlbDto relationshipDdlbDto;
		for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
			Object[] objects = resultList.get(loop);
			relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
			returnList.add(relationshipDdlbDto);
		}
		return returnList;
	}

	private DynamicQuery getRelationshipSidDynamicQuery(final int hierarchyDefinitionSid) {
		DynamicQuery dynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.RELATIONSHIP_BUILDER_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(RELATIONSHIP_NAME_PROPERTY));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		return dynamicQuery;
	}

	/**
	 * Gets a list of companies
	 *
	 * @param startIndex
	 * @param endIndex
	 * @param filterText
	 * @param companySids
	 * @param companyDdlbDefault
	 * @param selectedCompanyDdlbDto
	 * @return
	 * @throws Exception
	 */
	public List<CompanyDdlbDto> getCompanies(final List<String> companySids) throws SystemException {
		List<CompanyDdlbDto> companies = new ArrayList<>();
		if (companySids != null && !companySids.isEmpty()) {
			DynamicQuery helper = HelperTableLocalServiceUtil.dynamicQuery();
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMPANY_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.parseInt(Arrays.toString(companyTypeIds.get(0)));
			DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID,
					UiUtils.convertStringListToIngeter(companySids)));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(COMPANY_TYPE_PROPERTY, companyId));
			List<Object[]> returnlist = dataSelectionDao.getCompanies(dynamicQuery);
			CompanyDdlbDto companyDdlbDto;
			for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
				Object[] objects = returnlist.get(loop);
				companyDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
						String.valueOf(objects[1]));
				companies.add(companyDdlbDto);
			}
		}
		LOGGER.debug("companies return size= {} ", companies.size());
		return companies;
	}

	// Added For Mandated
	public String getGenerateMarketValue(int rbID) {
		List<Object> temp = getGenerateMarketValueResult(rbID);
		String marketType = StringUtils.EMPTY;
		if ((!temp.isEmpty())
				&& (String.valueOf(temp.get(0)) != null && !StringUtils.EMPTY.equals(String.valueOf(temp.get(0))))) {
			marketType = String.valueOf(temp.get(0));
		}

		return marketType;
	}

	public List<Object> getGenerateMarketValueResult(int rbID) {
		try {
			List<Object> list;
			StringBuilder queryString = new StringBuilder();
			queryString
					.append("select RELATIONSHIP_LEVEL_VALUES from RELATIONSHIP_LEVEL_DEFINITION where \n"
							+ "RELATIONSHIP_BUILDER_SID='")
					.append(rbID).append("'\n").append("and  LEVEL_NAME='Market Type'");
			CommonDAO spDAO = new CommonDAOImpl();
			list = (List) spDAO.executeSelectQuery(queryString.toString());
			return list;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return Collections.emptyList();
		}
	}

	public String getDefinedValue(String definedValue) {
		String str = StringUtils.EMPTY;
		List<Object> listValue = getDefinedValueResult(definedValue);
		if (!listValue.isEmpty()) {
			for (int i = 0; i < listValue.size(); i++) {
				str = String.valueOf(listValue.get(0));
			}
		}

		return str;

	}

	public List<Object> getDefinedValueResult(String definedValue) {
		String str = StringUtils.EMPTY;
		try {
			CommonDAO commonDAO = new CommonDAOImpl();
			str = "select LEVEL_VALUE_REFERENCE from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID="
					+ definedValue + " and LEVEL_NAME='Market Type'";
			return (List<Object>) commonDAO.executeSelectQuery(str);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public String getHelperValue(String marketType) {
		String marketTypeValue = StringUtils.EMPTY;
		List<Object> temp = getHelperValueResult(marketType);
		if (!temp.isEmpty()) {
			for (int i = 0; i < temp.size(); i++) {
				marketTypeValue = String.valueOf(temp.get(i));
			}
		}
		return marketTypeValue;
	}

	public List<Object> getHelperValueResult(String projId) {
		String str = StringUtils.EMPTY;
		try {
			CommonDAO salesProjDAO = new CommonDAOImpl();
			str = "SELECT\n" + "  DESCRIPTION\n" + "FROM HELPER_TABLE H\n" + "JOIN RELATIONSHIP_LEVEL_DEFINITION RL\n"
					+ "  ON RL.RELATIONSHIP_LEVEL_VALUES = H.HELPER_TABLE_SID\n" + "JOIN PROJECTION_MASTER P\n"
					+ "  ON P.CUST_RELATIONSHIP_BUILDER_SID = RL.RELATIONSHIP_BUILDER_SID\n"
					+ "  AND P.PROJECTION_MASTER_SID = " + projId + "\n" + "JOIN PROJECTION_CUST_HIERARCHY PC\n"
					+ "  ON PC.RELATIONSHIP_LEVEL_SID = RL.RELATIONSHIP_LEVEL_SID\n"
					+ "  AND PC.PROJECTION_MASTER_SID = P.PROJECTION_MASTER_SID\n"
					+ "WHERE RL.LEVEL_NAME = 'MARKET TYPE'\n" + "AND LIST_NAME = 'CONTRACT_TYPE'";
			return (List<Object>) salesProjDAO.executeSelectQuery(str);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public int getDiscountCount(String filterText) {
		try {
			DynamicQuery query = HelperTableLocalServiceUtil.dynamicQuery();
			String tempFilterText = filterText;
			tempFilterText = StringUtils.trimToEmpty(tempFilterText) + Constant.PERCENT;
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			productProjectionList.add(ProjectionFactoryUtil.property(Constant.DESCRIPTION));
			query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			query.add(RestrictionsFactoryUtil.ilike(Constant.DESCRIPTION, tempFilterText));
			query.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, Constant.RS_TYPE));
			discountDdlbCount = dataSelectionDao.getDiscountCount(query);
		} catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
		}
		return discountDdlbCount;
	}

	/**
	 * Gets a list of companies
	 *
	 * @param startIndex
	 * @param endIndex
	 * @param filterText
	 * @param companySids
	 * @param companyDdlbDefault
	 * @param selectedCompanyDdlbDto
	 * @return
	 * @throws Exception
	 */
	public List<CompanyDdlbDto> getDiscounts(int startIndex, int endIndex, String filterText,
			CompanyDdlbDto discountDdlbDefault, CompanyDdlbDto selectedDiscount) throws SystemException {
		List<CompanyDdlbDto> discounts = new ArrayList<>();
		int startValue = startIndex;
		int endValue = endIndex;
		if (startIndex == 0) {
			discounts.add(discountDdlbDefault);
		}
		CompanyDdlbDto discountDdlbDto;
		DynamicQuery query = HelperTableLocalServiceUtil.dynamicQuery();
		String tempFilterText = filterText;
		tempFilterText = StringUtils.trimToEmpty(tempFilterText) + Constant.PERCENT;
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.DESCRIPTION));
		query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		query.add(RestrictionsFactoryUtil.ilike(Constant.DESCRIPTION, tempFilterText));
		query.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, Constant.RS_TYPE));
		query.setLimit(startValue, endValue);
		List<Object[]> resultList = dataSelectionDao.getDiscounts(query);
		if (selectedDiscount == null) {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				Object[] objects = resultList.get(loop);
				discounts.add(new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
						String.valueOf(objects[1]), true));
			}
		} else {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				Object[] objects = resultList.get(loop);
				if (Integer.valueOf(String.valueOf(objects[0])).equals(selectedDiscount.getRsModelSid())) {
					selectedDiscount.setRsNo(String.valueOf(objects[1]));
					discounts.add(selectedDiscount);
				} else {
					discountDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
							String.valueOf(objects[1]), true);
					discounts.add(discountDdlbDto);
				}
			}
		}
		return discounts;
	}

	public String getCheckValue(String hierarchySid) {
		String fieldName = StringUtils.EMPTY;

		List<Object> fieldNameList = SPRCommonLogic.getContractFieldValue(hierarchySid);
		if (!fieldNameList.isEmpty()) {
			for (int i = 0; i < fieldNameList.size(); i++) {
				fieldName = String.valueOf(fieldNameList.get(0));
			}
		}

		return fieldName;
	}

	public void callInsertProcedure(int projectionId, String userId, String sessionId, String procedureName) {
		LOGGER.debug("In callInsertProcedure");
		LOGGER.debug("ProcedureName--->= {} ", procedureName);
		LOGGER.debug("ProjectionId---->= {} ", projectionId);
		LOGGER.debug("UserId---------->= {} ", userId);
		LOGGER.debug("SessionId------->= {} ", sessionId);

		GtnSqlUtil.procedureCallService(procedureName, new Object[] { projectionId, userId, sessionId });

		LOGGER.debug("Ending callSalesInsertProcedure");
	}

	public boolean callReturnsCalculateProcedure(int projectionId, String userId, String sessionId, String frequency,
			String procedureName) {
		LOGGER.debug("In callInsertProcedure");
		LOGGER.debug("ProcedureName--->= {} ", procedureName);
		LOGGER.debug("ProjectionId---->= {} ", projectionId);
		LOGGER.debug("UserId---------->= {} ", userId);
		LOGGER.debug("SessionId------->= {} ", sessionId);
		LOGGER.debug("frequency------->= {} ", frequency);

		return GtnSqlUtil.procedureCallService(procedureName,
				new Object[] { projectionId, userId, sessionId, frequency });
	}

	public String getMarketTypeValue(int projectId) {
		List<Object> temp = getMarketType(projectId);
		String marketType = StringUtils.EMPTY;
		if (!temp.isEmpty()) {
			Object[] objects = (Object[]) temp.get(0);
			if (String.valueOf(objects[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(objects[0]))) {
				marketType = String.valueOf(objects[0]);
			}
		}

		return marketType;
	}

	public List<Object> getMarketType(int projectionId) {
		try {
			List list;
			StringBuilder queryString = new StringBuilder();
			queryString.append("select RELATIONSHIP_LEVEL_VALUES,LEVEL_NO from RELATIONSHIP_LEVEL_DEFINITION \n")
					.append("where RELATIONSHIP_LEVEL_SID in ( select RELATIONSHIP_LEVEL_SID\n")
					.append("from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID= ");
			if (projectionId != 0) {
				queryString.append(StringUtils.EMPTY).append(projectionId);
				queryString.append(" ) and LEVEL_NAME='Market TYPE'");
			}
			list = (List) salesProjectionDAO.executeSelectQuery(queryString.toString());
			return list;

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return Collections.emptyList();
		}
	}

	/**
	 * Gets the Product group.
	 *
	 * @param name
	 * @param sids
	 * @param no
	 * @param action
	 * @param groupIdentifier
	 * @param isCustomGroup
	 * @param start
	 * @param offset
	 * @param filters
	 * @param sortByColumns
	 * @return the Product group result list
	 * @throws java.lang.Exception
	 */
	public List<GroupDTO> searchGroup(final GroupDTO dto, final Boolean isCustomGroup, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns, int start, int offset) {
		List resultList = null;
		List<GroupDTO> returnList = null;
		try {
			if (isCustomGroup) {
				resultList = getCustomerList(dto, isCustomGroup, filters, sortByColumns, Boolean.FALSE, start, offset);
				returnList = Converters.convertCustomerGroupList(resultList);
			} else {
				resultList = getProductList(dto, isCustomGroup, filters, sortByColumns, Boolean.FALSE, start, offset);
				returnList = Converters.convertItemGroupList(resultList);
			}

		} catch (PortalException | SystemException ex) {
			LOGGER.error(ex.getMessage());
		}
		return returnList;
	}

	/**
	 * Search group count
	 *
	 * @param dto
	 * @param isCompanyGroup
	 * @param filters
	 * @param sortByColumns
	 * @return
	 * @throws Exception
	 */
	public int searchGroupCount(final GroupDTO dto, final Boolean isCompanyGroup, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns) throws PortalException {
		List countList;
		if (isCompanyGroup) {
			countList = getCustomerList(dto, isCompanyGroup, filters, sortByColumns, Boolean.TRUE, 0, 0);
		} else {
			countList = getProductList(dto, isCompanyGroup, filters, sortByColumns, Boolean.TRUE, 0, 0);
		}
		return QueryUtils.getCount(countList);
	}

	/**
	 * Used to change the alignment of Selected Product table
	 *
	 * @param selectedProduct
	 * @param selectedProductContainer
	 */
	public static void selectedProductTableAlignmentChange(TreeTable selectedProduct,
			ExtTreeContainer<Leveldto> selectedProductContainer) {
		int length = 0;
		for (Leveldto dto : selectedProductContainer.getItemIds()) {
			if (length < dto.getDisplayValue().length()) {
				length = dto.getDisplayValue().length();
			}
		}
		if (length > NumericConstants.FIFTY) {
			if (length <= NumericConstants.SIXTY) {
				selectedProduct.setColumnWidth(DISPLAY_VALUE, NumericConstants.FIVE_ONE_ZERO);
			} else if (length <= NumericConstants.SEVENTY) {
				selectedProduct.setColumnWidth(DISPLAY_VALUE, NumericConstants.FIVE_SEVEN_ZERO);
			} else if (length <= NumericConstants.EIGHTY) {
				selectedProduct.setColumnWidth(DISPLAY_VALUE, NumericConstants.SIX_THREE_ZERO);
			} else if (length <= NumericConstants.NINETY) {
				selectedProduct.setColumnWidth(DISPLAY_VALUE, NumericConstants.SEVEN_THREE_ZERO);
			} else if (length <= NumericConstants.HUNDRED) {
				selectedProduct.setColumnWidth(DISPLAY_VALUE, NumericConstants.EIGHT_ONE_ZERO);
			}
		} else {
			selectedProduct.setColumnWidth(DISPLAY_VALUE, -1);
		}
	}

	public static final String DISPLAY_VALUE = "displayValue";

	public void insertToReturnDetails(int projectionIdValue) {
		salesProjectionDAO.executeBulkUpdateQuery(SQlUtil.getQuery("RETURN_DETAILS_INSERT")
				.replace(Constant.PROJECTION_ID_AT, String.valueOf(projectionIdValue)));
	}

	/**
	 * Used to get the ReturnDetailsSid for each Hierarchy No
	 *
	 * @param productDescriptionMap
	 * @param session
	 */
	public Map<String, String> getReturnDetails(final SessionDTO session, boolean flag) {
		if (flag) {
			// Used to Insert the record in RETURNS_MAP TABLE
			String query1 = SQlUtil.getQuery("RETURNS_MAP_INSERT").replace("@PROJECTION_SID",
					String.valueOf(session.getProjectionId()));
			salesProjectionDAO.executeBulkUpdateQuery(query1);
		}

		Map<String, String> returnDetilsMap = new HashMap<>();
		String query = SQlUtil.getQuery("RETURN_DETAILS_RESULTS").replace(Constant.PROJECTION_ID_AT,
				String.valueOf(session.getProjectionId()));
		List resultsList = (List) salesProjectionDAO.executeSelectQuery(query);
		for (int i = 0; i < resultsList.size(); i++) {
			Object[] ob = (Object[]) resultsList.get(i);
			returnDetilsMap.put(ob[0].toString(), ob[1].toString());
			if (Objects.equals(Integer.valueOf(session.getProductLevelNumber()), Integer.valueOf(ob[NumericConstants.TWO].toString()))) {
				if (!session.getDetailsSID().isEmpty()) { // Added for GAL-9131
					session.setDetailsSID(session.getDetailsSID().concat(",").concat(ob[1].toString()));
				} else {
					session.setDetailsSID(ob[1].toString());
				}
			}
		}
		return returnDetilsMap;
	}

	public String getContractValue(String definedValue) {
		String str = StringUtils.EMPTY;
		List<Object> listValue = getContractValueResult(definedValue);
		if (!listValue.isEmpty()) {
			for (int i = 0; i < listValue.size(); i++) {
				str = String.valueOf(listValue.get(0));
			}
		}

		return str;

	}

	public List<Object> getContractValueResult(String definedValue) {
		String str = StringUtils.EMPTY;
		try {
			CommonDAO resultDAO = new CommonDAOImpl();
			str = "select FIELD_NAME from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue
					+ " and  LEVEL_NAME in('Customer','Trading Partner')";
			return (List<Object>) resultDAO.executeSelectQuery(str);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public static List<Object[]> getAccrualSelection(int projectionId) {
		List<Object[]> obj = null;
		try {
			String customSql = SQlUtil.getQuery(DataSelectionLogic.class, "getAccrualSelection");
			customSql = customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
			obj = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return obj;
	}

	public List getProductList(final GroupDTO dto, final Boolean isCompanyGroup, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns, Boolean isCount, int startIndex, int offset)
			throws PortalException {
		String sql = QueryUtils.getQuery(getGroupInput(dto, isCompanyGroup), "getProdGroupSearch");
		String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, filterMap(isCompanyGroup))
				.toString();
		sql = sql.replace("@FILTER@", filterQuery.replace("where", " AND "));
		if (isCount) {
			sql = sql.replace(AT_ORDER_BY, " ");
			sql = sql.replace(AT_SELECTION, "COUNT (DISTINCT IG.ITEM_GROUP_SID)");
		} else {
			sql = sql.replace(AT_SELECTION,
					"DISTINCT IG.ITEM_GROUP_SID, IG.ITEM_GROUP_NO, IG.ITEM_GROUP_NAME, COMPANY_NAME, IG.VERSION_NO, IG.ITEM_GROUP_DESCRIPTION");
			String sortingQuery = AbstractFilterLogic.getInstance()
					.orderByQueryGenerator(sortByColumns, filterMap(isCompanyGroup)).toString();
			sql = sql.replace(AT_ORDER_BY, sortingQuery);
			sql = sql + " OFFSET " + startIndex + Constant.ROWS_FETCH_NEXT_SPACE + offset + " ROWS ONLY ";
		}
		SalesProjectionDAO dao = new SalesProjectionDAOImpl();
		return (List) dao.executeSelectQuery(sql);
	}

	public static final String AT_SELECTION = "@SELECTION@";
	public static final String AT_ORDER_BY = "@ORDER_BY@";

	public List getCustomerList(final GroupDTO dto, final Boolean indicator, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns, Boolean isCount, int startIndex, int offset)
			throws PortalException {
		String sql = QueryUtils.getQuery(getGroupInput(dto, indicator), "getCustGroupSearch");
		String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, filterMap(indicator))
				.toString();
		sql = sql.replace("@FILTER@", filterQuery.replace("where", " AND "));
		if (isCount) {
			sql = sql.replace(AT_ORDER_BY, " ");
			sql = sql.replace(AT_SELECTION, " COUNT (DISTINCT IG.COMPANY_GROUP_SID)");
		} else {
			sql = sql.replace(AT_SELECTION,
					" DISTINCT IG.COMPANY_GROUP_SID, IG.COMPANY_GROUP_NO, IG.COMPANY_GROUP_NAME, IG.VERSION_NO, IG.COMPANY_GROUP_DESCRIPTION  ");
			String sortingQuery = AbstractFilterLogic.getInstance()
					.orderByQueryGenerator(sortByColumns, filterMap(indicator)).toString();
			sql = sql.replace(AT_ORDER_BY, sortingQuery);
			sql = sql + " OFFSET " + startIndex + Constant.ROWS_FETCH_NEXT_SPACE + offset + " ROWS ONLY ";
		}
		SalesProjectionDAO dao = new SalesProjectionDAOImpl();
		return (List) dao.executeSelectQuery(sql);
	}

	public Map<String, String> filterMap(final Boolean isCompanyGroup) {
		Map<String, String> filterMap = new HashMap<>();
		if (!isCompanyGroup) {
			filterMap.put("productGroupName", "IG.ITEM_GROUP_NAME");
			filterMap.put("productGroupNo", "IG.ITEM_GROUP_NO");
			filterMap.put("company", "COMPANY_NAME");
			filterMap.put(StringUtils.EMPTY, "IG.ITEM_GROUP_SID");
			filterMap.put("productgroupDescription", "IG.ITEM_GROUP_DESCRIPTION");
		} else {
			filterMap.put("customerGroupName", "IG.COMPANY_GROUP_NAME");
			filterMap.put("customerGroupNo", "IG.COMPANY_GROUP_NO");
			filterMap.put(StringUtils.EMPTY, "IG.COMPANY_GROUP_SID");
			filterMap.put("customergroupDescription", "IG.COMPANY_GROUP_DESCRIPTION");
		}
		return filterMap;
	}

	private List getGroupInput(final GroupDTO dto, final Boolean isCompanyGroup) {
		List inputList = new ArrayList();
		if (isCompanyGroup) {
			if (dto.getCustomerGroupNo() != null && !dto.getCustomerGroupNo().isEmpty()) {
				inputList.add(dto.getCustomerGroupNo().replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
			} else {
				inputList.add(CommonUtils.CHAR_PERCENT);
			}
			if (dto.getCustomerGroupName() != null && !dto.getCustomerGroupName().isEmpty()) {
				inputList.add(dto.getCustomerGroupName().replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
			} else {
				inputList.add(CommonUtils.CHAR_PERCENT);
			}
			if (dto.getCustomerGroupSid() != null && !dto.getCustomerGroupSid().isEmpty()) {
				String whereCond = "WHERE COMPANY_MASTER_SID IN (" + dto.getCustomerGroupSid() + ")";
				inputList.add(whereCond);
			} else {
				inputList.add(" ");
			}
		} else {
			if (dto.getProductGroupNo() != null && !dto.getProductGroupNo().isEmpty()) {
				inputList.add(dto.getProductGroupNo().replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
			} else {
				inputList.add(CommonUtils.CHAR_PERCENT);
			}
			if (dto.getProductGroupName() != null && !dto.getProductGroupName().isEmpty()) {
				inputList.add(dto.getProductGroupName().replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT));
			} else {
				inputList.add(CommonUtils.CHAR_PERCENT);
			}
			if (dto.getProductGroupSid() != null && !dto.getProductGroupSid().isEmpty()) {
				String whereCond = "WHERE ITEM_MASTER_SID IN (" + dto.getProductGroupSid() + ")";
				inputList.add(whereCond);
			} else {
				inputList.add(" ");
			}
		}
		return inputList;
	}

	/**
	 * Used to update the Return Details Table after DataSelection Tab modification
	 *
	 * @param session
	 */
	public void updateReturnDetails(SessionDTO session) {
		String query1 = SQlUtil.getQuery("UPDATE_RETURN_DETAILS").replace(Constant.PROJECTION_ID_AT,
				String.valueOf(session.getProjectionId()));
		salesProjectionDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(query1, session.getCurrentTableNames()));
	}

	/**
	 * This method inserts in CCPMAP table for the selected relationshipBuilderSid
	 *
	 * @param relationshipBuilderSids
	 */
	public void dataSelectionInsert(String relationshipBuilderSids) {
		LOGGER.debug("Entering dataSelectionInsert= {}", relationshipBuilderSids);
		String query = SQlUtil.getQuery(getClass(), "nm.saveCustomerCcp");
		query = query.replace("?RBS", relationshipBuilderSids);
		salesProjectionDAO.executeBulkUpdateQuery(query);

	}

	/**
	 * Method return a list of business units.
	 *
	 * @param businessUnitId
	 * @return
	 */
	public List getBusinessUnits(int businessUnitId) {

		String query = SQlUtil.getQuery("get-business-units");
		if (businessUnitId == 0) {
			query = query.replace("AND CM.COMPANY_MASTER_SID = @ORGANIZATION_KEY", StringUtils.EMPTY);
		} else {
			query = query.replace("@ORGANIZATION_KEY", StringUtils.EMPTY + businessUnitId);
		}
		return HelperTableLocalServiceUtil.executeSelectQuery(query);
	}

	/**
	 * Method return a list of companies.
	 *
	 * @param companyId
	 * @return
	 */
	public List getCompanies() {

		String query = SQlUtil.getQuery("get-companies");
		if (!companiesList.isEmpty()) {
			return Collections.unmodifiableList(companiesList);
		}
		query = query.replace("AND CM.COMPANY_MASTER_SID = @GLCOMP", StringUtils.EMPTY);
		List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
		companiesList = list;
		return list;

	}

	/**
	 * Checks for at least one active file for the given business unit.
	 *
	 * @param businessUnitSystemId
	 * @param companyMasterSystemId
	 * @return returns true if file at least one file is active else false.
	 */
	public boolean checkForActiveFiles(Object businessUnitSystemId, Object companyMasterSystemId) {
		boolean returnValue = false;

		if (businessUnitSystemId != null && companyMasterSystemId != null) {
			String query = SQlUtil.getQuery("check-for-active-file");
			query = query.replace("[?BUISNESS_UNIT]", StringUtils.EMPTY + businessUnitSystemId);
			query = query.replace("[?COMPANY]", StringUtils.EMPTY + companyMasterSystemId);

			List list = HelperTableLocalServiceUtil.executeSelectQuery(query);

			returnValue = (Integer) list.get(0) == 1;
		}

		return returnValue;
	}

	public static final String FILTER_AT = "@FILTER";

	/**
	 * Used to insert the selected Customer and product hierarchy in
	 * Projection_Cust_Hierarchy and Projection_Prod_Hierarchy
	 *
	 * @param hierarchyList
	 *            It will have the hierarchy which we selected in customer or
	 *            product selection
	 * @param queryName
	 *            It will have the name of the query we need to pick up. Query will
	 *            written in xml in th path sqlResources->DataSelectionQueries
	 * @param projectionID
	 */
	public static void hierarchyDetailsInsert(List<String> hierarchyList, final String queryName,
			final int projectionID, final boolean isDataSelectionTab) {
		StringBuilder queryBuilder = new StringBuilder();

		if (isDataSelectionTab) {
			queryBuilder.append(SQlUtil.getQuery(Constant.DELETION).replace(Constant.AT_TABLE_NAME, queryName))
					.append(" WHERE PROJECTION_MASTER_SID = ").append(projectionID);
		}
		queryBuilder.append(SQlUtil.getQuery(queryName));

		String unionAll = StringUtils.EMPTY;
		for (String relationshipLevelSid : hierarchyList) {
			queryBuilder.append(unionAll).append(SELECT_CAPS).append(projectionID).append(" ,")
					.append(relationshipLevelSid);
			unionAll = UNION_ALL;
		}
		HelperTableLocalServiceUtil.executeUpdateQuery(queryBuilder.toString());
	}

	/**
	 * Used to insert projection details table
	 *
	 * @param projectionIdValue
	 * @param currentTableNames
	 * @param isDataSelectionTab
	 */
	public static void projectionDetailsInsert(final int projectionIdValue, final GtnSmallHashMap currentTableNames,
			final boolean isDataSelectionTab) {
		String queryBuilder;
		if (isDataSelectionTab) {
			queryBuilder = SQlUtil.getQuery("PROJECTION_DETAILS_INSERT_DS_TAB")
					.replace(Constant.PROJECTION_MASTER_SID_AT, String.valueOf(projectionIdValue));
		} else {
			queryBuilder = SQlUtil.getQuery("PROJECTION_DETAILS_INSERT").replace(Constant.PROJECTION_MASTER_SID_AT,
					String.valueOf(projectionIdValue));
		}
		HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder, currentTableNames));
	}

	public static final String HIERARCHY_NO = "HIERARCHY_NO";

	/**
	 * To get the selected cust and prod values. Used in edit mode. For add mode we
	 * will get it directly from the ui container
	 *
	 * @param projectionId
	 * @param queryName
	 * @return
	 */
	public List<Leveldto> getCustandProdSelection(final int projectionId, final String queryName) {
		String sql = SQlUtil.getQuery(queryName).replace(Constant.PROJECTION_MASTER_SID_AT,
				String.valueOf(projectionId));
		List results = HelperTableLocalServiceUtil.executeSelectQuery(sql);
		List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto;
		for (int j = 0; j < results.size(); j++) {
			Object[] object = (Object[]) results.get(j);
			dto = new Leveldto();
			dto.setHierarchyNo(object[0].toString());
			dto.setRelationshipLevelValue(object[1].toString());
			dto.setLevelNo(Integer.valueOf(object[NumericConstants.TWO].toString()));
			dto.setLevel(object[NumericConstants.THREE].toString());
			resultList.add(dto);
		}
		return resultList;
	}

	/**
	 *
	 * @param relationshipBuilderSid
	 * @return
	 */
	private Map<String, List> getRelationshipDetails(SessionDTO sessionDTO, String relationshipBuilderSid,
			boolean isCustomerHierarchy) {

		String customSql = SQlUtil.getQuery("getHierarchyTableDetails");
		customSql = customSql.replace(RBSID, relationshipBuilderSid);
		customSql = customSql.replace("?RLDV",
				isCustomerHierarchy ? sessionDTO.getCustomerRelationVersion() + StringUtils.EMPTY
						: sessionDTO.getProductRelationVersion() + StringUtils.EMPTY);
		customSql = customSql.replace("?HLDV",
				isCustomerHierarchy ? sessionDTO.getCustomerHierarchyVersion() + StringUtils.EMPTY
						: sessionDTO.getProductHierarchyVersion() + StringUtils.EMPTY);
		List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);

		Map<String, List> resultMap = new LinkedHashMap<>();

		String hierarchyNoType = isCustomerHierarchy ? "CUST_HIERARCHY_NO" : "PROD_HIERARCHY_NO";

		RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid,
				hierarchyNoType, sessionDTO);
		tempList.clear();
		tempList = HelperTableLocalServiceUtil.executeSelectQuery(
				QueryUtil.replaceTableNames(bean.getFinalQuery(), sessionDTO.getCurrentTableNames()));
		for (int j = tempList.size() - 1; j >= 0; j--) {
			Object[] object = (Object[]) tempList.get(j);
			final List<Object> detailsList = new ArrayList<>();
			detailsList.add(object[1]); // Level Value
			detailsList.add(object[NumericConstants.TWO]); // Level No
			detailsList.add(object[NumericConstants.THREE]); // Level Name
			detailsList.add(object[NumericConstants.FOUR]); // RL Level Value -
															// Actual System Id
			detailsList.add(isCustomerHierarchy ? "C" : "P"); // HIERARCHY
			updateRelationShipLevelList(object, detailsList, 0);
			resultMap.put(String.valueOf(object[0]), detailsList);

			if (j == tempList.size() - 1) {
				if (isCustomerHierarchy) {
					sessionDTO.setCustomerLastLevelNo(Integer.parseInt(object[NumericConstants.THREE].toString()));
				} else {
					sessionDTO.setProductLastLevelNo(Integer.parseInt(object[NumericConstants.THREE].toString()));
				}
			}

		}
		return resultMap;
	}

	public Map<String, List> getRelationshipDetailsCustom(SessionDTO sessionDTO, String relationshipBuilderSid) {
            Map<String, List> resultMap = new LinkedHashMap<>();
            if(!"0".equals(relationshipBuilderSid)){
		String customSql = SQlUtil.getQuery("getHierarchyTableDetailsCustom");
		customSql = customSql.replace(RBSID, relationshipBuilderSid);
		List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);

		RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid,
				"customSalesCP", sessionDTO);
		tempList.clear();
		String customCCPQuery = SQlUtil.getQuery("getRelationshipCustomCCP").replace("?RBSID", relationshipBuilderSid)
				.replace("@CUSTOMSID", relationshipBuilderSid);
		tempList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil
				.replaceTableNames(customCCPQuery + bean.getCustomFinalQuery(), sessionDTO.getCurrentTableNames()));
		for (int j = tempList.size() - 1; j >= 0; j--) {
			Object[] object = (Object[]) tempList.get(j);
			final List<Object> detailsList = new ArrayList<>();
			detailsList.add(object[1]); // Level Value
			detailsList.add(object[NumericConstants.TWO]); // Level No
			detailsList.add(object[NumericConstants.THREE]); // Level Name
			detailsList.add(object[NumericConstants.FOUR]); // RL Level Value -
			detailsList.add(object[object.length - 1]); // Actual System Id
			// HIERARCHY
			updateRelationShipLevelList(object, detailsList, 1);

			resultMap.put(String.valueOf(object[0]), detailsList);

			if (j == tempList.size() - 1) {
				if (detailsList.get(detailsList.size() - 1).equals("C")) {
					sessionDTO.setCustomerLastLevelNo(Integer.parseInt(object[NumericConstants.THREE].toString()));
				} else {
					sessionDTO.setProductLastLevelNo(Integer.parseInt(object[NumericConstants.THREE].toString()));
				}
			}

		}
            }
		return resultMap;
	}

	public static Map<String, List> getRelationshipDetailsDeductionCustom(SessionDTO sessionDTO,
			String relationshipBuilderSid) {
		String customSql = SQlUtil.getQuery("getHierarchyTableDetailsDeductionCustom");
		customSql = customSql.replace(RBSID, relationshipBuilderSid);
		customSql = customSql.replace(RBVERSION, String.valueOf(sessionDTO.getDeductionRelationVersion()));
		List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);

		Map<String, List> resultMap = new HashMap<>();
		RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid,
				"customSalesDed", sessionDTO);
		tempList.clear();
		tempList = HelperTableLocalServiceUtil.executeSelectQuery(
				QueryUtil.replaceTableNames(bean.getDeductionFinalQuery(), sessionDTO.getCurrentTableNames()));
		for (int j = tempList.size() - 1; j >= 0; j--) {
			Object[] object = (Object[]) tempList.get(j);
			final List detailsList = new ArrayList();
			detailsList.add(object[1]); // Level Value
			detailsList.add(object[NumericConstants.TWO]); // Level No
			detailsList.add(object[NumericConstants.THREE]); // Level Name
			detailsList.add(object[NumericConstants.FOUR]); // RL Level Value -
															// Actual System Id
			detailsList.add("D"); // HIERARCHY INDICATOR
			updateRelationShipLevelList(object, detailsList, 0);
			resultMap.put(String.valueOf(object[0]), detailsList);
		}
		return resultMap;
	}

	/**
	 * Used to check which level is top in selected customer hierarchy either
	 * customer or contract It is used for CCP_HIERARCHY_INSERT query formation
	 *
	 * @param hierarchySid
	 * @return
	 */
	public String getTopLevelInHierarchy(final String hierarchySid) {
		List<String> list = (List<String>) HelperTableLocalServiceUtil
				.executeSelectQuery(SQlUtil.getQuery("GET_TOP_LEVEL_NAME").replace("@HIERARCHY_SID", hierarchySid));
		for (String levelName : list) {
			if ("Customer".equals(levelName) || "Trading Partner".equals(levelName)
					|| "TradingPartner".equals(levelName) || Constant.CONTRACT_SMALL.equals(levelName)) {
				return levelName;
			}
		}
		return StringUtils.EMPTY;
	}

	String prepareRelationShipQuery(final Map<String, Object> parameters, boolean isSelectOnly) {
		StringBuilder queryString = new StringBuilder();
		String query2 = SQlUtil.getQuery(getClass(), "get-lower-levels-based-on-hierarchy-no-with-projId-Select");
		if (PROJECTION_PROD_HIERARCHY.equals(parameters.get(Constant.TABLE_NAME))) {
			ArrayList<String> rlSids = (ArrayList<String>) parameters.get(RL_SIDS);
			StringBuilder hierarchyInclusion = new StringBuilder();

			for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
				hierarchyInclusion.append("HIERARCHY_NO LIKE '");
				hierarchyInclusion.append(rlSids.get(loop));
				hierarchyInclusion.append("%'");
				if (loop != (limit - 1)) {
					hierarchyInclusion.append(" OR ");
				}
			}
			String hierarchyExclusion = stringListToString(rlSids);
			String query = SQlUtil.getQuery(getClass(), "get-lower-levels-based-on-hierarchy-no-with-projId");

			query = query.replace("[?BU_COMPANY_MASTER_SID]",
					StringUtils.EMPTY + parameters.get(BUSINESS_UNIT_PROPERTY));
			query = query.replace("[?PROJECTION_MASTER_SID]",
					StringUtils.EMPTY + parameters.get(Constant.PROJECTION_ID));
			query = query.replace("[?HIERARCHY_INCLUDE]", hierarchyInclusion);
			query = query.replace("[?HIERARCHY_EXCLUDE]", hierarchyExclusion);

			if (parameters.get(Constant.MODULE) != null
					&& "cff".equalsIgnoreCase(String.valueOf(parameters.get(Constant.MODULE)))) {
				query = query.replace(Constant.HIERARCHY_TABLE, "CFF_PROD_HIERARCHY");
				query = query.replace(Constant.MASTER_TABLE_SID_COLUMN, "CFF_MASTER_SID");
				query2 = query2.replace(Constant.HIERARCHY_TABLE, "CFF_PROD_HIERARCHY");
				query2 = query2.replace(Constant.MASTER_TABLE_SID_COLUMN, "CFFl_MASTER_SID");
			} else {
				query = query.replace(Constant.HIERARCHY_TABLE, PROJECTION_PROD_HIERARCHY);
				query = query.replace(Constant.MASTER_TABLE_SID_COLUMN, "PROJECTION_MASTER_SID");
				query2 = query2.replace(Constant.HIERARCHY_TABLE, PROJECTION_PROD_HIERARCHY);
				query2 = query2.replace(Constant.MASTER_TABLE_SID_COLUMN, "PROJECTION_MASTER_SID");
			}
			queryString.append(query);
		} else {
			if (parameters.get(RL_SIDS) != null) {
				ArrayList<String> rlSids = (ArrayList<String>) parameters.get(RL_SIDS);
				if (rlSids != null && !rlSids.isEmpty()) {
					String qry = "   SELECT distinct " + String.valueOf(parameters.get(Constant.PROJECTION_ID))
							+ ",RLD.RELATIONSHIP_LEVEL_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD ";
					queryString.append(qry);

					queryString.append(" WHERE (");
					for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
						queryString.append("HIERARCHY_NO LIKE '");
						queryString.append(rlSids.get(loop));
						queryString.append("%'");
						if (loop != (limit - 1)) {
							queryString.append(" OR ");
						}
					}
					queryString.append(") AND HIERARCHY_NO NOT IN (");
					queryString.append(stringListToString(rlSids));
					queryString.append(')');
					queryString
							.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
					queryString.append(String.valueOf(parameters.get(Constant.TABLE_NAME)));

					if (parameters.get(Constant.MODULE) != null
							&& "cff".equalsIgnoreCase(String.valueOf(parameters.get(Constant.MODULE)))) {
						queryString.append(" PH WHERE PH.CFF_MASTER_SID = ");
					} else {
						queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
					}
					queryString.append(String.valueOf(parameters.get(Constant.PROJECTION_ID)));
					queryString.append(')');
				}
			}

		}
		return isSelectOnly ? query2 : queryString.toString();
	}

	public static String stringListToString(List<String> stringList) {
		StringBuilder builder = new StringBuilder();
		if (stringList != null && !stringList.isEmpty()) {
			for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
				builder.append('\'');
				builder.append(stringList.get(loop));
				builder.append('\'');
				if (loop != (limit - 1)) {
					builder.append(", ");
				}
			}
		}
		return builder.toString();
	}

	/**
	 *
	 * @param hierarchyDetailsMap
	 * @param hierarchyIndicator
	 * @return
	 */
	public int getMaximumLevelNo(Map<String, List> hierarchyDetailsMap, String hierarchyIndicator) {
		int maxLevelNo = 0;
		for (Map.Entry<String, List> entry : hierarchyDetailsMap.entrySet()) {
			int levelNo = Integer.parseInt(entry.getValue().get(NumericConstants.TWO).toString());
            if (maxLevelNo < levelNo && hierarchyIndicator.equals(entry.getValue().get(NumericConstants.FOUR))) {
                maxLevelNo = levelNo;
            }
        }
        return maxLevelNo;
    }

    public void callInsertProcedureForNm(int projectionId, SessionDTO session, String procedureName,
            String screenName) {
        StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
            query.append(procedureName);
            query.append(' ');
            query.append(projectionId);
            query.append(',');
            query.append(session.getUserId());
            query.append(",'");
            query.append(session.getSessionId());
            if (!screenName.equals(NATIONAL_ASSUMPTIONS.getConstant()) && !screenName.equals(Constant.PPA_SMALL)) {

                query.append("','");
                query.append(screenName);
            }
            query.append('\'');
            HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
            LOGGER.info("Normal Procedures: {}", query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
public void callInsertProcedureForNmDiscountMaster(int projectionId, SessionDTO session, String procedureName,
            String screenName) {
    String deductionCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
        StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
            query.append(procedureName);
            query.append(' ');
            query.append(projectionId);
            query.append(',');
            query.append(session.getUserId());
            query.append(",'");
            query.append(session.getSessionId());
            if (!screenName.equals(NATIONAL_ASSUMPTIONS.getConstant()) && !screenName.equals(Constant.PPA_SMALL)) {

                query.append("','");
                query.append(screenName);
            }
            query.append('\'')
            .append(",'").append(CommonLogic.getFrequency(session.getDsFrequency())).append('\'')
            .append(",'").append(deductionCaptionUdc).append('\'');
            LOGGER.info("before callInsertProcedureForNmDiscountMaster {}", query.toString());
            HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
            LOGGER.info("Normal Procedures: {}", query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    public void callInsertProcedureForNmStatus(int projectionId, SessionDTO session, String procedureName,
            String screenName) {

        StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
            query.append(procedureName);
            query.append(' ');
            query.append(projectionId);
            query.append(',');
            query.append(session.getUserId());
            query.append(",'");
            query.append(session.getSessionId());
            if (!screenName.equals(NATIONAL_ASSUMPTIONS.getConstant()) && !screenName.equals(Constant.PPA_SMALL)) {

                query.append("','");
                query.append(screenName);
            }
            query.append('\'');
            HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
            LOGGER.info("Query callInsertProcedureForNmStatus: {}", query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    
    public void callViewInsertProcedureForNm(SessionDTO session,String screenName,String view,String startPeriod,String endPeriod) {
        int masterSid = screenName.equalsIgnoreCase(SALES_SMALL) ? session.getCustomRelationShipSid() : session.getCustomDeductionRelationShipSid();
        String frequency = screenName.equalsIgnoreCase(SALES_SMALL) && session.getDsFrequency().equals(Constant.SEMI_ANNUALY) ? Constant.SEMI_ANNUALLY : session.getDsFrequency();
        String deductionCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
        StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
             LOGGER.debug(startPeriod);
            query.append(Constant.PRC_VIEWS_POPULATION);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId())
                                .append(",'").append(session.getSessionId()).append('\'')
                                .append(",'").append(session.getFunctionMode()).append('\'')
                                .append(",'").append(CommonLogic.getFrequency(frequency)).append('\'')
                                .append(",'").append(screenName).append('\'')
                                .append(",'").append(view).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(String.valueOf(masterSid)).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(deductionCaptionUdc)
                                .append('\'');
				HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
                                LOGGER.info("Query callViewInsertProcedureForNm: {}", query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    public void callViewInsertProcedureForPV(SessionDTO session,String screenName,String view,String startPeriod) {
        int relSid = screenName.equalsIgnoreCase(SALES_SMALL) ? session.getCustomRelationShipSid() : session.getCustomDeductionRelationShipSid();
        String freq = screenName.equalsIgnoreCase(SALES_SMALL) && session.getDsFrequency().equals(Constant.SEMI_ANNUALY) ? Constant.SEMI_ANNUALLY : session.getDsFrequency();
        String dedCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
        StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
             LOGGER.debug(startPeriod);
            query.append(Constant.PRC_VIEWS_POPULATION);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId())
                                .append(",'").append(session.getSessionId()).append('\'')
                                .append(",'").append('G').append('\'')
                                .append(",'").append(CommonLogic.getFrequency(freq)).append('\'')
                                .append(",'").append(screenName).append('\'')
                                .append(",'").append(view).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(String.valueOf(relSid)).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(dedCaptionUdc)
                                .append('\'');
				HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
                                LOGGER.info("Query callViewInsertProcedureForPV: {}", query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    public String callViewInsertProcedures(SessionDTO session,String screenName,String view,String startPeriod,String endPeriod,String massUpdateField) {
     int deductionMasterSid = screenName.equalsIgnoreCase(SALES_SMALL) ? session.getCustomRelationShipSid() : session.getCustomDeductionRelationShipSid();
     String frequencyValue = screenName.equalsIgnoreCase(SALES_SMALL) && session.getDsFrequency().equals(Constant.SEMI_ANNUALY) ? Constant.SEMI_ANNUALLY : session.getDsFrequency();
     String updateUnitField="Unit Volume".equals(massUpdateField)?"UNITS":massUpdateField;
     String deductionCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
     LOGGER.info("nmSalesInsertDiscMasterProcedure**************************************{}");
         StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
            query.append(Constant.PRC_VIEWS_POPULATION);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId())
                                .append(",'").append(session.getSessionId()).append('\'')
                                .append(",'").append(session.getFunctionMode()).append('\'')
                                .append(",'").append(CommonLogic.getFrequency(frequencyValue)).append('\'')
                                .append(",'").append(screenName).append('\'')
                                .append(",'").append(view).append('\'')
                                .append(",'").append(startPeriod).append('\'')
                                .append(",'").append(endPeriod).append('\'')
                                .append(",'").append(updateUnitField).append('\'')
                                .append(",'").append(deductionMasterSid).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(deductionCaptionUdc).append('\'')
                                .append(';');
                                HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
                                LOGGER.info(QUERY_CALL_VIEW_INSERT_PROCEDURES, query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return query.toString();

    }
    public void callViewInsertProcedureForUOM(SessionDTO session,String screenName,String view,String startPeriod) {
        int masterSid = screenName.equalsIgnoreCase(SALES_SMALL) ? session.getCustomRelationShipSid() : session.getCustomDeductionRelationShipSid();
        String frequency = screenName.equalsIgnoreCase(SALES_SMALL) && session.getDsFrequency().equals(Constant.SEMI_ANNUALY) ? Constant.SEMI_ANNUALLY : session.getDsFrequency();
        String deductionCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
        StringBuilder query = new StringBuilder(EXEC_WITH_SPACE);
        try {
             LOGGER.debug(startPeriod);
            query.append(Constant.PRC_VIEWS_POPULATION);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId())
                                .append(",'").append(session.getSessionId()).append('\'')
                                .append(",'").append(UOM).append('\'')
                                .append(",'").append(CommonLogic.getFrequency(frequency)).append('\'')
                                .append(",'").append(screenName).append('\'')
                                .append(",'").append(view).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(String.valueOf(masterSid)).append('\'')
                                .append(',').append("null")
                                .append(',').append("null")
                                .append(",'").append(session.getUomCode()).append('\'')
                                .append(',').append("null")
                                .append(",'").append(deductionCaptionUdc)
                                .append('\'');
				HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
                                LOGGER.info("Query callViewInsertProcedureForUOM: {}", query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    
    
    public String callViewInsertProceduresString(SessionDTO session,String screenName,String view,String startPeriod,String endPeriod,String massUpdateField) {
        StringBuilder query = new StringBuilder();
        String frequencyValue = session.getDsFrequency().equals(Constant.SEMI_ANNUALY) ? Constant.SEMI_ANNUALLY : session.getDsFrequency();
        String deductionCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
        try {
            query.append(EXEC_WITH_SPACE);
            query.append(Constant.PRC_VIEWS_POPULATION);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId())
                                .append(",'").append(session.getSessionId()).append('\'')
                                .append(",'").append(session.getFunctionMode()).append('\'')
                                .append(",'").append(CommonLogic.getFrequency(frequencyValue)).append('\'')
                                .append(",'").append(screenName).append('\'')
                                .append(",'").append(view).append('\'')
                                .append(",'").append(startPeriod).append('\'')
                                .append(",'").append(endPeriod).append('\'')
                                .append(",'").append(massUpdateField).append('\'')
                                .append(',').append(session.getCustomRelationShipSid()).append("")
                                .append(",'").append("").append('\'')
                                .append(",'").append("").append('\'')
                                .append(",'").append("").append('\'')
                                .append(',').append("null")
                                .append(",'").append(deductionCaptionUdc).append('\'')
                                .append(';');
                                LOGGER.info(QUERY_CALL_VIEW_INSERT_PROCEDURES, query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return query.toString();

    }
    
    public String callViewInsertProceduresString(SessionDTO session,String frequency,String screenName,String view,String startPeriod,String endPeriod,String massUpdateField) {
     LOGGER.info("nmSalesInsertDiscMasterProcedure**************************************{}", frequency);
        String truncateQuery="TRUNCATE TABLE ST_CUSTOM_SALES";
        StringBuilder query = new StringBuilder(QueryUtil.replaceTableNames(truncateQuery, session.getCurrentTableNames()));
        String deductionCaptionUdc = session.getDataSelectionDeductionLevelCaption().startsWith("UDC") ? session.getDataSelectionDeductionLevelCaption().replace(" ", StringUtils.EMPTY) : session.getDataSelectionDeductionLevelCaption();
        try {
            query.append(EXEC_WITH_SPACE);
            query.append(Constant.PRC_VIEWS_POPULATION);
				query.append(' ').append(session.getProjectionId()).append(',');
				query.append(session.getUserId())
                                .append(",'").append(session.getSessionId()).append('\'')
                                .append(",'").append(session.getFunctionMode()).append('\'')
                                .append(",'").append(CommonLogic.getFrequency(session.getDsFrequency())).append('\'')
                                .append(",'").append(screenName).append('\'')
                                .append(",'").append(view).append('\'')
                                .append(",'").append(startPeriod).append('\'')
                                .append(",'").append(endPeriod).append('\'')
                                .append(",'").append(massUpdateField).append('\'')
                                .append(',').append(session.getCustomRelationShipSid()).append("")
                                .append(",'").append("").append('\'')
                                .append(",'").append("").append('\'')
                                .append(",'").append("").append('\'')
                                .append(',').append("null")
                                .append(",'").append(deductionCaptionUdc).append('\'')
                                .append(';');
                                LOGGER.info(QUERY_CALL_VIEW_INSERT_PROCEDURES, query.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return query.toString();

    }

	public void callViewInsertProceduresThread(SessionDTO session, String screenName, String startPeriod,
			String endPeriod, String massUpdateField) {
        LOGGER.info("callViewInsertProceduresThread************************************{}");
		ThreadPool service = ThreadPool.getInstance();
		service.submitRunnable(commonUtil.createRunnable(Constant.FUNCTION_PRC_VIEWS_CALL, session, screenName, "C",
				startPeriod, endPeriod, massUpdateField));
		service.submitRunnable(commonUtil.createRunnable(Constant.FUNCTION_PRC_VIEWS_CALL, session, screenName, "P",
				startPeriod, endPeriod, massUpdateField));
		service.submitRunnable(commonUtil.createRunnable(Constant.FUNCTION_PRC_VIEWS_CALL, session, screenName, "U",
				startPeriod, endPeriod, massUpdateField));
    }

    public void nmDiscountInsertProcedure(SessionDTO session) {
		ThreadPool discountService = ThreadPool.getInstance();
        if (!Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            session.addFutureMap(Constant.DISCOUNT_MASTER_PROCEDURE_CALL,
					new Future[] { discountService.submitRunnable(commonUtil.createRunnable(Constant.PROCEDURE_CALL,
				SalesUtils.PRC_NM_MASTER_INSERT, session.getProjectionId(), session.getUserId(),
							session.getSessionId(), Constant.DISCOUNT3, session)) });
			commonUtil.waitsForOtherThreadsToComplete(session.getFutureValue(Constant.DISCOUNT_MASTER_PROCEDURE_CALL));
            session.addFutureMap(Constant.DISCOUNT_PROCEDURE_CALL,
					new Future[] {
							discountService.submitRunnable(commonUtil.createRunnable(Constant.PROCEDURE_CALL,
									SalesUtils.PRC_NM_ACTUAL_INSERT, session.getProjectionId(), session.getUserId(),
									session.getSessionId(), Constant.DISCOUNT3, session)),
							discountService.submitRunnable(commonUtil.createRunnable(Constant.PROCEDURE_CALL,
									SalesUtils.PRC_NM_PROJECTION_INSERT, session.getProjectionId(), session.getUserId(),
									session.getSessionId(), Constant.DISCOUNT3, session)) });
            }
	}
    
	/**
	 * Used to insert accrual projection details table
	 *
	 * @param projectionIdValue
	 * @param currentTableNames
	 * @param isDataSelectionTab
	 */
	public static void accrualDetailsInsert(final int projectionIdValue, final GtnSmallHashMap currentTableNames) {
		String queryBuilder = SQlUtil.getQuery("save-accrual-proj-details").replace(Constant.PROJECTION_MASTER_SID_AT,
				String.valueOf(projectionIdValue));
		HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder, currentTableNames));
	}

	public void saveCurrenctActiveFile(SessionDTO session) {

		String query = SQlUtil.getQuery("PFD_MAIN_TO_TEMP_INSERT");
		query = query.replace("@PROJECTIONMASTERSID", String.valueOf(session.getProjectionId()));
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
	}

	public void updateProjectionNameAndProjectionDescription(SessionDTO session) {
		String query = SQlUtil.getQuery("UPDATE_PROJECTION_NAME_AND_PROJECTION_DESC");
		query = query.replace("@PROJECTIONMASTER_NAME", String.valueOf(session.getProjectionName()));
		query = query.replace("@PROJECTIONMASTER_DESC", String.valueOf(session.getDescription()));
		query = query.replace("@PROJECTIONMASTERSID", String.valueOf(session.getProjectionId()));
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
	}

	public int isFileChanged(SessionDTO session) {
		int fileStatus = 1;
		StringBuilder name = new StringBuilder();
		String inputQuery = "SELECT BUSINESS_UNIT,COMPANY_MASTER_SID from PROJECTION_MASTER WHERE PROJECTION_MASTER_SID ="
				+ session.getProjectionId();
		List<Object[]> inputList = HelperTableLocalServiceUtil.executeSelectQuery(inputQuery);
		String query = SQlUtil.getQuery("Check_New_File_isActivated")
				.replace(Constant.PROJECTION_ID_AT, String.valueOf(session.getProjectionId()))
				.replace("@BUSINESSUNIT", String.valueOf(inputList.get(0)[0]))
				.replace("@COMPANYID", String.valueOf(inputList.get(0)[1]));
		List list = HelperTableLocalServiceUtil
				.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				name.append(String.valueOf(obj[1])).append(" - ").append(String.valueOf(obj[3])).append(',');
				fileStatus = 0;
			}
			session.setFileName(!name.toString().isEmpty() ? name.substring(0, name.length() - 1) : StringUtils.EMPTY);
			session.setFileStatus(fileStatus);
			return fileStatus;
		} else {
			session.setFileStatus(fileStatus);
			return fileStatus;
		}
	}

	public void loadProjectionFileDetailsTabInGenerate(final SessionDTO session) {
		List<Object> inputList = new ArrayList();
		inputList.add(session.getProjectionId());
		inputList.add(IndicatorConstants.INDICATOR_MODULE_NON_MANDATED.getConstant());
		inputList.add(session.getUserId());
		inputList.add(session.getSessionId());
		inputList.add(session.getProjectionId());
		QueryUtils.updateAppDataUsingSessionTables(inputList, "PFD_TEMP_INSERT_WHILE_GENERATE", session);
	}

	private static void updateRelationShipLevelList(Object[] object, List<Object> detailsList, int extraColumnIndex) {
		if (object.length >= 5) {
			List<Object> displayFormat = new ArrayList<>();
			for (int i = 5; i < object.length - extraColumnIndex; i++) {
				displayFormat.add(object[i]);
			}
			detailsList.add(displayFormat);
		}
	}

	public void deleteFromTempCCPTable(final SessionDTO session) {
		String finalQuery = QueryUtil.replaceTableNames(
				QueryUtils.getQuery(Collections.emptyList(), "deleteTempCCPTable"), session.getCurrentTableNames());
		salesProjectionDAO.executeBulkUpdateQuery(finalQuery);

	}

	public Date getDefaultEligibleDateFromForecastConfiguration() {
		String query = "SELECT  PROJECTION_START_DATE FROM   [Udf_na_proj_dates]('Commercial')";
		List forecastEligibleDate = (List) salesProjectionDAO.executeSelectQuery(query);
		return forecastEligibleDate != null && !forecastEligibleDate.isEmpty() ? (Date) forecastEligibleDate.get(0)
				: null;
	}

	public String getremovedcontractbasedonEligibleDate(final SessionDTO session) {
		List<Object> inputList = new ArrayList();
		inputList.add(session.getProjectionId());
            List<Object[]> removedcontract = QueryUtils.getAppData(inputList, "eligibledatealertquery",null);
            return   removedcontract.toString().replace("[", "").replace("]", "");
        }
        
    public Date getWorkflowEligibleDateFromProjMaster(final DataSelectionDTO dataSelectionDTO) {
        String datequery = "SELECT FORECAST_ELIGIBLE_DATE FROM PROJECTION_MASTER where PROJECTION_MASTER_SID=" + dataSelectionDTO.getProjectionId() + "";
        List workflowforecastEligibleDate = (List) salesProjectionDAO.executeSelectQuery(datequery);
        return workflowforecastEligibleDate != null ? (Date) workflowforecastEligibleDate.get(0) : null;
    }

    public void loadCustomViewValues(ComboBox customRelationDdlb, Map<String, String> dataMap,boolean isDataSelection) {
        if (dataMap.get(CUSTOMER_SID_LITERAL) != null && dataMap.get(CUSTVER) != null && dataMap.size()>3) {
            String sqlQuery = QueryUtils.getQuery(Collections.emptyList(),"loadCustomRelationValues");
            sqlQuery = sqlQuery.replace(CUSTOMER_SID_VARIABLE, dataMap.get(CUSTOMER_SID_LITERAL)).replace(PRODUCT_SID_VARIABLE, dataMap.get(PROD_SID_LITERAL))
                    .replace(CUSTVER_VARIABLE, dataMap.get(CUSTVER)).replace(PRODVER_VARIABLE, dataMap.get(PRODVER));
            List<Object[]> queryList = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);

            for (Object[] objects : queryList) {
                customRelationDdlb.addItem(objects[0]);
                customRelationDdlb.setItemCaption(objects[0], String.valueOf(objects[1]));
            }
            if (!queryList.isEmpty()  && isDataSelection) {
                customRelationDdlb.setValue(queryList.get(0)[0]);
            }
        }
    }
    
    public void loadCustomViewDeductionValues(ComboBox customDeductionRelationDdlb, Map<String, String> dataDeductionMap,boolean isDataSelection) {
    	
    	int compareSizeValue = 3;
        if (dataDeductionMap.get(CUSTOMER_SID_LITERAL) != null && dataDeductionMap.get(CUSTVER) != null && dataDeductionMap.size()>compareSizeValue) {
            String discountSqlQuery = QueryUtils.getQuery(Collections.emptyList(),"loadCustomDeductionRelationValues");
            discountSqlQuery = discountSqlQuery.replace(CUSTOMER_SID_VARIABLE, dataDeductionMap.get(CUSTOMER_SID_LITERAL)).replace(PRODUCT_SID_VARIABLE, dataDeductionMap.get(PROD_SID_LITERAL))
                    .replace(CUSTVER_VARIABLE, dataDeductionMap.get(CUSTVER)).replace(PRODVER_VARIABLE, dataDeductionMap.get(PRODVER));
            List<Object[]> queryDiscountList = HelperTableLocalServiceUtil.executeSelectQuery(discountSqlQuery);

            for (Object[] objects : queryDiscountList) {
                customDeductionRelationDdlb.addItem(objects[0]);
                customDeductionRelationDdlb.setItemCaption(objects[0], String.valueOf(objects[1]));
            }
            if (!queryDiscountList.isEmpty() && isDataSelection) {
                customDeductionRelationDdlb.select(queryDiscountList.get(0)[0]);
            }
        }
    }
    
      public void nmSalesViewsPopulationProcedure(SessionDTO session) {
        String query = SQlUtil.getQuery("ViewTableTruncationSales");
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		LOGGER.info("nmSalesViewsPopulationProcedure Truncate Query{}",
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		ThreadPool serviceViewPopulation = ThreadPool.getInstance();
		serviceViewPopulation.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL,
						session.getFunctionMode(), Constant.SALES1, "C", "", "", session));
		serviceViewPopulation.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_SALES_POPULATION_CALL,
						session.getFunctionMode(), Constant.SALES1, "P", "", "", session));
		serviceViewPopulation.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_SALES_POPULATION_CALL,
						session.getFunctionMode(), Constant.SALES1, "U", "", "", session));
    }

    public void nmSalesViewsPopulationProcedureWithoutTruncation(SessionDTO session) {
		ThreadPool serviceViewPopulationWithoutTruncation = ThreadPool.getInstance();
		serviceViewPopulationWithoutTruncation.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL,
						session.getFunctionMode(), Constant.SALES1, "C", "", "", session));
		serviceViewPopulationWithoutTruncation.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_SALES_POPULATION_CALL,
						session.getFunctionMode(), Constant.SALES1, "P", "", "", session));
		serviceViewPopulationWithoutTruncation.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_SALES_POPULATION_CALL,
						session.getFunctionMode(), Constant.SALES1, "U", "", "", session));
    }  
      
      public void nmSalesViewsPopulationProcedureUOM(SessionDTO session) {
        LOGGER.info("nmSalesViewsPopulationProcedure For UOM");
		ThreadPool serviceViewPopulationProcedureUOM = ThreadPool.getInstance();
		CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.SALES);
		serviceViewPopulationProcedureUOM.submitRunnable(commonUtil.createRunnable(Constant.FUNCTION_PRC_VIEWS_CALL_UOM,
				Constant.CUSTOMER_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "C", "", "",
				session));
		serviceViewPopulationProcedureUOM.submitRunnable(commonUtil.createRunnable(Constant.FUNCTION_PRC_VIEWS_CALL_UOM,
				Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "P", "", "",
				session));
		serviceViewPopulationProcedureUOM.submitRunnable(commonUtil.createRunnable(Constant.FUNCTION_PRC_VIEWS_CALL_UOM,
				Constant.PRODUCT_VIEW_SALES_POPULATION_CALL, session.getFunctionMode(), Constant.SALES1, "U", "", "",
				session));
    }

    public void nmDiscountViewsPopulationProcedure(SessionDTO session) {
		ThreadPool serviceDiscountViewsPopulationProcedure = ThreadPool.getInstance();
        String query = SQlUtil.getQuery("ViewTableTruncationDiscount");
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		LOGGER.info("nmDiscountViewsPopulationProcedure Truncate Query{}",
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.DISCOUNT3);
		serviceDiscountViewsPopulationProcedure.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL,
						session.getFunctionMode(), Constant.DISCOUNT3, "C", "null", "null", session));
		serviceDiscountViewsPopulationProcedure.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_DISCOUNT_POPULATION_CALL,
						session.getFunctionMode(), Constant.DISCOUNT3, "P", "null", "null", session));
		serviceDiscountViewsPopulationProcedure.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.CUSTOM_VIEW_DISCOUNT_POPULATION_CALL,
						session.getFunctionMode(), Constant.DISCOUNT3, "U", "null", "null", session));
    }

    public void nmDiscountViewsPopulationProcedureForUPS(SessionDTO session) {
		ThreadPool serviceDiscountViewsPopulationProcedureForUPS = ThreadPool.getInstance();
		CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.DISCOUNT3);
		serviceDiscountViewsPopulationProcedureForUPS.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.CUSTOMER_VIEW_DISCOUNT_POPULATION_CALL,
						session.getFunctionMode(), Constant.DISCOUNT3, "C", "null", "null", session));
		serviceDiscountViewsPopulationProcedureForUPS.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_DISCOUNT_POPULATION_CALL,
						session.getFunctionMode(), Constant.DISCOUNT3, "P", "null", "null", session));
		serviceDiscountViewsPopulationProcedureForUPS.submitRunnable(
				commonUtil.createRunnable(Constant.PRC_VIEWS_CALL, Constant.CUSTOM_VIEW_DISCOUNT_POPULATION_CALL,
						session.getFunctionMode(), Constant.DISCOUNT3, "U", "null", "null", session));
    }

    public void nmPvViewsPopulationProcedure(SessionDTO session) {
		ThreadPool serviceNmPvViewsPopulationProcedure = ThreadPool.getInstance();
        String query = SQlUtil.getQuery("ViewTableTruncationVariance");
		HelperTableLocalServiceUtil
				.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		LOGGER.info("nmPvViewsPopulationProcedure Truncate Query{}",
				QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
		CommonLogic.updateFlagStatusToRForAllViewsDiscount(session, Constant.VARIANCE_SCREEN);
        session.addFutureMap(Constant.CUSTOMER_VIEW_VARIANCE_POPULATION_CALL,
				new Future[] { serviceNmPvViewsPopulationProcedure.submitRunnable(commonUtil.createRunnable(
						Constant.PV_PRC_VIEWS_CALL, Constant.CUSTOMER_VIEW_VARIANCE_POPULATION_CALL,
						Constant.GENERATE_FLAG, Constant.VARIANCE_SCREEN, "C", "null", "null", session)) });
		serviceNmPvViewsPopulationProcedure.submitRunnable(
				commonUtil.createRunnable(Constant.PV_PRC_VIEWS_CALL, Constant.PRODUCT_VIEW_VARIANCE_POPULATION_CALL,
						Constant.GENERATE_FLAG, Constant.VARIANCE_SCREEN, "P", "null", "null", session));
		serviceNmPvViewsPopulationProcedure.submitRunnable(
				commonUtil.createRunnable(Constant.PV_PRC_VIEWS_CALL, Constant.CUSTOM_VIEW_VARIANCE_POPULATION_CALL,
						Constant.GENERATE_FLAG, Constant.VARIANCE_SCREEN, "U", "null", "null", session));
    }
    
    public void loadCustomViewValuesDeduction(ComboBox customRelationDdlb, Map<String, String> dataMap) {
        if (dataMap.get(CUSTOMER_SID_LITERAL) != null && dataMap.get(CUSTVER) != null) {
			String sqlQuery = QueryUtils.getQuery(Collections.emptyList(), "loadCustomDeductionRelationValues");
			sqlQuery = sqlQuery.replace(CUSTOMER_SID_VARIABLE, dataMap.get(CUSTOMER_SID_LITERAL))
					.replace(PRODUCT_SID_VARIABLE, dataMap.get(PROD_SID_LITERAL))
                    .replace(CUSTVER_VARIABLE, dataMap.get(CUSTVER)).replace(PRODVER_VARIABLE, dataMap.get(PRODVER));
            List<Object[]> queryList = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);

            for (Object[] objects : queryList) {
                LOGGER.info(objects[0].toString());
                customRelationDdlb.addItem(objects[0]);
                customRelationDdlb.setItemCaption(objects[0], String.valueOf(objects[1]));
            }
        }
    }
    
    public void callForDeductionLevelMapQuery(SessionDTO session) {
        try {
            if (session.getCustomDeductionRelationShipSid() != 0) {
                session.setDiscountDeductionLevelDetails(getRelationshipDetailsDeductionCustom(session, String.valueOf(session.getCustomDeductionRelationShipSid())));
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }
    
    public void saveFreqAndDedLevel(int projectionIdValue, DataSelectionDTO dataselectionDtoToSave) {
        Map map = new HashMap();
        map.put(Constant.FREQUENCY, dataselectionDtoToSave.getFrequency());
        map.put(Constant.DATA_SELECTION_DED_LEVEL, dataselectionDtoToSave.getDataSelectionDeductionLevelSid());
        CommonLogic.saveProjectionSelection(map, projectionIdValue, DATA_SELECTION_LANDING_SCREEN.getConstant());
    }
    
    
    
    private GtnReportHierarchyLevelBean getSelectedHierarchyLevelBean(Leveldto selectedHierarchyLevelDto) {
         GtnReportHierarchyLevelBean selectedHierarchyLevelBean = new GtnReportHierarchyLevelBean();
          
         selectedHierarchyLevelBean.setFieldName(selectedHierarchyLevelDto.getFieldName()==null ? "" : selectedHierarchyLevelDto.getFieldName());
         selectedHierarchyLevelBean.setHierarchyDefSid(selectedHierarchyLevelDto.getHierarchyId() == null ? 0 : selectedHierarchyLevelDto.getHierarchyId());
         selectedHierarchyLevelBean.setHierarchyLevelDefSid(selectedHierarchyLevelDto.getHierarchyLevelDefnId() == null ? "" : selectedHierarchyLevelDto.getHierarchyLevelDefnId());
         selectedHierarchyLevelBean.setHierarchyNo(selectedHierarchyLevelDto.getHierarchyNo() == null ? "" : selectedHierarchyLevelDto.getHierarchyNo());
         selectedHierarchyLevelBean.setHierarchyType(selectedHierarchyLevelDto.getHierarchyType() == null ? "" : selectedHierarchyLevelDto.getHierarchyType());
         selectedHierarchyLevelBean.setHierarchyVersionNo(selectedHierarchyLevelDto.getHierarchyVersionNo() == null ? 0:selectedHierarchyLevelDto.getHierarchyVersionNo());
         selectedHierarchyLevelBean.setLevel(selectedHierarchyLevelDto.getLevel() == null ? "" : selectedHierarchyLevelDto.getLevel());
         selectedHierarchyLevelBean.setLevelNo(selectedHierarchyLevelDto.getLevelNo() == null ? 0 : selectedHierarchyLevelDto.getLevelNo());
         selectedHierarchyLevelBean.setLevelValueReference(selectedHierarchyLevelDto.getLevelValueReference() == null ? "" : selectedHierarchyLevelDto.getLevelValueReference());
         selectedHierarchyLevelBean.setRelationShipBuilderId(selectedHierarchyLevelDto.getRelationShipBuilderId() == null ? "" :selectedHierarchyLevelDto.getRelationShipBuilderId() );
         selectedHierarchyLevelBean.setRelationShipSid(selectedHierarchyLevelDto.getRelationShipSid() == null ? "" : selectedHierarchyLevelDto.getRelationShipSid());
         selectedHierarchyLevelBean.setRelationshipLevelSid(selectedHierarchyLevelDto.getRelationshipLevelSid() == null ? 0 :selectedHierarchyLevelDto.getRelationshipLevelSid());
         selectedHierarchyLevelBean.setRelationshipLevelValue(selectedHierarchyLevelDto.getRelationshipLevelValue() == null ? "" : selectedHierarchyLevelDto.getRelationshipLevelValue());
         selectedHierarchyLevelBean.setTableName(selectedHierarchyLevelDto.getTableName() == null ? "" : selectedHierarchyLevelDto.getTableName());
         
         return selectedHierarchyLevelBean;
    }
}