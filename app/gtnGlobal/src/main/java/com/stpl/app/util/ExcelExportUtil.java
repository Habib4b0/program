package com.stpl.app.util;


import org.apache.commons.lang.StringUtils;

/**
 * Contains the constants that were used in ExcelExport.
 * 
 * @author 
 */
public final class ExcelExportUtil {

    /** The Constant EXCEL_MIME_TYPE. */
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    
    /** The Constant WINDOW_NAME. */
    public static final String WINDOW_NAME = "_blank";
    
    /** The Constant CSV. */
    public static final String CSV = ".csv";
    
    /** The Constant COMMA. */
    public static final String COMMA = ConstantsUtils.COMMA;
    
    /** The Constant DATE_FORMAT. */
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    /**
     * The TAB.
     */
    public static final String TAB = "\t";
    /**
     * Constructor
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
