/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.service;

import com.stpl.app.gtnworkflow.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa.Tippireddy
 */
public class WorkflowImpl {

    private static final Logger LOGGER = Logger.getLogger(WorkflowImpl.class);

    public List getBusinessTabPermission(String businessRoleId, String moduleName) {
        String sql = StringUtils.EMPTY;
        try {
            sql = SQlUtil.getQuery("tabPermission");
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
}
