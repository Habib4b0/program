
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataSelection.logic;

import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.DataSelectionDAO;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.cff.logic.DataSourceConnection;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.dataSelection.dto.CompanyDdlbDto;
import com.stpl.app.cff.ui.dataSelection.dto.RelationshipDdlbDto;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Converters;
import com.stpl.app.cff.util.DataSelectionUtil;
import com.stpl.app.cff.util.UiUtils;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CcpDetails;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.GroupDTO;
import com.stpl.ifs.ui.forecastds.dto.HierarchyLookupDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
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
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.TreeTable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_CUSTOMER_GROUP;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.service.CffCustHierarchyLocalServiceUtil;
import com.stpl.app.parttwo.service.CffProdHierarchyLocalServiceUtil;

/**
 *
 * @author mohamed.hameed
 */
public class DataSelectionLogic {

    /**
     * The data selection dao.
     */
    DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionLogic.class);
    int discountDdlbCount = 0;
    CommonDAO salesProjectionDAO = new CommonDAOImpl();
    DataSelectionDAO dataSelectionDAO = new DataSelectionDAOImpl();

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
            resultList.add(hdto);//Sanityrelat0090

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
                    if ("COMPANY_MASTER".equalsIgnoreCase(String.valueOf(object[3]))) {
                        leveldto.setFromCompany(true);
                    } else if ("CONTRACT_MASTER".equalsIgnoreCase(String.valueOf(object[3]))) {
                        leveldto.setFromContract(true);
                    } else if ("ITEM_MASTER".equalsIgnoreCase(String.valueOf(object[3]))) {
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
            LOGGER.error(e + " in DSLogic - loadCustomerForecastLevel");
        }
        return resultList;
    }

    /**
     * Load inner customer level.
     *
     * @param levelName the level name
     * @param hierarchyId the hierarchy id
     * @param selectedLevelSids
     * @return the list
     */
    public List<Leveldto> loadInnerLevel(String levelName, int hierarchyId, List<Integer> selectedLevelSids, final boolean isNdc,
            final String fieldName, final String relationshipSid, final Map<String, String> descriptionMap, final String level, final String screenName, int discountID, int levelNo) throws Exception {
        List<Leveldto> values = new ArrayList<Leveldto>();
        List result = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        Leveldto dto;
        if (isNdc) {
            parameters.put("isNdc", "true");
        } else {
            parameters.put("isNdc", "false");
        }
        parameters.put("hierarchyDefinitionSid", hierarchyId);
        parameters.put("levelName", levelName);
        parameters.put("level", level);
        parameters.put("relationshipLevelSidList", selectedLevelSids);
        parameters.put("fieldName", fieldName);
        if (StringUtils.isBlank(relationshipSid) || "null".equals(String.valueOf(relationshipSid)) || "0".equals(String.valueOf(relationshipSid))) {
            parameters.put("relationshipSid", "null");
        } else {
            parameters.put("relationshipSid", relationshipSid);
        }
        parameters.put("discount", discountID);
        parameters.put("levelNo", levelNo);
        parameters.put("screenName", screenName);
        result = dataSelectionDao.getInnerLevel(parameters);
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
                if ("COMPANY_MASTER".equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromCompany(true);
                } else if ("CONTRACT_MASTER".equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromContract(true);
                } else if ("ITEM_MASTER".equalsIgnoreCase(String.valueOf(obj[4]))) {
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
                .add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchyId))
                .setProjection(ProjectionFactoryUtil.property("relationshipBuilderSid"))));

        dynamicQuery.add(RestrictionsFactoryUtil.in("levelNo", levelNo));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

        productProjectionList.add(ProjectionFactoryUtil.distinct((ProjectionFactoryUtil.property("relationshipLevelValues"))));
        productProjectionList.add(ProjectionFactoryUtil.property("levelName"));
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
        dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", sids));
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
            dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid", sids));
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
            parameters.put("indicator", "getRemovableChildren");
            parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
            parameters.put("tableName", " PROJECTION_PROD_HIERARCHY ");
            parameters.put("projectionId", projectionId);
            removeChildLevels = dataSelectionDao.executeQuery(parameters);
            deleteProductHierarchyLevels(projectionId, removeLevels);
            if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
                deleteProductHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
            }
        }
        saveProductHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, "update");
    }

    private void deleteProductHierarchyLevels(final int projectionId, final List<String> removeLevels) throws Exception {
        List<CffProdHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffProdHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipLevelSid", UiUtils.convertStringListToParsedIngeter(removeLevels)));
        details = dataSelectionDao.findProdHierarchyByProjectionId(dynamicQuery);
        for (final CffProdHierarchy prod : details) {
            dataSelectionDao.deleteProjectionProdHierarchies(prod);
        }
    }

    public void updateProductHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId) throws Exception {
        List<CffProdHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffProdHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        details = dataSelectionDao.findProdHierarchyByProjectionId(dynamicQuery);
        for (final CffProdHierarchy cust : details) {
            dataSelectionDao.deleteProjectionProdHierarchies(cust);
        }
        saveProductHierarchyLogic(levelList, endLevelSids, projectionId, null, "save");
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
        parameters.put("indicator", "getChildLevelRLSid");
        parameters.put("rlSids", endLevelSids);
        parameters.put("projectionId", projectionId);
        parameters.put("tableName", "CFF_PROD_HIERARCHY");
        parameters.put("module", "cff");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDAO.executeQuery(parameters);
        }
        CffProdHierarchy cffProdHierarchy = CffProdHierarchyLocalServiceUtil.createCffProdHierarchy(0);
        try {
            if ("update".equals(indicator)) {
                for (String rsId : addLevels) {
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            } else if ("save".equals(indicator)) {
                for (Leveldto dto : levelList) {

                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    cffProdHierarchy.setCffMasterSid(projectionId);
                    cffProdHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDAO.addProjectionProdHierarchy(cffProdHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.info(e + " saveProductHierarchyLogic");
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
            parameters.put("indicator", "getRemovableChildren");
            parameters.put("removeLevels", UiUtils.stringListToString(removeLevels));
            parameters.put("projectionId", projectionId);
            parameters.put("tableName", " PROJECTION_CUST_HIERARCHY ");
            removeChildLevels = dataSelectionDao.executeQuery(parameters);
            deleteCustomerHierarchyLevels(projectionId, removeLevels);
            if (removeChildLevels != null && !removeChildLevels.isEmpty()) {
                deleteCustomerHierarchyLevels(projectionId, UiUtils.objectListToStringList(removeChildLevels));
            }
        }
        saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, addLevels, "update");
    }

    private void deleteCustomerHierarchyLevels(final int projectionId, final List<String> removeLevels) throws Exception {
        List<CffCustHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        dynamicQuery.add(RestrictionsFactoryUtil.in("relationshipLevelSid", UiUtils.convertStringListToParsedIngeter(removeLevels)));
        details = dataSelectionDao.findCustHierarchyByProjectionId(dynamicQuery);
        for (final CffCustHierarchy cust : details) {
            dataSelectionDao.deleteProjectionCustHierarchies(cust);
        }
    }

    public void updateCustomerHierarchyLogic(final List<Leveldto> levelList, final List<String> endLevelSids, final int projectionId) throws Exception {
        List<CffCustHierarchy> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffCustHierarchy.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        details = dataSelectionDao.findCustHierarchyByProjectionId(dynamicQuery);
        for (final CffCustHierarchy cust : details) {
            dataSelectionDao.deleteProjectionCustHierarchies(cust);
        }
        saveCustomerHierarchyLogic(levelList, endLevelSids, projectionId, null, "save");
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
        parameters.put("indicator", "getChildLevelRLSid");
        parameters.put("projectionId", projectionId);
        parameters.put("rlSids", endLevelSids);
        parameters.put("tableName", "CFF_CUST_HIERARCHY");
        parameters.put("module", "cff");
        List<Object> endLevels = null;
        if (endLevelSids != null && !endLevelSids.isEmpty()) {
            endLevels = dataSelectionDAO.executeQuery(parameters);
        }

        CffCustHierarchy cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(0);
        try {
            if ("update".equals(indicator)) {
                for (String rsId : addLevels) {
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(rsId)));
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            } else if ("save".equals(indicator)) {
                for (Leveldto dto : levelList) {
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            }
            if (endLevels != null && !endLevels.isEmpty()) {
                for (Object relationshipLevelSid : endLevels) {
                    cffCustHierarchy.setCffMasterSid(projectionId);
                    cffCustHierarchy.setRelationshipLevelSid(UiUtils.parseStringToInteger(String.valueOf(relationshipLevelSid)));
                    dataSelectionDAO.addProjectionCustHierarchy(cffCustHierarchy);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e + " in saveCustomerHierarchyLogic");
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
        List<CffDetails> details;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CffDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
        details = dataSelectionDao.findProjDetailsByProjectionId(dynamicQuery);
        for (final CffDetails detail : details) {
            dataSelectionDao.deleteProjectionDetails(detail);
        }
    }

    /**
     * Insert records.
     *
     * @param levelList the level list
     */
    private void insertRecords(List<Leveldto> levelList, final int projectionId) {

        CffCustHierarchy cffCustHierarchy = CffCustHierarchyLocalServiceUtil.createCffCustHierarchy(0);
        try {
            for (Leveldto dto : levelList) {
                cffCustHierarchy.setCffMasterSid(projectionId);
                cffCustHierarchy.setRelationshipLevelSid(dto.getRelationshipLevelSid());
                dataSelectionDao.addProjectionCustHierarchy(cffCustHierarchy);
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
        List inputList = new ArrayList();
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
        List<Leveldto> resultList = new ArrayList<>();
        Leveldto dto;
        try {
            resultss = CommonQueryUtils.getAppData(inputList, "loadSelectedContainer", null);
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
                    if ("COMPANY_MASTER".equalsIgnoreCase(String.valueOf(objects[5]))) {
                        dto.setFromCompany(true);
                    } else if ("CONTRACT_MASTER".equalsIgnoreCase(String.valueOf(objects[5]))) {
                        dto.setFromContract(true);
                    } else if ("ITEM_MASTER".equalsIgnoreCase(String.valueOf(objects[5]))) {
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
            LOGGER.error(ex + " in getRelationShipValues");
        }
        return resultList;
    }

    public List getParentLevels(final int levelNo, final int relationshipLevelSid) {
        List resultss;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("levelNo", levelNo);
        parameters.put("indicator", StringUtils.EMPTY);
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
        parameters.put("indicator", StringUtils.EMPTY);
        parameters.put("parent", parent);
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
            LOGGER.error(ex);

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

    public List<String> getCustomerGroupDetails(int companyGroupSid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);

        dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid", companyGroupSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
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
        productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
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
            helperProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
            helper.add(RestrictionsFactoryUtil.eq("listName", "COMP_TYPE"));
            helper.add(RestrictionsFactoryUtil.like("description", "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyId = 0;
            companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            filterText = StringUtils.trimToEmpty(filterText) + "%";
            dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", UiUtils.convertStringListToIngeter(companySids)));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("companyName"));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("companyName", filterText));
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
            helperProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
            helper.add(RestrictionsFactoryUtil.eq("listName", "COMP_TYPE"));
            helper.add(RestrictionsFactoryUtil.like("description", "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyId = 0;
            companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            filterText = StringUtils.trimToEmpty(filterText) + "%";
            dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", UiUtils.convertStringListToIngeter(companySids)));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("companyName"));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            dynamicQuery.add(RestrictionsFactoryUtil.ilike("companyName", filterText));
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
            dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid", itemSids));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
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
            dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid", itemSids));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            resultList = dataSelectionDao.getItemMaster(dynamicQuery);
        }
        return resultList;
    }

    public List<Integer> getItemIdFromCompanyInCCp(final List<String> companySids, final int companySid) throws Exception {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class);
        if (companySids != null && !companySids.isEmpty()) {
            dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", UiUtils.convertStringListToIngeter(companySids)));
        }
        if (companySid != 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid", companySid));
        }
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
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
        dynamicQuery.add(PropertyFactoryUtil.forName("brandMasterSid").in(
                DynamicQueryFactoryUtil.forClass(BrandMaster.class).setProjection(ProjectionFactoryUtil.property("brandMasterSid"))));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
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
            dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid", UiUtils.convertStringListToIngeter(itemSidsFromHierarchy)));
            resultList = dataSelectionDao.getItemMaster(dynamicQuery);
        }
        return resultList;
    }

    public ForecastConfig getTimePeriod(String screenName) throws Exception {
        List<ForecastConfig> resultList = null;
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
        if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            screenName = "Commercial";
        } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            screenName = "Government";
        }
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", screenName));
        dynamicQuery.addOrder(OrderFactoryUtil.desc("versionNo"));
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
                parameters.put("projectionId", projectionId);
                parameters.put("indicator", indicator);
                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
                parameters.put("hierarchyNo", dto.getHierarchyNo());
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
        parameters.put("indicator", "getRbId");
        parameters.put("hierarchyDefinitionSid", hierarchyDefinitionSid);
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
        parameters.put("indicator", "insertToCcpMap");
        parameters.put("relationshipBuilderSids", relationshipBuilderSidsList);
        parameters.put("scrennName", screenName);
        dataSelectionDao.saveCcp(parameters);

    }

    public void saveCcp(final List<Leveldto> customerEndLevels, final String productHierarchyEndLevelsHierNos, final String indicator, final String projectionId) throws Exception {
        if (customerEndLevels != null && !customerEndLevels.isEmpty()) {
            Map<String, Object> parameters;
            for (Leveldto dto : customerEndLevels) {
                parameters = new HashMap<String, Object>();
                parameters.put("projectionId", projectionId);
                parameters.put("indicator", indicator);
                parameters.put("relationshipLevelSid", dto.getRelationshipLevelSid());
                parameters.put("hierarchyNo", dto.getHierarchyNo());
                parameters.put("productHierarchyEndLevelsHierNos", productHierarchyEndLevelsHierNos);
                parameters.put("indicator", "saveCcp");
                dataSelectionDao.getCcpMap(parameters);
            }
        }
    }

    public List<Leveldto> getParentLevelsWithHierarchyNo(final String hierarchyNos, final Map<String, String> descriptionMap) {
        List resultss;
        List<Leveldto> resultList = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("hierarchyNos", hierarchyNos);
        parameters.put("indicator", "getParentLevelsWithHierarchyNo");
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
            LOGGER.error(ex);
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
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchyDefinitionSid));
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
        dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchyDefinitionSid));
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
        parameters.put("hierarchyNo", hierarchyNo);
        parameters.put("lowestLevelNo", lowestLevelNo);
        parameters.put("indicator", "getChildLevelsWithHierarchyNo");
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
            LOGGER.error(ex);
        }
        return resultList;
    }

    public void getEndLevelRelationshipLevels(final List<String> endLevelSids, final String relationshipSid, List<Leveldto> ccList, List<String> availableHierNo) throws Exception {
        Leveldto dto;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("indicator", "getChildLevelRL");
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
                if ("COMPANY_MASTER".equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromCompany(true);
                } else if ("CONTRACT_MASTER".equalsIgnoreCase(String.valueOf(obj[4]))) {
                    dto.setFromContract(true);
                } else if ("ITEM_MASTER".equalsIgnoreCase(String.valueOf(obj[4]))) {
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
            if (tempDate[0] != null) {
                dto.setFileEndYear(Integer.parseInt(String.valueOf(tempDate[0])));
            } else {
                dto.setFileEndYear(0);
            }
            if (tempDate[1] != null) {
                dto.setFileEndMonth(Integer.parseInt(String.valueOf(tempDate[1])));
            } else {
                dto.setFileEndMonth(0);
            }
        }else{
             dto.setFileEndYear(0);
             dto.setFileEndMonth(0);
        }
    }

    public void deleteTempOnUpdate(final String projectionHierarchyTable, final int projectionId, final String hNos) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("indicator", "deleteTempOnUpdate");
        parameters.put("projectionHierarchyTable", projectionHierarchyTable);
        parameters.put("projectionId", projectionId);
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
        parameters.put("indicator", "getFSValue");
        parameters.put("relationshipLevelValue", relationshipLevelValue);
        parameters.put("fieldName", fieldName);
        try {
            list = (List) dataSelectionDao.executeQuery(parameters);
            return list;
        } catch (Exception e) {
            LOGGER.error(e + "in getFSValue");
            return null;
        }
    }

    public List<Integer> getItemIdFromCompanyForGlComp(final int companySid) throws Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("indicator", "companyFilter");
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
        dynamicQuery.add(RestrictionsFactoryUtil.eq("hierarchyDefinitionSid", hierarchyDefinitionSid));
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
            helperProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
            helper.add(RestrictionsFactoryUtil.eq("listName", "COMPANY_TYPE"));
            helper.add(RestrictionsFactoryUtil.like("description", "Glcomp"));
            helper.setProjection(ProjectionFactoryUtil.distinct(helperProjectionList));
            List<Object[]> companyTypeIds = HelperTableLocalServiceUtil.dynamicQuery(helper);
            int companyId = 0;
            companyId = Integer.valueOf(String.valueOf(companyTypeIds.get(0)));
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
            dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", UiUtils.convertStringListToIngeter(companySids)));
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("companyName"));
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
        String marketType = "";
        if (temp.size() > 0) {
            if (String.valueOf(temp.get(0)) != null && !"".equals(String.valueOf(temp.get(0)))) {
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
            LOGGER.error(ex);
            return null;
        }
    }

    public String getDefinedValue(String definedValue) {
        String str = "";
        List<Object> listValue = getDefinedValueResult(definedValue);
        if (listValue.size() > 0) {
            for (int i = 0; i < listValue.size(); i++) {
                str = String.valueOf(listValue.get(0));
            }
        }

        return str;

    }

    public List<Object> getDefinedValueResult(String definedValue) {
        String str = "";
        try {
            CommonDAO salesProjectionDAO = new CommonDAOImpl();
            str = "select LEVEL_VALUE_REFERENCE from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue + " and LEVEL_NAME='Market Type'";
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str, null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public String getHelperValue(String marketType) {
        String marketTypeValue = "";
        List<Object> temp = getHelperValueResult(marketType);
        if (temp.size() > 0) {
            for (int i = 0; i < temp.size(); i++) {
                marketTypeValue = String.valueOf(temp.get(i));
            }
        }
        return marketTypeValue;
    }

    public List<Object> getHelperValueResult(String projId) {
        String str = "";
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
            LOGGER.error(e);
            return null;
        }
    }

    public int getDiscountCount(String filterText) {
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            filterText = StringUtils.trimToEmpty(filterText) + "%";
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
            productProjectionList.add(ProjectionFactoryUtil.property("description"));
            query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            query.add(RestrictionsFactoryUtil.ilike("description", filterText));
            query.add(RestrictionsFactoryUtil.eq("listName", "RS_TYPE"));
            discountDdlbCount = dataSelectionDao.getDiscountCount(query);
        } catch (Exception ex) {
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
    public List<CompanyDdlbDto> getDiscounts(int startIndex, int endIndex, String filterText, CompanyDdlbDto discountDdlbDefault, CompanyDdlbDto selectedDiscount) throws Exception {
        List<CompanyDdlbDto> discounts = new ArrayList<CompanyDdlbDto>();
        int startValue = startIndex;
        int endValue = endIndex;
        if (startIndex == 0) {
            discounts.add(discountDdlbDefault);
        }
        CompanyDdlbDto discountDdlbDto;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        filterText = StringUtils.trimToEmpty(filterText) + "%";
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("helperTableSid"));
        productProjectionList.add(ProjectionFactoryUtil.property("description"));
        query.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        query.add(RestrictionsFactoryUtil.ilike("description", filterText));
        query.add(RestrictionsFactoryUtil.eq("listName", "RS_TYPE"));
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
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    }

    public String getMarketTypeValue(int ProjectId) {
        List<Object> temp = getMarketType(ProjectId);
        String marketType = "";
        if (temp.size() > 0) {
            Object[] objects = (Object[]) temp.get(0);
            if (String.valueOf(objects[0]) != null && !"".equals(String.valueOf(objects[0]))) {
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
                queryString.append("" + projectionId);
                queryString.append(" ) and LEVEL_NAME='Market TYPE'");
            }
            list = (List) salesProjectionDAO.executeSelectQuery(queryString.toString(), null, null);
            return list;

        } catch (Exception ex) {
            LOGGER.error(ex);
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
     * @param indicator
     * @param start
     * @param offset
     * @param filters
     * @param sortByColumns
     * @return the Product group result list
     * @throws java.lang.Exception
     */
    public List<GroupDTO> searchGroup(String name, String no, List<String> sids, String indicator, String groupIdentifier, String action,
            int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) throws Exception {
        List resultList = null;
        List<GroupDTO> returnList = null;
        name = name.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        no = no.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        Map<String, Object> parameters = prepareSearchGroup(name, no, sids, indicator, action, groupIdentifier, start, offset, filters, sortByColumns);
        resultList = dataSelectionDao.executeQueryforchannel(parameters);
        try {
            if (INDICATOR_CUSTOMER_GROUP.getConstant().equals(groupIdentifier)) {
                returnList = Converters.convertCustomerGroupList(resultList);
            } else {
                returnList = Converters.convertItemGroupList(resultList);
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in searchGroup");
        }
        return returnList;
    }

    public int searchGroupCount(String name, String no, List<String> sids, String indicator, String groupIdentifier, String action) throws Exception {
        List<Object> countList = null;
        int count = 0;
        name = name.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        no = no.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        Map<String, Object> parameters = prepareSearchGroup(name, no, sids, indicator, action, groupIdentifier, 0, 0, null, null);
        countList = dataSelectionDao.executeQueryforchannel(parameters);
        if (countList != null && !countList.isEmpty()) {
            count = Integer.parseInt(String.valueOf(countList.get(0)));
        }
        return count;
    }

    private Map<String, Object> prepareSearchGroup(String name, String no, List<String> sids, String indicator, String action, String groupIdentifier,
            int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);
        parameters.put("no", no);
        if (sids != null && !sids.isEmpty()) {
            parameters.put("sids", UiUtils.stringListToString(sids));
        } else {
            parameters.put("sids", null);
        }
        parameters.put("action", action);
        parameters.put("groupIdentifier", groupIdentifier);
        parameters.put("indicator", indicator);

        if (!"count".equals(action)) {
            parameters.put("start", start);
            parameters.put("offset", offset);
        } else {
            parameters.put("start", null);
            parameters.put("offset", null);
        }
        parameters.put("isFiltered", "false");
        parameters.put("isOrdered", "false");
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                    } else {
                        filterString = stringFilter.getFilterString();
                        parameters.put("filter~" + stringFilter.getPropertyId(), DataSelectionUtil.filterUser(filterString));
                    }
                    parameters.put("isFiltered", "true");
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put("filter~" + betweenFilter.getPropertyId() + "~from", String.valueOf(startValue));
                    parameters.put("filter~" + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                    parameters.put("isFiltered", "true");
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put("filter~" + compare.getPropertyId() + "~from", String.valueOf(value));
                    } else {
                        parameters.put("filter~" + compare.getPropertyId() + "~to", String.valueOf(value));
                    }
                    parameters.put("isFiltered", "true");
                }
            }
        }
        if (sortByColumns != null) {
            for (Iterator<SortByColumn> it = sortByColumns.iterator(); it.hasNext();) {
                SortByColumn orderByColumn = it.next();
                String columnId = orderByColumn.getName();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    parameters.put("orderBy~" + columnId, "asc");
                    parameters.put("isOrdered", "true");
                } else {
                    parameters.put("orderBy~" + columnId, "desc");
                    parameters.put("isOrdered", "true");
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

    public String getContractValue(String definedValue) {
        String str = "";
        List<Object> listValue = getContractValueResult(definedValue);
        if (listValue.size() > 0) {
            for (int i = 0; i < listValue.size(); i++) {
                str = String.valueOf(listValue.get(0));
            }
        }

        return str;

    }

    public List<Object> getContractValueResult(String definedValue) {
        String str = "";
        try {
            CommonDAO salesProjectionDAO = new CommonDAOImpl();
            str = "select FIELD_NAME from HIERARCHY_LEVEL_DEFINITION where HIERARCHY_DEFINITION_SID=" + definedValue + " and  LEVEL_NAME in('Customer','Trading Partner')";
            List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(str, null, null);
            return list;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List getProjection(int projectionId) throws SystemException, Exception {
        String sql = "SELECT CFF_NAME,PROD_RELATIONSHIP_BUILDER_SID,CUST_RELATIONSHIP_BUILDER_SID From dbo.CFF_MASTER where CFF_MASTER_SID = " + projectionId;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        if (list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }

    public Boolean hasTradingPartner(int projectionId) throws SystemException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("indicator", "hasTradingPartner");
        parameters.put("projectionId", projectionId);
        List returnList = dataSelectionDAO.executeQuery(parameters);
        if (returnList.isEmpty()) {
            return true;
        } else {
            return (Integer) returnList.get(0) >= 1;
        }
    }
}
