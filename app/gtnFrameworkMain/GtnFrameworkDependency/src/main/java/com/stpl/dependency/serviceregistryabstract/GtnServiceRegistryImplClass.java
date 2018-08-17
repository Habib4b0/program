package com.stpl.dependency.serviceregistryabstract;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;

public abstract class GtnServiceRegistryImplClass {

	public GtnFrameworkDependencyLogger logger;
	
	public String readPropertyFile(String propertyFile){
		return "readPropertyFile:"+propertyFile;
	}
	
	public void logInformation(Class<?> className){
		logger = GtnFrameworkDependencyLogger.getGTNLogger(className);
	}
}
