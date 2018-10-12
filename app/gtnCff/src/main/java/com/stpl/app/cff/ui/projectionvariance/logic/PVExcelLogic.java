/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionvariance.logic;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionvariance.dto.PVParameters;
import com.stpl.app.cff.ui.projectionvariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CommonConstants.VARIANCE;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nandha Kumar
 */
public class PVExcelLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(PVExcelLogic.class);
    

    private final Map<String, List<ProjectionVarianceDTO>> resultMap;
    private final Map<String, List<List<ProjectionVarianceDTO>>> discountMapDetails;
    private final Map<String, Map<String, ProjectionVarianceDTO>> pivotDiscountMap = new HashMap<>();
    private final List<String> hierarchyKeys;
    private final List<String> tradingPartnerKeys;
    private final List<String> discountKeys;
    private final PVSelectionDTO selection;
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat UNIT = new DecimalFormat("#,###");
    public static final String CUSTOM_CONSTANT = "Custom";
    public static final String STRING_TWO_DECIMAL_FORMAT = "#,##0.00";
    private static final DecimalFormat RATE_PER = new DecimalFormat(STRING_TWO_DECIMAL_FORMAT);
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final int INDEX_VALUE = 6;
    private int frequencyDivision;
    private static final int BASECOLUMN_LEVELNAME_INDEX = 3;
    private static final List<Object> PROCRAWLIST_TOTAL_PERIOD = new ArrayList();
    private static final List<Object[]> PROCRAWLIST_DETAIL = new ArrayList();
    private static final List<Object[]> PROCRAWLIST_DETAIL_DISCOUNT = new ArrayList();
    private static final List<Integer> PRIOR_LIST = new ArrayList();
    private boolean isTotal = false;
    private String levelFilterValue = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String viewValue = StringUtils.EMPTY;
    public static final String CUSTOMER_VARIABLE = "Customer";
    public static final String SEPERATOR_FORMAT = "`\\$";
    private final Map<String, String> discountNameMap = new HashMap<>();
    public static final String TOTAL1 = "Total";
    private List<Object> pivotDiscountList = new ArrayList<>();
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private List<Integer> pivotPriorProjIdList;
    private final Map<String, String> customViewRelationshipHier = new HashMap();
    private final PVParameters parameterDto;
    private boolean discountFlag;
    private boolean isCustomView;
    private static final String PRC_CFF_RESULTS = "Prc_cff_results";
    private static final String C = "C";
    private static final String P = "P";
    private final Map<String, List<ProjectionVarianceDTO>> discountMap;
    private final ProjectionVarianceLogic logic = new ProjectionVarianceLogic();
   public static final String SCHEDULE_ID = "Schedule ID";
    public static final String PROGRAM_TYPESMALL = "Program Type";
    public static final String SCHEDULE_CATEGORY_SMALL = "Schedule Category";
    public static final String PRC_CFF_EXCEL_EXPORT = "PRC_CFF_EXCEL_EXPORT";
    public static final String PERIOD_LABEL = "Period";
    public static final String PRC_CFF_DISCOUNT_EXCEL_EXPORT = "PRC_CFF_DISCOUNT_EXCEL_EXPORT";
    public static final char D_INDICATOR = 'D';
    public static final String EXCEL_FLAG = "isExcel";
    public static final String DISPLAY_FORMAT = "format";
    public static final String PIVOT_LABEL = "PIVOT";
    private static final int COLUMN_COUNT_DISCOUNT = 12;
    public static final String PROGRAM = "PROGRAM";
    public static final String PROGRAM_CATEGORY = "PROGRAM CATEGORY";
    private static final String ALL = "ALL";
    private static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    private static final String DF_LEVEL_NAME = "dfLevelName";

    public PVExcelLogic(Map<String, List<ProjectionVarianceDTO>> resultMap, PVSelectionDTO selection,
            List<String> hierarchyKeys, List<String> tradingPartnerKeys, List<String> discountKeys, PVParameters parameterDto, Map<String, List<ProjectionVarianceDTO>> discountMap, Map<String, List<List<ProjectionVarianceDTO>>> discountMapDetails) {
        this.resultMap = resultMap;
        this.selection = selection;
        this.hierarchyKeys = hierarchyKeys;
        this.tradingPartnerKeys = tradingPartnerKeys;
        this.discountKeys = discountKeys;
        this.parameterDto = parameterDto;
        this.discountMap = discountMap;
        this.discountMapDetails = discountMapDetails;
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
        PRIOR_LIST.clear();
        PRIOR_LIST.addAll(selection.getProjIdList());
        discountKeys.clear();
        isTotal = TOTAL1.equalsIgnoreCase(selection.getLevel());
        discountFlag = !ConstantsUtil.TOTAL_DISCOUNT.equalsIgnoreCase(selection.getDiscountLevel());
        isCustomView = selection.isIsCustomHierarchy();
        if (isCustomView) {
            selection.setHierarchyIndicator(StringUtils.EMPTY);
            customViewRelationshipHier.putAll(getGroupCustomViewNM());
        }
        isRefreshNeeded(selection.getLevelFilterValue(), selection.getGroupFilter(), selection.getHierarchyIndicator(), selection.getFrequencyDivision());
        if (Constants.LabelConstants.PERIOD.toString().equalsIgnoreCase(selection.getPivotView())) {
            if (isTotal) {
                getTotalRawData();
                if (discountFlag) {
                    getTotalDiscount(selection);
                }
                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                List<ProjectionVarianceDTO> totalList = logic.getCustPeriodVariance(PROCRAWLIST_TOTAL_PERIOD, selection, dto);
                resultMap.put(TOTAL1, totalList);

                if (discountFlag) {
                    discountCustomize();
                }
            } else {
                executeProcedurePRCPVSELECTION();
                if (discountFlag) {
                    parameterDto.setViewName("DETAIL_DISCOUNT");
                    calculateDiscount();
                }
                calculateAndCustomizeDetailPeriod(PROCRAWLIST_DETAIL);
            }
        } else if (isTotal) {
            getTotalRawDataPivot();
            calculateAndCustomizeTotalPivot();
        } else {
            executeProcedurePRCPVSELECTIONPIVOT();
            if (discountFlag) {
                parameterDto.setViewName("DETAIL_DISCOUNT");
            }
            customizeDiscountPivot();
            if (isCustomView) {
                calculateDetailPivotForCustomView(PROCRAWLIST_DETAIL);
            } else {
                calculateAndCustomizeDetailPivot(PROCRAWLIST_DETAIL);
            }
            
        }
    }

    private boolean isRefreshNeeded(String levelFilterValue, String groupFilterValue, String viewValue, int freDiv) {
        boolean val = this.frequencyDivision == freDiv
                && this.levelFilterValue.equals(levelFilterValue)
                && this.groupFilterValue.equals(groupFilterValue)
                && this.viewValue.equals(viewValue);
        this.levelFilterValue = levelFilterValue;
        this.groupFilterValue = groupFilterValue;
        this.viewValue = viewValue;
        this.frequencyDivision = freDiv;
        return !val;
    }

    private void calculateAndCustomizeDetailPeriod(List<Object[]> rawList) {
         Map<Object, List<Object[]>> groupedResult = rawList.stream().map(obj -> (Object[]) obj)
                .collect(Collectors.groupingBy(x -> {
                    return new ArrayList<>(Arrays.asList(x[0],x[1],x[3], x[4]));
                }));
        for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
            List<Object[]> list = entry.getValue();
            final Object[] obj = list.get(0);
            final Object[] proj;
            final Object[] actual;
            if (list.size() > 1) {
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = list.get(1);
                } else {
                    actual = list.get(1);
                    proj = list.get(0);
                }
            } else {
                Object[] empty = IntStream.rangeClosed(0, obj.length).boxed().map(e -> 0).toArray();
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = empty;
                } else {
                    actual = empty;
                    proj = list.get(0);
                }
            }
            String key = obj[1].toString();
            detailCustomization(key, obj, actual, proj);
        }
    }

    public void detailCustomization(String key, Object[] obj,Object[] actual,Object[] proj) {
        List<ProjectionVarianceDTO> pvList = resultMap.get(key);
        if (pvList == null) {
            //To check condition total or details values
            pvList = new ArrayList();
            getCustPeriodVariancDetails(pvList, selection, obj,actual,proj);
            if (isCustomView) {
                customHierarchyAndTPKeys(obj, key, pvList);
            } else {
                hierarchyAndTPkeys(obj, key, pvList);
            }
        } else {
            updateCustPeriodVarianceDetails(pvList, selection, obj,actual,proj);
        }
    }


	private void hierarchyAndTPkeys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {

        hierarchyKeys.add(key);
        resultMap.put(key, pvList);
        if (CUSTOMER_VARIABLE.equalsIgnoreCase(String.valueOf(obj[BASECOLUMN_LEVELNAME_INDEX]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[BASECOLUMN_LEVELNAME_INDEX]))) {
            tradingPartnerKeys.add(key);
        }
    }

	public String getFormattedValue(DecimalFormat format, String value) {
            String valueExcel = value;
        if (valueExcel.contains(Constants.NULL)) {
            valueExcel = ZERO;
        } else {
			valueExcel = format.format(Double.valueOf(valueExcel));
        }
        return valueExcel;
    }

    public String isNull(String valueParam) {
        String value = valueParam;
        if (value.contains(Constants.NULL)) {
            value = ZERO;
        }
        return value;
    }

    public void getTotalRawData() {
        List<Integer> projectionIdList = new ArrayList();
        PROCRAWLIST_TOTAL_PERIOD.clear();
        PRIOR_LIST.clear();
        pivotDiscountList.clear();
        String frequency = checkFrequency(selection.getFrequency());
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String projectionId = CommonUtils.collectionToString(projectionIdList, false);
        String viewName=selection.getView().equalsIgnoreCase(CUSTOM_CONSTANT)?String.valueOf(D_INDICATOR):selection.getView();
        Object[] orderedArg = {projectionId,selection.getSessionDTO().getLevelHierarchyNo(), frequency,null,null,
           selection.getSessionDTO().getUserId() ,selection.getSessionDTO().getSessionId() ,viewName, 
           selection.getSessionDTO().getCustomViewMasterSid(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion())? null : selection.getSessionDTO().getDeductionInclusion()};
        List< Object[]> rawList = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        PROCRAWLIST_TOTAL_PERIOD.addAll(rawList);
        rawList.clear();
    }

    public String checkFrequency(String frequency) {
        String frequencyCheck = frequency;
        switch (frequencyCheck) {
            case StringConstantsUtil.QUARTERLY_FREQ:
                frequencyCheck = StringConstantsUtil.QUARTERLY_LABEL;
                break;
            case StringConstantsUtil.SEMI_ANNUALLY_FREQ:
                frequencyCheck = StringConstantsUtil.SEMI_ANNUAL_LABEL;
                break;
            case StringConstantsUtil.MONTHLY_FREQ:
                frequencyCheck = StringConstantsUtil.MONTHLY_LABEL;
                break;
            default:
                frequencyCheck = StringConstantsUtil.ANNUAL_LABEL;
                break;
        }
        return frequencyCheck;
    }

    public void getTotalRawDataPivot() {
        List<Integer> projectionIdList = new ArrayList();
        PROCRAWLIST_TOTAL_PERIOD.clear();
        PRIOR_LIST.clear();
        pivotDiscountList.clear();
        String frequency = checkFrequency(selection.getFrequency());

        projectionIdList.add(selection.getCurrentProjId());
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(projId);
        }
        
        String projectionIds = CommonUtils.collectionToString(projectionIdList, false);
        String viewName = selection.getView().equalsIgnoreCase(CUSTOM_CONSTANT) ? String.valueOf(D_INDICATOR) : selection.getView();
        Object[] orderedArg = {projectionIds, selection.getSessionDTO().getLevelHierarchyNo(), frequency, null, null,
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(), viewName,
            selection.getSessionDTO().getCustomViewMasterSid(), ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        PROCRAWLIST_TOTAL_PERIOD.addAll(gtsResult);
        
        if (!selection.getDiscountLevel().equals(ConstantsUtil.TOTAL_DISCOUNT)) {
            String discountLevelName = !selection.getDeductionLevelFilter().isEmpty() ? selection.getDeductionLevelValues() : selection.getDiscountLevel();
            discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM_CATEGORY) ? PROGRAM_TYPESMALL : discountLevelName;
            discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM) ? SCHEDULE_ID : discountLevelName;
            Object[] orderedArgDiscount = {projectionIds, frequency, null, null, discountLevelName,
                ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(), null,
                selection.getSessionDTO().getCustomViewMasterSid(), selection.getSessionDTO().getSessionId(), selection.getSessionDTO().getUserId(), viewName, selection.getSessionDTO().getLevelHierarchyNo()};

            List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArgDiscount);
            pivotDiscountList.addAll(discountsList);
        }
    }

    public void executeProcedurePRCPVSELECTION() {
        PROCRAWLIST_DETAIL.clear();
        PROCRAWLIST_DETAIL_DISCOUNT.clear();
        List<Integer> projectionIdList = new ArrayList();
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String frequency = selection.getFrequency();
        String projectionId = CommonUtils.collectionToString(projectionIdList, false);
        String levelNo= selection.getView().equalsIgnoreCase(CUSTOMER_VARIABLE)? selection.getSessionDTO().getProductLevelNumber():selection.getSessionDTO().getCustomerLevelNumber();
        levelNo = selection.getView().equalsIgnoreCase(CUSTOM_CONSTANT) ? "1" : levelNo;
        List<Object[]> rawList;
        List<Object[]> rawListDiscount;
        String viewIndicator = selection.getView().equalsIgnoreCase(CUSTOM_CONSTANT) ? String.valueOf(D_INDICATOR) : selection.getView();
        Object[] orderedArg = {projectionId, frequency, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),
            viewIndicator, selection.getCustomId(), ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(),
            ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),levelNo };
        
        rawList = CommonLogic.callProcedure(PRC_CFF_EXCEL_EXPORT, orderedArg);
        PROCRAWLIST_DETAIL.addAll(rawList);
        
        if (discountFlag) {
              String discountLevelName = !selection.getDeductionLevelFilter().isEmpty() ? selection.getDeductionLevelValues() : selection.getDiscountLevel();
             discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM_CATEGORY) ?SCHEDULE_CATEGORY_SMALL : discountLevelName;
            discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM) ? SCHEDULE_ID : discountLevelName;
             Object[] orderedArgDiscountCustom = {projectionId, frequency, discountLevelName, 
                 ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), 
                 ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),
                    null,selection.getCustomId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getUserId(),viewIndicator,levelNo};
             
             rawListDiscount = CommonLogic.callProcedure(PRC_CFF_DISCOUNT_EXCEL_EXPORT, orderedArgDiscountCustom);
           
            PROCRAWLIST_DETAIL_DISCOUNT.addAll(rawListDiscount);
        }
    }

    public void executeProcedurePRCPVSELECTIONPIVOT() {
        PROCRAWLIST_DETAIL.clear();
        PROCRAWLIST_DETAIL_DISCOUNT.clear();
        List<Integer> projectionIdList = new ArrayList();
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String frequency = selection.getFrequency();
        String projectionId = CommonUtils.collectionToString(projectionIdList, false);
      
        String levelNo= selection.getView().equalsIgnoreCase(CUSTOMER_VARIABLE)? selection.getSessionDTO().getProductLevelNumber():selection.getSessionDTO().getCustomerLevelNumber();
        levelNo = selection.getView().equalsIgnoreCase(CUSTOM_CONSTANT) ? "1" : levelNo;
        String viewName=selection.getView().equalsIgnoreCase(CUSTOM_CONSTANT)?String.valueOf(D_INDICATOR):selection.getView();
        Object[] orderedArg = {projectionId,frequency,
           selection.getSessionDTO().getUserId() ,selection.getSessionDTO().getSessionId() ,viewName, 
           selection.getSessionDTO().getCustomViewMasterSid(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), 
           ALL.equals(selection.getSessionDTO().getDeductionInclusion())? null : selection.getSessionDTO().getDeductionInclusion(),levelNo};
        List<Object[]> rawList = CommonLogic.callProcedure(PRC_CFF_EXCEL_EXPORT, orderedArg);
        PROCRAWLIST_DETAIL.addAll(rawList);
        discountProcedureExecute(projectionId, frequency, viewName, levelNo);
    }

    private void discountProcedureExecute(String projectionId, String frequency, String viewName, String levelNo) {
        List<Object[]> rawListDiscount;
        if (discountFlag) {
            String discountLevelName = !selection.getDeductionLevelFilter().isEmpty() ? selection.getDeductionLevelValues() : selection.getDiscountLevel();
            discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM_CATEGORY) ? PROGRAM_TYPESMALL : discountLevelName;
            discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM) ? SCHEDULE_ID : discountLevelName;
            Object[] orderedArgDiscountCustomPivot = {projectionId, frequency, discountLevelName,
                ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(),
                ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),
                null,selection.getCustomId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getUserId(),viewName,levelNo};
            rawListDiscount = CommonLogic.callProcedure(PRC_CFF_DISCOUNT_EXCEL_EXPORT, orderedArgDiscountCustomPivot);
            PROCRAWLIST_DETAIL_DISCOUNT.addAll(rawListDiscount);

        }
    }

    private void calculateAndCustomizeTotalPivot() {
        if (pivotPriorProjIdList != null) {
            pivotPriorProjIdList.clear();
        } else {
            pivotPriorProjIdList = new ArrayList<>();
        }
        
        for (Integer projId : selection.getProjIdList()) {
            pivotPriorProjIdList.add(projId);
        }
        List<ProjectionVarianceDTO> finalList = logic.getCustomizedPivotTotalResults(PROCRAWLIST_TOTAL_PERIOD,selection, pivotDiscountList);
        resultMap.put(TOTAL1, finalList);
    }

    private void addListPivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, 
            int indexTotal, String key, String group,Object[] inputData) {
        List<Object[]> rawList = (List<Object[]>) inputData[0];
        Object[] actual = (Object[]) inputData[1];
        Object[] proj = (Object[]) inputData[2];
        List<String> periodList = new ArrayList<>(selection.getPeriodList());
        int indexForTotal = indexTotal - 1;
        int tempFrequencyDivision = selection.getFrequencyDivision();
         if (isTotal) {
            ProjectionVarianceDTO total = new ProjectionVarianceDTO();
            total.setGroup(PROJECTION_TOTAL);
            total.setDfLevelNumber(PROJECTION_TOTAL);
            total.setDfLevelName(PROJECTION_TOTAL);
            pvList.add(total);
        } else {
            ProjectionVarianceDTO detail = new ProjectionVarianceDTO();
            String groupName = null;
           
                if (CommonUtils.isValueEligibleForLoading()) {
                   groupName = getFormattedExcelColumns(detail, selection, obj);

                } else {
                    Map<Object,Object> dataMap=new HashMap<>();
                    dataMap.put(DISPLAY_FORMAT, selection.getDisplayFormat());
                    dataMap.put(EXCEL_FLAG, Boolean.TRUE);
                    groupName = CommonUtils.getDisplayFormattedName(obj[NumericConstants.TWO].toString(), selection.getHierarchyIndicator(),
                            selection.getSessionDTO().getHierarchyLevelDetails(), selection.getSessionDTO(),dataMap );
                }
            
            detail.setGroup(groupName);
            pvList.add(detail);
        }
        String groupColumn;
        if (tempFrequencyDivision == NumericConstants.TWELVE) {

            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
            groupColumn = groupColumn.toLowerCase(Locale.ENGLISH);
        } else {
            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
        }
        if (periodList.contains(groupColumn)) {
            pvList.add(frequencyBasedDTO);
            //Ex-Factory-Sales
            if (selection.isVarExFacSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacValue", Constants.VALUE, actual, NumericConstants.TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacVariance", Constants.VARIANCE,actual, NumericConstants.TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("ExFacPer", Constants.CHANGE, actual, NumericConstants.TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Ex-Factory-Customer
            if (selection.isVarExFacSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacValue", Constants.VALUE, actual, NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("CustExFacPer", Constants.CHANGE, actual, NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Demand
            if (selection.isVarDemandSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesValue", Constants.VALUE, actual, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesVariance", Constants.VARIANCE, actual, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("DemandSalesPer", Constants.CHANGE, actual, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Adjusted Demand
            if (selection.isVarAdjDemand()) {

                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("AdjDemandValue", Constants.VALUE, actual, NumericConstants.TWENTY_SEVEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("AdjDemandVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_SEVEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("AdjDemandPer", Constants.CHANGE, actual, NumericConstants.TWENTY_SEVEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Inventory Withdraw Summary
            if (selection.isVarInvSales()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("InvWithValue", Constants.VALUE, actual, NumericConstants.NINETEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("InvWithVariance", Constants.VARIANCE, actual, NumericConstants.NINETEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("InvWithPer", Constants.CHANGE, actual, NumericConstants.NINETEEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Inventory Withdraw Details
            if (selection.isVarIwDetails()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithDetailsValue", Constants.VALUE, actual, NumericConstants.TWENTY_NINE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithDetailsVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_NINE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("InvWithDetailsPer", Constants.CHANGE, actual, NumericConstants.TWENTY_NINE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of Ex-Factory product
            if (selection.isVarPerExFacSales()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerExFacValue", Constants.VALUE,actual, NumericConstants.NINE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerExFacVariance", Constants.VARIANCE, actual, NumericConstants.NINE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerExFacPer", Constants.CHANGE, actual, NumericConstants.NINE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of Ex-Factory Cust
            if (selection.isVarPerExFacCustomer()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerCustExFacValue", Constants.VALUE, actual, NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerCustExFacPer", Constants.CHANGE, actual,NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of Demand
            if (selection.isVarPerDemandSales()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerDemandSalesValue", Constants.VALUE, actual, NumericConstants.TWENTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerDemandSalesPer", Constants.CHANGE,actual, NumericConstants.TWENTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of Adjusted Demand
            if (selection.isVarPerAdjDemand()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerAdjDemandValue", Constants.VALUE,actual, NumericConstants.THIRTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE,actual, NumericConstants.THIRTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of inventory Withdraw Summary
            if (selection.isVarPerInvSales()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithValue", Constants.VALUE, actual, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithPer", Constants.CHANGE, actual, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //% of inventory Withdraw details
            if (selection.isVarPerIwDetails()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithDetailsValue", Constants.VALUE, actual, NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithDetailsVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithDetailsPer", Constants.CHANGE, actual, NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Contract Sales @ WAC
            if (selection.isVarContractsales()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACValue", Constants.VALUE, actual, NumericConstants.FIVE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, actual, NumericConstants.FIVE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, actual, NumericConstants.FIVE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //Contract Units
            if (selection.isVarContractUnits()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("ContractUnitsValue", Constants.VALUE, actual, NumericConstants.SIX, frequencyBasedDTO, selection,new Object[]{ UNIT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("ContractUnitsVar", Constants.VARIANCE, actual, NumericConstants.SIX, frequencyBasedDTO, selection,new Object[]{ UNIT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("ContractUnitsPer", Constants.CHANGE, actual, NumericConstants.SIX, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //Discount $
            if (selection.isVarDisAmount()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountValue", Constants.VALUE, actual, NumericConstants.SEVEN, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountVar", Constants.VARIANCE, actual, NumericConstants.SEVEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("DiscountAmountPer", Constants.CHANGE, actual, NumericConstants.SEVEN, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }
            //Discount %
            if (selection.isVarDisRate()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountSalesValue", Constants.VALUE, actual, NumericConstants.EIGHT, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountSalesVar", Constants.VARIANCE, actual, NumericConstants.EIGHT, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountSalesPer", Constants.CHANGE, actual, NumericConstants.EIGHT, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //RPU
            if (selection.isVarRPU()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("RPUValue", Constants.VALUE, actual, NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("RPUVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("RPUPer", Constants.CHANGE, actual, NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //DiscountPerExFactory
            if (selection.isDiscountPerExFactory()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountPerExFactoryValue", Constants.VALUE, actual, NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountPerExFactoryVar", Constants.VARIANCE, actual, NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountPerExFactoryPer", Constants.CHANGE, actual, NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //Net Sales 
            if (selection.isVarNetSales()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesValue", Constants.VALUE, actual, NumericConstants.SEVENTEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesVariance", Constants.VARIANCE, actual, NumericConstants.SEVENTEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("NetSalesPer", Constants.CHANGE, actual, NumericConstants.SEVENTEEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //NetSales%ExFactory 
            if (selection.isNetSalesExFactory()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("NetSalesExFactoryValue", Constants.VALUE, actual, NumericConstants.THIRTY_THREE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("NetSalesExFactoryVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY_THREE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("NetSalesExFactoryPer", Constants.CHANGE, actual, NumericConstants.THIRTY_THREE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
        
        String hierarchyNo = String.valueOf(obj[1]);
        String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
            Map<String, List> relationshipLevelDetailsMap = selection.isIsCustomHierarchy() ? selection.getSessionDTO().getCustomDescription() : selection.getSessionDTO().getHierarchyLevelDetails();
            List productList = relationshipLevelDetailsMap.get(hierarchy.trim());
            if (!isTotal && productList !=null && !productList.isEmpty() && P.equals(String.valueOf(productList.get(4)))) {
                /**
                 * Net Ex-Factory Sales
                 */
                if (selection.isNetExFactorySales()) {
                    if (selection.isColValue()) {
                         selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VALUE, Constants.VALUE, actual, NumericConstants.THIRTY_FIVE, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                    }
                    if (selection.isColVariance()) {
                         selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VARIANCE, Constants.VARIANCE, actual, NumericConstants.THIRTY_FIVE, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                    }
                    if (selection.isColPercentage()) {
                         selection.setConversionNeeded(false);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constants.CHANGE, actual, NumericConstants.THIRTY_FIVE, frequencyBasedDTO, selection, new Object[]{RATE,rawList,proj});
                    }
                }
                /**
                 * Net Ex-Factory Sales as % of Ex-Factory Sales
                 */
                if (selection.isNetExFactorySalesPerExFactory()) {
                     selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constants.VALUE, actual, NumericConstants.THIRTY_SIX, frequencyBasedDTO, selection,new Object[]{ RATE,rawList,proj});
                    }
                    if (selection.isColVariance()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constants.VARIANCE, actual, NumericConstants.THIRTY_SIX, frequencyBasedDTO, selection,new Object[]{ RATE,rawList,proj});
                    }
                    if (selection.isColPercentage()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constants.CHANGE, actual, NumericConstants.THIRTY_SIX, frequencyBasedDTO, selection, new Object[]{RATE,rawList,proj});
                    }
                }
            }

            //COGS
            if (selection.isVarCOGC()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("COGCValue", Constants.VALUE, actual, indexForTotal + NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("COGCVariance", Constants.VARIANCE, actual, indexForTotal + NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("COGCPer", Constants.CHANGE, actual, indexForTotal + NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }
            //Net Profit
            if (selection.isVarNetProfit()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitValue", Constants.VALUE, actual, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitVariance", Constants.VARIANCE, actual, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("NetProfitPer", Constants.CHANGE, actual, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            String commonColumn = StringUtils.EMPTY;

            if (tempFrequencyDivision == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (tempFrequencyDivision == NumericConstants.TWO) {
                commonColumn = "S" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (tempFrequencyDivision == 1) {
                commonColumn = String.valueOf(obj[NumericConstants.THREE]);
            } else if (tempFrequencyDivision == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[NumericConstants.THREE];
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
    public static final String PROJECTION_TOTAL = "Projection Total";

    private void calculateForTotal(String variableName, String varibaleCat,Object[] actual, int index, ProjectionVarianceDTO pvDTO, 
            PVSelectionDTO selection, Object[] inputData) {
        DecimalFormat format=(DecimalFormat)inputData[0];
        List<Object[]> rawList=(List<Object[]>)inputData[1];
        Object[] proj=(Object[])inputData[2];
        PVCommonLogic.customizePeriodV2(variableName, varibaleCat, selection, pvDTO, format, index, actual,proj, format.equals(RATE_PER),true);
        
        List<Object[]> priorList=rawList.stream().filter(e->Integer.parseInt(String.valueOf(e[0]))!=selection.getCurrentProjId()).collect(Collectors.toList());
        for (int j = 0; j < PRIOR_LIST.size(); j++) {
            PVCommonLogic.getPriorCommonCustomizationV2(varibaleCat, selection, priorList, pvDTO, variableName, index, j, format.equals(RATE_PER), format,true);
        }
    }

    private void calculateAndCustomizeDetailPivot(List<Object[]> rawList) {
          Map<Object, List<Object[]>> groupedResult = rawList.stream().map(obj -> (Object[]) obj)
                .collect(Collectors.groupingBy(x -> {
                    return new ArrayList<>(Arrays.asList(x[0],x[1],x[3], x[4]));
                }));
        for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
             List<Object[]> list = entry.getValue();
             final Object[] obj = list.get(0);
            final Object[] proj;
            final Object[] actual;
            if (list.size() > 1) {
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = list.get(1);
                } else {
                    actual = list.get(1);
                    proj = list.get(0);
                }
            } else {
                Object[] emptyArray = Collections.nCopies(obj.length, 0).toArray(new Integer[0]);
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = emptyArray;
                } else {
                    actual = emptyArray;
                    proj = list.get(0);
                }
            }
            String key = obj[1].toString();
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.parseInt(obj[0].toString()), Integer.parseInt(obj[1].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            freVarianceDTO.setDfLevelNumber(groupId);
            freVarianceDTO.setDfLevelName(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addListPivot(pvList, obj, freVarianceDTO, INDEX_VALUE, key, groupId,new Object[]{rawList,actual,proj});
				hierarchyAndTPkeys(obj, key, pvList);
            } else {
                updateListPivot(pvList, obj, freVarianceDTO, INDEX_VALUE, key, groupId,rawList,actual,proj);
            }
        }
    }

    private void updateListPivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexTotal, 
            String key, String group,List<Object[]> rawList,Object[] actual,Object[] proj) {
        List<String> periodList = new ArrayList<>(selection.getPeriodList());
        String groupColumn;
        if (frequencyDivision == NumericConstants.TWELVE) {
            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
            groupColumn = groupColumn.toLowerCase(Locale.ENGLISH);
        } else {
            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
        }
        if (periodList.contains(groupColumn)) {
            int indexForTotal = indexTotal - 1;

            pvList.add(frequencyBasedDTO);
            //Ex-Factory-Sales
            if (selection.isVarExFacSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacValue", Constants.VALUE, actual, NumericConstants.TWO, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacVariance", Constants.VARIANCE, actual, NumericConstants.TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("ExFacPer", Constants.CHANGE, actual, NumericConstants.TWO, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //Ex-Factory-Customer
            if (selection.isVarExFacCustomer()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacValue", Constants.VALUE, actual, NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("CustExFacPer", Constants.CHANGE, actual, NumericConstants.TWENTY_EIGHT, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //Demand
            if (selection.isVarDemandSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesValue", Constants.VALUE, actual, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesVariance", Constants.VARIANCE, actual, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("DemandSalesPer", Constants.CHANGE, actual, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //Adjusted Demand
            if (selection.isVarAdjDemand()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("AdjDemandValue", Constants.VALUE, actual, NumericConstants.TWENTY_SEVEN, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("AdjDemandVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_SEVEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("AdjDemandPer", Constants.CHANGE, actual, NumericConstants.TWENTY_SEVEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Inventory Withdraw Sales
            if (selection.isVarInvSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithValue", Constants.VALUE, actual, NumericConstants.NINETEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithVariance", Constants.VARIANCE, actual, NumericConstants.NINETEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("InvWithPer", Constants.CHANGE, actual, NumericConstants.NINETEEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Inventory Withdraw Details
            if (selection.isVarIwDetails()) {
                if (selection.isColValue()) {
                    calculateForTotal("InvWithDetailsValue", Constants.VALUE, actual, NumericConstants.TWENTY_NINE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("InvWithDetailsVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_NINE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("InvWithDetailsPer", Constants.CHANGE, actual, NumericConstants.TWENTY_NINE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //% of Ex-Factory
            if (selection.isVarPerExFacSales()) {
                if (selection.isColValue()) {
                    calculateForTotal("PerExFacValue", Constants.VALUE, actual, NumericConstants.NINE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerExFacVariance", Constants.VARIANCE, actual, NumericConstants.NINE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerExFacPer", Constants.CHANGE, actual, NumericConstants.NINE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of Ex-Factory Cust
            if (selection.isVarPerExFacCustomer()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerCustExFacValue", Constants.VALUE, actual, NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerCustExFacPer", Constants.CHANGE, actual, NumericConstants.THIRTY_ONE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //% of Demand
            if (selection.isVarPerDemandSales()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerDemandSalesValue", Constants.VALUE, actual, NumericConstants.TWENTY, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerDemandSalesPer", Constants.CHANGE, actual, NumericConstants.TWENTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //% of Adjusted Demand
            if (selection.isVarPerAdjDemand()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerAdjDemandValue", Constants.VALUE, actual, NumericConstants.THIRTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, actual, NumericConstants.THIRTY, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //% of inventory Withdraw Sales
            if (selection.isVarPerInvSales()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithValue", Constants.VALUE, actual, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithPer", Constants.CHANGE, actual, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }

            //% of inventory Withdraw details
            if (selection.isVarPerIwDetails()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithDetailsValue", Constants.VALUE, actual, NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithDetailsVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithDetailsPer", Constants.CHANGE, actual, NumericConstants.THIRTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Contract Sales @ WAC
            if (selection.isVarContractsales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACValue", Constants.VALUE, actual, NumericConstants.FIVE, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, actual, NumericConstants.FIVE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, actual, NumericConstants.FIVE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }
            //Contract Units
            if (selection.isVarContractUnits()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("ContractUnitsValue", Constants.VALUE, actual, NumericConstants.SIX, frequencyBasedDTO, selection, new Object[]{UNIT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("ContractUnitsVar", Constants.VARIANCE, actual, NumericConstants.SIX, frequencyBasedDTO, selection, new Object[]{UNIT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("ContractUnitsPer", Constants.CHANGE, actual, NumericConstants.SIX, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            //Discount $
            if (selection.isVarDisAmount()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountValue", Constants.VALUE, actual, NumericConstants.SEVEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountVar", Constants.VARIANCE, actual, NumericConstants.SEVEN, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("DiscountAmountPer", Constants.CHANGE, actual, NumericConstants.SEVEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //Discount %
            if (selection.isVarDisRate()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountSalesValue", Constants.VALUE, actual, NumericConstants.EIGHT, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountSalesVar", Constants.VARIANCE, actual, NumericConstants.EIGHT, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountSalesPer", Constants.CHANGE, actual, NumericConstants.EIGHT, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }
            //RPU
            if (selection.isVarRPU()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("RPUValue", Constants.VALUE, actual, NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("RPUVariance", Constants.VARIANCE, actual, NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("RPUPer", Constants.CHANGE, actual, NumericConstants.TWENTY_TWO, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //DiscountPerExFactory
            if (selection.isDiscountPerExFactory()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountPerExFactoryValue", Constants.VALUE, actual, NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountPerExFactoryVar", Constants.VARIANCE, actual, NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountPerExFactoryPer", Constants.CHANGE, actual, NumericConstants.THIRTY_FOUR, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //Net Sales 
            if (selection.isVarNetSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesValue", Constants.VALUE, actual, indexForTotal + NumericConstants.SEVENTEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesVariance", Constants.VARIANCE, actual, indexForTotal + NumericConstants.SEVENTEEN, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("NetSalesPer", Constants.CHANGE, actual, indexForTotal + NumericConstants.SEVENTEEN, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //NetSales%ExFactory 
            if (selection.isNetSalesExFactory()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("NetSalesExFactoryValue", Constants.VALUE, actual, NumericConstants.THIRTY_THREE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColVariance()) {
                    calculateForTotal("NetSalesExFactoryVariance", Constants.VARIANCE, actual, NumericConstants.THIRTY_THREE, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("NetSalesExFactoryPer", Constants.CHANGE, actual, NumericConstants.THIRTY_THREE, frequencyBasedDTO, selection,new Object[]{ RATE_PER,rawList,proj});
                }
            }
            String hierarchyNo = String.valueOf(obj[1]);
            String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
            Map<String, List> relationshipLevelDetailsMap = selection.isIsCustomHierarchy()? selection.getSessionDTO().getCustomDescription() :selection.getSessionDTO().getHierarchyLevelDetails();
            List productList = relationshipLevelDetailsMap.get(hierarchy.trim());
            if (!isTotal && !productList.isEmpty() && P.equals(String.valueOf(productList.get(4)))) {
                /**
                 * Net Ex-Factory Sales
                 */
                if (selection.isNetExFactorySales()) {
                    if (selection.isColValue()) {
                        selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VALUE, Constants.VALUE, actual, NumericConstants.THIRTY_FIVE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                    }
                    if (selection.isColVariance()) {
                        selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VARIANCE, Constants.VARIANCE, actual, NumericConstants.THIRTY_FIVE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                    }
                    if (selection.isColPercentage()) {
                        selection.setConversionNeeded(false);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constants.CHANGE, actual, NumericConstants.THIRTY_FIVE, frequencyBasedDTO, selection, new Object[]{RATE,rawList,proj});
                    }
                }
                /**
                 * Net Ex-Factory Sales as % of Ex-Factory Sales
                 */
                if (selection.isNetExFactorySalesPerExFactory()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constants.VALUE, actual, NumericConstants.THIRTY_SIX, frequencyBasedDTO, selection,new Object[]{ RATE,rawList,proj});
                    }
                    if (selection.isColVariance()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constants.VARIANCE, actual, NumericConstants.THIRTY_SIX, frequencyBasedDTO, selection, new Object[]{RATE,rawList,proj});
                    }
                    if (selection.isColPercentage()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constants.CHANGE, actual, NumericConstants.THIRTY_SIX, frequencyBasedDTO, selection, new Object[]{RATE,rawList,proj});
                    }
                }
            }
            //COGS
            if (selection.isVarCOGC()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("COGCValue", Constants.VALUE, actual, indexForTotal + NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection,new Object[]{ AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("COGCVariance", Constants.VARIANCE, actual, indexForTotal + NumericConstants.TWENTY_FIVE, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("COGCPer", Constants.CHANGE, actual, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }
            //Net Profit
            if (selection.isVarNetProfit()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitValue", Constants.VALUE, actual, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitVariance", Constants.VARIANCE, actual, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, new Object[]{AMOUNT,rawList,proj});
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("NetProfitPer", Constants.CHANGE, actual, indexForTotal + NumericConstants.TWENTY_SIX, frequencyBasedDTO, selection, new Object[]{RATE_PER,rawList,proj});
                }
            }

            String commonColumn = StringUtils.EMPTY;
            if (frequencyDivision == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (frequencyDivision == NumericConstants.TWO) {
                commonColumn = "S" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (frequencyDivision == 1) {
                commonColumn = String.valueOf(obj[NumericConstants.THREE]);
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[NumericConstants.THREE];
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

    public static final String SALES = "SALES";

    public void discountCustomize() {

        List<ProjectionVarianceDTO> discountDollarValuelist;
        List<ProjectionVarianceDTO> discountDollarVariancelist;
        List<ProjectionVarianceDTO> discountDollarPercentlist;

        List<ProjectionVarianceDTO> discountperValuelist;
        List<ProjectionVarianceDTO> discountperVariancelist;
        List<ProjectionVarianceDTO> discountperPercentlist;

        List<ProjectionVarianceDTO> rpuValueList;
        List<ProjectionVarianceDTO> rpuVarianceList;
        List<ProjectionVarianceDTO> rpuPercentList;

        List<ProjectionVarianceDTO> discountperExfacValuelist;
        List<ProjectionVarianceDTO> discountperExfacVariancelist;
        List<ProjectionVarianceDTO> discountperExfacPercentlist;


        discountDollarValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.THREE, BooleanConstant.getFalseFlag(), Constants.VALUE, BooleanConstant.getTrueFlag());
        discountDollarVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.THREE, BooleanConstant.getFalseFlag(), Constants.VARIANCE, BooleanConstant.getTrueFlag());
        discountDollarPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.THREE, BooleanConstant.getTrueFlag(), Constants.CHANGE, BooleanConstant.getFalseFlag());

        discountperValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOUR, BooleanConstant.getTrueFlag(), Constants.VALUE, BooleanConstant.getFalseFlag());
        discountperVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOUR, BooleanConstant.getTrueFlag(), Constants.VARIANCE, BooleanConstant.getFalseFlag());
        discountperPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOUR, BooleanConstant.getTrueFlag(), Constants.CHANGE, BooleanConstant.getFalseFlag());

        rpuValueList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.TEN, BooleanConstant.getFalseFlag(), Constants.VALUE, BooleanConstant.getFalseFlag());
        rpuVarianceList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.TEN, BooleanConstant.getFalseFlag(), VARIANCE.getConstant(), BooleanConstant.getFalseFlag());
        rpuPercentList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.TEN, BooleanConstant.getTrueFlag(), Constants.CHANGE, BooleanConstant.getFalseFlag());

        discountperExfacValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.ELEVEN, BooleanConstant.getTrueFlag(), Constants.VALUE, BooleanConstant.getFalseFlag());
        discountperExfacVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.ELEVEN, BooleanConstant.getTrueFlag(), Constants.VARIANCE, BooleanConstant.getFalseFlag());
        discountperExfacPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.ELEVEN, BooleanConstant.getTrueFlag(), Constants.CHANGE, BooleanConstant.getFalseFlag());

        discountMap.put("discountDollar", discountDollarValuelist);
        discountMap.put("discountDollarVariance", discountDollarVariancelist);
        discountMap.put("discountDollarPercent", discountDollarPercentlist);

        discountMap.put("discountPervalue", discountperValuelist);
        discountMap.put("discountPervariance", discountperVariancelist);
        discountMap.put("discountPerPercent", discountperPercentlist);

        discountMap.put("rpuValue", rpuValueList);
        discountMap.put("rpuVariance", rpuVarianceList);
        discountMap.put("rpuPercent", rpuPercentList);

        discountMap.put("discountPerExfacvalue", discountperExfacValuelist);
        discountMap.put("discountPerExfacvariance", discountperExfacVariancelist);
        discountMap.put("discountPerExfacPercent", discountperExfacPercentlist);

    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    void getTotalDiscount(PVSelectionDTO projSelDTO) {
        pivotDiscountList.clear();
        List<String> projectionIdList = new ArrayList<>();
        pivotDiscountList = new ArrayList<>();
        String frequency = checkFrequency(selection.getFrequency());
        projectionIdList.add(String.valueOf(projSelDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.collectionToString(projectionIdList, false);
        String discountLevelName = !projSelDTO.getDeductionLevelValues().isEmpty() ? projSelDTO.getDeductionLevelValues() : projSelDTO.getDiscountLevel();
        discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM_CATEGORY) ? PROGRAM_TYPESMALL : discountLevelName;
        discountLevelName = discountLevelName.equalsIgnoreCase(PROGRAM) ? SCHEDULE_ID : discountLevelName;
        String viewName=projSelDTO.getView().equalsIgnoreCase(CUSTOM_CONSTANT)?String.valueOf(D_INDICATOR):projSelDTO.getView();
        Object[] orderedArg = {projectionId, frequency, null, null,discountLevelName,
            ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(), null , 
                      selection.getSessionDTO().getCustomViewMasterSid()
                      ,selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getUserId(),viewName,selection.getSessionDTO().getLevelHierarchyNo() };
      
        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArg);
        pivotDiscountList.addAll(discountsList);

    }

    private Map<String, String> getGroupCustomViewNM() {
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getCustomDescription();
        Map<String, String> customViewMap = new HashMap<>();
        Map<Object, Object> dataMap = new HashMap<>();
        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            String key = entry.getKey();
            dataMap.clear();
            dataMap.put(DISPLAY_FORMAT, selection.getDisplayFormat());
            dataMap.put(EXCEL_FLAG, Boolean.TRUE);
            String value = CommonUtils.getDisplayFormattedName(key, entry.getValue().get(4).toString(), relationshipLevelDetailsMap, selection.getSessionDTO(), dataMap);
            customViewMap.put(key, value);
        }
        return customViewMap;
    }

    ProjectionVarianceDTO setDataObjects(ProjectionVarianceDTO dto, ProjectionVarianceDTO parentDto, PVSelectionDTO pvsdto) {
        dto.setLevelNo(parentDto.getLevelNo());
        dto.setTreeLevelNo(parentDto.getTreeLevelNo());
        dto.setParentNode(parentDto.getParentNode());
        dto.setLevelValue(parentDto.getGroup());
        dto.setHierarchyIndicator(parentDto.getHierarchyIndicator());
        dto.setHierarchyNo(parentDto.getHierarchyNo());
        if (dto.getHierarchyIndicator().equals(C)) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(P)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
        }
        return dto;
    }

    public List<ProjectionVarianceDTO> getCustomisedDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer, String indicator, boolean isConversionNeeded) {
        LOGGER.debug("Inside getCustomisedProjectionResultsTotalDiscount");

        String lastValue = StringUtils.EMPTY;
        projSelDTO.setConversionNeeded(isConversionNeeded);
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int vFrequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> vPriorList = new ArrayList<>(projSelDTO.getProjIdList());
        
        Map<Object, List<Object[]>> groupedResult = dataList.stream().map(obj -> (Object[]) obj)
                        .collect(Collectors.groupingBy(x -> {
                            return new ArrayList<>(Arrays.asList( x[2], x[3]));
         }));
        int i=0;
        for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
             List<Object[]> list = entry.getValue();
             final Object[] obj = list.get(0);
            final Object[] proj;
            final Object[] actual;
            if (list.size() > 1) {
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = list.get(1);
                } else {
                    actual = list.get(1);
                    proj = list.get(0);
                }
            } else {
                Object[] emptyArray = Collections.nCopies(obj.length, 0).toArray(new Integer[0]);
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = emptyArray;
                } else {
                    actual = emptyArray;
                    proj = list.get(0);
                }
            }
           
                if (obj[obj.length - 2] != null && !StringUtils.EMPTY.equals(lastValue) && !"null".equals(lastValue) && !lastValue.equals(String.valueOf(obj[obj.length - 2]))) {
                    pvDTO.setGroup(lastValue);
                    pvDTO.setDfLevelNumber(lastValue);
                    pvDTO.setDfLevelName(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[obj.length - 2]);
                pvDTO.setGroup(lastValue);
                pvDTO.setDfLevelNumber(lastValue);
                pvDTO.setDfLevelName(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (vFrequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "Q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (vFrequencyDivision == NumericConstants.TWO) {
                    commonColumn = "S" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (vFrequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                } else if (vFrequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.TWO])) - 1);
                    commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[1];
                }
                PVCommonLogic.customizePeriodDiscountV2(commonColumn, indicator, projSelDTO, pvDTO, isPer ? RATE : AMOUNT, index, actual,proj, isPer);
                for (int j = 0; j < vPriorList.size(); j++) {
                    PVCommonLogic.getPriorCommonCustomizationV2(indicator, projSelDTO, list, pvDTO, commonColumn, index, j, isPer, isPer ? RATE : AMOUNT,true);
                }
                if (i++ == groupedResult.entrySet().size() - 1) {
                    resultDto.add(pvDTO);
                }
            
        }
                
        
        LOGGER.debug("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = > {}", resultDto.size());
        return resultDto;
    }

    public List<ProjectionVarianceDTO> getCustPeriodVariancDetails(List<ProjectionVarianceDTO> pvList, final PVSelectionDTO pvsdto, final Object[] obj,Object[] actual,Object[] proj) {

        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<>();
        ProjectionVarianceDTO exFacValue1 = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
        //Group Column projSelDTO
        String groupName;
        String hierarchyNo = String.valueOf(obj[1]);
        String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
        if (isCustomView) {
            groupName = customViewRelationshipHier.get(hierarchy.trim() == null ? StringUtils.EMPTY : hierarchy.trim());
            groupName = groupName == null ? StringUtils.EMPTY : groupName;
            dto.setHierarchyNo(obj[NumericConstants.TWO].toString());
            dto.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
        } else {
            Map<Object, Object> dataMap = new HashMap<>();
            dataMap.put(DISPLAY_FORMAT, selection.getDisplayFormat());
            dataMap.put(EXCEL_FLAG, Boolean.TRUE);
            
            groupName = CommonUtils.getDisplayFormattedName(hierarchy.trim(), selection.getHierarchyIndicator(),
                            selection.getSessionDTO().getHierarchyLevelDetails(), selection.getSessionDTO(), dataMap);
            dto.setGroup(groupName);
        }
        if (groupName.contains("`$")) {
                String[] tempArr = groupName.split(SEPERATOR_FORMAT);
                dto.addStringProperties(DF_LEVEL_NUMBER, tempArr[0]);
                dto.addStringProperties(DF_LEVEL_NAME, tempArr[1]);
            } else if (selection.getDisplayFormat().length > 0) {
                int index = (int) selection.getDisplayFormat()[0];
                if (index == 0) {
                    dto.addStringProperties(DF_LEVEL_NUMBER, groupName);
                    
                } else {
                    dto.addStringProperties(DF_LEVEL_NAME, groupName);
                }
            } else {
                dto.addStringProperties(DF_LEVEL_NUMBER, groupName);
                dto.addStringProperties(DF_LEVEL_NAME, groupName);
            }
        dto.setGroup(groupName);
        pvList.add(dto);

        ProjectionVarianceDTO vDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vDemandVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO invWithSummaryValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithSummaryVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithSummaryPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vPerExFactoryProdVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO perExFacProductVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerExFactoryProd = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vPerExFactoryCustVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerExFactoryCustVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerExFactoryCustPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vPerDemandVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vPerAdjDemandVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerAdjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerAdjDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vPerInvWithSummaryVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerInvWithSummaryVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerInvWithSummaryPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vPerIwDetialsVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerIwDetialsVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vPerIwDetialsPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO custExFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vAdjDemandVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vAdjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vAdjDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempwDetialsValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vInvWithDetialsVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vInvWithDetialsPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempDiscountDollarValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempDiscountDollarVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempDiscountDollarPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempDiscountPerValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempDiscountPerVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempDiscountPerPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempContractSalesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempContractSalesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempContractSalesPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempContractUnitValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempContractUnitVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempContractUnitPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempRpuValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempRpuVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempRpuPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempNetSalesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tesmpNetSalesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempNetSalesPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempCogcValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempCogcVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempCogcPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO tempNetProfitValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempNetProfitVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO tempNetProfitPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO discountPerExfacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerExfacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerExfacPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO netSalesExfacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netSalesExfacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netSalesExfacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO netExFactorySalesVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netExFactorySalesVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netExFactorySalesPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO netExFactorySalesPerExFactoryVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netExFactorySalesPerExFactoryVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO netExFactorySalesPerExFactoryPercent = new ProjectionVarianceDTO();

        //Ex factory product
        if (pvsdto.isVarExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                exFacValue1 = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, actual, NumericConstants.TWO, pvsdto, AMOUNT, exFacValue1, true,proj);
                pvList.add(exFacValue1);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                exFacVariance = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWO, pvsdto, AMOUNT, exFacVariance, true,proj);
                pvList.add(exFacVariance);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                exFacPercent = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWO, pvsdto, RATE, exFacPercent, true,proj);
                pvList.add(exFacPercent);

            }
        }

        // Customer ExFac Sales for detail start 
        if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                custExFacValue = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_EIGHT, pvsdto, AMOUNT, custExFacValue, true,proj);
                pvList.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                custExFacVar = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_EIGHT, pvsdto, AMOUNT, custExFacVar, true,proj);
                pvList.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                custExFacPer = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_EIGHT, pvsdto, RATE, custExFacPer, true,proj);
                pvList.add(custExFacPer);
            }
        }
//            //Demand - start
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                vDemandValue = getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.VALUE, obj, actual, NumericConstants.EIGHTEEN, pvsdto, AMOUNT, vDemandValue, true,proj);
                pvList.add(vDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vDemandVariance = getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.VARIANCE, obj, actual, NumericConstants.EIGHTEEN, pvsdto, AMOUNT, vDemandVariance, true,proj);
                pvList.add(vDemandVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                vDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.CHANGE, obj, actual, NumericConstants.EIGHTEEN, pvsdto, RATE, vDemandPer, true,proj);
                pvList.add(vDemandPer);
            }
        }

        // Adjusted Demand  start 
        if (pvsdto.isVarAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vAdjDemandVal = getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_SEVEN, pvsdto, AMOUNT, vAdjDemandVal, true,proj);
                pvList.add(vAdjDemandVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vAdjDemandVar = getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_SEVEN, pvsdto, AMOUNT, vAdjDemandVar, true,proj);
                pvList.add(vAdjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vAdjDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_SEVEN, pvsdto, RATE, vAdjDemandPer, true,proj);
                pvList.add(vAdjDemandPer);
            }
        }
//
//            //Inv with drawal Summary
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                invWithSummaryValue = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VALUE, obj, actual, NumericConstants.NINETEEN, pvsdto, AMOUNT, invWithSummaryValue, true,proj);
                pvList.add(invWithSummaryValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                invWithSummaryVar = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VARIANCE, obj, actual, NumericConstants.NINETEEN, pvsdto, AMOUNT, invWithSummaryVar, true,proj);
                pvList.add(invWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithSummaryPer = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.CHANGE, obj, actual, NumericConstants.NINETEEN, pvsdto, RATE, invWithSummaryPer, true,proj);
                pvList.add(invWithSummaryPer);
            }
        }

        // Inventary withdrawal Details for detail start 
        if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempwDetialsValue = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_NINE, pvsdto, AMOUNT, tempwDetialsValue, true,proj);
                pvList.add(tempwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vInvWithDetialsVar = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_NINE, pvsdto, AMOUNT, vInvWithDetialsVar, true,proj);
                pvList.add(vInvWithDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                vInvWithDetialsPer = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_NINE, pvsdto, RATE, vInvWithDetialsPer, true,proj);
                pvList.add(vInvWithDetialsPer);
            }
        }
//
//       //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerExFactoryProdVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, actual, NumericConstants.NINE, pvsdto, RATE, vPerExFactoryProdVal, true,proj);
                pvList.add(vPerExFactoryProdVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                perExFacProductVariance = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VARIANCE, obj, actual, NumericConstants.NINE, pvsdto, RATE, perExFacProductVariance, true,proj);
                pvList.add(perExFacProductVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerExFactoryProd = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.CHANGE, obj, actual, NumericConstants.NINE, pvsdto, RATE, vPerExFactoryProd, true,proj);
                pvList.add(vPerExFactoryProd);
            }
        }
//        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerExFactoryCustVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, actual, NumericConstants.THIRTY_ONE, pvsdto, RATE, vPerExFactoryCustVal, true,proj);
                pvList.add(vPerExFactoryCustVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerExFactoryCustVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VARIANCE, obj, actual, NumericConstants.THIRTY_ONE, pvsdto, RATE, vPerExFactoryCustVar, true,proj);
                pvList.add(vPerExFactoryCustVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerExFactoryCustPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.CHANGE, obj, actual, NumericConstants.THIRTY_ONE, pvsdto, RATE, vPerExFactoryCustPer, true,proj);
                pvList.add(vPerExFactoryCustPer);
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerDemandVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY, pvsdto, RATE, vPerDemandVal, true,proj);
                pvList.add(vPerDemandVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerDemandVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY, pvsdto, RATE, vPerDemandVar, true,proj);
                pvList.add(vPerDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY, pvsdto, RATE, vPerDemandPer, true,proj);
                pvList.add(vPerDemandPer);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerAdjDemandVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, actual, NumericConstants.THIRTY, pvsdto, RATE, vPerAdjDemandVal, true,proj);
                pvList.add(vPerAdjDemandVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerAdjDemandVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VARIANCE, obj, actual, NumericConstants.THIRTY, pvsdto, RATE, vPerAdjDemandVar, true,proj);
                pvList.add(vPerAdjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerAdjDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.CHANGE, obj, actual, NumericConstants.THIRTY, pvsdto, RATE, vPerAdjDemandPer, true,proj);
                pvList.add(vPerAdjDemandPer);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerInvWithSummaryVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_ONE, pvsdto, RATE, vPerInvWithSummaryVal, true,proj);
                pvList.add(vPerInvWithSummaryVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerInvWithSummaryVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_ONE, pvsdto, RATE, vPerInvWithSummaryVar, true,proj);
                pvList.add(vPerInvWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerInvWithSummaryPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_ONE, pvsdto, RATE, vPerInvWithSummaryPer, true,proj);
                pvList.add(vPerInvWithSummaryPer);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerIwDetialsVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VALUE, obj, actual, NumericConstants.THIRTY_TWO, pvsdto, RATE, vPerIwDetialsVal, true,proj);
                pvList.add(vPerIwDetialsVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerIwDetialsVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VARIANCE, obj, actual, NumericConstants.THIRTY_TWO, pvsdto, RATE, vPerIwDetialsVar, true,proj);
                pvList.add(vPerIwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerIwDetialsPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.CHANGE, obj, actual, NumericConstants.THIRTY_TWO, pvsdto, RATE, vPerIwDetialsPer, true,proj);
                pvList.add(vPerIwDetialsPer);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempContractSalesValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VALUE, obj, actual, NumericConstants.FIVE, pvsdto, AMOUNT, tempContractSalesValue, true,proj);
                pvList.add(tempContractSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempContractSalesVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VARIANCE, obj, actual, NumericConstants.FIVE, pvsdto, AMOUNT, tempContractSalesVar, true,proj);
                pvList.add(tempContractSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempContractSalesPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.CHANGE, obj, actual, NumericConstants.FIVE, pvsdto, RATE, tempContractSalesPer, true,proj);
                pvList.add(tempContractSalesPer);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {

            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                tempContractUnitValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VALUE, obj, actual, NumericConstants.SIX, pvsdto, AMOUNT_UNITS, tempContractUnitValue, true,proj);
                pvList.add(tempContractUnitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempContractUnitVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VARIANCE, obj, actual, NumericConstants.SIX, pvsdto, AMOUNT_UNITS, tempContractUnitVar, true,proj);
                pvList.add(tempContractUnitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempContractUnitPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.CHANGE, obj, actual, NumericConstants.SIX, pvsdto, RATE, tempContractUnitPer, true,proj);
                pvList.add(tempContractUnitPer);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempDiscountDollarValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.VALUE, obj, actual, NumericConstants.SEVEN, pvsdto, AMOUNT, tempDiscountDollarValue, true,proj);
                pvList.add(tempDiscountDollarValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempDiscountDollarVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.VARIANCE, obj, actual, NumericConstants.SEVEN, pvsdto, AMOUNT, tempDiscountDollarVar, true,proj);
                pvList.add(tempDiscountDollarVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempDiscountDollarPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.CHANGE, obj, actual, NumericConstants.SEVEN, pvsdto, RATE, tempDiscountDollarPer, true,proj);
                pvList.add(tempDiscountDollarPer);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                tempDiscountPerValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.VALUE, obj, actual, NumericConstants.EIGHT, pvsdto, RATE, tempDiscountPerValue, true,proj);
                pvList.add(tempDiscountPerValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempDiscountPerVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.VARIANCE, obj, actual, NumericConstants.EIGHT, pvsdto, RATE, tempDiscountPerVar, true,proj);
                pvList.add(tempDiscountPerVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempDiscountPerPercent = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.CHANGE, obj, actual, NumericConstants.EIGHT, pvsdto, RATE, tempDiscountPerPercent, true,proj);
                pvList.add(tempDiscountPerPercent);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                tempRpuValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_FOUR, pvsdto, AMOUNT, tempRpuValue, true,proj);
                pvList.add(tempRpuValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempRpuVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_FOUR, pvsdto, AMOUNT, tempRpuVar, true,proj);
                pvList.add(tempRpuVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempRpuPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_FOUR, pvsdto, RATE, tempRpuPer, true,proj);
                pvList.add(tempRpuPer);
            }
        }
        //DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerExfacValue = getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.VALUE, obj, actual, NumericConstants.THIRTY_FOUR, pvsdto, RATE, discountPerExfacValue, true,proj);
                pvList.add(discountPerExfacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                discountPerExfacVar = getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.VARIANCE, obj, actual, NumericConstants.THIRTY_FOUR, pvsdto, RATE, discountPerExfacVar, true,proj);
                pvList.add(discountPerExfacVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerExfacPercent = getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.CHANGE, obj, actual, NumericConstants.THIRTY_FOUR, pvsdto, RATE, discountPerExfacPercent, true,proj);
                pvList.add(discountPerExfacPercent);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempNetSalesValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VALUE, obj, actual, NumericConstants.SEVENTEEN, pvsdto, AMOUNT, tempNetSalesValue, true,proj);
                pvList.add(tempNetSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tesmpNetSalesVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VARIANCE, obj, actual, NumericConstants.SEVENTEEN, pvsdto, AMOUNT, tesmpNetSalesVar, true,proj);
                pvList.add(tesmpNetSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempNetSalesPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.CHANGE, obj, actual, NumericConstants.SEVENTEEN, pvsdto, RATE, tempNetSalesPer, true,proj);
                pvList.add(tempNetSalesPer);
            }
        }
        //NetSales%ExFactory
        if (pvsdto.isNetSalesExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netSalesExfacValue = getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.VALUE, obj, actual, NumericConstants.THIRTY_SIX, pvsdto, RATE, netSalesExfacValue, true,proj);
                pvList.add(netSalesExfacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                netSalesExfacVar = getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.VARIANCE, obj, actual, NumericConstants.THIRTY_SIX, pvsdto, RATE, netSalesExfacVar, true,proj);
                pvList.add(netSalesExfacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                netSalesExfacPer = getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.CHANGE, obj, actual, NumericConstants.THIRTY_SIX, pvsdto, RATE, netSalesExfacPer, true,proj);
                pvList.add(netSalesExfacPer);
            }
        }

        Map<String, List> relationshipLevelDetailsMap = selection.isIsCustomHierarchy() ? selection.getSessionDTO().getCustomDescription() : selection.getSessionDTO().getHierarchyLevelDetails();
        List productList = relationshipLevelDetailsMap.get(hierarchy.trim());
        if (!isTotal && productList !=null && !productList.isEmpty() && P.equals(String.valueOf(productList.get(4)))) {
            /**
             * Net Ex-Factory Sales
             */
            if (pvsdto.isNetExFactorySales()) {
                if (pvsdto.isColValue()) {
                    pvsdto.setConversionNeeded(true);
                    pvsdto.setVarIndicator(Constants.VALUE);
                    netExFactorySalesVal = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.VALUE, obj, actual, NumericConstants.THIRTY_FIVE, pvsdto, AMOUNT, netExFactorySalesVal, true,proj);
                    pvList.add(netExFactorySalesVal);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setConversionNeeded(true);
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                    netExFactorySalesVariance = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.VARIANCE, obj, actual, NumericConstants.THIRTY_FIVE, pvsdto, AMOUNT, netExFactorySalesVariance, true,proj);
                    pvList.add(netExFactorySalesVariance);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setConversionNeeded(false);
                    pvsdto.setVarIndicator(Constants.CHANGE);
                    netExFactorySalesPercent = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.CHANGE, obj, actual, NumericConstants.THIRTY_FIVE, pvsdto, RATE, netExFactorySalesPercent, true,proj);
                    pvList.add(netExFactorySalesPercent);
                }
            }
            /**
             * Net Ex-Factory Sales as % of Ex-Factory Sales
             */
            if (pvsdto.isNetExFactorySalesPerExFactory()) {
                pvsdto.setConversionNeeded(false);
                if (pvsdto.isColValue()) {
                    pvsdto.setVarIndicator(Constants.VALUE);
                    netExFactorySalesPerExFactoryVal = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.VALUE, obj, actual, NumericConstants.THIRTY_FOUR, pvsdto, RATE, netExFactorySalesPerExFactoryVal, true,proj);
                    pvList.add(netExFactorySalesPerExFactoryVal);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                    netExFactorySalesPerExFactoryVariance = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.VARIANCE, obj, actual, NumericConstants.THIRTY_FOUR, pvsdto, RATE, netExFactorySalesPerExFactoryVariance, true,proj);
                    pvList.add(netExFactorySalesPerExFactoryVariance);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setVarIndicator(Constants.CHANGE);
                    netExFactorySalesPerExFactoryPercent = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.CHANGE, obj, actual, NumericConstants.THIRTY_FOUR, pvsdto, RATE, netExFactorySalesPerExFactoryPercent, true,proj);
                    pvList.add(netExFactorySalesPerExFactoryPercent);
                }
            }
        }

        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempCogcValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_FIVE, pvsdto, AMOUNT, tempCogcValue, true,proj);
                pvList.add(tempCogcValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempCogcVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_FIVE, pvsdto, AMOUNT, tempCogcVar, true,proj);
                pvList.add(tempCogcVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempCogcPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_FIVE, pvsdto, RATE, tempCogcPer, true,proj);
                pvList.add(tempCogcPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempNetProfitValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VALUE, obj, actual, NumericConstants.TWENTY_SIX, pvsdto, AMOUNT, tempNetProfitValue, true,proj);
                pvList.add(tempNetProfitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempNetProfitVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VARIANCE, obj, actual, NumericConstants.TWENTY_SIX, pvsdto, AMOUNT, tempNetProfitVar, true,proj);
                pvList.add(tempNetProfitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempNetProfitPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.CHANGE, obj, actual, NumericConstants.TWENTY_SIX, pvsdto, RATE, tempNetProfitPer, true,proj);
                pvList.add(tempNetProfitPer);
            }
        }
        return projectionVarianceDTO;
    }

    public ProjectionVarianceDTO getCommonCustomizedDTODetails(String groupName, String varibaleCat, Object[] obj, Object[] act, final int totalListPostion, PVSelectionDTO pvsdto, final DecimalFormat format, ProjectionVarianceDTO pvDTO, boolean addFlag,Object[] proj) {
        int vFrequencyDiv = pvsdto.getFrequencyDivision();
         
        if (addFlag) {
            if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
               String groupValue = groupName.concat(Constants.VALUE);
                pvDTO.addStringProperties(DF_LEVEL_NUMBER, groupValue);
                pvDTO.addStringProperties(DF_LEVEL_NAME, groupValue);
                pvDTO.setGroup(groupValue);
            } else if (pvsdto.getVarIndicator().equals(Constants.VARIANCE)) {
                String groupVaiance = groupName.concat(Constants.VARIANCE);
                pvDTO.addStringProperties(DF_LEVEL_NUMBER, groupVaiance);
                pvDTO.addStringProperties(DF_LEVEL_NAME, groupVaiance);
                pvDTO.setGroup(groupVaiance);
            } else if (pvsdto.getVarIndicator().equals(Constants.CHANGE)) {
                String groupChange = groupName.concat(Constants.CHANGE);
                pvDTO.addStringProperties(DF_LEVEL_NUMBER, groupChange);
                pvDTO.addStringProperties(DF_LEVEL_NAME, groupChange);
                pvDTO.setGroup(groupChange);
            }
        }
        List<Integer> vPriorList = new ArrayList<>(pvsdto.getProjIdList());
        if (obj != null) {
            String commonColumn = StringUtils.EMPTY;
            if (vFrequencyDiv == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (vFrequencyDiv == NumericConstants.TWO) {
                commonColumn = "S" + obj[NumericConstants.FOUR] + StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (vFrequencyDiv == 1) {
                commonColumn = StringUtils.EMPTY + obj[NumericConstants.THREE];
            } else if (vFrequencyDiv == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR])) - 1);
                commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[NumericConstants.THREE];
            }
            PVCommonLogic.customizePeriodV2(commonColumn, varibaleCat, pvsdto, pvDTO, format, totalListPostion, act,proj, groupName.contains("%"),true);
            
            for (int j = 0; j < vPriorList.size(); j++) {
                List<Object[]> priorList=PROCRAWLIST_DETAIL.stream().filter(e->Integer.parseInt(String.valueOf(e[0]))!=pvsdto.getCurrentProjId()).collect(Collectors.toList());
                PVCommonLogic.getPriorCommonCustomizationV2(varibaleCat, pvsdto, priorList ,pvDTO, commonColumn, totalListPostion, j, groupName.contains("%"), format,true);
            }
        }
        return pvDTO;
    }

    public void updateCustPeriodVarianceDetails(List<ProjectionVarianceDTO> pvList, final PVSelectionDTO pvsdto, final Object[] obj,final Object[] act,final Object[] proj) {
        int listIndex = 1;

        //Ex fac product
        if (pvsdto.isVarExFacSales()) {
            if (selection.isColValue()) {
                pvsdto.setConversionNeeded(true);
                selection.setVarIndicator(Constants.VALUE);
            ProjectionVarianceDTO exFacValue = pvList.get(listIndex++);
            getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, act, NumericConstants.TWO, pvsdto, AMOUNT, exFacValue, true,proj);
            }
            if (selection.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                selection.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO exFacVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWO, pvsdto, AMOUNT, exFacVar, false,proj);
            }
            if (selection.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                selection.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO exFacPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.CHANGE, obj, act, NumericConstants.TWO, pvsdto, RATE, exFacPer, false,proj);
            }

        }
//           
//           // Customer ExFac Sales for detail start 
        if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO custExFacValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_EIGHT, pvsdto, AMOUNT, custExFacValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO custExFacVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_EIGHT, pvsdto, AMOUNT, custExFacVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO custExFacPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_EIGHT, pvsdto, RATE, custExFacPer, false,proj);
            }
        }

        //Demand - start
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO demandValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.VALUE, obj, act, NumericConstants.EIGHTEEN, pvsdto, AMOUNT, demandValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO demandVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.VARIANCE, obj, act, NumericConstants.EIGHTEEN, pvsdto, AMOUNT, demandVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO demandPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.CHANGE, obj, act, NumericConstants.EIGHTEEN, pvsdto, RATE, demandPer, false,proj);
            }
        }

        // Adjusted Demand  start 
        if (pvsdto.isVarAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO adjDamandVale = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_SEVEN, pvsdto, AMOUNT, adjDamandVale, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO adjDemandVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_SEVEN, pvsdto, AMOUNT, adjDemandVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO adjdemandPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_SEVEN, pvsdto, RATE, adjdemandPer, false,proj);
            }
        }

        //Inv with drawal summary
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO invWithValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VALUE, obj, act, NumericConstants.NINETEEN, pvsdto, AMOUNT, invWithValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO invWithVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VARIANCE, obj, act, NumericConstants.NINETEEN, pvsdto, AMOUNT, invWithVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO invWithPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.CHANGE, obj, act, NumericConstants.NINETEEN, pvsdto, RATE, invWithPer, false,proj);
            }
        }

        // Inventary withdrawal Details for detail start 
        if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO iwDetialsValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_NINE, pvsdto, AMOUNT, iwDetialsValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO iwDetialsVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_NINE, pvsdto, AMOUNT, iwDetialsVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO iwDetialsPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_NINE, pvsdto, RATE, iwDetialsPer, false,proj);
            }
        }
//            
//          //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO perExFacProductValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, act, NumericConstants.NINE, pvsdto, RATE, perExFacProductValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO perExFacProductVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VARIANCE, obj, act, NumericConstants.NINE, pvsdto, RATE, perExFacProductVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO perExFacProductPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.CHANGE, obj, act, NumericConstants.NINE, pvsdto, RATE, perExFacProductPer, false,proj);

            }
        }
//            
//            
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO perExFacCustValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, act, NumericConstants.THIRTY_ONE, pvsdto, RATE, perExFacCustValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO perExFacCustVariance = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VARIANCE, obj, act, NumericConstants.THIRTY_ONE, pvsdto, RATE, perExFacCustVariance, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO perExFacCustPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.CHANGE, obj, act, NumericConstants.THIRTY_ONE, pvsdto, RATE, perExFacCustPer, false,proj);

            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO perDemandValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY, pvsdto, RATE, perDemandValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO perDemandVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY, pvsdto, RATE, perDemandVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO perDemandPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY, pvsdto, RATE, perDemandPer, false,proj);

            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO perAdjDemandValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, act, NumericConstants.THIRTY, pvsdto, RATE, perAdjDemandValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO perAdjDemandVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VARIANCE, obj, act, NumericConstants.THIRTY, pvsdto, RATE, perAdjDemandVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO perAdjDemandPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.CHANGE, obj, act, NumericConstants.THIRTY, pvsdto, RATE, perAdjDemandPer, false,proj);

            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO perInvWithSummaryValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_ONE, pvsdto, RATE, perInvWithSummaryValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO perInvWithSummaryVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_ONE, pvsdto, RATE, perInvWithSummaryVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO perInvWithSummaryPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_ONE, pvsdto, RATE, perInvWithSummaryPer, false,proj);

            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO perIwDetialsValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VALUE, obj, act, NumericConstants.THIRTY_TWO, pvsdto, RATE, perIwDetialsValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO perIwDetialsVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VARIANCE, obj, act, NumericConstants.THIRTY_TWO, pvsdto, RATE, perIwDetialsVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO perIwDetialsPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.CHANGE, obj, act, NumericConstants.THIRTY_TWO, pvsdto, RATE, perIwDetialsPer, false,proj);

            }
        }

        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO contractSalesValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VALUE, obj, act, NumericConstants.FIVE, pvsdto, AMOUNT, contractSalesValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO contractSalesVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VARIANCE, obj, act, NumericConstants.FIVE, pvsdto, AMOUNT, contractSalesVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO contractSalesPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.CHANGE, obj, act, NumericConstants.FIVE, pvsdto, RATE, contractSalesPer, false,proj);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO contractUnitValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VALUE, obj, act, NumericConstants.SIX, pvsdto, AMOUNT_UNITS, contractUnitValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO contractUnitVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VARIANCE, obj, act, NumericConstants.SIX, pvsdto, AMOUNT_UNITS, contractUnitVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO contractUnitPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.CHANGE, obj, act, NumericConstants.SIX, pvsdto, RATE, contractUnitPer, false,proj);

            }
        }

//        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountDollarValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.VALUE, obj, act, NumericConstants.SEVEN, pvsdto, AMOUNT, discountDollarValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO discountDollarVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.VARIANCE, obj, act, NumericConstants.SEVEN, pvsdto, AMOUNT, discountDollarVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountDollarPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.CHANGE, obj, act, NumericConstants.SEVEN, pvsdto, RATE, discountDollarPer, false,proj);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountPerValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.VALUE, obj, act, NumericConstants.EIGHT, pvsdto, RATE, discountPerValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO discountPerVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.VARIANCE, obj, act, NumericConstants.EIGHT, pvsdto, RATE, discountPerVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountPerPercent = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.CHANGE, obj, act, NumericConstants.EIGHT, pvsdto, RATE, discountPerPercent, false,proj);

            }
        }

        // RPU
        if (pvsdto.isVarRPU()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO rpuValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_FOUR, pvsdto, AMOUNT, rpuValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO rpuVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_FOUR, pvsdto, AMOUNT, rpuVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO rpuPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_FOUR, pvsdto, RATE, rpuPer, false,proj);
            }
        }
        //DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO discountPerExFactoryValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.VALUE, obj, act, NumericConstants.THIRTY_FOUR, pvsdto, RATE, discountPerExFactoryValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO discountPerExFactoryVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.VARIANCE, obj, act, NumericConstants.THIRTY_FOUR, pvsdto, RATE, discountPerExFactoryVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO discountPerExFactoryPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.CHANGE, obj, act, NumericConstants.THIRTY_FOUR, pvsdto, RATE, discountPerExFactoryPer, false,proj);

            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netSalesValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VALUE, obj, act, NumericConstants.SEVENTEEN, pvsdto, AMOUNT, netSalesValue, false,proj);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO netSalesVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VARIANCE, obj, act, NumericConstants.SEVENTEEN, pvsdto, AMOUNT, netSalesVar, false,proj);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netSalesPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.CHANGE, obj, act, NumericConstants.SEVENTEEN, pvsdto, RATE, netSalesPer, false,proj);

            }
        }

        //NetSales%ExFactory 
        if (pvsdto.isNetSalesExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netExFactoryValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.VALUE, obj, act, NumericConstants.THIRTY_SIX, pvsdto, RATE, netExFactoryValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO netExFactoryVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.VARIANCE, obj, act, NumericConstants.THIRTY_SIX, pvsdto, RATE, netExFactoryVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netExFactoryPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.CHANGE, obj, act, NumericConstants.THIRTY_SIX, pvsdto, RATE, netExFactoryPer, false,proj);
            }
        }

        String hierarchyNo = String.valueOf(obj[1]);
        String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        List productList = relationshipLevelDetailsMap.get(hierarchy.trim());
        if (!isTotal && !productList.isEmpty() && P.equals(String.valueOf(productList.get(4)))) {
            /**
             * Net Ex-Factory Sales
             */
            if (pvsdto.isNetExFactorySales()) {
                if (pvsdto.isColValue()) {
                    pvsdto.setConversionNeeded(true);
                    pvsdto.setVarIndicator(Constants.VALUE);
                    ProjectionVarianceDTO netExFactorySalesValue = pvList.get(listIndex++);
                    getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.VALUE, obj, act, NumericConstants.THIRTY_FIVE, pvsdto, AMOUNT, netExFactorySalesValue, true,proj);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setConversionNeeded(true);
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                    ProjectionVarianceDTO netExFactorySalesVar = pvList.get(listIndex++);
                    getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.VARIANCE, obj, act, NumericConstants.THIRTY_FIVE, pvsdto, AMOUNT, netExFactorySalesVar, true,proj);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setConversionNeeded(false);
                    pvsdto.setVarIndicator(Constants.CHANGE);
                    ProjectionVarianceDTO netExFactorySalesPer = pvList.get(listIndex++);
                    getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.CHANGE, obj, act, NumericConstants.THIRTY_FIVE, pvsdto, RATE, netExFactorySalesPer, true,proj);
                }
            }
            /**
             * Net Ex-Factory Sales as % of Ex-Factory Sales
             */
            if (pvsdto.isNetExFactorySalesPerExFactory()) {
                pvsdto.setConversionNeeded(false);
                if (pvsdto.isColValue()) {
                    pvsdto.setVarIndicator(Constants.VALUE);
                    ProjectionVarianceDTO netExFactorySalesPerExFactoryValue = pvList.get(listIndex++);
                    getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.VALUE, obj, act, NumericConstants.THIRTY_FOUR, pvsdto, RATE, netExFactorySalesPerExFactoryValue, true,proj);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                    ProjectionVarianceDTO netExFactorySalesPerExFactoryVar = pvList.get(listIndex++);
                    getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.VARIANCE, obj, act, NumericConstants.THIRTY_FOUR, pvsdto, RATE, netExFactorySalesPerExFactoryVar, true,proj);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setVarIndicator(Constants.CHANGE);
                    ProjectionVarianceDTO netExFactorySalesPerExFactoryPer = pvList.get(listIndex++);
                    getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.CHANGE, obj, act, NumericConstants.THIRTY_FOUR, pvsdto, RATE, netExFactorySalesPerExFactoryPer, true,proj);
                }
            }
        }

        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO cogcValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_FIVE, pvsdto, AMOUNT, cogcValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO cogcVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_FIVE, pvsdto, AMOUNT, cogcVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO cogcPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_FIVE, pvsdto, RATE, cogcPer, false,proj);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                ProjectionVarianceDTO netProfitValue = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VALUE, obj, act, NumericConstants.TWENTY_SIX, pvsdto, AMOUNT, netProfitValue, false,proj);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                ProjectionVarianceDTO netProfitVar = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VARIANCE, obj, act, NumericConstants.TWENTY_SIX, pvsdto, AMOUNT, netProfitVar, false,proj);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                ProjectionVarianceDTO netProfitPer = pvList.get(listIndex++);
                getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.CHANGE, obj, act, NumericConstants.TWENTY_SIX, pvsdto, RATE, netProfitPer, false,proj);
                LOGGER.debug("End of Method = {}", listIndex);
            }
        }
    }

    private ProjectionVarianceDTO calculateDiscountPeriodV2(String varibaleCat, Object[] obj, int currentIndex, ProjectionVarianceDTO pvDTO,
            PVSelectionDTO selection, DecimalFormat format,boolean isPer,List<Object[]> list) {
        int vFrequencyDiv = selection.getFrequencyDivision();
        String commonColumn = StringUtils.EMPTY;
        pvDTO.setGroup(String.valueOf(obj[obj.length -2]));
        pvDTO.setDfLevelNumber(String.valueOf(obj[obj.length -1]));
        pvDTO.setDfLevelName(String.valueOf(obj[obj.length -1]));
        final Object[] proj;
            final Object[] actual;
            if (list.size() > 1) {
                
                if (String.valueOf(obj[obj.length - 1]).equalsIgnoreCase(ZERO)) {
                    actual = list.get(0);
                    proj = list.get(1);
                } else {
                    actual = list.get(1);
                    proj = list.get(0);
                }
            } else {
                Object[] emptyArray = Collections.nCopies(obj.length, 0).toArray(new Object[0]);
                if (String.valueOf(obj[obj.length - 1]).equals(ZERO)) {
                    actual = list.get(0);
                    proj = emptyArray;
                } else {
                    actual = emptyArray;
                    proj = list.get(0);
                }
            }
        switch (vFrequencyDiv) {
            case NumericConstants.FOUR:
                commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                break;
            case NumericConstants.TWO:
                commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
                break;
            case 1:
                commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
                break;
            case NumericConstants.TWELVE:
                String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])) - 1);
                commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[NumericConstants.TWO];
                break;
            default:
                break;
        }
        PVCommonLogic.customizePeriodDiscountV2(commonColumn, selection.getVarIndicator(), selection, pvDTO, isPer ? RATE : AMOUNT, currentIndex, actual,proj, isPer);
        List<Integer> vPriorList = selection.getProjIdList();
        for (int j = 0; j < vPriorList.size(); j++) {
            PVCommonLogic.getPriorCommonCustomizationV2(varibaleCat, selection, list, pvDTO, commonColumn, currentIndex, j, format.equals(RATE_PER), format,true);
        }
        return pvDTO;
    }

    private void calculateDiscount() {
        String oldDiscount = StringUtils.EMPTY;
        String oldHierarchyNo = StringUtils.EMPTY;
        String newDiscount;
        List<List<ProjectionVarianceDTO>> finaldiscountlist = new ArrayList<>();

        ProjectionVarianceDTO vDiscountDollarVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountDollarVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountDollarPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vDiscountPerVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO vDiscountPerPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO vRpuVal = new ProjectionVarianceDTO();
        ProjectionVarianceDTO rpuVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO rpuPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO discountPerExfacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerExfacVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO discountPerExfacPercent = new ProjectionVarianceDTO();

        List<ProjectionVarianceDTO> discountDollarvalueList = new ArrayList<>();
        List<ProjectionVarianceDTO> discountDollarVarList = new ArrayList<>();
        List<ProjectionVarianceDTO> discountDollarPerList = new ArrayList<>();

        List<ProjectionVarianceDTO> discountPerValueList = new ArrayList<>();
        List<ProjectionVarianceDTO> discountPerVarianceList = new ArrayList<>();
        List<ProjectionVarianceDTO> discountPerPercentList = new ArrayList<>();

        List<ProjectionVarianceDTO> rpuValueList = new ArrayList<>();
        List<ProjectionVarianceDTO> rpuVarianceList = new ArrayList<>();
        List<ProjectionVarianceDTO> rpuPercentList = new ArrayList<>();

        List<ProjectionVarianceDTO> discountPerExfacValueList = new ArrayList<>();
        List<ProjectionVarianceDTO> discountPerExfacVarianceList = new ArrayList<>();
        List<ProjectionVarianceDTO> discountPerExfacPercentList = new ArrayList<>();
         Map<Object, List<Object[]>> groupedResult = PROCRAWLIST_DETAIL_DISCOUNT.stream().map(obj -> (Object[]) obj)
                        .collect(Collectors.groupingBy(x -> {
                            return new ArrayList<>(Arrays.asList( x[0],x[1] ,x[2], x[3]));
         }));
         int i=0;
         for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
           List<Object[]> list = entry.getValue();
            final Object[] obj = list.get(0);
            
            String parentkey= obj[1].toString();
            if (i == 0) {
                oldHierarchyNo = parentkey;
            }
            String newHierarchyNo = parentkey;
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                newDiscount = String.valueOf(obj[obj.length - 2]);
                if (oldDiscount.equals(newDiscount)) {
                    if (selection.isVarDisAmount()) {
                        if (selection.isColValue()) {
                            selection.setConversionNeeded(true);
                            vDiscountDollarVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FOUR, vDiscountDollarVal, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                             selection.setConversionNeeded(true);
                            discountDollarVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FOUR, discountDollarVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                             selection.setConversionNeeded(false);
                            discountDollarPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FOUR, discountDollarPercent, selection, RATE_PER,true,list);
                        }
                    }
                    //Discount %
                    if (selection.isVarDisRate()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vDiscountPerVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FIVE, vDiscountPerVal, selection, RATE_PER,false,list);
                        }
                        if (selection.isColVariance()) {
                            discountPerVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FIVE, discountPerVariance, selection, RATE_PER,false,list);
                        }
                        if (selection.isColPercentage()) {
                            vDiscountPerPer = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FIVE, vDiscountPerPer, selection, RATE_PER,true,list);
                        }
                    }
                    //RPU
                    if (selection.isVarRPU()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vRpuVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.ELEVEN, vRpuVal, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            rpuVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.ELEVEN, rpuVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            rpuPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.ELEVEN, rpuPercent, selection, RATE_PER,true,list);
                        }
                    }
                    //Discount Exfactory Per Change
                    if (selection.isDiscountPerExFactory()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            discountPerExfacValue = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.TWELVE, discountPerExfacValue, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            discountPerExfacVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.TWELVE, discountPerExfacVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            discountPerExfacPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.TWELVE, discountPerExfacPercent, selection, RATE_PER,true,list);
                        }
                    }
                    
                } else if (i == 0) {
                    if (selection.isVarDisAmount()) {
                        if (selection.isColValue()) {
                            selection.setConversionNeeded(true);
                            vDiscountDollarVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FOUR, vDiscountDollarVal, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            selection.setConversionNeeded(true);
                            discountDollarVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FOUR, discountDollarVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            selection.setConversionNeeded(false);
                            discountDollarPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FOUR, discountDollarPercent, selection, RATE_PER,true,list);
                        }
                    }
                    //Discount %
                    if (selection.isVarDisRate()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vDiscountPerVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FIVE, vDiscountPerVal, selection, RATE_PER,false,list);
                        }
                        if (selection.isColVariance()) {
                            discountPerVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FIVE, discountPerVariance, selection, RATE_PER,false,list);
                        }
                        if (selection.isColPercentage()) {
                            vDiscountPerPer = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FIVE, vDiscountPerPer, selection, RATE_PER,true,list);
                        }
                    }
                    //RPU
                    if (selection.isVarRPU()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vRpuVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.ELEVEN, vRpuVal, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            rpuVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.ELEVEN, rpuVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            rpuPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.ELEVEN, rpuPercent, selection, RATE_PER,true,list);
                        }
                    }
                    //Discount Exfactory Per Change
                    if (selection.isDiscountPerExFactory()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            discountPerExfacValue = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.TWELVE, discountPerExfacValue, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            discountPerExfacVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.TWELVE, discountPerExfacVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            discountPerExfacPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.TWELVE, discountPerExfacPercent, selection, RATE_PER,true,list);
                        }
                    }
                    /*New discount means add at List */
                    discountDollarvalueList.add(vDiscountDollarVal);
                    discountDollarVarList.add(discountDollarVariance);
                    discountDollarPerList.add(discountDollarPercent);

                    discountPerValueList.add(vDiscountPerVal);
                    discountPerVarianceList.add(discountPerVariance);
                    discountPerPercentList.add(vDiscountPerPer);

                    rpuValueList.add(vRpuVal);
                    rpuVarianceList.add(rpuVariance);
                    rpuPercentList.add(rpuPercent);

                    discountPerExfacValueList.add(discountPerExfacValue);
                    discountPerExfacVarianceList.add(discountPerExfacVariance);
                    discountPerExfacPercentList.add(discountPerExfacPercent);

                    oldDiscount = newDiscount;
                    
                } else {

                    /*Empty the DTO */
                    vDiscountDollarVal = new ProjectionVarianceDTO();
                    discountDollarVariance = new ProjectionVarianceDTO();
                    discountDollarPercent = new ProjectionVarianceDTO();

                    vDiscountPerVal = new ProjectionVarianceDTO();
                    discountPerVariance = new ProjectionVarianceDTO();
                    vDiscountPerPer = new ProjectionVarianceDTO();

                    vRpuVal = new ProjectionVarianceDTO();
                    rpuVariance = new ProjectionVarianceDTO();
                    rpuPercent = new ProjectionVarianceDTO();

                    discountPerExfacValue = new ProjectionVarianceDTO();
                    discountPerExfacVariance = new ProjectionVarianceDTO();
                    discountPerExfacPercent = new ProjectionVarianceDTO();

                    oldDiscount = newDiscount;
                    if (selection.isVarDisAmount()) {
                        if (selection.isColValue()) {
                            selection.setConversionNeeded(true);
                            vDiscountDollarVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FOUR, vDiscountDollarVal, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            selection.setConversionNeeded(true);
                            discountDollarVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FOUR, discountDollarVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            selection.setConversionNeeded(false);
                            discountDollarPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FOUR, discountDollarPercent, selection, RATE_PER,true,list);
                        }
                    }
                    //Discount %
                    if (selection.isVarDisRate()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vDiscountPerVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FIVE, vDiscountPerVal, selection, RATE_PER,false,list);
                        }
                        if (selection.isColVariance()) {
                            discountPerVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FIVE, discountPerVariance, selection, RATE_PER,false,list);
                        }
                        if (selection.isColPercentage()) {
                            vDiscountPerPer = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FIVE, vDiscountPerPer, selection, RATE_PER,true,list);
                        }
                    }
                    //RPU
                    if (selection.isVarRPU()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vRpuVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.ELEVEN, vRpuVal, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            rpuVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.ELEVEN, rpuVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            rpuPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.ELEVEN, rpuPercent, selection, RATE_PER,true,list);
                        }
                    }
                    //Discount Exfactory Per Change
                    if (selection.isDiscountPerExFactory()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            discountPerExfacValue = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.TWELVE, discountPerExfacValue, selection, AMOUNT,false,list);
                        }
                        if (selection.isColVariance()) {
                            discountPerExfacVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.TWELVE, discountPerExfacVariance, selection, AMOUNT,false,list);
                        }
                        if (selection.isColPercentage()) {
                            discountPerExfacPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.TWELVE, discountPerExfacPercent, selection, RATE_PER,true,list);
                        }
                    }

                    /*New discount means add at List */
                    discountDollarvalueList.add(vDiscountDollarVal);
                    discountDollarVarList.add(discountDollarVariance);
                    discountDollarPerList.add(discountDollarPercent);

                    discountPerValueList.add(vDiscountPerVal);
                    discountPerVarianceList.add(discountPerVariance);
                    discountPerPercentList.add(vDiscountPerPer);

                    rpuValueList.add(vRpuVal);
                    rpuVarianceList.add(rpuVariance);
                    rpuPercentList.add(rpuPercent);

                    discountPerExfacValueList.add(discountPerExfacValue);
                    discountPerExfacVarianceList.add(discountPerExfacVariance);
                    discountPerExfacPercentList.add(discountPerExfacPercent);
                }
            } else {

                finaldiscountlist.add(discountDollarvalueList);
                finaldiscountlist.add(discountDollarVarList);
                finaldiscountlist.add(discountDollarPerList);

                finaldiscountlist.add(discountPerValueList);
                finaldiscountlist.add(discountPerVarianceList);
                finaldiscountlist.add(discountPerPercentList);

                finaldiscountlist.add(rpuValueList);
                finaldiscountlist.add(rpuVarianceList);
                finaldiscountlist.add(rpuPercentList);

                finaldiscountlist.add(discountPerExfacValueList);
                finaldiscountlist.add(discountPerExfacVarianceList);
                finaldiscountlist.add(discountPerExfacPercentList);

                String key = oldHierarchyNo;

                discountMapDetails.put(key, finaldiscountlist);
                oldHierarchyNo = newHierarchyNo;
                finaldiscountlist = new ArrayList<>();

                discountDollarvalueList = new ArrayList<>();
                discountDollarVarList = new ArrayList<>();
                discountDollarPerList = new ArrayList<>();

                discountPerValueList = new ArrayList<>();
                discountPerVarianceList = new ArrayList<>();
                discountPerPercentList = new ArrayList<>();

                rpuValueList = new ArrayList<>();
                rpuVarianceList = new ArrayList<>();
                rpuPercentList = new ArrayList<>();

                discountPerExfacValueList = new ArrayList<>();
                discountPerExfacVarianceList = new ArrayList<>();
                discountPerExfacPercentList = new ArrayList<>();

                /*Empty the DTO */
                vDiscountDollarVal = new ProjectionVarianceDTO();
                discountDollarVariance = new ProjectionVarianceDTO();
                discountDollarPercent = new ProjectionVarianceDTO();

                vDiscountPerVal = new ProjectionVarianceDTO();
                discountPerVariance = new ProjectionVarianceDTO();
                vDiscountPerPer = new ProjectionVarianceDTO();

                vRpuVal = new ProjectionVarianceDTO();
                rpuVariance = new ProjectionVarianceDTO();
                rpuPercent = new ProjectionVarianceDTO();

                discountPerExfacValue = new ProjectionVarianceDTO();
                discountPerExfacVariance = new ProjectionVarianceDTO();
                discountPerExfacPercent = new ProjectionVarianceDTO();

                newDiscount = String.valueOf(obj[obj.length - 2]);

                if (selection.isVarDisAmount()) {
                    if (selection.isColValue()) {
                        selection.setConversionNeeded(true);
                        vDiscountDollarVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FOUR, vDiscountDollarVal, selection, AMOUNT,false,list);
                    }
                    if (selection.isColVariance()) {
                        selection.setConversionNeeded(true);
                        discountDollarVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FOUR, discountDollarVariance, selection, AMOUNT,false,list);
                    }
                    if (selection.isColPercentage()) {
                        selection.setConversionNeeded(false);
                        discountDollarPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FOUR, discountDollarPercent, selection, RATE_PER,true,list);
                    }
                }
                //Discount %
                if (selection.isVarDisRate()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        vDiscountPerVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.FIVE, vDiscountPerVal, selection, RATE_PER,false,list);
                    }
                    if (selection.isColVariance()) {
                        discountPerVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.FIVE, discountPerVariance, selection, RATE_PER,false,list);
                    }
                    if (selection.isColPercentage()) {
                        vDiscountPerPer = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.FIVE, vDiscountPerPer, selection, RATE_PER,true,list);
                    }
                }
                //RPU
                if (selection.isVarRPU()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        vRpuVal = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.ELEVEN, vRpuVal, selection, AMOUNT,false,list);
                    }
                    if (selection.isColVariance()) {
                        rpuVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.ELEVEN, rpuVariance, selection, AMOUNT,false,list);
                    }
                    if (selection.isColPercentage()) {
                        rpuPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.ELEVEN, rpuPercent, selection, RATE_PER,true,list);
                    }
                }
                //Discount Exfactory Per Change
                if (selection.isDiscountPerExFactory()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        discountPerExfacValue = calculateDiscountPeriodV2(Constants.VALUE, obj, NumericConstants.TWELVE, discountPerExfacValue, selection, AMOUNT,false,list);
                    }
                    if (selection.isColVariance()) {
                        discountPerExfacVariance = calculateDiscountPeriodV2(Constants.VARIANCE, obj, NumericConstants.TWELVE, discountPerExfacVariance, selection, AMOUNT,false,list);
                    }
                    if (selection.isColPercentage()) {
                        discountPerExfacPercent = calculateDiscountPeriodV2(Constants.CHANGE, obj, NumericConstants.TWELVE, discountPerExfacPercent, selection, RATE_PER,true,list);
                    }
                }
                discountDollarvalueList.add(vDiscountDollarVal);
                discountDollarVarList.add(discountDollarVariance);
                discountDollarPerList.add(discountDollarPercent);

                discountPerValueList.add(vDiscountPerVal);
                discountPerVarianceList.add(discountPerVariance);
                discountPerPercentList.add(vDiscountPerPer);

                rpuValueList.add(vRpuVal);
                rpuVarianceList.add(rpuVariance);
                rpuPercentList.add(rpuPercent);

                discountPerExfacValueList.add(discountPerExfacValue);
                discountPerExfacVarianceList.add(discountPerExfacVariance);
                discountPerExfacPercentList.add(discountPerExfacPercent);
                oldDiscount = newDiscount;
            }
            i++;
            if (i == groupedResult.entrySet().size() - 1) {
                finaldiscountlist.add(discountDollarvalueList);
                finaldiscountlist.add(discountDollarVarList);
                finaldiscountlist.add(discountDollarPerList);

                finaldiscountlist.add(discountPerValueList);
                finaldiscountlist.add(discountPerVarianceList);
                finaldiscountlist.add(discountPerPercentList);

                finaldiscountlist.add(rpuValueList);
                finaldiscountlist.add(rpuVarianceList);
                finaldiscountlist.add(rpuPercentList);

                finaldiscountlist.add(discountPerExfacValueList);
                finaldiscountlist.add(discountPerExfacVarianceList);
                finaldiscountlist.add(discountPerExfacPercentList);

                String key = oldHierarchyNo;

                discountMapDetails.put(key, finaldiscountlist);
            }
        }
            

    }


    private void customizeDiscountPivot() {
        int count = PROCRAWLIST_DETAIL_DISCOUNT.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String newyear;
        String oldYear = StringUtils.EMPTY;
        String newPeriod;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        ProjectionVarianceDTO discountDto = new ProjectionVarianceDTO();
        List<String> discountNames = new ArrayList<>(selection.getDiscountLevel().equals("Program") ? selection.getDiscountNameList() : selection.getDiscountNameCFF());
        for (int i = 0; i < discountNames.size(); i++) {
            String name = String.valueOf(discountNames.get(i)).replaceAll(" ", StringUtils.EMPTY);
            discountNameMap.put(name, String.valueOf(i));
        }

        Map<String, ProjectionVarianceDTO> periodDiscountMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Object[] obj = PROCRAWLIST_DETAIL_DISCOUNT.get(i);
            if (i == 0) {
                oldHierarchyNo = obj[1].toString();
            }
            String newHierarchyNo = String.valueOf(obj[1]) ;
            newyear = String.valueOf(obj[NumericConstants.TWO]);
            newPeriod = String.valueOf(obj[NumericConstants.THREE]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    setBaseValue(discountDto, obj);

                } else if (i == 0) {
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn = getVisibleColumnHeader(obj);
                    setBaseValue(discountDto, obj);
                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn = getVisibleColumnHeader(obj);
                    setBaseValue(discountDto, obj);
                    oldYear = newyear;
                    oldPeriod = newPeriod;

                }
            } else {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);

                periodDiscountMap = new HashMap<>();
                discountDto = new ProjectionVarianceDTO();
                commonColumn = getVisibleColumnHeader(obj);
                setBaseValue(discountDto, obj);
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


    private void setBaseValue(ProjectionVarianceDTO discountDto, Object[] obj) {
        addListDetailDiscountPivot(discountDto,obj);
    }
    
       private void addListDetailDiscountPivot(ProjectionVarianceDTO discountDto, Object[] obj) {
        String discountName = String.valueOf(obj[NumericConstants.FOURTEEN]).replace(" ", StringUtils.EMPTY);
        String head = discountName + String.valueOf(discountNameMap.get(discountName));
        if (selection.isVarDisAmount()) {
            selection.setConversionNeeded(true);
            if (selection.isColValue()) {
                customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_VALUE + head, Constants.VALUE, selection, discountDto, AMOUNT, NumericConstants.SEVEN, obj);
            }
            if (selection.isColVariance()) {
                customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_VAR + head, Constants.VARIANCE, selection, discountDto, AMOUNT, NumericConstants.SEVEN, obj);
            }
            if (selection.isColPercentage()) {
                selection.setConversionNeeded(false);
                customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_PER + head, Constants.CHANGE, selection, discountDto, RATE, NumericConstants.SEVEN, obj);
            }
        }
        //Discount %
        if (selection.isVarDisRate()) {
            selection.setConversionNeeded(false);
            if (selection.isColValue()) {
               customizePivot(StringConstantsUtil.DISCOUNT_SALES_VALUE + head, Constants.VALUE, selection, discountDto, RATE, NumericConstants.TEN, obj);
            }
            if (selection.isColVariance()) {
               customizePivot(StringConstantsUtil.DISCOUNT_SALES_VAR + head, Constants.VARIANCE, selection, discountDto, RATE, NumericConstants.TEN, obj);
            }
            if (selection.isColPercentage()) {
                customizePivot(StringConstantsUtil.DISCOUNT_SALES_PER + head, Constants.CHANGE, selection, discountDto, RATE, NumericConstants.TEN, obj);
            }
        }
        //RPU
        if (selection.isVarRPU()) {
            selection.setConversionNeeded(false);
            if (selection.isColValue()) {
                customizePivot(StringConstantsUtil.RPU_VALUE + head, Constants.VALUE, selection, discountDto, AMOUNT, NumericConstants.THIRTEEN, obj);
            }
            if (selection.isColVariance()) {
                customizePivot(StringConstantsUtil.RPU_VARIANCE + head, Constants.VARIANCE, selection, discountDto, AMOUNT, NumericConstants.THIRTEEN, obj);
            }
            if (selection.isColPercentage()) {
                customizePivot(StringConstantsUtil.RPU_PER + head, Constants.CHANGE, selection, discountDto, RATE, NumericConstants.THIRTEEN, obj);
            }
        }
        //Discount Exfactory Per Change
        if (selection.isDiscountPerExFactory()) {
            selection.setConversionNeeded(false);
            if (selection.isColValue()) {
                customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_VALUE + head, Constants.VALUE, selection, discountDto, RATE, NumericConstants.SIXTEEN, obj);
            }
            if (selection.isColVariance()) {
                customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_VAR + head, Constants.VARIANCE, selection, discountDto, RATE, NumericConstants.SIXTEEN, obj);
            }
            if (selection.isColPercentage()) {
                customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_PER + head, Constants.CHANGE, selection, discountDto, RATE, NumericConstants.SIXTEEN, obj);
            }
        }
    }
    

     public void customizePivot(String variableValue, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO projDTO, DecimalFormat format, int index, Object[] obj) {
        try {
            List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
            PVCommonLogic.customizePeriod(variableValue, variableCategory, pvsdto, projDTO, format, index, obj, format.equals(RATE));
            for (int j = 0; j < priorList.size(); j++) {
                PVCommonLogic.getPriorCommonCustomization(variableCategory, pvsdto, obj, projDTO, variableValue, index, j, format.equals(RATE), COLUMN_COUNT_DISCOUNT, format);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private String getVisibleColumnHeader(Object[] obj) {
        String commonColumn = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = "Q" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = "S" + obj[NumericConstants.THREE] + StringUtils.EMPTY + obj[NumericConstants.TWO];
        } else if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + obj[NumericConstants.TWO];
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.THREE])) - 1);
            commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[NumericConstants.TWO];
        }
        return commonColumn;
    }

    private void calculateDetailPivotForCustomView(List<Object[]> rawList) {
        String appendedParentKey = "";
        Map<Object, List<Object[]>> groupedResult = rawList.stream().map(obj -> (Object[]) obj)
                        .collect(Collectors.groupingBy(x -> {
                            return new ArrayList<>(Arrays.asList( x[0],x[1] ,x[3], x[4]));
                        }));
        int i=0;
        for(Map.Entry<Object, List<Object[]>> localList: groupedResult.entrySet()){
            List<Object[]> list = localList.getValue();
             final Object[] obj = list.get(0);
            final Object[] proj;
            final Object[] actual;
            if (list.size() > 1) {
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = list.get(1);
                } else {
                    actual = list.get(1);
                    proj = list.get(0);
                }
            } else {
                Object[] emptyArray = Collections.nCopies(obj.length, 0).toArray(new Integer[0]);
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = emptyArray;
                } else {
                    actual = emptyArray;
                    proj = list.get(0);
                }
            }
            String key;
            if (isTotal) {
                key = TOTAL1;
            } else if (isCustomView) {
                key = obj[1].toString();
                key = !key.contains("-") ? key.concat(".") : key;
                key = key + appendedParentKey;
            } else {
                key = obj[1].toString();
            }
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.parseInt(obj[3].toString()), Integer.parseInt(obj[4].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            freVarianceDTO.setDfLevelName(groupId);
            freVarianceDTO.setDfLevelNumber(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addListPivot(pvList, obj, freVarianceDTO, INDEX_VALUE, key, groupId,new Object[]{rawList,actual,proj});
                appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1];
                if (isCustomView && !isTotal) {
                    customHierarchyAndTPKeys(obj, key, pvList);
                } else {
					hierarchyAndTPkeys(obj, key, pvList);
                }
            } else if (obj[obj.length - 1] != null && !isTotal) {
                Object[] temp = rawList.get(i - 1) == null ? new Object[0] : rawList.get(i - 1);
                String currValue = obj[1].toString();
                String tempValue = temp[1] == null ? "oldtempValue"
                        : temp[1].toString();
                if (!currValue.equals(tempValue)) {
                    pvList = new ArrayList();
                    addListPivot(pvList, obj, freVarianceDTO, INDEX_VALUE, key, groupId,new Object[]{rawList,actual,proj});
                    if (isCustomView && !isTotal) {
                        customHierarchyAndTPKeys(obj, key, pvList);
                    } else {
						hierarchyAndTPkeys(obj, key, pvList);
                    }
                    appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1];
                } else {
                    updateListPivot(pvList, obj, freVarianceDTO, INDEX_VALUE, key, groupId,rawList,actual,proj);
                }
            } else {
                updateListPivot(pvList, obj, freVarianceDTO, INDEX_VALUE, key, groupId,rawList,actual,proj);
            }
            i++;
        }
    }

    private void customHierarchyAndTPKeys(Object[] obj, String keyParam, List<ProjectionVarianceDTO> pvList) {
        String parentKey = obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString();
        String newKey;
        String key = keyParam;
        if (parentKey == null) {
            hierarchyKeys.add(key);
            resultMap.put(key, pvList);
        } else {
            if (key.contains("$")) {
                key = (key.split("\\$"))[0];
            }
            newKey = key.endsWith(".") ? key+"$"+parentKey : key.trim()+".$"+parentKey;
            hierarchyKeys.add(newKey);
            resultMap.put(newKey, pvList);
        }
        if (CUSTOMER_VARIABLE.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
            tradingPartnerKeys.add(key);
        }
    }

    public String getFormattedExcelColumns(ProjectionVarianceDTO detail, PVSelectionDTO selection, Object[] obj) {
        Map<String,List> listData=!selection.isIsCustomHierarchy()? selection.getSessionDTO().getHierarchyLevelDetails():selection.getSessionDTO().getCustomDescription();
        List<String> groupName = CommonUtils.getFormattedDisplayName(obj[1].toString(),
                listData, selection.getDisplayFormat());
        detail.setGroup(groupName.toString());

        if (selection.getDisplayFormat().length == 1 && selection.getDisplayFormat().length > 0) {
            int index = (int) selection.getDisplayFormat()[0];
            if (index == 0) {
                detail.addStringProperties(DF_LEVEL_NUMBER, groupName.get(0));
            } else {
                detail.addStringProperties(DF_LEVEL_NAME, groupName.get(0));
            }
        } else {
            detail.addStringProperties(DF_LEVEL_NUMBER, groupName.get(0));
            detail.addStringProperties(DF_LEVEL_NAME, groupName.get(0));
            if (groupName.size() == 2) {
                detail.addStringProperties(DF_LEVEL_NUMBER, groupName.get(0));
                detail.addStringProperties(DF_LEVEL_NAME, groupName.get(1));
            }
        }
        return groupName.toString();
    }

}
