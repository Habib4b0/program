package com.stpl.gtn.gtn2o.registry.config.lookups;

import static org.junit.Assert.assertFalse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnFrameworkForecastProductHierarchyLookUpTest {

	
	@Test
    public void testMainScreenConfigPrivateMethod1() {
        String methodName = "addForecastProductHierarchyLookUpRootLayout";
        GtnFrameworkForecastProductHierarchyLookUp instance = new GtnFrameworkForecastProductHierarchyLookUp();
        testComponentConfig(methodName, instance);
    }
	
	@Test
    public void testMainScreenConfigPrivateMethod2() {
		GtnFrameworkForecastProductHierarchyLookUp instance = new GtnFrameworkForecastProductHierarchyLookUp();
       assertFalse( instance.getProdHierarchyLookUpView("Commercial Forecasting")==null);
    }


	 public void testComponentConfig(String gtnFrameworkConfigMethodName, GtnFrameworkForecastProductHierarchyLookUp instance) {
	        System.out.println(gtnFrameworkConfigMethodName);
	        Method arr[] = instance.getClass().getDeclaredMethods();
	        Method method = null;
	        for (Method met : arr) {
	            if (gtnFrameworkConfigMethodName.equals(met.getName())) {
	                method = met;
	                break;
	            }
	        }
	        int methodParams = method != null ? method.getParameterCount() : 0;
	        if (methodParams != 0) {
	            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, instance);
	            assertFalse(componentList.isEmpty());
	        }
	    }

	    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkForecastProductHierarchyLookUp instance) throws SecurityException {
	        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
	        try {
	            Class[] classArr = method.getParameterTypes();
	            Object[] obj = new Object[method.getParameterTypes().length];
	            obj[0] = componentList;
	            for (int i = 1; i < obj.length; i++) {
	                switch (classArr[i].getName()) {
	                    case "java.lang.String":
	                        obj[i] = "VALUE";
	                        break;
	                    case "java.util.List":
	                        obj[i] = new ArrayList<>();
	                        break;
	                    default:
	                        break;
	                }
	            }
	            method.setAccessible(true);
	            method.invoke(instance, obj);
	        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
	            Logger.getLogger(GtnFrameworkForecastProductHierarchyLookUp.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return componentList;
	    }
}
