/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request.rebateschedule;

import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsRebateScheduleGeneralRequest {

	public GtnWsRebateScheduleGeneralRequest() {
		super();
	}

	private GtnWsRebateScheduleInfoBean rebateScheduleInfoBean;

	public GtnWsRebateScheduleInfoBean getRebateScheduleInfoBean() {
		return rebateScheduleInfoBean;
	}

	public void setRebateScheduleInfoBean(GtnWsRebateScheduleInfoBean rebateScheduleInfoBean) {
		this.rebateScheduleInfoBean = rebateScheduleInfoBean;
	}

}
