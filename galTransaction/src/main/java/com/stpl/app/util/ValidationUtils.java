package com.stpl.app.util;

/**
 * The Class ValidationUtils.
 */
public final class ValidationUtils {

	/** The special characters. */
	public final static String SPECIAL_CHARACTERS = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";

	/** The special characters error msg. */
	public final static String SPCL_CHARS_ERROR_MSG = "allowed Special characters are @,*,#,.,$,&,_,-";

	/** The numeric validation. */
	public final static String NUMERIC_VALIDATION = "([0-9])*";

	/** The numeric validation message. */
	public final static String NUMERIC_VALIDATION_MSG = "value can contain only numbers";

	/** The alpha numeric validation. */
	public final static String ALPHA_NUMERIC_VALIDATION = "([0-9|a-z|A-Z|\\*\\_])*";

	/** The alpha numeric validation msg. */
	public final static String ALPHA_NUMERIC_VALIDATION_MSG = "value can contain only numbers and alphabets";

	/** The PRICE. */
	public final static String PRICE = "([0-9|\\.|])*";

	/** The PRICE message. */
	public final static String PRICE_MESSAGE = "allowed characters are price values";

	/** The BALANCE. */
	public final static String BALANCE = "([0-9|\\.|])*";

	/** The BALANCE message. */
	public final static String BALANCE_MESSAGE = "allowed charactes are balance values";

	/** The numeric and star validation. */
	public final static String NUMERIC_AND_STAR_VALIDATION = "([0-9|\\*|])*";

	/** The PRICE and star validation. */
	public final static String PRICE_AND_STAR_VALIDATION = "([0-9|\\.\\*|])*";
	
	/**
	 * Instantiates a new validation utils.
	 */
	private ValidationUtils(){
		
	}
}
