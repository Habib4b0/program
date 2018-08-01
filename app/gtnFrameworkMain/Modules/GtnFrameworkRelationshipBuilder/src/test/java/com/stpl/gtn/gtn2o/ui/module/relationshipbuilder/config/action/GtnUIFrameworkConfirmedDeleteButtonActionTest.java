/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkConfirmedDeleteButtonActionTest {
    
    public GtnUIFrameworkConfirmedDeleteButtonActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkConfirmedDeleteButtonAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkConfirmedDeleteButtonAction instance = new GtnUIFrameworkConfirmedDeleteButtonAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of createInstance method, of class GtnUIFrameworkConfirmedDeleteButtonAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkConfirmedDeleteButtonAction instance = new GtnUIFrameworkConfirmedDeleteButtonAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
        
    }


    /**
     * Test of getRbDeleteActionConfig method, of class GtnUIFrameworkConfirmedDeleteButtonAction.
     */
    @Test
    public void testGetRbDeleteActionConfig() {
        System.out.println("getRbDeleteActionConfig");
        GtnWsRecordBean relationBean = null;
       List<Object> parameters = IntStream.rangeClosed(0, 1).boxed().collect(Collectors.toList());
        GtnUIFrameworkConfirmedDeleteButtonAction instance = new GtnUIFrameworkConfirmedDeleteButtonAction();
        GtnUIFrameWorkActionConfig result = instance.getRbDeleteActionConfig(relationBean, parameters);
        assertEquals(3, result.getActionParameterList().size());
    }

    /**
     * Test of getRbDeleteInfoAction method, of class GtnUIFrameworkConfirmedDeleteButtonAction.
     */
    @Test
    public void testGetRbDeleteInfoAction() {
        System.out.println("getRbDeleteInfoAction");
        List<Object> parameters = IntStream.rangeClosed(0, 3).boxed().collect(Collectors.toList());
        GtnUIFrameworkConfirmedDeleteButtonAction instance = new GtnUIFrameworkConfirmedDeleteButtonAction();
        GtnUIFrameWorkActionConfig result = instance.getRbDeleteInfoAction(parameters);
        assertEquals(2, result.getActionParameterList().size());
    }
    
}
