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
import java.lang.reflect.Constructor;
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
@PrepareForTest(value={GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAction.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkEditButtonActionTest {
    
    public GtnUIFrameworkEditButtonActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkEditButtonAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkEditButtonAction instance = new GtnUIFrameworkEditButtonAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

  
    /**
     * Test of createInstance method, of class GtnUIFrameworkEditButtonAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkEditButtonAction instance = new GtnUIFrameworkEditButtonAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
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
        Constructor cons= (GtnUIFrameworkBaseComponent.class.getDeclaredConstructors()[0]);
        cons.setAccessible(true);
        GtnUIFrameworkBaseComponent object= Mockito.spy((GtnUIFrameworkBaseComponent) cons.newInstance(null,null));
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
         GtnUIFrameworkEditButtonAction instance = Mockito.spy(new GtnUIFrameworkEditButtonAction());
        gtnUIFrameWorkActionConfig.setActionParameterList(IntStream.rangeClosed(0, 20).boxed().collect(Collectors.toList()));
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        

        
        List<Object> parameters=IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList());
        gtnUIFrameWorkActionConfig.setActionParameterList(parameters);
        doReturn(getSampleBean()).when(object).getValueFromComponent();
        doNothing().when(instance).executeNavigationAction(parameters, componentId);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
      GtnWsRecordBean getSampleBean() {
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        return bean;
      }
}
