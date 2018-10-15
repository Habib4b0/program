package com.stpl.dependency.queryengine.response;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryResponseBean;
import com.stpl.dependency.queryengine.bean.GtnQueryEngineWebServiceBean;

public class GtnQueryEngineWebServiceResponse {

	private Object result;

	private GtnQueryEngineWebServiceBean queryEngineWebServiceBean;
	private GtnFrameworkQueryResponseBean queryResponseBean;
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public GtnQueryEngineWebServiceBean getQueryEngineWebServiceBean() {
		return queryEngineWebServiceBean;
	}

	public void setQueryEngineWebServiceBean(GtnQueryEngineWebServiceBean queryEngineWebServiceBean) {
		this.queryEngineWebServiceBean = queryEngineWebServiceBean;
	}

	public GtnFrameworkQueryResponseBean getQueryResponseBean() {
		return queryResponseBean;
	}

	public void setQueryResponseBean(GtnFrameworkQueryResponseBean queryResponseBean) {
		this.queryResponseBean = queryResponseBean;
	}

}
