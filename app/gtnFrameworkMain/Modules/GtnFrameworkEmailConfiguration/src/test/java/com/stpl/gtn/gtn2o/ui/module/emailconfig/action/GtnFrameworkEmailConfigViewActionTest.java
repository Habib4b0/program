/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.emailconfig.constants.GtnFrameworkEmailConfigStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkEmailConfigViewActionTest {
    
    public GtnFrameworkEmailConfigViewActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkEmailConfigViewAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEmailConfigViewAction instance = new GtnFrameworkEmailConfigViewAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkEmailConfigViewAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        List<Object> obj=Arrays.asList(1,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME,
                GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD,
                GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS,
                GtnFrameworkCommonConstants.MAIL_CONFIG_VIEW_BUTTON,GtnFrameworkCommonConstants.EMAIL_CONFIG_ADD_SAVE_BUTTON,
                GtnFrameworkCommonConstants.EMAIL_CONFIG_BACK_BTN);
        
        
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        doNothing().when(tableBaseComponent).setVisible(Mockito.anyBoolean());
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnFrameworkEmailConfigViewAction instance = new GtnFrameworkEmailConfigViewAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of saveForecastConfiguration method, of class GtnFrameworkEmailConfigViewAction.
     */
    @Test
    public void testSaveForecastConfiguration() {
        try{
        System.out.println("saveForecastConfiguration");
         System.out.println("doAction");;
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        List<Object> obj=Arrays.asList(1,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME,
                GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD,
                GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER,GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS,
                GtnFrameworkCommonConstants.MAIL_CONFIG_VIEW_BUTTON,GtnFrameworkCommonConstants.EMAIL_CONFIG_ADD_SAVE_BUTTON,
                GtnFrameworkCommonConstants.EMAIL_CONFIG_BACK_BTN);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        doNothing().when(tableBaseComponent).setVisible(Mockito.anyBoolean());
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnFrameworkEmailConfigViewAction instance = new GtnFrameworkEmailConfigViewAction();
        Method method = instance.getClass().getDeclaredMethod("saveForecastConfiguration",List.class);
        method.setAccessible(true);
        method.invoke(instance, obj);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkEmailConfigViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createInstance method, of class GtnFrameworkEmailConfigViewAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkEmailConfigViewAction instance = new GtnFrameworkEmailConfigViewAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
