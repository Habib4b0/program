package com.stpl.gtn.gtn2o.ws.request.processscheduler;

import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;

public class GtnWsProcessSchedulerRequest {

	public GtnWsProcessSchedulerRequest() {
		super();
	}

	private GtnWsProcessSchedulerBean processSchedulerBean;
	private GtnCffOutBoundBean cffOutBoundBean;

	public GtnCffOutBoundBean getCffOutBoundBean() {
		return cffOutBoundBean;
	}

	public void setCffOutBoundBean(GtnCffOutBoundBean cffOutBoundBean) {
		this.cffOutBoundBean = cffOutBoundBean;
	}

	public GtnWsProcessSchedulerBean getProcessSchedulerBean() {
		return processSchedulerBean;
	}

	public void setProcessSchedulerBean(GtnWsProcessSchedulerBean processSchedulerBean) {
		this.processSchedulerBean = processSchedulerBean;
	}

}
