package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import com.stpl.app.gtnforecasting.queryUtils.CommonQueryUtils;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.SELECT_ONE;
import static com.stpl.app.gtnforecasting.utils.Constant.STRING_EMPTY;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
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
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import static com.stpl.app.utils.Constants.CommonConstants.CONTRACT_DETAILS;
import com.stpl.app.utils.QueryUtils;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyServiceImpl;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.sqlutil.GtnSqlUtil;
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;

/**
 *
 * @author Abhiram
 */
public class CommonLogic {

    private static final CommonDAO commonDao = new CommonDAOImpl();
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    private static final CommonQueryUtils commonQueryUtil = new CommonQueryUtils();
    private static boolean viewFlag = false;
    private static String screenName = StringUtils.EMPTY;
    public static final String CCPMAP = ") CCPMAP,";
    final static Map<String, String> fileMap = new HashMap<>();
    public static final String LEVEL_CAPS = "@LEVEL";
    public static final String JOIN_SPACE = " JOIN";
    public static final String JOIN = " JOIN ";
    public static final String BUILDERSID = "@BUILDERSID";
    public static final String EACH = "EACH";
    public static final String AND_SPMFILTER_CC_P1 = " AND SPM.FILTER_CCP=1";
    public static final String RELBUILD_SID = "@RELBUILDSID";
    public static final String DEDLEVEL_VALUES = "@DEDLEVELVALUES";
    public static final String DEDUCTION = "DEDUCTION";
    public static final String SALES = "SALES";
    public static final String INVALID_LEVEL_NO = "Invalid Level No:";
    
    GtnFrameworkHierarchyServiceImpl gtnFrameworkHierarchyServiceImpl=new GtnFrameworkHierarchyServiceImpl();
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
     * Get Deduction Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getDeductionHierarchy(int projectionId, final int levelNo) {
        return getHierarchy(projectionId, Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY, levelNo);
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
            LOGGER.error(ex);
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
            LOGGER.debug(ex.getCause());
        }
        if (userId != 0) {
            int selectedId = Integer.valueOf(String.valueOf(value));
            for (CustomViewMaster custom : customViewList) {
                if ((custom.getCustomViewMasterSid() == selectedId) && (custom.getCreatedBy() == userId)) {
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
                    CustomViewMaster customViewMaster = CustomViewMasterLocalServiceUtil.createCustomViewMaster(0);
                    customViewMaster.setProjectionMasterSid(session.getProjectionId());
                    customViewMaster.setViewName(viewName);
                    customViewMaster.setCreatedBy(userId);
                    customViewMaster.setCreatedDate(date);
                    customViewMaster = commonDao.addCustomView(customViewMaster);
                    if (customViewMaster != null) {
                        customId = customViewMaster.getCustomViewMasterSid();
                        customViewDetailsSaveLogic(customId, levelList);
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
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
                        List<CustomViewDetails> detailsList = null;
                        if (session.getCustomDetailMap().containsKey(customId)) {
                            detailsList = session.getCustomDetailMap().get(customId);
                        } else {
                            detailsList = getCustomViewDetails(customId);
                        }
                        for (CustomViewDetails customDetails : detailsList) {
                            try {
                                commonDao.deleteCustomViewDetails(customDetails);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }
                        if (session.getCustomDetailMap().containsKey(customId)) {
                            session.getCustomDetailMap().remove(customId);
                            session.getCustomHierarchyMap().remove(customId);
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
                query.add(RestrictionsFactoryUtil.ne(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, customId));
            }

            list = commonDao.getCustomViewList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public static boolean customViewDetailsSaveLogic(int customId, List levelList) {
        String query = "INSERT INTO CUSTOM_VIEW_DETAILS  (HIERARCHY_ID,HIERARCHY_INDICATOR,CUSTOM_VIEW_MASTER_SID,LEVEL_NO)";
        int i = 0;
        int listSize = levelList.size() - 1;
        for (Object ob : levelList) {
            Leveldto dto = (Leveldto) ob;
            query += " SELECT " + dto.getHierarchyId() + " , '" + dto.getHierarchyIndicator() + "' , " + customId + " , " + dto.getTreeLevelNo();
            if (i != listSize) {
                query += " UNION ALL ";
            }
            i++;
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
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
            query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, customId));
            query.addOrder(OrderFactoryUtil.asc(LEVEL_NO));
            list = commonDao.getCustomViewDetailsList(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return list;
    }
    public static final String LEVEL_NO = "levelNo";

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
            if (customDetailsList != null && !customDetailsList.isEmpty()) {
                StringBuilder relationShipLevelQry = new StringBuilder();
                relationShipLevelQry.append("select DISTINCT LEVEL_NAME,LEVEL_NO,HIERARCHY_LEVEL_DEFINITION_SID from dbo.RELATIONSHIP_LEVEL_DEFINITION where HIERARCHY_LEVEL_DEFINITION_SID in (");
                for (int i = 0; i < customDetailsList.size(); i++) {
                    relationShipLevelQry.append(customDetailsList.get(i).getHierarchyId());
                    if (i != customDetailsList.size() - 1) {
                        relationShipLevelQry.append(CommonUtil.COMMA);
                    }
                }
                relationShipLevelQry.append(CommonUtil.CLOSE_PARANTHESIS);
                List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(relationShipLevelQry.toString());

                for (CustomViewDetails ob : customDetailsList) {
                    for (Object[] obj : list) {
                        if ((String.valueOf(obj[2]).trim().equals(String.valueOf(ob.getHierarchyId()).trim())) && (obj.length > 1)) {
                            Leveldto dto = new Leveldto();
                            dto.setHierarchyId(ob.getHierarchyId());
                            dto.setLevelNo(Integer.valueOf(String.valueOf((obj[1].toString()).trim())));
                            dto.setLevel(String.valueOf(obj[0]));
                            dto.setTreeLevelNo(ob.getLevelNo());
                            dto.setHierarchyIndicator(ob.getHierarchyIndicator());
                            listValue.add(dto);
                        }
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
                LOGGER.error(ex);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            }
        }
        return cvm;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, String tabName, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, boolean isCount, boolean isLimit, List<String> discountList, ProjectionSelectionDTO projSelDTO) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, tabName, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, isCount, start, offset, isLimit, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid, discountList, projSelDTO);
            if (StringUtils.isNotBlank(query)) {
                List<Object> list = (List<Object>) executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
                if (list != null && !list.isEmpty()) {
                    for (Object list1 : list) {
                        final Object[] obj = (Object[]) list1;
                        Leveldto dto = getCustomizedView(obj, false);
                        listValue.add(dto);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return listValue;
    }

    public static int getLevelListCount(int projectionId, String tabName, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, List<String> discountList, ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        try {
            String query = getLevelListQuery(projectionId, tabName, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid, discountList, projSelDTO);
            List<Object> list = null;
            if (!query.equals(StringUtils.EMPTY)) {
                list = (List<Object>) executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            }
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public static int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, boolean isGroupFilter, String levelName) {
        int count = 0;
        try {
            List<Object> list = null;
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, isGroupFilter, levelName);
            if (!query.equals(StringUtils.EMPTY)) {
                list = (List<Object>) executeSelectQuery(query, null, null);
            }
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    public static String getLevelListQuery(int projectionId, String tabName, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo,
            String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId,
            String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, List<String> discountList, ProjectionSelectionDTO projSelDTO) {
        if (isCustom) {
            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) executeSelectQuery(hierarchyIndicatorQuery, null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
            projSelDTO.setCustomhierarchy(hierarchyIndicator);

            String customHierarchyCheck = "SELECT CASE WHEN MAX(HIERARCHY_INDICATOR) = MIN(HIERARCHY_INDICATOR) THEN MIN(HIERARCHY_INDICATOR) \n"
                    + "ELSE 'B' END FROM CUSTOM_VIEW_DETAILS WHERE CUSTOM_VIEW_MASTER_SID = " + customId;
            list = (List<Object>) executeSelectQuery(customHierarchyCheck, null, null);
            if (list != null && !list.isEmpty()) {
                projSelDTO.setCustomViewIndicator(String.valueOf(list.get(0)));
            }
            if (StringUtils.EMPTY.equals(hierarchyIndicator)) {
                return StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if ((hierarchyNo != null) && (!hierarchyNo.equals(StringUtils.EMPTY))) {
            if (isExpand) {
                whereCond = " and HLD " + hierarchyIndicator.trim() + ".HIERARCHY_NO =  '" + hierarchyNo + "' ";
            }
            if (!isFilter) {
                hierarchyNo1 = hierarchyNo;
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "";
        if (isCount) {
            selectClause += " select Count(distinct HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO)  ";
        } else {
            selectClause += " IF OBJECT_ID('TEMPDB.DBO.#HIERARCHY_VALUES', 'U') IS NOT NULL\n"
                    + "              DROP TABLE #HIERARCHY_VALUES;\n"
                    + "create TABLE #HIERARCHY_VALUES  (\n"
                    + "       LEVEL_NO VARCHAR(50)\n"
                    + "       ,TREE_LEVEL_NO VARCHAR(50)\n"
                    + "       ,HIERARCHY_INDICATOR VARCHAR(50)\n"
                    + "       ,LEVEL_NAME VARCHAR(200)\n"
                    + "       ,RELATIONSHIP_LEVEL_VALUES VARCHAR(50)\n"
                    + "       ,PARENT_NODE VARCHAR(100)\n"
                    + "       ,HIERARCHY_NO VARCHAR(50)\n"
                    + "    )\n"
                    + "\n"
                    + "INSERT INTO #HIERARCHY_VALUES (\n"
                    + "       LEVEL_NO\n"
                    + "       ,TREE_LEVEL_NO\n"
                    + "       ,HIERARCHY_INDICATOR\n"
                    + "       ,LEVEL_NAME\n"
                    + "       ,RELATIONSHIP_LEVEL_VALUES\n"
                    + "       ,PARENT_NODE\n"
                    + "       ,HIERARCHY_NO\n"
                    + "       )";
            selectClause += " SELECT DISTINCT HLD" + hierarchyIndicator.trim() + Constant.LEVEL_NO_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                    + " HLD" + hierarchyIndicator.trim() + Constant.LEVEL_NAME_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ,"
                    + " HLD" + hierarchyIndicator.trim() + Constant.PARENT_NODE_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO  ";
            if (isLimit) {
                if (tabName.contains("PPA")) {
                    recordNumber += " SELECT * FROM #HIERARCHY_VALUES ORDER BY HIERARCHY_NO ASC OFFSET " + start + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
                } else {
                    recordNumber += " SELECT * FROM #HIERARCHY_VALUES ORDER BY HIERARCHY_NO ASC OFFSET " + start + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
                }
            } else {
                selectClause += ", ROW_NUMBER() OVER  (ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC) AS TEMP_INDEX  ";
            }
        }
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, tabName, sessionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid, discountList, projSelDTO.getSessionDTO().getAction());
            customSql += FROM_SPACE + customViewQuery;
        } else {
            String relationshipBuilderSid = custRelSid;
            if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                relationshipBuilderSid = prodRelSid;
            }
            String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + "HIERARCHY_INDICATOR,RLD.PARENT_NODE";
            String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + "  HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
            String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid"
                    + " AND RLD.RELATIONSHIP_BUILDER_SID  = " + relationshipBuilderSid + " \n"
                    + " JOIN projection_details PD "
                    + " ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " "
                    + getGroupFilterQuery(userGroup, userId, sessionId, false, discountList);

            joinQuery1 += CCPMAP;

            String joinQuery2 = "  relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON  PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                    + " AND PCH.projection_master_sid =   " + projectionId
                    + " WHERE  RLD1.hierarchy_no LIKE ' " + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD " + hierarchyIndicator.trim();
            String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD " + hierarchyIndicator.trim() + Constant.HIERARCHY_NO_PERCENT;

            customSql += FROM_SPACE + selectClause1 + FROM_SPACE + joinQuery1 + " " + selectClause2 + FROM_SPACE + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }
    public static final String FROM_SPACE = " from ";

    public static String getCustomViewLevelListQuery(int projectionId, String tabName, int sessionId, int customId, String hierarchyIndicator, int levelNo, String productHierarchyNo, String customerHierarchyNo, String custRelSid, String prodRelSid, List<String> discountList, String action) {
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
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + custRelSid
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projectionId
                + getMasterTableJoin(tabName, sessionId, discountList, action)
                + " ) CCPMAPC"
                + JOIN_SPACE
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD     "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + prodRelSid
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projectionId
                + " ) CCPMAPP "
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID"
                + " LEFT JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME"
                + " FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + customId + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'"
                + " LEFT JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS  CVD "
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + customId + Constant.AND_CVDLEVEL_NO_LIKE + productLevelNo + "'"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN  PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE
                + " where HLD" + hierarchyIndicator + ".RELATIONSHIP_LEVEL_VALUES is not null";
        return customViewQuery;
    }

    public static String getAllHierarchyLevelsQuery(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {
        String finalQuery = StringUtils.EMPTY;
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId, sessionId, relationshipBuilderSid);
        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo, String userGroup, int userId, int sessionId, String relationshipBuilderSid) {

        String customSql;
        String tableName = getViewTableName(hierarchyIndicator);
        String mainSelect = "SELECT HLD.level_no, HLD.level_no as TREE_LEVEL_NO,'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,HLD.LEVEL_NAME,HLD.relationship_level_values,HLD.PARENT_NODE,HLD.HIERARCHY_NO ";
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid, RLD.level_no," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR, RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid "
                + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + " \n"
                + " JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQuery(userGroup, userId, sessionId, false, null) + CCPMAP;
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + tableName + " PCH  ON PCH.relationship_level_sid =  RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid  = " + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '%' AND RLD1.LEVEL_NO >= " + levelNo + ")  HLD ";
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD.hierarchy_no + '%'";

        customSql = mainSelect + FROM_SPACE + selectClause1 + FROM_SPACE + joinQuery1 + " " + selectClause2 + FROM_SPACE + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public static String getHierarchyTreeQuery(int projectionId, String hierarchyIndicator, final int levelNo) {

        List<Object> queryInputs = new ArrayList<>();
        queryInputs.add(hierarchyIndicator);
//        queryInputs.add(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator) ? "CUST_RELATIONSHIP_BUILDER_SID" : "PROD_RELATIONSHIP_BUILDER_SID");
        queryInputs.add(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator) ? "CUST_RELATIONSHIP_BUILDER_SID" : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator) ? "PROD_RELATIONSHIP_BUILDER_SID" : "DED_RELATIONSHIP_BULDER_SID");
        queryInputs.add(projectionId);
        queryInputs.add(levelNo);
        return PPAQuerys.getQuery(queryInputs, "relation-ship-level-details");

    }

    public static List<Object> getLevelNoAndHierarchyNo(Object value) {
        LOGGER.debug("Inside getLevelNoAndHierarchyNo");
        List<Object> levelHierarchy = new ArrayList<>();
        String selectedId = DASH;
        if ((value != null) && (!SELECT_ONE.equals(String.valueOf(value)))) {
            selectedId = String.valueOf(value);
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
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
            projectionListFrom.add(ProjectionFactoryUtil.property(LEVEL_NO));
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
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator, String action) {
        List<Leveldto> newLevelList = new ArrayList<>();
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
            LOGGER.error(ex);
        }
        return newLevelList;
    }

    public static List<Leveldto> getAllHierarchyLevels(int startLevelNo, int projectionId, String hierarchyIndicator, String userGroup, int userId, int sessionId, String relationshipBuilderSid, String action) {
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
        LOGGER.info("Procedure Name " + procedureName);
        try {
			return convertResultSetToList(
					GtnSqlUtil.getResultFromProcedure(getQuery(procedureName, orderedArgs), orderedArgs));
		} catch (Exception e) {
			LOGGER.error(e);
		}
        return new ArrayList<>();
    }

    public static void callProcedureforUpdate(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name " + procedureName);
        GtnSqlUtil.procedureCallService(getQuery(procedureName, orderedArgs), orderedArgs);

    }
    private static String getQuery(String procedureName, Object[] orderedArgs) {
		StringBuilder procedureToCall = new StringBuilder("{call ");
		procedureToCall.append(procedureName);
		int noOfArgs = orderedArgs.length;
		for (int i = 0; i < noOfArgs; i++) {
			if (i == 0) {
				procedureToCall.append(CommonUtil.OPEN_PARANTHESIS);
			}
			procedureToCall.append("?,");
			if (i == noOfArgs - 1) {
				procedureToCall.append(CommonUtil.CLOSE_PARANTHESIS);
			}
		}
		procedureToCall.replace(procedureToCall.lastIndexOf(CommonUtil.COMMA),
				procedureToCall.lastIndexOf(CommonUtil.COMMA) + 1, StringUtils.EMPTY);
		procedureToCall.append("}");
		return procedureToCall.toString();
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
     *
     * @param discounts
     * @param isProgram
     * @param session
     * @return
     */
    public static List<List<String>> getDiscountNoList(List<String> discounts, boolean isProgram, SessionDTO session) {
        List<List<String>> discountlist = new ArrayList();
        List<String> discountNolist = new ArrayList();
        List<String> discountNamelist = new ArrayList();

        if (discounts != null && !discounts.isEmpty()) {
            String selectedDiscounts = CommonUtils.CollectionToString(discounts, true);

            StringBuilder query = new StringBuilder(SQlUtil.getQuery(isProgram ? "get-discount-name-with-program" : "get-discount-name-with-program-category"));
            query.replace(query.indexOf("?"), query.indexOf("?") + 1, selectedDiscounts);
            List<Object> list = (List<Object>) executeSelectQuery(QueryUtil.replaceTableNames(query.toString(), session.getCurrentTableNames()), null, null);
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
        List<NmProjectionSelection> list = new ArrayList<>();
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
            LOGGER.error(ex);
        }
    }

    private static String getMasterTableJoin(String tabName, int sessionId, List<String> discountList, String action) {
        String masterTableQuery = StringUtils.EMPTY;
        boolean viewflag = Constant.VIEW.equals(action);
        if (sessionId != 0) {
            if (Constant.PPA.equals(tabName) || tabName.contains("PPA")) {
                masterTableQuery = viewflag ? " JOIN NM_PPA_PROJECTION_MASTER PPA_MASTER ON PPA_MASTER.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID " : " JOIN ST_NM_PPA_PROJECTION_MASTER PPA_MASTER ON PPA_MASTER.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID ";
            } else if (Constant.DISCOUNT_PROJECTION_RESULTS.equals(tabName)) {
                masterTableQuery = viewflag ? " JOIN NM_DISCOUNT_PROJ_MASTER D_MASTER ON D_MASTER.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID " : " JOIN ST_NM_DISCOUNT_PROJ_MASTER D_MASTER ON D_MASTER.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID ";
                if (discountList != null && !discountList.isEmpty()) {
                    masterTableQuery += " and D_MASTER.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(discountList, true) + " )";
                }
            }
            //If Joins Needed for Sales (or) any other screen, it can be written here
        }
        return masterTableQuery;
    }

    /**
     * Save projection selection
     *
     * @param map
     * @param projectionID
     * @param screenName
     */
    public void saveProjectionSelectionMandatedDiscountProjection(Map map, int projectionID, String screenName) throws PortalException, SystemException {
        List<Object> list;
        String query = " IF EXISTS (SELECT 1 from M_PROJECTION_SELECTION  WHERE PROJECTION_MASTER_SID=" + projectionID + "AND SCREEN_NAME='" + screenName + "' ) SELECT 1 ELSE SELECT 0";
        list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list == null || list.isEmpty() || Integer.valueOf(String.valueOf(list.get(0))) == 0) {
            saveSelection(map, projectionID, screenName, Constant.SAVE, Constant.M_PROJECTION_SELECTION);
        } else {
            saveSelection(map, projectionID, screenName, Constant.UPDATE, Constant.M_PROJECTION_SELECTION);
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
    public void saveSelection(Map map, int projectionID, String screenName, String saveOrUpdate, String tableName) throws PortalException, SystemException {
        Object[] obj = map.keySet().toArray();
        StringBuilder queryBuilder = new StringBuilder();

        if (Constant.SAVE.equalsIgnoreCase(saveOrUpdate)) {
            queryBuilder.append("INSERT INTO ").append(tableName).append(" (PROJECTION_MASTER_SID,SCREEN_NAME,FIELD_NAME,FIELD_VALUES)  ");
            for (int i = 0; i < map.size(); i++) {
                queryBuilder.append("SELECT '").append(projectionID).append("','").append(screenName).append("','");
                queryBuilder.append(obj[i]).append("','").append(map.get(obj[i])).append("'\n");
                if (i != map.size() - 1) {
                    queryBuilder.append("\n UNION ALL \n ");
                }
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
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
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
            LOGGER.error(ex);
        }
        return null;
    }

    public static Map<Object, Object> getNMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
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
    public static List<List<String>> getPriceGroupTypeList(List<String> discountName, SessionDTO session) {
        List<List<String>> discountlist = new ArrayList<>();
        List<String> discountNolist = new ArrayList<>();
        List<String> priceGrouplist = new ArrayList<>();
        List list = null;
        if (discountName != null && !discountName.isEmpty()) {
            list = commonQueryUtil.getPriceGroupType(discountName, session);
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
        List<NmProjectionSelection> list = new ArrayList<>();
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
            LOGGER.error(ex);
        }
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String where) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + Constant.DOT_YEARS_SPACE;
        String selectClause = IS_NULL_OPEN + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS= " + table2 + ".YEARS";
        selectClause += IS_NULL_OPEN + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS) ";
        finalWhereCond += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table2 + Constant.DOT_PERIODS_QUERY;
        groupBy += ", " + table1 + Constant.DOT_PERIODS_QUERY;
        selectClause += " as PERIODS,  \n";

        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static List<String> getCommonSelectWhereOrderGroupByClause(String table1, String table2, String table3, String where, boolean isTable3) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS, PERIODS";
        String groupBy = " " + table1 + Constant.DOT_YEARS_SPACE;
        String selectClause = IS_NULL_OPEN + table1 + ".YEARS , " + table2 + ".YEARS)";
        String selectClause1 = IS_NULL_OPEN + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS) ";
        if (isTable3) {
            selectClause = "Isnull(Isnull(" + table1 + ".YEARS , " + table2 + ".YEARS), " + table3 + ".YEARS)";
            selectClause1 = "Isnull(Isnull(" + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS), " + table3 + ".PERIODS)";
        }
        selectClause += " as YEARS, ";
        selectClause += selectClause1;
        selectClause += " as PERIODS, \n";
        String finalWhereCond = " " + where + " " + table1 + ".YEARS= " + table2 + Constant.DOT_YEARS_SPACE;
        finalWhereCond += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table2 + ".PERIODS ";
        groupBy += ", " + table1 + Constant.DOT_PERIODS_QUERY;

        String finalWhereCond1 = " " + where + " " + table1 + ".YEARS=" + table3 + Constant.DOT_YEARS_SPACE;
        finalWhereCond1 += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table3 + Constant.DOT_PERIODS_QUERY;
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(finalWhereCond1);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    public static String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = Constant.AND_SMALL_SPACE + table + ".USER_ID = " + userId + Constant.AND_SMALL_SPACE + table + ".SESSION_ID = " + sessionId + " \n";
        return user;
    }

    public static String getUserSessionQueryConditionPR(int userId, int sessionId, String table) {
        String user = table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP) {
        String ccpWhereCond = Constant.AND_SMALL_SPACE + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID \n"
                + Constant.AND_SMALL_SPACE + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID \n";
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
        //Need to remove once the dynamic changes is done in Government
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(projSelDTO.getScreenName())) {
            if (!CommonUtils.isInteger(projSelDTO.getYear())) {
                periodFilter = " PERIOD_DATE BETWEEN ( '" + startDate + "') and ( '" + endDate + "') \n ";
            }
        } else if (!CommonUtils.isInteger(projSelDTO.getYear())) {
            periodFilter = " and PERIOD_DATE BETWEEN ( '" + startDate + "') and ( '" + endDate + "') \n";
        }

        return periodFilter;
    }

    public static List getPeriodRestrictionPR(ProjectionSelectionDTO projSelDTO) {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth() != 0 ? projSelDTO.getStartMonth() : 1) + "-"
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
        String tableQuery;
        if (isVariance) {
            tableQuery = "DECLARE @CCP TABLE\n"
                    + "  ( \n"
                    + "     ID                     INT IDENTITY(1, 1),\n"
                    + "    RELATIONSHIP_LEVEL_SID  INT,\n"
                    + "     CCP_DETAILS_SID       INT,\n"
                    + "     HIERARCHY_NO         VARCHAR(50)\n"
                    + "     UNIQUE NONCLUSTERED (CCP_DETAILS_SID, ID)\n"
                    + "   )   \n "
                    + " INSERT INTO  @CCP\n"
                    + "     (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        } else {
            tableQuery = "DECLARE  @CCP TABLE\n"
                    + "      (\n"
                    + "    RELATIONSHIP_LEVEL_SID INT,\n"
                    + "     CCP_DETAILS_SID    INT,\n"
                    + "     HIERARCHY_NO          VARCHAR(50)\n"
                    + "  ) \n "
                    + " INSERT  INTO @CCP\n"
                    + "            (RELATIONSHIP_LEVEL_SID,CCP_DETAILS_SID,HIERARCHY_NO) \n";
        }

        return tableQuery;
    }

    public static String getCCPTempTableQueryPR() {
        String tableQuery = " DECLARE @CCP TABLE\n"
                + "    (\n"
                + "    RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID INT,\n"
                + "PROJECTION_DETAILS_SID INT NOT NULL, \n"
                + "     HIERARCHY_NO           VARCHAR(50) \n"
                + "  ) \n"
                + "  INSERT INTO @CCP\n"
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
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID  \n"
                + "AND RLD.RELATIONSHIP_BUILDER_SID= " + relationshipBuilderSid + "\n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID  \n"
                + " AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId();
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }
        ccpQuery += " ) CCPMAP, \n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID  \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1  \n"
                + JOIN + viewtable + " PCH  \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID  \n"
                + " AND PCH.PROJECTION_MASTER_SID= " + projSelDTO.getProjectionId() + " \n "
                + " WHERE RLD1.HIERARCHY_NO like  '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n "
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n "
                + " WHERE LCCP.HIERARCHY_NO in  \n "
                + " (SELECT RLD2.HIERARCHY_NO  \n "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2  \n "
                + JOIN + viewtable + " PCH2  \n"
                + " ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID  \n "
                + " AND PCH2.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD2.LEVEL_NO = " + projSelDTO.getTreeLevelNo() + ") \n";

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
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }
        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + JOIN + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + JOIN + viewtable + " PCH2 \n"
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
        String ccpQuery = "SELECT distinct HLD " + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP " + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM  \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID  \n"
                + " FROM  RELATIONSHIP_LEVEL_DEFINITION RLD  \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID  \n"
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }

        ccpQuery += "  ) CCPMAPC \n"
                + JOIN_SPACE
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID= RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID= RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY  PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n";
        return ccpQuery;
    }

    public static final String SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP = " (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n";
    public static final String JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL = " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n";

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
        String ccpQuery = "SELECT distinct  HLD" + projSelDTO.getHierarchyIndicator() + ".RELATIONSHIP_LEVEL_SID, CCPMAP" + projSelDTO.getHierarchyIndicator() + ".CCP_DETAILS_SID,CCPMAPC.PROJECTION_DETAILS_SID, HLD" + projSelDTO.getHierarchyIndicator() + ".HIERARCHY_NO FROM  \n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,PD.PROJECTION_DETAILS_SID, CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD  \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }

        ccpQuery += " ) CCPMAPC JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID  \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD  \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }

        ccpQuery += " ) CCPMAPP  \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN  dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON  PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN  dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON  PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + "')  HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n";
        return ccpQuery;
    }

    public static List<String> getAllHierarchyNo(String hierarchyNo) {
        LOGGER.debug("getAllHierarchyNo started");
        List<String> allLevelHierarchy = new ArrayList<>();
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
        LOGGER.debug("getAllHierarchyNo ended");
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
        for (int index = 0; index < NumericConstants.EIGHT; index++) {
            cssLayout.addComponent(pageManagement.getComponent(0));
        }
        controlBar.addComponent(cssLayout);
        return controlBar;
    }

    public static String getGroupFilterQuery(String userGroup) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty() && !userGroup.equals(STRING_EMPTY)) {
            query = "JOIN "
                    + (viewFlag ? "NM_SALES_PROJECTION_MASTER" : "ST_NM_SALES_PROJECTION_MASTER ")
                    + " SPMG ON SPMG.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  SPMG.USER_GROUP ='" + userGroup + "'";
        }
        return query;
    }

    public static String getGroupFilterQuery(String userGroup, int userId, int sessionId, boolean isPrior, List<String> discountList) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.ALL)) {
                if (userGroup.contains(Constant.DISCOUNT_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterDiscountQuery(isPrior, discountList);
                } else if (userGroup.contains(Constant.PPA_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterPPAQuery(userGroup, isPrior);
                } else if (userGroup.contains(Constant.SALES_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
                }
            } else if (userGroup.startsWith(Constant.DISCOUNT)) {
                userGroup = " = '" + userGroup.replace(Constant.DISCOUNT, StringUtils.EMPTY) + "' ";
                query = getGroupFilterDiscountQuery(isPrior, discountList);
            } else if (userGroup.startsWith(Constant.PPA)) {
                userGroup = " = '" + userGroup.replace(Constant.PPA, StringUtils.EMPTY) + "' ";
                query = getGroupFilterPPAQuery(userGroup, isPrior);
            } else if (userGroup.startsWith(Constant.SALES_WITH_HYPHEN)) {
                userGroup = " = '" + userGroup.replace(Constant.SALES_WITH_HYPHEN, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
            }
        }
        return query;
    }
    public static final String LIKE_PERCENT = " like '%' ";

    public static String getGroupFilterDiscountQuery(boolean isPrior, List<String> discountList) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_DISCOUNT_PROJ_MASTER D ON D.CCP_DETAILS_SID=C.CCP_DETAILS_SID ";
        if (discountList != null && !discountList.isEmpty()) {
            query += " and D.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(discountList, true) + " )";
        }
        return query;
    }

    public static String getGroupFilterPPAQuery(String userGroup, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "   JOIN " + tableIndicator + "NM_PPA_PROJECTION_MASTER P ON P.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID WHERE  P.USER_GROUP " + userGroup;
        return query;
    }

    public static String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "   JOIN " + tableIndicator + "NM_SALES_PROJECTION_MASTER S ON S.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID WHERE  S.USER_GROUP " + userGroup;
        if (!isPrior && (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED).equals(screenName)) {
            query += (viewFlag ? "" : getUserSessionQueryCondition(userId, sessionId, Constant.S));
        }
        return query;
    }

    public static String getGroupQuery(String table) {
        String query = "SELECT DISTINCT M.USER_GROUP FROM " + table + " M"
                + " WHERE M.USER_GROUP IS NOT NULL ";
        return query;
    }

    public static List<String> getDiscountGroup(SessionDTO session) {
        List<String> groupList = new ArrayList<>();
        try {
            Set salesGroup = session.getDiscountgroupSet();
            if (salesGroup != null && !salesGroup.isEmpty()) {
                for (Object list1 : salesGroup) {
                    groupList.add(Constant.DISCOUNT + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return groupList;
    }

    public static List<String> getPPAGroup() {
        List<String> groupList = new ArrayList<>();
        try {
            String query = getGroupQuery("ST_NM_PPA_PROJECTION_MASTER");
            List<Object> list = (List<Object>) executeSelectQuery(query, null, null);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    groupList.add(Constant.PPA + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return groupList;
    }

    public static List<String> getSalesGroup(SessionDTO session) {
        List<String> groupList = new ArrayList<>();
        try {
            Set salesGroup = session.getSalesgroupSet();
            if (salesGroup != null && !salesGroup.isEmpty()) {
                for (Object list1 : salesGroup) {
                    groupList.add(Constant.SALES_WITH_HYPHEN + String.valueOf(list1));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return groupList;
    }

    public static List<String> getAllGroup(SessionDTO session, boolean isPPA) {
        List<String> groupList = new ArrayList<>();
        groupList.add("All Sales Group");
        groupList.add("All Discount Group");
        if (isPPA) {
            groupList.add("All PPA Group");
        }
        if (session.getSalesgroupSet() == null) {
            GroupFilter.initSalesMap(session);
        }
        groupList.addAll(getSalesGroup(session));
        if (session.getDiscountgroupSet() == null) {
            GroupFilter.initdiscountMap(session);
        }
        groupList.addAll(getDiscountGroup(session));
        if (isPPA) {
        }
        return groupList;
    }

    public static List<String> getAllPPAGroup() {
        List<String> groupList = new ArrayList<>();
        groupList.add(Constant.ALL_GROUP);
        return groupList;
    }

    public static int getTradingPartnerLevelNo(boolean isCustom, int projectionIdOrCustomId) {
        int levelNo = 0;
        String query = "SELECT DISTINCT ";
        if (isCustom) {
            query += " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                    + "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVM.CUSTOM_VIEW_MASTER_SID=CVD.CUSTOM_VIEW_MASTER_SID AND CVM.CUSTOM_VIEW_MASTER_SID=" + projectionIdOrCustomId + "\n"
                    + "        JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.HIERARCHY_LEVEL_DEFINITION_SID = CVD.HIERARCHY_ID\n"
                    + "        where  RLD.LEVEL_NAME='Trading Partner'";
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
            LOGGER.debug(ex);
        }

        return levelNo;
    }

    public static int getTradingPartnerLevelNoForPR(boolean isCustom, int projectionIdOrCustomId) {
        int levelNo = 0;
        String query = "SELECT DISTINCT ";
        if (isCustom) {
            query += " CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD \n"
                    + "        JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVM.CUSTOM_VIEW_MASTER_SID=CVD.CUSTOM_VIEW_MASTER_SID AND CVM.CUSTOM_VIEW_MASTER_SID=" + projectionIdOrCustomId + "\n"
                    + "        JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.HIERARCHY_LEVEL_DEFINITION_SID = CVD.HIERARCHY_ID\n"
                    + "        WHERE  RLD.LEVEL_NAME='Trading Partner'";
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

    public static List<String> getSalesGroupDDLB(SessionDTO session) {
        List<String> groupList = new ArrayList<>();
        Set<String> groupValues = session.getSalesgroupSet();
        for (String list1 : groupValues) {
            groupList.add(Constant.SALES_WITH_HYPHEN + String.valueOf(list1));
        }
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
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION  RLD \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false, projSelDTO.getDiscountNoList());
        }

        ccpQuery += " ) CCPMAPC  JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,  CCP.CCP_DETAILS_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION  RLD \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), false, projSelDTO.getDiscountNoList());
        }

        ccpQuery += " ) CCPMAPP  \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n "
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID =" + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD  ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID  \n "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID= RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID= " + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD  ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID  \n "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE
                + " ) \n";
        return ccpQuery;
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
        LOGGER.debug(PROJECTION_ID + projectionId);
        return getHierarchy(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY, levelNo, rbID);
    }

    /**
     * Get Product Hierarchy
     *
     * @param projectionId
     * @return list
     */
    public static List<Leveldto> getProductHierarchy(int projectionId, final int levelNo, final Object rbID) {
        LOGGER.debug(PROJECTION_ID + projectionId);
        return getHierarchy(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY, levelNo, rbID);
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
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            LOGGER.debug(PROJECTION_ID + projectionId);
            String levelQuery = SQlUtil.getQuery("deduction-loading").replace("@PROJID", String.valueOf(projectionId));
            deductionList = (List<String[]>) salesProjectionDAO.executeSelectQuery(levelQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return deductionList;
    }
    public static final String PROJECTION_ID = "projectionId ";

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
    public static List<Leveldto> getHierarchy(String hierarchyIndicator, final int levelNo, final Object rbID) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getHierarchyTreeQuery(hierarchyIndicator, levelNo, rbID);
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

    public static String getHierarchyTreeQuery(String hierarchyIndicator, final int levelNo, final Object rbID) {
        String selectClause = "select distinct RLD.LEVEL_NO, "
                + " RLD.LEVEL_NO as TREE_LEVEL_NO,"
                + "'" + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = FROM_SPACE;
        String customSql = selectClause + from
                + " RELATIONSHIP_LEVEL_DEFINITION as RLD "
                + "where "
                + "RLD.LEVEL_NO >=" + levelNo + " AND RLD.RELATIONSHIP_BUILDER_SID=" + rbID + ";";
        return customSql;
    }

    public static String getIndicator(int levelNo, int viewName) {
        List<CustomViewDetails> list = null;
        String indicator = StringUtils.EMPTY;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CustomViewDetails.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, viewName));
        query.add(RestrictionsFactoryUtil.eq(LEVEL_NO, levelNo));
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
        query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, viewName));
        try {
            list = commonDao.getCustomViewDetailsList(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + Constant.LEVEL_NO_QUOTE
                    + " '" + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                    + " HLD" + hierarchyIndicator.trim() + Constant.LEVEL_NAME_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + Constant.PARENT_NODE_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC OFFSET " + start + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no," + "'" + hierarchyIndicator + "'" + "  HIERARCHY_INDICATOR, RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid, RLD1.PARENT_NODE ";
        String joinQuery1 = "  relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + CCPMAP;
        String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid   = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid =" + projectionId;

        if (isGroupFilter) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE ' " + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += "  WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' " + getLevelNo(levelNo) + ") HLD " + hierarchyIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + Constant.HIERARCHY_NO_PERCENT;
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo);
            customSql += FROM_SPACE + customViewQuery;
        } else {
            customSql += FROM_SPACE + selectClause1 + FROM_SPACE + joinQuery1 + " " + selectClause2 + FROM_SPACE + joinQuery2 + " " + mainJoin
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
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD  "
                + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projectionId
                + " ) CCPMAPC"
                + JOIN_SPACE
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD  "
                + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projectionId
                + " ) CCPMAPP "
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID"
                + JOIN
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + customId + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'"
                + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND  PCH2.PROJECTION_MASTER_SID=" + projectionId
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'"
                + JOIN
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON "
                + " CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO like '" + productLevelNo + "'"
                + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID =RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE;
        return customViewQuery;
    }

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, boolean filterDdlb, String levelName) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, StringUtils.EMPTY, filterDdlb, levelName);
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

    public static List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, boolean filterDdlb, String levelName) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, false, start, offset, true, isCustom, customId, userGroup, filterDdlb, levelName);
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

    public static String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter,
            boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, String userGroup, boolean filterDdlb, String levelName) {
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
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + Constant.LEVEL_NO_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                    + " HLD" + hierarchyIndicator.trim() + Constant.LEVEL_NAME_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + Constant.PARENT_NODE_QUOTE
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC OFFSET " + start + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC) AS TEMP_INDEX ";
            }
        }
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
        String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + "  HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid =  CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId + " " + getGroupFilterQuery(userGroup) + CCPMAP;

        String joinQuery2 = " relationship_level_definition RLD1  JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid  \n"
                + " AND PCH.projection_master_sid  =" + projectionId;

        if (filterDdlb) {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo + "' AND RLD1.LEVEL_NAME IN (" + levelName + ")) HLD" + hierarchyIndicator.trim();
        } else {
            joinQuery2 += " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
        }

        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + Constant.HIERARCHY_NO_PERCENT;
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo);
            customSql += FROM_SPACE + customViewQuery;
        } else {
            customSql += FROM_SPACE + selectClause1 + FROM_SPACE + joinQuery1 + " " + selectClause2 + FROM_SPACE + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
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
                + "     (\n"
                + "  RELATIONSHIP_LEVEL_SID INT,\n"
                + "     CCP_DETAILS_SID       INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  ) \n"
                + "  INSERT  INTO @CCP\n"
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
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,  RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM  RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID=" + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n"
                + " ) CCPMAPC \n"
                + JOIN_SPACE
                + " (SELECT  RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + " FROM  RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID AND RLD.RELATIONSHIP_BUILDER_SID=" + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n"
                + " ) CCPMAPP  ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID \n"
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON  \n"
                + "  CVD.CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON  CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN dbo.CUSTOM_VIEW_MASTER CVM ON  \n"
                + " CVD.CUSTOM_VIEW_MASTER_SID = " + projSelDTO.getCustomId() + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'\n"
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON  CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID \n "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID= RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + "') HLDP  ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n";
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
        String finalQuery = StringUtils.EMPTY;
        finalQuery += getHierarchyLevelsQuery(projectionId, hierarchyIndicator, startLevelNo, userGroup, userId, sessionId);

        return finalQuery;
    }

    public static String getHierarchyLevelsQuery(int projectionId, String hierarchyIndicator, int levelNo, String userGroup, int userId, int sessionId) {

        String customSql;
        String tableName = getViewTableName(hierarchyIndicator);
        String mainSelect = "SELECT HLD.level_no, HLD.level_no as TREE_LEVEL_NO,'" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,HLD.LEVEL_NAME,HLD.relationship_level_values,HLD.PARENT_NODE,HLD.HIERARCHY_NO ";
        String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR, RLD.PARENT_NODE ";
        String selectClause2 = " ( SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR, RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
        String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid JOIN projection_details PD "
                + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND  PD.projection_master_sid =" + projectionId + " " + getGroupFilterQueryMandated(userGroup, userId, sessionId) + CCPMAP;
        String joinQuery2 = " relationship_level_definition RLD1 JOIN  " + tableName + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                + " AND PCH.projection_master_sid = " + projectionId
                + " WHERE  RLD1.hierarchy_no LIKE '%' AND RLD1.LEVEL_NO >= " + levelNo + ") HLD";
        String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD.hierarchy_no + '%'";

        customSql = mainSelect + FROM_SPACE + selectClause1 + FROM_SPACE + joinQuery1 + " " + selectClause2 + FROM_SPACE + joinQuery2 + " " + mainJoin;
        return customSql;

    }

    public static String getGroupFilterQueryMandated(String userGroup, int userId, int sessionId) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.ALL)) {
                if (userGroup.contains(Constant.DISCOUNT_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterDiscountQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains(Constant.PPA_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterPPAQuery(userGroup, userId, sessionId);
                } else if (userGroup.contains(Constant.SALES_SMALL)) {
                    userGroup = LIKE_PERCENT;
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
        String user = " " + table + ".USER_ID=" + userId + Constant.AND_SMALL_SPACE + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }

    public static String getCCPWhereConditionQuery(String projectionDetails, String CCP) {
        String ccpWhereCond = Constant.AND_SMALL_SPACE + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
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
        String tableQuery = "DECLARE @CCP TABLE \n"
                + "      (\n"
                + "     RELATIONSHIP_LEVEL_SID INT,\n"
                + "     PROJECTION_DETAILS_SID INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     HIERARCHY_NO           VARCHAR(50)\n"
                + "  )  \n"
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
                + " ( SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        ccpQuery += " ) CCPMAPC \n"
                + JOIN_SPACE
                + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID \n"
                + "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD \n"
                + JOIN_CCP_MAP_CCP_ON_RLDRELATIONSHIP_LEVEL
                + Constant.AND_RLDRELATIONSHIP_BUILDER_SID + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + Constant.JOIN_PROJECTION_DETAILS_PD_ON_PDCCP + projSelDTO.getProjectionId() + "\n";

        ccpQuery += " ) CCPMAPP \n"
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID  \n "
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + customerLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID  \n"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID= " + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + customerHierarchyNo + Constant.HLDC_ON_CCPMAP_HIERARCHY_NO_LIKE
                + JOIN
                + SELECT_RL_D2HIERARCHY_NORLD2RELATIONSHIP
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                + Constant.AND_CVDCUSTOM_VIEW_MASTER_SID + projSelDTO.getCustomId() + Constant.AND_CVDLEVEL_NO_LIKE + productLevelNo + "'\n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID  \n"
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + Constant.WHERE_RL_D2HIERARCHY_NO_LIKE + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%' \n"
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
        String ccpQuery = " SELECT DISTINCT LCCP.RELATIONSHIP_LEVEL_SID,LCCP.PROJECTION_DETAILS_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from \n"
                + "(SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID,CCPMAP.PROJECTION_DETAILS_SID from \n"
                + "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID,PD.PROJECTION_DETAILS_SID \n"
                + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD  \n"
                + "JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID \n"
                + "AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + "JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID \n"
                + "AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();

        ccpQuery += " ) CCPMAP,\n"
                + " (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD1 \n"
                + JOIN + viewtable + " PCH \n"
                + " ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID \n"
                + " AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + " WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%' ) HLD \n"
                + " WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP \n"
                + " WHERE LCCP.HIERARCHY_NO in \n"
                + " (SELECT RLD2.HIERARCHY_NO \n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD2 \n"
                + JOIN + viewtable + " PCH2 \n"
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
    public static void saveProjectionSelection(final Map<String, String> map, final String tabName, final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException {

        String screenName = projectionSelectionDTO.getScreenName();
        String tableName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName) ? "NM_PROJECTION_SELECTION" : CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName) ? Constant.M_PROJECTION_SELECTION : CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName) ? "CH_PROJECTION_SELECTION" : StringUtils.EMPTY;
        StringBuilder query = new StringBuilder();
        query.append("DELETE\n" + "FROM\n" + " ").append(tableName).append("\n" + "WHERE\n" + "PROJECTION_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append("\n" + "AND SCREEN_NAME LIKE '").append(tabName).append("';\n");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            query.append("INSERT \n" + "INTO\n" + StringUtils.EMPTY).append(tableName).append("\n" + "	(PROJECTION_MASTER_SID, SCREEN_NAME, FIELD_NAME, FIELD_VALUES)\n" + "VALUES\n" + "	(").append(projectionSelectionDTO.getProjectionId()).append(", '").append(tabName).append("', '").append(entry.getKey()).append("', '").append(entry.getValue()).append("');\n");
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

            LOGGER.debug(" getDropDownList method ends with return value strList size =" + helperList.size());

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return helperList;
    }

    public static Map editProjectionResults(final String tabName, final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException {

        String screenName = projectionSelectionDTO.getScreenName();
        String tableName = CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName) ? "NM_PROJECTION_SELECTION" : CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(screenName) ? Constant.M_PROJECTION_SELECTION : CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(screenName) ? "CH_PROJECTION_SELECTION" : StringUtils.EMPTY;

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
    public static String getPeriodSID(String frequency, String selectedPeriod, boolean formatFlag) {
        String periodSID = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(selectedPeriod) && !Constant.NULL.equals(selectedPeriod)) {
            try {
                String year = Constant.ANNUAL.equalsIgnoreCase(frequency) ? selectedPeriod.trim() : getYearAndPeriod(selectedPeriod, frequency, formatFlag)[0];
                String period = Constant.ANNUAL.equalsIgnoreCase(frequency) ? selectedPeriod.trim() : getYearAndPeriod(selectedPeriod, frequency, formatFlag)[1];
                List list;
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
                LOGGER.error(ex);
            } catch (Exception ex) {
                LOGGER.error(ex);
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
    public static String[] getYearAndPeriod(String period, String frequency, boolean formatFlag) {
        String[] periodArr = new String[NumericConstants.TWO];
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
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
            projectionListFrom.add(ProjectionFactoryUtil.property(LEVEL_NO));
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
            query.add(RestrictionsFactoryUtil.eq(Constant.CUSTOM_VIEW_MASTER_SID_PROPERTY, customId));
            query.addOrder(OrderFactoryUtil.asc(LEVEL_NO));
            list = CustomViewDetailsLocalServiceUtil.dynamicQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
                + "'" + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                + " RLD.LEVEL_NAME, RLD.HIERARCHY_LEVEL_DEFINITION_SID";
        String from = FROM_SPACE + getViewTableName(hierarchyIndicator);
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

        }
        return dto;
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
        String query;
        Map<Object, Object> map = new HashMap<>();

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
            LOGGER.debug(ex);
        }
        return null;
    }

// For mandated
    public static String getMandatedTempCCPQueryForCOGS(PVSelectionDTO pvsDTO) {
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
                + "      FROM  PERIOD\n"
                + "     WHERE PERIOD_DATE = @PROJECTION_DATE "
                + "\n"
                + "DECLARE @PERIOD TABLE\n"
                + SLASH_N
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
                + "WHERE  PERIOD_DATE BETWEEN CONVERT(DATE, '" + fromToList.get(0) + "') AND CONVERT(DATE, '" + fromToList.get(1) + "')"
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
                + " )\n"
                + "  INSERT INTO #TEMP_CCP (\n"
                + " COMPANY_MASTER_SID\n"
                + " , CONTRACT_MASTER_SID\n"
                + " , ITEM_MASTER_SID\n"
                + ",CCP_DETAILS_SID \n"
                + " )\n"
                + " SELECT distinct C.COMPANY_MASTER_SID\n"
                + "  , C.CONTRACT_MASTER_SID\n"
                + "  , C.ITEM_MASTER_SID\n"
                + ", C.CCP_DETAILS_SID \n"
                + " FROM CCP_DETAILS C\n"
                + "WHERE EXISTS (SELECT 1\n"
                + "                   FROM   #SELECTED_HIERARCHY_NO CCP\n"
                + "                   WHERE  CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + " DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + " INSERT INTO @ITEMID\n"
                + " SELECT IM.ITEM_MASTER_SID\n"
                + " FROM ITEM_MASTER IM\n"
                + " WHERE EXISTS (\n"
                + "  SELECT 1\n"
                + " FROM #TEMP_CCP A\n"
                + " WHERE IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + " )"
                + "DECLARE @COGS_ITEM TABLE\n"
                + SLASH_N
                + "     ITEM_MASTER_SID   INT,\n"
                + "     PERIOD_SID        INT,\n"
                + "     PRICING_QUALIFIER VARCHAR(50),\n"
                + "     ITEM_PRICE        NUMERIC(22, 6),\n"
                + "     PRIMARY KEY (ITEM_MASTER_SID, PERIOD_SID)\n"
                + "  ) \n "
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
    public static final String SLASH_N = "  (\n";

    public static String getTempRetrunsQuery() {

        String query = " DECLARE @COUNT INT\n"
                + " \n"
                + "IF OBJECT_ID('TEMPDB..#ITEM') IS NOT NULL\n"
                + "       DROP TABLE #ITEM\n"
                + " \n"
                + "CREATE TABLE #ITEM (\n"
                + "       ID INT IDENTITY(1, 1)\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "     )\n"
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
                + "         )\n"
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
                + SLASH_N
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
        String groupBy = " " + table1 + Constant.DOT_YEARS_SPACE;
        String selectClause = IS_NULL_OPEN + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + "on" + " " + table1 + ".YEARS=" + table2 + ".YEARS  ";

        selectClause += IS_NULL_OPEN + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS)";
        selectClause += " as PERIODS, \n";
        if (freq != 1) {
            finalWhereCond += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table2 + Constant.DOT_PERIODS_QUERY;
            groupBy += ", " + table1 + Constant.DOT_PERIODS_QUERY;

        }
        orderBy += ", PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }
    public static final String IS_NULL_OPEN = "Isnull(";

    public List<String> getBrand() {
        List<String> brandList = new ArrayList<>();
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

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
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
        inputList.add(projSelDTO.getProjectionId());
        String returnsQuery = PPAQuerys.getAppQuery(inputList, isTotal ? "getIsRS_RETURN_Total" : "getIsRS_RETURN_Detail");
        String finalQuery = "DECLARE @COUNT BIT \n" + (isTotal ? returnsQuery : (new CommonLogic().insertAvailableHierarchyNo(projSelDTO) + returnsQuery));
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
        String ppaQuery;
        String tempGroupFilter = projSelDTO.getGroupFilter();
        projSelDTO.setGroupFilter(StringUtils.EMPTY);
        ppaQuery = PPAQuerys.getAppQuery(inputList, isTotal ? "getIsRS_PPA_Total" : "getIsRS_PPA_Detail");
        String finalQuery = isTotal ? ppaQuery : (new CommonLogic().insertAvailableHierarchyNo(projSelDTO) + ppaQuery);
        List<Object[]> isReturnsList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(finalQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
        Boolean isCount = Boolean.FALSE;
        if (QueryUtils.getCount(isReturnsList) != 0) {
            isCount = Boolean.TRUE;
        }
        projSelDTO.setGroupFilter(tempGroupFilter);
        return isCount;
    }

    public static List getPPADiscountNameList(PVSelectionDTO selection) {
        String query = "SELECT";
        boolean flag = false;
        if (null != selection.getDiscountLevel()) {
            switch (selection.getDiscountLevel()) {
                case Constant.PROGRAM_CATEGORY_LABEL:
                    query += " CASE WHEN COUNT(DISTINCT RS.REBATE_PROGRAM_TYPE) >0 THEN 'Price Protection' ELSE NULL END ";
                    flag = true;
                    break;
                case "Program":
                    query += " DISTINCT RS.RS_NAME ";
                    break;
                default:
                    return Collections.emptyList();
            }
        }
        query += " FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN ST_CCP_HIERARCHY PD ON PD.CCP_DETAILS_SID = TEMP.CCP_DETAILS_SID  \n"
                + "JOIN  RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        Object str = !list.isEmpty() ? list.get(0) : null;
        if (flag && "null".equals(String.valueOf(str))) {
            return Collections.emptyList();
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
                + "JOIN RS_CONTRACT  RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }
    public static void getPPADiscountListPR(ProjectionSelectionDTO selection, List<String> noList, List<String> nameList) {
        List<Object[]> list;
        String query = "SELECT DISTINCT RS.RS_CONTRACT_SID,RS.RS_NAME FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
        list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            for (Object[] object : list) {
                noList.add(object[0].toString());
                nameList.add(object[1].toString());
            }
        }
    }

    /**
     * To drop the temp tables created in dynamically for the given user id and
     * session id. Query is placed in the Other
     * sources->src/main/resources->sqlresources->deleteTempQueries.xml
     *
     * @param userId
     * @param sessionId
     */
    public static void dropDynamicTables(String userId, String sessionId) {
        Map<String, String> inputs = new HashMap<>();
        inputs.put("@USER_ID", userId);
        inputs.put("@SESSION_ID", sessionId);
        QueryUtils.updateDataFromMap(inputs, "Drop.DynamicTempTables");
    }

    /**
     * Returns true if two arrays having same elements
     *
     * @param current
     * @param old
     * @return
     */
    public static boolean checkProcedureInputIsSame(Object[] current, Object[] old) {

        LOGGER.debug("Inside Procedure input check");
        if (old == null || current == null || current.length != old.length) {
            return false;
        }

        for (int i = 0; i < old.length; i++) {
            if (!String.valueOf(old[i]).trim().equals(String.valueOf(current[i]).trim())) {
                return false;
            }
        }
        LOGGER.debug(" Skipping Procedure call ........Getting from List");

        return true;
    }

    public static String getCustomCCPQueryDPR(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;
        boolean isPrior = false;
        if (projSelDTO instanceof PVSelectionDTO) {
            PVSelectionDTO dto = (PVSelectionDTO) projSelDTO;
            isPrior = dto.isIsPrior();
        }
        int treeLevel = projSelDTO.getTreeLevelNo() == 0 ? 1 : projSelDTO.getTreeLevelNo();
        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + treeLevel;
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + treeLevel;
        }

        String ccpQuery = " IF OBJECT_ID('TEMPDB..#PROJECTION_DETAILS') IS NOT NULL\n"
                + "       DROP TABLE #PROJECTION_DETAILS;\n"
                + "\n"
                + "WITH CCPMAPC\n"
                + "AS (\n"
                + "       SELECT RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "              ,RLD.RELATIONSHIP_LEVEL_SID\n"
                + "              ,RLD.HIERARCHY_NO\n"
                + "              ,CCP.CCP_DETAILS_SID\n"
                + "              ,PD.PROJECTION_DETAILS_SID\n"
                + "       FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "       JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                + "              AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + "       JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " )\n"
                + "       ,CCPMAPP\n"
                + "AS   (\n"
                + "       SELECT RLD.RELATIONSHIP_LEVEL_VALUES\n"
                + "              ,RLD.RELATIONSHIP_LEVEL_SID\n"
                + "              ,RLD.HIERARCHY_NO\n"
                + "              ,CCP.CCP_DETAILS_SID\n"
                + "              ,PD.PROJECTION_DETAILS_SID\n"
                + "       FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "       JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                + "              AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + "       JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "              AND PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ")\n"
                + "       ,CCPMAPP_CTE\n"
                + "  AS (\n"
                + "       SELECT C.CCP_DETAILS_SID\n"
                + "       FROM CCPMAPC C\n"
                + "       INNER JOIN CCPMAPP P ON C.CCP_DETAILS_SID = P.CCP_DETAILS_SID\n"
                + "              AND P.PROJECTION_DETAILS_SID = C.PROJECTION_DETAILS_SID\n"
                + "       ) \n"
                + "       ,HLDC\n"
                + " AS (\n"
                + "       SELECT RLD2.HIERARCHY_NO\n"
                + "              ,RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "              ,CVD.LEVEL_NO\n"
                + "       FROM DBO.CUSTOM_VIEW_DETAILS CVD\n"
                + "       JOIN DBO.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID = HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "              AND CVD.CUSTOM_VIEW_MASTER_SID = " + projSelDTO.getCustomId() + "\n"
                + "              AND CVD.LEVEL_NO LIKE '" + customerLevelNo + "'\n"
                + "       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "       JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "              AND PCH2.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "       WHERE RLD2.HIERARCHY_NO LIKE '" + customerHierarchyNo + "'\n"
                + "              AND EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CCPMAPC C\n"
                + "                     WHERE C.HIERARCHY_NO LIKE RLD2.HIERARCHY_NO + '%'\n"
                + "                     )\n"
                + "       ) \n "
                + "       ,HLDP\n"
                + "AS  (\n"
                + "       SELECT RLD2.HIERARCHY_NO\n"
                + "              ,RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "              ,CVD.LEVEL_NO\n"
                + "       FROM DBO.CUSTOM_VIEW_DETAILS CVD\n"
                + "       JOIN DBO.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID = HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "              AND CVD.CUSTOM_VIEW_MASTER_SID = " + projSelDTO.getCustomId() + "\n"
                + "              AND CVD.LEVEL_NO LIKE '" + productLevelNo + "'\n"
                + "       JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "       JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "              AND PCH2.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "       WHERE RLD2.HIERARCHY_NO LIKE '" + productHierarchyNo + "'\n"
                + "              AND EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CCPMAPP P\n"
                + "                     WHERE P.HIERARCHY_NO LIKE RLD2.HIERARCHY_NO + '%'\n"
                + "                     )\n"
                + "       )\n"
                + "       ,CCP_DETAILS"
                + " AS (\n"
                + "       SELECT DISTINCT C.PROJECTION_DETAILS_SID \n"
                + "              ,RS_CONTRACT_SID ,D.CCP_DETAILS_SID\n"
                + "       FROM CCPMAP" + projSelDTO.getHierarchyIndicator() + " C\n"
                + "       LEFT JOIN HLD" + projSelDTO.getHierarchyIndicator() + " H ON C.HIERARCHY_NO = H.HIERARCHY_NO\n";
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            String groupFilter = getGroupFilterQuery(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
            groupFilter = groupFilter.replace("PD", "C");
            ccpQuery += " " + groupFilter;
        }
        ccpQuery += "       )\n"
                + "\n"
                + "       SELECT * INTO #PROJECTION_DETAILS FROM CCP_DETAILS ";

        return ccpQuery;
    }

    //  ----------------   Added for performance Improvement ----------------
    /**
     * Method used to get the Level Name based on the Level No. Level Details
     * are maintained in a List(List&lt;Leveldto&gt;).
     *
     * @param levelNo
     * @param levelList
     * @return
     */
    public String getLevelNameByNo(final int levelNo, final List<Leveldto> levelList) {
        String levelName = StringUtils.EMPTY;

        for (Leveldto levelDTO : levelList) {

            if (levelDTO.getLevelNo() == levelNo) {
                levelName = levelDTO.getLevel();
                break;
            }

        }

        return levelName;
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

        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator) || Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {

            count = getCountForHierarchy(sessionDTO.getHierarchyLevelDetails(), hierarchyNo, levelNo, hierarchyIndicator);

        } else {

            throw new IllegalArgumentException("Invalid Hierarchy Indicator :" + hierarchyIndicator);

        }
        LOGGER.debug("Count is " + count);
        return count;

    }

    /**
     * Method is used to get the count for a particular level in the hierarchy.
     * Count varies based on the parent level. Count is obtained from map(Can be
     * taken from session DTO based on Hierarchy Indicator) that is populated
     * with the relationship details in data selection Generate , Edit and other
     * activities.
     *
     * @param customerHierarchyMap
     * @param hierarchyNo
     * @param levelNo
     * @return
     */
    private int getCountForHierarchy(final Map<String, List> customerHierarchyMap, final String hierarchyNo, final int levelNo, final String hierarchyIndicator) {
        int count = 0;

        if (levelNo == 0) {
            throw new IllegalArgumentException("Invalid Level No: " + levelNo);
        }

        for (Map.Entry<String, List> entry : customerHierarchyMap.entrySet()) {

            List levelDetailsList = entry.getValue();

            if ((levelNo == Integer.valueOf(levelDetailsList.get(2).toString()) && hierarchyIndicator.equals(levelDetailsList.get(4).toString())) && (StringUtils.isBlank(hierarchyNo) || "%".equals(hierarchyNo) || entry.getKey().startsWith(hierarchyNo))) {

                count++;
            }

        }
        return count;
    }

    /**
     * Method is used to get the count for a particular level in the customer or
     * product hierarchy. Count varies based on the parent level.
     *
     * @param projSelDTO
     * @return
     */
    public int getCount(final ProjectionSelectionDTO projSelDTO) {
        int count = 0;

        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator()) || Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {

            String query = SQlUtil.getQuery("hiearchy-no-count-query");
            String hierarchyQuery = getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo());

            if (StringUtils.isNotBlank(hierarchyQuery)) {
                query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, hierarchyQuery);
                query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, getColumnName(projSelDTO.getHierarchyIndicator()));
                query = query.replace("[?TAB_BASED_JOIN]", getJoinBasedOnTab(projSelDTO.getTabName(), projSelDTO.getGroupFilter(), projSelDTO.getScreenName()));
                query = query.replace("?DEDJOIN",projSelDTO.getTabName().equals("Variance")?" WHERE PV_FILTERS=1 ":StringUtils.EMPTY);
                List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                if (list != null && !list.isEmpty()) {
                    count = Integer.valueOf(list.get(0).toString());
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid Hierarchy Indicator :" + projSelDTO.getHierarchyIndicator());
        }
        LOGGER.debug("Count is " + count);
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
            final int customId, final String customerHierarchyNo, final String productHierarchyNo) {

        int count = 0;
        String countQuery = insertAvailableHierarchyNo(session, hierarchyNo, hierarchyIndicator, levelNo, Boolean.TRUE,
                customId, customerHierarchyNo, productHierarchyNo);
        countQuery += SQlUtil.getQuery("custom-view-count-query");
        countQuery = countQuery.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(Boolean.TRUE, customerHierarchyNo, productHierarchyNo, hierarchyIndicator));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(countQuery, session.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            count = Integer.valueOf(list.get(0).toString());
        }
        LOGGER.debug("Count is  " + count);
        return count;
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
        String selectedHierarchy = getHierarchyJoinQuery(projSelDTO);
        if (!selectedHierarchy.equals(StringUtils.EMPTY)) {
            countQuery += SQlUtil.getQuery("custom-view-count-query");
            countQuery = countQuery.replace(Constant.SELECTED_HIERARCHY_JOIN, selectedHierarchy);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(countQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                count = Integer.valueOf(list.get(0).toString());
            }
        }
        LOGGER.debug("ending getCountForCustomView");
        return count;
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
        query = query.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        query = query.replace(Constant.START_QUESTION, String.valueOf(start));
        query = query.replace(Constant.END_QUESTION, String.valueOf(end));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        } else {
            return Collections.emptyList();
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
     * @param hierarchy
     * @return
     */
    public List getHiearchyNoForCustomViewForExcel(final ProjectionSelectionDTO projSelDTO, int start) {

        String query = SQlUtil.getQuery("custom-view-hiearchy-no-discount-query");
        query = query.replace("[?CUST_RELATIONSHIP_BUILDER_SID]", projSelDTO.getCustRelationshipBuilderSid());
        query = query.replace("[?PROD_RELATIONSHIP_BUILDER_SID]", projSelDTO.getProdRelationshipBuilderSid());
        query = query.replace("[?CUSTOM_VIEW_MASTER_SID]", String.valueOf(projSelDTO.getCustomId()));
        query = query.replace("[?RS_MODEL_SID]", getSelectedRSIDForQuery(projSelDTO));
        query = query.replace(Constant.START_QUESTION, String.valueOf(start));
        query = query.replace(Constant.END_QUESTION, String.valueOf(1000));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        } else {
            return Collections.emptyList();
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
     * @param hierarchy
     * @return
     */
    public List getHiearchyNoAsList(final ProjectionSelectionDTO projSelDTO, int start, int end) {
        String query = SQlUtil.getQuery("hiearchy-no-query");
        query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        query = query.replace(Constant.START_QUESTION, String.valueOf(start));
        query = query.replace(Constant.END_QUESTION, String.valueOf(end));
        query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, getColumnName(projSelDTO.getHierarchyIndicator()));
        query = query.replace("[?TAB_BASED_JOIN]", getJoinBasedOnTab(projSelDTO.getTabName(), projSelDTO.getGroupFilter(), projSelDTO.getScreenName()));
        query = query.replace("?DEDJOIN",projSelDTO.getTabName().equals("Variance")?" WHERE PV_FILTERS=1 ":StringUtils.EMPTY);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        }

        return Collections.EMPTY_LIST;
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
    public List getHiearchyNoAsListForExcel(final ProjectionSelectionDTO projSelDTO, int start, int end, final String hierarchy) {

        String query = SQlUtil.getQuery("hiearchy-no-discount-query-excel");
        query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, hierarchy);
        query = query.replace(Constant.START_QUESTION, String.valueOf(start));
        query = query.replace(Constant.END_QUESTION, String.valueOf(end));
        query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, getColumnName(projSelDTO.getHierarchyIndicator()));
        query = query.replace("[?RS_MODEL_SID]", getSelectedRSIDForQuery(projSelDTO));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        }

        return Collections.emptyList();
    }

    /**
     * Method used to get the actual level no. in the hierarchy from the Custom
     * View Level No. based on the selected Custom View. Custom View Level
     * Details are maintained in a List(List&lt;Leveldto&gt;).
     *
     * @param projSelDTO
     * @return
     */
    public int getActualLevelNoFromCustomView(final SessionDTO session, final int customId, final int treeLevelNo) {
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
        if (levelList != null) {
            for (Leveldto levelDTO : levelList) {
                if (levelDTO.getTreeLevelNo() == projSelDTO.getTreeLevelNo()) {
                    hierarchyIndicator = levelDTO.getHierarchyIndicator();
                    break;
                }
            }
        }
        return hierarchyIndicator;
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
     * Method is used to build the join query. This join determines that
     * hierarchy no associated with CCP that is used to manipulate the data.
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
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    joinQuery.append(" CH.CUST_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(productHierarchyNo)) {
                        joinQuery.append("AND CH.PROD_HIERARCHY_NO LIKE '");
                        joinQuery.append(productHierarchyNo);
                        joinQuery.append("%'");
                    }
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    joinQuery.append(" CH.PROD_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(customerHierarchyNo)) {
                        joinQuery.append("AND CH.CUST_HIERARCHY_NO LIKE '");
                        joinQuery.append(customerHierarchyNo);
                        joinQuery.append("%'");
                    }
                    break;
                default:

                    LOGGER.warn("Invalid Hierarchy Indicator: " + hierarchyIndicator);
            }

        } else {
            joinQuery.append(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator) ? "CH.CUST_HIERARCHY_NO " : "CH.PROD_HIERARCHY_NO");
            joinQuery.append(" LIKE A.HIERARCHY_NO + '%' ");
        }

        return joinQuery.toString();
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
    public String getHierarchyJoinQuery(final SessionDTO sessionDTO, final int customId, final int treeLevelNo, final boolean isCustom, final String hierarchyIndicator, final String customerHierarchyNo, final String productHierarchyNo) {
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
        sql = SQlUtil.getQuery(CommonUtil.isValueEligibleForLoading()&& projSelDTO.isExcelFlag()?"selected-hierarchy-no-customer-excel":(CommonUtil.isValueEligibleForLoading()&&!projSelDTO.isExcelFlag())?"selected-hierarchy-no-customer":Constant.SELECTED_HIERARCHY_NO);
        if (projSelDTO.isIsCustomHierarchy()) {
            String currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(projSelDTO);
            int levelNo = getActualLevelNoFromCustomView(projSelDTO);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getCustomerHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                default:
                    LOGGER.warn("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        }
        sql = sql.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));

        sql += getJoinBasedOnTab(projSelDTO.getTabName(), projSelDTO.getGroupFilter(), projSelDTO.getScreenName());
        if (!projSelDTO.getCustomerLevelFilter().isEmpty() || !projSelDTO.getProductLevelFilter().isEmpty()) {
            sql += AND_SPMFILTER_CC_P1;
        }
        LOGGER.debug("Group Filter Value :  " + projSelDTO.getGroupFilter());
        return sql;

    }

    public static String getCCPQueryForPV(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = QueryUtil.replaceTableNames(new CommonLogic().insertAvailableHierarchyNo(projSelDTO), projSelDTO.getSessionDTO().getCurrentTableNames());
        ccpQuery += "SELECT * FROM #SELECTED_HIERARCHY_NO";
        return ccpQuery;
    }

    public String insertAvailableHierarchyNoForExpand(ProjectionSelectionDTO projSelDTO) {
        String sql;
        sql = SQlUtil.getQuery(Constant.SELECTED_HIERARCHY_NO);
        sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchyForExpand(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        sql = sql.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        sql += getJoinBasedOnTab(projSelDTO.getTabName(), projSelDTO.getGroupFilter(), projSelDTO.getScreenName());
        if (!projSelDTO.getCustomerLevelFilter().isEmpty() || !projSelDTO.getProductLevelFilter().isEmpty()) {
            sql += " AND SPM.FILTER_CCP=1 ";
        }
        LOGGER.debug("Group Filter Value:" + projSelDTO.getGroupFilter());
        return sql;
    }

    public String insertAvailableHierarchyNoForCustomExpand(Set nodeSet,ProjectionSelectionDTO projSelDTO) {
        String queryNameforSales=CommonUtil.isValueEligibleForLoading()?"selected-hierarchy-no-custom-proj":"selected-hierarchy-no-custom";
        String sql;
        sql = SQlUtil.getQuery(queryNameforSales);
        if (!projSelDTO.getCustomerLevelFilter().isEmpty() || !projSelDTO.getProductLevelFilter().isEmpty()) {
            sql += AND_SPMFILTER_CC_P1;
        }
        sql = sql.replace("[?Hierarchy-Combination]", getCustomHierarchies(nodeSet));
        return sql;
    }

    private String getCustomHierarchies(Set<TreeNode> nodeSet) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNotFirstElement = false;
        int i = 1;
        for (TreeNode treeNode : nodeSet) {

            if (isNotFirstElement) {
                stringBuilder.append(",\n");
            }
            stringBuilder.append("('");
            String previousOppositeHierarchy = treeNode.getPreviousOppositeHieararchy(treeNode.getHierarchyIndicator());
            stringBuilder.append(treeNode.getHierachyNo()).
                    append("',");
            if (previousOppositeHierarchy.isEmpty()) {
                stringBuilder.append("NULL");
            } else {
                stringBuilder.append("'").append(previousOppositeHierarchy).append("'");
            }

            stringBuilder.append(",").append("'").append(treeNode.getHierarchyIndicator()).append("',").append(i++);
            stringBuilder.append(")");
            isNotFirstElement = true;
        }
        return stringBuilder.toString();
    }

    public String insertSelectedHierarchHierarchyNo(Set hierarchyNoSet, ProjectionSelectionDTO projSelDTO) {
        String sql;
        sql = SQlUtil.getQuery("selected-hierarchy-no-ordered");
        sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getHiererchyNoFromSet(hierarchyNoSet));
        sql = sql.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        sql += getJoinBasedOnTab(projSelDTO.getTabName(), projSelDTO.getGroupFilter(), projSelDTO.getScreenName());
        if (!projSelDTO.getCustomerLevelFilter().isEmpty() || !projSelDTO.getProductLevelFilter().isEmpty()) {
            sql += AND_SPMFILTER_CC_P1;
        }

        LOGGER.debug("Group Filter Value:" + projSelDTO.getGroupFilter());
        return sql;
    }

    private String getHiererchyNoFromSet(Set hierarchyNoSet) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isNotFirstElement = false;
        int i = 1;
        for (Object object : hierarchyNoSet) {
            if (isNotFirstElement) {
                stringBuilder.append(",\n");
            }
            stringBuilder.append("('");
            stringBuilder.append(((TreeNode) object).getHierachyNo());
            stringBuilder.append("'," + i++ + ")");
            isNotFirstElement = true;
        }

        return stringBuilder.toString();
    }

    public String insertAvailableHierarchyNo(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, boolean isCustomHierarchy,
            final int customId, final String customerHierarchyNo, final String productHierarchyNo) {
        String sql;
        sql = SQlUtil.getQuery(Constant.SELECTED_HIERARCHY_NO);
        if (isCustomHierarchy) {
            String currentHierarchyIndicator = getHiearchyIndicatorFromCustomView(sessionDTO, customId, levelNo);
            int actualLevelNo = getActualLevelNoFromCustomView(sessionDTO, customId, levelNo);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, customerHierarchyNo, currentHierarchyIndicator, actualLevelNo));
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, productHierarchyNo, currentHierarchyIndicator, actualLevelNo));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, getSelectedHierarchy(sessionDTO, hierarchyNo, hierarchyIndicator, levelNo));
        }
        sql = sql.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(sessionDTO, customId, levelNo, isCustomHierarchy, hierarchyIndicator, customerHierarchyNo, productHierarchyNo));
        return sql;

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
            throw new IllegalArgumentException(INVALID_LEVEL_NO + levelNo);
        }

        Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
        StringBuilder stringBuilder = new StringBuilder();

        boolean isNotFirstElement = false;
        boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);

        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if ((Integer.valueOf(entry.getValue().get(2).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(4).toString())) && (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {

                if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("')");

                isNotFirstElement = true;
            }
        }
        if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
            stringBuilder.append("('");
            stringBuilder.append("')");
        }
        return stringBuilder.toString();
    }
    
    public String getSelectedHierarchyForExpand(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {

        if (levelNo == 0) {
            throw new IllegalArgumentException(INVALID_LEVEL_NO + levelNo);
        }

        Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
        StringBuilder stringBuilder = new StringBuilder();

        boolean isNotFirstElement = false;
        boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);

        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            int entryLevel = Integer.valueOf(entry.getValue().get(2).toString());
            if ((entryLevel >= levelNo) && (hierarchyIndicator.equals(entry.getValue().get(4).toString())) && (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {
                if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("')");

                isNotFirstElement = true;
            }
        }
        if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
            stringBuilder.append("('");
            stringBuilder.append("')");
        }
        return stringBuilder.toString();
    }

    public String getGroupFilterJoinQuery(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = StringUtils.EMPTY;
        boolean isPrior = false;
        if (projSelDTO instanceof PVSelectionDTO) {
            PVSelectionDTO dto = (PVSelectionDTO) projSelDTO;
            isPrior = dto.isIsPrior();
        }
        if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            String userGroup = projSelDTO.getGroupFilter();
            ccpQuery += " " + getGroupFilterQueryPR(userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(), isPrior, projSelDTO.getDiscountNoList());
        }
        return ccpQuery;
    }

    public static String getScreenName() {
        return screenName;
    }

    public static String getGroupFilterDiscountQueryPR(boolean isPrior, List<String> discountList) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = " JOIN " + tableIndicator + "NM_DISCOUNT_PROJ_MASTER D ON D.CCP_DETAILS_SID=CH.CCP_DETAILS_SID ";
        if (discountList != null && !discountList.isEmpty()) {
            query += " and D.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(discountList, true) + " )";
        }
        return query;
    }

    public static String getGroupFilterPPAQueryPR(String userGroup, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "  JOIN " + tableIndicator + "NM_PPA_PROJECTION_MASTER P ON P.CCP_DETAILS_SID=CH.CCP_DETAILS_SID WHERE  P.USER_GROUP " + userGroup;
        return query;
    }

    public static String getGroupFilterSalesQueryPR(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "  JOIN " + tableIndicator + "NM_SALES_PROJECTION_MASTER S ON S.CCP_DETAILS_SID=CH.CCP_DETAILS_SID WHERE  S.USER_GROUP " + userGroup;
        if (!isPrior && (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED).equals(screenName)) {
            query += (viewFlag ? "" : getUserSessionQueryCondition(userId, sessionId, Constant.S));
        }
        return query;
    }

    public static String getGroupFilterQueryPR(String userGroup, int userId, int sessionId, boolean isPrior, List<String> discountList) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.ALL)) {
                if (userGroup.contains(Constant.DISCOUNT_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterDiscountQueryPR(isPrior, discountList);
                } else if (userGroup.contains(Constant.PPA_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterPPAQueryPR(userGroup, isPrior);
                } else if (userGroup.contains(Constant.SALES_SMALL)) {
                    userGroup = LIKE_PERCENT;
                    query = getGroupFilterSalesQueryPR(userGroup, userId, sessionId, isPrior);
                }
            } else if (userGroup.startsWith(Constant.DISCOUNT)) {
                userGroup = " = '" + userGroup.replace(Constant.DISCOUNT, StringUtils.EMPTY) + "' ";
                query = getGroupFilterDiscountQueryPR(isPrior, discountList);
            } else if (userGroup.startsWith(Constant.PPA)) {
                userGroup = " = '" + userGroup.replace(Constant.PPA, StringUtils.EMPTY) + "' ";
                query = getGroupFilterPPAQueryPR(userGroup, isPrior);
            } else if (userGroup.startsWith(Constant.SALES_WITH_HYPHEN)) {
                userGroup = " = '" + userGroup.replace(Constant.SALES_WITH_HYPHEN, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQueryPR(userGroup, userId, sessionId, isPrior);
            }
        }
        return query;
    }

    public static void setScreenName(String screenName) {
        CommonLogic.screenName = screenName;
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

    public String getColumnNameCustom(final String hierarchyIndicator) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = "CCPH.CUST_HIERARCHY_NO";
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnName = "CCPH.PROD_HIERARCHY_NO";
        } else {
            columnName = "MAS.DEDUCTION_HIERARCHY_NO";
        }
        return columnName;
    }
    
    /**
     * Method Used to Join the Master tables based on the Tab Name.Method also
     * adds the condition for group Filter, If Group Filter value is not empty.
     *
     * @return
     */
    public String getJoinBasedOnTab(String tabName, String groupFilterValue, String screenName) {
        String sql = StringUtils.EMPTY;
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(screenName)) {
            if (tabName.toLowerCase().contains("sales")) {
                sql += SQlUtil.getQuery("sales-join-nonmandated");
                sql += addGroupFilterCondtion("All Sales Groups", groupFilterValue.trim().equalsIgnoreCase("null") || groupFilterValue.isEmpty() ? Constant.PERCENT : groupFilterValue);
            } else if (tabName.toLowerCase().contains("discount")) {
                sql += SQlUtil.getQuery("discount-join");
                sql += addGroupFilterCondtion(Constant.ALL_DISCOUNT_GROUP, groupFilterValue);
            } else if (tabName.toLowerCase().contains("ppa")) {
                sql += SQlUtil.getQuery("ppa-join");
                sql += addGroupFilterCondtion("All PPA Group", groupFilterValue);
            } else if (tabName.toLowerCase().contains("alternate_history")) {
                sql += SQlUtil.getQuery("alternate-join-count");
            } else if (tabName.toLowerCase().contains("variance")) {
                  sql += SQlUtil.getQuery("discount-join");
            }
        } else if (tabName.toLowerCase().contains("m_discount")) {
            sql += SQlUtil.getQuery("discount-join-mandated");
        } else {
            sql += SQlUtil.getQuery("sales-join-mandated");
        }
        return sql;
    }

    /**
     * Method used to add group filter condition to different tabs based on the
     * default value and group filter value. If the group filter is empty or
     * contains the default value, condition will not be added.
     *
     * @param defaultValue
     * @param groupFilterValue
     * @return
     */
    private String addGroupFilterCondtion(String defaultValue, String groupFilterValue) {
        String sql = StringUtils.EMPTY;
        if (StringUtils.isNotBlank(groupFilterValue) && !defaultValue.equals(groupFilterValue)) {
            sql += SQlUtil.getQuery("user-group-condition");
            sql = sql.replace("[?USER_GROUP]", groupFilterValue);
        }
        return sql;
    }

    public String getFirstLevelNo(int projectionSid, String hierarchyIndicator) {
        String sql = SQlUtil.getQuery("levelNo-Query");
        sql = sql.replace(Constant.AT_TABLE_NAME, hierarchyIndicator.equals("C") ? "PROJECTION_CUST_HIERARCHY" : "PROJECTION_PROD_HIERARCHY");
        sql = sql.replace("@PROJECTION_MASTER_SID", String.valueOf(projectionSid));
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return String.valueOf(resultList.get(0));
    }

    /**
     * This method used to build a String of All Selected hierarchy no.s that
     * can be used in the queries.Current level Hierarchy no.s are obtained from
     * map(Can be taken from session DTO based on Hierarchy Indicator) that is
     * populated with the relationship details in data selection Generate , Edit
     * and other activities.
     *
     * @param sessionDTO
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param levelNo
     * @return
     */
    public List<String> getSelectedHierarchyForExcel(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {
        List<String> finalList = new ArrayList<>();
        Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
        int noOfLevels = 0;
        StringBuilder stringBuilder = new StringBuilder();

        boolean isNotFirstElement = false;
        boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);
        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if ((Integer.valueOf(entry.getValue().get(2).toString()) >= levelNo && hierarchyIndicator.equals(entry.getValue().get(4).toString())) && (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {
                if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(entry.getKey());
                stringBuilder.append("')");
                noOfLevels++;
                isNotFirstElement = true;
            }
        }
        finalList.add(String.valueOf(noOfLevels));
        finalList.add(stringBuilder.toString());
        return finalList;
    }

    /**
     * Returns the List of RS Model SID as Comma separated String.
     *
     * @param sessionDTO
     * @return
     */
    private String getSelectedRSIDForQuery(ProjectionSelectionDTO selection) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> discountIdList = selection.getDiscountList().get(0);
        for (String rsId : discountIdList) {
            stringBuilder.append(CommonUtil.OPEN_PARANTHESIS);
            stringBuilder.append(rsId);
            stringBuilder.append(CommonUtil.CLOSE_PARANTHESIS);
            stringBuilder.append(ConstantsUtils.COMMA);
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public static List getDiscountList(SessionDTO session) {
        String query = "SELECT DISTINCT RS.RS_CONTRACT_SID FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
        return HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }
    
    public static int getMasterSid(String tableName){
        String query = "SELECT MASTER_TABLE_SID FROM dbo.HIERARCHY_TABLE_MASTER WHERE TABLE_NAME='"+tableName+"'";
        return Integer.parseInt(String.valueOf(HelperTableLocalServiceUtil.executeSelectQuery(query).get(0)));
    }
    
    public void insertPFDTemp(SessionDTO session, String methodology, String allocationBasis, boolean isSales) {
        String values, screensName;
        if (isSales) {
            screensName = "S";
            if (Constant.SINGLE_PERIOD.equals(methodology) || Constant.AVERAGE.equals(methodology) || Constant.ROLLINGANNUALTREND.equalsIgnoreCase(methodology)) {
                session.setFileNameUsedInSales(loadFileName(allocationBasis));
                Object[] obj = session.getLatestProjectionFileDetails().get(loadFileName(allocationBasis));
                if (obj == null) {
                    return;
                }
                values = session.getProjectionId() + CommonUtil.COMMA + obj[0] + CommonUtil.COMMA + obj[1] + ",'" + screensName + STRING_COMMA_ONE;
                session.setFileName(obj[2] + " - " + obj[4]);
            } else {
                session.setFileNameUsedInSales(methodology);
                Object[] obj = session.getLatestProjectionFileDetails().get(loadFileName(methodology));
                if (obj == null) {
                    return;
                }
                values = session.getProjectionId() + CommonUtil.COMMA + obj[0] + CommonUtil.COMMA + obj[1] + ",'" + screensName + STRING_COMMA_ONE;
                session.setFileName(obj[2] + " - " + obj[4]);
            }
        } else if (Constant.SINGLE_PERIOD.equals(methodology) || Constant.AVERAGE.equals(methodology) || Constant.ROLLINGANNUALTREND.equalsIgnoreCase(methodology) || Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(methodology)) {
            screensName = "D";
            Object[] obj = session.getLatestProjectionFileDetails().get(loadFileName(session.getFileNameUsedInSales()));
            if (obj == null) {
                return;
            }
            values = session.getProjectionId() + CommonUtil.COMMA + obj[0] + CommonUtil.COMMA + obj[1] + ",'" + screensName + STRING_COMMA_ONE;
        } else if (methodology.equals(CONTRACT_DETAILS.getConstant())) {
            screensName = "C";
            Object[] obj = session.getLatestProjectionFileDetails().get(loadFileName(session.getFileNameUsedInSales()));
            if (obj == null) {
                return;
            }
            values = session.getProjectionId() + CommonUtil.COMMA + obj[0] + CommonUtil.COMMA + obj[1] + ",'" + screensName + STRING_COMMA_ONE;
        } else {
            screensName = "D";
            Object[] obj = session.getLatestProjectionFileDetails().get(loadFileName(methodology));
            if (obj == null) {
                return;
            }
            values = session.getProjectionId() + CommonUtil.COMMA + obj[0] + CommonUtil.COMMA + obj[1] + ",'" + screensName + STRING_COMMA_ONE;
        }
        String query = SQlUtil.getQuery("PFD_TEMP_INSERT");
        query = query.replace("@SCREENNAME", !"S".equals(screensName) ? "'C','D'" : "'" + screensName + "'")
                .replace("@PROJECTIONMASTERSID", String.valueOf(session.getProjectionId()))
                .replace("@TEMPVALUES", values);
        if (isSales) {
            query = query + SQlUtil.getQuery("UPDATE_PFD_DISCOUNT_FLAG").replace("@PROJECTIONMASTERSID", String.valueOf(session.getProjectionId()));
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }
    public static final String STRING_COMMA_ONE = "','1'";

    private String loadFileName(String fileName) {
        if (fileMap.isEmpty()) {
            fileMap.put(Constant.LabelConstants.PERC_OF_EX_FACTORY.getConstant(), Constant.EX_FACTORY_SALES_LABEL);
            fileMap.put(Constant.PERCOFEXFACTORYSALES, Constant.EX_FACTORY_SALES_LABEL);
            fileMap.put(Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND, Constant.EX_FACTORY_SALES_LABEL);
            fileMap.put(Constant.PERCOFDEMAND, Constant.DEMAND);
            fileMap.put(Constant.PERCOFINVENTORYWITHDRAWAL, Constant.INVENTORY_WITHDRAWAL_FORECAST_DETAIL);
            fileMap.put(Constant.PERC_OF_ADJUSTED_DEMAND, Constant.ADJUSTED_DEMAND);
            fileMap.put(Constant.CUSTOMER_GTS, Constant.CUSTOMER_SALES);

            fileMap.put(Constant.EX_FACTORY_SALES_LABEL, Constant.EX_FACTORY_SALES_LABEL);
            fileMap.put(Constant.DEMAND, Constant.DEMAND);
            fileMap.put(Constant.INVENTORY_WITHDRAWAL_FORECAST_DETAIL, Constant.INVENTORY_WITHDRAWAL_FORECAST_DETAIL);
            fileMap.put(Constant.ADJUSTED_DEMAND, Constant.ADJUSTED_DEMAND);
            fileMap.put(Constant.CUSTOMER_SALES, Constant.CUSTOMER_SALES);
        }
        return fileMap.get(fileName);
    }

    public Object[] getFileMethodologyName(SessionDTO session, String methodology) {
        Object[] obj;
        if (Constant.SINGLE_PERIOD.equals(methodology) || Constant.AVERAGE.equals(methodology)
                || Constant.ROLLINGANNUALTREND.equalsIgnoreCase(methodology) || Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(methodology) || methodology.equals(CONTRACT_DETAILS.getConstant())) {
            obj = session.getLatestProjectionFileDetails().get(loadFileName(session.getFileNameUsedInSales()));
        } else {
            obj = session.getLatestProjectionFileDetails().get(loadFileName(methodology));
        }
        return obj;
    }
    
   public ComboBox loadItemUomConversionDdlb(SessionDTO session,ComboBox unitOfMeasureDdlb) {
        List<String> uomList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(SQlUtil.getQuery("uom-dropdown-loading"), session.getCurrentTableNames()));
        if (uomList != null && !uomList.isEmpty()) {
            unitOfMeasureDdlb.addItem(EACH);
            for (String uomCode : uomList) {
                if(uomCode!=null){
                    unitOfMeasureDdlb.addItem(uomCode);
                }
            }
        } else {
             unitOfMeasureDdlb.addItem(EACH);
        }
        return unitOfMeasureDdlb;
    }
    
    public static void updateForFilter(ProjectionSelectionDTO projectionDTO,String indicator,boolean isVariance) {
        String uomQuery = StringUtils.EMPTY;
        String updateAllQuery = SALES.equals(indicator) ? "UPDATE ST_NM_SALES_PROJECTION_MASTER SET FILTER_CCP=null ;":"UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET FILTER_CCP=null ;";
        updateAllQuery = isVariance ? "UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET PV_FILTERS=null;" : updateAllQuery;
        boolean isCustomer=false;
        boolean isProduct=false;
        boolean isDeduction=false;
        
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateAllQuery, projectionDTO.getSessionDTO().getCurrentTableNames()));
        if (!projectionDTO.getCustomerLevelFilter().isEmpty()) {
            uomQuery += SQlUtil.getQuery("update-Customer").replace(BUILDERSID,projectionDTO.getSessionDTO().getCustRelationshipBuilderSid()).replace("@RELATIONSIDS",projectionDTO.getCustomerLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            isCustomer=true;
        }
        if (!projectionDTO.getProductLevelFilter().isEmpty()) {
            uomQuery += SQlUtil.getQuery("update-Product").replace(BUILDERSID, projectionDTO.getSessionDTO().getProdRelationshipBuilderSid()).replace("@RELATIONSIDS",projectionDTO.getProductLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            isProduct=true;
        }
         if ((!projectionDTO.getDeductionLevelFilter().isEmpty()|| !projectionDTO.getDeductionLevelFilterPv().isEmpty()) && DEDUCTION.equals(indicator)) {
            uomQuery += SQlUtil.getQuery(Constant.DEDUCTION_DYNAMIC_FILTER).replace(Constant.DEDUCTION_SID, projectionDTO.getSessionDTO().getDedRelationshipBuilderSid()).replace(DEDLEVEL_VALUES,projectionDTO.getDeductionLevelFilter().toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            isDeduction=true;
         }
        
        if (!uomQuery.isEmpty()) {
            uomQuery += !isVariance?SQlUtil.getQuery("update-Filter-CCP"):SQlUtil.getQuery("update-Filter-CCP").replace("FILTER_CCP", "PV_FILTERS");
            if (isCustomer) {
                uomQuery += " JOIN #CUSTOMER CUS ON ST.CUST_HIERARCHY_NO LIKE CUS.HIERARCHY_NO + '%' ";
            }
            if (isProduct) {
                uomQuery += " JOIN #PRODUCT PRO ON ST.PROD_HIERARCHY_NO LIKE PRO.HIERARCHY_NO + '%' ";
            }
            
            uomQuery += SALES.equals(indicator) ? "JOIN ST_NM_SALES_PROJECTION_MASTER SPM ON SPM.CCP_DETAILS_SID = ST.CCP_DETAILS_SID " : " JOIN ST_NM_DISCOUNT_PROJ_MASTER SPM ON SPM.CCP_DETAILS_SID = ST.CCP_DETAILS_SID ";
            
            if (isDeduction) {
                uomQuery += " JOIN  #HIER_DEDUCTION_PROD PRO1 ON SPM.DEDUCTION_HIERARCHY_NO LIKE PRO1.HIERARCHY_NO + '%' ";
            }
           
        }else{
            if (DEDUCTION.equals(indicator) && isVariance) {
                uomQuery = " UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET PV_FILTERS=1 ";
            } else if (SALES.equals(indicator)) {
                uomQuery = "UPDATE ST_NM_SALES_PROJECTION_MASTER SET FILTER_CCP=1 ";
            } else if (DEDUCTION.equals(indicator)) {
                uomQuery = " UPDATE ST_NM_DISCOUNT_PROJ_MASTER SET FILTER_CCP=1 ";
            }
        }
        
        if (!uomQuery.isEmpty()) {
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(uomQuery, projectionDTO.getSessionDTO().getCurrentTableNames()));
        }
    }
    
    
     public static void loadCustomMenuBar(List<Object[]> listOfLevelFilter,CustomMenuBar.CustomMenuItem filterValues) throws IllegalStateException {
        String newLevel=StringUtils.EMPTY;
        String oldLevel = StringUtils.EMPTY;
        String listOfSids = StringUtils.EMPTY;
        CustomMenuBar.CustomMenuItem[] customerlevelCustomItem = new CustomMenuBar.CustomMenuItem[listOfLevelFilter.size()];
        customerlevelCustomItem[0] = filterValues.addItem(new MenuItemDTO(listOfLevelFilter.get(0)[0], listOfLevelFilter.get(0)[1].toString()), null);
        customerlevelCustomItem[0].setCheckable(true);
        customerlevelCustomItem[0].setItemClickable(true);
        customerlevelCustomItem[0].setItemClickNotClosable(true);
        customerlevelCustomItem[0].setCheckAll(true);
        for (int i = 1; i < listOfLevelFilter.size(); i++) {
            MenuItemDTO dto = null;
            Object[] obj = listOfLevelFilter.get(i);
            newLevel = obj[0].toString();
            if (oldLevel.equals(newLevel)) {
                listOfSids += "," + obj[1].toString();
                oldLevel = newLevel;
            } else {
                if (i != 1) {
                    dto = new MenuItemDTO(listOfSids, oldLevel);
                    listOfSids = "";
                    customerlevelCustomItem[i] = filterValues.addItem(dto, null);
                    customerlevelCustomItem[i].setCheckable(true);
                    customerlevelCustomItem[i].setItemClickable(true);
                    customerlevelCustomItem[i].setItemClickNotClosable(true);
                }
                listOfSids += obj[1].toString();
                oldLevel = newLevel;
            }
            if (i == listOfLevelFilter.size() - 1) {
                dto = new MenuItemDTO(listOfSids, newLevel);
                customerlevelCustomItem[i] = filterValues.addItem(dto, null);
                customerlevelCustomItem[i].setCheckable(true);
                customerlevelCustomItem[i].setItemClickable(true);
                customerlevelCustomItem[i].setItemClickNotClosable(true);
            }
        }
    }
     
     public void loadUnitOfMeasureDdlb(ComboBox unitOfMeasureDdlb,SessionDTO session) {
        unitOfMeasureDdlb.removeAllItems();
        unitOfMeasureDdlb.setNullSelectionAllowed(true);
        unitOfMeasureDdlb = loadItemUomConversionDdlb(session,unitOfMeasureDdlb);
        unitOfMeasureDdlb.setNullSelectionItemId(EACH);
        unitOfMeasureDdlb.select(EACH);
        unitOfMeasureDdlb.markAsDirty();
    }
     
       public List<Object[]> getCustomerLevelValues(int projectionId, String type, ProjectionSelectionDTO projDto,List<Object> productList,List<Object> deductionList) {
        SalesProjectionDAO salesProjectionDao = new SalesProjectionDAOImpl();
        String maintableName = "CONTRACT_MASTER";
        String companyMaster = Constant.COMPANY_MASTER;
        List<Object[]> stockList = new ArrayList<>();
        List tableFieldNameList = new ArrayList<>();
        try {
            tableFieldNameList = (List) salesProjectionDao.executeSelectQuery(SQlUtil.getQuery("sales-filter-customer")
                    .replace(Constant.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
                    .replace(LEVEL_CAPS, type));
            
            if (!tableFieldNameList.isEmpty()) {
                String userDefined= userDefinedLevel(salesProjectionDao, projectionId, type,"C");
                Object[] tableFieldName = (Object[]) tableFieldNameList.get(0);

                String fieldName = String.valueOf(tableFieldName[0]);
                String tableName = String.valueOf(tableFieldName[1]);

                String primaryKey = getPrimaryKeyColumn(maintableName);

                Set<String> list = new HashSet();
                list.add(maintableName);
                list.add(companyMaster);
                if (!tableName.isEmpty()) {
                    list.add(tableName);
                }
                if (tableName.contains("COMPANY")) {
                    maintableName = companyMaster;
                    primaryKey = getPrimaryKeyColumn(maintableName);
                }
                String gtnFrameworkQuery = gtnFrameworkHierarchyServiceImpl.getQueryByTableNameAndHierarchyTypeForMultiLevel(new ArrayList<>(list), "CUSTOMER HIERARCHY");

                GtnFrameworkEntityMasterBean masterBean = GtnFrameworkEntityMasterBean.getInstance();
                GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = masterBean.getKeyRelationBeanUsingTableIdAndColumnName(tableName, fieldName);

                String fullUserDefinedQuery = SQlUtil.getQuery("user-defined-level-values").replace(Constant.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
                        .replace(LEVEL_CAPS, type);
                String formedQuery = StringUtils.EMPTY;
                String query = fullUserDefinedQuery;
                boolean isuserDefined="User Defined".equals(userDefined);
                if (!isuserDefined) {
                    formedQuery = queryFormationForLoadingDdlb(fieldName, tableName, primaryKey, gtnFrameworkQuery, singleColumnRelationBean, maintableName, "C");
                    query = formedQuery;
                }
               
                query = (productList.isEmpty() || isuserDefined) ? query
                        : (SQlUtil.getQuery("product-dynamic-filter").replace(LEVEL_VALUES, productList.toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                                .replace(RELBUILD_SID, projDto.getSessionDTO().getProdRelationshipBuilderSid()) + query + " JOIN #HIER_PRODUCT HP ON ST_CCP_HIERARCHY.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' ");

                query = (deductionList.isEmpty() || isuserDefined) ? query
                        : (SQlUtil.getQuery("deduction-dynamic-filter").replace(DEDLEVEL_VALUES, deductionList.toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                                .replace("@DEDRELBUILDSID", projDto.getSessionDTO().getDedRelationshipBuilderSid()) + query + " JOIN ST_NM_DISCOUNT_PROJ_MASTER SDPM ON SDPM.CCP_DETAILS_SID=ST_CCP_HIERARCHY.CCP_DETAILS_SID "
                        + " JOIN #HIER_DEDUCTION_PROD HD ON SDPM.DEDUCTION_HIERARCHY_NO LIKE HD.HIERARCHY_NO+'%' ");

                stockList = (List<Object[]>) salesProjectionDao.executeSelectQuery(QueryUtil.replaceTableNames(query, projDto.getSessionDTO().getCurrentTableNames()));
                return stockList;

            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return stockList;
    }
    public static final String LEVEL_VALUES = "@LEVELVALUES";

    public List<Object[]> getProductLevelValues(int projectionId, String type, ProjectionSelectionDTO projectionDto,List<Object> customerFilter,List<Object> deductionFilter) {
        SalesProjectionDAO salesProjectionDao = new SalesProjectionDAOImpl();
        List stockList = new ArrayList<>();
        List tableFieldNameList = new ArrayList<>();
        try {
            tableFieldNameList = (List) salesProjectionDao.executeSelectQuery(SQlUtil.getQuery("sales-filter-product")
                    .replace(Constant.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId)).replace(LEVEL_CAPS, type));
            if (!tableFieldNameList.isEmpty()) {
                Object[] tableFieldName = (Object[]) tableFieldNameList.get(0);

                String userDefined = userDefinedLevel(salesProjectionDao, projectionId, type, "P");
                String fieldName = String.valueOf(tableFieldName[0]);
                String tableName = String.valueOf(tableFieldName[1]);
                String mainTableName = "ITEM_MASTER";
                List tableNameList = new ArrayList<>();
                tableNameList.add(mainTableName);
                tableNameList.add(tableName);
                String primaryKey = getPrimaryKeyColumn(mainTableName);

                String gtnFrameworkRouteBean = gtnFrameworkHierarchyServiceImpl.getQueryByTableNameAndHierarchyTypeForMultiLevel(tableNameList, "PRODUCT HIERARCHY");
                GtnFrameworkEntityMasterBean masterBean = GtnFrameworkEntityMasterBean.getInstance();
                GtnFrameworkSingleColumnRelationBean singleColumnRelationBean = masterBean.getKeyRelationBeanUsingTableIdAndColumnName(tableName, fieldName);
                String query = "User Defined".equals(userDefined)
                        ? SQlUtil.getQuery("user-defined-level-values").replace(Constant.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
                                .replace(LEVEL_CAPS, type)
                        : queryFormationForLoadingDdlb(fieldName, tableName, primaryKey, gtnFrameworkRouteBean,
                                singleColumnRelationBean, mainTableName, "P");

                query = customerFilter.isEmpty() ? query
                        : (SQlUtil.getQuery("customer-dynamic-filter").replace(LEVEL_VALUES, customerFilter.toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                                .replace(RELBUILD_SID, projectionDto.getSessionDTO().getCustRelationshipBuilderSid()) + query + ("User Defined".equals(userDefined)? StringUtils.EMPTY:" JOIN #HIER_CUST HP ON ST_CCP_HIERARCHY.CUST_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' "));

                query = deductionFilter.isEmpty() ? query
                        : (SQlUtil.getQuery("deduction-dynamic-filter").replace(DEDLEVEL_VALUES, deductionFilter.toString().replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY))
                                .replace("@DEDRELBUILDSID", projectionDto.getSessionDTO().getDedRelationshipBuilderSid()) + query + " JOIN ST_NM_DISCOUNT_PROJ_MASTER SDPM ON SDPM.CCP_DETAILS_SID=ST_CCP_HIERARCHY.CCP_DETAILS_SID "
                        + " JOIN #HIER_DEDUCTION_PROD HD ON SDPM.DEDUCTION_HIERARCHY_NO LIKE HD.HIERARCHY_NO+'%' ");

                stockList = (List<Object[]>) salesProjectionDao.executeSelectQuery(QueryUtil.replaceTableNames(query, projectionDto.getSessionDTO().getCurrentTableNames()));
                return stockList;
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return stockList;
    }
    
    
    public List<Object[]> getDeductionLevelValues(int projectionId, String type, ProjectionSelectionDTO projectionDto,List<Object> productFilter,List<Object> customerFilter) {
        SalesProjectionDAO salesProjectionDao = new SalesProjectionDAOImpl();
        List deductionValuesList = new ArrayList<>();
        StringBuilder query=new StringBuilder();
        String selectClause=" HT.DESCRIPTION,HT.HELPER_TABLE_SID ";
        String joinClause=StringUtils.EMPTY;
        String udcJoinClause=" JOIN UDCS  UDC ON UDC.MASTER_SID=RS.RS_CONTRACT_SID AND UDC.MASTER_TYPE='RS_CONTRACT' ";
        try {
            switch (Integer.parseInt(type)) {
                case 1:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_CATEGORY  AND HT.HELPER_TABLE_SID <> 0 ";
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                case 2:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                case 3:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE AND HT.HELPER_TABLE_SID <> 0 ";
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                case 4:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC1 AND HT.HELPER_TABLE_SID <> 0 ";
                    break;

                case 5:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC2 AND HT.HELPER_TABLE_SID <> 0 ";
                    break;
                case 6:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC3 AND HT.HELPER_TABLE_SID <> 0 ";
                    break;
                case 7:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC4 AND HT.HELPER_TABLE_SID <> 0 ";
                    break;
                case 8:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC5 AND HT.HELPER_TABLE_SID <> 0 ";
                    break;
                case 9:
                    joinClause = " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UDC.UDC6 AND HT.HELPER_TABLE_SID <> 0 ";
                    break;
                case 10:
                    selectClause = " RS.RS_ID,RS.RS_CONTRACT_SID ";
                    joinClause = StringUtils.EMPTY;
                    udcJoinClause=StringUtils.EMPTY;
                    break;
                default:
                    LOGGER.info("More than 10 Levels");

            }
            query.append("SELECT ").append(selectClause).append(" FROM ST_NM_DISCOUNT_PROJ_MASTER DPM ");
            query.append(" JOIN RS_CONTRACT RS ON DPM.RS_CONTRACT_SID = RS.RS_CONTRACT_SID ").append(udcJoinClause).append(joinClause);
            query.append(" JOIN ST_CCP_HIERARCHY CCP ON CCP.CCP_DETAILS_SID=DPM.CCP_DETAILS_SID  ");

            if (!productFilter.isEmpty()) {
                String oldCustomerQuery=query.toString();
                query=new StringBuilder();
                oldCustomerQuery = SQlUtil.getQuery("product-dynamic-filter") + oldCustomerQuery + " JOIN #HIER_PRODUCT HP ON CCP.PROD_HIERARCHY_NO LIKE HP.HIERARCHY_NO+'%' ";
                oldCustomerQuery= oldCustomerQuery.replace(LEVEL_VALUES,productFilter.toString().replace("[", "").replace("]", "")).replace(RELBUILD_SID, projectionDto.getSessionDTO().getProdRelationshipBuilderSid());
                query.append(oldCustomerQuery);
            }
            if (!customerFilter.isEmpty()) {
                String oldProductQuery=query.toString();
                query=new StringBuilder();
                oldProductQuery= SQlUtil.getQuery("customer-dynamic-filter")+oldProductQuery+" JOIN #HIER_CUST HC ON CCP.CUST_HIERARCHY_NO LIKE HC.HIERARCHY_NO+'%' ";
                oldProductQuery= oldProductQuery.replace(LEVEL_VALUES,customerFilter.toString().replace("[", "").replace("]", "")).replace(RELBUILD_SID, projectionDto.getSessionDTO().getCustRelationshipBuilderSid());
                query.append(oldProductQuery);
            }
            query.append(" GROUP BY ").append(selectClause);
            deductionValuesList = (List<Object[]>) salesProjectionDao.executeSelectQuery(QueryUtil.replaceTableNames(query.toString(),projectionDto.getSessionDTO().getCurrentTableNames()));

        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return deductionValuesList;
    }

    public String userDefinedLevel(SalesProjectionDAO salesProjectionDao, int projectionId, String type,String indicator) throws SystemException, PortalException {
        String hierarchySid=indicator.equals("P")?"PRODUCT_HIERARCHY_SID":"CUSTOMER_HIERARCHY_SID";
        List<String> userDefinedList= (List<String>) salesProjectionDao.executeSelectQuery(SQlUtil.getQuery("user-defined-join")
                .replace(Constant.PROJECTION_MASTER_SID_AT, String.valueOf(projectionId))
                .replace(LEVEL_CAPS, type).replace(Constant.HIERARCHY_SID_AT, hierarchySid));
       
        return String.valueOf(userDefinedList.get(0));
    }

    private String getPrimaryKeyColumn(String mainTableName) throws SystemException, PortalException {
        SalesProjectionDAO salesProjectionDao = new SalesProjectionDAOImpl();
        List primaryKeyList = (List) salesProjectionDao.executeSelectQuery(SQlUtil.getQuery("primary-Key").replace("@TABLENAME", mainTableName));
        return String.valueOf(primaryKeyList.get(0));
    }

    private String queryFormationForLoadingDdlb(String fieldName, String childTableName,String primaryKey ,String joinQuery, GtnFrameworkSingleColumnRelationBean singleColumnRelationBean,String mainTable,String indicator) {
        StringBuilder formedQuery = new StringBuilder();
        String aliasNameField = childTableName + "." + fieldName;
        String keyField = mainTable + "." + primaryKey;
        String helperJoin = gtnFrameworkHierarchyServiceImpl.addTableJoin(singleColumnRelationBean);
            if (helperJoin.isEmpty()) {
                formedQuery.append("SELECT distinct ").append(aliasNameField).append(",").append(keyField).append(" FROM ");
                formedQuery.append(joinQuery);
                if (indicator.equals("C")) {
                    formedQuery.append(" JOIN dbo.CCP_DETAILS AS CCP_DETAILS ON CCP_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID");
                    formedQuery.append(" AND CCP_DETAILS.CONTRACT_MASTER_SID=CONTRACT_MASTER.CONTRACT_MASTER_SID");
                } else {
                    formedQuery.append(" JOIN dbo.CCP_DETAILS AS CCP_DETAILS ON CCP_DETAILS.ITEM_MASTER_SID = ITEM_MASTER.ITEM_MASTER_SID");
                }
                formedQuery.append(" JOIN dbo.ST_CCP_HIERARCHY AS ST_CCP_HIERARCHY ON ST_CCP_HIERARCHY.CCP_DETAILS_SID");
                formedQuery.append(" = CCP_DETAILS.CCP_DETAILS_SID");
            } else {
                List<String> columnList = gtnFrameworkHierarchyServiceImpl.getMappingColumns(singleColumnRelationBean);
                formedQuery.append("SELECT distinct ").append(columnList.get(0)).append(",").append(columnList.get(1)).append(" FROM ");
                formedQuery.append(joinQuery);
                formedQuery.append(helperJoin);
                if (indicator.equals("C")) {
                    formedQuery.append(" JOIN dbo.CCP_DETAILS AS CCP_DETAILS ON CCP_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID ");
                    formedQuery.append(" AND CCP_DETAILS.CONTRACT_MASTER_SID=CONTRACT_MASTER.CONTRACT_MASTER_SID");
                } else {
                    formedQuery.append(" JOIN dbo.CCP_DETAILS AS CCP_DETAILS ON CCP_DETAILS.ITEM_MASTER_SID = ITEM_MASTER.ITEM_MASTER_SID");
                }
                formedQuery.append(" JOIN dbo.ST_CCP_HIERARCHY AS ST_CCP_HIERARCHY ON ST_CCP_HIERARCHY.CCP_DETAILS_SID");
                formedQuery.append(" = CCP_DETAILS.CCP_DETAILS_SID");
            }
  
        return formedQuery.toString();
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
    
    
    
    public Map<String, List<Object>> getFilterValues(CustomMenuBar.CustomMenuItem filterValues) {
        Map<String, List<Object>> keyValueMap = new HashMap<>();
        List<Object> valuesList = new ArrayList<>();
        List<Object> captionList = new ArrayList<>();
        if (filterValues != null && filterValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = filterValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked() && !String.valueOf(customMenuItem1.getMenuItem().getWindow()).equals("0")) {
                    valuesList.add(customMenuItem1.getMenuItem().getWindow());
                    captionList.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
        }
        keyValueMap.put("SID", valuesList);
        keyValueMap.put("CAPTION", captionList);
        return keyValueMap;
    }
    
    
    public List<Object[]> displayFormatValues() {
        String query = "SELECT HELPER_TABLE_SID,DESCRIPTION FROM dbo.HELPER_TABLE WHERE LIST_NAME = 'DISPLAY_FORMAT' ORDER BY CREATED_DATE;";
        return (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }
    
    public void loadDisplayFormat(List<Object[]> listOfLevelFilter, CustomMenuBar.CustomMenuItem filterValues) {
        CustomMenuBar.CustomMenuItem[] customerlevelCustomItem = new CustomMenuBar.CustomMenuItem[listOfLevelFilter.size()];
        for (int i = 0; i < listOfLevelFilter.size(); i++) {
            Object[] obj = listOfLevelFilter.get(i);
            MenuItemDTO dto = new MenuItemDTO(i, obj[1].toString());
            customerlevelCustomItem[i] = filterValues.addItem(dto, null);
            customerlevelCustomItem[i].setCheckable(true);
            customerlevelCustomItem[i].setItemClickable(true);
            customerlevelCustomItem[i].setItemClickNotClosable(true);
            customerlevelCustomItem[i].setChecked(true);
        }
    }
    
    public static void loadMenuBar(List<String> levelFilter,CustomMenuBar.CustomMenuItem filterValues) {
        for (String string : levelFilter) {
           checkMenuBarItem(filterValues, string);
        }
    }
    
    public static void unCheckMultiSelect(CustomMenuBar.CustomMenuItem filterValues) {
        if (filterValues != null && filterValues.getChildren() != null) {
            for (CustomMenuBar.CustomMenuItem string : filterValues.getChildren()) {
                string.setChecked(false);
            }
        }
    }
    
       public String getSelectedHierarchyDeduction(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {

        if (levelNo == 0) {
            throw new IllegalArgumentException(INVALID_LEVEL_NO + levelNo);
        }

        Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
        StringBuilder stringBuilder = new StringBuilder();

        boolean isNotFirstElement = false;
        boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo) || "D".equals(hierarchyIndicator);

        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if ((Integer.valueOf(entry.getValue().get(2).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(4).toString())) && (isHierarchyNoNotAvailable)) {

                if (isNotFirstElement) {
                    stringBuilder.append(",\n");
                }
                stringBuilder.append("('");
                stringBuilder.append(entry.getValue().get(3).toString());
                stringBuilder.append("')");

                isNotFirstElement = true;
            }
        }
        if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
            stringBuilder.append("('");
            stringBuilder.append("')");
        }
        return stringBuilder.toString();
    }

    
        public String getColumnNameCustomRel(final String hierarchyIndicator,final String parentHierarchyNo,SessionDTO sessionDTO) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = " AND CCPH.CUST_HIERARCHY_NO LIKE '"+ parentHierarchyNo +"%'";
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnName = "AND CCPH.PROD_HIERARCHY_NO LIKE '"+ parentHierarchyNo +"%'";
        } else {
            String hierarchyNo = replacePercentHierarchy(parentHierarchyNo);
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.PARENT_HIERARCHY_NO LIKE '"+ hierarchyNo +"' and  relationship_builder_sid = "+ sessionDTO.getDedRelationshipBuilderSid() +" JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=MAS.RS_CONTRACT_SID\n " +
                " AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'";
        }
        return columnName;
    }
    
    public String getColumnNameCustomDed(final String hierarchyIndicator) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = " CCPH.CUST_HIERARCHY_NO LIKE SHN.HIERARCHY_NO+'%' ";
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnName = " CCPH.PROD_HIERARCHY_NO LIKE SHN.HIERARCHY_NO+'%' ";
        } else {
            columnName = " RLD.RELATIONSHIP_LEVEL_VALUES = SHN.HIERARCHY_NO ";
        }
        return columnName;
    }
    
    public String getDedCustomJoin(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO=SHN.HIERARCHY_NO ";
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO=SHN.HIERARCHY_NO ";
        } else {
            String parentHierNo = replacePercentHierarchy(hierarchyNo);
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON LEVEL_NO = "+ levelNo +" AND RLD.PARENT_HIERARCHY_NO LIKE '"+ parentHierNo +"' and relationship_builder_sid = "+ sessionDTO.getDedRelationshipBuilderSid() +" JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=MAS.RS_CONTRACT_SID\n " +
"                     AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'";
        }
        return columnName;
    }

    public String getDedCustomJoinGenerate(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO=A.HIERARCHY_NO ";
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD1 ON RLD1.HIERARCHY_NO=A.HIERARCHY_NO ";
        } else {
            String parentHierarchyNo =  replacePercentHierarchy(hierarchyNo);
            columnName = " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.relationship_level_values=A.HIERARCHY_NO AND LEVEL_NO = "+ levelNo +" AND RLD.PARENT_HIERARCHY_NO LIKE '"+ parentHierarchyNo +"' and relationship_builder_sid = "+ sessionDTO.getDedRelationshipBuilderSid() +" JOIN #PARENT_VALIDATE PR ON PR.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n " +
            "                     AND PR.PARENT_HIERARCHY LIKE RLD.PARENT_HIERARCHY_NO+'%'";       
        }
        return columnName;
    }
    
    
    public String getSelectStatementCustom(final String hierarchyIndicator) {
        String columnName;
        if (hierarchyIndicator.equalsIgnoreCase("C")) {
            columnName = " SHN.HIERARCHY_NO ";
        } else if (hierarchyIndicator.equalsIgnoreCase("P")) {
            columnName = " SHN.HIERARCHY_NO ";
        } else {
            columnName = " RLD.RELATIONSHIP_LEVEL_VALUES ";
        }
        return columnName;
    }
    
     public String replacePercentHierarchy(String hierarchyNo) {
         String percentHierarchy;
        if (StringUtils.isEmpty(hierarchyNo)) {
            percentHierarchy = "%";
        } else {
            percentHierarchy = hierarchyNo.contains("~") ? "%"+hierarchyNo.replace("~","%")+"%" : "%"+hierarchyNo+"%";
        }
        return percentHierarchy;
    }

}