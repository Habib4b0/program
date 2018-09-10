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
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
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

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            boolean isFilter, boolean isGroupFilter, String levelName) {
        int count = 0;
        String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, StringUtils.EMPTY,
                StringUtils.EMPTY, isFilter, false, true, 0, 0, false, false, 0, isGroupFilter, levelName);
        List<Object> list = (List<Object>) executeSelectQuery(query);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.parseInt(String.valueOf(ob));
        }
        return count;
    }

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount,
            int start, int offset, boolean isLimit, boolean isCustom, int customId, boolean isGroupFilter,
            String levelName) {
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";

        if (hierarchyNo != null) {
            if ((!hierarchyNo.equals(StringUtils.EMPTY)) && (isExpand)) {
                whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
            }
            if ((!isFilter) && (!hierarchyNo.equals(StringUtils.EMPTY))) {
                hierarchyNo1 = hierarchyNo;

            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, " + " '" + hierarchyIndicator
                    + Constant.AS_HIERARCHY_INDICATOR_COMMA + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES," + " HLD"
                    + hierarchyIndicator.trim() + ".PARENT_NODE," + " HLD" + hierarchyIndicator.trim()
                    + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC OFFSET " + start
                        + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim()
                        + ".HIERARCHY_NO ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,"
                + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,"
                + "'" + hierarchyIndicator + "'"
                + " HIERARCHY_INDICATOR ,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid  = " + projectionId
                + ") CCPMAP ,";
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator)
                + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid  =" + projectionId;

        if (isGroupFilter) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName
                    + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' " + getLevelNo(levelNo) + ") HLD "
                    + hierarchyIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo,
                    productHierarchyNo, customerHierarchyNo);
            customSql += Constant.FROM_SMALL + customViewQuery;
        } else {
            customSql += Constant.FROM_SMALL + selectClause1 + Constant.FROM_SMALL + joinQuery1 + " " + selectClause2
                    + Constant.FROM_SMALL + joinQuery2 + " " + mainJoin + whereCond;
        }
        customSql += recordNumber;

        return customSql;
    }

    public static String getCustomViewLevelListQuery(int projectionId, int customId, String hierarchyIndicator,
            int levelNo, String productHierarchyNo, String customerHierarchyNo) {
        String productHierarchyNum = productHierarchyNo;
        String customerHierarchyNum = customerHierarchyNo;
        customerHierarchyNum += Constant.PERCENT;
        productHierarchyNum += Constant.PERCENT;
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
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = "
                + projectionId + " ) CCPMAPC" + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = "
                + projectionId + " ) CCPMAPP " + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID" + JOIN_SPACE
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON " + " CVD.CUSTOM_VIEW_MASTER_SID =" + customId
                + " AND  CVD.LEVEL_NO  like '" + customerLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
                + projectionId + " WHERE RLD2.HIERARCHY_NO  like '" + customerHierarchyNum
                + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'" + JOIN_SPACE
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON " + " CVD.CUSTOM_VIEW_MASTER_SID= " + customId
                + " AND CVD.LEVEL_NO like '" + productLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
                + projectionId + " WHERE RLD2.HIERARCHY_NO  like '" + productHierarchyNum
                + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE;
        return customViewQuery;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset,
            String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo,
            String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId,
            boolean filterDdlb, String levelName) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo,
                    customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId,
                    StringUtils.EMPTY, 0, 0, filterDdlb, levelName);

            List<Object> list = (List<Object>) executeSelectQuery(query);
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

    public static String getCCPQuery(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery;
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery = getCustomCCPQuery(projSelDTO);
        } else {
            ccpQuery = getGeneralCCPQuery(projSelDTO);
        }
        return ccpQuery;
    }

    public static String getCustomCCPQuery(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getCustomLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getCustomLevelNo();
        }
        String ccpQuery = "(SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID,HLD"
                + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO, CCPMAP" + projSelDTO.getHierarchyIndicator()
                + ".CCP_DETAILS_SID FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " ) CCPMAPC \n" + " JOIN  "
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,  RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n" + JOIN_SPACE
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER  CVM ON \n" + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId()
                + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION  HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2  ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN  PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " WHERE  RLD2.HIERARCHY_NO like '" + customerHierarchyNo
                + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE + JOIN_SPACE
                + " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n" + " CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId()
                + " AND  CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION  HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN  RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON  PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " WHERE  RLD2.HIERARCHY_NO like '" + productHierarchyNo
                + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE + " ) CCP \n";
        return ccpQuery;
    }

    public static String getGeneralCCPQuery(ProjectionSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " (SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT distinct CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID \n"
                + " WHERE PM.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + ") CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n" + JOIN_SPACE + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n" + " AND PCH.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo()
                + "%' ) HLD \n" + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n" + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n" + JOIN_SPACE + viewtable + " PCH2 \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID \n" + " AND PCH2.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ")) CCP \n";
        return ccpQuery;
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
    public static String getIndicator(int levelNo, int viewName) {
        List<CustomViewDetails> list = null;
        String indicator = StringUtils.EMPTY;
		DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, viewName));
        query.add(RestrictionsFactoryUtil.eq(LEVEL_NO_PROPERTY, levelNo));
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
        query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return list.size();
    }

    public static String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = Constant.AND_SMALL_SPACE + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table
                + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount,
            int start, int offset, boolean isLimit, boolean isCustom, int customId, String userGroup, int userId,
            int sessionId, boolean filterDdlb, String levelName) {
        String hierIndicator = hierarchyIndicator;
        if (isCustom) {

            String hierIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID="
                    + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) executeSelectQuery(hierIndicatorQuery);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierIndicator = String.valueOf(ob);
            } else {
                hierIndicator = StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if ((hierarchyNo != null) && (!hierarchyNo.equals(StringUtils.EMPTY))) {
            if (isExpand) {
                whereCond = " and HLD" + hierIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
            }
            if (!isFilter) {
                hierarchyNo1 = hierarchyNo;
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierIndicator.trim() + ".HIERARCHY_NO) ";
        } else {
            selectClause += " distinct HLD" + hierIndicator.trim() + ".LEVEL_NO, " + " HLD"
                    + hierIndicator.trim() + ".TREE_LEVEL_NO, " + " '" + hierIndicator
                    + Constant.AS_HIERARCHY_INDICATOR_COMMA + " HLD" + hierIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES," + " HLD"
                    + hierIndicator.trim() + ".PARENT_NODE," + " HLD" + hierIndicator.trim()
                    + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierIndicator.trim() + ".HIERARCHY_NO ASC OFFSET " + start
                        + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierIndicator.trim()
                        + ".HIERARCHY_NO ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO,"
                + "'" + hierIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO,"
                + "'" + hierIndicator + "'"
                + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " "
                + getGroupFilterQuery(userGroup, userId, sessionId) + ") CCPMAP,";

        String joinQuery2 = " relationship_level_definition RLD1  JOIN " + getViewTableName(hierIndicator)
                + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;

        if (filterDdlb) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE  '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName
                    + ")) HLD" + hierIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE  '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo
                    + ") HLD" + hierIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierIndicator.trim() + ".hierarchy_no + '%'";
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierIndicator, levelNo,
                    productHierarchyNo, customerHierarchyNo);
            customSql += Constant.FROM_SMALL + customViewQuery;
        } else {
            customSql += Constant.FROM_SMALL + selectClause1 + Constant.FROM_SMALL + joinQuery1 + " " + selectClause2
                    + Constant.FROM_SMALL + joinQuery2 + " " + mainJoin + whereCond;
        }
        customSql += recordNumber;
        return customSql;
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
        String query = "JOIN ST_M_DISCOUNT_PROJ_MASTER D ON D.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  D.USER_GROUP "
                + userGroup + getUserSessionQueryCondition(userId, sessionId, "D");
        return query;
    }

    public static String getGroupFilterPPAQuery(String userGroup, int userId, int sessionId) {
        String query = "JOIN ST_M_PPA_PROJECTION_MASTER P ON P.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  P.USER_GROUP "
                + userGroup
                + getUserSessionQueryCondition(userId, sessionId, Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId) {
        String query = "JOIN ST_M_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP "
                + userGroup + getUserSessionQueryCondition(userId, sessionId, Constant.S);
        return query;
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
            List<Object> list = (List<Object>) executeSelectQuery(str);
            return list;
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
