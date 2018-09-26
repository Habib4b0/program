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
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkEmailConfigurationValidationTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnFrameworkEmailConfigurationValidationTest() {
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
     * Test of configureParams method, of class GtnFrameworkEmailConfigurationValidation.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEmailConfigurationValidation instance = new GtnFrameworkEmailConfigurationValidation();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkEmailConfigurationValidation.
     */
    @Test
    public void testDoAction() throws Exception {
        String componentId = "";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIGURATION_VALIDATION);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS);
        
        
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        GtnFrameworkEmailConfigurationValidation instance = new GtnFrameworkEmailConfigurationValidation();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of validationForConfig method, of class GtnFrameworkEmailConfigurationValidation.
     */
    @Test
    public void testValidationForConfig() throws Exception {
        String componentId = "value";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIGURATION_VALIDATION);
        gtnUIFrameWorkActionConfig.addActionParameter("");
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS);
        
        
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        GtnFrameworkEmailConfigurationValidation instance = new GtnFrameworkEmailConfigurationValidation();
        thrown.expect(GtnFrameworkValidationFailedException.class);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
    
    @Test
    public void testValidationForConfigPass() throws Exception {
        String componentId = "value";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIGURATION_VALIDATION);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS);
        
        
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("Process").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        GtnFrameworkEmailConfigurationValidation instance = new GtnFrameworkEmailConfigurationValidation();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
    

    /**
     * Test of saveActionTrigger method, of class GtnFrameworkEmailConfigurationValidation.
     */
    @Test
    public void testSaveActionTrigger() throws Exception {
        String componentId = "sd";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkEmailConfigStringContants.EMAIL_CONFIGURATION_VALIDATION);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_SMTP);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_HOST_NAME);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_EMAIL_ADDRESS);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_PASS_FIELD);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_PORT_NUMBER);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.EMAIL_CONFIG_TAB_TEST_MAIL_ADDRESS);
        
        
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        GtnFrameworkEmailConfigurationValidation instance = new GtnFrameworkEmailConfigurationValidation();
        GtnWsMailConfigurationRequest mcRequest=new GtnWsMailConfigurationRequest();
        instance.saveActionTrigger(mcRequest, componentId);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkEmailConfigurationValidation.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkEmailConfigurationValidation instance = new GtnFrameworkEmailConfigurationValidation();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
