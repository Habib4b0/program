package com.stpl.gtn.gtn2o.queryengine.config;

import java.util.List;

public class GtnFrameworkQueryEngineConfig {

	private List<GtnFrameworkQueryConfig> queryConfigList;

	private List<GtnFrameworkQueryEngineConfig> childQueryEngineConfigList;

	public GtnFrameworkQueryEngineConfig() {
		super();
	}

	public List<GtnFrameworkQueryConfig> getQueryConfigList() {
		return queryConfigList;
	}

	public void setQueryConfigList(List<GtnFrameworkQueryConfig> queryConfigList) {
		this.queryConfigList = queryConfigList;
	}

	public List<GtnFrameworkQueryEngineConfig> getChildQueryEngineConfigList() {
		return childQueryEngineConfigList;
	}

	public void setChildQueryEngineConfigList(List<GtnFrameworkQueryEngineConfig> childQueryEngineConfigList) {
		this.childQueryEngineConfigList = childQueryEngineConfigList;
	}

}
