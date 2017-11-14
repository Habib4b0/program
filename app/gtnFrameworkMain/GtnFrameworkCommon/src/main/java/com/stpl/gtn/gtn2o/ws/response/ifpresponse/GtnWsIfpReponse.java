/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.ifpresponse;

import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;

public class GtnWsIfpReponse {

	public GtnWsIfpReponse() {
		super();
	}

	private GtnIFamilyPlanBean gtnIFamilyPlan;

	private GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean;

	public GtnIFamilyPlanBean getGtnIFamilyPlan() {
		return gtnIFamilyPlan;
	}

	public void setGtnIFamilyPlan(GtnIFamilyPlanBean gtnIFamilyPlan) {
		this.gtnIFamilyPlan = gtnIFamilyPlan;
	}

	public GtnIFamilyPlanValidationBean getGtnIFamilyPlanValidationBean() {
		return gtnIFamilyPlanValidationBean;
	}

	public void setGtnIFamilyPlanValidationBean(GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean) {
		this.gtnIFamilyPlanValidationBean = gtnIFamilyPlanValidationBean;
	}

}