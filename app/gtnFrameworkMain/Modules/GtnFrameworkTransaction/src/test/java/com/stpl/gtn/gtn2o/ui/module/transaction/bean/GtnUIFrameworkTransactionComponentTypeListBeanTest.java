/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.bean;

import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class GtnUIFrameworkTransactionComponentTypeListBeanTest {
    
    public GtnUIFrameworkTransactionComponentTypeListBeanTest() {
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
     * Test of getInvalidModule method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetInvalidModule() {
        System.out.println("getInvalidModule");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        String result = instance.getInvalidModule();
        assertFalse(result!=null);
    }

    /**
     * Test of setInvalidModule method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetInvalidModule() {
        System.out.println("setInvalidModule");
        String invalidModule = "rrrr";
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setInvalidModule(invalidModule);
        assertFalse(invalidModule.isEmpty());
    }

    /**
     * Test of getInventoryType method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetInventoryType() {
        System.out.println("getInventoryType");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<String> result = instance.getInventoryType();
        assertFalse(result!=null);
        
    }

    /**
     * Test of setInventoryType method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetInventoryType() {
        System.out.println("setInventoryType");
        List<String> inventoryType = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setInventoryType(inventoryType);
        assertFalse(inventoryType==null);
    }

    /**
     * Test of getListViewComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetListViewComponent() {
        System.out.println("getListViewComponent");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getListViewComponent();
        assertFalse(result!=null);
    }

    /**
     * Test of setListViewComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetListViewComponent() {
        System.out.println("setListViewComponent");
        List<GtnWSTransactionColumnBean> listViewComponent = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setListViewComponent(listViewComponent);
        assertFalse(listViewComponent==null);
    }

    /**
     * Test of getDefaultListViewComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetDefaultListViewComponent() {
        System.out.println("getDefaultListViewComponent");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getDefaultListViewComponent();
        assertFalse(result!=null);
    }

    /**
     * Test of setDefaultListViewComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetDefaultListViewComponent() {
        System.out.println("setDefaultListViewComponent");
        List<GtnWSTransactionColumnBean> defaultListViewComponent = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setDefaultListViewComponent(defaultListViewComponent);
        assertFalse(defaultListViewComponent==null);
    }

    /**
     * Test of getSearchComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetSearchComponent() {
        System.out.println("getSearchComponent");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getSearchComponent();
        assertFalse(result!=null);
    }

    /**
     * Test of setSearchComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetSearchComponent() {
        System.out.println("setSearchComponent");
        List<GtnWSTransactionColumnBean> searchComponent = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setSearchComponent(searchComponent);
        assertFalse(searchComponent==null);
    }

    /**
     * Test of getViewModeComponents method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetViewModeComponents() {
        System.out.println("getViewModeComponents");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getViewModeComponents();
        assertFalse(result!=null);
    }

    /**
     * Test of setViewModeComponents method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetViewModeComponents() {
        System.out.println("setViewModeComponents");
        List<GtnWSTransactionColumnBean> viewModeComponents = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setViewModeComponents(viewModeComponents);
        assertFalse(viewModeComponents==null);
    }

    /**
     * Test of getViewDateModeComponents method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetViewDateModeComponents() {
        System.out.println("getViewDateModeComponents");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getViewDateModeComponents();
        assertFalse(result!=null);
    }

    /**
     * Test of setViewDateModeComponents method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetViewDateModeComponents() {
        System.out.println("setViewDateModeComponents");
        List<GtnWSTransactionColumnBean> viewDateModeComponents = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setViewDateModeComponents(viewDateModeComponents);
        assertFalse(viewDateModeComponents==null);
    }

    /**
     * Test of getViewModeOrderComponents method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetViewModeOrderComponents() {
        System.out.println("getViewModeOrderComponents");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getViewModeOrderComponents();
        assertFalse(result!=null);
    }

    /**
     * Test of setViewModeOrderComponents method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetViewModeOrderComponents() {
        System.out.println("setViewModeOrderComponents");
        List<GtnWSTransactionColumnBean> viewModeOrderComponents = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setViewModeOrderComponents(viewModeOrderComponents);
        assertFalse(viewModeOrderComponents==null);      
    }

    /**
     * Test of getStaticComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetStaticComponent() {
        System.out.println("getStaticComponent");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<GtnWSTransactionColumnBean> result = instance.getStaticComponent();
        assertFalse(result!=null);
    }

    /**
     * Test of setStaticComponent method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetStaticComponent() {
        System.out.println("setStaticComponent");
        List<GtnWSTransactionColumnBean> staticComponent = new ArrayList<>();
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setStaticComponent(staticComponent);
        assertFalse(staticComponent==null); 
    }

    /**
     * Test of isViewIndexFlag method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testIsViewIndexFlag() {
        System.out.println("isViewIndexFlag");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        boolean expResult = false;
        boolean result = instance.isViewIndexFlag();
        assertEquals(expResult, result);
    }

    /**
     * Test of setViewIndexFlag method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetViewIndexFlag() {
        System.out.println("setViewIndexFlag");
        boolean viewIndexFlag = false;
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setViewIndexFlag(viewIndexFlag);
        assertFalse(viewIndexFlag);
    }

    /**
     * Test of isOutBoundModule method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testIsOutBoundModule() {
        System.out.println("isOutBoundModule");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        boolean expResult = false;
        boolean result = instance.isOutBoundModule();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOutBoundModule method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetOutBoundModule() {
        System.out.println("setOutBoundModule");
        boolean outBoundModule = false;
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setOutBoundModule(outBoundModule);
        assertFalse(outBoundModule);
    }

    /**
     * Test of getReprocessingWebServiceURL method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetReprocessingWebServiceURL() {
        System.out.println("getReprocessingWebServiceURL");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        String result = instance.getReprocessingWebServiceURL();
        assertFalse(result!=null);       
    }

    /**
     * Test of setReprocessingWebServiceURL method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetReprocessingWebServiceURL() {
        System.out.println("setReprocessingWebServiceURL");
        String reprocessingWebServiceURL = "gdgd";
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setReprocessingWebServiceURL(reprocessingWebServiceURL);
        assertFalse(reprocessingWebServiceURL.isEmpty());     
    }

    /**
     * Test of getStagingUpdateColumnsValues method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
//    @Test
//    public void testGetStagingUpdateColumnsValues() {
//        System.out.println("getStagingUpdateColumnsValues");
//        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
//        Object[] result = instance.getStagingUpdateColumnsValues();
//    }

    /**
     * Test of getStagingInsertColumns method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
//    @Test
//    public void testGetStagingInsertColumns() {
//        System.out.println("getStagingInsertColumns");
//        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
//        Object[] expResult = null;
//        Object[] result = instance.getStagingInsertColumns();
//        assertArrayEquals(expResult, result);
//    }

    /**
     * Test of getStagingUpdateColumns method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
//    @Test
//    public void testGetStagingUpdateColumns() {
//        System.out.println("getStagingUpdateColumns");
//        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
//        Object[] expResult = null;
//        Object[] result = instance.getStagingUpdateColumns();
//        assertArrayEquals(expResult, result);
//    }

    /**
     * Test of setStagingUpdateColumnsValues method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetStagingUpdateColumnsValues() {
        System.out.println("setStagingUpdateColumnsValues");
        Object[] stagingUpdateColumnsValues ={1,2,2,2,3};
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setStagingUpdateColumnsValues(stagingUpdateColumnsValues);
        assertFalse(stagingUpdateColumnsValues.length==0);
    }

    /**
     * Test of setStagingInsertColumns method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetStagingInsertColumns() {
        System.out.println("setStagingInsertColumns");
        Object[] stagingInsertColumns = {1,2,2,2,3};
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setStagingInsertColumns(stagingInsertColumns);
        assertFalse(stagingInsertColumns.length==0);
    }

    /**
     * Test of setStagingUpdateColumns method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testSetStagingUpdateColumns() {
        System.out.println("setStagingUpdateColumns");
        Object[] stagingUpdateColumns = {1,2,2,2,3};
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.setStagingUpdateColumns(stagingUpdateColumns);
        assertFalse(stagingUpdateColumns.length==0);
    }

    /**
     * Test of getFormatterMap method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
//    @Test
//    public void testGetFormatterMap_String() {
//        System.out.println("getFormatterMap");
//        String property = "jgjg";
//        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
//        String result = instance.getFormatterMap(property);
//        assertFalse(result.isEmpty());
//    }

    /**
     * Test of putFormatterMap method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testPutFormatterMap() {
        System.out.println("putFormatterMap");
        String propertyId = "hfh";
        String pattern = "dgdg";
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.putFormatterMap(propertyId, pattern);
        assertFalse(pattern.isEmpty());
    }

    /**
     * Test of getFormatterMap method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetFormatterMap_0args() {
        System.out.println("getFormatterMap");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.getFormatterMap();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormatterList method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testGetFormatterList() {
        System.out.println("getFormatterList");
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        List<String> expResult = null;
        List<String> result = instance.getFormatterList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addFormatterList method, of class GtnUIFrameworkTransactionComponentTypeListBean.
     */
    @Test
    public void testAddFormatterList() {
        System.out.println("addFormatterList");
        String value = "gdg";
        GtnUIFrameworkTransactionComponentTypeListBean instance = new GtnUIFrameworkTransactionComponentTypeListBean();
        instance.addFormatterList(value);
        assertFalse(value.isEmpty());
    }
    
}
