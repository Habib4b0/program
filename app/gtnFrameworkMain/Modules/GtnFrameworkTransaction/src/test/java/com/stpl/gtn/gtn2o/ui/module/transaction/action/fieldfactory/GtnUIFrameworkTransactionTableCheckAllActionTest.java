/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameWorkTransactioneRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import com.vaadin.ui.AbstractComponent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkActionParameter.class})
public class GtnUIFrameworkTransactionTableCheckAllActionTest {
    
    public GtnUIFrameworkTransactionTableCheckAllActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkTransactionTableCheckAllAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkTransactionTableCheckAllAction instance = new GtnUIFrameworkTransactionTableCheckAllAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkTransactionTableCheckAllAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class,GtnUIFrameworkActionParameter.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean=new GtnUIFrameworkTransactionComponentTypeListBean();
        GtnWSTransactionTableCheckAllBean checkallBean=new GtnWSTransactionTableCheckAllBean();
        checkallBean.setCheckAll(true);
        Set<String> checkedIdSet=new HashSet<>();
        checkedIdSet.add("ds");
        checkedIdSet.add("d2s");
        checkedIdSet.add("ds4");
        checkedIdSet.add("d7s");
        checkedIdSet.add("8ds");        
        checkallBean.setCheckedIdSet(checkedIdSet);
        List<Object> obj=Arrays.asList("0","searchResultTable","resultPanelLayout",componentTypeBean,"4","5","6","7");
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        List<GtnWsRecordBean> dataTableRecordList=new ArrayList<>();
        GtnWsRecordBean bean=new GtnWsRecordBean();
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        dataTableRecordList.add(bean);
        GtnUIFrameworkActionParameter actionParameter=new GtnUIFrameworkActionParameter();
        actionParameter.setCurrentValue(bean);
        
        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
        
               
        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
        
        doReturn(actionParameter).when(tableComponent).getActionParameter();
        
        doReturn(checkallBean).when(tableComponent).getSharedPopupData();
        
        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameworkTransactionTableCheckAllAction instance = new GtnUIFrameworkTransactionTableCheckAllAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of refreshTable method, of class GtnUIFrameworkTransactionTableCheckAllAction.
     */
    @Test
    public void testRefreshTable() throws Exception {
        System.out.println("refreshTable");
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        GtnUIFrameworkComponentData tableComponent = Mockito.mock(GtnUIFrameworkComponentData.class);

        doReturn(tableComponent).when(tableBaseComponent).getComponentData();

        PowerMockito.when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        String componentId = "fsfs";
        GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
        String propertyId = "gsssg";
        Object value = 64964;
        GtnUIFrameworkTransactionTableCheckAllAction instance = new GtnUIFrameworkTransactionTableCheckAllAction();
        Method method = instance.getClass().getDeclaredMethod("refreshTable",GtnUIFrameworkBaseComponent.class,String.class,Object.class);
        method.setAccessible(true);
        method.invoke(instance,baseComponent, propertyId, value);
        assertFalse(propertyId.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkTransactionTableCheckAllAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkTransactionTableCheckAllAction instance = new GtnUIFrameworkTransactionTableCheckAllAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
