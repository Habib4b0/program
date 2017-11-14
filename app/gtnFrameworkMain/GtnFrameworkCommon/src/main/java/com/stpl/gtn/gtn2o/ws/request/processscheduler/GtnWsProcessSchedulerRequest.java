package com.stpl.gtn.gtn2o.ws.request.processscheduler;

import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;

public class GtnWsProcessSchedulerRequest {

	public GtnWsProcessSchedulerRequest() {
		super();
	}

	private GtnWsProcessSchedulerBean processSchedulerBean;

	public GtnWsProcessSchedulerBean getProcessSchedulerBean() {
		return processSchedulerBean;
	}

	public void setProcessSchedulerBean(GtnWsProcessSchedulerBean processSchedulerBean) {
		this.processSchedulerBean = processSchedulerBean;
	}

}
