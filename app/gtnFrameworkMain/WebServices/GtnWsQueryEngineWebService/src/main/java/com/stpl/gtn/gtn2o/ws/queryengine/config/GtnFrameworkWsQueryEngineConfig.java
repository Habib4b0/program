package com.stpl.gtn.gtn2o.ws.queryengine.config;

import java.util.List;

public class GtnFrameworkWsQueryEngineConfig {
	
	private List<GtnFrameworkWsQueryConfig> queryConfigList;

	private List<GtnFrameworkWsQueryEngineConfig> childQueryEngineConfigList;

	public GtnFrameworkWsQueryEngineConfig() {
		super();
	}

	public List<GtnFrameworkWsQueryConfig> getQueryConfigList() {
		return queryConfigList;
	}

	public void setQueryConfigList(List<GtnFrameworkWsQueryConfig> queryConfigList) {
		this.queryConfigList = queryConfigList;
	}

	public List<GtnFrameworkWsQueryEngineConfig> getChildQueryEngineConfigList() {
		return childQueryEngineConfigList;
	}

	public void setChildQueryEngineConfigList(List<GtnFrameworkWsQueryEngineConfig> childQueryEngineConfigList) {
		this.childQueryEngineConfigList = childQueryEngineConfigList;
	}
}
