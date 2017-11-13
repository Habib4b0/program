/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request.deductioncalendar;

import com.stpl.gtn.gtn2o.ws.deductioncalendar.bean.GtnWsDeductionCalendarBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsDeductionCalendarRequest {

	public GtnWsDeductionCalendarRequest() {
		super();
	}

	private GtnWsDeductionCalendarBean deductionCalendarBean;

	public GtnWsDeductionCalendarBean getDeductionCalendarBean() {
		return deductionCalendarBean;
	}

	public void setDeductionCalendarBean(GtnWsDeductionCalendarBean deductionCalendarBean) {
		this.deductionCalendarBean = deductionCalendarBean;
	}

}
