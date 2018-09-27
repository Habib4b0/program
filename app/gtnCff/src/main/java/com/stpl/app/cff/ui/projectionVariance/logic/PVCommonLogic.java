/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.logic;

import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERCENT;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class PVCommonLogic {

    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private static final String ZERO = "0";
    private static final String PER_FORMAT = "#,##0.00";
    private static final DecimalFormat RATE_PER = new DecimalFormat(PER_FORMAT);
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat(PER_FORMAT);
    private static final String CURRENT = "Current";
    private static final String ACCRUAL = "Accrual";
    private static final String ACTUAL = "Actual";
    private static final String ACCRUAL_DASH = "-";
    public static final Logger LOGGER = LoggerFactory.getLogger(PVCommonLogic.class);
    private static final String COMPARISON_CURRENT = "Current Projection";
    private static boolean priorComparison = false;
    
    private PVCommonLogic() {
        LOGGER.debug("Entering PVCommonLogic ");
    }

    public static void customizePeriod(String commonColumn, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, DecimalFormat format, int index, Object[] obj, boolean isPer) {
        try {
            String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 2])));
            String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
            String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));

            addPeriodProperties(variableCategory, currentValue, format,commonColumn, pvsdto, pvDTO, isPer, actualValue, accrualValue);

        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
    }
     public static void customizePeriodV2(String commonColumn, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, 
             DecimalFormat format, int index, Object[] actual,Object[] proj, boolean isPer,boolean isExcel) {
        try {
            String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + actual[isExcel ? (actual.length - NumericConstants.THREE):(actual.length -  NumericConstants.TWO)])));
            String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + proj[index])));
            String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + actual[index])));

            addPeriodProperties(variableCategory, currentValue, format, commonColumn, pvsdto, pvDTO, isPer, actualValue, accrualValue);

        } catch (Exception e) {
            LOGGER.error("{}",e);
        }
    }
     
     public static void customizePeriodDiscountV2(String commonColumn, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, 
             DecimalFormat format, int index, Object[] actual,Object[] proj, boolean isPer) {
        try {
            String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + actual[actual.length - NumericConstants.THREE])));
            String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + proj[index])));
            String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + actual[index])));

            addPeriodProperties(variableCategory, currentValue, format, commonColumn, pvsdto, pvDTO, isPer, actualValue, accrualValue);

        } catch (Exception e) {
            LOGGER.error("{}",e);
        }
    }

    private static void addPeriodProperties(String variableCategory, String currentValue, DecimalFormat format,
            String commonColumn, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, boolean isPer, String actualValue, String accrualValue) {
        variableValueCustomization(variableCategory, currentValue, format, commonColumn + CURRENT + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
        variableValueCustomization(variableCategory, actualValue, format, commonColumn + ACTUAL + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
        if (!nullCheck(StringUtils.EMPTY + accrualValue)) {
            variableValueCustomization(variableCategory, accrualValue, format, commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
        } else {
            pvDTO.addStringProperties(commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), ACCRUAL_DASH);
        }
        
        switch (pvsdto.getComparisonBasis()) {
            
            case StringConstantsUtil.ACTUALS1:
                comparisonBasisCustomization(variableCategory, currentValue, actualValue, format, commonColumn + CURRENT + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
                comparisonBasisCustomization(variableCategory, accrualValue, actualValue, format, commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
                break;
            case StringConstantsUtil.ACCRUALS:
                if (!nullCheck(StringUtils.EMPTY +accrualValue)) {
                    comparisonBasisCustomization(variableCategory, currentValue, accrualValue, format, commonColumn + CURRENT + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
                    comparisonBasisCustomization(variableCategory, actualValue, accrualValue, format, commonColumn + ACTUAL + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
                }
                break;
            case COMPARISON_CURRENT:
                comparisonBasisCustomization(variableCategory, actualValue, currentValue, format, commonColumn + ACTUAL + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
                comparisonBasisCustomization(variableCategory, accrualValue, currentValue, format, commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), pvsdto, pvDTO, isPer);
                break;
            default:
                break;
        }
    }

    static void getPriorCommonCustomization(String variableCategory, PVSelectionDTO pvsdto, final Object[] row, ProjectionVarianceDTO projDTO,
            String commonColumn, int index, int priorIndex, final Boolean isPer, int columnCountTotal, DecimalFormat format) {
        LOGGER.debug("Inside getPivotCommonCustomization");
        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
        String visibleColumn = commonColumn + priorList.get(priorIndex);
        String comparisonPriorVal = StringUtils.EMPTY;
        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index + ((priorIndex + 1) * columnCountTotal)])));
        boolean comparisonBasis = !StringConstantsUtil.ACTUALS1.equalsIgnoreCase(pvsdto.getComparisonBasis())
                && !StringConstantsUtil.ACCRUALS.equalsIgnoreCase(pvsdto.getComparisonBasis()) && !COMPARISON_CURRENT.equalsIgnoreCase(pvsdto.getComparisonBasis());
        String actValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 1])));
        String accrValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 2])));
        String currValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])));

        if (comparisonBasis) {
            comparisonPriorVal = String.valueOf(Double.parseDouble(isNull(StringUtils.EMPTY + 
                    row[index + ((Integer.parseInt(pvsdto.getComparisonBasis()) + 1) * columnCountTotal)])));
            priorComparison = visibleColumn.equals(commonColumn + pvsdto.getProjIdList().get(Integer.parseInt(pvsdto.getComparisonBasis())));
        }
        priorVraibleValueCustomization(variableCategory, priorVal, format, visibleColumn, pvsdto, 
                projDTO, isPer, actValue, accrValue, currValue, comparisonPriorVal, commonColumn);
        LOGGER.debug("Ending getPivotCommonCustomization");
    }
    
    static void getPriorCommonCustomizationV2(String variableCategory, PVSelectionDTO pvsdto, final List<Object[]> rows, ProjectionVarianceDTO projDTO,
            String commonColumn, int index, int priorIndex, final Boolean isPer, DecimalFormat format,boolean isExcel) {
        LOGGER.debug("Inside getPivotCommonCustomization");
        int currentProjectionId = pvsdto.getCurrentProjId();

        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
        int priorProjId = priorList.get(priorIndex);
        int comparisonprojId = 0;

        String visibleColumn = commonColumn + priorProjId;
        String comparisonPriorVal = StringUtils.EMPTY;
        String priorVal = StringUtils.EMPTY;
        boolean comparisonBasis = !StringConstantsUtil.ACTUALS1.equalsIgnoreCase(pvsdto.getComparisonBasis())
                && !StringConstantsUtil.ACCRUALS.equalsIgnoreCase(pvsdto.getComparisonBasis()) && !COMPARISON_CURRENT.equalsIgnoreCase(pvsdto.getComparisonBasis());
        
        if(comparisonBasis ){
           comparisonprojId =  pvsdto.getProjIdList().get(Integer.parseInt(pvsdto.getComparisonBasis()));
        }
        excelCustomization(isExcel, rows);
        String actValue = StringUtils.EMPTY;
        String accrValue = StringUtils.EMPTY;
        String currValue = StringUtils.EMPTY;
        for (Object[] row : rows) {
            int projectionId = Integer.parseInt(String.valueOf(row[0]));
            boolean isActual = Integer.parseInt(String.valueOf(row[row.length - 1])) == 0;
            if (projectionId == currentProjectionId) {
                if (isActual) {
                    actValue = getDoubleValue(row[index]);
                } else {
                    currValue = getDoubleValue(row[index]);
                }
            } else if (projectionId == priorProjId && !isActual) {
                priorVal = getDoubleValue(row[index]);
            } else if (projectionId == comparisonprojId && comparisonBasis && !isActual) {
                comparisonPriorVal = getDoubleValue(row[index]);
                priorComparison = visibleColumn.equals(commonColumn + comparisonprojId);
            }
        }
        priorVraibleValueCustomization(variableCategory, priorVal, format, visibleColumn, pvsdto,
                projDTO, isPer, actValue, accrValue, currValue, comparisonPriorVal, commonColumn);
        LOGGER.debug("Ending getPivotCommonCustomization");
    }

    public static void excelCustomization(boolean isExcel, final List<Object[]> rows) {
        if (isExcel) {
            Map<Object, List<Object[]>> groupedResult = rows.stream().map(obj -> (Object[]) obj)
                    .collect(Collectors.groupingBy(x -> {
                        return new ArrayList<>(Arrays.asList(x[1], x[3], x[4]));
                    }));
            List<Object[]> list1 = new ArrayList<>();
            for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
                list1.addAll(entry.getValue());
            }
            rows.addAll(list1);
        }
    }
    
    private static void priorVraibleValueCustomization(String variableCategory, String priorVal, DecimalFormat format, String visibleColumn, PVSelectionDTO pvsdto, ProjectionVarianceDTO projDTO, final Boolean isPer, String actValue, String accrValue, String currValue, String comparisonPriorVal, String commonColumn) {
        variableValueCustomization(variableCategory, priorVal, format, visibleColumn, pvsdto, projDTO, isPer);
        switch (pvsdto.getComparisonBasis()) {
            
            case StringConstantsUtil.ACTUALS1:
                comparisonBasisCustomization(variableCategory, priorVal, actValue, format, visibleColumn, pvsdto, projDTO, isPer);
                break;
            case StringConstantsUtil.ACCRUALS:
                comparisonBasisCustomization(variableCategory, priorVal, accrValue, format, visibleColumn, pvsdto, projDTO, isPer);
                break;
            case COMPARISON_CURRENT:
                comparisonBasisCustomization(variableCategory, priorVal, currValue, format, visibleColumn, pvsdto, projDTO, isPer);
                break;
            default:
                if (!priorComparison) {
                    comparisonBasisCustomization(variableCategory, priorVal, comparisonPriorVal, format, visibleColumn, pvsdto, projDTO, isPer);
                }
                comparisonBasisCustomization(variableCategory, currValue, comparisonPriorVal, format, commonColumn + CURRENT + pvsdto.getCurrentProjId(), pvsdto, projDTO, isPer);
                comparisonBasisCustomization(variableCategory, actValue, comparisonPriorVal, format, commonColumn + ACTUAL + pvsdto.getCurrentProjId(), pvsdto, projDTO, isPer);
                comparisonBasisCustomization(variableCategory, accrValue, comparisonPriorVal, format, commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), pvsdto, projDTO, isPer);
                break;
                
        }
    }

    public static String isNull(String value) {
        String valueCommon = value;
        if (valueCommon.contains(NULL.getConstant()) || valueCommon.isEmpty() ){
            valueCommon = ZERO;
        }
        return valueCommon;
    }
    public static String getDoubleValue(Object value) {
        String result=String.valueOf(value);
        if (value==null || result.isEmpty()) {
            result = ZERO;
        }
        return  Double.toString(Double.parseDouble(result));
    }

    public static Boolean nullCheck(String value) {
        if (value.contains(NULL.getConstant())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static String getVariance(String actualValue, String priorVal, DecimalFormat format, final PVSelectionDTO selectionDto) {
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
            value = String.valueOf(roundToFraction((val - val1), 10000));
            value = roundToFraction(Double.parseDouble(value), 100) + "";
            value = getFormattedValue(format, value);
        } else {
            variance = String.valueOf(Double.parseDouble(isNull(actualValue)) - Double.parseDouble(isNull(priorVal)));
            value = selectionDto.isConversionNeeded() ? CommonUtils.getConversionFormattedValue(selectionDto, variance, true) : getFormattedValue(format, variance);

        }
        return value;
    }

    public static String getPerChange(String actualValue, String priorVal, DecimalFormat format) {
        DecimalFormat formatter = new DecimalFormat(PER_FORMAT);
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
            value = String.valueOf(roundToFraction((val - val1), 10000));
            value = roundToFraction(Double.parseDouble(value), 100) + "";
        } else {
            variance = String.valueOf(Double.parseDouble(isNull(actualValue)) - Double.parseDouble(isNull(priorVal)));
            value = getFormattedValue(formatter, variance);
        }
        String priorval = getFormattedValue(formatter, priorVal);

        value = value.replace(",", StringUtils.EMPTY);
        priorval = priorval.replace(",", StringUtils.EMPTY);
        Double perChange = Double.parseDouble(value) / Double.parseDouble(priorval);
        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
            perChange = 0.0;
        }
        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
        String change = String.valueOf(perChange);
        if (change.contains("E")) {
            change = change.substring(0, 3);
        }
        change = getFormattedValue(format, change);
        return change;
    }

    public static double roundToFraction(double x, long fraction) {
        return (double) Math.round(x * fraction) / fraction;
    }

    public static String getFormattedValue(DecimalFormat format, String value) {
        if (value.contains(NULL.getConstant())|| value.isEmpty()) {
            value = ZERO;
        } else {
            value = format.format(Double.valueOf(value));
        }
        return value;
    }

    public static void comparisonBasisCustomization(String varibaleCat, String commonValue, String currentValue, DecimalFormat format, String commonColumn, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, boolean isPer) {
        if (varibaleCat.equals(Constants.VARIANCE)) {
            // for CURRENT
            String val = getVariance(commonValue, currentValue, format, pvsdto);
            pvDTO.addStringProperties(commonColumn, isPer ? val + PERCENT : val);
        }
        if (varibaleCat.equals(Constants.CHANGE)) {
            //for CURRENT
            String val = getPerChange(commonValue, currentValue, format);
            pvDTO.addStringProperties(commonColumn, val + PERCENT);
        }
    }

    public static void variableValueCustomization(String varibaleCat, String commonValue, DecimalFormat format, String commonColumn, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, boolean isPer) {
        if (varibaleCat.equals(Constants.VALUE)) {
            // for CURRENT
            String baseValue = pvsdto.isConversionNeeded() && !isPer ? CommonUtils.getConversionFormattedValue(pvsdto, commonValue, true)
                    : getFormattedValue(format, commonValue);
            pvDTO.addStringProperties(commonColumn, isPer ? baseValue + PERCENT : baseValue);
        }
    }

}
