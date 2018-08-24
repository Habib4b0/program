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
import java.util.ArrayList;
import java.util.List;
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

public class GtnFrameworkHierarchyChangeActionTest {

    public GtnFrameworkHierarchyChangeActionTest() {
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
     * GtnFrameworkHierarchyChangeAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkHierarchyChangeAction instance = new GtnFrameworkHierarchyChangeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of getVersion method, of class GtnFrameworkHierarchyChangeAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("testCreateInstance");
        GtnFrameworkHierarchyChangeAction instance = new GtnFrameworkHierarchyChangeAction();
        GtnFrameworkHierarchyChangeAction result = (GtnFrameworkHierarchyChangeAction) instance.createInstance();
        assertEquals(result, instance);
    }

    /**
     * Test of doAction method, of class GtnFrameworkHierarchyChangeAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        String componentId = "";

        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        gtnUIFrameWorkActionConfig.addActionParameter(0);  gtnUIFrameWorkActionConfig.addActionParameter(1);
        gtnUIFrameWorkActionConfig.addActionParameter("2");
        when(GtnUIFrameworkGlobalUI.getSessionProperty("hierarchyNameValueChange")).thenReturn(false);
        GtnFrameworkHierarchyChangeAction instance = Mockito.spy(GtnFrameworkHierarchyChangeAction.class);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        //  case 2
        when(GtnUIFrameworkGlobalUI.getSessionProperty("hierarchyNameValueChange")).thenReturn(true);
        GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        when(base.getIntegerFromField()).thenReturn(1);
        List<Integer> versionNoList = new ArrayList<>();
        versionNoList.add(1);

        doNothing().when(base).loadDefaultComboboxAll(versionNoList,0);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
        doReturn(versionNoList).when(instance).getVersion(1);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);

    }

//    /**
//     * Test of getVersion method, of class GtnFrameworkHierarchyChangeAction.
//     */
//    @Test
//    public void testGetVersion() {
//        System.out.println("getVersion");
//        int hierarchySid = 0;
//        GtnFrameworkHierarchyChangeAction instance = new GtnFrameworkHierarchyChangeAction();
//        List<Integer> expResult = null;
//        List<Integer> result = instance.getVersion(hierarchySid);
//        assertEquals(expResult, result);
//    }

}
