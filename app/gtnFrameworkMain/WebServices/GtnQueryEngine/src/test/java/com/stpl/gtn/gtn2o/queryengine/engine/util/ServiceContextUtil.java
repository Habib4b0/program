package com.stpl.gtn.gtn2o.queryengine.engine.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceContextUtil  {

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
    public static synchronized ServiceContextUtil instance() {
		if (null == serviceContextUtil) {
			serviceContextUtil = new ServiceContextUtil();
            System.out.println("\n Instance created \n");
        }
		return serviceContextUtil;
    }

    public Object getBean(String name) {

        if (appContext == null) {

			String[] contextFiles = { "GtnFrameworkQueryEngine-test.xml" };
            appContext = new ClassPathXmlApplicationContext(contextFiles);

        }
        return appContext.getBean(name);
    }
}
