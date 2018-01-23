/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.NATIONAL_ASSUMPTIONS;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil.executeSelectQuery;
import static com.stpl.ifs.util.constants.GlobalConstants.getCommercialConstant;
import static com.stpl.ifs.util.constants.GlobalConstants.getGovernmentConstant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

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
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractFilterLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CcpDetails;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.Constants.IndicatorConstants;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.ui.TreeTable;

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
	public static final String SELECT_CAPS = " SELECT ";
	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionLogic.class);
	private int discountDdlbCount = 0;
	private final CommonDAO salesProjectionDAO = new CommonDAOImpl();
	public static final String LEVEL_NO = "levelNo";
	public static final String RELATIONSHIP_SID = "relationshipSid";
	public static final String BUSINESS_UNIT_PROPERTY = "businessUnit";
	public static final String SELECTION_AT = "@SELECTION";
	private List companiesList = new ArrayList<>();
	private final RelationShipFilterLogic relationLogic = RelationShipFilterLogic.getInstance();

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

		List result = dataSelectionDao.getHierarchyGroup(tempHierarchyName, tempHierarchyType, customerOrProduct,
				action);

		HierarchyLookupDTO hdto;
		for (int i = 0; i < result.size(); i++) {
			hdto = new HierarchyLookupDTO();
			final Object[] obj = (Object[]) result.get(i);
			hdto.setHierarchyId(Integer.valueOf(obj[0].toString()));
			hdto.setHierarchyName(String.valueOf(obj[1].toString()));
			hdto.setHighestLevel(String.valueOf(obj[NumericConstants.THREE].toString()));
			hdto.setLowestLevel(String.valueOf(obj[NumericConstants.FOUR].toString()));
			hdto.setCreatedDate(String.valueOf(obj[NumericConstants.FIVE].toString()));
			hdto.setCreatedDateSearch(Converters.parseDate(String.valueOf(obj[NumericConstants.FIVE].toString())));
			if (obj[NumericConstants.SIX] != null) {
				hdto.setModifiedDate(String.valueOf(obj[NumericConstants.SIX].toString()));
				hdto.setModifiedDateSearch(Converters.parseDate(String.valueOf(obj[NumericConstants.SIX].toString())));
			}
			resultList.add(hdto);

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
		LOGGER.debug(" hierarchyName "+hierarchyName);
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
			LOGGER.error(e);
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
			LOGGER.error(e);
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
		List result;
		Map<String, Object> parameters = new HashMap<>();
		Leveldto dto;
		if (isNdc) {
			parameters.put("isNdc", Constant.TRUE);
		} else {
			parameters.put("isNdc", "false");
		}

		parameters.put("glCompId", companySID);
		parameters.put(BUSINESS_UNIT_PROPERTY, businessUnitSID);
		parameters.put(Constant.HIERARCHYDEFINITIONSID, hierarchyId);
		parameters.put(Constant.LEVELNAME, levelName);
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
		result = dataSelectionDao.getInnerLevel(parameters);
		if (!result.isEmpty() && result != null) {
			for (int i = 0; i < result.size(); i++) {
				dto = new Leveldto();
				Object[] obj = (Object[]) result.get(i);
				dto.setLevel(String.valueOf(obj[NumericConstants.EIGHT]));
				dto.setRelationshipLevelValue(String.valueOf(obj[0]));
				dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
				dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
				dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);

		dynamicQuery.add(PropertyFactoryUtil.forName(Constant.RELATIONSHIP_BUILDER_SID)
				.in(DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class)
						.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyId))
						.setProjection(ProjectionFactoryUtil.property(Constant.RELATIONSHIP_BUILDER_SID))));

		dynamicQuery.add(RestrictionsFactoryUtil.in(LEVEL_NO, levelNo));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

		productProjectionList
				.add(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("relationshipLevelValues")));
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
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
			sids.add(Integer.parseInt(sid));
		}

		final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
		dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, sids));
		return dataSelectionDao.getCompanyMasterList(dynamicQuery);
	}

	public List<ItemMaster> getItemsFromSids(final List<String> itemSids) throws SystemException {

		List<Integer> sids = new ArrayList<>();
		List<ItemMaster> items = null;
		for (String sid : itemSids) {
			sids.add(Integer.parseInt(sid));
		}
		if (itemSids != null && !itemSids.isEmpty()) {
			final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, sids));
			items = dataSelectionDao.getItemMaster(dynamicQuery);
		}
		return items;
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
		final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class);
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
		final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class);
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
		LOGGER.debug("saveProductHierarchyLogic endLevelSids projectionId " + projectionId);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Constant.INDICATOR, "getChildLevelRLSid");
		parameters.put(RL_SIDS, endLevelSids);
		parameters.put(Constant.PROJECTION_ID, projectionId);
		parameters.put(Constant.TABLE_NAME, PROJECTION_PROD_HIERARCHY);
		parameters.put(BUSINESS_UNIT_PROPERTY, dataSelectionDTO.getBusinessUnitSystemId());
		String insertQuery = prepareRelationShipQuery(parameters, false);
		String endLevelsQuery = "";
		if (endLevelSids != null && !endLevelSids.isEmpty()) {
			endLevelsQuery = prepareRelationShipQuery(parameters, true);
		}

		try {
			if (Constant.UPDATE.equals(indicator)) {
				int listSize = addLevels.size();
				for (int i = 0; i < listSize; i++) {
					if (i == 0) {
						insertQuery += "Insert into PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID)  ";
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
						insertQuery = insertQuery
								+ "Insert into PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) ";
					}
					insertQuery += SELECT_CAPS + projectionId + " , " + levelList.get(i).getRelationshipLevelSid();
					if (i != listSize - 1) {
						insertQuery += UNION_ALL;
					}
				}
				if (insertQuery.isEmpty()) {
					insertQuery = insertQuery
							+ "Insert into PROJECTION_PROD_HIERARCHY (PROJECTION_MASTER_SID, RELATIONSHIP_LEVEL_SID) ";
				} else {
					insertQuery += UNION_ALL + endLevelsQuery;
				}
			}
			if (!insertQuery.isEmpty()) {
				HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery);
			}
		} catch (Exception e) {
			LOGGER.error(e);
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
		final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class);
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
		final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class);
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
		LOGGER.debug("saveCustomerHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId "
				+ projectionId);
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
			LOGGER.error(e);
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
		final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
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
				dto.setLevelNo(Integer.parseInt(String.valueOf(resultSet.getObject(1))));
				dto.setTableName(String.valueOf(resultSet.getObject(NumericConstants.TWO)));
				dto.setFieldName(String.valueOf(resultSet.getObject(NumericConstants.THREE)));
				dto.setLevelValueReference(String.valueOf(resultSet.getObject(NumericConstants.FOUR)));
				resultList.add(dto);
			}

			resultss.add(resultList);
		} catch (Exception ex) {
			LOGGER.error(ex);
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
				dto.setLevelNo(Integer.parseInt(String.valueOf(resultSet.getObject(1))));
				dto.setRelationshipLevelValue(String.valueOf(resultSet.getObject(NumericConstants.TWO)));
				dto.setParentNode(String.valueOf(resultSet.getObject(NumericConstants.THREE)));
				dto.setLevel(String.valueOf(resultSet.getObject(NumericConstants.FOUR)));
				resultList.add(dto);
			}

			resultss.add(resultList);
		} catch (Exception ex) {
			LOGGER.error(ex);
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
				dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
				dto.setRelationshipLevelValue(String.valueOf(objects[1]));
				dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
				dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
				dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
				dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
				dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
				dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
				resultList.add(dto);
			}

		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(ex);
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
			dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
			dto.setRelationshipLevelValue(String.valueOf(objects[1]));
			dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
			dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
			dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
			dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
			dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
			dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
			dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
			dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
		} catch (SystemException | NumberFormatException ex) {
			LOGGER.error(ex);

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
			java.util.logging.Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
		}
		return user;
	}

	public String deleteProjection(int projectionId, String currentUserId, String screenName) {
		LOGGER.debug("Enters deleteProjection with " + projectionId);
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
			LOGGER.error(e);
			return str;
		}
		return str;
	}

	public List<String> getCustomerGroupDetails(int companyGroupSid) throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);

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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);

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
			DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMP_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
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
					companyDdlbDto = new CompanyDdlbDto(Integer.valueOf(String.valueOf(objects[0])),
							String.valueOf(objects[1]));
					companies.add(companyDdlbDto);
				}
			} else {
				for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
					Object[] objects = returnlist.get(loop);
					if ((Integer.valueOf(String.valueOf(objects[0]))) == selectedCompanyDdlbDto.getCompanyMasterSid()) {
						selectedCompanyDdlbDto.setCompanyName(String.valueOf(objects[1]));
						companies.add(selectedCompanyDdlbDto);
					} else {
						companyDdlbDto = new CompanyDdlbDto(Integer.valueOf(String.valueOf(objects[0])),
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
			DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMP_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
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
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
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
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class);
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
			sidList.add(Integer.parseInt(String.valueOf(sid)));
		}
		return sidList;
	}

	public List<Integer> getItemSidsFromAllBrand() throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
		dynamicQuery.add(PropertyFactoryUtil.forName(Constant.BRAND_MASTER_SID).in(DynamicQueryFactoryUtil
				.forClass(BrandMaster.class).setProjection(ProjectionFactoryUtil.property(Constant.BRAND_MASTER_SID))));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		List resultList = dataSelectionDao.getItemMaster(dynamicQuery);
		List<Integer> returnList = new ArrayList<>();
		for (Object sid : resultList) {
			returnList.add(Integer.parseInt(String.valueOf(sid)));
		}
		return returnList;
	}

	public List<ItemMaster> getItemMaster(final List<String> itemSidsFromHierarchy) throws SystemException {
		List<ItemMaster> resultList = null;
		if (itemSidsFromHierarchy != null && !itemSidsFromHierarchy.isEmpty()) {
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
			dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID,
					UiUtils.convertStringListToIngeter(itemSidsFromHierarchy)));
			resultList = dataSelectionDao.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public ForecastConfig getTimePeriod(String screenName) throws PortalException, SystemException {
		List<ForecastConfig> resultList = null;
		int businessProcessType = 0;
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
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
		parameters.put(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid);
		List<Object> resultList = dataSelectionDao.getCcpMap(parameters);
		for (Object rbSid : resultList) {
			returnList.add(String.valueOf(rbSid));
		}
		return returnList;
	}

	public void insertToCcpMap(List<String> relationshipBuilderSids, String screenName) throws SystemException {
		List<String> relationshipBuilderSidsList = null;
		Map<String, Object> parameters = new HashMap<>();
		if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
			relationshipBuilderSidsList = new ArrayList<>(relationshipBuilderSids);
		}
		parameters.put(Constant.INDICATOR, "insertToCcpMap");
		parameters.put("relationshipBuilderSids", relationshipBuilderSidsList);
		parameters.put("scrennName", screenName);
		dataSelectionDao.saveCcp(parameters);

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
					dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
					dto.setRelationshipLevelValue(String.valueOf(objects[1]));
					dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
					dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
					dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
					dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
					dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
					dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
					dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
					dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
					if (descriptionMap != null) {
						dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
					}
					resultList.add(dto);
				}
			}
		} catch (NumberFormatException ex) {
			LOGGER.error(ex);
		}
		return resultList;
	}

	public int getRelationshipSidCount(String filterText, final int hierarchyDefinitionSid)
			throws SystemException, PortalException {
		int count = dataSelectionDao
				.getRelationshipCount(getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid));
		return count + 1;
	}

	public List<RelationshipDdlbDto> getRelationshipSidLazy(int startIndex, int endIndex,
			final RelationshipDdlbDto defaultRelationshipDdlbDto, String filterText, final int hierarchyDefinitionSid,
			RelationshipDdlbDto selectedRelationshipDdlbDto) throws SystemException, PortalException {
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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
		String tempFilterText = filterText;
		tempFilterText = StringUtils.trimToEmpty(tempFilterText) + Constant.PERCENT;
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid));
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
			final int hierarchyDefinitionSid) throws SystemException, PortalException {
		List<RelationshipDdlbDto> returnList = new ArrayList<>();
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid));
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

	public List<Leveldto> getChildLevelsWithHierarchyNo(String hierarchyNo, int lowestLevelNo,
			final Map<String, String> descriptionMap, Object businessUnit, Leveldto selectedLevelDto,
			int hierarchyVersion, int relationShipVersion, int subListIndex) {
		List<Object[]> resultss;
		List<Leveldto> resultList = null;
		try {
			Leveldto dto;
			List<Object> inputs = new ArrayList<>();
			inputs.add(hierarchyNo);
			inputs.add(hierarchyNo);
			inputs.add(lowestLevelNo);
			inputs.add(relationShipVersion);
			inputs.add(hierarchyVersion);
			String query = StringUtils.EMPTY;
			if (!String.valueOf(businessUnit).equals("null") && !String.valueOf(businessUnit).equals("0")
					&& !String.valueOf(businessUnit).isEmpty()) {
				query = relationLogic.getChildLevelQueryForProduct(selectedLevelDto, relationShipVersion,
						String.valueOf(businessUnit), lowestLevelNo, subListIndex);
				resultss = HelperTableLocalServiceUtil.executeSelectQuery(query);
			} else {
				query = "getChildLevelsWithHierarchyNo_New";
				resultss = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtils.getQuery(inputs, query));
			}

			if (resultss != null) {
				resultList = new ArrayList<>();
				for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
					Object objects[] = (Object[]) resultss.get(loop);
					dto = new Leveldto();
					dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
					dto.setRelationshipLevelValue(String.valueOf(objects[1]));
					dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
					dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
					dto.setLevelValueReference(String.valueOf(objects[NumericConstants.FOUR]));
					dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
					dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
					dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[NumericConstants.SEVEN])));
					dto.setHierarchyNo(String.valueOf(objects[NumericConstants.EIGHT]));
					dto.setRelationShipBuilderId(String.valueOf(objects[NumericConstants.NINE]));
					if (descriptionMap != null) {
						dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
					}
					resultList.add(dto);
				}
			}
		} catch (NumberFormatException ex) {
			LOGGER.error(ex);
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
			dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
			dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
			dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])));
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
			String query = CustomSQLUtil.get("getFileEndDate");
			query = query.replace("[?BUSINESS_UNIT]", StringUtils.EMPTY + dto.getBusinessUnitSystemId());
			List list = (List) salesProjectionDAO.executeSelectQuery(query, null, null);
			if (list != null && !list.isEmpty()) {
				Object[] tempDate = (Object[]) list.get(0);
				dto.setFileEndYear(Integer.parseInt(String.valueOf(tempDate[0])));
				dto.setFileEndMonth(Integer.parseInt(String.valueOf(tempDate[1])));
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e);
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
			list = (List) dataSelectionDao.executeQuery(parameters);
			return list;
		} catch (SystemException e) {
			LOGGER.error(e);
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
			sidList.add(Integer.parseInt(String.valueOf(sid)));
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
			throws SystemException, PortalException {
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
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
		dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid));
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
			DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMPANY_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
			DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
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
				companyDdlbDto = new CompanyDdlbDto(Integer.valueOf(String.valueOf(objects[0])),
						String.valueOf(objects[1]));
				companies.add(companyDdlbDto);
			}
		}
		LOGGER.debug("companies return size " + companies.size());
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
			StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
			queryString.append("select RELATIONSHIP_LEVEL_VALUES from RELATIONSHIP_LEVEL_DEFINITION where \n"
					+ "RELATIONSHIP_BUILDER_SID='" + rbID + "'\n" + "and \n" + "LEVEL_NAME='Market Type'");
			CommonDAO spDAO = new CommonDAOImpl();
			list = (List) spDAO.executeSelectQuery(queryString.toString(), null, null);
			return list;
		} catch (Exception ex) {
			LOGGER.error(ex);
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
			return (List<Object>) commonDAO.executeSelectQuery(str, null, null);
		} catch (Exception e) {
			LOGGER.error(e);
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
			return (List<Object>) salesProjDAO.executeSelectQuery(str, null, null);
		} catch (Exception e) {
			LOGGER.error(e);
			return Collections.emptyList();
		}
	}

	public int getDiscountCount(String filterText) {
		try {
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
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
			LOGGER.error(ex);
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
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
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
				discounts.add(new CompanyDdlbDto(Integer.valueOf(String.valueOf(objects[0])),
						String.valueOf(objects[1]), true));
			}
		} else {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				Object[] objects = resultList.get(loop);
				if (Integer.valueOf(String.valueOf(objects[0])).equals(selectedDiscount.getRsModelSid())) {
					selectedDiscount.setRsNo(String.valueOf(objects[1]));
					discounts.add(selectedDiscount);
				} else {
					discountDdlbDto = new CompanyDdlbDto(Integer.valueOf(String.valueOf(objects[0])),
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
		LOGGER.debug("ProcedureName---> " + procedureName);
		LOGGER.debug("ProjectionId----> " + projectionId);
		LOGGER.debug("UserId----------> " + userId);
		LOGGER.debug("SessionId-------> " + sessionId);

		GtnSqlUtil.procedureCallService(procedureName, new Object[] { projectionId, userId, sessionId });

		LOGGER.debug("Ending callSalesInsertProcedure");
	}

	public boolean callReturnsCalculateProcedure(int projectionId, String userId, String sessionId, String frequency,
			String procedureName) {
		LOGGER.debug("In callInsertProcedure");
		LOGGER.debug("ProcedureName---> " + procedureName);
		LOGGER.debug("ProjectionId----> " + projectionId);
		LOGGER.debug("UserId----------> " + userId);
		LOGGER.debug("SessionId-------> " + sessionId);
		LOGGER.debug("frequency-------> " + frequency);

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
			StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
			queryString.append("select RELATIONSHIP_LEVEL_VALUES,LEVEL_NO from RELATIONSHIP_LEVEL_DEFINITION \n"
					+ "where RELATIONSHIP_LEVEL_SID in ( select RELATIONSHIP_LEVEL_SID\n"
					+ "from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID= ");
			if (projectionId != 0) {
				queryString.append(StringUtils.EMPTY + projectionId);
				queryString.append(" ) and LEVEL_NAME='Market TYPE'");
			}
			list = (List) salesProjectionDAO.executeSelectQuery(queryString.toString(), null, null);
			return list;

		} catch (Exception ex) {
			LOGGER.error(ex);
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
			LOGGER.error(ex);
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
			List<SortByColumn> sortByColumns) throws SystemException, PortalException {
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
				.replace(Constant.PROJECTION_ID_AT, String.valueOf(projectionIdValue)), null, null);
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
			salesProjectionDAO.executeBulkUpdateQuery(query1, null, null);
		}

		Map<String, String> returnDetilsMap = new HashMap<>();
		String query = SQlUtil.getQuery("RETURN_DETAILS_RESULTS").replace(Constant.PROJECTION_ID_AT,
				String.valueOf(session.getProjectionId()));
		List resultsList = (List) salesProjectionDAO.executeSelectQuery(query, null, null);
		for (int i = 0; i < resultsList.size(); i++) {
			Object[] ob = (Object[]) resultsList.get(i);
			returnDetilsMap.put(ob[0].toString(), ob[1].toString());
			if (Integer.valueOf(session.getProductLevelNumber()) == Integer
					.valueOf(ob[NumericConstants.TWO].toString())) {
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
			return (List<Object>) resultDAO.executeSelectQuery(str, null, null);
		} catch (Exception e) {
			LOGGER.error(e);
			return Collections.emptyList();
		}
	}

	public static List<Object[]> getAccrualSelection(int projectionId) {
		List<Object[]> obj = null;
		try {
			String customSql = CustomSQLUtil.get("getAccrualSelection");
			customSql = customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
			obj = (List<Object[]>) executeSelectQuery(customSql, null, null);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return obj;
	}

	public List getProductList(final GroupDTO dto, final Boolean isCompanyGroup, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns, Boolean isCount, int startIndex, int offset)
			throws SystemException, PortalException {
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
			throws SystemException, PortalException {
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
	 * Used to update the Return Details Table after DataSelection Tab
	 * modification
	 *
	 * @param session
	 */
	public void updateReturnDetails(SessionDTO session) {
		String query1 = SQlUtil.getQuery("UPDATE_RETURN_DETAILS").replace(Constant.PROJECTION_ID_AT,
				String.valueOf(session.getProjectionId()));
		salesProjectionDAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(query1, session.getCurrentTableNames()),
				null, null);
	}

	/**
	 * This method inserts in CCPMAP table for the selected
	 * relationshipBuilderSid
	 *
	 * @param relationshipBuilderSids
	 */
	public void dataSelectionInsert(String relationshipBuilderSids) {
		LOGGER.debug("Entering dataSelectionInsert" + relationshipBuilderSids);
		String query = CustomSQLUtil.get("nm.saveCustomerCcp");
		query = query.replace("?RBS", relationshipBuilderSids);
		salesProjectionDAO.executeBulkUpdateQuery(query, null, null);

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
	 * Used to form the query from selection container of customer and product
	 *
	 * @param contractList
	 * @return the string having SELECT [HIERARCHY_NO] AS STRING AND
	 *         RELATIONSHIP_LEVEL_VALUES AS INT EXAMPLE: SELECT '12.1.1.2', 543
	 *         UNION ALL SELECT '12.1.1.3', 234
	 */
	private String formQueryWithUnionAllForARP(List<Object[]> contractList) {
		StringBuilder queryBuilder = new StringBuilder();
		String unionAll = StringUtils.EMPTY;
		for (Object[] objects : contractList) {
			queryBuilder.append(unionAll).append(SELECT_CAPS).append(objects[1])
					.append(" as RELATIONSHIP_LEVEL_VALUES ");
			unionAll = UNION_ALL;
		}
		return queryBuilder.toString();
	}

	/**
	 * Used to insert the selected Customer and product hierarchy in
	 * Projection_Cust_Hierarchy and Projection_Prod_Hierarchy
	 *
	 * @param hierarchyList
	 *            It will have the hierarchy which we selected in customer or
	 *            product selection
	 * @param queryName
	 *            It will have the name of the query we need to pick up. Query
	 *            will written in xml in th path
	 *            sqlResources->DataSelectionQueries
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
	 * To get the selected cust and prod values. Used in edit mode. For add mode
	 * we will get it directly from the ui container
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
		customSql = customSql.replace("?RLDV", isCustomerHierarchy ? sessionDTO.getCustomerRelationVersion()+ StringUtils.EMPTY 
                                                : sessionDTO.getProductRelationVersion()+ StringUtils.EMPTY);
		customSql = customSql.replace("?HLDV", isCustomerHierarchy ? sessionDTO.getCustomerHierarchyVersion()+ StringUtils.EMPTY 
                                                : sessionDTO.getProductHierarchyVersion()+ StringUtils.EMPTY);
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
			updateRelationShipLevelList(object, detailsList, String.valueOf(object[1]));
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

	/**
	 *
	 * @param relationshipBuilderSid
	 * @return
	 */
	public Map<String, List> getRelationshipDetailsDeduction(SessionDTO sessionDTO, String relationshipBuilderSid,
			boolean isCustomerHierarchy) {
		String customSql = SQlUtil.getQuery("getHierarchyTableDetailsDeduction");
		customSql = customSql.replace(RBSID, relationshipBuilderSid);
		List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);

		Map<String, List> resultMap = new HashMap<>();
		RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid,
				"D", sessionDTO);
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
			updateRelationShipLevelList(object, detailsList, String.valueOf(object[1]));
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
		StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
		String query2 = CustomSQLUtil.get("get-lower-levels-based-on-hierarchy-no-with-projId-Select");
		if (PROJECTION_PROD_HIERARCHY.equals(parameters.get(Constant.TABLE_NAME))) {
			List<String> rlSids = (ArrayList<String>) parameters.get(RL_SIDS);
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
			String query = CustomSQLUtil.get("get-lower-levels-based-on-hierarchy-no-with-projId");

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
				List<String> rlSids = (ArrayList<String>) parameters.get(RL_SIDS);
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
					queryString.append(")");
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
					queryString.append(")");
				}
			}

		}
		return isSelectOnly ? query2 : queryString.toString();
	}

	public static String stringListToString(List<String> stringList) {
		StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
		if (stringList != null && !stringList.isEmpty()) {
			for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
				builder.append("'");
				builder.append(stringList.get(loop));
				builder.append("'");
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
			int levelNo = Integer.valueOf(entry.getValue().get(NumericConstants.TWO).toString());
			if (maxLevelNo < levelNo && hierarchyIndicator.equals(entry.getValue().get(NumericConstants.FOUR))) {
				maxLevelNo = levelNo;
			}
		}
		return maxLevelNo;
	}

	public void callInsertProcedureForNm(int projectionId, String userId, String sessionId, String procedureName,
			String screenName) {

		StringBuilder query = new StringBuilder("EXEC ");
		try {
			query.append(procedureName);
			query.append(Constant.SPACE);
			query.append(projectionId);
			query.append(",");
			query.append(userId);
			query.append(",'");
			query.append(sessionId);
			if (!screenName.equals(NATIONAL_ASSUMPTIONS.getConstant()) && !screenName.equals(Constant.PPA_SMALL)) {

				query.append("','");
				query.append(screenName);
			}
			query.append("'");
			HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
		} catch (Exception ex) {
			LOGGER.error(ex);
		}

	}

	/**
	 * To insert the Accural_proj_details table in edit and add mode
	 *
	 * @param ccpHierarchyQuery
	 * @param tempTableNames
	 * @param topLevelName
	 * @param isDataSelectionTab
	 *            -- It will be true if its called from data selection tab
	 */
	private void callARPCCPInsertion(String[] ccpHierarchyQuery, GtnSmallHashMap tempTableNames, String topLevelName,
			boolean isDataSelectionTab, DataSelectionDTO dtoValue, boolean isAdd) {
		String dedLevel = "Deduction Program Type".equalsIgnoreCase(dtoValue.getDeductionLevel())
				? "REBATE_PROGRAM_TYPE"
				: "Deduction Category".equalsIgnoreCase(dtoValue.getDeductionLevel()) ? "RS_CATEGORY"
						: "Deduction Schedule Type".equalsIgnoreCase(dtoValue.getDeductionLevel()) ? Constant.RS_TYPE
								: StringUtils.EMPTY;
		String filter = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(dedLevel)) {
			if (isAdd) {
				filter = " AND R1." + dedLevel + " = " + dtoValue.getDeductionValueId();
			} else {
				List<Object> list = new ArrayList<>();
				list.add(dtoValue.getDeductionValue());
				List<Object> sid = QueryUtils.getAppData(list, "get-helper-table-query", null);
				filter = " AND R1." + dedLevel + " = " + sid.get(0);
			}
		}
		StringBuilder builder = new StringBuilder();
		if (isDataSelectionTab) {
			builder.append(QueryUtil.replaceTableNames(
					SQlUtil.getQuery(Constant.DELETION).replace(Constant.AT_TABLE_NAME, "ACCRUAL_PROJ_DETAILS"),
					tempTableNames));
		}
		builder.append(SQlUtil.getQuery("InsertAccrualCCPValue"));
		builder.replace(builder.indexOf(Constant.CONTRACT_AT),
				Constant.CONTRACT_AT.length() + builder.lastIndexOf(Constant.CONTRACT_AT), ccpHierarchyQuery[0]);
		builder.replace(builder.indexOf(Constant.CUSTOMER_AT),
				Constant.CUSTOMER_AT.length() + builder.lastIndexOf(Constant.CUSTOMER_AT), ccpHierarchyQuery[1]);
		builder.replace(builder.indexOf(Constant.PRODUCT_AT),
				Constant.PRODUCT_AT.length() + builder.lastIndexOf(Constant.PRODUCT_AT),
				ccpHierarchyQuery[NumericConstants.TWO].replace("HIERARCHY_NO,", StringUtils.EMPTY));
		builder.replace(builder.indexOf(Constant.PROJECTION_MASTER_SID_AT),
				Constant.PROJECTION_MASTER_SID_AT.length() + builder.lastIndexOf(Constant.PROJECTION_MASTER_SID_AT),
				String.valueOf(dtoValue.getProjectionId()));
		builder.replace(builder.indexOf(SELECTION_AT), SELECTION_AT.length() + builder.lastIndexOf(SELECTION_AT),
				filter);
		if (Constant.CONTRACT_SMALL.equalsIgnoreCase(topLevelName)) {
			builder.replace(builder.indexOf(FILTER_AT), FILTER_AT.length() + builder.lastIndexOf(FILTER_AT),
					"COM.HIERARCHY_NO LIKE C.HIERARCHY_NO");
		} else {
			builder.replace(builder.indexOf(FILTER_AT), FILTER_AT.length() + builder.lastIndexOf(FILTER_AT),
					"C.HIERARCHY_NO LIKE COM.HIERARCHY_NO");
		}
		HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(builder.toString(), tempTableNames));
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
				name.append(String.valueOf(obj[1])).append(" - ").append(String.valueOf(obj[3])).append(",");
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

	private void updateRelationShipLevelList(Object[] object, List<Object> detailsList, String defaultValue) {
		if (object.length > 5) {
			List<Object> displayFormat = new ArrayList<>();
			displayFormat.add(defaultValue);
			for (int i = 5; i < object.length; i++) {
				displayFormat.add(object[i]);
			}
			detailsList.add(displayFormat);
		}
	}

	public void deleteFromTempCCPTable(final SessionDTO session) {
		String finalQuery = QueryUtil.replaceTableNames(
				QueryUtils.getQuery(Collections.emptyList(), "deleteTempCCPTable"), session.getCurrentTableNames());
		salesProjectionDAO.executeBulkUpdateQuery(finalQuery, null, null);

	}
}