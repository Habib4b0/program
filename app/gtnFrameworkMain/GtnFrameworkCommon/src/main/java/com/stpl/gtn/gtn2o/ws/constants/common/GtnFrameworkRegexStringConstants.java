package com.stpl.gtn.gtn2o.ws.constants.common;

public class GtnFrameworkRegexStringConstants {

	public static final String ALPHANUMERIC_FORMAT = "^[a-zA-Z0-9]*$";
	public static final String ALPHANUMERIC_ERROR_MSG = " can only be Alphanumeric";

	public static final String NUMERIC_FORMAT = "^[0-9]*$";
	public static final String NUMERIC_ERROR_MSG = " can only be Numbers";

	public static final String ALPHA_FORMAT = "^[a-zA-Z]*$";
	public static final String ALPHA_ERROR_MSG = " can only be Alphabets";

	public static final String NUMERIC_DECIAL_DOUBLE_PRECISION_FORMAT = "^[0-9]+(\\.[0-9]{1,2})?$";

	public static final String NUMERIC_WITH_SIX_DECIMAL_PRECISION = "^[0-9]+(\\.[0-9]{1,6})?$";

	public static final String NUMERIC_WITH_THREE_DECIMAL_PRECISION = "^[0-9]+(\\.[0-9]{1,3})?$";

	public static final String DATE_FORMAT = " ^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";

	public static final String NUMERIC_SPECIAL_CHAR = "([0-9\\.|\\*|\\,|\\_|\\-|\\@|\\#|\\$|\\+|\\'|\\&|\\%|\\^|\\{|\\}|\\[|\\]|\\s|\\/|\\(|\\!|\\)])*";

	public static final String NUMERIC_SPECIAL_CHAR_MSG = " allows only Numeric and special characters";

	public static final String ALLOWS_ONLY_NUMERIC = " allows only Numeric";

	public static final String ACCEPT_8_CHARACTER = "^[a-zA-Z0-9]{8}?$";

	public static final String ACCEPT_9_CHARACTER = "^[a-zA-Z0-9]{9}?$";

	public static final String ACCEPT_MIN8_MAX100_CHARACTER = "^[a-zA-Z0-9]{8,100}?$";

	public static final String ACCEPT_NEGATIVE_AND_ZERO = "^\\-[1-9]*|0$";
        
        public static final String ACCEPT_NEGATIVE_AND_ZERO_L7 = "^\\-[0-9]+$|0";

	public static final String ACCEPT_POSITIVE_AND_ZERO = "^[1-9]*|0$";
        public static final String ACCEPT_POS_AND_ZERO_AND_NEGA = "^\\-[0-9]+$|0|[0-9]+";

	public static final String NUMERIC_WITH_DOT_REGEX = "^\\d+(\\.\\d+)*$";

	private GtnFrameworkRegexStringConstants() {
	}
}
