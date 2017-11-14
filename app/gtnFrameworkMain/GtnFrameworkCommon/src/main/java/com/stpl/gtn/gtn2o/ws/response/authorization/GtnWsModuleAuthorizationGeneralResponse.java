package com.stpl.gtn.gtn2o.ws.response.authorization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

public class GtnWsModuleAuthorizationGeneralResponse implements GtnWSResponseData {
	public GtnWsModuleAuthorizationGeneralResponse() {
		super();
	}

	private Map<String, GtnWsModuleAuthorizationBean> moduleWiseComponentMap = new HashMap<>();
	private List<GtnWsModuleAuthorizationBean> moduleWiseTablePropertyList = new ArrayList<>();
	private boolean moduleUpdate;

	public void addModuleWiseComponentMap(String componentId, GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean) {
		moduleWiseComponentMap.put(componentId, gtnWsModuleSecurityBean);
	}

	public GtnWsModuleAuthorizationBean getModuleWiseComponentMap(String componentId) {
		return moduleWiseComponentMap.get(componentId);
	}

	public void addModuleWiseTablePropertyList(GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean) {
		moduleWiseTablePropertyList.add(gtnWsModuleSecurityBean);
	}

	public GtnWsModuleAuthorizationBean getModuleWiseTablePropertyList(int index) {
		return moduleWiseTablePropertyList.get(index);
	}

	public List<GtnWsModuleAuthorizationBean> getModuleWiseTablePropertyList() {

		return moduleWiseTablePropertyList == null ? moduleWiseTablePropertyList
				: Collections.unmodifiableList(moduleWiseTablePropertyList);
	}

	public void setModuleWiseTablePropertyList(List<GtnWsModuleAuthorizationBean> moduleWiseTablePropertyList) {
		this.moduleWiseTablePropertyList = moduleWiseTablePropertyList == null ? moduleWiseTablePropertyList
				: Collections.unmodifiableList(moduleWiseTablePropertyList);
	}

	public boolean isModuleUpdate() {
		return moduleUpdate;
	}

	public void setModuleUpdate(boolean moduleUpdate) {
		this.moduleUpdate = moduleUpdate;
	}

	public Map<String, GtnWsModuleAuthorizationBean> getModuleWiseComponentMap() {
		return moduleWiseComponentMap == null ? moduleWiseComponentMap
				: Collections.unmodifiableMap(moduleWiseComponentMap);
	}

}
