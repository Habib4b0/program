/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.cfprequest;

import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsCfpRequest implements GtnWSRequestData {

	public GtnWsCfpRequest() {
		super();
	}

	private GtnCFamilyPlan gtnCFamilyPlan;

	public GtnCFamilyPlan getGtnCFamilyPlan() {
		return gtnCFamilyPlan;
	}

	public void setGtnCFamilyPlan(GtnCFamilyPlan gtnCFamilyPlan) {
		this.gtnCFamilyPlan = gtnCFamilyPlan;
	}

}
