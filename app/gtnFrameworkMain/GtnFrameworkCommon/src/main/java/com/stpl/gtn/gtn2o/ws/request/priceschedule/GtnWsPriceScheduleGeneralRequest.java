/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request.priceschedule;

import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsPriceScheduleGeneralRequest {

	public GtnWsPriceScheduleGeneralRequest() {
		super();
	}

	private GtnUIFrameWorkPSInfoBean psInfoBean;

	public GtnUIFrameWorkPSInfoBean getPsInfoBean() {
		return psInfoBean;
	}

	public void setPsInfoBean(GtnUIFrameWorkPSInfoBean psInfoBean) {
		this.psInfoBean = psInfoBean;
	}

}
