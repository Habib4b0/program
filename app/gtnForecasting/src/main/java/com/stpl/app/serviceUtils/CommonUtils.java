package com.stpl.app.serviceUtils;

import com.vaadin.v7.ui.NativeSelect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Common utility class that holds common logic methods and constants.
 *
 * @author
 */
public class CommonUtils {

	/** The Constant EMPTY. */
	public static final String EMPTY = "";
	
	/** The Constant STRING_ASTERISK. */
	public static final String STRING_ASTERISK = "*";
	
	/** The Constant MMDDYYYY. */
	public static final String MMDDYYYY = "MM/dd/yyyy";
	
	/** The Constant MMDDYYYYHHMMMSS. */
	public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/** The Constant STRING_NULL. */
	public static final String STRING_NULL = ConstantsUtils.NULL;
	
	/** The Constant CHAR_PERCENT. */
	public static final char CHAR_PERCENT = '%';
	
	/** The Constant CHAR_ASTERISK. */
	public static final char CHAR_ASTERISK = '*';

	/** The Constant SALES_MASTER. */
	public static final String SALES_MASTER = "SALES_MASTER";
	
	/** The Constant REBATE_PLAN_TYPE. */
	public static final String REBATE_PLAN_TYPE = "RP_TYPE";
        
        /** The Constant REBATE_PLAN_TYPE. */
	public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";
        
        
        /** The Constant STATUS. */
	public static final String STATUS = "STATUS";
	
	/** The Constant REBATE_PLAN_STATUS. */
	public static final String REBATE_PLAN_STATUS = "RP_STATUS";
	
	/** The Constant REBATE_BASED_ON. */
	public static final String REBATE_BASED_ON = "RP_BASED_ON";
	
	/** The Constant REBATE_STRUCTURE. */
	public static final String REBATE_STRUCTURE = "RP_STRUCTURE";
	
	/** The Constant REBATE_RANGE_BASED_ON. */
	public static final String REBATE_RANGE_BASED_ON = "RP_RANGE_BASED_ON";
	
	/** The Constant REBATE_SCHEDULE_TYPE. */
	public static final String REBATE_SCHEDULE_TYPE = "RS_TYPE";
	
	/** The Constant REBATE_PROGRAM_TYPE. */
	public static final String REBATE_PROGRAM_TYPE = "RS_PROGRAM_TYPE";
	
	/** The Constant PAYMENT_METHOD. */
	public static final String PAYMENT_METHOD = "PAYMENT_METHOD";
	
	/** The Constant REBATE_FREQUENCY. */
	public static final String REBATE_FREQUENCY = "RS_RBT_FREQUENCY";
	
	/** The Constant PAYMENT_FREQUENCY. */
	public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";
	
	/** The Constant PAYMENT_TERMS. */
	public static final String PAYMENT_TERMS = "PAYMENT_TERMS";
        
	/** The Constant RULE. */
	public static final String RULE = "Rule";        
	
	/** The Constant CALENDAR. */
	public static final String CALENDAR = "RS_CALENDAR";
    
    /** The Constant TIER_OPERATOR. */
    public static final String TIER_OPERATOR = "RP_OPERATOR";
    
    /** The Constant RS_STATUS. */
    public static final String RS_STATUS = "RS_STATUS";

    /** The Constant REBATE_VALIDATION_PROFILE. */
    public static final String REBATE_VALIDATION_PROFILE = "RS_VALDN_PROFILE";
    
    /** The Constant REBATE_PLAN_LEVEL. */
    public static final String REBATE_PLAN_LEVEL = "RS_RP_LEVEL";
    
    /** The Constant REBATE_RULE_ASSOCIATION. */
    public static final String REBATE_RULE_ASSOCIATION = "RS_RBT_RULE_ASSOCN";
    
    /** The Constant REBATE_RULE_TYPE. */
    public static final String REBATE_RULE_TYPE = "REBATE_RULE_TYPE";
    
    /** The Constant IFP_TYPE. */
    public static final String IFP_TYPE = "IFP_TYPE";

    /** The Constant REBATE_SCHEDULE_DESIGNATION. */
    public static final String REBATE_SCHEDULE_DESIGNATION = "RS_DESIGN";
    
    /** The Constant REBATE_SCHEDULE_CATEGORY. */
    public static final String REBATE_SCHEDULE_CATEGORY = "RS_CATEGORY";
    
    /** The Constant TRADE_CLASS. */
    public static final String TRADE_CLASS = "RS_TRADECLASS";
    
    /** The Constant UDC1. */
    public static final String UDC1 = "RS_UDC1";
    
    /** The Constant UDC2. */
    public static final String UDC2 = "RS_UDC2";
    
    /** The Constant UDC3. */
    public static final String UDC3 = "RS_UDC3";
    
    /** The Constant UDC4. */
    public static final String UDC4 = "RS_UDC4";
    
    /** The Constant UDC5. */
    public static final String UDC5 = "RS_UDC5";
    
    /** The Constant UDC6. */
    public static final String UDC6 = "RS_UDC6";
      
    /** The Constant CALCULATION_TYPE. */
    public static final String CALCULATION_TYPE = "CALCULATION_TYPE";
    
    /** The Constant CALCULATION_LEVEL. */
    public static final String CALCULATION_LEVEL = "CALCULATION_LEVEL";
    
    /** The Constant CALCULATION_TYPE. */
    public static final String INTEREST_BEARING_INDICATOR = "INTEREST_BEARING_INDICATOR";
    
    /** The Constant CALCULATION_LEVEL. */
    public static final String INTEREST_BEARING_BASIS = "INTEREST_BEARING_BASIS";
        
    /** The Constant FORECAST_SALE_BASIS. */
    public static final String FORECAST_SALE_BASIS = "RS_FRCST_SALES_BASIS";
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
    
    /** The Constant ZERO. */
    public static final int ZERO = 0;
    
    /** The Constant ONE. */
    public static final int ONE = 1;
    
    public static final String SELECT_ONE  = "Select One";
    public static final String INDICATOR_PERCENT = "%";
    public static final String INDICATOR_DOLLAR = "$";
    /**
     * Null representation of HelperDTO (Select one item)
     */
//    public static final HelperDTO NULL_HELPER_DTO = new HelperDTO(ConstantsUtils.SELECT_ONE);

   
//    /**
//     * Add items to the NativeSelect from list of HelperDTO.
//     *
//     * @param select - NativeSelect
//     * @param helperList - List of HelperDTO.
//     * @return NativeSelect with added items.
//     */
//    public NativeSelect getNativeSelect(final NativeSelect select,
//            final List<HelperDTO> helperList) {
//        try{
//            
//        for (final HelperDTO helperDTO : helperList) {
//            select.addItem(helperDTO.getId());
//            select.setItemCaption(helperDTO.getId(),helperDTO.getDescription());
//            
//        }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//        return select;
//    }
//    
//    public ComboBox getComboBoxV1(final ComboBox select,
//            final List<HelperDTO> helperList) {
//        try{
//            
//        for (final HelperDTO helperDTO : helperList) {
//            select.addItem(helperDTO.getId());
//            select.setItemCaption(helperDTO.getId(),helperDTO.getDescription());
//            
//        }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//        return select;
//    }    

    /**
     * Add the items to NativeSelect and returns it.
     *
     * @param select - NativeSelect.
     * @return NativeSelect with added items.
     */
    public NativeSelect getStatusSelect(final NativeSelect select) {
        try{
        select.addItem("Active");
        select.addItem("Inactive");
         } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return select;
    }

    /**
     * Adds the list of string to the Native select and returns it.
     *
     * @param select - NativeSelect
     * @param list -List of String.
     * @return - NativeSelect with the added items.
     */
    public NativeSelect getNativeList(final NativeSelect select, final List<String> list) {
         for (final String str : list) {
            select.addItem(str);
        }
        return select;
    }

    /**
     * Converts the String object to Date object and returns the Date object.
     *
     * @param strDate - String date.
     * @return - date object
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String strDate){
        if (strDate == null || strDate.equals(EMPTY)
                || strDate.equals(STRING_NULL)) {
            return null;
        }
        Date aDate = new Date();
		try {
			aDate = convertStringToDate(MMDDYYYY, strDate);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e.getCause());
		}
        return aDate;
    }

    /**
     * Converts the String date to date object with the given format.
     *
     * @param aMask - format for date.
     * @param strDate - String date that format need to be changed.
     * @return - date object with the particular format.
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static final Date convertStringToDate(final String aMask, final String strDate){
       Date date = null;
        try{
        SimpleDateFormat dateFormat = null;
        
        dateFormat = new SimpleDateFormat(aMask);
        date = dateFormat.parse(strDate);
         } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return date;
    }

    /**
     * Formats the two digit year format to four digit year and returns the Date
     * object.
     *
     * @param enteredDate - Date Object that format need to be changed.
     * @return Date Object with formatted date object.
     */
    public static final Date convert2DigitTo4DigitYearFormat(final Date enteredDate) {
    	Date enterDate = enteredDate;

        if (enterDate != null && !enterDate.equals("")) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
                final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
                final Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -14);
                sdf.set2DigitYearStart(cal.getTime());
                final String datesVal = sdf.format(enterDate);
                final Date temp = CommonUtils.convertStringToDate(fmt.format(sdf.parse(datesVal)));
                enterDate = temp;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return enterDate;
    }

    /**
     * To get yesterday date
     * @return date
     */
    public static String getYesterdayDate() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        date.setDate(date.getDate()-1);
        return dateFormat.format(date);
    }
    
    /**
     * Format a time from a given input format to given target format
     * 
     * @param inputFormat
     * @param inputTimeStamp
     * @param outputFormat
     * @return
     * @throws java.text.ParseException
     */
    public static String TimeStampConverter(final String inputFormat,
            String inputTimeStamp, final String outputFormat)
            throws java.text.ParseException {
        return new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(
                inputFormat).parse(inputTimeStamp));
    }
    
    public static String getViewTableName(String hierarchyIndicator) {
        String viewtable = "";
        if ("C".equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if ("P".equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }
    
    /**
     * To check whether the given string is integer or not
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
      /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }
    
        public static String replaceShortMonthForMonth(String periods) {
        String[] array = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return periods.replace(array[0], "M1")
                .replace(array[1], "M2")
                .replace(array[2], "M3")
                .replace(array[3], "M4")
                .replace(array[4], "M5")
                .replace(array[5], "M6")
                .replace(array[6], "M7")
                .replace(array[7], "M8")
                .replace(array[8], "M9")
                .replace(array[9], "M10")
                .replace(array[10], "M11")
                .replace(array[11], "M12");
    }
    public static String replaceIntegerForMonth(String periods) {
        String[] array = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return periods.replace(array[0], "1")
                .replace(array[1], "2")
                .replace(array[2], "3")
                .replace(array[3], "4")
                .replace(array[4], "5")
                .replace(array[5], "6")
                .replace(array[6], "7")
                .replace(array[7], "8")
                .replace(array[8], "9")
                .replace(array[9], "10")
                .replace(array[10], "11")
                .replace(array[11], "12");
    }
    
       public static String stringListToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if (stringList != null && !stringList.isEmpty()) {
            for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
                builder.append("'");
                builder.append(stringList.get(loop));
                builder.append("'");
                if (loop != (limit - 1)) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }
       
     public static List<String> getTempTableList() {
        List<String> tempTables = new ArrayList<String>();
        tempTables.add("ST_NM_SALES_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_SALES");
        tempTables.add("ST_NM_SALES_PROJECTION_MASTER");
        tempTables.add("ST_NM_DISCOUNT_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_DISCOUNT");
        tempTables.add("ST_NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("ST_NM_PPA_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_PPA");
        tempTables.add("ST_NM_PPA_PROJECTION_MASTER");
        tempTables.add("ST_NM_ASSUMPTIONS");
        tempTables.add("NM_SALES_PROJECTION");
        tempTables.add("NM_ACTUAL_SALES");
        tempTables.add("NM_SALES_PROJECTION_MASTER");
        tempTables.add("NM_DISCOUNT_PROJECTION");
        tempTables.add("NM_ACTUAL_DISCOUNT");
        tempTables.add("NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("NM_PPA_PROJECTION");
        tempTables.add("NM_ACTUAL_PPA");
        tempTables.add("NM_PPA_PROJECTION_MASTER");
        tempTables.add("NM_ASSUMPTIONS");
        tempTables.add("PROJECTION_DETAILS");
        return tempTables;
    }
     
     public static String getDBinput(String identifier) {
        return identifier.replace(INDICATOR_PERCENT, "[%]").replace("*", INDICATOR_PERCENT).replace("_", "[_]");
    }
   
}
