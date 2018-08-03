/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.vaadin.ui.TextField;
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
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnFrameworkSearchSecurityActionTest {

    public GtnFrameworkSearchSecurityActionTest() {
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
     * GtnFrameworkSearchSecurityAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkSearchSecurityAction instance = new GtnFrameworkSearchSecurityAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkSearchSecurityAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkSearchSecurityAction instance = new GtnFrameworkSearchSecurityAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of doAction method, of class GtnFrameworkSearchSecurityAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

        Constructor cons = (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        GtnUIFrameworkBaseComponent object = (GtnUIFrameworkBaseComponent) cons.newInstance(new TextField(), null);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
        List<Object> parameters = IntStream.rangeClosed(0, 5).boxed().collect(Collectors.toList());
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        GtnFrameworkSearchSecurityAction instance = new GtnFrameworkSearchSecurityAction();
        parameters.set(1, true);
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

}
