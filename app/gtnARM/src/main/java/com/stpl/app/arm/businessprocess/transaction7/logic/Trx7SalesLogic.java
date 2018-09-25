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
import com.stpl.ifs.util.QueryUtil;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 * @param <T>
 */
public class Trx7SalesLogic<T extends AdjustmentDTO, E extends AbstractSelectionDTO> extends AbstractPipelineLogic<T, E> {

    public static final Logger LOGGERTRX7 = LoggerFactory.getLogger(Trx7SalesLogic.class);

    @Override
    public int getCount(Criteria tr7SalesCriteria) {

        return getSalesCount(tr7SalesCriteria.getParent(), tr7SalesCriteria.getSelectionDto());
    }

    @Override
    public DataResult<T> getData(Criteria tr7SalesCriteria) {
        return getSalesData(tr7SalesCriteria.getParent(), tr7SalesCriteria.getSelectionDto(), tr7SalesCriteria.getStart(), tr7SalesCriteria.getOffset());
    }

    @Override
    public List<Object> getMonthYear() {
        String sqlQuery = SQlUtil.getQuery("getMonthYear");
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        List<Object> defaultValue = new ArrayList<>();
        if (!resultList.isEmpty()) {
            Object[] value = (Object[]) resultList.get(0);
            for (Object value1 : value) {
                defaultValue.add(String.valueOf(value1));
            }
        }
        return defaultValue;
    }

    /**
     * Get month name from month number
     *
     * @param monthName
     * @return Jan-1........Dec-12
     */
    public static String getMonthName(int monthNo) {
        String month = StringUtils.EMPTY;
        try {
            DateFormatSymbols symbols = new DateFormatSymbols();
            String[] months = symbols.getShortMonths();
            month = months[monthNo - 1];
        } catch (Exception e) {
            LOGGERTRX7.error("Error in getMonthName :", e);
        }
        return month;
    }

    public int getSalesCount(Object tr7SalesParentId, SelectionDTO tr7Sselection) {
        int countRes = NumericConstants.ZERO;
        try {
            LOGGERTRX7.debug("Inside getSalesCount--{}", tr7Sselection.getProjectionMasterSid());
            String level = CommonConstant.CUSTOMER_LEVEL;
            String parentLevel = "";
            String brandMasterSid = "";
            String companyMasterSid = "";

            String tr7SalesSql = "";
            if (tr7SalesParentId instanceof AdjustmentDTO) {
                parentLevel = ((AdjustmentDTO) tr7SalesParentId).getLevelNames();
                companyMasterSid = ((AdjustmentDTO) tr7SalesParentId).getCompSids();
                brandMasterSid = ((AdjustmentDTO) tr7SalesParentId).getBranditemmasterSid();

                if (parentLevel.equals(CommonConstant.CUSTOMER_LEVEL)) {
                    companyMasterSid = ((AdjustmentDTO) tr7SalesParentId).getCompSids();
                    level = CommonConstant.BRAND_LEVEL;
                } else if (parentLevel.equals(CommonConstant.BRAND_LEVEL)) {
                    companyMasterSid = ((AdjustmentDTO) tr7SalesParentId).getCompSids();
                    brandMasterSid = ((AdjustmentDTO) tr7SalesParentId).getBranditemmasterSid();
                    level = "ITEM";
                }

            }
            level = getLevel(tr7Sselection, level);

            if (tr7Sselection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
                tr7SalesSql = SQlUtil.getQuery("trx7_getSalesBrandCount");
            } else {
                tr7SalesSql = SQlUtil.getQuery("trx7_getSalesBrandCountEdit");
            }
            tr7SalesSql = tr7SalesSql.replace("[$VIEW]", level);

            tr7SalesSql = tr7SalesSql.replace("[PROJECTION_MASTER_SID]", String.valueOf(tr7Sselection.getProjectionMasterSid()));
            LOGGERTRX7.debug("brandMasterSid----{}", brandMasterSid);
            tr7SalesSql = tr7SalesSql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);
            if ("ITEM".equals(level)) {
                if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(tr7Sselection.getSaleslevelFilterValue()))) {
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
                } else {
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
                }
            }
            if (CommonConstant.BRAND_LEVEL.equals(level)) {
                if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(tr7Sselection.getSaleslevelFilterValue()))) {
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
                } else {
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                    tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
                }

            } else {
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
            }
            tr7SalesSql = CommonLogic.replaceTableNames(tr7SalesSql, tr7Sselection.getSessionDTO().getCurrentTableNames());
            LOGGERTRX7.debug("sql----{}", tr7SalesSql);
            List<Integer> result = QueryUtils.executeSelect(tr7SalesSql);
            countRes = (result != null && !result.isEmpty()) ? result.get(0) : 0;
            LOGGERTRX7.debug("Exit getSalesCount--{}", countRes);
        } catch (Exception ex) {
            LOGGERTRX7.error("Error in getSalesCount :", ex);
        }
        return countRes;
    }

    public DataResult<T> getSalesData(Object salesParentId, SelectionDTO tr7Selection ,int start, int offset) {
        LOGGERTRX7.debug("Inside getSalesData--{}", tr7Selection.getProjectionMasterSid());
        String tr7SalesSql = "";
        String level = CommonConstant.CUSTOMER_LEVEL;
        String parentLevel = "";
        String brandMasterSid = "0";
        String companyMasterSid = "0";

        if (salesParentId instanceof AdjustmentDTO) {

            parentLevel = ((AdjustmentDTO) salesParentId).getLevelNames();
            companyMasterSid = ((AdjustmentDTO) salesParentId).getCompSids();
            brandMasterSid = ((AdjustmentDTO) salesParentId).getBranditemmasterSid();

            if (parentLevel.equals(CommonConstant.CUSTOMER_LEVEL)) {
                companyMasterSid = ((AdjustmentDTO) salesParentId).getCompSids();
                level = CommonConstant.BRAND_LEVEL;
            } else if (parentLevel.equals(CommonConstant.BRAND_LEVEL)) {
                companyMasterSid = ((AdjustmentDTO) salesParentId).getCompSids();
                brandMasterSid = ((AdjustmentDTO) salesParentId).getBranditemmasterSid();
                level = "ITEM";
            }

        }
        level = getLevel(tr7Selection, level);

        tr7SalesSql = getSql(tr7Selection);
        tr7SalesSql = tr7SalesSql.replace("[$VIEW]", level);
        tr7SalesSql = tr7SalesSql.replace("[PROJECTION_MASTER_SID]", String.valueOf(tr7Selection.getProjectionMasterSid()));
        tr7SalesSql = tr7SalesSql.replace("[PRODUCT_MASTER_SID]", brandMasterSid);

        tr7SalesSql = tr7SalesSql.replace("[LEVEL_FILTER]", String.valueOf(tr7Selection.getSaleslevelFilterValue()));
        tr7SalesSql = tr7SalesSql.replace("[COM_MASTER_SID]", companyMasterSid);
        tr7SalesSql = tr7SalesSql.replace("[BRA_MASTER_SID]", brandMasterSid);

        if ("ITEM".equals(level)) {
            if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(tr7Selection.getSaleslevelFilterValue()))) {
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
            } else {
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "AND BM.BRAND_MASTER_SID  = " + brandMasterSid + "");
            }
        }
        if (CommonConstant.BRAND_LEVEL.equals(level)) {
            if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(tr7Selection.getSaleslevelFilterValue()))) {
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, "");
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
            } else {
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, CommonConstant.AND_APSCOMPANY_MASTER_SID + companyMasterSid + "");
                tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
            }

        } else {
            tr7SalesSql = tr7SalesSql.replace(CommonConstant.COMPANY_MASTER_SID, "");
            tr7SalesSql = tr7SalesSql.replace(CommonConstant.BRAND_MASTER_SID, "");
        }

        tr7SalesSql += " ORDER BY PRODUCT_ID OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        tr7SalesSql = CommonLogic.replaceTableNames(tr7SalesSql, tr7Selection.getSessionDTO().getCurrentTableNames());
        List<Object[]> result = QueryUtils.executeSelect(tr7SalesSql);
        LOGGERTRX7.debug("sql=============={}", tr7SalesSql);
        DataResult<T> resultList = customizier(ARMUtils.getInstance().getTrx7SalesVariables(), result);
        LOGGERTRX7.debug("Exit getSalesData--{}", result.size());
        return resultList;
    }

    private String getLevel(SelectionDTO tr7Selection, String level) {
        String retLevel = level;
        if (CommonConstant.PRODUCT_LEVEL.equalsIgnoreCase(String.valueOf(tr7Selection.getSaleslevelFilterValue()))) {
            retLevel = "ITEM";
        } else if (CommonConstant.BRAND_LEVEL_FILTER.equalsIgnoreCase(String.valueOf(tr7Selection.getSaleslevelFilterValue()))) {
            retLevel = CommonConstant.BRAND_LEVEL;
        }
        return retLevel;
    }

    private String getSql(SelectionDTO selection) {
        String salesSql;
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            salesSql = SQlUtil.getQuery("trx7_getSalesBrand");

        } else {
            salesSql = SQlUtil.getQuery("trx7_getSalesBrandEdit");
        }
        return salesSql;
    }

    public boolean updatePriceOverride(List input) {
        LOGGERTRX7.debug("Inside update price override");
        try {
            QueryUtils.itemUpdate(input, "trx7_PA_sales_updatePriceOverride");
        } catch (Exception e) {
            LOGGERTRX7.error("Error in updatePriceOverride :", e);
            return false;
        }
        return true;

    }

    public void getSalesResults(Object[] orderedArgs) {
        QueryUtil.callProcedure("PRC_ARM_DISTRIBUTION_FEE_SALES", orderedArgs);
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO tr7Selection) {
        LOGGERTRX7.debug("Inside of getExcelResultList --");
        String query;
        boolean isView = tr7Selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            query = SQlUtil.getQuery("trx7_getPASalesExcelQueryView");
        } else {
            query = SQlUtil.getQuery("trx7_getPASalesExcelQuery");
        }
        query = query.replace("@PROJECTIONMASTERSID", String.valueOf(tr7Selection.getProjectionMasterSid()));
        query = query.replace("@USERID", String.valueOf(tr7Selection.getUserId()));
        query = query.replace("@SESSIONID", String.valueOf(tr7Selection.getSessionId()));
        LOGGERTRX7.debug("query --{}", query);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(CommonLogic.replaceTableNames(query, tr7Selection.getSessionDTO().getCurrentTableNames()));
        LOGGERTRX7.debug("Exit of getExcelResultList --{}", list.size());
        return list;
    }
}
