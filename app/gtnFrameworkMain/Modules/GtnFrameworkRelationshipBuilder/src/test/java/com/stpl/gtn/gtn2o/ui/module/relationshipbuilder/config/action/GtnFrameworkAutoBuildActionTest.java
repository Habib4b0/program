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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import java.util.Arrays;
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

public class GtnFrameworkAutoBuildActionTest {

    public GtnFrameworkAutoBuildActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkAutoBuildAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkAutoBuildAction instance = new GtnFrameworkAutoBuildAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkAutoBuildAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkAutoBuildAction instance = new GtnFrameworkAutoBuildAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Run the GtnFrameworkAutoBuildAction() constructor test.
     *
     *
     */
    @Test
    public void testGtnFrameworkAutoBuildAction_1()
            throws Exception {
        GtnFrameworkAutoBuildAction result = new GtnFrameworkAutoBuildAction();
        assertNotNull(result);

    }

    /**
     * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testConfigureParams_1()
            throws Exception {
        GtnFrameworkAutoBuildAction fixture = new GtnFrameworkAutoBuildAction();
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

        fixture.configureParams(gtnUIFrameWorkActionConfig);

    }

    /**
     * Run the GtnUIFrameWorkAction createInstance() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCreateInstance_1()
            throws Exception {
        GtnFrameworkAutoBuildAction fixture = new GtnFrameworkAutoBuildAction();

        GtnUIFrameWorkAction result = fixture.createInstance();

        assertNotNull(result);
    }

    /**
     * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDoAction_1()
            throws Exception {
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);

        GtnFrameworkAutoBuildAction fixture = new GtnFrameworkAutoBuildAction();
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        List<Object> parameters = IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
//        parameters.set(2, 1);
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

    }

    /**
     * Test of getParentIDInfo method, of class GtnFrameworkAutoBuildAction.
     */
    @Test
    public void testGetParentIDInfo() {
        System.out.println("getParentIDInfo");
        GtnWsRecordBean selectedLevelBean = getSampleBean();
        GtnUIFrameworkBaseComponent rbTreeBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        GtnFrameworkAutoBuildAction instance = new GtnFrameworkAutoBuildAction();
        when(rbTreeBaseComponent.getParent(selectedLevelBean)).thenReturn(selectedLevelBean);
         instance.getParentIDInfo(selectedLevelBean, rbTreeBaseComponent);
    }
    GtnWsRecordBean getSampleBean() {
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        return bean;
    }
}
