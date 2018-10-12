/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.app.cff.ui.fileselection.util.ConstantsUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.Constants.IndicatorConstants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);// Logger
    private static CommonServiceImpl instance = null;

    private CommonServiceImpl() {
        //default
    }

    public static synchronized CommonServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommonServiceImpl();
        }
        return instance;
    }

    public List fetchFieldsForSecurity(String moduleName, String tabName) {
        String query = "SELECT DISPLAY_NAME, PROPERTY_NAME ,CATEGORY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '" + moduleName + "' "
                + " AND TAB_NAME = '" + tabName + "' AND CATEGORY_NAME NOT IN ('Button','Tab') ";
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public List getDetailsResults(String fileName, String version, String fileType, String country) {
        String sqlString = StringUtils.EMPTY;
        try {
            sqlString = SQlUtil.getQuery("getDetailsResults");
            sqlString = sqlString.concat("'").concat(fileName).concat("'");
            if ("Gross Trade Sales".equals(fileType) && "US".equals(country)) {
                sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FORESIGHT' OR FM.SOURCE LIKE 'LE_FORESIGHT')");
            } else if ("Gross Trade Sales".equals(fileType) && "PR".equals(country)) {
                sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_SALES')");
            } else if ("Vaccine Segmentation".equals(fileType)) {
                sqlString = sqlString.concat(" AND (FM.SOURCE LIKE 'FF_VACCINE')");
            }
            sqlString = sqlString.concat(" AND FM.COUNTRY='").concat(country).concat("'");

            if (version.contains("~")) {
                String[] versionArray = version.split("~");
                sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
            } else {
                sqlString = sqlString.concat(StringConstantsUtil.AND).concat(" FM.FORECAST_VER='").concat(version).concat("'");
            }
            sqlString = sqlString.concat(" ORDER BY FM.FORECAST_YEAR");
            return HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(sqlString);
            return Collections.emptyList();
        }
    }

    public List getBusinessFunctionPermission(String businessRoleId, String moduleName) {
        String[] str = null;
        String mod;
        String sql = StringUtils.EMPTY;
        if (moduleName.contains(",")) {
            str = moduleName.split(",");
            mod = str[0];
        } else {
            mod = moduleName;
        }
        try {
            sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.functionPermission");
            if (businessRoleId.length() != 0) {
                sql += StringConstantsUtil.UDM_BUSINESS_ROLE_MASTER_SID
                        + businessRoleId + ")";
            }
            if (mod.length() != 0) {
                sql += StringConstantsUtil.AND_SPM_MODULE_NAME + mod + "') ";
            }

            if (str != null && !str[1].equals(StringUtils.EMPTY) && str[1].length() != 0) {
                sql += " AND spm.TAB_NAME in ('" + str[1] + "') ";
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return Collections.emptyList();
        }

    }

    public List getBusinessFieldPermission(String businessRoleId, String moduleName) {
        String sql = StringUtils.EMPTY;
        try {
            String[] str = null;
            String mod;
            if (moduleName.contains(",")) {
                str = moduleName.split(",");
                mod = str[0];
            } else {
                mod = moduleName;
            }

            if (mod.equals("Item Hierarchy")) {
                sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermissionForTransaction");
            } else {
                sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");
            }
            if (businessRoleId.length() != 0) {
                sql += StringConstantsUtil.UDM_BUSINESS_ROLE_MASTER_SID
                        + businessRoleId + ")";
            }
            if (mod.length() != 0) {
                sql += StringConstantsUtil.AND_SPM_MODULE_NAME + mod + "') ";
            }
            if (str != null && !str[1].equals(StringUtils.EMPTY) && str[1].length() != 0) {
                sql += " AND spm.TAB_NAME like ('" + str[1] + "') ";
            }
            sql = ((moduleName.equalsIgnoreCase("Demand")) || (moduleName.equalsIgnoreCase("Returns")) || (moduleName.equalsIgnoreCase("Inventory"))
                    || (moduleName.equalsIgnoreCase("Item Hierarchy")) || (moduleName.equalsIgnoreCase("IvldReturns")) || (moduleName.equalsIgnoreCase("IvldCompanyMaster")) || (moduleName.equalsIgnoreCase("IvldItemMaster")) || (moduleName.equalsIgnoreCase("IvldItemPricing")) || (moduleName.equalsIgnoreCase("IvldItemIdentifier")) || (moduleName.equalsIgnoreCase("IvldCompanyIdentifier")) || (moduleName.equalsIgnoreCase("IvldCompanyParent")) || (moduleName.equalsIgnoreCase("IvldCompanyTradeClass")) || (moduleName.equalsIgnoreCase("IvldCustomerGtsForecast")) || (moduleName.equalsIgnoreCase("IvldCustomerGtsActual")) || ("GlobalFilesCompanyIdentifier,GlobalFilesCompanyIdentifier".equalsIgnoreCase(moduleName))) ? sql.replace("distinct", "") : sql;
            sql = ((moduleName.equalsIgnoreCase("Demand,Demand")) || (moduleName.equalsIgnoreCase("Returns,Returns")) || (moduleName.equalsIgnoreCase("IvldItemMaster,View")) || (moduleName.equalsIgnoreCase("IvldItemPricing,View")) || (moduleName.equalsIgnoreCase("IvldItemIdentifier,View")) || (moduleName.equalsIgnoreCase("Inventory,Inventory"))
                    || (moduleName.equalsIgnoreCase("Item Hierarchy,Item Hierarchy")) || ("Sales Master".equalsIgnoreCase(moduleName)) || ("Customer Sales".equalsIgnoreCase(moduleName)) || (moduleName.equalsIgnoreCase("GlobalFilesCompanyMaster,View")) || (moduleName.equalsIgnoreCase("IvldCompanyIdentifier,View")) || (moduleName.equalsIgnoreCase("IvldCompanyParent,View")) || (moduleName.equalsIgnoreCase("IvldCompanyTradeClass,View")) || ("GlobalFilesItemIdentifier".equalsIgnoreCase(moduleName)) || ("GlobalFilesItemPricing".equalsIgnoreCase(moduleName)) || ("GlobalFilesItemMaster".equalsIgnoreCase(moduleName)) || ("GlobalFilesCompanyMaster".equalsIgnoreCase(moduleName))
                    || ("GlobalFilesCompanyIdentifier,GlobalFilesCompanyIdentifier".equalsIgnoreCase(moduleName)) || ("GlobalFilesCompanyParent".equalsIgnoreCase(moduleName)) || ("GlobalFilesCompanyTradeClass".equalsIgnoreCase(moduleName))
                    || ("Actual GTS Customer Product".equalsIgnoreCase(moduleName)) || ("Cpi Index".equalsIgnoreCase(moduleName)) || ("Audit Inbound".equalsIgnoreCase(moduleName)) || ("Actual Master".equalsIgnoreCase(moduleName)) || ("Forecast Sales".equalsIgnoreCase(moduleName)) || ("IvldActualMaster".equalsIgnoreCase(moduleName))) ? sql.replace("distinct", "") : sql;

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return Collections.emptyList();
        }
    }

    public List getBusinessTabPermission(String businessRoleId, String moduleName) {
        String sql = StringUtils.EMPTY;
        try {

            sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");
            if (businessRoleId.length() != 0) {
                sql += StringConstantsUtil.UDM_BUSINESS_ROLE_MASTER_SID
                        + businessRoleId + ")";
            }

            if (moduleName.length() != 0) {
                sql += StringConstantsUtil.AND_SPM_MODULE_NAME + moduleName + "') ";
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return Collections.emptyList();
        }
    }

    public List executeQuery(final Map<String, Object> parameters) {
        StringBuilder queryString = new StringBuilder();
        if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.HAS_TRADING_PARTNER.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.HAS_TRADING_PARTNER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID)));
            queryString.append('\'');
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_UNSAVED_PROJECTION_IDS.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_UNSAVED_PROJECTION_IDS));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {

            if ("PROJECTION_PROD_HIERARCHY".equals(parameters.get(StringConstantsUtil.TABLE_NAME))) {
                ArrayList<String> rlSids = (ArrayList<String>) parameters.get(StringConstantsUtil.RL_SIDS);
                StringBuilder hierarchyInclusion = new StringBuilder();
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    hierarchyInclusion.append(StringConstantsUtil.HIERARCHY_NO_LIKE);
                    hierarchyInclusion.append(rlSids.get(loop));
                    hierarchyInclusion.append("%'");
                    if (loop != (limit - 1)) {
                        hierarchyInclusion.append(" OR ");
                    }
                }
                String hierarchyExclusion = CommonUtils.stringListToString(rlSids);
                String query = SQlUtil.getQuery("get-lower-levels-based-on-hierarchy-no");
                query = query.replace("[?BU_COMPANY_MASTER_SID]", StringUtils.EMPTY + parameters.get("businessUnit"));
                query = query.replace("[?PROJECTION_MASTER_SID]", StringUtils.EMPTY + parameters.get(StringConstantsUtil.PROJECTION_ID));
                query = query.replace("[?HIERARCHY_INCLUDE]", hierarchyInclusion);
                query = query.replace("[?HIERARCHY_EXCLUDE]", hierarchyExclusion);

                if (parameters.get(StringConstantsUtil.MODULE) != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.MODULE)))) {
                    query = query.replace("[?HIERARCHY_TABLE]", "CFF_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "CFF_MASTER_SID");
                } else {
                    query = query.replace("[?HIERARCHY_TABLE]", "PROJECTION_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "PROJECTION_MASTER_SID");
                }
                queryString.append(query);
            } else if (parameters.get(StringConstantsUtil.RL_SIDS) != null) {
                ArrayList<String> rlSids = (ArrayList<String>) parameters.get(StringConstantsUtil.RL_SIDS);
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(SQlUtil.getQuery("getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append(StringConstantsUtil.HIERARCHY_NO_LIKE);
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(')');
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.TABLE_NAME)));

                    if (parameters.get(StringConstantsUtil.MODULE) != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.MODULE)))) {
                        queryString.append(" PH WHERE PH.CFF_MASTER_SID = ");
                    } else {
                        queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    }
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID)));
                    queryString.append(')');
                }
            }
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_CHILD_LEVEL_RL.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            ArrayList<String> rlSids = (ArrayList<String>) parameters.get(StringConstantsUtil.RL_SIDS);
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_CHILD_LEVEL_RL));
                queryString.append(" and (");
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    queryString.append("HIERARCHY_NO like '");
                    queryString.append(rlSids.get(loop));
                    queryString.append("%'");
                    if (loop != (limit - 1)) {
                        queryString.append(" or ");
                    }
                }
                queryString.append(") and HIERARCHY_NO not in (");
                queryString.append(CommonUtils.stringListToString(rlSids));
                if (parameters.get(StringConstantsUtil.AVAILABLE_HIER_NO) != null) {
                    ArrayList<String> availableHierNo = (ArrayList<String>) parameters.get(StringConstantsUtil.AVAILABLE_HIER_NO);
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_REMOVABLE_CHILDREN.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_REMOVABLE_CHILDREN));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.REMOVE_LEVELS)));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.REMOVE_LEVELS)));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : getTempTableList()) {
                queryString.append(SQlUtil.getQuery(StringConstantsUtil.DELETE_TEMP_ON_UPDATE));
                queryString.replace(queryString.indexOf(StringConstantsUtil.DTBL), queryString.indexOf(StringConstantsUtil.DTBL) + 5, table);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf(StringConstantsUtil.HTBL), queryString.indexOf(StringConstantsUtil.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf(StringConstantsUtil.HTBL), queryString.indexOf(StringConstantsUtil.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + 4, hNos);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
            }
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_FS_VALUE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_FS_VALUE));
            queryString.replace(queryString.indexOf(StringConstantsUtil.RLC), queryString.indexOf(StringConstantsUtil.RLC) + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.COMPANY_FILTER.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.COMPANY_FILTER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get("companySid")));
            queryString.append('\'');
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            if (parameters.get(StringConstantsUtil.INDICATOR) != null
                    && (StringConstantsUtil.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR))))) {
                List<Integer> list = new ArrayList<>();
                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
                list.add(returnValue);
                return list;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            }

        } catch (Exception ex) {
            LOGGER.error("In executeQuery  -> {}", ex.getMessage());
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }

    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct) {
        StringBuilder queryBuilder = new StringBuilder();
        try {
            queryBuilder.append("SELECT distinct c.hierarchy_Definition_Sid,c.hierarchy_Name,a.level_Name, a.level_No , ");
            queryBuilder.append("b.level_No , c.created_Date, c.modified_Date, c.version_No ");
            queryBuilder.append("from Hierarchy_Level_Definition a , Hierarchy_Level_Definition b ,Hierarchy_Definition c ");
            queryBuilder.append("where a.hierarchy_Definition_Sid = b.hierarchy_Definition_Sid and a.hierarchy_Definition_Sid = c.hierarchy_Definition_Sid ");
            queryBuilder.append("AND c.hierarchy_Category in (select ht.helper_Table_Sid from Helper_Table ht where ht.list_Name = 'HIERARCHY_CATEGORY' and ht.description like '");

            if (IndicatorConstants.INDICATOR_CUSTOMER_HIERARCHY.getConstant().equals(customerOrProduct)) {
                queryBuilder.append("Customer Hierarchy");
            } else {
                queryBuilder.append("Product Hierarchy");
            }
            queryBuilder.append("') ");
            queryBuilder.append(" AND b.hierarchy_Definition_Sid = c.hierarchy_Definition_Sid ");
            queryBuilder.append(" AND a.level_No in (SELECT MAX(h.level_No) FROM Hierarchy_Level_Definition h WHERE h.hierarchy_Definition_Sid = a.hierarchy_Definition_Sid) ");
            queryBuilder.append(" AND b.level_No in  (SELECT MIN(h.level_No) FROM Hierarchy_Level_Definition h WHERE h.hierarchy_Definition_Sid = b.hierarchy_Definition_Sid) ");
            if (hierarchyName != null && !"null".equals(hierarchyName) && !StringUtils.isEmpty(hierarchyName) && !StringUtils.isBlank(hierarchyName)) {
                queryBuilder.append(" AND c.hierarchy_Name like '");
                queryBuilder.append(hierarchyName);
                queryBuilder.append("' ");
            }
            if (hierarchyType != null && !"null".equals(hierarchyType) && !StringUtils.isEmpty(hierarchyType) && !StringUtils.isBlank(hierarchyType)) {
                queryBuilder.append(" AND c.hierarchy_Type in (select ht.helper_Table_Sid from Helper_Table ht where ht.description like '");
                queryBuilder.append(hierarchyType);
                queryBuilder.append("') ");
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List getParentLevels(final int relationshipLevelSid, final Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder();

        try {
            if (parameters.get(StringConstantsUtil.INDICATOR) != null && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))
                    && "getParentLevelsWithHierarchyNo".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
                queryBuilder.append(SQlUtil.getQuery("getParentLevelsWithHierarchyNo"));
                queryBuilder.replace(queryBuilder.indexOf("?"), queryBuilder.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNos")));
            } else {
                queryBuilder.append(SQlUtil.getQuery("getParentLevels"));
                queryBuilder.append(" RBLD.relationshipLevelSid = '");
                queryBuilder.append(relationshipLevelSid);
                queryBuilder.append("')");
                if (parameters.get("parent") != null) {
                    queryBuilder.append(" AND RLD.relationshipLevelValues = '");
                    queryBuilder.append(String.valueOf(parameters.get("parent")));
                    queryBuilder.append("' ");
                }
                queryBuilder.append(" ORDER by RLD.levelNo desc");
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error("In getParentLevels -> {}", ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List getInnerLevel(Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if ((parameters.get(StringConstantsUtil.IS_NDC) != null && "true".equals(String.valueOf(parameters.get(StringConstantsUtil.IS_NDC))))
                    || (!CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(parameters.get("screenName"))
                    && parameters.get(StringConstantsUtil.DUP_LEVEL) != null && ("Ndc".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.DUP_LEVEL)))
                    || "Item".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.DUP_LEVEL))) || StringConstantsUtil.PROD.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.DUP_LEVEL)))))) {

                queryBuilder.append(SQlUtil.getQuery("get-inner-level-products"));
                if (StringConstantsUtil.PROD.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))) {
                    queryBuilder.append(" AND ( RLD.LEVEL_NAME='PRODUCT') ");
                } else {
                    queryBuilder.append(" AND ( RLD.LEVEL_NAME='NDC') ");
                }
                queryBuilder.append(" AND CM.COMPANY_MASTER_SID = ");
                queryBuilder.append(String.valueOf(parameters.get("glCompId")));
                queryBuilder.append(" AND GLC.COMPANY_CODE = CM.COMPANY_ID AND IM.NDC8 = GLC.NDC8 ");
                queryBuilder.append(" AND IM.ORGANIZATION_KEY = ");
                queryBuilder.append(String.valueOf(parameters.get("businessUnit"))).append(' ');
            } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_NDC)))
                    && "company".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))) {
                queryBuilder.append(SQlUtil.getQuery("get-inner-level-companies"));
            } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_NDC)))
                    && ("therapeutic class".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))
                    || "brand".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                queryBuilder.append(SQlUtil.getQuery("get-inner-level-brand-therapeutic-class"));
            } else if (parameters.get(StringConstantsUtil.DUP_LEVEL) != null && Constants.INDICATOR_LEVEL_CUSTOMER.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.DUP_LEVEL)))) {
                queryBuilder.append(SQlUtil.getQuery("getInnerLevelCustomer"));
            } else if (parameters.get(StringConstantsUtil.DUP_LEVEL) != null && Constants.INDICATOR_LEVEL_CONTRACT.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.DUP_LEVEL)))) {
                queryBuilder.append(SQlUtil.getQuery("getInnerLevelContract"));
            } else {
                queryBuilder.append(SQlUtil.getQuery("getInnerLevel"));
            }
            if (parameters.get(StringConstantsUtil.HIERARCHY_DEFINITION_SID) != null) {
                queryBuilder.append(" and RB.hierarchy_Definition_Sid = ");
                queryBuilder.append(String.valueOf(parameters.get(StringConstantsUtil.HIERARCHY_DEFINITION_SID)));
            }
            if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_NDC)))) {
                queryBuilder.append(" and RLD.level_Name = '");
                queryBuilder.append(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)));
                queryBuilder.append('\'');
            }
            if (parameters.get(StringConstantsUtil.RELATIONSHIP_SID) != null && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.RELATIONSHIP_SID)))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.RELATIONSHIP_SID))) && !"0".equals(String.valueOf(parameters.get(StringConstantsUtil.RELATIONSHIP_SID)))) {
                queryBuilder.append(" and RLD.RELATIONSHIP_BUILDER_SID = '");
                queryBuilder.append(String.valueOf(parameters.get(StringConstantsUtil.RELATIONSHIP_SID)));
                queryBuilder.append('\'');
            }
            if ("AccrualRateProjection".equalsIgnoreCase(String.valueOf(parameters.get("screenName"))) && (!"Segment Group".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) && !"Segment".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))) && !"Segments".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) && !"Company".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) && !"GL Company".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) && !"Business Unit".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))) {
                queryBuilder.append("and RLD.relationship_Level_Values IN (");
                if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && ("Market Type".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) || "MarketType".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  CM.CONTRACT_TYPE from CONTRACT_MASTER CM \n"
                            ).append( StringConstantsUtil.JOIN_RS_CNT_RS_C_TYPE
                            ).append( StringConstantsUtil.RS_C_TYPE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.SELECT_HELPER_TABLE_SID 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "'  )");
                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && "Contract".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))) {
                    queryBuilder.append("Select Distinct  RS_C_TYPE.CONTRACT_MASTER_SID from CONTRACT_MASTER CM \n"
                            ).append( StringConstantsUtil.JOIN_RS_CNT_RS_C_TYPE
                            ).append( StringConstantsUtil.RS_C_TYPE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.SELECT_HELPER_TABLE_SID 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "'    )");

                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && "Contract Holder".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME)))) {
                    queryBuilder.append(" Select Distinct  CM.CONT_HOLD_COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            ).append( StringConstantsUtil.JOIN_RS_CNT_RS_C_TYPE
                            ).append( StringConstantsUtil.RS_C_TYPE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.SELECT_HELPER_TABLE_SID 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "'    )");

                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && ("Trading Partner".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) || "Customer".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            ).append( "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            ).append( "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            ).append( StringConstantsUtil.AND_NEW_LINE
                            ).append( "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            ).append( StringConstantsUtil.WHERE_SPACE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER
                            ).append( "-- LIST_NAME like'" 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "'\n"
                            ).append( "DESCRIPTION like'" 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "' \n"
                            ).append( "))");
                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && ("Company".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            ).append( "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            ).append( "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            ).append( StringConstantsUtil.AND_NEW_LINE
                            ).append( "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            ).append( StringConstantsUtil.WHERE_SPACE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER
                            ).append( "-- LIST_NAME like'" 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "'\n"
                            ).append( "DESCRIPTION like'" 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "' \n"
                            ).append( "))  JOIN COMPANY_MASTER COM_MAS ON COM_MAS.COMPANY_MASTER_SID=CFP_CD_SID.COMPANY_MASTER_SID\n"
                            ).append( " AND COM_MAS.COMPANY_TYPE IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            ).append( " DESCRIPTION like'GLCOMP' AND LIST_NAME='COMPANY_TYPE')");
                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && ("Item".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) || StringConstantsUtil.PROD.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))) || "Ndc".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  IFP_CD_SID.ITEM_MASTER_SID from CONTRACT_MASTER CM \n"
                            ).append( StringConstantsUtil.JOIN_IFP_CONTRACT_IFP_SID_ON_CMCONTRACT
                            ).append( StringConstantsUtil.JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON
                            ).append( StringConstantsUtil.AND_NEW_LINE
                            ).append( StringConstantsUtil.IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT
                            ).append( StringConstantsUtil.WHERE_SPACE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA
                            ).append( StringConstantsUtil.DESCRIPTION_LIKE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "' )) \n");
                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && ("Brand".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  IM.BRAND_MASTER_SID from CONTRACT_MASTER CM\n"
                            ).append( StringConstantsUtil.JOIN_IFP_CONTRACT_IFP_SID_ON_CMCONTRACT
                            ).append( StringConstantsUtil.JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON
                            ).append( "Join  ITEM_MASTER  IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n"
                            ).append( StringConstantsUtil.IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT
                            ).append( StringConstantsUtil.WHERE_SPACE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA
                            ).append( StringConstantsUtil.DESCRIPTION_LIKE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "' ))");
                } else if (parameters.get(StringConstantsUtil.LEVEL_NAME) != null && ("Therapeutic Class".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.LEVEL_NAME))))) {
                    queryBuilder.append("Select Distinct  IM.THERAPEUTIC_CLASS from CONTRACT_MASTER CM\n"
                            ).append( StringConstantsUtil.JOIN_IFP_CONTRACT_IFP_SID_ON_CMCONTRACT
                            ).append( StringConstantsUtil.JOIN_IFP_CONTRACT_DETAILS_IFP_CD_SID_ON
                            ).append( "Join  ITEM_MASTER  IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n"
                            ).append( StringConstantsUtil.IFP_SID_CONTRACT_MASTER_SID_IN_SELECT_CONT
                            ).append( StringConstantsUtil.WHERE_SPACE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) 
                            ).append( StringConstantsUtil.IN_SELECT_HELPER_TABLE_SID_FROM_HELPER_TA
                            ).append( StringConstantsUtil.DESCRIPTION_LIKE 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_VALUE)) 
                            ).append( StringConstantsUtil.AND_LIST_NAME 
                            ).append( String.valueOf(parameters.get(StringConstantsUtil.DEDUCTION_LEVEL)) ).append( "' ))");
                }
                queryBuilder.append(')');
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error(" in getInnerLevel()= {}", ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return Collections.emptyList();
        }
    }

    public List getCcpMap(final Map<String, Object> parameters) {
        StringBuilder customSql = new StringBuilder();
        try {
            LOGGER.debug("Entering getCcpMap method ");
            if (parameters.get(StringConstantsUtil.INDICATOR) != null && "getRbId".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
                customSql.append(SQlUtil.getQuery("getRbIdFromHier"));
                if (parameters.get(StringConstantsUtil.HIERARCHY_DEFINITION_SID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.HIERARCHY_DEFINITION_SID)));
                }
            } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
                customSql.append(SQlUtil.getQuery("saveCcpMerge"));
                if (parameters.get(StringConstantsUtil.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID)));
                }
                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }
                if (parameters.get(StringConstantsUtil.PROJECTION_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID)));
                }
            }
            if (parameters.get(StringConstantsUtil.INDICATOR) != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
                HelperTableLocalServiceUtil.executeUpdateQuery(customSql.toString());
                return Collections.emptyList();
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(customSql.toString());
            }
        } catch (Exception e) {
            LOGGER.error(" in getCcpMap= {}",e.getMessage());
            LOGGER.error(customSql.toString());
            return Collections.emptyList();
        }
    }

    public Object tempOperation(final Map<String, Object> input, final String queryName) {
        String customSql = SQlUtil.getQuery(queryName);
        StringBuilder finalQuery = new StringBuilder();
        try {
            Object temp;
            if ("getHierarchyTableDetails".equals(queryName)) {
                for (Map.Entry<String, Object> key : input.entrySet()) {
                    customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
                }
                List tempList = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                Map<String, String> valueList = new HashMap<>();

                for (int i = tempList.size() - 1; i >= 0; i--) {
                    customSql = SQlUtil.getQuery("getRelationshipLevelValues");
                    Object[] tempListObject = (Object[]) tempList.get(i);
                    customSql = customSql.replace("?FIELD", String.valueOf(tempListObject[0]));
                    customSql = customSql.replace("?TABLE", String.valueOf(tempListObject[1]));
                    customSql = customSql.replace("?IDCOL", String.valueOf(tempListObject[2]));
                    customSql = customSql.replace("?LNO", String.valueOf(tempListObject[3]));
                    customSql = customSql.replace("?RBSID", String.valueOf(input.get("?RBSID")));
                    if (i != 0) {
                        customSql = customSql.concat(" UNION ALL ");
                    }
                    finalQuery.append(customSql);
                }
                List tempValueList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
                for (int j = tempValueList.size() - 1; j >= 0; j--) {
                    Object[] tempObject = (Object[]) tempValueList.get(j);
                    valueList.put(String.valueOf(tempObject[0]), String.valueOf(tempObject[1]));
                }
                temp = valueList;
            } else {
                for (Map.Entry<String, Object> key : input.entrySet()) {
                    if (customSql.contains(key.getKey())) {
                        customSql = customSql.replace(key.getKey(), String.valueOf(key.getValue()));
                    }
                }
                if ("ds.singleBrand".equals(queryName)) {
                    temp = HelperTableLocalServiceUtil.executeSelectQuery(customSql);
                } else {
                    temp = HelperTableLocalServiceUtil.executeUpdateQueryCount(customSql);
                }
            }
            return temp;
        } catch (Exception e) {
            LOGGER.error("In tempOperation -> {}", e.getMessage());
            LOGGER.error(customSql);
            return null;
        }
    }

    public List<HelperTable> getHelperTableSId(String listName) {
        try {
            final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantsUtil.LIST_NAME,
                    listName));
            dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtil.DESCRIPTION));
            return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    public static List<String> getTempTableList() {
        List<String> tempTables = new ArrayList<>(NumericConstants.TWENTY_FIVE);
        tempTables.add("ST_NM_SALES_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_SALES");
        tempTables.add("ST_NM_SALES_PROJECTION_MASTER");
        tempTables.add("ST_NM_DISCOUNT_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_DISCOUNT");
        tempTables.add("ST_NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("ST_NM_PPA_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_PPA");
        tempTables.add("ST_NM_PPA_PROJECTION_MASTER");
        tempTables.add("ST_NM_ASSUMPTIONS");
        tempTables.add("NM_SALES_PROJECTION");
        tempTables.add("NM_ACTUAL_SALES");
        tempTables.add("NM_SALES_PROJECTION_MASTER");
        tempTables.add("NM_DISCOUNT_PROJECTION");
        tempTables.add("NM_ACTUAL_DISCOUNT");
        tempTables.add("NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("NM_PPA_PROJECTION");
        tempTables.add("NM_ACTUAL_PPA");
        tempTables.add("NM_PPA_PROJECTION_MASTER");
        tempTables.add("NM_ASSUMPTIONS");
        tempTables.add("PROJECTION_DETAILS");
        return tempTables;
    }

    public List executeQueryforchannel(final Map<String, Object> parameters) {
        StringBuilder queryString = new StringBuilder();
        if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.HAS_TRADING_PARTNER.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.HAS_TRADING_PARTNER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID)));
            queryString.append('\'');
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_UNSAVED_PROJECTION_IDS.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_UNSAVED_PROJECTION_IDS));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            if (parameters.get(StringConstantsUtil.RL_SIDS) != null) {
                ArrayList<String> rlSids = (ArrayList<String>) parameters.get(StringConstantsUtil.RL_SIDS);
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(SQlUtil.getQuery("getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append(StringConstantsUtil.HIERARCHY_NO_LIKE);
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(')');
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.TABLE_NAME)));
                    queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID)));
                    queryString.append(')');
                }
            }
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_CHILD_LEVEL_RL.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            ArrayList<String> rlSids = (ArrayList<String>) parameters.get(StringConstantsUtil.RL_SIDS);
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_CHILD_LEVEL_RL));
                queryString.append(" and (");
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    queryString.append("HIERARCHY_NO like '");
                    queryString.append(rlSids.get(loop));
                    queryString.append("%'");
                    if (loop != (limit - 1)) {
                        queryString.append(" or ");
                    }
                }
                queryString.append(") and HIERARCHY_NO not in (");
                queryString.append(CommonUtils.stringListToString(rlSids));
                if (parameters.get(StringConstantsUtil.AVAILABLE_HIER_NO) != null) {
                    ArrayList<String> availableHierNo = (ArrayList<String>) parameters.get(StringConstantsUtil.AVAILABLE_HIER_NO);
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_REMOVABLE_CHILDREN.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_REMOVABLE_CHILDREN));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.REMOVE_LEVELS)));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get(StringConstantsUtil.REMOVE_LEVELS)));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get(StringConstantsUtil.PROJECTION_ID));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : getTempTableList()) {
                queryString.append(SQlUtil.getQuery(StringConstantsUtil.DELETE_TEMP_ON_UPDATE));
                queryString.replace(queryString.indexOf(StringConstantsUtil.DTBL), queryString.indexOf(StringConstantsUtil.DTBL) + 5, table);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf(StringConstantsUtil.HTBL), queryString.indexOf(StringConstantsUtil.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf(StringConstantsUtil.HTBL), queryString.indexOf(StringConstantsUtil.HTBL) + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + 4, hNos);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
            }
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.GET_FS_VALUE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.GET_FS_VALUE));
            queryString.replace(queryString.indexOf(StringConstantsUtil.RLC), queryString.indexOf(StringConstantsUtil.RLC) + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null && StringConstantsUtil.COMPANY_FILTER.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {
            queryString.append(SQlUtil.getQuery(StringConstantsUtil.COMPANY_FILTER));
            queryString.append('\'');
            queryString.append(String.valueOf(parameters.get("companySid")));
            queryString.append('\'');
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null
                && ("getHierarchyGroup".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))
                || "getHierarchyGroupCount".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR))))) { //searchGroup
            String query = SQlUtil.getQuery("getHierarchyGroup");

            if (parameters.get(StringConstantsUtil.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))
                    && !StringConstantsUtil.COUNT.equals(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))) {
                query = query.replace(StringConstantsUtil.SELECTION, " C.HIERARCHY_DEFINITION_SID AS SID, C.HIERARCHY_NAME AS NAME, A.LEVEL_NAME AS LEVEL, "
                        + "A.LEVEL_NO AS HIGH_LEVEL, B.LEVEL_NO AS LOW_LEVEL, C.CREATED_DATE AS CREATED_DATE, C.MODIFIED_DATE AS MODIFIED_DATE, C.VERSION_NO AS VERSION ");
            } else {
                query = query.replace(StringConstantsUtil.SELECTION, " COUNT(C.HIERARCHY_DEFINITION_SID) AS SID_COUNT ");
            }
            if (!StringUtils.isBlank(String.valueOf(parameters.get("customerOrProduct"))) && "Customer Hierachy".equals(String.valueOf(parameters.get("customerOrProduct")))) {
                query = query.replace("?HIERARCHY_TYPE?", "Customer Hierarchy");
            } else {
                query = query.replace("?HIERARCHY_TYPE?", "Product Hierarchy");
            }
            if (parameters.get(StringConstantsUtil.HIERARCHY_NAME) != null && !"null".equals(String.valueOf(parameters.get(StringConstantsUtil.HIERARCHY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.HIERARCHY_NAME)))) {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", " AND C.HIERARCHY_NAME like '" + (parameters.get(StringConstantsUtil.HIERARCHY_NAME)) + "' ");
            } else {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", StringUtils.EMPTY);
            }
            if (parameters.get(StringConstantsUtil.HIERARCHY_TYPE) != null && !"null".equals(String.valueOf(parameters.get(StringConstantsUtil.HIERARCHY_TYPE))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.HIERARCHY_TYPE)))) {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", " AND C.HIERARCHY_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT where HT.DESCRIPTION LIKE '"
                        + (parameters.get(StringConstantsUtil.HIERARCHY_TYPE)) + "') ");
            } else {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", StringUtils.EMPTY);
            }
            try {
                if (parameters.get(StringConstantsUtil.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))
                        && !StringConstantsUtil.COUNT.equals(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))) {
                    if (String.valueOf(parameters.get(StringConstantsUtil.IS_FILTERED)).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder();
                        if ((parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append(StringConstantsUtil.UNQUOTE_AND_QUOTE);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO)))) {
                            filterAppender.append(" AND C.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(from);
                            filterAppender.append(StringConstantsUtil.UNQUOTE_AND_QUOTE);
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_HIERARCHY_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_HIERARCHY_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_HIERARCHY_NAME)))) {
                            filterAppender.append(" AND C.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_HIERARCHY_NAME)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(StringConstantsUtil.FILTER_HIGHEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_HIGHEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_HIGHEST_LEVEL)))) {
                            filterAppender.append(" AND A.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_HIGHEST_LEVEL)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_LOWEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_LOWEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_LOWEST_LEVEL)))) {
                            filterAppender.append(" AND B.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_LOWEST_LEVEL)));
                            filterAppender.append("' ");
                        }
                        query = query.replace(StringConstantsUtil.Q_FILTER, filterAppender.toString());
                    } else {
                        query = query.replace(StringConstantsUtil.Q_FILTER, StringUtils.EMPTY);
                    }
                    if ((parameters.get(StringConstantsUtil.IS_ORDERED) == null || StringConstantsUtil.STRING_FALSE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_ORDERED))))) {
                        query = query.replace(StringConstantsUtil.ORDER_BY, " ORDER BY C.CREATED_DATE ");
                    } else if (parameters.get(StringConstantsUtil.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_ORDERED)))) {
                        StringBuilder orderByAppender = new StringBuilder();
                        if (parameters.get(StringConstantsUtil.ORDER_BY_HIERARCHY_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_HIERARCHY_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_HIERARCHY_NAME)))) {
                            orderByAppender.append(" ORDER BY C.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_HIERARCHY_NAME)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_HIGHEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_HIGHEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_HIGHEST_LEVEL)))) {
                            orderByAppender.append(" ORDER BY A.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_HIGHEST_LEVEL)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_LOWEST_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_LOWEST_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_LOWEST_LEVEL)))) {
                            orderByAppender.append(" ORDER BY B.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_LOWEST_LEVEL)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY C.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY C.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH)));
                        }
                        query = query.replace(StringConstantsUtil.ORDER_BY, orderByAppender.toString());
                    }
                    if (parameters.get(StringConstantsUtil.START) != null && parameters.get(StringConstantsUtil.OFFSET_WO_SPACE) != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get(StringConstantsUtil.START)));
                        int offset = Integer.parseInt(String.valueOf(parameters.get(StringConstantsUtil.OFFSET_WO_SPACE)));
                        query = query + StringConstantsUtil.OFFSET_W_SPACE + startIndex + StringConstantsUtil.ROWS_FETCH_NEXT + offset + StringConstantsUtil.ROWS_ONLY_W_SPACE;
                    }

                } else {
                    query = query.replace(StringConstantsUtil.ORDER_BY, StringUtils.EMPTY);
                    query = query.replace(StringConstantsUtil.Q_FILTER, StringUtils.EMPTY);
                }

            } catch (NumberFormatException | ParseException ex) {
                LOGGER.error("Exception= {} and Query= {}", ex.getMessage(), StringConstantsUtil.IN_EXECUTE_QUERY);
            }
            queryString.append(query);
            queryString.append(';');
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null
                && ("searchGroup".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))
                || "searchGroupCount".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR))))) {
            queryString.append(prepareSearchGroupQuery(parameters));
        } else if (parameters.get(StringConstantsUtil.INDICATOR) != null
                && ("searchView".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))
                || "searchViewCount".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR))))) {
            queryString.append(prepareSearchViewQuery(parameters));
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            if (parameters.get(StringConstantsUtil.INDICATOR) != null
                    && (StringConstantsUtil.DELETE_TEMP_ON_UPDATE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR))))) {
                List<Integer> list = new ArrayList<>();
                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
                list.add(returnValue);
                return list;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            }
        } catch (Exception ex) {
            LOGGER.error("Message from Exception= {} and IN_EXECUTE QUERY= {}", ex.getMessage(), StringConstantsUtil.IN_EXECUTE_QUERY);
            LOGGER.error(queryString.toString());
            return Collections.emptyList();
        }
    }
    

    private String prepareSearchViewQuery(final Map<String, Object> parameters) {
        String query = StringUtils.EMPTY;
        try {
            StringBuilder queryString = new StringBuilder(SQlUtil.getQuery("findViewByNameJoin"));
            if (parameters.get(StringConstantsUtil.VIEW_TYPE) != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.VIEW_TYPE)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.VIEW_TYPE)))) {
                queryString.append(" FVM.VIEW_TYPE LIKE '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.VIEW_TYPE)));
                queryString.append("' ");

                if (String.valueOf(parameters.get(StringConstantsUtil.VIEW_TYPE)).equalsIgnoreCase("private")
                        && parameters.get(StringConstantsUtil.USER_ID) != null
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.USER_ID)))) {
                    queryString.append(" AND FVM.CREATED_BY = ");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.USER_ID)));
                }

            }
            if (parameters.get(StringConstantsUtil.VIEW_NAME) != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.VIEW_NAME)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.VIEW_NAME)))) {
                queryString.append(" AND FVM.VIEW_NAME LIKE '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.VIEW_NAME)));
                queryString.append("' ");
            }

            if (parameters.get(StringConstantsUtil.FORECAST_TYPE) != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.FORECAST_TYPE)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FORECAST_TYPE)))) {
                queryString.append(" AND PM.FORECASTING_TYPE ='");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FORECAST_TYPE)));
                queryString.append("' ");
            }

            queryString.append(StringConstantsUtil.Q_FILTER);
            queryString.append(StringConstantsUtil.ORDER_BY);
            query = queryString.toString();

                if (parameters.get(StringConstantsUtil.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))
                        && !StringConstantsUtil.COUNT.equals(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))) {
                    query = query.replace(StringConstantsUtil.SELECTION, SQlUtil.getQuery("searchViewFindSelection"));
                    if (String.valueOf(parameters.get(StringConstantsUtil.IS_FILTERED)).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder();
                        if ((parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append(StringConstantsUtil.UNQUOTE_AND_QUOTE);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }

                        if (parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO)))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(from);
                            filterAppender.append(StringConstantsUtil.UNQUOTE_AND_QUOTE);
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String to = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))))
                                && (parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO) == null || Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO)))
                                || StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_TO))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat(StringConstantsUtil.EEE_MMM_DD_H_HMMSS_Z_YYYY);
                            SimpleDateFormat format = new SimpleDateFormat(StringConstantsUtil.M_MDDYYYY);
                            String from = format.format(parse.parse(String.valueOf(parameters.get(StringConstantsUtil.FILTER_MODIFIED_DATE_SEARCH_FROM))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_VIEW_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_VIEW_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_VIEW_NAME)))) {
                            filterAppender.append(" AND FVM.VIEW_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_VIEW_NAME)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_VIEW_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_DESCRIPTION)))) {
                            filterAppender.append(" AND PM.PROJECTION_DESCRIPTION like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_DESCRIPTION)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(StringConstantsUtil.FILTER_CUSTOMER_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_HIERARCHY)))) {
                            filterAppender.append(" AND CUST_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_HIERARCHY)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(StringConstantsUtil.FILTER_CUSTOMER_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_LEVEL)))) {
                            filterAppender.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_LEVEL)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP)))) {
                            filterAppender.append(" AND CMG.COMPANY_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP)));
                            filterAppender.append("' ");
                        }

                        if (parameters.get(StringConstantsUtil.FILTER_COMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_COMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_COMPANY)))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_COMPANY)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_BRAND_TYPE) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_BRAND_TYPE)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_BRAND_TYPE)))) {
                            filterAppender.append(" AND PM.BRAND_TYPE like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_BRAND_TYPE)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_PRODUCT_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_HIERARCHY)))) {
                            filterAppender.append(" AND PROD_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_HIERARCHY)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_PRODUCT_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_LEVEL)))) {
                            filterAppender.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_LEVEL)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP)))) {
                            filterAppender.append(" AND IG.ITEM_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_CREATED_BY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_BY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_BY)))) {
                            filterAppender.append(" AND PM.CREATED_BY in (");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CREATED_BY)));
                            filterAppender.append(") ");
                        }

                        query = query.replace(StringConstantsUtil.Q_FILTER, filterAppender.toString());
                    } else {
                        query = query.replace(StringConstantsUtil.Q_FILTER, StringUtils.EMPTY);
                    }
                    if ((parameters.get(StringConstantsUtil.IS_ORDERED) == null || StringConstantsUtil.STRING_FALSE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_ORDERED))))) {
                        query = query.replace(StringConstantsUtil.ORDER_BY, " ORDER BY PM.CREATED_DATE ");
                    } else if (parameters.get(StringConstantsUtil.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_ORDERED)))) {
                        StringBuilder orderByAppender = new StringBuilder();
                        if (parameters.get(StringConstantsUtil.ORDER_BY_VIEW_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_VIEW_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_VIEW_NAME)))) {
                            orderByAppender.append(" ORDER BY FVM.VIEW_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_VIEW_NAME)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_DESCRIPTION)))) {
                            orderByAppender.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_DESCRIPTION)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_FROM) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_FROM)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_FROM)))) {
                            orderByAppender.append(" ORDER BY PM.FROM_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_FROM)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_TO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_TO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_TO)))) {
                            orderByAppender.append(" ORDER BY PM.TO_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_TO)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_HIERARCHY)))) {
                            orderByAppender.append(" ORDER BY CUST_HD.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_HIERARCHY)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_LEVEL)))) {
                            orderByAppender.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_LEVEL)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP)))) {
                            orderByAppender.append(" ORDER BY CMG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_COMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_COMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_COMPANY)))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_COMPANY)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_BRAND_TYPE) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_BRAND_TYPE)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_BRAND_TYPE)))) {
                            orderByAppender.append(" ORDER BY PM.BRAND_TYPE  ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_BRAND_TYPE)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_HIERARCHY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_HIERARCHY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_HIERARCHY)))) {
                            orderByAppender.append(" ORDER BY PROD_HD.HIERARCHY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_HIERARCHY)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_LEVEL) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_LEVEL)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_LEVEL)))) {
                            orderByAppender.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL  ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_LEVEL)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_DATE_SEARCH)));
                        }

                        if (parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH)))) {
                            orderByAppender.append(" ORDER BY PM.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BYMODIFIED_DATE_SEARCH)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_CREATED_BY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_BY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_BY)))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_BY ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CREATED_BY)));
                        }
                        query = query.replace(StringConstantsUtil.ORDER_BY, orderByAppender.toString());
                    }
                    if (parameters.get(StringConstantsUtil.START) != null && parameters.get(StringConstantsUtil.OFFSET_WO_SPACE) != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get(StringConstantsUtil.START)));
                        int offset = Integer.parseInt(String.valueOf(parameters.get(StringConstantsUtil.OFFSET_WO_SPACE)));
                        query = query + StringConstantsUtil.OFFSET_W_SPACE + startIndex + StringConstantsUtil.ROWS_FETCH_NEXT + offset + StringConstantsUtil.ROWS_ONLY_W_SPACE;
                    }
                } else {
                    query = query.replace(StringConstantsUtil.SELECTION, " COUNT (DISTINCT FVM.VIEW_ID) ");
                    query = query.replace(StringConstantsUtil.ORDER_BY, StringUtils.EMPTY);
                    query = query.replace(StringConstantsUtil.Q_FILTER, StringUtils.EMPTY);
                }
        } catch (NumberFormatException | ParseException ex) {
                LOGGER.error("Exception message from method= {} and StringConstandsUtil:Query= {}", ex.getMessage(), StringConstantsUtil.IN_EXECUTE_QUERY);
                LOGGER.error(query);
            }
        return query;
    }

    private String prepareSearchGroupQuery(final Map<String, Object> parameters) {
        LOGGER.debug("Entering prepareSearchGroupQuery method");
        String query = StringUtils.EMPTY;
        try {
            String name = null;
            String no = null;
            String desc = null;
            String sids = null;
            StringBuilder queryString = new StringBuilder();
            if (parameters.get(StringConstantsUtil.GROUP_IDENTIFIER) != null && Constants.CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.GROUP_IDENTIFIER)))) {
                queryString.append(SQlUtil.getQuery("getCustomerGroupPaged"));
                name = " CG.COMPANY_GROUP_NAME ";
                no = " CG.COMPANY_GROUP_NO ";
                desc = " CG.COMPANY_GROUP_DESCRIPTION ";
                sids = " CGD.COMPANY_MASTER_SID ";
            } else {
                queryString.append(SQlUtil.getQuery("getProductGroupPaged"));
                no = " IG.ITEM_GROUP_NO ";
                name = " IG.ITEM_GROUP_NAME ";
                desc = " IG.ITEM_GROUP_DESCRIPTION ";
                sids = " IGD.ITEM_MASTER_SID ";
            }
            if (parameters.get("no") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("no")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("no")))) {
                queryString.append(StringConstantsUtil.AND);
                queryString.append(no);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("no")));
                queryString.append('\'');
            }
            if (parameters.get("name") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("name")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("name")))) {
                queryString.append(StringConstantsUtil.AND);
                queryString.append(name);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("name")));
                queryString.append('\'');
            }
            if (parameters.get("sids") != null && !"null".equals(String.valueOf(parameters.get("sids")))) {
                queryString.append(StringConstantsUtil.AND);
                queryString.append(sids);
                queryString.append(" in (");
                queryString.append(String.valueOf(parameters.get("sids")));
                queryString.append(')');
            }

            queryString.append(StringConstantsUtil.Q_FILTER);
            queryString.append(StringConstantsUtil.ORDER_BY);

            query = queryString.toString();
                if (parameters.get(StringConstantsUtil.ACTION) != null && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))
                        && !StringConstantsUtil.COUNT.equals(String.valueOf(parameters.get(StringConstantsUtil.ACTION)))) {
                    if (parameters.get(StringConstantsUtil.GROUP_IDENTIFIER) != null && Constants.CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.GROUP_IDENTIFIER)))) {
                        query = query.replace(StringConstantsUtil.SELECTION, " DISTINCT CG.COMPANY_GROUP_SID, CG.COMPANY_GROUP_NO, CG.COMPANY_GROUP_NAME, CG.VERSION_NO, CG.COMPANY_GROUP_DESCRIPTION ");
                    } else {
                        query = query.replace(StringConstantsUtil.SELECTION, " DISTINCT IG.ITEM_GROUP_SID, IG.ITEM_GROUP_NO, IG.ITEM_GROUP_NAME, CM.COMPANY_NAME, IG.VERSION_NO, IG.ITEM_GROUP_DESCRIPTION ");
                    }
                    if (String.valueOf(parameters.get(StringConstantsUtil.IS_FILTERED)).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder();
                        if (parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NAME)))) {
                            filterAppender.append(StringConstantsUtil.AND);
                            filterAppender.append(name);
                            filterAppender.append(StringConstantsUtil.LIKE_QUOTE);
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NAME)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NAME)))) {
                            filterAppender.append(StringConstantsUtil.AND);
                            filterAppender.append(name);
                            filterAppender.append(StringConstantsUtil.LIKE_QUOTE);
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NAME)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NO)))) {
                            filterAppender.append(StringConstantsUtil.AND);
                            filterAppender.append(no);
                            filterAppender.append(StringConstantsUtil.LIKE_QUOTE);
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_NO)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NO)))) {
                            filterAppender.append(StringConstantsUtil.AND);
                            filterAppender.append(no);
                            filterAppender.append(StringConstantsUtil.LIKE_QUOTE);
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_NO)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_DESCRIPTION)))) {
                            filterAppender.append(StringConstantsUtil.AND);
                            filterAppender.append(desc);
                            filterAppender.append(StringConstantsUtil.LIKE_QUOTE);
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_CUSTOMER_GROUP_DESCRIPTION)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_DESCRIPTION)))) {
                            filterAppender.append(StringConstantsUtil.AND);
                            filterAppender.append(desc);
                            filterAppender.append(StringConstantsUtil.LIKE_QUOTE);
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_PRODUCT_GROUP_DESCRIPTION)));
                            filterAppender.append("' ");
                        }
                        if (parameters.get(StringConstantsUtil.FILTER_COMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTER_COMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTER_COMPANY)))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get(StringConstantsUtil.FILTER_COMPANY)));
                            filterAppender.append("' ");
                        }
                        query = query.replace(StringConstantsUtil.Q_FILTER, filterAppender.toString());
                    } else {
                        query = query.replace(StringConstantsUtil.Q_FILTER, StringUtils.EMPTY);
                    }
                    if ((parameters.get(StringConstantsUtil.IS_ORDERED) == null || StringConstantsUtil.STRING_FALSE.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_ORDERED))))) {
                        if (parameters.get(StringConstantsUtil.GROUP_IDENTIFIER) != null && Constants.CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.GROUP_IDENTIFIER)))) {
                            query = query.replace(StringConstantsUtil.ORDER_BY, " ORDER BY CG.COMPANY_GROUP_SID ");
                        } else {
                            query = query.replace(StringConstantsUtil.ORDER_BY, " ORDER BY IG.ITEM_GROUP_SID ");
                        }
                    } else if (parameters.get(StringConstantsUtil.IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.IS_ORDERED)))) {
                        StringBuilder orderByAppender = new StringBuilder();
                        if (parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NAME)))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NAME)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NO)))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_NO)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION)))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_CUSTOMER_GROUP_DESCRIPTION)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NAME) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NAME)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NAME)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NAME)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NO) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NO)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NO)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_NO)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_DESCRIPTION) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_DESCRIPTION)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_DESCRIPTION)))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_PRODUCT_GROUP_DESCRIPTION)));
                        }
                        if (parameters.get(StringConstantsUtil.ORDER_BY_COMPANY) != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_COMPANY)))
                                && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_COMPANY)))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get(StringConstantsUtil.ORDER_BY_COMPANY)));
                        }
                        query = query.replace(StringConstantsUtil.ORDER_BY, orderByAppender.toString());
                    }
                    if (parameters.get(StringConstantsUtil.START) != null && parameters.get(StringConstantsUtil.OFFSET_WO_SPACE) != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get(StringConstantsUtil.START)));
                        int offset = Integer.parseInt(String.valueOf(parameters.get(StringConstantsUtil.OFFSET_WO_SPACE)));
                        query = query + StringConstantsUtil.OFFSET_W_SPACE + startIndex + StringConstantsUtil.ROWS_FETCH_NEXT + offset + StringConstantsUtil.ROWS_ONLY_W_SPACE;
                    }
                } else {
                    if (parameters.get(StringConstantsUtil.GROUP_IDENTIFIER) != null && Constants.CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.GROUP_IDENTIFIER)))) {
                        query = query.replace(StringConstantsUtil.SELECTION, " COUNT (DISTINCT CG.COMPANY_GROUP_SID) ");
                    } else {
                        query = query.replace(StringConstantsUtil.SELECTION, " COUNT (DISTINCT IG.ITEM_GROUP_SID) ");
                    }
                    query = query.replace(StringConstantsUtil.ORDER_BY, StringUtils.EMPTY);
                    query = query.replace(StringConstantsUtil.Q_FILTER, StringUtils.EMPTY);
                }
           
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
        return query;

    }
    
}
