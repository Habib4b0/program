package com.stpl.gtn.gtn2o.ws.request.authorization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleComponentBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsTablePropertyBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsModuleAuthorizationGeneralRequest implements GtnWSRequestData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsModuleAuthorizationGeneralRequest() {
		super();
	}

	private GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean;
	private List<GtnWsModuleAuthorizationBean> gtnWsModuleSecuritySaveBeanList;
	private List<GtnWsModuleComponentBean> gtnWsModuleComponentBeanList;
	private List<GtnWsTablePropertyBean> gtnWsTablePropertyBeanList;

	public List<GtnWsModuleComponentBean> getGtnWsModuleComponentBeanList() {
		return gtnWsModuleComponentBeanList == null ? gtnWsModuleComponentBeanList
				: Collections.unmodifiableList(gtnWsModuleComponentBeanList);
	}

	public void setGtnWsModuleComponentBeanList(List<GtnWsModuleComponentBean> gtnWsModuleComponentBeanList) {
		this.gtnWsModuleComponentBeanList = gtnWsModuleComponentBeanList == null ? gtnWsModuleComponentBeanList
				: Collections.unmodifiableList(gtnWsModuleComponentBeanList);
	}

	public List<GtnWsTablePropertyBean> getGtnWsTablePropertyBeanList() {
		return gtnWsTablePropertyBeanList == null ? gtnWsTablePropertyBeanList
				: Collections.unmodifiableList(gtnWsTablePropertyBeanList);
	}

	public void setGtnWsTablePropertyBeanList(List<GtnWsTablePropertyBean> gtnWsTablePropertyBeanList) {
		this.gtnWsTablePropertyBeanList = gtnWsTablePropertyBeanList == null ? gtnWsTablePropertyBeanList
				: Collections.unmodifiableList(gtnWsTablePropertyBeanList);
	}

	public List<GtnWsModuleAuthorizationBean> getGtnWsModuleSecuritySaveBeanList() {
		return gtnWsModuleSecuritySaveBeanList == null ? gtnWsModuleSecuritySaveBeanList
				: Collections.unmodifiableList(gtnWsModuleSecuritySaveBeanList);
	}

	public void setGtnWsModuleSecuritySaveBeanList(List<GtnWsModuleAuthorizationBean> gtnWsModuleSecuritySaveBeanList) {
		this.gtnWsModuleSecuritySaveBeanList = gtnWsModuleSecuritySaveBeanList == null ? gtnWsModuleSecuritySaveBeanList
				: Collections.unmodifiableList(gtnWsModuleSecuritySaveBeanList);
	}

	public GtnWsModuleAuthorizationBean getGtnWsModuleSecurityBean() {
		return gtnWsModuleSecurityBean;
	}

	public void setGtnWsModuleSecurityBean(GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean) {
		this.gtnWsModuleSecurityBean = gtnWsModuleSecurityBean;
	}

	public void addGtnWsModuleSecuritySaveBean(GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean) {
		gtnWsModuleSecuritySaveBeanList.add(gtnWsModuleSecurityBean);
	}


}
