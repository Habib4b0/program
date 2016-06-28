/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.queryUtils.CommonQueryUtils;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.galforecasting.utils.Constant.STRING_EMPTY;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.app.util.Constants;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abhiram
 */
public class CommonLogic {

    private static final CommonDAO commonDao = new CommonDAOImpl();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    private static final CommonQueryUtils commonQueryUtil = new CommonQueryUtils();
    /**
     * The Constant LOGGER.
     */
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CommonLogic.class);

    /**
     * Get Customer Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getCustomerHierarchy(int projectionId, final int levelNo) {
        return getHierarchy(projectionId, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, levelNo);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchy(int projectionId, final int levelNo) {
        return getHierarchy(projectionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, levelNo);
    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchy(int projectionId, String hierarchyIndicator, final int levelNo) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getHierarchyTreeQuery(projectionId, hierarchyIndicator, levelNo);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, true);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return listValue;
    }

    /**
     * Gets the Customized View
     *
     * @param obj
     * @param levelDef
     * @param hierarchyId
     * @param hierarchyIndicator
     * @return
     */
    public static Leveldto getCustomizedView(Object[] obj, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
        dto.setHierarchyIndicator(String.valueOf(obj[2]));
        dto.setLevel(String.valueOf(obj[3]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[4])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[4]));
            dto.setParentNode(String.valueOf(obj[5]));
            dto.setHierarchyNo(String.valueOf(obj[6]));
        }
        return dto;
    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<CustomViewMaster> getCustomViewList(int projectionId) {
        List<CustomViewMaster> list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewMaster.class);
            query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
            LOGGER.info(ex.getCause());
        }
        return list;
    }

    public static int customDdlbOptionChange(ComboBox customDdlb, Button editBtn, ComboBox level) {
        editBtn.setEnabled(false);
        level.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!Constant.NULL.equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.valueOf(value);
            editBtn.setEnabled(true);
            level.setEnabled(true);
            return selectedId;
        }
        return 0;
    }

    public static int customDdlbOptionChange(ComboBox customDdlb, Button editBtn) {
        editBtn.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!Constant.NULL.equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.valueOf(value);
            editBtn.setEnabled(true);
            return selectedId;
        }
        return 0;
    }

    public static boolean editButtonValidation(ComboBox customDdlb, List<CustomViewMaster> customViewList) {
        Object value = customDdlb.getValue();
        String userId1 = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        int userId = 0;
        try {
            userId = Integer.valueOf(userId1);
        } catch (NumberFormatException ex) {
            LOGGER.info(ex.getCause());
        }
        if (userId != 0) {
            int selectedId = Integer.valueOf(String.valueOf(value));
            for (CustomViewMaster custom : customViewList) {
                if (custom.getCustomViewMasterSid() == selectedId) {
                    if (custom.getCreatedBy() == userId) {
                        return true;
                    }
                }
            }
        }
        NotificationUtils.getErrorNotification("Edit a View", "You cannot edit a view that you did not create. Please select another view, or create a new one");
        return false;
    }

    public static int customViewSaveLogic(SessionDTO session, int customId, String viewName, List levelList) {

        String userId1 = session.getUserId();
        int userId = 0;
        if (CommonUtils.isInteger(userId1)) {
            userId = Integer.valueOf(userId1);
        }

        if (userId != 0) {
            Date date = new Date();
            if (customId == 0) {
                try {
                    CustomViewMaster customViewMaster = CustomViewMasterLocalServiceUtil.createCustomViewMaster(0);
                    customViewMaster.setProjectionMasterSid(session.getProjectionId());
                    customViewMaster.setViewName(viewName);
                    customViewMaster.setCreatedBy(userId);
                    customViewMaster.setCreatedDate(date);
                    customViewMaster = commonDao.addCustomView(customViewMaster);
                    if (customViewMaster != null) {
                        try {
                            customId = customViewMaster.getCustomViewMasterSid();
                            customViewDetailsSaveLogic(customId, levelList);
                        } catch (SystemException ex) {
                            LOGGER.info(ex.getCause());
                        } catch (PortalException ex) {
                            LOGGER.info(ex.getCause());
                        }
                    }
                } catch (SystemException ex) {
                    LOGGER.info(ex.getCause());
                }

            } else {

                try {
                    CustomViewMaster customViewMaster = getCustomView(customId);
                    customViewMaster.setProjectionMasterSid(session.getProjectionId());
                    customViewMaster.setViewName(viewName);
                    customViewMaster.setModifiedBy(userId);
                    customViewMaster.setModifiedDate(date);
                    customViewMaster = commonDao.updateCustomView(customViewMaster);
                    if (customViewMaster != null) {
                        customId = customViewMaster.getCustomViewMasterSid();
                        List<CustomViewDetails> detailsList = getCustomViewDetails(customId);
                        for (CustomViewDetails customDetails : detailsList) {
                            try {
                                commonDao.deleteCustomViewDetails(customDetails);
                            } catch (SystemException ex) {
                                LOGGER.info(ex.getCause());
                            }
                        }

                        customViewDetailsSaveLogic(customId, levelList);
                    }
                } catch (SystemException ex) {
                    LOGGER.info(ex.getCause());
                } catch (PortalException ex) {
                    LOGGER.info(ex.getCause());
                }
            }
        }
        return customId;
    }

    public static boolean isValidViewName(int projectionId, String viewName, int customId) {
        List<CustomViewMaster> list = getCustomViewforViewName(projectionId, viewName, customId);
        if (list != null && !list.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<CustomViewMaster> getCustomViewforViewName(int projectionId, String viewName, int customId) {
        List<CustomViewMaster> list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewMaster.class);
            query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
            query.add(RestrictionsFactoryUtil.eq("viewName", viewName));
            if (customId != 0) {
                query.add(RestrictionsFactoryUtil.ne("customViewMasterSid", customId));
            }

            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
            LOGGER.info(ex.getCause());
        }
        return list;
    }

    public static boolean customViewDetailsSaveLogic(int customId, List levelList) throws SystemException, PortalException {
        for (Object ob : levelList) {
            Leveldto dto = (Leveldto) ob;
            CustomViewDetails customViewDetails = CustomViewDetailsLocalServiceUtil.createCustomViewDetails(0);
            customViewDetails.setCustomViewMasterSid(customId);
            customViewDetails.setHierarchyId(dto.getHierarchyId());
            customViewDetails.setHierarchyIndicator(dto.getHierarchyIndicator());
            customViewDetails.setLevelNo(dto.getTreeLevelNo());
            commonDao.addCustomViewDetails(customViewDetails);
        }
        return true;
    }

    /**
     * Get Custom View Details
     *
     * @param customId
     * @return list
     */
    public static List<CustomViewDetails> getCustomViewDetails(int customId) {
        List<CustomViewDetails> list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
            query.add(RestrictionsFactoryUtil.eq("customViewMasterSid", customId));
            query.addOrder(OrderFactoryUtil.asc("levelNo"));
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
            LOGGER.info(ex.getCause());
        }
        return list;
    }

    /**
     * Get the Custom Tree
     *
     * @param customId
     * @return
     */
    public static List<Leveldto> getCustomTree(int customId) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        if (customId != 0) {
            List<CustomViewDetails> customDetailsList = getCustomViewDetails(customId);
            for (CustomViewDetails ob : customDetailsList) {
                List list = getRelationshipLevels(ob.getHierarchyId());
                if (list != null && !list.isEmpty()) {
                    Object[] obj = (Object[]) list.get(0);
                    if (obj.length > 1) {
                        Leveldto dto = new Leveldto();
                        dto.setHierarchyId(ob.getHierarchyId());
                        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[1])));
                        dto.setLevel(String.valueOf(obj[0]));
                        dto.setTreeLevelNo(ob.getLevelNo());
                        dto.setHierarchyIndicator(ob.getHierarchyIndicator());
                        listValue.add(dto);
                    }
                }
            }
        }
        return listValue;
    }

    public static CustomViewMaster getCustomView(int customViewMasterSid) {
        CustomViewMaster cvm = null;
        if (customViewMasterSid != 0) {
            try {
                cvm = commonDao.getCustomView(customViewMasterSid);
            } catch (SystemException ex) {
                LOGGER.info(ex.getCause());
            } catch (PortalException ex) {
                LOGGER.info(ex.getCause());
            }
        }
        return cvm;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, boolean isCount, boolean isLimit) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, isCount, start, offset, isLimit, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return listValue;
    }

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        int count = 0;
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = null;
            if (!query.equals(StringUtils.EMPTY)) {
                list = (List<Object>) executeSelectQuery(query, null, null);
            }
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return count;
    }

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, int userId, int sessionId, boolean isGroupFilter, String levelName) {
        int count = 0;
        try {
            List<Object> list = null;
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, isGroupFilter, levelName);
            if (!query.equals(StringUtils.EMPTY)) {
                list = (List<Object>) executeSelectQuery(query, null, null);
            }
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    public static int getLevelIndex(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, String findHierarchyNo, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        int index = 0;
        try {
            String levelIndexQuery = getLevelIndexQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, findHierarchyNo, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = (List<Object>) executeSelectQuery(levelIndexQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                index = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {

            LOGGER.info(ex.getCause());
        }
        return index;
    }

    public static String getLevelIndexQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, String findHierarchyNo, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        String levelListQuery = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, false, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
        String selectClause = "select A.TEMP_INDEX from (" + levelListQuery + ") A where A.HIERARCHY_NO='" + findHierarchyNo + "'";
        return selectClause;
    }

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        if (isCustom) {

            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) executeSelectQuery(hierarchyIndicatorQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
            if (StringUtils.EMPTY.equals(hierarchyIndicator)) {
                return StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid);
            customSql += " from " + customViewQuery;
        } else {
            String relationshipBuilderSid = custRelSid;
            if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                relationshipBuilderSid = prodRelSid;
            }
            String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
            String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
            String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid"
                    + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + " \n"
                    + " JOIN projection_details PD "
                    + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQuery(userGroup, userId, sessionId, false) + ") CCPMAP,";

            String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                    + " AND PCH.projection_master_sid =" + projectionId
                    + " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
            String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";

            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }

    public static int getSalesCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        int count = 0;
        try {
            String query = getSalesCountQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public static String getSalesCountQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {

        String selectClause = "select Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        String customSql = selectClause;
        if (isCustom) {
            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid);
            customSql += " from " + customViewQuery;
        } else {
            String relationshipBuilderSid = custRelSid;
            if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                relationshipBuilderSid = prodRelSid;
            }
            String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
            String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
            String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid"
                    + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + " \n"
                    + " JOIN projection_details PD "
                    + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQuery(userGroup, userId, sessionId) + ") CCPMAP,";

            String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                    + " AND PCH.projection_master_sid =" + projectionId
                    + " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
            String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";

            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin;
        }
        return customSql;
    }

    public static String getCustomViewLevelListQuery(int projectionId, int customId, String hierarchyIndicator, int levelNo, String productHierarchyNo, String customerHierarchyNo, String custRelSid, String prodRelSid) {
        customerHierarchyNo += Constant.PERCENT;
        productHierarchyNo += Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customerLevelNo = StringUtils.EMPTY + levelNo;
        } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            productLevelNo = StringUtils.EMPTY + levelNo;
        }
        String customViewQuery = "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + custRelSid + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " ) CCPMAPC"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + prodRelSid + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " ) CCPMAPP "
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID"
                + " LEFT JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME \n"
                + " FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'"
                + " LEFT JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'"
                + " where HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_VALUES is not null";
        return customViewQuery;
    }

    public static String getCustomLevelListQuery1(int projectionId, String hierarchyIndicator, int levelNo, int treeLevelNo, int hierarchyId, String hierarchyNo, boolean isFilter, String parentHierarchyInd, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom) {

        String whereCond = " ";
        if (hierarchyNo != null) {
            if (isFilter) {
                whereCond = " and HIERARCHY_NO='" + hierarchyNo + "' ";
            } else if (parentHierarchyInd.equals(hierarchyIndicator)) {
                whereCond = " and HIERARCHY_NO like '" + hierarchyNo + "%' ";
            } else {
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += "Count(*) ";
        } else {
            selectClause += " LEVEL_NO, "
                    + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " LEVEL_NAME,"
                    + " RELATIONSHIP_LEVEL_VALUES,"
                    + " PARENT_NODE,"
                    + " HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String relationId = "select RELATIONSHIP_LEVEL_SID from ";
        relationId += getViewTableName(hierarchyIndicator);
        relationId += " where PROJECTION_MASTER_SID=" + projectionId;
        String customSql = selectClause + " from RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_LEVEL_SID in (" + relationId + ")"
                + " and HIERARCHY_LEVEL_DEFINITION_SID=" + hierarchyId
                + " and LEVEL_NO=" + levelNo
                + whereCond
                + recordNumber;
        return customSql;
    }

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {
        String finalQuery = StringUtils.EMPTY;
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId, sessionId, relationshipBuilderSid);
        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {

        String customSql = StringUtils.EMPTY;
        String tableName = getViewTableName(hierarchyIndicator);
        String mainSelect = "SELECT HLD.level_no, HLD.level_no as TREE_LEVEL_NO,'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,HLD.LEVEL_NAME,HLD.relationship_level_values,HLD.PARENT_NODE,HLD.HIERARCHY_NO ";
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid "
                + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + " \n"
                + " JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQuery(userGroup, userId, sessionId, false) + ") CCPMAP,";
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + tableName + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '%' AND RLD1.LEVEL_NO >= " + levelNo + ") HLD";
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD.hierarchy_no + '%'";

        customSql = mainSelect + " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = " from " + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.PROJECTION_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo;
        return customSql;
    }

    public static List<Object> getLevelNoAndHierarchyNo(Object value) {
        LOGGER.info("Inside getLevelNoAndHierarchyNo");
        List<Object> levelHierarchy = new ArrayList<Object>();
        String selectedId = DASH;
        if (value != null) {
            if (!SELECT_ONE.equals(String.valueOf(value))) {
                selectedId = String.valueOf(value);
            }
        }
        int levelNo = -1;
        String hierarchyNo = StringUtils.EMPTY;
        int j = selectedId.indexOf('~');
        if (j > 0) {
            levelNo = Integer.valueOf(selectedId.substring(0, j));
        }
        if (selectedId.length() > (j + 1)) {
            hierarchyNo = selectedId.substring(j + 1, selectedId.length());
        }
        levelHierarchy.add(Integer.valueOf(levelNo));
        levelHierarchy.add(hierarchyNo);
        LOGGER.info("Ending getLevelNoAndHierarchyNo");
        return levelHierarchy;
    }

    public static List<Object> getParentLevelNoAndHierarchyNo(int levelNo, String hierarchyNos) {
        List<Object> levelHierarchy = new ArrayList<Object>();
        String hierarchyNo = getParentHierarchyNo(hierarchyNos);
        levelNo--;
        if (hierarchyNo.isEmpty()) {
            levelNo = 0;
        }
        levelHierarchy.add(Integer.valueOf(levelNo));
        levelHierarchy.add(hierarchyNo);
        return levelHierarchy;
    }

    public static List<List<Object>> getAllLevelNoAndHierarchyNo(Object value) {
        LOGGER.info("getAllLevelNoAndHierarchyNo started");
        List<List<Object>> allLevelHierarchy = new ArrayList<List<Object>>();
        List<Object> levelHierarchy = getLevelNoAndHierarchyNo(value);

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        String hierarchyNo1 = String.valueOf(levelHierarchy.get(1));
        List<String> nos = getAllHierarchyNo(hierarchyNo1);
        Collections.reverse(nos);
        for (String hierarchyNo : nos) {
            levelHierarchy = new ArrayList<Object>();
            levelHierarchy.add(Integer.valueOf(levelNo));
            levelHierarchy.add(hierarchyNo);
            allLevelHierarchy.add(0, levelHierarchy);
            levelNo--;
        }
        LOGGER.info("getAllLevelNoAndHierarchyNo ended");
        return allLevelHierarchy;
    }

    public static List<Object> getParentLevelNoAndHierarchyNo(Object value) {
        List<Object> levelHierarchy = getLevelNoAndHierarchyNo(value);

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        return getParentLevelNoAndHierarchyNo(levelNo, hierarchyNo);
    }

    public static List getRelationshipLevels(int hierarchyLevelId) {
        List list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
            query.add(RestrictionsFactoryUtil.eq("hierarchyLevelDefinitionSid", hierarchyLevelId));
            ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
            projectionListFrom.add(ProjectionFactoryUtil.property("levelNo"));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = commonDao.getRelationshipLevels(query);
        } catch (SystemException ex) {
            LOGGER.info(ex.getCause());
        }
        return list;
    }

    public static Leveldto getNextLevel(int levelNo, List<Leveldto> hierarchy) {
        for (Leveldto dto : hierarchy) {
            if (dto.getTreeLevelNo() == levelNo) {
                return dto;
            }
        }

        return null;
    }

    public static Leveldto getLevel(List<Leveldto> hierarchy) {
        for (Leveldto dto : hierarchy) {
            return dto;
        }

        return null;
    }

    public static String getViewTableName(String hierarchyIndicator) {
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator) {
        List<Leveldto> newLevelList = new ArrayList<Leveldto>();
        try {
            String query = getAllHierarchyLevelsQuery(startLevelNo, projectionId, hierarchyIndicator, StringUtils.EMPTY, 0, 0, DASH);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    newLevelList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return newLevelList;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {
        List<Leveldto> newLevelList = new ArrayList<Leveldto>();
        try {
            String query = getAllHierarchyLevelsQuery(startLevelNo, projectionId, hierarchyIndicator, userGroup, userId, sessionId, relationshipBuilderSid);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    newLevelList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return newLevelList;
    }

    /**
     * Procedure Call
     *
     * @param procedureName
     * @param orderedArgs
     * @return
     */
    public static List<Object[]> callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.info("Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.info(i + " -- " + orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);

            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                LOGGER.error(new Date() + e.getMessage());
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(new Date() + e.getMessage());
            }
            try {
                connection.close();

            } catch (Exception ex) {
                LOGGER.error(new Date() + ex.getMessage());
            }
            try {
                System.gc();
            } catch (Exception ex) {
                LOGGER.error(new Date() + ex.getMessage());
            }
        }
        return objectList;
    }

    public static void callProcedureforUpdate(String procedureName, Object[] orderedArgs) {
        LOGGER.info("Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.info(i + " -- " + orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                statement.executeUpdate();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.info(ex.getCause());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                LOGGER.error(new Date() + e.getMessage());
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(new Date() + e.getMessage());
            }
            try {
                connection.close();

            } catch (Exception ex) {
                LOGGER.error(new Date() + ex.getMessage());
            }
            try {
                System.gc();
            } catch (Exception ex) {
                LOGGER.error(new Date() + ex.getMessage());
            }
        }

    }

    /**
     * To convert the given Result Set into List of Objects
     *
     * @param rs
     * @return
     */
    private static List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<Object[]>();

        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.info(ex.getCause());
            }
        }
        return objList;
    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<List<String>> getDiscountNoList(int projectionId, List<String> discounts, boolean isProgram, String userId, String sessionId) {
        List<List<String>> discountlist = new ArrayList<List<String>>();
        List<String> discountNolist = new ArrayList<String>();
        List<String> discountNamelist = new ArrayList<String>();
        List<Object> list = null;
        if (discounts != null && !discounts.isEmpty()) {
            String selectedDiscounts = CommonUtils.CollectionToString(discounts, true);
            String customSql = "select Distinct RS.RS_MODEL_SID as DISCOUNT_ID,RS.RS_NAME as DISCOUNT_NAME from RS_MODEL RS, PROJECTION_DETAILS D,"
                    + " ST_NM_DISCOUNT_PROJ_MASTER DM "
                    + "where DM.PROJECTION_DETAILS_SID = D.PROJECTION_DETAILS_SID "
                    + " and D.PROJECTION_MASTER_SID = " + projectionId;
            if (isProgram) {
                customSql += " and RS.RS_NAME in (" + selectedDiscounts + ")";
            } else {
                customSql += " and DM.PRICE_GROUP_TYPE in (" + selectedDiscounts + ")";
            }

            customSql += " and DM.RS_MODEL_SID=RS.RS_MODEL_SID and DM.USER_ID=" + userId + " AND DM.SESSION_ID = " + sessionId + " "
                    + " order by DISCOUNT_NAME";
            list = (List<Object>) executeSelectQuery(customSql, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    discountNolist.add(String.valueOf(obj[0]));
                    discountNamelist.add(String.valueOf(obj[1]));
                }
            }
        }
        discountlist.add(discountNolist);
        discountlist.add(discountNamelist);
        return discountlist;
    }

    public static List<Leveldto> getLevelListDiscount(int projectionId, String hierarchyIndicator, int levelNo, int hierarchyId) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            List list = CustomViewMasterLocalServiceUtil.getHierarchyLevelsForDiscount(projectionId, hierarchyIndicator, levelNo, hierarchyId);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedDiscountView(obj, hierarchyIndicator, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return listValue;
    }

    /**
     * Gets the Customized View
     *
     * @param levelDef
     * @param hierarchyId
     * @param hierarchyIndicator
     * @return
     */
    public static Leveldto getCustomizedDiscountView(Object[] obj, String hierarchyIndicator, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setLevel(String.valueOf(obj[1]));
        dto.setRelationShipBuilderId(String.valueOf(obj[5]));
        dto.setHierarchyLevelDefnId(String.valueOf(obj[4]));
        if (isHierarchy) {
            dto.setTreeLevelNo(dto.getLevelNo());
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[2])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[2]));
            dto.setParentNode(String.valueOf(obj[3]));
        }
        dto.setHierarchyIndicator(hierarchyIndicator);

        return dto;
    }

    /**
     * Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
    public static void saveProjectionSelection(Map map, int projectionID, String screenName) {
        List<NmProjectionSelection> list = new ArrayList<NmProjectionSelection>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                commonQueryUtil.saveSelection(map, projectionID, screenName, Constant.SAVE);
            } else {
                commonQueryUtil.saveSelection(map, projectionID, screenName, Constant.UPDATE);
            }
        } catch (SystemException ex) {
            LOGGER.info(ex.getCause());
        }
    }

    /**
     * Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
    public void saveProjectionSelectionMandatedDiscountProjection(Map map, int projectionID, String screenName) throws PortalException, Exception {
        List<MProjectionSelection> list = new ArrayList<MProjectionSelection>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                saveSelection(map, projectionID, screenName, Constant.SAVE, "M_PROJECTION_SELECTION");
            } else {
                saveSelection(map, projectionID, screenName, Constant.UPDATE, "M_PROJECTION_SELECTION");
            }
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Logic to Save projection selection section in all screens
     *
     * @param map
     * @param projectionID
     * @param screenName
     * @param saveOrUpdate
     */
    public void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate, String tableName) throws PortalException, Exception {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();

        if (Constant.SAVE.equalsIgnoreCase(saveOrUpdate)) {

            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO ").append(tableName).append(" (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE ").append(tableName).append(" SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(" WHERE PROJECTION_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(queryBuilder.toString());
    }

    /**
     * Get projection selection
     *
     * @param projectionId
     * @param screenName
     * @return
     */
    public static Map<Object, Object> getCHProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public static Map<Object, Object> getNMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return null;
    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<List<String>> getPriceGroupTypeList(int projectionId, List<String> discountName, String userId, String sessionId) {
        List<List<String>> discountlist = new ArrayList<List<String>>();
        List<String> discountNolist = new ArrayList<String>();
        List<String> priceGrouplist = new ArrayList<String>();
        List list = null;
        if (discountName != null && !discountName.isEmpty()) {
            list = commonQueryUtil.getPriceGroupType(projectionId, discountName, userId, sessionId);
        }

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                if (!priceGrouplist.contains(obj[1])) {
                    priceGrouplist.add(String.valueOf(obj[1]));
                }
                discountNolist.add(String.valueOf(obj[0]));

            }
        }
        discountlist.add(discountNolist);
        discountlist.add(priceGrouplist);

        return discountlist;
    }

    /**
     * Gets the DiscountNo.
     *
     * @param projectionId
     * @param priceGroupType
     * @return object
     */
    public static Object executeSelectQuery(String query, Object udc1, Object udc2) {

        return commonDao.executeSelectQuery(query, udc1, udc2);

    }

    /**
     * Gets the DiscountNo.
     *
     * @param projectionId
     * @param priceGroupType
     * @return object
     */
    public static Object executeBulkUpdateQuery(String query, Object udc1, Object udc2) {
        return commonDao.executeBulkUpdateQuery(query, udc1, udc2);
    }

    /**
     * Gets the DiscountNo.
     *
     * @param projectionId
     * @param priceGroupType
     * @return object
     */
    public static Object executeUpdateQuery(List<?> nmSalesList, Object udc1, Object udc2, Object udc3) {

        return commonDao.executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }

    /**
     * Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
    public void saveDiscountProjectionResults(Map map, int projectionID, String screenName) {
        List<NmProjectionSelection> list = new ArrayList<NmProjectionSelection>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                commonQueryUtil.saveSelection(map, projectionID, screenName, Constant.SAVE);
            } else {
                commonQueryUtil.saveSelection(map, projectionID, screenName, Constant.UPDATE);
            }
        } catch (SystemException ex) {
            LOGGER.info(ex.getCause());
        }
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String where) {
        List<String> list = new ArrayList<String>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS=" + table2 + ".YEARS";
        selectClause += "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS)";
        finalWhereCond += " and " + table1 + ".PERIODS=" + table2 + ".PERIODS";
        groupBy += ", " + table1 + ".PERIODS";
        selectClause += " as PERIODS, \n";

        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String table3, String where, boolean isTable3) {
        List<String> list = new ArrayList<String>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS)";
        String selectClause1 = "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS)";
        if (isTable3) {
            selectClause = "Isnull(Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS), " + table3 + ".YEARS)";
            selectClause1 = "Isnull(Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS), " + table3 + ".PERIODS)";
        }
        selectClause += " as YEARS, ";
        selectClause += selectClause1;
        selectClause += " as PERIODS, \n";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS=" + table2 + ".YEARS ";
        finalWhereCond += " and " + table1 + ".PERIODS=" + table2 + ".PERIODS ";
        groupBy += ", " + table1 + ".PERIODS";

        String finalWhereCond1 = " " + where + " " + table1 + ".YEARS=" + table3 + ".YEARS ";
        finalWhereCond1 += " and " + table1 + ".PERIODS=" + table3 + ".PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(finalWhereCond1);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getUserSessionQueryConditionPR(int userId, int sessionId, String table) {
        String user = table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID \n"
                + " and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID \n";
        return ccpWhereCond;
    }

    public static String getPeriodRestrictionQuery(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        String periodFilter = StringUtils.EMPTY;
        if (!CommonUtils.isInteger(projSelDTO.getYear())) {
            periodFilter = " and PERIOD_DATE BETWEEN ( '" + startDate + "') and ( '" + endDate + "') \n";
        }

        return periodFilter;
    }

    public static List getPeriodRestrictionPR(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        List list = new ArrayList();
        list.add(startDate);
        list.add(endDate);
        return list;
    }

    public static String getPeriodRestrictionQueryforPV(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        String periodFilter = StringUtils.EMPTY;
        if (!CommonUtils.isInteger(projSelDTO.getYear())) {
            periodFilter = " PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate + "') \n";
        }
        
        return periodFilter;
    }

    public static String getCCPQuery(ProjectionSelectionDTO projSelDTO, Boolean isVariance) {
        String ccpQuery = getCCPTempTableQuery(isVariance);
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQuery(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQuery(projSelDTO);
        }
        return ccpQuery;
    }

    public static String getCCPQueryPR(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = getCCPTempTableQueryPR();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQueryPR(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQueryPR(projSelDTO);
        }
        return ccpQuery;
    }

    public static String getCCPTempTableQuery(Boolean isVariance) {
        String tableQuery = StringUtils.EMPTY;
        if (isVariance) {
            tableQuery = "DECLARE @CCP TABLE\n"
                    + "  (\n"
                    + "     ID                     INT IDENTITY(1, 1),\n"
                    + "     RELATIONSHIP_LEVEL_SID INT,\n"
                    + "     CCP_DETAILS_SID        INT,\n"
                    + "     HIERARCHY_NO           VARCHAR(50)\n"
                    + "     UNIQUE NONCLUSTERED (CCP_DETAILS_SID, ID)\n"
                    + "  ) \n"
                    + " INSERT INTO @CCP\n"
                    + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        } else {
            tableQuery = "DECLARE @CCP TABLE\n"
                    + "  (\n"
                    + "     RELATIONSHIP_LEVEL_SID INT,\n"
                    + "     CCP_DETAILS_SID        INT,\n"
                    + "     HIERARCHY_NO           VARCHAR(50)\n"
                    + "  ) \n"
                    + " INSERT INTO @CCP\n"
                    + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        }

        return tableQuery;
    }

    public static String getCCPTempTableQueryPR() {
        String tableQuery = "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "PROJECTION_DETAILS_SID INT NOT NULL, \n"
                + "     HIERARCHY_NO           VARCHAR(50) \n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,PROJECTION_DETAILS_SID,HIERARCHY_NO) \n";
        return tableQuery;
    }

    public static String getGeneralCCPQuery(ProjectionSelectionDTO projSelDTO) {

        String relationshipBuilderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getProdRelationshipBuilderSid();
        }
        boolean isPrior = false;
        if (projSelDTO instanceof PVSelectionDTO) {
            PVSelectionDTO dto = (PVSelectionDTO) projSelDTO;
            isPrior = dto.isIsPrior();
        }
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = "   SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior);
        }
        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + " JOIN " + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + " JOIN " + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";

        return ccpQuery;
    }

    public static String getGeneralCCPQueryPR(ProjectionSelectionDTO projSelDTO) {

        String relationshipBuilderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getProdRelationshipBuilderSid();
        }
        boolean isPrior = false;
        if (projSelDTO instanceof PVSelectionDTO) {
            PVSelectionDTO dto = (PVSelectionDTO) projSelDTO;
            isPrior = dto.isIsPrior();
        }
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.PROJECTION_DETAILS_SID,LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, CCPMAP.PROJECTION_DETAILS_SID,HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID , PD.PROJECTION_DETAILS_SID\n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior);
        }
        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + " JOIN " + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + " JOIN " + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";

        return ccpQuery;
    }

    public static String getCustomCCPQuery(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;
        boolean isPrior = false;
        if (projSelDTO instanceof PVSelectionDTO) {
            PVSelectionDTO dto = (PVSelectionDTO) projSelDTO;
            isPrior = dto.isIsPrior();
        }
        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = "SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID,HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior);
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n";
        return ccpQuery;
    }

    public static String getCustomCCPQueryPR(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;
        boolean isPrior = false;
        if (projSelDTO instanceof PVSelectionDTO) {
            PVSelectionDTO dto = (PVSelectionDTO) projSelDTO;
            isPrior = dto.isIsPrior();
        }
        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = "SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID,CCPMAPC.PROJECTION_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,PD.PROJECTION_DETAILS_SID, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior);
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n";
        return ccpQuery;
    }

    public static List<String> getAllHierarchyNo(String hierarchyNo) {
        LOGGER.info("getAllHierarchyNo started");
        List<String> allLevelHierarchy = new ArrayList<String>();
        String extraDot = StringUtils.EMPTY;
        if (hierarchyNo.endsWith(".")) {
            extraDot = ".";
        }
        String[] hierarchyNoArray = hierarchyNo.split("\\.");
        String hierarchyNo1 = hierarchyNoArray[0];
        allLevelHierarchy.add(hierarchyNo1 + extraDot);
        for (int i = 1; i < hierarchyNoArray.length - 1; i++) {
            hierarchyNo1 = hierarchyNo1 + "." + hierarchyNoArray[i];
            allLevelHierarchy.add(hierarchyNo1 + extraDot);
        }
        if (!allLevelHierarchy.contains(hierarchyNo)) {
            allLevelHierarchy.add(hierarchyNo);
        }
        LOGGER.info("getAllHierarchyNo ended");
        return allLevelHierarchy;
    }

    public static String getParentHierarchyNo(String hierarchyNos) {
        int len = hierarchyNos.length();
        String extraDot = StringUtils.EMPTY;
        if (hierarchyNos.endsWith(".")) {
            extraDot = ".";
        }
        String hierarchyNo = hierarchyNos.substring(0, len - 1);
        int lin = hierarchyNo.lastIndexOf(".");
        if (lin > 0) {
            hierarchyNo = hierarchyNo.substring(0, lin) + extraDot;
        } else {
            hierarchyNo = StringUtils.EMPTY;
        }
        return hierarchyNo;
    }

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName(Constant.RESPONSIVE_PAGED_TABLE);

        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < 8; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }

    public static int getLevelListCountForDPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, boolean isGroupFilter, int sessionId, String userGroup, String custRelSid, String prodRelSid) {
        int count = 0;
        String query = getLevelListQueryForDPR(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, isGroupFilter, sessionId, userGroup, custRelSid, prodRelSid);
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

    public static String getLevelListQueryForDPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, boolean isGroupFilter, int sessionId, String userGroup, String custRelSid, String prodRelSid) {

        if (isCustom) {

            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) executeSelectQuery(hierarchyIndicatorQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = StringUtils.EMPTY;
        if (!isGroupFilter) {
            joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                    + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + "JOIN projection_master PM  "
                    + "  ON PD.projection_master_sid = PM.projection_master_sid \n"
                    + " WHERE  PM.projection_master_sid =" + projectionId + ") CCPMAP,";
        } else {
            joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                    + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + "JOIN projection_master PM  "
                    + "  ON PD.projection_master_sid = PM.projection_master_sid JOIN ST_NM_DISCOUNT_PROJ_MASTER A1 ON A1.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID \n"
                    + " WHERE  PM.projection_master_sid =" + projectionId + "  AND  A1.USER_GROUP='" + userGroup + "' AND A1.SESSION_ID=" + sessionId + ") CCPMAP,";
        }
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid);
            customSql += " from " + customViewQuery;
        } else {
            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }

    public static List<Leveldto> getConditionalLevelListForDPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int sessionId, String custRelSid, String prodRelSid) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getLevelListQueryForDPR(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, true, isCustom, customId, userGroup, sessionId, custRelSid, prodRelSid);

            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return listValue;
    }

    public static String getLevelListQueryForDPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, boolean isLimit, boolean isCustom, int customId, String userGroup, int sessionId, String custRelSid, String prodRelSid) {
        if (isCustom) {

            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;

            List<Object> list = (List<Object>) executeSelectQuery(hierarchyIndicatorQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + "JOIN projection_master PM  "
                + "  ON PD.projection_master_sid = PM.projection_master_sid JOIN ST_NM_DISCOUNT_PROJ_MASTER A1 ON A1.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID \n"
                + " WHERE  PM.projection_master_sid =" + projectionId + "  AND  A1.USER_GROUP='" + userGroup + "' AND A1.SESSION_ID=" + sessionId + ") CCPMAP,";

        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid);
            customSql += " from " + customViewQuery;
        } else {
            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty() && !userGroup.equals(STRING_EMPTY)) {
            query = "JOIN ST_NM_SALES_PROJECTION_MASTER SPMG ON SPMG.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  SPMG.USER_GROUP ='" + userGroup + "'";
            query += getUserSessionQueryCondition(userId, sessionId, "SPMG");
        }
        return query;
    }

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.All)) {
                if (userGroup.contains(Constant.DISCOUNT_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterDiscountQuery(userGroup, userId, sessionId, isPrior);
                } else if (userGroup.contains(Constant.PPA_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterPPAQuery(userGroup, userId, sessionId, isPrior);
                } else if (userGroup.contains(Constant.SALES_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
                }
            } else if (userGroup.startsWith(Constant.DISCOUNT)) {
                userGroup = " = '" + userGroup.replace(Constant.DISCOUNT, StringUtils.EMPTY) + "' ";
                query = getGroupFilterDiscountQuery(userGroup, userId, sessionId, isPrior);
            } else if (userGroup.startsWith(Constant.PPA)) {
                userGroup = " = '" + userGroup.replace(Constant.PPA, StringUtils.EMPTY) + "' ";
                query = getGroupFilterPPAQuery(userGroup, userId, sessionId, isPrior);
            } else if (userGroup.startsWith(Constant.Sales)) {
                userGroup = " = '" + userGroup.replace(Constant.Sales, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
            }
        }
        return query;
    }

    public static String getGroupFilterDiscountQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_DISCOUNT_PROJ_MASTER D ON D.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  D.USER_GROUP " + userGroup;
        if (!isPrior) {
            query += getUserSessionQueryCondition(userId, sessionId, "D");
        }
        return query;
    }

    public static String getGroupFilterPPAQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP " + userGroup;
        if (!isPrior) {
            query += getUserSessionQueryCondition(userId, sessionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        }
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup;
        if (!isPrior) {
            query += getUserSessionQueryCondition(userId, sessionId, Constant.S);
        }
        return query;
    }

    private static String getGroupQuery(int projectionId, int sessionId, int userId, String table) {
        String query = "SELECT DISTINCT M.USER_GROUP FROM " + table + " M"
                + "  JOIN PROJECTION_DETAILS PD ON M.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID"
                + " WHERE RLD.LEVEL_NAME = 'Trading Partner' "
                + " AND M.USER_GROUP IS NOT NULL "
                + " AND PD.PROJECTION_MASTER_SID ='" + projectionId + "' "
                + " AND M.USER_ID=" + userId
                + " AND M.SESSION_ID=" + sessionId;
        return query;
    }

    public static List<String> getDiscountGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<String>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_DISCOUNT_PROJ_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.DISCOUNT + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return groupList;
    }

    public static List<String> getPPAGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<String>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_PPA_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.PPA + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return groupList;
    }

    public static List<String> getSalesGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<String>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_SALES_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.Sales + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return groupList;
    }

    public static List<String> getAllGroup(int projectionId, int userId, int sessionId, boolean isPPA) {
        List<String> groupList = new ArrayList<String>();
        groupList.add("All Sales Group");
        groupList.add("All Discount Group");
        if (isPPA) {
            groupList.add("All PPA Group");
        }
        groupList.addAll(getSalesGroup(projectionId, sessionId, userId));
        groupList.addAll(getDiscountGroup(projectionId, sessionId, userId));
        if (isPPA) {
            groupList.addAll(getPPAGroup(projectionId, sessionId, userId));
        }
        return groupList;
    }

    public static List<String> getAllPPAGroup(int projectionId, int userId, int sessionId) {
        List<String> groupList = new ArrayList<String>();
        groupList.add(Constant.ALL_GROUP);
        groupList.addAll(getPPAGroup(projectionId, sessionId, userId));
        return groupList;
    }

    public static int getTradingPartnerLevelNo(boolean isCustom, int projectionIdOrCustomId) {
        int levelNo = 0;
        String query = "SELECT DISTINCT ";
        if (isCustom) {
            query += " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "                JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVM.CUSTOM_VIEW_MASTER_SID=CVD.CUSTOM_VIEW_MASTER_SID AND CVM.CUSTOM_VIEW_MASTER_SID=" + projectionIdOrCustomId + "\n"
                    + "                JOIN dbo.PROJECTION_CUST_HIERARCHY PCH ON CVM.PROJECTION_MASTER_SID =PCH.PROJECTION_MASTER_SID\n"
                    + "                JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID \n"
                    + "                AND CVD.HIERARCHY_ID=RLD.HIERARCHY_LEVEL_DEFINITION_SID AND RLD.LEVEL_NAME = 'Trading Partner'";
        } else {
            query += " RLD.LEVEL_NO FROM dbo.RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                    + "                JOIN dbo.PROJECTION_CUST_HIERARCHY PCH ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID \n"
                    + "                AND PCH.PROJECTION_MASTER_SID = " + projectionIdOrCustomId + "\n"
                    + "                 WHERE RLD.LEVEL_NAME = 'Trading Partner'";
        }
        try {
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                levelNo = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }

        return levelNo;
    }

    public static List<String> getSalesGroupDDLB(int projectionId, int userId, int sessionId) {
        List<String> groupList = new ArrayList<String>();
        groupList.addAll(getSalesGroup(projectionId, sessionId, userId));
        return groupList;
    }

    public static String getCustomCCPQueryForDPR(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;
        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = "(SELECT distinct  CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false);
        }

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false);
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'"
                + " ) \n";
        return ccpQuery;
    }

    public static List<Integer> getPageNumber() {
        List<Integer> pagelength = new ArrayList<Integer>();
        pagelength.add(10);
        pagelength.add(15);
        pagelength.add(20);
        pagelength.add(25);
        pagelength.add(50);
        pagelength.add(100);
        return pagelength;
    }

    /**
     * Get Customer Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getCustomerHierarchy(int projectionId, final int levelNo, final Object rbID) {
        return getHierarchy(projectionId, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, levelNo, rbID);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchy(int projectionId, final int levelNo, final Object rbID) {
        return getHierarchy(projectionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, levelNo, rbID);
    }

    /**
     * Get projection selection
     *
     * @param projectionId
     * @param screenName
     * @return
     */
    public static Map<Object, Object> getMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchy(int projectionId, String hierarchyIndicator, final int levelNo, final Object rbID) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getHierarchyTreeQuery(projectionId, hierarchyIndicator, levelNo, rbID);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                int count = 0;
                for (Object list1 : list) {
                    count++;
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, true);
                    dto.setCount(count);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return listValue;
    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo, final Object rbID) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = " from " + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.PROJECTION_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo + " AND RLD.RELATIONSHIP_BUILDER_SID=" + rbID + ";";
        return customSql;
    }

    public static String getIndicator(int levelNo, int viewName) {
        List<CustomViewDetails> list = null;
        String indicator = StringUtils.EMPTY;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
        query.add(RestrictionsFactoryUtil.eq("customViewMasterSid", viewName));
        query.add(RestrictionsFactoryUtil.eq("levelNo", levelNo));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        if (list != null && !list.isEmpty()) {
            for (CustomViewDetails customViewDetails : list) {
                indicator = customViewDetails.getHierarchyIndicator();
                break;
            }
        }
        return indicator;
    }

    public static int getIndicatorCount(int projectionId, int viewName) {
        List<CustomViewDetails> list = null;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
        query.add(RestrictionsFactoryUtil.eq("customViewMasterSid", viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return list.size();
    }

    public static int getLevelListCountDPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, boolean isFilter, boolean isGroupFilter, String levelName, int customSid, boolean customFlag) {
        int count = 0;
        String query = getLevelListQueryDPR(projectionId, hierarchyIndicator, levelNo, hierarchyNo, StringUtils.EMPTY, StringUtils.EMPTY, isFilter, false, true, 0, 0, false, customFlag, customFlag ? customSid : 0, isGroupFilter, levelName);
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

    public static String getLevelListQueryDPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, boolean isGroupFilter, String levelName) {
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (isCustom) {
            hierarchyIndicator = StringUtils.EMPTY.equalsIgnoreCase(hierarchyIndicator) || "null".equalsIgnoreCase(String.valueOf(hierarchyIndicator)) ? "P" : CommonLogic.getIndicator(levelNo, customId);
        }
        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + ") CCPMAP,";
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;

        if (isGroupFilter) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' " + getLevelNo(levelNo) + ") HLD" + hierarchyIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo);
            customSql += " from " + customViewQuery;
        } else {
            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }

    public static String getLevelNo(int levelNo) {
        String str = StringUtils.EMPTY;
        if (levelNo != 0) {
            str = "AND RLD1.LEVEL_NO = " + levelNo;
        }
        return str;
    }

    public static String getCustomViewLevelListQuery(int projectionId, int customId, String hierarchyIndicator, int levelNo, String productHierarchyNo, String customerHierarchyNo) {
        customerHierarchyNo += Constant.PERCENT;
        productHierarchyNo += Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customerLevelNo = StringUtils.EMPTY + levelNo;
        } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            productLevelNo = StringUtils.EMPTY + levelNo;
        }
        String customViewQuery = "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " ) CCPMAPC"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " ) CCPMAPP "
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID"
                + " JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'"
                + " JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO like '" + productLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";
        return customViewQuery;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, boolean filterDdlb, String levelName) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, StringUtils.EMPTY, 0, 0, filterDdlb, levelName);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return listValue;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int userId, int sessionId, boolean filterDdlb, String levelName) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, userGroup, userId, sessionId, filterDdlb, levelName);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return listValue;
    }

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, String userGroup, int userId, int sessionId, boolean filterDdlb, String levelName) {
        if (isCustom) {

            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) executeSelectQuery(hierarchyIndicatorQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQuery(userGroup, userId, sessionId) + ") CCPMAP,";

        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;

        if (filterDdlb) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo);
            customSql += " from " + customViewQuery;
        } else {
            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }

    public static List<Leveldto> getConditionalLevelListCH(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, boolean isCount, boolean isLimit) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
        return listValue;
    }

    public static int getLevelListCountCH(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        int count = 0;
        try {

            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);

            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);

            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {

            LOGGER.info(ex.getCause());
        }
        return count;
    }

    public static String getCCPQueryCH(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = getCCPTempTableQueryCH();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQueryCH(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQueryCH(projSelDTO);
        }
        return ccpQuery;
    }

    public static String getCCPTempTableQueryCH() {
        String tableQuery = "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        return tableQuery;
    }

    public static String getCustomCCPQueryCH(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = "SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID,HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID=" + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID=" + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n";
        return ccpQuery;
    }

    public static String getGeneralCCPQueryCH(ProjectionSelectionDTO projSelDTO) {

        String relationshipBuilderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getProdRelationshipBuilderSid();
        }
        String hierarchyNo = projSelDTO.getHierarchyNo();
        if (projSelDTO.isIsFilter()) {
            hierarchyNo = StringUtils.EMPTY;
        }
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());

        String ccpQuery = "SELECT HLD.RELATIONSHIP_LEVEL_SID, CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO \n"
                + "                  FROM   (SELECT RLD.HIERARCHY_NO,\n"
                + "        CCP.CCP_DETAILS_SID\n"
                + " FROM   RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + " JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + " JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " ) CCPMAP JOIN\n"
                + "(SELECT RLD1.HIERARCHY_NO,\n"
                + "        RLD1.RELATIONSHIP_LEVEL_SID,\n"
                + "        RLD1.LEVEL_NO\n"
                + " FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                + " JOIN   " + viewtable + "  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID AND PCH.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "AND  RLD1.HIERARCHY_NO LIKE '" + hierarchyNo + "%') HLD ON CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO+'%'\n"
                + "  WHERE HLD.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + "\n";

        return ccpQuery;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId) {
        List<Leveldto> newLevelList = new ArrayList<Leveldto>();
        try {
            String query = getAllHierarchyLevelsQuery(startLevelNo, projectionId, hierarchyIndicator, userGroup, userId, sessionId);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    newLevelList.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return newLevelList;
    }

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId) {
        String finalQuery = StringUtils.EMPTY;
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId, sessionId);

        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo, String userGroup, int userId, int sessionId) {

        String customSql = StringUtils.EMPTY;
        String tableName = getViewTableName(hierarchyIndicator);
        String mainSelect = "SELECT HLD.level_no, HLD.level_no as TREE_LEVEL_NO,'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,HLD.LEVEL_NAME,HLD.relationship_level_values,HLD.PARENT_NODE,HLD.HIERARCHY_NO ";
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQueryMandated(userGroup, userId, sessionId) + ") CCPMAP,";
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + tableName + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '%' AND RLD1.LEVEL_NO >= " + levelNo + ") HLD";
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD.hierarchy_no + '%'";

        customSql = mainSelect + " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public static String getGroupFilterQueryMandated(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.All)) {
                if (userGroup.contains(Constant.DISCOUNT_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterDiscountQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains(Constant.PPA_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterPPAQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains(Constant.SALES_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterSalesQuery(userGroup, userId, sessionId);
                }
            } else if (userGroup.startsWith(Constant.DISCOUNT)) {
                userGroup = " = '" + userGroup.replace(Constant.DISCOUNT, StringUtils.EMPTY) + "' ";
                query = getGroupFilterDiscountQuery(userGroup, userId, sessionId);
            } else if (userGroup.startsWith(Constant.PPA)) {
                userGroup = " = '" + userGroup.replace(Constant.PPA, StringUtils.EMPTY) + "' ";
                query = getGroupFilterPPAQuery(userGroup, userId, sessionId);
            } else if (userGroup.startsWith(Constant.Sales)) {
                userGroup = " = '" + userGroup.replace(Constant.Sales, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId);
            }
        }
        return query;
    }

    public static String getGroupFilterDiscountQuery(String userGroup, int userId, int sessionId) {
        String query = "JOIN ST_M_DISCOUNT_PROJ_MASTER D ON D.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  D.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, "D");
        return query;
    }

    public static String getGroupFilterPPAQuery(String userGroup, int userId, int sessionId) {
        String query = "JOIN ST_M_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId) {
        String query = "JOIN ST_M_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, Constant.S);
        return query;
    }

    public static String getUserSessionQueryConditionForPR(int userId, int sessionId, String table) {
        String user = " " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public static String getCCPQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = getCCPTempTableQueryForPR();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQueryForPR(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQueryForPR(projSelDTO);
        }
        return ccpQuery;
    }

    public static String getCCPTempTableQueryForPR() {
        String tableQuery = "DECLARE @CCP TABLE\n"
                + "  (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     PROJECTION_DETAILS_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + " INSERT INTO @CCP\n"
                + "            (RELATIONSHIP_LEVEL_SID,PROJECTION_DETAILS_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        return tableQuery;
    }

    public static String getCustomCCPQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        String ccpQuery = "SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, PD.PROJECTION_DETAILS_SID,CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID,HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        ccpQuery += " ) CCPMAPC \n"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n";

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%' \n"
                + " JOIN "
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCPMAPC.CCP_DETAILS_SID AND\n"
                + "  PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n";
        return ccpQuery;
    }

    public static String getGeneralCCPQueryForPR(ProjectionSelectionDTO projSelDTO) {

        String relationshipBuilderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getProdRelationshipBuilderSid();
        }

        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID,LCCP.PROJECTION_DETAILS_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID,CCPMAP.PROJECTION_DETAILS_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID,PD.PROJECTION_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();

        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + " JOIN " + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + " JOIN " + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ") \n";
        return ccpQuery;
    }

    /**
     * Saves the projection selection in all the screens.
     *
     * @param map
     * @param tabName
     * @param projectionSelectionDTO
     * @throws PortalException
     * @throws Exception
     */
    public static void saveProjectionSelection(final Map<String, String> map, final String tabName, final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, Exception {

        String screenName = projectionSelectionDTO.getScreenName();
        String tableName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName) ? "NM_PROJECTION_SELECTION" : CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName) ? "M_PROJECTION_SELECTION" : CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName) ? "CH_PROJECTION_SELECTION" : StringUtils.EMPTY;
        StringBuilder query = new StringBuilder();
        query.append("DELETE\n" + "FROM\n" + " ").append(tableName).append("\n" + "WHERE\n" + "PROJECTION_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append("\n" + "AND SCREEN_NAME LIKE '").append(tabName).append("';\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            query.append("INSERT \n" + "INTO\n" + StringUtils.EMPTY).append(tableName).append("\n" + "	(PROJECTION_MASTER_SID, SCREEN_NAME, FIELD_NAME, FIELD_VALUES)\n" + "VALUES\n" + "	(").append(projectionSelectionDTO.getProjectionId()).append(", '").append(tabName).append("', '").append(entry.getKey()).append("', '").append(entry.getValue()).append("');\n");
        }

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(query.toString());

    }

    public List<HelperDTO> getDropDownList(final String listType) {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        try {
            SalesProjectionDAO dao = new SalesProjectionDAOImpl();
            LOGGER.info("entering getDropDownList method with paramater listType=" + listType);
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constant.LIST_NAME, listType), RestrictionsFactoryUtil.like(Constant.LIST_NAME, "ALL")));
            cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constant.DESCRIPTION));
            final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
            helperList.add(new HelperDTO(0, Constant.SELECT_ONE));
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    final HelperTable helperTable = (HelperTable) list.get(i);
                    helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                            helperTable.getDescription()));
                }
            }

            LOGGER.info(" getDropDownList method ends with return value strList size =" + helperList.size());

        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return helperList;
    }

    public static Map editProjectionResults(final String tabName, final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException, Exception {

        String screenName = projectionSelectionDTO.getScreenName();
        String tableName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName) ? "NM_PROJECTION_SELECTION" : CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName) ? "M_PROJECTION_SELECTION" : CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName) ? "CH_PROJECTION_SELECTION" : StringUtils.EMPTY;

        StringBuilder query = new StringBuilder();
        query.append("SELECT FIELD_NAME, FIELD_VALUES FROM ").append(tableName).append("\n" + "WHERE PROJECTION_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append("\n AND SCREEN_NAME LIKE '").append(tabName).append("';\n");
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List<Object[]> resultlist = (List) dao.executeSelectQuery(query.toString());
        Map<String, String> resultmap = new HashMap<>();
        for (Object[] list1 : resultlist) {
            resultmap.put((String) list1[0], (String) list1[1]);
        }
        return resultmap;
    }

    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CommonLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Method returns PeriodSID from the given frequency
     *
     * @param frequency
     * @param selectedYear
     * @param selectedPeriod
     * @return
     */
    public static String getPeriodSID(String frequency, String selectedPeriod, boolean formatFlag) {
        String periodSID = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(selectedPeriod) && !Constant.NULL.equals(selectedPeriod)) {
            try {
                String year = Constant.ANNUAL.equalsIgnoreCase(frequency) ? selectedPeriod.trim() : getYearAndPeriod(selectedPeriod, frequency, formatFlag)[0];
                String period = Constant.ANNUAL.equalsIgnoreCase(frequency) ? selectedPeriod.trim() : getYearAndPeriod(selectedPeriod, frequency, formatFlag)[1];
                List list = new ArrayList<>();
                String query = CustomSQLUtil.get("getPeriodSID");
                query = query.replace("@selectedfreq", frequency);
                query = query.replace("@selectedyear", year);
                query = query.replace("@selectedperiod", period);
                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
                list = (List) salesProjectionDAO.executeSelectQuery(query);
                if (!list.isEmpty()) {
                    periodSID = String.valueOf(list.get(0));
                }
            } catch (PortalException ex) {
                LOGGER.error(ex.getMessage());
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else {
            periodSID = DASH;
        }
        return periodSID;
    }

    /**
     * returns year and period of given frequency
     *
     * @param period
     * @param frequency
     * @return
     */
    public static String[] getYearAndPeriod(String period, String frequency, boolean formatFlag) throws Exception {
        String[] periodArr = new String[2];
        String[] splitPeriodYear = period.trim().split(" ");
        if (formatFlag) {
            periodArr[0] = splitPeriodYear[1].trim();
            periodArr[1] = Constant.MONTHLY.equals(frequency)
                    ? String.valueOf(CommonUtils.getMonthNumber(splitPeriodYear[0].trim())) : splitPeriodYear[0].trim().replaceAll("[^0-9]", StringUtils.EMPTY);
        } else {
            periodArr[0] = splitPeriodYear[0].trim();
            periodArr[1] = Constant.MONTHLY.equals(frequency)
                    ? String.valueOf(CommonUtils.getMonthNumber(splitPeriodYear[1].trim())) : splitPeriodYear[1].trim().replaceAll("[^0-9]", StringUtils.EMPTY);
        }
        return periodArr;
    }

    /**
     * Get the Custom Tree
     *
     * @param customId
     * @return
     */
    public static List<Leveldto> getCustomTreeMan(int customId) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        if (customId != 0) {
            List<CustomViewDetails> customDetailsList = getCustomViewDetailsMan(customId);
            for (CustomViewDetails ob : customDetailsList) {
                List list = getRelationshipLevelsMan(ob.getHierarchyId());
                if (list != null && !list.isEmpty()) {
                    Object[] obj = (Object[]) list.get(0);
                    if (obj.length > 1) {
                        Leveldto dto = new Leveldto();
                        dto.setHierarchyId(ob.getHierarchyId());
                        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[1])));
                        dto.setLevel(String.valueOf(obj[0]));
                        dto.setTreeLevelNo(ob.getLevelNo());
                        dto.setHierarchyIndicator(ob.getHierarchyIndicator());
                        listValue.add(dto);
                    }
                }
            }
        }
        return listValue;
    }

    public static List getRelationshipLevelsMan(int hierarchyLevelId) {
        List list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
            query.add(RestrictionsFactoryUtil.eq("hierarchyLevelDefinitionSid", hierarchyLevelId));
            ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
            projectionListFrom.add(ProjectionFactoryUtil.property("levelNo"));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery(query);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return list;
    }

    /**
     * Get Custom View Details
     *
     * @param customId
     * @return list
     */
    public static List<CustomViewDetails> getCustomViewDetailsMan(int customId) {
        List<CustomViewDetails> list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
            query.add(RestrictionsFactoryUtil.eq("customViewMasterSid", customId));
            query.addOrder(OrderFactoryUtil.asc("levelNo"));
            list = CustomViewDetailsLocalServiceUtil.dynamicQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return list;
    }

    /**
     * Get Customer Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getCustomerHierarchyMandated(int projectionId, final int levelNo) {
        return getHierarchyMan(projectionId, Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, levelNo);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchyMandated(int projectionId, final int levelNo) {
        return getHierarchyMan(projectionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, levelNo);
    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchyMan(int projectionId, String hierarchyIndicator, final int levelNo) {
        List<Leveldto> listValue = new ArrayList<Leveldto>();
        try {
            String query = getHierarchyTreeQueryMan(projectionId, hierarchyIndicator, levelNo);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedViewMan(obj, true);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return listValue;
    }

    public static String getHierarchyTreeQueryMan(int projectionId, String hierarchyIndicator, final int levelNo) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = " from " + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.PROJECTION_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo;
        return customSql;
    }

    /**
     * Gets the Customized View
     *
     * @param levelDef
     * @param hierarchyId
     * @param hierarchyIndicator
     * @return
     */
    public static Leveldto getCustomizedViewMan(Object[] obj, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
        dto.setHierarchyIndicator(String.valueOf(obj[2]));
        dto.setLevel(String.valueOf(obj[3]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[4])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[4]));
            dto.setParentNode(String.valueOf(obj[5]));
            dto.setHierarchyNo(String.valueOf(obj[6]));
            dto.setRelationshipLevelName(String.valueOf(DataSelectionForm.getRelationLevelValues().get(dto.getHierarchyNo())));
        }
        return dto;
    }

    public static List<Object> getLevelNoAndHierarchyNo(Object value, boolean filter) {
        List<Object> levelHierarchy = new ArrayList<Object>();
        String selectedId = DASH;
        if (value != null) {
            if (!SELECT_ONE.equals(String.valueOf(value))) {
                selectedId = String.valueOf(value);
            }
        }
        int levelNo = -1;
        String hierarchyNo = StringUtils.EMPTY;
        int j = selectedId.indexOf('~');
        if (filter && j > 0) {
            levelNo = Integer.valueOf(selectedId.substring(j - 1, j));
        } else if (j > 0) {
            levelNo = Integer.valueOf(selectedId.substring(0, j));
        }
        if (selectedId.length() > (j + 1)) {
            hierarchyNo = selectedId.substring(j + 1, selectedId.length());
        }
        levelHierarchy.add(Integer.valueOf(levelNo));
        levelHierarchy.add(hierarchyNo);
        return levelHierarchy;
    }

    public static int getLevelListCountMSPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, boolean isFilter, boolean isGroupFilter, String levelName) {
        int count = 0;
        String query = getLevelListQueryMSPR(projectionId, hierarchyIndicator, levelNo, hierarchyNo, StringUtils.EMPTY, StringUtils.EMPTY, isFilter, false, true, 0, 0, false, false, 0, isGroupFilter, levelName);
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

    public static String getLevelListQueryMSPR(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, boolean isGroupFilter, String levelName) {
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";

        if (hierarchyNo != null) {
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + ") CCPMAP,";
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;

        if (isGroupFilter) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' " + getLevelNo(levelNo) + ") HLD" + hierarchyIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo);
            customSql += " from " + customViewQuery;
        } else {
            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;

        return customSql;
    }

    public static Map<Object, Object> getReturnsProjectionSelection(final int projectionId, final String screenName) {
        List list = new ArrayList<>();
        String query = StringUtils.EMPTY;
        Map<Object, Object> map = new HashMap<Object, Object>();

        query = "select Field_Name,Field_Values from RETURNS_PROJECTION_SELECTION\n"
                + "where Projection_Master_Sid=" + projectionId + ";";
        try {

            list = (List) commonDao.executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return null;
    }

    public static String getTempCCPQueryForCOGS(PVSelectionDTO pvsDTO) {
        List fromToList = CommonLogic.getPeriodRestrictionPR(pvsDTO);
        String query = " DECLARE @FROM_DATE DATE\n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
                + "      ,  @PROGRAM_TYPE VARCHAR(50) = '" + pvsDTO.getDiscountLevel().toUpperCase() + "'"
                + " \n"
                + " SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                + " FROM PROJECTION_MASTER\n"
                + " WHERE PROJECTION_MASTER_SID = " + pvsDTO.getProjectionId() + " \n"
                + " SELECT @START_PERIOD_SID = PERIOD_SID\n"
                + "      FROM PERIOD\n"
                + "      WHERE PERIOD_DATE = @STARTFROM\n"
                + "      SELECT @END_PERIOD_SID = PERIOD_SID\n"
                + "      FROM PERIOD\n"
                + "     WHERE PERIOD_DATE = @PROJECTION_DATE "
                + "\n"
                + "DECLARE @PERIOD TABLE\n"
                + "  (\n"
                + "     PERIOD_SID  INT PRIMARY KEY,\n"
                + "     YEAR        INT,\n"
                + "     MONTH       INT,\n"
                + "     QUARTER     INT,\n"
                + "     SEMI_ANNUAL INT\n"
                + "  )\n"
                + "\n"
                + "INSERT INTO @PERIOD\n"
                + "            (PERIOD_SID,\n"
                + "             YEAR,\n"
                + "             MONTH,\n"
                + "             QUARTER,\n"
                + "             SEMI_ANNUAL)\n"
                + "SELECT PERIOD_SID,\n"
                + "       YEAR,\n"
                + "       MONTH,\n"
                + "       QUARTER,\n"
                + "       SEMI_ANNUAL\n"
                + "FROM   PERIOD\n"
                + "WHERE  PERIOD_DATE BETWEEN CONVERT(DATE, '"+fromToList.get(0)+"') AND CONVERT(DATE, '"+fromToList.get(1)+"')"
                + "\n"
                + ""
                + ""
                + " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + " CREATE TABLE #TEMP_CCP (\n"
                + " COMPANY_MASTER_SID INT\n"
                + " ,CONTRACT_MASTER_SID INT\n"
                + " ,ITEM_MASTER_SID INT\n"
                + ",CCP_DETAILS_SID        INT\n"
                + " ,PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + " ,PROJECTION_MASTER_SID INT)\n"
                + "  INSERT INTO #TEMP_CCP (\n"
                + " COMPANY_MASTER_SID\n"
                + " , CONTRACT_MASTER_SID\n"
                + " , ITEM_MASTER_SID\n"
                + ",CCP_DETAILS_SID \n"
                + " , PROJECTION_DETAILS_SID\n"
                + " , PROJECTION_MASTER_SID\n"
                + " )\n"
                + " SELECT distinct C.COMPANY_MASTER_SID\n"
                + "  , C.CONTRACT_MASTER_SID\n"
                + "  , C.ITEM_MASTER_SID\n"
                + ", PD.CCP_DETAILS_SID \n"
                + "  , PD.PROJECTION_DETAILS_SID\n"
                + "  , PD.PROJECTION_MASTER_SID\n"
                + " FROM CCP_DETAILS C\n"
                + " JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "WHERE  PD.PROJECTION_MASTER_SID = " + pvsDTO.getProjectionId() + "\n"
                + "AND EXISTS (SELECT 1\n"
                + "                   FROM   @CCP CCP\n"
                + "                   WHERE  CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + " DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + " INSERT INTO @ITEMID\n"
                + " SELECT IM.ITEM_MASTER_SID\n"
                + " FROM ITEM_MASTER IM\n"
                + " WHERE EXISTS (\n"
                + "  SELECT 1\n"
                + " FROM #TEMP_CCP A\n"
                + " WHERE PROJECTION_MASTER_SID = " + pvsDTO.getProjectionId() + "\n"
                + " AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + " )"
                + "DECLARE @COGS_ITEM TABLE\n"
                + "  (\n"
                + "     ITEM_MASTER_SID   INT,\n"
                + "     PERIOD_SID        INT,\n"
                + "     PRICING_QUALIFIER VARCHAR(50),\n"
                + "     ITEM_PRICE        NUMERIC(22, 6),\n"
                + "     PRIMARY KEY (ITEM_MASTER_SID, PERIOD_SID)\n"
                + "  )\n"
                + "\n"
                + "INSERT INTO @COGS_ITEM\n"
                + "            (ITEM_MASTER_SID,\n"
                + "             PERIOD_SID,\n"
                + "             PRICING_QUALIFIER,\n"
                + "             ITEM_PRICE)\n"
                + "SELECT ITEM_MASTER_SID,\n"
                + "       PERIOD_SID,\n"
                + "       PRICING_QUALIFIER,\n"
                + "       ITEM_PRICE\n"
                + "FROM   [dbo].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U"
                + "\n";
        return query;
    }

    public static String getTempRetrunsQuery() {

        String query = " DECLARE @COUNT INT\n"
                + " \n"
                + "IF OBJECT_ID('TEMPDB..#ITEM') IS NOT NULL\n"
                + "       DROP TABLE #ITEM\n"
                + " \n"
                + "CREATE TABLE #ITEM (\n"
                + "       ID INT IDENTITY(1, 1)\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       )\n"
                + " \n"
                + "INSERT INTO #ITEM (ITEM_MASTER_SID)\n"
                + "SELECT ITEM_MASTER_SID\n"
                + "FROM @ITEMID\n"
                + " \n"
                + "SET @COUNT = @@ROWCOUNT\n"
                + " \n"
                + "DECLARE @I INT = 1\n"
                + "DECLARE @ITEM INT\n"
                + " \n"
                + "IF Object_id('TEMPDB..#TEMP_RETURNS') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_RETURNS\n"
                + " \n"
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + "       )\n"
                + " \n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "       SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                     );\n"
                + " \n"
                + "       WITH ITEM_PROJ_DETAILS\n"
                + "       AS (\n"
                + "              SELECT ROW_NUMBER() OVER (\n"
                + "                           PARTITION BY ITEM_MASTER_SID ORDER BY LASTEST_DATE DESC\n"
                + "                           ) RN\n"
                + "                     , ITEM_MASTER_SID\n"
                + "                     , PM.PROJECTION_MASTER_SID\n"
                + "                     , RETURNS_DETAILS_SID\n"
                + "              FROM RETURNS_DETAILS RD\n"
                + "              INNER JOIN PROJECTION_MASTER PM\n"
                + "                     ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "              CROSS APPLY (\n"
                + "                     VALUES (MODIFIED_DATE)\n"
                + "                           , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "              )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "              )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                  AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_ACTUALS\n"
                + "       FULL JOIN (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                  AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_PROJ\n"
                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
                + " \n"
                + "       SET @I = @I + 1\n"
                + "END";

        return query;
    }

    public static String getTemp_CCPD_RetrunsQuery() {

        String query = "   IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "  DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD\n"
                + "  (\n"
                + "     COMPANY_MASTER_SID     INT,\n"
                + "     CONTRACT_MASTER_SID    INT,\n"
                + "     ITEM_MASTER_SID        INT,\n"
                + "     PROJECTION_DETAILS_SID INT,\n"
                + "     PROJECTION_MASTER_SID  INT\n"
                + "  )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCPD\n"
                + "            (COMPANY_MASTER_SID,\n"
                + "             CONTRACT_MASTER_SID,\n"
                + "             ITEM_MASTER_SID,\n"
                + "             PROJECTION_DETAILS_SID,\n"
                + "             PROJECTION_MASTER_SID)\n"
                + "SELECT    DISTINCT COMPANY_MASTER_SID,\n"
                + "                   CONTRACT_MASTER_SID,\n"
                + "                   ITEM_MASTER_SID,\n"
                + "                   PROJECTION_DETAILS_SID,\n"
                + "                   PROJECTION_MASTER_SID\n"
                + "FROM      (SELECT     COMPANY_MASTER_SID,\n"
                + "                      T_CCP.CONTRACT_MASTER_SID,\n"
                + "                      T_CCP.ITEM_MASTER_SID,\n"
                + "                      PROJECTION_DETAILS_SID,\n"
                + "                      PROJECTION_MASTER_SID,\n"
                + "                      RS_ID,\n"
                + "                      RS_TYPE\n"
                + "           FROM       #TEMP_CCP T_CCP\n"
                + "           INNER JOIN RS_CONTRACT RS ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "           INNER JOIN RS_CONTRACT_DETAILS RSC ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                                             AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "           WHERE      EXISTS (SELECT     1\n"
                + "                              FROM       CFP_CONTRACT CF\n"
                + "                              INNER JOIN CFP_CONTRACT_DETAILS CFD ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                                                 AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                                                 AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                                                 AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID)) R\n"
                + "JOIN HELPER_TABLE HT ON R.RS_TYPE = HT.HELPER_TABLE_SID\n"
                + "WHERE     HT.DESCRIPTION = 'Returns'\n"
                + "\n"
                + "\n"
                + "DECLARE @ITEM_ID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEM_ID\n"
                + "SELECT DISTINCT ITEM_MASTER_SID\n"
                + "FROM   #TEMP_CCPD A\n";

        return query;
    }

    public static List<String> getCommonSelectWhereOrderGroupByNetSalesClause(String table1, String table2, int freq) {
        List<String> list = new ArrayList<String>();
        String orderBy = " YEARS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + "on" + " " + table1 + ".YEARS=" + table2 + ".YEARS";

        selectClause += "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS)";
        selectClause += " as PERIODS, \n";
        if (freq != 1) {
            finalWhereCond += " and " + table1 + ".PERIODS=" + table2 + ".PERIODS";
            groupBy += ", " + table1 + ".PERIODS";

        }
        orderBy += ", PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public List<String> getBrand() {
        List<String> brandList = new ArrayList<String>();
        String query = "select distinct BRAND_NAME  from dbo.BRAND_MASTER WHERE BRAND_NAME!='null'";
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object brandName : list) {
            brandList.add(String.valueOf(brandName));
        }
        return brandList;

    }

    public static ComboBox getIdentifierType(final ComboBox itemIdentifierType, SessionDTO session) {
        itemIdentifierType.removeAllItems();
        itemIdentifierType.addItem(Constants.SELECT_ONE);
        itemIdentifierType.setNullSelectionAllowed(true);
        itemIdentifierType.setNullSelectionItemId(Constants.SELECT_ONE);
        String query = SQlUtil.getQuery("load-item-qualifier");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());

        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null && !list.isEmpty()) {
            for (Object identifier : list) {
                final Object[] obj = (Object[]) identifier;
                itemIdentifierType.addItem(obj[0]);
                itemIdentifierType.setItemCaption(obj[0], String.valueOf(obj[1]));
            }
        }
        return itemIdentifierType;
    }

    /**
     * isPpaAnd Returns
     *
     * @param projSelDTO
     * @return - Boolean
     */
    public static Boolean isReturns(final boolean isTotal, final ProjectionSelectionDTO projSelDTO) {
        List inputList = new ArrayList();
        String tempGroupFilter = projSelDTO.getGroupFilter();
        projSelDTO.setGroupFilter(StringUtils.EMPTY);
        String ccpQuery = getCCPQuery(projSelDTO, Boolean.FALSE);
        inputList.add(projSelDTO.getProjectionId());
        String returnsQuery = PPAQuerys.getAppQuery(inputList, isTotal ? "getIsRS_RETURN_Total" : "getIsRS_RETURN_Detail");
        String finalQuery = "DECLARE @COUNT BIT \n" + (isTotal ? returnsQuery : (ccpQuery + returnsQuery));
        List<Object[]> isPpaList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        Boolean isCount = Boolean.FALSE;
        if (QueryUtils.getCount(isPpaList) != 0) {
            isCount = Boolean.TRUE;
        }
        projSelDTO.setGroupFilter(tempGroupFilter);
        return isCount;
    }

    /**
     * isPpaAnd Returns
     *
     * @param projSelDTO
     * @return - Boolean
     */
    public static Boolean isPPA(final boolean isTotal, final ProjectionSelectionDTO projSelDTO) {
        List inputList = new ArrayList();
        inputList.add(projSelDTO.getProjectionId());
        String tempGroupFilter = projSelDTO.getGroupFilter();
        projSelDTO.setGroupFilter(StringUtils.EMPTY);
        String ccpQuery = getCCPQuery(projSelDTO, Boolean.FALSE);
        String ppaQuery = PPAQuerys.getAppQuery(inputList, isTotal ? "getIsRS_PPA_Total" : "getIsRS_PPA_Detail");
        String finalQuery = (isTotal ? ppaQuery : (ccpQuery + ppaQuery));
        List<Object[]> isReturnsList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
        Boolean isCount = Boolean.FALSE;
        if (QueryUtils.getCount(isReturnsList) != 0) {
            isCount = Boolean.TRUE;
        }
        projSelDTO.setGroupFilter(tempGroupFilter);
        return isCount;
    }

    /**
     * Temp table create for display RS information
     *
     * @param projSelDTO
     * @return
     */
    public static String tempRSTable(PVSelectionDTO projSelDTO) {
        String query = StringUtils.EMPTY;
        query = "IF OBJECT_ID('TEMPDB.DBO.#rs_details', 'U') IS NOT NULL\n"
                + "     DROP TABLE #rs_details;\n"
                + "\n"
                + "CREATE TABLE #rs_details (\n"
                + "     c_YEARS INT\n"
                + "     , c_period INT\n"
                + "     , c_DISCOUNTS VARCHAR(100)\n"
                + "     , c_PROJECTION_SALES NUMERIC(22, 6)\n";

        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            query += ",p" + i + "_PROJECTION_SALES NUMERIC(22, 6)\n";
        }
        query += ")\n"
                + "\n"
                + "INSERT INTO #rs_details (\n"
                + "     c_YEARS\n"
                + "     , c_period\n"
                + "     , c_DISCOUNTS\n"
                + "     , c_PROJECTION_SALES\n";
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            query += ",p" + i + "_PROJECTION_SALES\n";
        }
        query += " )";
        return query;
    }

    /**
     * Method for displaying temp table values.
     *
     * @param projSelDTO
     * @return
     */
    public static String rsTempTableSelect(PVSelectionDTO projSelDTO) {
        String query = "SELECT *\n"
                + "FROM #rs_details\n"
                + "ORDER BY  c_DISCOUNTS,c_YEARS\n"
                + "     , c_period";
        return query;
    }

    /**
     * Temp table create for display RS information Pivot
     *
     * @param projSelDTO
     * @return
     */
    public static String tempRSTablePivot(PVSelectionDTO projSelDTO) {
        String query = StringUtils.EMPTY;
        query = "IF OBJECT_ID('TEMPDB.DBO.#rs_details', 'U') IS NOT NULL\n"
                + "     DROP TABLE #rs_details;\n"
                + "\n"
                + "CREATE TABLE #rs_details (\n"
                + "     YEARS INT\n"
                + "     , PERIODS INT\n"
                + "     , DISCOUNTS VARCHAR(100)\n"
                + "     , C_DOL_VAL NUMERIC(22,6)\n"
                + "     , C_DOL_VAR NUMERIC(22,6)\n"
                + "     , C_DOL_PER NUMERIC(22,6)\n"
                + "     , C_RATE_VAL NUMERIC(22,6)\n"
                + "     , C_RATE_VAR NUMERIC(22,6)\n"
                + "     , C_RATE_PER NUMERIC(22,6)\n"
                + "     , C_RPU_VAL NUMERIC(22,6)\n"
                + "     , C_RPU_VAR NUMERIC(22,6)\n"
                + "     , C_RPU_PER NUMERIC(22,6)\n";

        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            query += ",P" + i + "_DOL_VALUE NUMERIC(22, 6)\n";
            query += ",P" + i + "_DOL_VAR NUMERIC(22, 6)\n";
            query += ",P" + i + "_DOL_PER NUMERIC(22, 6)\n";
            query += ",P" + i + "_RATE_VAL NUMERIC(22, 6)\n";
            query += ",P" + i + "_RATE_VAR NUMERIC(22, 6)\n";
            query += ",P" + i + "_RATE_PER NUMERIC(22, 6)\n";
            query += ",P" + i + "_RPU_VAL NUMERIC(22, 6)\n";
            query += ",P" + i + "_RPU_VAR NUMERIC(22, 6)\n";
            query += ",P" + i + "_RPU_PER NUMERIC(22, 6)\n";
        }
        query += ")\n"
                + "\n"
                + "INSERT INTO #rs_details (\n"
                + "     YEARS \n"
                + "     , PERIODS \n"
                + "     , DISCOUNTS \n"
                + "     , C_DOL_VAL \n"
                + "     , C_DOL_VAR \n"
                + "     , C_DOL_PER \n"
                + "     , C_RATE_VAL \n"
                + "     , C_RATE_VAR \n"
                + "     , C_RATE_PER \n"
                + "     , C_RPU_VAL \n"
                + "     , C_RPU_VAR \n"
                + "     , C_RPU_PER \n";
        for (int i = 0; i < projSelDTO.getProjIdList().size(); i++) {
            query += ",P" + i + "_DOL_VALUE \n";
            query += ",P" + i + "_DOL_VAR \n";
            query += ",P" + i + "_DOL_PER \n";
            query += ",P" + i + "_RATE_VAL \n";
            query += ",P" + i + "_RATE_VAR \n";
            query += ",P" + i + "_RATE_PER \n";
            query += ",P" + i + "_RPU_VAL \n";
            query += ",P" + i + "_RPU_VAR \n";
            query += ",P" + i + "_RPU_PER \n";
        }
        query += " )";
        return query;
    }

    /**
     * Method for displaying temp table values Pivot.
     *
     * @param projSelDTO
     * @return
     */
    public static String rsTempTableSelectPivot(PVSelectionDTO projSelDTO) {
        return "SELECT *\n"
                + "FROM #rs_details\n"
                + "ORDER BY DISCOUNTS\n"
                + "	, YEARS\n"
                + "	, periods";
    }

    public static List getPPADiscountNameList(PVSelectionDTO selection) {
        String query = "SELECT";
        boolean flag = false;
        if ("Program Category".equals(selection.getDiscountLevel())) {
            query += " CASE WHEN COUNT(DISTINCT RS.REBATE_PROGRAM_TYPE) >0 THEN 'Price Protection' ELSE NULL END ";
            flag = true;
        } else if ("Program".equals(selection.getDiscountLevel())) {
            query += " DISTINCT RS.RS_NAME ";
        }
        query += " FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n"
                + "JOIN RS_MODEL RS ON RS.RS_MODEL_SID = TEMP.RS_MODEL_SID \n"
                + "WHERE USER_ID = " + selection.getUserId() + " AND SESSION_ID = " + selection.getSessionId() + " AND PD.PROJECTION_MASTER_SID=" + selection.getProjectionId();
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        Object str = list.get(0);
        if (flag && "null".equals(String.valueOf(str))) {
            return null;
        } else {
            return list;
        }

    }

    public static List getPPADiscountNameListPR(ProjectionSelectionDTO selection, Boolean isHeader) {
        String query = "SELECT";
        if (isHeader) {
            query += " DISTINCT RS.RS_NAME ";
        } else {
            query += " COUNT(DISTINCT RS.RS_NAME)";
        }
        query += " FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n"
                + "JOIN RS_MODEL RS ON RS.RS_MODEL_SID = TEMP.RS_MODEL_SID \n"
                + "WHERE USER_ID = " + selection.getUserId() + " AND SESSION_ID = " + selection.getSessionId() + " AND PD.PROJECTION_MASTER_SID=" + selection.getProjectionId();
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

}
