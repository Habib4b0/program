package com.stpl.gtn.gtn2o.ws.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GtnWSLogger {

	private Logger logger = null;

	private GtnWSLogger(Class<?> className) {

		logger = LogManager.getLogger(className);
	}

	public static GtnWSLogger getGTNLogger(Class<?> className) {
		return new GtnWSLogger(className);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void debug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	public void error(String message) {
		logger.error(message);
	}

	public void error(String message, Exception e) {
		logger.error(message, e);
	}

	public void queryLog(String message) {
		logger.info(message);
	}

	public void entry() {
		return;

	}

	public void exit() {
		return;
	}

}
