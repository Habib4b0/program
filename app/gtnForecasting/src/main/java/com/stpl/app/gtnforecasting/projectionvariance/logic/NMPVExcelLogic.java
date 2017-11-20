/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.logic;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.DESCENDING;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import com.stpl.ifs.ui.util.NumericConstants;
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
            netSalesPer, cogsValue, cogsVar, cogsPer, netProfitValue, netProfitVar, netProfitPer, netExFactoryValue, netExFactoryVar, netExFactoryPer, discountPerExFactoryValue,
            discountPerExFactoryVar, discountPerExFactoryPer, netExFactorySales, netExFactorySalesPerExFactory;

    private final Map<String, List<ProjectionVarianceDTO>> resultMap;

    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final PVSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    public static final String STRING_FORMAT_TWO_DECIMAL = "#,##0.00";
    private static final DecimalFormat RATE_PER = new DecimalFormat(STRING_FORMAT_TWO_DECIMAL);
    private static final DecimalFormat RATE_PERC = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String CURRENT = "Current";
    private static final String ACTUAL = "Actual";
    private static final String ACCRUAL = "Accrual";
    private static final int COLUMN_COUNT_DISCOUNT = 12;
    private static final int COLUMN_COUNT_TOTAL = 75;
    private static final int COLUMN_COUNT_DETAIL = 54;
    private final int index = 5;
    private final int index_detail_discount = 4;
    private int frequencyDivision;
    private final int baseColumn_levelName_index = 0;
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
    public static final String PRC_PROJECTION_RESULTS_DISCOUNT_PROCEDURE = "PRC_PROJECTION_RESULTS_DISCOUNT";
    public static final String QUARTERLY1 = "QUARTERLY";
    public static final String PIVOT1 = "PIVOT";
    private static String DASH = "-";
    List<Object> pivotDiscountList = new ArrayList<>();
    List<ProjectionVarianceDTO> discountList = new ArrayList<>();
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat(STRING_FORMAT_TWO_DECIMAL);
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private static final String DETAIL = "Detail";
    List<Object> pivotTotalList = new ArrayList<>();
    List<Integer> pivotPriorProjIdList = new ArrayList();
    private final Map<String, PVParameters> parameters = new HashMap();
    private final Map<String, String> customView_relationship_hierarchy = new HashMap();
    PVParameters parameterDto;
    private boolean discountFlag;
    private boolean isCustomView;
    Map<String, String> discountNameMap = new HashMap<>();
    public static final String PROGRAM = "Program";
    public static final String PROGRAM_CAT = "Program Category";
    public static final String VARIANCE1 = "VARIANCE";
    Map<String, Map<String, ProjectionVarianceDTO>> pivotDiscountMap = new HashMap<>();
    private final int total_discount_index = 8;
    private boolean actualBasis = false;
    private boolean accrualBasis = false;
    private static final String VAL = "Value";
    private static final String VAR = "Var";
    private static final String PER = "Per";
    private static final String P = "P";

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
        isCustomView = selection.isIsCustomHierarchy();
        actualBasis = (Constant.ACTUALS).equals(selection.getComparisonBasis());
        accrualBasis = (Constant.ACCRUALS).equals(selection.getComparisonBasis());

        if (isCustomView) {
            customView_relationship_hierarchy.putAll(getGroup_customViewNM());
        }

        isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());

        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {

            if (isTotal) {

                getTotalRawData();
                getTotalPivotVariance(selection);
                if (discountFlag) {
                    getTotalDiscount(selection);
                }

                calculateAndCustomize_total_period(procRawList_total);
                if (discountFlag) {
                    discount_Customize();
                }

            } else {
                executeProcedure_PRC_PV_SELECTION();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                    executeProcedure_PRC_PV_SELECTION();
                }
                if (isCustomView) {
                    calculateAndCustomize_periodForCustomView(procRawList_detail, procRawList_detail_discount);
                } else {
                    calculateAndCustomize_detail_period(procRawList_detail, procRawList_detail_discount);
                }
            }
        } else {

            if (isTotal) {

                String discLevel = selection.getDiscountLevel();
                if (discLevel.equals(PROGRAM) || discLevel.equals(PROGRAM_CAT)) {
                    getTotalPivotVariance(selection);
                    getTotalDiscount(selection);
                    getPivot_customization();

                } else {
                    getTotalRawData();
                    calculateAndCustomize_total_pivot(procRawList_total);
                }
            } else {
                executeProcedure_PRC_PV_SELECTION();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                    executeProcedure_PRC_PV_SELECTION();
                }

                customize_discount_pivot();
                calculateAndCustomize_detail_pivot(procRawList_detail);

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

    private void calculateAndCustomize_detail_period(List<Object[]> rawList, List<Object[]> rawList_discount) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
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
                String key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
                String discountName = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
                if (hierarchyKey.add(key)) {
                    listIndex = 0;
                    discountMap.clear();
                }
                Integer newListIndex = discountMap.get(discountName);
                if (newListIndex == null) {
                    discountMap.put(discountName, listIndex++);
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
                    detail.setHierarchyNo(obj[1].toString());
                    detail.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
                } else {
//                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
                      groupName = CommonUtil.getDisplayFormattedName(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString(),
                            selection.getSessionDTO().getHierarchyLevelDetails(), selection.getSessionDTO(), selection.getDisplayFormat());
                }

                detail.setGroup(groupName);
                pvList.add(detail);
            }
            //Ex-Factory-Sales
            if (selection.isColValue() && selection.isVarExFacSales()) {

                exFacValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, exFacValue, selection, AMOUNT);
                pvList.add(exFacValue);
            }
            if (selection.isColVariance() && selection.isVarExFacSales()) {

                exFacVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, exFacVar, selection, AMOUNT);
                pvList.add(exFacVar);
            }
            if (selection.isColPercentage() && selection.isVarExFacSales()) {

                exFacPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, exFacPer, selection, RATE_PER);
                pvList.add(exFacPer);
            }
            //Demand
            if (selection.isColValue() && selection.isVarDemandSales()) {
                demandValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, demandValue, selection, AMOUNT);
                pvList.add(demandValue);
            }
            if (selection.isColVariance() && selection.isVarDemandSales()) {
                demandVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, demandVar, selection, AMOUNT);
                pvList.add(demandVar);
            }
            if (selection.isColPercentage() && selection.isVarDemandSales()) {
                demandPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, demandPer, selection, RATE_PER);
                pvList.add(demandPer);
            }
            //Inventory Withdraw Sales
            if (selection.isColValue() && selection.isVarInvSales()) {
                invWithValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, invWithValue, selection, AMOUNT);
                pvList.add(invWithValue);
            }
            if (selection.isColVariance() && selection.isVarInvSales()) {
                invWithVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, invWithVar, selection, AMOUNT);
                pvList.add(invWithVar);
            }
            if (selection.isColPercentage() && selection.isVarInvSales()) {
                invWithPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, invWithPer, selection, RATE_PER);
                pvList.add(invWithPer);
            }
            //% Of Ex-Factory
            if (selection.isColValue() && selection.isVarPerExFacSales()) {
                perExFacValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, perExFacValue, selection, RATE_PER);
                pvList.add(perExFacValue);
            }
            if (selection.isColVariance() && selection.isVarPerExFacSales()) {
                perExFacVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, perExFacVar, selection, RATE_PER);
                pvList.add(perExFacVar);
            }
            if (selection.isColPercentage() && selection.isVarPerExFacSales()) {
                perExFacPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, perExFacPer, selection, RATE_PER);
                pvList.add(perExFacPer);
            }

            //% of Demand
            if (selection.isColValue() && selection.isVarPerDemandSales()) {
                perDemandValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, perDemandValue, selection, RATE_PER);
                pvList.add(perDemandValue);
            }
            if (selection.isColVariance() && selection.isVarPerDemandSales()) {
                perDemandVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, perDemandVar, selection, RATE_PER);
                pvList.add(perDemandVar);
            }
            if (selection.isColPercentage() && selection.isVarPerDemandSales()) {
                perDemandPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, perDemandPer, selection, RATE_PER);
                pvList.add(perDemandPer);
            }
            //% of inventory Withdraw Sales
            if (selection.isColValue() && selection.isVarPerInvSales()) {
                perInvWithValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, perInvWithValue, selection, RATE_PER);
                pvList.add(perInvWithValue);
            }
            if (selection.isColVariance() && selection.isVarPerInvSales()) {
                perInvWithVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, perInvWithVar, selection, RATE_PER);
                pvList.add(perInvWithVar);
            }
            if (selection.isColPercentage() && selection.isVarPerInvSales()) {
                perInvWithPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, perInvWithPer, selection, RATE_PER);
                pvList.add(perInvWithPer);
            }
            //Contract Sales @ WAC
            if (selection.isColValue() && selection.isVarContractsales()) {
                salesValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, salesValue, selection, AMOUNT);
                pvList.add(salesValue);
            }
            if (selection.isColVariance() && selection.isVarContractsales()) {
                salesVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, salesVar, selection, AMOUNT);
                pvList.add(salesVar);
            }
            if (selection.isColPercentage() && selection.isVarContractsales()) {
                salesPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, salesPer, selection, RATE_PER);
                pvList.add(salesPer);
            }
            //Contract Units
            if (selection.isColValue() && selection.isVarContractUnits()) {
                unitsValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, unitsValue, selection, AMOUNT_UNITS);
                pvList.add(unitsValue);
            }
            if (selection.isColVariance() && selection.isVarContractUnits()) {
                unitsVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, unitsVar, selection, AMOUNT_UNITS);
                pvList.add(unitsVar);
            }
            if (selection.isColPercentage() && selection.isVarContractUnits()) {
                unitsPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, unitsPer, selection, RATE_PER);
                pvList.add(unitsPer);
            }
            //Discount $
            if (selection.isColValue() && selection.isVarDisAmount()) {
                disDollValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, disDollValue, selection, AMOUNT);
                pvList.add(disDollValue);
            }
            if (selection.isColVariance() && selection.isVarDisAmount()) {
                disDollVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, disDollVar, selection, AMOUNT);
                pvList.add(disDollVar);
            }
            if (selection.isColPercentage() && selection.isVarDisAmount()) {
                disDollPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, disDollPer, selection, RATE_PER);
                pvList.add(disDollPer);
            }
            //Discount %
            if (selection.isColValue() && selection.isVarDisRate()) {
                disPerValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.THIRTY_ONE : NumericConstants.THIRTY_FOUR, disPerValue, selection, RATE_PER);
                pvList.add(disPerValue);
            }
            if (selection.isColVariance() && selection.isVarDisRate()) {
                disPerVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTY_ONE : NumericConstants.THIRTY_FOUR, disPerVar, selection, RATE_PER);
                pvList.add(disPerVar);
            }
            if (selection.isColPercentage() && selection.isVarDisRate()) {
                disPerPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTY_ONE : NumericConstants.THIRTY_FOUR, disPerPer, selection, RATE_PER);
                pvList.add(disPerPer);
            }
            //RPU
            if (selection.isColValue() && selection.isVarRPU()) {
                rpuValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, rpuValue, selection, AMOUNT);
                pvList.add(rpuValue);
            }
            if (selection.isColVariance() && selection.isVarRPU()) {
                rpuVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, rpuVar, selection, AMOUNT);
                pvList.add(rpuVar);
            }
            if (selection.isColPercentage() && selection.isVarRPU()) {
                rpuPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, rpuPer, selection, RATE_PER);
                pvList.add(rpuPer);
            }
            //Discount % of Ex-Factory 44
            if (selection.isColValue() && selection.isDiscountPerExFactory()) {
                discountPerExFactoryValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, discountPerExFactoryValue, selection, RATE_PER);
                pvList.add(discountPerExFactoryValue);
            }
            if (selection.isColVariance() && selection.isDiscountPerExFactory()) {
                discountPerExFactoryVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, discountPerExFactoryVar, selection, RATE_PER);
                pvList.add(discountPerExFactoryVar);
            }
            if (selection.isColPercentage() && selection.isDiscountPerExFactory()) {
                discountPerExFactoryPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, discountPerExFactoryPer, selection, RATE_PER);
                pvList.add(discountPerExFactoryPer);
            }
            //Net Sales 
            if (selection.isColValue() && selection.isVarNetSales()) {
                netSalesValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, netSalesValue, selection, AMOUNT);
                pvList.add(netSalesValue);
            }
            if (selection.isColVariance() && selection.isVarNetSales()) {
                netSalesVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, netSalesVar, selection, AMOUNT);
                pvList.add(netSalesVar);
            }
            if (selection.isColPercentage() && selection.isVarNetSales()) {
                netSalesPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, netSalesPer, selection, RATE_PER);
                pvList.add(netSalesPer);
            }
            //Net Sales ExFactory 42
            if (selection.isColValue() && selection.isNetSalesExFactory()) {
                netExFactoryValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, netExFactoryValue, selection, RATE_PER);
                pvList.add(netExFactoryValue);
            }
            if (selection.isColVariance() && selection.isNetSalesExFactory()) {
                netExFactoryVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, netExFactoryVar, selection, RATE_PER);
                pvList.add(netExFactoryVar);
            }
            if (selection.isColPercentage() && selection.isNetSalesExFactory()) {
                netExFactoryPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, netExFactoryPer, selection, RATE_PER);
                pvList.add(netExFactoryPer);
            }

            Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
            List productList = relationshipLevelDetailsMap.get(obj[1].toString());
            if (!isTotal && P.equals(String.valueOf(productList.get(4)))) {
                /**
                 * Net Ex-Factory Sales
                 */
                if (selection.isNetExFactorySales()) {
                    if (selection.isColValue()) {
                        netExFactorySales = new ProjectionVarianceDTO();
                        calculate(Constant.NET_EXFACT_SALES, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, netExFactorySales, selection, AMOUNT);
                        pvList.add(netExFactorySales);
                    }
                    if (selection.isColVariance()) {
                        netExFactorySales = new ProjectionVarianceDTO();
                        calculate(Constant.NET_EXFACT_SALES, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, netExFactorySales, selection, AMOUNT);
                        pvList.add(netExFactorySales);
                    }
                    if (selection.isColPercentage()) {
                        netExFactorySales = new ProjectionVarianceDTO();
                        calculate(Constant.NET_EXFACT_SALES, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, netExFactorySales, selection, RATE_PER);
                        pvList.add(netExFactorySales);
                    }
                }
                /**
                 * Net Ex-Factory Sales as % of Ex-Factory Sales
                 */
                if (selection.isNetExFactorySalesPerExFactory()) {
                    if (selection.isColValue()) {
                        netExFactorySalesPerExFactory = new ProjectionVarianceDTO();
                        calculate(Constant.NET_EXFACT_SALES_PER_EXFACT, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, netExFactorySalesPerExFactory, selection, RATE_PER);
                        pvList.add(netExFactorySalesPerExFactory);
                    }
                    if (selection.isColVariance()) {
                        netExFactorySalesPerExFactory = new ProjectionVarianceDTO();
                        calculate(Constant.NET_EXFACT_SALES_PER_EXFACT, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, netExFactorySalesPerExFactory, selection, RATE_PER);
                        pvList.add(netExFactorySalesPerExFactory);
                    }
                    if (selection.isColPercentage()) {
                        netExFactorySalesPerExFactory = new ProjectionVarianceDTO();
                        calculate(Constant.NET_EXFACT_SALES_PER_EXFACT, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, netExFactorySalesPerExFactory, selection, RATE_PER);
                        pvList.add(netExFactorySalesPerExFactory);
                    }
                }
            }

            //COGS
            if (selection.isColValue() && selection.isVarCOGC()) {
                cogsValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, cogsValue, selection, AMOUNT);
                pvList.add(cogsValue);
            }
            if (selection.isColVariance() && selection.isVarCOGC()) {
                cogsVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, cogsVar, selection, AMOUNT);
                pvList.add(cogsVar);
            }
            if (selection.isColPercentage() && selection.isVarCOGC()) {
                cogsPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, cogsPer, selection, RATE_PER);
                pvList.add(cogsPer);
            }
            //Net Profit
            if (selection.isColValue() && selection.isVarNetProfit()) {
                netProfitValue = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, netProfitValue, selection, AMOUNT);
                pvList.add(netProfitValue);
            }
            if (selection.isColVariance() && selection.isVarNetProfit()) {
                netProfitVar = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, netProfitVar, selection, AMOUNT);
                pvList.add(netProfitVar);
            }
            if (selection.isColPercentage() && selection.isVarNetProfit()) {
                netProfitPer = new ProjectionVarianceDTO();
                calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, netProfitPer, selection, RATE_PER);
                pvList.add(netProfitPer);
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    private void updateList(List<ProjectionVarianceDTO> pvList, Object[] obj, int index) {

        int listIndex = 1;

        //Ex-Factory-Sales
        if (selection.isColValue() && selection.isVarExFacSales()) {
            exFacValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, exFacValue, selection, AMOUNT);

        }
        if (selection.isColVariance() && selection.isVarExFacSales()) {
            exFacVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, exFacVar, selection, AMOUNT);

        }
        if (selection.isColPercentage() && selection.isVarExFacSales()) {
            exFacPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, exFacPer, selection, RATE_PER);
        }
        //Demand
        if (selection.isColValue() && selection.isVarDemandSales()) {
            demandValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, demandValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarDemandSales()) {
            demandVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, demandVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarDemandSales()) {
            demandPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, demandPer, selection, RATE_PER);
        }
        //Inventory Withdraw Sales
        if (selection.isColValue() && selection.isVarInvSales()) {
            invWithValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, invWithValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarInvSales()) {
            invWithVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, invWithVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarInvSales()) {
            invWithPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, invWithPer, selection, RATE_PER);
        }
        //% of Ex-Factory
        if (selection.isColValue() && selection.isVarPerExFacSales()) {
            perExFacValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, perExFacValue, selection, RATE_PER);
        }
        if (selection.isColVariance() && selection.isVarPerExFacSales()) {
            perExFacVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, perExFacVar, selection, RATE_PER);
        }
        if (selection.isColPercentage() && selection.isVarPerExFacSales()) {
            perExFacPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, perExFacPer, selection, RATE_PER);
        }
        //% of Demand
        if (selection.isColValue() && selection.isVarPerDemandSales()) {
            perDemandValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, perDemandValue, selection, RATE_PER);
        }
        if (selection.isColVariance() && selection.isVarPerDemandSales()) {
            perDemandVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, perDemandVar, selection, RATE_PER);
        }
        if (selection.isColPercentage() && selection.isVarPerDemandSales()) {
            perDemandPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_DEMAND.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, perDemandPer, selection, RATE_PER);
        }
        //% of inventory Withdraw Sales
        if (selection.isColValue() && selection.isVarPerInvSales()) {
            perInvWithValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, perInvWithValue, selection, RATE_PER);
        }
        if (selection.isColVariance() && selection.isVarPerInvSales()) {
            perInvWithVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, perInvWithVar, selection, RATE_PER);
        }
        if (selection.isColPercentage() && selection.isVarPerInvSales()) {
            perInvWithPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, perInvWithPer, selection, RATE_PER);
        }
        //Contract Sales @ WAC
        if (selection.isColValue() && selection.isVarContractsales()) {
            salesValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, salesValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarContractsales()) {
            salesVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, salesVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarContractsales()) {
            salesPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, salesPer, selection, RATE_PER);
        }
        //Contract Units
        if (selection.isColValue() && selection.isVarContractUnits()) {
            unitsValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, unitsValue, selection, AMOUNT_UNITS);
        }
        if (selection.isColVariance() && selection.isVarContractUnits()) {
            unitsVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, unitsVar, selection, AMOUNT_UNITS);
        }
        if (selection.isColPercentage() && selection.isVarContractUnits()) {
            unitsPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, unitsPer, selection, RATE_PER);
        }
        //Discount $
        if (selection.isColValue() && selection.isVarDisAmount()) {
            disDollValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, disDollValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarDisAmount()) {
            disDollVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, disDollVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarDisAmount()) {
            disDollPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, disDollPer, selection, RATE_PER);
        }
        //Discount %
        if (selection.isColValue() && selection.isVarDisRate()) {
            disPerValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.THIRTY_ONE : NumericConstants.THIRTY_FOUR, disPerValue, selection, RATE_PER);
        }
        if (selection.isColVariance() && selection.isVarDisRate()) {
            disPerVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTY_ONE : NumericConstants.THIRTY_FOUR, disPerVar, selection, RATE_PER);
        }
        if (selection.isColPercentage() && selection.isVarDisRate()) {
            disPerPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTY_ONE : NumericConstants.THIRTY_FOUR, disPerPer, selection, RATE_PER);
        }
        //RPU
        if (selection.isColValue() && selection.isVarRPU()) {
            rpuValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, rpuValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarRPU()) {
            rpuVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, rpuVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarRPU()) {
            rpuPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, rpuPer, selection, RATE_PER);
        }

        //Discount % of Ex-Factory
        if (selection.isColValue() && selection.isDiscountPerExFactory()) {
            discountPerExFactoryValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, discountPerExFactoryValue, selection, RATE_PER);
        }
        if (selection.isColVariance() && selection.isDiscountPerExFactory()) {
            discountPerExFactoryVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, discountPerExFactoryVar, selection, RATE_PER);
        }
        if (selection.isColPercentage() && selection.isDiscountPerExFactory()) {
            discountPerExFactoryPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, discountPerExFactoryPer, selection, RATE_PER);
        }
        //Net Sales 
        if (selection.isColValue() && selection.isVarNetSales()) {
            netSalesValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, netSalesValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarNetSales()) {
            netSalesVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, netSalesVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarNetSales()) {
            netSalesPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, netSalesPer, selection, RATE_PER);
        }
        //Net Sales ExFactory
        if (selection.isColValue() && selection.isNetSalesExFactory()) {
            netExFactoryValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, netExFactoryValue, selection, RATE_PER);
        }
        if (selection.isColVariance() && selection.isNetSalesExFactory()) {
            netExFactoryVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, netExFactoryVar, selection, RATE_PER);
        }
        if (selection.isColPercentage() && selection.isNetSalesExFactory()) {
            netExFactoryPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, netExFactoryPer, selection, RATE_PER);
        }

        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        List productList = relationshipLevelDetailsMap.get(obj[1].toString());
        if (!isTotal && P.equals(String.valueOf(productList.get(4)))) {
            /**
             * Net Ex-Factory Sales
             */
            if (selection.isNetExFactorySales()) {
                if (selection.isColValue()) {
                    netExFactorySales = pvList.get(listIndex++);
                    calculate(Constant.NET_EXFACT_SALES, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, netExFactorySales, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    netExFactorySales = pvList.get(listIndex++);
                    calculate(Constant.NET_EXFACT_SALES, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, netExFactorySales, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    netExFactorySales = pvList.get(listIndex++);
                    calculate(Constant.NET_EXFACT_SALES, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, netExFactorySales, selection, RATE_PER);
                }
            }
            /**
             * Net Ex-Factory Sales as % of Ex-Factory Sales
             */
            if (selection.isNetExFactorySalesPerExFactory()) {
                if (selection.isColValue()) {
                    netExFactorySalesPerExFactory = pvList.get(listIndex++);
                    calculate(Constant.NET_EXFACT_SALES_PER_EXFACT, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, netExFactorySalesPerExFactory, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    netExFactorySalesPerExFactory = pvList.get(listIndex++);
                    calculate(Constant.NET_EXFACT_SALES_PER_EXFACT, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, netExFactorySalesPerExFactory, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    netExFactorySalesPerExFactory = pvList.get(listIndex++);
                    calculate(Constant.NET_EXFACT_SALES_PER_EXFACT, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, netExFactorySalesPerExFactory, selection, RATE_PER);
                }
            }
        }
        //COGS
        if (selection.isColValue() && selection.isVarCOGC()) {
            cogsValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, cogsValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarCOGC()) {
            cogsVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, cogsVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarCOGC()) {
            cogsPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, cogsPer, selection, RATE_PER);
        }
        //Net Profit
        if (selection.isColValue() && selection.isVarNetProfit()) {
            netProfitValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, netProfitValue, selection, AMOUNT);
        }
        if (selection.isColVariance() && selection.isVarNetProfit()) {
            netProfitVar = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, netProfitVar, selection, AMOUNT);
        }
        if (selection.isColPercentage() && selection.isVarNetProfit()) {
            netProfitPer = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, netProfitPer, selection, RATE_PER);
        }
        LOGGER.debug("List Index----------" + listIndex);

    }

    private void calculate(String varaibleName, String varibaleCat, Object[] obj, int currentIndex, ProjectionVarianceDTO pvDTO,
            PVSelectionDTO selection, DecimalFormat format) {
        boolean flag;
        flag = varaibleName.contains("%");
        pvDTO.setGroup(varaibleName.concat(varibaleCat));
        String commonColumn = StringUtils.EMPTY;
        List<Integer> priorList = selection.getProjIdList();
        switch (frequencyDivision) {
            case NumericConstants.FOUR:
                commonColumn = "Q" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                break;
            case NumericConstants.TWO:
                commonColumn = "S" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                break;
            case 1:
                commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
                break;
            case NumericConstants.TWELVE:
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
                commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
                break;
            default:
                break;
        }
        PVCommonLogic.customizePeriod(commonColumn, varibaleCat, selection, pvDTO, format, currentIndex, obj, flag);
        for (int j = 0; j < priorList.size(); j++) {
            PVCommonLogic.getPriorCommonCustomization(varibaleCat, selection, obj, pvDTO, commonColumn + priorList.get(j), currentIndex, j, flag, isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT_DETAIL, format);
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
        List< Object[]> rawList = null;
        String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        List<Integer> projectionIdList = new ArrayList();
        procRawList_total.clear();
        priorList.clear();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = QUARTERLY1;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMIANNUAL_CAPS;
        } else if (frequency.equals(Constant.MONTHLY)) {
            frequency = Constant.MONTHLY_COLUMN;
        } else {
            frequency = Constant.ANNUAL_CAPS;
        }
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, VARIANCE1, selection.getSessionDTO().getSessionId(), selection.getUserId(), PIVOT1};
        rawList = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        procRawList_total.addAll(rawList);
        rawList.clear();
    }

    public void executeProcedure_PRC_PV_SELECTION() {
        List< Object[]> rawList = null;
        boolean isRefresh = true;
        if (parameterDto.getViewName().equals("DETAIL_TOTAL_DISCOUNT")) {
            PVParameters lastParameter = parameters.get("PRC_PROJECTION_VARIANCE_DISCOUNT");
            if (lastParameter != null) {
                isRefresh = !lastParameter.equals(parameterDto);
            }
            parameters.put("PRC_PROJECTION_VARIANCE_DISCOUNT", parameterDto.clone());
        } else {
            PVParameters lastParameter = null;
            lastParameter = parameters.get(PRC_PV_SELECTION);
            if (lastParameter != null) {
                isRefresh = !lastParameter.equals(parameterDto);
            }
            parameters.put(PRC_PV_SELECTION, parameterDto.clone());
        }
        if (isRefresh) {
            NMProjectionVarianceLogic logic = new NMProjectionVarianceLogic();
            List<Integer> projectionIdList = new ArrayList();
            projectionIdList.add(selection.getCurrentProjId());
            projectionIdList.addAll(selection.getProjIdList());
            String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
            String levelName = !selection.getDeductionLevelFilter().isEmpty() ? selection.getSelectedDeductionLevelName() : parameterDto.getDiscountLevel();
            String sIds = selection.getDeductionLevelFilter().isEmpty() ? null :logic.removeBracesInList(selection.getDeductionLevelFilter());
            Object[] orderedArg = {projectionId, parameterDto.getUserId(), parameterDto.getSessionId(),levelName
                , parameterDto.getFrequency(), selection.isIsCustomHierarchy() ? "D" : parameterDto.getViewIndicator(),
                parameterDto.getGroupFilter(), parameterDto.getGroupFilterValue(), parameterDto.getHierarchyNo(),
                parameterDto.getLevelNo(), parameterDto.getViewName(), parameterDto.getCustomViewMasterSid(), parameterDto.getView().equals("Variable") ? "pivot" : parameterDto.getView(),selection.getUomCode(),"ALL".equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), "ALL".equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),sIds};
            rawList = CommonLogic.callProcedure(PRC_PV_SELECTION, orderedArg);
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
        int totalIndex = NumericConstants.TWO, i = 0;

        for (ListIterator<Object[]> iterator = rawList.listIterator(); iterator.hasNext();) {
            Object[] obj = iterator.next();
            if (i++ == 0) {
                addList(pvList, obj, totalIndex);
            } else {
                updateList(pvList, obj, totalIndex);
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

            List<String> common = null;
            if (!"null".equalsIgnoreCase(String.valueOf(obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index])) && !"null".equalsIgnoreCase(String.valueOf(obj[isTotal ? frequencyDivision == 1 ? 0 : baseColumn_period_indexForTotal : baseColumn_period_index]))) {
                common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index].toString()), Integer.valueOf(obj[isTotal ? frequencyDivision == 1 ? 0 : baseColumn_period_indexForTotal : baseColumn_period_index].toString()));
                String groupId = common.get(0);

                if (periodList.contains(groupId)) {
                    String gorupName = common.get(1);
                    ProjectionVarianceDTO frequencyBasedDTO = new ProjectionVarianceDTO();
                    frequencyBasedDTO.setGroup(gorupName);
                    if (i++ == 0) {
                        addList_pivot(pvList, obj, frequencyBasedDTO, NumericConstants.TWO, "");

                    } else {
                        updateList_pivot(pvList, obj, frequencyBasedDTO, NumericConstants.TWO, "");
                    }
                }
            }

        }
    }

    private void addList_pivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexForTotal, String key) {

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
                detail.setHierarchyNo(obj[1].toString());
//                detail.setParentHierarchyNo(obj[obj.length-1] == null ? null : obj[obj.length-1].toString().substring(obj[obj.length-1].toString().indexOf('-') + 1));
                detail.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
            } else {
//                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
                groupName = CommonUtil.getDisplayFormattedName(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString(),
                            selection.getSessionDTO().getHierarchyLevelDetails(), selection.getSessionDTO(), selection.getDisplayFormat());
            }

            detail.setGroup(groupName);
            pvList.add(detail);
        }

        pvList.add(frequencyBasedDTO);

        //Ex-Factory-Sales
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("ExFacValue", Constant.VALUE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ExFacVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.SEVEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Inventory Withdraw Sales
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constant.VALUE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Ex-Factory
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constant.VALUE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of inventory Withdraw Sales
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constant.VALUE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constant.CHANGE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constant.VALUE, obj, indexForTotal + NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constant.VARIANCE, obj, indexForTotal + NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constant.CHANGE, obj, indexForTotal + NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales ExFactory
        if (selection.isNetSalesExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesExFactoryValue", Constant.VALUE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesExFactoryVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesExFactoryPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount Per ExFactory
        if (selection.isDiscountPerExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountPerExFactoryValue", Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountPerExFactoryVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountPerExFactoryPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }

        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        List productList = relationshipLevelDetailsMap.get(obj[1].toString());
        if (!isTotal && P.equals(String.valueOf(productList.get(4)))) {
            if (selection.isNetExFactorySales()) {
                if (selection.isColValue()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_COLUMN_VALUE, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_COLUMN_VARIANCE, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            if (selection.isNetExFactorySalesPerExFactory()) {
                if (selection.isColValue()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
            }
        }

        if (!isTotal) {
            String commonColumn = StringUtils.EMPTY;

            switch (frequencyDivision) {
                case NumericConstants.FOUR:
                    commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.FOUR];
                    break;
                case NumericConstants.TWO:
                    commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.FOUR];
                    break;
                case 1:
                    commonColumn = String.valueOf(obj[NumericConstants.FOUR]);
                    break;
                case NumericConstants.TWELVE:
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[NumericConstants.FOUR];
                    break;
                default:
                    break;
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

    private void calculateForTotal(String variableName, String varibaleCat, Object[] obj, int currentIndex, ProjectionVarianceDTO pvDTO, PVSelectionDTO selection, DecimalFormat format) {
        PVCommonLogic.customizePeriod(variableName, varibaleCat, selection, pvDTO, format, currentIndex, obj, format.equals(RATE_PER));
        priorList.clear();
        priorList.addAll(selection.getProjIdList());
        for (int j = 0; j < priorList.size(); j++) {
            PVCommonLogic.getPriorCommonCustomization(varibaleCat, selection, obj, pvDTO, variableName + priorList.get(j), currentIndex, j, format.equals(RATE_PER), isTotal ? COLUMN_COUNT_TOTAL : COLUMN_COUNT_DETAIL, format);
        }
    }

    private void calculateAndCustomize_detail_pivot(List<Object[]> rawList) {
        List<String> periodList = selection.getPeriodList();

        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key;
            if ("null".equals(String.valueOf(obj[obj.length - 1]))) {
                key = obj[baseColumn_hierarchy_index].toString();
            } else {
                key = obj[baseColumn_hierarchy_index].toString().endsWith(".") ? obj[baseColumn_hierarchy_index].toString() + "$" + obj[obj.length - 1].toString() : obj[baseColumn_hierarchy_index].toString() + ".$" + obj[obj.length - 1].toString();
            }
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[isTotal ? baseColumn_year_indexForTotal : baseColumn_year_index].toString()), Integer.valueOf(obj[isTotal ? frequencyDivision == 1 ? 0 : baseColumn_period_indexForTotal : baseColumn_period_index].toString()));
            String groupId = common.get(0);
            if (periodList.contains(groupId)) {
                String gorupName = common.get(1);
                ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
                freVarianceDTO.setGroup(gorupName);
                if (pvList == null) {
                    //To check condition total or details values
                    pvList = new ArrayList();

                    addList_pivot(pvList, obj, freVarianceDTO, index, key);
                    hierarchyAndTP_keys(obj, key, pvList);
                } else {
                    updateList_pivot(pvList, obj, freVarianceDTO, index, key);
                }
            }
        }
    }

    private void updateList_pivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexForTotal, String key) {

        pvList.add(frequencyBasedDTO);
        //Ex-Factory-Sales
        if (selection.isVarExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("ExFacValue", Constant.VALUE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.FIVE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ExFacVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.FIVE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ExFacPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FOUR : NumericConstants.FIVE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Demand
        if (selection.isVarDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("DemandSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DemandSalesVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DemandSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SEVEN : NumericConstants.TEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Inventory Withdraw Sales
        if (selection.isVarInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("InvWithValue", Constant.VALUE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("InvWithVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("InvWithPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TEN : NumericConstants.THIRTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Ex-Factory
        if (selection.isVarPerExFacSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerExFacValue", Constant.VALUE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerExFacVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerExFacPer", Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTEEN : NumericConstants.SIXTEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of Demand
        if (selection.isVarPerDemandSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerDemandSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerDemandSalesVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerDemandSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTEEN : NumericConstants.NINETEEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //% of inventory Withdraw Sales
        if (selection.isVarPerInvSales()) {
            if (selection.isColValue()) {
                calculateForTotal("PerInvWithValue", Constant.VALUE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("PerInvWithVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("PerInvWithPer", Constant.CHANGE, obj, isTotal ? NumericConstants.NINETEEN : NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Sales @ WAC
        if (selection.isVarContractsales()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractSalesWACValue", Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractSalesWACVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractSalesWACVarPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_TWO : NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Contract Units
        if (selection.isVarContractUnits()) {
            if (selection.isColValue()) {
                calculateForTotal("ContractUnitsValue", Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("ContractUnitsVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("ContractUnitsPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_FIVE : NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountAmountValue", Constant.VALUE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountAmountVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountAmountPer", Constant.CHANGE, obj, isTotal ? NumericConstants.TWENTY_EIGHT : NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.THIRTY : NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountSalesVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.THIRTY : NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, RATE_PERC);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.THIRTY : NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculateForTotal("RPUValue", Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("RPUVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("RPUPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_SIX : NumericConstants.THIRTY_SEVEN, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales 
        if (selection.isVarNetSales()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesValue", Constant.VALUE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FORTY_NINE : NumericConstants.FORTY, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //COGS
        if (selection.isVarCOGC()) {
            if (selection.isColValue()) {
                calculateForTotal("COGCValue", Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("COGCVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("COGCPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_TWO : NumericConstants.FORTY_THREE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Profit
        if (selection.isVarNetProfit()) {
            if (selection.isColValue()) {
                calculateForTotal("NetProfitValue", Constant.VALUE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetProfitVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, AMOUNT);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetProfitPer", Constant.CHANGE, obj, isTotal ? NumericConstants.FIFTY_FIVE : NumericConstants.FORTY_SIX, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Net Sales ExFactory
        if (selection.isNetSalesExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("NetSalesExFactoryValue", Constant.VALUE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("NetSalesExFactoryVariance", Constant.VARIANCE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("NetSalesExFactoryPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SIXTY_SEVEN : NumericConstants.FORTY_NINE, frequencyBasedDTO, selection, RATE_PER);
            }
        }
        //Discount Per ExFactory
        if (selection.isDiscountPerExFactory()) {
            if (selection.isColValue()) {
                calculateForTotal("DiscountPerExFactoryValue", Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColVariance()) {
                calculateForTotal("DiscountPerExFactoryVar", Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
            if (selection.isColPercentage()) {
                calculateForTotal("DiscountPerExFactoryPer", Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY : NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, RATE_PER);
            }
        }

        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        List productList = relationshipLevelDetailsMap.get(obj[1].toString());
        if (!isTotal && P.equals(String.valueOf(productList.get(4)))) {
            if (selection.isNetExFactorySales()) {
                if (selection.isColValue()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_COLUMN_VALUE, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_COLUMN_VARIANCE, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_THREE : NumericConstants.FIFTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            if (selection.isNetExFactorySalesPerExFactory()) {
                if (selection.isColValue()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constant.VALUE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constant.VARIANCE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constant.CHANGE, obj, isTotal ? NumericConstants.SEVENTY_SIX : NumericConstants.FIFTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
            }
        }

        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.FOUR];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.FOUR];
        } else if (frequencyDivision == 1) {
            commonColumn = String.valueOf(obj[NumericConstants.FOUR]);
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
            commonColumn = monthName.toLowerCase() + obj[NumericConstants.FOUR];
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

    public void getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int currentIndex, boolean isPer, String parentGroup) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        String lastGroupName = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());

        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (PROGRAM_CAT.equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
                    if (!"".equals(lastValue) && !"null".equals(lastValue) && obj[NumericConstants.TWO] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                        pvDTO.setGroup(lastValue);
                        resultDto.add(pvDTO);

                        pvDTO = new ProjectionVarianceDTO();
                    }
                    lastValue = String.valueOf(obj[NumericConstants.TWO]);

                    pvDTO.setGroup(lastValue);
                } else {
                    if (!StringUtils.EMPTY.equals(lastValue) && !Constant.NULL.equals(lastValue) && obj[obj.length - 1] != null && !lastValue.equals(String.valueOf(obj[obj.length - 1]))) {
                        pvDTO.setGroup(lastGroupName);
                        resultDto.add(pvDTO);
                        pvDTO = new ProjectionVarianceDTO();
                    }

                    lastValue = String.valueOf(obj[obj.length - 1]);
                    lastGroupName = String.valueOf(obj[NumericConstants.TWO]);
                    pvDTO.setGroup(lastGroupName);
                }
                String commonColumn = StringUtils.EMPTY;
                switch (frequencyDivision) {
                    case NumericConstants.FOUR:
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                        break;
                    case NumericConstants.TWO:
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                        break;
                    case 1:
                        commonColumn = StringUtils.EMPTY + obj[0];
                        break;
                    case NumericConstants.TWELVE:
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                        break;
                    default:
                        break;
                }

                PVCommonLogic.customizePeriod(commonColumn, projSelDTO.getVarIndicator(), projSelDTO, pvDTO, isPer ? RATE : AMOUNT, currentIndex, obj, isPer);
                for (int j = 0; j < priorList.size(); j++) {
                    PVCommonLogic.getPriorCommonCustomization(projSelDTO.getVarIndicator(), projSelDTO, obj, pvDTO, commonColumn + priorList.get(j), currentIndex, j, isPer, COLUMN_COUNT_DISCOUNT, isPer ? RATE : AMOUNT);
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
            resultMap.put(parentGroup, resultDto);
        } else {
            for (Iterator<String> it = projSelDTO.getDiscountNameList().iterator(); it.hasNext();) {
                pvDTO = new ProjectionVarianceDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove("group");
                for (String Obj : columnList) {
                    pvDTO.addStringProperties(Obj, DASH);
                }
                resultDto.add(pvDTO);
            }
        }

        LOGGER.info("Ending getCustomisedProjectionResultsTotalDiscount with dataList list size  = = >" + dataList.size());
    }

    private void commonCustomizationForTotalDiscount(String group, List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int indexValue, boolean isPer) {
        LOGGER.info("Inside commonCustomizationForTotalDiscount");

        String parentGroup;
        if (!isDetail) {
            if (projSelDTO.isColValue()) {
                projSelDTO.setVarIndicator(Constant.VALUE);
                parentGroup = group + "value";
                if (!selection.getDiscountNameList().isEmpty()) {
                    getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, isPer, parentGroup);
                }
            }
            if (projSelDTO.isColVariance()) {
                projSelDTO.setVarIndicator(Constant.VARIANCE);
                parentGroup = group + "variance";
                if (!selection.getDiscountNameList().isEmpty()) {
                    getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, isPer, parentGroup);
                }
            }
            if (projSelDTO.isColPercentage()) {

                parentGroup = group + "change";
                projSelDTO.setVarIndicator(Constant.CHANGE);
                if (!selection.getDiscountNameList().isEmpty()) {
                    getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, isPer, parentGroup);
                }
            }

        }
        LOGGER.info("Ending commonCustomizationForTotalDiscount");
    }

    private void calculate_discount(String varaibleName, String varibaleCat, String masterKey, Object[] obj, int currentIndex, ProjectionVarianceDTO pvDTO,
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

        switch (frequencyDivision) {
            case NumericConstants.FOUR:
                commonColumn = "Q" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
                break;
            case NumericConstants.TWO:
                commonColumn = "S" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
                break;
            case 1:
                commonColumn = StringUtils.EMPTY + obj[baseColumn_year_discount_index];
                break;
            case NumericConstants.TWELVE:
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[baseColumn_period_discount_index])) - 1);
                commonColumn = monthName.toLowerCase() + obj[baseColumn_year_discount_index];
                break;
            default:
                break;
        }
        PVCommonLogic.customizePeriod(commonColumn, varibaleCat, selection, pvDTO, format, currentIndex, obj, format.equals(RATE_PER));
        List<Integer> priorList = selection.getProjIdList();
        for (int j = 0; j < priorList.size(); j++) {
            PVCommonLogic.getPriorCommonCustomization(varibaleCat, selection, obj, pvDTO, commonColumn + priorList.get(j), currentIndex, j, format.equals(RATE_PER), COLUMN_COUNT_DISCOUNT, format);
        }
    }

    private void addList_detail_discount(String key, final Object[] obj) {
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                disDollValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, key, obj, NumericConstants.SIX, disDollValue, selection, AMOUNT, true, 0);
            }
            if (selection.isColVariance()) {
                disDollVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, key, obj, NumericConstants.SIX, disDollVar, selection, AMOUNT, true, 0);
            }
            if (selection.isColPercentage()) {
                disDollPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, key, obj, NumericConstants.SIX, disDollPer, selection, RATE_PER, true, 0);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                disPerValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, key, obj, NumericConstants.NINE, disPerValue, selection, RATE_PER, true, 0);
            }
            if (selection.isColVariance()) {
                disPerVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, key, obj, NumericConstants.NINE, disPerVar, selection, RATE_PER, true, 0);
            }
            if (selection.isColPercentage()) {
                disPerPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, key, obj, NumericConstants.NINE, disPerPer, selection, RATE_PER, true, 0);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                rpuValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, key, obj, NumericConstants.TWELVE, rpuValue, selection, AMOUNT, true, 0);
            }
            if (selection.isColVariance()) {
                rpuVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, key, obj, NumericConstants.TWELVE, rpuVar, selection, AMOUNT, true, 0);
            }
            if (selection.isColPercentage()) {
                rpuPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, key, obj, NumericConstants.TWELVE, rpuPer, selection, RATE_PER, true, 0);
            }
        }
        //Discount Exfactory Per Change
        if (selection.isDiscountPerExFactory()) {
            if (selection.isColValue()) {
                discountPerExFactoryValue = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, key, obj, NumericConstants.FIFTEEN, discountPerExFactoryValue, selection, AMOUNT, true, 0);
            }
            if (selection.isColVariance()) {
                discountPerExFactoryVar = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, key, obj, NumericConstants.FIFTEEN, discountPerExFactoryVar, selection, AMOUNT, true, 0);
            }
            if (selection.isColPercentage()) {
                discountPerExFactoryPer = new ProjectionVarianceDTO();
                calculate_discount(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, key, obj, NumericConstants.FIFTEEN, discountPerExFactoryPer, selection, RATE_PER, true, 0);
            }
        }
    }

    private void updateList_detail_discount(String key, final Object[] obj, int listIndex) {
        //Discount $
        if (selection.isVarDisAmount()) {
            if (selection.isColValue()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, key, obj, NumericConstants.SIX, null, selection, AMOUNT, false, listIndex);
            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, key, obj, NumericConstants.SIX, null, selection, AMOUNT, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, key, obj, NumericConstants.SIX, null, selection, RATE_PER, false, listIndex);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            if (selection.isColValue()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, key, obj, NumericConstants.NINE, null, selection, RATE_PER, false, listIndex);

            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, key, obj, NumericConstants.NINE, null, selection, RATE_PER, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, key, obj, NumericConstants.NINE, null, selection, RATE_PER, false, listIndex);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            if (selection.isColValue()) {
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, key, obj, NumericConstants.TWELVE, null, selection, AMOUNT, false, listIndex);

            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, key, obj, NumericConstants.TWELVE, null, selection, AMOUNT, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, key, obj, NumericConstants.TWELVE, null, selection, RATE_PER, false, listIndex);
            }
        }
        //Discount Exfactory Per Change
        if (selection.isDiscountPerExFactory()) {
            if (selection.isColValue()) {
                calculate_discount(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, key, obj, NumericConstants.FIFTEEN, null, selection, RATE_PER, false, listIndex);

            }
            if (selection.isColVariance()) {
                calculate_discount(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, key, obj, NumericConstants.FIFTEEN, null, selection, RATE_PER, false, listIndex);

            }
            if (selection.isColPercentage()) {
                calculate_discount(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, key, obj, NumericConstants.FIFTEEN, null, selection, RATE_PER, false, listIndex);
            }
        }
    }

    public void discount_Customize() {

        boolean isDetail = selection.getLevel().equals(DETAIL);
        commonCustomizationForTotalDiscount("D$", pivotDiscountList, selection, isDetail, NumericConstants.FIVE, Boolean.FALSE);
        commonCustomizationForTotalDiscount("D%", pivotDiscountList, selection, isDetail, NumericConstants.EIGHT, Boolean.TRUE);
        commonCustomizationForTotalDiscount("RPU-", pivotDiscountList, selection, isDetail, NumericConstants.ELEVEN, Boolean.FALSE);
        commonCustomizationForTotalDiscount("Dis%Ex", pivotDiscountList, selection, isDetail, NumericConstants.FOURTEEN, Boolean.TRUE);
    }

    public void getTotalPivotVariance(PVSelectionDTO selection) {
        String frequency = selection.getFrequency();
        List< Object[]> gtsResult = null;
        String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<>();
        pivotTotalList = new ArrayList<>();
        pivotPriorProjIdList = new ArrayList<>();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = QUARTERLY1;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMIANNUAL_CAPS;
        } else if (frequency.equals(Constant.MONTHLY)) {
            frequency = Constant.MONTHLY_COLUMN;
        } else {
            frequency = Constant.ANNUAL_CAPS;
        }
        projectionIdList.add(String.valueOf(selection.getCurrentProjId()));
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, VARIANCE1, selection.getSessionDTO().getSessionId(), selection.getUserId(), PIVOT1};
        gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

    public void getPivot_customization() {
        NMProjectionVarianceLogic logic = new NMProjectionVarianceLogic();

        List<ProjectionVarianceDTO> finalList = logic.getCustomizedPivotTotalResults(pivotTotalList, pivotPriorProjIdList, selection, selection, pivotDiscountList);
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
        String discountId = null;
        List<String> projectionIdList = new ArrayList<>();
        pivotDiscountList = new ArrayList<>();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = QUARTERLY1;
        } else if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMIANNUAL_CAPS;
        } else if (frequency.equals(Constant.MONTHLY)) {
            frequency = Constant.MONTHLY_COLUMN;
        } else {
            frequency = Constant.ANNUAL_CAPS;
        }
        projectionIdList.add(String.valueOf(projSelDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        List<Object[]> discountsList = null;
        if (selection.getDiscountLevel().equals(PROGRAM)) {
            Object[] orderedArg = {projectionId, frequency, discountId, VARIANCE1, projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId(), "1", PROGRAM};
            discountsList = CommonLogic.callProcedure(PRC_PROJECTION_RESULTS_DISCOUNT_PROCEDURE, orderedArg);
        } else {
            Object[] orderedArg = {projectionId, frequency, discountId, VARIANCE1, projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId(), "1", PROGRAM_CAT};
            discountsList = CommonLogic.callProcedure(PRC_PROJECTION_RESULTS_DISCOUNT_PROCEDURE, orderedArg);
        }
        pivotDiscountList.addAll(discountsList);

    }

    private void customize_discount_pivot() {
        int count = procRawList_detail_discount.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String newyear;
        String oldYear = StringUtils.EMPTY;
        String newPeriod;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        List<Integer> projList = selection.getProjIdList();
        ProjectionVarianceDTO discountDto = new ProjectionVarianceDTO();

        List<String> discountNames = new ArrayList<>(selection.getDeductionLevelCaptions());
        //PPA
        List list3 = CommonLogic.getPPADiscountNameList(selection);
        if (list3 != null) {
            List<String> ppaRebate = new ArrayList<>();
            for (String string : ppaRebate) {
                ppaRebate.add(string.toUpperCase());
            }

            discountNames.addAll(ppaRebate);
        }

        List<String> discountNames1 = new ArrayList<>(selection.getDiscountLevel().equals(PROGRAM) ? discountNames : discountNames);
        for (int i = 0; i < discountNames1.size(); i++) {
            String name = String.valueOf(discountNames1.get(i)).replaceAll(" ", StringUtils.EMPTY);
            discountNameMap.put(name, String.valueOf(i));
        }

        Map<String, ProjectionVarianceDTO> periodDiscountMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) procRawList_detail_discount.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[1]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));
            }
            String newHierarchyNo = String.valueOf(obj[1]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));
            newyear = String.valueOf(obj[NumericConstants.THREE]);
            newPeriod = String.valueOf(obj[NumericConstants.TWO]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
                    setBase_Value(discountDto, obj, discount);
                    customize_PriorList(discountDto, projList, discount, obj);

                } else if (i == 0) {
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn = getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);

                    setBase_Value(discountDto, obj, discount);
                    customize_PriorList(discountDto, projList, discount, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn = getVisibleColumn_Header(obj);
                    String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
                    setBase_Value(discountDto, obj, discount);
                    customize_PriorList(discountDto, projList, discount, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                }
            } else {

                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);

                periodDiscountMap = new HashMap<>();
                discountDto = new ProjectionVarianceDTO();
                commonColumn = getVisibleColumn_Header(obj);
                String discount = String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY);
                setBase_Value(discountDto, obj, discount);
                customize_PriorList(discountDto, projList, discount, obj);

                oldYear = newyear;
                oldPeriod = newPeriod;

                oldHierarchyNo = newHierarchyNo;
            }
            if (i == count - 1) {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);
            }

        }
    }

    private String discount_No(String discountName) {
        String number = String.valueOf(discountNameMap.get(discountName));
        if ("null".equals(number) || number.equals("NoNumber")) {
            return StringUtils.EMPTY;
        }
        return number;
    }

    private void setBase_Value(ProjectionVarianceDTO discountDto, Object[] obj, String discount) {

        calculatePivotDiscount(discountDto, obj, discount, "DiscountAmount", NumericConstants.SIX, selection.getCurrentProjId(), AMOUNT);
        calculatePivotDiscount(discountDto, obj, discount, "DiscountSales", NumericConstants.NINE, selection.getCurrentProjId(), RATE_PER);
        calculatePivotDiscount(discountDto, obj, discount, "RPU", NumericConstants.TWELVE, selection.getCurrentProjId(), AMOUNT);
        calculatePivotDiscount(discountDto, obj, discount, "DiscountPerExFactory", NumericConstants.FIFTEEN, selection.getCurrentProjId(), RATE_PER);

    }

    private void calculatePivotDiscount(ProjectionVarianceDTO discountDto, Object[] obj, String discount, String discountColumn, int currentIndex, int projId, DecimalFormat format) {
        String discountNo = discount_No(discount);
        boolean isPer = format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE);
        String visibleColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.ZERO]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + projId;
        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex])));
        String Value = getFormattedValue(format, currentValue);
        discountDto.addStringProperties(visibleColumn, isPer ? Value + PERCENT : Value);
        String actualColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.ZERO]).replaceAll(" ", StringUtils.EMPTY) + discountNo + ACTUAL + projId;
        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 1])));
        String Value1 = getFormattedValue(format, actualValue);
        discountDto.addStringProperties(actualColumn, isPer ? Value1 + PERCENT : Value1);
        String accrualColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.ZERO]).replaceAll(" ", StringUtils.EMPTY) + discountNo + ACCRUAL + projId;
        String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 2])));
        String accValue = getFormattedValue(format, accrualValue);
        discountDto.addStringProperties(accrualColumn, isPer ? accValue + PERCENT : accValue);

        if (actualBasis) {
            String discountVarCurrent;
            String variance = PVCommonLogic.getVariance(actualValue, currentValue, format, selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(actualValue, currentValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + CURRENT + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);
        } else if (accrualBasis) {
            String discountVarCurrent;
            String variance = PVCommonLogic.getVariance(accrualValue, currentValue, format, selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(accrualValue, currentValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + CURRENT + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);

        }
    }

    private void calculatePivotDiscountPrior(ProjectionVarianceDTO discountDto, Object[] obj, String discount, String discountColumn, int currentIndex, int priorIndex, int projId, DecimalFormat format) {
        String discountNo = discount_No(discount);
        boolean isPer = format.equals(RATE) || format.equals(RATE_PER);
        priorIndex = currentIndex + priorIndex;
        String visibleColumn = discountColumn + VAL + String.valueOf(obj[0]).replaceAll(" ", StringUtils.EMPTY) + discountNo + projId;
        String priorValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[priorIndex])));
        String val = getFormattedValue(format, priorValue);
        discountDto.addStringProperties(visibleColumn, isPer ? val + PERCENT : val);

        boolean isActualFormat = (obj[currentIndex - 1] == null);
        String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 2])));
        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 1])));
        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex])));

        if (actualBasis) {
            if (!isActualFormat) {
                String variance = PVCommonLogic.getVariance(actualValue, priorValue, isPer ? RATE : AMOUNT, selection);
                String discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
                discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

                String discountPer = PVCommonLogic.getPerChange(actualValue, priorValue, RATE_PER);
                String discountVarPer = discountColumn + PER + discount + discountNo + projId;
                discountDto.addStringProperties(discountVarPer, isPer ? discountPer + PERCENT : discountPer);
            }
        } else if (accrualBasis) {
            String variance = PVCommonLogic.getVariance(accrualValue, priorValue, isPer ? RATE : AMOUNT, selection);
            String discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT: variance);

            String discountPer = PVCommonLogic.getPerChange(accrualValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer, isPer ? discountPer + PERCENT : discountPer);
        } else {
            String variance = PVCommonLogic.getVariance(currentValue, priorValue, isPer ? RATE : AMOUNT, selection);
            String discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(currentValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer, isPer ? discountPer + PERCENT : discountPer);
        }
    }

    private void customize_PriorList(ProjectionVarianceDTO discountDto, List<Integer> projList, String discount, Object[] obj) {

        if (!projList.isEmpty()) {
            for (int j = 1; j <= projList.size(); j++) {

                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountAmount", NumericConstants.SIX, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), AMOUNT);
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountSales", NumericConstants.NINE, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), RATE);
                calculatePivotDiscountPrior(discountDto, obj, discount, "RPU", NumericConstants.TWELVE, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), AMOUNT);
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountPerExFactory", NumericConstants.FIFTEEN, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), RATE);

            }
        }
    }

    private String getVisibleColumn_Header(Object[] obj) {
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "Q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[NumericConstants.THREE];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "S" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[NumericConstants.THREE];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[NumericConstants.THREE];
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) - 1);
            commonColumn = monthName.toLowerCase() + obj[NumericConstants.THREE];
        }
        return commonColumn;
    }

    private Map<String, String> getGroup_customViewNM() {
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, String> customViewMap = new HashMap<>();
        Set keys = relationshipLevelDetailsMap.keySet();

        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
//            String value = (String) relationshipLevelDetailsMap.get(key).get(0).toString();
            String value = CommonUtil.getDisplayFormattedName(key, relationshipLevelDetailsMap.get(key).get(4).toString(), relationshipLevelDetailsMap, selection.getSessionDTO(), selection.getDisplayFormat());
            customViewMap.put(key, value);
        }
        return customViewMap;
    }

    private void calculateAndCustomize_periodForCustomView(List<Object[]> rawList, List<Object[]> rawList_discount) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key;
            if ("null".equals(String.valueOf(obj[obj.length - 1]))) {
                key = obj[baseColumn_hierarchy_index].toString();
            } else {
                key = obj[baseColumn_hierarchy_index].toString().endsWith(".") ? obj[baseColumn_hierarchy_index].toString() + "$" + obj[obj.length - 1].toString() : obj[baseColumn_hierarchy_index].toString() + ".$" + obj[obj.length - 1].toString();
            }

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
                String key;
                if ("null".equals(String.valueOf(obj[obj.length - 1]))) {
                    key = obj[baseColumn_hierarchy_index].toString();
                } else {
                    key = obj[baseColumn_hierarchy_index].toString().endsWith(".") ? obj[baseColumn_hierarchy_index].toString() + "$" + obj[obj.length - 1].toString() : obj[baseColumn_hierarchy_index].toString() + ".$" + obj[obj.length - 1].toString();
                }
                String discountName = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
                if (hierarchyKey.add(key)) {
                    listIndex = 0;
                    discountMap.clear();
                }
                Integer newListIndex = discountMap.get(discountName);
                if (newListIndex == null) {
                    discountMap.put(discountName, listIndex++);
                    addList_detail_discount(key, obj);
                } else {
                    updateList_detail_discount(key, obj, newListIndex);
                }
            }
        }
    }

    private void customhierarchyAndTP_keys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {
        String parentKey = obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString().substring(obj[obj.length - 1].toString().indexOf('-') + 1);
        String newKey;
        if (parentKey == null) {
            hierarchyKeys.add(key);
            resultMap.put(key, pvList);
        } else {
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
