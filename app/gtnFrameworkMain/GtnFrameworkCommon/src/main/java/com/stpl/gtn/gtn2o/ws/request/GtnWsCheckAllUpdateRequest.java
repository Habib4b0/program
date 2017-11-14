/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsCheckAllUpdateRequest {

	public GtnWsCheckAllUpdateRequest() {
		super();
	}

	private GtnWsCheckAllUpdateBean updateBean;

	public GtnWsCheckAllUpdateBean getUpdateBean() {
		return updateBean;
	}

	public void setUpdateBean(GtnWsCheckAllUpdateBean updateBean) {
		this.updateBean = updateBean;
	}

}
