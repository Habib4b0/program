/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.cfpresponse;

import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlan;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanValidationBean;

public class GtnWsCfpReponse {

	public GtnWsCfpReponse() {
		super();
	}

	private GtnCFamilyPlan gtnCFamilyPlan;

	private GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean;

	public GtnCFamilyPlan getGtnCFamilyPlan() {
		return gtnCFamilyPlan;
	}

	public void setGtnCFamilyPlan(GtnCFamilyPlan gtnCFamilyPlan) {
		this.gtnCFamilyPlan = gtnCFamilyPlan;
	}

	public GtnCFamilyPlanValidationBean getGtnCFamilyPlanValidationBean() {
		return gtnCFamilyPlanValidationBean;
	}

	public void setGtnCFamilyPlanValidationBean(GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean) {
		this.gtnCFamilyPlanValidationBean = gtnCFamilyPlanValidationBean;
	}

}