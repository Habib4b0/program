package com.stpl.gtn.gtn2o.queryengine.config;

import java.util.Collections;
import java.util.List;

public class GtnFrameworkQueryEngineConfig {

	private List<GtnFrameworkQueryConfig> queryConfigList;

	private List<GtnFrameworkQueryEngineConfig> childQueryEngineConfigList;

	public GtnFrameworkQueryEngineConfig() {
		super();
	}

	public List<GtnFrameworkQueryConfig> getQueryConfigList() {
		return queryConfigList != null ? Collections.unmodifiableList(queryConfigList) : queryConfigList;
	}

	public void setQueryConfigList(List<GtnFrameworkQueryConfig> queryConfigList) {
		this.queryConfigList = queryConfigList != null ? Collections.unmodifiableList(queryConfigList)
				: queryConfigList;
	}

	public List<GtnFrameworkQueryEngineConfig> getChildQueryEngineConfigList() {
		return childQueryEngineConfigList != null ? Collections.unmodifiableList(childQueryEngineConfigList)
				: childQueryEngineConfigList;
	}

	public void setChildQueryEngineConfigList(List<GtnFrameworkQueryEngineConfig> childQueryEngineConfigList) {
		this.childQueryEngineConfigList = childQueryEngineConfigList != null
				? Collections.unmodifiableList(childQueryEngineConfigList)
				: childQueryEngineConfigList;
	}

}
