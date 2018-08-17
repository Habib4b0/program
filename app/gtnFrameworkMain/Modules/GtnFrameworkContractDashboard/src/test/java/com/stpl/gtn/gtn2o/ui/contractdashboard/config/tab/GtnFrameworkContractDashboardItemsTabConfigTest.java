/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnFrameworkContractDashboardItemsTabConfigTest {
    
    public GtnFrameworkContractDashboardItemsTabConfigTest() {
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
     * Test of getTabConfig method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertFalse(result.getTabCaption().isEmpty());
    }

    /**
     * Test of addContractHeaderDetailDecissionLayout method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddContractHeaderDetailDecissionLayout() {
        String methodName = "addContractHeaderDetailDecissionLayout";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderFieldPanel method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddContractHeaderFieldPanel() {
        String methodName = "addContractHeaderFieldPanel";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionLevel method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddDecissionLevel() {
        String methodName = "addDecissionLevel";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionView method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddDecissionView() {
        String methodName = "addDecissionView";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHeaderFieldComponent method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddHeaderFieldComponent() {
        String methodName = "addHeaderFieldComponent";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpId method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpId() {
        String methodName = "addIfpId";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpNo method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpNo() {
        String methodName = "addIfpNo";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpName method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpName() {
        String methodName = "addIfpName";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpStatus method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpStatus() {
        String methodName = "addIfpStatus";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpStartDate method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpStartDate() {
        String methodName = "addIfpStartDate";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpEndDate method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpEndDate() {
        String methodName = "addIfpEndDate";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpDesignation method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpDesignation() {
        String methodName = "addIfpDesignation";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentIfpId method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddParentIfpId() {
        String methodName = "addParentIfpId";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentIfpName method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddParentIfpName() {
        String methodName = "addParentIfpName";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpType method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpType() {
        String methodName = "addIfpType";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCreatedBy method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddCreatedBy() {
        String methodName = "addCreatedBy";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCreatedDate method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddCreatedDate() {
        String methodName = "addCreatedDate";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addIfpCategory method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddIfpCategory() {
        String methodName = "addIfpCategory";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addModifiedBy method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddModifiedBy() {
        String methodName = "addModifiedBy";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addModifiedDate method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddModifiedDate() {
        String methodName = "addModifiedDate";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of itemsTabHistoryDataTableComponent method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testItemsTabHistoryDataTableComponent() {
        String methodName = "itemsTabHistoryDataTableComponent";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeatilsLayout method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddDeatilsLayout() {
        String methodName = "addDeatilsLayout";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of massUpdatePanel method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testMassUpdatePanel() {
        String methodName = "massUpdatePanel";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHiddenField method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddHiddenField() {
        String methodName = "addHiddenField";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMassCheck method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddMassCheck() {
    try {
            System.out.println("addMassCheck");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String cdPricingTabPrefix="";
            String parent="";
            GtnUIFrameWorkActionConfig pricingMassCheckEnableDisableAction=new GtnUIFrameWorkActionConfig();
            GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addMassCheck",List.class,String.class,String.class,GtnUIFrameWorkActionConfig.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,cdPricingTabPrefix,parent,pricingMassCheckEnableDisableAction);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDashboardPrcingTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addMassPopulateField method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddMassPopulateField() {
        String methodName = "addMassPopulateField";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonLayout method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddPopulateButtonLayout() {
        String methodName = "addPopulateButtonLayout";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonComponent method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddPopulateButtonComponent() {
        String methodName = "addPopulateButtonComponent";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of reordLayout method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testReordLayout() {
        String methodName = "reordLayout";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of itemsResultPanel method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testItemsResultPanel() {
        String methodName = "itemsResultPanel";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of itemsViewResultTable method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testItemsViewResultTable() {
        String methodName = "itemsViewResultTable";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRemoveButtonLayout method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddRemoveButtonLayout() {
        String methodName = "addRemoveButtonLayout";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
        String methodName = "addExcelButtonComponent";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addViewExcelButtonComponent method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testAddViewExcelButtonComponent() {
        String methodName = "addViewExcelButtonComponent";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getTableEditableField method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testGetTableEditableField() {
        String methodName = "getTableEditableField";
        GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getTableFilterFieldMap method, of class GtnFrameworkContractDashboardItemsTabConfig.
     */
    @Test
    public void testGetTableFilterFieldMap() {
        try {
            System.out.println("getTableFilterFieldMap");
            GtnFrameworkContractDashboardItemsTabConfig instance = new GtnFrameworkContractDashboardItemsTabConfig();
            Method method = instance.getClass().getDeclaredMethod("getTableFilterFieldMap");
            method.setAccessible(true);
            Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> map = (Map<String, GtnUIFrameworkPagedTableCustomFilterConfig>) method.invoke(instance);
            assertFalse(map.isEmpty());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardItemsTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardItemsTabConfig gtnFrameworkContractDashboardInfomationTabConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardInfomationTabConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardInfomationTabConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardItemsTabConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
                switch (classArr[i].getName()) {
                    case "java.lang.String":
                        obj[i] = "VALUE";
                        break;
                    case "java.util.List":
                        obj[i] = new ArrayList<>();
                        break;
                    default:
                        break;
                }
            }
            method.setAccessible(true);
            method.invoke(gtnFrameworkContractDashboardInfomationTabConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardInfomationTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
