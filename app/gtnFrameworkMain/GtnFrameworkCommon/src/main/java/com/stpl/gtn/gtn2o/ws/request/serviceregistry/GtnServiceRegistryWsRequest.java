package com.stpl.gtn.gtn2o.ws.request.serviceregistry;

import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWebServiceRegisterBean;
import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnServiceRegistryWsRequest {

	private GtnWsServiceRegistryBean gtnWsServiceRegistryBean;
	private GtnWebServiceRegisterBean gtnWebServiceRegisterBean;

	public GtnWsServiceRegistryBean getGtnWsServiceRegistryBean() {
		return gtnWsServiceRegistryBean;
	}

	public void setGtnWsServiceRegistryBean(GtnWsServiceRegistryBean gtnWsServiceRegistryBean) {
		this.gtnWsServiceRegistryBean = gtnWsServiceRegistryBean;
	}

	public GtnWebServiceRegisterBean getGtnWebServiceRegisterBean() {
		return gtnWebServiceRegisterBean;
	}

	public void setGtnWebServiceRegisterBean(GtnWebServiceRegisterBean gtnWebServiceRegisterBean) {
		this.gtnWebServiceRegisterBean = gtnWebServiceRegisterBean;
	}

}
