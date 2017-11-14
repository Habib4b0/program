/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request.rebateplan;

import com.stpl.gtn.gtn2o.ws.rebateplan.GtnWsRebatePlanInfoBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsRebatePlanGeneralRequest {

	public GtnWsRebatePlanGeneralRequest() {
		super();
	}

	private GtnWsRebatePlanInfoBean rebatePlanInfoBean;

	public GtnWsRebatePlanInfoBean getRebatePlanInfoBean() {
		return rebatePlanInfoBean;
	}

	public void setRebatePlanInfoBean(GtnWsRebatePlanInfoBean rebatePlanInfoBean) {
		this.rebatePlanInfoBean = rebatePlanInfoBean;
	}

}
