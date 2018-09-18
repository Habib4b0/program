/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentListForInvalidBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class})
public class GtnUIFrameworkTransactionInvalidViewLoadActionTest {
    
    public GtnUIFrameworkTransactionInvalidViewLoadActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkTransactionInvalidViewLoadAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkTransactionInvalidViewLoadAction instance = new GtnUIFrameworkTransactionInvalidViewLoadAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkTransactionInvalidViewLoadAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean=new GtnUIFrameworkTransactionComponentTypeListBean();
        List<Object> obj=Arrays.asList("0","1","2",componentTypeBean,"4","5","6","7");
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        List<GtnWsRecordBean> dataTableRecordList=new ArrayList<>();
        GtnWsRecordBean bean=new GtnWsRecordBean();
        dataTableRecordList.add(bean);
        
        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
        GtnUIFrameworkTransactionComponentListForInvalidBean bean1=new GtnUIFrameworkTransactionComponentListForInvalidBean();
       
        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
        doReturn(bean1).when(tableComponent).getCustomData();
        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        gtnUIFrameWorkActionConfig.setFieldValues(Arrays.asList("intefaceName", "transactionViewLayout"));
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameworkTransactionInvalidViewLoadAction instance = new GtnUIFrameworkTransactionInvalidViewLoadAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);

    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkTransactionInvalidViewLoadAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkTransactionInvalidViewLoadAction instance = new GtnUIFrameworkTransactionInvalidViewLoadAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
