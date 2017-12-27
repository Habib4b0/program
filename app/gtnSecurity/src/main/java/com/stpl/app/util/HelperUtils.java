package com.stpl.app.util;

import java.util.ArrayList;
import java.util.List;

import com.stpl.app.security.businessRoleModuleMaster.dto.HelperDTO;
import com.vaadin.v7.ui.ComboBox;
 
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

	public static final String REBATE_PROGRAM_TYPE="RebateProgramType";
	public static final String PAYMENT_TERMS="PaymentTerms";
	public static final String REBATE_FREQUENCY="RebateFrequency";
	public static final String PAYMENT_FREQUENCY="PaymentFrequency";
	public static final String REBATE_PLAN_LEVEL="RebatePlanLevel";
	public static final String CALENDER="Calendar";
	public static final String VALIDATION_PROFILE="ValidationProfile";
	public static final String REBATE_STRUCTURE="RebateStructure";
	public static final String PAYMENT_METHOD="PaymentMethod";
	public static final String REBATE_BASED_ON="RebateBasedOn";
	public static final String REBATE_RANGE_BASED_ON="RebateRangeBasedOn";

	
	public static List<String> getStatus() {
		List<String> status = new ArrayList<String>();
		status.add("Active");
		status.add("Inactive");
		return status;
	}
	
	
	public  ComboBox getNativeSelect(ComboBox select,List<HelperDTO> helperList) {
		for (HelperDTO helperDTO : helperList) {
			select.addItem(helperDTO.getDescription());
		}
		return select;
	}
	
	public static List<String> getMfpPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add(CommonUIUtils.STARTDATE);
		status.add(CommonUIUtils.ENDDATE);
		status.add("TradeClass StartDate");
		status.add("TradeClass EndDate");
		status.add("Status");
		status.add(CommonUIUtils.ATTACHED_STATUS);
		status.add("Attached EndDate");
		return status;
	}
	public static List<String> getIfpPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add(CommonUIUtils.STARTDATE);
		status.add(CommonUIUtils.ENDDATE);
		status.add("Item StartDate");
		status.add("Item EndDate");
        status.add(CommonUIUtils.ATTACHED_STATUS);
		
		return status;
	}
	
	public static List<String> getCfpPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add(CommonUIUtils.STARTDATE);
		status.add(CommonUIUtils.ENDDATE);
		status.add("TradeClass StartDate");
		status.add("TradeClass EndDate");
        status.add(CommonUIUtils.ATTACHED_STATUS);
		
		return status;
	}
	
	public static List<String> getPsPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add(CommonUIUtils.STARTDATE);
		status.add(CommonUIUtils.ENDDATE);
		status.add("RevisionDate");
		return status;
	}	
	public static List<String> geRsPopulateList() {
		List<String> status = new ArrayList<String>();
		status.add("Rebate Plan Name");
		status.add("Rebate Amount");
		status.add(CommonUIUtils.STARTDATE);
		status.add(CommonUIUtils.ENDDATE);
		return status;
	}
	
	
}
