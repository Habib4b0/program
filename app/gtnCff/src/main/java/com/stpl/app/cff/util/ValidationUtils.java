package com.stpl.app.cff.util;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidationUtils.
 */
public final class ValidationUtils {

	/** The rebate tier from to. */
	public static final String REBATE_TIER_FROM_TO = "([\\-|0-9]{1,22}+(\\.[0-9]{1,2}+)?$)";

	/** The rebate spcl char. */
	public static final String REBATE_SPCL_CHAR = "([0-9|a-z|A-Z|\\.|\\*|\\,|\\_|\\-|\\@|\\#|\\$|\\+|\\'|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

	/** The rebate spcl char msg. */
	public static final String REBATE_SPCL_CHAR_MSG = "allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!,',+,&";

	/** The rebate free amt. */
	public static final String REBATE_FREE_AMT = "(^[0-9]{1,20}+(\\.[0-9]{1,2}+)?$)";

	/** The rebate tier value. */
	public static final String REBATE_TIER_VALUE = "(^[0-9]{1,22}+(\\.[0-9]{1,2}+)?$)";

	/** The spcl char. */
	public static final String SPCL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

	/** The apostrophe spcl char. */
	public static final String APOSTROPHE_SPCL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\'\\'\\)])*";

	/** The spcl char msg. */
	public static final String SPCL_CHAR_MSG = "allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!";

	/** The special char msgs. */
	public static final String SPECIAL_CHAR_MSGS = "allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!,'";

	/** The numeric validation. */
	public static final String NUMERIC_VALIDATION = "([0-9])*";

	/** The numeric validation message. */
	public static final String NUMERIC_VALIDATION_MESSAGE = "Value can contain only numbers";

	/** The numeric search validation. */
	public static final String NUMERIC_SEARCH_VALIDATION = "([0-9|\\*])*";

	/** The numeric validation neg message. */
	public static final String NUMERIC_VALIDATION_NEG_MESSAGE = "Enter positive value for lives";

	/** The price. */
	public static final String PRICE = "([0-9|\\.|])*";

	/** The price message. */
	public static final String PRICE_MESSAGE = "Item price  can contain only digits";

	/** The search special character. */
	public static final String SEARCH_SPECIAL_CHARACTER = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";

	/** The search spcl char msg. */
	public static final String SEARCH_SPCL_CHAR_MSG = "Allowed Special characters are @,*,#,.,$,&,_,-";

	/** The identifier spcl chars. */
	public static final String IDENTIFIER_SPCL_CHARS = "([0-9|a-z|A-Z|\\*])*";

	/** The identifier spcl chars msg. */
	public static final String IDENTIFIER_SPCL_CHARS_MSG = "Identifier should contain numbers and characters";

	/** The zipcode char. */
	public static final String ZIPCODE_CHAR = "([0-9|a-z|A-Z|\\-|\\s])*";

	/** The zipcode char msg. */
	public static final String ZIPCODE_CHAR_MSG = "Special characters except - not allowed for zip code";

	/** The qualifier spcl chars. */
	public static final String QUALIFIER_SPCL_CHARS = "([0-9|a-z|A-Z|\\ ])*";

	/** The qualifier spcl chars msg. */
	public static final String QUALIFIER_SPCL_CHARS_MSG = "Please enter only Alphabets and characters";

	/** The alpha numeric msg. */
	public static final String ALPHA_NUMERIC_MSG = "Please enter only Numbers and Alphabets";

	/** The alpha numeric. */
	public static final String ALPHA_NUMERIC = "([0-9|a-z|A-Z|\\ |\\*])*";
	
	public static final String NUMERIC_VALIDATION_ERROR="Years can contain only numbers";

             /** The Constant SPECIAL_CHAR. */
        public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";
        
           /** The Constant SPECIAL_CHAR_MSG. */
        public static final String SPECIAL_CHAR_MSG = "Allowed Special characters are @,#,.,%,$,&,_,-,(,),/,!";   
           
             /** The Constant SPECIAL_CHAR MAIL. */
        public static final String SPECIAL_CHAR_MAIL = "\"^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"+ \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$\"";
        
           /** The Constant SPECIAL_CHAR__MAIL_MSG. */
        public static final String SPECIAL_CHAR_MAIL_MSG = "Enter valid mail address";   
           /** The Constant SPECIAL_CHAR__MAIL_MSG. */
        public static final String MAIL_PATTERN = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
           
        
	/**
	 * The constructor
	 */
	private ValidationUtils() {
		// empty constructor
	}
}
