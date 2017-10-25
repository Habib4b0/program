package com.stpl.app.util;

// TODO: Auto-generated Javadoc
/**
 * Class contains String constants used for validation.
 *
 * @author
 */
public final class ValidationUtils {

    /** The Constant REBATE_TIER_FROM_TO. */
    public static final String REBATE_TIER_FROM_TO = "([\\-|0-9]{1,22}+(\\.[0-9]{1,2}+)?$)";
    
    /** The Constant REBATE_SPECIAL_CHAR. */
    public static final String REBATE_SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\*|\\,|\\_|\\-|\\@|\\#|\\$|\\+|\\'|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    
    /** The Constant REBATE_SPECIAL_CHAR. */
    public static final String REBATE_SPECIAL_CHAR_SEARCH = "([0-9|a-z|A-Z|\\.|\\*|\\,|\\_|\\-|\\@|\\#|\\$|\\+|\\'|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    
    /** The Constant REBATE_SPECIAL_CHAR_MSG. */
    public static final String REBATE_SPECIAL_CHAR_MSG = "Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!,',+,&";
    
    /** The Constant REBATE_FREE_AMT. */
    public static final String REBATE_FREE_AMT = "(^[0-9]{1,38}+(\\.[0-9]{1,2}+)?$)";
    
    /** The Constant REBATE_TIER_VALUE. */
    public static final String REBATE_TIER_VALUE = "(^[0-9]{1,22}+(\\.[0-9]{1,2}+)?$)";
    
    /** The Constant SPECIAL_CHAR. */
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    
    /** The Constant APOSTROPHE_SP_CHAR. */
    public static final String APOSTROPHE_SP_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\'\\'\\)])*";
    
    /** The Constant SPECIAL_CHAR_MSG. */
    public static final String SPECIAL_CHAR_MSG = "Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!";
    
    /** The Constant SPECIAL_CHAR_MSGS. */
    public static final String SPECIAL_CHAR_MSGS = "Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!,'";
    
    /** The Constant NUM_VALIDATION. */
    public static final String NUM_VALIDATION = "([0-9])*";
    
    /** The Constant NUM_VALIDATION. */
    public static final String NUM_VALIDATION_WITH_COMMA = "([0-9|\\,])*";
    
    /** The Constant NUM_VALID_MSG. */
    public static final String NUM_VALID_MSG = "Value can contain only numbers";
    
    /** The Constant NUM_SEARCH_VALIDATION. */
    public static final String NUM_SEARCH_VALIDATION = "([0-9|\\*])*";
    
    /** The Constant NUM_NEG_MSG. */
    public static final String NUM_NEG_MSG = "Enter positive value for lives";
    
    /** The Constant PRICE. */
    public static final String PRICE = "([0-9|\\.|])*";
    
    /** The Constant PRICE_MSG. */
    public static final String PRICE_MSG = "Item price  can contain only digits";
    
    /** The Constant SEARCH_SP_CHAR. */
    public static final String SEARCH_SP_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";
    
    /** The Constant SEARCH_SPCHAR_MSG. */
    public static final String SEARCH_SPCHAR_MSG = "Allowed Special characters are @,*,#,.,$,&,_,-";
    
    /** The Constant IDENTIFIER_SPCHAR. */
    public static final String IDENTIFIER_SPCHAR = "([0-9|a-z|A-Z|\\*])*";
    
    /** The Constant IDENTIFIER_SPCHAR_MSG. */
    public static final String IDENTIFIER_SPCHAR_MSG = "Identifier should contain numbers and characters";
    
    /** The Constant ZIPCODE_CHAR. */
    public static final String ZIPCODE_CHAR = "([0-9|a-z|A-Z|\\-|\\s])*";
    
    /** The Constant ZIPCODE_CHAR_MSG. */
    public static final String ZIPCODE_CHAR_MSG = "Special characters except - not allowed for zip code";
    
    /** The Constant QUALIFIER_SPCHAR. */
    public static final String QUALIFIER_SPCHAR = "([0-9|a-z|A-Z|\\s])*";
    
    /** The Constant QUALIFIER_SPCHAR_MSG. */
    public static final String QUALIFIER_SPCHAR_MSG = "Please enter only numbers and characters";
    
    /** The Constant SEARCH_CHAR_VALID. */
    public static final String SEARCH_CHAR_VALID = "([0-9|a-z|A-Z|\\_|\\$|\\.|\\*|\\s])*";
    
    /** The special character. */
    public static String specialCharacter = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
    
    /** The special character message. */
    public static String specialCharacterMessage = "Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!";
    
    public static String alphaNumericChars = "([0-9|a-z|A-Z|\\ |\\*])*";
    
    public static String alphaNumericCharsMessage = "Special Characters are not allowed in ";
    
    public static final String REBATE_SCHEDULE_AMT = "(^[0-9]{1,22}+(\\.[0-9]{1,6}+)?$)";   
    public static String DATE_VALIDATION = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
    
    public static final String REBATE_SCHEDULE_AMT_DOUBLE = "(^[0-9]{1,45}+(\\.[0-9]{1,6}+)?$)";
    
    public static final String REBATE_SCHEDULE_AMT_SAVE = "(^[0-9]{1,15}+(\\.[0-9]{1,6}+)?$)";
    
    public static final String NUMBER_VALIDATION = "([0-9])*";
    
    public static final String SPECIAL_CHA = "([0-9|a-z|A-Z|\\.|\\,|\\@|\\#|\\$|\\&|\\%||\\^||\\&||\\<||\\>||\\*||\\[||\\]||\\?||\\:||\\;||\\{||\\}||\\|||\\+||\\=|\\/|\\(|\\!|\\)])*";
    
    public static final String SPECIAL_CHAR_MS = "Allowed Special characters are  !,@,#,$,%,^,&,*,.,(,),<,>,?,/,””’,:,;,[,},{,],\\,|,+,=\"";
    
    public static final String TRADE_CLASS_VALID = "Add at least one Trade Class in Company Trade Class tab";
    
    public static final String PARENT_COMP_START_DATE_VALID = "Parent Company Start Date is Mandatory in Parent Company Tab";
    
    public static final String COM_IDENTIFIER_VALID = "Company Identifier already exists for another Company";
    
    public static final String CANNOT_DELETE = "Cannot Delete";
    
    public static final String START_DATE_VALID = "Start Date is Mandatory in Identifier Tab";
    
    public static final String COMP_TRADE_CLASS_VALID = "Trade Class is Mandatory in Company TradeClass Tab";
    
    public static final String MAND_VALID = "Not all the mandatory fields are populated.";
    
    public static final String IDENTIFIER_VALID = "Identifier Should be less than 50 characters";
    
    public static final String STATUS_VALID = "Status required for Item ID ";
    
    public static final String SD_ITEM_ID_VALID = "Start Date required for Item ID ";
    
    public static final String SD_VALID = "Start Date is Mandatory ";
    
    
    
    /**
     * Constructor.
     */
    private ValidationUtils(){
    	//Empty
    }
}
