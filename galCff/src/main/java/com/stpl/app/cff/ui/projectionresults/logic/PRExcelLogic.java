/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionresults.logic;

import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionVariance.dto.PVParameters;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.CUSTOM;
import static com.stpl.app.cff.util.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERCENT;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author jayaram
 */
public class PRExcelLogic {
    
    public static final Logger LOGGER = Logger.getLogger(PRExcelLogic.class);
    private ProjectionResultsDTO exFacProduct, exFacCustomer, demand, adjustedDemand, invWithSummary, invWithDetail,
                                 perExFacProduct, perExFacCustomer, perDemand, perAdjustedDemand, perInvWithSummary, 
                                 perInvWithDetail, contractSales, units, disDoll, disPer, rpu, netSales, cogs, netProfit;

    private final Map<String, List<ProjectionResultsDTO>> resultMap;
    private final Map<String, List<List<ProjectionResultsDTO>>> discountMap;
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final ProjectionSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final String ZERO = "0";
    private final int index = 1;
    private final int indexPivot = 5;
    private int frequencyDivision;
    private final int baseColumn_hierarchy_index = 1;
    private final int baseColumn_period_index = 3;
    private final int base_Column_Year = 3;
    private final int base_Column_frequency = 4;
    private final int baseColumn_year_index = 4;
    private final List<Object[]> procRawList_total = new ArrayList();
    private final List<Object[]> procRawList_detail = new ArrayList();
    private final List<Object[]> procRawList_discount = new ArrayList();
    private final List<Object[]> procRawList_detail_discount = new ArrayList();
    private boolean isTotal = false;
    private String levelFilterValue = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String viewValue = StringUtils.EMPTY;
    private static final String PRC_PROJ_RESULTS_TOTAL = "PRC_CFF_RESULTS";
    private static final String PRC_PROJ_RESULTS_TOTAL_DISCOUNT = "PRC_CFF_PROJECTION_RESULTS_DISCOUNT";
    private final List<Object[]> procRawList_total_discount = new ArrayList();
    private static String DASH = "-";
    List<Object> pivotDiscountList = new ArrayList<Object>();
    List<ProjectionResultsDTO> discountList = new ArrayList<ProjectionResultsDTO>();
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    List<Object> pivotTotalList = new ArrayList<Object>();
    List<Integer> pivotPriorProjIdList = new ArrayList();
    private final Map<String, String> customView_relationship_hierarchy = new HashMap();
    PVParameters parameterDto;
    private boolean isCustomView;
    private final int indexDetail = 7;
    String discountName= StringUtils.EMPTY;
    Map<String,Map<String,ProjectionResultsDTO>> pivotDiscountMap=new HashMap<String,Map<String,ProjectionResultsDTO>>();
    Map<String,Map<String,ProjectionResultsDTO>> totalpivotDiscountMap=new HashMap<String,Map<String,ProjectionResultsDTO>>();
  

    public PRExcelLogic(Map<String, List<ProjectionResultsDTO>> resultMap, ProjectionSelectionDTO selection,
            List<String> hierarchyKeys, List<String> tradingPartnerKeys, List<String> discountKeys, PVParameters parameterDto,Map<String, List<List<ProjectionResultsDTO>>> discountMap) {
        this.resultMap = resultMap;
        this.selection = selection;
        this.hierarchyKeys = hierarchyKeys;
        this.tradingPartnerKeys = tradingPartnerKeys;
        this.discountKeys = discountKeys;
        this.parameterDto = parameterDto;
        this.discountMap=discountMap;
       
    }

    /**
     * Checking the selected level is "Total" if it is then need to hit the
     * procedure PRC_PROJECTION_RESULTS and customize the value
     *
     * Checking the selected level is "Detail" if it is then need to hit the
     * procedure "" and customize the value
     *
     * Finally the results will be updated in resultMap Which initalized in
     * constructor
     */
    public void getPVData() {
        resultMap.clear();
        hierarchyKeys.clear();
        tradingPartnerKeys.clear();
        discountKeys.clear();
        isCustomView = selection.getView().equals(CUSTOM.getConstant());
        if (isCustomView) {
            customView_relationship_hierarchy.putAll(getGroup_customView(selection.getCustomId()));
        }
        frequencyDivision = selection.getFrequencyDivision();

        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
            
           // get the hierarchy and discount data from db
            getTotalRawData(Boolean.FALSE);
            
            //Total Level Customization
            calculateAndCustomize_period(procRawList_total, Boolean.TRUE);
            
             //Total Discount Level Customization
            calculate_Total_Discount();
            
            //Detail Level Customization
            calculateAndCustomize_period(procRawList_detail, Boolean.FALSE);

            customizeDiscount_period(procRawList_discount, isTotal);
        } else {
             getTotalRawData(Boolean.TRUE);

            //Total Level Customization for Total and Discount 
            total_Discount_Customize();
            customize_discount_pivot();
            calculateAndCustomize_variable(procRawList_total, procRawList_discount, Boolean.TRUE, Boolean.TRUE);

            //Detail Level Customization for Hierarchy and Discount
            calculateAndCustomize_variable(procRawList_detail, procRawList_detail_discount, Boolean.FALSE, Boolean.TRUE);
            
            if (DESCENDING.getConstant().equals(selection.getProjectionOrder())) {
                for (Map.Entry<String, List<ProjectionResultsDTO>> entry : resultMap.entrySet()) {
                    //Take the dto of 1st index (Level Name)
                    ProjectionResultsDTO dto = entry.getValue().remove(0);
                    //Reverse the period list
                    Collections.reverse(entry.getValue());
                    //Add the level Name in 1st Index
                    entry.getValue().add(0, dto);
        }
    }
        }
    }

   
    private void calculateAndCustomize_period(List<Object[]> rawList, boolean isTotal){
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = StringUtils.EMPTY;
            if (isTotal) {
                key = "Total";
            } else {
                key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
            }
            List<ProjectionResultsDTO> pvList = resultMap.get(key);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList(pvList, obj, index, isTotal);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList(pvList, obj, index, isTotal);
            }
        }
    }
    
    

    private void hierarchyAndTP_keys(Object[] obj, String key, List<ProjectionResultsDTO> pvList) {

        hierarchyKeys.add(key);
        resultMap.put(key, pvList);
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[2]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[2]))) {
            tradingPartnerKeys.add(key);
        }
    }

     private void addList(List<ProjectionResultsDTO> pvList, final Object[] obj, int index, boolean isTotal) {
        try {
            if (isTotal) {
                ProjectionResultsDTO total = new ProjectionResultsDTO();
                total.setGroup("CFF Total");
                pvList.add(total);

                //Ex-Factory-Product ok
                exFacProduct = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, index, exFacProduct, selection, AMOUNT, isTotal,false);
                pvList.add(exFacProduct);

                // Ex-Factory-Customer -ok
                exFacCustomer = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, 55, exFacCustomer, selection, AMOUNT, isTotal,false);
                pvList.add(exFacCustomer);

                //Demand-ok
                demand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.DEMAND.toString(), Constants.VALUE, obj, 29, demand, selection, AMOUNT, isTotal,false);
                pvList.add(demand);

                //Adjusted Demand-ok
                adjustedDemand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, 53, adjustedDemand, selection, AMOUNT, isTotal,false);
                pvList.add(adjustedDemand);

                //Inventory Withdraw Summary
                invWithSummary = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VALUE, obj, 31, invWithSummary, selection, AMOUNT, isTotal,false);
                pvList.add(invWithSummary);

                //Inventory Withdraw Detail
                invWithDetail = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VALUE, obj, 57, invWithDetail, selection, AMOUNT, isTotal,false);
                pvList.add(invWithDetail);

                //% of Ex-Factory Product
                perExFacProduct = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, 13, perExFacProduct, selection, AMOUNT, isTotal,false);
                pvList.add(perExFacProduct);

                //% of Ex-Factory Customer-ok
                perExFacCustomer = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, 61, perExFacCustomer, selection, AMOUNT, isTotal,false);
                pvList.add(perExFacCustomer);

                //% of Demand -ok
                perDemand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_DEMAND.toString(), Constants.VALUE, obj, 33, perDemand, selection, AMOUNT, isTotal,false);
                pvList.add(perDemand);

                //% of Adjusted Demand -ok
                perAdjustedDemand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, 59, perAdjustedDemand, selection, AMOUNT, isTotal,false);
                pvList.add(perAdjustedDemand);

                //% of inventory Withdraw Summary-ok
                perInvWithSummary = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VALUE, obj, 35, perInvWithSummary, selection, AMOUNT, isTotal,false);
                pvList.add(perInvWithSummary);

                perInvWithDetail = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VALUE, obj, 63, perInvWithDetail, selection, AMOUNT, isTotal,false);
                pvList.add(perInvWithDetail);

            } else {
                ProjectionResultsDTO detail = new ProjectionResultsDTO();
                //Group Column projSelDTO

                String groupName;
                if (isCustomView) {
                    groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                    groupName = groupName == null ? "" : groupName;
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[1].toString(), selection.getHierarchyIndicator());
                   
                }

                detail.setGroup(groupName);
                pvList.add(detail);
            }

            //Contract Sales @ WAC -ok
            contractSales = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VALUE, obj, isTotal ? 5 : 7, contractSales, selection, AMOUNT, isTotal,true);
            pvList.add(contractSales);

            String salesOrUnits = selection.getSalesOrUnit();
            if (!salesOrUnits.equals("Sales")) {
                //Contract Units-ok
                units = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VALUE, obj, isTotal ? 7 : 9, units, selection, AMOUNT, isTotal,true);
                pvList.add(units);
            }

            //Discount $-ok
            String group = StringUtils.EMPTY;
            disDoll = new ProjectionResultsDTO();
            if (isTotal) {
                group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
            } else {
                group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
            }
            calculate(group, Constants.VALUE, obj, isTotal ? 9 : 11, disDoll, selection, AMOUNT, isTotal,true);
            pvList.add(disDoll);

            //Discount % -ok
            disPer = new ProjectionResultsDTO();
            group = StringUtils.EMPTY;
            if (isTotal) {
                group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
            } else {
                group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
            }
            calculate(group, Constants.VALUE, obj, isTotal ? 11 : 13, disPer, selection, AMOUNT, isTotal,true);
            pvList.add(disPer);

            //RPU-ok
            rpu = new ProjectionResultsDTO();
            if (isTotal) {
                group = Constants.PVVariables.TOTAL_RPU.toString();
            } else {
                group = Constants.PVVariables.TOTAL_RPU.toString();
            }
            calculate(group, Constants.VALUE, obj, isTotal ? 41 : 43, rpu, selection, AMOUNT, isTotal,true);
            pvList.add(rpu);

            //Net Sales -ok
            netSales = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VALUE, obj, isTotal ? 27 : 29, netSales, selection, AMOUNT, isTotal,true);
            pvList.add(netSales);

            //COGS -ok
            cogs = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_COGS.toString(), Constants.VALUE, obj, isTotal ? 43 : 45, cogs, selection, AMOUNT, isTotal,true);
            pvList.add(cogs);

            //Net Profit-ok
            netProfit = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VALUE, obj, isTotal ? 45 : 47, netProfit, selection, AMOUNT, isTotal,true);
            pvList.add(netProfit);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateList(List<ProjectionResultsDTO> pvList, Object[] obj, int index, boolean isTotal) {

        int listIndex = 1;
        if (isTotal) {
            //Ex-Factory-Product
            exFacProduct = pvList.get(listIndex++);
            //  exFacProduct = pvList.get(0);
            calculate(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, index, exFacProduct, selection, AMOUNT, isTotal,false);
          //  pvList.add(exFacProduct);

            // Ex-Factory-Customer
            exFacCustomer = pvList.get(listIndex++);
            //  exFacCustomer = pvList.get(1);
            calculate(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, 55, exFacCustomer, selection, AMOUNT, isTotal,false);
           // pvList.add(exFacCustomer);

            //Demand
            demand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.DEMAND.toString(), Constants.VALUE, obj, 29, demand, selection, AMOUNT, isTotal,false);
           // pvList.add(demand);

            //Adjusted Demand
            adjustedDemand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, 53, adjustedDemand, selection, AMOUNT, isTotal,false);
          //  pvList.add(adjustedDemand);

            //Inventory Withdraw Summary
            invWithSummary = pvList.get(listIndex++);
            calculate(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VALUE, obj, 31, invWithSummary, selection, AMOUNT, isTotal,false);
          //  pvList.add(invWithSummary);

            //Inventory Withdraw Detail
            invWithDetail = pvList.get(listIndex++);
            calculate(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VALUE, obj, 57, invWithDetail, selection, AMOUNT, isTotal,false);
          //  pvList.add(invWithDetail);

            //% of Ex-Factory Product
            perExFacProduct = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, 13, perExFacProduct, selection, AMOUNT, isTotal,false);
          //  pvList.add(perExFacProduct);

            //% of Ex-Factory Customer
            perExFacCustomer = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, 61, perExFacCustomer, selection, AMOUNT, isTotal,false);
           // pvList.add(perExFacCustomer);

            //% of Demand
            perDemand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_DEMAND.toString(), Constants.VALUE, obj, 33, perDemand, selection, AMOUNT, isTotal,false);
         //   pvList.add(perDemand);

            //% of Adjusted Demand
            perAdjustedDemand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, 59, perAdjustedDemand, selection, AMOUNT, isTotal,false);
           // pvList.add(perAdjustedDemand);

            //% of inventory Withdraw Summary
            perInvWithSummary = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VALUE, obj, 35, perInvWithSummary, selection, AMOUNT, isTotal,false);
            // pvList.add(perInvWithSummary);

            perInvWithDetail = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VALUE, obj, 63, perInvWithDetail, selection, AMOUNT, isTotal,false);
            // pvList.add(perInvWithDetail);

        }
        //  Contract Sales @ WAC
        contractSales = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VALUE, obj, isTotal ? 5 : 7, contractSales, selection, AMOUNT, isTotal,true);

        String salesOrUnits = selection.getSalesOrUnit();
        if (!salesOrUnits.equals("Sales")) {
            //Contract Units
            units = pvList.get(listIndex++);
            calculate(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VALUE, obj, isTotal ? 7 : 9, units, selection, AMOUNT, isTotal,true);
        }

        //Discount $
        String group = StringUtils.EMPTY;
        disDoll = pvList.get(listIndex++);
        if (isTotal) {
            group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
        } else {
            group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
        }
        calculate(group, Constants.VALUE, obj, isTotal ? 9 : 11, disDoll, selection, AMOUNT, isTotal,true);

        //Discount %
        disPer = pvList.get(listIndex++);
        if (isTotal) {
            group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
        } else {
            group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
        }
        calculate(group, Constants.VALUE, obj, isTotal ? 11 : 13, disPer, selection, AMOUNT, isTotal,true);

        //RPU
        rpu = pvList.get(listIndex++);
        if (isTotal) {
            group = Constants.PVVariables.TOTAL_RPU.toString();
        } else {
            group = Constants.PVVariables.TOTAL_RPU.toString();
        }
        calculate(group, Constants.VALUE, obj, isTotal ? 41 : 43, rpu, selection, AMOUNT, isTotal,true);

        //Net Sales 
        netSales = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VALUE, obj, isTotal ? 27 : 29, netSales, selection, AMOUNT, isTotal,true);

        //COGS
        cogs = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_COGS.toString(), Constants.VALUE, obj, isTotal ? 43 : 45, cogs, selection, AMOUNT, isTotal,true);

        //Net Profit
        netProfit = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VALUE, obj, isTotal ? 45 : 47, netProfit, selection, AMOUNT, isTotal,true);

    }

    private void calculate(String varaibleName, String varibaleCat, Object[] obj, int index, ProjectionResultsDTO pvDTO,ProjectionSelectionDTO selection, DecimalFormat format,boolean isTotal,boolean salesInclusionFlag) {

        String column = StringUtils.EMPTY;
        String value = StringUtils.EMPTY;
        String baseValue = StringUtils.EMPTY;
        pvDTO.setGroup(varaibleName);
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == 4) {
            if(isTotal){
            commonColumn = "q" + obj[base_Column_frequency] + StringUtils.EMPTY + obj[base_Column_Year];
            }else{
               commonColumn = "q" + obj[6] + StringUtils.EMPTY + obj[5]; 
            }
        } else if (frequencyDivision == 2) {
             if(isTotal){
            commonColumn = "s" + obj[base_Column_frequency] + StringUtils.EMPTY + obj[base_Column_Year];
            }else{
               commonColumn = "s" + obj[6] + StringUtils.EMPTY + obj[5]; 
            }
        } else if (frequencyDivision == 1) {
            if(isTotal){
            commonColumn = StringUtils.EMPTY + obj[base_Column_Year];
            }else{
               commonColumn = StringUtils.EMPTY + obj[5];
            }
        } else if (frequencyDivision == 12) {
            if(isTotal){
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[base_Column_frequency])) - 1);
            commonColumn = monthName.toLowerCase() + obj[3];
            }else{
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[6])) - 1);
                commonColumn = monthName.toLowerCase() + obj[5]; 
            }
        }

        //Actuals
        column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
        if (salesInclusionFlag) {
            if (obj[index] == null) {
                pvDTO.addStringProperties(column, StringUtils.EMPTY);
            } else {
                value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                if (varaibleName.startsWith("%")) {
                    baseValue = getFormattedValuePer(format, value);
                } else {
                    baseValue = getFormattedValue(format, value);
                }
                pvDTO.addStringProperties(column, baseValue);
            }
        } else {
            value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
            if (varaibleName.startsWith("%")) {
                baseValue = getFormattedValuePer(format, value);
            } else {
                baseValue = getFormattedValue(format, value);
            }
            pvDTO.addStringProperties(column, baseValue);

        }
        
        //Projection
        column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
        if(salesInclusionFlag){
           if(obj[index+1]==null){
           pvDTO.addStringProperties(column, StringUtils.EMPTY); 
        }else{
               value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1])));
        if (varaibleName.startsWith("%")) {
            baseValue = getFormattedValuePer(format, value);
        } else {
            baseValue = getFormattedValue(format, value);
        }
        pvDTO.addStringProperties(column, baseValue);
           }
        }else{
        value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1])));
        if (varaibleName.startsWith("%")) {
            baseValue = getFormattedValuePer(format, value);
        } else {
            baseValue = getFormattedValue(format, value);
        }
        pvDTO.addStringProperties(column, baseValue);
        }
        
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(NULL.getConstant())) {
            value = ZERO;
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public String isNull(String value) {
        if (value.contains(NULL.getConstant())) {
            value = ZERO;
        }
        return value;
    }

    public void getTotalRawData(boolean isPivot) {
        String frequency = selection.getFrequency();
        String detailFreq = StringUtils.EMPTY;
        // String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        String view = selection.getView().equals(CUSTOM.getConstant()) ? "A" : selection.getHierarchyIndicator();
        procRawList_total.clear();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
            detailFreq = "Q";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
            detailFreq = "S";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
            detailFreq = "M";
        } else {
            frequency = "ANNUAL";
            detailFreq = "A";
        }

        if (!isPivot) {
            //Total Level Procedure 
            procRawList_total.clear();
            Object[] orderedArgs = {selection.getProjectionId(), frequency, "ASSUMPTIONS"};
            List< Object[]> rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS_TOTAL, orderedArgs);
            procRawList_total.addAll(rawList);
            rawList.clear();
            
             //Total Discount Level Procedure  
            procRawList_total_discount.clear();
            Object[] orderedArgsTotalDiscount = {selection.getProjectionId(), frequency, "ASSUMPTIONS",null, null, null, null, "excel"};
            rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS_TOTAL_DISCOUNT, orderedArgsTotalDiscount);
            procRawList_total_discount.addAll(rawList);
            rawList.clear();

            //Detail Level Procedure
            procRawList_detail.clear();
            Object[] orderedArgsDetail = {selection.getProjectionId(), frequency, "ASSUMPTIONS", "period", view, null, selection.getFilterLevelNo(), selection.getCustomId(), null};
            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArgsDetail);
            procRawList_detail.addAll(rawList);
            rawList.clear();

            //Discount Level Procedure
            procRawList_discount.clear();
            Object[] orderedArgsDiscount = {selection.getProjectionId(), detailFreq, "ASSUMPTIONS", "period", "PROGRAM", null, view, null, selection.getFilterLevelNo(), selection.getCustomId(), null};
            rawList = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgsDiscount);
            procRawList_discount.addAll(rawList);
            rawList.clear();

        } else {

            String dateQuery = CommonLogic.getHistoryPeriodDate(selection.getFrequency(), selection.getHistoryNum());
            List dateList = HelperTableLocalServiceUtil.executeSelectQuery(dateQuery);
            String fromDate = StringUtils.EMPTY;
            if (dateList != null && dateList.size() > 0) {
                Object obj = dateList.get(0);
                fromDate = String.valueOf(obj);
            }

            //Total Level Procedure
            procRawList_total.clear();
            Object[] orderedArgs = {selection.getProjectionId(), frequency, "ASSUMPTIONS", "PIVOT", fromDate};
            List<Object[]> rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS_TOTAL, orderedArgs);
            procRawList_total.addAll(rawList);
            rawList.clear();

            //Detail Level Procedure
            procRawList_detail.clear();
            Object[] orderedArgsDetail = {selection.getProjectionId(), detailFreq, "ASSUMPTIONS", "PIVOT", view, null, selection.getFilterLevelNo(), selection.getCustomId(), fromDate};
            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArgsDetail);
            procRawList_detail.addAll(rawList);
            rawList.clear();

            //Total Discount Procedure
            procRawList_discount.clear();
            Object[] orderedArgsTotalDiscount = {selection.getProjectionId(), detailFreq, "ASSUMPTIONS", "PIVOT", fromDate, null, "PROGRAM", "excel"};
            rawList = CommonLogic.callProcedure("PRC_CFF_PROJECTION_RESULTS_DISCOUNT", orderedArgsTotalDiscount);
            procRawList_discount.addAll(rawList);
            rawList.clear();

            //Detail Discount Procedure
            procRawList_detail_discount.clear();
            Object[] orderedArgDetailDiscount = {selection.getProjectionId(), detailFreq, "ASSUMPTIONS", "PIVOT", "PROGRAM", null, view, null, selection.getFilterLevelNo(), selection.getCustomId(), fromDate};
            rawList = CommonLogic.callProcedure("PRC_CFF_DISCOUNT_EXCEL_EXPORT", orderedArgDetailDiscount);
            procRawList_detail_discount.addAll(rawList);
            rawList.clear();
        }

    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    void getTotalDiscount(ProjectionSelectionDTO projSelDTO) {
        pivotDiscountList.clear();
        String frequency = projSelDTO.getFrequency();
        String discountId = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<String>();
        pivotDiscountList = new ArrayList<Object>();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", projSelDTO.getSessionId(), projSelDTO.getUserId(), "1"};
        List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArg);
        pivotDiscountList.addAll(discountsList);

    }

    private Map<String, String> getGroup_customView(int customMasterSid) {
        Map<String, String> map = new HashMap();
        String query = SQlUtil.getQuery("FIND_CUSTOM_RELATIOSHIP_BUILDER");
        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", "" + customMasterSid);
        LOGGER.info("Query :" + query);
        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (!rawList.isEmpty()) {
            for (Iterator<Object[]> it = rawList.iterator(); it.hasNext();) {
                Object[] obj = it.next();
                map.put(obj[1] == null ? "" : obj[1].toString(), obj[0] == null ? "" : obj[0].toString());
            }
        }
        return map;
    }

     private void customizeDiscount_period(List<Object[]> rawList, boolean isTotal){
           calculateDiscount();
    }
    
    
    private void calculateDiscount() {

        String column = StringUtils.EMPTY;
        String value = StringUtils.EMPTY;
        String baseValue = StringUtils.EMPTY;
        ProjectionResultsDTO totalDiscDollar = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscPer = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPU = new ProjectionResultsDTO();
        String oldDiscount = StringUtils.EMPTY;
        String oldHierarchyNo = StringUtils.EMPTY;
        int count = procRawList_discount.size();
        String commonColumn = StringUtils.EMPTY;
        String newDiscount=StringUtils.EMPTY;
        List<ProjectionResultsDTO> discountDollarList = new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> discountperList = new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> totalRPUList = new ArrayList<ProjectionResultsDTO>();
        List<List<ProjectionResultsDTO>> finaldiscountlist = new ArrayList<List<ProjectionResultsDTO>>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_discount.get(i);
          if(i==0){
              oldHierarchyNo=String.valueOf(obj[1]);
          }
            String newHierarchyNo = String.valueOf(obj[1]);
            if (oldHierarchyNo.equals(newHierarchyNo)) {
            
                newDiscount=String.valueOf(obj[5]);
                if (oldDiscount.equals(newDiscount)) {
                  
                    if (frequencyDivision == 4) {
                        commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[3];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "s" + obj[4] + StringUtils.EMPTY + obj[3];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : 4])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : 3];
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[6]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[7]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[8]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[9]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[10]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[11]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                } else {
                 
                    if (i == 0) {
                        totalDiscDollar.setGroup(String.valueOf(obj[5]));
                        totalDiscPer.setGroup(String.valueOf(obj[5]));
                        totalRPU.setGroup(String.valueOf(obj[5]));
                        
                        if (frequencyDivision == 4) {
                            commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[3];
                        } else if (frequencyDivision == 2) {
                            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 12) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                        }

                       column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[6]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[7]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[8]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[9]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[10]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[11]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                        oldDiscount = newDiscount;
                    } else {
                        /*New discount means add at List */
                        discountDollarList.add(totalDiscDollar);
                        discountperList.add(totalDiscPer);
                        totalRPUList.add(totalRPU);

                        /*Empty the DTO */
                        totalDiscDollar = new ProjectionResultsDTO();
                        totalDiscPer = new ProjectionResultsDTO();
                        totalRPU = new ProjectionResultsDTO();

                        oldDiscount = newDiscount;
                        totalDiscDollar.setGroup(String.valueOf(obj[5]));
                        totalDiscPer.setGroup(String.valueOf(obj[5]));
                        totalRPU.setGroup(String.valueOf(obj[5]));

                        if (frequencyDivision == 4) {
                            commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[3];
                        } else if (frequencyDivision == 2) {
                            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 12) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                        }

                        column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[6]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[7]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[8]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[9]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[10]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[11]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                    }
                }

            } else {
                finaldiscountlist.add(discountDollarList);
                finaldiscountlist.add(discountperList);
                finaldiscountlist.add(totalRPUList);

                String key = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
                discountMap.put(key, finaldiscountlist);
                oldHierarchyNo = newHierarchyNo;
                finaldiscountlist = new ArrayList<List<ProjectionResultsDTO>>();
                discountDollarList = new ArrayList<ProjectionResultsDTO>();
                discountperList = new ArrayList<ProjectionResultsDTO>();
                totalRPUList = new ArrayList<ProjectionResultsDTO>();
                
                       /*Empty the DTO */
                        totalDiscDollar = new ProjectionResultsDTO();
                        totalDiscPer = new ProjectionResultsDTO();
                        totalRPU = new ProjectionResultsDTO();
                        
                        totalDiscDollar.setGroup(String.valueOf(obj[5]));
                        totalDiscPer.setGroup(String.valueOf(obj[5]));
                        totalRPU.setGroup(String.valueOf(obj[5]));
                        
                        newDiscount=String.valueOf(obj[5]);
                        if (frequencyDivision == 4) {
                            commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[3];
                        } else if (frequencyDivision == 2) {
                            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 12) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                        }

                       column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[6]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[7]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[8]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[9]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[10]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[11]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                        oldDiscount = newDiscount;           
            }
            if (i == count - 1) {
                discountDollarList.add(totalDiscDollar);
                discountperList.add(totalDiscPer);
                totalRPUList.add(totalRPU);
                finaldiscountlist.add(discountDollarList);
                finaldiscountlist.add(discountperList);
                finaldiscountlist.add(totalRPUList);
                String key = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
                discountMap.put(key, finaldiscountlist);
            }

        }
    }

    public String getFormattedValuePer(DecimalFormat FORMAT, String value) {
        if (value.contains("null")) {
            value = "0";
        }
        Double newValue = Double.valueOf(value);
        if (FORMAT.toPattern().contains("%")) {
            newValue = newValue / 100;
        }
        value = FORMAT.format(newValue);
        value = value.replace("$", "");
        value = value + "%";

        return value;
    }

    private void calculateAndCustomize_variable(List<Object[]> rawList, List<Object[]> discountList, boolean isTotal, boolean isDiscount) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = StringUtils.EMPTY;
            int indexValue = 0;
            if (isTotal) {
                indexValue = indexPivot;
                key = "Total";
            } else {
                indexValue = indexDetail;
                key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
            }
            List<ProjectionResultsDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[isTotal ? base_Column_Year : 5].toString()), Integer.valueOf(obj[isTotal ? base_Column_frequency : 6].toString()));
            String groupId = common.get(1);
             String pcommonColumn = common.get(0);
            
            List<String> periodList = new ArrayList<String>(selection.getPeriodList());
            
            ProjectionResultsDTO freVarianceDTO = new ProjectionResultsDTO();
            if (periodList.contains(pcommonColumn) || (frequencyDivision==12 && periodList.contains(pcommonColumn.toLowerCase()))) {
                periodList.remove(pcommonColumn);
                      
                freVarianceDTO.setGroup(groupId);
            
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList_pivot(pvList, obj, freVarianceDTO, indexValue, isTotal, isDiscount,key);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList_pivot(obj, freVarianceDTO, indexValue, isTotal, key, isDiscount);
            }
          }
        }

//        //Discount
//        if (isDiscount && !discountList.isEmpty()) {
//            Set<String> discountSet = new HashSet();
//            Set<String> hierarchySet = new HashSet();
//            int listIndex = 0;
//            for (ListIterator<Object[]> it = discountList.listIterator(); it.hasNext();) {
//                Object[] obj = it.next();
//                String key = StringUtils.EMPTY;
////            it.remove();
//                if (isTotal) {
//                    key = "Total";
//                } else {
//                    key = obj[baseColumn_hierarchy_index].toString();
//                    key = key.substring(key.indexOf('-') + 1);
//    }
//                String discountName = obj[isTotal ? base_Column_Year : 5] == null ? "" : String.valueOf(obj[isTotal ? base_Column_Year : 5]);
//                if (discountSet.add(discountName)) {
//                    hierarchySet.clear();
//                    listIndex = 1;
//                    hierarchySet.add(key);
//                } else if (!isTotal && hierarchySet.add(key)) {
//                    listIndex = 1;
//                }
//                List<ProjectionResultsDTO> pvList = resultMap.get(key);
////                System.out.println("hierarchySet = " + hierarchySet);
////                System.out.println("==ket =========="+key+"==============list.si=========>>>"+pvList.size());
//                updateList_detail_discount_pivot(pvList, obj, listIndex++, discountName, isTotal);
//            }
//        }
    }
    
   private void addList_pivot(List<ProjectionResultsDTO> pvList, Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal, boolean isTotal, boolean isDiscount,String key) {

        if (isTotal) {
            ProjectionResultsDTO total = new ProjectionResultsDTO();
            total.setGroup("CFF Total");
            pvList.add(total);

        } else {
            ProjectionResultsDTO detail = new ProjectionResultsDTO();
            String groupName;
            if (isCustomView) {
                groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                groupName = groupName == null ? "" : groupName;
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), selection.getHierarchyIndicator());
            }
            detail.setGroup(groupName);
            pvList.add(detail);
        }

        //Ex-Factory-Product
        calculateForTotal("exFactory", obj, isTotal ? 1 : 3, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Ex-Factory-Customer
        calculateForTotal("custExFactory", obj, indexForTotal + 50, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Demand
        calculateForTotal("demand", obj, indexForTotal + 24, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Adjusted Demand
        calculateForTotal("adjDemand", obj, indexForTotal + 54, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Inventory Withdraw summary
        calculateForTotal("inventory", obj, indexForTotal + 26, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Inventory Withdraw Details
        calculateForTotal("inventoryDetails", obj, indexForTotal + 52, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //% of Ex-Factory Product
        calculateForTotal("perExFactory", obj, indexForTotal + 8, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of Ex-Factory Customer
        calculateForTotal("perCustExFactory", obj, indexForTotal + 56, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of Demand
        calculateForTotal("perDemand", obj, indexForTotal + 28, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of Adjusted Demand
        calculateForTotal("perAdjDemand", obj, indexForTotal + 54, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of inventory Withdraw Summary
        calculateForTotal("perInventory", obj, indexForTotal + 30, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of inventory Withdraw Detail
        calculateForTotal("perInventoryDetails", obj, indexForTotal + 58, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //Contract Sales @ WAC
        calculateForTotal("conSalesWac", obj, indexForTotal, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Contract Units
        calculateForTotal("unitVol", obj, indexForTotal + 2, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Discount $
        calculateForTotal("totDisDol", obj, indexForTotal + 4, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Discount %
        calculateForTotal("totDisPer", obj, indexForTotal + 6, frequencyBasedDTO, selection, Boolean.FALSE,true);

        //RPU
        calculateForTotal("totalRPU", obj, indexForTotal + 36, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Net Sales 
        calculateForTotal("netSales", obj, indexForTotal + 22, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //COGS
        calculateForTotal("cogs", obj, indexForTotal + 38, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Net Profit
        calculateForTotal("netProfit", obj, indexForTotal + 40, frequencyBasedDTO, selection, Boolean.TRUE,true);

        String commonColumn=StringUtils.EMPTY;
       if (frequencyDivision == 4) {
           if (isTotal) {
               commonColumn = "Q" + obj[4] + StringUtils.EMPTY + obj[3];
           } else {
               commonColumn = "Q" + obj[6] + StringUtils.EMPTY + obj[5];
           }
       } else if (frequencyDivision == 2) {
           if (isTotal) {
               commonColumn = "S" + obj[4] + StringUtils.EMPTY + obj[3];
           } else {
               commonColumn = "S" + obj[6] + StringUtils.EMPTY + obj[5];
           }
       } else if (frequencyDivision == 1) {
           if (isTotal) {
               commonColumn = String.valueOf(obj[3]);
           } else {
               commonColumn = String.valueOf(obj[5]);
           }
       } else if (frequencyDivision == 12) {
           if (isTotal) {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
               commonColumn = monthName.toLowerCase() + obj[3];
           } else {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[5])) - 1);
               commonColumn = monthName.toLowerCase() + obj[5];
           }
       }
       Map<String, ProjectionResultsDTO> valueMap=null;
       if(isTotal){
            valueMap = totalpivotDiscountMap.get("Total");
       }else{
          valueMap = pivotDiscountMap.get(key);  
       }
        if (valueMap != null) {
            ProjectionResultsDTO dto = valueMap.get(commonColumn);
            if(dto!=null){
            for (Object prop : dto.getProperties().keySet()) {
                String value = String.valueOf(dto.getPropertyValue(prop));
                frequencyBasedDTO.addStringProperties(prop, value);
            }
            }
        }
      pvList.add(frequencyBasedDTO);

    }

    private void updateList_pivot(Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal, boolean isTotal, String key, boolean isDiscount) {
        List list = resultMap.get(key);
        //Ex-Factory-Product
        calculateForTotal("exFactory", obj, isTotal ? 1 : 3, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Ex-Factory-Customer
        calculateForTotal("custExFactory", obj, indexForTotal + 50, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Demand
        calculateForTotal("demand", obj, indexForTotal + 24, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Adjusted Demand
        calculateForTotal("adjDemand", obj, indexForTotal + 54, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Inventory Withdraw summary
        calculateForTotal("inventory", obj, indexForTotal + 26, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //Inventory Withdraw Details
        calculateForTotal("inventoryDetails", obj, indexForTotal + 52, frequencyBasedDTO, selection, Boolean.TRUE,Boolean.TRUE);

        //% of Ex-Factory Product
        calculateForTotal("perExFactory", obj, indexForTotal + 8, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of Ex-Factory Customer
        calculateForTotal("perCustExFactory", obj, indexForTotal + 56, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of Demand
        calculateForTotal("perDemand", obj, indexForTotal + 28, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of Adjusted Demand
        calculateForTotal("perAdjDemand", obj, indexForTotal + 54, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of inventory Withdraw Summary
        calculateForTotal("perInventory", obj, indexForTotal + 30, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //% of inventory Withdraw Detail
        calculateForTotal("perInventoryDetails", obj, indexForTotal + 58, frequencyBasedDTO, selection, Boolean.FALSE,Boolean.TRUE);

        //Contract Sales @ WAC
        calculateForTotal("conSalesWac", obj, indexForTotal, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Contract Units
        calculateForTotal("unitVol", obj, indexForTotal + 2, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Discount $
        calculateForTotal("totDisDol", obj, indexForTotal + 4, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Discount %
        calculateForTotal("totDisPer", obj, indexForTotal + 6, frequencyBasedDTO, selection, Boolean.FALSE,true);

        //RPU
        calculateForTotal("totalRPU", obj, indexForTotal + 36, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Net Sales 
        calculateForTotal("netSales", obj, indexForTotal + 22, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //COGS
        calculateForTotal("cogs", obj, indexForTotal + 38, frequencyBasedDTO, selection, Boolean.TRUE,true);

        //Net Profit
        calculateForTotal("netProfit", obj, indexForTotal + 40, frequencyBasedDTO, selection, Boolean.TRUE,true);

      
         String commonColumn=StringUtils.EMPTY;
       if (frequencyDivision == 4) {
           if (isTotal) {
               commonColumn = "Q" + obj[4] + StringUtils.EMPTY + obj[3];
           } else {
               commonColumn = "Q" + obj[6] + StringUtils.EMPTY + obj[5];
           }
       } else if (frequencyDivision == 2) {
           if (isTotal) {
               commonColumn = "S" + obj[4] + StringUtils.EMPTY + obj[3];
           } else {
               commonColumn = "S" + obj[6] + StringUtils.EMPTY + obj[5];
           }
       } else if (frequencyDivision == 1) {
           if (isTotal) {
               commonColumn = String.valueOf(obj[3]);
           } else {
               commonColumn = String.valueOf(obj[5]);
           }
       } else if (frequencyDivision == 12) {
           if (isTotal) {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
               commonColumn = monthName.toLowerCase() + obj[3];
           } else {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[5])) - 1);
               commonColumn = monthName.toLowerCase() + obj[5];
           }
       }
        Map<String, ProjectionResultsDTO> valueMap = null;
        if (isTotal) {
            valueMap = totalpivotDiscountMap.get("Total");
        } else {
            valueMap = pivotDiscountMap.get(key);
        }
        if (valueMap != null) {
            ProjectionResultsDTO dto = valueMap.get(commonColumn);
            if (dto != null) {
                for (Object prop : dto.getProperties().keySet()) {
                    String value = String.valueOf(dto.getPropertyValue(prop));
                    frequencyBasedDTO.addStringProperties(prop, value);
                }
            }
        }
        
        
        list.add(frequencyBasedDTO);

    }
    
    
    private void calculateForTotal(String variableName, Object[] obj, int index, ProjectionResultsDTO pvDTO, ProjectionSelectionDTO selection, boolean isAmtformat,boolean inclusionFlag) {
        String val = StringUtils.EMPTY;
        /*Actuals */
        val = getFormattedValue(isAmtformat ? AMOUNT : RATE_PER, String.valueOf(obj[index]));
        pvDTO.addStringProperties(variableName + Constants.LabelConstants.ACTUALS.getConstant(), isAmtformat ? val : val+PERCENT);
        
        /*Projection*/
       val = getFormattedValue(isAmtformat ? AMOUNT : RATE_PER, String.valueOf(obj[index + 1]));
       pvDTO.addStringProperties(variableName + Constants.LabelConstants.PROJECTIONS.getConstant(), isAmtformat ? val : val+PERCENT);

    }
    
    
    private void updateList_detail_discount_pivot(List<ProjectionResultsDTO> list, final Object[] obj, int listIndex, String discountSet, boolean isTotal) {
        ProjectionResultsDTO frequencyBasedDTO = list.get(listIndex < list.size() ? listIndex : 0); 
//        ProjectionResultsDTO frequencyBasedDTO = list.get(listIndex);
        //Total Discount Dollar
        calculateForTotal("totDisDol" + discountSet.replace(" ", StringUtils.EMPTY), obj, isTotal ? 4 : 6, frequencyBasedDTO, selection, Boolean.TRUE,true);
        //Total Discount Percentage
        calculateForTotal("totDisPer" + discountSet.replace(" ", StringUtils.EMPTY), obj, isTotal ? 6 : 8, frequencyBasedDTO, selection, Boolean.FALSE,true);
        //Total RPU
        calculateForTotal("totalRPU" + discountSet.replace(" ", StringUtils.EMPTY), obj, isTotal ? 8 : 10, frequencyBasedDTO, selection, Boolean.TRUE,true);
    }
    
          private void calculate_Total_Discount() {
        String column = StringUtils.EMPTY;
        String value = StringUtils.EMPTY;
        String baseValue = StringUtils.EMPTY;
        ProjectionResultsDTO totalDiscDollar=new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscPer=new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPU=new ProjectionResultsDTO();
        String oldDiscount=StringUtils.EMPTY;
        int count=procRawList_total_discount.size();
        String commonColumn = StringUtils.EMPTY;
        List<ProjectionResultsDTO> discountDollarList=new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> discountperList=new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> totalRPUList=new ArrayList<ProjectionResultsDTO>();
        List<List<ProjectionResultsDTO>> finaldiscountlist=new ArrayList<List<ProjectionResultsDTO>>();
        for(int i=0;i<count;i++){
            Object[] obj = (Object[])procRawList_total_discount.get(i);
            String newDiscount=String.valueOf(obj[3]);
            if(oldDiscount.equals(newDiscount)){
                if (frequencyDivision == 4) {
                        commonColumn = "q" + obj[2] + StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "s" + obj[2] + StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[2])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[1];
                    }
                   column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if( obj[4]==null){
                       totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                   baseValue = getFormattedValue(AMOUNT, value);
                   totalDiscDollar.addStringProperties(column, baseValue);
                  }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                if (obj[5] == null) {
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                } else {
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }
                    
                   column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                   if(obj[6]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY); 
                   }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                   baseValue = getFormattedValuePer(AMOUNT, value);
                   totalDiscPer.addStringProperties(column, baseValue);
                   }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                      if(obj[7]==null){
                            totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                      }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                      }
                   
                  column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if(obj[8]==null){
                       totalRPU.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                   baseValue = getFormattedValue(AMOUNT, value);
                   totalRPU.addStringProperties(column, baseValue);
                  }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[9]==null){
                         totalRPU.addStringProperties(column, StringUtils.EMPTY);  
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);  
                    }
            }else{
                if(i==0){
                    if (frequencyDivision == 4) {
                        commonColumn = "q" + obj[2] + StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "s" + obj[2] + StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[2])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[1];
                    }
                   
                    totalDiscDollar.setGroup(String.valueOf(obj[3]));
                    totalDiscPer.setGroup(String.valueOf(obj[3]));
                    totalRPU.setGroup(String.valueOf(obj[3]));
                    
                   column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if( obj[4]==null){
                       totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                   baseValue = getFormattedValue(AMOUNT, value);
                   totalDiscDollar.addStringProperties(column, baseValue);
                  }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                if (obj[5] == null) {
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                } else {
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }
                    
                   column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                   if(obj[6]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY); 
                   }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                   baseValue = getFormattedValuePer(AMOUNT, value);
                   totalDiscPer.addStringProperties(column, baseValue);
                   }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                      if(obj[7]==null){
                            totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                      }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                      }
                   
                  column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if(obj[8]==null){
                       totalRPU.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                   baseValue = getFormattedValue(AMOUNT, value);
                   totalRPU.addStringProperties(column, baseValue);
                  }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[9]==null){
                         totalRPU.addStringProperties(column, StringUtils.EMPTY);  
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);  
                    }
                    
                    oldDiscount=newDiscount;
                }else{
                   
                    /*New discount means add at List */
                    discountDollarList.add(totalDiscDollar);
                    discountperList.add(totalDiscPer);
                    totalRPUList.add(totalRPU);
                    
                    /*Empty the DTO */
                    totalDiscDollar = new ProjectionResultsDTO();
                    totalDiscPer = new ProjectionResultsDTO();
                    totalRPU = new ProjectionResultsDTO();
                    
                    oldDiscount=newDiscount;
                    totalDiscDollar.setGroup(String.valueOf(obj[3]));
                    totalDiscPer.setGroup(String.valueOf(obj[3]));
                    totalRPU.setGroup(String.valueOf(obj[3]));
                    
                      if (frequencyDivision == 4) {
                        commonColumn = "q" + obj[2] + StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "s" + obj[2] + StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[1];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[2])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[1];
                    }
                   
                  column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if( obj[4]==null){
                       totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                   baseValue = getFormattedValue(AMOUNT, value);
                   totalDiscDollar.addStringProperties(column, baseValue);
                  }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                if (obj[5] == null) {
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                } else {
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }
                    
                   column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                   if(obj[6]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY); 
                   }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                   baseValue = getFormattedValuePer(AMOUNT, value);
                   totalDiscPer.addStringProperties(column, baseValue);
                   }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                      if(obj[7]==null){
                            totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                      }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                      }
                   
                  column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if(obj[8]==null){
                       totalRPU.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                   value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                   baseValue = getFormattedValue(AMOUNT, value);
                   totalRPU.addStringProperties(column, baseValue);
                  }
                   
                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[9]==null){
                         totalRPU.addStringProperties(column, StringUtils.EMPTY);  
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);  
                    }
                }
            }
                   if(i==count-1){
                       
                    discountDollarList.add(totalDiscDollar);
                    discountperList.add(totalDiscPer);
                    totalRPUList.add(totalRPU);
                    
                    finaldiscountlist.add(discountDollarList);
                    finaldiscountlist.add(discountperList);
                    finaldiscountlist.add(totalRPUList);
                    discountMap.put("Total",finaldiscountlist);
                    }
                
            }
        }
    
    
        private void customize_discount_pivot() {
        int count = procRawList_detail_discount.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String discountNo = StringUtils.EMPTY;
        String newyear = StringUtils.EMPTY;
        String oldYear = StringUtils.EMPTY;
        String newPeriod = StringUtils.EMPTY;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        ProjectionResultsDTO discountDto = new ProjectionResultsDTO();
       Map<String, ProjectionResultsDTO> periodDiscountMap = new HashMap<String, ProjectionResultsDTO>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[1]);
                oldHierarchyNo = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
            }
            String newHierarchyNo = String.valueOf(obj[1]);
            newHierarchyNo = newHierarchyNo.substring(newHierarchyNo.indexOf('-') + 1);
            newyear = String.valueOf(obj[3]);
            newPeriod = String.valueOf(obj[4]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    setBase_Value(discountDto,obj,false);
                } else{
                    if (i == 0) {
                    discountDto = new ProjectionResultsDTO();
                    commonColumn=getVisibleColumn_Header(obj,false);
                    setBase_Value(discountDto,obj,false);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                   
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionResultsDTO();
                    commonColumn=getVisibleColumn_Header(obj,false);
                    setBase_Value(discountDto,obj,false);

                    oldYear = newyear;
                    oldPeriod = newPeriod;
                }
            }
            }else {
                  periodDiscountMap.put(commonColumn, discountDto);
                  pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
                  periodDiscountMap = new HashMap<String, ProjectionResultsDTO>();
                  discountDto = new ProjectionResultsDTO();
                  commonColumn=getVisibleColumn_Header(obj,false);
                  setBase_Value(discountDto,obj,false);
                  oldYear = newyear;
                  oldPeriod = newPeriod;
                  oldHierarchyNo=newHierarchyNo;
                  
            }
            if (i == count - 1) {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
            }
        }
    }  
          
          
    private void setBase_Value(ProjectionResultsDTO discountDto, Object[] obj,boolean istotal) {
        
        String visibleColumn = "totDisDol" + String.valueOf(istotal?obj[3]:obj[5]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.ACTUALS.getConstant();
        
        String value=getCellValue(istotal?obj[4]:obj[6], "Amount");
        discountDto.addStringProperties(visibleColumn, value);
        
        visibleColumn = "totDisDol" + String.valueOf(istotal?obj[3]:obj[5]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.PROJECTIONS.getConstant();
        value=getCellValue(istotal?obj[5]:obj[7], "Amount");
        discountDto.addStringProperties(visibleColumn, value);
        
       
        visibleColumn = "totDisPer" + String.valueOf(istotal?obj[3]:obj[5]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.ACTUALS.getConstant();
        value=getCellValue(istotal?obj[6]:obj[8], "Rate");
        
        discountDto.addStringProperties(visibleColumn, value);
        
        visibleColumn = "totDisPer" + String.valueOf(istotal?obj[3]:obj[5]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.PROJECTIONS.getConstant();
        value=getCellValue(istotal?obj[7]:obj[9], "Rate");
        discountDto.addStringProperties(visibleColumn, value);

        visibleColumn = "totalRPU" + String.valueOf(istotal?obj[3]:obj[5]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.ACTUALS.getConstant();
        value=getCellValue(istotal?obj[8]:obj[10], "Amount");
        discountDto.addStringProperties(visibleColumn, value);
        
        visibleColumn = "totalRPU" + String.valueOf(istotal?obj[3]:obj[5]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.PROJECTIONS.getConstant();
        value=getCellValue(istotal?obj[9]:obj[11], "Amount");
        discountDto.addStringProperties(visibleColumn, value);
       }
    
    
    private String getCellValue(Object obj,String type)
    {
      String returnValue=StringUtils.EMPTY;
      if(obj==null){
          return returnValue;
      }else{
          returnValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj)));
          if("Amount".equals(type)){
               returnValue = getFormattedValue(AMOUNT, returnValue);
          }else if("Rate".equals(type)){
               returnValue = getFormattedValue(RATE, returnValue);
               returnValue=returnValue+PERCENT;
          }
          }
      return !returnValue.equals("null")?returnValue:StringUtils.EMPTY;
    }
    

     private String getVisibleColumn_Header(Object[] obj, boolean istotal) {
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == 4) {
            if (istotal) {
                commonColumn = "Q" + obj[2] + StringUtils.EMPTY + obj[1];
            } else {
                commonColumn = "Q" + obj[4] + StringUtils.EMPTY + obj[3];
            }
        } else if (frequencyDivision == 2) {
            if (istotal) {
                commonColumn = "S" + obj[2] + StringUtils.EMPTY + obj[1];
            } else {
                commonColumn = "S" + obj[4] + StringUtils.EMPTY + obj[3];
            }
        } else if (frequencyDivision == 1) {
            if (istotal) {
                commonColumn = StringUtils.EMPTY + obj[1];
            } else {
                commonColumn = StringUtils.EMPTY + obj[3];
            }
        } else if (frequencyDivision == 12) {
            if (istotal) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[2])) - 1);
                commonColumn = monthName.toLowerCase() + obj[1];
            } else {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
                commonColumn = monthName.toLowerCase() + obj[3];
            }
        }
        return commonColumn;
    }
    
    
     
        private void total_Discount_Customize()
        {
        int count=procRawList_discount.size();
        String newyear = StringUtils.EMPTY;
        String oldYear = StringUtils.EMPTY;
        String newPeriod = StringUtils.EMPTY;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        ProjectionResultsDTO discountDto = new ProjectionResultsDTO();
       Map<String, ProjectionResultsDTO> periodDiscountMap = new HashMap<String, ProjectionResultsDTO>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_discount.get(i);
            newyear = String.valueOf(obj[1]);
            newPeriod = String.valueOf(obj[2]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    setBase_Value(discountDto,obj,true);
                } else{
                    if (i == 0) {
                    discountDto = new ProjectionResultsDTO();
                    commonColumn=getVisibleColumn_Header(obj,true);
                    setBase_Value(discountDto,obj,true);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                   
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionResultsDTO();
                    commonColumn=getVisibleColumn_Header(obj,true);
                    setBase_Value(discountDto,obj,true);

                    oldYear = newyear;
                    oldPeriod = newPeriod;
                }
            }
       
            if (i == count - 1) {
                periodDiscountMap.put(commonColumn, discountDto);
                totalpivotDiscountMap.put("Total", periodDiscountMap);
            }
        }
        }
        
        
        
        
        
}
