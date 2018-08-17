/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.service;

import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa.Tippireddy
 */
public class AdminConsoleImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminConsoleImpl.class);

    public List getBusinessTabPermission(String businessRoleId, String moduleName) {
        String sql = StringUtils.EMPTY;
        try {
            sql = SQlUtil.getQuery("tabPermission");
            if (businessRoleId.length() != 0) {
                sql += AND_UBM_BUSINESS_ROLE_MASTER_SID_IN
                        + businessRoleId + ")";
            }
            if (moduleName.length() != 0) {
                sql += AND_SPM_MODULE_NAME_IN + moduleName + "') ";
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        }
    }
    public static final String AND_SPM_MODULE_NAME_IN = " AND spm.MODULE_NAME in ('";
    public static final String AND_UBM_BUSINESS_ROLE_MASTER_SID_IN = " AND ubm.BUSINESSROLE_MASTER_SID in (";

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
                sql = SQlUtil.getQuery("fieldPermissionForTransaction");
            } else {
                sql = SQlUtil.getQuery("fieldPermission");
            }

            if (businessRoleId.length() != 0) {
                sql += AND_UBM_BUSINESS_ROLE_MASTER_SID_IN
                        + businessRoleId + ")";
            }

            if (mod.length() != 0) {
                sql += AND_SPM_MODULE_NAME_IN + mod + "') ";
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
            sql = SQlUtil.getQuery("functionPermission");
            if (businessRoleId.length() != 0) {
                sql += AND_UBM_BUSINESS_ROLE_MASTER_SID_IN
                        + businessRoleId + ")";
            }
            if (mod.length() != 0) {
                sql += AND_SPM_MODULE_NAME_IN + mod + "') ";
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
    
    public List fetchFieldsForSecurity(String moduleName, String tabName, Object obj1, Object obj2, Object obj3){
        String query = "";
        try {
            query = "SELECT DISPLAY_NAME, PROPERTY_NAME ,CATEGORY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '"+moduleName+"' "
                    + " AND TAB_NAME = '"+tabName+"' AND CATEGORY_NAME NOT IN ('Button','Tab') ";
            return HelperTableLocalServiceUtil.executeSelectQuery(query);
        } catch(Exception ex){
           LOGGER.error(ex.getMessage());
           LOGGER.error(query);
           return null;           
        } 
        
    }
    
    public List getDetailsResults(String fileName, String version, String fileType, String country,int year) {
        List list = new ArrayList();
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
            if(version.contains("~"))
            {
               String[] versionArray=version.split("~");
               sqlString=sqlString.concat(" AND ").concat(" (FM.FORECAST_VER='").concat(versionArray[0]).concat("' or FM.FORECAST_VER='").concat(versionArray[1]).concat("')");
            }else{
              sqlString=sqlString.concat(" AND ").concat(" FM.FORECAST_VER='").concat(version).concat("'");  
            }
            sqlString=sqlString.concat(" ORDER BY FM.FORECAST_YEAR");
            list.addAll(HelperTableLocalServiceUtil.executeSelectQuery(sqlString));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(sqlString);
        } 
        return list;
    }
    public Object executeSelectQuery(List input, String queryName, String quaryName2) {
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            if (queryName != null && !queryName.isEmpty()) {
                StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(' ');
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            }
        } catch (Exception e) {
            
            LOGGER.error(e.getMessage());
        } 
        return returnList;
    }
}
