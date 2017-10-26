package com.stpl.app.contract.dashboard.util;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.contract.util.Constants;

/**
 * The Class ExcelExportUtil.
 *
 * @author shrihariharan
 */
public final class ExcelExportUtil {

    /**
     * The Constant EXCEL_EXPORT_IMAGE.
     */
    public static final String EXCEL_EXPORT_IMAGE = "../../icons/excel.png";
    /**
     * The Constant EXCEL_MIME_TYPE.
     */
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    /**
     * The Constant EMPTY_DATE.
     */
    public static final String EMPTY_DATE = StringUtils.EMPTY + ",";
    /**
     * The Constant WINDOW_NAME.
     */
    public static final String WINDOW_NAME = "_blank";
    /**
     * The Constant CSV.
     */
    public static final String CSV = ".csv";
    /**
     * The Constant COMMA.
     */
    public static final String COMMA = ",";
    /**
     * The Constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = Constants.MM_DD_YYYY;
    /**
     * The Constant LIMIT.
     */
    public static final int LIMIT = 1000000;
    /**
     * The Constant ITERATION_COUNT.
     */
    public static final int ITERATION_COUNT = 20000;
    /**
     * The Constant MAX_RECORDS.
     */
    public static final int MAX_RECORDS = 60000;
    /**
     * Empty Constructor
     */
    private ExcelExportUtil(){
    	//Empty Constructor
    }
}
