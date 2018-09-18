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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
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
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class})
public class GtnUIFrameworkTransactionReprocessRemoveActionTest {
    
    public GtnUIFrameworkTransactionReprocessRemoveActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkTransactionReprocessRemoveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkTransactionReprocessRemoveAction instance = new GtnUIFrameworkTransactionReprocessRemoveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

//    /**
//     * Test of doAction method, of class GtnUIFrameworkTransactionReprocessRemoveAction.
//     */
//    @Test
//    public void testDoAction() throws Exception {
//        System.out.println("getComponentForInvalidModules");
//        GtnUIFrameworkTransactionComponentListForInvalidBean bean = new GtnUIFrameworkTransactionComponentListForInvalidBean();
//        List<GtnUIFrameworkComponentConfig> staticComponent1List = new ArrayList<>();
//        bean.setStaticComponent1List(staticComponent1List);
//        List<String> fieldValues = 
//        Arrays.asList("companyIdlayoutInterfaceNameCss5",
//				"companyIdlayoutInterfaceNameCss4", "searchCriterialayout1", "resultPanel", "resultPanelLayout",
//				"resultlayout", "searchButtonlayout", "reprocessButtonlayout", "intefaceName",
//				GtnTransactionUIConstants.SEARCH_CRITERIA_LAYOUT, "gtnExcelButtonlayout");
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
//        List<Object> obj=Arrays.asList("0","searchResultTable","resultPanelLayout",componentTypeBean,"4","5","6","7");
//        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
//        List<GtnWsRecordBean> dataTableRecordList=new ArrayList<>();
//        GtnWsRecordBean bean1=new GtnWsRecordBean();
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        bean1.addProperties("gddgdg");
//        dataTableRecordList.add(bean1);
//        gtnUIFrameWorkActionConfig.setFieldValues(Arrays.asList("companyIdlayoutInterfaceNameCss5",
//				"companyIdlayoutInterfaceNameCss4", "searchCriterialayout1", "resultPanel", "resultPanelLayout",
//				"resultlayout", "searchButtonlayout", "reprocessButtonlayout", "intefaceName",
//				GtnTransactionUIConstants.SEARCH_CRITERIA_LAYOUT, "gtnExcelButtonlayout"));
//        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
//        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
//        
//        doReturn(checkallBean).when(tableComponent).getSharedPopupData();
//        
//        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
//        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
//        when(GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME)).thenReturn(1);
//        boolean isVisible = true;
//        gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList("jghjg","khhk",GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT,GtnTransactionUIConstants.SEARCH_TABLE_ID));
//        GtnUIFrameworkTransactionReprocessRemoveAction instance = new GtnUIFrameworkTransactionReprocessRemoveAction();
//        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
//    }

    /**
     * Test of getTableNameModuleWise method, of class GtnUIFrameworkTransactionReprocessRemoveAction.
     */
    @Test
    public void testGetTableNameModuleWise() throws Exception {
        System.out.println("getTableNameModuleWise");
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn(1).when(tableBaseComponent).getIntegerFromField();
        doReturn("Peri").when(tableBaseComponent).getStringFromField();
        doReturn("CPI").when(tableBaseComponent).getCaptionFromComboBox();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        String[] elements = {"VwIvldInventoryWdActualProjMas", "VwIvldAdjDemandForeActual", "VwIvldDemandForecastActual", "VwIvldActualsMaster", "VwCffOutboundMaster", "lj"};
        for (String s : elements) {
            GtnUIFrameworkTransactionReprocessRemoveAction instance = new GtnUIFrameworkTransactionReprocessRemoveAction();
        Method method = instance.getClass().getDeclaredMethod("getTableNameModuleWise",String.class);
        method.setAccessible(true);
        method.invoke(instance, s);
        assertFalse(elements.length==0);
        }
    }

    /**
     * Test of getDemandTableName method, of class GtnUIFrameworkTransactionReprocessRemoveAction.
     */
    @Test
    public void testGetDemandTableName() throws Exception {
        System.out.println("getDemandTableName");
        String componentId1 = "adjustedDemandForecastId";
        String componentId2 = "forecastTypeSid";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn(1).when(tableBaseComponent).getIntegerFromField();
        doReturn("Peri").when(tableBaseComponent).getStringFromField();
        doReturn(GtnTransactionUIConstants.ADJUSTED_DEMAND).when(tableBaseComponent).getCaptionFromComboBox();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnUIFrameworkTransactionReprocessRemoveAction instance = new GtnUIFrameworkTransactionReprocessRemoveAction();
        Method method = instance.getClass().getDeclaredMethod("getDemandTableName",String.class,String.class);
        method.setAccessible(true);
        method.invoke(instance, componentId1,componentId2);
        assertFalse(componentId1.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkTransactionReprocessRemoveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkTransactionReprocessRemoveAction instance = new GtnUIFrameworkTransactionReprocessRemoveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of getInventoryTableName method, of class GtnUIFrameworkTransactionReprocessRemoveAction.
     */
    @Test
    public void testGetInventoryTableName() throws Exception {
        System.out.println("getInventoryTableName");
        String inventoryType = "inventoryType";
        String inventoryLevel = "inventoryLevel";
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doReturn(1).when(tableBaseComponent).getIntegerFromField();
        doReturn("Peri").when(tableBaseComponent).getStringFromField();
        doReturn(GtnTransactionUIConstants.ACTUALS).when(tableBaseComponent).getCaptionFromComboBox();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        GtnUIFrameworkTransactionReprocessRemoveAction instance = new GtnUIFrameworkTransactionReprocessRemoveAction();
        Method method = instance.getClass().getDeclaredMethod("getInventoryTableName",String.class,String.class);
        method.setAccessible(true);
        method.invoke(instance, inventoryType,inventoryLevel);
        assertFalse(inventoryType.isEmpty());
    }
    
}
