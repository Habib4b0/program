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
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
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
import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 *
 * @author Karthik.Raja
 */
@PrepareForTest(value={GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAction.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameworkRBRequestActionTest {
    
    public GtnUIFrameworkRBRequestActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkRBRequestAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkRBRequestAction instance = new GtnUIFrameworkRBRequestAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkRBRequestAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkRBRequestAction instance = new GtnUIFrameworkRBRequestAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkRBRequestAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        gtnUIFrameWorkActionConfig.addActionParameter(componentId);
        GtnWsRelationshipBuilderRequest rbRequest=new GtnWsRelationshipBuilderRequest();
        GtnWsRecordBean bean=new GtnWsRecordBean();
        List<Object> parameters = IntStream.rangeClosed(0, 15).boxed().collect(Collectors.toList());
        
        bean.setRecordHeader(parameters);
        parameters.set(5, new Date());
        parameters.set(6, new Date());
        parameters.set(7,  new Date());
        bean.setProperties(parameters);
        rbRequest.setMainNode(bean);
        gtnUIFrameWorkActionConfig.addActionParameter(rbRequest);
        
        GtnUIFrameworkRBRequestAction instance = new GtnUIFrameworkRBRequestAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        
        
        rbRequest.setMainNode(null);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }
}
