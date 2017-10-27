
package com.stpl.app.cff.logic;

import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.SalesProjectionDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import static com.stpl.app.cff.util.ConstantsUtil.SELECT_ONE;
import com.stpl.app.cff.util.NotificationUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
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

public class CommonLogic {

    private static final CommonDAO commonDao = new CommonDAOImpl();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Constant LOGGER.
     */
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CommonLogic.class);
    public static final String ALL_GROUP = "All PPA Group";

    /**
     * Get Customer Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getCustomerHierarchy(int projectionId, final int levelNo) {
        return getHierarchy(projectionId, "C", levelNo);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchy(int projectionId, final int levelNo) {
        return getHierarchy(projectionId, "P", levelNo);
    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchy(int projectionId, String hierarchyIndicator, final int levelNo) {
        List<Leveldto> listValue = new ArrayList<>();
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
            LOGGER.error(ex);
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
        dto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.TWO]));
        dto.setLevel(String.valueOf(obj[NumericConstants.THREE]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.FOUR]));
            dto.setParentNode(String.valueOf(obj[NumericConstants.FIVE]));
            dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
        }
        return dto;
    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<CffCustomViewMaster> getCustomViewList(int projectionId) {
        List<CffCustomViewMaster> list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CffCustomViewMaster.class);
            query.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public static int customDdlbOptionChange(ComboBox customDdlb, Button editBtn, ComboBox level) {
        editBtn.setEnabled(false);
        level.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!"null".equals(value) && !SELECT_ONE.equals(value)) {
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
        if (!"null".equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.valueOf(value);
            editBtn.setEnabled(true);
            return selectedId;
        }
        return 0;
    }

    public static boolean editButtonValidation(ComboBox customDdlb, List<CffCustomViewMaster> customViewList) {
        Object value = customDdlb.getValue();
        String userId1 = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID);
        int userId = 0;
        try {
            userId = Integer.valueOf(userId1);
        } catch (NumberFormatException ex) {
            LOGGER.error(ex);
        }
        if (userId != 0) {
            int selectedId = Integer.valueOf(String.valueOf(value));
            for (CffCustomViewMaster custom : customViewList) {
                if (custom.getCffCustomViewMasterSid() == selectedId && custom.getCreatedBy() == userId) {
                        return true;
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
                    CffCustomViewMaster customViewMaster = CffCustomViewMasterLocalServiceUtil.createCffCustomViewMaster(0);
                    customViewMaster.setCffMasterSid(session.getProjectionId());
                    customViewMaster.setViewName(viewName);
                    customViewMaster.setCreatedBy(userId);
                    customViewMaster.setCreatedDate(date);
                    customViewMaster = commonDao.addCustomView(customViewMaster);
                    if (customViewMaster != null) {
                        customId = customViewMaster.getCffCustomViewMasterSid();
                        customViewDetailsSaveLogic(customId, levelList);
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }

            } else {

                try {
                    CffCustomViewMaster customViewMaster = getCustomView(customId);
                    customViewMaster.setCffMasterSid(session.getProjectionId());
                    customViewMaster.setViewName(viewName);
                    customViewMaster.setModifiedBy(userId);
                    customViewMaster.setModifiedDate(date);
                    customViewMaster = commonDao.updateCustomView(customViewMaster);
                    if (customViewMaster != null) {
                        customId = customViewMaster.getCffCustomViewMasterSid();
                        List<CffCustomViewDetails> detailsList = getCustomViewDetails(customId);
                        for (CffCustomViewDetails customDetails : detailsList) {
                            try {
                                commonDao.deleteCustomViewDetails(customDetails);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }

                        customViewDetailsSaveLogic(customId, levelList);
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            }
        }
        return customId;
    }

    public static int cffCustomViewSaveLogic(SessionDTO session, int customId, String viewName, List levelList) {
        String userId1 = session.getUserId();
        int userId = 0;
        if (CommonUtils.isInteger(userId1)) {
            userId = Integer.valueOf(userId1);
        }
        CFFDAO dao = CFFDAOImpl.getInstance();
        if (userId != 0) {
            if (customId == 0) {
                try {
                    List list = new ArrayList<>();
                    list.add(session.getProjectionId());
                    list.add(viewName);
                    list.add(userId);
                    String sql = CommonQueryUtils.getAppQuery(list, "insertCustomViewMaster");
                    sql += cffCustomViewDetailsSaveLogic(customId, levelList, false);
                    dao.executeUpdateQuery(sql);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else {
                try {
                    List list = new ArrayList<>();
                    list.add(session.getProjectionId());
                    list.add(viewName);
                    list.add(userId);
                    CommonQueryUtils.updateAppData(list, "updateCustomViewMaster");
                    List deleteInpList = new ArrayList<>();
                    deleteInpList.add(customId);
                    CommonQueryUtils.updateAppData(deleteInpList, "deleteCustomViewDetails");
                    cffCustomViewDetailsSaveLogic(customId, levelList, true);
                } catch (Exception ex) {
                    LOGGER.error(ex);
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
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CffCustomViewMaster.class);
            query.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
            query.add(RestrictionsFactoryUtil.eq("viewName", viewName));
            if (customId != 0) {
                query.add(RestrictionsFactoryUtil.ne("cffCustomViewMasterSid", customId));
            }

            list = commonDao.getCustomViewList(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public static boolean customViewDetailsSaveLogic(int customId, List levelList) throws SystemException  {
        for (Object ob : levelList) {
            Leveldto dto = (Leveldto) ob;
            CffCustomViewDetails customViewDetails = CffCustomViewDetailsLocalServiceUtil.createCffCustomViewDetails(0);
            customViewDetails.setCffCustomViewMasterSid(customId);
            customViewDetails.setHierarchyId(dto.getHierarchyId());
            customViewDetails.setHierarchyIndicator(dto.getHierarchyIndicator());
            customViewDetails.setLevelNo(dto.getTreeLevelNo());
            commonDao.addCustomViewDetails(customViewDetails);
        }
        return true;
    }

    public static String cffCustomViewDetailsSaveLogic(int customId, List levelList, boolean isUpdate) {
        StringBuilder declareSql = new StringBuilder("DECLARE  @identity_val VARCHAR (50)='" + customId + "'");
        StringBuilder sql = new StringBuilder("insert into CFF_CUSTOM_VIEW_DETAILS(CFF_CUSTOM_VIEW_MASTER_SID,HIERARCHY_ID,HIERARCHY_INDICATOR,LEVEL_NO) values ");
        String quotes = "'";
        String comma = ",";
        for (Object ob : levelList) {
            Leveldto dto = (Leveldto) ob;
            sql.append("(@identity_val,").append(quotes).append(dto.getHierarchyId()).append(quotes).append(comma).append(quotes)
                    .append(dto.getHierarchyIndicator()).append(quotes).append(comma).append(quotes).append(dto.getTreeLevelNo()).append(quotes).append("),");
        }
        String query = sql.toString();
        if (isUpdate) {
            query += declareSql.toString();
        }
        String finalQuery = query.substring(0, query.lastIndexOf(','));
        return finalQuery;
    }

    /**
     * Get Custom View Details
     *
     * @param customId
     * @return list
     */
    public static List<CffCustomViewDetails> getCustomViewDetails(int customId) {
        List<CffCustomViewDetails> list = null;
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(CffCustomViewDetails.class);
            query.add(RestrictionsFactoryUtil.eq("cffCustomViewMasterSid", customId));
            query.addOrder(OrderFactoryUtil.asc(StringConstantsUtil.LEVEL_NO));
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
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
        List<Leveldto> listValue = new ArrayList<>();
        if (customId != 0) {
            List<CffCustomViewDetails> customDetailsList = getCustomViewDetails(customId);
            for (CffCustomViewDetails ob : customDetailsList) {
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

    public static CffCustomViewMaster getCustomView(int customViewMasterSid) {
        CffCustomViewMaster cvm = null;
        if (customViewMasterSid != 0) {
            try {
                cvm = commonDao.getCustomView(customViewMasterSid);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            }
        }
        return cvm;
    }

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {
        String finalQuery = "";
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId, sessionId, relationshipBuilderSid);
        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {
        String customSql = "";
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

        customSql = mainSelect + StringConstantsUtil.FROM_SMALL + selectClause1 + StringConstantsUtil.FROM_SMALL + joinQuery1 + " " + selectClause2 + StringConstantsUtil.FROM_SMALL + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo) {
        String selectClause = "select distinct RLD.LEVEL_NO,  "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as  HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = StringConstantsUtil.FROM_SMALL + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.CFF_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo;
        return customSql;
    }

    public static List<Object> getLevelNoAndHierarchyNo(Object value) {
        LOGGER.debug("Inside getLevelNoAndHierarchyNo");
        List<Object> levelHierarchy = new ArrayList<>();
        String selectedId = "0";
        if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
                selectedId = String.valueOf(value);
        }
        int levelNo = -1;
        String hierarchyNo = "";
        int j = selectedId.indexOf('~');
        if (j > 0) {
            levelNo = Integer.valueOf(selectedId.substring(0, j));
        }
        if (selectedId.length() > (j + 1)) {
            hierarchyNo = selectedId.substring(j + 1, selectedId.length());
        }
        levelHierarchy.add(Integer.valueOf(levelNo));
        levelHierarchy.add(hierarchyNo);
        LOGGER.debug("Ending getLevelNoAndHierarchyNo");
        return levelHierarchy;
    }

    public static List<Object> getParentLevelNoAndHierarchyNo(int levelNo, String hierarchyNos) {
        List<Object> levelHierarchy = new ArrayList<>();
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
        LOGGER.debug("getAllLevelNoAndHierarchyNo started");
        List<List<Object>> allLevelHierarchy = new ArrayList<>();
        List<Object> levelHierarchy = getLevelNoAndHierarchyNo(value);

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        String hierarchyNo1 = String.valueOf(levelHierarchy.get(1));
        List<String> nos = getAllHierarchyNo(hierarchyNo1);
        Collections.reverse(nos);
        for (String hierarchyNo : nos) {
            levelHierarchy = new ArrayList<>();
            levelHierarchy.add(Integer.valueOf(levelNo));
            levelHierarchy.add(hierarchyNo);
            allLevelHierarchy.add(0, levelHierarchy);
            levelNo--;
        }
        LOGGER.debug("getAllLevelNoAndHierarchyNo ended");
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
            projectionListFrom.add(ProjectionFactoryUtil.property("levelName"));
            projectionListFrom.add(ProjectionFactoryUtil.property(StringConstantsUtil.LEVEL_NO));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = commonDao.getRelationshipLevels(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
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
        String viewtable = "";
        if ("C".equals(hierarchyIndicator)) {
            viewtable = "CFF_CUST_HIERARCHY";
        } else if ("P".equals(hierarchyIndicator)) {
            viewtable = "CFF_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public static List<Leveldto> getAllHierarchyLevels() {
        List<Leveldto> newLevelList = new ArrayList<>();
            String query = "";
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    newLevelList.add(dto);
                }
            }
        return newLevelList;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {
        List<Leveldto> newLevelList = new ArrayList<>();
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
            LOGGER.error(ex);
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
        LOGGER.debug("Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
        List<Object[]> objectList = new ArrayList<>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                StringBuilder procedureToCall = new StringBuilder("{call ");
                procedureToCall.append(procedureName);
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall.append(ConstantsUtil.OPEN_PARANTHESIS);
                    }
                    procedureToCall.append("?,");
                    if (i == noOfArgs - 1) {
                        procedureToCall.append(ConstantsUtil.CLOSE_PARANTHESIS);
                    }
                }
                procedureToCall.replace(procedureToCall.lastIndexOf(ConstantsUtil.COMMA), procedureToCall.lastIndexOf(ConstantsUtil.COMMA) + 1, StringUtils.EMPTY);
                procedureToCall.append("}");
                statement = connection.prepareCall(procedureToCall.toString());
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.debug(i + " -- " + orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);

            }
        } catch (Exception ex) {
           
            LOGGER.error(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                connection.close();

            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return objectList;
    }
    public static  void callProcedureUpdate(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
       
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                StringBuilder procedureToCall = new StringBuilder("{call ");
                procedureToCall.append(procedureName);
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall.append(ConstantsUtil.OPEN_PARANTHESIS);
                    }
                    procedureToCall.append("?,");
                    if (i == noOfArgs - 1) {
                        procedureToCall.append(ConstantsUtil.CLOSE_PARANTHESIS);
                    }
                }
                procedureToCall.replace(procedureToCall.lastIndexOf(ConstantsUtil.COMMA), procedureToCall.lastIndexOf(ConstantsUtil.COMMA) + 1, StringUtils.EMPTY);
                procedureToCall.append("}");
                statement = connection.prepareCall(procedureToCall.toString());
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.debug(i + " -- " + orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                statement.executeUpdate();

                

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                connection.close();

            } catch (Exception ex) {
                LOGGER.error(ex);
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
        List<Object[]> objList = new ArrayList<>();

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
            LOGGER.error(ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
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
    public static List<List<String>> getDiscountNoList(int projectionId, List<String> discounts, boolean isProgram) {
        List<List<String>> discountlist = new ArrayList<>();
        List<String> discountNolist = new ArrayList<>();
        List<String> discountNamelist = new ArrayList<>();
        List<Object> list = null;
        if (discounts != null && !discounts.isEmpty()) {
            String selectedDiscounts = CollectionToString(discounts, true);
            String customSql = "select Distinct RS.RS_MODEL_SID as DISCOUNT_ID,RS.RS_NAME as DISCOUNT_NAME from RS_MODEL RS, PROJECTION_DETAILS D,"
                    + " ST_NM_DISCOUNT_PROJ_MASTER DM "
                    + "where DM.PROJECTION_DETAILS_SID = D.PROJECTION_DETAILS_SID "
                    + " and D.PROJECTION_MASTER_SID = " + projectionId;
            if (isProgram) {
                customSql += " and RS.RS_NAME in (" + selectedDiscounts + ")";
            } else {
                customSql += " and DM.PRICE_GROUP_TYPE in (" + selectedDiscounts + ")";
            }

            customSql += " and DM.RS_MODEL_SID=RS.RS_MODEL_SID"
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
        List<Leveldto> listValue = new ArrayList<>();
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
            LOGGER.error(ex);
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
        dto.setRelationShipBuilderId(String.valueOf(obj[NumericConstants.FIVE]));
        dto.setHierarchyLevelDefnId(String.valueOf(obj[NumericConstants.FOUR]));
        if (isHierarchy) {
            dto.setTreeLevelNo(dto.getLevelNo());
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.TWO]));
            dto.setParentNode(String.valueOf(obj[NumericConstants.THREE]));
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
    public static void saveProjectionSelection(int projectionID, String screenName) {
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.SCREEN_NAME, screenName));
        try {
            NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
    public void saveProjectionSelectionMandatedDiscountProjection(Map map, int projectionID, String screenName) {
        List<MProjectionSelection> list = new ArrayList<>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.SCREEN_NAME, screenName));
        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                saveSelection(map, projectionID, screenName, "save", "M_PROJECTION_SELECTION");
            } else {
                saveSelection(map, projectionID, screenName, "update", "M_PROJECTION_SELECTION");
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
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
    public void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate, String tableName) {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();
        LOGGER.debug("saveOrUpdate >> " + saveOrUpdate);
        if ("save".equalsIgnoreCase(saveOrUpdate)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO ").append(tableName).append(" (CFF_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE ").append(tableName).append(" SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append("'");
                queryBuilder.append(" WHERE CFF_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        LOGGER.debug("save / update query >> " + queryBuilder);
        HelperTableLocalServiceUtil.executeUpdateQuery(queryBuilder.toString());
    }

    /**
     * Get projection selection
     *
     * @param projectionId
     * @param screenName
     * @return
     */
    public static Map<Object, Object> getCHProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property("fieldName"));
        projectionListFrom.add(ProjectionFactoryUtil.property("fieldValues"));
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
            LOGGER.error(ex);
        }
        return null;
    }

    public static Map<Object, Object> getNMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
         String query;
         query="SELECT FIELD_NAME, FIELD_VALUES FROM CFF_SELECTION WHERE CFF_MASTER_SID = " + projectionId + " AND SCREEN_NAME LIKE '" + screenName +  ("'");
       
        try {
            list= HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<List<String>> getPriceGroupTypeList(List<String> discountName) {
        List<List<String>> discountlist = new ArrayList<>();
        List<String> discountNolist = new ArrayList<>();
        List<String> priceGrouplist = new ArrayList<>();
        List list = null;
        if (discountName != null && !discountName.isEmpty()) {
        }

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                discountNolist.add(String.valueOf(obj[0]));
                priceGrouplist.add(String.valueOf(obj[1]));
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

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String where) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + StringConstantsUtil.DOT_YEARS;
        String selectClause = "Isnull (" + table1 + StringConstantsUtil.YEARS_DOT + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + where + " " + table1 + StringConstantsUtil.YEARS_DOT_EQUAL + table2 + StringConstantsUtil.DOT_YEARS;
        selectClause += "Isnull (" + table1 + StringConstantsUtil.PERIODS_DOT + table2 + StringConstantsUtil.PERIODS_DOT_CLOSE_BRACE;
        finalWhereCond += StringConstantsUtil.SMALL_AND + table1 + StringConstantsUtil.PERIODS_DOT_EQUAL + table2 + StringConstantsUtil.DOT_PERIODS;
        groupBy += ", " + table1 + StringConstantsUtil.DOT_PERIODS;
        selectClause += " as PERIODS , \n";

        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String table3, String where, boolean isTable3) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + StringConstantsUtil.DOT_YEARS;
        String selectClause = " Isnull(" + table1 + StringConstantsUtil.YEARS_DOT + table2 + ".YEARS)";
        String selectClause1 = " Isnull(" + table1 + StringConstantsUtil.PERIODS_DOT + table2 + StringConstantsUtil.PERIODS_DOT_CLOSE_BRACE;
        if (isTable3) {
            selectClause = "Isnull(Isnull(" + table1 + StringConstantsUtil.YEARS_DOT + table2 + ".YEARS), " + table3 + ".YEARS)";
            selectClause1 = "Isnull(Isnull(" + table1 + StringConstantsUtil.PERIODS_DOT + table2 + ".PERIODS), " + table3 + StringConstantsUtil.PERIODS_DOT_CLOSE_BRACE;
        }
        selectClause += " as YEARS, ";
        selectClause += selectClause1;
        selectClause += " as PERIODS, \n";
        String finalWhereCond = " " + where + " " + table1 + StringConstantsUtil.YEARS_DOT_EQUAL + table2 + ".YEARS ";
        finalWhereCond += StringConstantsUtil.SMALL_AND + table1 + StringConstantsUtil.PERIODS_DOT_EQUAL + table2 + ".PERIODS ";
        groupBy += ", " + table1 + StringConstantsUtil.DOT_PERIODS;

        String finalWhereCond1 = " " + where + " " + table1 + StringConstantsUtil.YEARS_DOT_EQUAL + table3 + ".YEARS ";
        finalWhereCond1 += StringConstantsUtil.SMALL_AND + table1 + StringConstantsUtil.PERIODS_DOT_EQUAL + table3 + StringConstantsUtil.DOT_PERIODS;
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(finalWhereCond1);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = StringConstantsUtil.SMALL_AND + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP) {
        String ccpWhereCond = StringConstantsUtil.SMALL_AND + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID \n"
                + StringConstantsUtil.SMALL_AND + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID \n";
        return ccpWhereCond;
    }

    public static String getPeriodRestrictionQuery(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        String periodFilter = "";
        if (!CommonUtils.isInteger(projSelDTO.getYear())) {
            periodFilter = " and PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate + "') \n";
        }

        return periodFilter;
    }

    public static String getPeriodRestrictionQueryforPV(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        String periodFilter = "";
        if (!CommonUtils.isInteger(projSelDTO.getYear())) {
            periodFilter = " PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate + "') \n";
        }

        return periodFilter;
    }

    public static String getCCPQueryForCff(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery =  QueryUtil.replaceTableNames(new CommonLogic().insertAvailableHierarchyNo(projSelDTO), projSelDTO.getSessionDTO().getCurrentTableNames());
        ccpQuery+= "SELECT * FROM #SELECTED_HIERARCHY_NO";
        return ccpQuery;
    }
    
    
    
    public static String getCCPTempTableQuery() {
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
   
    public static List<String> getAllHierarchyNo(String hierarchyNo) {
        LOGGER.debug("getAllHierarchyNo started");
        List<String> allLevelHierarchy = new ArrayList<>();
        String extraDot = "";
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
        LOGGER.debug("getAllHierarchyNo ended");
        return allLevelHierarchy;
    }

    public static String getParentHierarchyNo(String hierarchyNos) {
        int len = hierarchyNos.length();
        String extraDot = "";
        if (hierarchyNos.endsWith(".")) {
            extraDot = ".";
        }
        String hierarchyNo = hierarchyNos.substring(0, len - 1);
        int lin = hierarchyNo.lastIndexOf(".");
        if (lin > 0) {
            hierarchyNo = hierarchyNo.substring(0, lin) + extraDot;
        } else {
            hierarchyNo = "";
        }
        return hierarchyNo;
    }

    public static HorizontalLayout getResponsiveControls(HorizontalLayout tempLayout) {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName(Constants.RESPONSIVE_PAGED_TABLE);

        HorizontalLayout pageSize = (HorizontalLayout) tempLayout.getComponent(0);
        HorizontalLayout pageManagement = (HorizontalLayout) tempLayout.getComponent(1);

        CssLayout cssLayout = new CssLayout();
        cssLayout.setSizeFull();
        cssLayout.addComponent(pageSize.getComponent(0));
        cssLayout.addComponent(pageSize.getComponent(0));
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }


    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId) {
        String query = "";
        if (!userGroup.isEmpty() && !userGroup.equals("empty")) {
            query = "JOIN ST_NM_SALES_PROJECTION_MASTER SPMG ON SPMG.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  SPMG.USER_GROUP ='" + userGroup + "'";
            query += getUserSessionQueryCondition(userId, sessionId, "SPMG");
        }
        return query;
    }

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String query = "";
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith("All")) {
                if (userGroup.contains("Discount")) {
                    userGroup = " like '%' ";
                    query = getGroupFilterDiscountQuery(userGroup, userId, sessionId, isPrior);
                } else if (userGroup.contains("PPA")) {
                    userGroup = " like '%' ";
                    query = getGroupFilterPPAQuery(userGroup, userId, sessionId, isPrior);
                }
            } else if (userGroup.startsWith(StringConstantsUtil.DISCOUNT_HYPEN)) {
                userGroup = " = '" + userGroup.replace(StringConstantsUtil.DISCOUNT_HYPEN, "") + "' ";
                query = getGroupFilterDiscountQuery(userGroup, userId, sessionId, isPrior);
            } else if (userGroup.startsWith("PPA-")) {
                userGroup = " = '" + userGroup.replace("PPA-", "") + "' ";
                query = getGroupFilterPPAQuery(userGroup, userId, sessionId, isPrior);
            } else if (userGroup.startsWith(StringConstantsUtil.SALES_HYPEN)) {
                userGroup = " = '" + userGroup.replace(StringConstantsUtil.SALES_HYPEN, "") + "' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
            }
        }
        return query;
    }

    public static String getGroupFilterDiscountQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = "";
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
        String tableIndicator = "";
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = " JOIN " + tableIndicator + "NM_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP " + userGroup;
        if (!isPrior) {
            query += getUserSessionQueryCondition(userId, sessionId, "P");
        }
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = "";
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup;
        if (!isPrior) {
            query += getUserSessionQueryCondition(userId, sessionId, "S");
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
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_DISCOUNT_PROJ_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(StringConstantsUtil.DISCOUNT_HYPEN + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return groupList;
    }

    public static List<String> getPPAGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_PPA_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add("PPA-" + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return groupList;
    }

    public static List<String> getSalesGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_SALES_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(StringConstantsUtil.SALES_HYPEN + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return groupList;
    }

    public static List<String> getAllGroup(int projectionId, int userId, int sessionId, boolean isPPA) {
        List<String> groupList = new ArrayList<>();
        groupList.add("All Sales Group");
        groupList.add("All Discount Group");
        if (isPPA) {
            groupList.add("All PPA Group");
        }
        groupList.addAll(getSalesGroup(projectionId, sessionId, userId));
        groupList.addAll(getDiscountGroup(projectionId, sessionId, userId));
        return groupList;
    }

    public static List<String> getAllPPAGroup(int projectionId, int userId, int sessionId) {
        List<String> groupList = new ArrayList<>();
        groupList.add(ALL_GROUP);
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
            LOGGER.error(ex);
        }

        return levelNo;
    }

    public static List<String> getSalesGroupDDLB(int projectionId, int userId, int sessionId) {
        List<String> groupList = new ArrayList<>();
        groupList.addAll(getSalesGroup(projectionId, sessionId, userId));
        return groupList;
    }

    public static List<Integer> getPageNumber() {
        List<Integer> pagelength = new ArrayList<>();
        pagelength.add(NumericConstants.TEN);
        pagelength.add(NumericConstants.FIFTEEN);
        pagelength.add(NumericConstants.TWENTY);
        pagelength.add(NumericConstants.TWENTY_FIVE);
        pagelength.add(NumericConstants.FIFTY);
        pagelength.add(NumericConstants.HUNDRED);
        return pagelength;
    }

    /**
     * Get Customer Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getCustomerHierarchy(int projectionId, final int levelNo, final Object rbID) {
        return getHierarchy(projectionId, "C", levelNo, rbID);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchy(int projectionId, final int levelNo, final Object rbID) {
        return getHierarchy(projectionId, "P", levelNo, rbID);
    }

    /**
     * Get projection selection
     *
     * @param projectionId
     * @param screenName
     * @return
     */
    public static Map<Object, Object> getMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(MProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property("fieldName"));
        projectionListFrom.add(ProjectionFactoryUtil.property("fieldValues"));
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
            LOGGER.error(ex);
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
        List<Leveldto> listValue = new ArrayList<>();
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
            LOGGER.error(ex);
        }
        return listValue;
    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo, final Object rbID) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as  TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = StringConstantsUtil.FROM_SMALL + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as  RLD "
                + "where HC.PROJECTION_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID= RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo + " AND RLD.RELATIONSHIP_BUILDER_SID=" + rbID + ";";
        return customSql;
    }

    public static String getIndicator(int levelNo, int viewName) {
        List<CustomViewDetails> list = null;
        String indicator = "";
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CUSTOM_VIEW_MASTER_SID, viewName));
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LEVEL_NO, levelNo));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        if (list != null && !list.isEmpty()) {
            for (CustomViewDetails customViewDetails : list) {
                indicator = customViewDetails.getHierarchyIndicator();
                break;
            }
        }
        return indicator;
    }

    public static int getIndicatorCount(int viewName) {
        List<CustomViewDetails> list = null;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CUSTOM_VIEW_MASTER_SID, viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list.size();
    }


    public static String getLevelNo(int levelNo) {
        String str = "";
        if (levelNo != 0) {
            str = "AND RLD1.LEVEL_NO = " + levelNo;
        }
        return str;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId) {
        List<Leveldto> newLevelList = new ArrayList<>();
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
            LOGGER.error(ex);
        }
        return newLevelList;
    }

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId) {
        String finalQuery = "";
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId, sessionId);
        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo, String userGroup, int userId, int sessionId) {
        String customSql = "";
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

        customSql = mainSelect + StringConstantsUtil.FROM_SMALL + selectClause1 + StringConstantsUtil.FROM_SMALL + joinQuery1 + " " + selectClause2 + StringConstantsUtil.FROM_SMALL + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public static String getGroupFilterQueryMandated(String userGroup, int userId, int sessionId) {
        String query = "";
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith("All")) {
                if (userGroup.contains("Discount")) {
                    userGroup = "  like '%' ";
                    query = getGroupFilterDiscountQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains("PPA")) {
                    userGroup = " like  '%' ";
                    query = getGroupFilterPPAQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains("Sales")) {
                    userGroup = " like  '%' ";
                    query = getGroupFilterSalesQuery(userGroup, userId, sessionId);
                }
            } else if (userGroup.startsWith(StringConstantsUtil.DISCOUNT_HYPEN)) {
                userGroup = " = '" + userGroup.replace(StringConstantsUtil.DISCOUNT_HYPEN, "") + "' ";
                query = getGroupFilterDiscountQuery(userGroup, userId, sessionId);
            } else if (userGroup.startsWith("PPA-")) {
                userGroup = " = '" + userGroup.replace("PPA-", "") + "' ";
                query = getGroupFilterPPAQuery(userGroup, userId, sessionId);
            } else if (userGroup.startsWith(StringConstantsUtil.SALES_HYPEN)) {
                userGroup = " = '" + userGroup.replace(StringConstantsUtil.SALES_HYPEN, "") + "' ";
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
        String query = "JOIN ST_M_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, "P");
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId) {
        String query = "JOIN ST_M_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, "S");
        return query;
    }

    public static String getUserSessionQueryConditionForPR(int userId, int sessionId, String table) {
        String user = " " + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String projectionDetails, String CCP) {
        String ccpWhereCond = StringConstantsUtil.SMALL_AND + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
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

    /**
     * Saves the projection selection in all the screens.
     *
     * @param map
     * @param tabName
     * @param projectionSelectionDTO
     * @throws PortalException
     * @throws Exception
     */

    public static void saveProjectionSelection(final Map<String, String> map, final String tabName, final int projectionID) throws PortalException, SystemException {

        String tableName ="CFF_SELECTION";
        StringBuilder query = new StringBuilder();
        query.append("DELETE\n" + "FROM\n" + " ").append(tableName).append("\n" + "WHERE\n" + "CFF_MASTER_SID = ").append(projectionID).append("\n" + "AND SCREEN_NAME LIKE '").append(tabName).append("';\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            query.append("INSERT \n" + "INTO\n" + "").append(tableName).append("\n" + "	(CFF_MASTER_SID, SCREEN_NAME, FIELD_NAME, FIELD_VALUES)\n" + "VALUES\n" + "	(").append(projectionID).append(", '").append(tabName).append("', '").append(entry.getKey()).append("', '").append(entry.getValue()).append("');\n");
        }

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(query.toString());

    }

    public List<HelperDTO> getDropDownList(final String listType) {
        final List<HelperDTO> helperList = new ArrayList<>();
        try {
            SalesProjectionDAO dao = new SalesProjectionDAOImpl();
            LOGGER.debug("entering getDropDownList method with paramater listType=" + listType);
            final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like("listName", listType), RestrictionsFactoryUtil.like("listName", "ALL")));
            cfpDynamicQuery.addOrder(OrderFactoryUtil.asc("description"));
            final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
            helperList.add(new HelperDTO(0, "-Select One-"));
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {

                    final HelperTable helperTable = (HelperTable) list.get(i);
                    helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                            helperTable.getDescription()));
                }
            }

            LOGGER.debug(" getDropDownList method ends with return value strList size =" + helperList.size());

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return helperList;
    }

    public static Map editProjectionResults(final String tabName, final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException {
        String tableName = "CFF_SELECTION";
        StringBuilder query = new StringBuilder();
        query.append("SELECT FIELD_NAME, FIELD_VALUES FROM ").append(tableName).append("\n" + "WHERE CFF_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append("\n AND SCREEN_NAME LIKE '").append(tabName).append("';\n");
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
            Date date;
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
    public static String getPeriodSID(String selectedPeriod) {
        String periodSID;
        if (StringUtils.isNotBlank(selectedPeriod) && !"null".equals(selectedPeriod)) {
            periodSID = StringUtils.EMPTY;
        } else {
            periodSID = "0";
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
    public static String[] getYearAndPeriod(String period, String frequency, boolean formatFlag) {
        
        String[] periodArr = new String[NumericConstants.TWO];
        String[] splitPeriodYear = period.trim().split(" ");
        if (formatFlag) {
            periodArr[0] = splitPeriodYear[1].trim();
            periodArr[1] = ConstantsUtil.MONTHLY.equals(frequency)
                    ? String.valueOf(getMonthNumber(splitPeriodYear[0].trim())) : splitPeriodYear[0].trim().replaceAll("[^0-9]", "");
        } else {
            periodArr[0] = splitPeriodYear[0].trim();
            periodArr[1] = ConstantsUtil.MONTHLY.equals(frequency)
                    ? String.valueOf(getMonthNumber(splitPeriodYear[1].trim())) : splitPeriodYear[1].trim().replaceAll("[^0-9]", "");
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
        List<Leveldto> listValue = new ArrayList<>();
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
            projectionListFrom.add(ProjectionFactoryUtil.property("levelName"));
            projectionListFrom.add(ProjectionFactoryUtil.property(StringConstantsUtil.LEVEL_NO));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
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
            query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CUSTOM_VIEW_MASTER_SID, customId));
            query.addOrder(OrderFactoryUtil.asc(StringConstantsUtil.LEVEL_NO));
            list = CustomViewDetailsLocalServiceUtil.dynamicQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        return getHierarchyMan(projectionId, "C", levelNo);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchyMandated(int projectionId, final int levelNo) {
        return getHierarchyMan(projectionId, "P", levelNo);
    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchyMan(int projectionId, String hierarchyIndicator, final int levelNo) {
        List<Leveldto> listValue = new ArrayList<>();
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
            LOGGER.error(ex);
        }
        return listValue;
    }

    public static String getHierarchyTreeQueryMan(int projectionId, String hierarchyIndicator, final int levelNo) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME , RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = StringConstantsUtil.FROM_SMALL + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + "  as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.CFF_MASTER_SID=" + projectionId
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
        dto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.TWO]));
        dto.setLevel(String.valueOf(obj[NumericConstants.THREE]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.FOUR]));
            dto.setParentNode(String.valueOf(obj[NumericConstants.FIVE]));
            dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
        }
        return dto;
    }

    public static List<Object> getLevelNoAndHierarchyNo(Object value, boolean filter) {
        List<Object> levelHierarchy = new ArrayList<>();
        String selectedId = "0";
        if (value != null && !SELECT_ONE.equals(String.valueOf(value))) {
                selectedId = String.valueOf(value);
        }
        int levelNo = -1;
        String hierarchyNo = "";
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


    public static Map<Object, Object> getReturnsProjectionSelection(final int projectionId) {
        List list;
        String query = "";
        Map<Object, Object> map = new HashMap<>();
        query = "select Field_Name,Field_Values from RETURNS_PROJECTION_SELECTION\n"
                + "where Projection_Master_Sid=" + projectionId + ";";
            list = (List) commonDao.executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
      
    }

    public static String getTempCCPQueryForCOGS(PVSelectionDTO pvsDTO) {
        String query = " DECLARE @FROM_DATE DATE\n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
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
                + " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + " CREATE TABLE #TEMP_CCP (\n"
                + " COMPANY_MASTER_SID INT\n"
                + " ,CONTRACT_MASTER_SID INT\n"
                + " ,ITEM_MASTER_SID INT\n"
                + " ,PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + " ,PROJECTION_MASTER_SID INT)\n"
                + "  INSERT INTO #TEMP_CCP (\n"
                + " COMPANY_MASTER_SID\n"
                + " , CONTRACT_MASTER_SID\n"
                + " , ITEM_MASTER_SID\n"
                + " , PROJECTION_DETAILS_SID\n"
                + " , PROJECTION_MASTER_SID\n"
                + " )\n"
                + " SELECT C.COMPANY_MASTER_SID\n"
                + "  , C.CONTRACT_MASTER_SID\n"
                + "  , C.ITEM_MASTER_SID\n"
                + "  , PD.PROJECTION_DETAILS_SID\n"
                + "  , pm.PROJECTION_MASTER_SID\n"
                + " FROM @CCP CCP\n"
                + " , CCP_DETAILS C\n"
                + " , PROJECTION_DETAILS PD\n"
                + " , PROJECTION_MASTER PM\n"
                + " WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "  AND PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + " AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "  AND PM.PROJECTION_MASTER_SID = " + pvsDTO.getProjectionId() + "\n"
                + " DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + " INSERT INTO @ITEMID\n"
                + " SELECT IM.ITEM_MASTER_SID\n"
                + " FROM ITEM_MASTER IM\n"
                + " WHERE EXISTS (\n"
                + "  SELECT 1\n"
                + " FROM #TEMP_CCP A\n"
                + " WHERE PROJECTION_MASTER_SID = " + pvsDTO.getProjectionId() + "\n"
                + " AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + " )";
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
                + "   (\n"
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
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS";
        String groupBy = " " + table1 + StringConstantsUtil.DOT_YEARS;
        String selectClause = "Isnull(" + table1 + StringConstantsUtil.YEARS_DOT + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + "on" + " " + table1 + StringConstantsUtil.YEARS_DOT_EQUAL + table2 + StringConstantsUtil.DOT_YEARS;
        selectClause += "Isnull(" + table1 + StringConstantsUtil.PERIODS_DOT + table2 + StringConstantsUtil.PERIODS_DOT_CLOSE_BRACE;
        selectClause += " as PERIODS, \n";
        if (freq != 1) {
            finalWhereCond += StringConstantsUtil.SMALL_AND + table1 + StringConstantsUtil.PERIODS_DOT_EQUAL + table2 + StringConstantsUtil.DOT_PERIODS;
            groupBy += ", " + table1 + StringConstantsUtil.DOT_PERIODS;

        }
        orderBy += ", PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();
        String query = "select distinct BRAND_NAME  from dbo.BRAND_MASTER WHERE BRAND_NAME!='null'";
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object brandName : list) {
            brandList.add(String.valueOf(brandName));
        }
        return brandList;

    }

    public List<String> getIdentifierType() {
        List<String> identifierList = new ArrayList<>();
        String query = "select distinct ITEM_IDENTIFIER_VALUE from dbo.ITEM_IDENTIFIER WHERE ITEM_IDENTIFIER_VALUE!='null'";
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object idenifier : list) {
            identifierList.add(String.valueOf(idenifier));
        }
        return identifierList;
    }

    public static int getMonthNumber(String monthName) {
        int monthNumber = 0;
        try {
            Date date;
            date = new SimpleDateFormat("MMM").parse(monthName); //put your month name here
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            monthNumber = cal.get(Calendar.MONTH) + 1;
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return monthNumber;
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }
    
    public static String getHistoryPeriodDate(String freq,int histNum)
    {
        String dateQuery=StringUtils.EMPTY;
        if("Quarterly".equals(freq)){
            dateQuery="SELECT Cast(Dateadd(qq, -@history, Dateadd(qq, Datediff(qq, 0, Getdate()), 0)) AS DATE)";
       }else if("Semi-Annually".equals(freq)){
            dateQuery = "SELECT Cast(Dateadd(qq, 2 * -@history, CASE\n"
                    + "                                  WHEN Datepart(qq, Getdate()) < 3 THEN Concat(Year(Getdate()), '-01-01')\n"
                    + "                                  ELSE Concat(Year(Getdate()), '-07-01')\n"
                    + "                                END) AS DATE)";
       }else if("Annually".equals(freq)){
          dateQuery="SELECT Cast(Dateadd(yy, -@history, Dateadd(yy, Datediff(yy, 0, Getdate()), 0))AS DATE)";
       }else if("Monthly".equals(freq)){
         dateQuery="SELECT Cast(Dateadd(mm, -@history, Dateadd(mm, Datediff(mm, 0, Getdate()), 0))AS DATE)";
       }
        dateQuery=dateQuery.replace("@history", String.valueOf(histNum));
        return dateQuery;
    }
    
    /**
     * Convert the given list value to feed in query format
     *      
     * @param valueToform 
     * @param coloumnName 
     * @return  
     */
    public String formInqueryStringValue(final List<String> valueToform, String coloumnName) {
        StringBuilder value = new StringBuilder();
        String comma = StringUtils.EMPTY;
        for (String string : valueToform) {
            value.append(comma).append(coloumnName).append(" like '").append(string).append("%'");
            comma = " or ";
        }
        return value.toString();
    }
 /**
     * Method used to get the Hierarchy Indicator based on the Custom View Level
     * No. based on the selected Custom View. Custom View Level Details are
     * maintained in a List(List&lt;Leveldto&gt;).
     *
     * @param projSelDTO
     * @return
     */
    public String getHiearchyIndicatorFromCustomView(final ProjectionSelectionDTO projSelDTO) {
        List<Leveldto> levelList = projSelDTO.getSessionDTO().getCustomHierarchyMap().get(projSelDTO.getCustomId());
    
        String hierarchyIndicator = StringUtils.EMPTY;
        for (Leveldto levelDTO : levelList) {
            if (levelDTO.getTreeLevelNo() == projSelDTO.getTreeLevelNo()) {
                hierarchyIndicator = levelDTO.getHierarchyIndicator();
                break;
}
        }
        return hierarchyIndicator;
    }
 /**
     * Method is used to obtain the List of hierarchyNo for each level in the
     * relationship that is used in the projection based on the used Custom
     * Hierarchy.Method is used in results screens to retrieve the hierarchy no
     * alone without data.
     *
     * @param projSelDTO
     * @param start
     * @param end
     * @return 
     */
    public List getHiearchyNoForCustomView(final ProjectionSelectionDTO projSelDTO, int start, int end) {

        String query = insertAvailableHierarchyNo(projSelDTO);
        query += SQlUtil.getQuery("custom-view-hiearchy-no-query");
        query = query.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        query = query.replace("[?START]", String.valueOf(start));
        query = query.replace("[?END]", String.valueOf(end));    
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        } else {
            return Collections.emptyList();
        }

    }

    /**
     * Method used to insert the available (current level) Hierarchy No.'s in a
     * #temp table. These hierarchy no.'s can be used to retrieve the data from
     * the projection tables. Current level Hierarchy no.s are obtained from
     * map(Can be taken from session DTO based on Hierarchy Indicator) that is
     * populated with the relationship details in data selection Generate , Edit
     * and other activities.
     *
     * @param projSelDTO
     * @return
     */
    public String insertAvailableHierarchyNo(ProjectionSelectionDTO projSelDTO) {
        String sql;
        sql = SQlUtil.getQuery("selected-hierarchy-no");
        if (projSelDTO.isIsCustomHierarchy()) {
            String currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(projSelDTO);
            int levelNo = getActualLevelNoFromCustomView(projSelDTO);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case "C":
                    sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getCustomerHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                case "P":
                    sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                default:
                    LOGGER.warn("Invalid Hierarchy Indicator: " + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        }
        sql = sql.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        System.out.println(" insertAvailableHierarchyNo ----------- >>>> "+sql);
        LOGGER.debug("Group Filter Value:" + projSelDTO.getGroupFilter());
        return sql;

    }
 /**
     * Method is used to build the join query that can be used while loading the
     * data for different screens. This join determines that hierarchy no
     * associated with CCP that is used to retrieve the data.
     *
     * @param projSelDTO
     * @return
     */
    public String getHierarchyJoinQuery(ProjectionSelectionDTO projSelDTO) {

        String currentHierarchyIndicator;

        if (projSelDTO.isIsCustomHierarchy()) {
            currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(projSelDTO);
        } else {
            currentHierarchyIndicator = projSelDTO.getHierarchyIndicator();
        }

        String joinQuery = getHierarchyJoinQuery(projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator);
        return joinQuery;
    }    
    /**
     * Method is used to build the join query. This join determines that hierarchy no
     * associated with CCP that is used to manipulate the data.
     *
     * @param isCustomHierarchy
     * @param customerHierarchyNo
     * @param productHierarchyNo
     * @param hierarchyIndicator
     * @return
     */
    public String getHierarchyJoinQuery(boolean isCustomHierarchy, String customerHierarchyNo, String productHierarchyNo, String hierarchyIndicator) {
        StringBuilder joinQuery = new StringBuilder();

        joinQuery.append(" JOIN ST_CCP_HIERARCHY CH ON ");
        if (isCustomHierarchy) {

            switch (String.valueOf(hierarchyIndicator)) {
                case "C":
                    joinQuery.append(" CH.CUST_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(productHierarchyNo)) {
                        joinQuery.append("AND CH.PROD_HIERARCHY_NO LIKE '");
                        joinQuery.append(productHierarchyNo);
                        joinQuery.append("%'");
                    }
                    break;
                case "P":
                    joinQuery.append(" CH.PROD_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(customerHierarchyNo)) {
                        joinQuery.append("AND CH.CUST_HIERARCHY_NO LIKE '");
                        joinQuery.append(customerHierarchyNo);
                        joinQuery.append("%'");
                    }
                    break;
                default:

                    LOGGER.warn("Invalid Hierarchy Indicator:" + hierarchyIndicator);
            }

        } else {
            joinQuery.append("C".equals(hierarchyIndicator) ? "CH.CUST_HIERARCHY_NO " : "CH.PROD_HIERARCHY_NO");
            joinQuery.append(" LIKE A.HIERARCHY_NO + '%' ");
        }

        return joinQuery.toString();
    }
       /**
     * Method used to get the actual level no. in the hierarchy from the Custom
     * View Level No. based on the selected Custom View. Custom View Level
     * Details are maintained in a List(List&lt;Leveldto&gt;).
     *
     * @param projSelDTO
     * @return
     */
    public int getActualLevelNoFromCustomView(final ProjectionSelectionDTO projSelDTO) {
        List<Leveldto> levelList = projSelDTO.getSessionDTO().getCustomHierarchyMap().get(projSelDTO.getCustomId());

        int levelNo = 0;

        for (Leveldto levelDTO : levelList) {
            if (levelDTO.getTreeLevelNo() == projSelDTO.getTreeLevelNo()) {
                levelNo = levelDTO.getLevelNo();
                break;
            }
        }
        return levelNo;
    }
     /**
     * This method used to build a String of available (Current Level) hierarchy
     * no.s that can be used in the queries.Current level Hierarchy no.s are
     * obtained from map(Can be taken from session DTO based on Hierarchy
     * Indicator) that is populated with the relationship details in data
     * selection Generate , Edit and other activities.
     *
     * @param sessionDTO
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param levelNo
     * @return
     */
    public String getSelectedHierarchy(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {
      
        if (levelNo == 0) {
            throw new IllegalArgumentException("Invalid Level No:" + levelNo);
        }


        Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
        StringBuilder stringBuilder = new StringBuilder();

        boolean isNotFirstElement = false;
        boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);
        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if (Integer.valueOf(entry.getValue().get(NumericConstants.TWO).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(NumericConstants.FOUR).toString()) 
                    && (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {

                    if (isNotFirstElement) {
                        stringBuilder.append(",\n");
                    }
                    stringBuilder.append("('");
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append("')");

                    isNotFirstElement = true;
            }
        }
        return stringBuilder.toString();
    }
        public static void loadCustomHierarchyList(final SessionDTO session) {
        Map<Integer, List<Leveldto>> hierarchy;
        if (session.getCustomHierarchyMap() == null) {
            hierarchy = new HashMap<>();
        } else {
            hierarchy = session.getCustomHierarchyMap();
        }
        int customId = session.getCustomId();
        if (!session.getCustomHierarchyMap().containsKey(customId)) {
            List<CffCustomViewDetails> customDetailsList = null;
            List<Leveldto> listValue = new ArrayList<>();
            if (customId != 0) {
                if (session.getCustomDetailMap().get(customId) != null) {
                    customDetailsList = session.getCustomDetailMap().get(customId);
                } else {
                    customDetailsList = getCustomViewDetails(customId);
                    session.getCustomDetailMap().put(customId, customDetailsList);
                }
                if (customDetailsList != null && !customDetailsList.isEmpty()) {
                    StringBuilder relationShipLevelQry = new StringBuilder();
                    relationShipLevelQry.append("select DISTINCT LEVEL_NAME,LEVEL_NO,HIERARCHY_LEVEL_DEFINITION_SID from dbo.RELATIONSHIP_LEVEL_DEFINITION where HIERARCHY_LEVEL_DEFINITION_SID in (");
                    for (int i = 0; i < customDetailsList.size(); i++) {
                        relationShipLevelQry.append(customDetailsList.get(i).getHierarchyId());
                        if (i != customDetailsList.size() - 1) {
                            relationShipLevelQry.append(",");
                        }
                    }

                    relationShipLevelQry.append(")");
                    List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(relationShipLevelQry.toString());
                    /**
                     * assign null to Object , To be destroyed By JVM *
                     */
                    for (CffCustomViewDetails ob : customDetailsList) {
                        for (Object[] obj : list) {
                            if (String.valueOf(obj[NumericConstants.TWO]).trim().equals(String.valueOf(ob.getHierarchyId()).trim()) && obj.length > 1) {
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
            }
            hierarchy.put(customId, listValue);
            session.setCustomHierarchyMap(hierarchy);
        }
    }
             /**
     * Method is used to obtain the List of hierarchyNo for each level in the
     * relationship that is used in the projection based on the used Custom
     * Hierarchy.Method is used in results screens to retrieve the hierarchy no
     * alone without data.
     *
     * @param projSelDTO
     * @param start
     * @param end
     * @return
     */
    public List getHiearchyNoAsList(final ProjectionSelectionDTO projSelDTO, int start, int end) {

        String query = SQlUtil.getQuery("hiearchy-no-query");
        query = query.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        query = query.replace("[?START]", String.valueOf(start));
        query = query.replace("[?END]", String.valueOf(end));
        query = query.replace("[?HIERARCHY_COLUMN]", getColumnName(projSelDTO.getHierarchyIndicator()));
        query = query.replace("[?TAB_BASED_JOIN]", "");
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        }

        return Collections.emptyList();
    }
       /**
     * Return the column name associated to hierarchy indicator
     * 
     * @param hierarchyIndicator
     * @return 
     */
    public String getColumnName(final String hierarchyIndicator) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = "CUST_HIERARCHY_NO";
        } else {
            columnName = "PROD_HIERARCHY_NO";
        }
        return columnName;
    }
     /**
     * Method is used to obtain the count for each level in the relationship
     * that is used in the projection based on the used Custom Hierarchy.
     *
     * @param projSelDTO
     * @return 
     */
    public int getCountForCustomView(final ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getSessionDTO().getCustomHierarchyMap().get(projSelDTO.getCustomId()).size() < projSelDTO.getTreeLevelNo()) {
            LOGGER.debug("Custom view last level"); 
            return 0;
        }
        String countQuery = insertAvailableHierarchyNo(projSelDTO);
        countQuery += SQlUtil.getQuery("custom-view-count-query");
        countQuery = countQuery.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(countQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            count = Integer.valueOf(list.get(0).toString());
        }
        LOGGER.debug("ending getCountForCustomView");
        return count;
    }
    /**
     * Method is used to obtain the count for each level in the relationship
     * that is used in the projection based on the used Custom Hierarchy.
     *
     * @param session
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param levelNo
     * @param customId
     * @param customerHierarchyNo
     * @param productHierarchyNo
     * @return 
     */
    public int getCountForCustomView(final SessionDTO session, final String hierarchyNo, final String hierarchyIndicator, final int levelNo, 
            final int customId,final String customerHierarchyNo, final String productHierarchyNo) {

        int count = 0;
        String countQuery = insertAvailableHierarchyNo(session, hierarchyNo, hierarchyIndicator, levelNo, Boolean.TRUE,
                customId, customerHierarchyNo,productHierarchyNo);
        countQuery += SQlUtil.getQuery("custom-view-count-query");
        countQuery = countQuery.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(Boolean.TRUE, customerHierarchyNo, productHierarchyNo, hierarchyIndicator));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(countQuery, session.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            count = Integer.valueOf(list.get(0).toString());
        }
        LOGGER.debug("Count is  "+count);
        return count;
    }
    public String insertAvailableHierarchyNo(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo,boolean isCustomHierarchy,
            final int customId,final String customerHierarchyNo,final String productHierarchyNo) {
        String sql;
        sql = SQlUtil.getQuery("selected-hierarchy-no");
        if (isCustomHierarchy) {
            String currentHierarchyIndicator =  getHiearchyIndicatorFromCustomView(sessionDTO, customId, levelNo);
            int actualLevelNo = getActualLevelNoFromCustomView(sessionDTO, customId, levelNo);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case "C":
                    sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(sessionDTO, customerHierarchyNo, currentHierarchyIndicator, actualLevelNo));
                    break;
                case "P":
                    sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(sessionDTO, productHierarchyNo, currentHierarchyIndicator, actualLevelNo));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo));
        }
        sql = sql.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(sessionDTO, customId, levelNo,isCustomHierarchy,hierarchyIndicator,customerHierarchyNo,productHierarchyNo));
        return sql;

    }
    /**
     * Method is used to build the join query that can be used while loading the
     * data for different screens. This join determines that hierarchy no
     * associated with CCP that is used to retrieve the data.
     *
     * @param sessionDTO
     * @param customId
     * @param treeLevelNo
     * @param isCustom
     * @param hierarchyIndicator
     * @param customerHierarchyNo
     * @param productHierarchyNo
     * @return
     */
    public String getHierarchyJoinQuery(final SessionDTO sessionDTO, final int customId, final int treeLevelNo,final boolean isCustom, final String hierarchyIndicator
                    , final String customerHierarchyNo,final String productHierarchyNo) {
        String currentHierarchyIndicator;
        if (isCustom) {
            currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(sessionDTO, customId, treeLevelNo);
        } else {
            currentHierarchyIndicator = hierarchyIndicator;
        }
        String joinQuery = getHierarchyJoinQuery(isCustom, customerHierarchyNo, productHierarchyNo, currentHierarchyIndicator);
        return joinQuery;
    }
     /**
     * Method used to get the Hierarchy Indicator based on the Custom View Level
     * No. based on the selected Custom View. Custom View Level Details are
     * maintained in a List(List&lt;Leveldto&gt;).
     *
     * @param sessionDTO
     * @param customId
     * @param treeLevelNo
     * @return
     */
    public String getHiearchyIndicatorFromCustomView(final SessionDTO sessionDTO, final int customId, final int treeLevelNo) {
        List<Leveldto> levelList = sessionDTO.getCustomHierarchyMap().get(customId);
        
        String hierarchyIndicator = StringUtils.EMPTY;
        for (Leveldto levelDTO : levelList) {
            if (levelDTO.getTreeLevelNo() == treeLevelNo) {
                
                hierarchyIndicator = levelDTO.getHierarchyIndicator();
                break;
            }
        }
        return hierarchyIndicator;
    }
    
     /**
     * Method used to get the actual level no. in the hierarchy from the Custom
     * View Level No. based on the selected Custom View. Custom View Level
     * Details are maintained in a List(List&lt;Leveldto&gt;).
     *
     * @param projSelDTO
     * @return
     */
    public int getActualLevelNoFromCustomView(final SessionDTO session,final int customId,final int treeLevelNo) {
        List<Leveldto> levelList = session.getCustomHierarchyMap().get(customId);

        int levelNo = 0;

        for (Leveldto levelDTO : levelList) {
            if (levelDTO.getTreeLevelNo() == treeLevelNo) {
                levelNo = levelDTO.getLevelNo();
                break;
            }
        }
        return levelNo;
    }
    /**
     * Method is used to get the count for a particular level in the customer or
     * product hierarchy. Count varies based on the parent level.
     *
     * @param sessionDTO
     * @param hierarchyNo
     * @param levelNo
     * @param hierarchyIndicator
     * @return
     */
    public int getCount(final SessionDTO sessionDTO, final String hierarchyNo, final int levelNo, final String hierarchyIndicator) {

        int count = 0;

        if ("C".equals(hierarchyIndicator) || "P".equals(hierarchyIndicator)) {
            System.out.println(" sessionDTO.getHierarchyLevelDetails()  "+sessionDTO.getHierarchyLevelDetails());
            count = getCountForHierarchy(sessionDTO.getHierarchyLevelDetails(), hierarchyNo, levelNo,hierarchyIndicator);
            
        } else {

            throw new IllegalArgumentException("Invalid Hierarchy Indicator :" + hierarchyIndicator);

        }
        LOGGER.debug("Count is "+count);
        return count;

    }
    /**
     * Method is used to get the count for a particular level in the 
     * hierarchy. Count varies based on the parent level. Count is obtained from
     * map(Can be taken from session DTO based on Hierarchy Indicator) that is
     * populated with the relationship details in data selection Generate , Edit
     * and other activities.
     *
     * @param customerHierarchyMap
     * @param hierarchyNo
     * @param levelNo
     * @return
     */

    private int getCountForHierarchy(final Map<String, List> customerHierarchyMap, final String hierarchyNo, final int levelNo,final String hierarchyIndicator) {
        int count = 0;

        if (levelNo == 0) {
            throw new IllegalArgumentException("Invalid Level No: " + levelNo);
        }

        for (Map.Entry<String, List> entry : customerHierarchyMap.entrySet()) {

            List levelDetailsList = entry.getValue();

            if (levelNo == Integer.valueOf(levelDetailsList.get(NumericConstants.TWO).toString()) && hierarchyIndicator.equals(levelDetailsList.get(NumericConstants.FOUR).toString()) 
                    && (StringUtils.isBlank(hierarchyNo) || "%".equals(hierarchyNo) || entry.getKey().startsWith(hierarchyNo))) {
                    count++;
            }

        }
        return count;
    }
        /**
     * To drop the temp tables created in dynamically for the given user id and session id.
     * Query is placed in the Other sources->src/main/resources->sqlresources->deleteTempQueries.xml
     * 
     * @param userId
     * @param sessionId 
     */
    public static void dropDynamicTables(String userId, String sessionId) {
        Map<String, String> inputs = new HashMap<>();
        inputs.put("@USER_ID", userId);
        inputs.put("@SESSION_ID", sessionId);
        CFFQueryUtils.updateDataFromMap(inputs, "Drop.DynamicTempTables");
    }
}


