package com.stpl.gtn.gtn2o.registry.config.dataassumptions;

import static org.junit.Assert.assertFalse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnFrameworkDataAssumptionsTabConfigTest {
	
	@Test
    public void testMainScreenConfigPrivateMethod1() {
        String methodName = "addDataAssumptionsTabComponents";
        GtnFrameworkDataAssumptionsTabConfig instance = new GtnFrameworkDataAssumptionsTabConfig();
        testComponentConfig(methodName, instance);
    }

	
	public void testComponentConfig(String methodName, GtnFrameworkDataAssumptionsTabConfig gtnFrameworkDataAssumptionsTabConfig) {
        System.out.println(methodName);
        Method arr[] = gtnFrameworkDataAssumptionsTabConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (methodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkDataAssumptionsTabConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkDataAssumptionsTabConfig gtnFrameworkDataAssumptionsTabConfig) throws SecurityException {
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
            method.invoke(gtnFrameworkDataAssumptionsTabConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkDataAssumptionsTabConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
}
