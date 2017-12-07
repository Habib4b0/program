package com.stpl.gtn.gtn2o.ws.udc.bean;

import java.io.Serializable;

import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;

public class GtnWsBrandMasterInfoBean implements Serializable {

	public GtnWsBrandMasterInfoBean() {
		super();
	}
	private static final long serialVersionUID = 1L;
	private GtnWsBrandMasterBean gtnWsBrandMasterBean;

	public GtnWsBrandMasterBean getGtnWsBrandMasterBean() {
		return gtnWsBrandMasterBean;
	}

	public void setGtnWsBrandMasterBean(GtnWsBrandMasterBean gtnWsBrandMasterBean) {
		this.gtnWsBrandMasterBean = gtnWsBrandMasterBean;
	}

}
