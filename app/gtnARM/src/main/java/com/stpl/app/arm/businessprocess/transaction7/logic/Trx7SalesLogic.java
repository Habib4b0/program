/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 * @param <T>
 */
public class Trx7SalesLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T, E> {

    public static final Logger LOGGERTRX7 = Logger.getLogger(Trx7SalesLogic.class);
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    @Override
    public int getCount(Criteria criteria) {

        return getSalesCount(criteria.getParent(), criteria.getSelectionDto());
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        return getSalesData(criteria.getParent(), criteria.getSelectionDto(), criteria.getStart(), criteria.getOffset());
    }

    @Override
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
            LOGGERTRX7.error("Error in getMonthName :"+e);
        }
        return monthName;
    }

    public int getSalesCount(Object parentId, SelectionDTO selection) {
        int countRes = NumericConstants.ZERO;
        try {
            LOGGERTRX7.debug("Inside getSalesCount--" + selection.getProjectionMasterSid());
            String level = CommonConstant.CUSTOMER_LEVEL;
            String parentLevel = "";
            String brandMasterSid = "";
            String companyMasterSid = "";

            String sql = "";
            if (parentId instanceof AdjustmentDTO) {
                parentLevel = ((AdjustmentDTO) parentId).getLevelNames();
                companyMasterSid = ((AdjustmentDTO) parentId).getCompSids();
                brandMasterSid = ((AdjustmentDTO) parentId).getBranditemmasterSid();

                if (parentLevel.equals(CommonConstant.CUSTOMER_LEVEL)) {
                    companyMasterSid = ((AdjustmentDTO) parentId).getCompSids();
                    level = CommonConstant.BRAND_LEVEL;
                } else if (parentLevel.equals(CommonConstant.BRAND_LEVEL)) {
                    companyMasterSid = ((AdjustmentDTO) parentId).getCompSids();
                    brandMasterSid = ((AdjustmentDTO) parentId).getBranditemmasterSid();
                    level = "ITEM";
                }

            }
            if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                level = "ITEM";
            } else if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                level = CommonConstant.BRAND_LEVEL;
            }

            if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
                sql = SQlUtil.getQuery("trx7_getSalesBrandCount");
            } else {
                sql = SQlUtil.getQuery("trx7_getSalesBrandCountEdit");
            }
            sql = sql.replace("[$VIEW]", level);

            sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getProjectionMasterSid()));
            LOGGERTRX7.debug("brandMasterSid----" + brandMasterSid);
            sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
            if ("ITEM".equals(level)) {
                if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                    sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                    sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
                } else {
                    sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                    sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
                }
            }
            if (CommonConstant.BRAND_LEVEL.equals(level)) {
                if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                    sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                    sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
                } else {
                    sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                    sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
                }

            } else {
                sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
            }
            sql = CommonLogic.replaceTableNames(sql, selection.getSessionDTO().getCurrentTableNames());
            LOGGERTRX7.debug("sql----" + sql);
            List<Integer> result = QueryUtils.executeSelect(sql);
            countRes = (result != null && !result.isEmpty()) ? result.get(0) : 0;
            LOGGERTRX7.debug("Exit getSalesCount--" + countRes);
        } catch (Exception ex) {
            LOGGERTRX7.error("Error in getSalesCount :"+ex);
        }
        return countRes;
    }

    public DataResult<T> getSalesData(Object parentId, SelectionDTO selection, int start, int offset) {
        LOGGERTRX7.debug("Inside getSalesData--" + selection.getProjectionMasterSid());
        String sql = "";
        String level = CommonConstant.CUSTOMER_LEVEL;
        String parentLevel = "";
        String brandMasterSid = "0";
        String companyMasterSid = "0";

        if (parentId instanceof AdjustmentDTO) {

            parentLevel = ((AdjustmentDTO) parentId).getLevelNames();
            companyMasterSid = ((AdjustmentDTO) parentId).getCompSids();
            brandMasterSid = ((AdjustmentDTO) parentId).getBranditemmasterSid();

            if (parentLevel.equals(CommonConstant.CUSTOMER_LEVEL)) {
                companyMasterSid = ((AdjustmentDTO) parentId).getCompSids();
                level = CommonConstant.BRAND_LEVEL;
            } else if (parentLevel.equals(CommonConstant.BRAND_LEVEL)) {
                companyMasterSid = ((AdjustmentDTO) parentId).getCompSids();
                brandMasterSid = ((AdjustmentDTO) parentId).getBranditemmasterSid();
                level = "ITEM";
            }

        }
        if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
            level = "ITEM";
        } else if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
            level = CommonConstant.BRAND_LEVEL;
        }

        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("trx7_getSalesBrand");

        } else {
            sql = SQlUtil.getQuery("trx7_getSalesBrandEdit");
        }
        sql = sql.replace("[$VIEW]", level);
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getProjectionMasterSid()));
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);

        sql = sql.replace("[LEVEL_FILTER]", String.valueOf(selection.getSaleslevelFilterValue()));
        sql = sql.replace("[COM_MASTER_SID]", companyMasterSid);
        sql = sql.replace("[BRA_MASTER_SID]", brandMasterSid);

        if ("ITEM".equals(level)) {
            if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
            } else {
                sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        }
        if (CommonConstant.BRAND_LEVEL.equals(level)) {
            if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
            } else {
                sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
            }

        } else {
            sql = sql.replace(CommonConstant.COMPANY_MASTER_SID, "");
            sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
        }

        sql += " ORDER BY PRODUCT_NO OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        sql = CommonLogic.replaceTableNames(sql, selection.getSessionDTO().getCurrentTableNames());
        List<Object[]> result = QueryUtils.executeSelect(sql);
        LOGGERTRX7.debug("sql==============" + sql);
        DataResult<T> resultList = customizier("com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO",
                ARMUtils.getTrx7SalesVariables(), result);
        LOGGERTRX7.debug("Exit getSalesData--" + result.size());
        return resultList;
    }

    public boolean updatePriceOverride(List input) {
        LOGGERTRX7.debug("Inside update price override");
        try {
            QueryUtils.itemUpdate(input, "trx7_PA_sales_updatePriceOverride");
        } catch (Exception e) {
            LOGGERTRX7.error("Error in updatePriceOverride :"+e);
            return false;
        }
        return true;

    }

    public void getSalesResults(Object[] orderedArgs) {
        callProcedure("PRC_ARM_DISTRIBUTION_FEE_SALES", orderedArgs);
    }

    public static void callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGERTRX7.debug("Procedure Name " + procedureName);
        StringBuilder procedureToCall = new StringBuilder("{call ");
        procedureToCall.append(procedureName);
        int noOfArgs = orderedArgs.length;
        for (int i = 0; i < noOfArgs; i++) {
            if (i == 0) {
                procedureToCall.append("(");
            }
            procedureToCall.append("?,");
            if (i == noOfArgs - 1) {
                procedureToCall.append(")");
            }
        }
        procedureToCall.append("}");
        String procedureToCallVal = procedureToCall.toString().replace(",)", ")");
        try (Connection connection = ((DataSource) new InitialContext().lookup(DATASOURCE_CONTEXT)).getConnection();
                CallableStatement statement = connection.prepareCall(procedureToCallVal)) {
            for (int i = 0; i < noOfArgs; i++) {
                LOGGERTRX7.debug(i + " -- " + orderedArgs[i]);
                statement.setObject(i + 1, orderedArgs[i]);
            }
            statement.executeUpdate();
        } catch (Exception ex) {
            LOGGERTRX7.error("Error in callProcedure :"+ex);
        }

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        LOGGERTRX7.debug("Inside of getExcelResultList --");
        String query;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("trx7_getPASalesExcelQueryView");
        } else {
            query = SQlUtil.getQuery("trx7_getPASalesExcelQuery");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(selection.getSessionId()));
        LOGGERTRX7.debug("query --" + query);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        LOGGERTRX7.debug("Exit of getExcelResultList --" + list.size());
        return list;
    }
}
