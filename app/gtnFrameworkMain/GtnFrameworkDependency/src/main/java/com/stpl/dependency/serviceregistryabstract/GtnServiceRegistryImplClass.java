package com.stpl.dependency.serviceregistryabstract;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;

public abstract class GtnServiceRegistryImplClass {

	public String readPropertyFile(String propertyFile){
		return "readPropertyFile:"+propertyFile;
	}
	
	public GtnFrameworkDependencyLogger logInformation(Class<?> className){
		GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger.getGTNLogger(className);
		return gtnLogger;
	}
}
