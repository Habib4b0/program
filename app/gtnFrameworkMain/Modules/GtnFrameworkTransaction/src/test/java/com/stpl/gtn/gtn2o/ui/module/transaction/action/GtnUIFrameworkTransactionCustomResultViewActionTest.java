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
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import java.lang.reflect.Method;
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
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class})
public class GtnUIFrameworkTransactionCustomResultViewActionTest {
    
    public GtnUIFrameworkTransactionCustomResultViewActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkTransactionCustomResultViewAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkTransactionCustomResultViewAction instance = new GtnUIFrameworkTransactionCustomResultViewAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnUIFrameworkTransactionCustomResultViewAction.
     */
//    @Test
//    public void testDoAction() throws Exception {
//       System.out.println("doAction");
//       String componentId = "AdjDemandForecastTypeSid";
//       GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
//
//        List<String>[] visibleColumnArray = null;
//        List<String>[] visibleHeaderArray = null;
//        Object tableNameForDemand = "searchResultTable";
//        int totalCombination = 0;
//        visibleColumnArray = new List[totalCombination];
//        visibleHeaderArray = new List[totalCombination];
//        for (int i = 0; i < totalCombination; i++) {
//            visibleColumnArray[i] = new ArrayList<>();
//            visibleHeaderArray[i] = new ArrayList<>();
//        }
//        gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList("sssvs", visibleColumnArray, visibleHeaderArray, GtnTransactionUIConstants.SEARCH_TABLE_ID, tableNameForDemand));
//        GtnUIFrameworkTransactionCustomResultViewAction instance = new GtnUIFrameworkTransactionCustomResultViewAction();
//        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
//    }

    /**
     * Test of getTableName method, of class GtnUIFrameworkTransactionCustomResultViewAction.
     */
    @Test
    public void testGetTableName() {
        System.out.println("getTableName");
        String tableName = "searchResultTable";
        GtnUIFrameworkTransactionCustomResultViewAction instance = new GtnUIFrameworkTransactionCustomResultViewAction();
        try{
        Method method = instance.getClass().getDeclaredMethod("getTableName",String.class);
        method.setAccessible(true);
        method.invoke(instance, tableName);
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkTransactionCustomResultViewActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkTransactionCustomResultViewAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkTransactionCustomResultViewAction instance = new GtnUIFrameworkTransactionCustomResultViewAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of getVisibleColumnAndHeaderForInventory method, of class GtnUIFrameworkTransactionCustomResultViewAction.
     */
//    @Test
//    public void testGetVisibleColumnAndHeaderForInventory() throws Exception {
//        System.out.println("getVisibleColumnAndHeaderForInventory");
//    List<String>[] visibleColumnArray = null;
//        List<String>[] visibleHeaderArray = null;
//        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
//        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
//        doReturn(1).when(tableBaseComponent).getIntegerFromField();
//        doReturn("Peri").when(tableBaseComponent).getStringFromField();
//        doReturn("Actuals").when(tableBaseComponent).getCaptionFromComboBox();
//        doReturn("Projections").when(tableBaseComponent).getCaptionFromComboBox();
//        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
////         PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
////         GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
////         when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
////         doReturn(1).when(object).getIntegerFromField();
////         doReturn("Peri").when(object).getStringFromField();
////         doReturn("Actuals").when(object).getCaptionFromComboBox();
////         doReturn("Projections").when(object).getCaptionFromComboBox();
//         
//        
//        int totalCombination = 0;
//        visibleColumnArray = new List[totalCombination];
//        visibleHeaderArray = new List[totalCombination];
//        for (int i = 0; i < totalCombination; i++) {
//            visibleColumnArray[i] = new ArrayList<>();
//            visibleHeaderArray[i] = new ArrayList<>();
//        }
//        GtnUIFrameworkTransactionCustomResultViewAction instance = new GtnUIFrameworkTransactionCustomResultViewAction();
//        List<Object> result = instance.getVisibleColumnAndHeaderForInventory(visibleColumnArray, visibleHeaderArray);
//    }

    /**
     * Test of getVisibleColumnForDemand method, of class GtnUIFrameworkTransactionCustomResultViewAction.
     */
//    @Test
//    public void testGetVisibleColumnForDemand() throws Exception {
//        System.out.println("getVisibleColumnForDemand");
//        List[] visibleColumn = null;
//        List[] visibleHeader = null;
//        String tableName = "";
//        GtnUIFrameworkTransactionCustomResultViewAction instance = new GtnUIFrameworkTransactionCustomResultViewAction();
//        List<Object> expResult = null;
//        List<Object> result = instance.getVisibleColumnForDemand(visibleColumn, visibleHeader, tableName);
//        assertEquals(expResult, result);
//    }
    
}
