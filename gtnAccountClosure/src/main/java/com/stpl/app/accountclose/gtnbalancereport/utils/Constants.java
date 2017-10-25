/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.utils;

/**
 *
 * @author Santanukumar
 */
public class Constants {
    public static final  String DS_SELECTED_CUSTOMERS="GTN_DS_SELECTED_CUSTOMERS";
    public static final String DS_SELECTED_PRODUCTS="GTN_DS_SELECTED_PRODUCTS";
    public static final String ACCTCLOSE = "Accounting Closure";
    public static final String PRIVATE = "Private";
    public static final String SAVE = "save";
    public static final int ZERO = 0;
    public static final String USER_ID = "userId";
    public static final String GTN_BALANCE="GTN_BALANCE";
    /**
     * The fail.
     */
    public static final String FAIL = "fail";
    /**
     * Enum for Date format constants
     */
    public enum DateFormatConstants {

        DEFAULT_JAVA_DATE_FORMAT("EEE MMM dd HH:mm:ss z yyyy"),
        DEFAULT_SQL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss.SSS"),
        MMddyyyy("MM/dd/yyyy"),
        yyyyMMddhhmmssSSS("yyyy-MM-dd hh:mm:ss.SSS"),
        MMddyyyyhhmmss("MM/dd/yyyy hh:mm:ss");
        private String constant;

        private DateFormatConstants(String constant) {
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
    /**
     * Enum for Date format constants
     */
    public enum VariableConstants {

        DEMANDTYPE("Demand"),
        PIPELINETYPE("Pipe Line"),
        SELECT_ONE("-Select One-"),
        CUSTOMER("Customer"),
        BRAND("Brand"),
        ITEM("Item"),
        ACCT_CLOSER_MASTERID("AcctCloserMasterId"),
        FREQUENCY_DIVISION("frequencyDivision"),
        HISTORY_END_YEAR("historyEndYear"),
        HISTORY_END_MONTH("historyEndMonth"),
        HISTORY_END_PERIOD("historyEndPeriod"),
        HISTORY_END_DAY("historyEndDay"),
        SPACE(" ");
        private String constant;

        private VariableConstants(String constant) {
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
    /**
     * Enum for Regex constants.
     */
    public enum RegexConstants {

        /**
         * The regex extract digits.
         */
        REGEX_EXTRACT_DIGITS("\\D+"),;
        /**
         * The constant.
         */
        private String constant;

        /**
         * Instantiates a new regex constants.
         *
         * @param constant the constant
         */
        private RegexConstants(String constant) {
            this.constant = constant;
        }

        /**
         * Gets the constant.
         *
         * @return the constant
         */
        public String getConstant() {
            return constant;
        }

        /**
         * To string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return constant;
        }
    }
    
}
