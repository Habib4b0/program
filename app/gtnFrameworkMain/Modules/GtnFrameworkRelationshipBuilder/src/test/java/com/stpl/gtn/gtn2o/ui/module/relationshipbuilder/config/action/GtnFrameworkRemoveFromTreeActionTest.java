/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
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

public class GtnFrameworkRemoveFromTreeActionTest {

    public GtnFrameworkRemoveFromTreeActionTest() {
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
     * GtnFrameworkRemoveFromTreeAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkRemoveFromTreeAction instance = new GtnFrameworkRemoveFromTreeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkRemoveFromTreeAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkRemoveFromTreeAction instance = new GtnFrameworkRemoveFromTreeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of doAction method, of class GtnFrameworkRemoveFromTreeAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        String treeId="1";
        String initialTableId="1";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        gtnUIFrameWorkActionConfig.addActionParameter(0);
        gtnUIFrameWorkActionConfig.addActionParameter(treeId);
        gtnUIFrameWorkActionConfig.addActionParameter(initialTableId);
        gtnUIFrameWorkActionConfig.addActionParameter("3");
        gtnUIFrameWorkActionConfig.addActionParameter("4");
        gtnUIFrameWorkActionConfig.addActionParameter("5");
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
         GtnFrameworkRemoveFromTreeAction instance = new GtnFrameworkRemoveFromTreeAction();
         
         
        GtnUIFrameworkBaseComponent rbTreeBaseComponent =  Mockito.mock( GtnUIFrameworkBaseComponent.class);
        doReturn(null).when(rbTreeBaseComponent).removeParentAndChildTreeItems(initialTableId, false);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(treeId)).thenReturn(rbTreeBaseComponent);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        
        GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
        doNothing().when(logic).addCurrentSearchCriteria(Mockito.any(GtnWebServiceSearchCriteria.class));
        doReturn(logic).when(rbTreeBaseComponent).getLogicFromPagedDataTable();
        doReturn(null).when(rbTreeBaseComponent).getAllTreeNodes();
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("5")).thenReturn(rbTreeBaseComponent);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(treeId)).thenReturn(rbTreeBaseComponent);
      
        //case 2
        List<GtnWsRecordBean> returnList=new ArrayList<>();
        doNothing().when(rbTreeBaseComponent).setComponentVisible(Mockito.anyBoolean());
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)).thenReturn(rbTreeBaseComponent);
        doReturn(returnList).when(rbTreeBaseComponent).removeParentAndChildTreeItems(initialTableId, false);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(treeId)).thenReturn(rbTreeBaseComponent);
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        
        //case 3
        returnList.add(getSampleBean());
        doReturn(returnList).when(rbTreeBaseComponent).removeParentAndChildTreeItems(initialTableId, false);
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(treeId)).thenReturn(rbTreeBaseComponent);
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
