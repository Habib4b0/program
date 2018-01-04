/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.Constants.IndicatorConstants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

public class CommonServiceImpl {

    private static final Logger LOGGER = Logger.getLogger(CommonServiceImpl.class);// Logger
    private static CommonServiceImpl instance = null;

    private CommonServiceImpl() {
        //default
    }

    public static CommonServiceImpl getInstance() {
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

    public List getDetailsResults(String fileName, String version, String fileType, String country, int year) {
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
                sqlString = sqlString.concat(" AND ").concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
            } else {
                sqlString = sqlString.concat(" AND ").concat(" FM.FORECAST_VER='").concat(version).concat("'");
            }
            sqlString = sqlString.concat(" ORDER BY FM.FORECAST_YEAR");
            return HelperTableLocalServiceUtil.executeSelectQuery(sqlString);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(sqlString);
            return null;
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
                sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                        + businessRoleId + ")";
            }
            if (mod.length() != 0) {
                sql += " AND spm.MODULE_NAME in ('" + mod + "') ";
            }

            if (str != null && !str[1].equals(StringUtils.EMPTY) && str[1].length() != 0) {
                sql += " AND spm.TAB_NAME in ('" + str[1] + "') ";
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
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
                sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                        + businessRoleId + ")";
            }
            if (mod.length() != 0) {
                sql += " AND spm.MODULE_NAME in ('" + mod + "') ";
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
            return null;
        }
    }

    public List getBusinessTabPermission(String businessRoleId, String moduleName) {
        String sql = StringUtils.EMPTY;
        try {

            sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");
            if (businessRoleId.length() != 0) {
                sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                        + businessRoleId + ")";
            }

            if (moduleName.length() != 0) {
                sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        }
    }

    public List executeQuery(final Map<String, Object> parameters) {
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        if (parameters.get("indicator") != null && "hasTradingPartner".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("hasTradingPartner"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("projectionId")));
            queryString.append("'");
        } else if (parameters.get("indicator") != null && "getUnsavedProjectionIds".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("getUnsavedProjectionIds"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get("indicator") != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {

            if ("PROJECTION_PROD_HIERARCHY".equals(parameters.get("tableName"))) {
                List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
                StringBuilder hierarchyInclusion = new StringBuilder();
                for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                    hierarchyInclusion.append("HIERARCHY_NO LIKE '");
                    hierarchyInclusion.append(rlSids.get(loop));
                    hierarchyInclusion.append("%'");
                    if (loop != (limit - 1)) {
                        hierarchyInclusion.append(" OR ");
                    }
                }
                String hierarchyExclusion = CommonUtils.stringListToString(rlSids);
                String query = SQlUtil.getQuery("get-lower-levels-based-on-hierarchy-no");
                query = query.replace("[?BU_COMPANY_MASTER_SID]", StringUtils.EMPTY + parameters.get("businessUnit"));
                query = query.replace("[?PROJECTION_MASTER_SID]", StringUtils.EMPTY + parameters.get("projectionId"));
                query = query.replace("[?HIERARCHY_INCLUDE]", hierarchyInclusion);
                query = query.replace("[?HIERARCHY_EXCLUDE]", hierarchyExclusion);

                if (parameters.get("module") != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get("module")))) {
                    query = query.replace("[?HIERARCHY_TABLE]", "CFF_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "CFF_MASTER_SID");
                } else {
                    query = query.replace("[?HIERARCHY_TABLE]", "PROJECTION_PROD_HIERARCHY");
                    query = query.replace("[?MASTER_TABLE_SID_COLUMN]", "PROJECTION_MASTER_SID");
                }
                queryString.append(query);
            } else if (parameters.get("rlSids") != null) {
                List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(SQlUtil.getQuery("getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append("HIERARCHY_NO LIKE '");
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(")");
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get("tableName")));

                    if (parameters.get("module") != null && "cff".equalsIgnoreCase(String.valueOf(parameters.get("module")))) {
                        queryString.append(" PH WHERE PH.CFF_MASTER_SID = ");
                    } else {
                        queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    }
                    queryString.append(String.valueOf(parameters.get("projectionId")));
                    queryString.append(")");
                }
            }
        } else if (parameters.get("indicator") != null && "getChildLevelRL".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(SQlUtil.getQuery("getChildLevelRL"));
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
                if (parameters.get("availableHierNo") != null) {
                    List<String> availableHierNo = (ArrayList<String>) parameters.get("availableHierNo");
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get("indicator") != null && "getRemovableChildren".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("getRemovableChildren"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
        } else if (parameters.get("indicator") != null && "deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get("projectionId"));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : getTempTableList()) {
                queryString.append(SQlUtil.getQuery("deleteTempOnUpdate"));
                queryString.replace(queryString.indexOf("?DTBL"), queryString.indexOf("?DTBL") + 5, table);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + 4, hNos);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
            }
        } else if (parameters.get("indicator") != null && "getFSValue".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("getFSValue"));
            queryString.replace(queryString.indexOf("?RLC?"), queryString.indexOf("?RLC?") + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get("indicator") != null && "companyFilter".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("companyFilter"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("companySid")));
            queryString.append("'");
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            if (parameters.get("indicator") != null
                    && ("deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
                List<Integer> list = new ArrayList<Integer>();
                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
                list.add(returnValue);
                return list;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            }

        } catch (Exception ex) {
            LOGGER.error("In executeQuery  ->" + ex.getMessage());
            LOGGER.error(queryString.toString());
            return null;
        }
    }

    public List getHierarchyGroup(final String hierarchyName, final String hierarchyType, final String customerOrProduct, final String action) {
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
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
            return null;
        }
    }

    public List getParentLevels(final int levelNo, final int relationshipLevelSid, final Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        try {
            if (parameters.get("indicator") != null && !StringUtils.isBlank(String.valueOf(parameters.get("indicator")))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("indicator")))
                    && "getParentLevelsWithHierarchyNo".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
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
            LOGGER.error("In getParentLevels ->" + ex.getMessage());
            LOGGER.error(queryBuilder.toString());
            return null;
        }
    }

    public List getInnerLevel(Map<String, Object> parameters) {
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);
        try {
            if ((parameters.get("isNdc") != null && "true".equals(String.valueOf(parameters.get("isNdc"))))
                    || (!CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS.equals(parameters.get("screenName"))
                    && parameters.get("level") != null && ("Ndc".equalsIgnoreCase(String.valueOf(parameters.get("level")))
                    || "Item".equalsIgnoreCase(String.valueOf(parameters.get("level"))) || "Product".equalsIgnoreCase(String.valueOf(parameters.get("level")))))) {

                queryBuilder.append(SQlUtil.getQuery("get-inner-level-products"));
                if ("Product".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                    queryBuilder.append(" AND ( RLD.LEVEL_NAME='PRODUCT') ");
                } else {
                    queryBuilder.append(" AND ( RLD.LEVEL_NAME='NDC') ");
                }
                queryBuilder.append(" AND CM.COMPANY_MASTER_SID = ");
                queryBuilder.append(String.valueOf(parameters.get("glCompId")));
                queryBuilder.append(" AND GLC.COMPANY_CODE = CM.COMPANY_ID AND IM.NDC8 = GLC.NDC8 ");
                queryBuilder.append(" AND IM.ORGANIZATION_KEY = ");
                queryBuilder.append(String.valueOf(parameters.get("businessUnit"))).append(" ");
            } else if (parameters.get("levelName") != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get("isNdc")))
                    && "company".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                queryBuilder.append(SQlUtil.getQuery("get-inner-level-companies"));
            } else if (parameters.get("levelName") != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get("isNdc")))
                    && ("therapeutic class".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))
                    || "brand".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                queryBuilder.append(SQlUtil.getQuery("get-inner-level-brand-therapeutic-class"));
            } else if (parameters.get("level") != null && Constants.INDICATOR_LEVEL_CUSTOMER.equalsIgnoreCase(String.valueOf(parameters.get("level")))) {
                queryBuilder.append(SQlUtil.getQuery("getInnerLevelCustomer"));
            } else if (parameters.get("level") != null && Constants.INDICATOR_LEVEL_CONTRACT.equalsIgnoreCase(String.valueOf(parameters.get("level")))) {
                queryBuilder.append(SQlUtil.getQuery("getInnerLevelContract"));
            } else {
                queryBuilder.append(SQlUtil.getQuery("getInnerLevel"));
            }
            if (parameters.get("hierarchyDefinitionSid") != null) {
                queryBuilder.append(" and RB.hierarchy_Definition_Sid = ");
                queryBuilder.append(String.valueOf(parameters.get("hierarchyDefinitionSid")));
            }
            if (parameters.get("levelName") != null && !"true".equalsIgnoreCase(String.valueOf(parameters.get("isNdc")))) {
                queryBuilder.append(" and RLD.level_Name = '");
                queryBuilder.append(String.valueOf(parameters.get("levelName")));
                queryBuilder.append("'");
            }
            if (parameters.get("relationshipSid") != null && !StringUtils.isBlank(String.valueOf(parameters.get("relationshipSid")))
                    && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get("relationshipSid"))) && !"0".equals(String.valueOf(parameters.get("relationshipSid")))) {
                queryBuilder.append(" and RLD.RELATIONSHIP_BUILDER_SID = '");
                queryBuilder.append(String.valueOf(parameters.get("relationshipSid")));
                queryBuilder.append("'");
            }
            if ("AccrualRateProjection".equalsIgnoreCase(String.valueOf(parameters.get("screenName"))) && (!"Segment Group".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) && !"Segment".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) && !"Segments".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) && !"Company".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) && !"GL Company".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) && !"Business Unit".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                queryBuilder.append("and RLD.relationship_Level_Values IN (");
                if (parameters.get("levelName") != null && ("Market Type".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "MarketType".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("Select Distinct  CM.CONTRACT_TYPE from CONTRACT_MASTER CM \n"
                            + "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n"
                            + "RS_C_TYPE." + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "'  )");
                } else if (parameters.get("levelName") != null && "Contract".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                    queryBuilder.append("\n"
                            + "                    Select Distinct  RS_C_TYPE.CONTRACT_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n"
                            + "RS_C_TYPE." + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "'    )");

                } else if (parameters.get("levelName") != null && "Contract Holder".equalsIgnoreCase(String.valueOf(parameters.get("levelName")))) {
                    queryBuilder.append("\n"
                            + "                    Select Distinct  CM.CONT_HOLD_COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join RS_CONTRACT RS_C_TYPE ON RS_C_TYPE.CONTRACT_MASTER_SID=CM.CONTRACT_MASTER_SID AND \n"
                            + "RS_C_TYPE." + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "'    )");

                } else if (parameters.get("levelName") != null && ("Trading Partner".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "Customer".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("\n"
                            + "Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            + "AND\n"
                            + "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            + "-- LIST_NAME like'" + String.valueOf(parameters.get("deductionLevel")) + "'\n"
                            + "DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "' \n"
                            + "))");
                } else if (parameters.get("levelName") != null && ("Company".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("\n"
                            + "Select Distinct  CFP_CD_SID.COMPANY_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join CFP_CONTRACT CFP_SID ON CM.CONTRACT_MASTER_SID = CFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  CFP_CONTRACT_DETAILS CFP_CD_SID  ON  CFP_CD_SID.CFP_CONTRACT_SID=CFP_SID.CFP_CONTRACT_SID\n"
                            + "AND\n"
                            + "CFP_SID.CONTRACT_MASTER_SID IN (Select Distinct CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            + "-- LIST_NAME like'" + String.valueOf(parameters.get("deductionLevel")) + "'\n"
                            + "DESCRIPTION like'" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "' \n"
                            + "))" + "  JOIN COMPANY_MASTER COM_MAS ON COM_MAS.COMPANY_MASTER_SID=CFP_CD_SID.COMPANY_MASTER_SID\n"
                            + "                            AND COM_MAS.COMPANY_TYPE IN(Select HELPER_TABLE_SID from HELPER_TABLE where \n"
                            + "                            DESCRIPTION like'GLCOMP' AND LIST_NAME='COMPANY_TYPE')");
                } else if (parameters.get("levelName") != null && ("Item".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "Product".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))) || "Ndc".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("\n"
                            + "Select Distinct  IFP_CD_SID.ITEM_MASTER_SID from CONTRACT_MASTER CM \n"
                            + "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n"
                            + "AND\n"
                            + "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  "
                            + "DESCRIPTION like '" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "' )) \n");
                } else if (parameters.get("levelName") != null && ("Brand".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("Select Distinct  IM.BRAND_MASTER_SID from CONTRACT_MASTER CM\n"
                            + "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n"
                            + "Join  ITEM_MASTER  IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n"
                            + "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  "
                            + "DESCRIPTION like '" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "' ))");
                } else if (parameters.get("levelName") != null && ("Therapeutic Class".equalsIgnoreCase(String.valueOf(parameters.get("levelName"))))) {
                    queryBuilder.append("Select Distinct  IM.THERAPEUTIC_CLASS from CONTRACT_MASTER CM\n"
                            + "Join IFP_CONTRACT IFP_SID ON CM.CONTRACT_MASTER_SID = IFP_SID.CONTRACT_MASTER_SID\n"
                            + "Join  IFP_CONTRACT_DETAILS IFP_CD_SID  ON  IFP_CD_SID.IFP_CONTRACT_SID=IFP_SID.IFP_CONTRACT_SID\n"
                            + "Join  ITEM_MASTER  IM ON IM.ITEM_MASTER_SID=IFP_CD_SID.ITEM_MASTER_SID AND\n"
                            + "IFP_SID.CONTRACT_MASTER_SID IN (Select CONTRACT_MASTER_SID from RS_CONTRACT\n"
                            + "where " + String.valueOf(parameters.get("deductionLevel")) + " IN(Select HELPER_TABLE_SID from HELPER_TABLE where  "
                            + "DESCRIPTION like '" + String.valueOf(parameters.get("deductionValue")) + "' AND LIST_NAME='" + String.valueOf(parameters.get("deductionLevel")) + "' ))");
                }
                queryBuilder.append(")");
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in getInnerLevel()");
            LOGGER.error(queryBuilder.toString());
            return null;
        }
    }

    public List getCcpMap(final Map<String, Object> parameters) {
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering getCcpMap method ");
            if (parameters.get("indicator") != null && "getRbId".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                customSql.append(SQlUtil.getQuery("getRbIdFromHier"));
                if (parameters.get("hierarchyDefinitionSid") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyDefinitionSid")));
                }
            } else if (parameters.get("indicator") != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                customSql.append(SQlUtil.getQuery("saveCcpMerge"));
                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }
                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }
                if (parameters.get("projectionId") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("projectionId")));
                }
            }
            if (parameters.get("indicator") != null && "saveCcp".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
                HelperTableLocalServiceUtil.executeUpdateQuery(customSql.toString());
                return null;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(customSql.toString());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage() + " in getCcpMap");
            LOGGER.error(customSql.toString());
            return null;
        }
    }

    public Object tempOperation(final Map<String, Object> input, final String queryName) {
        String customSql = SQlUtil.getQuery(queryName);
        String finalQuery = StringUtils.EMPTY;
        try {
            Object temp;
            if ("getHierarchyTableDetails".equals(queryName)) {
                for (String key : input.keySet()) {
                    customSql = customSql.replace(key, String.valueOf(input.get(key)));
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
                    finalQuery += customSql;
                }
                List tempValueList = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
                for (int j = tempValueList.size() - 1; j >= 0; j--) {
                    Object[] tempObject = (Object[]) tempValueList.get(j);
                    valueList.put(String.valueOf(tempObject[0]), String.valueOf(tempObject[1]));
                }
                temp = valueList;
            } else {
                for (String key : input.keySet()) {
                    if (customSql.contains(key)) {
                        customSql = customSql.replace(key, String.valueOf(input.get(key)));
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
            LOGGER.error("In tempOperation ->" + e.getMessage());
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
            return null;
        }
    }

    public static List<String> getTempTableList() {
        List<String> tempTables = new ArrayList<String>();
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
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        if (parameters.get("indicator") != null && "hasTradingPartner".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("hasTradingPartner"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("projectionId")));
            queryString.append("'");
        } else if (parameters.get("indicator") != null && "getUnsavedProjectionIds".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("getUnsavedProjectionIds"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("deleteDate")));
        } else if (parameters.get("indicator") != null && "getChildLevelRLSid".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            if (parameters.get("rlSids") != null) {
                List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
                if (rlSids != null && !rlSids.isEmpty()) {
                    queryString.append(SQlUtil.getQuery("getChildLevelRLSidRestricted"));
                    queryString.append(" WHERE (");
                    for (int loop = 0, limit = rlSids.size(); loop < limit; loop++) {
                        queryString.append("HIERARCHY_NO LIKE '");
                        queryString.append(rlSids.get(loop));
                        queryString.append("%'");
                        if (loop != (limit - 1)) {
                            queryString.append(" OR ");
                        }
                    }
                    queryString.append(") AND HIERARCHY_NO NOT IN (");
                    queryString.append(CommonUtils.stringListToString(rlSids));
                    queryString.append(")");
                    queryString.append(" AND RLD.RELATIONSHIP_LEVEL_SID not in (SELECT PH.RELATIONSHIP_LEVEL_SID FROM ");
                    queryString.append(String.valueOf(parameters.get("tableName")));
                    queryString.append(" PH WHERE PH.PROJECTION_MASTER_SID = ");
                    queryString.append(String.valueOf(parameters.get("projectionId")));
                    queryString.append(")");
                }
            }
        } else if (parameters.get("indicator") != null && "getChildLevelRL".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            List<String> rlSids = (ArrayList<String>) parameters.get("rlSids");
            if (rlSids != null && !rlSids.isEmpty()) {
                queryString.append(SQlUtil.getQuery("getChildLevelRL"));
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
                if (parameters.get("availableHierNo") != null) {
                    List<String> availableHierNo = (ArrayList<String>) parameters.get("availableHierNo");
                    queryString.append(", ");
                    queryString.append(CommonUtils.stringListToString(availableHierNo));
                }
                queryString.append(") ");
            }
        } else if (parameters.get("indicator") != null && "getRemovableChildren".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("getRemovableChildren"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("removeLevels")));
        } else if (parameters.get("indicator") != null && "deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            String hNos = String.valueOf(parameters.get("hNos"));
            String projectionId = String.valueOf(parameters.get("projectionId"));
            String projectionHierarchyTable = String.valueOf(parameters.get("projectionHierarchyTable"));
            for (String table : getTempTableList()) {
                queryString.append(SQlUtil.getQuery("deleteTempOnUpdate"));
                queryString.replace(queryString.indexOf("?DTBL"), queryString.indexOf("?DTBL") + 5, table);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HTBL"), queryString.indexOf("?HTBL") + 5, projectionHierarchyTable);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
                queryString.replace(queryString.indexOf("?HNO"), queryString.indexOf("?HNO") + 4, hNos);
                queryString.replace(queryString.indexOf("?PID"), queryString.indexOf("?PID") + 4, projectionId);
            }
        } else if (parameters.get("indicator") != null && "getFSValue".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("getFSValue"));
            queryString.replace(queryString.indexOf("?RLC?"), queryString.indexOf("?RLC?") + 5, String.valueOf(parameters.get("relationshipLevelValue")));
        } else if (parameters.get("indicator") != null && "companyFilter".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {
            queryString.append(SQlUtil.getQuery("companyFilter"));
            queryString.append("'");
            queryString.append(String.valueOf(parameters.get("companySid")));
            queryString.append("'");
        } else if (parameters.get("indicator") != null
                && ("getHierarchyGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                || "getHierarchyGroupCount".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) { //searchGroup
            String query = SQlUtil.getQuery("getHierarchyGroup");

            if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                    && !"count".equals(String.valueOf(parameters.get("action")))) {
                query = query.replace("?SELECTION?", " C.HIERARCHY_DEFINITION_SID AS SID, C.HIERARCHY_NAME AS NAME, A.LEVEL_NAME AS LEVEL, "
                        + "A.LEVEL_NO AS HIGH_LEVEL, B.LEVEL_NO AS LOW_LEVEL, C.CREATED_DATE AS CREATED_DATE, C.MODIFIED_DATE AS MODIFIED_DATE, C.VERSION_NO AS VERSION ");
            } else {
                query = query.replace("?SELECTION?", " COUNT(C.HIERARCHY_DEFINITION_SID) AS SID_COUNT ");
            }
            if (!StringUtils.isBlank(String.valueOf(parameters.get("customerOrProduct"))) && "Customer Hierachy".equals(String.valueOf(parameters.get("customerOrProduct")))) {
                query = query.replace("?HIERARCHY_TYPE?", "Customer Hierarchy");
            } else {
                query = query.replace("?HIERARCHY_TYPE?", "Product Hierarchy");
            }
            if (parameters.get("hierarchyName") != null && !"null".equals(String.valueOf(parameters.get("hierarchyName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("hierarchyName")))) {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", " AND C.HIERARCHY_NAME like '" + String.valueOf(parameters.get("hierarchyName")) + "' ");
            } else {
                query = query.replace("?HIERARCHY_NAME_CONDITION?", StringUtils.EMPTY);
            }
            if (parameters.get("hierarchyType") != null && !"null".equals(String.valueOf(parameters.get("hierarchyType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("hierarchyType")))) {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", " AND C.HIERARCHY_TYPE in (SELECT HT.HELPER_TABLE_SID FROM HELPER_TABLE HT where HT.DESCRIPTION LIKE '"
                        + String.valueOf(parameters.get("hierarchyType")) + "') ");
            } else {
                query = query.replace("?HIERARCHY_TYPE_CONDITION?", StringUtils.EMPTY);
            }
            try {
                if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                        && !"count".equals(String.valueOf(parameters.get("action")))) {
                    if (String.valueOf(parameters.get("isFiltered")).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder(StringUtils.EMPTY);
                        if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND C.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND C.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND C.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))) {
                            filterAppender.append(" AND C.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND C.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~hierarchyName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~hierarchyName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~hierarchyName")))) {
                            filterAppender.append(" AND C.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~hierarchyName")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~highestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~highestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~highestLevel")))) {
                            filterAppender.append(" AND A.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~highestLevel")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~lowestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~lowestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~lowestLevel")))) {
                            filterAppender.append(" AND B.LEVEL_NO like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~lowestLevel")));
                            filterAppender.append("' ");
                        }
                        query = query.replace("?FILTER?", filterAppender.toString());
                    } else {
                        query = query.replace("?FILTER?", StringUtils.EMPTY);
                    }
                    if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                        query = query.replace("?ORDER_BY?", " ORDER BY C.CREATED_DATE ");
                    } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                        StringBuilder orderByAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("orderBy~hierarchyName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~hierarchyName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~hierarchyName")))) {
                            orderByAppender.append(" ORDER BY C.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~hierarchyName")));
                        }

                        if (parameters.get("orderBy~highestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~highestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~highestLevel")))) {
                            orderByAppender.append(" ORDER BY A.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~highestLevel")));
                        }

                        if (parameters.get("orderBy~lowestLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~lowestLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~lowestLevel")))) {
                            orderByAppender.append(" ORDER BY B.LEVEL_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~lowestLevel")));
                        }

                        if (parameters.get("orderBy~createdDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                            orderByAppender.append(" ORDER BY C.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
                        }

                        if (parameters.get("orderBy~modifiedDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                            orderByAppender.append(" ORDER BY C.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
                        }
                        query = query.replace("?ORDER_BY?", orderByAppender.toString());
                    }
                    if (parameters.get("start") != null && parameters.get("offset") != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                        int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
                    }

                } else {
                    query = query.replace("?ORDER_BY?", StringUtils.EMPTY);
                    query = query.replace("?FILTER?", StringUtils.EMPTY);
                }

            } catch (Exception ex) {
                LOGGER.error(ex.getMessage() + " in execute query");
            }
            queryString.append(query);
            queryString.append(";");
        } else if (parameters.get("indicator") != null
                && ("searchGroup".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                || "searchGroupCount".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
            queryString.append(prepareSearchGroupQuery(parameters));
        } else if (parameters.get("indicator") != null
                && ("searchView".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))
                || "searchViewCount".equalsIgnoreCase(String.valueOf(parameters.get("indicator"))))) {
            queryString.append(prepareSearchViewQuery(parameters));
        } else {
            queryString.append(String.valueOf(parameters.get("query")));
        }
        try {
            if (parameters.get("indicator") != null
                    && (("deleteTempOnUpdate".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))))) {
                List<Integer> list = new ArrayList<Integer>();
                int returnValue = HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
                list.add(returnValue);
                return list;
            } else {
                return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in execute query");
            LOGGER.error(queryString.toString());
            return null;
        }
    }

    private String prepareSearchViewQuery(final Map<String, Object> parameters) {
        String query = StringUtils.EMPTY;
        try {
            StringBuilder queryString = new StringBuilder(SQlUtil.getQuery("findViewByNameJoin"));
            if (parameters.get("viewType") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("viewType")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("viewType")))) {
                queryString.append(" FVM.VIEW_TYPE LIKE '");
                queryString.append(String.valueOf(parameters.get("viewType")));
                queryString.append("' ");

                if (String.valueOf(parameters.get("viewType")).equalsIgnoreCase("private")
                        && parameters.get("userId") != null
                        && !StringUtils.isBlank(String.valueOf(parameters.get("userId")))) {
                    queryString.append(" AND FVM.CREATED_BY = ");
                    queryString.append(String.valueOf(parameters.get("userId")));
                }

            }
            if (parameters.get("viewName") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("viewName")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("viewName")))) {
                queryString.append(" AND FVM.VIEW_NAME LIKE '");
                queryString.append(String.valueOf(parameters.get("viewName")));
                queryString.append("' ");
            }

            if (parameters.get("forecastType") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("forecastType")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("forecastType")))) {
                queryString.append(" AND PM.FORECASTING_TYPE ='");
                queryString.append(String.valueOf(parameters.get("forecastType")));
                queryString.append("' ");
            }

            queryString.append("?FILTER?");
            queryString.append("?ORDER_BY?");
            query = queryString.toString();

            try {
                if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                        && !"count".equals(String.valueOf(parameters.get("action")))) {
                    query = query.replace("?SELECTION?", SQlUtil.getQuery("searchViewFindSelection"));
                    if (String.valueOf(parameters.get("isFiltered")).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder(StringUtils.EMPTY);
                        if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND PM.CREATED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND PM.CREATED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                                && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
                            filterAppender.append(" AND PM.CREATED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE BETWEEN '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(from);
                            filterAppender.append("' AND '");
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE < '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
                            filterAppender.append(to);
                            filterAppender.append("' ");
                        } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                                && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
                            filterAppender.append(" AND PM.MODIFIED_DATE > '");
                            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
                            filterAppender.append(from);
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~viewName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~viewName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~viewName")))) {
                            filterAppender.append(" AND FVM.VIEW_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~viewName")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~viewName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~description")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~description")))) {
                            filterAppender.append(" AND PM.PROJECTION_DESCRIPTION like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~description")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~customerHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerHierarchy")))) {
                            filterAppender.append(" AND CUST_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerHierarchy")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~customerLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerLevel")))) {
                            filterAppender.append(" AND PM.CUSTOMER_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerLevel")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~customerGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerGroup")))) {
                            filterAppender.append(" AND CMG.COMPANY_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerGroup")));
                            filterAppender.append("' ");
                        }

                        if (parameters.get("filter~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~company")))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~company")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~brandType") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~brandType")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~brandType")))) {
                            filterAppender.append(" AND PM.BRAND_TYPE like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~brandType")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productHierarchy")))) {
                            filterAppender.append(" AND PROD_HD.HIERARCHY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productHierarchy")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productLevel")))) {
                            filterAppender.append(" AND PM.PRODUCT_HIERARCHY_LEVEL like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productLevel")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productGroup")))) {
                            filterAppender.append(" AND IG.ITEM_GROUP_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productGroup")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~createdBy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdBy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdBy")))) {
                            filterAppender.append(" AND PM.CREATED_BY in (");
                            filterAppender.append(String.valueOf(parameters.get("filter~createdBy")));
                            filterAppender.append(") ");
                        }

                        query = query.replace("?FILTER?", filterAppender.toString());
                    } else {
                        query = query.replace("?FILTER?", StringUtils.EMPTY);
                    }
                    if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                        query = query.replace("?ORDER_BY?", " ORDER BY PM.CREATED_DATE ");
                    } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                        StringBuilder orderByAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("orderBy~viewName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~viewName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~viewName")))) {
                            orderByAppender.append(" ORDER BY FVM.VIEW_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~viewName")));
                        }

                        if (parameters.get("orderBy~description") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~description")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~description")))) {
                            orderByAppender.append(" ORDER BY PM.PROJECTION_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~description")));
                        }

                        if (parameters.get("orderBy~from") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~from")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~from")))) {
                            orderByAppender.append(" ORDER BY PM.FROM_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~from")));
                        }

                        if (parameters.get("orderBy~to") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~to")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~to")))) {
                            orderByAppender.append(" ORDER BY PM.TO_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~to")));
                        }

                        if (parameters.get("orderBy~customerHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerHierarchy")))) {
                            orderByAppender.append(" ORDER BY CUST_HD.HIERARCHY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerHierarchy")));
                        }

                        if (parameters.get("orderBy~customerLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerLevel")))) {
                            orderByAppender.append(" ORDER BY PM.CUSTOMER_HIERARCHY_LEVEL ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerLevel")));
                        }

                        if (parameters.get("orderBy~customerGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerGroup")))) {
                            orderByAppender.append(" ORDER BY CMG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerGroup")));
                        }

                        if (parameters.get("orderBy~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~company")));
                        }

                        if (parameters.get("orderBy~brandType") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~brandType")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~brandType")))) {
                            orderByAppender.append(" ORDER BY PM.BRAND_TYPE  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~brandType")));
                        }

                        if (parameters.get("orderBy~productHierarchy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productHierarchy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productHierarchy")))) {
                            orderByAppender.append(" ORDER BY PROD_HD.HIERARCHY_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productHierarchy")));
                        }

                        if (parameters.get("orderBy~productLevel") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productLevel")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productLevel")))) {
                            orderByAppender.append(" ORDER BY PM.PRODUCT_HIERARCHY_LEVEL  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productLevel")));
                        }

                        if (parameters.get("orderBy~productGroup") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroup")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroup")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME  ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productGroup")));
                        }
                        if (parameters.get("orderBy~createdDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
                        }

                        if (parameters.get("orderBy~modifiedDateSearch") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                            orderByAppender.append(" ORDER BY PM.MODIFIED_DATE ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
                        }
                        if (parameters.get("orderBy~createdBy") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdBy")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdBy")))) {
                            orderByAppender.append(" ORDER BY PM.CREATED_BY ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~createdBy")));
                        }
                        query = query.replace("?ORDER_BY?", orderByAppender.toString());
                    }
                    if (parameters.get("start") != null && parameters.get("offset") != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                        int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
                    }
                } else {
                    query = query.replace("?SELECTION?", " COUNT (DISTINCT FVM.VIEW_ID) ");
                    query = query.replace("?ORDER_BY?", StringUtils.EMPTY);
                    query = query.replace("?FILTER?", StringUtils.EMPTY);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage() + " in execute query");
                LOGGER.error(query);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
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
                queryString.append(" AND ");
                queryString.append(no);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("no")));
                queryString.append("'");
            }
            if (parameters.get("name") != null
                    && !StringUtils.isEmpty(String.valueOf(parameters.get("name")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("name")))) {
                queryString.append(" AND ");
                queryString.append(name);
                queryString.append(" LIKE '");
                queryString.append(String.valueOf(parameters.get("name")));
                queryString.append("'");
            }
            if (parameters.get("sids") != null && !"null".equals(String.valueOf(parameters.get("sids")))) {
                queryString.append(" AND ");
                queryString.append(sids);
                queryString.append(" in (");
                queryString.append(String.valueOf(parameters.get("sids")));
                queryString.append(")");
            }

            queryString.append("?FILTER?");
            queryString.append("?ORDER_BY?");

            query = queryString.toString();
            try {
                if (parameters.get("action") != null && !StringUtils.isBlank(String.valueOf(parameters.get("action")))
                        && !"count".equals(String.valueOf(parameters.get("action")))) {
                    if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                        query = query.replace("?SELECTION?", " DISTINCT CG.COMPANY_GROUP_SID, CG.COMPANY_GROUP_NO, CG.COMPANY_GROUP_NAME, CG.VERSION_NO, CG.COMPANY_GROUP_DESCRIPTION ");
                    } else {
                        query = query.replace("?SELECTION?", " DISTINCT IG.ITEM_GROUP_SID, IG.ITEM_GROUP_NO, IG.ITEM_GROUP_NAME, CM.COMPANY_NAME, IG.VERSION_NO, IG.ITEM_GROUP_DESCRIPTION ");
                    }
                    if (String.valueOf(parameters.get("isFiltered")).equals("true")) {
                        StringBuilder filterAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("filter~customerGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerGroupName")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(name);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerGroupName")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productGroupName")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(name);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productGroupName")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~customerGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customerGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customerGroupNo")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(no);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customerGroupNo")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productGroupNo")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(no);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productGroupNo")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~customergroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~customergroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~customergroupDescription")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(desc);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~customergroupDescription")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~productgroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~productgroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~productgroupDescription")))) {
                            filterAppender.append(" AND ");
                            filterAppender.append(desc);
                            filterAppender.append(" like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~productgroupDescription")));
                            filterAppender.append("' ");
                        }
                        if (parameters.get("filter~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("filter~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~company")))) {
                            filterAppender.append(" AND CM.COMPANY_NAME like '");
                            filterAppender.append(String.valueOf(parameters.get("filter~company")));
                            filterAppender.append("' ");
                        }
                        query = query.replace("?FILTER?", filterAppender.toString());
                    } else {
                        query = query.replace("?FILTER?", StringUtils.EMPTY);
                    }
                    if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                        if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                            query = query.replace("?ORDER_BY?", " ORDER BY CG.COMPANY_GROUP_SID ");
                        } else {
                            query = query.replace("?ORDER_BY?", " ORDER BY IG.ITEM_GROUP_SID ");
                        }
                    } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                        StringBuilder orderByAppender = new StringBuilder(StringUtils.EMPTY);
                        if (parameters.get("orderBy~customerGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerGroupName")))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerGroupName")));
                        }
                        if (parameters.get("orderBy~customerGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customerGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customerGroupNo")))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customerGroupNo")));
                        }
                        if (parameters.get("orderBy~customergroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~customergroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~customergroupDescription")))) {
                            orderByAppender.append(" ORDER BY CG.COMPANY_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~customergroupDescription")));
                        }
                        if (parameters.get("orderBy~productGroupName") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupName")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupName")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productGroupName")));
                        }
                        if (parameters.get("orderBy~productGroupNo") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupNo")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupNo")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_NO ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productGroupNo")));
                        }
                        if (parameters.get("orderBy~productgroupDescription") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productgroupDescription")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productgroupDescription")))) {
                            orderByAppender.append(" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~productgroupDescription")));
                        }
                        if (parameters.get("orderBy~company") != null
                                && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company")))
                                && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                            orderByAppender.append(" ORDER BY CM.COMPANY_NAME ");
                            orderByAppender.append(String.valueOf(parameters.get("orderBy~company")));
                        }
                        query = query.replace("?ORDER_BY?", orderByAppender.toString());
                    }
                    if (parameters.get("start") != null && parameters.get("offset") != null) {
                        int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                        int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY ";
                    }
                } else {
                    if (parameters.get("groupIdentifier") != null && Constants.INDICATOR_CUSTOMER_GROUP.equalsIgnoreCase(String.valueOf(parameters.get("groupIdentifier")))) {
                        query = query.replace("?SELECTION?", " COUNT (DISTINCT CG.COMPANY_GROUP_SID) ");
                    } else {
                        query = query.replace("?SELECTION?", " COUNT (DISTINCT IG.ITEM_GROUP_SID) ");
                    }
                    query = query.replace("?ORDER_BY?", StringUtils.EMPTY);
                    query = query.replace("?FILTER?", StringUtils.EMPTY);
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage() + " in execute query");
                LOGGER.error(query);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        }
        return query;

    }

}
