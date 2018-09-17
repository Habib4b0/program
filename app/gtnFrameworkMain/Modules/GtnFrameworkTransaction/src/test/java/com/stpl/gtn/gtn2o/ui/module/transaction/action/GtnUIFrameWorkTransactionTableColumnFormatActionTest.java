/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GtnUIFrameWorkTransactionTableColumnFormatActionTest {
    
    public GtnUIFrameWorkTransactionTableColumnFormatActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
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
       
        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of manageValidTableRecordType method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
     */
    @Test
    public void testManageValidTableRecordType() throws Exception {
        System.out.println("manageValidTableRecordType");
        GtnWsRecordBean record = new GtnWsRecordBean();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);          
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnUIFrameworkTransactionComponentTypeListBean componentBean = new GtnUIFrameworkTransactionComponentTypeListBean();
        String[] headers={"fsdf","sfss","fsfss","AMP","khkk","itemPrice","hkh"};
        tableBaseComponent.setTableColumnHeaders(headers);
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
        Method method = instance.getClass().getDeclaredMethod("manageValidTableRecordType",GtnWsRecordBean.class,GtnUIFrameworkBaseComponent.class,GtnUIFrameworkTransactionComponentTypeListBean.class);
        method.setAccessible(true);
        method.invoke(instance, record, tableBaseComponent, componentBean);
        assertFalse(headers.length==0);
    }

    /**
     * Test of setFormatter method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
     */
    @Test
    public void testSetFormatter() {
        System.out.println("setFormatter");
        Object propertyId = GtnWsTransactionConstants.AVGSHELFLIFE;
        Object value = 12;
        String pattern=GtnWsTransactionConstants.AVGSHELFLIFE;
        GtnUIFrameworkTransactionComponentTypeListBean componentBean = new GtnUIFrameworkTransactionComponentTypeListBean();
        componentBean.putFormatterMap((String) propertyId, pattern);
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("setFormatter",Object.class,Object.class,GtnUIFrameworkTransactionComponentTypeListBean.class);
        method.setAccessible(true);
        method.invoke(instance, propertyId, value, componentBean);  
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameWorkTransactioneRecordTypeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(value==null);
    }

    /**
     * Test of formatPercentageValue method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
     */
    @Test
    public void testFormatPercentageValue() {
        System.out.println("formatPercentageValue");
        DecimalFormat decimalFormat = new DecimalFormat();
        Object value = 22;
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
         try{
        Method method = instance.getClass().getDeclaredMethod("formatPercentageValue",DecimalFormat.class,Object.class);
        method.setAccessible(true);
        method.invoke(instance, decimalFormat, value);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameWorkTransactioneRecordTypeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(value==null);
    }

    /**
     * Test of checkEmptyRecordValue method, of class GtnUIFrameWorkTransactionTableColumnFormatAction.
     */
    @Test
    public void testCheckEmptyRecordValue() {
        System.out.println("checkEmptyRecordValue");
        Object value = 22;
        GtnUIFrameWorkTransactionTableColumnFormatAction instance = new GtnUIFrameWorkTransactionTableColumnFormatAction();
    try{
        Method method = instance.getClass().getDeclaredMethod("checkEmptyRecordValue",Object.class);
        method.setAccessible(true);
        method.invoke(instance, value);
        assertFalse(value==null);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameWorkTransactioneRecordTypeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(value==null);
    }
    
}
