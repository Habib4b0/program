/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.displayformat.main.RelationshipLevelValuesMasterBean;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.DataSourceConnection;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.dataSelection.dto.CompanyDdlbDto;
import com.stpl.app.cff.ui.dataSelection.dto.RelationshipDdlbDto;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.Converters;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.RelationshipBuilderLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.TreeTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class DataSelectionLogic {

	/**
	 * The data selection dao.
	 */
	private final DataSelectionDAO dataSelectionDaoImpl = new DataSelectionDAOImpl();
        
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DataSelectionLogic.class);
	private int discountDdlbCount = 0;
	private final DataSelectionDAO vDataSelectionDao = new DataSelectionDAOImpl();
	public static final String FILTER = "filter~";
	public static final String HIERARCHY_NO = "hierarchyNo";
	public static final String CFF_MASTER_SID = "cffMasterSid";
	public static final String INDICATOR = "indicator";
	public static final String PROJECTION_ID = "projectionId";
	private final CommonUtils commonUtils = new CommonUtils();
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

		final List<HierarchyLookupDTO> resultList = new ArrayList<>();

		if (hierarchyName.contains(String.valueOf(CommonUtils.CHAR_ASTERISK))) {
			hierarchyName = hierarchyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		}
		if (hierarchyType.contains(String.valueOf(CommonUtils.CHAR_ASTERISK))) {
			hierarchyType = hierarchyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		}

		final List result = dataSelectionDaoImpl.getHierarchyGroup(hierarchyName, hierarchyType, customerOrProduct, action);

		HierarchyLookupDTO hdto;
		for (int i = 0; i < result.size(); i++) {
			hdto = new HierarchyLookupDTO();
			final Object[] obj = (Object[]) result.get(i);
			hdto.setHierarchyId(Integer.parseInt(obj[0].toString()));
			hdto.setHierarchyName(String.valueOf(obj[1].toString()));
			hdto.setHighestLevel(String.valueOf(obj[NumericConstants.THREE].toString()));
			hdto.setLowestLevel(String.valueOf(obj[NumericConstants.FOUR].toString()));
			hdto.setCreatedDate(String.valueOf(obj[NumericConstants.FIVE].toString()));
			hdto.setCreatedDateSearch(Converters.parseDate(String.valueOf(obj[NumericConstants.FIVE].toString())));
			if (obj[NumericConstants.SIX] != null) {
				hdto.setModifiedDate(String.valueOf(obj[NumericConstants.SIX].toString()));
				hdto.setModifiedDateSearch(Converters.parseDate(String.valueOf(obj[NumericConstants.SIX].toString())));
			}
			hdto.setVersionNo(Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN].toString())));

			if (obj[NumericConstants.TWO] != null) {
			}
			if (obj[NumericConstants.THREE] != null) {
			}
			resultList.add(hdto);// Sanityrelat0090

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
	public List<Leveldto> loadCustomerForecastLevel(int hierarchyId, int HierarchyVersion) {
		final List<Leveldto> resultList = new ArrayList<>();
		Leveldto leveldto;
		List<Object> input = new ArrayList<>();
		input.add(hierarchyId);
		input.add(HierarchyVersion);
		try {
			List<Object[]> returnlist = HelperTableLocalServiceUtil
					.executeSelectQuery(CommonQueryUtils.getAppQuery(input, "selectHierarchyDetails"));
			for (final Object[] object : returnlist) {
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
					if (StringConstantsUtil.COMPANY_MASTER
							.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromCompany(true);
					} else if (StringConstantsUtil.CONTRACT_MASTER
							.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromContract(true);
					} else if (StringConstantsUtil.ITEM_MASTER
							.equalsIgnoreCase(String.valueOf(object[NumericConstants.THREE]))) {
						leveldto.setFromItem(true);
					} else {
						leveldto.setFromCompany(false);
						leveldto.setFromContract(false);
						leveldto.setFromItem(false);
					}
				}
				resultList.add(leveldto);
			}
		} catch (final Exception e) {
			LOGGER.error(" in DSLogic - loadCustomerForecastLevel= {}", e);
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
	 * @return the list
	 */
	public List<Leveldto> loadInnerLevel(String levelName, int hierarchyId, List<Integer> selectedLevelSids,
			final boolean isNdc, final String fieldName, final String relationshipSid,
			final Map<String, String> descriptionMap, final String level, final String screenName, int discountID,
			int levelNo, final Object companySID, final Object businessUnitSID) throws SystemException {
		final List<Leveldto> values = new ArrayList<>();
		List result = null;
		final Map<String, Object> parameters = new HashMap<>();
		Leveldto dto;
		if (isNdc) {
			parameters.put("isNdc", "true");
			parameters.put("glCompId", companySID);
			parameters.put(StringConstantsUtil.BUSINESS_UNIT1, businessUnitSID);
		} else {
			parameters.put("isNdc", StringConstantsUtil.STRING_FALSE);
			parameters.put("glCompId", companySID);
			parameters.put(StringConstantsUtil.BUSINESS_UNIT1, businessUnitSID);
		}
		parameters.put(StringConstantsUtil.HIERARCHY_DEFINITION_SID, hierarchyId);
		parameters.put("levelName", levelName);
		parameters.put("level", level);
		parameters.put("relationshipLevelSidList", selectedLevelSids);
		parameters.put("fieldName", fieldName);
		if (StringUtils.isBlank(relationshipSid) || "null".equals(String.valueOf(relationshipSid))
				|| "0".equals(String.valueOf(relationshipSid))) {
			parameters.put(StringConstantsUtil.RELATIONSHIP_SID, "null");
		} else {
			parameters.put(StringConstantsUtil.RELATIONSHIP_SID, relationshipSid);
		}
		parameters.put("discount", discountID);
		parameters.put(StringConstantsUtil.LEVEL_NO, levelNo);
		parameters.put("screenName", screenName);
		result = dataSelectionDaoImpl.getInnerLevel(parameters) != null
				|| !dataSelectionDaoImpl.getInnerLevel(parameters).isEmpty() ? dataSelectionDaoImpl.getInnerLevel(parameters)
						: Collections.EMPTY_LIST;
		for (int i = 0; i < result.size(); i++) {
			dto = new Leveldto();
			final Object[] obj = (Object[]) result.get(i);
			dto.setLevel(String.valueOf(obj[NumericConstants.EIGHT]));
			dto.setRelationshipLevelValue(String.valueOf(obj[0]));
			dto.setLevelNo(DataTypeConverter.convertObjectToInt(obj[1]));
			dto.setParentNode(String.valueOf(obj[NumericConstants.TWO]));
			dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(obj[NumericConstants.THREE]));
			dto.setTableName(String.valueOf(obj[NumericConstants.FOUR]));
			dto.setFieldName(String.valueOf(obj[NumericConstants.FIVE]));
			dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
			dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.SEVEN]));
			if (obj[NumericConstants.FOUR] != null && !StringUtils.isEmpty(String.valueOf(obj[NumericConstants.FOUR]))
					&& !StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR]))) {
				if (StringConstantsUtil.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromCompany(true);
				} else if (StringConstantsUtil.CONTRACT_MASTER
						.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromContract(true);
				} else if (StringConstantsUtil.ITEM_MASTER
						.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
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
		final List<String> values = new ArrayList<>();
		List result = null;
		final DynamicQuery dynamicQuery = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(PropertyFactoryUtil.forName(StringConstantsUtil.RELATIONSHIP_BUILDER_SID)
				.in(RelationshipBuilderLocalServiceUtil.dynamicQuery()
						.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.HIERARCHY_DEFINITION_SID, hierarchyId))
						.setProjection(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_BUILDER_SID))));

		dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.LEVEL_NO, levelNo));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

		productProjectionList
				.add(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("relationshipLevelValues")));
		productProjectionList.add(ProjectionFactoryUtil.property("levelName"));
		productProjectionList.add(ProjectionFactoryUtil.property("parentNode"));
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_LEVEL_SID));

		dynamicQuery.setProjection(productProjectionList);
		result = dataSelectionDaoImpl.getCustomerForecastLevel(dynamicQuery);
		for (int i = 0; i < result.size(); i++) {
			final Object[] obj = (Object[]) result.get(i);
			values.add(String.valueOf(obj[0]));
		}

		return values;
	}

	public List<CompanyMaster> getCompanyFromSids(final List<String> companySids) throws SystemException {

		final List<Integer> sids = new ArrayList<>();

		for (final String sid : companySids) {
			sids.add(DataTypeConverter.convertStringToInteger(sid));
		}

		final DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.COMPANY_MASTER_SID, sids));
		final List<CompanyMaster> companies = dataSelectionDaoImpl.getCompanyMasterList(dynamicQuery);
		return companies;
	}

	public List<ItemMaster> getItemsFromSids(final List<String> itemSids) throws SystemException {

		final List<Integer> sids = new ArrayList<>();
		List<ItemMaster> items = null;
		for (final String sid : itemSids) {
			sids.add(DataTypeConverter.convertStringToInteger(sid));
		}
		if (itemSids != null && !itemSids.isEmpty()) {
			final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.ITEM_MASTER_SID, sids));
			items = dataSelectionDaoImpl.getItemMaster(dynamicQuery);
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
			DataSelectionDTO dataSelectionDTO) throws SystemException {
		if (removeLevels != null && !removeLevels.isEmpty()) {
			List<Object> removeChildLevels = null;
			final Map<String, Object> parameters = new HashMap<>();
			parameters.put(INDICATOR, "getRemovableChildren");
			parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
			parameters.put(StringConstantsUtil.TABLE_NAME, " PROJECTION_PROD_HIERARCHY ");
			parameters.put(PROJECTION_ID, projectionId);
			removeChildLevels = dataSelectionDaoImpl.executeQuery(parameters);
			deleteProductHierarchyLevels(projectionId, removeLevels);
			if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
				deleteProductHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
			}
		}
		saveProductHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, StringConstantsUtil.UPDATE_PROPERTY,
				dataSelectionDTO);
	}

	private void deleteProductHierarchyLevels(final int projectionId, final List<String> removeLevels)
			throws SystemException {
		List<CffProdHierarchy> details;
		final DynamicQuery dynamicQuery = CffProdHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(CFF_MASTER_SID, projectionId));
		dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.RELATIONSHIP_LEVEL_SID,
				UiUtils.convertStringListToParsedIngeter(removeLevels)));
		details = dataSelectionDaoImpl.findProdHierarchyByProjectionId(dynamicQuery);
		for (final CffProdHierarchy prod : details) {
			dataSelectionDaoImpl.deleteProjectionProdHierarchies(prod);
		}
	}

	public void updateProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, DataSelectionDTO dataSelectionDTO) throws SystemException {
		List<CffProdHierarchy> details;
		final DynamicQuery dynamicQuery = CffProdHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(CFF_MASTER_SID, projectionId));
		details = dataSelectionDaoImpl.findProdHierarchyByProjectionId(dynamicQuery);
		for (final CffProdHierarchy cust : details) {
			dataSelectionDaoImpl.deleteProjectionProdHierarchies(cust);
		}
		saveProductHierarchyLogic(levelList, endLevelSids, projectionId, null, "save", dataSelectionDTO);
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
			DataSelectionDTO dataSelectionDTO) throws SystemException {
		LOGGER.debug("saveProductHierarchyLogic endLevelSids projectionId= {} ", projectionId);
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "getChildLevelRLSid");
		parameters.put(StringConstantsUtil.RL_SIDS, endLevelSids);
		parameters.put(PROJECTION_ID, projectionId);
		parameters.put(StringConstantsUtil.TABLE_NAME, "CFF_PROD_HIERARCHY");
		parameters.put("module", "cff");
		parameters.put(StringConstantsUtil.BUSINESS_UNIT1, dataSelectionDTO.getBusinessUnitSystemId());
		List<Object> endLevels = null;
		if (endLevelSids != null && !endLevelSids.isEmpty()) {
			endLevels = vDataSelectionDao.executeQuery(parameters);
		}
		final CffProdHierarchy cffProdHierarchy = CffProdHierarchyLocalServiceUtil.createCffProdHierarchy(0);
		try {
			if (StringConstantsUtil.UPDATE_PROPERTY.equals(indicator)) {
				for (final String rsId : addLevels) {
					cffProdHierarchy.setCffMasterSid(projectionId);
					cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
					vDataSelectionDao.addProjectionProdHierarchy(cffProdHierarchy);
				}
			} else if ("save".equals(indicator)) {
				for (final Leveldto dto : levelList) {

					cffProdHierarchy.setCffMasterSid(projectionId);
					cffProdHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
					vDataSelectionDao.addProjectionProdHierarchy(cffProdHierarchy);
				}
			}
			if (endLevels != null && !endLevels.isEmpty()) {
				for (final Object relationshipLevelSid : endLevels) {
					cffProdHierarchy.setCffMasterSid(projectionId);
					cffProdHierarchy.setRelationshipLevelSid(
							UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
					vDataSelectionDao.addProjectionProdHierarchy(cffProdHierarchy);
				}
			}
		} catch (final SystemException e) {
			LOGGER.error(" saveProductHierarchyLogic= {}", e);
		}
	}

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
			final Map<String, Object> parameters = new HashMap<>();
			parameters.put(INDICATOR, "getRemovableChildren");
			parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
			parameters.put(PROJECTION_ID, projectionId);
			parameters.put(StringConstantsUtil.TABLE_NAME, " PROJECTION_CUST_HIERARCHY ");
			removeChildLevels = dataSelectionDaoImpl.executeQuery(parameters);
			deleteCustomerHierarchyLevels(projectionId, removeLevels);
			if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
				deleteCustomerHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
			}
		}
		saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, addLevels,
				StringConstantsUtil.UPDATE_PROPERTY);
	}

	private void deleteCustomerHierarchyLevels(final int projectionId, final List<String> removeLevels)
			throws SystemException {
		List<CffCustHierarchy> details;
		final DynamicQuery dynamicQuery = CffCustHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(CFF_MASTER_SID, projectionId));
		dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.RELATIONSHIP_LEVEL_SID,
				UiUtils.convertStringListToParsedIngeter(removeLevels)));
		details = dataSelectionDaoImpl.findCustHierarchyByProjectionId(dynamicQuery);
		for (final CffCustHierarchy cust : details) {
			dataSelectionDaoImpl.deleteProjectionCustHierarchies(cust);
		}
	}

	public void updateCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId) throws SystemException {
		List<CffCustHierarchy> details;
		final DynamicQuery dynamicQuery = CffCustHierarchyLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(CFF_MASTER_SID, projectionId));
		details = dataSelectionDaoImpl.findCustHierarchyByProjectionId(dynamicQuery);
		for (final CffCustHierarchy cust : details) {
			dataSelectionDaoImpl.deleteProjectionCustHierarchies(cust);
		}
		saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, null, "save");
	}

	/**
	 * Save customer hierarchy logic.
	 *
	 * @param levelList
	 *            the level list
     * @param endLevelSids
	 * @param projectionId
     * @param addLevels
     * @param indicator
	 * @throws java.lang.Exception
	 */
	public void saveCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids,
			final int projectionId, final List<String> addLevels, final String indicator) throws SystemException {
		LOGGER.debug("saveCustomerHierarchyLogic endLevelSids  projectionId= {}", projectionId);
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "getChildLevelRLSid");
		parameters.put(PROJECTION_ID, projectionId);
		parameters.put(StringConstantsUtil.RL_SIDS, endLevelSids);
		parameters.put(StringConstantsUtil.TABLE_NAME, "CFF_CUST_HIERARCHY");
		parameters.put("module", "cff");
		List<Object> endLevels = null;
		if (endLevelSids != null && !endLevelSids.isEmpty()) {
			endLevels = vDataSelectionDao.executeQuery(parameters);
		}

		final CffCustHierarchy cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(0);
		try {
			if (StringConstantsUtil.UPDATE_PROPERTY.equals(indicator)) {
				for (final String rsId : addLevels) {
					cffCustHierarchy.setCffMasterSid(projectionId);
					cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
					vDataSelectionDao.addProjectionCustHierarchy(cffCustHierarchy);
				}
			} else if ("save".equals(indicator)) {
				for (final Leveldto dto : levelList) {
					cffCustHierarchy.setCffMasterSid(projectionId);
					cffCustHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
					vDataSelectionDao.addProjectionCustHierarchy(cffCustHierarchy);
				}
			}
			if (endLevels != null && !endLevels.isEmpty()) {
				for (final Object relationshipLevelSid : endLevels) {
					cffCustHierarchy.setCffMasterSid(projectionId);
					cffCustHierarchy.setRelationshipLevelSid(
							UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
					vDataSelectionDao.addProjectionCustHierarchy(cffCustHierarchy);
				}
			}
		} catch (final SystemException e) {
			LOGGER.error(" in saveCustomerHierarchyLogic={}", e);
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
		List<CffDetails> details;
		final DynamicQuery dynamicQuery = CffDetailsLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq(CFF_MASTER_SID, projectionId));
		details = dataSelectionDaoImpl.findProjDetailsByProjectionId(dynamicQuery);
		for (final CffDetails detail : details) {
			dataSelectionDaoImpl.deleteProjectionDetails(detail);
		}
	}

	/**
	 * Gets the hierarchy values.
	 *
	 * @return the hierarchy values
	 */
	public List getHierarchyValues() {
		final List resultss = new ArrayList();
		final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();

		final List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto = null;

		final String query = " select HLD.LEVEL_NO,HLD.TABLE_NAME,HLD.FIELD_NAME,HLD.LEVEL_VALUE_REFERENCE,* from dbo.HIERARCHY_LEVEL_DEFINITION HLD WHERE HIERARCHY_LEVEL_DEFINITION_SID IN  "
				+ " (select DISTINCT HIERARCHY_LEVEL_DEFINITION_SID FROM dbo.RELATIONSHIP_LEVEL_DEFINITION "
				+ " WHERE RELATIONSHIP_LEVEL_SID IN(select DISTINCT RELATIONSHIP_LEVEL_SID from dbo.PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID='1') ) ";

		try (Connection connection = dataSourceConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				dto = new Leveldto();
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(resultSet.getObject(1)));
				dto.setTableName(String.valueOf(resultSet.getObject(NumericConstants.TWO)));
				dto.setFieldName(String.valueOf(resultSet.getObject(NumericConstants.THREE)));
				dto.setLevelValueReference(String.valueOf(resultSet.getObject(NumericConstants.FOUR)));
				resultList.add(dto);
			}
		} catch (SQLException | NamingException ex) {
			Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
		}

		resultss.add(resultList);

		return resultss;
	}

	/**
	 * Gets the relation ship values.
	 *
	 * @return the relation ship values
	 */
	public List getRelationShipValues() {
		final List resultss = new ArrayList();
		final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();

		final List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto = null;

		final String query = " select LEVEL_NO,RELATIONSHIP_LEVEL_VALUES,PARENT_NODE,LEVEL_NAME "
				+ " FROM dbo.RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_LEVEL_SID IN(select DISTINCT RELATIONSHIP_LEVEL_SID from dbo.PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID='1') ORDER by LEVEL_NO ";

		try (Connection connection = dataSourceConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				dto = new Leveldto();
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(resultSet.getObject(1)));
				dto.setRelationshipLevelValue(String.valueOf(resultSet.getObject(NumericConstants.TWO)));
				dto.setParentNode(String.valueOf(resultSet.getObject(NumericConstants.THREE)));
				dto.setLevel(String.valueOf(resultSet.getObject(NumericConstants.FOUR)));
				resultList.add(dto);
			}

		} catch (SQLException | NamingException ex) {
			Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
		}
		resultss.add(resultList);

		return resultss;
	}

	/**
	 * Gets the relation ship values.
	 *
	 * @param projectionId
	 * @param indicator
	 * @return the relation ship values
	 */
	public List getRelationShipValues(final int projectionId, final String indicator, final Object levelNo,
			final Map<String, String> descriptionMap) {
		List resultss;
		final List inputList = new ArrayList();
		if ("customer".equalsIgnoreCase(indicator)) {
			inputList.add("CUST");
		} else {
			inputList.add("PROD");
		}
		inputList.add(projectionId);
		if (levelNo != null) {
			inputList.add("AND RLD.LEVEL_NO <= '" + levelNo + "'");
		} else {
			inputList.add(" ");
		}
		final List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto;
		try {
			resultss = CommonQueryUtils.getAppData(inputList, "loadSelectedContainer", null);
			for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
				dto = new Leveldto();
				final Object[] objects = (Object[]) resultss.get(loop);
				dto.setLevelNo(DataTypeConverter.convertObjectToInt(objects[0]));
				dto.setRelationshipLevelValue(String.valueOf(objects[1]));
				dto.setParentNode(String.valueOf(objects[NumericConstants.TWO]));
				dto.setLevel(String.valueOf(objects[NumericConstants.THREE]));
				dto.setRelationshipLevelSid(DataTypeConverter.convertObjectToInt(objects[NumericConstants.FOUR]));
				dto.setTableName(String.valueOf(objects[NumericConstants.FIVE]));
				dto.setFieldName(String.valueOf(objects[NumericConstants.SIX]));
				dto.setHierarchyNo(String.valueOf(objects[NumericConstants.SEVEN]));
				if (objects[NumericConstants.FIVE] != null
						&& !StringUtils.isEmpty(String.valueOf(objects[NumericConstants.FIVE]))
						&& !StringUtils.isBlank(String.valueOf(objects[NumericConstants.FIVE]))) {
					if (StringConstantsUtil.COMPANY_MASTER
							.equalsIgnoreCase(String.valueOf(objects[NumericConstants.FIVE]))) {
						dto.setFromCompany(true);
					} else if (StringConstantsUtil.CONTRACT_MASTER
							.equalsIgnoreCase(String.valueOf(objects[NumericConstants.FIVE]))) {
						dto.setFromContract(true);
					} else if (StringConstantsUtil.ITEM_MASTER
							.equalsIgnoreCase(String.valueOf(objects[NumericConstants.FIVE]))) {
						dto.setFromItem(true);
					} else {
						dto.setFromCompany(false);
						dto.setFromContract(false);
						dto.setFromItem(false);
					}
				}
				if (descriptionMap != null) {
					dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.SEVEN])));
				}

				resultList.add(dto);
			}
		} catch (final NumberFormatException ex) {
			LOGGER.error(" in getRelationShipValues=[}", ex);
		}
		return resultList;
	}

	public List getParentLevels(final int levelNo, final int relationshipLevelSid) {
		List resultss;
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(StringConstantsUtil.LEVEL_NO, levelNo);
		parameters.put(INDICATOR, StringUtils.EMPTY);
		parameters.put(StringConstantsUtil.RELATIONSHIP_LEVEL_SID, relationshipLevelSid);

		final List<Leveldto> resultList = new ArrayList<>();
		Leveldto dto;

		try {
			resultss = dataSelectionDaoImpl.getParentLevels(levelNo, relationshipLevelSid, parameters);

			for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
				dto = new Leveldto();
				final Object[] objects = (Object[]) resultss.get(loop);
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
		} catch (final SystemException | NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultList;
	}

	public Leveldto getParentLevels(final int levelNo, final int relationshipLevelSid, final String parent) {
		List resultss;
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(StringConstantsUtil.LEVEL_NO, levelNo);
		parameters.put(INDICATOR, StringUtils.EMPTY);
		parameters.put("parent", parent);
		parameters.put(StringConstantsUtil.RELATIONSHIP_LEVEL_SID, relationshipLevelSid);

		final Leveldto dto = new Leveldto();

		try {
			resultss = dataSelectionDaoImpl.getParentLevels(levelNo, relationshipLevelSid, parameters);
			final Object[] objects = (Object[]) resultss.get(0);
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
		} catch (final SystemException | NumberFormatException ex) {
			LOGGER.error(ex.getMessage());

		}
		return dto;
	}

	/**
	 * Load saved customer hierarchy.
	 *
	 * @return the list
	 */
	public List loadSavedCustomerHierarchy() {
		return null;
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

			user = dataSelectionDaoImpl.getUser(Long.valueOf(userId));

		} catch (final PortalException | SystemException | NumberFormatException ex) {
			java.util.logging.Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
		}
		return user;
	}

	public List<String> getCustomerGroupDetails(int companyGroupSid) throws SystemException {
		final DynamicQuery dynamicQuery = CompanyGroupDetailsLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid", companyGroupSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_MASTER_SID));
		dynamicQuery.setProjection(productProjectionList);
		final List resultList = dataSelectionDaoImpl.getCustomerGroupDetails(dynamicQuery);

		final List<String> returnList = new ArrayList<>();
		for (final Object companySid : resultList) {
			returnList.add(String.valueOf(companySid));
		}
		return returnList;
	}

	public List<String> getItemGroupDetails(int itemGroupSid) throws SystemException {
		final DynamicQuery dynamicQuery = ItemGroupDetailsLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid", itemGroupSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.ITEM_MASTER_SID));
		dynamicQuery.setProjection(productProjectionList);
		final List resultList = dataSelectionDaoImpl.getCustomerGroupDetails(dynamicQuery);

		final List<String> returnList = new ArrayList<>();
		for (final Object companySid : resultList) {
			returnList.add(String.valueOf(companySid));
		}
		return returnList;
	}

	public List<String> executeQuery(final String query) throws SystemException {
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("query", query);
		final List resultList = dataSelectionDaoImpl.executeQuery(parameters);
		final List<String> returnList = new ArrayList<>();
		for (final Object value : resultList) {
			returnList.add(String.valueOf(value));
		}
		return returnList;
	}

	public List<CompanyDdlbDto> getCompanyForDdlbFromSids(final List<String> companySids) throws SystemException {

		final List<CompanyMaster> companies = getCompanyFromSids(companySids);
		final List<CompanyDdlbDto> companiesForDdlb = new ArrayList<>();
		CompanyDdlbDto companyDdlbDto;
		for (final CompanyMaster companyMaster : companies) {
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
		final List<CompanyDdlbDto> companies = new ArrayList<>();
		if (startIndex == 0) {
			companies.add(companyDdlbDefault);
		}
		if (companySids != null && !companySids.isEmpty()) {
			final DynamicQuery helper = HelperTableLocalServiceUtil.dynamicQuery();
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LIST_NAME, "COMP_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(StringConstantsUtil.DESCRIPTION1, StringConstantsUtil.GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			final List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.parseInt(String.valueOf(companyTypeIds.get(0)));
			final DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			filterText = StringUtils.trimToEmpty(filterText) + "%";
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.COMPANY_MASTER_SID,
					UiUtils.convertStringListToIngeter(companySids)));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_MASTER_SID));
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_NAME_PROPERTY));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.COMPANY_NAME_PROPERTY, filterText));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.COMPANY_TYPE_PROPERTY, companyId));
			dynamicQuery.setLimit(startIndex, endIndex);
			final List<Object[]> returnlist = dataSelectionDaoImpl.getCompanies(dynamicQuery);
			CompanyDdlbDto companyDdlbDto;
			if (selectedCompanyDdlbDto == null) {
				for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
					final Object[] objects = returnlist.get(loop);
					companyDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
							String.valueOf(objects[1]));
					companies.add(companyDdlbDto);
				}
			} else {
				for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
					final Object[] objects = returnlist.get(loop);
					if (Integer.parseInt(String.valueOf(objects[0])) == selectedCompanyDdlbDto.getCompanyMasterSid()) {
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
			final DynamicQuery helper = HelperTableLocalServiceUtil.dynamicQuery();
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LIST_NAME, "COMP_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(StringConstantsUtil.DESCRIPTION1, StringConstantsUtil.GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			final List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.parseInt(String.valueOf(companyTypeIds.get(0)));
			final DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			filterText = StringUtils.trimToEmpty(filterText) + "%";
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.COMPANY_MASTER_SID,
					UiUtils.convertStringListToIngeter(companySids)));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_MASTER_SID));
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_NAME_PROPERTY));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			dynamicQuery.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.COMPANY_NAME_PROPERTY, filterText));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.COMPANY_TYPE_PROPERTY, companyId));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			count = dataSelectionDaoImpl.getCompaniesCount(dynamicQuery);
		}
		return count;
	}

	public List<ItemMaster> getItemMasterFromCompanies(final List<String> companySids) throws SystemException {
		List<ItemMaster> resultList = null;
		final List<Integer> itemSids = getItemIdFromCompanyInCCp(companySids, 0);
		if (!itemSids.isEmpty()) {
			final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.ITEM_MASTER_SID, itemSids));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.ITEM_MASTER_SID));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			resultList = dataSelectionDaoImpl.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public List<ItemMaster> getItemMasterFromCompany(final int companySid) throws SystemException {
		List<ItemMaster> resultList = null;
		final List<Integer> itemSids = getItemIdFromCompanyInCCp(null, companySid);
		if (!itemSids.isEmpty()) {
			final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.ITEM_MASTER_SID, itemSids));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.ITEM_MASTER_SID));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			resultList = dataSelectionDaoImpl.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public List<Integer> getItemIdFromCompanyInCCp(final List<String> companySids, final int companySid)
			throws SystemException {
		final DynamicQuery dynamicQuery = CcpDetailsLocalServiceUtil.dynamicQuery();
		if (companySids != null && !companySids.isEmpty()) {
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.COMPANY_MASTER_SID,
					UiUtils.convertStringListToIngeter(companySids)));
		}
		if (companySid != 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.COMPANY_MASTER_SID, companySid));
		}
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.ITEM_MASTER_SID));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		final List resultList = dataSelectionDaoImpl.getItemIdFromCompanyInCCp(dynamicQuery);
		final List<Integer> sidList = new ArrayList<>();
		for (final Object sid : resultList) {
			sidList.add(DataTypeConverter.convertObjectToInt(sid));
		}
		return sidList;
	}

	public List<Integer> getItemSidsFromAllBrand() throws SystemException {
		final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
		dynamicQuery.add(PropertyFactoryUtil.forName("brandMasterSid").in(BrandMasterLocalServiceUtil.dynamicQuery().setProjection(ProjectionFactoryUtil.property("brandMasterSid"))));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.ITEM_MASTER_SID));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		final List resultList = dataSelectionDaoImpl.getItemMaster(dynamicQuery);
		final List<Integer> returnList = new ArrayList<>();
		for (final Object sid : resultList) {
			returnList.add(DataTypeConverter.convertObjectToInt(sid));
		}
		return returnList;
	}

	public List<ItemMaster> getItemMaster(final List<String> itemSidsFromHierarchy) throws SystemException {
		List<ItemMaster> resultList = null;
		if (itemSidsFromHierarchy != null && !itemSidsFromHierarchy.isEmpty()) {
			final DynamicQuery dynamicQuery = ItemMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.ITEM_MASTER_SID,
					UiUtils.convertStringListToIngeter(itemSidsFromHierarchy)));
			resultList = dataSelectionDaoImpl.getItemMaster(dynamicQuery);
		}
		return resultList;
	}

	public ForecastConfig getTimePeriod(String screenName) throws SystemException {
		List<ForecastConfig> resultList = null;
		final DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
		if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
			screenName = "Commercial";
		} else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
			screenName = "Government";
		}
		dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", screenName));
		dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
		resultList = dataSelectionDaoImpl.getForecastConfig(dynamicQuery);
		ForecastConfig forecastConfig = null;
		if (resultList != null && !resultList.isEmpty()) {
			forecastConfig = resultList.get(0);
		}
		return forecastConfig;
	}

	public List<String> getEndLevelCCP(final List<Leveldto> customerEndLevels, final String indicator,
			final String projectionId) throws SystemException {
		final List<String> customerCcpList = new ArrayList<>();
		if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
			List resultList;
			Map<String, Object> parameters;
			for (final Leveldto dto : customerEndLevels) {
				parameters = new HashMap<>();
				parameters.put(PROJECTION_ID, projectionId);
				parameters.put(INDICATOR, indicator);
				parameters.put(StringConstantsUtil.RELATIONSHIP_LEVEL_SID, dto.getRelationshipLevelSid());
				parameters.put(HIERARCHY_NO, dto.getHierarchyNo());
				resultList = dataSelectionDaoImpl.getCcpMap(parameters);
				for (final Object ccpId : resultList) {
					customerCcpList.add(String.valueOf(ccpId));
				}
			}
		}
		return customerCcpList;
	}

	public List<String> getRbIds(final int hierarchyDefinitionSid) throws SystemException {
		final List<String> returnList = new ArrayList<>();
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "getRbId");
		parameters.put(StringConstantsUtil.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid);
		final List<Object> resultList = dataSelectionDaoImpl.getCcpMap(parameters);
		for (final Object rbSid : resultList) {
			returnList.add(String.valueOf(rbSid));
		}
		return returnList;
	}

	public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos,
			final String indicator, final String projectionId) throws SystemException {
		if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
			Map<String, Object> parameters;
			for (final Leveldto dto : customerEndLevels) {
				parameters = new HashMap<>();
				parameters.put(PROJECTION_ID, projectionId);
				parameters.put(INDICATOR, indicator);
				parameters.put(StringConstantsUtil.RELATIONSHIP_LEVEL_SID, dto.getRelationshipLevelSid());
				parameters.put(HIERARCHY_NO, dto.getHierarchyNo());
				parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
				parameters.put(INDICATOR, "saveCcp");
				dataSelectionDaoImpl.getCcpMap(parameters);
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
					.executeSelectQuery(CommonQueryUtils.getAppQuery(inputs, "getParentLevelsWithHierarchyNo_New"));

			if (resultss != null) {
				resultList = new ArrayList<>();
				for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
					final Object[] objects = (Object[]) resultss.get(loop);
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
					if (descriptionMap != null) {
						dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[NumericConstants.EIGHT])));
					}
					resultList.add(dto);
				}
			}
		} catch (final NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
		}
		return resultList;
	}

	public int getRelationshipSidCount(String filterText, final int hierarchyDefinitionSid)
			throws SystemException, PortalException {
		final int count = dataSelectionDaoImpl
				.getRelationshipCount(getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid));
		return count + 1;
	}

	public List<RelationshipDdlbDto> getRelationshipSidLazy(int startIndex, int endIndex,
			final RelationshipDdlbDto defaultRelationshipDdlbDto, String filterText, final int hierarchyDefinitionSid,
			RelationshipDdlbDto selectedRelationshipDdlbDto) throws SystemException, PortalException {
		final List<RelationshipDdlbDto> returnList = new ArrayList<>();
		final DynamicQuery dynamicQuery = getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid);
		dynamicQuery.setLimit(startIndex, endIndex);
		final List<Object[]> resultList = dataSelectionDaoImpl.getRelationship(dynamicQuery);
		RelationshipDdlbDto relationshipDdlbDto;
		if (startIndex == 0) {
			returnList.add(defaultRelationshipDdlbDto);
		}
		if (selectedRelationshipDdlbDto == null) {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				final Object[] objects = resultList.get(loop);
				relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
				returnList.add(relationshipDdlbDto);
			}
		} else {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				final Object[] objects = resultList.get(loop);
				if (String.valueOf(objects[0]).equals(selectedRelationshipDdlbDto.getRelationshipBuilderSid())) {
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
		final DynamicQuery dynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();
		filterText = StringUtils.trimToEmpty(filterText) + "%";
		dynamicQuery
				.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_BUILDER_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_NAME));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		dynamicQuery.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.RELATIONSHIP_NAME, filterText));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		return dynamicQuery;
	}

	public List<RelationshipDdlbDto> getRelationshipSids(final RelationshipDdlbDto defaultRelationshipDdlbDto,
			final int hierarchyDefinitionSid) throws SystemException, PortalException {
		final List<RelationshipDdlbDto> returnList = new ArrayList<>();
		final DynamicQuery dynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();
		dynamicQuery
				.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_BUILDER_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_NAME));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		final List<Object[]> resultList = dataSelectionDaoImpl.getRelationship(dynamicQuery);
		RelationshipDdlbDto relationshipDdlbDto;
		returnList.add(defaultRelationshipDdlbDto);
		for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
			final Object[] objects = resultList.get(loop);
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
			String query;
			if (!String.valueOf(businessUnit).equals("null") && !String.valueOf(businessUnit).equals("0")
					&& !String.valueOf(businessUnit).isEmpty()) {
				query = relationLogic.getChildLevelQueryForProduct(selectedLevelDto, relationShipVersion,
						String.valueOf(businessUnit), lowestLevelNo, subListIndex);
				resultss = HelperTableLocalServiceUtil.executeSelectQuery(query);
			} else {
				query = "getChildLevelsWithHierarchyNo_New";
				resultss = HelperTableLocalServiceUtil.executeSelectQuery(CommonQueryUtils.getAppQuery(inputs, query));
			}

			if (resultss != null) {
				resultList = new ArrayList<>();
				for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
					Object[] objects = resultss.get(loop);
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

	public void getEndLevelRelationshipLevels(final List<String> endLevelSids, final String relationshipSid,
			List<Leveldto> ccList, List<String> availableHierNo) throws SystemException {
		Leveldto dto;
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "getChildLevelRL");
		parameters.put(StringConstantsUtil.RELATIONSHIP_SID, relationshipSid);
		parameters.put(StringConstantsUtil.RL_SIDS, endLevelSids);
		parameters.put("availableHierNo", availableHierNo);
		final List<Object> endLevels = dataSelectionDaoImpl.executeQuery(parameters);
		for (int i = 0, j = endLevels.size(); i < j; i++) {
			dto = new Leveldto();
			final Object[] obj = (Object[]) endLevels.get(i);
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
				if (StringConstantsUtil.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromCompany(true);
				} else if (StringConstantsUtil.CONTRACT_MASTER
						.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
					dto.setFromContract(true);
				} else if (StringConstantsUtil.ITEM_MASTER
						.equalsIgnoreCase(String.valueOf(obj[NumericConstants.FOUR]))) {
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
		String query = SQlUtil.getQuery("getFileEndDate");
		query = query.replace("[?BUSINESS_UNIT]", StringUtils.EMPTY + dto.getBusinessUnitSystemId());
		final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
		if (list != null && !list.isEmpty()) {
			final Object[] tempDate = (Object[]) list.get(0);
			if (tempDate[0] != null) {
				dto.setFileEndYear(DataTypeConverter.convertObjectToInt(tempDate[0]));
			} else {
				dto.setFileEndYear(0);
			}
			if (tempDate[1] != null) {
				dto.setFileEndMonth(DataTypeConverter.convertObjectToInt(tempDate[1]));
			} else {
				dto.setFileEndMonth(0);
			}
		} else {
			dto.setFileEndYear(0);
			dto.setFileEndMonth(0);
		}
	}

	public void deleteTempOnUpdate(final String projectionHierarchyTable, final int projectionId, final String hNos)
			throws SystemException {
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "deleteTempOnUpdate");
		parameters.put("projectionHierarchyTable", projectionHierarchyTable);
		parameters.put(PROJECTION_ID, projectionId);
		parameters.put("hNos", hNos);
		dataSelectionDaoImpl.executeQuery(parameters);
	}

	public Object deleteProjection(final int projectionId) throws SystemException {
		final Map<String, Object> input = new HashMap<>();
		input.put("?PID", projectionId);
		return dataSelectionDaoImpl.tempOperation(input, "deleteProjection");
	}

	public List getFSValue(String relationshipLevelValue, final String fieldName) {
		List list = new ArrayList();
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "getFSValue");
		parameters.put("relationshipLevelValue", relationshipLevelValue);
		parameters.put("fieldName", fieldName);
		try {
			list = dataSelectionDaoImpl.executeQuery(parameters);
			return list;
		} catch (final SystemException e) {
			LOGGER.error("in getFSValue={}", e);
			return Collections.emptyList();
		}
	}

	public List<Integer> getItemIdFromCompanyForGlComp(final int companySid) throws SystemException {
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "companyFilter");
		parameters.put("companySid", companySid);
		final List resultList = dataSelectionDaoImpl.executeQuery(parameters);
		final List<Integer> sidList = new ArrayList<>();
		for (final Object sid : resultList) {
			sidList.add(DataTypeConverter.convertObjectToInt(sid));
		}
		return sidList;
	}

	public Map<String, String> getLevelValueMap(Object relationshipBuilderSID) throws SystemException {
		final Map<String, Object> input = new HashMap<>();
		input.put(StringConstantsUtil.RBSID, relationshipBuilderSID);
		return (Map<String, String>) dataSelectionDaoImpl.tempOperation(input, "getHierarchyTableDetails");
	}

	public List<RelationshipDdlbDto> getRelationshipSid(final int hierarchyDefinitionSid)
			throws SystemException, PortalException {
		final List<RelationshipDdlbDto> returnList = new ArrayList<>();
		final DynamicQuery dynamicQuery = getRelationshipSidDynamicQuery(hierarchyDefinitionSid);
		final List<Object[]> resultList = dataSelectionDaoImpl.getRelationship(dynamicQuery);
		RelationshipDdlbDto relationshipDdlbDto;
		for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
			final Object[] objects = resultList.get(loop);
			relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
			returnList.add(relationshipDdlbDto);
		}
		return returnList;
	}

	private DynamicQuery getRelationshipSidDynamicQuery(final int hierarchyDefinitionSid) {
		final DynamicQuery dynamicQuery = RelationshipBuilderLocalServiceUtil.dynamicQuery();
		dynamicQuery
				.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.HIERARCHY_DEFINITION_SID, hierarchyDefinitionSid));
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_BUILDER_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.RELATIONSHIP_NAME));
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
		final List<CompanyDdlbDto> companies = new ArrayList<>();
		if (companySids != null && !companySids.isEmpty()) {
			final DynamicQuery helper = HelperTableLocalServiceUtil.dynamicQuery();
			final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
			helperProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.HELPER_TABLE_SID));
			helper.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LIST_NAME, "COMPANY_TYPE"));
			helper.add(RestrictionsFactoryUtil.like(StringConstantsUtil.DESCRIPTION1, StringConstantsUtil.GLCOMP));
			helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
			final List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
			int companyId = 0;
			companyId = Integer.parseInt(String.valueOf(companyTypeIds.get(0)));
			final DynamicQuery dynamicQuery = CompanyMasterLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.in(StringConstantsUtil.COMPANY_MASTER_SID,
					UiUtils.convertStringListToIngeter(companySids)));
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_MASTER_SID));
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.COMPANY_NAME_PROPERTY));
			dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			dynamicQuery.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.COMPANY_TYPE_PROPERTY, companyId));
			final List<Object[]> returnlist = dataSelectionDaoImpl.getCompanies(dynamicQuery);
			CompanyDdlbDto companyDdlbDto;
			for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
				final Object[] objects = returnlist.get(loop);
				companyDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
						String.valueOf(objects[1]));
				companies.add(companyDdlbDto);
			}
		}
		LOGGER.debug("Companies return size= {}", companies.size());
		return companies;
	}

	// Added For Mandated
	public String getGenerateMarketValue(int rbID) {
		final List<Object> temp = getGenerateMarketValueResult(rbID);
		String marketType = "";
		if (!temp.isEmpty() && String.valueOf(temp.get(0)) != null && !"".equals(String.valueOf(temp.get(0)))) {
			marketType = String.valueOf(temp.get(0));
		}

		return marketType;
	}

	public List<Object> getGenerateMarketValueResult(int rbID) {
		try {
			List<Object> list;
			final StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
			queryString.append("select RELATIONSHIP_LEVEL_VALUES from RELATIONSHIP_LEVEL_DEFINITION where \n"
					+ "RELATIONSHIP_BUILDER_SID='" + rbID + "'\n" + "and \n" + "LEVEL_NAME='Market Type'");
			final CommonDAO salesProjectionDAO = new CommonDAOImpl();
			list = (List) salesProjectionDAO.executeSelectQuery(queryString.toString());
			return list;
		} catch (final Exception ex) {
			LOGGER.error(ex.getMessage());
			return Collections.emptyList();
		}
	}

	public String getDefinedValue(String definedValue) {
		String str = "";
		final List<Object> listValue = getDefinedValueResult(definedValue);
		if (!listValue.isEmpty()) {
			for (int i = 0; i < listValue.size(); i++) {
				str = String.valueOf(listValue.get(0));
			}
		}

		return str;

	}

	public List<Object> getDefinedValueResult(String definedValue) {
		String str = "";
		try {
			final CommonDAO salesProjectionDAO = new CommonDAOImpl();
			str = "select LEVEL_VALUE_REFERENCE from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID="
					+ definedValue + " and LEVEL_NAME='Market Type'";
			final List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str);
			return list;
		} catch (final Exception e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public String getHelperValue(String marketType) {
		String marketTypeValue = "";
		final List<Object> temp = getHelperValueResult(marketType);
		if (!temp.isEmpty()) {
			for (int i = 0; i < temp.size(); i++) {
				marketTypeValue = String.valueOf(temp.get(i));
			}
		}
		return marketTypeValue;
	}

	public List<Object> getHelperValueResult(String projId) {
		String str = "";
		try {
			final CommonDAO salesProjectionDAO = new CommonDAOImpl();
			str = "select DESCRIPTION from HELPER_TABLE where\n"
					+ " HELPER_TABLE_SID in(select RELATIONSHIP_LEVEL_VALUES\n" + "from RELATIONSHIP_LEVEL_DEFINITION\n"
					+ " where RELATIONSHIP_BUILDER_SID in(Select CUST_RELATIONSHIP_BUILDER_SID\n"
					+ " from PROJECTION_MASTER where PROJECTION_MASTER_SID=" + projId
					+ ") AND LEVEL_NAME='Market Type') \n" + "and LIST_NAME='CONTRACT_TYPE';";
			final List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str);
			return list;
		} catch (final Exception e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public int getDiscountCount(String filterText) {
		try {
			final DynamicQuery query = HelperTableLocalServiceUtil.dynamicQuery();
			filterText = StringUtils.trimToEmpty(filterText) + "%";
			final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.HELPER_TABLE_SID));
			productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.DESCRIPTION1));
			query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
			query.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.DESCRIPTION1, filterText));
			query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LIST_NAME, "RS_TYPE"));
			discountDdlbCount =(int) HelperTableLocalServiceUtil.dynamicQueryCount(query);
		} catch (final Exception ex) {
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
		final List<CompanyDdlbDto> discounts = new ArrayList<>();
		final int startValue = startIndex;
		final int endValue = endIndex;
		if (startIndex == 0) {
			discounts.add(discountDdlbDefault);
		}
		CompanyDdlbDto discountDdlbDto;
		final DynamicQuery query = HelperTableLocalServiceUtil.dynamicQuery();
		filterText = StringUtils.trimToEmpty(filterText) + "%";
		final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.HELPER_TABLE_SID));
		productProjectionList.add(ProjectionFactoryUtil.property(StringConstantsUtil.DESCRIPTION1));
		query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
		query.add(RestrictionsFactoryUtil.ilike(StringConstantsUtil.DESCRIPTION1, filterText));
		query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LIST_NAME, "RS_TYPE"));
		query.setLimit(startValue, endValue);
		final List<Object[]> resultList = dataSelectionDaoImpl.getDiscounts(query);
		if (selectedDiscount == null) {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				final Object[] objects = resultList.get(loop);
				discounts.add(
						new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])), String.valueOf(objects[1])));
			}
		} else {
			for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
				final Object[] objects = resultList.get(loop);
				if (Integer.valueOf(String.valueOf(objects[0])).equals(selectedDiscount.getRsModelSid())) {
					selectedDiscount.setRsNo(String.valueOf(objects[1]));
					discounts.add(selectedDiscount);
				} else {
					discountDdlbDto = new CompanyDdlbDto(Integer.parseInt(String.valueOf(objects[0])),
							String.valueOf(objects[1]));
					discounts.add(discountDdlbDto);
				}
			}
		}
		return discounts;
	}

	public void callSalesInsertProcedure(int projectionId, String userId, String sessionId, String procedureName) {
		LOGGER.debug("In callSalesInsertProcedure");
		LOGGER.debug("ProcedureName---> {} ", procedureName);
		LOGGER.debug("ProjectionId----> {} ", projectionId);
		LOGGER.debug("UserId----------> {} ", userId);
		LOGGER.debug("SessionId-------> {} ", sessionId);

		GtnSqlUtil.procedureCallService(procedureName, new Object[] { projectionId, userId, sessionId });
	}

	public String getMarketTypeValue(int ProjectId) {
		final List<Object> temp = getMarketType(ProjectId);
		String marketType = "";
		if (!temp.isEmpty()) {
			final Object[] objects = (Object[]) temp.get(0);
			if (String.valueOf(objects[0]) != null && !"".equals(String.valueOf(objects[0]))) {
				marketType = String.valueOf(objects[0]);
			}
		}

		return marketType;
	}

	public List<Object> getMarketType(int projectionId) {
		try {
			List list;
			final StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
			queryString.append("select RELATIONSHIP_LEVEL_VALUES,LEVEL_NO from RELATIONSHIP_LEVEL_DEFINITION \n"
					+ "where RELATIONSHIP_LEVEL_SID in ( select RELATIONSHIP_LEVEL_SID\n"
					+ "from PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID= ");
			if (projectionId != 0) {
				queryString.append(Integer.toString(projectionId));
				queryString.append(" ) and LEVEL_NAME='Market TYPE'");
			}
			list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
			return list;

		} catch (final Exception ex) {
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
	 * @param indicator
	 * @param start
	 * @param offset
	 * @param filters
	 * @param sortByColumns
	 * @return the Product group result list
	 * @throws java.lang.Exception
	 */
	public List<GroupDTO> searchGroup(String name, String no, List<String> sids, String indicator,
			String groupIdentifier, String action, int start, int offset, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns) throws SystemException {
		List resultList = null;
		List<GroupDTO> returnList = null;
		name = name.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		no = no.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		final Map<String, Object> parameters = prepareSearchGroup(name, no, null, indicator, action, groupIdentifier,
				start, offset, filters, sortByColumns);
		resultList = dataSelectionDaoImpl.executeQueryforchannel(parameters);
		try {
			if (Constants.CUSTOMER_GROUP.equals(groupIdentifier)) {
				returnList = Converters.convertCustomerGroupList(resultList);
			} else {
				returnList = Converters.convertItemGroupList(resultList);
			}
		} catch (final Exception ex) {
			LOGGER.error(" in searchGroup={}", ex);
		}
		return returnList;
	}

	public int searchGroupCount(String name, String no, List<String> sids, String indicator, String groupIdentifier,
			String action) throws SystemException {
		List<Object> countList = null;
		int count = 0;
		name = name.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		no = no.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
		final Map<String, Object> parameters = prepareSearchGroup(name, no, null, indicator, action, groupIdentifier, 0,
				0, null, null);
		countList = dataSelectionDaoImpl.executeQueryforchannel(parameters);
		if (countList != null && !countList.isEmpty()) {
			count = Integer.parseInt(String.valueOf(countList.get(0)));
		}
		return count;
	}

	private Map<String, Object> prepareSearchGroup(String name, String no, List<String> sids, String indicator,
			String action, String groupIdentifier, int start, int offset, Set<Container.Filter> filters,
			List<SortByColumn> sortByColumns) {
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		parameters.put("no", no);
		if (sids != null && !sids.isEmpty()) {
			parameters.put("sids", UiUtils.stringListToString(sids));
		} else {
			parameters.put("sids", null);
		}
		parameters.put("action", action);
		parameters.put("groupIdentifier", groupIdentifier);
		parameters.put(INDICATOR, indicator);

		if (!"count".equals(action)) {
			parameters.put("start", start);
			parameters.put("offset", offset);
		} else {
			parameters.put("start", null);
			parameters.put("offset", null);
		}
		parameters.put(StringConstantsUtil.IS_FILTERED, StringConstantsUtil.STRING_FALSE);
		parameters.put(StringConstantsUtil.IS_ORDERED, StringConstantsUtil.STRING_FALSE);
		if (filters != null) {
			for (final Container.Filter filter : filters) {
				if (filter instanceof SimpleStringFilter) {
					final SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
					String filterString = "%" + stringFilter.getFilterString() + "%";
					if (!"createdBy".equals(stringFilter.getPropertyId())) {
						parameters.put(FILTER + stringFilter.getPropertyId(), filterString);
					} else {
						filterString = stringFilter.getFilterString();
						parameters.put(FILTER + stringFilter.getPropertyId(),
								DataSelectionUtil.filterUser(filterString));
					}
					parameters.put(StringConstantsUtil.IS_FILTERED, "true");
				} else if (filter instanceof Between) {
					final Between betweenFilter = (Between) filter;
					final Date startValue = (Date) betweenFilter.getStartValue();
					final Date endValue = (Date) betweenFilter.getEndValue();
					parameters.put(FILTER + betweenFilter.getPropertyId() + "~from", String.valueOf(startValue));
					parameters.put(FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
					parameters.put(StringConstantsUtil.IS_FILTERED, "true");
				} else if (filter instanceof Compare) {
					final Compare compare = (Compare) filter;
					final Compare.Operation operation = compare.getOperation();
					final Date value = (Date) compare.getValue();
					if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
						parameters.put(FILTER + compare.getPropertyId() + "~from", String.valueOf(value));
					} else {
						parameters.put(FILTER + compare.getPropertyId() + "~to", String.valueOf(value));
					}
					parameters.put(StringConstantsUtil.IS_FILTERED, "true");
				}
			}
		}
		if (sortByColumns != null) {
			for (final Iterator<SortByColumn> it = sortByColumns.iterator(); it.hasNext();) {
				final SortByColumn orderByColumn = it.next();
				final String columnId = orderByColumn.getName();
				if (orderByColumn.getType() == SortByColumn.Type.ASC) {
					parameters.put("orderBy~" + columnId, "asc");
					parameters.put(StringConstantsUtil.IS_ORDERED, "true");
				} else {
					parameters.put("orderBy~" + columnId, "desc");
					parameters.put(StringConstantsUtil.IS_ORDERED, "true");
				}
			}
		}
		return parameters;
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
		for (final Leveldto dto : selectedProductContainer.getItemIds()) {
			if (length < dto.getDisplayValue().length()) {
				length = dto.getDisplayValue().length();
			}
		}
		if (length > NumericConstants.FIFTY) {
			if (length <= NumericConstants.SIXTY) {
				selectedProduct.setColumnWidth(StringConstantsUtil.DISPLAY_VALUE, NumericConstants.FIVE_ONE_ZERO);
			} else if (length <= NumericConstants.SEVENTY) {
				selectedProduct.setColumnWidth(StringConstantsUtil.DISPLAY_VALUE, NumericConstants.FIVE_SEVEN_ZERO);
			} else if (length <= NumericConstants.EIGHTY) {
				selectedProduct.setColumnWidth(StringConstantsUtil.DISPLAY_VALUE, NumericConstants.SIX_THREE_ZERO);
			} else if (length <= NumericConstants.NINETY) {
				selectedProduct.setColumnWidth(StringConstantsUtil.DISPLAY_VALUE, NumericConstants.SEVEN_THREE_ZERO);
			} else if (length <= NumericConstants.HUNDRED) {
				selectedProduct.setColumnWidth(StringConstantsUtil.DISPLAY_VALUE, NumericConstants.EIGHT_ONE_ZERO);
			}
		} else {
			selectedProduct.setColumnWidth(StringConstantsUtil.DISPLAY_VALUE, -1);
		}
	}

	public String getContractValue(String definedValue) {
		String str = "";
		final List<Object> listValue = getContractValueResult(definedValue);
		if (!listValue.isEmpty()) {
			for (int i = 0; i < listValue.size(); i++) {
				str = String.valueOf(listValue.get(0));
			}
		}

		return str;

	}

	public List<Object> getContractValueResult(String definedValue) {
		String str = "";
		try {
			final CommonDAO salesProjectionDAO = new CommonDAOImpl();
			str = "select FIELD_NAME from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue
					+ " and  LEVEL_NAME in('Customer','Trading Partner')";
			final List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str);
			return list;
		} catch (final Exception e) {
			LOGGER.error(e.getMessage());
			return Collections.emptyList();
		}
	}

	public List getProjection(int projectionId) {
		final String sql = "SELECT CFF_NAME,PROD_RELATIONSHIP_BUILDER_SID,CUST_RELATIONSHIP_BUILDER_SID,CFF_ELIGIBLE_DATE From dbo.CFF_MASTER where CFF_MASTER_SID = "
				+ projectionId;
		final List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
		if (list.isEmpty()) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

	public Boolean hasTradingPartner(int projectionId) throws SystemException {
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(INDICATOR, "hasTradingPartner");
		parameters.put(PROJECTION_ID, projectionId);
		final List returnList = vDataSelectionDao.executeQuery(parameters);
		if (returnList.isEmpty()) {
			return BooleanConstant.getTrueFlag();
		} else {
			return (Integer) returnList.get(0) >= 1;
		}
	}

	public Map<String, List> getLevelValueDetails(SessionDTO sessionDTO, Object relationshipBuilderSID,
			boolean isCustomerHierarchy) {
		return getRelationshipDetails(sessionDTO, relationshipBuilderSID.toString(), isCustomerHierarchy);
	}

	/**
	 *
	 * @param relationshipBuilderSid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, List> getRelationshipDetails(SessionDTO sessionDTO, String relationshipBuilderSid,
			boolean isCustomerHierarchy) {
		String customSql = SQlUtil.getQuery("getHierarchyTableDetails");
		customSql = customSql.replace(StringConstantsUtil.RBSID, relationshipBuilderSid);
		customSql = customSql.replace("?RLDV", isCustomerHierarchy ? sessionDTO.getCustomerRelationVersion()+ StringUtils.EMPTY 
                : sessionDTO.getProductRelationVersion()+ StringUtils.EMPTY);
		customSql = customSql.replace("?HLDV", isCustomerHierarchy ? sessionDTO.getCustomerHierarchyVersion()+ StringUtils.EMPTY 
                : sessionDTO.getProductHierarchyVersion()+ StringUtils.EMPTY);
		List<Object[]> tempList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(customSql);
		Map<String, List> resultMap = new HashMap<>();
		String hierarchyNoType = isCustomerHierarchy ? "CUST_HIERARCHY_NO" : "PROD_HIERARCHY_NO";
		RelationshipLevelValuesMasterBean bean = new RelationshipLevelValuesMasterBean(tempList, relationshipBuilderSid,
				hierarchyNoType, sessionDTO);
		tempList.clear();
		tempList = HelperTableLocalServiceUtil.executeSelectQuery(
				QueryUtil.replaceTableNames(bean.getFinalQuery(), sessionDTO.getCurrentTableNames()));
		for (int j = tempList.size() - 1; j >= 0; j--) {
			final Object[] object = tempList.get(j);
			final List<Object> detailsList = new ArrayList<>();
			detailsList.add(object[1]); // Level Value
			detailsList.add(object[NumericConstants.TWO]); // Level No
			detailsList.add(object[NumericConstants.THREE]); // Level Name
			detailsList.add(object[NumericConstants.FOUR]); // RL Level Value - Actual System Id
			detailsList.add(isCustomerHierarchy ? "C" : "P"); // HIERARCHY INDICATOR
			commonUtils.updateRelationShipLevelList(object, detailsList, String.valueOf(object[1]));
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
        public Date getDefaultEligibleDateFromForecastConfiguration() {
            String query = "SELECT  PROJECTION_START_DATE FROM   [Udf_na_proj_dates]('Consolidated Financial Forecast')";
            List cffEligibleDatelist = HelperTableLocalServiceUtil.executeSelectQuery(query);
            return (Date) cffEligibleDatelist.get(0);
        }
            public String getremovedcontractbasedonCFFEligibleDate(final SessionDTO session) {
            List<Object> inputList = new ArrayList();
		inputList.add(session.getProjectionId());
            List<Object[]> removedcontract = CommonQueryUtils.getAppData(inputList, "CFFeligibledatealertquery",null);
            return   removedcontract.toString().replace("[", "").replace("]", "");
        }
}
