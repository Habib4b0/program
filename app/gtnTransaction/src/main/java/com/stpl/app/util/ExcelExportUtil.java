package com.stpl.app.util;

import org.apache.commons.lang.StringUtils;

/**
 * The Class ExcelExportUtil.
 *
 * @author soundarrajan
 */
public final class ExcelExportUtil {

	/** The Constant EXCEL_MIME_TYPE. */
	public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	/** The Constant WINDOW_NAME. */
	public static final String WINDOW_NAME = "_blank";

	/** The Constant CSV. */
	public static final String CSV = ".csv";

	/** The Constant COMMA. */
	public static final String COMMA = ",";

	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "MM/dd/yyyy";

	/** The Constant EMPTY_DATE. */
	public static final String EMPTY_DATE = StringUtils.EMPTY + ",";

	/** The Constant LIMIT. */
	public static final int LIMIT = 1000000;

	/** The Constant ITERATION_COUNT. */
	public static final int ITERATION_COUNT = 20000;

	/** The Constant MAX_RECORDS. */
	public static final int MAX_RECORDS = 60000;
        
        /**
          * The TAB.
        */
        public static final String TAB = "\t";
	
	/**
	 * Instantiates a new excel export util.
	 */
	private ExcelExportUtil(){
		//Empty
	}
    /**
     * To null check and replace double quotes in string 
     * CSV will spilt the values based on Comma and if string contains quotes and comma, values will misplace
     * @param rawObj -value to write in csv
     * @return 
     */    
     public static String replaceDoubleQuotes(Object rawObj) {
        String excelString = rawObj == null ? " " : String.valueOf(rawObj);
        if (StringUtils.isNotBlank(String.valueOf(excelString))) {
                 excelString = excelString.replace("\"", "\"\"");
        } 
            return excelString;
            
    }
}
