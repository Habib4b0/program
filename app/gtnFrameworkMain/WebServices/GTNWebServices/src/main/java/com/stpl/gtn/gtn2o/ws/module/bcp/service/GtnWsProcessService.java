package com.stpl.gtn.gtn2o.ws.module.bcp.service;

public class GtnWsProcessService{
	private GtnWsProcessService() {
		super();
	}

	public static ProcessBuilder createProcess(String[] commands) {
		return new ProcessBuilder(commands);
	}

}
