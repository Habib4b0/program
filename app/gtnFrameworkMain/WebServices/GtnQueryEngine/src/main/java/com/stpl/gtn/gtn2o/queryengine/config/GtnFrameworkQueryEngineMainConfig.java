package com.stpl.gtn.gtn2o.queryengine.config;

public class GtnFrameworkQueryEngineMainConfig {

	private Object[] queryMemoryArray;

	private GtnFrameworkQueryEngineConfig rootEngineConfig;

	public GtnFrameworkQueryEngineMainConfig() {
		super();
	}

	public GtnFrameworkQueryEngineMainConfig(GtnFrameworkQueryEngineConfig rootEngineConfig) {
		super();
		this.rootEngineConfig = rootEngineConfig;
	}

	public Object[] getQueryMemoryArray() {
		return queryMemoryArray != null ? queryMemoryArray.clone() : null;
	}

	public void setQueryMemoryArray(Object[] queryMemoryArray) {
		this.queryMemoryArray = queryMemoryArray != null ? queryMemoryArray.clone() : null;
	}

	public GtnFrameworkQueryEngineConfig getRootEngineConfig() {
		return rootEngineConfig;
	}

	public void setRootEngineConfig(GtnFrameworkQueryEngineConfig rootEngineConfig) {
		this.rootEngineConfig = rootEngineConfig;
	}

}
