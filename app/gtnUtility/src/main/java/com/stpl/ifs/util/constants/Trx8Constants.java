package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sathya.Seelan
 */
public class Trx8Constants {

    private static final PropertiesReader.ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getSku() {
        return properties.getProperty("SKU");
    }

    public static String getBrand() {
        return properties.getProperty("BRAND");
    }

    public static String getExpandedBrand() {
        return properties.getProperty("EXPANDED_BRAND");
    }

    public static String getDescription() {
        return properties.getProperty("DESCRIPTION");
    }

    public static String getMaximum() {
        return properties.getProperty("MAXIMUM");
    }

    public static String get75thPercentile() {
        return properties.getProperty("75TH_PERCENTILE");
    }

    public static String get50thPercentile() {
        return properties.getProperty("50TH_PERCENTILE");
    }

    public static String get25thPercentile() {
        return properties.getProperty("25TH_PERCENTILE");
    }

    public static String getUpperOb() {
        return properties.getProperty("UPPER_OB");
    }

    public static String getLowerOb() {
        return properties.getProperty("LOWER_OB");
    }

    public static String getIs_Outliner() {
        return properties.getProperty("IS_OUTLINER");
    }

    public static String getOriginalSaleMonth() {
        return properties.getProperty("ORIGINAL_SALE_MONTH");
    }

    public static String getOriginalSaleUnits() {
        return properties.getProperty("ORIGINAL_SALE_UNITS");
    }

    public static String getOriginalSaleDollars() {
        return properties.getProperty("ORIGINAL_SALE_DOLLARS");
    }

    public static String getASP() {
        return properties.getProperty("ASP");
    }

    public static String getCumReturnUnits() {
        return properties.getProperty("CUM_RETURN_UNITS");
    }

    public static String getActualReturnRate() {
        return properties.getProperty("ACTUAL_RETURN_RATE");
    }

    public static String getWeightedAvgMonths() {
        return properties.getProperty("WEIGHTED_AVG_MONTHS");
    }

    public static String getFirstReturnDate() {
        return properties.getProperty("FIRST_RETURN_DATE");
    }

    public static String getLastReturnDate() {
        return properties.getProperty("LAST_RETURN_DATE");
    }

    public static String getMaxExpiredMonth() {
        return properties.getProperty("MAX_EXPIRED_MONTH");
    }

    public static String getCutOffDate() {
        return properties.getProperty("CUTOFF_DATE");
    }

    public static String getPastExpiration() {
        return properties.getProperty("PAST_EXPIRATION");
    }

    public static String getReturnComplete() {
        return properties.getProperty("RETURN_COMPLETE");
    }

    public static String getExpectedReturnRate() {
        return properties.getProperty("EXPECTED_RETURN_RATE");
    }

    public static String getEstimatedReturnUnits() {
        return properties.getProperty("ESTIMATED_RETURN_UNITS");
    }

    public static String getActualEstimatedReturnUnits() {
        return properties.getProperty("ACTUAL_ESTIMATED_RETURN_UNITS");
    }

    public static String getAdjEstimatedReturnUnits() {
        return properties.getProperty("ADJ_ESTIMATED_RETURN_UNITS");
    }

    public static String getValueatOriginalASP() {
        return properties.getProperty("VALUE_AT_ORIGINAL_ASP");
    }

    public static String getAdjValueatOriginalASP() {
        return properties.getProperty("ADJ_VALUE_AT_ORIGINAL_ASP");
    }

    public static String getMaxExpiredMonthsPlusCutOff() {
        return properties.getProperty("MAX_EXPIRED_MONTHS_PLUS_CUTOFF");
    }

    public static String getVersion() {
        return properties.getProperty("VERSION");
    }

    public static String getProjectedBalance() {
        return properties.getProperty("PROJECTED_BALANCE");
    }

    public static String getCurrentBalance() {
        return properties.getProperty("TRX7_CURRENT_BALANCE");
    }

    public static String getRatio() {
        return properties.getProperty("RATIO");
    }

    public static String getVariance() {
        return properties.getProperty("VARIANCE");
    }

    public static String getOverride() {
        return properties.getProperty("OVERRIDE");
    }

    public static String getAdjustment() {
        return properties.getProperty("ADJUSTMENT");
    }

    public static String getProjectedBalanceColumn() {
        return properties.getProperty("PROJECTED_BALANCE_COLUMN");
    }

    public static String getCurrentBalanceColumn() {
        return properties.getProperty("CURRENT_BALANCE_COLUMN");
    }

    public static String getRatioColumn() {
        return properties.getProperty("RATIO_COLUMN");
    }

    public static String getVarianceColumn() {
        return properties.getProperty("VARIANCE_COLUMN");
    }

    public static String getOverrideColumn() {
        return properties.getProperty("OVERRIDE_COLUMN");
    }

    public static String getAdjustmentColumn() {
        return properties.getProperty("ADJUSTMENT_COLUMN");
    }

    public static String getMethodology() {
        return properties.getProperty("METHODOLOGY");
    }

    public static String getMethodologyColumn() {
        return properties.getProperty("METHODOLOGY_COLUMN");
    }

    public static String getRate() {
        return properties.getProperty("RATE");
    }

    public static String getRateColumn() {
        return properties.getProperty("RATE_COLUMN");
    }
    
    public static String getTransaction8() {
        return properties.getProperty("TRANSACTION_8");
    } 
}
