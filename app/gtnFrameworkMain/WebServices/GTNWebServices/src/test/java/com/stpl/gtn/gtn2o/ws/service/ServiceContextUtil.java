/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.service;

/**
 *
 * @author Shyam.Sundar
 */
import org.junit.Ignore;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Ignore
public class ServiceContextUtil {

	private ClassPathXmlApplicationContext appContext = null;
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private ServiceContextUtil serviceContextUtil = null;

	private ServiceContextUtil() {
		// ...
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public ServiceContextUtil instance() {
		if (null == serviceContextUtil) {
			serviceContextUtil = new ServiceContextUtil();
		}
		return serviceContextUtil;
	}

	public Object getBean(String name) {

		if (appContext == null) {

			String[] contextFiles = { "applicationServiceContext-ReturnsForecasting.xml" };
			appContext = new ClassPathXmlApplicationContext(contextFiles);

		}
		return appContext.getBean(name);
	}
}
