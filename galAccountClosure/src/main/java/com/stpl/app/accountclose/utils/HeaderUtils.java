
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.*;
import static com.stpl.app.accountclose.utils.HeaderUtils.getBaseRateSummaryColumns;
import java.text.DateFormatSymbols;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class HeaderUtils {

    private static final Logger LOGGER = Logger.getLogger(HeaderUtils.class);
    /**
     * The Constant ATTACHMENT_HEADER.
     */
    public static final String[] ATTACHMENT_HEADER = new String[]{"Document Name", "Date Added", "User Name"};
    /**
     * The Constant ATTACHMENT_COLUMNS.
     */
    public static final Object[] ATTACHMENT_COLUMNS = new Object[]{"documentName", "dateAdded", "userName"};
    /**
     * Customer Group Name.
     */
    public static final Object CUS_VISIBLE_COLUMN[] = new Object[]{
        "customerGroupName", "customerGroupNo", "company", "segmentGroup", "segment"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public static final String CUS_COLUMN_HEADER[] = new String[]{
        "Customer Group Name", "Customer Group No", "Company", "Segment Group", "Segment"};

    /**
     * Product Group Name.
     */
    public static final Object PROD_VISIBLE_COLUMN[] = new Object[]{
        "productGroupName", "productGroupNo", "company", "segmentGroup", "segment"};
    /**
     * The Constant PROD_COLUMN_HEADER.
     */
    public static final String PROD_COLUMN_HEADER[] = new String[]{
        "Product Group Name", "Product Group No", "Company", "Segment Group", "Segment"};

    public static final Object TREE_COLUMNS[] = new Object[]{
        "checkRecord", "id", "currRate", "penRate"};
    public static final String TREE_HEADERS[] = new String[]{
        " ", "Customer", "Current Rate", "Pending Rate"};
    /**
     * Customer Group Name.
     */
    public static final Object CUSTOMER_LOOKUP_VISIBLE_COLUMN[] = new Object[]{
        "customerGroupName", "customerGroupNo", "customerGroupDesc"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public static final String CUSTOMER_LOOKUP_COLUMN_HEADER[] = new String[]{
        "Customer Group Name", "Customer Group#", "Customer Group Desc."};

    /**
     * Product Group Name.
     */
    public static final Object PRODUCT_LOOKUP_VISIBLE_COLUMN[] = new Object[]{
        "productGroupName", "productGroupNo", "productGroupDesc", "company"};
    /**
     * The Constant PROD_COLUMN_HEADER.
     */
    public static final String PRODUCT_LOOKUP_COLUMN_HEADER[] = new String[]{
        "Product Group Name", "Product Group#", "Product Group Desc.", "Company"};
    /**
     * Customer Group Name.
     */
    public static final Object BR_CUS_VISIBLE_COLUMN[] = new Object[]{
        "customerGroupName", "customerGroupNo", "company", "segmentGroup", "segment"};
    /**
     * The Constant CUS_COLUMN_HEADER.
     */
    public static final String BR_CUS_COLUMN_HEADER[] = new String[]{
        "Customer Group Name", "Customer Group#", "Company", "Segment Group", "Segment"};

    /**
     * Product Group Name.
     */
    public static final Object BR_PROD_VISIBLE_COLUMN[] = new Object[]{
        "productGroupName", "productGroupNo", "company", "segmentGroup", "segment"};
    ;
    /**
     * The Constant PROD_COLUMN_HEADER.
     */
    public static final String BR_PROD_COLUMN_HEADER[] = new String[]{
        "Product Group Name", "Product Group#", "Company", "Segment Group", "Segment"};

    /**
     *
     * @param frequency
     * @param frequencyRange
     * @param isExcelExport
     * @return
     */
    public static CustomTableHeaderDTO getBaseRateSummaryTableColumns(String frequency, int frequencyRange, boolean isExcelExport) {
        LOGGER.info("----Inside getBaseRateSummaryTableColumns() -----");
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        return getBaseRateSummaryColumns(tableHeaderDTO, frequency, frequencyRange, isExcelExport);
    }

    /**
     *
     * @param tableHeaderDTO
     * @param frequency
     * @param frequencyRange
     * @param isExcelExport
     * @return
     */
    public static CustomTableHeaderDTO getBaseRateSummaryColumns(CustomTableHeaderDTO tableHeaderDTO, String frequency, int frequencyRange, boolean isExcelExport) {

        return tableHeaderDTO;
    }

    /**
     *
     * @param num
     * @return
     */
    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num == 12) {
            num = 0;
        }
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static CustomTableHeaderDTO getTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("checkRecord", StringUtils.EMPTY, Boolean.class);
        tableHeaderDTO.addSingleColumn("id", "Customer", String.class);
        tableHeaderDTO.addSingleColumn("currRate", "Current Rate", String.class);
        tableHeaderDTO.addSingleColumn("penRate", "Pending Rate", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getTableColumnsAvailable(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("checkRecord", StringUtils.EMPTY, Boolean.class);
        tableHeaderDTO.addSingleColumn("id", "Customer", String.class);
        tableHeaderDTO.addSingleColumn("currRate", "Current Rate", String.class);
        tableHeaderDTO.addSingleColumn("penRate", "Pending Rate", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getTableColumnsLeft(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("checkRecord", StringUtils.EMPTY, Boolean.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getTableColumnsForSuggestedAdj(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("id", "Customer", String.class);
        tableHeaderDTO.addSingleColumn("currRate", "Active Adj", String.class);
        tableHeaderDTO.addSingleColumn("penRate", "Pending Adj", String.class);
        tableHeaderDTO.addSingleColumn("conAdj", "Reconciled", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSummTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("checkRecord", StringUtils.EMPTY, Boolean.class);
        tableHeaderDTO.addSingleColumn("customerName", "Customer/Product", String.class);
        tableHeaderDTO.addSingleColumn("currentRate", "Current Rate", String.class);
        tableHeaderDTO.addSingleColumn("suggestedRate", "Suggested Rate", String.class);
        tableHeaderDTO.addSingleColumn("accrualRate", "Accrual Rate", String.class);
        tableHeaderDTO.addSingleColumn("startDate", "Start Date", String.class);
        tableHeaderDTO.addSingleColumn("endDate", "End Date", String.class);
        tableHeaderDTO.addSingleColumn("alternateContract", "Alternate Contract", String.class);
        tableHeaderDTO.addSingleColumn("alternateProduct", "Alternate Product", String.class);
        tableHeaderDTO.addSingleColumn("methodology", "Methodology", String.class);
        tableHeaderDTO.addSingleColumn("reasonCode", "Reason Code", String.class);
        tableHeaderDTO.addSingleColumn("notes", "Notes", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSummHistColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("customerName", "Customer/Product", String.class);
        tableHeaderDTO.addSingleColumn("currentRate", "Current Rate", String.class);
        tableHeaderDTO.addSingleColumn("suggestedRate", "Suggested Rate", String.class);
        tableHeaderDTO.addSingleColumn("accrualRate", "Accrual Rate", String.class);
        tableHeaderDTO.addSingleColumn("startDate", "Start Date", String.class);
        tableHeaderDTO.addSingleColumn("endDate", "End Date", String.class);
        tableHeaderDTO.addSingleColumn("alternateContract", "Alternate Contract", String.class);
        tableHeaderDTO.addSingleColumn("alternateProduct", "Alternate Product", String.class);
        tableHeaderDTO.addSingleColumn("methodology", "Methodology", String.class);
        tableHeaderDTO.addSingleColumn("reasonCode", "Reason Code", String.class);
        tableHeaderDTO.addSingleColumn("notes", "Notes", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getSummaryHistoryColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("customerName", "Customer/Product", String.class);
        tableHeaderDTO.addSingleColumn("startDate", "Start Date", String.class);
        tableHeaderDTO.addSingleColumn("endDate", "End Date", String.class);
        tableHeaderDTO.addSingleColumn("accrualRate", "Accrual Rate", String.class);
        tableHeaderDTO.addSingleColumn("suggestedRate", "Suggested Rate", String.class);
        tableHeaderDTO.addSingleColumn("currentRate", "Current Rate", String.class);
        tableHeaderDTO.addSingleColumn("alternateContract", "Alternate Contract", String.class);
        tableHeaderDTO.addSingleColumn("alternateProduct", "Alternate Product", String.class);
        tableHeaderDTO.addSingleColumn("methodology", "Methodology", String.class);
        tableHeaderDTO.addSingleColumn("reasonCode", "Reason Code", String.class);
        tableHeaderDTO.addSingleColumn("notes", "Notes", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getFixedDollarCustProdTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("checkRecord", StringUtils.EMPTY, Boolean.class);
        tableHeaderDTO.addSingleColumn("id", "Customer/Product", String.class);
        tableHeaderDTO.addSingleColumn("activeAdj", "Active Adj", String.class);
        tableHeaderDTO.addSingleColumn("pendingAdj", "Pending Adj", String.class);
        return tableHeaderDTO;
    }
    
    public static CustomTableHeaderDTO getGlrMappingSearchTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("check", StringUtils.EMPTY, Boolean.class);
        tableHeaderDTO.addSingleColumn("acctType", "Type", String.class);
        tableHeaderDTO.addSingleColumn("accountID", "Account ID", String.class);
        tableHeaderDTO.addSingleColumn("accountnumber", "Account Number", String.class);
        tableHeaderDTO.addSingleColumn("accountdesc", "Account Desc", String.class);
        tableHeaderDTO.addSingleColumn("remark", "Remark", String.class);
        tableHeaderDTO.addSingleColumn("subledgertype", "Sub-ledger Type", String.class);
        tableHeaderDTO.addSingleColumn("indicator", "Indicator", String.class);
        tableHeaderDTO.addSingleColumn("periodIndicator", "Period Indicator", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getGlrMappingIndexSearchTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("businessUnit", "Business Unit", String.class);
        tableHeaderDTO.addSingleColumn("programType", "Deduction Type", String.class);
        tableHeaderDTO.addSingleColumn("programsubType", "Deduction Sub-Type", String.class);
        tableHeaderDTO.addSingleColumn("frequency", "Frequency", String.class);
        tableHeaderDTO.addSingleColumn("createdBy", "Created By", String.class);
        tableHeaderDTO.addSingleColumn("createdDate", "Created Date", String.class);
        tableHeaderDTO.addSingleColumn("modifiedBy", "Modified By", String.class);
        tableHeaderDTO.addSingleColumn("modifiedDate", "Modified Date", String.class);
        tableHeaderDTO.addSingleColumn("source", "Source", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getGlrMapAvailableTableTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("check", StringUtils.EMPTY, Boolean.class);
        tableHeaderDTO.addSingleColumn("idvalue", "ID", String.class);
        tableHeaderDTO.addSingleColumn("name", "Name", String.class);
        tableHeaderDTO.addSingleColumn("version", "Version", String.class);
        tableHeaderDTO.addSingleColumn("category", "Category", String.class);
        tableHeaderDTO.addSingleColumn("createdDate", "CreatedDate", String.class);
        tableHeaderDTO.addSingleColumn("modifiedDate", "ModifiedDate", String.class);
        ;
        return tableHeaderDTO;
    }
        
        
        
        
    public static CustomTableHeaderDTO getLeftFixedDollarCustProdTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("checkRecord", StringUtils.EMPTY, Boolean.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getFixedDollarResultsTableColumns(CustomTableHeaderDTO fullHeaderDTO, String allocationMethod) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn("id", "Customer/Product", String.class);
        fullHeaderDTO.addSingleColumn("id", "Customer/Product", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getFixedDollarResultsTableRightColumns(CustomTableHeaderDTO fullHeaderDTO, String allocationMethod, String SalesBasis) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn("id", "Customer/Product", String.class);
        fullHeaderDTO.addSingleColumn("id", "Customer/Product", String.class);
        if ("Auto Reconcile".equals(allocationMethod)) {
            tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Accrual Period Sales", String.class);
            fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Accrual Period Sales", String.class);
        }
        if ("Sales".equals(allocationMethod) && SalesBasis != null && SalesBasis != StringUtils.EMPTY) {
            if (SalesBasis.contains("Contract")) {
                tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Contract Sales", String.class);
                fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Contract Sales", String.class);
            }
            if (SalesBasis.contains("Trade")) {
                tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range GTS", String.class);
                fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range GTS", String.class);
            }
        }
        if ("Rate Adjustment".equals(allocationMethod) && SalesBasis != null && SalesBasis != StringUtils.EMPTY) {

            if (SalesBasis.contains("Contract")) {

                tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Contract Sales", String.class);
                fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Contract Sales", String.class);
            }
            if (SalesBasis.contains("Trade")) {
                tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range GTS", String.class);
                fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range GTS", String.class);
            }
        }
        if ("Accruals".equals(allocationMethod)) {
            tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Accruals", String.class);
            fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Accruals", String.class);
        }
        if ("Actuals".equals(allocationMethod)) {
            tableHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Actuals", String.class);
            fullHeaderDTO.addSingleColumn("accrualPeriodSales", "Date Range Actuals", String.class);
        }
        tableHeaderDTO.addSingleColumn("accrualPeriodAccruals", "Accrual Period Accruals", String.class);

        tableHeaderDTO.addSingleColumn("autoAccruals", "Auto Accruals", String.class);
        tableHeaderDTO.addSingleColumn("manualAdjustment", "Manual Adjustment", String.class);
        tableHeaderDTO.addSingleColumn("paymentTrueUp", "Payment True-up", String.class);
        tableHeaderDTO.addSingleColumn("other", "Other", String.class);
        tableHeaderDTO.addSingleColumn("projectedDisc", "Projected Disc.", String.class);
        tableHeaderDTO.addSingleColumn("percentageAccrualToProj", "% Accrual to Proj.", String.class);
        tableHeaderDTO.addSingleColumn("percentageActualToProj", "% Actuals to Proj.", String.class);
        tableHeaderDTO.addSingleColumn("accrualPeriodActuals", "Accrual Period Actuals", String.class);
        tableHeaderDTO.addSingleColumn("variance", "Variance", String.class);
        tableHeaderDTO.addSingleColumn("suggestedAdj", "Suggested Adj", String.class);
        tableHeaderDTO.addSingleColumn("percentageRelease", "% Release", String.class);
        tableHeaderDTO.addSingleColumn("manualAmount", "Manual Amount", String.class);
        tableHeaderDTO.addSingleColumn("totalAdjustmentDollar", "Total Adj $", String.class);
        tableHeaderDTO.addSingleColumn("totalAdjustmentDollarPerDay", "Total Adj $ per day", String.class);
        tableHeaderDTO.addSingleColumn("reasonCode", "Reason Code", String.class);
        tableHeaderDTO.addSingleColumn("notes", "Notes", String.class);
        fullHeaderDTO.addSingleColumn("accrualPeriodAccruals", "Accrual Period Accruals", String.class);

        fullHeaderDTO.addSingleColumn("autoAccruals", "Auto Accruals", String.class);
        fullHeaderDTO.addSingleColumn("manualAdjustment", "Manual Adjustment", String.class);
        fullHeaderDTO.addSingleColumn("paymentTrueUp", "Payment True-up", String.class);
        fullHeaderDTO.addSingleColumn("other", "Other", String.class);
        fullHeaderDTO.addSingleColumn("projectedDisc", "Projected Disc.", String.class);
        fullHeaderDTO.addSingleColumn("percentageAccrualToProj", "% Accrual to Proj.", String.class);

        fullHeaderDTO.addSingleColumn("accrualPeriodActuals", "Accrual Period Actuals", String.class);
        fullHeaderDTO.addSingleColumn("variance", "Variance", String.class);
        fullHeaderDTO.addSingleColumn("suggestedAdj", "Suggested Adj", String.class);
        fullHeaderDTO.addSingleColumn("percentageRelease", "% Release", String.class);
        fullHeaderDTO.addSingleColumn("manualAmount", "Manual Amount", String.class);
        fullHeaderDTO.addSingleColumn("totalAdjustmentDollar", "Total Adj $", String.class);
        fullHeaderDTO.addSingleColumn("totalAdjustmentDollarPerDay", "Total Adj $ per day", String.class);
        fullHeaderDTO.addSingleColumn("reasonCode", "Reason Code", String.class);
        fullHeaderDTO.addSingleColumn("notes", "Notes", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getBRTableColumns(String from, String to, List<String> doubleHeaderlist, boolean isExcelExport, boolean actuals, List<String> key) {
        CustomTableHeaderDTO headerDTO = new CustomTableHeaderDTO();
        int startPeriod = 0;
        int endPeriod = 0;
        headerDTO.addSingleColumn("levelValue", StringUtils.EMPTY, String.class);
        if (!doubleHeaderlist.isEmpty()) {
            startPeriod = doubleHeaderlist.indexOf(from);
            endPeriod = doubleHeaderlist.indexOf(to);
            for (int i = startPeriod; i <= endPeriod; i++) {

                if (isExcelExport) {
                    headerDTO.addSingleColumn("firstColumn", StringUtils.EMPTY, String.class);
                    isExcelExport = false;
                }

                List<Object> doubleHeaderMap = new ArrayList<Object>();
                List<Object> tripleHeaderMap = new ArrayList<Object>();
                List<Object> actualDoubleHeaderMap = new ArrayList<Object>();
                List<Object> prjDoubleHeaderMap = new ArrayList<Object>();
                String commonColumn = doubleHeaderlist.get(i).replaceAll(" - ", StringUtils.EMPTY);

                String actualColumn = "Actuals";
                String projectionColumn = "Projections";
                String commonHeader = doubleHeaderlist.get(i).replaceAll("-", StringUtils.EMPTY);
                String header = StringUtils.EMPTY;

                Object singleColumn = commonColumn;

                if (actuals) {
                    header = ACTUALS.getConstant();
                } else {
                    if (!key.isEmpty()) {
                        header = ACTUALS.getConstant();
                        for (int j = 0; j < key.size(); j++) {
                            if (key.get(j).toString().contains(commonColumn)) {
                                header = PROJECTIONS.getConstant();
                            }
                        }
                    }

                }
                headerDTO.addSingleColumn(commonColumn, commonHeader, String.class);
                actualDoubleHeaderMap.add(commonColumn);
                prjDoubleHeaderMap.add(projectionColumn);
                actualDoubleHeaderMap.clear();
                prjDoubleHeaderMap.clear();
                tripleHeaderMap.clear();
            }
        }
        return headerDTO;
    }

    /**
     * Returns table header for transactional view in history table
     *
     * @param fullHeaderDTO
     * @return
     */
    public static CustomTableHeaderDTO getSummHistTableColumns(CustomTableHeaderDTO fullHeaderDTO) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
        tableHeaderDTO.addSingleColumn("customerNo", "Customer No", String.class);
        tableHeaderDTO.addSingleColumn("customerName", "Customer Name", String.class);
        tableHeaderDTO.addSingleColumn("contractNo", "Contract No", String.class);
        tableHeaderDTO.addSingleColumn("contractName", "Contract Name", String.class);
        tableHeaderDTO.addSingleColumn("therpeutic", "Therapeutic Class", String.class);
        tableHeaderDTO.addSingleColumn("brandId", "Brand ID", String.class);
        tableHeaderDTO.addSingleColumn("brandName", "Brand Name", String.class);
        tableHeaderDTO.addSingleColumn("itemNo", "Item No", String.class);
        tableHeaderDTO.addSingleColumn("itemName", "Item Name", String.class);
        tableHeaderDTO.addSingleColumn("startDate", "Start Date", String.class);
        tableHeaderDTO.addSingleColumn("endDate", "End Date", String.class);
        tableHeaderDTO.addSingleColumn("accrualRate", "Accrual Rate", String.class);
        tableHeaderDTO.addSingleColumn("suggRate", "Suggested Rate", String.class);
        tableHeaderDTO.addSingleColumn("reasonCode", "Reason Code", String.class);
        tableHeaderDTO.addSingleColumn("notes", "Notes", String.class);
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getBRCalcColumns(List<String> doubleHeaderlist) {
        CustomTableHeaderDTO headerDTO = new CustomTableHeaderDTO();
        if (!doubleHeaderlist.isEmpty()) {

            for (int i = 0; i < doubleHeaderlist.size(); i++) {
                String commonColumn = doubleHeaderlist.get(i).replaceAll(" - ", StringUtils.EMPTY);
                String commonHeader = doubleHeaderlist.get(i).replaceAll("-", StringUtils.EMPTY);
                String header = commonHeader.replaceAll("Actuals", StringUtils.EMPTY);

                Object singleColumn = commonColumn;
                headerDTO.addSingleColumn(commonColumn, commonHeader, String.class);

            }
        }
        return headerDTO;
    }
}
