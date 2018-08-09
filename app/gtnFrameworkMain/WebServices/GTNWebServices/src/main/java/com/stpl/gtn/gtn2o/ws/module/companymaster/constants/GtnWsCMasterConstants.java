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
	public static final String QUERY_COUNT = "Select count(*) from (Select distinct cm.COMPANY_MASTER_SID,cm.COMPANY_ID,cm.COMPANY_NO,cm.COMPANY_NAME,cm.COMPANY_STATUS,cm.COMPANY_TYPE\n";
	public static final String COMPANY_STATUS_HELPER = "companyStatusHelper";
	private GtnWsCMasterConstants() {
	}

	public static List<String> getMonthList() {
		return Collections.unmodifiableList(MONTH_LIST);
	}
}
