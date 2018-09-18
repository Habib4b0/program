/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentListForInvalidBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class})
public class GtnUIFrameworkTransactionViewActionTest {
    
    public GtnUIFrameworkTransactionViewActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkTransactionViewAction.
     */
//    @Test
//    public void testDoAction() throws Exception {
//        System.out.println("testDoAction");
//        GtnUIFrameworkTransactionComponentListForInvalidBean bean = new GtnUIFrameworkTransactionComponentListForInvalidBean();
//        List<GtnUIFrameworkComponentConfig> staticComponent1List = new ArrayList<>();
//        bean.setStaticComponent1List(staticComponent1List);
//
//        String tableName = "searchResultTable";
//        String componentId = "";
//        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
//        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
//        GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean=new GtnUIFrameworkTransactionComponentTypeListBean();
//        GtnWSTransactionTableCheckAllBean checkallBean=new GtnWSTransactionTableCheckAllBean();
//        checkallBean.setCheckAll(true);
//        Set<String> checkedIdSet=new HashSet<>();
//        checkedIdSet.add("ds");
//        checkedIdSet.add("d2s");
//        checkedIdSet.add("ds4");
//        checkedIdSet.add("d7s");
//        checkedIdSet.add("8ds");        
//        checkallBean.setCheckedIdSet(checkedIdSet);
//        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
//        List<GtnWsRecordBean> dataTableRecordList=new ArrayList<>();
//        GtnWsRecordBean bean1=new GtnWsRecordBean();
//        bean1.addProperties(1);
//        bean1.addProperties(2);
//        bean1.addProperties(3);
//        bean1.addProperties(4);
//        bean1.addProperties(5);
//        bean1.addProperties(6);
//        bean1.addProperties(7);
//        bean1.addProperties(8);
//        dataTableRecordList.add(bean1);
//        gtnUIFrameWorkActionConfig.setFieldValues(Arrays.asList("companyIdlayoutInterfaceNameCss5",
//				"companyIdlayoutInterfaceNameCss4", "searchCriterialayout1", "resultPanel", "resultPanelLayout",
//				"resultlayout", "searchButtonlayout", "reprocessButtonlayout", "intefaceName",
//				GtnTransactionUIConstants.SEARCH_CRITERIA_LAYOUT, "gtnExcelButtonlayout"));
//        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
//        GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
//        GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
//	GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
//        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
//        
//        doReturn(checkallBean).when(tableComponent).getSharedPopupData();
//        
//        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
//        
//        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
//        when(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME)).thenReturn(1);
//    
//        List<Object> onlyViewColumns = new ArrayList<>();
//        List<String> helperComponentID = new ArrayList<>();
//        List<String> viewVisibleColumn = new ArrayList<>();
//        List<String> defaultViewVisibleColumn = new ArrayList<>();
//        List<GtnWSTransactionColumnBean> viewModeComponents= new ArrayList<>();
//        List<GtnWSTransactionColumnBean> viewModeOrderComponents=new ArrayList<>();
//        List<GtnWSTransactionColumnBean> viewDateModeComponents=new ArrayList<>();
//
//        Boolean isInvalid=false;
//        gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList("xfx",onlyViewColumns,tableName,helperComponentID,viewVisibleColumn
//                          ,defaultViewVisibleColumn,viewModeComponents,viewModeOrderComponents,isInvalid,viewDateModeComponents,bean1));
//        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
//        GtnUIFrameworkTransactionViewAction in = Mockito.spy(instance);
//       
//        
//       doReturn(response).when(in).callViewResultsService(wsclient, request);
//       in.doAction(componentId, gtnUIFrameWorkActionConfig);
//   }
//
//    /**
//     * Test of loadDataFromService method, of class GtnUIFrameworkTransactionViewAction.
//     */
//    @Test
//    public void testLoadDataFromService() throws Exception {
//        System.out.println("loadDataFromService");
//        List<Object> componentList = null;
//        String tableName = "";
//        List<String> helpercomponentList = null;
//        int systemId = 0;
//        String demandTypeColumnName = "";
//        String demandTypeColumnValue = "";
//        GtnUIFrameworkTransactionComponentTypeListBean gtnUIFrameworkTransactionComponentTypeListBean = null;
//        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
//        instance.loadDataFromService(componentList, tableName, helpercomponentList, systemId, demandTypeColumnName, demandTypeColumnValue, gtnUIFrameworkTransactionComponentTypeListBean);
//    }

    /**
     * Test of addComponentsForView method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testAddComponentsForView() {
        System.out.println("addComponentsForView");
        List<GtnWSTransactionColumnBean> viewModeComponents = new ArrayList<>();
        GtnWSTransactionColumnBean e = new GtnWSTransactionColumnBean();
        e.setColumnID("jggy");
        e.setColumnName("fsgs");
        viewModeComponents.add(e);
        String layoutName = "gdgdf";
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("addComponentsForView",List.class,String.class);
        method.setAccessible(true);
        method.invoke(instance,viewModeComponents, layoutName);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        //List<GtnUIFrameworkComponentConfig> result = instance.addComponentsForView(viewModeComponents, layoutName);
        assertFalse(layoutName.isEmpty());
    }

    /**
     * Test of getSortedColumnBean method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetSortedColumnBean() {
        System.out.println("getSortedColumnBean");
        List<GtnWSTransactionColumnBean> viewModeComponents = new ArrayList<>();
        GtnWSTransactionColumnBean e = new GtnWSTransactionColumnBean();
        e.setColumnID("jggy");
        e.setColumnName("fsgs");
        e.setViewModeIndex(1);
        viewModeComponents.add(e);
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("getSortedColumnBean",List.class);
        method.setAccessible(true);
        method.invoke(instance,viewModeComponents);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(viewModeComponents.isEmpty());
    }

    /**
     * Test of getSortedOrderColumnBean method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetSortedOrderColumnBean() {
        System.out.println("getSortedOrderColumnBean");
        List<GtnWSTransactionColumnBean> viewModeComponents = new ArrayList<>();
        GtnWSTransactionColumnBean e = new GtnWSTransactionColumnBean();
        e.setColumnID("jggy");
        e.setColumnName("fsgs");
        e.setViewModeOrder(1);
        viewModeComponents.add(e);
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("getSortedOrderColumnBean",List.class);
        method.setAccessible(true);
        method.invoke(instance,viewModeComponents);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(viewModeComponents.isEmpty());
    }

    /**
     * Test of addTextField method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testAddTextField() {
        System.out.println("addTextField");
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        GtnWSTransactionColumnBean component = new GtnWSTransactionColumnBean();
        component.setColumnID("jggy");
        component.setColumnName("fsgs");
        String layoutName = "gdsd";
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("addTextField",List.class,GtnWSTransactionColumnBean.class,String.class);
        method.setAccessible(true);
        method.invoke(instance,componentList, component, layoutName);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(componentList.isEmpty());
        //instance.addTextField(componentList, component, layoutName);
    }

    /**
     * Test of addDateField method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testAddDateField() {
        System.out.println("addDateField");
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        GtnWSTransactionColumnBean component = new GtnWSTransactionColumnBean();
        component.setColumnID("jggy");
        component.setColumnName("fsgs");
        String layoutName = "geees";
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("addDateField",List.class,GtnWSTransactionColumnBean.class,String.class);
        method.setAccessible(true);
        method.invoke(instance,componentList, component, layoutName);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of parseDate method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testParseDate() {
        System.out.println("parseDate");
        String d = "31/12/1998";  
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        //Date result = instance.parseDate(d);
        try{
        Method method = instance.getClass().getDeclaredMethod("parseDate",String.class);
        method.setAccessible(true);
        method.invoke(instance,d);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(d.isEmpty());
    }

    /**
     * Test of getTableName method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetTableName() {
        System.out.println("getTableName");
        String tableName = "Ivld";
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("getTableName",String.class);
        method.setAccessible(true);
        method.invoke(instance,tableName);
          } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(tableName.isEmpty());
    }

    /**
     * Test of getComponentList method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetComponentList() throws Exception {
        System.out.println("getComponentList");
        String forecastTypeCaption = GtnTransactionUIConstants.ACTUALS;
        List<GtnWSTransactionColumnBean> viewModeOrderComponents = new ArrayList<>();
        List<GtnWSTransactionColumnBean> viewModeComponents = new ArrayList<>();
        String layoutName = "yfyfy";
        List<Object> viewModeOrderColumnList = new ArrayList<>();
        List<Object> viewModeColumnList = new ArrayList<>();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        Method method = instance.getClass().getDeclaredMethod("getComponentList",String.class,List.class,List.class,String.class,List.class,List.class);
        method.setAccessible(true);
        method.invoke(instance,forecastTypeCaption, viewModeOrderComponents, viewModeComponents, layoutName, viewModeOrderColumnList, viewModeColumnList);
        assertFalse(layoutName.isEmpty());
    }

    /**
     * Test of getSystemId method, of class GtnUIFrameworkTransactionViewAction.
     */
//    @Test
//    public void testGetSystemId() {
//        System.out.println("getSystemId");
//        boolean isInvalid = false;
//        String componentId = "hff";
//        List<Object> actionParamList = new ArrayList<>();
//        GtnWsRecordBean bean=new GtnWsRecordBean();
//        actionParamList.add(bean);
//        actionParamList.add(bean);
//        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
//        int result = instance.getSystemId(isInvalid, componentId, actionParamList);
//    }

    /**
     * Test of setValuesToComponents method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testSetValuesToComponents() throws Exception {
        System.out.println("setValuesToComponents");
        List<Object> componentList = new ArrayList<>();
        Object e=new Object();
        Object e1=new Object();
        Object e2=new Object();
        componentList.add(e);
        componentList.add(e1);
        componentList.add(e2);
        Object[] resultArray = {1,2,3,4};
        List<GtnWSTransactionColumnBean> viewDateModeComponents = new ArrayList<>();
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        Method method = instance.getClass().getDeclaredMethod("setValuesToComponents",List.class,Object[].class,List.class);
        method.setAccessible(true);
        method.invoke(instance,componentList, resultArray, viewDateModeComponents);
        assertFalse(componentList.isEmpty());
    }

    /**
     * Test of getDateMode method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetDateMode() {
        System.out.println("getDateMode");
        List<GtnWSTransactionColumnBean> viewDateMode = new ArrayList<>();
        GtnWSTransactionColumnBean e = new GtnWSTransactionColumnBean();
        e.setColumnID("fss");
        e.setColumnName("cfs");
        viewDateMode.add(e);
        Object componentId = new Object();
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("getDateMode",List.class,Object.class);
        method.setAccessible(true);
        method.invoke(instance,viewDateMode, componentId);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(viewDateMode.isEmpty());
    }

    /**
     * Test of getValuesForDateComponent method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetValuesForDateComponent() {
        System.out.println("getValuesForDateComponent");
        int i = 0;
        List<Object> componentList = new ArrayList<>();
        Object e=new Object();
        componentList.add(e);
        GtnWSTransactionColumnBean viewDateModeBean = new GtnWSTransactionColumnBean();
        Object value = new Object();
        Object[] resultArray = {1,2,3,4};
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("getValuesForDateComponent",int.class,List.class,GtnWSTransactionColumnBean.class,Object.class, Object[].class);
        method.setAccessible(true);
        method.invoke(instance,i, componentList, viewDateModeBean, value, resultArray);
         } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(componentList.isEmpty());
    }

    /**
     * Test of getValuesForComponent method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testGetValuesForComponent() throws Exception {
        System.out.println("getValuesForComponent");
        int i = 0;
        List<Object> componentList = new ArrayList<>();
        componentList.add("baselineAmp");
        Object[] resultArray = {1,2,3,4};
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        Method method = instance.getClass().getDeclaredMethod("getValuesForComponent",int.class,List.class,Object[].class);
        method.setAccessible(true);
        method.invoke(instance,i, componentList, resultArray);
        assertFalse(componentList.isEmpty());
    }

    /**
     * Test of callDecimalFormatForItemPrice method, of class GtnUIFrameworkTransactionViewAction.
     */
    @Test
    public void testCallDecimalFormatForItemPrice() {
        System.out.println("callDecimalFormatForItemPrice");
        Object componentValue = new Object();
        String qualifierName = "gdg";
        GtnUIFrameworkTransactionViewAction instance = new GtnUIFrameworkTransactionViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("callDecimalFormatForItemPrice",Object.class,String.class);
        method.setAccessible(true);
        method.invoke(instance,componentValue, qualifierName);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertFalse(qualifierName.isEmpty());
    }
    
}
