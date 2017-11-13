/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.response.netsales;

import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsNetSalesGeneralResponse {

	public GtnWsNetSalesGeneralResponse() {
		super();
	}

	private GtnUIFrameworkNsfInfoBean nsfInfoBean;
	private GtnWsNsfUpdateBean nsfUpdateBean;

	public GtnUIFrameworkNsfInfoBean getNsfInfoBean() {
		return nsfInfoBean;
	}

	public void setNsfInfoBean(GtnUIFrameworkNsfInfoBean nsfInfoBean) {
		this.nsfInfoBean = nsfInfoBean;
	}

	public GtnWsNsfUpdateBean getNsfUpdateBean() {
		return nsfUpdateBean;
	}

	public void setNsfUpdateBean(GtnWsNsfUpdateBean nsfUpdateBean) {
		this.nsfUpdateBean = nsfUpdateBean;
	}

}
