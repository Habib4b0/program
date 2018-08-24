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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.vaadin.ui.TextField;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkAddToTreeActionTest {

    public GtnFrameworkAddToTreeActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkAddToTreeAction.
     */
    @org.junit.Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkAddToTreeAction.
     */
    @org.junit.Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDoAction_2()
            throws Exception {
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        GtnFrameworkAddToTreeAction fixture = new GtnFrameworkAddToTreeAction();
        String componentId = "componentId";
        String remove="removeBtnId";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        gtnUIFrameWorkActionConfig.addActionParameter(componentId);
        gtnUIFrameWorkActionConfig.addActionParameter(componentId);
        gtnUIFrameWorkActionConfig.addActionParameter("tableId");
        gtnUIFrameWorkActionConfig.addActionParameter("treeId");
        gtnUIFrameWorkActionConfig.addActionParameter(remove);
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        GtnUIFrameworkBaseComponent rbTreeBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        when(rbTreeBaseComponent.getItemsFromTable()).thenReturn(Collections.emptyList());
        when(GtnUIFrameworkGlobalUI.getSessionProperty("selectedId")).thenReturn(bean);
        when(rbTreeBaseComponent.getItemsFromDataTable()).thenReturn(Collections.emptyList());
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("treeId")).thenReturn(rbTreeBaseComponent);
        
         TextField rbTreeBaseComponent2 = new TextField();
        
        when(GtnUIFrameworkGlobalUI.getVaadinComponent(remove)).thenReturn(rbTreeBaseComponent2);
        Set<GtnWsRecordBean> selectedValue = new HashSet<>();
        GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
        gtnWsRecordBean.setRecordHeader(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
        gtnWsRecordBean.setProperties(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
        selectedValue.add(gtnWsRecordBean);
        
        GtnUIFrameworkBaseComponent table = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        Mockito.doNothing().when(table).removeItemsFromMultiSelectDataTable();
        Mockito.doNothing().when(table).setTableValue(null);
        when(table.getValueFromComponent()).thenReturn(selectedValue);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tableId")).thenReturn(table);
        fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
        
        //case 2
        rbTreeBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        List<GtnWsRecordBean> list = new ArrayList<>();
        when(rbTreeBaseComponent.getItemsFromTable()).thenReturn(list);
        when(GtnUIFrameworkGlobalUI.getSessionProperty("selectedId")).thenReturn(bean);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("treeId")).thenReturn(rbTreeBaseComponent);
        PowerMockito.doNothing().when(GtnUIFrameworkGlobalUI.class, "showMessageBox", componentId, GtnUIFrameworkActionType.INFO_ACTION,
                GtnFrameworkCommonStringConstants.ERROR, "Please select the parent node ");

        fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCheckToProceed() {
        try {
            System.out.println("checkToProceed");
            Set<Integer> selectedValue = new HashSet<>();
            selectedValue.add(0);
            selectedValue.add(1);
            int levelNo = 1;
            GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
            boolean expResult = false;
            Method method = instance.getClass().getDeclaredMethod("checkToProceed", Object.class, Integer.TYPE);
            method.setAccessible(true);
            Object result = method.invoke(instance, selectedValue, levelNo);
            assertEquals(expResult, (Boolean) result);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkAddToTreeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//
    /**
     * Test of loadAdditionProperty method, of class
     * GtnFrameworkAddToTreeAction.
     */
    @Test
    public void testLoadAdditionProperty() {
        try {
            System.out.println("loadAdditionProperty");

            Set<GtnWsRecordBean> selectedValue = new HashSet<>();
            GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
            gtnWsRecordBean.setRecordHeader(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
            gtnWsRecordBean.setProperties(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
            selectedValue.add(gtnWsRecordBean);
            GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
            boolean expResult = false;
            Method method = instance.getClass().getDeclaredMethod("loadAdditionProperty", Set.class);
            method.setAccessible(true);
            method.invoke(instance, selectedValue);
            assertEquals(expResult, (Boolean) gtnWsRecordBean.getProperties().get(gtnWsRecordBean.getProperties().size() - 1));
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkAddToTreeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
