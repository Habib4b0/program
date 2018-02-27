/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.DataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.MProjectionSelection;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.CustomViewDetailsLocalServiceUtil;
import com.stpl.app.service.CustomViewMasterLocalServiceUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.MProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.RelationshipLevelDefinitionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import static com.stpl.ifs.util.constants.GlobalConstants.getGovernmentConstant;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<CustomViewMaster> getCustomViewList(int projectionId) {
        List<CustomViewMaster> list = null;
        try {
			DynamicQuery query = CustomViewMasterLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return list;
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
			LOGGER.error(ex.getMessage());
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
    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo) {
        String selectClause = "select distinct RLD.LEVEL_NO, " + " RLD.LEVEL_NO as TREE_LEVEL_NO," + "'"
                + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = Constant.FROM_SMALL + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC," + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.PROJECTION_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID AND RLD.LEVEL_NO >=" + levelNo;
        return customSql;
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

    public static Leveldto getCustomizedHierarchyView(Object[] obj, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
        dto.setLevel(String.valueOf(obj[1]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.TWO]));
            dto.setHierarchyNo(String.valueOf(obj[NumericConstants.THREE]));
        }
        return dto;
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

    public static List<Object> getLevelNoAndHierarchyNo(Object value, boolean filter) {
        List<Object> levelHierarchy = new ArrayList<>();
        String selectedId = DASH;
        if ((value != null) && (!SELECT_ONE.equals(String.valueOf(value)))) {
            selectedId = String.valueOf(value);
        }
        int levelNo = -1;
        String hierarchyNo = StringUtils.EMPTY;
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

    public static Leveldto getNextLevel(int levelNo, List<Leveldto> hierarchy) {
        for (Leveldto dto : hierarchy) {
            if (dto.getTreeLevelNo() == levelNo) {
                return dto;
            }
        }
        return null;
    }

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            boolean isFilter, boolean isGroupFilter, String levelName) {
        int count = 0;
        String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, StringUtils.EMPTY,
                StringUtils.EMPTY, isFilter, false, true, 0, 0, false, false, 0, isGroupFilter, levelName);
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
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
            if ((!hierarchyNo.equals(StringUtils.EMPTY)) && (!isFilter)) {
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
                + projectionId + " WHERE RLD2.HIERARCHY_NO  like '" + customerHierarchyNo
                + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'" + JOIN_SPACE
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON " + " CVD.CUSTOM_VIEW_MASTER_SID= " + customId
                + " AND CVD.LEVEL_NO like '" + productLevelNo + "'"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
                + projectionId + " WHERE RLD2.HIERARCHY_NO  like '" + productHierarchyNo
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

    public static String getCCPQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = getCCPTempTableQuery();
        if (projSelDTO.isIsCustomHierarchy()) {
            ccpQuery += getCustomCCPQueryForPR(projSelDTO);
        } else {
            ccpQuery += getGeneralCCPQueryForPR(projSelDTO);
        }
        return ccpQuery;
    }

    public static String getCCPTempTableQuery() {
        String tableQuery = "DECLARE @CCP TABLE\n" + "  (\n" + "     CCP_DETAILS_SID INT,\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n" + "     HIERARCHY_NO           VARCHAR(50)\n" + "  ) \n"
                + " INSERT INTO @CCP\n" + "            (RELATIONSHIP_LEVEL_SID,HIERARCHY_NO,CCP_DETAILS_SID) \n";
        return tableQuery;
    }

    public static String getCustomCCPQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getCustomLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getCustomLevelNo();
        }
        String ccpQuery = " SELECT distinct HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID,HLD"
                + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO, CCPMAP" + projSelDTO.getHierarchyIndicator()
                + ".CCP_DETAILS_SID FROM \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION  RLD \n"
                + " JOIN  CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID ="
                + projSelDTO.getProjectionId() + "\n" + " ) CCPMAPC \n" + " JOIN"
                + " (SELECT  RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION  RLD \n"
                + " JOIN  CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID ="
                + projSelDTO.getProjectionId() + "\n" + " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n" + JOIN_SPACE
                + " (SELECT  RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n" + " CVD.CUSTOM_VIEW_MASTER_SID = "
                + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID="
                + projSelDTO.getProjectionId() + "\n" + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo
                + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE + JOIN_SPACE
                + " (SELECT  RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                + " JOIN  dbo.CUSTOM_VIEW_MASTER CVM ON \n" + " CVD.CUSTOM_VIEW_MASTER_SID = "
                + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID ="
                + projSelDTO.getProjectionId() + "\n" + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo
                + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE + " \n";
        return ccpQuery;
    }

    public static String getGeneralCCPQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());
        String ccpQuery = " SELECT LCCP.RELATIONSHIP_LEVEL_SID, LCCP.HIERARCHY_NO , LCCP.CCP_DETAILS_SID from \n"
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
                + projSelDTO.getProjectionId() + "\n" + " WHERE RLD2.LEVEL_NO=" + projSelDTO.getLevelNo() + ") \n";

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

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator) {
        String finalQuery = StringUtils.EMPTY;
        finalQuery += getAllHierarchyLevelsQuery(startLevelNo, projectionId, hierarchyIndicator, StringUtils.EMPTY, 0,
                0);
        return finalQuery;
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

    public static List<List<Object>> getAllLevelNoAndHierarchyNo(Object value) {
        LOGGER.debug("getAllLevelNoAndHierarchyNo started");
        List<List<Object>> allLevelHierarchy = new ArrayList<>();
        List<Object> levelHierarchy = getLevelNoAndHierarchyNo(value, false);

        int levelNo = Integer.parseInt(String.valueOf(levelHierarchy.get(0)));
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        allLevelHierarchy.add(0, levelHierarchy);
        while (levelNo > 1) {
            List<Object> levelH = getParentLevelNoAndHierarchyNo(levelNo, hierarchyNo);
            levelNo = Integer.parseInt(String.valueOf(levelH.get(0)));
            hierarchyNo = String.valueOf(levelH.get(1));
            allLevelHierarchy.add(0, levelH);
        }
        LOGGER.debug("getAllLevelNoAndHierarchyNo ended");
        return allLevelHierarchy;
    }

    public static int getLevelIndex(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, String findHierarchyNo, boolean isCustom,
            int customId) {
        int index = 0;
        String levelIndexQuery = getLevelIndexQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo,
                productHierarchyNo, customerHierarchyNo, findHierarchyNo, isCustom, customId, StringUtils.EMPTY, 0, 0);
        List<Object> list = (List<Object>) executeSelectQuery(levelIndexQuery, null, null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            index = Integer.parseInt(String.valueOf(ob));
        }
        return index;
    }

    public static List<Object> getParentLevelNoAndHierarchyNo(int levelNo, String hierarchyNo) {
        List<Object> levelHierarchy = new ArrayList<>();
        if (StringUtils.countMatches(hierarchyNo, ".") > 1) {
            levelNo--;
            hierarchyNo = hierarchyNo.substring(0,
                    StringUtils.ordinalIndexOf(hierarchyNo, ".", StringUtils.countMatches(hierarchyNo, ".") - 1) + 1);
        } else {
            levelNo = 0;
            hierarchyNo = StringUtils.EMPTY;
        }
        levelHierarchy.add(Integer.valueOf(levelNo));
        levelHierarchy.add(hierarchyNo);
        return levelHierarchy;
    }

    public static int customDdlbOptionChange(ComboBox customDdlb, Button editBtn) {
        editBtn.setEnabled(false);
        String value = String.valueOf(customDdlb.getValue());
        if (!Constant.NULL.equals(value) && !SELECT_ONE.equals(value)) {
            int selectedId = Integer.parseInt(value);
            editBtn.setEnabled(true);
            return selectedId;
        }
        return 0;
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

    public static List getRelationshipLevels(int hierarchyLevelId) {
        List list = null;
        try {
			DynamicQuery query = RelationshipLevelDefinitionLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq("hierarchyLevelDefinitionSid", hierarchyLevelId));
            ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.LEVEL_NAME));
            projectionListFrom.add(ProjectionFactoryUtil.property(LEVEL_NO_PROPERTY));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = commonDao.getRelationshipLevels(query);
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
    public static List<CustomViewDetails> getCustomViewDetails(int customId) {
        List<CustomViewDetails> list = null;
        try {
			DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, customId));
            query.addOrder(OrderFactoryUtil.asc(LEVEL_NO_PROPERTY));
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return list;
    }

    public static CustomViewMaster getCustomView(int customViewMasterSid) throws PortalException {
        CustomViewMaster cvm = null;
        if (customViewMasterSid != 0) {
            try {
                cvm = commonDao.getCustomView(customViewMasterSid);
            } catch (PortalException | SystemException ex) {
				LOGGER.error(ex.getMessage());
            }
        }
        return cvm;
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
			DynamicQuery query = CustomViewMasterLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
            query.add(RestrictionsFactoryUtil.eq("viewName", viewName));
            if (customId != 0) {
                query.add(RestrictionsFactoryUtil.ne(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, customId));
            }

            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return list;
    }

    public static int customViewSaveLogic(SessionDTO session, int customId, String viewName, List levelList) {

        String userId1 = session.getUserId();
        int userId = 0;
        if (CommonUtils.isInteger(userId1)) {
            userId = Integer.parseInt(userId1);
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
							LOGGER.error(ex.getMessage());
                        }
                    }
                } catch (SystemException ex) {
					LOGGER.error(ex.getMessage());
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
								LOGGER.error(ex.getMessage());
                            }
                        }

                        customViewDetailsSaveLogic(customId, levelList);
                    }
                } catch (SystemException | PortalException ex) {
					LOGGER.error(ex.getMessage());
                }
            }
        }
        return customId;
    }

    public static boolean customViewDetailsSaveLogic(int customId, List levelList) throws SystemException {
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

    public static boolean editButtonValidation(ComboBox customDdlb, List<CustomViewMaster> customViewList) {
        Object value = customDdlb.getValue();
        String userId1 = (String) VaadinSession.getCurrent().getAttribute(Constant.USER_ID);
        int userId = 0;
        try {
            userId = Integer.parseInt(userId1);
        } catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
        }
        if (userId != 0) {
            int selectedId = Integer.parseInt(String.valueOf(value));
            for (CustomViewMaster custom : customViewList) {
                if ((custom.getCustomViewMasterSid() == selectedId) && (custom.getCreatedBy() == userId)) {
                    return true;
                }
            }
        }
        AbstractNotificationUtils.getErrorNotification("Edit a View",
                "You cannot edit a view that you did not create. Please select another view, or create a new one");
        return false;
    }

    public static List<String> getAllHierarchyNo(String hierarchyNo) {
        LOGGER.debug("getAllHierarchyNo started");
        List<String> allLevelHierarchy = new ArrayList<>();
        allLevelHierarchy.add(0, hierarchyNo);
        while (StringUtils.countMatches(hierarchyNo, ".") > 1) {
            hierarchyNo = getParentHierarchyNo(hierarchyNo);
            if (!hierarchyNo.isEmpty()) {
                allLevelHierarchy.add(0, hierarchyNo);
            }
        }
        LOGGER.debug("getAllHierarchyNo ended");
        return allLevelHierarchy;
    }

    public static String getParentHierarchyNo(String hierarchyNo) {
        if (StringUtils.countMatches(hierarchyNo, ".") > 1) {
            hierarchyNo = hierarchyNo.substring(0,
                    StringUtils.ordinalIndexOf(hierarchyNo, ".", StringUtils.countMatches(hierarchyNo, ".") - 1) + 1);
        } else {
            hierarchyNo = StringUtils.EMPTY;
        }
        return hierarchyNo;
    }

    /**
     * Mandated Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
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

    /**
     * NM Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
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
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        } catch (PortalException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        } catch (Exception ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        }
    }

    /**
     * Save Channels SRP
     *
     * @param projectionSelectionDTO
     * @param projectionId
     */
    public void saveCHSPRSelection(Map<String, Object> projectionSelectionDTO, int projectionId) {
        List<ChProjectionSelection> list = null;
		DynamicQuery query = ChProjectionSelectionLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, projectionSelectionDTO.get(Constant.SCREEN_NAME)));
        try {
            list = ChProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && list.isEmpty()) {
                commonLogic.saveSelection(projectionSelectionDTO, projectionId,
                        String.valueOf(projectionSelectionDTO.get(Constant.SCREEN_NAME)), Constant.SAVE,
                        "CH_PROJECTION_SELECTION");
            } else {
                commonLogic.saveSelection(projectionSelectionDTO, projectionId,
                        String.valueOf(projectionSelectionDTO.get(Constant.SCREEN_NAME)), Constant.UPDATE,
                        "CH_PROJECTION_SELECTION");
            }
        } catch (SystemException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        } catch (PortalException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        } catch (Exception ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
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
    public void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate) {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();
        if (Constant.SAVE.equalsIgnoreCase(saveOrUpdate)) {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append(
                        "INSERT INTO NM_PROJECTION_SELECTION (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES) VALUES ");
                queryBuilder.append("('").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("')\n");
            }
        } else {
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("UPDATE NM_PROJECTION_SELECTION SET FIELD_NAME = '");
                queryBuilder.append(obj[i]).append("',").append("FIELD_VALUES = '").append(map.get(obj[i])).append('\'');
                queryBuilder.append(" WHERE PROJECTION_MASTER_SID = '").append(projectionID)
                        .append(" ' AND SCREEN_NAME = '").append(screenName).append("' AND FIELD_NAME ='")
                        .append(obj[i]).append("'\n");
            }
        }
        commonDao.executeBulkUpdateQuery(queryBuilder.toString(), null, null);
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
        List<CustomViewDetails> list = null;
		DynamicQuery query = CustomViewDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return list.size();
    }

    public static ForecastConfig getTimePeriod() {
        DataSelectionDAO dataSelectionDao = new DataSelectionDAOImpl();
        List resultList = null;
        int businessProcessType = 0;
        try {
            businessProcessType = CommonUtils.getHelperCode(CommonUtils.BUSINESS_PROCESS_TYPE, getGovernmentConstant());
        } catch (PortalException | SystemException ex) {
			LoggerFactory.getLogger(SPRCommonLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
		DynamicQuery dynamicQuery = ForecastConfigLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq("businessProcessType", businessProcessType));
        dynamicQuery.addOrder(OrderFactoryUtil.desc(Constant.VERSION_NO));
        try {
            resultList = dataSelectionDao.getForecastConfig(dynamicQuery);
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        ForecastConfig forecastConfig = null;
        if (resultList != null && !resultList.isEmpty()) {
            forecastConfig = (ForecastConfig) resultList.get(0);
        }
        return forecastConfig;
    }

    public static String getHierarchyLevelsQueryForSales(int projectionId, String hierarchyIndicator, int levelNo,
            int hierarchyLevelDefId, int treeLevelNo) {

        String selectClause1 = "select RELATIONSHIP_LEVEL_SID from " + getViewTableName(hierarchyIndicator);
        selectClause1 += " where PROJECTION_MASTER_SID=" + projectionId;
        String selectClause = "select" + " LEVEL_NO, " + treeLevelNo + " as TREE_LEVEL_NO, " + "'" + hierarchyIndicator
                + Constant.AS_HIERARCHY_INDICATOR_COMMA + " LEVEL_NAME," + " RELATIONSHIP_LEVEL_VALUES,"
                + " PARENT_NODE," + " HIERARCHY_NO";
        String customSql = selectClause + " from RELATIONSHIP_LEVEL_DEFINITION where RELATIONSHIP_LEVEL_SID in ("
                + selectClause1 + ")" + " and HIERARCHY_LEVEL_DEFINITION_SID=" + hierarchyLevelDefId + " and LEVEL_NO="
                + levelNo;
        return customSql;

    }

    /**
     * Get Hierarchy
     *
     * @param projectionId
     * @param hierarchyIndicator
     * @return
     */
    public static List<Leveldto> getHierarchy(int projectionId, String hierarchyIndicator) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getHierarchyTreeQuery(projectionId, hierarchyIndicator);

            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedViewForSales(obj, true);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
			LOGGER.error(ex.getMessage());
        }
        return listValue;
    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator) {
        String selectClause = "select distinct RLD.LEVEL_NO,RLD.LEVEL_NO, " + "'" + hierarchyIndicator
                + "' HIERARCHY_INDICATOR," + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = Constant.FROM_SMALL + getViewTableName(hierarchyIndicator);
        String customSql = selectClause + from + " as HC," + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where HC.PROJECTION_MASTER_SID=" + projectionId
                + " and HC.RELATIONSHIP_LEVEL_SID=RLD.RELATIONSHIP_LEVEL_SID";
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
    public static Leveldto getCustomizedViewForSales(Object[] obj, boolean isHierarchy) {
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
        } catch (SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public void callSalesInsertProcedure(int projectionId, String userId, String sessionId, String sql) {

        GtnSqlUtil.procedureCallService(sql, new Object[]{projectionId, userId, sessionId});

    }

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId) {
        int count = 0;
        String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo,
                customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, false, StringUtils.EMPTY);
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = Integer.parseInt(String.valueOf(ob));
        }
        return count;
    }

    public String[] getTableAndFieldName(String levelName) {

        String query = "select LEVEL_NAME , \"TABLE_NAME\" ,FIELD_NAME from HIERARCHY_LEVEL_DEFINITION WHERE \n"
                + " HIERARCHY_DEFINITION_SID in ";
        String[] returnValues = new String[1];
        List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;

                if (levelName.equals(obj[0])) {
                    returnValues[0] = String.valueOf(obj[1]);
                    returnValues[1] = String.valueOf(obj[NumericConstants.TWO]);
                }

            }

        }
        return returnValues;
    }

    public static String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails,
            String CCP) {
        String ccpWhereCond = Constant.AND_SMALL_SPACE + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP
                + ".RELATIONSHIP_LEVEL_SID \n" + Constant.AND_SMALL_SPACE + CCP + ".CCP_DETAILS_SID="
                + projectionDetails + ".CCP_DETAILS_SID \n";
        return ccpWhereCond;
    }

    public static String getPeriodRestrictionQuery(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-" + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        String periodFilter = StringUtils.EMPTY;
        if (!CommonUtils.isInteger(projSelDTO.getYear())) {
            periodFilter = " and PERIOD_DATE BETWEEN CONVERT(DATE, '" + startDate + "') and CONVERT(DATE, '" + endDate
                    + "') \n";
        }

        return periodFilter;
    }

    public static String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = Constant.AND_SMALL_SPACE + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table
                + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String where) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS  ";
        String selectClause = IS_NULL + table1 + ".YEARS,  " + table2 + ".YEARS) as YEARS,  ";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS = " + table2 + ".YEARS";
        selectClause += IS_NULL + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS) ";
        finalWhereCond += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table2
                + Constant.DOT_PERIODS_QUERY;
        groupBy += ", " + table1 + Constant.DOT_PERIODS_QUERY;
        selectClause += " as PERIODS, \n";

        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static String getUserSessionQueryConditionForPR(int userId, int sessionId, String table) {
        String user = " " + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table + ".SESSION_ID=" + sessionId
                + " \n";
        return user;
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String table3,
            String where, boolean isTable3) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = IS_NULL + table1 + ".YEARS, " + table2 + ".YEARS)";
        String selectClause1 = IS_NULL + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS)";
        if (isTable3) {
            selectClause = "Isnull(Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS), " + table3 + ".YEARS)";
            selectClause1 = "Isnull(Isnull(" + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS), " + table3
                    + ".PERIODS)";
        }
        selectClause += " as YEARS, ";
        selectClause += selectClause1;
        selectClause += " as PERIODS, \n";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS=" + table2 + ".YEARS ";
        finalWhereCond += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table2 + ".PERIODS ";
        groupBy += ", " + table1 + Constant.DOT_PERIODS_QUERY;

        String finalWhereCond1 = " " + where + " " + table1 + ".YEARS=" + table3 + ".YEARS ";
        finalWhereCond1 += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table3
                + Constant.DOT_PERIODS_QUERY;
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(finalWhereCond1);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    // Added after refactor
    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset,
            String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo,
            String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId,
            String userGroup, int userId, int sessionId, boolean filterDdlb, String levelName) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo,
                    customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, userGroup,
                    userId, sessionId, filterDdlb, levelName);
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

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId,
            String userGroup, int userId, int sessionId, boolean isGroupFilter, String levelName) {
        int count = 0;
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo,
                    customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId,
                    sessionId, isGroupFilter, levelName);
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.parseInt(String.valueOf(ob));
            }
        } catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
        }
        return count;
    }

    public static int getLevelIndex(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, String findHierarchyNo, boolean isCustom,
            int customId, String userGroup, int userId, int sessionId) {
        int index = 0;
        try {
            String levelIndexQuery = getLevelIndexQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo,
                    productHierarchyNo, customerHierarchyNo, findHierarchyNo, isCustom, customId, userGroup, userId,
                    sessionId);
            List<Object> list = (List<Object>) executeSelectQuery(levelIndexQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                index = Integer.parseInt(String.valueOf(ob));
            }
        } catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
        }
        return index;
    }

    public static String getLevelIndexQuery(int projectionId, String hierarchyIndicator, int levelNo,
            String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, String findHierarchyNo,
            boolean isCustom, int customId, String userGroup, int userId, int sessionId) {
        String levelListQuery = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo,
                productHierarchyNo, customerHierarchyNo, false, false, false, 0, 0, false, isCustom, customId,
                userGroup, userId, sessionId, false, StringUtils.EMPTY);
        String selectClause = "select A.TEMP_INDEX from (" + levelListQuery + ") A where A.HIERARCHY_NO='"
                + findHierarchyNo + "'";
        return selectClause;
    }

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo,
            String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount,
            int start, int offset, boolean isLimit, boolean isCustom, int customId, String userGroup, int userId,
            int sessionId, boolean filterDdlb, String levelName) {
        if (isCustom) {

            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID="
                    + customId + " and LEVEL_NO=" + levelNo;
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
        if ((hierarchyNo != null) && (!hierarchyNo.equals(StringUtils.EMPTY))) {
            if (isExpand) {
                whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
            }
            if (!isFilter) {
                hierarchyNo1 = hierarchyNo;
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, " + " HLD"
                    + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, " + " '" + hierarchyIndicator
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
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + "'"
                + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " "
                + getGroupFilterQuery(userGroup, userId, sessionId) + ") CCPMAP,";

        String joinQuery2 = " relationship_level_definition RLD1  JOIN " + getViewTableName(hierarchyIndicator)
                + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;

        if (filterDdlb) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE  '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName
                    + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE  '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo
                    + ") HLD" + hierarchyIndicator.trim();
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

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.ALL)) {
                if (userGroup.contains(Constant.DISCOUNT_SMALL)) {
                    userGroup = " like '%' ";
                    query = getGroupFilterDiscountQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains(Constant.PPA_SMALL)) {
                    userGroup = "  like '%' ";
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
            } else if (userGroup.startsWith(Constant.SALES_WITH_HYPHEN)) {
                userGroup = " = '" + userGroup.replace(Constant.SALES_WITH_HYPHEN, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId);
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

    private static String getGroupQuery(int projectionId, int sessionId, int userId, String table) {
        String query = "SELECT DISTINCT M.USER_GROUP FROM " + table + " M"
                + "  JOIN PROJECTION_DETAILS PD ON M.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID"
                + " WHERE RLD.LEVEL_NAME = 'Trading Partner' AND M.USER_GROUP IS NOT NULL AND PD.PROJECTION_MASTER_SID ='"
                + projectionId + "' " + " AND M.USER_ID=" + userId + " AND M.SESSION_ID=" + sessionId;
        return query;
    }

    public static List<String> getDiscountGroup(int projectionId, int sessionId, int userId) {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_M_DISCOUNT_PROJ_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.DISCOUNT + String.valueOf(list1));
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
            String query = getGroupQuery(projectionId, sessionId, userId, "ST_M_PPA_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.PPA + String.valueOf(list1));
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
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.SALES_WITH_HYPHEN + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
			LOGGER.error(ex.getMessage());
        }
        return groupList;
    }

    public static List<String> getAllGroup(int projectionId, int userId, int sessionId) {
        List<String> groupList = new ArrayList<>();
        groupList.add("All Sales Group");
        groupList.add("All Discount Group");
        groupList.add("All PPA Group");
        groupList.addAll(getSalesGroup(projectionId, sessionId, userId));
        groupList.addAll(getDiscountGroup(projectionId, sessionId, userId));
        groupList.addAll(getPPAGroup(projectionId, sessionId, userId));
        return groupList;
    }

    public static int getTradingPartnerLevelNo(boolean isCustom, int projectionIdOrCustomId) {
        int levelNo = 0;
        String query = "SELECT DISTINCT ";
        if (isCustom) {
            query += " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                    + "                JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVM.CUSTOM_VIEW_MASTER_SID=CVD.CUSTOM_VIEW_MASTER_SID AND CVM.CUSTOM_VIEW_MASTER_SID="
                    + projectionIdOrCustomId + "\n"
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
                levelNo = Integer.parseInt(String.valueOf(ob));
            }
        } catch (NumberFormatException ex) {
			LOGGER.error(ex.getMessage());
        }

        return levelNo;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator,
            String userGroup, int userId, int sessionId) {
        List<Leveldto> newLevelList = new ArrayList<>();
        try {
            String query = getAllHierarchyLevelsQuery(startLevelNo, projectionId, hierarchyIndicator, userGroup, userId,
                    sessionId);
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

    public static List<Object> getLevelNoAndHierarchyNo(Object value) {
        List<Object> levelHierarchy = new ArrayList<>();
        String selectedId = DASH;
        if ((value != null) && (!SELECT_ONE.equals(String.valueOf(value)))) {
            selectedId = String.valueOf(value);
        }
        int levelNo = -1;
        String hierarchyNo = StringUtils.EMPTY;
        int j = selectedId.indexOf('~');
        if (j > 0) {
            levelNo = Integer.parseInt(selectedId.substring(0, j));
        }
        if (selectedId.length() > (j + 1)) {
            hierarchyNo = selectedId.substring(j + 1, selectedId.length());
        }
        levelHierarchy.add(Integer.valueOf(levelNo));
        levelHierarchy.add(hierarchyNo);
        return levelHierarchy;
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

        } catch (PortalException | SystemException ex) {
			LOGGER.error(ex.getMessage());
        }
        return helperList;
    }

    public static int getHelperTableSID(String listName, String description) throws PortalException, SystemException {
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
		final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.DESCRIPTION, description));
        final List<HelperTable> list = dao.getHelperTableList(dynamicQuery);
        return list != null && !list.isEmpty() ? ((HelperTable) list.get(0)).getHelperTableSid() : 0;
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
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
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

    public static List<Date> getStartAndEndDate(int startQuater, int endQuater, int startYear, int endYear) {

        List<Date> result = new ArrayList<>();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.set(Calendar.DATE, 1);
        startCal.set(Calendar.YEAR, startYear);
        endCal.set(Calendar.YEAR, endYear);
        if (startQuater == 1) {
            startCal.set(Calendar.MONTH, 0);

        } else if (startQuater == NumericConstants.TWO) {
            startCal.set(Calendar.MONTH, NumericConstants.THREE);
        } else if (startQuater == NumericConstants.THREE) {
            startCal.set(Calendar.MONTH, NumericConstants.SIX);
        } else if (startQuater == NumericConstants.FOUR) {
            startCal.set(Calendar.MONTH, NumericConstants.NINE);
        }
        if (endQuater == 1) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY_ONE);
            endCal.set(Calendar.MONTH, NumericConstants.TWO);
        } else if (endQuater == NumericConstants.TWO) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY);
            endCal.set(Calendar.MONTH, NumericConstants.FIVE);
        } else if (endQuater == NumericConstants.THREE) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY);
            endCal.set(Calendar.MONTH, NumericConstants.EIGHT);
        } else if (endQuater == NumericConstants.FOUR) {
            endCal.set(Calendar.DATE, NumericConstants.THIRTY_ONE);
            endCal.set(Calendar.MONTH, NumericConstants.ELEVEN);
        }
        result.add(startCal.getTime());
        result.add(endCal.getTime());
        return result;
    }

    public static List<Object> getContractFieldValue(String definedValue) {
        String str = StringUtils.EMPTY;
        try {

            str = "select FIELD_NAME from HIERARCHY_LEVEL_DEFINITION hld,PROJECTION_MASTER pm  where \n"
                    + " LEVEL_NAME in('Customer','Trading Partner')  and pm.CUSTOMER_HIERARCHY_SID=hld.HIERARCHY_DEFINITION_SID\n"
                    + "and pm.PROJECTION_MASTER_SID=" + definedValue;
            List<Object> list = (List<Object>) executeSelectQuery(str, null, null);
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
        } catch (SystemException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        } catch (PortalException ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        } catch (Exception ex) {
			LOGGER.error(StringUtils.EMPTY,ex);
        }
    }
}
