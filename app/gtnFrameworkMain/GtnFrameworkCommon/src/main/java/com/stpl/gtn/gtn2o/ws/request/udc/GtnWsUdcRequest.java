package com.stpl.gtn.gtn2o.ws.request.udc;

import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsBrandMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;

public class GtnWsUdcRequest implements GtnWSRequestData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GtnWsUdcBean gtnWsUdcBean;
	private GtnWsBrandMasterInfoBean gtnWsBrandMasterInfoBean;

	public GtnWsUdcRequest() {
		super();
	}

	public GtnWsUdcBean getGtnWsUdcBean() {
		return gtnWsUdcBean;
	}

	public void setGtnWsUdcBean(GtnWsUdcBean gtnWsUdcBean) {
		this.gtnWsUdcBean = gtnWsUdcBean;
	}

	public GtnWsBrandMasterInfoBean getGtnWsBrandMasterInfoBean() {
		return gtnWsBrandMasterInfoBean;
	}

	public void setGtnWsBrandMasterInfoBean(GtnWsBrandMasterInfoBean gtnWsBrandMasterInfoBean) {
		this.gtnWsBrandMasterInfoBean = gtnWsBrandMasterInfoBean;
	}

}
