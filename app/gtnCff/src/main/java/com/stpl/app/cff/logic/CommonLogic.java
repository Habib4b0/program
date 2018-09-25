
package com.stpl.app.cff.logic;

import static com.stpl.app.cff.util.ConstantsUtil.SELECT_ONE;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.slf4j.LoggerFactory;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.queryUtils.CFFQueryUtils;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.NotificationUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.service.CffCustomViewDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffCustomViewMasterLocalServiceUtil;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class CommonLogic {

    
    private static final CommonDAO commonDao = new CommonDAOImpl();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    public static final String PRC_CFF_FILES_DATA_INSERT = "PRC_CFF_FILES_DATA_INSERT";
    public static final String FILES_INSERT = "FILES_INSERT";
    private ThreadPool service = ThreadPool.getInstance();
    public static final String RUNNING_STATUS = "R";
    public static final String PROJECTION_ID_FLOWER_BRACES = "PROJECTION_ID= {}";
    /**
     * The Constant LOGGER.
     */
public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonLogic.class);
    public static final String ALL_GROUP = "All PPA Group";
    public static final String LEVEL_CAPS = "@LEVEL";
    public static final String BUILDERSID = "@BUILDERSID";
    public static final String EACH = "EACH";
    
    public static final String RELATIONSHIPJOIN = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO=A.HIERARCHY_NO AND RELATIONSHIP_BUILDER_SID =";
    public static final String RELATIONSHIPVERSION = " AND RLD1.VERSION_NO=";
    private static final String RELATIONSHIP_BUILDER_SID = "' and relationship_builder_sid = ";

    /**
     * Get Customer Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getCustomerHierarchy(int projectionId, final int levelNo, final int versionNo) {
        return getHierarchy(projectionId, "C", levelNo,versionNo);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchy(int projectionId, final int levelNo, final int versionNo) {
        return getHierarchy(projectionId, "P", levelNo,versionNo);
    }
    
     /**
     * Get Deduction Hierarchy
     *
     * @param projectionId
     * @return list
     */
    
    
    public static List<Leveldto> getDeductionHierarchy(int projectionId, final int levelNo, final int versionNo) {
        return getHierarchy(projectionId, "D", levelNo,versionNo);
    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchy(int projectionId, String hierarchyIndicator, final int levelNo, final int versionNo) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = "D".equals(hierarchyIndicator)? getHierarchyTreeQueryDeduction(projectionId, hierarchyIndicator, levelNo,versionNo) : getHierarchyTreeQuery(projectionId, hierarchyIndicator, levelNo,versionNo);
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, true);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
            DynamicQuery query = CffCustomViewMasterLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return list;
    }

    public static int customDdlbOptionChange(ComboBox customDdlb, Button editBtn, ComboBox level) {
        editBtn.setEnabled(false);
        level.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!"null".equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.parseInt(value);
            level.setEnabled(true);
            return selectedId;
        }
        return 0;
    }

    public static int customDdlbOptionChange(ComboBox customDdlb, Button editBtn) {
        editBtn.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!"null".equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.parseInt(value);
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
            userId = Integer.parseInt(userId1);
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
        if (userId != 0) {
            int selectedId = Integer.parseInt(String.valueOf(value));
            for (CffCustomViewMaster custom : customViewList) {
                if (custom.getCffCustomViewMasterSid() == selectedId && custom.getCreatedBy() == userId) {
                        return true;
                }
            }
        }
        NotificationUtils.getErrorNotification("Edit a View", "You cannot edit a view that you did not create. Please select another view, or create a new one");
        return false;
    }


    public static int cffCustomViewSaveLogic(SessionDTO session, int customId, String viewName, List levelList) {
        String userId1 = session.getUserId();
        int userId = 0;
        if (CommonUtils.isInteger(userId1)) {
            userId = Integer.parseInt(userId1);
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
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
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
                    LOGGER.error(ex.getMessage());
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
            DynamicQuery query = CffCustomViewMasterLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("cffMasterSid", projectionId));
            query.add(RestrictionsFactoryUtil.eq("viewName", viewName));
            if (customId != 0) {
                query.add(RestrictionsFactoryUtil.ne("cffCustomViewMasterSid", customId));
            }

            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return list;
    }

    public static boolean customViewDetailsSaveLogic(int customId, List levelList) throws SystemException  {
        for (Object ob : levelList) {
            Leveldto dto = (Leveldto) ob;
            int create = (int) CounterLocalServiceUtil.increment();
            CffCustomViewDetails customViewDetails = CffCustomViewDetailsLocalServiceUtil.createCffCustomViewDetails(create);
            customViewDetails.setCffCustomViewMasterSid(customId);
            customViewDetails.setHierarchyId(dto.getHierarchyId());
            customViewDetails.setHierarchyIndicator(dto.getHierarchyIndicator());
            customViewDetails.setLevelNo(dto.getTreeLevelNo());
            commonDao.addCustomViewDetails(customViewDetails);
        }
        return true;
    }

    public static String cffCustomViewDetailsSaveLogic(int customId, List levelList, boolean isUpdate) {
        StringBuilder declareSql = new StringBuilder("DECLARE  @identity_val VARCHAR (50)='" ).append( customId ).append( "'");
        StringBuilder sql = new StringBuilder("insert into CFF_CUSTOM_VIEW_DETAILS(CFF_CUSTOM_VIEW_MASTER_SID,HIERARCHY_ID,HIERARCHY_INDICATOR,LEVEL_NO) values ");
        char quotes = '\'';
        char comma = ',';
        for (Object ob : levelList) {
            Leveldto dto = (Leveldto) ob;
            sql.append("(@identity_val,").append(quotes).append(dto.getHierarchyId()).append(quotes).append(comma).append(quotes)
                    .append(dto.getHierarchyIndicator()).append(quotes).append(comma).append(quotes).append(dto.getTreeLevelNo()).append(quotes).append("),");
        }
        String query = sql.toString();
        if (isUpdate) {
            query += declareSql.toString();
        }
        return query.substring(0, query.lastIndexOf(','));
    }

    /**
     * Get Custom View Details
     *
     * @param customId
     * @return list
     */
    public static List<Object[]> getCustomViewDetails(int customId) {
        List<Object[]> list = null;
        try {
          String query="Select * from CUST_VIEW_MASTER WHERE CUST_VIEW_MASTER_SID="+customId;
            list = (List<Object[]> )commonDao.executeSelectQuery(query);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return list;
    }


    public static CffCustomViewMaster getCustomView(int customViewMasterSid) {
        CffCustomViewMaster cvm = null;
        if (customViewMasterSid != 0) {
            try {
                cvm = commonDao.getCustomView(customViewMasterSid);
            } catch (SystemException | PortalException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return cvm;
    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo, final int versionNo) {
        String selectClause = "select distinct RLD.LEVEL_NO,  "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as  HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = StringConstantsUtil.FROM_SMALL + getViewTableName(hierarchyIndicator);
        StringBuilder customSql = new StringBuilder();
        customSql.append(selectClause);
        customSql.append(from);
        customSql.append(" as HC,");
        customSql.append(" RELATIONSHIP_LEVEL_DEFINITION as RLD ");
        customSql.append(StringConstantsUtil.CFF_MASTER_WHERE).append(projectionId);
        customSql.append(" and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=").append(levelNo);
        customSql.append(" AND RLD.VERSION_NO = ").append(versionNo);
        return customSql.toString();
    }
    
    
    public static String getHierarchyTreeQueryDeduction(int projectionId, String hierarchyIndicator, final int levelNo, final int versionNo) {
        List<Object> queryInputs = new ArrayList<>();
        queryInputs.add(hierarchyIndicator);
        queryInputs.add("DED_RELATIONSHIP_BULDER_SID");
        queryInputs.add(projectionId);
        queryInputs.add(levelNo);
        queryInputs.add(versionNo);
        return CommonQueryUtils.getAppQuery(queryInputs, "relation-ship-level-details");
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
            levelNo = Integer.parseInt(selectedId.substring(0, j));
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

        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
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

        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        return getParentLevelNoAndHierarchyNo(levelNo, hierarchyNo);
    }

    public static List getRelationshipLevels(int hierarchyLevelId) {
        List list = null;
        try {
            DynamicQuery query = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("hierarchyLevelDefinitionSid", hierarchyLevelId));
            ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
            projectionListFrom.add(ProjectionFactoryUtil.property("levelName"));
            projectionListFrom.add(ProjectionFactoryUtil.property(StringConstantsUtil.LEVEL_NO));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = commonDao.getRelationshipLevels(query);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
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
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    newLevelList.add(dto);
                }
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
        LOGGER.info("Procedure Name= {} ", procedureName);
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
                        procedureToCall.append('(');
                    }
                    procedureToCall.append("?,");
                    if (i == noOfArgs - 1) {
                        procedureToCall.append(')');
                    }
                }
                procedureToCall.replace(procedureToCall.lastIndexOf(ConstantsUtil.COMMA), procedureToCall.lastIndexOf(ConstantsUtil.COMMA) + 1, StringUtils.EMPTY);
                procedureToCall.append('}');
                statement = connection.prepareCall(procedureToCall.toString());
                for (int i = 0; i < noOfArgs; i++) {
                     LOGGER.info("Arguments {}", orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);

            }
        } catch (SQLException | NamingException ex) {
           
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (connection != null) {
                connection.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return objectList;
    }
    public static  void callProcedureUpdate(String procedureName, Object[] orderedArgs) {
        LOGGER.info("Procedure Name= {} ", procedureName);
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
                        procedureToCall.append('(');
                    }
                    procedureToCall.append("?,");
                    if (i == noOfArgs - 1) {
                        procedureToCall.append(')');
                    }
                }
                procedureToCall.replace(procedureToCall.lastIndexOf(ConstantsUtil.COMMA), procedureToCall.lastIndexOf(ConstantsUtil.COMMA) + 1, StringUtils.EMPTY);
                procedureToCall.append('}');
                statement = connection.prepareCall(procedureToCall.toString());
                for (int i = 0; i < noOfArgs; i++) {
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                statement.executeUpdate();
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                if (connection != null) {
                connection.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
       
    }
    
    public Future callThreadForProcedureFileInsert(SessionDTO sessionDTO){
        Future future=service.submitRunnable(new Runnable() {
            @Override
            public void run() {
                updateStatusForProcedure(RUNNING_STATUS, sessionDTO, FILES_INSERT, "PRODUCT");
                Object[] productInput = {sessionDTO.getProjectionId(), sessionDTO.getUserId(), sessionDTO.getSessionId(), 0};
                callProcedureUpdate(PRC_CFF_FILES_DATA_INSERT, productInput);
            }
        });
        service.submitRunnable(new Runnable() {
            @Override
            public void run() {
                 updateStatusForProcedure(RUNNING_STATUS, sessionDTO, FILES_INSERT, "CUSTOMER");
                Object[] customerInput = {sessionDTO.getProjectionId(), sessionDTO.getUserId(), sessionDTO.getSessionId(), 1};
                callProcedureUpdate(PRC_CFF_FILES_DATA_INSERT, customerInput);
            }
        });
        
        return future;
    }
    
    public static void updateStatusForProcedure(String status,SessionDTO session,String screenName,String viewName){
    String updateStatus="UPDATE ST_STATUS_TABLE SET FLAG='"+status+"'  WHERE SCREEN_NAME='"+screenName+"' AND VIEW_NAME='"+viewName+"'";
    HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateStatus, session.getCurrentTableNames()));
    
    }
    
    public void checkForCompletion(SessionDTO session, String screenName, String viewName) {
        String updateStatus = "Select FLAG from ST_STATUS_TABLE WHERE SCREEN_NAME='" + screenName + "' AND VIEW_NAME='" + viewName + "'";
        List<String> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(updateStatus, session.getCurrentTableNames()));
        if (list.get(0) != null && !list.get(0).trim().equalsIgnoreCase("C")) {
            waitForSeconds();
            checkForCompletion(session, screenName, viewName);
        }
    }
    
    public void checkForCompletionALL(SessionDTO session, String screenName,PVSelectionDTO pvSelectionDTO ) {
        String selectStatus = "Select count(*) from ST_STATUS_TABLE WHERE SCREEN_NAME='" + screenName + "' AND VIEW_NAME ='"+pvSelectionDTO.getView()+"' and FLAG='R'";
        List<Integer> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(selectStatus, session.getCurrentTableNames()));
        if (list.get(0) != null && list.get(0)>=1) {
            waitForSeconds();
            checkForCompletionALL(session, screenName,pvSelectionDTO);
        }
    }
    
    public void waitForSeconds() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            LOGGER.error( "Interrupted!", ex);
            Thread.currentThread().interrupt();
        }
    }
    
    public static void truncateTempTable(SessionDTO session){
    String truncateStatus=SQlUtil.getQuery("TableTruncationCFF");
    HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(truncateStatus, session.getCurrentTableNames()));
    
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
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return objList;
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
        LOGGER.debug("saveOrUpdate= {} ", saveOrUpdate);
        if ("save".equalsIgnoreCase(saveOrUpdate)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("INSERT INTO ").append(tableName).append(" (CFF_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE ").append(tableName).append(" SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append('\'');
                queryBuilder.append(" WHERE CFF_MASTER_SID = '").append(projectionID).append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='").append(obj[i]).append("'\n");
            }
        }
        LOGGER.debug("Save / update query >> {}", queryBuilder);
        HelperTableLocalServiceUtil.executeUpdateQuery(queryBuilder.toString());
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
                    Object[] obj =  list.get(i);
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
     * Gets the DiscountNo.
     *
     * @param projectionId
     * @param priceGroupType
     * @return object
     */
    public static Object executeSelectQuery(String query) {

        return commonDao.executeSelectQuery(query);

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
		return StringConstantsUtil.SMALL_AND + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table
				+ ".SESSION_ID=" + sessionId + " \n";
    }

    public static String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String ccp) {
		return StringConstantsUtil.SMALL_AND + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + ccp
				+ ".RELATIONSHIP_LEVEL_SID \n"
                + StringConstantsUtil.SMALL_AND + ccp + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID \n";
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
        StringBuilder tableQuery = new StringBuilder();
        tableQuery.append("DECLARE @CCP TABLE\n");
        tableQuery.append("  (\n");
        tableQuery.append("     RELATIONSHIP_LEVEL_SID INT,\n");
        tableQuery.append("     CCP_DETAILS_SID        INT,\n");
        tableQuery.append("     HIERARCHY_NO           VARCHAR(50)\n");
        tableQuery.append("  ) \n");
        tableQuery.append(" INSERT INTO @CCP\n");
        tableQuery.append("            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n");
        return tableQuery.toString();
    }
   
    public static List<String> getAllHierarchyNo(String hierarchyNo) {
        LOGGER.debug("getAllHierarchyNo started");
        List<String> allLevelHierarchy = new ArrayList<>();
        String extraDot = "";
        if (hierarchyNo.endsWith(".")) {
            extraDot = ".";
        }
        String[] hierarchyNoArray = hierarchyNo.split("\\.");
        StringBuilder hierarchyNo1 = new StringBuilder(hierarchyNoArray[0]);
        allLevelHierarchy.add(hierarchyNo1 + extraDot);
        for (int i = 1; i < hierarchyNoArray.length - 1; i++) {
            hierarchyNo1.append(".").append(hierarchyNoArray[i]);
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
        int lin = hierarchyNo.lastIndexOf('.');
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
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT M.USER_GROUP FROM ").append(table).append(" M");
        query.append("  JOIN PROJECTION_DETAILS PD ON M.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID");
        query.append(" JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID");
        query.append(" JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ");
        query.append(" ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID");
        query.append(" WHERE RLD.LEVEL_NAME = 'Trading Partner' ");
        query.append(" AND M.USER_GROUP IS NOT NULL ");
        query.append(" AND PD.PROJECTION_MASTER_SID ='").append(projectionId).append("' ");
        query.append(" AND M.USER_ID=").append(userId);
        query.append(" AND M.SESSION_ID=").append(sessionId);
        return query.toString();
    }

    public static List<String> getDiscountGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_DISCOUNT_PROJ_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(StringConstantsUtil.DISCOUNT_HYPEN + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return groupList;
    }

    public static List<String> getPPAGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_PPA_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add("PPA-" + (list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return groupList;
    }

    public static List<String> getSalesGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_NM_SALES_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(StringConstantsUtil.SALES_HYPEN + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
            List<Object> list = (List<Object>) executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                levelNo = Integer.parseInt(String.valueOf(ob));
            }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
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
        DynamicQuery query = MProjectionSelectionLocalServiceUtil.dynamicQuery();
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
                    Object[] obj =  list.get(i);
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
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getHierarchyTreeQuery(projectionId, hierarchyIndicator, levelNo, rbID);
            List<Object> list = (List<Object>) executeSelectQuery(query);
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
            LOGGER.error(ex.getMessage());
        }
        return listValue;
    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo, final Object rbID) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as  TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = StringConstantsUtil.FROM_SMALL + getViewTableName(hierarchyIndicator);
        String customSql = StringUtils.EMPTY;
        customSql = customSql + selectClause + from + " as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as  RLD "
                + StringConstantsUtil.CFF_MASTER_WHERE + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID= RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo + " AND RLD.RELATIONSHIP_BUILDER_SID='" + rbID + "';";
        return customSql;
    }

    public static String getIndicator(int levelNo, int viewName) {
        List<CustomViewDetails> list = null;
        String indicator = "";
        DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CUSTOM_VIEW_MASTER_SID, viewName));
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.LEVEL_NO, levelNo));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
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

    public static int getIndicatorCount(int viewName) {
        List<CustomViewDetails> list = new ArrayList<>();
        DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CUSTOM_VIEW_MASTER_SID, viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
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
        String query = StringUtils.EMPTY;
        query = query + "JOIN ST_M_DISCOUNT_PROJ_MASTER D ON D.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  D.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, "D");
        return query;
    }

    public static String getGroupFilterPPAQuery(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        query = query + "JOIN ST_M_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, "P");
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        query = query + "JOIN ST_M_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup + getUserSessionQueryCondition(userId, sessionId, "S");
        return query;
    }

    public static String getUserSessionQueryConditionForPR(int userId, int sessionId, String table) {
        String user = StringUtils.EMPTY;
        user = user + " " + table + ".USER_ID=" + userId + StringConstantsUtil.SMALL_AND + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String projectionDetails, String ccp) {
         String ccpWhereCond = StringUtils.EMPTY;
         ccpWhereCond = ccpWhereCond + StringConstantsUtil.SMALL_AND + ccp + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
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

    public static void saveProjectionSelection(final Map<String, String> map, final String tabName, final int projectionID) throws SystemException {

        String tableName ="CFF_SELECTION";
        StringBuilder query = new StringBuilder();
        query.append("DELETE\n" + "FROM\n" + " ").append(tableName).append("\n" + "WHERE\n" + "CFF_MASTER_SID = ").append(projectionID).append("\n" + "AND SCREEN_NAME LIKE '").append(tabName).append("';\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            query.append("INSERT \n" + "INTO\n" + "").append(tableName).append("\n" + "	(CFF_MASTER_SID, SCREEN_NAME, FIELD_NAME, FIELD_VALUES)\n" + "VALUES\n" + "	(").append(projectionID).append(", '").append(tabName).append("', '").append(entry.getKey()).append("', '").append(entry.getValue()).append("');\n");
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
    }

    public List<HelperDTO> getDropDownList(final String listType) {
        final List<HelperDTO> helperList = new ArrayList<>();
        try {
            LOGGER.debug("entering getDropDownList method with paramater listType= {}", listType);
            final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
            cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like("listName", listType), RestrictionsFactoryUtil.like("listName", "ALL")));
            cfpDynamicQuery.addOrder(OrderFactoryUtil.asc("description"));
            final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
            helperList.add(new HelperDTO(0, "-Select One-"));
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {

                    final HelperTable helperTable = (HelperTable) list.get(i);
                    helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                            helperTable.getDescription()));
                }
            }

            LOGGER.debug("getDropDownList method ends with return value strList size = {}", helperList.size());

        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return helperList;
    }

    public static Map editProjectionResults(final String tabName, final ProjectionSelectionDTO projectionSelectionDTO) throws  SystemException {
        String tableName = "CFF_SELECTION";
        StringBuilder query = new StringBuilder();
        query.append("SELECT FIELD_NAME, FIELD_VALUES FROM ").append(tableName).append("\n" + "WHERE CFF_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append("\n AND SCREEN_NAME LIKE '").append(tabName).append("';\n");
        List<Object[]> resultlist = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
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
            DynamicQuery query = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("hierarchyLevelDefinitionSid", hierarchyLevelId));
            ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
            projectionListFrom.add(ProjectionFactoryUtil.property("levelName"));
            projectionListFrom.add(ProjectionFactoryUtil.property(StringConstantsUtil.LEVEL_NO));
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
            DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq(StringConstantsUtil.CUSTOM_VIEW_MASTER_SID, customId));
            query.addOrder(OrderFactoryUtil.asc(StringConstantsUtil.LEVEL_NO));
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
            List<Object> list = (List<Object>) executeSelectQuery(query);
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
    
    public static List<Leveldto> getCustomHierarchy(int masterSid){
        List<Leveldto> listCustomValue = new ArrayList<>();
        String selectQuery="SELECT LEVEL_NO,LEVEL_NO,HIERARCHY_INDICATOR,LEVEL_NAME,HIERARCHY_ID FROM CUST_VIEW_DETAILS WHERE CUSTOM_VIEW_MASTER_SID=@SID";
        selectQuery=selectQuery.replace("@SID", String.valueOf(masterSid));
        List<Object> list = (List<Object>) executeSelectQuery(selectQuery);
        Consumer<Object> consumer=(Object t) -> {
            Object[] temparray=(Object[])t;
            Leveldto dto = getCustomizedViewMan(temparray, true);
            listCustomValue.add(dto);
        };
        list.forEach(consumer);
        return listCustomValue;
    } 

    public static String getHierarchyTreeQueryMan(int projectionId, String hierarchyIndicator, final int levelNo) {
         String customSql = StringUtils.EMPTY;
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                + " RLD.LEVEL_NAME , RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = StringConstantsUtil.FROM_SMALL + getViewTableName(hierarchyIndicator);
        customSql = customSql + selectClause + from + "  as HC,"
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + StringConstantsUtil.CFF_MASTER_WHERE + projectionId
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
            levelNo = Integer.parseInt(selectedId.substring(j - 1, j));
        } else if (j > 0) {
            levelNo = Integer.parseInt(selectedId.substring(0, j));
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
            list = (List) commonDao.executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
      
    }

    public static String getTempCCPQueryForCOGS(PVSelectionDTO pvsDTO) {
         String query = StringUtils.EMPTY;
         query = query + " DECLARE @FROM_DATE DATE\n"
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
        String query = StringUtils.EMPTY;
        query = query + " DECLARE @COUNT INT\n"
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

    public static String getTempCcpdRetrunsQuery() {

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
            LOGGER.error(ex.getMessage());
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
    public static String collectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return collectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String collectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace('[', '\'').replace(']', '\'').replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString = framedString.replace(", ", "");
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
                    LOGGER.warn("Invalid Hierarchy Indicator= {}", currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        }
        sql = sql.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        LOGGER.debug("Group Filter Value= {}", projSelDTO.getGroupFilter());
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
        String joinQuery = StringUtils.EMPTY;
        if (projSelDTO.isIsCustomHierarchy()) {
            currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(projSelDTO);
        } else {
            currentHierarchyIndicator = projSelDTO.getHierarchyIndicator();
        }

        joinQuery = joinQuery + getHierarchyJoinQuery(projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator);
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

                    LOGGER.warn("Invalid Hierarchy Indicator: {}", hierarchyIndicator);
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
        int i=1;
        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if (Integer.parseInt(entry.getValue().get(NumericConstants.TWO).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(NumericConstants.FOUR).toString()) 
                    && (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {

                    if (isNotFirstElement) {
                        stringBuilder.append(",\n");
                    }
                    stringBuilder.append("('");
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append("', ");
                    stringBuilder.append(i++);
                    stringBuilder.append( " )");

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
            List<Object[]> customDetailsList = null;
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
                    relationShipLevelQry.append("select DISTINCT LEVEL_NAME,LEVEL_NO,HIERARCHY_ID,HIERARCHY_INDICATOR from dbo.CUST_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID in (");
                    for (int i = 0; i < customDetailsList.size(); i++) {
                        relationShipLevelQry.append(customDetailsList.get(i)[0]);
                        if (i != customDetailsList.size() - 1) {
                            relationShipLevelQry.append(',');
                        }
                    }

                    relationShipLevelQry.append(')');
                    List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(relationShipLevelQry.toString());
                    /**
                     * assign null to Object , To be destroyed By JVM *
                     */
                        for (Object[] obj : list) {
                            if (obj.length > 1 && String.valueOf(obj[NumericConstants.TWO]).trim().equals(String.valueOf(obj[2]))) {
                                    Leveldto dto = new Leveldto();
                                    dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[2])));
                                    dto.setLevelNo(Integer.valueOf(String.valueOf((obj[1].toString()).trim())));
                                    dto.setLevel(String.valueOf(obj[0]));
                                    dto.setTreeLevelNo(Integer.valueOf(String.valueOf((obj[1].toString()).trim())));
                                    dto.setHierarchyIndicator(String.valueOf(obj[3]));
                                    listValue.add(dto);
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
        query = query.replace("[?HIERARCHY_COLUMN]", getColumnName(projSelDTO.getHierarchyIndicator()));
        query = query.replace("[?TAB_BASED_JOIN]", SQlUtil.getQuery("discount-join-filter"));
        query += CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO());
        query += SQlUtil.getQuery("custom-view-condition-query");
        query = query.replace("[?START]", String.valueOf(start));
        query = query.replace("[?END]", String.valueOf(end));
        query = query.replace(Constants.RELJOIN, CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO())); 
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
            count = Integer.parseInt(list.get(0).toString());
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
        String countQuery = insertAvailableHierarchyNo(session, hierarchyNo, hierarchyIndicator, levelNo, BooleanConstant.getTrueFlag(),
                customId, customerHierarchyNo,productHierarchyNo);
        countQuery += SQlUtil.getQuery("custom-view-count-query");
        countQuery = countQuery.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(BooleanConstant.getTrueFlag(), customerHierarchyNo, productHierarchyNo, hierarchyIndicator));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(countQuery, session.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(list.get(0).toString());
        }
        LOGGER.debug("Count is= {}", count);
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
        String joinQuery = StringUtils.EMPTY;
        if (isCustom) {
            currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(sessionDTO, customId, treeLevelNo);
        } else {
            currentHierarchyIndicator = hierarchyIndicator;
        }
         joinQuery = joinQuery + getHierarchyJoinQuery(isCustom, customerHierarchyNo, productHierarchyNo, currentHierarchyIndicator);
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

            count = getCountForHierarchy(sessionDTO.getHierarchyLevelDetails(), hierarchyNo, levelNo,hierarchyIndicator);          
        } else {
            throw new IllegalArgumentException("Invalid Hierarchy Indicator :" + hierarchyIndicator);
        }
        LOGGER.debug("Count is= {} ", count);
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

            if (levelNo == Integer.parseInt(levelDetailsList.get(NumericConstants.TWO).toString()) && hierarchyIndicator.equals(levelDetailsList.get(NumericConstants.FOUR).toString()) 
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
    
    public static void loadDdlbForLevelFilterOption(ComboBox ddlb, List<Leveldto> currentHierarchy, String view) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                int maxLevel = currentHierarchy.size();
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Leveldto levelDto = (Leveldto) currentHierarchy.get(i);
                    int level = view.equals(Constants.CUSTOM_LABEL) ? levelDto.getTreeLevelNo() : levelDto.getCount();
                    if (level <= maxLevel) {
                        Object itemId = null;
                        itemId = levelDto.getTreeLevelNo();
                        ddlb.addItem(itemId);
                        ddlb.setItemCaption(itemId,levelDto.getLevel());
                    }
                }
            }
        }
    }
    public static void loadDdlbForDeduction(ComboBox ddlb, List<String[]> currentHierarchy) {
        if (ddlb != null) {
            resetDdlb(ddlb);
            if (currentHierarchy != null && !currentHierarchy.isEmpty()) {
                for (int i = 0; i < currentHierarchy.size(); i++) {
                    Object[] levelValues =  currentHierarchy.get(i);
                        ddlb.addItem(DataTypeConverter.convertObjectToInt(levelValues[0]));
                        ddlb.setItemCaption(DataTypeConverter.convertObjectToInt(levelValues[0]), String.valueOf(levelValues[1]));
                }
            }
        }
    }
    
    public ComboBox loadItemUomConversionDdlb(SessionDTO session,ComboBox unitOfMeasureDdlb) {
        List<String> uomList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(SQlUtil.getQuery("uom-dropdown-loading").replace("?CFFMASTERSID", String.valueOf(session.getProjectionId())), session.getCurrentTableNames()));
        unitOfMeasureDdlb.addItem(EACH);
        for (String uomCode : uomList) {
            unitOfMeasureDdlb.addItem(uomCode);
        }
        return unitOfMeasureDdlb;
    }
    
    public static void updateForFilter(PVSelectionDTO projectionDTO, String indicator) {
        String uomQuery = StringUtils.EMPTY;
        String updateAllQuery = "SALES".equals(indicator) ? "UPDATE ST_NM_SALES_PROJECTION_MASTER SET FILTER_CCPD=null ;" : "UPDATE ST_CCP_DEDUCTION_HIERARCHY SET FILTER_CCPD=null ;";
        boolean isCustomer = false;
        boolean isProduct = false;
        boolean isDeduction = false;
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateAllQuery, projectionDTO.getSessionDTO().getCurrentTableNames()));
        if (!projectionDTO.getCustomerLevelFilter().isEmpty()) {
            uomQuery += SQlUtil.getQuery("update-Customer").replace(BUILDERSID, projectionDTO.getSessionDTO().getCustRelationshipBuilderSid())
                    .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projectionDTO.getSessionDTO().getCustomerRelationVersion()))
                    .replace("@RELATIONSIDS", projectionDTO.getCustomerLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            isCustomer = true;
        }
        if (!projectionDTO.getProductLevelFilter().isEmpty()) {
            uomQuery += SQlUtil.getQuery("update-Product").replace(BUILDERSID, projectionDTO.getSessionDTO().getProdRelationshipBuilderSid())
                    .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projectionDTO.getSessionDTO().getProductRelationVersion()))
                    .replace("@RELATIONSIDS", projectionDTO.getProductLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            isProduct = true;
        }
        if (!projectionDTO.getDeductionLevelFilter().isEmpty() && "DEDUCTION".equals(indicator)) {
            uomQuery += SQlUtil.getQuery(StringConstantsUtil.DEDUCTION_FILTER_QUERY).replace(StringConstantsUtil.DEDRELBUILDSID, projectionDTO.getSessionDTO().getDedRelationshipBuilderSid())
                    .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projectionDTO.getSessionDTO().getDeductionRelationVersion()))
                    .replace(StringConstantsUtil.DEDLEVELVALUES, projectionDTO.getDeductionLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            isDeduction = true;
        }

        if (!uomQuery.isEmpty()) {
            uomQuery += SQlUtil.getQuery("update-Filter-CCP");
            if (isCustomer) {
                uomQuery += " JOIN #CUSTOMER CUS ON ST.CUST_HIERARCHY_NO LIKE CUS.HIERARCHY_NO + '%' ";
            }
            if (isProduct) {
                uomQuery += " JOIN #PRODUCT PRO ON ST.PROD_HIERARCHY_NO LIKE PRO.HIERARCHY_NO + '%' ";
            }

            uomQuery += "SALES".equals(indicator) ? "JOIN ST_NM_SALES_PROJECTION_MASTER SPM ON SPM.CCP_DETAILS_SID = ST.CCP_DETAILS_SID " : " JOIN ST_CCP_DEDUCTION_HIERARCHY SPM ON SPM.CCP_DETAILS_SID = ST.CCP_DETAILS_SID ";

            if (isDeduction) {
                uomQuery += " JOIN  #HIER_DEDUCTION_PROD PRO1 ON SPM.DEDUCTION_HIERARCHY_NO LIKE PRO1.HIERARCHY_NO + '%' ";
            }

        } else {
            uomQuery += " UPDATE ST_CCP_DEDUCTION_HIERARCHY SET FILTER_CCPD = 1 ";
        }
        updateAllQuery += uomQuery;
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateAllQuery, projectionDTO.getSessionDTO().getCurrentTableNames()));

    }
    
     public static void loadCustomMenuBar(List<Object[]> listOfLevelFilter,CustomMenuBar.CustomMenuItem filterValues)  {
        String newLevel;
        String oldLevel = StringUtils.EMPTY;
        StringBuilder listOfSids = new StringBuilder();
        CustomMenuBar.CustomMenuItem[] customerlevelItem = new CustomMenuBar.CustomMenuItem[listOfLevelFilter.size()];
        customerlevelItem[0] = filterValues.addItem(new MenuItemDTO(listOfLevelFilter.get(0)[0], listOfLevelFilter.get(0)[1].toString()), null);
        customerlevelItem[0].setCheckable(true);
        customerlevelItem[0].setItemClickable(true);
        customerlevelItem[0].setItemClickNotClosable(true);
        customerlevelItem[0].setCheckAll(true);
        for (int i = 1; i < listOfLevelFilter.size(); i++) {
            MenuItemDTO dto = null;
            Object[] obj = listOfLevelFilter.get(i);
            newLevel = obj[0].toString();
            if (oldLevel.equals(newLevel)) {
                listOfSids.append(",").append(obj[1]);
                oldLevel = newLevel;
            } else {
                if (i != 1) {
                    dto = new MenuItemDTO(listOfSids, oldLevel);
                    listOfSids = new StringBuilder("");
                    customerlevelItem[i] = filterValues.addItem(dto, null);
                    customerlevelItem[i].setCheckable(true);
                    customerlevelItem[i].setItemClickable(true);
                    customerlevelItem[i].setItemClickNotClosable(true);
                }
                listOfSids.append(obj[1]);
                oldLevel = newLevel;
            }
            if (i == listOfLevelFilter.size() - 1) {
                dto = new MenuItemDTO(listOfSids, newLevel);
                customerlevelItem[i] = filterValues.addItem(dto, null);
                customerlevelItem[i].setCheckable(true);
                customerlevelItem[i].setItemClickable(true);
                customerlevelItem[i].setItemClickNotClosable(true);
            }
        }
    }
     
     public static void loadUnitOfMeasureDdlb(ComboBox unitOfMeasureDdlb,SessionDTO session) {
        unitOfMeasureDdlb.setNullSelectionAllowed(true);
        unitOfMeasureDdlb =new CommonLogic().loadItemUomConversionDdlb(session,unitOfMeasureDdlb);
        unitOfMeasureDdlb.setNullSelectionItemId(EACH);
        unitOfMeasureDdlb.select(EACH);
        unitOfMeasureDdlb.markAsDirty();
        unitOfMeasureDdlb.setDescription((String) (unitOfMeasureDdlb.getValue() == null ? ConstantsUtils.SELECT_ONE : unitOfMeasureDdlb.getValue()));
        
    }
     
	@SuppressWarnings("unchecked")
	public static List<Object[]> getCustomerLevelValues(int projectionId, String levelNo, PVSelectionDTO projDto) {
        List<Object[]> stockList = new ArrayList<>();
        List tableFieldNameList = new ArrayList<>();
        try {
			GtnForecastHierarchyInputBean inputBean = createInputForWebservice(projDto, levelNo, "C");
            tableFieldNameList = (List) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil.getQuery("sales-filter-customer")
                    .replace(StringConstantsUtil.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
							.replace(LEVEL_CAPS, levelNo));
          if(!tableFieldNameList.isEmpty()){
            
				String userDefined = userDefinedLevel(projectionId, levelNo, "C");
            String fullUserDefinedQuery = SQlUtil.getQuery("user-defined-level-values").replace(StringConstantsUtil.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
						.replace(LEVEL_CAPS, levelNo);
            String formedQuery;
            String query = fullUserDefinedQuery;
            boolean isuserDefined = "User Defined".equals(userDefined);
            if (!isuserDefined) {
					formedQuery = getQueryForLoadingDiscount(inputBean);
                  query = formedQuery;
            }
            query = projDto.getProductLevelFilter().isEmpty() ? query : 
                    (SQlUtil.getQuery("product-dynamic-filter").replace(StringConstantsUtil.LEVELVALUES, projDto.getProductLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                            .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projDto.getSessionDTO().getProductRelationVersion()))
								.replace(StringConstantsUtil.RELBUILDSID,
										projDto.getSessionDTO().getProdRelationshipBuilderSid())
								+ query);
            
            query = projDto.getDeductionLevelFilter().isEmpty() ? query : 
                    (SQlUtil.getQuery(StringConstantsUtil.DEDUCTION_FILTER_QUERY).replace(StringConstantsUtil.DEDLEVELVALUES, projDto.getDeductionLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                            .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projDto.getSessionDTO().getDeductionRelationVersion()))
								.replace(StringConstantsUtil.DEDRELBUILDSID,
										projDto.getSessionDTO().getDedRelationshipBuilderSid())
								+ query);
				StringBuilder sb = new StringBuilder(query);
				String whereString = StringConstantsUtil.WHERE_CAPS;
				if (!projDto.getProductLevelFilter().isEmpty()) {
					sb.insert(query.lastIndexOf(whereString),
							" JOIN #HIER_PRODUCT HP ON ST_CCP_HIERARCHY.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' ");
				}

				if (!projDto.getDeductionLevelFilter().isEmpty()) {
					sb.insert(query.lastIndexOf(whereString),
							" JOIN ST_CCP_DEDUCTION_HIERARCHY SDPM ON SDPM.CCP_DETAILS_SID=ST_CCP_HIERARCHY.CCP_DETAILS_SID  JOIN #HIER_DEDUCTION_PROD HD ON SDPM.DEDUCTION_HIERARCHY_NO LIKE HD.HIERARCHY_NO+'%' ");
				}
            
				stockList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(
						QueryUtil.replaceTableNames(sb.toString(), projDto.getSessionDTO().getCurrentTableNames()));
            }


        } catch (SystemException | PortalException ex) {
            LOGGER.error(ex.getMessage());
        }
        return stockList;
    }

	public static String getQueryForLoadingDiscount(GtnForecastHierarchyInputBean inputBean) {
		GtnWsForecastRequest forecastDiscountRequest = new GtnWsForecastRequest();
		forecastDiscountRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastDiscountRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_HIERARCHY_CONTROL
						+ GtnWebServiceUrlConstants.GTN_QUERY_FOR_TABLENAME_HIERARCHY_TYPE,
				request, getGsnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();

	}

	public static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID);
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}
	private static GtnForecastHierarchyInputBean createInputForWebservice(PVSelectionDTO projDto, String levelNo,
			String hieIndicator) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setLevelNo(Integer.parseInt(levelNo));
		inputBean.setProjectionId(projDto.getSessionDTO().getProjectionId());
		inputBean.setHierarchyIndicator(hieIndicator);
		inputBean.setCff(true);
		return inputBean;
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> getProductLevelValues(int projectionId, String type, PVSelectionDTO projectionDto) {
        List stockList = new ArrayList<>();
        try {
            String userDefined= userDefinedLevel(projectionId, type,"P");
            
			GtnForecastHierarchyInputBean inputBean = createInputForWebservice(projectionDto, type, "P");

            String query = "User Defined".equals(userDefined) ?
                        SQlUtil.getQuery("user-defined-level-values").replace(StringConstantsUtil.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
                        .replace(LEVEL_CAPS, type):
					getQueryForLoadingDiscount(inputBean);
            
            query = projectionDto.getCustomerLevelFilter().isEmpty() ? query : 
                    (SQlUtil.getQuery("customer-dynamic-filter").replace(StringConstantsUtil.LEVELVALUES, projectionDto.getCustomerLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                            .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projectionDto.getSessionDTO().getCustomerRelationVersion()))
							.replace(StringConstantsUtil.RELBUILDSID,
									projectionDto.getSessionDTO().getCustRelationshipBuilderSid())
							+ query);
           
            query = projectionDto.getDeductionLevelFilter().isEmpty() ? query : 
                    (SQlUtil.getQuery(StringConstantsUtil.DEDUCTION_FILTER_QUERY).replace(StringConstantsUtil.DEDLEVELVALUES, projectionDto.getDeductionLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                            .replace(StringConstantsUtil.RELATION_VER, String.valueOf(projectionDto.getSessionDTO().getDeductionRelationVersion()))
							.replace(StringConstantsUtil.DEDRELBUILDSID,
									projectionDto.getSessionDTO().getDedRelationshipBuilderSid())
							+ query);
            
			StringBuilder sb = new StringBuilder(query);
			if (!projectionDto.getCustomerLevelFilter().isEmpty()) {
				sb.insert(query.lastIndexOf(StringConstantsUtil.WHERE_CAPS),
						" JOIN #HIER_CUST HP ON ST_CCP_HIERARCHY.CUST_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' ");
			}

			if (!projectionDto.getDeductionLevelFilter().isEmpty()) {
				sb.insert(query.lastIndexOf(StringConstantsUtil.WHERE_CAPS),
						" JOIN ST_CCP_DEDUCTION_HIERARCHY SDPM ON SDPM.CCP_DETAILS_SID=ST_CCP_HIERARCHY.CCP_DETAILS_SID  JOIN #HIER_DEDUCTION_PROD HD ON SDPM.DEDUCTION_HIERARCHY_NO LIKE HD.HIERARCHY_NO+'%' ");
			}

			stockList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(
					QueryUtil.replaceTableNames(sb.toString(), projectionDto.getSessionDTO().getCurrentTableNames()));

        } catch (SystemException | PortalException ex) {
            LOGGER.error(ex.getMessage());
        }
        return stockList;
    }
    
	@SuppressWarnings({ "unchecked" })
	public static List<Object[]> getDeductionLevelValues(String type, PVSelectionDTO projectionDto) {
        List dedValuesList = new ArrayList<>();
        StringBuilder queryLevel=new StringBuilder();
		String selectClause = "  HT.DESCRIPTION,HT.HELPER_TABLE_SID";
        String joinClause=StringUtils.EMPTY;
        String udcJoinClause=" JOIN UDCS  UDC ON UDC.MASTER_SID=RS.RS_CONTRACT_SID AND UDC.MASTER_TYPE='RS_CONTRACT' ";
        try {
            switch (Integer.parseInt(type)) {
                case 1:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_CATEGORY AND HT.HELPER_TABLE_SID <> 0 ";
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                   
                case 2:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_TYPE  AND HT.HELPER_TABLE_SID <> 0 ";
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                case 3:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                case 4:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC1 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC1'";
                    break;

                case 5:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC2 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC2'";
                    break;
                case 6:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC3 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC3'";
                    break;
                case 7:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC4 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC4'";
                    break;
                case 8:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC5 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC5'";
                    break;
                case 9:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC6 AND HT.HELPER_TABLE_SID <> 0 AND HT.LIST_NAME = 'RS_UDC6'";
                    break;
                case 10:
                    selectClause = " RS.RS_ID,RS.RS_NAME,RS.RS_CONTRACT_SID ";
                    joinClause = StringUtils.EMPTY;
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                default:
                    break; 

            }
            queryLevel.append("SELECT ").append(selectClause).append(" FROM ST_CCP_DEDUCTION_HIERARCHY DPM ");
            queryLevel.append(" JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID ").append(udcJoinClause).append(joinClause);
            queryLevel.append(" JOIN ST_CCP_HIERARCHY CCP ON CCP.CCP_DETAILS_SID=DPM.CCP_DETAILS_SID  ");

            if (!projectionDto.getProductLevelFilter().isEmpty()) {
                String oldCustQuery=queryLevel.toString();
                queryLevel=new StringBuilder();
                oldCustQuery = SQlUtil.getQuery("product-dynamic-filter") + oldCustQuery + " JOIN #HIER_PRODUCT HP ON CCP.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' ";
                oldCustQuery = oldCustQuery.replace(StringConstantsUtil.RELATION_VER,
						String.valueOf(projectionDto.getSessionDTO().getProductRelationVersion()));
                oldCustQuery= oldCustQuery.replace(StringConstantsUtil.LEVELVALUES,projectionDto.getProductLevelFilter().toString().replace("[", "").replace("]", "")).replace(StringConstantsUtil.RELBUILDSID, projectionDto.getSessionDTO().getProdRelationshipBuilderSid());
                queryLevel.append(oldCustQuery);
            }
            if (!projectionDto.getCustomerLevelFilter().isEmpty()) {
                String oldProdQuery=queryLevel.toString();
                queryLevel=new StringBuilder();
                oldProdQuery= SQlUtil.getQuery("customer-dynamic-filter")+oldProdQuery+" JOIN #HIER_CUST HC ON CCP.CUST_HIERARCHY_NO LIKE HC.HIERARCHY_NO+'%' ";
				oldProdQuery = oldProdQuery.replace(StringConstantsUtil.RELATION_VER,
						String.valueOf(projectionDto.getSessionDTO().getCustomerRelationVersion()));
                oldProdQuery= oldProdQuery.replace(StringConstantsUtil.LEVELVALUES,projectionDto.getCustomerLevelFilter().toString().replace("[", "").replace("]", "")).replace(StringConstantsUtil.RELBUILDSID, projectionDto.getSessionDTO().getCustRelationshipBuilderSid());
                queryLevel.append(oldProdQuery);
            }

            queryLevel.append(" GROUP BY ").append(selectClause);
            
            dedValuesList = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(queryLevel.toString(),projectionDto.getSessionDTO().getCurrentTableNames()));

        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return dedValuesList;
    }

    public static String userDefinedLevel(int projectionId, String type,String indicator) throws PortalException {
        String hierarchySid=indicator.equals("P")?"PRODUCT_HIERARCHY_SID":"CUSTOMER_HIERARCHY_SID";
        List<String> userDefinedList= (List<String>) HelperTableLocalServiceUtil.executeSelectQuery(SQlUtil.getQuery("user-defined-join")
                .replace(StringConstantsUtil.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
                .replace(LEVEL_CAPS, type).replace(StringConstantsUtil.HIERARCHY_SID_AT, hierarchySid));
       
        return String.valueOf(userDefinedList.get(0));
    }

    public static void checkMenuBarItem(CustomMenuBar.CustomMenuItem customMenuItem, String obj) {
        if (customMenuItem != null && customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.getMenuItem().getWindow().toString().trim().equalsIgnoreCase(obj.trim())) {
                    object.setChecked(true);
                }
            }
        }
    } 
    
    
    
    public static Map<String, List<Object>> getFilterValues(CustomMenuBar.CustomMenuItem filterValues) {
        Map<String, List<Object>> filterMap = new HashMap<>();
        List<Object> valueList = new ArrayList<>();
        List<Object> captionDataList = new ArrayList<>();
        if (filterValues != null && filterValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> itemData = filterValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> iteratorData = itemData.iterator(); iteratorData.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem = iteratorData.next();
                if (customMenuItem.isChecked() && !String.valueOf(customMenuItem.getMenuItem().getWindow()).equals("0")) {
                    valueList.add(customMenuItem.getMenuItem().getWindow());
                    captionDataList.add(customMenuItem.getMenuItem().getCaption());
                }
            }
        }
        filterMap.put("SID", valueList);
        filterMap.put("CAPTION", captionDataList);
        return filterMap;
    }
    
    public static void resetDdlb(ComboBox ddlb) {
        ddlb.removeAllItems();
        ddlb.addItem(Constants.CommonConstants.SELECT_ONE.getConstant());
        ddlb.setNullSelectionAllowed(true);
        ddlb.setImmediate(true);
        ddlb.setNullSelectionItemId(Constants.CommonConstants.SELECT_ONE.getConstant());
    }
    
       /**
     * Get DEDUCTION 
     *
     * @param projectionId
     * @return list
     */
    public static List<String[]> getDeductionLevel(int projectionId) {
        List<String[]> deductionList = new ArrayList<>();
        try {
            LOGGER.debug("projectionId= {}", projectionId);
            String levelQuery = SQlUtil.getQuery("deduction-loading").replace("@PROJID", String.valueOf(projectionId));
            deductionList = (List<String[]>) HelperTableLocalServiceUtil.executeSelectQuery(levelQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return deductionList;
    }
    

    @SuppressWarnings("unchecked")
	public List<Object[]> displayFormatValues() {
        String query = "SELECT HELPER_TABLE_SID,DESCRIPTION FROM dbo.HELPER_TABLE WHERE LIST_NAME = 'DISPLAY_FORMAT' ORDER BY CREATED_DATE;";
        return (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }
    
    public void loadDisplayFormat(List<Object[]> listOfLevelFilter, CustomMenuBar.CustomMenuItem filterValues) {
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[listOfLevelFilter.size()];
        for (int i = 0; i < listOfLevelFilter.size(); i++) {
            Object[] obj = listOfLevelFilter.get(i);
            MenuItemDTO dto = new MenuItemDTO(i, obj[1].toString());
            customItem[i] = filterValues.addItem(dto, null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
            customItem[i].setChecked(true);
        }
    }
    
    public String getSelectedHierarchyDeduction(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo,ProjectionSelectionDTO projSelDTO) {

        if (levelNo == 0) {
            throw new IllegalArgumentException("Invalid Level No:" + levelNo);
        } 

        Map<String, List> relationshipLevelDetailsMap = !projSelDTO.isIsCustomHierarchy() ? sessionDTO.getHierarchyLevelDetails() : sessionDTO.getCustomDescription();
        StringBuilder stringBuilder = new StringBuilder();
        String hierarchyForLevel=StringUtils.EMPTY;
        boolean isNotFirstElement = false;
        boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo) || "D".equals(hierarchyIndicator);
        int i=1;
        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if ((isHierarchyNoNotAvailable) && (Integer.parseInt(entry.getValue().get(2).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(4).toString()))) {

                if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(entry.getKey());
                hierarchyForLevel=hierarchyForLevel.concat(entry.getKey()).concat(Constants.COMMA);
                stringBuilder.append("', ");
                stringBuilder.append(i++);
                stringBuilder.append( " )");

                isNotFirstElement = true;
            }
        }
        if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
            stringBuilder.append("('");
            stringBuilder.append("')");
        }
         hierarchyForLevel=hierarchyForLevel.substring(0, hierarchyForLevel.lastIndexOf(','));
        sessionDTO.setLevelHierarchyNo(hierarchyForLevel);
        return stringBuilder.toString();
    }
    
    public String getDedCustomJoinGenerate(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {
        StringBuilder columnsName = new StringBuilder();
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnsName.append(RELATIONSHIPJOIN);
            columnsName.append(sessionDTO.getCustRelationshipBuilderSid());
            columnsName.append(RELATIONSHIPVERSION);
            columnsName.append(sessionDTO.getCustomerRelationVersion());
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnsName.append(RELATIONSHIPJOIN);
            columnsName.append(sessionDTO.getProdRelationshipBuilderSid());
            columnsName.append(RELATIONSHIPVERSION);
            columnsName.append(sessionDTO.getProductRelationVersion());
        } else {
            String parentHierNo = replacePercentHierarchy(hierarchyNo);
            columnsName.append(" JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.relationship_level_values=A.HIERARCHY_NO AND LEVEL_NO = ").append(levelNo)
                    .append(" AND RLD.PARENT_HIERARCHY_NO LIKE '").append(parentHierNo).append(RELATIONSHIP_BUILDER_SID).append(sessionDTO.getDedRelationshipBuilderSid())
                    .append(" AND VERSION_NO = ").append(sessionDTO.getDeductionRelationVersion())
                    .append(" JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n ")
                    .append(" AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'");
        }
        return columnsName.toString();
    }
    
    public static String getRelJoinGenerate(String hierarchyIndicator,SessionDTO sessionDTO) {
        StringBuilder columnRelName = new StringBuilder();
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnRelName.append(RELATIONSHIPJOIN);
            columnRelName.append(sessionDTO.getCustRelationshipBuilderSid());
            columnRelName.append(RELATIONSHIPVERSION);
            columnRelName.append(sessionDTO.getCustomerRelationVersion());
            
           
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnRelName.append(RELATIONSHIPJOIN);
            columnRelName.append(sessionDTO.getProdRelationshipBuilderSid());
            columnRelName.append(RELATIONSHIPVERSION);
            columnRelName.append(sessionDTO.getProductRelationVersion());
            
        } else {
            columnRelName.append(" JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.RELATIONSHIP_LEVEL_VALUES = A.HIERARCHY_NO AND RELATIONSHIP_BUILDER_SID =");       
            columnRelName.append(sessionDTO.getDedRelationshipBuilderSid());
            columnRelName.append(RELATIONSHIPVERSION);
            columnRelName.append(sessionDTO.getDeductionRelationVersion());
        }
        return columnRelName.toString();
    }
    
     public static void loadCustomMenuBarFoScheduleID(List<Object[]> listOfLevelFilter,CustomMenuBar.CustomMenuItem filterValues)  {
        String oldLevel = StringUtils.EMPTY;
        StringBuilder listOfSids = new StringBuilder();
        CustomMenuBar.CustomMenuItem[] customerlevelItem = new CustomMenuBar.CustomMenuItem[listOfLevelFilter.size()];
        customerlevelItem[0] = filterValues.addItem(new MenuItemDTO(listOfLevelFilter.get(0)[0], listOfLevelFilter.get(0)[1].toString()), null);
        customerlevelItem[0].setCheckable(true);
        customerlevelItem[0].setItemClickable(true);
        customerlevelItem[0].setItemClickNotClosable(true);
        customerlevelItem[0].setCheckAll(true);
        for (int i = 1; i < listOfLevelFilter.size(); i++) {
            MenuItemDTO dto = null;
            Object[] obj = listOfLevelFilter.get(i);
            String firstIndex = obj[0].toString();
            String secondIndex = obj[1].toString();
            String newLevel = firstIndex + " - " + secondIndex;
         
                if (i != 1) {
                    dto = new MenuItemDTO(listOfSids, oldLevel);
                     listOfSids = new StringBuilder("");
                    customerlevelItem[i] = filterValues.addItem(dto, null);
                    customerlevelItem[i].setCheckable(true);
                    customerlevelItem[i].setItemClickable(true);
                    customerlevelItem[i].setItemClickNotClosable(true);
                }
                listOfSids.append(obj[2]);
                oldLevel = newLevel;
        
            if (i == listOfLevelFilter.size() - 1) {
                dto = new MenuItemDTO(listOfSids, newLevel);
                customerlevelItem[i] = filterValues.addItem(dto, null);
                customerlevelItem[i].setCheckable(true);
                customerlevelItem[i].setItemClickable(true);
                customerlevelItem[i].setItemClickNotClosable(true);
            }
        }
    }
     
     public String replacePercentHierarchy(String hierarchyNo) {
         String percentHierarchy;
        if (StringUtils.isEmpty(hierarchyNo)) {
            percentHierarchy = "%";
        } else {
            percentHierarchy = hierarchyNo.contains("~") ? '%'+hierarchyNo.replace('~','%')+'%' : '%'+hierarchyNo+'%';
        }
        return percentHierarchy;
     }
      
public static Date fromDateIsNull(Date fromDate) {
        if (fromDate == null) {
            Calendar calendarFromPeriod = Calendar.getInstance();
            calendarFromPeriod.set(Calendar.YEAR, (calendarFromPeriod.get(Calendar.YEAR) - 3));
            calendarFromPeriod.set(Calendar.MONTH, 0);
            calendarFromPeriod.set(Calendar.DAY_OF_MONTH, 1);
            return calendarFromPeriod.getTime();
        }
        return fromDate;
    }
    public static Date toDateIsNull(Date toDate) {
        if (toDate == null) {
            Calendar calendatToPeriod = Calendar.getInstance();
            calendatToPeriod.set(Calendar.YEAR, (calendatToPeriod.get(Calendar.YEAR) + 3));
            calendatToPeriod.set(Calendar.MONTH, 11);
            calendatToPeriod.set(Calendar.DAY_OF_MONTH, calendatToPeriod.getActualMaximum(Calendar.DAY_OF_MONTH));
          return calendatToPeriod.getTime();
        }
        return toDate;
    }
    
    public static List<String[]> getDeductionLevelForDataSelection() {
        List<String[]> deductionList = new ArrayList<>();
        try {
            String levelQuery = SQlUtil.getQuery("deduction-loading-DataSelection");
            deductionList = (List<String[]>) HelperTableLocalServiceUtil.executeSelectQuery(levelQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        deductionList.add(0, new String[]{"0",ConstantsUtils.SELECT_ONE});
        return deductionList;
    }
        }
    

