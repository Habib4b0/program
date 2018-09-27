package com.stpl.gtn.gtn2o.registry.config;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import static org.junit.Assert.assertFalse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnUIFrameworkDataSelectionScreenConfigTest {

	@Test
    public void testMainScreenConfigPrivateMethod1() {
        String methodName = "addRootLayout";
        GtnUIFrameworkDataSelectionScreenConfig instance = new GtnUIFrameworkDataSelectionScreenConfig();
        testComponentConfig(methodName, instance);
    }
	@Test
    public void testMainScreenConfigPrivateMethod2() {
        String methodName = "addProjectionOptionsPanel";
        GtnUIFrameworkDataSelectionScreenConfig instance = new GtnUIFrameworkDataSelectionScreenConfig();
        testComponentConfig(methodName, instance);
    }
	@Test
    public void testMainScreenConfigPrivateMethod3() {
        String methodName = "addProjectionOptionInnerLayout";
        GtnUIFrameworkDataSelectionScreenConfig instance = new GtnUIFrameworkDataSelectionScreenConfig();
        testComponentConfig(methodName, instance);
    }
	@Test
    public void testMainScreenConfigPrivateMethod4() {
        String methodName = "addModeSelectionLayout";
        GtnUIFrameworkDataSelectionScreenConfig instance = new GtnUIFrameworkDataSelectionScreenConfig();
        testComponentConfig(methodName, instance);
    }
	@Test
	public void testPublicMethod()
	{
        GtnUIFrameworkDataSelectionScreenConfig instance = new GtnUIFrameworkDataSelectionScreenConfig();
        instance.getDataSelectionView(GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING);
	}
	
	
	 public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnUIFrameworkDataSelectionScreenConfig gtnFrameworkContractMainConfig) {
	        System.out.println(gtnFrameworkContractMainConfigMethodName);
	        Method arr[] = gtnFrameworkContractMainConfig.getClass().getDeclaredMethods();
	        Method method = null;
	        for (Method met : arr) {
	            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
	                method = met;
	                break;
	            }
	        }
	        int methodParams = method != null ? method.getParameterCount() : 0;
	        if (methodParams != 0) {
	            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractMainConfig);
	            assertFalse(componentList.isEmpty());
	        }
	    }

	    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnUIFrameworkDataSelectionScreenConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
	            method.invoke(gtnFrameworkContractMainConfig, obj);
	        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
	            Logger.getLogger(GtnUIFrameworkDataSelectionScreenConfig.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return componentList;
	    }
}
