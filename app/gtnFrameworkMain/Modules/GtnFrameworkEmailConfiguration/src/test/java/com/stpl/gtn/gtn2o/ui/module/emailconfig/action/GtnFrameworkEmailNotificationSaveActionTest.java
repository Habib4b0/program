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
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
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
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkEmailNotificationSaveActionTest {
    
    public GtnFrameworkEmailNotificationSaveActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkEmailNotificationSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkEmailNotificationSaveAction instance = new GtnFrameworkEmailNotificationSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkEmailNotificationSaveAction.
     */
    @Test
    public void testDoAction() throws Exception {
        
        System.out.println("doAction");
        String componentId = "V009";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_PROCESS_NAME);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_EMAIL_TO);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_SUBJECT);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_EMAIL_CC);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_EMAIL_BODY);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_FAILURE_TO);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_SUBJECT1);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_EMAIL_CC1);
        gtnUIFrameWorkActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADD_EMAIL_BODY1);
        

        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkEmailNotificationSaveAction instance = new GtnFrameworkEmailNotificationSaveAction();
        
        GtnFrameworkEmailNotificationSaveAction in = Mockito.spy(instance);
        
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob={3,"SUPPORT@BPITECHNOLOGIES.COM","SUCCESS - CFF_OUTBOUND_INTERFACE","SUPPORT@BPITECHNOLOGIES.COM","SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID","COUNTS","SUPPORT@BPITECHNOLOGIES.COM","FAILURE - CFF_OUTBOUND_INTERFACE",
                                "SUPPORT@BPITECHNOLOGIES.COM","CFF_OUTBOUND_INTERFACE FAILURE","CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad );
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnWsMailConfigurationResponse.setNotificationSuccess(true);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class),Mockito.any(GtnUIFrameworkWebserviceRequest.class));
      
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of getModifiedRequest method, of class GtnFrameworkEmailNotificationSaveAction.
     */
    @Test
    public void testGetModifiedRequest() {
        System.out.println("doAction");
        String componentId = "V009";

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

        List<Object> resetActionParams = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        resetActionParams.set(1, GtnFrameworkCommonConstants.ADD_PROCESS_NAME);
        resetActionParams.set(2, GtnFrameworkCommonConstants.ADD_EMAIL_TO);
        resetActionParams.set(3, GtnFrameworkCommonConstants.ADD_SUBJECT);
        resetActionParams.set(4, GtnFrameworkCommonConstants.ADD_EMAIL_CC);
        resetActionParams.set(5, GtnFrameworkCommonConstants.ADD_EMAIL_BODY);
        resetActionParams.set(6, GtnFrameworkCommonConstants.ADD_FAILURE_TO);
        resetActionParams.set(7, GtnFrameworkCommonConstants.ADD_SUBJECT1);
        resetActionParams.set(8, GtnFrameworkCommonConstants.ADD_EMAIL_CC1);
        resetActionParams.set(9, GtnFrameworkCommonConstants.ADD_EMAIL_BODY1);
        gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);

        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(object);

        doNothing().when(object).loadFieldValue(Mockito.anyObject());

        GtnFrameworkEmailNotificationSaveAction instance = new GtnFrameworkEmailNotificationSaveAction();

        GtnFrameworkEmailNotificationSaveAction in = Mockito.spy(instance);

        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob = {3, "SUPPORT@BPITECHNOLOGIES.COM", "SUCCESS - CFF_OUTBOUND_INTERFACE", "SUPPORT@BPITECHNOLOGIES.COM", "SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID", "COUNTS", "SUPPORT@BPITECHNOLOGIES.COM", "FAILURE - CFF_OUTBOUND_INTERFACE",
            "SUPPORT@BPITECHNOLOGIES.COM", "CFF_OUTBOUND_INTERFACE FAILURE", "CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad);
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnWsMailConfigurationResponse.setNotificationSuccess(true);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));

        in.getModifiedRequest(eMailConfigurationBean, resetActionParams);
    }

    /**
     * Test of saveMailNotification method, of class GtnFrameworkEmailNotificationSaveAction.
     */
    @Test
    public void testSaveMailNotification() throws Exception {
        System.out.println("saveMailNotification");
        String componentId = "V009";

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

        List<Object> resetActionParams = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        resetActionParams.set(1, GtnFrameworkCommonConstants.ADD_PROCESS_NAME);
        resetActionParams.set(2, GtnFrameworkCommonConstants.ADD_EMAIL_TO);
        resetActionParams.set(3, GtnFrameworkCommonConstants.ADD_SUBJECT);
        resetActionParams.set(4, GtnFrameworkCommonConstants.ADD_EMAIL_CC);
        resetActionParams.set(5, GtnFrameworkCommonConstants.ADD_EMAIL_BODY);
        resetActionParams.set(6, GtnFrameworkCommonConstants.ADD_FAILURE_TO);
        resetActionParams.set(7, GtnFrameworkCommonConstants.ADD_SUBJECT1);
        resetActionParams.set(8, GtnFrameworkCommonConstants.ADD_EMAIL_CC1);
        resetActionParams.set(9, GtnFrameworkCommonConstants.ADD_EMAIL_BODY1);
        gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);

        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn("addProcessName").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(object);

        doNothing().when(object).loadFieldValue(Mockito.anyObject());

        GtnFrameworkEmailNotificationSaveAction instance = new GtnFrameworkEmailNotificationSaveAction();

        GtnFrameworkEmailNotificationSaveAction in = Mockito.spy(instance);

        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob = {3, "SUPPORT@BPITECHNOLOGIES.COM", "SUCCESS - CFF_OUTBOUND_INTERFACE", "SUPPORT@BPITECHNOLOGIES.COM", "SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID", "COUNTS", "SUPPORT@BPITECHNOLOGIES.COM", "FAILURE - CFF_OUTBOUND_INTERFACE",
            "SUPPORT@BPITECHNOLOGIES.COM", "CFF_OUTBOUND_INTERFACE FAILURE", "CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad);
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnWsMailConfigurationResponse.setNotificationSuccess(true);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        doReturn(gtnUIFrameworkWebserviceResponse).when(in).callMailConfigSaveAction(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));

        in.saveMailNotification(resetActionParams, componentId);
    }

    /**
     * Test of emailIdCommaReplacedWithSpace method, of class GtnFrameworkEmailNotificationSaveAction.
     */
    @Test
    public void testEmailIdCommaReplacedWithSpace() {
        System.out.println("emailIdCommaReplacedWithSpace");
        String inputMaildId = "emailconfig@gmail.com,emailconfig1@gmail.com";
        GtnFrameworkEmailNotificationSaveAction instance = new GtnFrameworkEmailNotificationSaveAction();
        String result = instance.emailIdCommaReplacedWithSpace(inputMaildId);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkEmailNotificationSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkEmailNotificationSaveAction instance = new GtnFrameworkEmailNotificationSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
