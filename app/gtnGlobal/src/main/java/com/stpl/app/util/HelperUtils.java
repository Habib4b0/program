package com.stpl.app.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Class contains the constants and common utility method.
 *
 * @author
 */
public final class HelperUtils {

	 /** The Constant EMPTY. */
 	public static final String EMPTY = "";
	    
    	/** The Constant ITEM_TYPE. */
    	public static final String ITEM_TYPE = "ItemType";
	    
    	/** The Constant RP_TYPE. */
    	public static final String RP_TYPE = "RebatePlanType";
	    
    	/** The Constant COMPANY_TYPE. */
    	public static final String COMPANY_TYPE = "CompanyType";
	    
    	/** The Constant MEMBER_TYPE. */
    	public static final String MEMBER_TYPE = "MemberType";
	    
    	/** The Constant CFP_TYPE. */
    	public static final String CFP_TYPE = "CompanyFamilyPlanType";
	    
    	/** The Constant MFP_TYPE. */
    	public static final String MFP_TYPE = "MemberFamilyPlanType";
	    
    	/** The Constant IFP_TYPE. */
    	public static final String IFP_TYPE = "ItemFamilyPlanType";
	    
    	/** The Constant RS_TYPE. */
    	public static final String RS_TYPE = "RebateScheduleType";
	    
    	/** The Constant PS_TYPE. */
    	public static final String PS_TYPE = "PriceScheduleType";

	    /** The Constant TRADE_CLASS. */
    	public static final String TRADE_CLASS = "TradeClass";
	    
    	/** The Constant MODULE_NAME. */
    	public static final String MODULE_NAME = "module";
	    
    	/** The Constant COMPANY_CRT_QUALIFIER_NAME. */
    	public static final String COMPANY_CRT_QUALIFIER_NAME = "COMPANY_CRT_QUALIFIER_NAME";
	    
    	/** The Constant MEMBER_DRT_QUALIFIER_NAME. */
    	public static final String MEMBER_DRT_QUALIFIER_NAME = "MEMBER_DRT_QUALIFIER_NAME";
	    
    	/** The Constant ITEM_IRT_QUALIFIER_NAME. */
    	public static final String ITEM_IRT_QUALIFIER_NAME = "ITEM_IRT_QUALIFIER_NAME";
	      
      	/** The Constant LOGGER. */
      	private static final Logger LOGGER = LoggerFactory.getLogger(HelperUtils.class);
	
	/**
	 * Default Constructor.
	 */
    private HelperUtils() {

    }

   

    /**
     * Converts the object to string and returns the string.
     *
     * @param str - Object
     * @return String
     */
    public static String getString(final Object str) {
         String stringOut ="";
    try{
      stringOut = StringUtils.trimToEmpty(String.valueOf(str));
        if (ConstantsUtils.NULL.equals(stringOut)) {
            
            stringOut = StringUtils.EMPTY;
        }
  } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return stringOut;
    }
    public static Integer getInteger(final Object str) {
         String stringOut ="";
    try{
      stringOut = StringUtils.trimToEmpty(String.valueOf(str));
        if (ConstantsUtils.NULL.equals(stringOut)) {
            
            stringOut = "0";
        }
  } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return Integer.valueOf(stringOut);
    }
}
