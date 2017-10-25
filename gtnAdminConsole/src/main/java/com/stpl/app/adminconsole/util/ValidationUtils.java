package com.stpl.app.adminconsole.util;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidationUtils.
 */
public final class ValidationUtils {

    public static final String REBATE_TIER_FROM_TO = "([\\-|0-9]{1,22}+(\\.[0-9]{1,2}+)?$)";

    public static final String REBATE_SPCL_CHAR = "([0-9|a-z|A-Z|\\.|\\*|\\,|\\_|\\-|\\@|\\#|\\$|\\+|\\'|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    public static final String REBATE_SPCL_CHAR_MSG = "allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!,',+,&";

    public static final String REBATE_FREE_AMT = "(^[0-9]{1,20}+(\\.[0-9]{1,2}+)?$)";

    public static final String REBATE_TIER_VALUE = "(^[0-9]{1,22}+(\\.[0-9]{1,2}+)?$)";

    public static final String SPCL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    public static final String APOSTROPHE_SPCL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\'\\'\\)])*";

    public static final String SPCL_CHAR_MSG = "allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!";

    public static final String SPECIAL_CHAR_MSGS = "allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!,'";

    public static final String NUMERIC_VALIDATION = "([0-9])*";

    public static final String NUMERIC_VALIDATION_MESSAGE = "Value can contain only numbers";

    public static final String NUMERIC_SEARCH_VALIDATION = "([0-9|\\*])*";

    public static final String NUMERIC_VALIDATION_NEG_MESSAGE = "Enter positive value for lives";

    public static final String PRICE = "([0-9|\\.|])*";

    public static final String PRICE_MESSAGE = "Item price  can contain only digits";

    public static final String SEARCH_SPECIAL_CHARACTER = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";

    public static final String SEARCH_SPCL_CHAR_MSG = "Allowed Special characters are @,*,#,.,$,&,_,-";

    public static final String IDENTIFIER_SPCL_CHARS = "([0-9|a-z|A-Z|\\*])*";

    public static final String IDENTIFIER_SPCL_CHARS_MSG = "Identifier should contain numbers and characters";

    public static final String ZIPCODE_CHAR = "([0-9|a-z|A-Z|\\-|\\s])*";

    public static final String ZIPCODE_CHAR_MSG = "Special characters except - not allowed for zip code";

    public static final String QUALIFIER_SPCL_CHARS = "([0-9|a-z|A-Z|\\ ])*";

    public static final String QUALIFIER_SPCL_CHARS_MSG = "Please enter only Alphabets and characters";

    public static final String ALPHA_NUMERIC_MSG = "Please enter only Numbers and Alphabets";

    public static final String ALPHA_NUMERIC = "([0-9|a-z|A-Z|\\ |\\*])*";

    public static final String NUMERIC_VALIDATION_ERROR = "Years can contain only numbers";

    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    public static final String SPECIAL_CHAR_MSG = "Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!";

    public static final String SPECIAL_CHAR_MAIL = "\"^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"+ \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$\"";

    public static final String SPECIAL_CHAR_MAIL_MSG = "Enter valid mail address";

    public static final String MAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    
    public static final String INTEGER_VALIDATION = "^[0-9]+$|0";
    public static final String NEGATIVE_INTEGER_VALIDATION = "^\\-[0-9]+$|0";
    
    private ValidationUtils() {

    }
}
