/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.google.gwt.user.client.Window.Navigator;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkNavigationAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.vaadin.ui.UI;
import java.lang.reflect.Constructor;
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
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value={GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAction.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkNavigationAction.class,UI.class,Navigator.class})
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

//    /**
//     * Test of doAction method, of class GtnUIFrameworkEditButtonAction.
//     */
//    @Test
//    public void testDoAction() throws Exception {
//        System.out.println("doAction");
//        PowerMockito.mock(GtnUIFrameWorkNavigationAction.class);
//        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
//        String componentId = "";
//        
//        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
//        Constructor cons= (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
//        cons.setAccessible(true);
//        GtnUIFrameworkBaseComponent object= (GtnUIFrameworkBaseComponent) cons.newInstance(null,null);
//        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
//        GtnUIFrameworkRelationshipCopyAction instance = new GtnUIFrameworkRelationshipCopyAction();
//        gtnUIFrameWorkActionConfig.setActionParameterList(IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList()));
//        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
//    }

}
