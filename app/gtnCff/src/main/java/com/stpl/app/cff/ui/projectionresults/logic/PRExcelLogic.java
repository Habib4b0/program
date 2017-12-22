/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionresults.logic;

import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.CommonConstantsForChannels.CUSTOM;
import static com.stpl.app.cff.util.Constants.LabelConstants.DESCENDING;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERCENT;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
                                 perInvWithDetail, contractSales, units, disDoll, disPer, rpu, netSales, cogs, netProfit, 
                                 discountPercentageExFactory,netSalesPercentageExFactory, productViewExFactoryDTO;

    private final Map<String, List<ProjectionResultsDTO>> resultMap;
    private final Map<String, List<List<ProjectionResultsDTO>>> discountMap;
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final ProjectionSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final DecimalFormat UNIT = new DecimalFormat("##,###,##0");
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
    private static final String PRC_PROJ_RESULTS_TOTAL = "PRC_CFF_RESULTS";
    private static final String PRC_PROJ_RESULTS_TOTAL_DISCOUNT = "PRC_CFF_PROJECTION_RESULTS_DISCOUNT";
    private final List<Object[]> procRawList_total_discount = new ArrayList();
    private List<Object> pivotDiscountList = new ArrayList<>();
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private final Map<String, String> customView_relationship_hierarchy = new HashMap();
    private boolean isCustomView;
    private final int indexDetail = 7;
    private Map<String,Map<String,ProjectionResultsDTO>> pivotDiscountMap=new HashMap<>();
    private Map<String,Map<String,ProjectionResultsDTO>> totalpivotDiscountMap=new HashMap<>();
    
  
    public PRExcelLogic(Map<String, List<ProjectionResultsDTO>> resultMap, ProjectionSelectionDTO selection,
            List<String> hierarchyKeys, List<String> tradingPartnerKeys, List<String> discountKeys, Map<String, List<List<ProjectionResultsDTO>>> discountMap) {
        this.resultMap = resultMap;
        this.selection = selection;
        this.hierarchyKeys = hierarchyKeys;
        this.tradingPartnerKeys = tradingPartnerKeys;
        this.discountKeys = discountKeys;
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
            customView_relationship_hierarchy.putAll(getGroup_customViewNM());
        }
        frequencyDivision = selection.getFrequencyDivision();

        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {

            // get the hierarchy and discount data from db
            getTotalRawData(Boolean.FALSE);

            //Total Level Customization
            calculateAndCustomize_period(procRawList_total, Boolean.TRUE);

            //Total Discount Level Customization
            calculate_Total_Discount();

            //Detail Level Customization.
                calculateAndCustomize_period(procRawList_detail, false);

            customizeDiscount_period();
        } else {
            getTotalRawData(Boolean.TRUE);

            //Total Level Customization for Total and Discount 
            total_Discount_Customize();
            customize_discount_pivot();
            calculateAndCustomize_variable(procRawList_total, Boolean.TRUE);

            //Detail Level Customization for Hierarchy and Discount
            calculateAndCustomize_variable(procRawList_detail, Boolean.FALSE);

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
            String key;
            if (isTotal) {
                key = StringConstantsUtil.TOTAL;
            } else {
                key = obj[baseColumn_hierarchy_index].toString() +("null".equals(String.valueOf(obj[obj.length - 5])) ? StringUtils.EMPTY : "$"+String.valueOf(obj[obj.length - 5]));
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
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
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
                calculate(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, index, exFacProduct, AMOUNT, isTotal,false);
                pvList.add(exFacProduct);

                // Ex-Factory-Customer -ok
                exFacCustomer = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, NumericConstants.FORTY_NINE, exFacCustomer, AMOUNT, isTotal,false);
                pvList.add(exFacCustomer);

                //Demand-ok
                demand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.DEMAND.toString(), obj, NumericConstants.TWENTY_NINE, demand, AMOUNT, isTotal,false);
                pvList.add(demand);

                //Adjusted Demand-ok
                adjustedDemand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, NumericConstants.FORTY_SEVEN, adjustedDemand, AMOUNT, isTotal,false);
                pvList.add(adjustedDemand);

                //Inventory Withdraw Summary
                invWithSummary = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, NumericConstants.THIRTY_ONE, invWithSummary, AMOUNT, isTotal,false);
                pvList.add(invWithSummary);

                //Inventory Withdraw Detail
                invWithDetail = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, NumericConstants.FIFTY_ONE, invWithDetail, AMOUNT, isTotal,false);
                pvList.add(invWithDetail);

                //% of Ex-Factory Product
                perExFacProduct = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, NumericConstants.THIRTEEN, perExFacProduct, AMOUNT, isTotal,false);
                pvList.add(perExFacProduct);

                //% of Ex-Factory Customer-ok
                perExFacCustomer = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, NumericConstants.FIFTY_FIVE, perExFacCustomer, AMOUNT, isTotal,false);
                pvList.add(perExFacCustomer);

                //% of Demand -ok
                perDemand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_DEMAND.toString(), obj, NumericConstants.THIRTY_THREE, perDemand, AMOUNT, isTotal,false);
                pvList.add(perDemand);

                //% of Adjusted Demand -ok
                perAdjustedDemand = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, NumericConstants.FIFTY_THREE, perAdjustedDemand, AMOUNT, isTotal,false);
                pvList.add(perAdjustedDemand);

                //% of inventory Withdraw Summary-ok
                perInvWithSummary = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, NumericConstants.THIRTY_FIVE, perInvWithSummary, AMOUNT, isTotal,false);
                pvList.add(perInvWithSummary);

                perInvWithDetail = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, NumericConstants.FIFTY_SEVEN, perInvWithDetail, AMOUNT, isTotal,false);
                pvList.add(perInvWithDetail);

            } else {
                ProjectionResultsDTO detail = new ProjectionResultsDTO();
                //Group Column projSelDTO

                String groupName;
                if (isCustomView) {
                    groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                    groupName = groupName == null ? "" : groupName;
                    detail.setHierarchyNo(obj[1].toString());
                    detail.setParentHierarchyNo(obj[obj.length - 5] == null ? null : obj[obj.length - 5].toString());
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[1].toString(), selection.getHierarchyIndicator());
                      
                }

                detail.setGroup(groupName);
                pvList.add(detail);
            }

            //Product or Custom view based Ex-Factory CEL-376
            Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
            List productList = relationshipLevelDetailsMap.get(obj[1].toString());
            if (!isTotal && !productList.isEmpty() && "P".equals(String.valueOf(productList.get(4)))) {
                productViewExFactoryDTO = new ProjectionResultsDTO();
                calculate(String.valueOf(obj[2]) + " " + Constants.LabelConstants.PRODUCT_LEVEL_EX_FACTORY.getConstant(), obj, NumericConstants.THREE, productViewExFactoryDTO, AMOUNT, isTotal, true);
                pvList.add(productViewExFactoryDTO);
            }

            //Contract Sales @ WAC -ok
            contractSales = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, isTotal ? NumericConstants.FIVE : NumericConstants.SEVEN, contractSales, AMOUNT, isTotal,true);
            pvList.add(contractSales);
            
            //% of Ex-Factory Product
            if (!isTotal) {
                perExFacProduct = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, NumericConstants.FIFTEEN, perExFacProduct, AMOUNT, isTotal, false);
                pvList.add(perExFacProduct);
            }

            String salesOrUnits = selection.getSalesOrUnit();
            if (!salesOrUnits.equals(StringConstantsUtil.SALES)) {
                //Contract Units-ok
                units = new ProjectionResultsDTO();
                calculate(Constants.PVVariables.VAR_UNIT_VOLUME.toString(), obj, isTotal ? NumericConstants.SEVEN : NumericConstants.NINE, units, UNIT, isTotal,true);
                pvList.add(units);
            }

            //Discount $-ok
            String group;
            disDoll = new ProjectionResultsDTO();
            if (isTotal) {
                group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
            } else {
                group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
            }
            calculate(group, obj, isTotal ? NumericConstants.NINE : NumericConstants.ELEVEN, disDoll, AMOUNT, isTotal,true);
            pvList.add(disDoll);

            //Discount % -ok
            disPer = new ProjectionResultsDTO();
            if (isTotal) {
                group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
            } else {
                group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
            }
            calculate(group, obj, isTotal ? NumericConstants.ELEVEN : NumericConstants.THIRTEEN, disPer, RATE_PER, isTotal,true);
            pvList.add(disPer);

            //RPU-ok
            rpu = new ProjectionResultsDTO();
            if (isTotal) {
                group = Constants.PVVariables.TOTAL_RPU.toString();
            } else {
                group = Constants.PVVariables.TOTAL_RPU.toString();
            }
            calculate(group, obj, isTotal ? NumericConstants.FORTY_ONE : NumericConstants.FORTY_THREE, rpu, AMOUNT, isTotal,true);
            pvList.add(rpu);
            
            //Discount % of Ex-Factory
            discountPercentageExFactory = new ProjectionResultsDTO();
            calculate(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.toString(), obj, isTotal ? NumericConstants.SIXTY_ONE : NumericConstants.SIXTY_FOUR, discountPercentageExFactory, RATE_PER, isTotal,true);
            pvList.add(discountPercentageExFactory);

            //Net Sales -ok
            netSales = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_NETSALES.toString(), obj, isTotal ? NumericConstants.TWENTY_SEVEN : NumericConstants.TWENTY_NINE, netSales, AMOUNT, isTotal,true);
            pvList.add(netSales);
            
            //Net Sales % of Ex-Factory
            netSalesPercentageExFactory = new ProjectionResultsDTO();
            calculate(Constants.LabelConstants.NET_SALES_PERCENTAGE_EXFACTORY.toString(), obj, isTotal ? NumericConstants.FIFTY_NINE : NumericConstants.SIXTY_TWO, netSalesPercentageExFactory, RATE_PER, isTotal,true);
            pvList.add(netSalesPercentageExFactory);
            
            //COGS -ok
            cogs = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_COGS.toString(), obj, isTotal ? NumericConstants.FORTY_THREE : NumericConstants.FORTY_FIVE, cogs, AMOUNT, isTotal,true);
            pvList.add(cogs);

            //Net Profit-ok
            netProfit = new ProjectionResultsDTO();
            calculate(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, isTotal ? NumericConstants.FORTY_FIVE : NumericConstants.FORTY_SEVEN, netProfit, AMOUNT, isTotal,true);
            pvList.add(netProfit);

         } catch (Exception e) {
             LOGGER.error(e);
         }

    }

    private void updateList(List<ProjectionResultsDTO> pvList, Object[] obj, int index, boolean isTotal) {

        int listIndex = 1;
        if (isTotal) {
            //Ex-Factory-Product
            exFacProduct = pvList.get(listIndex++);
            calculate(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), obj, index, exFacProduct, AMOUNT, isTotal,false);

            // Ex-Factory-Customer
            exFacCustomer = pvList.get(listIndex++);
            calculate(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), obj, NumericConstants.FORTY_NINE, exFacCustomer, AMOUNT, isTotal,false);

            //Demand
            demand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.DEMAND.toString(), obj, NumericConstants.TWENTY_NINE, demand, AMOUNT, isTotal,false);

            //Adjusted Demand
            adjustedDemand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.ADJUSTED_DEMAND.toString(), obj, NumericConstants.FORTY_SEVEN, adjustedDemand, AMOUNT, isTotal,false);

            //Inventory Withdraw Summary
            invWithSummary = pvList.get(listIndex++);
            calculate(Constants.PVVariables.INVENTORY_SUMMARY.toString(), obj, NumericConstants.THIRTY_ONE, invWithSummary, AMOUNT, isTotal,false);

            //Inventory Withdraw Detail
            invWithDetail = pvList.get(listIndex++);
            calculate(Constants.PVVariables.INVENTORY_DETAILS.toString(), obj, NumericConstants.FIFTY_ONE, invWithDetail, AMOUNT, isTotal,false);

            //% of Ex-Factory Product
            perExFacProduct = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, NumericConstants.THIRTEEN, perExFacProduct, AMOUNT, isTotal,false);

            //% of Ex-Factory Customer
            perExFacCustomer = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), obj, NumericConstants.FIFTY_FIVE, perExFacCustomer, AMOUNT, isTotal,false);

            //% of Demand
            perDemand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_DEMAND.toString(), obj, NumericConstants.THIRTY_THREE, perDemand, AMOUNT, isTotal,false);

            //% of Adjusted Demand
            perAdjustedDemand = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), obj, NumericConstants.FIFTY_THREE, perAdjustedDemand, AMOUNT, isTotal,false);

            //% of inventory Withdraw Summary
            perInvWithSummary = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), obj, NumericConstants.THIRTY_FIVE, perInvWithSummary, AMOUNT, isTotal,false);

            perInvWithDetail = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), obj, NumericConstants.FIFTY_SEVEN, perInvWithDetail, AMOUNT, isTotal,false);

        }
        
        //Product or Custom view based Ex-Factory CEL-376
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        List productList = relationshipLevelDetailsMap.get(obj[1].toString());
        if (!isTotal && !productList.isEmpty() && "P".equals(String.valueOf(productList.get(4)))) {
            productViewExFactoryDTO = pvList.get(listIndex++);
            calculate(String.valueOf(obj[2].toString()) + " " + Constants.LabelConstants.PRODUCT_LEVEL_EX_FACTORY.getConstant(), obj, NumericConstants.THREE, productViewExFactoryDTO, AMOUNT, isTotal, true);
        }
        
        //  Contract Sales @ WAC
        contractSales = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), obj, isTotal ? NumericConstants.FIVE : NumericConstants.SEVEN, contractSales, AMOUNT, isTotal,true);
        
        //% of Ex-Factory Product
        if (!isTotal) {
            perExFacProduct = pvList.get(listIndex++);
            calculate(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), obj, NumericConstants.FIFTEEN, perExFacProduct, AMOUNT, isTotal, false);
        }

        String salesOrUnits = selection.getSalesOrUnit();
        if (!salesOrUnits.equals(StringConstantsUtil.SALES)) {
            //Contract Units
            units = pvList.get(listIndex++);
            calculate(Constants.PVVariables.VAR_UNIT_VOLUME.toString(), obj, isTotal ? NumericConstants.SEVEN : NumericConstants.NINE, units, UNIT, isTotal,true);
        }

        //Discount %
         String group;
        disPer = pvList.get(listIndex++);
        if (isTotal) {
            group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
        } else {
            group = Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString();
        }
        calculate(group, obj, isTotal ? NumericConstants.ELEVEN : NumericConstants.THIRTEEN, disPer, RATE_PER, isTotal,true);

        //RPU
        rpu = pvList.get(listIndex++);
        if (isTotal) {
            group = Constants.PVVariables.TOTAL_RPU.toString();
        } else {
            group = Constants.PVVariables.TOTAL_RPU.toString();
        }
        calculate(group, obj, isTotal ? NumericConstants.FORTY_ONE : NumericConstants.FORTY_THREE, rpu,  AMOUNT, isTotal,true);
        
         //Discount $
       
        disDoll = pvList.get(listIndex++);
        if (isTotal) {
            group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
        } else {
            group = Constants.PVVariables.TOTAL_DISCOUNT_AMOUNT.toString();
        }
        calculate(group, obj, isTotal ? NumericConstants.NINE : NumericConstants.ELEVEN, disDoll, AMOUNT, isTotal,true);

        //Discount % of Ex-Factory
        discountPercentageExFactory = pvList.get(listIndex++);
        calculate(Constants.LabelConstants.DISCOUNT_PERCENTAGE_EXFACTORY.toString(), obj, isTotal ? NumericConstants.SIXTY_ONE : NumericConstants.SIXTY_FOUR, discountPercentageExFactory, RATE_PER, isTotal, true);
            
        //Net Sales 
        netSales = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_NETSALES.toString(), obj, isTotal ? NumericConstants.TWENTY_SEVEN : NumericConstants.TWENTY_NINE, netSales,  AMOUNT, isTotal,true);

        //Net Sales % of Ex-Factory
        netSalesPercentageExFactory = pvList.get(listIndex++);
        calculate(Constants.LabelConstants.NET_SALES_PERCENTAGE_EXFACTORY.toString(), obj, isTotal ? NumericConstants.FIFTY_NINE : NumericConstants.SIXTY_TWO, netSalesPercentageExFactory, RATE_PER, isTotal, true);

        //COGS
        cogs = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_COGS.toString(),  obj, isTotal ? NumericConstants.FORTY_THREE : NumericConstants.FORTY_FIVE, cogs,  AMOUNT, isTotal,true);

        //Net Profit
        netProfit = pvList.get(listIndex++);
        calculate(Constants.PVVariables.VAR_NET_PROFITE.toString(), obj, isTotal ? NumericConstants.FORTY_FIVE : NumericConstants.FORTY_SEVEN, netProfit,  AMOUNT, isTotal,true);
        LOGGER.debug("End of Method" + listIndex);
    }

    private void calculate(String varaibleName, Object[] obj, int index, ProjectionResultsDTO pvDTO, DecimalFormat format,boolean isTotal,boolean salesInclusionFlag) {

        String column;
        String value;
        String baseValue;
        pvDTO.setGroup(varaibleName);
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            if(isTotal){
            commonColumn = "q" + obj[base_Column_frequency] + StringUtils.EMPTY + obj[base_Column_Year];
            }else{
               commonColumn = "q" + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE]; 
            }
        } else if (frequencyDivision == NumericConstants.TWO) {
             if(isTotal){
            commonColumn = "s" + obj[base_Column_frequency] + StringUtils.EMPTY + obj[base_Column_Year];
            }else{
               commonColumn = "s" + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE]; 
            }
        } else if (frequencyDivision == 1) {
            if(isTotal){
            commonColumn = StringUtils.EMPTY + obj[base_Column_Year];
            }else{
               commonColumn = StringUtils.EMPTY + obj[NumericConstants.FIVE];
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            if(isTotal){
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[base_Column_frequency])) - 1);
            commonColumn = monthName.toLowerCase() + obj[NumericConstants.THREE];
            }else{
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])) - 1);
                commonColumn = monthName.toLowerCase() + obj[NumericConstants.FIVE]; 
            }
        }

        //Actuals
        column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
        if (salesInclusionFlag) {
            if (obj[index] == null) {
                pvDTO.addStringProperties(column, StringUtils.EMPTY);
            } else {
                value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                if (varaibleName.contains("%")|| varaibleName.equals(Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString())) {
                    baseValue = getFormattedValuePer(format, value);
                } else {
                    baseValue = getFormattedValue(format, value);
                }
                pvDTO.addStringProperties(column, baseValue);
            }
        } else {
            value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
            if (varaibleName.contains("%")|| varaibleName.equals(Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString())) {
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
        if (varaibleName.contains("%")|| varaibleName.equals(Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString())) {
            baseValue = getFormattedValuePer(format, value);
        } else {
            baseValue = getFormattedValue(format, value);
        }
        pvDTO.addStringProperties(column, baseValue);
           }
        }else{
        value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1])));
        if (varaibleName.contains("%")|| varaibleName.equals(Constants.PVVariables.TOTAL_DISCOUNT_PERC.toString())) {
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
        String detailFreq;
        String view = selection.getView().equals(CUSTOM.getConstant()) ? "A" : selection.getHierarchyIndicator();
        procRawList_total.clear();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = "QUARTERLY";
            detailFreq = "Q";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
            detailFreq = "S";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
            detailFreq = "M";
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
            detailFreq = "A";
        }

        if (!isPivot) {
            //Total Level Procedure 
            procRawList_total.clear();
            Object[] orderedArgs = {selection.getProjectionId(), frequency, StringConstantsUtil.ASSUMPTIONS};
            List< Object[]> rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS_TOTAL, orderedArgs);
            procRawList_total.addAll(rawList);
            rawList.clear();
            
             //Total Discount Level Procedure  
            procRawList_total_discount.clear();
            Object[] orderedArgsTotalDiscount = {selection.getProjectionId(), frequency, StringConstantsUtil.ASSUMPTIONS,null, null, null, null, "excel"};
            rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS_TOTAL_DISCOUNT, orderedArgsTotalDiscount);
            procRawList_total_discount.addAll(rawList);
            rawList.clear();

            //Detail Level Procedure
            procRawList_detail.clear();
            Object[] orderedArgsDetail = {selection.getProjectionId(), frequency, StringConstantsUtil.ASSUMPTIONS, "period", view, null, selection.getFilterLevelNo(), selection.getCustomId(), null,null,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArgsDetail);
            procRawList_detail.addAll(rawList);
            rawList.clear();

            //Discount Level Procedure
            procRawList_discount.clear();
            Object[] orderedArgsDiscount = {selection.getProjectionId(), detailFreq, StringConstantsUtil.ASSUMPTIONS, "period", StringConstantsUtil.PROGRAM, null, view, null, selection.getFilterLevelNo(), selection.getCustomId(), null,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
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
            Object[] orderedArgs = {selection.getProjectionId(), frequency, StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, fromDate};
            List<Object[]> rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS_TOTAL, orderedArgs);
            procRawList_total.addAll(rawList);
            rawList.clear();

            //Detail Level Procedure
            procRawList_detail.clear();
            Object[] orderedArgsDetail = {selection.getProjectionId(), detailFreq, StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, view, null, selection.getFilterLevelNo(), selection.getCustomId(),null, fromDate,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
            rawList = CommonLogic.callProcedure("PRC_CFF_EXCEL_EXPORT", orderedArgsDetail);
            procRawList_detail.addAll(rawList);
            rawList.clear();

            //Total Discount Procedure
            procRawList_discount.clear();
            Object[] orderedArgsTotalDiscount = {selection.getProjectionId(), detailFreq, StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, fromDate, null, StringConstantsUtil.PROGRAM, "excel"};
            rawList = CommonLogic.callProcedure("PRC_CFF_PROJECTION_RESULTS_DISCOUNT", orderedArgsTotalDiscount);
            procRawList_discount.addAll(rawList);
            rawList.clear();

            //Detail Discount Procedure
            procRawList_detail_discount.clear();
            Object[] orderedArgDetailDiscount = {selection.getProjectionId(), detailFreq, StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, StringConstantsUtil.PROGRAM, null, view, null, selection.getFilterLevelNo(), selection.getCustomId(), fromDate,selection.getSessionDTO().getUserId(),selection.getSessionDTO().getSessionId()};
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
        List<String> projectionIdList = new ArrayList<>();
        pivotDiscountList = new ArrayList<>();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", projSelDTO.getSessionId(), projSelDTO.getUserId(), "1"};
        List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArg);
        pivotDiscountList.addAll(discountsList);

    }

    private Map<String, String> getGroup_customViewNM() {
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, String> customViewMap = new HashMap<>();
        Set keys = relationshipLevelDetailsMap.keySet();

        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            String value = (String) relationshipLevelDetailsMap.get(key).get(0).toString();
            customViewMap.put(key, value);
        }
        return customViewMap;
    }
     private void customizeDiscount_period(){
           calculateDiscount();
    }
    
    
    private void calculateDiscount() {

        String column;
        String value;
        String baseValue;
        ProjectionResultsDTO totalDiscDollar = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscPer = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPU = new ProjectionResultsDTO();
        ProjectionResultsDTO discountPercentageExFactory=new ProjectionResultsDTO();
        String oldDiscount = StringUtils.EMPTY;
        String oldHierarchyNo = StringUtils.EMPTY;
        int count = procRawList_discount.size();
        String commonColumn = StringUtils.EMPTY;
        String newDiscount;
        List<ProjectionResultsDTO> discountDollarList = new ArrayList<>();
        List<ProjectionResultsDTO> discountperList = new ArrayList<>();
        List<ProjectionResultsDTO> totalRPUList = new ArrayList<>();
        List<ProjectionResultsDTO> discountPercentageExFactoryList = new ArrayList<>();
        List<List<ProjectionResultsDTO>> finaldiscountlist = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_discount.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[1]) + ("null".equals(String.valueOf(obj[obj.length - 3])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 3]));
            }
            String newHierarchyNo = String.valueOf(obj[1]) + ("null".equals(String.valueOf(obj[obj.length - 3])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 3]));
            if (oldHierarchyNo.equals(newHierarchyNo)) {

                newDiscount = String.valueOf(obj[NumericConstants.FIVE]);
                if (oldDiscount.equals(newDiscount)) {
                  
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "s" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : NumericConstants.FOUR])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : NumericConstants.THREE];
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.SIX]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.SEVEN]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.EIGHT]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[NumericConstants.NINE]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.TEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.ELEVEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                    
                    //Discount % of Ex-Factory - Actuals
                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    value = String.valueOf(obj[NumericConstants.THIRTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    //Discount % of Ex-Factory - Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    value = String.valueOf(obj[NumericConstants.FOURTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);
                } else {
                 
                    if (i == 0) {
                        totalDiscDollar.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        totalDiscPer.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        totalRPU.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        discountPercentageExFactory.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        
                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                        }

                       column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.SIX]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.SEVEN]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.EIGHT]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[NumericConstants.NINE]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.TEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.ELEVEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                    
                    //Discount % of Ex-Factory - Actuals
                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    value = String.valueOf(obj[NumericConstants.THIRTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    //Discount % of Ex-Factory - Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    value = String.valueOf(obj[NumericConstants.FOURTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);
                    
                        /*New discount means add at List */
                        discountDollarList.add(totalDiscDollar);
                        discountperList.add(totalDiscPer);
                        totalRPUList.add(totalRPU);
                        discountPercentageExFactoryList.add(discountPercentageExFactory);
                        oldDiscount = newDiscount;
                    } else {


                        /*Empty the DTO */
                        totalDiscDollar = new ProjectionResultsDTO();
                        totalDiscPer = new ProjectionResultsDTO();
                        totalRPU = new ProjectionResultsDTO();
                        discountPercentageExFactory = new ProjectionResultsDTO();

                        oldDiscount = newDiscount;
                        totalDiscDollar.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        totalDiscPer.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        totalRPU.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        discountPercentageExFactory.setGroup(String.valueOf(obj[NumericConstants.FIVE]));

                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                        }

                        column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.SIX]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.SEVEN]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.EIGHT]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[NumericConstants.NINE]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.TEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.ELEVEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                     /*New discount means add at List */
                        discountDollarList.add(totalDiscDollar);
                        discountperList.add(totalDiscPer);
                        totalRPUList.add(totalRPU);
                        discountPercentageExFactoryList.add(discountPercentageExFactory);
                    }
                    
                    //Discount % of Ex-Factory - Actuals
                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    value = String.valueOf(obj[NumericConstants.THIRTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    //Discount % of Ex-Factory - Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    value = String.valueOf(obj[NumericConstants.FOURTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);
                }

            } else {
                finaldiscountlist.add(discountDollarList);
                finaldiscountlist.add(discountperList);
                finaldiscountlist.add(totalRPUList);
                finaldiscountlist.add(discountPercentageExFactoryList);

                String key = oldHierarchyNo;
                Object[] oldObj;
                discountMap.put(key, finaldiscountlist);
                oldHierarchyNo = newHierarchyNo;
                finaldiscountlist = new ArrayList<>();
                discountDollarList = new ArrayList<>();
                discountperList = new ArrayList<>();
                totalRPUList = new ArrayList<>();
                discountPercentageExFactoryList = new ArrayList<>();
                
                       /*Empty the DTO */
                        totalDiscDollar = new ProjectionResultsDTO();
                        totalDiscPer = new ProjectionResultsDTO();
                        totalRPU = new ProjectionResultsDTO();
                        discountPercentageExFactory = new ProjectionResultsDTO();
                        
                        totalDiscDollar.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        totalDiscPer.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        totalRPU.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        discountPercentageExFactory.setGroup(String.valueOf(obj[NumericConstants.FIVE]));
                        
                        newDiscount=String.valueOf(obj[NumericConstants.FIVE]);
                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                        }

                       column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.SIX]==null){
                        totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.SEVEN]==null){
                         totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                    }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.EIGHT]==null){
                        totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                   if(obj[NumericConstants.NINE]==null){
                       totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                   }

                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    if(obj[NumericConstants.TEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }

                    //Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.ELEVEN]==null){
                        totalRPU.addStringProperties(column, StringUtils.EMPTY); 
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                    }
                    
                    //Discount % of Ex-Factory - Actuals
                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    value = String.valueOf(obj[NumericConstants.THIRTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    //Discount % of Ex-Factory - Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    value = String.valueOf(obj[NumericConstants.FOURTEEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);
                    
                 /*New discount means add at List */
                discountDollarList.add(totalDiscDollar);
                discountperList.add(totalDiscPer);
                totalRPUList.add(totalRPU);
                discountPercentageExFactoryList.add(discountPercentageExFactory);
                        oldDiscount = newDiscount;           
            }
            if (i == count - 1) {
                discountDollarList.add(totalDiscDollar);
                discountperList.add(totalDiscPer);
                totalRPUList.add(totalRPU);
                discountPercentageExFactoryList.add(discountPercentageExFactory);
                finaldiscountlist.add(discountDollarList);
                finaldiscountlist.add(discountperList);
                finaldiscountlist.add(totalRPUList);
                finaldiscountlist.add(discountPercentageExFactoryList);
                String key = oldHierarchyNo;
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
            newValue = newValue / NumericConstants.HUNDRED;
        }
        value = FORMAT.format(newValue);
        value = value.replace("$", "");
        value = value + "%";

        return value;
    }

    private void calculateAndCustomize_variable(List<Object[]> rawList, boolean isTotal) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key;
            int indexValue = 0;
            if (isTotal) {
                indexValue = indexPivot;
                key = StringConstantsUtil.TOTAL;
            } else {
                indexValue = indexDetail;
                key = obj[baseColumn_hierarchy_index].toString() +("null".equals(String.valueOf(obj[obj.length - 5])) ? StringUtils.EMPTY : "$"+ String.valueOf(obj[obj.length - 5]));
            }
            List<ProjectionResultsDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[isTotal ? base_Column_Year : NumericConstants.FIVE].toString()), Integer.valueOf(obj[isTotal ? base_Column_frequency : NumericConstants.SIX].toString()));
            String groupId = common.get(1);
             String pcommonColumn = common.get(0);
            
            List<String> periodList = new ArrayList<>(selection.getPeriodList());
            
            ProjectionResultsDTO freVarianceDTO = new ProjectionResultsDTO();
            if (periodList.contains(pcommonColumn) || (frequencyDivision==NumericConstants.TWELVE && periodList.contains(pcommonColumn.toLowerCase()))) {
                periodList.remove(pcommonColumn);
                      
                freVarianceDTO.setGroup(groupId);
            
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList_pivot(pvList, obj, freVarianceDTO, indexValue, isTotal, key);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList_pivot(obj, freVarianceDTO, indexValue, isTotal, key);
            }
          }
        }
    }
    
   private void addList_pivot(List<ProjectionResultsDTO> pvList, Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal, boolean isTotal, String key) {

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
                detail.setHierarchyNo(obj[1].toString());
                detail.setParentHierarchyNo(obj[obj.length - 5] == null ? null : obj[obj.length - 5].toString());
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), selection.getHierarchyIndicator());
            }
            detail.setGroup(groupName);
            pvList.add(detail);
        }

        //Ex-Factory-Product
        calculateForTotal("exFactory", obj, isTotal ? 1 : NumericConstants.THREE, frequencyBasedDTO, Boolean.TRUE);

        //Ex-Factory-Customer
        calculateForTotal("custExFactory", obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO, Boolean.TRUE);

        //Demand
        calculateForTotal("demand", obj, indexForTotal + NumericConstants.TWENTY_FOUR, frequencyBasedDTO, Boolean.TRUE);

        //Adjusted Demand
        calculateForTotal("adjDemand", obj, indexForTotal + NumericConstants.FORTY_TWO, frequencyBasedDTO, Boolean.TRUE);

        //Inventory Withdraw summary
        calculateForTotal("inventory", obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO,Boolean.TRUE);

        //Inventory Withdraw Details
        calculateForTotal("inventoryDetails", obj, indexForTotal + NumericConstants.FORTY_SIX, frequencyBasedDTO,Boolean.TRUE);

        //% of Ex-Factory Product
        calculateForTotal("perExFactory", obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO,  Boolean.FALSE);

        //% of Ex-Factory Customer
        calculateForTotal("perCustExFactory", obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO,  Boolean.FALSE);

        //% of Demand
        calculateForTotal("perDemand", obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO,  Boolean.FALSE);

        //% of Adjusted Demand
        calculateForTotal("perAdjDemand", obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO,  Boolean.FALSE);

        //% of inventory Withdraw Summary
        calculateForTotal("perInventory", obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO,  Boolean.FALSE);

        //% of inventory Withdraw Detail
        calculateForTotal("perInventoryDetails", obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO,  Boolean.FALSE);

        //Contract Sales @ WAC
        calculateForTotal("conSalesWac", obj, indexForTotal, frequencyBasedDTO,  Boolean.TRUE);

        //Contract Units
        calculateForTotal(StringConstantsUtil.UNIT_VOL_PROPERTY, obj, indexForTotal + NumericConstants.TWO, frequencyBasedDTO,  Boolean.TRUE);

        //Discount $
        calculateForTotal(StringConstantsUtil.TOT_DIS_DOL_PROPERTY, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO,  Boolean.TRUE);

        //Discount %
        calculateForTotal(StringConstantsUtil.TOT_DIS_PER_PROPERTY, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO,  Boolean.FALSE);

        //RPU
        calculateForTotal(StringConstantsUtil.TOTAL_RPU_PROPERTY, obj, indexForTotal + NumericConstants.THIRTY_SIX, frequencyBasedDTO,  Boolean.TRUE);
        
        //Discount % of Ex-Factory
       int index = isTotal ? indexForTotal + NumericConstants.FIFTY_SIX : indexForTotal + NumericConstants.FIFTY_SIX + 1;
       calculateForTotal(StringConstantsUtil.DIS_PER_EX_FACTORY_PROPERTY, obj, index, frequencyBasedDTO, false);

        //Net Sales 
        calculateForTotal("netSales", obj, indexForTotal + NumericConstants.TWENTY_TWO, frequencyBasedDTO,  Boolean.TRUE);
        
        //Net Sales % of Ex-Factory
        index = isTotal ? indexForTotal + NumericConstants.FIFTY_FOUR : indexForTotal + NumericConstants.FIFTY_FOUR + 1;
        calculateForTotal("netSalesPerExFactory", obj, index, frequencyBasedDTO, false);

        //COGS
        calculateForTotal("cogs", obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO,  Boolean.TRUE);

        //Net Profit
        calculateForTotal("netProfit", obj, indexForTotal + NumericConstants.FORTY, frequencyBasedDTO,  Boolean.TRUE);

        String commonColumn=StringUtils.EMPTY;
       if (frequencyDivision == NumericConstants.FOUR) {
           if (isTotal) {
               commonColumn = "Q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
           } else {
               commonColumn = "Q" + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
           }
       } else if (frequencyDivision == NumericConstants.TWO) {
           if (isTotal) {
               commonColumn = "S" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
           } else {
               commonColumn = "S" + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
           }
       } else if (frequencyDivision == 1) {
           if (isTotal) {
               commonColumn = String.valueOf(obj[NumericConstants.THREE]);
           } else {
               commonColumn = String.valueOf(obj[NumericConstants.FIVE]);
           }
       } else if (frequencyDivision == NumericConstants.TWELVE) {
           if (isTotal) {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
               commonColumn = monthName.toLowerCase() + obj[NumericConstants.THREE];
           } else {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])) - 1);
               commonColumn = monthName.toLowerCase() + obj[NumericConstants.FIVE];
           }
       }
       Map<String, ProjectionResultsDTO> valueMap=null;
       if(isTotal){
            valueMap = totalpivotDiscountMap.get(StringConstantsUtil.TOTAL);
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

    private void updateList_pivot(Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal, boolean isTotal, String key) {
        List list = resultMap.get(key);
        //Ex-Factory-Product
        calculateForTotal("exFactory", obj, isTotal ? 1 : NumericConstants.THREE, frequencyBasedDTO, Boolean.TRUE);

        //Ex-Factory-Customer
        calculateForTotal("custExFactory", obj, indexForTotal + NumericConstants.FORTY_FOUR, frequencyBasedDTO,Boolean.TRUE);

        //Demand
        calculateForTotal("demand", obj, indexForTotal + NumericConstants.TWENTY_FOUR, frequencyBasedDTO, Boolean.TRUE);

        //Adjusted Demand
        calculateForTotal("adjDemand", obj, indexForTotal + NumericConstants.FORTY_TWO, frequencyBasedDTO, Boolean.TRUE);

        //Inventory Withdraw summary
        calculateForTotal("inventory", obj, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, Boolean.TRUE);

        //Inventory Withdraw Details
        calculateForTotal("inventoryDetails", obj, indexForTotal + NumericConstants.FORTY_SIX, frequencyBasedDTO,Boolean.TRUE);

        //% of Ex-Factory Product
        calculateForTotal("perExFactory", obj, indexForTotal + NumericConstants.EIGHT, frequencyBasedDTO,  Boolean.FALSE);

        //% of Ex-Factory Customer
        calculateForTotal("perCustExFactory", obj, indexForTotal + NumericConstants.FIFTY, frequencyBasedDTO,  Boolean.FALSE);

        //% of Demand
        calculateForTotal("perDemand", obj, indexForTotal + NumericConstants.TWENTY_EIGHT, frequencyBasedDTO,  Boolean.FALSE);

        //% of Adjusted Demand
        calculateForTotal("perAdjDemand", obj, indexForTotal + NumericConstants.FORTY_EIGHT, frequencyBasedDTO,  Boolean.FALSE);

        //% of inventory Withdraw Summary
        calculateForTotal("perInventory", obj, indexForTotal + NumericConstants.THIRTY, frequencyBasedDTO,  Boolean.FALSE);

        //% of inventory Withdraw Detail
        calculateForTotal("perInventoryDetails", obj, indexForTotal + NumericConstants.FIFTY_TWO, frequencyBasedDTO,  Boolean.FALSE);

        //Contract Sales @ WAC
        calculateForTotal("conSalesWac", obj, indexForTotal, frequencyBasedDTO,  Boolean.TRUE);

        //Contract Units
        calculateForTotal(StringConstantsUtil.UNIT_VOL_PROPERTY, obj, indexForTotal + NumericConstants.TWO, frequencyBasedDTO,  Boolean.TRUE);

        //Discount $
        calculateForTotal(StringConstantsUtil.TOT_DIS_DOL_PROPERTY, obj, indexForTotal + NumericConstants.FOUR, frequencyBasedDTO,  Boolean.TRUE);

        //Discount %
        calculateForTotal(StringConstantsUtil.TOT_DIS_PER_PROPERTY, obj, indexForTotal + NumericConstants.SIX, frequencyBasedDTO,  Boolean.FALSE);

        //RPU
        calculateForTotal(StringConstantsUtil.TOTAL_RPU_PROPERTY, obj, indexForTotal + NumericConstants.THIRTY_SIX, frequencyBasedDTO,  Boolean.TRUE);

        //Discount % of Ex-Factory
        int index = isTotal ? indexForTotal + NumericConstants.FIFTY_SIX : indexForTotal + NumericConstants.FIFTY_SIX + 1;
        calculateForTotal(StringConstantsUtil.DIS_PER_EX_FACTORY_PROPERTY, obj, index, frequencyBasedDTO, false);

        //Net Sales 
        calculateForTotal("netSales", obj, indexForTotal + NumericConstants.TWENTY_TWO, frequencyBasedDTO,  Boolean.TRUE);

        //Net Sales % of Ex-Factory
        index = isTotal ? indexForTotal + NumericConstants.FIFTY_FOUR : indexForTotal + NumericConstants.FIFTY_FOUR + 1;
        calculateForTotal("netSalesPerExFactory", obj, index, frequencyBasedDTO, false);

        //COGS
        calculateForTotal("cogs", obj, indexForTotal + NumericConstants.THIRTY_EIGHT, frequencyBasedDTO,  Boolean.TRUE);

        //Net Profit
        calculateForTotal("netProfit", obj, indexForTotal + NumericConstants.FORTY, frequencyBasedDTO,  Boolean.TRUE);

      
         String commonColumn=StringUtils.EMPTY;
       if (frequencyDivision == NumericConstants.FOUR) {
           if (isTotal) {
               commonColumn = "Q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
           } else {
               commonColumn = "Q" + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
           }
       } else if (frequencyDivision == NumericConstants.TWO) {
           if (isTotal) {
               commonColumn = "S" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
           } else {
               commonColumn = "S" + obj[NumericConstants.SIX] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
           }
       } else if (frequencyDivision == 1) {
           if (isTotal) {
               commonColumn = String.valueOf(obj[NumericConstants.THREE]);
           } else {
               commonColumn = String.valueOf(obj[NumericConstants.FIVE]);
           }
       } else if (frequencyDivision == NumericConstants.TWELVE) {
           if (isTotal) {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
               commonColumn = monthName.toLowerCase() + obj[NumericConstants.THREE];
           } else {
               String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])) - 1);
               commonColumn = monthName.toLowerCase() + obj[NumericConstants.FIVE];
           }
       }
        Map<String, ProjectionResultsDTO> valueMap = null;
        if (isTotal) {
            valueMap = totalpivotDiscountMap.get(StringConstantsUtil.TOTAL);
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
    
    
    private void calculateForTotal(String variableName, Object[] obj, int index, ProjectionResultsDTO pvDTO, boolean isAmtformat) {
        String val;
        /*Actuals */
if(     StringConstantsUtil.UNIT_VOL_PROPERTY.equals(variableName)){
         val=String.valueOf(obj[index]);
            String baseValue = getFormattedValue(UNIT, val);
            pvDTO.addStringProperties(variableName + Constants.LabelConstants.ACTUALS.getConstant(),baseValue);
        }else{
        val = getFormattedValue(isAmtformat ? AMOUNT : RATE_PER, isNull(String.valueOf(obj[index])));
        pvDTO.addStringProperties(variableName + Constants.LabelConstants.ACTUALS.getConstant(), isAmtformat ? val : val+PERCENT);
        }



        /*Projection*/
        if (StringConstantsUtil.UNIT_VOL_PROPERTY.equals(variableName)) {
            val = String.valueOf(obj[index+ 1]);
            String baseValue = getFormattedValue(UNIT, val);
            pvDTO.addStringProperties(variableName + Constants.LabelConstants.PROJECTIONS.getConstant(), baseValue);
        } else {
            val = getFormattedValue(isAmtformat ? AMOUNT : RATE_PER, isNull(String.valueOf(obj[index + 1])));
            pvDTO.addStringProperties(variableName + Constants.LabelConstants.PROJECTIONS.getConstant(), isAmtformat ? val : val + PERCENT);
        }

    }

    private void calculate_Total_Discount() {
        String column;
        String value;
        String baseValue;
        ProjectionResultsDTO totalDiscDollar=new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscPer=new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPU=new ProjectionResultsDTO();
        ProjectionResultsDTO discountPercentageExFactory=new ProjectionResultsDTO();
        String oldDiscount=StringUtils.EMPTY;
        int count=procRawList_total_discount.size();
        String commonColumn = StringUtils.EMPTY;
        List<ProjectionResultsDTO> discountDollarList=new ArrayList<>();
        List<ProjectionResultsDTO> discountperList=new ArrayList<>();
        List<ProjectionResultsDTO> totalRPUList=new ArrayList<>();
        List<ProjectionResultsDTO> discountPercentageExFactoryList=new ArrayList<>();
        List<List<ProjectionResultsDTO>> finaldiscountlist=new ArrayList<>();
        for(int i=0;i<count;i++){
            Object[] obj = (Object[])procRawList_total_discount.get(i);
            String newDiscount=String.valueOf(obj[NumericConstants.THREE]);
            if(oldDiscount.equals(newDiscount)){
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "s" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[1];
                }
                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if( obj[NumericConstants.FOUR]==null){
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOUR])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                if (obj[NumericConstants.FIVE] == null) {
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                } else {
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                   if(obj[NumericConstants.SIX]==null){
                    totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                      if(obj[NumericConstants.SEVEN]==null){
                    totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                      }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if(obj[NumericConstants.EIGHT]==null){
                    totalRPU.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.NINE]==null){
                    totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                }
                    
                //Discount % of Ex-Factory - Actuals
                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                value = String.valueOf(obj[NumericConstants.TEN]);
                baseValue = getFormattedValuePer(AMOUNT, value);
                discountPercentageExFactory.addStringProperties(column, baseValue);

                //Discount % of Ex-Factory - Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                value = String.valueOf(obj[NumericConstants.ELEVEN]);
                baseValue = getFormattedValuePer(AMOUNT, value);
                discountPercentageExFactory.addStringProperties(column, baseValue);
                
            }else{
                if(i==0){
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "s" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[1];
                }

                totalDiscDollar.setGroup(String.valueOf(obj[NumericConstants.THREE]));
                totalDiscPer.setGroup(String.valueOf(obj[NumericConstants.THREE]));
                totalRPU.setGroup(String.valueOf(obj[NumericConstants.THREE]));
                discountPercentageExFactory.setGroup(String.valueOf(obj[NumericConstants.THREE]));

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if( obj[NumericConstants.FOUR]==null){
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOUR])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                if (obj[NumericConstants.FIVE] == null) {
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                } else {
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                   if(obj[NumericConstants.SIX]==null){
                    totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                      if(obj[NumericConstants.SEVEN]==null){
                    totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                      }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if(obj[NumericConstants.EIGHT]==null){
                    totalRPU.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.NINE]==null){
                    totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                }
                    //Discount % of Ex-Factory - Actuals
                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    value = String.valueOf(obj[NumericConstants.TEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    //Discount % of Ex-Factory - Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    value = String.valueOf(obj[NumericConstants.ELEVEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    oldDiscount=newDiscount;
                }else{

                /*New discount means add at List */
                discountDollarList.add(totalDiscDollar);
                discountperList.add(totalDiscPer);
                totalRPUList.add(totalRPU);
                discountPercentageExFactoryList.add(discountPercentageExFactory);

                /*Empty the DTO */
                totalDiscDollar = new ProjectionResultsDTO();
                totalDiscPer = new ProjectionResultsDTO();
                totalRPU = new ProjectionResultsDTO();
                discountPercentageExFactory = new ProjectionResultsDTO();

                    oldDiscount=newDiscount;
                totalDiscDollar.setGroup(String.valueOf(obj[NumericConstants.THREE]));
                totalDiscPer.setGroup(String.valueOf(obj[NumericConstants.THREE]));
                totalRPU.setGroup(String.valueOf(obj[NumericConstants.THREE]));
                discountPercentageExFactory.setGroup(String.valueOf(obj[NumericConstants.THREE]));

                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "s" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[1];
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if( obj[NumericConstants.FOUR]==null){
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOUR])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                if (obj[NumericConstants.FIVE] == null) {
                    totalDiscDollar.addStringProperties(column, StringUtils.EMPTY);
                } else {
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalDiscDollar.addStringProperties(column, baseValue);
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                   if(obj[NumericConstants.SIX]==null){
                    totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                   }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                      if(obj[NumericConstants.SEVEN]==null){
                    totalDiscPer.addStringProperties(column, StringUtils.EMPTY);
                      }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    totalDiscPer.addStringProperties(column, baseValue);
                }

                column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                  if(obj[NumericConstants.EIGHT]==null){
                    totalRPU.addStringProperties(column, StringUtils.EMPTY);
                  }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                }

                //Projection
                column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    if(obj[NumericConstants.NINE]==null){
                    totalRPU.addStringProperties(column, StringUtils.EMPTY);
                    }else{
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    totalRPU.addStringProperties(column, baseValue);
                }

                    //Discount % of Ex-Factory - Actuals
                    column = commonColumn + Constants.LabelConstants.ACTUALS.getConstant();
                    value = String.valueOf(obj[NumericConstants.TEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);

                    //Discount % of Ex-Factory - Projection
                    column = commonColumn + Constants.LabelConstants.PROJECTIONS.getConstant();
                    value = String.valueOf(obj[NumericConstants.ELEVEN]);
                    baseValue = getFormattedValuePer(AMOUNT, value);
                    discountPercentageExFactory.addStringProperties(column, baseValue);
            }
            }
                   if(i==count-1){

                discountDollarList.add(totalDiscDollar);
                discountperList.add(totalDiscPer);
                totalRPUList.add(totalRPU);
                discountPercentageExFactoryList.add(discountPercentageExFactory);

                finaldiscountlist.add(discountDollarList);
                finaldiscountlist.add(discountperList);
                finaldiscountlist.add(totalRPUList);
                finaldiscountlist.add(discountPercentageExFactoryList);
                    discountMap.put(StringConstantsUtil.TOTAL,finaldiscountlist);
            }

        }
    }
    
    
        private void customize_discount_pivot() {
        int count = procRawList_detail_discount.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String newyear;
        String oldYear = StringUtils.EMPTY;
        String newPeriod;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        String oldDiscount = StringUtils.EMPTY;
        String newDiscount;
        ProjectionResultsDTO discountDto = new ProjectionResultsDTO();
        Map<String, ProjectionResultsDTO> periodDiscountMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[1]) + ("null".equals(String.valueOf(obj[obj.length - 3])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 3]));
                oldDiscount = String.valueOf(obj[NumericConstants.FIVE]);
            }
            String newHierarchyNo = String.valueOf(obj[1]) + ("null".equals(String.valueOf(obj[obj.length - 3])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 3]));
            newyear = String.valueOf(obj[NumericConstants.THREE]);
            newPeriod = String.valueOf(obj[NumericConstants.FOUR]);
            newDiscount = String.valueOf(obj[NumericConstants.FIVE]);
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldDiscount.equals(newDiscount)) {
                    if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                        setBase_Value(discountDto, obj, false);
                    } else {
                        periodDiscountMap.put(commonColumn, discountDto);
                        discountDto = new ProjectionResultsDTO();
                        commonColumn = getVisibleColumn_Header(obj, false);
                        setBase_Value(discountDto, obj, false);
                        oldYear = newyear;
                        oldPeriod = newPeriod;
                    }
                    oldDiscount = newDiscount;
                } else {
                    if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                        setBase_Value(discountDto, obj, false);
                    } else {
                        periodDiscountMap.put(commonColumn, discountDto);
                        discountDto = new ProjectionResultsDTO();
                        commonColumn = getVisibleColumn_Header(obj, false);
                        setBase_Value(discountDto, obj, false);
                        oldYear = newyear;
                        oldPeriod = newPeriod;
                    }
                    oldDiscount = newDiscount;
                }
            } else {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
                periodDiscountMap = new HashMap<>();
                discountDto = new ProjectionResultsDTO();
                commonColumn = getVisibleColumn_Header(obj, false);
                setBase_Value(discountDto, obj, false);
                oldYear = newyear;
                oldPeriod = newPeriod;
                oldHierarchyNo = newHierarchyNo;
                oldDiscount = newDiscount;
            }
            if (i == count - 1) {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
            }
            
        }
    }  
          
          
    private void setBase_Value(ProjectionResultsDTO discountDto, Object[] obj,boolean istotal) {
        
        String visibleColumn = StringConstantsUtil.TOT_DIS_DOL_PROPERTY + String.valueOf(istotal?obj[NumericConstants.THREE]:obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.ACTUALS.getConstant();
        
        String value=getCellValue(istotal?obj[NumericConstants.FOUR]:obj[NumericConstants.SIX], StringConstantsUtil.AMOUNT1);
        discountDto.addStringProperties(visibleColumn, value);
        
        visibleColumn = StringConstantsUtil.TOT_DIS_DOL_PROPERTY + String.valueOf(istotal?obj[NumericConstants.THREE]:obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.PROJECTIONS.getConstant();
        value=getCellValue(istotal?obj[NumericConstants.FIVE]:obj[NumericConstants.SEVEN], StringConstantsUtil.AMOUNT1);
        discountDto.addStringProperties(visibleColumn, value);
        
       
        visibleColumn = StringConstantsUtil.TOT_DIS_PER_PROPERTY + String.valueOf(istotal?obj[NumericConstants.THREE]:obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.ACTUALS.getConstant();
        value=getCellValue(istotal?obj[NumericConstants.SIX]:obj[NumericConstants.EIGHT], "Rate");
        
        discountDto.addStringProperties(visibleColumn, value);
        
        visibleColumn = StringConstantsUtil.TOT_DIS_PER_PROPERTY + String.valueOf(istotal?obj[NumericConstants.THREE]:obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.PROJECTIONS.getConstant();
        value=getCellValue(istotal?obj[NumericConstants.SEVEN]:obj[NumericConstants.NINE], "Rate");
        discountDto.addStringProperties(visibleColumn, value);

        visibleColumn = StringConstantsUtil.TOTAL_RPU_PROPERTY + String.valueOf(istotal?obj[NumericConstants.THREE]:obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.ACTUALS.getConstant();
        value=getCellValue(istotal?obj[NumericConstants.EIGHT]:obj[NumericConstants.TEN], StringConstantsUtil.AMOUNT1);
        discountDto.addStringProperties(visibleColumn, value);
        
        visibleColumn = StringConstantsUtil.TOTAL_RPU_PROPERTY + String.valueOf(istotal?obj[NumericConstants.THREE]:obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) +Constants.LabelConstants.PROJECTIONS.getConstant();
        value=getCellValue(istotal?obj[NumericConstants.NINE]:obj[NumericConstants.ELEVEN], StringConstantsUtil.AMOUNT1);
        discountDto.addStringProperties(visibleColumn, value);
        
        //Discount % of Ex-Factory
        visibleColumn = StringConstantsUtil.DIS_PER_EX_FACTORY_PROPERTY + String.valueOf(istotal ? obj[NumericConstants.THREE] : obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) + Constants.LabelConstants.ACTUALS.getConstant();
        value = getCellValue(istotal ? obj[NumericConstants.TEN] : obj[NumericConstants.THIRTEEN], "Rate");
        discountDto.addStringProperties(visibleColumn, value);

        visibleColumn = StringConstantsUtil.DIS_PER_EX_FACTORY_PROPERTY + String.valueOf(istotal ? obj[NumericConstants.THREE] : obj[NumericConstants.FIVE]).replaceAll(" ", StringUtils.EMPTY) + Constants.LabelConstants.PROJECTIONS.getConstant();
        value = getCellValue(istotal ? obj[NumericConstants.ELEVEN] : obj[NumericConstants.FOURTEEN], "Rate");
        discountDto.addStringProperties(visibleColumn, value);
       }
    
    
    private String getCellValue(Object obj,String type)
    {
      String returnValue=StringUtils.EMPTY;
      if(obj==null){
          return returnValue;
      }else{
          returnValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj)));
          if(StringConstantsUtil.AMOUNT1.equals(type)){
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
        if (frequencyDivision == NumericConstants.FOUR) {
            if (istotal) {
                commonColumn = "Q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
            } else {
                commonColumn = "Q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            }
        } else if (frequencyDivision == NumericConstants.TWO) {
            if (istotal) {
                commonColumn = "S" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
            } else {
                commonColumn = "S" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            }
        } else if (frequencyDivision == 1) {
            if (istotal) {
                commonColumn = StringUtils.EMPTY + obj[1];
            } else {
                commonColumn = StringUtils.EMPTY + obj[NumericConstants.THREE];
            }
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            if (istotal) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) - 1);
                commonColumn = monthName.toLowerCase() + obj[1];
            } else {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                commonColumn = monthName.toLowerCase() + obj[NumericConstants.THREE];
            }
        }
        return commonColumn;
    }
    
    
     
        private void total_Discount_Customize()
        {
        int count=procRawList_discount.size();
        String newyear;
        String oldYear = StringUtils.EMPTY;
        String newPeriod;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        ProjectionResultsDTO discountDto = new ProjectionResultsDTO();
       Map<String, ProjectionResultsDTO> periodDiscountMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_discount.get(i);
            newyear = String.valueOf(obj[1]);
            newPeriod = String.valueOf(obj[NumericConstants.TWO]);
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
                totalpivotDiscountMap.put(StringConstantsUtil.TOTAL, periodDiscountMap);
            }
        }
        }
        
    private void calculateAndCustomize_periodForCustomView(List<Object[]> rawList, boolean isTotal) {
        String appendedParentKey = "";
        for (int i = 0; i < rawList.size(); i++) {
            Object[] obj = rawList.get(i);
            String key;
            if (isTotal) {
                key = StringConstantsUtil.TOTAL;
            } else if (isCustomView) {
                key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
                key = key + appendedParentKey;
            } else {
                key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
            }
            List<ProjectionResultsDTO> pvList = resultMap.get(key);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList(pvList, obj, index, isTotal);
                appendedParentKey = obj[obj.length - 5] == null ? "" : "$" + obj[obj.length - 5].toString();
                if (isCustomView && !isTotal) {
                    customhierarchyAndTP_keys(obj, key, pvList);
                } else {
                    hierarchyAndTP_keys(obj, key, pvList);
                }
            } else if (obj[obj.length - 5] != null && !isTotal) {
                Object[] temp = rawList.get(i - 1) == null ? new Object[0] : rawList.get(i - 1);
                String currValue = obj[obj.length - 5].toString();
                String tempValue = temp[temp.length - 5] == null ? "oldtempValue"
                        : temp[temp.length - 5].toString();
                if (!currValue.equals(tempValue)) {
                    pvList = new ArrayList();
                    addList(pvList, obj, index, isTotal);
                    if (isCustomView && !isTotal) {
                        customhierarchyAndTP_keys(obj, key, pvList);
                    } else {
                        hierarchyAndTP_keys(obj, key, pvList);
                    }
                    appendedParentKey = obj[obj.length - 5] == null ? "" : "$" + obj[obj.length - 5].toString();
                } else {
                    updateList(pvList, obj, index, isTotal);
                }
            } else {
                updateList(pvList, obj, index, isTotal);
            }
        }
    }

    private void customhierarchyAndTP_keys(Object[] obj, String key, List<ProjectionResultsDTO> pvList) {
        String parentKey = obj[obj.length - 5] == null ? null : obj[obj.length - 5].toString();
        String newKey;
        if (parentKey == null) {
            hierarchyKeys.add(key);
            resultMap.put(key, pvList);
        } else if (!hierarchyKeys.contains(key)) {
            if (key.contains("$")) {
                key = (key.split("\\$"))[0];
            }
            newKey = key + "$" + parentKey; //$ delimiter for key and parent key
            hierarchyKeys.add(newKey);
            resultMap.put(newKey, pvList);
        }
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
            tradingPartnerKeys.add(key);
        }
    }
        
        
        
}