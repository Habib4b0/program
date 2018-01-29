/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.logic;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionVariance.dto.PVParameters;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.CommonConstants.VARIANCE;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public static final String STRING_TWO_DECIMAL_FORMAT = "#,##0.00";
    private static final DecimalFormat RATE_PER = new DecimalFormat(STRING_TWO_DECIMAL_FORMAT);
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String CURRENT = "Current";
    private static final String ACTUAL = "Actual";
    private static final String ACCRUAL = "Accrual";
    private final int index = 6;
    private int frequencyDivision;
    private static final int BASECOLUMN_LEVELNAME_INDEX = 3;
    private static final List<Object> PROCRAWLIST_TOTAL_PERIOD = new ArrayList();
    private static final List<Object> PROCRAWLIST_DETAIL = new ArrayList();
    private static final List<Object[]> PROCRAWLIST_DETAIL_DISCOUNT = new ArrayList();
    private static final List<Integer> PRIOR_LIST = new ArrayList();
    private boolean isTotal = false;
    private String levelFilterValue = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String viewValue = StringUtils.EMPTY;
    private static final String PRC_PROJ_RESULTS = "PRC_PROJECTION_RESULTS";
    private final Map<String, String> discountNameMap = new HashMap<>();
    public static final String TOTAL1 = "Total";
    private List<Object> pivotDiscountList = new ArrayList<>();
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat(STRING_TWO_DECIMAL_FORMAT);
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private List<Integer> pivotPriorProjIdList;
    private final Map<String, String> CUSTOM_VIEW_RELATIONSHIP_HIER = new HashMap();
    private final PVParameters parameterDto;
    private boolean discountFlag;
    private boolean isCustomView;
    private static final String PERCENT = "%";
    private static final String PRC_CFF_RESULTS = "Prc_cff_results";
    private static final String C = "C";
    private static final String P = "P";
    private final Map<String, List<ProjectionVarianceDTO>> discountMap;
    private final ProjectionVarianceLogic logic = new ProjectionVarianceLogic();
    private static final String VAL = "Value";
    private static final String VAR = "Var";
    private static final String PER = "Per";
    private boolean actualBasis = false;
    private boolean accrualBasis = false;
    public static final String PRC_CFF_EXCEL_EXPORT = "PRC_CFF_EXCEL_EXPORT";
    public static final String PERIOD_LABEL = "Period";
    public static final String PRC_CFF_DISCOUNT_EXCEL_EXPORT = "PRC_CFF_DISCOUNT_EXCEL_EXPORT";
    public static final String PIVOT_LABEL = "PIVOT";
    private static final int COLUMN_COUNT_TOTAL = 96;
    private static final int COLUMN_COUNT_DISCOUNT = 12;
    private static final String ALL = "ALL";
    private static final int BASECOLUMN_HIERARCHY_INDEX = 2;

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
        actualBasis = (StringConstantsUtil.ACTUALS1).equals(selection.getComparisonBasis());
        accrualBasis = (StringConstantsUtil.ACCRUALS).equals(selection.getComparisonBasis());
        if (isCustomView) {
            selection.setHierarchyIndicator(StringUtils.EMPTY);
            CUSTOM_VIEW_RELATIONSHIP_HIER.putAll(getGroupCustomViewNM());
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

    private void calculateAndCustomizeDetailPeriod(List<Object> rawList) {
        String appendedParentKey = "";
        for (Iterator<Object> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = (Object[]) it.next();
            String key = "";
            if (isCustomView) {
                key = obj[NumericConstants.TWO].toString();
                key = key + appendedParentKey;
                appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
            } else {
                key = obj[NumericConstants.TWO].toString();
            }
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                getCustPeriodVariancDetails(pvList, selection, obj);
                if (isCustomView) {
                    customHierarchyAndTPKeys(obj, key, pvList);
                } else {
					hierarchyAndTPkeys(obj, key, pvList);
                }
            } else {
                updateCustPeriodVarianceDetails(pvList, selection, obj);
            }
        }
    }

	private void hierarchyAndTPkeys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {

        hierarchyKeys.add(key);
        resultMap.put(key, pvList);
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[BASECOLUMN_LEVELNAME_INDEX]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[BASECOLUMN_LEVELNAME_INDEX]))) {
            tradingPartnerKeys.add(key);
        }
    }

	public String getFormattedValue(DecimalFormat format, String value) {
        if (value.contains(Constants.NULL)) {
            value = ZERO;
        } else {
			value = format.format(Double.valueOf(value));
        }
        return value;
    }

    public String isNull(String value) {
        if (value.contains(Constants.NULL)) {
            value = ZERO;
        }
        return value;
    }

    public void getTotalRawData() {
        String frequency = selection.getFrequency();
        List<Integer> projectionIdList = new ArrayList();
        PROCRAWLIST_TOTAL_PERIOD.clear();
        PRIOR_LIST.clear();
        pivotDiscountList.clear();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = StringConstantsUtil.QUARTERLY_LABEL;
        } else if (frequency.equals(StringConstantsUtil.SEMI_ANNUALLY_FREQ)) {
            frequency = StringConstantsUtil.SEMI_ANNUAL_LABEL;
        } else if (frequency.equals(StringConstantsUtil.MONTHLY_FREQ)) {
            frequency = StringConstantsUtil.MONTHLY_LABEL;
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }
        projectionIdList.add(selection.getCurrentProjId());
        projectionIdList.addAll(selection.getProjIdList());
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionId, frequency, StringConstantsUtil.VARIANCE1, null, null, null,null,selection.getSessionDTO().getDiscountUom(), ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion())? null : selection.getSessionDTO().getDeductionInclusion()};
        List< Object[]> rawList = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        PROCRAWLIST_TOTAL_PERIOD.addAll(rawList);
        rawList.clear();
    }

    public void getTotalRawDataPivot() {
        String frequency = selection.getFrequency();
        List<Integer> projectionIdList = new ArrayList();
        PROCRAWLIST_TOTAL_PERIOD.clear();
        PRIOR_LIST.clear();
        pivotDiscountList.clear();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = StringConstantsUtil.QUARTERLY_LABEL;
        } else if (frequency.equals(StringConstantsUtil.SEMI_ANNUALLY_FREQ)) {
            frequency = StringConstantsUtil.SEMI_ANNUAL_LABEL;
        } else if (frequency.equals(StringConstantsUtil.MONTHLY_FREQ)) {
            frequency = StringConstantsUtil.MONTHLY_LABEL;
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }

        projectionIdList.add(selection.getCurrentProjId());
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(projId);
        }
        String discountLevel = !selection.getDeductionLevelFilter().isEmpty() ? CommonUtils.CollectionToString(selection.getDeductionLevelFilter(),false) : null;
        String projectionIds = CommonUtils.CollectionToString(projectionIdList, false);
        Object[] orderedArg = {projectionIds, frequency, StringConstantsUtil.VARIANCE1, null, null, null,null,selection.getSessionDTO().getDiscountUom(), ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion())?null : selection.getSessionDTO().getDeductionInclusion()};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        PROCRAWLIST_TOTAL_PERIOD.addAll(gtsResult);
        if (!selection.getDiscountLevel().equals(ConstantsUtil.TOTAL_DISCOUNT)) {
            Object[] orderedArgDiscount = {projectionIds, frequency, StringConstantsUtil.VARIANCE1, "Pivot", null, null, selection.getDiscountLevel(), "EXCEL",selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),discountLevel,null};
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
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String indicator = selection.getHierarchyIndicator();
        List<Object[]> rawList;
        List<Object[]> rawListDiscount;
        if (indicator.equals(StringUtils.EMPTY)) {
            indicator = "N";
            int custId = selection.getCustomId();
            Object[] orderedArg = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PERIOD_LABEL, indicator, null, 0, custId, null, null, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
            rawList = CommonLogic.callProcedure(PRC_CFF_EXCEL_EXPORT, orderedArg);
        } else {
            Object[] orderedArg = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PERIOD_LABEL, indicator, null, selection.getExcelFilterLevelNo(), null, null, null, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
            rawList = CommonLogic.callProcedure(PRC_CFF_EXCEL_EXPORT, orderedArg);
        }
        PROCRAWLIST_DETAIL.addAll(rawList);
        if (discountFlag) {
             String discountLevel = !selection.getDeductionLevelFilter().isEmpty() ? CommonUtils.CollectionToString(selection.getDeductionLevelFilter(),false) : null;
             String discountLevelName = !selection.getDeductionLevelFilter().isEmpty() ? selection.getDeductionLevelValues() : selection.getDiscountLevel();
            indicator = selection.getHierarchyIndicator();
            if (indicator.equals(StringUtils.EMPTY)) {
                indicator = "N";
                int custId = selection.getCustomId();
                Object[] orderedArgDiscountCustom = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PERIOD_LABEL, discountLevelName, discountLevelName, indicator,
                    null, selection.getExcelFilterLevelNo(), custId, null, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),discountLevel};
                rawListDiscount = CommonLogic.callProcedure(PRC_CFF_DISCOUNT_EXCEL_EXPORT, orderedArgDiscountCustom);
            } else {
                Object[] orderedArgDiscount = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PERIOD_LABEL, discountLevelName, discountLevelName, selection.getHierarchyIndicator(),
                    null, selection.getExcelFilterLevelNo(), null, null, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),discountLevel};

                rawListDiscount = CommonLogic.callProcedure(PRC_CFF_DISCOUNT_EXCEL_EXPORT, orderedArgDiscount);
            }
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
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String indicator = selection.getHierarchyIndicator();
        List<Object[]> rawList;
        List<Object[]> rawListDiscount;
        String order;
        String projectionOrder = selection.getProjectionPeriodOrder();
        if (projectionOrder.equals("Ascending")) {
            order = "A";
        } else {
            order = "D";
        }
        if (indicator.equals(StringUtils.EMPTY)) {
            indicator = "N";
            int custId = selection.getCustomId();

            Object[] orderedArg = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PIVOT_LABEL, indicator,
                null, selection.getExcelFilterLevelNo(), custId, null, order, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
            rawList = CommonLogic.callProcedure(PRC_CFF_EXCEL_EXPORT, orderedArg);
        } else {
            Object[] orderedArg = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PIVOT_LABEL, selection.getHierarchyIndicator(),
                null, selection.getExcelFilterLevelNo(), null, selection.getPivotStartDate(), order, selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
            rawList = CommonLogic.callProcedure(PRC_CFF_EXCEL_EXPORT, orderedArg);
        }
        PROCRAWLIST_DETAIL.addAll(rawList);
        if (discountFlag) {
            indicator = selection.getHierarchyIndicator();
            if (indicator.equals(StringUtils.EMPTY)) {
                indicator = "N";
                int custId = selection.getCustomId();
                Object[] orderedArgDiscount = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PIVOT_LABEL, selection.getDiscountLevel(), selection.getDiscountLevel(), indicator,
                    null, selection.getExcelFilterLevelNo(), custId, selection.getPivotStartDate(), selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
                rawListDiscount = CommonLogic.callProcedure(PRC_CFF_DISCOUNT_EXCEL_EXPORT, orderedArgDiscount);
            } else {

                Object[] orderedArgDiscount = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PIVOT_LABEL, selection.getDiscountLevel(), selection.getDiscountLevel(), selection.getHierarchyIndicator(),
                    null, selection.getExcelFilterLevelNo(), null, selection.getPivotStartDate(), selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId(),selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion()};
                rawListDiscount = CommonLogic.callProcedure(PRC_CFF_DISCOUNT_EXCEL_EXPORT, orderedArgDiscount);
            }
            PROCRAWLIST_DETAIL_DISCOUNT.addAll(rawListDiscount);

        }
    }

    private void calculateAndCustomizeTotalPivot() {
        if (pivotPriorProjIdList != null) {
            pivotPriorProjIdList.clear();
        }
        for (Integer projId : selection.getProjIdList()) {
            pivotPriorProjIdList.add(projId);
        }
        List<ProjectionVarianceDTO> finalList = logic.getCustomizedPivotTotalResults(PROCRAWLIST_TOTAL_PERIOD, pivotPriorProjIdList, selection, pivotDiscountList);
        resultMap.put(TOTAL1, finalList);
    }

    private void addListPivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexTotal, String key, String group) {
        List<String> periodList = new ArrayList<>(selection.getPeriodList());
        int indexForTotal = indexTotal - 1;
        int tempFrequencyDivision = selection.getFrequencyDivision();
        if (isTotal) {
            ProjectionVarianceDTO total = new ProjectionVarianceDTO();
            total.setGroup("Projection Total");
            pvList.add(total);
        } else {
            ProjectionVarianceDTO detail = new ProjectionVarianceDTO();
            String groupName;
            if (isCustomView) {
                groupName = CUSTOM_VIEW_RELATIONSHIP_HIER.get(obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : obj[NumericConstants.TWO].toString());
                groupName = groupName == null ? StringUtils.EMPTY : groupName;
                detail.setHierarchyNo(obj[NumericConstants.TWO].toString());
                detail.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
            } else {
                groupName = CommonUtils.getDisplayFormattedName(obj[NumericConstants.TWO].toString(), selection.getHierarchyIndicator(),
                            selection.getSessionDTO().getHierarchyLevelDetails(), selection.getSessionDTO(), selection.getDisplayFormat());
            }
            detail.setGroup(groupName);
            pvList.add(detail);
        }
        String groupColumn;
        if (tempFrequencyDivision == NumericConstants.TWELVE) {

            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
            groupColumn = groupColumn.toLowerCase();
        } else {
            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
        }
        if (periodList.contains(groupColumn)) {
            pvList.add(frequencyBasedDTO);
            //Ex-Factory-Sales
            if (selection.isVarExFacSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacValue", Constants.VALUE, obj, NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacVariance", Constants.VARIANCE, obj, NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("ExFacPer", Constants.CHANGE, obj, NumericConstants.SIX, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Ex-Factory-Customer
            if (selection.isVarExFacSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacValue", Constants.VALUE, obj, NumericConstants.SEVENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacVariance", Constants.VARIANCE, obj, NumericConstants.SEVENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("CustExFacPer", Constants.CHANGE, obj, NumericConstants.SEVENTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Demand
            if (selection.isVarDemandSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesValue", Constants.VALUE, obj, NumericConstants.FORTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesVariance", Constants.VARIANCE, obj, NumericConstants.FORTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("DemandSalesPer", Constants.CHANGE, obj, NumericConstants.FORTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Adjusted Demand
            if (selection.isVarAdjDemand()) {

                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("AdjDemandValue", Constants.VALUE, obj, NumericConstants.SEVENTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("AdjDemandVariance", Constants.VARIANCE, obj, NumericConstants.SEVENTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("AdjDemandPer", Constants.CHANGE, obj, NumericConstants.SEVENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Inventory Withdraw Summary
            if (selection.isVarInvSales()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("InvWithValue", Constants.VALUE, obj, NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("InvWithVariance", Constants.VARIANCE, obj, NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("InvWithPer", Constants.CHANGE, obj, NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Inventory Withdraw Details
            if (selection.isVarIwDetails()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithDetailsValue", Constants.VALUE, obj, NumericConstants.SEVENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithDetailsVariance", Constants.VARIANCE, obj, NumericConstants.SEVENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("InvWithDetailsPer", Constants.CHANGE, obj, NumericConstants.SEVENTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Ex-Factory product
            if (selection.isVarPerExFacSales()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerExFacValue", Constants.VALUE, obj, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerExFacVariance", Constants.VARIANCE, obj, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerExFacPer", Constants.CHANGE, obj, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Ex-Factory Cust
            if (selection.isVarPerExFacCustomer()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerCustExFacValue", Constants.VALUE, obj, NumericConstants.EIGHTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, obj, NumericConstants.EIGHTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerCustExFacPer", Constants.CHANGE, obj, NumericConstants.EIGHTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Demand
            if (selection.isVarPerDemandSales()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerDemandSalesValue", Constants.VALUE, obj, NumericConstants.FIFTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, obj, NumericConstants.FIFTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerDemandSalesPer", Constants.CHANGE, obj, NumericConstants.FIFTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Adjusted Demand
            if (selection.isVarPerAdjDemand()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerAdjDemandValue", Constants.VALUE, obj, NumericConstants.EIGHTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, obj, NumericConstants.EIGHTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, obj, NumericConstants.EIGHTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of inventory Withdraw Summary
            if (selection.isVarPerInvSales()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithValue", Constants.VALUE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithVariance", Constants.VARIANCE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of inventory Withdraw details
            if (selection.isVarPerIwDetails()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithDetailsValue", Constants.VALUE, obj, NumericConstants.EIGHTY_SEVEN, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithDetailsVariance", Constants.VARIANCE, obj, NumericConstants.EIGHTY_SEVEN, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithDetailsPer", Constants.CHANGE, obj, NumericConstants.EIGHTY_SEVEN, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Contract Sales @ WAC
            if (selection.isVarContractsales()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACValue", Constants.VALUE, obj, NumericConstants.NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, obj, NumericConstants.NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, obj, NumericConstants.NINE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Contract Units
            if (selection.isVarContractUnits()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("ContractUnitsValue", Constants.VALUE, obj, NumericConstants.TWELVE, frequencyBasedDTO, selection, UNIT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("ContractUnitsVar", Constants.VARIANCE, obj, NumericConstants.TWELVE, frequencyBasedDTO, selection, UNIT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("ContractUnitsPer", Constants.CHANGE, obj, NumericConstants.TWELVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Discount $
            if (selection.isVarDisAmount()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountValue", Constants.VALUE, obj, NumericConstants.FIFTEEN, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountVar", Constants.VARIANCE, obj, NumericConstants.FIFTEEN, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("DiscountAmountPer", Constants.CHANGE, obj, NumericConstants.FIFTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Discount %
            if (selection.isVarDisRate()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountSalesValue", Constants.VALUE, obj, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountSalesVar", Constants.VARIANCE, obj, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountSalesPer", Constants.CHANGE, obj, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //RPU
            if (selection.isVarRPU()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("RPUValue", Constants.VALUE, obj, NumericConstants.SIXTY_THREE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("RPUVariance", Constants.VARIANCE, obj, NumericConstants.SIXTY_THREE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("RPUPer", Constants.CHANGE, obj, NumericConstants.SIXTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //DiscountPerExFactory
            if (selection.isDiscountPerExFactory()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountPerExFactoryValue", Constants.VALUE, obj, NumericConstants.NINTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountPerExFactoryVar", Constants.VARIANCE, obj, NumericConstants.NINTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountPerExFactoryPer", Constants.CHANGE, obj, NumericConstants.NINTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Net Sales 
            if (selection.isVarNetSales()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesValue", Constants.VALUE, obj, NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesVariance", Constants.VARIANCE, obj, NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("NetSalesPer", Constants.CHANGE, obj, NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //NetSales%ExFactory 
            if (selection.isNetSalesExFactory()) {
                 selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("NetSalesExFactoryValue", Constants.VALUE, obj, NumericConstants.NINETY, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("NetSalesExFactoryVariance", Constants.VARIANCE, obj, NumericConstants.NINETY, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("NetSalesExFactoryPer", Constants.CHANGE, obj, NumericConstants.NINETY, frequencyBasedDTO, selection, RATE_PER);
                }
            }
        
        String hierarchyNo = String.valueOf(obj[BASECOLUMN_HIERARCHY_INDEX]);
        String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
            Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
            List productList = relationshipLevelDetailsMap.get(hierarchy.trim());
            if (!isTotal && !productList.isEmpty() && P.equals(String.valueOf(productList.get(4)))) {
                /**
                 * Net Ex-Factory Sales
                 */
                if (selection.isNetExFactorySales()) {
                    if (selection.isColValue()) {
                         selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VALUE, Constants.VALUE, obj, NumericConstants.NINTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                    }
                    if (selection.isColVariance()) {
                         selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VARIANCE, Constants.VARIANCE, obj, NumericConstants.NINTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                    }
                    if (selection.isColPercentage()) {
                         selection.setConversionNeeded(false);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constants.CHANGE, obj, NumericConstants.NINTY_SIX, frequencyBasedDTO, selection, RATE);
                    }
                }
                /**
                 * Net Ex-Factory Sales as % of Ex-Factory Sales
                 */
                if (selection.isNetExFactorySalesPerExFactory()) {
                     selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constants.VALUE, obj, NumericConstants.NINTY_NINE, frequencyBasedDTO, selection, RATE);
                    }
                    if (selection.isColVariance()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constants.VARIANCE, obj, NumericConstants.NINTY_NINE, frequencyBasedDTO, selection, RATE);
                    }
                    if (selection.isColPercentage()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constants.CHANGE, obj, NumericConstants.NINTY_NINE, frequencyBasedDTO, selection, RATE);
                    }
                }
            }

            //COGS
            if (selection.isVarCOGC()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("COGCValue", Constants.VALUE, obj, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("COGCVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("COGCPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Net Profit
            if (selection.isVarNetProfit()) {
                if (selection.isColValue()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitValue", Constants.VALUE, obj, indexForTotal + NumericConstants.SIXTY_NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                     selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.SIXTY_NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("NetProfitPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.SIXTY_NINE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            String commonColumn = StringUtils.EMPTY;

            if (tempFrequencyDivision == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (tempFrequencyDivision == NumericConstants.TWO) {
                commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (tempFrequencyDivision == 1) {
                commonColumn = String.valueOf(obj[0]);
            } else if (tempFrequencyDivision == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                commonColumn = monthName.toLowerCase() + obj[0];
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
        PVCommonLogic.customizePeriod(variableName, varibaleCat, selection, pvDTO, format, index, obj, format.equals(RATE_PER));
        for (int j = 0; j < PRIOR_LIST.size(); j++) {
            PVCommonLogic.getPriorCommonCustomization(varibaleCat, selection, obj, pvDTO, variableName + PRIOR_LIST.get(j), index, j, format.equals(RATE_PER), COLUMN_COUNT_TOTAL, format);
        }
    }

    private void calculateAndCustomizeDetailPivot(List<Object> rawList) {

        for (Iterator<Object> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = (Object[]) it.next();
            String key = obj[NumericConstants.TWO].toString();
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[0].toString()), Integer.valueOf(obj[1].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addListPivot(pvList, obj, freVarianceDTO, index, key, groupId);
				hierarchyAndTPkeys(obj, key, pvList);
            } else {
                updateListPivot(pvList, obj, freVarianceDTO, index, key, groupId);
            }
        }
    }

    private void updateListPivot(List<ProjectionVarianceDTO> pvList, Object[] obj, ProjectionVarianceDTO frequencyBasedDTO, int indexTotal, String key, String group) {
        List<String> periodList = new ArrayList<>(selection.getPeriodList());
        String groupColumn;
        if (frequencyDivision == NumericConstants.TWELVE) {
            groupColumn = group.replaceAll(" ", StringUtils.EMPTY);
            groupColumn = groupColumn.toLowerCase();
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
                    calculateForTotal("ExFacValue", Constants.VALUE, obj, NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ExFacVariance", Constants.VARIANCE, obj, NumericConstants.SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("ExFacPer", Constants.CHANGE, obj, NumericConstants.SIX, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Ex-Factory-Customer
            if (selection.isVarExFacCustomer()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacValue", Constants.VALUE, obj, NumericConstants.SEVENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("CustExFacVariance", Constants.VARIANCE, obj, NumericConstants.SEVENTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("CustExFacPer", Constants.CHANGE, obj, NumericConstants.SEVENTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Demand
            if (selection.isVarDemandSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesValue", Constants.VALUE, obj, NumericConstants.FORTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DemandSalesVariance", Constants.VARIANCE, obj, NumericConstants.FORTY_FIVE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                     selection.setConversionNeeded(false);
                    calculateForTotal("DemandSalesPer", Constants.CHANGE, obj, NumericConstants.FORTY_FIVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Adjusted Demand
            if (selection.isVarAdjDemand()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("AdjDemandValue", Constants.VALUE, obj, NumericConstants.SEVENTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("AdjDemandVariance", Constants.VARIANCE, obj, NumericConstants.SEVENTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("AdjDemandPer", Constants.CHANGE, obj, NumericConstants.SEVENTY_TWO, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Inventory Withdraw Sales
            if (selection.isVarInvSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithValue", Constants.VALUE, obj, NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("InvWithVariance", Constants.VARIANCE, obj, NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("InvWithPer", Constants.CHANGE, obj, NumericConstants.FORTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Inventory Withdraw Details
            if (selection.isVarIwDetails()) {
                if (selection.isColValue()) {
                    calculateForTotal("InvWithDetailsValue", Constants.VALUE, obj, NumericConstants.SEVENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("InvWithDetailsVariance", Constants.VARIANCE, obj, NumericConstants.SEVENTY_EIGHT, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("InvWithDetailsPer", Constants.CHANGE, obj, NumericConstants.SEVENTY_EIGHT, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Ex-Factory
            if (selection.isVarPerExFacSales()) {
                if (selection.isColValue()) {
                    calculateForTotal("PerExFacValue", Constants.VALUE, obj, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerExFacVariance", Constants.VARIANCE, obj, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerExFacPer", Constants.CHANGE, obj, NumericConstants.TWENTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Ex-Factory Cust
            if (selection.isVarPerExFacCustomer()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerCustExFacValue", Constants.VALUE, obj, NumericConstants.EIGHTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerCustExFacVariance", Constants.VARIANCE, obj, NumericConstants.EIGHTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerCustExFacPer", Constants.CHANGE, obj, NumericConstants.EIGHTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Demand
            if (selection.isVarPerDemandSales()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerDemandSalesValue", Constants.VALUE, obj, NumericConstants.FIFTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerDemandSalesVariance", Constants.VARIANCE, obj, NumericConstants.FIFTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerDemandSalesPer", Constants.CHANGE, obj, NumericConstants.FIFTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of Adjusted Demand
            if (selection.isVarPerAdjDemand()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerAdjDemandValue", Constants.VALUE, obj, NumericConstants.EIGHTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerAdjDemandSalesVariance", Constants.VARIANCE, obj, NumericConstants.EIGHTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerAdjDemandSalesPer", Constants.CHANGE, obj, NumericConstants.EIGHTY_ONE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of inventory Withdraw Sales
            if (selection.isVarPerInvSales()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithValue", Constants.VALUE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithVariance", Constants.VARIANCE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithPer", Constants.CHANGE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //% of inventory Withdraw details
            if (selection.isVarPerIwDetails()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("PerInvWithDetailsValue", Constants.VALUE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("PerInvWithDetailsVariance", Constants.VARIANCE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("PerInvWithDetailsPer", Constants.CHANGE, obj, NumericConstants.FIFTY_FOUR, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Contract Sales @ WAC
            if (selection.isVarContractsales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACValue", Constants.VALUE, obj, NumericConstants.NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("ContractSalesWACVar", Constants.VARIANCE, obj, NumericConstants.NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("ContractSalesWACVarPer", Constants.CHANGE, obj, NumericConstants.NINE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Contract Units
            if (selection.isVarContractUnits()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("ContractUnitsValue", Constants.VALUE, obj, NumericConstants.TWELVE, frequencyBasedDTO, selection, UNIT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("ContractUnitsVar", Constants.VARIANCE, obj, NumericConstants.TWELVE, frequencyBasedDTO, selection, UNIT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("ContractUnitsPer", Constants.CHANGE, obj, NumericConstants.TWELVE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            //Discount $
            if (selection.isVarDisAmount()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountValue", Constants.VALUE, obj, NumericConstants.FIFTEEN, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("DiscountAmountVar", Constants.VARIANCE, obj, NumericConstants.FIFTEEN, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("DiscountAmountPer", Constants.CHANGE, obj, NumericConstants.FIFTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Discount %
            if (selection.isVarDisRate()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountSalesValue", Constants.VALUE, obj, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountSalesVar", Constants.VARIANCE, obj, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountSalesPer", Constants.CHANGE, obj, NumericConstants.EIGHTEEN, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //RPU
            if (selection.isVarRPU()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("RPUValue", Constants.VALUE, obj, NumericConstants.SIXTY_THREE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("RPUVariance", Constants.VARIANCE, obj, NumericConstants.SIXTY_THREE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("RPUPer", Constants.CHANGE, obj, NumericConstants.SIXTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //DiscountPerExFactory
            if (selection.isDiscountPerExFactory()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("DiscountPerExFactoryValue", Constants.VALUE, obj, NumericConstants.NINTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("DiscountPerExFactoryVar", Constants.VARIANCE, obj, NumericConstants.NINTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("DiscountPerExFactoryPer", Constants.CHANGE, obj, NumericConstants.NINTY_THREE, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Net Sales 
            if (selection.isVarNetSales()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesValue", Constants.VALUE, obj, indexForTotal + NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetSalesVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("NetSalesPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.FORTY_TWO, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //NetSales%ExFactory 
            if (selection.isNetSalesExFactory()) {
                selection.setConversionNeeded(false);
                if (selection.isColValue()) {
                    calculateForTotal("NetSalesExFactoryValue", Constants.VALUE, obj, NumericConstants.NINETY, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColVariance()) {
                    calculateForTotal("NetSalesExFactoryVariance", Constants.VARIANCE, obj, NumericConstants.NINETY, frequencyBasedDTO, selection, RATE_PER);
                }
                if (selection.isColPercentage()) {
                    calculateForTotal("NetSalesExFactoryPer", Constants.CHANGE, obj, NumericConstants.NINETY, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            String hierarchyNo = String.valueOf(obj[BASECOLUMN_HIERARCHY_INDEX]);
            String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
            Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
            List productList = relationshipLevelDetailsMap.get(hierarchy.trim());
            if (!isTotal && !productList.isEmpty() && P.equals(String.valueOf(productList.get(4)))) {
                /**
                 * Net Ex-Factory Sales
                 */
                if (selection.isNetExFactorySales()) {
                    if (selection.isColValue()) {
                        selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VALUE, Constants.VALUE, obj, NumericConstants.NINTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                    }
                    if (selection.isColVariance()) {
                        selection.setConversionNeeded(true);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VARIANCE, Constants.VARIANCE, obj, NumericConstants.NINTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                    }
                    if (selection.isColPercentage()) {
                        selection.setConversionNeeded(false);
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constants.CHANGE, obj, NumericConstants.NINTY_SIX, frequencyBasedDTO, selection, RATE);
                    }
                }
                /**
                 * Net Ex-Factory Sales as % of Ex-Factory Sales
                 */
                if (selection.isNetExFactorySalesPerExFactory()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constants.VALUE, obj, NumericConstants.NINTY_NINE, frequencyBasedDTO, selection, RATE);
                    }
                    if (selection.isColVariance()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constants.VARIANCE, obj, NumericConstants.NINTY_NINE, frequencyBasedDTO, selection, RATE);
                    }
                    if (selection.isColPercentage()) {
                        calculateForTotal(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constants.CHANGE, obj, NumericConstants.NINTY_NINE, frequencyBasedDTO, selection, RATE);
                    }
                }
            }
            //COGS
            if (selection.isVarCOGC()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("COGCValue", Constants.VALUE, obj, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("COGCVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("COGCPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.SIXTY_SIX, frequencyBasedDTO, selection, RATE_PER);
                }
            }
            //Net Profit
            if (selection.isVarNetProfit()) {
                if (selection.isColValue()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitValue", Constants.VALUE, obj, indexForTotal + NumericConstants.SIXTY_NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColVariance()) {
                    selection.setConversionNeeded(true);
                    calculateForTotal("NetProfitVariance", Constants.VARIANCE, obj, indexForTotal + NumericConstants.SIXTY_NINE, frequencyBasedDTO, selection, AMOUNT);
                }
                if (selection.isColPercentage()) {
                    selection.setConversionNeeded(false);
                    calculateForTotal("NetProfitPer", Constants.CHANGE, obj, indexForTotal + NumericConstants.SIXTY_NINE, frequencyBasedDTO, selection, RATE_PER);
                }
            }

            String commonColumn = StringUtils.EMPTY;
            if (frequencyDivision == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (frequencyDivision == NumericConstants.TWO) {
                commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (frequencyDivision == 1) {
                commonColumn = String.valueOf(obj[0]);
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                commonColumn = monthName.toLowerCase() + obj[0];
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


        discountDollarValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FIVE, Boolean.FALSE, Constants.VALUE, Boolean.TRUE);
        discountDollarVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FIVE, Boolean.FALSE, Constants.VARIANCE, Boolean.TRUE);
        discountDollarPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FIVE, Boolean.TRUE, Constants.CHANGE, Boolean.FALSE);

        discountperValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.EIGHT, Boolean.TRUE, Constants.VALUE, Boolean.FALSE);
        discountperVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.EIGHT, Boolean.TRUE, Constants.VARIANCE, Boolean.FALSE);
        discountperPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.EIGHT, Boolean.TRUE, Constants.CHANGE, Boolean.FALSE);

        rpuValueList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.ELEVEN, Boolean.FALSE, Constants.VALUE, Boolean.FALSE);
        rpuVarianceList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.ELEVEN, Boolean.FALSE, VARIANCE.getConstant(), Boolean.FALSE);
        rpuPercentList = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.ELEVEN, Boolean.TRUE, Constants.CHANGE, Boolean.FALSE);

        discountperExfacValuelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOURTEEN, Boolean.TRUE, Constants.VALUE, Boolean.FALSE);
        discountperExfacVariancelist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOURTEEN, Boolean.TRUE, Constants.VARIANCE, Boolean.FALSE);
        discountperExfacPercentlist = getCustomisedDiscount(pivotDiscountList, selection, NumericConstants.FOURTEEN, Boolean.TRUE, Constants.CHANGE, Boolean.FALSE);

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

    public void getTotalPivotVariance(PVSelectionDTO selection) {
        String frequency = selection.getFrequency();
        List<Object> pivotTotalList;
        String discountId = CommonUtils.CollectionToString(selection.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = StringConstantsUtil.QUARTERLY_LABEL;
        } else if (frequency.equals(StringConstantsUtil.SEMI_ANNUALLY_FREQ)) {
            frequency = StringConstantsUtil.SEMI_ANNUAL_LABEL;
        } else if (frequency.equals(StringConstantsUtil.MONTHLY_FREQ)) {
            frequency = StringConstantsUtil.MONTHLY_LABEL;
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }
        projectionIdList.add(String.valueOf(selection.getCurrentProjId()));
        for (Integer projId : selection.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);

        Object[] orderedArg = {projectionId, frequency, discountId, StringConstantsUtil.VARIANCE1, selection.getSessionId(), selection.getUserId(), PIVOT_LABEL};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    void getTotalDiscount(PVSelectionDTO projSelDTO) {
        pivotDiscountList.clear();
        String frequency = projSelDTO.getFrequency();
        List<String> projectionIdList = new ArrayList<>();
        pivotDiscountList = new ArrayList<>();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = StringConstantsUtil.QUARTERLY_LABEL;
        } else if (frequency.equals(StringConstantsUtil.SEMI_ANNUALLY_FREQ)) {
            frequency = StringConstantsUtil.SEMI_ANNUAL_LABEL;
        } else if (frequency.equals(StringConstantsUtil.MONTHLY_FREQ)) {
            frequency = StringConstantsUtil.MONTHLY_LABEL;
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }
        projectionIdList.add(String.valueOf(projSelDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String discountLevel = !selection.getDeductionLevelFilter().isEmpty() ? CommonUtils.CollectionToString(selection.getDeductionLevelFilter(),false) : null;
        Object[] orderedArg = {projectionId, frequency, StringConstantsUtil.VARIANCE1, PERIOD_LABEL, null, null, selection.getDiscountLevel(), "EXCEL",selection.getSessionDTO().getDiscountUom(),ALL.equals(selection.getSessionDTO().getSalesInclusion()) ? null : selection.getSessionDTO().getSalesInclusion(), ALL.equals(selection.getSessionDTO().getDeductionInclusion()) ? null : selection.getSessionDTO().getDeductionInclusion(),discountLevel,null};
        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArg);
        pivotDiscountList.addAll(discountsList);

    }

    private Map<String, String> getGroupCustomViewNM() {
        Map<String, List> relationshipLevelDetailsMap = selection.getSessionDTO().getHierarchyLevelDetails();
        Map<String, String> customViewMap = new HashMap<>();
        Set keys = relationshipLevelDetailsMap.keySet();

        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            String value = CommonUtils.getDisplayFormattedName(key, relationshipLevelDetailsMap.get(key).get(4).toString(), relationshipLevelDetailsMap, selection.getSessionDTO(), selection.getDisplayFormat());
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
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!StringUtils.EMPTY.equals(lastValue) && !"null".equals(lastValue) && obj[NumericConstants.TWO] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[NumericConstants.TWO]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (vFrequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (vFrequencyDivision == NumericConstants.TWO) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (vFrequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (vFrequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                PVCommonLogic.customizePeriod(commonColumn, indicator, projSelDTO, pvDTO, isPer ? RATE : AMOUNT, index, obj, isPer);
                for (int j = 0; j < vPriorList.size(); j++) {
                    PVCommonLogic.getPriorCommonCustomization(indicator, projSelDTO, obj, pvDTO, commonColumn + vPriorList.get(j), index, j, isPer, COLUMN_COUNT_DISCOUNT, isPer ? RATE : AMOUNT);
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        }
        LOGGER.debug("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + resultDto.size());
        return resultDto;
    }

    public List<ProjectionVarianceDTO> getCustPeriodVariancDetails(List<ProjectionVarianceDTO> pvList, final PVSelectionDTO pvsdto, final Object[] obj) {

        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<>();
        ProjectionVarianceDTO exFacValue1 = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVariance = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPercent = new ProjectionVarianceDTO();

        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
        //Group Column projSelDTO
        String groupName;
        String hierarchyNo = String.valueOf(obj[BASECOLUMN_HIERARCHY_INDEX]);
        String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
        if (isCustomView) {
            groupName = CUSTOM_VIEW_RELATIONSHIP_HIER.get(hierarchy.trim() == null ? StringUtils.EMPTY : hierarchy.trim());
            groupName = groupName == null ? StringUtils.EMPTY : groupName;
            dto.setHierarchyNo(obj[NumericConstants.TWO].toString());
            dto.setParentHierarchyNo(obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString());
        } else {
            groupName = CommonUtils.getDisplayFormattedName(hierarchy.trim(), selection.getHierarchyIndicator(),
                            selection.getSessionDTO().getHierarchyLevelDetails(), selection.getSessionDTO(), selection.getDisplayFormat());
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
                exFacValue1 = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, obj, NumericConstants.SIX, pvsdto, AMOUNT, exFacValue1, true);
                pvList.add(exFacValue1);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                exFacVariance = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SIX, pvsdto, AMOUNT, exFacVariance, true);
                pvList.add(exFacVariance);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                exFacPercent = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), Constants.CHANGE, obj, obj, NumericConstants.SIX, pvsdto, RATE, exFacPercent, true);
                pvList.add(exFacPercent);

            }
        }

        // Customer ExFac Sales for detail start 
        if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                custExFacValue = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, obj, NumericConstants.SEVENTY_FIVE, pvsdto, AMOUNT, custExFacValue, true);
                pvList.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                custExFacVar = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SEVENTY_FIVE, pvsdto, AMOUNT, custExFacVar, true);
                pvList.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                custExFacPer = getCommonCustomizedDTODetails(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), Constants.CHANGE, obj, obj, NumericConstants.SEVENTY_FIVE, pvsdto, RATE, custExFacPer, true);
                pvList.add(custExFacPer);
            }
        }
//            //Demand - start
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                vDemandValue = getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.VALUE, obj, obj, NumericConstants.FORTY_FIVE, pvsdto, AMOUNT, vDemandValue, true);
                pvList.add(vDemandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vDemandVariance = getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.VARIANCE, obj, obj, NumericConstants.FORTY_FIVE, pvsdto, AMOUNT, vDemandVariance, true);
                pvList.add(vDemandVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                vDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.DEMAND.toString(), Constants.CHANGE, obj, obj, NumericConstants.FORTY_FIVE, pvsdto, RATE, vDemandPer, true);
                pvList.add(vDemandPer);
            }
        }

        // Adjusted Demand  start 
        if (pvsdto.isVarAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vAdjDemandVal = getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, obj, NumericConstants.SEVENTY_TWO, pvsdto, AMOUNT, vAdjDemandVal, true);
                pvList.add(vAdjDemandVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vAdjDemandVar = getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SEVENTY_TWO, pvsdto, AMOUNT, vAdjDemandVar, true);
                pvList.add(vAdjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vAdjDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.CHANGE, obj, obj, NumericConstants.SEVENTY_TWO, pvsdto, RATE, vAdjDemandPer, true);
                pvList.add(vAdjDemandPer);
            }
        }
//
//            //Inv with drawal Summary
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                invWithSummaryValue = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VALUE, obj, obj, NumericConstants.FORTY_EIGHT, pvsdto, AMOUNT, invWithSummaryValue, true);
                pvList.add(invWithSummaryValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                invWithSummaryVar = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.VARIANCE, obj, obj, NumericConstants.FORTY_EIGHT, pvsdto, AMOUNT, invWithSummaryVar, true);
                pvList.add(invWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                invWithSummaryPer = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_SUMMARY.toString(), Constants.CHANGE, obj, obj, NumericConstants.FORTY_EIGHT, pvsdto, RATE, invWithSummaryPer, true);
                pvList.add(invWithSummaryPer);
            }
        }

        // Inventary withdrawal Details for detail start 
        if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempwDetialsValue = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VALUE, obj, obj, NumericConstants.SEVENTY_EIGHT, pvsdto, AMOUNT, tempwDetialsValue, true);
                pvList.add(tempwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vInvWithDetialsVar = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SEVENTY_EIGHT, pvsdto, AMOUNT, vInvWithDetialsVar, true);
                pvList.add(vInvWithDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                vInvWithDetialsPer = getCommonCustomizedDTODetails(Constants.PVVariables.INVENTORY_DETAILS.toString(), Constants.CHANGE, obj, obj, NumericConstants.SEVENTY_EIGHT, pvsdto, RATE, vInvWithDetialsPer, true);
                pvList.add(vInvWithDetialsPer);
            }
        }
//
//       //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerExFactoryProdVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VALUE, obj, obj, NumericConstants.TWENTY_ONE, pvsdto, RATE, vPerExFactoryProdVal, true);
                pvList.add(vPerExFactoryProdVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                perExFacProductVariance = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.VARIANCE, obj, obj, NumericConstants.TWENTY_ONE, pvsdto, RATE, perExFacProductVariance, true);
                pvList.add(perExFacProductVariance);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerExFactoryProd = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), Constants.CHANGE, obj, obj, NumericConstants.TWENTY_ONE, pvsdto, RATE, vPerExFactoryProd, true);
                pvList.add(vPerExFactoryProd);
            }
        }
//        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerExFactoryCustVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VALUE, obj, obj, NumericConstants.EIGHTY_FOUR, pvsdto, RATE, vPerExFactoryCustVal, true);
                pvList.add(vPerExFactoryCustVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerExFactoryCustVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.VARIANCE, obj, obj, NumericConstants.EIGHTY_FOUR, pvsdto, RATE, vPerExFactoryCustVar, true);
                pvList.add(vPerExFactoryCustVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerExFactoryCustPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), Constants.CHANGE, obj, obj, NumericConstants.EIGHTY_FOUR, pvsdto, RATE, vPerExFactoryCustPer, true);
                pvList.add(vPerExFactoryCustPer);
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerDemandVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.VALUE, obj, obj, NumericConstants.FIFTY_ONE, pvsdto, RATE, vPerDemandVal, true);
                pvList.add(vPerDemandVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerDemandVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.VARIANCE, obj, obj, NumericConstants.FIFTY_ONE, pvsdto, RATE, vPerDemandVar, true);
                pvList.add(vPerDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_DEMAND.toString(), Constants.CHANGE, obj, obj, NumericConstants.FIFTY_ONE, pvsdto, RATE, vPerDemandPer, true);
                pvList.add(vPerDemandPer);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerAdjDemandVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VALUE, obj, obj, NumericConstants.EIGHTY_ONE, pvsdto, RATE, vPerAdjDemandVal, true);
                pvList.add(vPerAdjDemandVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerAdjDemandVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.VARIANCE, obj, obj, NumericConstants.EIGHTY_ONE, pvsdto, RATE, vPerAdjDemandVar, true);
                pvList.add(vPerAdjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerAdjDemandPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), Constants.CHANGE, obj, obj, NumericConstants.EIGHTY_ONE, pvsdto, RATE, vPerAdjDemandPer, true);
                pvList.add(vPerAdjDemandPer);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerInvWithSummaryVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VALUE, obj, obj, NumericConstants.FIFTY_FOUR, pvsdto, RATE, vPerInvWithSummaryVal, true);
                pvList.add(vPerInvWithSummaryVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerInvWithSummaryVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.VARIANCE, obj, obj, NumericConstants.FIFTY_FOUR, pvsdto, RATE, vPerInvWithSummaryVar, true);
                pvList.add(vPerInvWithSummaryVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerInvWithSummaryPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), Constants.CHANGE, obj, obj, NumericConstants.FIFTY_FOUR, pvsdto, RATE, vPerInvWithSummaryPer, true);
                pvList.add(vPerInvWithSummaryPer);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                vPerIwDetialsVal = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VALUE, obj, obj, NumericConstants.EIGHTY_SEVEN, pvsdto, RATE, vPerIwDetialsVal, true);
                pvList.add(vPerIwDetialsVal);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                vPerIwDetialsVar = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.VARIANCE, obj, obj, NumericConstants.EIGHTY_SEVEN, pvsdto, RATE, vPerIwDetialsVar, true);
                pvList.add(vPerIwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                vPerIwDetialsPer = getCommonCustomizedDTODetails(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), Constants.CHANGE, obj, obj, NumericConstants.EIGHTY_SEVEN, pvsdto, RATE, vPerIwDetialsPer, true);
                pvList.add(vPerIwDetialsPer);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempContractSalesValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VALUE, obj, obj, NumericConstants.NINE, pvsdto, AMOUNT, tempContractSalesValue, true);
                pvList.add(tempContractSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempContractSalesVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.VARIANCE, obj, obj, NumericConstants.NINE, pvsdto, AMOUNT, tempContractSalesVar, true);
                pvList.add(tempContractSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempContractSalesPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), Constants.CHANGE, obj, obj, NumericConstants.NINE, pvsdto, RATE, tempContractSalesPer, true);
                pvList.add(tempContractSalesPer);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {

            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                tempContractUnitValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VALUE, obj, obj, NumericConstants.TWELVE, pvsdto, AMOUNT_UNITS, tempContractUnitValue, true);
                pvList.add(tempContractUnitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempContractUnitVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.VARIANCE, obj, obj, NumericConstants.TWELVE, pvsdto, AMOUNT_UNITS, tempContractUnitVar, true);
                pvList.add(tempContractUnitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempContractUnitPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), Constants.CHANGE, obj, obj, NumericConstants.TWELVE, pvsdto, RATE, tempContractUnitPer, true);
                pvList.add(tempContractUnitPer);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempDiscountDollarValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.VALUE, obj, obj, NumericConstants.FIFTEEN, pvsdto, AMOUNT, tempDiscountDollarValue, true);
                pvList.add(tempDiscountDollarValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempDiscountDollarVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.VARIANCE, obj, obj, NumericConstants.FIFTEEN, pvsdto, AMOUNT, tempDiscountDollarVar, true);
                pvList.add(tempDiscountDollarVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempDiscountDollarPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), Constants.CHANGE, obj, obj, NumericConstants.FIFTEEN, pvsdto, RATE, tempDiscountDollarPer, true);
                pvList.add(tempDiscountDollarPer);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                tempDiscountPerValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.VALUE, obj, obj, NumericConstants.EIGHTEEN, pvsdto, RATE, tempDiscountPerValue, true);
                pvList.add(tempDiscountPerValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempDiscountPerVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.VARIANCE, obj, obj, NumericConstants.EIGHTEEN, pvsdto, RATE, tempDiscountPerVar, true);
                pvList.add(tempDiscountPerVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempDiscountPerPercent = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_DIS_RATE.toString(), Constants.CHANGE, obj, obj, NumericConstants.EIGHTEEN, pvsdto, RATE, tempDiscountPerPercent, true);
                pvList.add(tempDiscountPerPercent);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                tempRpuValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.VALUE, obj, obj, NumericConstants.SIXTY_THREE, pvsdto, AMOUNT, tempRpuValue, true);
                pvList.add(tempRpuValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempRpuVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SIXTY_THREE, pvsdto, AMOUNT, tempRpuVar, true);
                pvList.add(tempRpuVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempRpuPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_RPU.toString(), Constants.CHANGE, obj, obj, NumericConstants.SIXTY_THREE, pvsdto, RATE, tempRpuPer, true);
                pvList.add(tempRpuPer);
            }
        }
        //DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                discountPerExfacValue = getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.VALUE, obj, obj, NumericConstants.NINTY_THREE, pvsdto, RATE, discountPerExfacValue, true);
                pvList.add(discountPerExfacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                discountPerExfacVar = getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.VARIANCE, obj, obj, NumericConstants.NINTY_THREE, pvsdto, RATE, discountPerExfacVar, true);
                pvList.add(discountPerExfacVar);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                discountPerExfacPercent = getCommonCustomizedDTODetails(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constants.CHANGE, obj, obj, NumericConstants.NINTY_THREE, pvsdto, RATE, discountPerExfacPercent, true);
                pvList.add(discountPerExfacPercent);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempNetSalesValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VALUE, obj, obj, NumericConstants.FORTY_TWO, pvsdto, AMOUNT, tempNetSalesValue, true);
                pvList.add(tempNetSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tesmpNetSalesVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.VARIANCE, obj, obj, NumericConstants.FORTY_TWO, pvsdto, AMOUNT, tesmpNetSalesVar, true);
                pvList.add(tesmpNetSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempNetSalesPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NETSALES.toString(), Constants.CHANGE, obj, obj, NumericConstants.FORTY_TWO, pvsdto, RATE, tempNetSalesPer, true);
                pvList.add(tempNetSalesPer);
            }
        }
        //NetSales%ExFactory
        if (pvsdto.isNetSalesExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
                netSalesExfacValue = getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.VALUE, obj, obj, NumericConstants.NINETY, pvsdto, RATE, netSalesExfacValue, true);
                pvList.add(netSalesExfacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
                netSalesExfacVar = getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.VARIANCE, obj, obj, NumericConstants.NINETY, pvsdto, RATE, netSalesExfacVar, true);
                pvList.add(netSalesExfacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
                netSalesExfacPer = getCommonCustomizedDTODetails(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constants.CHANGE, obj, obj, NumericConstants.NINETY, pvsdto, RATE, netSalesExfacPer, true);
                pvList.add(netSalesExfacPer);
            }
        }

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
                    netExFactorySalesVal = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.VALUE, obj, obj, NumericConstants.NINTY_SIX, pvsdto, AMOUNT, netExFactorySalesVal, true);
                    pvList.add(netExFactorySalesVal);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setConversionNeeded(true);
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                    netExFactorySalesVariance = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.VARIANCE, obj, obj, NumericConstants.NINTY_SIX, pvsdto, AMOUNT, netExFactorySalesVariance, true);
                    pvList.add(netExFactorySalesVariance);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setConversionNeeded(false);
                    pvsdto.setVarIndicator(Constants.CHANGE);
                    netExFactorySalesPercent = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES, Constants.CHANGE, obj, obj, NumericConstants.NINTY_SIX, pvsdto, RATE, netExFactorySalesPercent, true);
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
                    netExFactorySalesPerExFactoryVal = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.VALUE, obj, obj, NumericConstants.NINTY_NINE, pvsdto, RATE, netExFactorySalesPerExFactoryVal, true);
                    pvList.add(netExFactorySalesPerExFactoryVal);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                    netExFactorySalesPerExFactoryVariance = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.VARIANCE, obj, obj, NumericConstants.NINTY_NINE, pvsdto, RATE, netExFactorySalesPerExFactoryVariance, true);
                    pvList.add(netExFactorySalesPerExFactoryVariance);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setVarIndicator(Constants.CHANGE);
                    netExFactorySalesPerExFactoryPercent = getCommonCustomizedDTODetails(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT, Constants.CHANGE, obj, obj, NumericConstants.NINTY_NINE, pvsdto, RATE, netExFactorySalesPerExFactoryPercent, true);
                    pvList.add(netExFactorySalesPerExFactoryPercent);
                }
            }
        }

        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempCogcValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.VALUE, obj, obj, NumericConstants.SIXTY_SIX, pvsdto, AMOUNT, tempCogcValue, true);
                pvList.add(tempCogcValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempCogcVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SIXTY_SIX, pvsdto, AMOUNT, tempCogcVar, true);
                pvList.add(tempCogcVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempCogcPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_COGS.toString(), Constants.CHANGE, obj, obj, NumericConstants.SIXTY_SIX, pvsdto, RATE, tempCogcPer, true);
                pvList.add(tempCogcPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
                tempNetProfitValue = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VALUE, obj, obj, NumericConstants.SIXTY_NINE, pvsdto, AMOUNT, tempNetProfitValue, true);
                pvList.add(tempNetProfitValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
                tempNetProfitVar = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.VARIANCE, obj, obj, NumericConstants.SIXTY_NINE, pvsdto, AMOUNT, tempNetProfitVar, true);
                pvList.add(tempNetProfitVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                tempNetProfitPer = getCommonCustomizedDTODetails(Constants.PVVariables.VAR_NET_PROFITE.toString(), Constants.CHANGE, obj, obj, NumericConstants.SIXTY_NINE, pvsdto, RATE, tempNetProfitPer, true);
                pvList.add(tempNetProfitPer);
            }
        }
        return projectionVarianceDTO;
    }

    public ProjectionVarianceDTO getCommonCustomizedDTODetails(String groupName, String varibaleCat, Object[] obj, Object[] dataObj, final int totalListPostion, PVSelectionDTO pvsdto, final DecimalFormat FORMAT, ProjectionVarianceDTO pvDTO, boolean addFlag) {
        int vFrequencyDiv = pvsdto.getFrequencyDivision();
         
        if (addFlag) {
            if (pvsdto.getVarIndicator().equals(Constants.VALUE)) {
                pvDTO.setGroup(groupName.concat(Constants.VALUE));
            } else if (pvsdto.getVarIndicator().equals(Constants.VARIANCE)) {
                pvDTO.setGroup(groupName.concat(Constants.VARIANCE));
            } else if (pvsdto.getVarIndicator().equals(Constants.CHANGE)) {
                pvDTO.setGroup(groupName.concat(Constants.CHANGE));
            }
        }
        List<Integer> vPriorList = new ArrayList<>(pvsdto.getProjIdList());
        if (obj != null) {
            obj = dataObj;
            String commonColumn = StringUtils.EMPTY;
            if (vFrequencyDiv == NumericConstants.FOUR) {
                commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (vFrequencyDiv == NumericConstants.TWO) {
                commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
            } else if (vFrequencyDiv == 1) {
                commonColumn = StringUtils.EMPTY + obj[0];
            } else if (vFrequencyDiv == NumericConstants.TWELVE) {
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                commonColumn = monthName.toLowerCase() + obj[0];
            }
            PVCommonLogic.customizePeriod(commonColumn, varibaleCat, pvsdto, pvDTO, FORMAT, totalListPostion, obj, groupName.contains("%"));
            for (int j = 0; j < vPriorList.size(); j++) {
                PVCommonLogic.getPriorCommonCustomization(varibaleCat, pvsdto, obj, pvDTO, commonColumn + vPriorList.get(j), totalListPostion, j, groupName.contains("%"), COLUMN_COUNT_TOTAL, FORMAT);
            }
        }
        return pvDTO;
    }

    public void updateCustPeriodVarianceDetails(List<ProjectionVarianceDTO> pvList, final PVSelectionDTO pvsdto, final Object[] obj) {
        int listIndex = 1;

        //Ex fac product
        if (pvsdto.isVarExFacSales()) {
            if (selection.isColValue()) {
                pvsdto.setConversionNeeded(true);
                selection.setVarIndicator(Constants.VALUE);
            }
            if (selection.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                selection.setVarIndicator(Constants.VARIANCE);
            }
            if (selection.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                selection.setVarIndicator(Constants.CHANGE);
            }

        }
//           
//           // Customer ExFac Sales for detail start 
        if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        //Demand - start
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        // Adjusted Demand  start 
        if (pvsdto.isVarAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        //Inv with drawal summary
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);

            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);

            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        // Inventary withdrawal Details for detail start 
        if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        if (pvsdto.isVarPerExFacSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);

            }
        }
//            
//            
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
//        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        // RPU
        if (pvsdto.isVarRPU()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        //DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        //NetSales%ExFactory 
        if (pvsdto.isNetSalesExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }

        String hierarchyNo = String.valueOf(obj[BASECOLUMN_HIERARCHY_INDEX]);
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
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setConversionNeeded(true);
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setConversionNeeded(false);
                    pvsdto.setVarIndicator(Constants.CHANGE);
                }
            }
            /**
             * Net Ex-Factory Sales as % of Ex-Factory Sales
             */
            if (pvsdto.isNetExFactorySalesPerExFactory()) {
                pvsdto.setConversionNeeded(false);
                if (pvsdto.isColValue()) {
                    pvsdto.setVarIndicator(Constants.VALUE);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setVarIndicator(Constants.VARIANCE);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setVarIndicator(Constants.CHANGE);
                }
            }
        }

        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VALUE);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(Constants.VARIANCE);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                pvsdto.setVarIndicator(Constants.CHANGE);
                LOGGER.debug("End of Method" + listIndex);
            }
        }
    }

    private ProjectionVarianceDTO calculateDiscountPeriod(String varibaleCat, Object[] obj, int currentIndex, ProjectionVarianceDTO pvDTO,
            PVSelectionDTO selection, DecimalFormat format) {
        int vFrequencyDiv = selection.getFrequencyDivision();
        String commonColumn = StringUtils.EMPTY;
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
                String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
                break;
            default:
                break;
        }
        PVCommonLogic.customizePeriod(commonColumn, varibaleCat, selection, pvDTO, format, currentIndex, obj, format.equals(RATE_PER));
        List<Integer> vPriorList = selection.getProjIdList();
        for (int j = 0; j < vPriorList.size(); j++) {
            PVCommonLogic.getPriorCommonCustomization(varibaleCat, selection, obj, pvDTO, commonColumn + vPriorList.get(j), currentIndex, j, format.equals(RATE_PER), COLUMN_COUNT_DISCOUNT, format);
        }
        return pvDTO;
    }

    private void calculateDiscount() {
        String oldDiscount = StringUtils.EMPTY;
        String oldHierarchyNo = StringUtils.EMPTY;
        int count = PROCRAWLIST_DETAIL_DISCOUNT.size();
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

        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) PROCRAWLIST_DETAIL_DISCOUNT.get(i);
            String parentkey;
            if ("null".equals(String.valueOf(obj[obj.length - 1]))) {
                parentkey = obj[0].toString();
            } else {
                parentkey = obj[0].toString().endsWith(".") ? obj[0].toString() + "$" + obj[obj.length - 1].toString() : obj[0].toString() + ".$" + obj[obj.length - 1].toString();
            }
            if (i == 0) {
                oldHierarchyNo = parentkey;
            }
            String newHierarchyNo = parentkey;
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                newDiscount = String.valueOf(obj[NumericConstants.FOUR]);
                if (oldDiscount.equals(newDiscount)) {
                    if (selection.isVarDisAmount()) {
                        if (selection.isColValue()) {
                            selection.setConversionNeeded(true);
                            vDiscountDollarVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SEVEN, vDiscountDollarVal, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                             selection.setConversionNeeded(true);
                            discountDollarVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SEVEN, discountDollarVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                             selection.setConversionNeeded(false);
                            discountDollarPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SEVEN, discountDollarPercent, selection, RATE_PER);
                        }
                    }
                    //Discount %
                    if (selection.isVarDisRate()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vDiscountPerVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.TEN, vDiscountPerVal, selection, RATE_PER);
                        }
                        if (selection.isColVariance()) {
                            discountPerVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.TEN, discountPerVariance, selection, RATE_PER);
                        }
                        if (selection.isColPercentage()) {
                            vDiscountPerPer = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.TEN, vDiscountPerPer, selection, RATE_PER);
                        }
                    }
                    //RPU
                    if (selection.isVarRPU()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vRpuVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.THIRTEEN, vRpuVal, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            rpuVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.THIRTEEN, rpuVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            rpuPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.THIRTEEN, rpuPercent, selection, RATE_PER);
                        }
                    }
                    //Discount Exfactory Per Change
                    if (selection.isDiscountPerExFactory()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            discountPerExfacValue = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SIXTEEN, discountPerExfacValue, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            discountPerExfacVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SIXTEEN, discountPerExfacVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            discountPerExfacPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SIXTEEN, discountPerExfacPercent, selection, RATE_PER);
                        }
                    }
                } else if (i == 0) {
                    vDiscountDollarVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountDollarVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountDollarPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    vDiscountPerVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountPerVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    vDiscountPerPer.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    vRpuVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    rpuVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    rpuPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    discountPerExfacValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountPerExfacVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountPerExfacPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    if (selection.isVarDisAmount()) {
                        if (selection.isColValue()) {
                            selection.setConversionNeeded(true);
                            vDiscountDollarVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SEVEN, vDiscountDollarVal, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            selection.setConversionNeeded(true);
                            discountDollarVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SEVEN, discountDollarVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            selection.setConversionNeeded(false);
                            discountDollarPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SEVEN, discountDollarPercent, selection, RATE_PER);
                        }
                    }
                    //Discount %
                    if (selection.isVarDisRate()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vDiscountPerVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.TEN, vDiscountPerVal, selection, RATE_PER);
                        }
                        if (selection.isColVariance()) {
                            discountPerVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.TEN, discountPerVariance, selection, RATE_PER);
                        }
                        if (selection.isColPercentage()) {
                            vDiscountPerPer = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.TEN, vDiscountPerPer, selection, RATE_PER);
                        }
                    }
                    //RPU
                    if (selection.isVarRPU()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vRpuVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.THIRTEEN, vRpuVal, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            rpuVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.THIRTEEN, rpuVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            rpuPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.THIRTEEN, rpuPercent, selection, RATE_PER);
                        }
                    }
                    //Discount Exfactory Per Change
                    if (selection.isDiscountPerExFactory()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            discountPerExfacValue = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SIXTEEN, discountPerExfacValue, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            discountPerExfacVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SIXTEEN, discountPerExfacVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            discountPerExfacPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SIXTEEN, discountPerExfacPercent, selection, RATE_PER);
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
                    vDiscountDollarVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountDollarVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountDollarPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    vDiscountPerVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountPerVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    vDiscountPerPer.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    vRpuVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    rpuVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    rpuPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    discountPerExfacValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountPerExfacVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                    discountPerExfacPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                    if (selection.isVarDisAmount()) {
                        if (selection.isColValue()) {
                            selection.setConversionNeeded(true);
                            vDiscountDollarVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SEVEN, vDiscountDollarVal, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            selection.setConversionNeeded(true);
                            discountDollarVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SEVEN, discountDollarVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            selection.setConversionNeeded(false);
                            discountDollarPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SEVEN, discountDollarPercent, selection, RATE_PER);
                        }
                    }
                    //Discount %
                    if (selection.isVarDisRate()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vDiscountPerVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.TEN, vDiscountPerVal, selection, RATE_PER);
                        }
                        if (selection.isColVariance()) {
                            discountPerVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.TEN, discountPerVariance, selection, RATE_PER);
                        }
                        if (selection.isColPercentage()) {
                            vDiscountPerPer = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.TEN, vDiscountPerPer, selection, RATE_PER);
                        }
                    }
                    //RPU
                    if (selection.isVarRPU()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            vRpuVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.THIRTEEN, vRpuVal, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            rpuVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.THIRTEEN, rpuVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            rpuPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.THIRTEEN, rpuPercent, selection, RATE_PER);
                        }
                    }
                    //Discount Exfactory Per Change
                    if (selection.isDiscountPerExFactory()) {
                        selection.setConversionNeeded(false);
                        if (selection.isColValue()) {
                            discountPerExfacValue = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SIXTEEN, discountPerExfacValue, selection, AMOUNT);
                        }
                        if (selection.isColVariance()) {
                            discountPerExfacVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SIXTEEN, discountPerExfacVariance, selection, AMOUNT);
                        }
                        if (selection.isColPercentage()) {
                            discountPerExfacPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SIXTEEN, discountPerExfacPercent, selection, RATE_PER);
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

                vDiscountDollarVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                discountDollarVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                discountDollarPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                vDiscountPerVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                discountPerVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                vDiscountPerPer.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                vRpuVal.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                rpuVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                rpuPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                discountPerExfacValue.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                discountPerExfacVariance.setGroup(String.valueOf(obj[NumericConstants.FOUR]));
                discountPerExfacPercent.setGroup(String.valueOf(obj[NumericConstants.FOUR]));

                newDiscount = String.valueOf(obj[NumericConstants.FOUR]);

                if (selection.isVarDisAmount()) {
                    if (selection.isColValue()) {
                        selection.setConversionNeeded(true);
                        vDiscountDollarVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SEVEN, vDiscountDollarVal, selection, AMOUNT);
                    }
                    if (selection.isColVariance()) {
                        selection.setConversionNeeded(true);
                        discountDollarVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SEVEN, discountDollarVariance, selection, AMOUNT);
                    }
                    if (selection.isColPercentage()) {
                        selection.setConversionNeeded(false);
                        discountDollarPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SEVEN, discountDollarPercent, selection, RATE_PER);
                    }
                }
                //Discount %
                if (selection.isVarDisRate()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        vDiscountPerVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.TEN, vDiscountPerVal, selection, RATE_PER);
                    }
                    if (selection.isColVariance()) {
                        discountPerVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.TEN, discountPerVariance, selection, RATE_PER);
                    }
                    if (selection.isColPercentage()) {
                        vDiscountPerPer = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.TEN, vDiscountPerPer, selection, RATE_PER);
                    }
                }
                //RPU
                if (selection.isVarRPU()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        vRpuVal = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.THIRTEEN, vRpuVal, selection, AMOUNT);
                    }
                    if (selection.isColVariance()) {
                        rpuVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.THIRTEEN, rpuVariance, selection, AMOUNT);
                    }
                    if (selection.isColPercentage()) {
                        rpuPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.THIRTEEN, rpuPercent, selection, RATE_PER);
                    }
                }
                //Discount Exfactory Per Change
                if (selection.isDiscountPerExFactory()) {
                    selection.setConversionNeeded(false);
                    if (selection.isColValue()) {
                        discountPerExfacValue = calculateDiscountPeriod(Constants.VALUE, obj, NumericConstants.SIXTEEN, discountPerExfacValue, selection, AMOUNT);
                    }
                    if (selection.isColVariance()) {
                        discountPerExfacVariance = calculateDiscountPeriod(Constants.VARIANCE, obj, NumericConstants.SIXTEEN, discountPerExfacVariance, selection, AMOUNT);
                    }
                    if (selection.isColPercentage()) {
                        discountPerExfacPercent = calculateDiscountPeriod(Constants.CHANGE, obj, NumericConstants.SIXTEEN, discountPerExfacPercent, selection, RATE_PER);
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
            if (i == count - 1) {
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

    public String getFormattedValuePercent(DecimalFormat FORMAT, String value) {
        if (value.contains(NULL.getConstant())) {
            value = FORMAT.format(Double.valueOf(ZERO));
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    private void customizeDiscountPivot() {
        int count = PROCRAWLIST_DETAIL_DISCOUNT.size();
        String oldHierarchyNo = StringUtils.EMPTY;
        String discountNo;
        String newyear;
        String oldYear = StringUtils.EMPTY;
        String newPeriod;
        String oldPeriod = StringUtils.EMPTY;
        String commonColumn = StringUtils.EMPTY;
        List<Integer> projList = selection.getProjIdList();
        ProjectionVarianceDTO discountDto = new ProjectionVarianceDTO();
        List<String> discountNames = new ArrayList<>(selection.getDiscountLevel().equals("Program") ? selection.getDiscountNameList() : selection.getDiscountNameCFF());
        for (int i = 0; i < discountNames.size(); i++) {
            String name = String.valueOf(discountNames.get(i)).replaceAll(" ", StringUtils.EMPTY);
            discountNameMap.put(name, String.valueOf(i));
        }

        Map<String, ProjectionVarianceDTO> periodDiscountMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Object[] obj = (Object[]) PROCRAWLIST_DETAIL_DISCOUNT.get(i);
            if (i == 0) {
                oldHierarchyNo = String.valueOf(obj[0]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));;
            }
            String newHierarchyNo = String.valueOf(obj[0]) + ("null".equals(String.valueOf(obj[obj.length - 1])) ? StringUtils.EMPTY : "$" + String.valueOf(obj[obj.length - 1]));;
            newyear = String.valueOf(obj[NumericConstants.TWO]);
            newPeriod = String.valueOf(obj[NumericConstants.THREE]);
            /* Below If condition used to check next hierarchy No is same with old hierarchy No*/
            if (oldHierarchyNo.equals(newHierarchyNo)) {
                if (oldYear.equals(newyear) && newPeriod.equals(oldPeriod)) {
                    String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                    discount = getDiscountName(discount);
                    setBaseValue(discountDto, obj, discount);
                    customizePriorList(discountDto, projList, discount, obj);

                } else if (i == 0) {
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn = getVisibleColumnHeader(obj);
                    String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                    discount = getDiscountName(discount);
                    setBaseValue(discountDto, obj, discount);
                    customizePriorList(discountDto, projList, discount, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                } else {

                    /*New discount means add at List */
                    periodDiscountMap.put(commonColumn, discountDto);
                    discountDto = new ProjectionVarianceDTO();
                    commonColumn = getVisibleColumnHeader(obj);
                    String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                    discount = getDiscountName(discount);
                    setBaseValue(discountDto, obj, discount);
                    customizePriorList(discountDto, projList, discount, obj);

                    oldYear = newyear;
                    oldPeriod = newPeriod;

                }
            } else {
                periodDiscountMap.put(commonColumn, discountDto);
                pivotDiscountMap.put(oldHierarchyNo, periodDiscountMap);

                periodDiscountMap = new HashMap<>();
                discountDto = new ProjectionVarianceDTO();
                commonColumn = getVisibleColumnHeader(obj);
                String discount = String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY);
                discount = getDiscountName(discount);
                setBaseValue(discountDto, obj, discount);
                customizePriorList(discountDto, projList, discount, obj);

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

    private String getDiscountNo(String discountName) {
        String number = String.valueOf(discountNameMap.get(discountName));
        if ("null".equals(number) || number.equals("NoNumber")) {
            return StringUtils.EMPTY;
        }
        return number;
    }

    private String getDiscountName(String discountName) {
        if ("RETURNS".equals(discountName)) {
            return "Returns";
        }
        return discountName;
    }

    private void customizePriorList(ProjectionVarianceDTO discountDto, List<Integer> projList, String discount, Object[] obj) {
        if (!projList.isEmpty()) {
            for (int j = 1; j <= projList.size(); j++) {
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountAmount", NumericConstants.SEVEN, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), AMOUNT,true);
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountSales", NumericConstants.TEN, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), RATE,false);
                calculatePivotDiscountPrior(discountDto, obj, discount, "RPU", NumericConstants.THIRTEEN, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), AMOUNT,false);
                calculatePivotDiscountPrior(discountDto, obj, discount, "DiscountPerExFactory", NumericConstants.SIXTEEN, (j * COLUMN_COUNT_DISCOUNT), projList.get(j - 1), RATE,false);

            }
        }
    }

    private void calculatePivotDiscountPrior(ProjectionVarianceDTO discountDto, Object[] obj, String discount, String discountColumn, int currentIndex, int priorIndex, int projId, DecimalFormat format,boolean isConversionNeeded) {
        String discountVarCurrent;
        selection.setConversionNeeded(isConversionNeeded);
        String discountNo = getDiscountNo(discount);
        boolean isPer = format.equals(RATE) || format.equals(RATE_PER);
        priorIndex = currentIndex + priorIndex;
        String visibleColumn = discountColumn + VAL + discount + discountNo + projId;
        String priorValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[priorIndex])));
        String val = selection.isConversionNeeded() && !isPer ? CommonUtils.getConversionFormattedValue(selection, priorValue, true)
                    : getFormattedValue(format, priorValue);
        discountDto.addStringProperties(visibleColumn, isPer ? val + PERCENT : val);

        if (actualBasis) {
            String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 1])));
            String variance = PVCommonLogic.getVariance(actualValue, priorValue, isPer ? RATE : AMOUNT,selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + StringConstantsUtil.VARIANCE_LABEL + discount + discountNo + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(actualValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);
        } else if (accrualBasis) {
            String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 2])));
            String variance = PVCommonLogic.getVariance(accrualValue, priorValue, isPer ? RATE : AMOUNT,selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + StringConstantsUtil.VARIANCE_LABEL + discount + discountNo + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(accrualValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);
        } else {
            String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex])));
            String variance = PVCommonLogic.getVariance(currentValue, priorValue, isPer ? RATE : AMOUNT,selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + StringConstantsUtil.VARIANCE_LABEL + discount + discountNo + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(currentValue, priorValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);
        }
    }

    private void setBaseValue(ProjectionVarianceDTO discountDto, Object[] obj, String discount) {

        calculatePivotDiscount(discountDto, obj, discount, "DiscountAmount", NumericConstants.SEVEN, selection.getCurrentProjId(), AMOUNT);
        calculatePivotDiscount(discountDto, obj, discount, "DiscountSales", NumericConstants.TEN, selection.getCurrentProjId(), RATE_PER);
        calculatePivotDiscount(discountDto, obj, discount, "RPU", NumericConstants.THIRTEEN, selection.getCurrentProjId(), AMOUNT);
        calculatePivotDiscount(discountDto, obj, discount, "DiscountPerExFactory", NumericConstants.SIXTEEN, selection.getCurrentProjId(), RATE_PER);

    }

    private void calculatePivotDiscount(ProjectionVarianceDTO discountDto, Object[] obj, String discount, String discountColumn, int currentIndex, int projId, DecimalFormat format) {
        String discountNo = getDiscountNo(discount);
        boolean isPer = format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE);
        String visibleColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY) + discountNo + CURRENT + projId;
        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex])));
        String Value = selection.isConversionNeeded() && !isPer ? CommonUtils.getConversionFormattedValue(selection, currentValue, true)
                        : getFormattedValue(format, currentValue);
        discountDto.addStringProperties(visibleColumn, isPer ? Value + PERCENT : Value);
        String actualColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY) + discountNo + ACTUAL + projId;
        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 1])));
        String Value1 = selection.isConversionNeeded() && !isPer
                        ? CommonUtils.getConversionFormattedValue(selection, actualValue, true)
                        : getFormattedValue(format, actualValue);
        discountDto.addStringProperties(actualColumn, isPer ? Value1 + PERCENT : Value1);
        String accrualColumn = discountColumn + VAL + String.valueOf(obj[NumericConstants.FOUR]).replaceAll(" ", StringUtils.EMPTY) + discountNo + ACCRUAL + projId;
        String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[currentIndex - 2])));
        String accValue =  selection.isConversionNeeded() && !isPer
                        ? CommonUtils.getConversionFormattedValue(selection, accrualValue, true)
                        : getFormattedValue(format, accrualValue);
        discountDto.addStringProperties(accrualColumn, isPer ? accValue + PERCENT : accValue);

        if (actualBasis) {
            String discountVarCurrent;
            String variance = PVCommonLogic.getVariance(actualValue, currentValue, format,selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + StringConstantsUtil.VARIANCE_LABEL + discount + discountNo + CURRENT + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(actualValue, currentValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + CURRENT + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);

        } else if (accrualBasis) {
            String discountVarCurrent;
            String variance = PVCommonLogic.getVariance(accrualValue, currentValue, format,selection);
            if (discountColumn.contains("RPU")) {
                discountVarCurrent = discountColumn + StringConstantsUtil.VARIANCE_LABEL + discount + discountNo + CURRENT + projId;
            } else {
                discountVarCurrent = discountColumn + VAR + discount + discountNo + CURRENT + projId;
            }
            discountDto.addStringProperties(discountVarCurrent, isPer ? variance + PERCENT : variance);

            String discountPer = PVCommonLogic.getPerChange(accrualValue, currentValue, RATE_PER);
            String discountVarPer = discountColumn + PER + discount + discountNo + CURRENT + projId;
            discountDto.addStringProperties(discountVarPer, discountPer + PERCENT);

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
            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
            commonColumn = monthName.toLowerCase() + obj[NumericConstants.TWO];
        }
        return commonColumn;
    }

    private void calculateDetailPivotForCustomView(List<Object> rawList) {
        String appendedParentKey = "";
        for (int i = 0; i < rawList.size(); i++) {
            Object[] obj = (Object[]) rawList.get(i);
            String key;
            if (isTotal) {
                key = TOTAL1;
            } else if (isCustomView) {
                key = obj[NumericConstants.TWO].toString();
                key = key + appendedParentKey;
            } else {
                key = obj[NumericConstants.TWO].toString();
            }
            List<ProjectionVarianceDTO> pvList = resultMap.get(key);
            List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, Integer.valueOf(obj[0].toString()), Integer.valueOf(obj[1].toString()));
            String groupId = common.get(1);
            ProjectionVarianceDTO freVarianceDTO = new ProjectionVarianceDTO();
            freVarianceDTO.setGroup(groupId);
            if (pvList == null) {
                //To check condition total or details values
                pvList = new ArrayList();
                addListPivot(pvList, obj, freVarianceDTO, index, key, groupId);
                appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
                if (isCustomView && !isTotal) {
                    customHierarchyAndTPKeys(obj, key, pvList);
                } else {
					hierarchyAndTPkeys(obj, key, pvList);
                }
            } else if (obj[obj.length - 1] != null && !isTotal) {
                Object[] temp = rawList.get(i - 1) == null ? new Object[0] : (Object[]) rawList.get(i - 1);
                String currValue = obj[obj.length - 1].toString();
                String tempValue = temp[temp.length - 1] == null ? "oldtempValue"
                        : temp[temp.length - 1].toString();
                if (!currValue.equals(tempValue)) {
                    pvList = new ArrayList();
                    addListPivot(pvList, obj, freVarianceDTO, index, key, groupId);
                    if (isCustomView && !isTotal) {
                        customHierarchyAndTPKeys(obj, key, pvList);
                    } else {
						hierarchyAndTPkeys(obj, key, pvList);
                    }
                    appendedParentKey = obj[obj.length - 1] == null ? "" : "$" + obj[obj.length - 1].toString();
                } else {
                    updateListPivot(pvList, obj, freVarianceDTO, index, key, groupId);
                }
            } else {
                updateListPivot(pvList, obj, freVarianceDTO, index, key, groupId);
            }
        }
    }

    private void customHierarchyAndTPKeys(Object[] obj, String key, List<ProjectionVarianceDTO> pvList) {
        String parentKey = obj[obj.length - 1] == null ? null : obj[obj.length - 1].toString();
        String newKey;
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
        if ("Customer".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))
                || "Trading Partner".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO]))) {
            tradingPartnerKeys.add(key);
        }
    }



}
