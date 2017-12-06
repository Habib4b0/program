package com.stpl.gtn.gtn2o.ws.udc.bean;

import java.io.Serializable;

public class GtnWsUdcBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsUdcBean(){
		super();
	}
	private GtnWsUdcInfoBean gtnWsUdcInfoBean;

	public GtnWsUdcInfoBean getGtnWsUdcInfoBean() {
		return gtnWsUdcInfoBean;
	}
	public void setGtnWsUdcInfoBean(GtnWsUdcInfoBean gtnWsUdcInfoBean) {
		this.gtnWsUdcInfoBean = gtnWsUdcInfoBean;
	}
	
	
}
