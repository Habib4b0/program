/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 * @param <T>
 */
public class Trx7SalesLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T,E> {

    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    @Override
    public int getCount(Criteria criteria) {

        return getSalesCount(criteria.getParent(), criteria.getSelectionDto());
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        return getSalesData(criteria.getParent(), criteria.getSelectionDto(), criteria.getStart(), criteria.getOffset());
    }

    public List<Object> getMonthYear() {
        String sql = SQlUtil.getQuery("getMonthYear");
        List result = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        List<Object> defaultValues = new ArrayList<>();
        if (!result.isEmpty()) {
            Object[] value = (Object[]) result.get(0);
            for (Object value1 : value) {
                defaultValues.add(String.valueOf(value1));
            }
        }
        return defaultValues;
    }

    /**
     * Get month name from month number
     *
     * @param monthName
     * @return Jan-1........Dec-12
     */
    public static String getMonthName(int monthNo) {
        String monthName = StringUtils.EMPTY;
        try {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            monthName = months[monthNo - 1];
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return monthName;
    }

    public int getSalesCount(Object parentId, SelectionDTO selection) {
         String level = "CUSTOMER";
        String parentLevel="";
        String brandMasterSid ="";
        String companyMasterSid="";
        
        String sql = "";
       if (parentId instanceof AdjustmentDTO) {
            parentLevel=((AdjustmentDTO) parentId).getLevelNames() ;
            companyMasterSid=((AdjustmentDTO) parentId).getCompSids();
            brandMasterSid = ((AdjustmentDTO) parentId).getBrand_item_masterSid();
                
            if(parentLevel.equals("CUSTOMER")){
             companyMasterSid=((AdjustmentDTO) parentId).getCompSids();
             level = "BRAND";
           }else if(parentLevel.equals("BRAND")){
            companyMasterSid=((AdjustmentDTO) parentId).getCompSids();
            brandMasterSid = ((AdjustmentDTO) parentId).getBrand_item_masterSid();
            level="ITEM";
            }
            
           
        }
        if ("Product".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))) {
            level = "ITEM";
        }else if("Brand".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))){
         level = "BRAND";
        }
       
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("trx7_getSalesBrandCount");
        } else {
            sql = SQlUtil.getQuery("trx7_getSalesBrandCountEdit");
        }
        sql = sql.replace("[$VIEW]", level);
        
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getProjectionMasterSid()));
        LOGGER.debug("brandMasterSid----" + brandMasterSid);
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
        if ("ITEM".equals(level)) {
            if ("Product".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))) {
                sql = sql.replace("[COMPANY_MASTER_SID]", "");
                sql = sql.replace("[BRAND_MASTER_SID]", "");
            } else {
                sql = sql.replace("[COMPANY_MASTER_SID]", " AND APS.COMPANY_MASTER_SID  = " + companyMasterSid + "");
                sql = sql.replace("[BRAND_MASTER_SID]", "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        } if("BRAND".equals(level)){
            if ("Brand".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))) {
            sql = sql.replace("[COMPANY_MASTER_SID]", "");
            sql = sql.replace("[BRAND_MASTER_SID]", "");
            }else{
            sql = sql.replace("[COMPANY_MASTER_SID]", " AND APS.COMPANY_MASTER_SID  = " + companyMasterSid + "");
             sql = sql.replace("[BRAND_MASTER_SID]", "");
        }
            
        }else {
            sql = sql.replace("[COMPANY_MASTER_SID]", "");
            sql = sql.replace("[BRAND_MASTER_SID]", "");
        }
        sql = CommonLogic.replaceTableNames(sql, selection.getSessionDTO().getCurrentTableNames());
        List<Integer> result = QueryUtils.executeSelect(sql);
        int countRes=(result!=null&&result.size()>0)? result.get(0):0;
        return countRes;
    }

    public DataResult<T> getSalesData(Object parentId, SelectionDTO selection, int start, int offset) {
     
        String sql = "";
        String level = "CUSTOMER";
        String parentLevel="";
        String brandMasterSid ="0";
        String companyMasterSid="0";
        
         if (parentId instanceof AdjustmentDTO) {
            
            parentLevel=((AdjustmentDTO) parentId).getLevelNames() ;
            companyMasterSid=((AdjustmentDTO) parentId).getCompSids();
            brandMasterSid = ((AdjustmentDTO) parentId).getBrand_item_masterSid();
                
            if(parentLevel.equals("CUSTOMER")){
             companyMasterSid=((AdjustmentDTO) parentId).getCompSids();
             level = "BRAND";
           }else if(parentLevel.equals("BRAND")){
            companyMasterSid=((AdjustmentDTO) parentId).getCompSids();
            brandMasterSid = ((AdjustmentDTO) parentId).getBrand_item_masterSid();
            level="ITEM";
            }
            
           
        }
         if ("Product".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))) {
            level = "ITEM";
        }else if("Brand".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))){
         level = "BRAND";
        }
         
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("trx7_getSalesBrand");
            
        } else {
            sql = SQlUtil.getQuery("trx7_getSalesBrandEdit");
        }
        sql = sql.replace("[$VIEW]", level);
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getProjectionMasterSid()));
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
        
        sql = sql.replace("[LEVEL_FILTER]", String.valueOf(selection.getSales_levelFilterValue()));
        sql = sql.replace("[COM_MASTER_SID]", companyMasterSid);
        sql = sql.replace("[BRA_MASTER_SID]", brandMasterSid);
       
        
        if ("ITEM".equals(level)) {
            if ("Product".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))) {
                sql = sql.replace("[COMPANY_MASTER_SID]", "");
                sql = sql.replace("[BRAND_MASTER_SID]", "");
            } else {
                sql = sql.replace("[COMPANY_MASTER_SID]", " AND APS.COMPANY_MASTER_SID  = " + companyMasterSid + "");
                sql = sql.replace("[BRAND_MASTER_SID]", "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        } if("BRAND".equals(level)){
            if ("Brand".equalsIgnoreCase(String.valueOf(selection.getSales_levelFilterValue()))) {
            sql = sql.replace("[COMPANY_MASTER_SID]", "");
            sql = sql.replace("[BRAND_MASTER_SID]", "");
            }else{
            sql = sql.replace("[COMPANY_MASTER_SID]", " AND APS.COMPANY_MASTER_SID  = " + companyMasterSid + "");
             sql = sql.replace("[BRAND_MASTER_SID]", "");
        }
            
        }else {
            sql = sql.replace("[COMPANY_MASTER_SID]", "");
            sql = sql.replace("[BRAND_MASTER_SID]", "");
        }
        
        
        sql += " ORDER BY PRODUCT_NO OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        sql = CommonLogic.replaceTableNames(sql, selection.getSessionDTO().getCurrentTableNames());
        List<Object[]> result = QueryUtils.executeSelect(sql);
        LOGGER.debug("result==============" + result.size());
        DataResult<T> resultList = customizier("com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO",
                ARMUtils.getTrx7SalesVariables(), result);
        return resultList;
    }

    public boolean updatePriceOverride(List input) {
        LOGGER.debug("Inside update price override");
        try {
            QueryUtils.itemUpdate(input, "trx7_PA_sales_updatePriceOverride");
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
        return true;

    }

    public void getSalesResults(Object[] orderedArgs) {
        callProcedure("PRC_ARM_DISTRIBUTION_FEE_SALES", orderedArgs);
    }

    public static void callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
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

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query = StringUtils.EMPTY;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("trx7_getPASalesExcelQueryView");
        } else {
            query = SQlUtil.getQuery("trx7_getPASalesExcelQuery");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        return list;
    }
}
