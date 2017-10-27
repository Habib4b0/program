/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic;

import static com.stpl.app.arm.utils.ARMUtils.SalesVariables.ST_ARM_PIPELINE_SALES;


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
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author srithar
 * @param <T>
 */
public class SalesLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T, E> {

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
            LOGGER.error("Error in getMonthName :"+e);

        }
        return monthName;
    }

    public int getSalesCount(Object parentId, SelectionDTO selection) {
        String sql = "";
        String level = "BRAND";
        String brandMasterSid = "0";
        if (parentId instanceof AdjustmentDTO) {
            level = "ITEM";
            brandMasterSid = ((AdjustmentDTO) parentId).getBranditemmasterSid();
        }
        if (VariableConstants.PRODUCT.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
            level = "ITEM";
        }
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("getSalesBrandCount");
        } else {
            sql = SQlUtil.getQuery("getSalesBrandCountEdit");
            sql = sql.replace("[TABLE]", selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_PIPELINE_SALES.toString()));
        }
        sql = sql.replace("[$VIEW]", level);
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getProjectionMasterSid()));
        LOGGER.debug("brandMasterSid----" + brandMasterSid);
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
        if ("ITEM".equals(level)) {
            if (VariableConstants.PRODUCT.equalsIgnoreCase(String.valueOf(selection.getSaleslevelFilterValue()))) {
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
            } else {
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        } else {
            sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
        }
        List<Integer> result = QueryUtils.executeSelect(sql);
        LOGGER.debug("count--------------------" + result.get(0));
        return result.get(0);
    }

    public DataResult<T> getSalesData(Object parentId, SelectionDTO selection, int start, int offset) {
        LOGGER.debug("selection.getDataSelectionDTO().getProjectionId() ******!!!!!!!" + selection.getProjectionMasterSid());

        String sql = "";
        String level = "BRAND";
        String brandMasterSid = "0";
        if (parentId instanceof AdjustmentDTO) {
            level = "ITEM";
            brandMasterSid = ((AdjustmentDTO) parentId).getBranditemmasterSid();
        }
        if (VariableConstants.PRODUCT.equals(String.valueOf(selection.getSaleslevelFilterValue()))) {
            level = "ITEM";
        }
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            sql = SQlUtil.getQuery("getSalesBrand");
        } else {
            sql = SQlUtil.getQuery("getSalesBrandEdit");
            sql = sql.replace("[TABLE]", selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_PIPELINE_SALES.toString()));
        }
        sql = sql.replace("[$VIEW]", level);
        sql = sql.replace("[PROJECTION_MASTER_SID]", String.valueOf(selection.getProjectionMasterSid()));
        sql = sql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
        sql = sql.replace("[LEVEL_FILTER]", String.valueOf(selection.getSaleslevelFilterValue()));
        if ("ITEM".equals(level)) {
            if (VariableConstants.PRODUCT.equals(String.valueOf(selection.getSaleslevelFilterValue()))) {
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
            } else {
                sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        } else {
            sql = sql.replace(CommonConstant.BRAND_MASTER_SID, "");
        }
        sql += " ORDER BY PRODUCT_NO OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        List<Object[]> result = QueryUtils.executeSelect(sql);
        return customizier("com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO",
                ARMUtils.getSalesVariables(), result);
    }

    public boolean updatePriceOverride(List input) {
        try {
            QueryUtils.itemUpdate(input, "PA_sales_updatePriceOverride");
        } catch (Exception e) {
            LOGGER.error("Error in updatePriceOverride :"+e);
            return false;
        }
        return true;

    }

    public void getSalesResults(Object[] orderedArgs) {
        callProcedure("PRC_ARM_PIPELINE_SALES", orderedArgs);
    }

    public static void callProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name " + procedureName);
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
                LOGGER.debug(i + " -- " + orderedArgs[i]);
                statement.setObject(i + 1, orderedArgs[i]);
            }
            statement.executeUpdate();
        } catch (Exception ex) {
            LOGGER.error("Error in callProcedure :"+ex);
        }

    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        String query;
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("getPASalesExcelQueryView");
        } else {
            query = SQlUtil.getQuery("getPASalesExcelQuery");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(selection.getProjectionMasterSid()));
        return HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
    }
}
