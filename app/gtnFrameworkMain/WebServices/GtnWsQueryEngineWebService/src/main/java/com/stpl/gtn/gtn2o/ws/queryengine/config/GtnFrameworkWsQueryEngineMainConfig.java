package com.stpl.gtn.gtn2o.ws.queryengine.config;

public class GtnFrameworkWsQueryEngineMainConfig {
	private Object[] queryMemoryArray;

	private GtnFrameworkWsQueryEngineConfig rootEngineConfig;

	public GtnFrameworkWsQueryEngineMainConfig() {
		super();
	}

	public GtnFrameworkWsQueryEngineMainConfig(GtnFrameworkWsQueryEngineConfig rootEngineConfig) {
		super();
		this.rootEngineConfig = rootEngineConfig;
	}

	public Object[] getQueryMemoryArray() {
		return queryMemoryArray;
	}

	public void setQueryMemoryArray(Object[] queryMemoryArray) {
		this.queryMemoryArray = queryMemoryArray;
	}

	public GtnFrameworkWsQueryEngineConfig getRootEngineConfig() {
		return rootEngineConfig;
	}

	public void setRootEngineConfig(GtnFrameworkWsQueryEngineConfig rootEngineConfig) {
		this.rootEngineConfig = rootEngineConfig;
	}
}
