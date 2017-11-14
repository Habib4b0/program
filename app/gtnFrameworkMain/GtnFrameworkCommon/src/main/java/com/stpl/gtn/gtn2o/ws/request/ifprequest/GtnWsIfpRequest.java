/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.ifprequest;

import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsIfpRequest implements GtnWSRequestData {

	public GtnWsIfpRequest() {
		super();
	}

	private GtnIFamilyPlanBean gtnIFamilyPlan;

	public GtnIFamilyPlanBean getGtnIFamilyPlan() {
		return gtnIFamilyPlan;
	}

	public void setGtnIFamilyPlan(GtnIFamilyPlanBean gtnIFamilyPlan) {
		this.gtnIFamilyPlan = gtnIFamilyPlan;
	}

}
