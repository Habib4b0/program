/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.vaadin.v7.ui.Tree;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkTreeItemClickActionTest {
    
    public GtnUIFrameworkTreeItemClickActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkTreeItemClickAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of createInstance method, of class GtnUIFrameworkTreeItemClickAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

//    /**
//     * Test of doAction method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testDoAction() throws Exception {
//        System.out.println("doAction");
//        String componentId = "";
//        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of loadFilteredResultLayout method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testLoadFilteredResultLayout() throws Exception {
//        System.out.println("loadFilteredResultLayout");
//        GtnWsRecordBean levelBean = null;
//        List<Object> parameters = null;
//        String componentId = "";
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        instance.loadFilteredResultLayout(levelBean, parameters, componentId);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getParentIDInfo method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testGetParentIDInfo() {
//        System.out.println("getParentIDInfo");
//        GtnWsRecordBean levelBean = null;
//        GtnUIFrameworkBaseComponent rbTreeBaseComponent = null;
//        int levelNo = 0;
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        List<List<String>> expResult = null;
//        List<List<String>> result = instance.getParentIDInfo(levelBean, rbTreeBaseComponent, levelNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLinkedLevelNotInList method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testGetLinkedLevelNotInList() {
//        System.out.println("getLinkedLevelNotInList");
//        GtnWsRecordBean currentSelectedBean = null;
//        GtnUIFrameworkBaseComponent rbTreeBaseComponent = null;
//        List<Object> parameters = null;
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        Set<String> expResult = null;
//        Set<String> result = instance.getLinkedLevelNotInList(currentSelectedBean, rbTreeBaseComponent, parameters);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRecentLinkedLevel method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testGetRecentLinkedLevel() {
//        System.out.println("getRecentLinkedLevel");
//        GtnWsRecordBean newLevelBean = null;
//        Tree relationBuilderTree = null;
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        GtnWsRecordBean expResult = null;
//        GtnWsRecordBean result = instance.getRecentLinkedLevel(newLevelBean, relationBuilderTree);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addAllNotInConditionValues method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testAddAllNotInConditionValues() {
//        System.out.println("addAllNotInConditionValues");
//        List<GtnWsRecordBean> childList = null;
//        Set<String> notInList = null;
//        String currentLevelNo = "";
//        List<GtnWsRecordBean> entireTreeNodeList = null;
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        instance.addAllNotInConditionValues(childList, notInList, currentLevelNo, entireTreeNodeList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNotInValues method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testSetNotInValues() {
//        System.out.println("setNotInValues");
//        GtnWsRecordBean childListBean = null;
//        List<GtnWsRecordBean> entireTreeNodeList = null;
//        Set<String> notInList = null;
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        instance.setNotInValues(childListBean, entireTreeNodeList, notInList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCurrentChildList method, of class GtnUIFrameworkTreeItemClickAction.
//     */
//    @Test
//    public void testGetCurrentChildList() {
//        System.out.println("getCurrentChildList");
//        List<GtnWsRecordBean> childList = null;
//        GtnWsRecordBean bean = null;
//        String currentLevelNo = "";
//        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
//        List<GtnWsRecordBean> expResult = null;
//        List<GtnWsRecordBean> result = instance.getCurrentChildList(childList, bean, currentLevelNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of isUserDefinedLevel method, of class GtnUIFrameworkTreeItemClickAction.
     */
    @Test
    public void testIsUserDefinedLevel() {
        System.out.println("isUserDefinedLevel");
        GtnWsRecordBean levelBean = new GtnWsRecordBean();
        List<Object>  rec=IntStream.rangeClosed(0, 10).boxed().collect(Collectors.toList());
        List<Object>  prop=IntStream.rangeClosed(0, 10).boxed().collect(Collectors.toList());
        levelBean.setRecordHeader(rec);
        rec.set(10, GtnWsRelationshipBuilderKeyConstant.LEVEL_VALUES_REFERENCE.ordinal());
        prop.set(10, GtnFrameworkWebserviceConstant.USER_DEFINED);
        levelBean.setProperties(prop);
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        boolean expResult = false;
        boolean result = instance.isUserDefinedLevel(levelBean);
        assertEquals(expResult, result);
    }

    /**
     * Test of isFirstLevel method, of class GtnUIFrameworkTreeItemClickAction.
     */
    @Test
    public void testIsFirstLevel() {
        System.out.println("isFirstLevel");
        GtnWsRecordBean levelBean = new GtnWsRecordBean();
        levelBean.setRecordHeader(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderConstants.NUMERIC_CONSTANT_ONE));
        levelBean.setProperties(Arrays.asList(GtnWsRelationshipBuilderKeyConstant.VALUE.ordinal(), GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        boolean expResult = true;
        boolean result = instance.isFirstLevel(levelBean);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEligibleForAdding method, of class GtnUIFrameworkTreeItemClickAction.
     */
    @Test
    public void testIsEligibleForAdding() {
        System.out.println("isEligibleForAdding");
        int currentLevelNo = 0;
        List<Object> parameters = null;
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        boolean expResult = false;
        boolean result = instance.isEligibleForAdding(currentLevelNo, parameters);
        assertEquals(expResult, result);
    }
    
}
