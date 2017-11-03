/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.logic;

import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import static com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic.LOGGER;
import static com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic.TWO_DECIMAL_FORMAT;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class PVCommonLogic {

    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final String ZERO = "0";
    private static final DecimalFormat RATE_PER = new DecimalFormat(TWO_DECIMAL_FORMAT);
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat(TWO_DECIMAL_FORMAT);
    private static final String CURRENT = "Current";
    private static String ACCRUAL = "Accrual";
    private static String ACTUAL = "Actual";
    private static String actualDASH = "-";

    static void getPriorCommonCustomization(String variableCategory, PVSelectionDTO pvsdto, final Object[] row, ProjectionVarianceDTO projDTO, String column, int index, int priorIndex, final Boolean isPer, int columnCountTotal, DecimalFormat format) {
        LOGGER.debug("Inside getPivotCommonCustomization");
        String visibleColumn;

        String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * columnCountTotal)];
        boolean actualBasis = Constant.ACTUALS.equalsIgnoreCase(pvsdto.getComparisonBasis());
        boolean accrualBasis = Constant.ACCRUALS.equalsIgnoreCase(pvsdto.getComparisonBasis());
        boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + row[index - 1]);
        String actValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 1])));
        String accrValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 2])));
        String currValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])));
        if (variableCategory.equalsIgnoreCase(Constant.VALUE)) {
            visibleColumn = column;
            //String baseValue = getFormattedValue(format, priorVal);
            String baseValue = pvsdto.isConversionNeeded() ? !isPer
                    ? CommonUtil.getConversionFormattedValue(pvsdto, priorVal, true)
                    : getFormattedValue(format, priorVal)
                    : getFormattedValue(format, priorVal);
            projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
        } else if (variableCategory.equalsIgnoreCase(Constant.VARIANCE)) {
            visibleColumn = column;
            if (actualBasis) {
                if (!actualCheck) {
                    String baseValue = getVariance(actValue, priorVal, format, pvsdto);
                    projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                }
            } else if (accrualBasis) {
                String baseValue = getVariance(accrValue, priorVal, format, pvsdto);
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
            } else {
                String baseValue = getVariance(currValue, priorVal, format, pvsdto);
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
            }

        } else {
            visibleColumn = column;
            if (actualBasis) {
                if (!actualCheck) {
                    String baseValue = getPerChange(actValue, priorVal, format);
                    projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                }
            } else if (accrualBasis) {
                String baseValue = getPerChange(accrValue, priorVal, format);
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
            } else {
                String baseValue = getPerChange(currValue, priorVal, format);
                projDTO.addStringProperties(visibleColumn, baseValue + PERCENT);
            }
        }
        LOGGER.debug("Ending getPivotCommonCustomization");
    }

    public static String isNull(String value) {
        if (value.contains(NULL.getConstant())) {
            value = ZERO;
        }
        return value;
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
            value = roundToFraction(Double.valueOf(value), 100) + "";
            value = getFormattedValue(format, value);
        } else {
            variance = String.valueOf(Double.valueOf(isNull(actualValue)) - Double.valueOf(isNull(priorVal)));
            value = selectionDto.isConversionNeeded() ? CommonUtil.getConversionFormattedValue(selectionDto, variance, true) : getFormattedValue(format, variance);
        }
        return value;
    }

    public static String getPerChange(String actualValue, String priorVal, DecimalFormat format) {
        DecimalFormat formatter = new DecimalFormat(TWO_DECIMAL_FORMAT);
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
            value = String.valueOf(roundToFraction((val - val1), 10000));
            value = roundToFraction(Double.valueOf(value), 100) + "";
        } else {
            variance = String.valueOf(Double.valueOf(isNull(actualValue)) - Double.valueOf(isNull(priorVal)));
            value = getFormattedValue(formatter, variance);
        }
        String priorval = getFormattedValue(formatter, priorVal);

        value = value.replace(",", StringUtils.EMPTY);
        priorval = priorval.replace(",", StringUtils.EMPTY);
        Double perChange = Double.valueOf(value) / Double.valueOf(priorval);
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

    public static String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(NULL.getConstant())) {
            value = ZERO;
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public static void comparisonBasisCustomization(String varibaleCat, String commonValue, String currentValue, DecimalFormat format, String commonColumn, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, boolean isPer, final PVSelectionDTO selectionDto) {
        if (varibaleCat.equals(Constant.VARIANCE)) {
            // for CURRENT
            String val = getVariance(commonValue, currentValue, format, selectionDto);
            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? val + PERCENT : val);
        }
        if (varibaleCat.equals(Constant.CHANGE)) {
            //for CURRENT
            String val = getPerChange(commonValue, currentValue, format);
            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);
        }
    }

    public static void customizePeriod(String commonColumn, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO pvDTO, DecimalFormat format, int index, Object[] obj, boolean isPer) {
        try {
            String accrualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 2])));
            String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
            String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
            boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[index - 1]);
            boolean actualBasis = Constant.ACTUALS.equalsIgnoreCase(pvsdto.getComparisonBasis());
            boolean accrualBasis = Constant.ACCRUALS.equalsIgnoreCase(pvsdto.getComparisonBasis());

            String commonValue = actualBasis ? actualValue : accrualValue;

            if (variableCategory.equalsIgnoreCase(Constant.VALUE)) {
                //for ACTUAL
               // String baseValue = !isPer || pvsdto.isConversionNeeded() ? CommonUtil.getConversionFormattedValue(pvsdto, actualValue, true) : getFormattedValue(format, actualValue);
               String baseValue = pvsdto.isConversionNeeded() ? !isPer
                        ? CommonUtil.getConversionFormattedValue(pvsdto, actualValue, true)
                        : getFormattedValue(format, actualValue)
                        : getFormattedValue(format, actualValue);
               if (!actualCheck) {
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                } else {
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                }
                //for CURRENT
               // baseValue = !isPer || pvsdto.isConversionNeeded() ? CommonUtil.getConversionFormattedValue(pvsdto, currentValue, true) : getFormattedValue(format, currentValue);
               baseValue = pvsdto.isConversionNeeded() ? !isPer
                        ? CommonUtil.getConversionFormattedValue(pvsdto, currentValue, true)
                        : getFormattedValue(format, currentValue)
                        : getFormattedValue(format, currentValue); 
               pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                //for Accrual
                if (!nullCheck(StringUtils.EMPTY + obj[index - 2])) {
                   // baseValue = !isPer || pvsdto.isConversionNeeded() ? CommonUtil.getConversionFormattedValue(pvsdto, accrualValue, true) :  getFormattedValue(format, accrualValue);
                   baseValue = pvsdto.isConversionNeeded() ? !isPer
                            ? CommonUtil.getConversionFormattedValue(pvsdto, accrualValue, true)
                            : getFormattedValue(format, accrualValue)
                            : getFormattedValue(format, accrualValue);
                    pvDTO.addStringProperties(commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                } else {
                    pvDTO.addStringProperties(commonColumn + ACCRUAL + pvsdto.getCurrentProjId(), actualDASH);
                }
            }
            if (actualBasis) {
                if (!actualCheck) {
                    comparisonBasisCustomization(variableCategory, commonValue, currentValue, format, commonColumn, pvsdto, pvDTO, isPer, pvsdto);
                } else if (variableCategory.equals(Constant.VALUE)) {
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                }
            } else if (accrualBasis) {
                comparisonBasisCustomization(variableCategory, commonValue, currentValue, format, commonColumn, pvsdto, pvDTO, isPer, pvsdto);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
