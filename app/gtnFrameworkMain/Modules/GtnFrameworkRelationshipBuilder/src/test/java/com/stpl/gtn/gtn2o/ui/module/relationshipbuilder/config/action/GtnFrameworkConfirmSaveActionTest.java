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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import java.util.Arrays;
import java.util.Date;
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
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})

public class GtnFrameworkConfirmSaveActionTest {

    public GtnFrameworkConfirmSaveActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkConfirmSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkConfirmSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    @Test
    public void doAction() throws GtnFrameworkGeneralException {
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        List<Object> parameters = IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
        parameters.set(6, new GtnWsRelationshipBuilderRequest());
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        GtnFrameworkConfirmSaveAction instance = new GtnFrameworkConfirmSaveAction();

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsRelationshipBuilderResponse rbNewResponse = new GtnWsRelationshipBuilderResponse();
        newResponse.setGtnWsRelationshipBuilderResponse(rbNewResponse);

        GtnFrameworkConfirmSaveAction in = Mockito.spy(instance);
        doReturn(newResponse).when(in)
                .getRbResponse(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        in.doAction(componentId, gtnUIFrameWorkActionConfig);

        //case 2
        rbNewResponse.setSuccess(true);

        rbNewResponse.setMainNode(getSampleBean());
        doReturn(newResponse).when(in)
                .getRbResponse(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(base).setComponentEnable(Mockito.anyBoolean());
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    GtnWsRecordBean getSampleBean() {
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());
        values.set(5, new Date().getTime());
        values.set(6, new Date().getTime());
        values.set(7, new Date().getTime());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        return bean;
    }
}
