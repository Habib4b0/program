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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})

public class GtnFrameworkSaveActionTest {
    
    public GtnFrameworkSaveActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of createRBRequest method, of class GtnFrameworkSaveAction.
     */
    @Test
    public void testCreateRBRequest() {
        System.out.println("createRBRequest");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        int hierarchySid = 1;
        int versionNo = 1;
        String relationshipName = "rpName";
        String relationshipDesc = "rpDes";
        String relationshipType = "rpType";
        String buildType = "buildType";
        Object[] expected = {rbRequest, hierarchySid, versionNo, relationshipName, relationshipDesc, relationshipType, buildType};
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        
        instance.createRBRequest(rbRequest, hierarchySid, versionNo, relationshipName, relationshipDesc, relationshipType, buildType);
        Object[] actuals = {rbRequest, rbRequest.getHierarchyDefSId(), rbRequest.getVersionNo(), rbRequest.getRelationshipName(),
            rbRequest.getRelationshipDescription(), rbRequest.getRelationshipType(), rbRequest.getBuildType()};
        assertArrayEquals(expected, actuals);
    }

    /**
     * Test of getSuccessActionConfigList method, of class
     * GtnFrameworkSaveAction.
     */
    @Test
    public void testGetSuccessActionConfigList() {
        System.out.println("getSuccessActionConfigList");
        List<Object> parameters = IntStream.rangeClosed(0, 11).boxed().collect(Collectors.toList());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        List<GtnUIFrameWorkActionConfig> result = instance.getSuccessActionConfigList(parameters, rbRequest);
        assertEquals(7, result.get(0).getActionParameterList().size());
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkEditButtonAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
        GtnWsRecordBean relationshipBean = getSampleBean();
        
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        List<Object> parameters = IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
        parameters.set(2, 1);
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        when(GtnUIFrameworkGlobalUI.getSessionProperty(gtnUIFrameWorkActionConfig.getActionParameterList().get(11).toString())).thenReturn(relationshipBean);
        when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn(GtnUIFrameworkModeType.COPY.toString());
        when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("122");
        
        GtnUIFrameworkWebserviceResponse newResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsRelationshipBuilderResponse rbNewResponse = new GtnWsRelationshipBuilderResponse();
        rbNewResponse.setMessageType(GtnFrameworkCommonStringConstants.VALIDATION);
        newResponse.setGtnWsRelationshipBuilderResponse(rbNewResponse);
        
        GtnFrameworkSaveAction in = Mockito.spy(instance);
        doReturn(newResponse).when(in)
                .getSavedResponse(Mockito.any(GtnUIFrameworkWebServiceClient.class), Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
        
        when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn(GtnUIFrameworkModeType.EDIT.toString());
        
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
    
    GtnWsRecordBean getSampleBean() {
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());
        
        values.set(5, new Date());
        
        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        return bean;
    }
}
