/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionresults.logic;

import com.stpl.app.galforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import static com.stpl.app.galforecasting.projectionresults.logic.NMProjectionResultsXLLogic.LOGGER;
import com.stpl.app.galforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.HeaderUtils;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.VALUE;
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
/*
 *
 * @author karthikraja.k
 */

public class NMProjectionResultsXLLogic {

    public static final Logger LOGGER = Logger.getLogger(NMProjectionResultsXLLogic.class);
    private ProjectionResultsDTO exFacValue, exFacVar, exFacPer, demandValue, demandVar, demandPer, invWithValue, invWithVar,
            invWithPer, perExFacValue, perExFacVar, perExFacPer, perDemandValue, perDemandVar, perDemandPer, perInvWithValue, perInvWithVar, perInvWithPer, salesValue, salesVar,
            salesPer, unitsValue, unitsVar, unitsPer, disDollValue, disDollVar, disDollPer, disPerValue, disPerVar, disPerPer, rpuValue, rpuVar, rpuPer, netSalesValue, netSalesVar,
            netSalesPer, cogsValue, cogsVar, cogsPer, netProfitValue, netProfitVar, netProfitPer;
    private final Map<String, List<ProjectionResultsDTO>> resultMap;
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final ProjectionSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat UNIT_VOLUME = new DecimalFormat("#,##0.00");
//    private static final DecimalFormat PERC = new DecimalFormat("#0.00%");
    private static final DecimalFormat PER = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final String ZERO = "0";
    private static final String CURRENT = "Projections";
    private static final String ACTUALS = "Actuals";
    private static final int COLUMN_COUNT = 14;
    private static final int COLUMN_COUNT_DISCOUNT = 3;
    private static final int COLUMN_COUNT_TOTAL = 21;
    private static final int BASE_COLUMN_COUNT = 10;
    private final int index = 7;
    private final int index_detail_discount = 6;
    private int frequencyDivision;
    private final int baseColumn_levelName_index = 0;
    private final int baseColumn_levelValue_index = 2;
    private final int baseColumn_hierarchy_index = 1;
    private final int baseColumn_hierarchyIndicator_index = 3;
    private final int baseColumn_period_index = 5;
    private final int baseColumn_year_index = 4;
    private final int baseColumn_period_discount_index = 5;
    private final int baseColumn_year_discount_index = 4;
    private final int baseColumn_period_indexForTotal = 1;
    private final int baseColumn_year_indexForTotal = 0;
    private final int baseColumn_discount_index = 6;
    private final List<Object[]> procRawList_total = new ArrayList();
    private final List<Object[]> procRawList_detail = new ArrayList();
    private final List<Object[]> procRawList_detail_discount = new ArrayList();
    private final List<Integer> priorList = new ArrayList();
    private boolean isTotal = false;
    private String levelFilterValue = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String viewValue = StringUtils.EMPTY;
    private static final String PRC_PROJ_RESULTS = "PRC_PROJECTION_RESULTS";
    private static final String PRC_PR_EXCEL = "PRC_PROJECTION_RESULT_EXCEL_EXPORT";
    private static String DASH = "-";
    List<Object> pivotDiscountList = new ArrayList<Object>();
    List<ProjectionResultsDTO> discountList = new ArrayList<ProjectionResultsDTO>();
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
    private String VAR_CONTRACT_UNITS = "Unit Volume";
    private String VAR_DIS_AMOUNT = "Total Discount $";
    private String VAR_DIS_PERCENT = "Total Discount %";
    private String VAR_RPU = "Total RPU";
    private String PERCENT = "%";

    public NMProjectionResultsXLLogic(Map<String, List<ProjectionResultsDTO>> resultMap, ProjectionSelectionDTO selection,
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
        discountKeys.clear();
        isTotal = "Total".equalsIgnoreCase(selection.getLevel());
        discountFlag = !"Total Discount".equalsIgnoreCase(selection.getDiscountLevel());
        isCustomView = selection.isIsCustomHierarchy();
        if (isCustomView) {
            customView_relationship_hierarchy.putAll(getGroup_customView(parameterDto.getCustomViewMasterSid()));
        }
        frequencyDivision = selection.getFrequencyDivision();
        boolean isRefresh = isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());
        long start = System.currentTimeMillis();
        long end;

        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
            isTotal = true;
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
            isTotal = false;
            start = System.currentTimeMillis();
            executeProcedure_PRC_PV_SELECTION();
            if (discountFlag) {
                parameterDto.setViewName("DETAIL_DISCOUNT");
                executeProcedure_PRC_PV_SELECTION();
            }
            end = System.currentTimeMillis();
            calculateAndCustomize_detail_period(procRawList_detail, procRawList_detail_discount);
        } else {
            System.out.println("Varable VIEW");
            isTotal = true;

            System.out.println("Total level" + (isRefresh && procRawList_total.isEmpty()));
            //  String discLevel = selection.getDiscountLevel();
            end = System.currentTimeMillis();

            start = end;
//                    }
            calculateAndCustomize_total_pivot();
            end = System.currentTimeMillis();
            isTotal = false;
            start = System.currentTimeMillis();
            executeProcedure_PRC_PV_SELECTION();
            if (discountFlag) {
                parameterDto.setViewName("DETAIL_DISCOUNT");
                executeProcedure_PRC_PV_SELECTION();
            }
            end = System.currentTimeMillis();
            start = end;
            calculateAndCustomize_detail_pivot(procRawList_detail, procRawList_detail_discount);
            end = System.currentTimeMillis();
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
//            it.remove();
            String key = obj[baseColumn_hierarchy_index].toString();
            key = key.substring(key.indexOf('-') + 1);
//            System.out.println("Key :" + key);
            List<ProjectionResultsDTO> pvList = resultMap.get(key);
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
            System.out.println("discountSet-------" + discountMap);
        }

    }

    private void hierarchyAndTP_keys(Object[] obj, String key, List<ProjectionResultsDTO> pvList) {

        hierarchyKeys.add(key);
//        System.out.println("key while adding in MAp :" + key);
        resultMap.put(key, pvList);
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[baseColumn_levelValue_index]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[baseColumn_levelValue_index]))) {
            tradingPartnerKeys.add(key);
        }
    }

    private void addList(List<ProjectionResultsDTO> pvList, final Object[] obj, int index) {
        try {

            if (isTotal) {
                ProjectionResultsDTO total = new ProjectionResultsDTO();
                total.setGroup("Projection Total");
                pvList.add(total);
            } else {
                ProjectionResultsDTO detail = new ProjectionResultsDTO();
                //Group Column projSelDTO

                String groupName;
                if (isCustomView) {
                    groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                    groupName = groupName == null ? "" : groupName;
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
                }
                detail.setGroup(groupName);
                pvList.add(detail);
            }
            //Contract Sales @ WAC
            if (selection.getSalesOrUnit().equals("Sales") || selection.getSalesOrUnit().equals("Both")) {
                salesValue = new ProjectionResultsDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, 5, 6, salesValue, selection, AMOUNT);
                pvList.add(salesValue);
            }
            //Contract Units
            if (selection.getSalesOrUnit().equals("Units") || selection.getSalesOrUnit().equals("Both")) {
                unitsValue = new ProjectionResultsDTO();
                calculate(VAR_CONTRACT_UNITS, Constant.VALUE, obj, 7, 8, unitsValue, selection, AMOUNT);
                pvList.add(unitsValue);
            }
            //Discount $
            disDollValue = new ProjectionResultsDTO();
            calculate(VAR_DIS_AMOUNT, Constant.VALUE, obj, 12, 13, disDollValue, selection, AMOUNT);
            pvList.add(disDollValue);
            //Discount %
            disPerValue = new ProjectionResultsDTO();
            calculate(VAR_DIS_PERCENT, Constant.VALUE, obj, 14, 15, disPerValue, selection, RATE);
            pvList.add(disPerValue);
            //RPU
            rpuValue = new ProjectionResultsDTO();
            calculate(VAR_RPU, Constant.VALUE, obj, 36, 37, rpuValue, selection, AMOUNT);
            pvList.add(rpuValue);
            //Net Sales 
            netSalesValue = new ProjectionResultsDTO();
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, 23, 24, netSalesValue, selection, AMOUNT);
            pvList.add(netSalesValue);
            //COGS
            cogsValue = new ProjectionResultsDTO();
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, 38, 39, cogsValue, selection, AMOUNT);
            pvList.add(cogsValue);
            //Net Profit
            netProfitValue = new ProjectionResultsDTO();
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, 40, 41, netProfitValue, selection, AMOUNT);
            pvList.add(netProfitValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateList(List<ProjectionResultsDTO> pvList, Object[] obj, int index) {

        int listIndex = 1;
        //Contract Sales @ WAC
        if (selection.getSalesOrUnit().equals("Sales") || selection.getSalesOrUnit().equals("Both")) {
            salesValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, 5, 6, salesValue, selection, AMOUNT);
        }
        //Contract Units\
        if (selection.getSalesOrUnit().equals("Units") || selection.getSalesOrUnit().equals("Both")) {
            unitsValue = pvList.get(listIndex++);
            calculate(VAR_CONTRACT_UNITS, Constant.VALUE, obj, 7, 8, unitsValue, selection, UNIT_VOLUME);
        }
        //Discount $
        disDollValue = pvList.get(listIndex++);
        calculate(VAR_DIS_AMOUNT, Constant.VALUE, obj, 12, 13, disDollValue, selection, AMOUNT);
        //Discount %
        disPerValue = pvList.get(listIndex++);
        calculate(VAR_DIS_PERCENT, Constant.VALUE, obj, 14, 15, disPerValue, selection, RATE);
        //RPU
        rpuValue = pvList.get(listIndex++);
        calculate(VAR_RPU, Constant.VALUE, obj, 36, 37, rpuValue, selection, AMOUNT);
        //Net Sales 
        netSalesValue = pvList.get(listIndex++);
        calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, 23, 24, netSalesValue, selection, AMOUNT);
        //COGS
        cogsValue = pvList.get(listIndex++);
        calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, 38, 39, cogsValue, selection, AMOUNT);
        //Net Profit
        netProfitValue = pvList.get(listIndex++);
        calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, 40, 41, netProfitValue, selection, AMOUNT);
    }

    private void calculate(String varaibleName, String varibaleCat, Object[] obj, int actIndex, int projindex, ProjectionResultsDTO pvDTO,
            ProjectionSelectionDTO selection, DecimalFormat format) {
//        System.out.println("Inside Calculate varaibleName:" + varaibleName + "varibaleCat" + varibaleCat);
        pvDTO.setGroup(varaibleName);
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == 4) {
            commonColumn = "q" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 2) {
            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[isTotal ? 1 : baseColumn_period_index])) - 1);
            commonColumn = monthName.toLowerCase() + obj[isTotal ? 0 : baseColumn_year_index];
        }

        if (varibaleCat.equals(Constant.VALUE)) {
            String projvalue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[projindex + 1])));
            String baseValue = getFormattedValue(format, projvalue);
            pvDTO.addStringProperties(commonColumn + CURRENT, varaibleName.contains(PERCENT) ? baseValue + PERCENT : baseValue);
            projvalue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actIndex + 1])));
            baseValue = getFormattedValue(format, projvalue);
            pvDTO.addStringProperties(commonColumn + ACTUALS, varaibleName.contains(PERCENT) ? baseValue + PERCENT : baseValue);
            if (varaibleName.contains("Percent")) {

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
        projectionIdList.add(selection.getProjectionId());
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, "ASSUMPTIONS", selection.getSessionId(), selection.getUserId()};
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
            projectionIdList.add(selection.getProjectionId());
            String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
            String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
            Object[] orderedArg = {projectionId, parameterDto.getUserId(), parameterDto.getSessionId(), parameterDto.getFrequency(), parameterDto.getViewIndicator(),
                parameterDto.getGroupFilter(), parameterDto.getGroupFilterValue(), parameterDto.getHierarchyNo(),
                parameterDto.getLevelNo(), 0, parameterDto.getViewName(), parameterDto.getCustomViewMasterSid(),
                parameterDto.getDiscountLevel()};
            List< Object[]> rawList = CommonLogic.callProcedure(PRC_PR_EXCEL, orderedArg);
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

    private List<ProjectionResultsDTO> calculateAndCustomize_total_period(List<Object[]> rawList) {

        //To check condition total or details values
        List<ProjectionResultsDTO> pvList = new ArrayList();
        ProjectionResultsDTO total = new ProjectionResultsDTO();
        total.setGroup("Projection Total");
        pvList.add(total);

        hierarchyKeys.add(Constants.LabelConstants.TOTAL.toString());
        NMProjectionResultsLogic logic = new NMProjectionResultsLogic();
        pvList.addAll(logic.getCustomizedProjectionTotal(rawList, selection, true));
        resultMap.put(Constants.LabelConstants.TOTAL.toString(), pvList);
        return pvList;
    }

    private void calculateAndCustomize_total_pivot() {
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
        projectionIdList.add(String.valueOf(selection.getProjectionId()));
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        System.out.println("==projection id string==========" + projectionId);
        Object[] orderedArgs = {selection.getProjectionId(), frequency, discountId, "ASSUMPTIONS", selection.getSessionId(), selection.getUserId()};
        hierarchyKeys.add(Constants.LabelConstants.TOTAL.toString());
        List<ProjectionResultsDTO> pvList = new ArrayList();
        ProjectionResultsDTO total = new ProjectionResultsDTO();
        total.setGroup("Projection Total");
        pvList.add(total);
        NMProjectionResultsLogic logic = new NMProjectionResultsLogic();
        pvList.addAll(logic.getProjectionPivotTotal(orderedArgs, selection));
        resultMap.put(Constants.LabelConstants.TOTAL.toString(), pvList);
    }

    private void addList_pivot(List<ProjectionResultsDTO> pvList, Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal) {
//        final int indexForTotal = 2;

        int frequencyDivision = selection.getFrequencyDivision();
        if (isTotal) {
            ProjectionResultsDTO total = new ProjectionResultsDTO();
            total.setGroup("Projection Total");
            pvList.add(total);
        } else {
            ProjectionResultsDTO detail = new ProjectionResultsDTO();
//            System.out.println("" + selection.getProjectionStartPeriod());
            //Group Column projSelDTO
            String groupName;
            if (isCustomView) {
                groupName = customView_relationship_hierarchy.get(obj[baseColumn_hierarchy_index] == null ? "" : obj[baseColumn_hierarchy_index].toString());
                groupName = groupName == null ? "" : groupName;
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
            }

//            System.out.println("groupName :" + groupName);
//                    if (groupName != null && !"null".equalsIgnoreCase(groupName)) {
            detail.setGroup(groupName);
            pvList.add(detail);
        }

        pvList.add(frequencyBasedDTO);

        //Ex-Factory-Sales
        calculateForTotal("conSalesWac", Constant.VALUE, obj, 6, 7, frequencyBasedDTO, selection, AMOUNT);
        //Contract Units
        calculateForTotal("unitVol", Constant.VALUE, obj, 8, 9, frequencyBasedDTO, selection, AMOUNT);
        //Discount $
        calculateForTotal("totDisDol", Constant.VALUE, obj, 13, 14, frequencyBasedDTO, selection, AMOUNT);
        //Discount %
        calculateForTotal("totDisPer", Constant.VALUE, obj, 15, 16, frequencyBasedDTO, selection, PER);
        //RPU
        calculateForTotal("totalRPU", Constant.VALUE, obj, 37, 38, frequencyBasedDTO, selection, AMOUNT);
        //Net Sales 
        calculateForTotal("netSales", Constant.VALUE, obj, 24, 25, frequencyBasedDTO, selection, AMOUNT);
        //COGS
        calculateForTotal("cogs", Constant.VALUE, obj, 39, 40, frequencyBasedDTO, selection, AMOUNT);
        //Net Profit
        calculateForTotal("netProfit", Constant.VALUE, obj, 41, 42, frequencyBasedDTO, selection, AMOUNT);

    }

    private void calculateForTotal(String variableName, String varibaleCat, Object[] obj, int actIndex, int index, ProjectionResultsDTO pvDTO, ProjectionSelectionDTO selection, DecimalFormat format) {
        if (varibaleCat.equals(Constant.VALUE)) {
            String val = getFormattedValue(format, String.valueOf(obj[index]));

            pvDTO.addStringProperties(variableName + CURRENT, val);
            val = getFormattedValue(format, String.valueOf(obj[actIndex]));
            pvDTO.addStringProperties(variableName + ACTUALS, val);
        }

    }

    private void calculateAndCustomize_detail_pivot(List<Object[]> rawList, List<Object[]> rawList_discount) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
//            it.remove();
            String key = obj[baseColumn_hierarchy_index].toString();
            key = key.substring(key.indexOf('-') + 1);
            List<ProjectionResultsDTO> pvList = resultMap.get(key);
            int year = Integer.valueOf(obj[baseColumn_year_index].toString());
            int period = Integer.valueOf(obj[frequencyDivision == 1 ? 0 : baseColumn_period_index].toString());
            if (year < selection.getHistoryStartYear() || ((year == selection.getHistoryStartYear()) && (period < selection.getHistoryStartPeriod()))) {
                continue;
            }
            List<String> common = HeaderUtils.getCommonColumnHeader(frequencyDivision, year, period);
            String groupId = common.get(1);
            ProjectionResultsDTO freVarianceDTO = new ProjectionResultsDTO();
            freVarianceDTO.setGroup(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addList_pivot(pvList, obj, freVarianceDTO, index);
                hierarchyAndTP_keys(obj, key, pvList);
            } else {
                updateList_pivot(pvList, obj, freVarianceDTO, index);
            }
        }
        if (discountFlag) {
            Set<String> discountSet = new HashSet();
            Set<String> hierarchySet = new HashSet();
            int listIndex = 0;
            for (ListIterator<Object[]> it = rawList_discount.listIterator(); it.hasNext();) {
                Object[] obj = it.next();
                int year = Integer.valueOf(obj[baseColumn_year_index].toString());
                int period = Integer.valueOf(obj[frequencyDivision == 1 ? 0 : baseColumn_period_index].toString());
                if (year < selection.getHistoryStartYear() || ((year == selection.getHistoryStartYear()) && (period < selection.getHistoryStartPeriod()))) {
                    continue;
                }
                String key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
                String discountName = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
                if (discountSet.add(discountName)) {
                    hierarchySet.clear();
                    listIndex = 1;
                } else if (hierarchySet.add(key)) {
                    listIndex = 1;
                }
//                System.out.println("key========> "+key);
                List<ProjectionResultsDTO> pvList = resultMap.get(key);
                updateList_detail_discount_pivot(pvList, key, obj, listIndex++, discountSet);
            }
        }
    }

    private void updateList_pivot(List<ProjectionResultsDTO> pvList, Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal) {

        pvList.add(frequencyBasedDTO);

        //Contract Sales @ WAC
        calculateForTotal("conSalesWac", Constant.VALUE, obj, 6, 7, frequencyBasedDTO, selection, AMOUNT);
        //Contract Units
        calculateForTotal("unitVol", Constant.VALUE, obj, 8, 9, frequencyBasedDTO, selection, AMOUNT);
        //Discount $
        calculateForTotal("totDisDol", Constant.VALUE, obj, 13, 14, frequencyBasedDTO, selection, AMOUNT);
        //Discount %
        calculateForTotal("totDisPer", Constant.VALUE, obj, 15, 16, frequencyBasedDTO, selection, PER);
        //RPU
        calculateForTotal("totalRPU", Constant.VALUE, obj, 37, 38, frequencyBasedDTO, selection, AMOUNT);
        //Net Sales 
        calculateForTotal("netSales", Constant.VALUE, obj, 24, 25, frequencyBasedDTO, selection, AMOUNT);
        //COGS
        calculateForTotal("cogs", Constant.VALUE, obj, 39, 40, frequencyBasedDTO, selection, AMOUNT);
        //Net Profit
        calculateForTotal("netProfit", Constant.VALUE, obj, 41, 42, frequencyBasedDTO, selection, AMOUNT);
    }

    public void getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, ProjectionSelectionDTO projSelDTO, int index, int actIndex, boolean isPer, String parentGroup, String indicator) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionResultsDTO> resultDto = new ArrayList<ProjectionResultsDTO>();
        // String indicator=projSelDTO.getVarIndicator();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionResultsDTO pvDTO = new ProjectionResultsDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        int discountInd = 3;
//        System.out.println("===data list size=======================>>>>>" + dataList.size());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!"".equals(lastValue) && !"null".equals(lastValue) && obj[3] != null && !lastValue.equals(String.valueOf(obj[3]))) {
                    //   pvDTO.setGroup(parentGroup+"-"+indicator+lastValue);
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);

                    pvDTO = new ProjectionResultsDTO();
                }
                lastValue = String.valueOf(obj[3]);

//                System.out.println("==group=============  ======>>>>" + lastValue);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = "q" + obj[2] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 2) {
                    commonColumn = "s" + obj[2] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[2];
//                    index--;
//                    actIndex--;
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[2])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[1];
                }
                String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                String actvalue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actIndex])));

                pvDTO.addStringProperties(commonColumn + CURRENT, isPer ? getFormattedValue(RATE, value) + PERCENT : getFormattedValue(AMOUNT, value));
                pvDTO.addStringProperties(commonColumn + "Actuals", isPer ? getFormattedValue(RATE, actvalue) + PERCENT : getFormattedValue(AMOUNT, actvalue));
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
            resultMap.put(parentGroup, resultDto);
        } else {
            for (String discountNameList : projSelDTO.getDiscountNameList()) {
                pvDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove("group");
                for (String Obj : columnList) {
                    pvDTO.addStringProperties(Obj, DASH);
                }
                resultDto.add(pvDTO);
            }
        }
//        System.out.println("==parent group============" + parentGroup + "==========" + resultDto.size());
//
//        System.out.println("==return discount size===============>>>>>" + discountList.size());
        LOGGER.info("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + discountList.size());

    }

    public ProjectionResultsDTO getCustomizedPPA(List<Object> dataList, ProjectionSelectionDTO projSelDTO, boolean isDetail, int index, int actIndex, boolean isPer, String parentGroup, String ppaGroup) {
        LOGGER.info("Inside getCustomizedPPA");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionResultsDTO pvDTO = new ProjectionResultsDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        pvDTO.setGroup(ppaGroup);
        if (dataList != null && !dataList.isEmpty()) {

            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[5];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "s" + obj[4] + StringUtils.EMPTY + obj[5];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[5];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[5];
                    }
                    column1 = commonColumn + CURRENT;
                    String value = "" + obj[5];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
//                    for (int j = 0; j < priorList.size(); j++) {
//                        column1 = commonColumn + priorList.get(j);
//                        String pValue = "" + obj[5 + ((j + 1) * 3)];
//                        if (projSelDTO.getSales().contains("SALES")) {
//                            pValue = getFormattedValue(AMOUNT, pValue);
//                        } else if (projSelDTO.getSales().contains("RATE")) {
//                            pValue = getFormattedValue(RATE_PER_THREE, pValue);
//                        }
//                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
//                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[5];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "s" + obj[4] + StringUtils.EMPTY + obj[5];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[5];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[5];
                    }
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT, isPer ? baseValue + PERCENT : baseValue);
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actIndex])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + "Actuals", isPer ? baseValue + PERCENT : baseValue);
//                    for (int j = 0; j < priorList.size(); j++) {
//                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
//                        String val = getFormattedValue(AMOUNT, priorVal);
//                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
//                    }
//                }
                }
            }
        }
//        System.out.println("==ppa parent group===========" + parentGroup);
        List list = resultMap.get(parentGroup);
//        System.out.println("==list siZe in ppa===================>>" + list.size());
        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionResultsDTO> dto = new ArrayList<ProjectionResultsDTO>();
            dto.add(pvDTO);
            resultMap.put(parentGroup, dto);
        }

        LOGGER.info("Ending getCustomizedPPA");
        return pvDTO;
    }

    public void getCustomizedReturns(List<Object> dataList, ProjectionSelectionDTO projSelDTO, boolean isDetail, int index, int actRetIndex, boolean isPer, String parentGroup, String returnsGroup) {
        LOGGER.info("Inside getCustomizedReturns");
        try {
            int frequencyDivision = projSelDTO.getFrequencyDivision();
            ProjectionResultsDTO pvDTO = new ProjectionResultsDTO();
            pvDTO.setLevelValue(projSelDTO.getLevelValue());
            pvDTO.setLevelNo(projSelDTO.getLevelNo());
            pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            pvDTO.setParent(0);
            pvDTO.setGroup(returnsGroup);
            int annualIndex = 0;
            int annualActRetIndex = 0;
            int discountIndex = 5;
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (isDetail) {
                        final Object[] obj = (Object[]) dataList.get(i);
                        String commonColumn = StringUtils.EMPTY;
                        String column1 = StringUtils.EMPTY;
                        if (frequencyDivision == 4) {
                            commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == 2) {
                            commonColumn = "s" + obj[4] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[discountIndex - 1];
                            annualIndex = index - 1;
                            annualActRetIndex = actRetIndex - 1;
                            //                        actRetIndex--;
                        } else if (frequencyDivision == 12) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[discountIndex];
                        }
                        column1 = commonColumn + CURRENT;
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
                            commonColumn = "q" + obj[4] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == 2) {
                            commonColumn = "s" + obj[4] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[discountIndex - 1];
                        } else if (frequencyDivision == 12) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[4])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[discountIndex];
                        }
                        if (frequencyDivision != 1) {
                            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                            String baseValue = getFormattedValue(AMOUNT, value);
                            pvDTO.addStringProperties(commonColumn + CURRENT, isPer ? baseValue + PERCENT : baseValue);
                            value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actRetIndex])));
                            baseValue = getFormattedValue(AMOUNT, value);
                            pvDTO.addStringProperties(commonColumn + ACTUALS, isPer ? baseValue + PERCENT : baseValue);
                        } else {
                            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[annualIndex])));
                            String baseValue = getFormattedValue(AMOUNT, value);
                            pvDTO.addStringProperties(commonColumn + CURRENT, isPer ? baseValue + PERCENT : baseValue);
                            value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[annualActRetIndex])));
                            baseValue = getFormattedValue(AMOUNT, value);
                            pvDTO.addStringProperties(commonColumn + ACTUALS, isPer ? baseValue + PERCENT : baseValue);
                        }
                    }
                }
            }
            LOGGER.info("Ending getCustomizedReturns");
//        System.out.println("==ppa parent group===========" + parentGroup);
            List list = resultMap.get(parentGroup);
//        System.out.println("==list siZe in ppa===================>>" + list.size());
            if (list != null) {
                list.add(pvDTO);
            } else {
                List<ProjectionResultsDTO> returnsDTO = new ArrayList<ProjectionResultsDTO>();
                returnsDTO.add(pvDTO);
                resultMap.put(parentGroup, returnsDTO);
            }
            //return pvDTO;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void commonCustomizationForTotalDiscount(String group, String discountType, List<Object> dataList, ProjectionSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String ppaGroup, String returnsGroup, List<Object> pivotList) {
        LOGGER.info("Inside commonCustomizationForTotalDiscount");
        //   List<ProjectionResultsDTO> dto = new ArrayList<ProjectionResultsDTO>();

        int indexValue = 0;
        int actIndex = 0;
        int ppaIndex = 0;
        int rpuIndex = 0;
        int actPpaIndex = 0;
        int actRpuIndex = 0;
        if (group.equals("D$")) {
            actIndex = 4;
            indexValue = 5;
            ppaIndex = 17;
            rpuIndex = 42;
            actPpaIndex = 16;
            actRpuIndex = 40;
        } else if (group.equals("D%")) {
            actIndex = 6;
            indexValue = 7;
            ppaIndex = 19;
            rpuIndex = 43;
            actPpaIndex = 18;
            actRpuIndex = 41;
        } else {
            actIndex = 8;
            indexValue = 9;
            ppaIndex = 33;
            rpuIndex = 45;
            actPpaIndex = 32;
            actRpuIndex = 44;
        }
        String parentGroup = StringUtils.EMPTY;
        List list = new ArrayList();
        if (!isDetail) {
            parentGroup = group + "value";
            if (!selection.getDiscountNameList().isEmpty()) {

                getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, actIndex, isPer, parentGroup, VALUE.getConstant());
            }
//            getCustomizedPPA(pivotList, projSelDTO, isDetail, ppaIndex, actPpaIndex, isPer, parentGroup, ppaGroup);
//            getCustomizedReturns(pivotList, projSelDTO, isDetail, rpuIndex, actRpuIndex, isPer, parentGroup, returnsGroup);

        }
        LOGGER.info("Ending commonCustomizationForTotalDiscount");
        // return dto;
    }

    private void calculate_discount(String varaibleName, String varibaleCat, String masterKey, Object[] obj, int actIndex, ProjectionResultsDTO pvDTO,
            ProjectionSelectionDTO selection, DecimalFormat format, boolean isAdd, int listIndex) {
//        System.out.println("Inside Calculate varaibleName:" + varaibleName + "varibaleCat" + varibaleCat);
        actIndex++;

        String key = masterKey + varaibleName;
        List<ProjectionResultsDTO> pvList_discount;
//        System.out.println("key---" + key);
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
            commonColumn = "q" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 2) {
            commonColumn = "s" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 12) {
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[baseColumn_period_discount_index])) - 1);
            commonColumn = monthName.toLowerCase() + obj[baseColumn_year_discount_index];
        }

        if (varibaleCat.equals(Constant.VALUE)) {
            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actIndex + 1])));
            String baseValue = getFormattedValue(format, value);
            pvDTO.addStringProperties(commonColumn + CURRENT, varaibleName.contains(PERCENT) ? baseValue + PERCENT : baseValue);
            value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actIndex])));
            baseValue = getFormattedValue(format, value);
            pvDTO.addStringProperties(commonColumn + ACTUALS, varaibleName.contains(PERCENT) ? baseValue + PERCENT : baseValue);

        }
//        for (int j = 0; j < priorList.size(); j++) {
//            if (varibaleCat.equals(Constant.VALUE)) {
//                String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))])));
//                String val = getFormattedValue(format, priorVal);
//                pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
//
//            } else if (varibaleCat.equals(Constant.VARIANCE)) {
//                String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))];
//                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
//                String val = getFormattedValue(format, variance);
//                pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
//
//            } else {
//                String priorVal = obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))] != null ? StringUtils.EMPTY + obj[index + ((j + 1) * (COLUMN_COUNT_DISCOUNT))] : ZERO;
//                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
//                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
//                    perChange = 0.0;
//                }
//                String change = String.valueOf(perChange);
//                String baseValu = getFormattedValue(format, change);
//                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
//
//            }
//        }
    }

    private void addList_detail_discount(String key, final Object[] obj) {
        //Discount $
        disDollValue = new ProjectionResultsDTO();
        calculate_discount(VAR_DIS_AMOUNT, Constant.VALUE, key, obj, index_detail_discount, disDollValue, selection, AMOUNT, true, 0);
        //Discount %
        disPerValue = new ProjectionResultsDTO();
        calculate_discount(VAR_DIS_PERCENT, Constant.VALUE, key, obj, index_detail_discount + 2, disPerValue, selection, RATE, true, 0);
        //RPU
        rpuValue = new ProjectionResultsDTO();
        calculate_discount(VAR_RPU, Constant.VALUE, key, obj, index_detail_discount + 4, rpuValue, selection, AMOUNT, true, 0);
    }

    private void updateList_detail_discount(String key, final Object[] obj, int listIndex) {
//        int listIndex = 0;
        //Discount $
//            disDollValue = pvList.get(listIndex++);
        calculate_discount(VAR_DIS_AMOUNT, Constant.VALUE, key, obj, index_detail_discount, null, selection, AMOUNT, false, listIndex);
        //Discount %
        calculate_discount(VAR_DIS_PERCENT, Constant.VALUE, key, obj, index_detail_discount + 2, null, selection, RATE, false, listIndex);

        //RPU
        calculate_discount(VAR_RPU, Constant.VALUE, key, obj, index_detail_discount + 4, null, selection, AMOUNT, false, listIndex);

    }

    private void updateList_detail_discount_pivot(List<ProjectionResultsDTO> pvList, String key, final Object[] obj, int listIndex, Set<String> discountSet) {
//        System.out.println("listIndex = " + listIndex);
        ProjectionResultsDTO frequencyBasedDTO = pvList.get(listIndex < pvList.size() ? listIndex : 0);
        //Discount $
        calculate_discount_pivot("totDisDol", Constant.VALUE, discountSet, obj, frequencyBasedDTO, index_detail_discount, selection, AMOUNT);
        //Discount %
        calculate_discount_pivot("totDisPer", Constant.VALUE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 1, selection, RATE);
        //RPU
        calculate_discount_pivot("totalRPU", Constant.VALUE, discountSet, obj, frequencyBasedDTO, index_detail_discount + 2, selection, AMOUNT);
    }

    private void calculate_discount_pivot(String varaibleName, String varibaleCat, Set<String> discountSet, Object[] obj, ProjectionResultsDTO pvDTO, int index,
            ProjectionSelectionDTO selection, DecimalFormat format) {

        String discoun_ppa_returns_Name = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
//        if ("PPA".equals(discoun_ppa_returns_Name) || "RETURNS".equalsIgnoreCase(discoun_ppa_returns_Name)) {
//            discoun_ppa_returns_Name = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString();
//        } else {
//            discoun_ppa_returns_Name = obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index] == null ? "" : obj[baseColumn_discount_index].toString().replace(" ", StringUtils.EMPTY) + (discountSet.size() - 1);
//        }
//        if ("RETURNS".equals(discoun_ppa_returns_Name)) {
//            discoun_ppa_returns_Name = "Returns";
//        } else if ("PPA".equals(discoun_ppa_returns_Name)) {
//            discoun_ppa_returns_Name = "PPADiscount";
//        }
        if (varaibleName.equals("totDisDol")) {
            System.out.println(obj[4] + "-" + (varaibleName + discoun_ppa_returns_Name + CURRENT) + "-" + obj[index + 2] + "-" + pvDTO.getGroup() + "-" + pvDTO.getPropertyValue("conSalesWacProjections"));
        }

        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 2])));
        String baseValue = getFormattedValue(format, value);
        pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + CURRENT, baseValue);
        value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1])));
        baseValue = getFormattedValue(format, value);
        pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + ACTUALS, baseValue);
//            System.out.println("Value Property ==>:" + varaibleName + discoun_ppa_returns_Name + CURRENT + selection.getCurrentProjId());

//        }
    }

    public void discount_Customize() {
        System.out.println("===insdie customize===================");
        //  List<ProjectionResultsDTO> discountList=new ArrayList<ProjectionResultsDTO>();
        boolean isDetail = selection.getLevel().equals(DETAIL);
        commonCustomizationForTotalDiscount("D$", "Discount", pivotDiscountList, selection, isDetail, 3, Boolean.FALSE, "PPA Discount $", "Return Discount $", pivotTotalList);
        commonCustomizationForTotalDiscount("D%", "Discount", pivotDiscountList, selection, isDetail, 4, Boolean.TRUE, "PPA Discount %", "Return Discount %", pivotTotalList);
        commonCustomizationForTotalDiscount("RPU-", "Discount", pivotDiscountList, selection, isDetail, 5, Boolean.FALSE, "PPA RPU", "Returns RPU", pivotTotalList);
    }

    public List<Object[]> getTotalPivotVariance(ProjectionSelectionDTO selection) {
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
        projectionIdList.add(String.valueOf(selection.getProjectionId()));
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        System.out.println("==projection id string==========" + projectionId);
        Object[] orderedArg = {projectionId, frequency, discountId, "ASSUMPTIONS", selection.getSessionId(), selection.getUserId(), "PIVOT"};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        System.out.println("==pivot results size===========" + gtsResult.size());
        pivotTotalList.addAll(gtsResult);
        return gtsResult;
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
        projectionIdList.add(String.valueOf(projSelDTO.getProjectionId()));
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, discountId, "ASSUMPTIONS", projSelDTO.getSessionId(), projSelDTO.getUserId(), "1"};
        List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArg);
        System.out.println("===discount list size=======>>>>" + discountsList.size());
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
}
