/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.config.popup;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sureka.selvam
 */
public class GtnUIFrameworkItemMasterParentCompanyPopupConfigTest {
    
    public GtnUIFrameworkItemMasterParentCompanyPopupConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSearchView method, of class GtnUIFrameworkItemMasterParentCompanyPopupConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        String methodName = "getSearchView";
        GtnUIFrameworkItemMasterParentCompanyPopupConfig instance = new  GtnUIFrameworkItemMasterParentCompanyPopupConfig();
        testComponentConfig(methodName, instance);
    }
     public void testComponentConfig(String gtnUIFrameworkItemMasterParentCompanyPopupConfigMethodName, GtnUIFrameworkItemMasterParentCompanyPopupConfig gtnUIFrameworkItemMasterParentCompanyPopupConfig) {
        System.out.println(gtnUIFrameworkItemMasterParentCompanyPopupConfigMethodName);
        Method arr[] = gtnUIFrameworkItemMasterParentCompanyPopupConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnUIFrameworkItemMasterParentCompanyPopupConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnUIFrameworkItemMasterParentCompanyPopupConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnUIFrameworkItemMasterParentCompanyPopupConfig gtnFrameworkItemMastertMainConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
                String name = classArr[i].getName();
                switch (classArr[i].getName()) {
                    case "java.lang.String":
                        obj[i] = "VALUE";
                        break;
                    case "java.util.List":
                        obj[i] = new ArrayList<>();
                        break;
                    case "com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider":
                        obj[i] = GtnFrameworkComponentConfigProvider.getInstance();
                        break;
                    default:
                        break;
                }
            }
            method.setAccessible(true);
            method.invoke(gtnFrameworkItemMastertMainConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnUIFrameworkItemMasterParentCompanyPopupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
    
}
