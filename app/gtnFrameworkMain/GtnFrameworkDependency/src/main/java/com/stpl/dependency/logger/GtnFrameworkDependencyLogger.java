package com.stpl.dependency.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GtnFrameworkDependencyLogger {
	private Logger logger = null;

	private GtnFrameworkDependencyLogger(Class<?> className) {

		logger = LogManager.getLogger(className);
	}

	public static GtnFrameworkDependencyLogger getGTNLogger(Class<?> className) {
		return new GtnFrameworkDependencyLogger(className);
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
		logger.debug(message);
	}

	public void trace(String message){
		logger.trace(message);
	}

	public void entry() {
		return;

	}

	public void exit() {
		return;
	}

}
