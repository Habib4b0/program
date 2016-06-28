/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.utils;

import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.util.Constants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.HEAD;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class HeaderUtils {
     /**
     * Dataselection results visible columns
     */
    public static final Object[] DATA_SELECTION_RESULTS_COLUMNS = new Object[]{"reportName", "description", "company", "marketType", "deductionType","deductionSubType" ,"customerGroup"
                                                                                ,"contractName","productGroup","fromPeriod","toPeriod","productName","productIdentifier"
                                                                                ,"createdDate","modifiedDate","createdBy","modifiedBy"};
    /**
     * Dataselection results column headers
     */
    public static final String[] DATA_SELECTION_RESULTS_HEADERS = new String[]{"Name", "Description", "Company", "Market Type", "Disc/Acct Type","Disc/Acct Sub-Type" ,"Customer Group"
                                                                                ,"Contract Name","Product Group","From Period","To Period","Product Name","Product Identifier"
                                                                                ,"Created Date","Modified Date","Created By","Modified By"};
    

    public static final Object[]AVAILABLE_SELECTED_CUSTOMERS_COLUMNS=new Object[]{Constants.CONTRACT_NO,"contractName","customerNo","customerName","customerAlias"};

    public static final String[]AVAILABLE_SELECTED_CUSTOMERS_HEADERS=new String[]{"Contract No.","Contract Name","Customer No","Customer Name","Customer Alias"};
    
    
    public static final Object[]AVAILABLE_SELECTED_PRODUCTS_COLUMNS=new Object[]{"sub_ledgerCode","productName","brandName","costCenter"};
    public static final String[]AVAILABLE_SELECTED_PRODUCTS_HEADERS=new String[]{"Sub-ledger Code","Product Name","Brand Name","Cost Center"};
    
    
    /**
     * Visible columns for public/private view lookup tables
     */
    public static final Object[] VIEW_LOOKUP_COLUMNS = new Object[]{"viewName", "reportName", "description", "company", "marketType", "deductionType","deductionSubType", "customerGroup", "contract", 
                                                                    "productGroup", "product", "productIdentifier", "fromPeriod", "toPeriod", "createdDate", "modifiedDate", "createdBy"};
    /**
     * Column headers for public/private view lookup tables
     */
    public static final String[] VIEW_LOOKUP_HEADERS = new String[]{"View Name", "Report Name", "Description", "Company", "Market Type", "Deduction Type","Deduction Sub Type", "Customer Group", "Contract", 
                                                                    "Product Group", "Product", "Product Identifier", "From Period", "To Period", "Created Date", "Modified Date", "Created By"};

   /**
     * Visible columns for public/private view lookup tables
     */
    public static final Object[] ACTUAL_VARIANCE_COLUMNS = new Object[]{"ndcNo", "ndcName", "ndcDescription", "brand", Constants.CONTRACT_NO, "contractName", "customerNo", "customerName","amount", 

                                                                    "accrualPeriod", "accrualPeriodStartDate", "accrualPeriodEndDate", "glDate", "settlementNo", "invoiceNo", "dispensedDate", "uploadDate"};
    /**
     * Column headers for public/private view lookup tables
     */
    public static final String[] ACTUAL_VARIANCE_HEADERS = new String[]{"Ndc #", "Ndc Name", "NDC Desc", "Brand", "Contract#", "Contract Name", "Customer#", "Customer Name","Amount", 
                                                                    "Accrual Period", "Accrual Period Start Date", "Accrual Period End Date", "GL Date", "Settlement#", "Invoice#", "Dispensed Date", "Upload Date"};

    static Map<String,String> headerMap=new HashMap<String, String>();
    static Map<String,String> brHeaderMap=new HashMap<String, String>();
    public static CustomTableHeaderDTO getBalanceReportLeftTableColumns(CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn("check.0", " ", Boolean.class);
        tableHeaderDTO.addSingleColumn("customer.1", "Customer", String.class);
        tableHeaderDTO.addDoubleColumn("grouped", " ");
        tableHeaderDTO.addDoubleHeaderMap("grouped", new Object[]{"check.0", "customer.1"});
        fullHeader.addSingleColumn("customer.0", "Customer", String.class);
        fullHeader.addDoubleColumn("grouped", " ");
        fullHeader.addDoubleHeaderMap("grouped", new Object[]{"customer.0"});
        return tableHeaderDTO;
    }

    public static CustomTableHeaderDTO getBalanceReportRightTableColumns(BalanceReportDTO balanceReportDTO, CustomTableHeaderDTO fullHeader,List<String> doubleHeaderlist,List<String>selectedHeaders) {
        CustomTableHeaderDTO headerDTO=new CustomTableHeaderDTO();
        String timeperiodFrom=balanceReportDTO.getTimePeriodFrom();
        String timeperiodTo=balanceReportDTO.getTimePeriodTo();
         Map<String,String> listMap=new HashMap<String,String>();
        int startPeriod=0;
        int endPeriod=0;
        int start = 2;
        if(!doubleHeaderlist.isEmpty()){
            startPeriod=doubleHeaderlist.indexOf(timeperiodFrom);
            endPeriod=doubleHeaderlist.indexOf(timeperiodTo);
            for (int i = startPeriod; i <= endPeriod; i++) {
                List<Object>dmapList=new ArrayList<Object>();
                List<Object>dmapList1=new ArrayList<Object>();
                for(int j=0;j<selectedHeaders.size();j++){
                    String temp = selectedHeaders.get(j)+doubleHeaderlist.get(i)+ "." + start;
                    String temp1 = selectedHeaders.get(j)+doubleHeaderlist.get(i)+ "." + (start-1);
                    start++;
                   listMap.put(selectedHeaders.get(j)+doubleHeaderlist.get(i), temp1);
                   headerDTO.addSingleColumn(temp, selectedHeaders.get(j), String.class);
                   fullHeader.addSingleColumn(temp1, selectedHeaders.get(j), String.class);
                   dmapList.add(temp);
                   dmapList1.add(temp1);
                }
                headerDTO.addDoubleColumn(doubleHeaderlist.get(i), doubleHeaderlist.get(i));
                headerDTO.addDoubleHeaderMap(doubleHeaderlist.get(i), dmapList.toArray());
                fullHeader.addDoubleColumn(doubleHeaderlist.get(i), doubleHeaderlist.get(i));
                fullHeader.addDoubleHeaderMap(doubleHeaderlist.get(i), dmapList1.toArray());
            }
            setBrHeaderMap(listMap);
        }
        return headerDTO;
    }
    public static List<String> getCommonColumnHeader(BalanceReportDTO brdto, int year, int period) {
        String frequency=brdto.getFrequency();
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if(com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY.getConstant().equals(frequency)){
             commonColumn = StringUtils.EMPTY + year;
             commonHeader = StringUtils.EMPTY + year;
        }else if(com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUALLY.getConstant().equals(frequency)){
            commonColumn = "s" + period + StringUtils.EMPTY + year;
            commonHeader = "S" + period + " - "  + year;
        }else if(com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY.getConstant().equals(frequency)){
            commonColumn = "q" + period + StringUtils.EMPTY + year;
            commonHeader = "Q" + period + " - "  + year;
        }else if(com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHLY.getConstant().equals(frequency)){
            String monthName = DataSelectionUtil.getMonthForInt(period - 1);
            commonColumn = monthName + year;
            commonHeader = monthName + " - "  + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }
    public static CustomTableHeaderDTO getHistoryRightTableColumns(BalanceReportDTO brdto,String selectedPeriod, CustomTableHeaderDTO fullHeader,List<String>selectedHeaders,Date asOfDate) {
        CustomTableHeaderDTO headerDTO=new CustomTableHeaderDTO();
        Map<String,String> listMap=new HashMap<String,String>();
          int start = 2;

                List<Object>dmapList=new ArrayList<Object>();
                List<Object>dmapList1=new ArrayList<Object>();
                if(asOfDate!=null){
                    selectedHeaders=loadHistoryPeriods(selectedHeaders);
                }
                for(int j=0;j<selectedHeaders.size();j++){
                    String temp = selectedHeaders.get(j)+selectedPeriod+ "." + start;
                    String temp1 = selectedHeaders.get(j)+selectedPeriod+ "." +( start-1);
                    start++;
                    if (selectedHeaders.get(j).equals("Variance Deductions")) {
                        brdto.setHypertLink(temp);
                  }
                    listMap.put(selectedHeaders.get(j)+selectedPeriod, temp);
                    
                   headerDTO.addSingleColumn(temp, selectedHeaders.get(j), String.class);
                   fullHeader.addSingleColumn(temp1, selectedHeaders.get(j), String.class);
                   dmapList.add(temp);
                   dmapList1.add(temp1);
                }
                setHeaderMap(listMap);
                headerDTO.addDoubleColumn(selectedPeriod, selectedPeriod);
                headerDTO.addDoubleHeaderMap(selectedPeriod, dmapList.toArray());
                fullHeader.addDoubleColumn(selectedPeriod, selectedPeriod);
                fullHeader.addDoubleHeaderMap(selectedPeriod, dmapList1.toArray());

        
        return headerDTO;
    }

    public static List<String> loadHistoryPeriods(List<String> selectedHeaders) {
        List<String> historyColumns=new ArrayList<String>();
        for (String historyColumn : selectedHeaders) {
            String temp="Current "+historyColumn;
            String priorTemp="Prior "+historyColumn;
            String varianceTemp="Variance "+historyColumn;
            historyColumns.add(temp);
            historyColumns.add(priorTemp);
            historyColumns.add(varianceTemp);
        }
       return historyColumns;
    }
    public static CustomTableHeaderDTO getHistoryTabLeftTableColumns(CustomTableHeaderDTO fullHeader) {
        CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();

        tableHeaderDTO.addSingleColumn("customer.0", "Customer", String.class);
        tableHeaderDTO.addDoubleColumn("grouped", " ");
        tableHeaderDTO.addDoubleHeaderMap("grouped", new Object[]{"customer.0"});
        fullHeader.addSingleColumn("customer.0", "Customer", String.class);
        fullHeader.addDoubleColumn("grouped", " ");
        fullHeader.addDoubleHeaderMap("grouped", new Object[]{"customer"});
        return tableHeaderDTO;
        
    }
   public static final Object[]LIBILITY_SUMMARY_COLUMNS=new Object[]{"year","actuals","accruals","remainingEstimate"};
    public static final String[]LIBILITY_SUMMARY_HEADERS=new String[]{"Year","Actuals","Accruals","Remaining Estimate"};

    public static Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public static void setHeaderMap(Map<String, String> headerMap) {
        HeaderUtils.headerMap = headerMap;
    }

    public static Map<String, String> getBrHeaderMap() {
        return brHeaderMap;
    }

    public static void setBrHeaderMap(Map<String, String> brHeaderMap) {
        HeaderUtils.brHeaderMap = brHeaderMap;
    }
    
    
    
}
