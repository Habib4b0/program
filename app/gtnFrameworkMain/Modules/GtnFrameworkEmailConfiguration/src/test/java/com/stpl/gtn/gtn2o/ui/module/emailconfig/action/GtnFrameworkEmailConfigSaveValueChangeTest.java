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
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
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
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
//import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkEmailConfigSaveValueChangeTest {
    
    public GtnFrameworkEmailConfigSaveValueChangeTest() {
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
     * Test of configureParams method, of class GtnFrameworkEmailConfigSaveValueChange.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEmailConfigSaveValueChange instance = new GtnFrameworkEmailConfigSaveValueChange();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkEmailConfigSaveValueChange.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
        


        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter("addProcessName");
        
//        List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
//		resetActionParams.set(2, "val");
//		resetActionParams.set(1, "addProcessName");
//		resetActionParams.set(3, "val");
//		resetActionParams.set(4, "val");
//	gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
        
     
        
//        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
//        cons.setAccessible(true);
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkEmailConfigSaveValueChange instance = new GtnFrameworkEmailConfigSaveValueChange();
        
        GtnFrameworkEmailConfigSaveValueChange in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob={3,"SUPPORT@BPITECHNOLOGIES.COM","SUCCESS - CFF_OUTBOUND_INTERFACE","SUPPORT@BPITECHNOLOGIES.COM","SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID","COUNTS","SUPPORT@BPITECHNOLOGIES.COM","FAILURE - CFF_OUTBOUND_INTERFACE",
                                "SUPPORT@BPITECHNOLOGIES.COM","CFF_OUTBOUND_INTERFACE FAILURE","CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad );
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
      
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of getModifiedRequest method, of class GtnFrameworkEmailConfigSaveValueChange.
     */
    @Test
    public void testGetModifiedRequest() {
        System.out.println("getModifiedRequest");
        String componentId = "V009";
        


        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
//        gtnUIFrameWorkActionConfig.addActionParameter(1);
//        gtnUIFrameWorkActionConfig.addActionParameter("addProcessName");
        
        List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "addProcessName");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
	gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
        
        List<String> fieldValues =Arrays.asList("addEmailTo","addEmailCc","addSubject","addEmailBody","addFailureTo","addEmailCc1","addSubject1","addEmailBody1");
        gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
        
//        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
//        cons.setAccessible(true);

        GtnWsGeneralRequest generalRequest=new GtnWsGeneralRequest();
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); // now run 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkEmailConfigSaveValueChange instance = new GtnFrameworkEmailConfigSaveValueChange();
        
        GtnFrameworkEmailConfigSaveValueChange in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob={3,"SUPPORT@BPITECHNOLOGIES.COM","SUCCESS - CFF_OUTBOUND_INTERFACE","SUPPORT@BPITECHNOLOGIES.COM","SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID","COUNTS","SUPPORT@BPITECHNOLOGIES.COM","FAILURE - CFF_OUTBOUND_INTERFACE",
                                "SUPPORT@BPITECHNOLOGIES.COM","CFF_OUTBOUND_INTERFACE FAILURE","CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad );
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
      
        in.getModifiedRequest(eMailConfigurationBean, generalRequest,resetActionParams);
    }

    /**
     * Test of processNameOnChange method, of class GtnFrameworkEmailConfigSaveValueChange.
     */
    @Test
    public void testProcessNameOnChange() {
        System.out.println("testProcessNameOnChange");
        String componentId = "V009";
        


        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
//        gtnUIFrameWorkActionConfig.addActionParameter(1);
//        gtnUIFrameWorkActionConfig.addActionParameter("addProcessName");
        
        List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "addProcessName");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
	gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
        
        List<String> fieldValues =Arrays.asList("addEmailTo","addEmailCc","addSubject","addEmailBody","addFailureTo","addEmailCc1","addSubject1","addEmailBody1");
        gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
        
//        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
//        cons.setAccessible(true);
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); // now run 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkEmailConfigSaveValueChange instance = new GtnFrameworkEmailConfigSaveValueChange();
        
        GtnFrameworkEmailConfigSaveValueChange in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob={3,"SUPPORT@BPITECHNOLOGIES.COM","SUCCESS - CFF_OUTBOUND_INTERFACE","SUPPORT@BPITECHNOLOGIES.COM","SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID","COUNTS","SUPPORT@BPITECHNOLOGIES.COM","FAILURE - CFF_OUTBOUND_INTERFACE",
                                "SUPPORT@BPITECHNOLOGIES.COM","CFF_OUTBOUND_INTERFACE FAILURE","CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad );
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
      
        in.processNameOnChange(resetActionParams, componentId,fieldValues);
    }

    /**
     * Test of replaceComma method, of class GtnFrameworkEmailConfigSaveValueChange.
     */
    @Test
    public void testReplaceComma() {
        System.out.println("replaceComma");
        Object emailIds = "emailconfig@gmail.com,email2config@gmail.com";
        GtnFrameworkEmailConfigSaveValueChange instance = new GtnFrameworkEmailConfigSaveValueChange();
        String result = instance.replaceComma(emailIds);
        assertFalse(emailIds==null);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkEmailConfigSaveValueChange.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkEmailConfigSaveValueChange instance = new GtnFrameworkEmailConfigSaveValueChange();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
