package com.stpl.gtn.gtn2o.ws.util;

public class GtnWsProcessUtil {
	private GtnWsProcessUtil() {
		super();
	}

	public static ProcessBuilder createProcess(String[] commands) {
		return new ProcessBuilder(commands);
	}

}
