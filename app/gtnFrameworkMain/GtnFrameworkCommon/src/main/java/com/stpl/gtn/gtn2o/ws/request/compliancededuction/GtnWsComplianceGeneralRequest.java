/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request.compliancededuction;

import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.GtnWsCDRRuleInfoBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsComplianceGeneralRequest {

	public GtnWsComplianceGeneralRequest() {
		super();
	}

	private GtnWsCDRRuleInfoBean ruleInfoBean;

	public GtnWsCDRRuleInfoBean getRuleInfoBean() {
		return ruleInfoBean;
	}

	public void setRuleInfoBean(GtnWsCDRRuleInfoBean ruleInfoBean) {
		this.ruleInfoBean = ruleInfoBean;
	}

}
