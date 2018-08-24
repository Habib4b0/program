package com.stpl.gtn.gtn2o.ws.response.serviceregistry;

import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnServiceRegistryWSResponse {

	public GtnServiceRegistryWSResponse() {
		super();
	}

	private GtnWsServiceRegistryBean gtnWsServiceRegistryBean;

	public GtnWsServiceRegistryBean getGtnWsServiceRegistryBean() {
		return gtnWsServiceRegistryBean;
	}

	public void setGtnWsServiceRegistryBean(GtnWsServiceRegistryBean gtnWsServiceRegistryBean) {
		this.gtnWsServiceRegistryBean = gtnWsServiceRegistryBean;
	}

}
