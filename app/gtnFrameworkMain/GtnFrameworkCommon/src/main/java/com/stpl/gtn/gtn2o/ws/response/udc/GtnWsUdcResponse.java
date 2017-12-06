package com.stpl.gtn.gtn2o.ws.response.udc;

import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;

public class GtnWsUdcResponse implements GtnWSRequestData{
	
	public GtnWsUdcResponse() {
		super();
	}
	private static final long serialVersionUID = 1L;
	private GtnWsUdcBean gtnWsUdcBean;

	public GtnWsUdcBean getGtnWsUdcBean() {
		return gtnWsUdcBean;
	}

	public void setGtnWsUdcBean(GtnWsUdcBean gtnWsUdcBean) {
		this.gtnWsUdcBean = gtnWsUdcBean;
	}
	
	

}
