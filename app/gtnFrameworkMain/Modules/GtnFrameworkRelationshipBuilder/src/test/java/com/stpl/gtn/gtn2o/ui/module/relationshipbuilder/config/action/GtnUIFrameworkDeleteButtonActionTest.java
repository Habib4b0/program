/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
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
public class GtnUIFrameworkDeleteButtonActionTest {
    
    public GtnUIFrameworkDeleteButtonActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

   

    /**
     * Test of getRbRequestActionConfig method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testGetRbRequestActionConfig() {
        System.out.println("getRbRequestActionConfig");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        GtnUIFrameWorkActionConfig result = instance.getRbRequestActionConfig(rbRequest);
        assertEquals(GtnUIFrameworkRBRequestAction.class.getName(), result.getActionParameterList().get(0));
        assertEquals(rbRequest, result.getActionParameterList().get(1));
    }

    /**
     * Test of getRbDeleteSuccessAlertAction method, of class GtnUIFrameworkDeleteButtonAction.
     */
    @Test
    public void testGetRbDeleteSuccessAlertAction() {
        System.out.println("getRbDeleteSuccessAlertAction");
        GtnWsRelationshipBuilderResponse rbNewResponse = new GtnWsRelationshipBuilderResponse();
        rbNewResponse.setMessageType("type");
        rbNewResponse.setMessage("msg");
        GtnUIFrameworkDeleteButtonAction instance = new GtnUIFrameworkDeleteButtonAction();
        GtnUIFrameWorkActionConfig result = instance.getRbDeleteSuccessAlertAction(rbNewResponse);
         assertEquals(rbNewResponse.getMessageType(), result.getActionParameterList().get(0));
        assertEquals(rbNewResponse.getMessage(), result.getActionParameterList().get(1));
    }
    
}
