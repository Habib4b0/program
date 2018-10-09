/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jayaram
 */
public class SPRCommonLogic {

    /**
     * CommonDAO
     */
    private static final CommonDAO commonDao = new CommonDAOImpl();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    private final CommonLogic commonLogic = new CommonLogic();
    public static final String JOIN_SPACE = " JOIN ";
    public static final String LEVEL_NO_PROPERTY = "levelNo";
    public static final String IS_NULL = "Isnull(";
    /**
     * The Constant LOGGER.
     */
	public static final Logger LOGGER = LoggerFactory.getLogger(SPRCommonLogic.class);

        public static String getViewTableName(String hierarchyIndicator) {
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public static String getLevelNo(int levelNo) {
        String str = StringUtils.EMPTY;
        if (levelNo != 0) {
            str = "AND RLD1.LEVEL_NO = " + levelNo;
        }
        return str;
    }

    /**
     * Gets the Customized View
     *
     * @param levelDef
     * @param hierarchyId
     * @param hierarchyIndicator
     * @return
     */
    public static Leveldto getCustomizedView(Object[] obj, boolean isHierarchy) {
        Leveldto dtoCustomizedDto = new Leveldto();
        dtoCustomizedDto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dtoCustomizedDto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
        dtoCustomizedDto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.TWO]));
        dtoCustomizedDto.setLevel(String.valueOf(obj[NumericConstants.THREE]));
        if (isHierarchy) {
            dtoCustomizedDto.setHierarchyId(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
        } else {
            dtoCustomizedDto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.FOUR]));
            dtoCustomizedDto.setParentNode(String.valueOf(obj[NumericConstants.FIVE]));
            dtoCustomizedDto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
        }
        return dtoCustomizedDto;
    }

    public static Object executeSelectQuery(String query) {
        return commonDao.executeSelectQuery(query);

    }

    /**
     * Procedure Call
     *
     * @param procedureName
     * @param orderedArgs
     * @return
     */
    public static List<Object[]> callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name= {} " , procedureName);
        DataSource datasource = null;
        ResultSet rs = null;
        List<Object[]> objectList = new ArrayList<>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex) {
            LOGGER.debug(ex.getMessage());
        }
        if (datasource != null) {
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
            procedureToCall.replace(procedureToCall.lastIndexOf(CommonUtil.COMMA),
                    procedureToCall.lastIndexOf(CommonUtil.COMMA) + 1, StringUtils.EMPTY);
            procedureToCall.append('}');
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall(procedureToCall.toString())) {
                for (int i = 0; i < noOfArgs; i++) {
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                rs = statement.executeQuery();
                objectList = convertResultSetToList(rs);

            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return objectList;
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

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator) {
        List<Leveldto> newLevelList = new ArrayList<>();

        try {
            String query = getAllHierarchyLevelsQuery(startLevelNo, projectionId, hierarchyIndicator, StringUtils.EMPTY,
                    0, 0);
            List<Object> list = (List<Object>) executeSelectQuery(query);
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

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator,
            String userGroup, int userId, int sessionId) {
        String finalQuery = StringUtils.EMPTY;
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId,
                sessionId);

        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo,
            String userGroup, int userId, int sessionId) {

        String customSql;
        String tableName = getViewTableName(hierarchyIndicator);
        String mainSelect = "SELECT HLD.level_no, HLD.level_no as TREE_LEVEL_NO,'" + hierarchyIndicator
                + "' as HIERARCHY_INDICATOR,HLD.LEVEL_NAME,HLD.relationship_level_values,HLD.PARENT_NODE,HLD.HIERARCHY_NO ";
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,"
                + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR ,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,"
                + "'" + hierarchyIndicator + "'"
                + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD  JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " "
                + getGroupFilterQuery(userGroup, userId, sessionId) + ") CCPMAP,";
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + tableName
                + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid  \n"
                + " AND PCH.projection_master_sid =" + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '%' AND RLD1.LEVEL_NO >= " + levelNo + ") HLD";
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD.hierarchy_no + '%'";

        customSql = mainSelect + Constant.FROM_SMALL + selectClause1 + Constant.FROM_SMALL + joinQuery1 + " "
                + selectClause2 + Constant.FROM_SMALL + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public void saveMandatedSRPSelection(Map map, int projectionID, String screenName) throws PortalException {
        List<MProjectionSelection> list = new ArrayList<>();
		DynamicQuery query = MProjectionSelectionLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = MProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.SAVE, "M_PROJECTION_SELECTION");
            } else {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.UPDATE, "M_PROJECTION_SELECTION");
            }
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
    }

    public void saveNMSRPSelection(Map map, int projectionID, String screenName) {
        List<NmProjectionSelection> list = new ArrayList<>();
		DynamicQuery query = NmProjectionSelectionLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.SAVE, "NM_PROJECTION_SELECTION");
            } else {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.UPDATE, "NM_PROJECTION_SELECTION");
            }
        } catch (PortalException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        }
    }

    public static int getIndicatorCount(int viewName) {
        List<CustomViewDetails> list = new ArrayList<>();
		DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return list.size();
    }

    public static String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        return Constant.AND_SMALL_SPACE + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table
                + ".SESSION_ID=" + sessionId + " \n";
    }

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        String userGroupFilterQuery = userGroup;
        if (!userGroupFilterQuery.isEmpty()) {
            if (userGroupFilterQuery.startsWith(Constant.ALL)) {
                if (userGroupFilterQuery.contains(Constant.DISCOUNT_SMALL)) {
                    userGroupFilterQuery = " like '%' ";
                    query = getGroupFilterDiscountQuery(userGroupFilterQuery, userId, sessionId);
                } else if (userGroupFilterQuery.contains(Constant.PPA_SMALL)) {
                    userGroupFilterQuery = "  like '%' ";
                    query = getGroupFilterPPAQuery(userGroupFilterQuery, userId, sessionId);
                } else if (userGroupFilterQuery.contains(Constant.SALES_SMALL)) {
                    userGroupFilterQuery = " like '%' ";
                    query = getGroupFilterSalesQuery(userGroupFilterQuery, userId, sessionId);
                }
            } else if (userGroupFilterQuery.startsWith(Constant.DISCOUNT)) {
                userGroupFilterQuery = " = '" + userGroupFilterQuery.replace(Constant.DISCOUNT, StringUtils.EMPTY) + "' ";
                query = getGroupFilterDiscountQuery(userGroupFilterQuery, userId, sessionId);
            } else if (userGroupFilterQuery.startsWith(Constant.PPA)) {
                userGroupFilterQuery = " = '" + userGroupFilterQuery.replace(Constant.PPA, StringUtils.EMPTY) + "' ";
                query = getGroupFilterPPAQuery(userGroupFilterQuery, userId, sessionId);
            } else if (userGroupFilterQuery.startsWith(Constant.SALES_WITH_HYPHEN)) {
                userGroupFilterQuery = " = '" + userGroupFilterQuery.replace(Constant.SALES_WITH_HYPHEN, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroupFilterQuery, userId, sessionId);
            }
        }
        return query;
    }

    public static String getGroupFilterDiscountQuery(String userGroup, int userId, int sessionId) {
        return "JOIN ST_M_DISCOUNT_PROJ_MASTER D ON D.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  D.USER_GROUP "
                + userGroup + getUserSessionQueryCondition(userId, sessionId, "D");
    }

    public static String getGroupFilterPPAQuery(String userGroup, int userId, int sessionId) {
        return "JOIN ST_M_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP "
                + userGroup
                + getUserSessionQueryCondition(userId, sessionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId) {
        return "JOIN ST_M_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP "
                + userGroup + getUserSessionQueryCondition(userId, sessionId, Constant.S);
    }

    public List<HelperDTO> getDropDownList(final String listType) {
        final List<HelperDTO> helperList = new ArrayList<>();
        try {
            SalesProjectionDAO dao = new SalesProjectionDAOImpl();
            LOGGER.debug("entering getDropDownList method with paramater listType= {}" , listType);
			final DynamicQuery cfpDynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
            cfpDynamicQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.like(Constant.LIST_NAME, listType),
                    RestrictionsFactoryUtil.like(Constant.LIST_NAME, "ALL")));
            cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(Constant.DESCRIPTION));
            final List<HelperTable> list = dao.getHelperTableList(cfpDynamicQuery);
            helperList.add(new HelperDTO(0, Constant.SELECT_ONE));
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    final HelperTable helperTable = (HelperTable) list.get(i);
                    helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
                }
            }

            LOGGER.debug(" getDropDownList method ends with return value strList size = {}" , helperList.size());

        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return helperList;
    }

    public static List<Object> getContractFieldValue(String definedValue) {
        String str = StringUtils.EMPTY;
        try {

            str = "select FIELD_NAME from HIERARCHY_LEVEL_DEFINITION hld,PROJECTION_MASTER pm  where \n"
                    + " LEVEL_NAME in('Customer','Trading Partner')  and pm.CUSTOMER_HIERARCHY_SID=hld.HIERARCHY_DEFINITION_SID\n"
                    + "and pm.PROJECTION_MASTER_SID=" + definedValue;
            return (List<Object>) executeSelectQuery(str);
        } catch (Exception e) {
			LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * NM Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
    public void saveReturnsSPSelection(Map map, int projectionID, String screenName) {
        List<NmProjectionSelection> list = new ArrayList<>();
		DynamicQuery query = NmProjectionSelectionLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.SAVE, "RETURNS_PROJECTION_SELECTION");
            } else {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.UPDATE,
                        "RETURNS_PROJECTION_SELECTION");
            }
        } catch (PortalException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        }
    }
}
