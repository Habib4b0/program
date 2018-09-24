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
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})

public class GtnFrameworkAvaliableNameUpdateActionTest {

    public GtnFrameworkAvaliableNameUpdateActionTest() {
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
     * GtnFrameworkAvaliableNameUpdateAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkAvaliableNameUpdateAction instance = new GtnFrameworkAvaliableNameUpdateAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class
     * GtnFrameworkAvaliableNameUpdateAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkAvaliableNameUpdateAction instance = new GtnFrameworkAvaliableNameUpdateAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
     *
     * @throws Exception
     *
     */
    @Test
    public void testDoAction_1()
            throws Exception {
        GtnFrameworkAvaliableNameUpdateAction fixture = new GtnFrameworkAvaliableNameUpdateAction();
        String componentId = "";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        List<GtnWsRecordBean> list = new ArrayList<>();
        list.add(getSampleBean());
        GtnUIFrameworkComponentData data = new GtnUIFrameworkComponentData();
        data.setDataTableRecordList(list);
        GtnUIFrameworkBaseComponent table = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        Mockito.doNothing().when(table).setTableColumnHeader(Mockito.anyString(), Mockito.anyString());
        Mockito.doNothing().when(table).setTableValue(null);
        Mockito.doNothing().when(base).setCaption(Mockito.anyString());
     
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(base);

        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        gtnUIFrameWorkActionConfig.addActionParameter(componentId);
        gtnUIFrameWorkActionConfig.addActionParameter(componentId);

           when(table.getComponentData()).thenReturn(data);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(table);
        fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
        gtnUIFrameWorkActionConfig.setEventParameter(getSampleBean());
        
        //case 2
         data.setDataTableRecordList(null);
         when(table.getComponentData()).thenReturn(data);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(table);
          fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    GtnWsRecordBean getSampleBean() {
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        return bean;
    }
}
