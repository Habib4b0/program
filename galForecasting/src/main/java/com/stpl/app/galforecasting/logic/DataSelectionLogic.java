/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.DataSelectionDAO;
import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.CompanyDdlbDto;
import com.stpl.app.galforecasting.dto.RelationshipDdlbDto;
import com.stpl.app.galforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.AbstractFilterLogic;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.galforecasting.utils.Converters;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
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
import static com.stpl.app.service.NmSalesProjectionMasterLocalServiceUtil.executeSelectQuery;
import com.stpl.app.service.ProjectionCustHierarchyLocalServiceUtil;
import com.stpl.app.service.ProjectionProdHierarchyLocalServiceUtil;
import com.stpl.app.utils.QueryUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSelectionLogic.
 *
 * @author soundarrajan
 */
public class DataSelectionLogic {

    /**
     * The data selection dao.
     */
    DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionLogic.class);
    int discountDdlbCount = 0;
    CommonDAO salesProjectionDAO = new CommonDAOImpl();

    /**
     * Gets the hierarchy group.
     *
     * @param hierarchyName the hierarchy name
     * @param hierarchyType the hierarchy type
     * @param customerOrProduct the customer or product
     * @param action the action
     * @return the hierarchy group
     */
    public List<HierarchyLookupDTO> getHierarchyGroup(String hierarchyName, String hierarchyType, final String customerOrProduct, final String action) throws ParseException {

        List<HierarchyLookupDTO> resultList = new ArrayList<HierarchyLookupDTO>();

        if (hierarchyName.contains(String.valueOf(CommonUtils.CHAR_ASTERISK))) {
            hierarchyName = hierarchyName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }
        if (hierarchyType.contains(String.valueOf(CommonUtils.CHAR_ASTERISK))) {
            hierarchyType = hierarchyType.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        }

        List result = dataSelectionDao.getHierarchyGroup(hierarchyName, hierarchyType, customerOrProduct, action);

        HierarchyLookupDTO hdto;
        for (int i = 0; i < result.size(); i++) {
            hdto = new HierarchyLookupDTO();
            final Object[] obj = (Object[]) result.get(i);
            hdto.setHierarchyId(Integer.valueOf(obj[0].toString()));
            hdto.setHierarchyName(String.valueOf(obj[1].toString()));
            hdto.setHighestLevel(String.valueOf(obj[3].toString()));
            hdto.setLowestLevel(String.valueOf(obj[4].toString()));
            hdto.setCreatedDate(String.valueOf(obj[5].toString()));
            hdto.setCreatedDateSearch(Converters.parseDate(String.valueOf(obj[5].toString())));
            if (obj[6] != null) {
                hdto.setModifiedDate(String.valueOf(obj[6].toString()));
                hdto.setModifiedDateSearch(Converters.parseDate(String.valueOf(obj[6].toString())));
            }
            hdto.setVersionNo(Integer.parseInt(String.valueOf(obj[7].toString())));

            if (obj[2] != null) {

            }
            if (obj[3] != null) {

            }
            resultList.add(hdto);

        }

        return resultList;
    }

    /**
     * Load customer forecast level.
     *
     * @param hierarchyId the hierarchy id
     * @param hierarchyName the hierarchy name
     * @return the list
     */
    public List<Leveldto> loadCustomerForecastLevel(int hierarchyId, String hierarchyName) {
        List<Leveldto> resultList = new ArrayList<Leveldto>();
        Leveldto leveldto;
        Map<String, Object> parameters = new HashMap<String, Object>();
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
                leveldto.setLevelValueReference(object[2] == null ? StringUtils.EMPTY : String.valueOf(object[2]));
                leveldto.setTableName(object[3] == null ? StringUtils.EMPTY : String.valueOf(object[3]));
                leveldto.setFieldName(object[4] == null ? StringUtils.EMPTY : String.valueOf(object[4]));
                if (object[3] != null && !StringUtils.isEmpty(String.valueOf(object[3])) && !StringUtils.isBlank(String.valueOf(object[3]))) {
                    if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(object[3]))) {
                        leveldto.setFromCompany(true);
                    } else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(object[3]))) {
                        leveldto.setFromContract(true);
                    } else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(object[3]))) {
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
            LOGGER.error(e.getMessage() + " in DSLogic - loadCustomerForecastLevel");
        }
        return resultList;
    }

    /**
     * Load inner customer level.
     *
     * @param levelName the level name
     * @param hierarchyId the hierarchy id
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
     * @return the list
     * @throws java.lang.Exception
     */
    public List<Leveldto> loadInnerLevel(String levelName, int hierarchyId, List<Integer> selectedLevelSids, final boolean isNdc,
            final String fieldName, final String relationshipSid, final Map<String, String> descriptionMap, final String level,
            final String screenName, int discountID, int levelNo, String deductionID, String deductionLevel) throws Exception {
        List<Leveldto> values = new ArrayList<>();
        List result;
        Map<String, Object> parameters = new HashMap<>();
        Leveldto dto;
        if (isNdc) {
            parameters.put("isNdc", Constant.TRUE);
        } else {
            parameters.put("isNdc", "false");
        }
        parameters.put(Constant.HIERARCHYDEFINITIONSID, hierarchyId);
        parameters.put(Constant.LEVELNAME, levelName);
        parameters.put("level", level);
        parameters.put("relationshipLevelSidList", selectedLevelSids);
        parameters.put(Constant.FIELD_NAME, fieldName);
        if (StringUtils.isBlank(relationshipSid) || Constant.NULL.equals(String.valueOf(relationshipSid)) || DASH.equals(String.valueOf(relationshipSid))) {
            parameters.put("relationshipSid", Constant.NULL);
        } else {
            parameters.put("relationshipSid", relationshipSid);
        }
        parameters.put("discount", discountID);
        parameters.put("levelNo", levelNo);
        parameters.put(Constant.SCREEN_NAME, screenName);
        parameters.put("deductionValue", StringUtils.EMPTY + deductionID);
        parameters.put("deductionLevel", deductionLevel);
        result = (dataSelectionDao.getInnerLevel(parameters) !=null || !dataSelectionDao.getInnerLevel(parameters).isEmpty()) ? dataSelectionDao.getInnerLevel(parameters) : Collections.EMPTY_LIST;
        if(!result.isEmpty() && result != null){
        for (int i = 0; i < result.size(); i++) {
            dto = new Leveldto();
            Object[] obj = (Object[]) result.get(i);
            dto.setLevel(String.valueOf(obj[8]));
            dto.setRelationshipLevelValue(String.valueOf(obj[0]));
            dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
            dto.setParentNode(String.valueOf(obj[2]));
            dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[3])));
            dto.setTableName(String.valueOf(obj[4]));
            dto.setFieldName(String.valueOf(obj[5]));
            dto.setHierarchyNo(String.valueOf(obj[6]));
            dto.setRelationShipBuilderId(String.valueOf(obj[7]));
            if (obj[4] != null && !StringUtils.isEmpty(String.valueOf(obj[4])) && !StringUtils.isBlank(String.valueOf(obj[4]))) {
                if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromCompany(true);
                } else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromContract(true);
                } else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromItem(true);
                } else {
                    dto.setFromCompany(false);
                    dto.setFromContract(false);
                    dto.setFromItem(false);
                }
            }

            if (isNdc) {
                dto.setNdc(String.valueOf(obj[7]));
                dto.setForm(String.valueOf(obj[8]));
                dto.setStrength(String.valueOf(obj[9]));
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
     * @param hierarchyId the hierarchy id
     * @return the list
     */
    public List<String> filterForGroup(List<String> levelNo, int hierarchyId) {
        List<String> values = new ArrayList<String>();
        List result = null;
        Leveldto dto;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);

        dynamicQuery.add(PropertyFactoryUtil.forName("relationshipBuilderSid").in(
                DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class)
                .add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyId))
                .setProjection(ProjectionFactoryUtil.property("relationshipBuilderSid"))));

        dynamicQuery.add(RestrictionsFactoryUtil.in("levelNo", levelNo));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

        productProjectionList.add(ProjectionFactoryUtil.distinct((ProjectionFactoryUtil.property("relationshipLevelValues"))));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
        productProjectionList.add(ProjectionFactoryUtil.property("parentNode"));
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipLevelSid"));

        dynamicQuery.setProjection(productProjectionList);
        result = dataSelectionDao.getCustomerForecastLevel(dynamicQuery);
        for (int i = 0; i < result.size(); i++) {

            Object[] obj = (Object[]) result.get(i);

            values.add(String.valueOf(obj[0]));
        }

        return values;
    }

    public List<CompanyMaster> getCompanyFromSids(final List<String> companySids) throws Exception {

        List<Integer> sids = new ArrayList<Integer>();

        for (String sid : companySids) {
            sids.add(Integer.parseInt(sid));
        }

        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, sids));
        List<CompanyMaster> companies = dataSelectionDao.getCompanyMasterList(dynamicQuery);
        return companies;
    }

    public List<ItemMaster> getItemsFromSids(final List<String> itemSids) throws Exception {

        List<Integer> sids = new ArrayList<Integer>();
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
     * @param levelList the level list
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void updateProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> removeLevels, final List<String> addLevels) throws Exception {
        if (removeLevels != null && !removeLevels.isEmpty()) {
            List<Object> removeChildLevels = null;
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put(Constant.INDICATOR, "getRemovableChildren");
            parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
            parameters.put("tableName", " PROJECTION_PROD_HIERARCHY ");
            parameters.put(Constant.PROJECTION_ID, projectionId);
            removeChildLevels = dataSelectionDao.executeQuery(parameters);
            deleteProductHierarchyLevels(projectionId, removeLevels);
            if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
                deleteProductHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
            }
        }
        saveProductHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, Constant.UPDATE);
    }

    private void deleteProductHierarchyLevels(final int projectionId, final List<String> removeLevels) throws Exception {
        List<ProjectionProdHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipLevelSid", UiUtils.convertStringListToParsedIngeter(removeLevels)));
        details = dataSelectionDao.findProdHierarchyByProjectionId(dynamicQuery);
        for (final ProjectionProdHierarchy prod : details) {
            dataSelectionDao.deleteProjectionProdHierarchies(prod);
        }
    }

    public void updateProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId) throws Exception {
        List<ProjectionProdHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionProdHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        details = dataSelectionDao.findProdHierarchyByProjectionId(dynamicQuery);
        for (final ProjectionProdHierarchy cust : details) {
            dataSelectionDao.deleteProjectionProdHierarchies(cust);
        }
        saveProductHierarchyLogic(levelList, endLevelSids, projectionId, null, Constant.SAVE);
    }

    /**
     * Save Product hierarchy logic.
     *
     * @param levelList the level list
     * @throws java.lang.Exception
     */
    public void saveProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) throws Exception {
        LOGGER.info("saveProductHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "getChildLevelRLSid");
        parameters.put("rlSids", endLevelSids);
        parameters.put(Constant.PROJECTION_ID, projectionId);
        parameters.put("tableName", "PROJECTION_PROD_HIERARCHY");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDao.executeQuery(parameters);
        }
        ProjectionProdHierarchy projectionProdHierarchy = ProjectionProdHierarchyLocalServiceUtil.createProjectionProdHierarchy(0);
        try {
            if (Constant.UPDATE.equals(indicator)) {
                for (String rsId : addLevels) {
                    projectionProdHierarchy.setProjectionMasterSid(projectionId);
                    projectionProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDao.addProjectionProdHierarchy(projectionProdHierarchy);
                }
            } else if (Constant.SAVE.equals(indicator)) {
                for (Leveldto dto : levelList) {

                    projectionProdHierarchy.setProjectionMasterSid(projectionId);
                    projectionProdHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDao.addProjectionProdHierarchy(projectionProdHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    projectionProdHierarchy.setProjectionMasterSid(projectionId);
                    projectionProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDao.addProjectionProdHierarchy(projectionProdHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage() + " saveProductHierarchyLogic");
        }
    }

    /**
     * Delete and inserts customer hierarchy logic to update
     *
     * @param levelList the level list
     * @param endLevelSids
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void updateCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> removeLevels, final List<String> addLevels) throws Exception {
        if (removeLevels != null && !removeLevels.isEmpty()) {
            List<Object> removeChildLevels = null;
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put(Constant.INDICATOR, "getRemovableChildren");
            parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
            parameters.put(Constant.PROJECTION_ID, projectionId);
            parameters.put("tableName", " PROJECTION_CUST_HIERARCHY ");
            removeChildLevels = dataSelectionDao.executeQuery(parameters);
            deleteCustomerHierarchyLevels(projectionId, removeLevels);
            if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
                deleteCustomerHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
            }
        }
        saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, Constant.UPDATE);
    }

    private void deleteCustomerHierarchyLevels(final int projectionId, final List<String> removeLevels) throws Exception {
        List<ProjectionCustHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionCustHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipLevelSid", UiUtils.convertStringListToParsedIngeter(removeLevels)));
        details = dataSelectionDao.findCustHierarchyByProjectionId(dynamicQuery);
        for (final ProjectionCustHierarchy cust : details) {
            dataSelectionDao.deleteProjectionCustHierarchies(cust);
        }
    }

    public void updateCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId) throws Exception {
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
     * @param levelList the level list
     * @param projectionId
     * @throws java.lang.Exception
     */
    public void saveCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId, final List<String> addLevels, final String indicator) throws Exception {
        LOGGER.info("saveCustomerHierarchyLogic endLevelSids size:" + endLevelSids.size() + " projectionId " + projectionId);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "getChildLevelRLSid");
        parameters.put(Constant.PROJECTION_ID, projectionId);
        parameters.put("rlSids", endLevelSids);
        parameters.put("tableName", "PROJECTION_CUST_HIERARCHY");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDao.executeQuery(parameters);
        }

        ProjectionCustHierarchy projectionCustHierarchy = ProjectionCustHierarchyLocalServiceUtil.createProjectionCustHierarchy(0);
        try {
            if (Constant.UPDATE.equals(indicator)) {
                for (String rsId : addLevels) {
                    projectionCustHierarchy.setProjectionMasterSid(projectionId);
                    projectionCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDao.addProjectionCustHierarchy(projectionCustHierarchy);
                }
            } else if (Constant.SAVE.equals(indicator)) {
                for (Leveldto dto : levelList) {
                    projectionCustHierarchy.setProjectionMasterSid(projectionId);
                    projectionCustHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDao.addProjectionCustHierarchy(projectionCustHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    projectionCustHierarchy.setProjectionMasterSid(projectionId);
                    projectionCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDao.addProjectionCustHierarchy(projectionCustHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " in saveCustomerHierarchyLogic");
        }
    }

    /**
     * To update the ccp in projeciton details
     *
     * @param projectionId
     * @throws Exception
     */
    public void updateCcpLogic(final List<Leveldto> customerHierarchyEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final int projectionId) throws Exception {

        saveCcp(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, indicator, String.valueOf(projectionId));
    }

    /**
     * To update the ccp in projeciton details
     *
     * @param projectionId
     * @throws Exception
     */
    public void updateCcpLogicView(final List<Leveldto> customerHierarchyEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final int projectionId) throws Exception {
        deleteProjectionDetails(projectionId);
        saveCcp(customerHierarchyEndLevels, productHierarchyEndLevelsHierNos, indicator, String.valueOf(projectionId));
    }

    /**
     * Deletes projection details records
     *
     * @param projectionId
     */
    private void deleteProjectionDetails(final int projectionId) throws Exception {
        List<ProjectionDetails> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        details = dataSelectionDao.findProjDetailsByProjectionId(dynamicQuery);
        for (final ProjectionDetails detail : details) {
            dataSelectionDao.deleteProjectionDetails(detail);
        }
    }

    /**
     * Insert records.
     *
     * @param levelList the level list
     */
    private void insertRecords(List<Leveldto> levelList, final int projectionId) {

        ProjectionCustHierarchy projectionCustHierarchy = ProjectionCustHierarchyLocalServiceUtil.createProjectionCustHierarchy(0);
        try {
            for (Leveldto dto : levelList) {
                projectionCustHierarchy.setProjectionMasterSid(projectionId);
                projectionCustHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                dataSelectionDao.addProjectionCustHierarchy(projectionCustHierarchy);
            }

        } catch (Exception e) {

        }

    }

    /**
     * Gets the hierarchy values.
     *
     * @return the hierarchy values
     */
    public List getHierarchyValues() {
        List resultss = new ArrayList();
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        List<Leveldto> resultList = new ArrayList<Leveldto>();
        Leveldto dto = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = dataSourceConnection.getConnection();

            final String query = " select HLD.LEVEL_NO,HLD.TABLE_NAME,HLD.FIELD_NAME,HLD.LEVEL_VALUE_REFERENCE,* from dbo.HIERARCHY_LEVEL_DEFINITION HLD WHERE HIERARCHY_LEVEL_DEFINITION_SID IN  "
                    + " (select DISTINCT HIERARCHY_LEVEL_DEFINITION_SID FROM dbo.RELATIONSHIP_LEVEL_DEFINITION "
                    + " WHERE RELATIONSHIP_LEVEL_SID IN(select DISTINCT RELATIONSHIP_LEVEL_SID from dbo.PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID='1') ) ";

            if (connection != null) {
                statement = connection.createStatement();

                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    dto = new Leveldto();
                    dto.setLevelNo(Integer.parseInt(String.valueOf(resultSet.getObject(1))));
                    dto.setTableName(String.valueOf(resultSet.getObject(2)));
                    dto.setFieldName(String.valueOf(resultSet.getObject(3)));
                    dto.setLevelValueReference(String.valueOf(resultSet.getObject(4)));
                    resultList.add(dto);
                }
            }

            resultss.add(resultList);
        } catch (Exception ex) {
        } finally {
            try {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        Connection connection = null;
        List<Leveldto> resultList = new ArrayList<Leveldto>();
        Leveldto dto = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            connection = dataSourceConnection.getConnection();

            final String query = " select LEVEL_NO,RELATIONSHIP_LEVEL_VALUES,PARENT_NODE,LEVEL_NAME "
                    + " FROM dbo.RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_LEVEL_SID IN(select DISTINCT RELATIONSHIP_LEVEL_SID from dbo.PROJECTION_CUST_HIERARCHY where PROJECTION_MASTER_SID='1') ORDER by LEVEL_NO ";

            if (connection != null) {
                statement = connection.createStatement();

                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    dto = new Leveldto();
                    dto.setLevelNo(Integer.parseInt(String.valueOf(resultSet.getObject(1))));
                    dto.setRelationshipLevelValue(String.valueOf(resultSet.getObject(2)));
                    dto.setParentNode(String.valueOf(resultSet.getObject(3)));
                    dto.setLevel(String.valueOf(resultSet.getObject(4)));
                    resultList.add(dto);
                }
            }

            resultss.add(resultList);
        } catch (Exception ex) {

        } finally {
            try {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultss;
    }

    /**
     * Gets the relation ship values.
     *
     * @param projectionId
     * @param indicator
     * @return the relation ship values
     */
    public List getRelationShipValues(final int projectionId, final String indicator, final Object levelNo, final Map<String, String> descriptionMap) {
        List resultss;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.PROJECTION_ID, projectionId);
        parameters.put(Constant.INDICATOR, indicator);
        if (levelNo == null) {
            parameters.put("restrictLevelNumber", null);
            parameters.put("levelNo", null);
        } else {
            parameters.put("restrictLevelNumber", Constant.TRUE);
            parameters.put("levelNo", String.valueOf(levelNo));
        }

        List<Leveldto> resultList = new ArrayList<Leveldto>();
        Leveldto dto;

        try {
            resultss = dataSelectionDao.getRelationShipValues(parameters);
            for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                dto = new Leveldto();
                Object objects[] = (Object[]) resultss.get(loop);
                dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                dto.setParentNode(String.valueOf(objects[2]));
                dto.setLevel(String.valueOf(objects[3]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[4])));
                dto.setTableName(String.valueOf(objects[5]));
                dto.setFieldName(String.valueOf(objects[6]));
                dto.setHierarchyNo(String.valueOf(objects[7]));
                if (objects[5] != null && !StringUtils.isEmpty(String.valueOf(objects[5])) && !StringUtils.isBlank(String.valueOf(objects[5]))) {
                    if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(objects[5]))) {
                        dto.setFromCompany(true);
                    } else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(objects[5]))) {
                        dto.setFromContract(true);
                    } else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(objects[5]))) {
                        dto.setFromItem(true);
                    } else {
                        dto.setFromCompany(false);
                        dto.setFromContract(false);
                        dto.setFromItem(false);
                    }
                }
                if (descriptionMap != null) {
                    dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[7])));
                }

                resultList.add(dto);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in getRelationShipValues");
        }
        return resultList;
    }

    public List getParentLevels(final int levelNo, final int relationshipLevelSid) {
        List resultss;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("levelNo", levelNo);
        parameters.put(Constant.INDICATOR, StringUtils.EMPTY);
        parameters.put("relationshipLevelSid", relationshipLevelSid);

        List<Leveldto> resultList = new ArrayList<Leveldto>();
        Leveldto dto;

        try {
            resultss = dataSelectionDao.getParentLevels(levelNo, relationshipLevelSid, parameters);
            for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                dto = new Leveldto();
                Object objects[] = (Object[]) resultss.get(loop);
                dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                dto.setParentNode(String.valueOf(objects[2]));
                dto.setLevel(String.valueOf(objects[3]));
                dto.setLevelValueReference(String.valueOf(objects[4]));
                dto.setTableName(String.valueOf(objects[5]));
                dto.setFieldName(String.valueOf(objects[6]));
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[7])));
                resultList.add(dto);
            }

        } catch (Exception ex) {

        }
        return resultList;
    }

    public Leveldto getParentLevels(final int levelNo, final int relationshipLevelSid, final String parent) {
        List resultss;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("levelNo", levelNo);
        parameters.put(Constant.INDICATOR, StringUtils.EMPTY);
        parameters.put(Constant.PARENT, parent);
        parameters.put("relationshipLevelSid", relationshipLevelSid);

        Leveldto dto = new Leveldto();

        try {
            resultss = dataSelectionDao.getParentLevels(levelNo, relationshipLevelSid, parameters);
            Object objects[] = (Object[]) resultss.get(0);
            dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
            dto.setRelationshipLevelValue(String.valueOf(objects[1]));
            dto.setParentNode(String.valueOf(objects[2]));
            dto.setLevel(String.valueOf(objects[3]));
            dto.setLevelValueReference(String.valueOf(objects[4]));
            dto.setTableName(String.valueOf(objects[5]));
            dto.setFieldName(String.valueOf(objects[6]));
            dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[7])));
            dto.setHierarchyNo(String.valueOf(objects[8]));
            dto.setRelationShipBuilderId(String.valueOf(objects[9]));
        } catch (Exception ex) {
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
        List list = null;

        return list;
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

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DataSelectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public String deleteProjection(int projectionId, String currentUserId, String screenName) {
        LOGGER.info("Enters deleteProjection with " + projectionId);
        String str = StringUtils.EMPTY;
        try {
            ProjectionMaster projMaster;
            projMaster = dataSelectionDao.getProjectionMaster(projectionId);
            if (!String.valueOf(projMaster.getCreatedBy()).equals(currentUserId)) {
                return "accessDenined";
            }
            if ("Returns".equalsIgnoreCase(screenName)) {
                String deleteQuery = SQlUtil.getQuery("DELETE_RETURN_PROJECTION").replace("@PROJECTION_MASTER_SID", StringUtils.EMPTY + projectionId);
                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

                salesProjectionDAO.executeUpdateQuery(deleteQuery.toString());
                str = "Pass";
            } else if (CommonUtils.BUSINESS_PROCESS_TYPE_ACCRUAL_RATE_PROJECTION.equalsIgnoreCase(screenName)) {
                DSLogic dslogic = new DSLogic();
                dslogic.deleteSelectedProjection(projectionId);
                str = "Pass";
            } else {
                deleteProjection(projectionId);
                str = dataSelectionDao.deleteProjection(screenName, projectionId);
            }

        } catch (Exception e) {
            return str;
        }
        return str;
    }

    public List<String> getCustomerGroupDetails(int companyGroupSid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);

        dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid", companyGroupSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        dynamicQuery.setProjection(productProjectionList);
        List resultList = dataSelectionDao.getCustomerGroupDetails(dynamicQuery);

        List<String> returnList = new ArrayList<String>();
        for (Object companySid : resultList) {
            returnList.add(String.valueOf(companySid));
        }
        return returnList;
    }

    public List<String> getItemGroupDetails(int itemGroupSid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);

        dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid", itemGroupSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
        dynamicQuery.setProjection(productProjectionList);
        List resultList = dataSelectionDao.getCustomerGroupDetails(dynamicQuery);

        List<String> returnList = new ArrayList<String>();
        for (Object companySid : resultList) {
            returnList.add(String.valueOf(companySid));
        }
        return returnList;
    }

    public List<String> executeQuery(final String query) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("query", query);
        List resultList = dataSelectionDao.executeQuery(parameters);
        List<String> returnList = new ArrayList<String>();
        for (Object value : resultList) {
            returnList.add(String.valueOf(value));
        }
        return returnList;
    }

    public List<CompanyDdlbDto> getCompanyForDdlbFromSids(final List<String> companySids) throws Exception {

        List<CompanyMaster> companies = getCompanyFromSids(companySids);
        List<CompanyDdlbDto> companiesForDdlb = new ArrayList<CompanyDdlbDto>();
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
    public List<CompanyDdlbDto> getCompaniesLazy(int startIndex, int endIndex, String filterText, final List<String> companySids, CompanyDdlbDto companyDdlbDefault, CompanyDdlbDto selectedCompanyDdlbDto) throws Exception {
        List<CompanyDdlbDto> companies = new ArrayList<CompanyDdlbDto>();
        if (startIndex == 0) {
            companies.add(companyDdlbDefault);
        }
        if (companySids != null && !companySids.isEmpty()) {
            DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
            helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
            helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMP_TYPE"));
            helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyId = 0;
            companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, UiUtils.convertStringListToIngeter(companySids)));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, filterText));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("companyType", companyId));
            dynamicQuery.setLimit(startIndex, endIndex);
            List<Object[]> returnlist = dataSelectionDao.getCompanies(dynamicQuery);
            CompanyDdlbDto companyDdlbDto;
            if (selectedCompanyDdlbDto == null) {
                for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
                    Object[] objects = (Object[]) returnlist.get(loop);
                    companyDdlbDto = new CompanyDdlbDto((Integer.valueOf(String.valueOf(objects[0]))), String.valueOf(objects[1]));
                    companies.add(companyDdlbDto);
                }
            } else {
                for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
                    Object[] objects = (Object[]) returnlist.get(loop);
                    if ((Integer.valueOf(String.valueOf(objects[0]))) == selectedCompanyDdlbDto.getCompanyMasterSid()) {
                        selectedCompanyDdlbDto.setCompanyName(String.valueOf(objects[1]));
                        companies.add(selectedCompanyDdlbDto);
                    } else {
                        companyDdlbDto = new CompanyDdlbDto((Integer.valueOf(String.valueOf(objects[0]))), String.valueOf(objects[1]));
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
    public int getCompaniesCount(String filterText, final List<String> companySids) throws Exception {
        int count = 0;
        if (companySids != null && !companySids.isEmpty()) {
            DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
            helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
            helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMP_TYPE"));
            helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyId = 0;
            companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, UiUtils.convertStringListToIngeter(companySids)));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, filterText));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("companyType", companyId));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            count = dataSelectionDao.getCompaniesCount(dynamicQuery);
        }
        return count;
    }

    public List<ItemMaster> getItemMasterFromCompanies(final List<String> companySids) throws Exception {
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

    public List<ItemMaster> getItemMasterFromCompany(final int companySid) throws Exception {
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

    public List<Integer> getItemIdFromCompanyInCCp(final List<String> companySids, final int companySid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class);
        if (companySids != null && !companySids.isEmpty()) {
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, UiUtils.convertStringListToIngeter(companySids)));
        }
        if (companySid != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.COMPANYMASTERSID, companySid));
        }
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List resultList = dataSelectionDao.getItemIdFromCompanyInCCp(dynamicQuery);
        List<Integer> sidList = new ArrayList<Integer>();
        for (Object sid : resultList) {
            sidList.add(Integer.parseInt(String.valueOf(sid)));
        }
        return sidList;
    }

    public List<Integer> getItemSidsFromAllBrand() throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        dynamicQuery.add(PropertyFactoryUtil.forName(Constant.BRAND_MASTER_SID).in(
                DynamicQueryFactoryUtil.forClass(BrandMaster.class).setProjection(ProjectionFactoryUtil.property(Constant.BRAND_MASTER_SID))));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List resultList = dataSelectionDao.getItemMaster(dynamicQuery);
        List<Integer> returnList = new ArrayList<Integer>();
        for (Object sid : resultList) {
            returnList.add(Integer.parseInt(String.valueOf(sid)));
        }
        return returnList;
    }

    public List<ItemMaster> getItemMaster(final List<String> itemSidsFromHierarchy) throws Exception {
        List<ItemMaster> resultList = null;
        if (itemSidsFromHierarchy != null && !itemSidsFromHierarchy.isEmpty()) {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.ITEM_MASTER_SID, UiUtils.convertStringListToIngeter(itemSidsFromHierarchy)));
            resultList = dataSelectionDao.getItemMaster(dynamicQuery);
        }
        return resultList;
    }

    public ForecastConfig getTimePeriod(String screenName) throws Exception {
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

    public List<String> getEndLevelCCP(final List<Leveldto> customerEndLevels, final String indicator, final String projectionId) throws Exception {
        List<String> customerCcpList = new ArrayList<String>();
        if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
            List resultList;
            Map<String, Object> parameters;
            for (Leveldto dto : customerEndLevels) {
                parameters = new HashMap<String, Object>();
                parameters.put(Constant.PROJECTION_ID, projectionId);
                parameters.put(Constant.INDICATOR, indicator);
                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
                parameters.put(Constant.HIERARACHY_NO, dto.getHierarchyNo());
                resultList = dataSelectionDao.getCcpMap(parameters);
                for (Object ccpId : resultList) {
                    customerCcpList.add(String.valueOf(ccpId));
                }
            }
        }
        return customerCcpList;
    }

    public List<String> getRbIds(final int hierarchyDefinitionSid) throws Exception {
        List<String> returnList = new ArrayList<String>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "getRbId");
        parameters.put(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid);
        List<Object> resultList = dataSelectionDao.getCcpMap(parameters);
        for (Object rbSid : resultList) {
            returnList.add(String.valueOf(rbSid));
        }
        return returnList;
    }

    public void insertToCcpMap(List<String> relationshipBuilderSids, String screenName) throws Exception {
        List<String> relationshipBuilderSidsList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
            relationshipBuilderSidsList = new ArrayList<String>(relationshipBuilderSids);
        }
        parameters.put(Constant.INDICATOR, "insertToCcpMap");
        parameters.put("relationshipBuilderSids", relationshipBuilderSidsList);
        parameters.put("scrennName", screenName);
        dataSelectionDao.saveCcp(parameters);

    }

    public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final String projectionId) throws Exception {
        if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
            Map<String, Object> parameters;
            for (Leveldto dto : customerEndLevels) {
                parameters = new HashMap<String, Object>();
                parameters.put(Constant.PROJECTION_ID, projectionId);
                parameters.put(Constant.INDICATOR, indicator);
                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
                parameters.put(Constant.HIERARACHY_NO, dto.getHierarchyNo());
                parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
                parameters.put(Constant.INDICATOR, "saveCcp");
                dataSelectionDao.getCcpMap(parameters);
            }
        }
    }

    public List<Leveldto> getParentLevelsWithHierarchyNo(final String hierarchyNos, final Map<String, String> descriptionMap) {
        List resultss;
        List<Leveldto> resultList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("hierarchyNos", hierarchyNos);
        parameters.put(Constant.INDICATOR, "getParentLevelsWithHierarchyNo");
        Leveldto dto;

        try {
            resultss = dataSelectionDao.getParentLevels(0, 0, parameters);

            if (resultss != null) {
                resultList = new ArrayList<Leveldto>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object objects[] = (Object[]) resultss.get(loop);
                    dto = new Leveldto();
                    dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                    dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                    dto.setParentNode(String.valueOf(objects[2]));
                    dto.setLevel(String.valueOf(objects[3]));
                    dto.setLevelValueReference(String.valueOf(objects[4]));
                    dto.setTableName(String.valueOf(objects[5]));
                    dto.setFieldName(String.valueOf(objects[6]));
                    dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[7])));
                    dto.setHierarchyNo(String.valueOf(objects[8]));
                    dto.setRelationShipBuilderId(String.valueOf(objects[9]));
                    if (descriptionMap != null) {
                        dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[8])));
                    }
                    resultList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    public int getRelationshipSidCount(String filterText, final int hierarchyDefinitionSid) throws SystemException, PortalException, Exception {
        int count = dataSelectionDao.getRelationshipCount(getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid));
        return count + 1;
    }

    public List<RelationshipDdlbDto> getRelationshipSidLazy(int startIndex, int endIndex, final RelationshipDdlbDto defaultRelationshipDdlbDto,
            String filterText, final int hierarchyDefinitionSid, RelationshipDdlbDto selectedRelationshipDdlbDto) throws SystemException, PortalException, Exception {
        List<RelationshipDdlbDto> returnList = new ArrayList<RelationshipDdlbDto>();
        DynamicQuery dynamicQuery = getRelationshipSidDynamicQuery(filterText, hierarchyDefinitionSid);
        dynamicQuery.setLimit(startIndex, endIndex);
        List<Object[]> resultList = dataSelectionDao.getRelationship(dynamicQuery);
        RelationshipDdlbDto relationshipDdlbDto;
        if (startIndex == 0) {
            returnList.add(defaultRelationshipDdlbDto);
        }
        if (selectedRelationshipDdlbDto == null) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) resultList.get(loop);
                relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
                returnList.add(relationshipDdlbDto);
            }
        } else {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) resultList.get(loop);
                if ((String.valueOf(objects[0])).equals(selectedRelationshipDdlbDto.getRelationshipBuilderSid())) {
                    selectedRelationshipDdlbDto.setRelationshipName(String.valueOf(objects[1]));
                    returnList.add(selectedRelationshipDdlbDto);
                } else {
                    relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
                    returnList.add(relationshipDdlbDto);
                }
            }
        }
        return returnList;
    }

    private DynamicQuery getRelationshipSidDynamicQuery(String filterText, final int hierarchyDefinitionSid) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipBuilderSid"));
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipName"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike("relationshipName", filterText));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        return dynamicQuery;
    }

    public List<RelationshipDdlbDto> getRelationshipSids(final RelationshipDdlbDto defaultRelationshipDdlbDto, final int hierarchyDefinitionSid) throws SystemException, PortalException, Exception {
        List<RelationshipDdlbDto> returnList = new ArrayList<RelationshipDdlbDto>();
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipBuilderSid"));
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipName"));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List<Object[]> resultList = dataSelectionDao.getRelationship(dynamicQuery);
        RelationshipDdlbDto relationshipDdlbDto;
        returnList.add(defaultRelationshipDdlbDto);
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            Object[] objects = (Object[]) resultList.get(loop);
            relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
            returnList.add(relationshipDdlbDto);
        }
        return returnList;
    }

    public List<Leveldto> getChildLevelsWithHierarchyNo(String hierarchyNo, int lowestLevelNo, final Map<String, String> descriptionMap) {
        List resultss;
        List<Leveldto> resultList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.HIERARACHY_NO, hierarchyNo);
        parameters.put("lowestLevelNo", lowestLevelNo);
        parameters.put(Constant.INDICATOR, "getChildLevelsWithHierarchyNo");
        Leveldto dto;

        try {
            resultss = dataSelectionDao.getChildLevels(parameters);

            if (resultss != null) {
                resultList = new ArrayList<Leveldto>();
                for (int loop = 0, limit = resultss.size(); loop < limit; loop++) {
                    Object objects[] = (Object[]) resultss.get(loop);
                    dto = new Leveldto();
                    dto.setLevelNo(Integer.parseInt(String.valueOf(objects[0])));
                    dto.setRelationshipLevelValue(String.valueOf(objects[1]));
                    dto.setParentNode(String.valueOf(objects[2]));
                    dto.setLevel(String.valueOf(objects[3]));
                    dto.setLevelValueReference(String.valueOf(objects[4]));
                    dto.setTableName(String.valueOf(objects[5]));
                    dto.setFieldName(String.valueOf(objects[6]));
                    dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(objects[7])));
                    dto.setHierarchyNo(String.valueOf(objects[8]));
                    dto.setRelationShipBuilderId(String.valueOf(objects[9]));
                    if (descriptionMap != null) {
                        dto.setDisplayValue(descriptionMap.get(String.valueOf(objects[8])));
                    }
                    resultList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    public void getEndLevelRelationshipLevels(final List<String> endLevelSids, final String relationshipSid, List<Leveldto> ccList, List<String> availableHierNo) throws Exception {
        Leveldto dto;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "getChildLevelRL");
        parameters.put("relationshipSid", relationshipSid);
        parameters.put("rlSids", endLevelSids);
        parameters.put("availableHierNo", availableHierNo);
        List<Object> endLevels = dataSelectionDao.executeQuery(parameters);
        for (int i = 0, j = endLevels.size(); i < j; i++) {
            dto = new Leveldto();
            Object[] obj = (Object[]) endLevels.get(i);
            dto.setRelationshipLevelValue(String.valueOf(obj[0]));
            dto.setLevelNo(Integer.parseInt(String.valueOf(obj[1])));
            dto.setParentNode(String.valueOf(obj[2]));
            dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[3])));
            dto.setTableName(String.valueOf(obj[4]));
            dto.setFieldName(String.valueOf(obj[5]));
            dto.setHierarchyNo(String.valueOf(obj[6]));
            dto.setRelationShipBuilderId(String.valueOf(obj[7]));
            dto.setLevel(String.valueOf(obj[8]));
            if (obj[4] != null && !StringUtils.isEmpty(String.valueOf(obj[4])) && !StringUtils.isBlank(String.valueOf(obj[4]))) {
                if (Constant.COMPANY_MASTER.equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromCompany(true);
                } else if (Constant.CONTRACT_MASTER.equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromContract(true);
                } else if (Constant.ITEM_MASTER.equalsIgnoreCase(String.valueOf(obj[4]))) {
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

    public void setForcastFileDate(DataSelectionDTO dto) throws SystemException, Exception {
        String query = CustomSQLUtil.get("getFileEndDate");
        List list = (List) salesProjectionDAO.executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            Object[] tempDate = (Object[]) list.get(0);
            dto.setFileEndYear(Integer.parseInt(String.valueOf(tempDate[0])));
            dto.setFileEndMonth(Integer.parseInt(String.valueOf(tempDate[1])));
        }
    }

    public void deleteTempOnUpdate(final String projectionHierarchyTable, final int projectionId, final String hNos) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "deleteTempOnUpdate");
        parameters.put("projectionHierarchyTable", projectionHierarchyTable);
        parameters.put(Constant.PROJECTION_ID, projectionId);
        parameters.put("hNos", hNos);
        dataSelectionDao.executeQuery(parameters);
    }

    public Object deleteProjection(final int projectionId) throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", projectionId);
        return dataSelectionDao.tempOperation(input, "deleteProjection");
    }

    public List getFSValue(String relationshipLevelValue, final String fieldName) {
        List list = new ArrayList();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "getFSValue");
        parameters.put("relationshipLevelValue", relationshipLevelValue);
        parameters.put(Constant.FIELD_NAME, fieldName);
        try {
            list = (List) dataSelectionDao.executeQuery(parameters);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "in getFSValue");
            return null;
        }
    }

    public List<Integer> getItemIdFromCompanyForGlComp(final int companySid) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constant.INDICATOR, "companyFilter");
        parameters.put("companySid", companySid);
        List resultList = dataSelectionDao.executeQuery(parameters);
        List<Integer> sidList = new ArrayList<Integer>();
        for (Object sid : resultList) {
            sidList.add(Integer.parseInt(String.valueOf(sid)));
        }
        return sidList;
    }

    public Map<String, String> getLevelValueMap(Object relationshipBuilderSID) throws Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?RBSID", relationshipBuilderSID);
        return (Map<String, String>) dataSelectionDao.tempOperation(input, "getHierarchyTableDetails");
    }

    public List<RelationshipDdlbDto> getRelationshipSid(final int hierarchyDefinitionSid) throws SystemException, PortalException, Exception {
        List<RelationshipDdlbDto> returnList = new ArrayList<RelationshipDdlbDto>();
        DynamicQuery dynamicQuery = getRelationshipSidDynamicQuery(hierarchyDefinitionSid);
        List<Object[]> resultList = dataSelectionDao.getRelationship(dynamicQuery);
        RelationshipDdlbDto relationshipDdlbDto;
        for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
            Object[] objects = (Object[]) resultList.get(loop);
            relationshipDdlbDto = new RelationshipDdlbDto(String.valueOf(objects[0]), String.valueOf(objects[1]));
            returnList.add(relationshipDdlbDto);
        }
        return returnList;
    }

    private DynamicQuery getRelationshipSidDynamicQuery(final int hierarchyDefinitionSid) {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RelationshipBuilder.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.HIERARCHYDEFINITIONSID, hierarchyDefinitionSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipBuilderSid"));
        productProjectionList.add(ProjectionFactoryUtil.property("relationshipName"));
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
    public List<CompanyDdlbDto> getCompanies(final List<String> companySids) throws Exception {
        List<CompanyDdlbDto> companies = new ArrayList<CompanyDdlbDto>();
        if (companySids != null && !companySids.isEmpty()) {
            DynamicQuery helper = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            final ProjectionList helperProjectionList = ProjectionFactoryUtil.projectionList();
            helperProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
            helper.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, "COMPANY_TYPE"));
            helper.add(RestrictionsFactoryUtil.like(Constant.DESCRIPTION, "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyId = 0;
            companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            dynamicQuery.add(RestrictionsFactoryUtil.in(Constant.COMPANYMASTERSID, UiUtils.convertStringListToIngeter(companySids)));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            dynamicQuery.add(RestrictionsFactoryUtil.eq("companyType", companyId));
            List<Object[]> returnlist = dataSelectionDao.getCompanies(dynamicQuery);
            CompanyDdlbDto companyDdlbDto;
            for (int loop = 0, limit = returnlist.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) returnlist.get(loop);
                companyDdlbDto = new CompanyDdlbDto((Integer.valueOf(String.valueOf(objects[0]))), String.valueOf(objects[1]));
                companies.add(companyDdlbDto);
            }
        }
        LOGGER.info("companies return size " + companies.size());
        return companies;
    }

    //Added For Mandated
    public String getGenerateMarketValue(int rbID) {
        List<Object> temp = getGenerateMarketValueResult(rbID);
        String marketType = StringUtils.EMPTY;
        if (temp.size() > 0) {
            if (String.valueOf(temp.get(0)) != null && !StringUtils.EMPTY.equals(String.valueOf(temp.get(0)))) {
                marketType = String.valueOf(temp.get(0));
            }
        }

        return marketType;
    }

    public List<Object> getGenerateMarketValueResult(int rbID) {
        try {
            List<Object> list = new ArrayList<Object>();
            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            queryString.append("select RELATIONSHIP_LEVEL_VALUES from RELATIONSHIP_LEVEL_DEFINITION where \n"
                    + "RELATIONSHIP_BUILDER_SID='" + rbID + "'\n"
                    + "and \n"
                    + "LEVEL_NAME='Market Type'");
            CommonDAO salesProjectionDAO = new CommonDAOImpl();
            list = (List) salesProjectionDAO.executeSelectQuery(queryString.toString(), null, null);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public String getDefinedValue(String definedValue) {
        String str = StringUtils.EMPTY;
        List<Object> listValue = getDefinedValueResult(definedValue);
        if (listValue.size() > 0) {
            for (int i = 0; i < listValue.size(); i++) {
                str = String.valueOf(listValue.get(0));
            }
        }

        return str;

    }

    public List<Object> getDefinedValueResult(String definedValue) {
        String str = StringUtils.EMPTY;
        try {
            CommonDAO salesProjectionDAO = new CommonDAOImpl();
            str = "select LEVEL_VALUE_REFERENCE from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue + " and LEVEL_NAME='Market Type'";
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str, null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public String getHelperValue(String marketType) {
        String marketTypeValue = StringUtils.EMPTY;
        List<Object> temp = getHelperValueResult(marketType);
        if (temp.size() > 0) {
            for (int i = 0; i < temp.size(); i++) {
                marketTypeValue = String.valueOf(temp.get(i));
            }
        }
        return marketTypeValue;
    }

    public List<Object> getHelperValueResult(String projId) {
        String str = StringUtils.EMPTY;
        try {
            CommonDAO salesProjectionDAO = new CommonDAOImpl();
            str = "select DESCRIPTION from HELPER_TABLE where\n"
                    + " HELPER_TABLE_SID in(select RELATIONSHIP_LEVEL_VALUES\n"
                    + "from RELATIONSHIP_LEVEL_DEFINITION\n"
                    + " where RELATIONSHIP_BUILDER_SID in(Select CUST_RELATIONSHIP_BUILDER_SID\n"
                    + " from PROJECTION_MASTER where PROJECTION_MASTER_SID=" + projId + ") AND LEVEL_NAME='Market Type') \n"
                    + "and LIST_NAME='CONTRACT_TYPE';";
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str, null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public int getDiscountCount(String filterText) {
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
            productProjectionList.add(ProjectionFactoryUtil.property(Constant.DESCRIPTION));
            query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            query.add(RestrictionsFactoryUtil.ilike(Constant.DESCRIPTION, filterText));
            query.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, Constant.RS_TYPE));
            discountDdlbCount = dataSelectionDao.getDiscountCount(query);
        } catch (Exception ex) {
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
    public List<CompanyDdlbDto> getDiscounts(int startIndex, int endIndex, String filterText, CompanyDdlbDto discountDdlbDefault, CompanyDdlbDto selectedDiscount) throws Exception {
        List<CompanyDdlbDto> discounts = new ArrayList<CompanyDdlbDto>();
        int startValue = startIndex;
        int endValue = endIndex;
        if (startIndex == 0) {
            discounts.add(discountDdlbDefault);
        }
        CompanyDdlbDto discountDdlbDto;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        filterText = StringUtils.trimToEmpty(filterText) + Constant.PERCENT;
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.HELPER_TABLE_SID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.DESCRIPTION));
        query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        query.add(RestrictionsFactoryUtil.ilike(Constant.DESCRIPTION, filterText));
        query.add(RestrictionsFactoryUtil.eq(Constant.LIST_NAME, Constant.RS_TYPE));
        query.setLimit(startValue, endValue);
        List<Object[]> resultList = dataSelectionDao.getDiscounts(query);
        if (selectedDiscount == null) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) resultList.get(loop);
                discounts.add(new CompanyDdlbDto((Integer.valueOf(String.valueOf(objects[0]))), String.valueOf(objects[1]), true));
            }
        } else {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                Object[] objects = (Object[]) resultList.get(loop);
                if (Integer.valueOf(String.valueOf(objects[0])).equals(selectedDiscount.getRsModelSid())) {
                    selectedDiscount.setRsNo(String.valueOf(objects[1]));
                    discounts.add(selectedDiscount);
                } else {
                    discountDdlbDto = new CompanyDdlbDto(Integer.valueOf(String.valueOf(objects[0])), String.valueOf(objects[1]), true);
                    discounts.add(discountDdlbDto);
                }
            }
        }
        return discounts;
    }

    public String getCheckValue(String rbCusId, String marketValue, String hier_Sid) {
        String str = StringUtils.EMPTY;
        String mkValue = StringUtils.EMPTY;
        String fieldName = StringUtils.EMPTY;

        List<Object> fieldNameList = SPRCommonLogic.getContractFieldValue(hier_Sid);
        if (fieldNameList.size() > 0) {
            for (int i = 0; i < fieldNameList.size(); i++) {
                fieldName = String.valueOf(fieldNameList.get(0));
            }
        }

        return fieldName;
    }

    public void callSalesInsertProcedure(int projectionId, String userId, String sessionId, String procedureName) {
        boolean status = false;
        LOGGER.info("In callSalesInsertProcedure");
        LOGGER.info("ProcedureName---> " + procedureName);
        LOGGER.info("ProjectionId----> " + projectionId);
        LOGGER.info("UserId----------> " + userId);
        LOGGER.info("SessionId-------> " + sessionId);

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            if (connection != null) {

                statement = connection.prepareCall(procedureName);

                statement.setObject(1, projectionId); //  @PROJECTION_SID
                statement.setObject(2, userId); //  @USER_ID
                statement.setObject(3, sessionId); //  @SESSION_ID

                status = statement.execute();
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public String getMarketTypeValue(int ProjectId) {
        List<Object> temp = getMarketType(ProjectId);
        String marketType = StringUtils.EMPTY;
        if (temp.size() > 0) {
            Object[] objects = (Object[]) temp.get(0);
            if (String.valueOf(objects[0]) != null && !StringUtils.EMPTY.equals(String.valueOf(objects[0]))) {
                marketType = String.valueOf(objects[0]);
            }
        }

        return marketType;
    }

    public List<Object> getMarketType(int projectionId) {
        try {
            List list = new ArrayList();
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
            LOGGER.error(ex.getMessage());
            return null;
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
    public List<GroupDTO> searchGroup(final GroupDTO dto, final Boolean isCustomGroup,
            Set<Container.Filter> filters, List<SortByColumn> sortByColumns, int start, int offset) throws Exception {
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

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in searchGroup");
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
    public int searchGroupCount(final GroupDTO dto, final Boolean isCompanyGroup, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws Exception {
        List countList = new ArrayList();
        if (isCompanyGroup) {
            countList = getCustomerList(dto, isCompanyGroup, filters, sortByColumns, Boolean.TRUE, 0, 0);
        } else {
            countList = getProductList(dto, isCompanyGroup, filters, sortByColumns, Boolean.TRUE, 0, 0);
        }
        int count = QueryUtils.getCount(countList);
        return count;
    }

    /**
     * Used to change the alignment of Selected Product table
     *
     * @param selectedProduct
     * @param selectedProductContainer
     */
    public static void selectedProductTableAlignmentChange(TreeTable selectedProduct, ExtTreeContainer<Leveldto> selectedProductContainer) {
        int length = 0;
        for (Leveldto dto : selectedProductContainer.getItemIds()) {
            if (length < dto.getDisplayValue().length()) {
                length = dto.getDisplayValue().length();
            }
        }
        if (length > 50) {
            if (length <= 60) {
                selectedProduct.setColumnWidth("displayValue", 510);
            } else if (length <= 70) {
                selectedProduct.setColumnWidth("displayValue", 570);
            } else if (length <= 80) {
                selectedProduct.setColumnWidth("displayValue", 630);
            } else if (length <= 90) {
                selectedProduct.setColumnWidth("displayValue", 730);
            } else if (length <= 100) {
                selectedProduct.setColumnWidth("displayValue", 810);
            }
        } else {
            selectedProduct.setColumnWidth("displayValue", -1);
        }
    }

    public void insertToReturnDetails(int projectionIdValue) throws Exception {
        salesProjectionDAO.executeBulkUpdateQuery(SQlUtil.getQuery("RETURN_DETAILS_INSERT").replace("@PROJECTION_ID", String.valueOf(projectionIdValue)), null, null);
    }

    /**
     * Used to get the ReturnDetailsSid for each Hierarchy No
     *
     * @param productDescriptionMap
     * @param session
     */
    public Map<String, String> getReturnDetails(Map<String, String> productDescriptionMap, final SessionDTO session, boolean flag) {
        if (flag) {
            //Used to Insert the record in RETURNS_MAP TABLE
            String query1 = SQlUtil.getQuery("RETURNS_MAP_INSERT").replace("@PROJECTION_SID", String.valueOf(session.getProjectionId()));
            salesProjectionDAO.executeBulkUpdateQuery(query1, null, null);
        }

        Map<String, String> returnDetilsMap = new HashMap<String, String>();
        String query = SQlUtil.getQuery("RETURN_DETAILS_RESULTS").replace("@PROJECTION_ID", String.valueOf(session.getProjectionId()));
        List resultsList = (List) salesProjectionDAO.executeSelectQuery(query, null, null);
        for (int i = 0; i < resultsList.size(); i++) {
            Object[] ob = (Object[]) resultsList.get(i);
            returnDetilsMap.put(ob[0].toString(), ob[1].toString());
            if (Integer.valueOf(session.getProductLevelNumber()) == Integer.valueOf(ob[2].toString())) {
                session.setDetailsSID(ob[1].toString());
            }
        }
        return returnDetilsMap;
    }

    public String getContractValue(String definedValue) {
        String str = StringUtils.EMPTY;
        List<Object> listValue = getContractValueResult(definedValue);
        if (listValue.size() > 0) {
            for (int i = 0; i < listValue.size(); i++) {
                str = String.valueOf(listValue.get(0));
            }
        }

        return str;

    }

    public List<Object> getContractValueResult(String definedValue) {
        String str = StringUtils.EMPTY;
        try {
            CommonDAO salesProjectionDAO = new CommonDAOImpl();
            str = "select FIELD_NAME from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue + " and  LEVEL_NAME in('Customer','Trading Partner')";
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str, null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    public static List<Object[]> getAccrualSelection(int projectionId) {
        List<Object[]> obj = null;
        try {
            String customSql = CustomSQLUtil.get("getAccrualSelection");
            customSql = customSql.replace("?PROJECTION_ID", String.valueOf(projectionId));
            obj = (List<Object[]>) executeSelectQuery(customSql, null, null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return obj;
    }

    public List getProductList(final GroupDTO dto, final Boolean isCompanyGroup, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, Boolean isCount, int startIndex, int offset) throws Exception {
        String sql = QueryUtils.getQuery(getGroupInput(dto, isCompanyGroup), "getProdGroupSearch");
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, filterMap(isCompanyGroup)).toString();
        sql = sql.replace("@FILTER@", filterQuery.replace("where", " AND "));
        if (isCount) {
            sql = sql.replace("@ORDER_BY@", " ");
            sql = sql.replace("@SELECTION@", "COUNT (DISTINCT IG.ITEM_GROUP_SID)");
        } else {
            sql = sql.replace("@SELECTION@", "DISTINCT IG.ITEM_GROUP_SID, IG.ITEM_GROUP_NO, IG.ITEM_GROUP_NAME, COMPANY_NAME, IG.VERSION_NO, IG.ITEM_GROUP_DESCRIPTION");
            String sortingQuery = AbstractFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, filterMap(isCompanyGroup)).toString();
            sql = sql.replace("@ORDER_BY@", sortingQuery);
            sql = sql + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
        }
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List list = (List) dao.executeSelectQuery(sql);
        return list;
    }

    public List getCustomerList(final GroupDTO dto, final Boolean indicator, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, Boolean isCount, int startIndex, int offset) throws Exception {
        String sql = QueryUtils.getQuery(getGroupInput(dto, indicator), "getCustGroupSearch");
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filters, filterMap(indicator)).toString();
        sql = sql.replace("@FILTER@", filterQuery.replace("where", " AND "));
        if (isCount) {
            sql = sql.replace("@ORDER_BY@", " ");
            sql = sql.replace("@SELECTION@", " COUNT (DISTINCT IG.COMPANY_GROUP_SID)");
        } else {
            sql = sql.replace("@SELECTION@", " DISTINCT IG.COMPANY_GROUP_SID, IG.COMPANY_GROUP_NO, IG.COMPANY_GROUP_NAME, IG.VERSION_NO, IG.COMPANY_GROUP_DESCRIPTION  ");
            String sortingQuery = AbstractFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, filterMap(indicator)).toString();
            sql = sql.replace("@ORDER_BY@", sortingQuery);
            sql = sql + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
        }
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List list = (List) dao.executeSelectQuery(sql);
        return list;
    }

    public Map<String, String> filterMap(final Boolean isCompanyGroup) {
        Map<String, String> filterMap = new HashMap<>();
        if (!isCompanyGroup) {
            filterMap.put("productGroupName", "IG.ITEM_GROUP_NAME");
            filterMap.put("productGroupNo", "IG.ITEM_GROUP_NO");
            filterMap.put("company", "CM.COMPANY_NAME");
            filterMap.put(StringUtils.EMPTY, "IG.ITEM_GROUP_SID");
        } else {
            filterMap.put("customerGroupName", "IG.COMPANY_GROUP_NAME");
            filterMap.put("customerGroupNo", "IG.COMPANY_GROUP_NO");
            filterMap.put(StringUtils.EMPTY, "IG.COMPANY_GROUP_SID");
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
        String query1 = SQlUtil.getQuery("UPDATE_RETURN_DETAILS").replace("@PROJECTION_ID", String.valueOf(session.getProjectionId()));
        salesProjectionDAO.executeBulkUpdateQuery(query1, null, null); 
    }
}
