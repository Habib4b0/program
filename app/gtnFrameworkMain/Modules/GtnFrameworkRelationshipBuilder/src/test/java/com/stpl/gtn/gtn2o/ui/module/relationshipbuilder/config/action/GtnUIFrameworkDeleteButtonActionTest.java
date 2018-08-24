/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value={GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameworkWebServiceClient.class})
public class GtnUIFrameworkDeleteButtonActionTest {
    
    
    
    public GtnUIFrameworkDeleteButtonActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

   

    /**
     * Test of getRbRequestActionConfig method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testGetRbRequestActionConfig() {
        System.out.println("getRbRequestActionConfig");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        GtnUIFrameWorkActionConfig result = instance.getRbRequestActionConfig(rbRequest);
        assertEquals(GtnUIFrameworkRBRequestAction.class.getName(), result.getActionParameterList().get(0));
        assertEquals(rbRequest, result.getActionParameterList().get(1));
    }

    /**
     * Test of getRbDeleteSuccessAlertAction method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testGetRbDeleteSuccessAlertAction() {
        System.out.println("getRbDeleteSuccessAlertAction");
        GtnWsRelationshipBuilderResponse rbNewResponse = new GtnWsRelationshipBuilderResponse();
        rbNewResponse.setMessageType("type");
        rbNewResponse.setMessage("msg");
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        GtnUIFrameWorkActionConfig result = instance.getRbDeleteSuccessAlertAction(rbNewResponse);
         assertEquals(rbNewResponse.getMessageType(), result.getActionParameterList().get(0));
        assertEquals(rbNewResponse.getMessage(), result.getActionParameterList().get(1));
    }
    /**
     * Test of doAction method, of class GtnUIFrameworkEditButtonAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
 GtnWsRelationshipBuilderResponse rbNewResponse = new GtnWsRelationshipBuilderResponse();
        rbNewResponse.setMessageType("type");
        rbNewResponse.setMessage("msg");
        
        GtnUIFrameworkWebserviceResponse response=PowerMockito.mock(GtnUIFrameworkWebserviceResponse.class);
        String componentId = "";
        when(response.getGtnWsRelationshipBuilderResponse()).thenReturn(new GtnWsRelationshipBuilderResponse());
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,  GtnUIFrameworkActionExecutor.class,GtnUIFrameworkWebServiceClient.class);
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        Constructor cons= (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        GtnUIFrameworkBaseComponent object= Mockito.spy((GtnUIFrameworkBaseComponent) cons.newInstance(null,null));
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
        GtnUIFrameworkDeleteButtonAction instance = Mockito.spy(new GtnUIFrameworkDeleteButtonAction());
        doReturn(response).when(instance).getResponse(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        
        List<Object> parameters=IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
        parameters.set(1, new GtnWsRecordBean());
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        //case 2
           rbNewResponse.setSuccess(true);
        when(response.getGtnWsRelationshipBuilderResponse()).thenReturn(rbNewResponse);
        doReturn(response).when(instance).getResponse(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        
        doNothing().when(object).removeItemFromDataTable(Mockito.anyString());
        doNothing().when(object).setTableValue(null);
         instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
}
