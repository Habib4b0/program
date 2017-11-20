package com.stpl.gtn.gtn2o.ws.request.processmonitor;

import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;

public class GtnWsProcessMonitorRequest {

	public GtnWsProcessMonitorRequest() {
		super();
	}

	private GtnWsProcessMonitorBean processMonitorBean;

	public GtnWsProcessMonitorBean getProcessMonitorBean() {
		return processMonitorBean;
	}

	public void setProcessMonitorBean(GtnWsProcessMonitorBean processMonitorBean) {
		this.processMonitorBean = processMonitorBean;
	}

}
