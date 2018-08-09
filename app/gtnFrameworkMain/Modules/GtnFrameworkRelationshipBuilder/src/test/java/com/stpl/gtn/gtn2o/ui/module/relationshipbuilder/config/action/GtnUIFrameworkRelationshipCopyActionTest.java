/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkNavigationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.vaadin.ui.TextField;
import java.lang.reflect.Constructor;
import java.util.Arrays;
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
import static org.powermock.api.mockito.PowerMockito.*;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkRelationshipCopyActionTest {

    public GtnUIFrameworkRelationshipCopyActionTest() {
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
     * Test of configureParams method, of class
     * GtnUIFrameworkRelationshipCopyAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkRelationshipCopyAction instance = new GtnUIFrameworkRelationshipCopyAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class
     * GtnUIFrameworkRelationshipCopyAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkRelationshipCopyAction instance = new GtnUIFrameworkRelationshipCopyAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkEditButtonAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        GtnUIFrameWorkNavigationAction action = Mockito.mock(GtnUIFrameWorkNavigationAction.class);
        Mockito.doNothing().when(action).doAction(Mockito.anyObject(), Mockito.anyObject());

        whenNew(GtnUIFrameWorkNavigationAction.class).withAnyArguments().thenReturn(action);

        mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        GtnUIFrameworkBaseComponent object = mock(GtnUIFrameworkBaseComponent.class);

        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        values.set(GtnWsRelationshipBuilderKeyConstant.IS_NODE_VISITED.ordinal(), false);
        bean.setProperties(values);
        when(object.getValueFromComponent()).thenReturn(bean);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

        GtnUIFrameworkRelationshipCopyAction instance = new GtnUIFrameworkRelationshipCopyAction();
        gtnUIFrameWorkActionConfig.setActionParameterList(IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList()));
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

}
