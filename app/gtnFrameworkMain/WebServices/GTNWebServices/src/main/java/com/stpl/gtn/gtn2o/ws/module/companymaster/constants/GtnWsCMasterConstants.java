/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companymaster.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnWsCMasterConstants {

	private static final List<String> MONTH_LIST = Arrays.asList("January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December");
	public static final String QUERY_COUNT = "Select count(*) from (Select distinct cm.COMPANY_MASTER_SID,cm.COMPANY_ID,cm.COMPANY_NO,cm.COMPANY_NAME,cm.COMPANY_STATUS,cm.COMPANY_TYPE,cm.COMPANY_START_DATE,\n"
			+ " cm.COMPANY_END_DATE,trade.COMPANY_TRADE_CLASS,trade.TRADE_CLASS_START_DATE,trade.TRADE_CLASS_END_DATE,cm.COMPANY_GROUP,cm.COMPANY_CATEGORY,cm.ORGANIZATION_KEY,cm.FINANCIAL_SYSTEM,comp.COMPANY_NO as comp,parent.PARENT_START_DATE,\n"
			+ " parent.PARENT_END_DATE,comp1.COMPANY_NO as priorCompanyNo,parent.PRIOR_PARENT_START_DATE,cm.REGION_CODE,udc.UDC1,udc.UDC2,udc.UDC3,udc.UDC4,udc.UDC5,udc.UDC6,cm.ADDRESS1,cm.ADDRESS2,cm.ZIP_CODE,cm.CITY,cm.STATE,cm.COUNTRY ";

	private GtnWsCMasterConstants() {
	}

	public static List<String> getMonthList() {
		return Collections.unmodifiableList(MONTH_LIST);
	}
}
