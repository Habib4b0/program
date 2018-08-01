/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import java.util.Date;
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
public class GtnFrameworkSaveActionTest {

    public GtnFrameworkSaveActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of createRBRequest method, of class GtnFrameworkSaveAction.
     */
    @Test
    public void testCreateRBRequest() {
        System.out.println("createRBRequest");
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        int hierarchySid = 1;
        int versionNo = 1;
        String relationshipName = "rpName";
        String relationshipDesc = "rpDes";
        String relationshipType = "rpType";
        String buildType = "buildType";
        Object[] expected = {rbRequest, hierarchySid, versionNo, relationshipName, relationshipDesc, relationshipType, buildType};
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();

        instance.createRBRequest(rbRequest, hierarchySid, versionNo, relationshipName, relationshipDesc, relationshipType, buildType);
        Object[] actuals = {rbRequest, rbRequest.getHierarchyDefSId(), rbRequest.getVersionNo(), rbRequest.getRelationshipName(),
            rbRequest.getRelationshipDescription(), rbRequest.getRelationshipType(), rbRequest.getBuildType()};
        assertArrayEquals(expected, actuals);
    }

    /**
     * Test of getSuccessActionConfigList method, of class
     * GtnFrameworkSaveAction.
     */
    @Test
    public void testGetSuccessActionConfigList() {
        System.out.println("getSuccessActionConfigList");
        List<Object> parameters = IntStream.rangeClosed(0, 11).boxed().collect(Collectors.toList());
        GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
        GtnFrameworkSaveAction instance = new GtnFrameworkSaveAction();
        List<GtnUIFrameWorkActionConfig> result = instance.getSuccessActionConfigList(parameters, rbRequest);
        assertEquals(7, result.get(0).getActionParameterList().size());
    }

}
