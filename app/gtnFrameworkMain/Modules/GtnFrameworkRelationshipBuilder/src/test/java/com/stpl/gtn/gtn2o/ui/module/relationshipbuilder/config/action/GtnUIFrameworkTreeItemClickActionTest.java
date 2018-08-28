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
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.vaadin.v7.ui.Tree;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
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
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value={GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAction.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkActionExecutor.class})
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
        GtnUIFrameworkBaseComponent object= (GtnUIFrameworkBaseComponent) cons.newInstance(null,null);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        gtnUIFrameWorkActionConfig.setActionParameterList(IntStream.rangeClosed(0, 24).boxed().collect(Collectors.toList()));
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
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
    /**
     * Test of getCurrentChildList method, of class GtnUIFrameworkTreeItemClickAction.
     */
    @Test
    public void testGetCurrentChildList() {
        System.out.println("getCurrentChildList");
        List<GtnWsRecordBean> childList = new ArrayList<>();
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values=IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());
        
        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        values.set(GtnWsRelationshipBuilderKeyConstant.IS_NODE_VISITED.ordinal(), false);
        bean.setProperties(values);
        
        childList.add(bean);
        
        bean.setChildList(new ArrayList<>(childList));
      
        String currentLevelNo = "1";
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        List<GtnWsRecordBean> expResult = null;
        List<GtnWsRecordBean> result = instance.getCurrentChildList(childList, bean, currentLevelNo);
        assertEquals(1, result.size());
     }

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
     @Test
    public void testGetParentIDInfo() {
        System.out.println("getParentIDInfo");
        GtnWsRecordBean selectedLevelBean = getSampleBean();
        GtnUIFrameworkBaseComponent rbTreeBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        GtnUIFrameworkTreeItemClickAction instance = new GtnUIFrameworkTreeItemClickAction();
        when(rbTreeBaseComponent.getParent(selectedLevelBean)).thenReturn(selectedLevelBean);
        Tree tr=Mockito.mock(Tree.class);
        doReturn(selectedLevelBean).when(tr).getParent(Mockito.any());
        GtnUIFrameworkComponentData data=  Mockito.mock(GtnUIFrameworkComponentData.class);
        data.setCustomData(tr);
        doReturn(data).when(rbTreeBaseComponent).getComponentData();
        
         instance.getParentIDInfo(selectedLevelBean, rbTreeBaseComponent,0);
    }
    GtnWsRecordBean getSampleBean() {
        GtnWsRecordBean bean = new GtnWsRecordBean();
        List<Object> values = IntStream.rangeClosed(0, GtnWsRelationshipBuilderKeyConstant.values().length).boxed().collect(Collectors.toList());

        bean.setRecordHeader(Arrays.stream(GtnWsRelationshipBuilderKeyConstant.values()).collect(Collectors.toList()));
        bean.setProperties(values);
        return bean;
    }
}
