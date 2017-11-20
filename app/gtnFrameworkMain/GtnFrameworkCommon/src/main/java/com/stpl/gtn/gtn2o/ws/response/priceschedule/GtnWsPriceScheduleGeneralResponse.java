/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.response.priceschedule;

import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameWorkPSInfoBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsPriceScheduleGeneralResponse {

	public GtnWsPriceScheduleGeneralResponse() {
		super();
	}

	private GtnUIFrameWorkPSInfoBean priceScheduleInfoBean;
	private boolean psIdDuplicate;
	private boolean psNoDuplicate;

	public GtnUIFrameWorkPSInfoBean getPriceScheduleInfoBean() {
		return priceScheduleInfoBean;
	}

	public void setPriceScheduleInfoBean(GtnUIFrameWorkPSInfoBean priceScheduleInfoBean) {
		this.priceScheduleInfoBean = priceScheduleInfoBean;
	}

	public boolean isPsIdDuplicate() {
		return psIdDuplicate;
	}

	public void setPsIdDuplicate(boolean psIdDuplicate) {
		this.psIdDuplicate = psIdDuplicate;
	}

	public boolean isPsNoDuplicate() {
		return psNoDuplicate;
	}

	public void setPsNoDuplicate(boolean psNoDuplicate) {
		this.psNoDuplicate = psNoDuplicate;
	}

}
