/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.util;

import com.stpl.ifs.ui.DateToStringConverter;

/**
 *
 * @author srithar
 */
public class ConstantsUtil {

    public static final String ADD = "Add";
    public static final String DELETE = "Remove";
    public static final String EDIT = "Update";
    public static final String TRANSFER = "Transfer";
    public static final String CHECK_RECORD = "checkRecord";
    public static final String PROJECTION_ID = "projectionId";
    public static final String STATUS = "status";
    public static final String ITEM_START_DATE = "itemStartDate";
    public static final String ITEM_END_DATE = "itemEndDate";
    public static final String RS = "RS";
    public static final String PROJECTIONTRANSFER = "Projection Transfer";
    public static final String SPACE = " ";
    public static final String CURRENT_COONTRACT = "CurrentContract";
    public static final String TRANSFER_CONTRACT = "TransferContract";
    public static final String ALIGN_CENTER = "align-center";
    public static final String TRANSFER_SUMMARY = "TRANSFER SUMMARY";
    public static final String CURRENT_SUMMARY = "CURRENT SUMMARY";
    public static final String SUMMARY = "SUMMARY";
    public static final String REMOVE = "REMOVE";
    public static final String FILTERCOMBOBOX = "filterComboBox";
    public static final DateToStringConverter DATETOSTRINGCONVERTER = new DateToStringConverter();
    public static final String NUMERIC = "[0-9]*\\.{0,1}[0-9]*";
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String ITEM_ADD = "Item Add";
    public static final String ITEM_REMOVE = "Item Remove";
    public static final String ITEM_UPDATE = "Item Update";
    public static final String ITEM_TRANSFER = "Item Transfer";

    public enum FrequencyConstants {

        ANNUAL("Annual"),
        ANNUALLY("Annually"),
        YEAR("Year"),
        YEARS("Years"),
        SEMI_ANNUAL("Semi-Annual"),
        SEMI_ANNUALLY("Semi-Annually"),
        QUARTERLY("Quarterly"),
        MONTHLY("Monthly"),
        MONTHS("Months"),
        SELECT_ONE("-Select One-"),
        QUARTERS("Quarters");

        private String frequencyValue;

        private FrequencyConstants(String frequencyValue) {
            this.frequencyValue = frequencyValue;
        }

        public String getConstant() {
            return frequencyValue;
        }

        @Override
        public String toString() {
            return frequencyValue;
        }
    }

    public enum ProjectionConstants {

        FREQUENCY_DIVISION("frequencyDivision"),
        FREQUENCY_PERIOD("frequencyPeriod"),
        PERIOD("period"),
        HISTORY_START_PERIOD("historyStartPeriod"),
        HISTORY_START_PERIOD_DDLB("historyStartPeriodDDLB"),
        HISTORY_END_PERIOD("historyEndPeriod"),
        HISTORY_START_DATE("historyStartDate"),
        HISTORY_END_DATE("historyEndDate"),
        HISTORY_END_DATE_DDLB("historyEndDateDDLB"),
        HISTORY_START_YEAR("historyStartYear"),
        HISTORY_START_YEAR_DDLB("historyStartYearDDLB"),
        HISTORY_END_YEAR("historyEndYear"),
        HISTORY_START_MONTH("historyStartMonth"),
        HISTORY_START_MONTH_DDLB("historyStartMonthDDLB"),
        HISTORY_END_MONTH("historyEndMonth"),
        HISTORY_START_DAY("historyStartDay"),
        HISTORY_END_DAY("historyEndDay"),
        FORECAST_START_PERIOD("forecastStartPeriod"),
        FORECAST_END_PERIOD("forecastEndPeriod"),
        FORECAST_START_DATE("forecastStartDate"),
        FORECAST_END_DATE("forecastEndDate"),
        FORECAST_START_YEAR("forecastStartYear"),
        FORECAST_END_YEAR("forecastEndYear"),
        FORECAST_START_MONTH("forecastStartMonth"),
        FORECAST_END_MONTH("forecastEndMonth"),
        FORECAST_START_DAY("forecastStartDay"),
        FORECAST_END_DAY("forecastEndDay"),
        PROJECTION_START_PERIOD("projectionStartPeriod"),
        PROJECTION_START_PERIOD_DDLB("projectionStartPeriodDDLB"),
        PROJECTION_END_PERIOD("projectionEndPeriod"),
        PROJECTION_START_DATE("projectionStartDate"),
        PROJECTION_START_DATE_DDLB("projectionStartDateDDLB"),
        PROJECTION_END_DATE("projectionEndDate"),
        PROJECTION_START_YEAR("projectionStartYear"),
        PROJECTION_START_YEAR_DDLB("projectionStartYearDDLB"),
        PROJECTION_END_YEAR("projectionEndYear"),
        PROJECTION_START_MONTH("projectionStartMonth"),
        PROJECTION_START_MONTH_DDLB("projectionStartMonthDDLB"),
        PROJECTION_END_MONTH("projectionEndMonth"),
        PROJECTION_START_DAY("projectionStartDay"),
        PROJECTION_END_DAY("projectionEndDay");
        private String constant;

        private ProjectionConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }

    public enum MassUpdateConstants {

        STATUS("Status"),
        ITEM_START_DATE("Item Start Date"),
        ITEM_END_DATE("Item End Date"),
        CP_START_DATE("CP Start Date"),
        CP_END_DATE("CP End Date"),
        PRICE_TYPE("Price Type"),
        PRICE("Price"),
        PRICE_PRODECTION_STATUS("Price Protection Status"),
        PRICE_PRODECTION_START_DATE("Price Protection Start Date"),
        PRICE_PRODECTION_END_DATE("Price Protection End Date"),
        MEASUREMENT_PRICE("Measurement Price"),
        NEP("NEP"),
        NEP_FORMULA("NEP Formula"),
        BASE_PRICE_TYPE("Base Price Type"),
        BASELINE_NET_WAC("Baseline Net WAC"),
        NET_BASELINE_WAC_FORMULA("Net Baseline WAC Formula"),
        SUBSEQUENT_PERIOD_PRICE_TYPE("Subsequent Period Price Type"),
        NET_SUBSEQUENT_PERIOD_PRICE("Net Subsequent Period Price"),
        NET_SUBSEQUENT_PERIOD_PRICE_FORMULA("Net Subsequent Period Price Formula"),
        PRICE_TOLERANCE_INTERVAL("Price Tolerance Interval"),
        PRICE_TOLERANCE_FREQUENCY("Price Tolerance Frequency"),
        PRICE_TOLERANCE_TYPE("Price Tolerance Type"),
        PRICE_TOLERANCE("Price Tolerance"),
        MAX_INCREMENTAL_CHANGE("Max Incremental Change"),
        RESET_ELIGIBLE("Reset Eligible"),
        RESET_TYPE("Reset Type"),
        RESET_DATE("Reset Date"),
        RESET_INTERVAL("Reset Interval"),
        RESET_FREQUENCY("Reset Frequency"),
        RESET_PRICE_TYPE("Reset Price Type"),
        NET_RESET_PRICE_TYPE("Net Reset Price Type"),
        NET_RESET_PRICE_TYPE_FORMULA("Net Reset Price Formula"),
        NET_PRICE_TYPE("Net Price Type"),
        NET_PRICE_TYPE_FORMULA("Net Price Type Formula");
        private String constant;

        private MassUpdateConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }
}
