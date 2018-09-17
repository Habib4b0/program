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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class GtnUIFrameWorkTransactioneRecordTypeActionTest {
    
    public GtnUIFrameWorkTransactioneRecordTypeActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
   
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
        
        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
        
               
        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
        
        doReturn(checkallBean).when(tableComponent).getSharedPopupData();
        
        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of manageTableRecordType method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testManageTableRecordType() throws Exception {
        System.out.println("manageTableRecordType");
        String componentId = "gfd";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean=new GtnUIFrameworkTransactionComponentTypeListBean();
        GtnWSTransactionTableCheckAllBean checkallBean=new GtnWSTransactionTableCheckAllBean();
        Set<String> checkedIdSet=new HashSet<>();
        checkedIdSet.add("ds");
        checkedIdSet.add("d2s");
        checkedIdSet.add("ds4");
        checkedIdSet.add("d7s");
        checkedIdSet.add("8ds");        
        checkallBean.setCheckedIdSet(checkedIdSet);
        List<Object> obj=Arrays.asList("0","1","fsfsf",componentTypeBean,"4","5","6","7");
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
        
        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
        
        tableComponent.setSharedPopupData(new GtnWSTransactionTableCheckAllBean());
        GtnUIFrameworkComponentData dataMock=Mockito.mock(GtnUIFrameworkComponentData.class);
               
        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
        doReturn(new Object()).when(dataMock).getSharedPopupData();
        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
        Method method = instance.getClass().getDeclaredMethod("manageTableRecordType",GtnWsRecordBean.class,GtnUIFrameworkBaseComponent.class,GtnWSTransactionTableCheckAllBean.class,String.class,GtnUIFrameworkTransactionComponentTypeListBean.class);
        method.setAccessible(true);
        method.invoke(instance, bean, tableBaseComponent, checkallBean, componentId, componentTypeBean);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of setFormatter method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testSetFormatter() {
        System.out.println("setFormatter");
        Object propertyId = GtnWsTransactionConstants.AVGSHELFLIFE;
        Object value = 12;
        String pattern=GtnWsTransactionConstants.AVGSHELFLIFE;
        GtnUIFrameworkTransactionComponentTypeListBean componentBean = new GtnUIFrameworkTransactionComponentTypeListBean();
        componentBean.putFormatterMap((String) propertyId, pattern);
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
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
     * Test of formatPercentValue method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testFormatPercentValue() {
        System.out.println("formatPercentValue");
        DecimalFormat decimalFormat = new DecimalFormat();
        Object value = 22;
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("formatPercentValue",DecimalFormat.class,Object.class);
        method.setAccessible(true);
        method.invoke(instance, decimalFormat, value);
        assertFalse(value==null);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameWorkTransactioneRecordTypeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of checkEmptyValue method, of class GtnUIFrameWorkTransactioneRecordTypeAction.
     */
    @Test
    public void testCheckEmptyValue() {
        System.out.println("checkEmptyValue");
        Object value = 22;
        GtnUIFrameWorkTransactioneRecordTypeAction instance = new GtnUIFrameWorkTransactioneRecordTypeAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("checkEmptyValue",Object.class);
        method.setAccessible(true);
        method.invoke(instance, value);
        assertFalse(value==null);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameWorkTransactioneRecordTypeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
