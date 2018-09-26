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
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
import java.util.Arrays;
import java.util.List;
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
import static org.powermock.api.mockito.PowerMockito.doReturn;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkEmailConfigSaveActionTest {
    
    public GtnFrameworkEmailConfigSaveActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkEmailConfigSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEmailConfigSaveAction instance = new GtnFrameworkEmailConfigSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkEmailConfigSaveAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        doNothing().when(tableBaseComponent).setVisible(Mockito.anyBoolean());
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnWsMailConfigurationRequest mcRequest =new GtnWsMailConfigurationRequest();
        List<Object> obj=Arrays.asList(0,mcRequest);
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnFrameworkEmailConfigSaveAction instance = new GtnFrameworkEmailConfigSaveAction();
        
        GtnFrameworkEmailConfigSaveAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        gtnWsMailConfigurationResponse.setSuccess(true);
        gtnWsMailConfigurationResponse.isSuccess();
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
        

    }

    /**
     * Test of saveMailConfiguration method, of class GtnFrameworkEmailConfigSaveAction.
     */
    @Test
    public void testSaveMailConfiguration() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(tableBaseComponent).setComponentEnable(Mockito.anyBoolean());
        doNothing().when(tableBaseComponent).setVisible(Mockito.anyBoolean());
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnWsMailConfigurationRequest mcRequest =new GtnWsMailConfigurationRequest();
        List<Object> obj=Arrays.asList(0,mcRequest);
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnFrameworkEmailConfigSaveAction instance = new GtnFrameworkEmailConfigSaveAction();
        
        GtnFrameworkEmailConfigSaveAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        gtnWsMailConfigurationResponse.setSuccess(true);
        gtnWsMailConfigurationResponse.isSuccess();
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkEmailConfigSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkEmailConfigSaveAction instance = new GtnFrameworkEmailConfigSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
