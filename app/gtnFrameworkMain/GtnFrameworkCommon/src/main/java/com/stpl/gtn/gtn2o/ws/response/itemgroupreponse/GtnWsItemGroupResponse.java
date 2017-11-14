/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.itemgroupreponse;

import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemGroupResponse implements GtnWSRequestData {

	public GtnWsItemGroupResponse() {
		super();
	}

	private GtnItemGrpInformationBean gtnItemGrpInformationBean;

	private GtnWsItemGrpValidationBean gtnWsItemGrpValidationBean;

	public GtnItemGrpInformationBean getGtnItemGrpInformationBean() {
		return gtnItemGrpInformationBean;
	}

	public void setGtnItemGrpInformationBean(GtnItemGrpInformationBean gtnItemGrpInformationBean) {
		this.gtnItemGrpInformationBean = gtnItemGrpInformationBean;
	}

	public GtnWsItemGrpValidationBean getGtnWsItemGrpValidationBean() {
		return gtnWsItemGrpValidationBean;
	}

	public void setGtnWsItemGrpValidationBean(GtnWsItemGrpValidationBean gtnWsItemGrpValidationBean) {
		this.gtnWsItemGrpValidationBean = gtnWsItemGrpValidationBean;
	}

}
