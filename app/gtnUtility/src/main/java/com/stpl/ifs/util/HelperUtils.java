package com.stpl.ifs.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
 
public class HelperUtils {
	
	
	public static final String EMPTY ="";
	
	
	
	
	
	public static final String ITEM_TYPE ="ItemType";
	public static final String RP_TYPE="RebatePlanType";
	public static final String COMPANY_TYPE = "CompanyType";
	public static final String MEMBER_TYPE = "MemberType";
	public static final String CFP_TYPE = "CompanyFamilyPlanType";
	public static final String MFP_TYPE = "MemberFamilyPlanType";
	public static final String IFP_TYPE = "ItemFamilyPlanType";
	public static final String RS_TYPE = "RebateScheduleType"; 	
	public static final String PS_TYPE = "PriceScheduleType";
	
	public static final String TRADE_CLASS="TradeClass";
	public static final String MODULE_NAME = "module";
	public static final String COMPANY_CRT_QUALIFIER_NAME="COMPANY_CRT_QUALIFIER_NAME";
	public static final String MEMBER_DRT_QUALIFIER_NAME="MEMBER_DRT_QUALIFIER_NAME";
	public static final String ITEM_IRT_QUALIFIER_NAME="ITEM_IRT_QUALIFIER_NAME";

	public static final String RebateProgramType="RebateProgramType";
	public static final String PaymentTerms="PaymentTerms";
	public static final String RebateFrequency="RebateFrequency";
	public static final String PaymentFrequency="PaymentFrequency";
	public static final String RebatePlanLevel="RebatePlanLevel";
	public static final String Calendar="Calendar";
	public static final String ValidationProfile="ValidationProfile";
	public static final String RebateStructure="RebateStructure";
	public static final String PaymentMethod="PaymentMethod";
	public static final String RebateBasedOn="RebateBasedOn";
	public static final String RebateRangeBasedOn="RebateRangeBasedOn";

	
	public static List<String> getStatus() {
		List<String> status = new ArrayList<String>();
		status.add("Active");
		status.add("Inactive");
		return status;
	}
	

	public static List<String> getPsPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add("StartDate");
		status.add("EndDate");
		status.add("RevisionDate");
		return status;
	}	
	public static List<String> geRsPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add("Rebate Plan Name");
		status.add("Rebate Amount");
		status.add("StartDate");
		status.add("EndDate");
		return status;
	}
	
	public static String getString(Object str){
		
		String stringOut=StringUtils.trimToEmpty(String.valueOf(str));
		if( stringOut.equals("null"))
			stringOut=StringUtils.EMPTY;
			
		return stringOut;
	}
}
