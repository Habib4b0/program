/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionvariance.logic;

import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.CHANGE;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.VALUE;
import static com.stpl.app.utils.Constants.LabelConstants.VARIANCE;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikeyans
 */
public class NMPVExcelLogic {

    public static final Logger LOGGER = Logger.getLogger(NMPVExcelLogic.class);
    private ProjectionVarianceDTO exFacValue, exFacVar, exFacPer, demandValue, demandVar, demandPer, invWithValue, invWithVar,
            invWithPer, perExFacValue, perExFacVar, perExFacPer, perDemandValue, perDemandVar, perDemandPer, perInvWithValue, perInvWithVar, perInvWithPer, salesValue, salesVar,
            salesPer, unitsValue, unitsVar, unitsPer, disDollValue, disDollVar, disDollPer, disPerValue, disPerVar, disPerPer, rpuValue, rpuVar, rpuPer, netSalesValue, netSalesVar,
            netSalesPer, cogsValue, cogsVar, cogsPer, netProfitValue, netProfitVar, netProfitPer;

    private final Map<String, List<ProjectionVarianceDTO>> resultMap;

    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final PVSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final DecimalFormat RATE_PERC = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String CURRENT = "Current";
    private static final int COLUMN_COUNT = 14;
    private static final int COLUMN_COUNT_DISCOUNT = 3;
    private static final int COLUMN_COUNT_TOTAL = 21;
    private static final int BASE_COLUMN_COUNT = 10;
    private final int index = 5;
    private final int index_detail_discount = 4;
    private int frequencyDivision;
    private final int baseColumn_levelName_index = 0;
    private final int baseColumn_levelValue_index = 2;
    private final int baseColumn_hierarchy_index = 1;
    private final int baseColumn_hierarchyIndicator_index = 2;
    private final int baseColumn_period_index = 3;
    private final int baseColumn_year_index = 4;
    private final int baseColumn_period_discount_index = 2;
    private final int baseColumn_year_discount_index = 3;
    private final int baseColumn_period_indexForTotal = 1;
    private final int baseColumn_year_indexForTotal = 0;
    private final int baseColumn_discount_index = 0;
    private final List<Object[]> procRawList_total = new ArrayList();
    private final List<Object[]> procRawList_detail = new ArrayList();
    private final List<Object[]> procRawList_detail_discount = new ArrayList();
    private final List<Integer> priorList = new ArrayList();
    private boolean isTotal = false;
    private String levelFilterValue = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String viewValue = StringUtils.EMPTY;
    private static final String PRC_PROJ_RESULTS = "PRC_PROJECTION_RESULTS";
    private static final String PRC_PV_SELECTION = "PRC_PROJECTION_VARIANCE";
    private static String DASH = "-";
    List<Object> pivotDiscountList = new ArrayList<Object>();
    List<ProjectionVarianceDTO> discountList = new ArrayList<ProjectionVarianceDTO>();
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat("#,##0.00");
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private static final String DETAIL = "Detail";
    List<Object> pivotTotalList = new ArrayList<Object>();
    List<Integer> pivotPriorProjIdList = new ArrayList();
    private final Map<String, PVParameters> parameters = new HashMap();
    private final Map<String, String> customView_relationship_hierarchy = new HashMap();
    PVParameters parameterDto;
    private boolean discountFlag;
    private boolean isCustomView;
     Map<String,String> discountNameMap=new HashMap<String,String>();
         Map<String,Map<String,ProjectionVarianceDTO>> pivotDiscountMap=new HashMap<String,Map<String,ProjectionVarianceDTO>>();

    public NMPVExcelLogic(Map<String, List<ProjectionVarianceDTO>> resultMap, PVSelectionDTO selection,
            List<String> hierarchyKeys, List<String> tradingPartnerKeys, List<String> discountKeys, PVParameters parameterDto) {
        this.resultMap = resultMap;
        this.selection = selection;
        this.hierarchyKeys = hierarchyKeys;
        this.tradingPartnerKeys = tradingPartnerKeys;
        this.discountKeys = discountKeys;
        this.parameterDto = parameterDto;
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
        priorList.clear();
        priorList.addAll(selection.getProjIdList());
        discountKeys.clear();
        isTotal = "Total".equalsIgnoreCase(selection.getLevel());
        discountFlag = !"Total Discount".equalsIgnoreCase(selection.getDiscountLevel());
        isCustomView = selection.getHierarchyIndicator().equals(StringUtils.EMPTY);
   
        if (isCustomView) {
            customView_relationship_hierarchy.putAll(getGroup_customView(parameterDto.getCustomViewMasterSid()));
        }
//        frequencyDivision = selection.getFrequencyDivision();
      
        boolean isRefresh = isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());
        long start = System.currentTimeMillis();
        long end;

        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
           
            if (isTotal) {
               
//                if (isRefresh && procRawList_total.isEmpty()) {
                getTotalRawData();
                getTotalPivotVariance(selection);
                if (discountFlag) {
                    getTotalDiscount(selection);
                }

//                }
                end = System.currentTimeMillis();
               
                start = end;
                calculateAndCustomize_total_period(procRawList_total);
                if (discountFlag) {
                    discount_Customize();
                }
                end = System.currentTimeMillis();
               
            } else {
                start = System.currentTimeMillis();
                executeProcedure_PRC_PV_SELECTION();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                    executeProcedure_PRC_PV_SELECTION();
                }
                end = System.currentTimeMillis();
               
                calculateAndCustomize_detail_period(procRawList_detail, procRawList_detail_discount);
            }
        } else {
          
            if (isTotal) {
                
                String discLevel = selection.getDiscountLevel();
               
                if (discLevel.equals("Program")) {
                    
                    getTotalPivotVariance(selection);
                    getPivot_customization();

                } else {
//                    if (isRefresh && procRawList_total.isEmpty()) {
                    getTotalRawData();
                    end = System.currentTimeMillis();
                  
                    start = end;
//                    }
                    calculateAndCustomize_total_pivot(procRawList_total);
                    end = System.currentTimeMillis();
                   
                }
            } else {

                start = System.currentTimeMillis();
                executeProcedure_PRC_PV_SELECTION();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                    executeProcedure_PRC_PV_SELECTION();
                }
                end = System.currentTimeMillis();
               
                start = end;
                 customize_discount_pivot();
                calculateAndCustomize_detail_pivot(procRawList_detail, procRawList_detail_discount);
                end = System.currentTimeMillis();
                
            }
            if (DESCENDING.getConstant().equals(selection.getProjectionOrder())) {
                for (Map.Entry<String, List<ProjectionVarianceDTO>> entry : resultMap.entrySet()) {
                    //Take the dto of 1st index (Level Name)
                    ProjectionVarianceDTO dto = entry.getValue().remove(0);
                    //Reverse the period list
                    Collections.reverse(entry.getValue());
                    //Add the level Name in 1st Index
                    entry.getValue().add(0, dto);
                }
            }      
        }
    }

    private boolean isRefreshNeeded(String levelFilterValue, String groupFilterValue, String viewValue, int freDiv) {
        boolean val = this.levelFilterValue.equals(levelFilterValue)
                && this.groupFilterValue.equals(groupFilterValue)
                && this.viewValue.equals(viewValue)
                && this.frequencyDivision == freDiv;
        this.levelFilterValue = levelFilterValue;
        this.groupFilterValue = groupFilterValue;
        this.viewValue = viewValue;
        this.frequencyDivision = freDiv;
        return !val;
    }

//    private List<Object[]> executePVQuery() {
//        String query = CustomSQLUtil.get("");
//        for (Map.Entry<String, String> entry : parameters.entrySet()) {
//            query = query.replace(entry.getKey(), entry.getValue());
//        }
//        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);

//        return rawList == null || rawList.isEmpty() ? Collections.EMPTY_LIST : rawList;
//    }
    private List<Object[]> executeQuery() {
        String query = "select * from dbo.Drop_Table;";

        List<Object[]> rawList = HelperTableLocalServiceUtil.executeSelectQuery(query);

        return rawList == null || rawList.isEmpty() ? Collections.EMPTY_LIST : rawList;
    }

    private void calculateAndCustomize_detail_period(List<Object[]> rawList, List<Object[]> rawList_discount) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
//            it.remove();
            String key = obj[baseColumn_hierarchy_index].toString();
            key = key.substring(key.indexOf('-') + 1);
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList(pvList, obj, index);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList(pvList, obj, index);
            }
        }
        if (discountFlag) {
            Map<String, Integer> discountMap = new HashMap();
            Set<String> hierarchyKey = new HashSet();
            int listIndex = 0;
            for (ListIterator<Object[]> it = rawList_discount.listIterator(); it.hasNext();) {
                Object[] obj = it.next();
//            it.remove();
                String key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
                String discountName = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
                Integer newListIndex = discountMap.get(discountName);
                if (newListIndex == null) {
                    discountMap.put(discountName, listIndex++);
                    hierarchyKey.clear();
                }
                if (hierarchyKey.add(key)) {
                    addList_detail_discount(key, obj);
                } else {
                    updateList_detail_discount(key, obj, newListIndex);
                }
            }
         
        }

    }

    private void hierarchyAndTP_keys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {

        hierarchyKeys.add(key);

        resultMap.put(key, pvList);
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[baseColumn_levelName_index]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[baseColumn_levelName_index]))) {
            tradingPartnerKeys.add(key);
        }
    }

    private void addList(List<ProjectionVarianceDTO> pvList, final Object[] obj, int index) {
        try {

            if (isTotal) {
                ProjectionVarianceDTO total = new ProjectionVarianceDTO();
                total.setGroup("Projection Total");
                pvList.add(total);
            } else {
                ProjectionVarianceDTO detail = new ProjectionVarianceDTO();
                //Group Column projSelDTO

                String groupName;
                if (isCustomView) {
                    groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                    groupName = groupName == null ? "" : groupName;
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
                }

//                    if (groupName != null && !"null".equalsIgnoreCase(groupName)) {
                detail.setGroup(groupName);
                pvList.add(detail);
            }
            //Ex-Factory-Sales
            if (selection.isColValue() && selection.isVarExFacSales()) {

                exFacValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, obj, index, exFacValue, selection, AMOUNT);
                pvList.add(exFacValue);
            }
            if (selection.isColVariance() && selection.isVarExFacSales()) {

                exFacVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, obj, index, exFacVar, selection, AMOUNT);
                pvList.add(exFacVar);
            }
            if (selection.isColPercentage() && selection.isVarExFacSales()) {

                exFacPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, obj, index, exFacPer, selection, RATE_PER);
                pvList.add(exFacPer);
            }
            //Demand
            if (selection.isColValue() && selection.isVarDemandSales()) {
                demandValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, obj, index + 1, demandValue, selection, AMOUNT);
                pvList.add(demandValue);
            }
            if (selection.isColVariance() && selection.isVarDemandSales()) {
                demandVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, obj, index + 1, demandVar, selection, AMOUNT);
                pvList.add(demandVar);
            }
            if (selection.isColPercentage() && selection.isVarDemandSales()) {
                demandPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, obj, index + 1, demandPer, selection, RATE_PER);
                pvList.add(demandPer);
            }
            //Inventory Withdraw Sales
            if (selection.isColValue() && selection.isVarInvSales()) {
                invWithValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, obj, index + 2, invWithValue, selection, AMOUNT);
                pvList.add(invWithValue);
            }
            if (selection.isColVariance() && selection.isVarInvSales()) {
                invWithVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, obj, index + 2, invWithVar, selection, AMOUNT);
                pvList.add(invWithVar);
            }
            if (selection.isColPercentage() && selection.isVarInvSales()) {
                invWithPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, obj, index + 2, invWithPer, selection, RATE_PER);
                pvList.add(invWithPer);
            }
            //% of Ex-Factory
            if (selection.isColValue() && selection.isVarPerExFacSales()) {
                perExFacValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VALUE, obj, index + 3, perExFacValue, selection, RATE_PER);
                pvList.add(perExFacValue);
            }
            if (selection.isColVariance() && selection.isVarPerExFacSales()) {
                perExFacVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, index + 3, perExFacVar, selection, RATE_PER);
                pvList.add(perExFacVar);
            }
            if (selection.isColPercentage() && selection.isVarPerExFacSales()) {
                perExFacPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.CHANGE, obj, index + 3, perExFacPer, selection, RATE_PER);
                pvList.add(perExFacPer);
            }
            //% of Demand
            if (selection.isColValue() && selection.isVarPerDemandSales()) {
                perDemandValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VALUE, obj, index + 4, perDemandValue, selection, RATE_PER);
                pvList.add(perDemandValue);
            }
            if (selection.isColVariance() && selection.isVarPerDemandSales()) {
                perDemandVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VARIANCE, obj, index + 4, perDemandVar, selection, RATE_PER);
                pvList.add(perDemandVar);
            }
            if (selection.isColPercentage() && selection.isVarPerDemandSales()) {
                perDemandPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.CHANGE, obj, index + 4, perDemandPer, selection, RATE_PER);
                pvList.add(perDemandPer);
            }
            //% of inventory Withdraw Sales
            if (selection.isColValue() && selection.isVarPerInvSales()) {
                perInvWithValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VALUE, obj, index + 5, perInvWithValue, selection, RATE_PER);
                pvList.add(perInvWithValue);
            }
            if (selection.isColVariance() && selection.isVarPerInvSales()) {
                perInvWithVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VARIANCE, obj, index + 5, perInvWithVar, selection, RATE_PER);
                pvList.add(perInvWithVar);
            }
            if (selection.isColPercentage() && selection.isVarPerInvSales()) {
                perInvWithPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.CHANGE, obj, index + 5, perInvWithPer, selection, RATE_PER);
                pvList.add(perInvWithPer);
            }
            //Contract Sales @ WAC
            if (selection.isColValue() && selection.isVarContractsales()) {
                salesValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, index + 6, salesValue, selection, AMOUNT);
                pvList.add(salesValue);
            }
            if (selection.isColVariance() && selection.isVarContractsales()) {
                salesVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, obj, index + 6, salesVar, selection, AMOUNT);
                pvList.add(salesVar);
            }
            if (selection.isColPercentage() && selection.isVarContractsales()) {
                salesPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, obj, index + 6, salesPer, selection, RATE_PER);
                pvList.add(salesPer);
            }
            //Contract Units
            if (selection.isColValue() && selection.isVarContractUnits()) {
                unitsValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VALUE, obj, index + 7, unitsValue, selection, AMOUNT_UNITS);
                pvList.add(unitsValue);
            }
            if (selection.isColVariance() && selection.isVarContractUnits()) {
                unitsVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VARIANCE, obj, index + 7, unitsVar, selection, AMOUNT_UNITS);
                pvList.add(unitsVar);
            }
            if (selection.isColPercentage() && selection.isVarContractUnits()) {
                unitsPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.CHANGE, obj, index + 7, unitsPer, selection, RATE_PER);
                pvList.add(unitsPer);
            }
            //Discount $
            if (selection.isColValue() && selection.isVarDisAmount()) {
                disDollValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, obj, index + 8, disDollValue, selection, AMOUNT);
                pvList.add(disDollValue);
            }
            if (selection.isColVariance() && selection.isVarDisAmount()) {
                disDollVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, obj, index + 8, disDollVar, selection, AMOUNT);
                pvList.add(disDollVar);
            }
            if (selection.isColPercentage() && selection.isVarDisAmount()) {
                disDollPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, obj, index + 8, disDollPer, selection, RATE_PER);
                pvList.add(disDollPer);
            }
            //Discount %
            if (selection.isColValue() && selection.isVarDisRate()) {
                disPerValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, obj, index + 9, disPerValue, selection, RATE_PER);
                pvList.add(disPerValue);
            }
            if (selection.isColVariance() && selection.isVarDisRate()) {
                disPerVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, obj, index + 9, disPerVar, selection, RATE_PER);
                pvList.add(disPerVar);
            }
            if (selection.isColPercentage() && selection.isVarDisRate()) {
                disPerPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, obj, index + 9, disPerPer, selection, RATE_PER);
                pvList.add(disPerPer);
            }
            //RPU
            if (selection.isColValue() && selection.isVarRPU()) {
                rpuValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, obj, isTotal ? index + 14 : index + 10, rpuValue, selection, AMOUNT);
                pvList.add(rpuValue);
            }
            if (selection.isColVariance() && selection.isVarRPU()) {
                rpuVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, obj, isTotal ? index + 14 : index + 10, rpuVar, selection, AMOUNT);
                pvList.add(rpuVar);
            }
            if (selection.isColPercentage() && selection.isVarRPU()) {
                rpuPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, obj, isTotal ? index + 14 : index + 10, rpuPer, selection, RATE_PER);
                pvList.add(rpuPer);
            }
            //Net Sales 
            if (selection.isColValue() && selection.isVarNetSales()) {
                netSalesValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, isTotal ? index + 15 : index + 11, netSalesValue, selection, AMOUNT);
                pvList.add(netSalesValue);
            }
            if (selection.isColVariance() && selection.isVarNetSales()) {
                netSalesVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VARIANCE, obj, isTotal ? index + 15 : index + 11, netSalesVar, selection, AMOUNT);
                pvList.add(netSalesVar);
            }
            if (selection.isColPercentage() && selection.isVarNetSales()) {
                netSalesPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.CHANGE, obj, isTotal ? index + 15 : index + 11, netSalesPer, selection, RATE_PER);
                pvList.add(netSalesPer);
            }
            //COGS
            if (selection.isColValue() && selection.isVarCOGC()) {
                cogsValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, isTotal ? index + 16 : index + 12, cogsValue, selection, AMOUNT);
                pvList.add(cogsValue);
            }
            if (selection.isColVariance() && selection.isVarCOGC()) {
                cogsVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VARIANCE, obj, isTotal ? index + 16 : index + 12, cogsVar, selection, AMOUNT);
                pvList.add(cogsVar);
            }
            if (selection.isColPercentage() && selection.isVarCOGC()) {
                cogsPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.CHANGE, obj, isTotal ? index + 16 : index + 12, cogsPer, selection, RATE_PER);
                pvList.add(cogsPer);
            }
            //Net Profit
            if (selection.isColValue() && selection.isVarNetProfit()) {
                netProfitValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, isTotal ? index + 17 : index + 13, netProfitValue, selection, AMOUNT);
                pvList.add(netProfitValue);
            }
            if (selection.isColVariance() && selection.isVarNetProfit()) {
                netProfitVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VARIANCE, obj, isTotal ? index + 17 : index + 13, netProfitVar, selection, AMOUNT);
                pvList.add(netProfitVar);
            }
            if (selection.isColPercentage() && selection.isVarNetProfit()) {
                netProfitPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.CHANGE, obj, isTotal ? index + 17 : index + 13, netProfitPer, selection, RATE_PER);
                pvList.add(netProfitPer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateList(List<ProjectionVarianceDTO> pvList, Object[] obj, int index) {

        int listIndex = 1;

        //Ex-Factory-Sales
        if (selection.isColValue() && selection.isVarExFacSales()) {
            exFacValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, obj, index, exFacValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarExFacSales()) {
            exFacVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, obj, index, exFacVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarExFacSales()) {
            exFacPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, obj, index, exFacPer, selection, RATE_PER);
        }
        //Demand
        if (selection.isColValue() && selection.isVarDemandSales()) {
            demandValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, obj, index + 1, demandValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarDemandSales()) {
            demandVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, obj, index + 1, demandVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarDemandSales()) {
            demandPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, obj, index + 1, demandPer, selection, RATE_PER);
        }
        //Inventory Withdraw Sales
        if (selection.isColValue() && selection.isVarInvSales()) {
            invWithValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, obj, index + 2, invWithValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarInvSales()) {
            invWithVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, obj, index + 2, invWithVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarInvSales()) {
            invWithPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, obj, index + 2, invWithPer, selection, RATE_PER);
        }
        //% of Ex-Factory
        if (selection.isColValue() && selection.isVarPerExFacSales()) {
            perExFacValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VALUE, obj, index + 3, perExFacValue, selection, RATE_PER);

        }
        if (selection.isColVariance() && selection.isVarPerExFacSales()) {
            perExFacVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, index + 3, perExFacVar, selection, RATE_PER);

        }
        if (selection.isColPercentage() && selection.isVarPerExFacSales()) {
            perExFacPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.CHANGE, obj, index + 3, perExFacPer, selection, RATE_PER);
        }
        //% of Demand
        if (selection.isColValue() && selection.isVarPerDemandSales()) {
            perDemandValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VALUE, obj, index + 4, perDemandValue, selection, RATE_PER);

        }
        if (selection.isColVariance() && selection.isVarPerDemandSales()) {
            perDemandVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VARIANCE, obj, index + 4, perDemandVar, selection, RATE_PER);

        }
        if (selection.isColPercentage() && selection.isVarPerDemandSales()) {
            perDemandPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.CHANGE, obj, index + 4, perDemandPer, selection, RATE_PER);
        }
        //% of inventory Withdraw Sales
        if (selection.isColValue() && selection.isVarPerInvSales()) {
            perInvWithValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VALUE, obj, index + 5, perInvWithValue, selection, RATE_PER);

        }
        if (selection.isColVariance() && selection.isVarPerInvSales()) {
            perInvWithVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VARIANCE, obj, index + 5, perInvWithVar, selection, RATE_PER);

        }
        if (selection.isColPercentage() && selection.isVarPerInvSales()) {
            perInvWithPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.CHANGE, obj, index + 5, perInvWithPer, selection, RATE_PER);
        }
        //Contract Sales @ WAC
        if (selection.isColValue() && selection.isVarContractsales()) {
            salesValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, index + 6, salesValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarContractsales()) {
            salesVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, obj, index + 6, salesVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarContractsales()) {
            salesPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, obj, index + 6, salesPer, selection, RATE_PER);
        }
        //Contract Units
        if (selection.isColValue() && selection.isVarContractUnits()) {
            unitsValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VALUE, obj, index + 7, unitsValue, selection, AMOUNT_UNITS);

        }
        if (selection.isColVariance() && selection.isVarContractUnits()) {
            unitsVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VARIANCE, obj, index + 7, unitsVar, selection, AMOUNT_UNITS);

        }
        if (selection.isColPercentage() && selection.isVarContractUnits()) {
            unitsPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.CHANGE, obj, index + 7, unitsPer, selection, RATE_PER);
        }
        //Discount $
        if (selection.isColValue() && selection.isVarDisAmount()) {
            disDollValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, obj, index + 8, disDollValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarDisAmount()) {
            disDollVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, obj, index + 8, disDollVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarDisAmount()) {
            disDollPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, obj, index + 8, disDollPer, selection, RATE_PER);
        }
        //Discount %
        if (selection.isColValue() && selection.isVarDisRate()) {
            disPerValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, obj, index + 9, disPerValue, selection, RATE_PER);

        }
        if (selection.isColVariance() && selection.isVarDisRate()) {
            disPerVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, obj, index + 9, disPerVar, selection, RATE_PER);

        }
        if (selection.isColPercentage() && selection.isVarDisRate()) {
            disPerPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, obj, index + 9, disPerPer, selection, RATE_PER);
        }
        //RPU
        if (selection.isColValue() && selection.isVarRPU()) {
            rpuValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, obj, isTotal ? index + 14 : index + 10, rpuValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarRPU()) {
            rpuVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, obj, isTotal ? index + 14 : index + 10, rpuVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarRPU()) {
            rpuPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, obj, isTotal ? index + 14 : index + 10, rpuPer, selection, RATE_PER);
        }
        //Net Sales 
        if (selection.isColValue() && selection.isVarNetSales()) {
            netSalesValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, isTotal ? index + 15 : index + 11, netSalesValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarNetSales()) {
            netSalesVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VARIANCE, obj, isTotal ? index + 15 : index + 11, netSalesVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarNetSales()) {
            netSalesPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.CHANGE, obj, isTotal ? index + 15 : index + 11, netSalesPer, selection, RATE_PER);
        }
        //COGS
        if (selection.isColValue() && selection.isVarCOGC()) {
            cogsValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, isTotal ? index + 16 : index + 12, cogsValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarCOGC()) {
            cogsVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VARIANCE, obj, isTotal ? index + 16 : index + 12, cogsVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarCOGC()) {
            cogsPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.CHANGE, obj, isTotal ? index + 16 : index + 12, cogsPer, selection, RATE_PER);
        }
        //Net Profit
        if (selection.isColValue() && selection.isVarNetProfit()) {
            netProfitValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, isTotal ? index + 17 : index + 13, netProfitValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarNetProfit()) {
            netProfitVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VARIANCE, obj, isTotal ? index + 17 : index + 13, netProfitVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarNetProfit()) {
            netProfitPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.CHANGE, obj, isTotal ? index + 17 : index + 13, netProfitPer, selection, RATE_PER);
        }
    }

    private void calculate(String varaibleName, String varibaleCat, Object[] obj, int index, ProjectionVarianceDTO pvDTO,
            PVSelectionDTO selection, DecimalFormat format) 
    {
       boolean flag;
       flag=varaibleName.contains("%");
        pvDTO.setGroup(varaibleName.concat(varibaleCat));
        String commonColumn = StringUtils.EMPTY;
        List<Integer> priorList = selection.getProjIdList();
        if (frequencyDivision == 4) {
            commonColumn = "Q" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 2) {
            commonColumn = "S" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
        }

        if (varibaleCat.equals(Constant.VALUE)) {
            
            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
            String baseValue = getFormattedValue(format, value);
            if(flag){
            pvDTO.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue+PERCENT);
            }else{
            pvDTO.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue);
            }

        }
        for (int j = 0; j < priorList.size(); j++) {
            if (varibaleCat.equals(Constant.VALUE)) {
                String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))])));
                String val = getFormattedValue(format, priorVal);
                if(varaibleName.contains("%")){
                pvDTO.addStringProperties(commonColumn + priorList.get(j), val+ PERCENT);
                }else{
                 pvDTO.addStringProperties(commonColumn + priorList.get(j), val); 
                }

            } else if (varibaleCat.equals(Constant.VARIANCE)) {
                String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))];
                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                String val = getFormattedValue(format, variance);
                if(varaibleName.contains("%")){
                pvDTO.addStringProperties(commonColumn + priorList.get(j), val+PERCENT);
                }else{
                 pvDTO.addStringProperties(commonColumn + priorList.get(j), val);   
                }

            } else {
                String priorVal = obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))] != null ? StringUtils.EMPTY + obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))] : ZERO;
                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                    perChange = 0.0;
                }
                String change = String.valueOf(perChange);
                String baseValu = getFormattedValue(format, change);
                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

            }
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

    public void getTotalRawData() {
        String frequency = selection.getFrequency();
        String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        List<Integer> projectionIdList = new ArrayList();
        procRawList_total.clear();
        priorList.clear();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);

        Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", selection.getSessionId(), selection.getUserId(), "PIVOT"};
        List< Object[]> rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        procRawList_total.addAll(rawList);
        rawList.clear();
    }

    public void executeProcedure_PRC_PV_SELECTION() {
        boolean isRefresh = true;
        if (parameterDto.getViewName().equals("DETAIL_TOTAL_DISCOUNT")) {
            PVParameters lastParameter = parameters.get("PRC_PROJECTION_VARIANCE_DISCOUNT");
            if (lastParameter != null) {
                isRefresh = !lastParameter.equals(parameterDto);
            }
            parameters.put("PRC_PROJECTION_VARIANCE_DISCOUNT", parameterDto.clone());
        } else {
            PVParameters lastParameter = parameters.get("PRC_PROJECTION_VARIANCE");
            if (lastParameter != null) {
                isRefresh = !lastParameter.equals(parameterDto);
            }
            parameters.put("PRC_PROJECTION_VARIANCE", parameterDto.clone());
        }
        if (isRefresh) {
            List<Integer> projectionIdList = new ArrayList();
            projectionIdList.add(selection.getCurrentProjId());
            projectionIdList.addAll(selection.getProjIdList());
            String projectionId = CommonUtils.CollectionToString(projectionIdList, false);

            Object[] orderedArg = {projectionId, parameterDto.getUserId(), parameterDto.getSessionId(),
                parameterDto.getDiscountLevel(), parameterDto.getFrequency(), parameterDto.getViewIndicator(),
                parameterDto.getGroupFilter(), parameterDto.getGroupFilterValue(), parameterDto.getHierarchyNo(),
                parameterDto.getLevelNo(), parameterDto.getViewName(), parameterDto.getCustomViewMasterSid(),parameterDto.getView().equals("Variable")? "pivot" : parameterDto.getView()};
            List< Object[]> rawList = CommonLogic.callProcedure(PRC_PV_SELECTION, orderedArg);

            if (parameterDto.getViewName().equals("DETAIL_TOTAL_DISCOUNT")) {
                procRawList_detail.clear();
                procRawList_detail.addAll(rawList);
            } else {
                procRawList_detail_discount.clear();
                procRawList_detail_discount.addAll(rawList);
            }
           rawList.clear();
        }
    }

    private List<ProjectionVarianceDTO> calculateAndCustomize_total_period(List<Object[]> rawList) {

        //To check condition total or details values
        List<ProjectionVarianceDTO> pvList = new ArrayList();
        hierarchyKeys.add(Constants.LabelConstants.TOTAL.toString());
        resultMap.put(Constants.LabelConstants.TOTAL.toString(), pvList);
        int index = 2, i = 0;

        for (ListIterator<Object[]> iterator = rawList.listIterator(); iterator.hasNext();) {
            Object[] obj = iterator.next();
//            iterator.remove();
            if (i++ == 0) {
                addList(pvList, obj, index);
            } else {
                updateList(pvList, obj, index);
            }
        }

        return pvList;
    }

    private void calculateAndCustomize_total_pivot(List<Object[]> rawList) {
        List<ProjectionVarianceDTO> pvList = new ArrayList();
        hierarchyKeys.add(Constants.LabelConstants.TOTAL.toString());
        resultMap.put(Constants.LabelConstants.TOTAL.toString(), pvList);
        int i = 0;

        List<String> periodList = selection.getPeriodList();

        for (ListIterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
//            it.remove();
//            String group = getfrequencyBased_group(obj);

            List<String> common = null;
            if (!"null".equalsIgnoreCase(String.valueOf(obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index])) && !"null".equalsIgnoreCase(String.valueOf(obj[isTotal ? frequencyDivision == 1 ? 0 : baseColumn_period_indexForTotal : baseColumn_period_index]))) {
                common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index].toString()), Integer.valueOf(obj[isTotal ? frequencyDivision == 1 ? 0 : baseColumn_period_indexForTotal : baseColumn_period_index].toString()));
                String groupId = common.get(0);

                if (periodList.contains(groupId)) {
                    String gorupName = common.get(1);
                    ProjectionVarianceDTO frequencyBasedDTO = new ProjectionVarianceDTO();
                    frequencyBasedDTO.setGroup(gorupName);
                    if (i++ == 0) {
                        addList_pivot(pvList, obj, frequencyBasedDTO, 2,"");

                    } else {
                        updateList_pivot(pvList, obj, frequencyBasedDTO, 2,"");
                    }
                }
            }

        }
    }

    private String getfrequencyBased_group(Object[] obj) {
        String groupName = "";

//        if (frequencyDivision == 1) {
//            commonColumn = "" + year;
//            commonHeader = "" + year;
//        } else if (frequencyDivision == 4) {
//            commonColumn = "Q" + period + "" + year;
//            commonHeader = "Q" + period + " " + year;
//        } else if (frequencyDivision == 2) {
//            commonColumn = "S" + period + "" + year;
//            commonHeader = "S" + period + " " + year;
//        } else if (frequencyDivision == 12) {
//            String monthName = getMonthForInt(period - 1);
//            commonColumn = monthName.toLowerCase() + year;
//            commonHeader = monthName + " " + year;
//        }
        if (frequencyDivision == 1) {
            groupName = obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index].toString();
        } else if (frequencyDivision == 4) {
            groupName = "Q" + obj[isTotal ? baseColumn_period_indexForTotal : baseColumn_period_index] + " " + obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index];
        } else if (frequencyDivision == 2) {
            groupName = "S" + obj[isTotal ? baseColumn_period_indexForTotal : baseColumn_period_index] + " " + obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? baseColumn_period_indexForTotal : baseColumn_period_index])) - 1);
            groupName = monthName + obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index];
        }
        return groupName;
    }

    private void addList_pivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexForTotal,String key) {
//        final int indexForTotal = 2;

        int frequencyDivision = selection.getFrequencyDivision();
        if (isTotal) {
            ProjectionVarianceDTO total = new ProjectionVarianceDTO();
            total.setGroup("Projection Total");
            pvList.add(total);
        } else {
            ProjectionVarianceDTO detail = new ProjectionVarianceDTO();

            //Group Column projSelDTO
            String groupName;
            if (isCustomView) {
                groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                groupName = groupName == null ? "" : groupName;
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
            }
       
        
//                    if (groupName != null && !"null".equalsIgnoreCase(groupName)) {
            detail.setGroup(groupName);
            pvList.add(detail);
        }

        pvList.add(frequencyBasedDTO);

        //Ex-Factory-Sales
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("ExFacValue", Constant.VALUE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ExFacVariance", Constant.VARIANCE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constant.CHANGE, obj, indexForTotal, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constant.VALUE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constant.VARIANCE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constant.CHANGE, obj, indexForTotal + 1, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Inventory Withdraw Sales
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constant.VALUE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constant.VARIANCE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constant.CHANGE, obj, indexForTotal + 2, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Ex-Factory
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constant.VALUE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constant.VARIANCE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constant.CHANGE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constant.VALUE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constant.VARIANCE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constant.CHANGE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of inventory Withdraw Sales
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constant.VALUE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constant.VARIANCE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constant.CHANGE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constant.VALUE, obj, indexForTotal + 6, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constant.VARIANCE, obj, indexForTotal + 6, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constant.CHANGE, obj, indexForTotal + 6, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constant.VALUE, obj, indexForTotal + 7, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constant.VARIANCE, obj, indexForTotal + 7, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constant.CHANGE, obj, indexForTotal + 7, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constant.VALUE, obj, indexForTotal + 8, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constant.VARIANCE, obj, indexForTotal + 8, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constant.CHANGE, obj, indexForTotal + 8, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constant.VALUE, obj, indexForTotal + 9, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constant.VARIANCE, obj, indexForTotal + 9, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constant.CHANGE, obj, indexForTotal + 9, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constant.VALUE, obj, isTotal ? indexForTotal + 14 : indexForTotal + 10, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 14 : indexForTotal + 10, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 14 : indexForTotal + 10, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constant.VALUE, obj, isTotal ? indexForTotal + 15 : indexForTotal + 11, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 15 : indexForTotal + 11, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 15 : indexForTotal + 11, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constant.VALUE, obj, isTotal ? indexForTotal + 16 : indexForTotal + 12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 16 : indexForTotal + 12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 16 : indexForTotal + 12, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constant.VALUE, obj, isTotal ? indexForTotal + 17 : indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 17 : indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 17 : indexForTotal + 13, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        if (!isTotal) {
            String commonColumn = StringUtils.EMPTY;

            if (frequencyDivision == 4) {
                commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[4];
            } else if (frequencyDivision == 2) {
                commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[4];
            } else if (frequencyDivision == 1) {
                commonColumn = String.valueOf(obj[4]);
            } else if (frequencyDivision == 12) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
                commonColumn = monthName.toLowerCase() + obj[4];
            }

            Map<String, ProjectionVarianceDTO> valueMap = pivotDiscountMap.get(key);
            if (valueMap != null) {

                ProjectionVarianceDTO dto = valueMap.get(commonColumn);
                if (dto != null) {
                    for (Object prop : dto.getProperties().keySet()) {
                        String value = String.valueOf(dto.getPropertyValue(prop));
                        frequencyBasedDTO.addStringProperties(prop, value);
                    }
                }
            }
        }
    }

    private void calculateForTotal(String variableName, String varibaleCat, Object[] obj, int index, ProjectionVarianceDTO pvDTO, PVSelectionDTO selection, DecimalFormat format) {
        if (varibaleCat.equals(Constant.VALUE)) {
            String val = getFormattedValue(format, obj[index].toString());

            pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(), val);
        } else {
            pvDTO.addStringProperties(variableName + CURRENT + selection.getCurrentProjId(), StringUtils.EMPTY);
        }

        for (int j = 0; j < priorList.size(); j++) {
            if (varibaleCat.equals(Constant.VALUE)) {
                String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))])));
                String val = getFormattedValue(format, priorVal);
                    pvDTO.addStringProperties(variableName + priorList.get(j), val);

            } else if (varibaleCat.equals(Constant.VARIANCE)) {
                String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))];
                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                String val = getFormattedValue(format, variance);
                     pvDTO.addStringProperties(variableName + priorList.get(j), val);

            } else if(varibaleCat.equals(Constant.CHANGE)){
                String priorVal = obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))] != null ? StringUtils.EMPTY + obj[index + ((j + 1) * (isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT))] : ZERO;
                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                    perChange = 0.0;
                }
                String change = String.valueOf(perChange);
                String baseValu = getFormattedValue(format, change);
//                System.out.println("baseValu = " + baseValu);
                pvDTO.addStringProperties(variableName + priorList.get(j), baseValu+ PERCENT);

            }
        }
    }

    private void calculateAndCustomize_detail_pivot(List<Object[]> rawList, List<Object[]> rawList_discount) {

        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
//            it.remove();
            String key = obj[baseColumn_hierarchy_index].toString();
            key = key.substring(key.indexOf('-') + 1);
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index].toString()), Integer.valueOf(obj[isTotal ? frequencyDivision == 1 ? 0 : baseColumn_period_indexForTotal : baseColumn_period_index].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();

                addList_pivot(pvList, obj, freVarianceDTO, index,key);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList_pivot(pvList, obj, freVarianceDTO, index,key);
            }
        }
//        if (discountFlag) {
//            Set<String> discountSet = new HashSet();
//            Set<String> hierarchySet = new HashSet();
//            int listIndex = 0;
//            for (ListIterator<Object[]> it = rawList_discount.listIterator(); it.hasNext();) {
//                Object[] obj = it.next();
////            it.remove();
//                String key = obj[baseColumn_hierarchy_index].toString();
//                key = key.substring(key.indexOf('-') + 1);
//                String discountName = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
//                if (discountSet.add(discountName)) {
//                    hierarchySet.clear();
//                    listIndex = 1;
//                } else if (hierarchySet.add(key)) {
//                    listIndex = 1;
//                }
//
//                List<ProjectionVarianceDTO> pvList = resultMap.get(key);
//                updateList_detail_discount_pivot(pvList, key, obj, listIndex++, discountSet);
//            }
//        }
    }

    private void updateList_pivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexForTotal,String key) {
//        int indexForTotal = 6;

        pvList.add(frequencyBasedDTO);
        //Ex-Factory-Sales
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("ExFacValue", Constant.VALUE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {                
                calculateForTotal("ExFacVariance", Constant.VARIANCE, obj, indexForTotal, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constant.CHANGE, obj, indexForTotal, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constant.VALUE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constant.VARIANCE, obj, indexForTotal + 1, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constant.CHANGE, obj, indexForTotal + 1, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Inventory Withdraw Sales
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constant.VALUE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constant.VARIANCE, obj, indexForTotal + 2, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constant.CHANGE, obj, indexForTotal + 2, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Ex-Factory
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constant.VALUE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constant.VARIANCE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constant.CHANGE, obj, indexForTotal + 3, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constant.VALUE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constant.VARIANCE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constant.CHANGE, obj, indexForTotal + 4, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of inventory Withdraw Sales
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constant.VALUE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constant.VARIANCE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constant.CHANGE, obj, indexForTotal + 5, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constant.VALUE, obj, indexForTotal + 6, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constant.VARIANCE, obj, indexForTotal + 6, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constant.CHANGE, obj, indexForTotal + 6, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constant.VALUE, obj, indexForTotal + 7, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constant.VARIANCE, obj, indexForTotal + 7, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constant.CHANGE, obj, indexForTotal + 7, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constant.VALUE, obj, indexForTotal + 8, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constant.VARIANCE, obj, indexForTotal + 8, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constant.CHANGE, obj, indexForTotal + 8, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constant.VALUE, obj, indexForTotal + 9, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constant.VARIANCE, obj, indexForTotal + 9, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constant.CHANGE, obj, indexForTotal + 9, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constant.VALUE, obj, isTotal ? indexForTotal + 14 : indexForTotal + 10, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 14 : indexForTotal + 10, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 14 : indexForTotal + 10, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constant.VALUE, obj, isTotal ? indexForTotal + 15 : indexForTotal + 11, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 15 : indexForTotal + 11, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 15 : indexForTotal + 11, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constant.VALUE, obj, isTotal ? indexForTotal + 16 : indexForTotal + 12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 16 : indexForTotal + 12, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 16 : indexForTotal + 12, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constant.VALUE, obj, isTotal ? indexForTotal + 17 : indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constant.VARIANCE, obj, isTotal ? indexForTotal + 17 : indexForTotal + 13, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constant.CHANGE, obj, isTotal ? indexForTotal + 17 : indexForTotal + 13, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        
             String commonColumn = StringUtils.EMPTY;
             if (frequencyDivision == 4) {
            commonColumn = "Q" + obj[3] + StringUtils.EMPTY + obj[4];
        } else if (frequencyDivision == 2) {
            commonColumn = "S" + obj[3] + StringUtils.EMPTY + obj[4];
        } else if (frequencyDivision == 1) {
            commonColumn = String.valueOf(obj[4]);
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
            commonColumn = monthName.toLowerCase() + obj[4];
    }
            Map<String, ProjectionVarianceDTO> valueMap = pivotDiscountMap.get(key);
            if (valueMap != null) {
                ProjectionVarianceDTO dto = valueMap.get(commonColumn);
                if (dto != null) {
                    for (Object prop : dto.getProperties().keySet()) {
                        String value = String.valueOf(dto.getPropertyValue(prop));
                        frequencyBasedDTO.addStringProperties(prop, value);
                    }
                }
            }

        
    }

    public void getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer, String parentGroup, String indicator) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<ProjectionVarianceDTO>();
        // String indicator=projSelDTO.getVarIndicator();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());

        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!"".equals(lastValue) && !"null".equals(lastValue) && obj[2] != null && !lastValue.equals(String.valueOf(obj[2]))) {
                    //   pvDTO.setGroup(parentGroup+"-"+indicator+lastValue);
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);

                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[2]);


                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                    String baseValue = getFormattedValue(AMOUNT, value);

                    pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance); 
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                    }
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
            resultMap.put(parentGroup, resultDto);
        } else {
            for (String discountNameList : projSelDTO.getDiscountNameList()) {
                pvDTO = new ProjectionVarianceDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove("group");
                for (String Obj : columnList) {
                    pvDTO.addStringProperties(Obj, DASH);
                }
                resultDto.add(pvDTO);
            }
        }
       
        LOGGER.info("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + discountList.size());

    }

    public ProjectionVarianceDTO getCustomizedPPA(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String parentGroup, String ppaGroup) {
        LOGGER.info("Inside getCustomizedPPA");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        pvDTO.setGroup(ppaGroup);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {

            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = "" + obj[5];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = "" + obj[5 + ((j + 1) * 3)];
                        if (projSelDTO.getSales().contains("SALES")) {
                            pValue = getFormattedValue(AMOUNT, pValue);
                        } else if (projSelDTO.getSales().contains("RATE")) {
                            pValue = getFormattedValue(RATE_PER_THREE, pValue);
                        }
                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        }
      
        List list = resultMap.get(parentGroup);

        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionVarianceDTO> dto = new ArrayList<ProjectionVarianceDTO>();
            dto.add(pvDTO);
            resultMap.put(parentGroup, dto);
        }

        LOGGER.info("Ending getCustomizedPPA");
        return pvDTO;
    }

    public void getCustomizedReturns(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String parentGroup, String returnsGroup) {
        LOGGER.info("Inside getCustomizedReturns");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        pvDTO.setGroup(returnsGroup);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {

            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = "" + obj[4];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = "" + obj[4 + ((j + 1) * 3)];
                        if (projSelDTO.getSales().contains("SALES")) {
                            pValue = getFormattedValue(AMOUNT, pValue);
                        } else if (projSelDTO.getSales().contains("RATE")) {
                            pValue = getFormattedValue(RATE, pValue);
                        }
                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        }
        LOGGER.info("Ending getCustomizedReturns");
      
        List list = resultMap.get(parentGroup);
       
        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionVarianceDTO> returnsDTO = new ArrayList<ProjectionVarianceDTO>();
            returnsDTO.add(pvDTO);
            resultMap.put(parentGroup, returnsDTO);
        }
        //return pvDTO;
    }

    private void commonCustomizationForTotalDiscount(String group, String discountType, List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String ppaGroup, String returnsGroup, List<Object> pivotList) {
        LOGGER.info("Inside commonCustomizationForTotalDiscount");
        //   List<ProjectionVarianceDTO> dto = new ArrayList<ProjectionVarianceDTO>();

        int indexValue = 0;
//        int ppaIndex = 0;
//        int rpuIndex = 0;
        if (group.equals("D$")) {
            indexValue = 3;
//            ppaIndex = 12;
//            rpuIndex = 21;
        } else if (group.equals("D%")) {
            indexValue = 4;
//            ppaIndex = 13;
//            rpuIndex = 22;
        } else {
            indexValue = 5;
//            ppaIndex = 15;
//            rpuIndex = 20;
        }
        String parentGroup = StringUtils.EMPTY;
        List list = new ArrayList();
        if (!isDetail) {
            if (projSelDTO.isColValue()) {
                projSelDTO.setVarIndicator(VALUE.getConstant());
                parentGroup = group + "value";
                if (!selection.getDiscountNameList().isEmpty()) {

                    getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, isPer, parentGroup, VALUE.getConstant());
                }
//                getCustomizedPPA(pivotList, projSelDTO, isDetail, ppaIndex, isPer, parentGroup, ppaGroup);
//                getCustomizedReturns(pivotList, projSelDTO, isDetail, rpuIndex, isPer, parentGroup, returnsGroup);
            }
            if (projSelDTO.isColVariance()) {
                projSelDTO.setVarIndicator(VARIANCE.getConstant());
                parentGroup = group + "variance";
                if (!selection.getDiscountNameList().isEmpty()) {
                    getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, isPer, parentGroup, VARIANCE.getConstant());
                }
//                getCustomizedPPA(pivotList, projSelDTO, isDetail, ppaIndex, isPer, parentGroup, ppaGroup);
//                getCustomizedReturns(pivotList, projSelDTO, isDetail, rpuIndex, isPer, parentGroup, returnsGroup);
            }
            if (projSelDTO.isColPercentage()) {

                parentGroup = group + "change";
                projSelDTO.setVarIndicator(CHANGE.getConstant());
                if (!selection.getDiscountNameList().isEmpty()) {
                    getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, isPer, parentGroup, CHANGE.getConstant());
                }
//                getCustomizedPPA(pivotList, projSelDTO, isDetail, ppaIndex, isPer, parentGroup, ppaGroup);
//                getCustomizedReturns(pivotList, projSelDTO, isDetail, rpuIndex, isPer, parentGroup, returnsGroup);
            }

        }
        LOGGER.info("Ending commonCustomizationForTotalDiscount");
        // return dto;
    }

    private void calculate_discount(String varaibleName, String varibaleCat, String masterKey, Object[] obj, int index, ProjectionVarianceDTO pvDTO,
            PVSelectionDTO selection, DecimalFormat format, boolean isAdd, int listIndex) {


        String key = masterKey + varaibleName + varibaleCat;
        List<ProjectionVarianceDTO> pvList_discount;

        if (isAdd) {
            pvList_discount = resultMap.get(key);
            if (pvList_discount == null) {
                pvList_discount = new ArrayList();
                resultMap.put(key, pvList_discount);
                discountKeys.add(key);
            }
            pvList_discount.add(pvDTO);
            pvDTO.setGroup(obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString());
        } else {
            pvList_discount = resultMap.get(key);
            pvDTO = pvList_discount.get(listIndex);
        }

        String commonColumn = StringUtils.EMPTY;

        if (frequencyDivision == 4) {
            commonColumn = "Q" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 2) {
            commonColumn = "S" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[baseColumn_period_discount_index])) - 1);
            commonColumn = monthName.toLowerCase() + obj[baseColumn_year_discount_index];
        }

        if (varibaleCat.equals(Constant.VALUE)) {
            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
            String baseValue = getFormattedValue(format, value);
            pvDTO.addStringProperties(commonColumn + CURRENT + selection.getCurrentProjId(), baseValue);

        }
        List<Integer> priorList = selection.getProjIdList();
        for (int j = 0; j < priorList.size(); j++) {
            if (varibaleCat.equals(Constant.VALUE)) {
                String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))])));
                String val = getFormattedValue(format, priorVal);
                pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

            } else if (varibaleCat.equals(Constant.VARIANCE)) {
                String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))];
                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                String val = getFormattedValue(format, variance);
                pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

            } else {
                String priorVal = obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))] != null ? StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))] : ZERO;
                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                    perChange = 0.0;
                }
                String change = String.valueOf(perChange);
                String baseValu = getFormattedValue(format, change); 
                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

            }
        }
    }

    private void addList_detail_discount(String key, final Object[] obj) {
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                disDollValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, key, obj, index_detail_discount, disDollValue, selection, AMOUNT, true, 0);
            }
            if (selection.isColVariance()) {
                disDollVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, key, obj, index_detail_discount, disDollVar, selection, AMOUNT, true, 0);
            }
            if (selection.isColPercentage()) {
                disDollPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, key, obj, index_detail_discount, disDollPer, selection, RATE_PER, true, 0);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                disPerValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, key, obj, index_detail_discount + 1, disPerValue, selection, RATE_PERC, true, 0);
            }
            if (selection.isColVariance()) {
                disPerVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, key, obj, index_detail_discount + 1, disPerVar, selection, RATE_PERC, true, 0);
            }
            if (selection.isColPercentage()) {
                disPerPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, key, obj, index_detail_discount + 1, disPerPer, selection, RATE_PER, true, 0);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                rpuValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, key, obj, index_detail_discount + 2, rpuValue, selection, AMOUNT, true, 0);
            }
            if (selection.isColVariance()) {
                rpuVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, key, obj, index_detail_discount + 2, rpuVar, selection, AMOUNT, true, 0);
            }
            if (selection.isColPercentage()) {
                rpuPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, key, obj, index_detail_discount + 2, rpuPer, selection, RATE_PER, true, 0);
            }
        }
    }

    private void updateList_detail_discount(String key, final Object[] obj, int listIndex) {
//        int listIndex = 0;
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
//            disDollValue = pvList.get(listIndex++);
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, key, obj, index_detail_discount, null, selection, AMOUNT, false, listIndex);
            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, key, obj, index_detail_discount, null, selection, AMOUNT, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, key, obj, index_detail_discount, null, selection, RATE_PER, false, listIndex);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, key, obj, index_detail_discount + 1, null, selection, RATE_PERC, false, listIndex);

            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, key, obj, index_detail_discount + 1, null, selection, RATE_PERC, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, key, obj, index_detail_discount + 1, null, selection, RATE_PER, false, listIndex);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, key, obj, index_detail_discount + 2, null, selection, AMOUNT, false, listIndex);

            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, key, obj, index_detail_discount + 2, null, selection, AMOUNT, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, key, obj, index_detail_discount + 2, null, selection, RATE_PER, false, listIndex);
            }
        }
    }

    private void updateList_detail_discount_pivot(List<ProjectionVarianceDTO> pvList, String key, final Object[] obj, int listIndex, Set<String> discountSet) {
        ProjectionVarianceDTO frequencyBasedDTO = pvList.get(listIndex);
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculate_discount_pivot("DiscountAmountValue", Constant.VALUE, discountSet, obj, frequencyBasedDTO, index_detail_discount, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculate_discount_pivot("DiscountAmountVar", Constant.VARIANCE, discountSet, obj, frequencyBasedDTO, index_detail_discount, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculate_discount_pivot("DiscountAmountPer", Constant.CHANGE, discountSet, obj, frequencyBasedDTO, index_detail_discount, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculate_discount_pivot("DiscountSalesValue", Constant.VALUE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 1, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculate_discount_pivot("DiscountSalesVar", Constant.VARIANCE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 1, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculate_discount_pivot("DiscountSalesPer", Constant.CHANGE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 1, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculate_discount_pivot("RPUValue", Constant.VALUE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 2, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculate_discount_pivot("RPUVariance", Constant.VARIANCE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 2, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculate_discount_pivot("RPUPer", Constant.CHANGE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 2, selection, RATE_PER);
            }
        }
    }

    private void calculate_discount_pivot(String varaibleName, String varibaleCat, Set<String> discountSet, Object[] obj, ProjectionVarianceDTO pvDTO, int index,
            PVSelectionDTO selection, DecimalFormat format) {

        String discoun_ppa_returns_Name = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
        if ("PPA".equals(discoun_ppa_returns_Name) || "RETURNS".equalsIgnoreCase(discoun_ppa_returns_Name)) {
            discoun_ppa_returns_Name = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
        } else {
            discoun_ppa_returns_Name = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString().replace(" ", StringUtils.EMPTY) + (discountSet.size() - 1);
        }

        if (varibaleCat.equals(Constant.VALUE)) {
            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
            String baseValue = getFormattedValue(format, value);

            pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + CURRENT + selection.getCurrentProjId(), baseValue);
        }
        List<Integer> priorList = selection.getProjIdList();
        for (int j = 0; j < priorList.size(); j++) {
            if (varibaleCat.equals(Constant.VALUE)) {
                String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))])));
                String val = getFormattedValue(format, priorVal);
                pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + priorList.get(j), val);

            } else if (varibaleCat.equals(Constant.VARIANCE)) {
                String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))];
                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                String val = getFormattedValue(format, variance);
                pvDTO.addStringProperties(varaibleName + priorList.get(j), val);

            } else {
                String priorVal = obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))] != null ? StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))] : ZERO;
                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                    perChange = 0.0;
                }
                String change = String.valueOf(perChange);
                String baseValu = getFormattedValue(format, change);
                pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + priorList.get(j), baseValu + PERCENT);

            }
        }
    }

    public void discount_Customize() {
      
        //  List<ProjectionVarianceDTO> discountList=new ArrayList<ProjectionVarianceDTO>();
        boolean isDetail = selection.getLevel().equals(DETAIL);
        commonCustomizationForTotalDiscount("D$", "Discount", pivotDiscountList, selection, isDetail, 3, Boolean.FALSE, "PPA Discount $", "Return Discount $", pivotTotalList);
        commonCustomizationForTotalDiscount("D%", "Discount", pivotDiscountList, selection, isDetail, 4, Boolean.TRUE, "PPA Discount %", "Return Discount %", pivotTotalList);
        commonCustomizationForTotalDiscount("RPU-", "Discount", pivotDiscountList, selection, isDetail, 5, Boolean.FALSE, "PPA RPU", "Returns RPU", pivotTotalList);
    }

    public void getTotalPivotVariance(PVSelectionDTO selection) {
        String frequency = selection.getFrequency();
        String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<String>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(String.valueOf(selection.getCurrentProjId()));
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", selection.getSessionId(), selection.getUserId(), "PIVOT"};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

    public void getPivot_customization() {
        //   getCustomizedPivotTotalResults
        NMProjectionVarianceLogic logic = new NMProjectionVarianceLogic();

        List<ProjectionVarianceDTO> finalList = logic.getCustomizedPivotTotalResults(pivotTotalList, null, pivotPriorProjIdList, selection, selection, pivotDiscountList);
        resultMap.put("Total", finalList);
    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    void getTotalDiscount(PVSelectionDTO projSelDTO) {
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
        projectionIdList.add(String.valueOf(projSelDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
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
 private void customize_discount_pivot() {
        int count = procRawList_detail_discount.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String discountNo = StringUtils.EMPTY;
        String newyear = StringUtils.EMPTY;
        String oldYear = StringUtils.EMPTY;
        String newPeriod = StringUtils.EMPTY;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        List<Integer> projList = selection.getProjIdList();
     ProjectionVarianceDTO discountDto = new ProjectionVarianceDTO();
     List<String> list=selection.getDiscountNameList();

       List<String> discountNames = new ArrayList<String>(selection.getDiscountNameList());
        //PPA
        List list3 = CommonLogic.getPPADiscountNameList(selection);
        if(list3!=null){
        discountNames.addAll(list3);
}

     
      List<String> discountNames1 = new ArrayList<String>(selection.getDiscountLevel().equals("Program") ? discountNames : discountNames);
        for (int i = 0; i < discountNames1.size(); i++) {
            String name = String.valueOf(discountNames1.get(i)).replaceAll(" ", StringUtils.EMPTY);
            discountNameMap.put(name, String.valueOf(i));
        }

       Map<String, ProjectionVarianceDTO> periodDiscountMap = new HashMap<String, ProjectionVarianceDTO>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
            if (i == 1) {
                oldHierarchyNo = String.valueOf(obj[1]);
                oldHierarchyNo = oldHierarchyNo.substring(oldHierarchyNo.indexOf('-') + 1);
            }
            String newHierarchyNo = String.valueOf(obj[1]);
            newHierarchyNo = newHierarchyNo.substring(newHierarchyNo.indexOf('-') + 1);
            newyear = String.valueOf(obj[3]);
            newPeriod = String.valueOf(obj[2]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
//                    discount=discount_Name(discount);
                    setBase_Value(discountDto,obj,discount);
                    discountNo = discount_No(discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);

                } else{
                    if (i == 1) {
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn=getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
                       
//                    discount=discount_Name(discount);
                    discountNo = discount_No(discount);
                    setBase_Value(discountDto,obj,discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn=getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
//                     discount=discount_Name(discount);
                    discountNo = discount_No(discount);
                    setBase_Value(discountDto,obj,discount);
                    customize_PriorList(discountDto, projList, discount, discountNo, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                }
            }
            }else {
                  periodDiscountMap.put(commonColumn, discountDto);
                  pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
                  
                  periodDiscountMap = new HashMap<String, ProjectionVarianceDTO>();
                  discountDto = new ProjectionVarianceDTO();
                  commonColumn=getVisibleColumn_Header(obj);
                  String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
//                   discount=discount_Name(discount);
                  discountNo = discount_No(discount);
                  setBase_Value(discountDto,obj,discount);
                  customize_PriorList(discountDto, projList, discount, discountNo, obj);
                  
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
     private String discount_No(String discountName){
         String number=String.valueOf(discountNameMap.get(discountName));
         if("null".equals(number)|| number.equals("NoNumber")){
             return StringUtils.EMPTY;
         }
         return number;
     }
                     private void setBase_Value(ProjectionVarianceDTO discountDto,Object[] obj,String discount){
                    String discountNo=StringUtils.EMPTY;
                    discountNo = discount_No(discount);
                    String visibleColumn = "DiscountAmountValue" + String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + selection.getCurrentProjId();
                    System.out.println(" discount $ visibleColumn :: "+visibleColumn);
                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                    discountDto.addStringProperties(visibleColumn, currentValue);
                    String discountVarCurrent = "DiscountAmountVar" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    discountDto.addStringProperties(discountVarCurrent, "$0");

                    String discountVarPer = "DiscountAmountPer" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    discountDto.addStringProperties(discountVarPer, "0.00%");

                    String discountPerColumn = "DiscountSalesValue" + String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + selection.getCurrentProjId();
                    String priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    String valPer = getFormattedValue(RATE, priorValPer);
                    discountDto.addStringProperties(discountPerColumn, valPer + PERCENT);

                    String discountVariance = "DiscountSalesVar" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    String valPer2 = getFormattedValue(RATE, "0");
                    discountDto.addStringProperties(discountVariance, valPer2);

                    String discountVarPercent = "DiscountSalesPer" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    discountDto.addStringProperties(discountVarPercent, valPer2);

                    discountPerColumn = "RPUValue" + String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + selection.getCurrentProjId();
                    priorValPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    valPer = getFormattedValue(AMOUNT, priorValPer);
                    discountDto.addStringProperties(discountPerColumn, valPer);

                    discountVariance = "RPUVariance" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    valPer2 = getFormattedValue(AMOUNT, "0");
                    discountDto.addStringProperties(discountVariance, valPer2);

                    discountVarPercent = "RPUPer" + discount + discountNo + CURRENT + selection.getCurrentProjId();
                    valPer2 = getFormattedValue(AMOUNT, "0");
                    discountDto.addStringProperties(discountVarPercent, valPer2); 
     }
      private void customize_PriorList(ProjectionVarianceDTO discountDto,List<Integer> projList,String discount,String discountNo,Object[] obj)
     {
                       if(!projList.isEmpty()){
                         for(int j=1;j<=projList.size();j++)  {
                         String column="DiscountAmountValue"+discount+discountNo+projList.get(j-1);
                         String priorValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4+(j*3)])));
                         discountDto.addStringProperties(column,  priorValue);
                          
                        String discountDollarVar="DiscountAmountVar"+discount+discountNo+projList.get(j-1);
                        String priorVal1 = StringUtils.EMPTY + obj[4+(j*3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal1)));
                        String val1 = getFormattedValue(RATE, variance);
                        discountDto.addStringProperties(discountDollarVar, val1);
                        
                        String discountDollarPer="DiscountAmountPer"+discount+discountNo+projList.get(j-1);
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal1))) / Double.valueOf(isNull(priorVal1));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        discountDto.addStringProperties(discountDollarPer, baseValu + PERCENT);
                        
                         column="DiscountSalesValue"+discount+discountNo+projList.get(j-1);
                         String priorPerValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5+(j*3)])));
                         String valPer = getFormattedValue(RATE , priorPerValue);
                        discountDto.addStringProperties(column, valPer + PERCENT);
                        
                         column="DiscountSalesVar"+discount+discountNo+projList.get(j-1);
                        priorVal1 = StringUtils.EMPTY + obj[5+(j*3)];
                        variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal1)));
                        val1 = getFormattedValue(AMOUNT, variance);
                        discountDto.addStringProperties(column, val1);
                        
                         column="DiscountSalesPer"+discount+discountNo+projList.get(j-1);
                         priorPerValue = StringUtils.EMPTY + obj[5+(j*3)];
                        Double perChangerpu = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorPerValue))) / Double.valueOf(isNull(priorPerValue));
                        if (perChangerpu.isNaN() || perChangerpu.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChangerpu))) {
                            perChangerpu = 0.0;
                        }
                        String changerpu = String.valueOf(perChangerpu);
                        String baseValurpu = getFormattedValue(RATE_PER, changerpu);
                        discountDto.addStringProperties(column, baseValurpu + PERCENT);
                        
                         column="RPUValue"+discount+discountNo+projList.get(j-1);
                        String rpuValues = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6+(j*3)])));
                        String rpuval = getFormattedValue(AMOUNT, rpuValues);
                        discountDto.addStringProperties(column, rpuval);
                        
                        column="RPUVariance"+discount+discountNo+projList.get(j-1);
                        String rpuPer = StringUtils.EMPTY + obj[6+(j*3)];
                        String rpupercentPer = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(rpuPer)));
                        String rpuPercentage = getFormattedValue(AMOUNT, rpupercentPer);
                        discountDto.addStringProperties(column,  rpuPercentage);
                        
                          column="RPUPer"+discount+discountNo+projList.get(j-1);
                         String priorRpuPer = StringUtils.EMPTY + obj[6+(j*3)];
                        Double rpuChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index+2])) - Double.valueOf(isNull(priorRpuPer))) / Double.valueOf(isNull(priorRpuPer));
                        if (rpuChange.isNaN() || rpuChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(rpuChange))) {
                            rpuChange = 0.0;
                        }
                        String changePriorRpu = String.valueOf(rpuChange);
                        String baseValueRpu = getFormattedValue(RATE_PER, changePriorRpu);
                        discountDto.addStringProperties(column, baseValueRpu + PERCENT);
                        
                       }
                     }
     }
     
   private String getVisibleColumn_Header(Object[] obj) {
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == 4) {
            commonColumn = "Q" +  obj[2]+ StringUtils.EMPTY + obj[3];
        } else if (frequencyDivision == 2) {
            commonColumn = "S" + obj[2] + StringUtils.EMPTY + obj[3];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[3];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[2])) - 1);
            commonColumn = monthName.toLowerCase() + obj[3];
        }
        return commonColumn;
    }   
}
