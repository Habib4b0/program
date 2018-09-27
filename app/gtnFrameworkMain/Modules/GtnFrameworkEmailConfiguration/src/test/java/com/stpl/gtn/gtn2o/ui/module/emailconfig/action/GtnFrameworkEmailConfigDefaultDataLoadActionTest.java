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
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
import com.vaadin.v7.ui.TextField;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkEmailConfigDefaultDataLoadActionTest {
    
    public GtnFrameworkEmailConfigDefaultDataLoadActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkEmailConfigDefaultDataLoadAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEmailConfigDefaultDataLoadAction instance = new GtnFrameworkEmailConfigDefaultDataLoadAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkEmailConfigDefaultDataLoadAction.
     */
    @Test
    public void testDoActionFail() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        GtnFrameworkEmailConfigDefaultDataLoadAction instance = new GtnFrameworkEmailConfigDefaultDataLoadAction();
        
        GtnFrameworkEmailConfigDefaultDataLoadAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
    
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
        


        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;

        
        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkEmailConfigDefaultDataLoadAction instance = new GtnFrameworkEmailConfigDefaultDataLoadAction();
        
        GtnFrameworkEmailConfigDefaultDataLoadAction in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob={1,"localhost","john.smith","johnS@123","25","Yes","support@bpitechnologies.com"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setDefaultDataLoad(defaultDataLoad);
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of getDefaultValue method, of class GtnFrameworkEmailConfigDefaultDataLoadAction.
     */
    @Test
    public void testGetDefaultValue() {
        try{
        System.out.println("getDefaultValue");
        String componentId = "V009";
        GtnFrameworkEmailConfigDefaultDataLoadAction instance = new GtnFrameworkEmailConfigDefaultDataLoadAction();
        Method method = instance.getClass().getDeclaredMethod("getDefaultValue",String.class);
        method.setAccessible(true);
        method.invoke(instance, componentId);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkEmailConfigDefaultDataLoadActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createInstance method, of class GtnFrameworkEmailConfigDefaultDataLoadAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkEmailConfigDefaultDataLoadAction instance = new GtnFrameworkEmailConfigDefaultDataLoadAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
