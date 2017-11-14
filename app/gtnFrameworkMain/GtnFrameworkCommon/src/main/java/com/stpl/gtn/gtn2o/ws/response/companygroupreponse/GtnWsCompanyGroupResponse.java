/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.companygroupreponse;

import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsCompanyGroupResponse implements GtnWSRequestData {

	public GtnWsCompanyGroupResponse() {
		super();
	}

	private GtnCompanyGrpInformationBean gtnCompanyGrpInformationBean;

	private GtnCompanyGrpValidationBean gtnCompanyGrpValidationBean;

	public GtnCompanyGrpInformationBean getGtnCompanyGrpInformationBean() {
		return gtnCompanyGrpInformationBean;
	}

	public void setGtnCompanyGrpInformationBean(GtnCompanyGrpInformationBean gtnCompanyGrpInformationBean) {
		this.gtnCompanyGrpInformationBean = gtnCompanyGrpInformationBean;
	}

	public GtnCompanyGrpValidationBean getGtnCompanyGrpValidationBean() {
		return gtnCompanyGrpValidationBean;
	}

	public void setGtnCompanyGrpValidationBean(GtnCompanyGrpValidationBean gtnCompanyGrpValidationBean) {
		this.gtnCompanyGrpValidationBean = gtnCompanyGrpValidationBean;
	}

}
