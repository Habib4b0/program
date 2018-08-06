/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import java.lang.reflect.Method;
import java.util.List;
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
public class GtnUIFrameWorkCompanyMasterLoadActionTest {
    
    public GtnUIFrameWorkCompanyMasterLoadActionTest() {
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
     * Test of getCompanyInformation method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testGetCompanyInformation() {
        System.out.println("getCompanyInformation");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        GtnCMasterInformationBean expResult = null;
        GtnCMasterInformationBean result = instance.getCompanyInformation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompanyInformation method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testSetCompanyInformation() {
        System.out.println("setCompanyInformation");
        GtnCMasterInformationBean companyInformation = null;
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        instance.setCompanyInformation(companyInformation);
        assertNull(companyInformation);
    }

    /**
     * Test of getUdcInfo method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testGetUdcInfo() {
        System.out.println("getUdcInfo");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        GtnCMasterCompanyUdcInfoBean expResult = null;
        GtnCMasterCompanyUdcInfoBean result = instance.getUdcInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUdcInfo method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testSetUdcInfo() {
        System.out.println("setUdcInfo");
        GtnCMasterCompanyUdcInfoBean udcInfo = null;
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        instance.setUdcInfo(udcInfo);
        assertNull(udcInfo);
    }

    /**
     * Test of getIdentifierBeanList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testGetIdentifierBeanList() {
        System.out.println("getIdentifierBeanList");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        List<GtnCMasterIdentifierInfoBean> expResult = null;
        List<GtnCMasterIdentifierInfoBean> result = instance.getIdentifierBeanList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdentifierBeanList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testSetIdentifierBeanList() {
        System.out.println("setIdentifierBeanList");
        List<GtnCMasterIdentifierInfoBean> identifierBeanList = null;
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        instance.setIdentifierBeanList(identifierBeanList);
        assertNull(identifierBeanList);
    }

    /**
     * Test of getTradeClassInfoList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testGetTradeClassInfoList() {
        System.out.println("getTradeClassInfoList");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        List<GtnCMasterCompanyTradeClassBean> expResult = null;
        List<GtnCMasterCompanyTradeClassBean> result = instance.getTradeClassInfoList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTradeClassInfoList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testSetTradeClassInfoList() {
        System.out.println("setTradeClassInfoList");
        List<GtnCMasterCompanyTradeClassBean> tradeClassInfoList = null;
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        instance.setTradeClassInfoList(tradeClassInfoList);
        assertNull(tradeClassInfoList); 
    }

    /**
     * Test of getParentCompanyInfoList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testGetParentCompanyInfoList() {
        System.out.println("getParentCompanyInfoList");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        List<GtnCMasterCompanyParentBean> expResult = null;
        List<GtnCMasterCompanyParentBean> result = instance.getParentCompanyInfoList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setParentCompanyInfoList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testSetParentCompanyInfoList() {
        System.out.println("setParentCompanyInfoList");
        List<GtnCMasterCompanyParentBean> parentCompanyInfoList = null;
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        instance.setParentCompanyInfoList(parentCompanyInfoList);
        assertNull(parentCompanyInfoList); 
    }

    /**
     * Test of getLoadNotesTabBeanList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testGetLoadNotesTabBeanList() {
        System.out.println("getLoadNotesTabBeanList");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        List<NotesTabBean> expResult = null;
        List<NotesTabBean> result = instance.getLoadNotesTabBeanList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLoadNotesTabBeanList method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testSetLoadNotesTabBeanList() {
        System.out.println("setLoadNotesTabBeanList");
        List<NotesTabBean> loadNotesTabBeanList = null;
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        instance.setLoadNotesTabBeanList(loadNotesTabBeanList);
        assertNull(loadNotesTabBeanList); 
    }

    /**
     * Test of createInstance method, of class GtnUIFrameWorkCompanyMasterLoadAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameWorkCompanyMasterLoadAction instance = new GtnUIFrameWorkCompanyMasterLoadAction();
        //GtnUIFrameWorkAction expResult = null;
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }


    
}
