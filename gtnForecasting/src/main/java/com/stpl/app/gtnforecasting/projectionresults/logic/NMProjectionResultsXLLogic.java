
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionresults.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import static com.stpl.app.gtnforecasting.projectionresults.logic.NMProjectionResultsXLLogic.LOGGER;
import com.stpl.app.gtnforecasting.projectionvariance.dto.PVParameters;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.LabelConstants.VALUE;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private ProjectionResultsDTO 
            salesValue,
            unitsValue, disDollValue, disPerValue, rpuValue, netSalesValue,
            cogsValue, netProfitValue, disPerExfac, netSalesExFac;
    private final Map<String, List<ProjectionResultsDTO>> resultMap;
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final ProjectionSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat UNIT_VOLUME = new DecimalFormat("#,##0.00");
    private static final DecimalFormat PER = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    private static final String ZERO = "0";
    private static final String CURRENT = "Projections";
    private static final String ACTUALS = "Actuals";
    private final int index = 7;
    private final int index_detail_discount = 6;
    private int frequencyDivision;
    private final int baseColumn_levelValue_index = 2;
    private final int baseColumn_hierarchy_index = 1;
    private final int baseColumn_hierarchyIndicator_index = 3;
    private final int baseColumn_period_index = 5;
    private final int baseColumn_year_index = 4;
    private final int baseColumn_period_discount_index = 5;
    private final int baseColumn_year_discount_index = 4;
    private final int baseColumn_discount_index = 13;
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
            customView_relationship_hierarchy.putAll(getGroup_customViewNM());
        }
        frequencyDivision = selection.getFrequencyDivision();
        boolean isRefresh = isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());

        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
            isTotal = true;
            getTotalRawData();
            getTotalPivotVariance(selection);
            if (discountFlag) {
                getTotalDiscount(selection);
            }

            calculateAndCustomize_total_period(procRawList_total);
            if (discountFlag) {
                discount_Customize();
            }

            isTotal = false;
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
        } else {
            isTotal = true;


            calculateAndCustomize_total_pivot();
            isTotal = false;
            executeProcedure_PRC_PV_SELECTION();
            if (discountFlag) {
                parameterDto.setViewName("DETAIL_DISCOUNT");
                parameterDto.setHierarchyNo("1");
                executeProcedure_PRC_PV_SELECTION();
            }
                calculateAndCustomize_detail_pivot(procRawList_detail, procRawList_detail_discount);
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
                String key = obj[baseColumn_hierarchy_index].toString();
                key = key.substring(key.indexOf('-') + 1);
                String discountName = obj[index_detail_discount] == null ? "" : obj[index_detail_discount].toString();
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

    private void hierarchyAndTP_keys(Object[] obj, String key, List<ProjectionResultsDTO> pvList) {

        hierarchyKeys.add(key);
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
                    detail.setHierarchyNo(obj[1].toString());
                    detail.setParentHierarchyNo(obj[NumericConstants.FIFTY_THREE] == null ? null : obj[NumericConstants.FIFTY_THREE].toString());
                } else {
                    groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
                }
                detail.setGroup(groupName);
                pvList.add(detail);
            }
            //Contract Sales @ WAC
            if (selection.getSalesOrUnit().equals("Sales") || selection.getSalesOrUnit().equals("Both")) {
                salesValue = new ProjectionResultsDTO();
                calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, NumericConstants.FIVE, NumericConstants.SIX, salesValue, selection, AMOUNT);
                pvList.add(salesValue);
            }
            //Contract Units
            if (selection.getSalesOrUnit().equals("Units") || selection.getSalesOrUnit().equals("Both")) {
                unitsValue = new ProjectionResultsDTO();
                calculate(VAR_CONTRACT_UNITS, Constant.VALUE, obj, NumericConstants.SEVEN, NumericConstants.EIGHT, unitsValue, selection, AMOUNT);
                pvList.add(unitsValue);
            }
            //Discount $
            disDollValue = new ProjectionResultsDTO();
            calculate(VAR_DIS_AMOUNT, Constant.VALUE, obj, NumericConstants.TWELVE, NumericConstants.THIRTEEN, disDollValue, selection, AMOUNT);
            pvList.add(disDollValue);
            //Discount %
            disPerValue = new ProjectionResultsDTO();
            calculate(VAR_DIS_PERCENT, Constant.VALUE, obj, NumericConstants.FOURTEEN, NumericConstants.FIFTEEN, disPerValue, selection, RATE);
            pvList.add(disPerValue);
            //RPU
            rpuValue = new ProjectionResultsDTO();
            calculate(VAR_RPU, Constant.VALUE, obj, NumericConstants.THIRTY_SIX, NumericConstants.THIRTY_SEVEN, rpuValue, selection, AMOUNT);
            pvList.add(rpuValue);
            //Discount % of Ex-Factory
            disPerExfac = new ProjectionResultsDTO();
            calculate(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER, Constant.VALUE, obj, NumericConstants.FIFTY, NumericConstants.FIFTY_ONE, disPerExfac, selection, RATE);
            pvList.add(disPerExfac);
            
            //Net Sales 
            netSalesValue = new ProjectionResultsDTO();
            calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, NumericConstants.TWENTY_THREE, NumericConstants.TWENTY_FOUR, netSalesValue, selection, AMOUNT);
            pvList.add(netSalesValue);
            
            //Net Sales % of Ex-Factory
            netSalesExFac = new ProjectionResultsDTO();
            calculate(Constant.NET_SALES_PER_OF_EX_FACTORY_HEADER, Constant.VALUE, obj, NumericConstants.FORTY_EIGHT, NumericConstants.FORTY_NINE, netSalesExFac, selection, RATE);
            pvList.add(netSalesExFac);
            
            //COGS
            cogsValue = new ProjectionResultsDTO();
            calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, NumericConstants.THIRTY_EIGHT, NumericConstants.THIRTY_NINE, cogsValue, selection, AMOUNT);
            pvList.add(cogsValue);
            //Net Profit
            netProfitValue = new ProjectionResultsDTO();
            calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, NumericConstants.FORTY, NumericConstants.FORTY_ONE, netProfitValue, selection, AMOUNT);
            pvList.add(netProfitValue);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    private void updateList(List<ProjectionResultsDTO> pvList, Object[] obj, int index) {

        int listIndex = 1;
        //Contract Sales @ WAC
        if (selection.getSalesOrUnit().equals("Sales") || selection.getSalesOrUnit().equals("Both")) {
            salesValue = pvList.get(listIndex++);
            calculate(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, obj, NumericConstants.FIVE, NumericConstants.SIX, salesValue, selection, AMOUNT);
        }
        //Contract Units\
        if (selection.getSalesOrUnit().equals("Units") || selection.getSalesOrUnit().equals("Both")) {
            unitsValue = pvList.get(listIndex++);
            calculate(VAR_CONTRACT_UNITS, Constant.VALUE, obj, NumericConstants.SEVEN, NumericConstants.EIGHT, unitsValue, selection, UNIT_VOLUME);
        }
        //Discount $
        disDollValue = pvList.get(listIndex++);
        calculate(VAR_DIS_AMOUNT, Constant.VALUE, obj, NumericConstants.TWELVE, NumericConstants.THIRTEEN, disDollValue, selection, AMOUNT);
        //Discount %
        disPerValue = pvList.get(listIndex++);
        calculate(VAR_DIS_PERCENT, Constant.VALUE, obj, NumericConstants.FOURTEEN, NumericConstants.FIFTEEN, disPerValue, selection, RATE);
        //RPU
        rpuValue = pvList.get(listIndex++);
        calculate(VAR_RPU, Constant.VALUE, obj, NumericConstants.THIRTY_SIX, NumericConstants.THIRTY_SEVEN, rpuValue, selection, AMOUNT);
        //Discount % of Ex-Factory
        disPerExfac = pvList.get(listIndex++);
        calculate(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER, Constant.VALUE, obj, NumericConstants.FIFTY, NumericConstants.FIFTY_ONE, disPerExfac, selection, RATE);

        //Net Sales 
        netSalesValue = pvList.get(listIndex++);
        calculate(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, obj, NumericConstants.TWENTY_THREE, NumericConstants.TWENTY_FOUR, netSalesValue, selection, AMOUNT);
        //Net Sales % of Ex-Factory
        netSalesExFac = pvList.get(listIndex++);
        calculate(Constant.NET_SALES_PER_OF_EX_FACTORY_HEADER, Constant.VALUE, obj, NumericConstants.FORTY_EIGHT, NumericConstants.FORTY_NINE, netSalesExFac, selection, RATE);
        //COGS
        cogsValue = pvList.get(listIndex++);
        calculate(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, obj, NumericConstants.THIRTY_EIGHT, NumericConstants.THIRTY_NINE, cogsValue, selection, AMOUNT);
        //Net Profit
        netProfitValue = pvList.get(listIndex++);
        calculate(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, obj, NumericConstants.FORTY, NumericConstants.FORTY_ONE, netProfitValue, selection, AMOUNT);
    }

    private void calculate(String varaibleName, String varibaleCat, Object[] obj, int actIndex, int projindex, ProjectionResultsDTO pvDTO,
            ProjectionSelectionDTO selection, DecimalFormat format) {
        pvDTO.setGroup(varaibleName);
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "q" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "s" + obj[isTotal ? 1 : baseColumn_period_index] + StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[isTotal ? 0 : baseColumn_year_index];
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = FORMAT.format(newValue);
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
        Object[] orderedArg = {projectionId, frequency, discountId, "ASSUMPTIONS", selection.getSessionDTO().getSessionId(), selection.getUserId()};
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
             PVParameters lastParameter =null;
             lastParameter = parameters.get("PRC_PROJECTION_VARIANCE");
            if (lastParameter != null) {
                isRefresh = !lastParameter.equals(parameterDto);
            }
            parameters.put("PRC_PROJECTION_VARIANCE", parameterDto.clone());
            }
        if (isRefresh) {
            List<Integer> projectionIdList = new ArrayList();
            projectionIdList.add(selection.getProjectionId());
            String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
            Object[] orderedArg = {projectionId, parameterDto.getUserId(), parameterDto.getSessionId(), parameterDto.getFrequency(), parameterDto.getViewIndicator(),
                parameterDto.getGroupFilter(), parameterDto.getGroupFilterValue(), parameterDto.getHierarchyNo(),
                parameterDto.getLevelNo(), 0, parameterDto.getViewName(), parameterDto.getCustomViewMasterSid(),
                parameterDto.getDiscountLevel()};
               
              List< Object[]> rawList  = CommonLogic.callProcedure(PRC_PR_EXCEL, orderedArg);
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
        Object[] orderedArgs = {selection.getProjectionId(), frequency, discountId, "ASSUMPTIONS", selection.getSessionDTO().getSessionId(), selection.getUserId()};
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
                detail.setHierarchyNo(obj[1].toString());
                detail.setParentHierarchyNo(obj[NumericConstants.FIFTY_THREE] == null ? null : obj[NumericConstants.FIFTY_THREE].toString());
            } else {
                groupName = selection.getSessionDTO().getLevelValueDiscription(obj[baseColumn_hierarchy_index].toString(), obj[baseColumn_hierarchyIndicator_index].toString());
            }

            detail.setGroup(groupName);
            pvList.add(detail);
        }

        pvList.add(frequencyBasedDTO);

        //Ex-Factory-Sales
        calculateForTotal("conSalesWac", Constant.VALUE, obj, NumericConstants.SIX, NumericConstants.SEVEN, frequencyBasedDTO, selection, AMOUNT);
        //Contract Units
        calculateForTotal("unitVol", Constant.VALUE, obj, NumericConstants.EIGHT, NumericConstants.NINE, frequencyBasedDTO, selection, RATE_PER);
        //Discount $
        calculateForTotal("totDisDol", Constant.VALUE, obj, NumericConstants.THIRTEEN, NumericConstants.FOURTEEN, frequencyBasedDTO, selection, AMOUNT);
        //Discount %
        calculateForTotal("totDisPer", Constant.VALUE, obj, NumericConstants.FIFTEEN, NumericConstants.SIXTEEN, frequencyBasedDTO, selection, PER);
        //RPU
        calculateForTotal("totalRPU", Constant.VALUE, obj, NumericConstants.THIRTY_SEVEN, NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
        //Net Sales 
        calculateForTotal("netSales", Constant.VALUE, obj, NumericConstants.TWENTY_FOUR, NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
        //COGS
        calculateForTotal("cogs", Constant.VALUE, obj, NumericConstants.THIRTY_NINE, NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
        //Net Profit
        calculateForTotal("netProfit", Constant.VALUE, obj, NumericConstants.FORTY_ONE, NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
        //Net Sales % of Ex-Factory
        calculateForTotal(Constant.NET_SALES_PER_OF_EX_FACTORY, Constant.VALUE, obj, NumericConstants.FORTY_NINE, NumericConstants.FIFTY, frequencyBasedDTO, selection, PER);
        //Discount % of Ex-Factory
        calculateForTotal(Constant.DISCOUNT_PER_OF_EX_FACTORY, Constant.VALUE, obj, NumericConstants.FIFTY_ONE, NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, PER);

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
            String key = "null".equals(String.valueOf(obj[NumericConstants.FIFTY_THREE])) ? obj[baseColumn_hierarchy_index].toString() : obj[baseColumn_hierarchy_index].toString() + "$" + obj[NumericConstants.FIFTY_THREE].toString();
//            key = key.substring(key.indexOf('-') + 1);
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
            Set<String> hierarchySet = new HashSet();
            Set<String> yearPeriod = new HashSet();
            int listIndex = 0;
            for (ListIterator<Object[]> it = rawList_discount.listIterator(); it.hasNext();) {
                Object[] obj = it.next(); 
                int year = Integer.valueOf(obj[baseColumn_year_index].toString());
                int period = Integer.valueOf(obj[frequencyDivision == 1 ? 0 : baseColumn_period_index].toString());
                if (year < selection.getHistoryStartYear() || ((year == selection.getHistoryStartYear()) && (period < selection.getHistoryStartPeriod()))) {
                    continue;
                }
                String key = "null".equals(String.valueOf(obj[13])) ? obj[baseColumn_hierarchy_index].toString() : obj[baseColumn_hierarchy_index].toString() + "$" + obj[13].toString();
//                key = key.substring(key.indexOf('-') + 1);
                if (hierarchySet.add(key)) {
                    listIndex = 1;
                    yearPeriod.clear();
                    yearPeriod.add(String.valueOf(year) + period);
                } else if (yearPeriod.add(String.valueOf(year) + period)) {
                    listIndex++;
                }
                List<ProjectionResultsDTO> pvList = resultMap.get(key);
                updateList_detail_discount_pivot(pvList, key, obj, listIndex);
            }
        }
    }

    private void updateList_pivot(List<ProjectionResultsDTO> pvList, Object[] obj, ProjectionResultsDTO frequencyBasedDTO, int indexForTotal) {

        pvList.add(frequencyBasedDTO);

        //Contract Sales @ WAC
        calculateForTotal("conSalesWac", Constant.VALUE, obj, NumericConstants.SIX, NumericConstants.SEVEN, frequencyBasedDTO, selection, AMOUNT);
        //Contract Units
        calculateForTotal("unitVol", Constant.VALUE, obj, NumericConstants.EIGHT, NumericConstants.NINE, frequencyBasedDTO, selection, RATE_PER);
        //Discount $
        calculateForTotal("totDisDol", Constant.VALUE, obj, NumericConstants.THIRTEEN, NumericConstants.FOURTEEN, frequencyBasedDTO, selection, AMOUNT);
        //Discount %
        calculateForTotal("totDisPer", Constant.VALUE, obj, NumericConstants.FIFTEEN, NumericConstants.SIXTEEN, frequencyBasedDTO, selection, PER);
        //RPU
        calculateForTotal("totalRPU", Constant.VALUE, obj, NumericConstants.THIRTY_SEVEN, NumericConstants.THIRTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
        //Net Sales 
        calculateForTotal("netSales", Constant.VALUE, obj, NumericConstants.TWENTY_FOUR, NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
        //COGS
        calculateForTotal("cogs", Constant.VALUE, obj, NumericConstants.THIRTY_NINE, NumericConstants.FORTY, frequencyBasedDTO, selection, AMOUNT);
        //Net Profit
        calculateForTotal("netProfit", Constant.VALUE, obj, NumericConstants.FORTY_ONE, NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
        //Net Sales % of Ex-Factory 
        calculateForTotal(Constant.NET_SALES_PER_OF_EX_FACTORY, Constant.VALUE, obj, NumericConstants.FORTY_NINE, NumericConstants.FIFTY, frequencyBasedDTO, selection, PER);
        //Discount % of Ex-Factory
        calculateForTotal(Constant.DISCOUNT_PER_OF_EX_FACTORY, Constant.VALUE, obj, NumericConstants.FIFTY_ONE, NumericConstants.FIFTY_TWO, frequencyBasedDTO, selection, PER);
    }

    public void getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, ProjectionSelectionDTO projSelDTO, int index, int actIndex, boolean isPer, String parentGroup, String indicator) {
        LOGGER.debug("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionResultsDTO> resultDto = new ArrayList<ProjectionResultsDTO>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionResultsDTO pvDTO = new ProjectionResultsDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!"".equals(lastValue) && !"null".equals(lastValue) && obj[NumericConstants.THREE] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.THREE]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);

                    pvDTO = new ProjectionResultsDTO();
                }
                lastValue = String.valueOf(obj[NumericConstants.THREE]);

                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "s" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO])) - 1);
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
            for (Iterator<String> it = projSelDTO.getDiscountNameList().iterator(); it.hasNext();) {
                pvDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove("group");
                for (String Obj : columnList) {
                    pvDTO.addStringProperties(Obj, DASH);
                }
                resultDto.add(pvDTO);
            }
        }
        LOGGER.debug("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + discountList.size());

    }

    public ProjectionResultsDTO getCustomizedPPA(List<Object> dataList, ProjectionSelectionDTO projSelDTO, boolean isDetail, int index, int actIndex, boolean isPer, String parentGroup, String ppaGroup) {
        LOGGER.debug("Inside getCustomizedPPA");
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
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "s" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[NumericConstants.FIVE];
                    }
                    column1 = commonColumn + CURRENT;
                    String value = "" + obj[NumericConstants.FIVE];
                    if (projSelDTO.getSales().contains("SALES")) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains("RATE")) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "s" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[NumericConstants.FIVE];
                    }
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT, isPer ? baseValue + PERCENT : baseValue);
                    value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[actIndex])));
                    baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + "Actuals", isPer ? baseValue + PERCENT : baseValue);
                }
            }
        }
        List list = resultMap.get(parentGroup);
        if (list != null) {
            list.add(pvDTO);
        } else {
            List<ProjectionResultsDTO> dto = new ArrayList<ProjectionResultsDTO>();
            dto.add(pvDTO);
            resultMap.put(parentGroup, dto);
        }

        LOGGER.debug("Ending getCustomizedPPA");
        return pvDTO;
    }

    public void getCustomizedReturns(List<Object> dataList, ProjectionSelectionDTO projSelDTO, boolean isDetail, int index, int actRetIndex, boolean isPer, String parentGroup, String returnsGroup) {
        LOGGER.debug("Inside getCustomizedReturns");
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
            int discountIndex = NumericConstants.FIVE;
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    if (isDetail) {
                        final Object[] obj = (Object[]) dataList.get(i);
                        String commonColumn = StringUtils.EMPTY;
                        String column1 = StringUtils.EMPTY;
                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "s" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[discountIndex - 1];
                            annualIndex = index - 1;
                            annualActRetIndex = actRetIndex - 1;
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[discountIndex];
                        }
                        column1 = commonColumn + CURRENT;
                        String value = "" + obj[NumericConstants.FOUR];
                        if (projSelDTO.getSales().contains("SALES")) {
                            value = getFormattedValue(AMOUNT, value);
                        } else if (projSelDTO.getSales().contains("RATE")) {
                            value = getFormattedValue(RATE, value);
                        }
                        pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                        for (int j = 0; j < priorList.size(); j++) {
                            column1 = commonColumn + priorList.get(j);
                            String pValue = "" + obj[NumericConstants.FOUR + ((j + 1) * NumericConstants.THREE)];
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
                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "s" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[discountIndex];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[discountIndex - 1];
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) - 1);
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
            LOGGER.debug("Ending getCustomizedReturns");
            List list = resultMap.get(parentGroup);
            if (list != null) {
                list.add(pvDTO);
            } else {
                List<ProjectionResultsDTO> returnsDTO = new ArrayList<ProjectionResultsDTO>();
                returnsDTO.add(pvDTO);
                resultMap.put(parentGroup, returnsDTO);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void commonCustomizationForTotalDiscount(String group, String discountType, List<Object> dataList, ProjectionSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer, String ppaGroup, String returnsGroup, List<Object> pivotList) {
        LOGGER.debug("Inside commonCustomizationForTotalDiscount");

        int indexValue = 0;
        int actIndex = 0;
        if (group.equals("D$")) {
            actIndex = NumericConstants.FOUR;
            indexValue = NumericConstants.FIVE;
        } else if (group.equals("D%")) {
            actIndex = NumericConstants.SIX;
            indexValue = NumericConstants.SEVEN;
        } else if (group.equals("Dis%Ex")) { 
            actIndex = NumericConstants.ELEVEN;
            indexValue = NumericConstants.TWELVE;
        } else {
            actIndex = NumericConstants.EIGHT;
            indexValue = NumericConstants.NINE;
        }
        String parentGroup = StringUtils.EMPTY;
        if (!isDetail) {
            parentGroup = group + "value";
            if (!selection.getDiscountNameList().isEmpty()) {

                getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, indexValue, actIndex, isPer, parentGroup, VALUE.getConstant());
            }

        }
        LOGGER.debug("Ending commonCustomizationForTotalDiscount");
    }

    private void calculate_discount(String varaibleName, String varibaleCat, String masterKey, Object[] obj, int actIndex, ProjectionResultsDTO pvDTO,
            ProjectionSelectionDTO selection, DecimalFormat format, boolean isAdd, int listIndex) {
        actIndex++;

        String key = masterKey + varaibleName;
        List<ProjectionResultsDTO> pvList_discount;
        if (isAdd) {
            pvList_discount = resultMap.get(key);
            if (pvList_discount == null) {
                pvList_discount = new ArrayList();
                resultMap.put(key, pvList_discount);
                discountKeys.add(key);
            }
            pvList_discount.add(pvDTO);
            pvDTO.setGroup(obj[index_detail_discount] == null ? "" : obj[index_detail_discount] == null ? "" : obj[index_detail_discount].toString());
        } else {
            pvList_discount = resultMap.get(key);
            pvDTO = pvList_discount.get(listIndex);
        }

        String commonColumn = StringUtils.EMPTY;

        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "q" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "s" + obj[baseColumn_period_discount_index] + StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[baseColumn_year_discount_index];
        } else if (frequencyDivision == NumericConstants.TWELVE) {
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
    }

    private void addList_detail_discount(String key, final Object[] obj) {
        //Discount $
        disDollValue = new ProjectionResultsDTO();
        calculate_discount(VAR_DIS_AMOUNT, Constant.VALUE, key, obj, index_detail_discount, disDollValue, selection, AMOUNT, true, 0);
        //Discount %
        disPerValue = new ProjectionResultsDTO();
        calculate_discount(VAR_DIS_PERCENT, Constant.VALUE, key, obj, index_detail_discount + NumericConstants.TWO, disPerValue, selection, RATE, true, 0);
        //RPU
        rpuValue = new ProjectionResultsDTO();
        calculate_discount(VAR_RPU, Constant.VALUE, key, obj, index_detail_discount + NumericConstants.FOUR, rpuValue, selection, AMOUNT, true, 0);
        //Discount % of Ex-Factory
        disPerExfac = new ProjectionResultsDTO();
        calculate_discount(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER, Constant.VALUE, key, obj, index_detail_discount + NumericConstants.SEVEN, disPerExfac, selection, RATE, true, 0);
    }

    private void updateList_detail_discount(String key, final Object[] obj, int listIndex) {
        calculate_discount(VAR_DIS_AMOUNT, Constant.VALUE, key, obj, index_detail_discount, null, selection, AMOUNT, false, listIndex);
        //Discount %
        calculate_discount(VAR_DIS_PERCENT, Constant.VALUE, key, obj, index_detail_discount + NumericConstants.TWO, null, selection, RATE, false, listIndex);

        //RPU
        calculate_discount(VAR_RPU, Constant.VALUE, key, obj, index_detail_discount + NumericConstants.FOUR, null, selection, AMOUNT, false, listIndex);
        //Discount % of Ex-Factory
        calculate_discount(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER, Constant.VALUE, key, obj, index_detail_discount + NumericConstants.SEVEN, null, selection, RATE, false, listIndex);

    }

    private void updateList_detail_discount_pivot(List<ProjectionResultsDTO> pvList, String key, final Object[] obj, int listIndex) {
        ProjectionResultsDTO frequencyBasedDTO = pvList.get(listIndex < pvList.size() ? listIndex : 0);
        //Discount $
        calculate_discount_pivot("totDisDol", Constant.VALUE,  obj, frequencyBasedDTO, index_detail_discount, selection, AMOUNT);
        //Discount %
        calculate_discount_pivot("totDisPer", Constant.VALUE, obj, frequencyBasedDTO, index_detail_discount + NumericConstants.TWO, selection, PER);
        //RPU
        calculate_discount_pivot("totalRPU", Constant.VALUE, obj, frequencyBasedDTO, index_detail_discount + NumericConstants.FOUR, selection, AMOUNT);
        //Discount %
        calculate_discount_pivot(Constant.DISCOUNT_PER_OF_EX_FACTORY, Constant.VALUE, obj, frequencyBasedDTO, index_detail_discount + NumericConstants.SEVEN, selection, PER);
    }

    private void calculate_discount_pivot(String varaibleName, String varibaleCat, Object[] obj, ProjectionResultsDTO pvDTO, int index,
            ProjectionSelectionDTO selection, DecimalFormat format) {

        String discoun_ppa_returns_Name = obj[NumericConstants.SIXTEEN] == null ? "" : obj[NumericConstants.SIXTEEN].toString();
        discoun_ppa_returns_Name = obj[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : obj[NumericConstants.SIXTEEN].toString().replace(" ", StringUtils.EMPTY);

        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + NumericConstants.TWO])));
        String baseValue = getFormattedValue(format, value);
        pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + CURRENT, baseValue);
        value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + 1])));
        baseValue = getFormattedValue(format, value);
        pvDTO.addStringProperties(varaibleName + discoun_ppa_returns_Name + ACTUALS, baseValue);
        
    }

    public void discount_Customize() {
        boolean isDetail = selection.getLevel().equals(DETAIL);
        commonCustomizationForTotalDiscount("D$", "Discount", pivotDiscountList, selection, isDetail, NumericConstants.THREE, Boolean.FALSE, "PPA Discount $", "Return Discount $", pivotTotalList);
        commonCustomizationForTotalDiscount("D%", "Discount", pivotDiscountList, selection, isDetail, NumericConstants.FOUR, Boolean.TRUE, "PPA Discount %", "Return Discount %", pivotTotalList);
        commonCustomizationForTotalDiscount("RPU-", "Discount", pivotDiscountList, selection, isDetail, NumericConstants.FIVE, Boolean.FALSE, "PPA RPU", "Returns RPU", pivotTotalList);
        commonCustomizationForTotalDiscount("Dis%Ex", "Discount", pivotDiscountList, selection, isDetail, NumericConstants.FOUR, Boolean.TRUE, "PPA Discount %", "Return Discount %", pivotTotalList); //change the NumericConstants
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
         Object[] orderedArg = {projectionId, frequency, discountId, "ASSUMPTIONS", selection.getSessionDTO().getSessionId(), selection.getUserId(), "PIVOT"};
        List< Object[]> gtsResult   = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        
        
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
           Object[] orderedArg = {projectionId, frequency, discountId, "ASSUMPTIONS", projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId(), "1"};
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
        
    private void calculateAndCustomize_periodForCustomView(List<Object[]> rawList, List<Object[]> rawList_discount) {
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = "null".equals(String.valueOf(obj[NumericConstants.FIFTY_THREE])) ? obj[baseColumn_hierarchy_index].toString() : obj[baseColumn_hierarchy_index].toString() + "$" + obj[NumericConstants.FIFTY_THREE].toString();
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
                String key = "null".equals(String.valueOf(obj[13])) ? obj[baseColumn_hierarchy_index].toString() : obj[baseColumn_hierarchy_index].toString() + "$" + obj[13].toString();
                String discountName = obj[6] == null ? "" : obj[6].toString();
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

    private void customhierarchyAndTP_keys(Object[] obj, String key, List<ProjectionResultsDTO> pvList) {
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

