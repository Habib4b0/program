package com.stpl.gtn.gtn2o.ws.response.processmonitor;

import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

public class GtnWsProcessMonitorResponse implements GtnWSResponseData {

	public GtnWsProcessMonitorResponse() {
		super();
	}

	private GtnWsProcessMonitorBean monitorBean;

	public GtnWsProcessMonitorBean getMonitorBean() {
		return monitorBean;
	}

	public void setMonitorBean(GtnWsProcessMonitorBean monitorBean) {
		this.monitorBean = monitorBean;
	}

}
