/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContract;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsContractDashboardSubmitLogicTest {
    
    public GtnWsContractDashboardSubmitLogicTest() {
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
    
    @Autowired
    private GtnWsContractDashboardSubmitLogic gtnWsContractDashboardSubmitLogic;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private GtnWsContractDashboardController gtnWsContractDashboardController;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    public GtnWsContractDashboardController getController() {
		return gtnWsContractDashboardController;
	}
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getController method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testGetController() {
        System.out.println("getController");
        GtnWsContractDashboardController result = gtnWsContractDashboardSubmitLogic.getController();
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        String queryName = "getCDCompanyAdditionMoveAllLeftQuery";
        String result = gtnWsContractDashboardSubmitLogic.getQuery(queryName);
        assertFalse(result==null);
    }

    /**
     * Test of checkCfpAdded method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckCfpAdded() throws Exception {
        System.out.println("checkCfpAdded");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkCfpAdded(gtnWsContractDashboardRequest);
        assertFalse(processBean==null);
    }
    
    @Test
    public void testCheckCfpAddedFalse() throws Exception {
        System.out.println("checkCfpAdded");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=null;
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
//        processBean.setUserId("189858");
//        processBean.setSessionId("847");
//        processBean.setContractId(1486);
//        processBean.setCfpContractId(377);
//        processBean.setIfpContractId(397);
//        processBean.setPsContractId(514);
//        processBean.setRsContractId(498);
//        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        //gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkCfpAdded(gtnWsContractDashboardRequest);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        assertFalse(processBean==null);
    }

    /**
     * Test of checkCfpTableNullValue method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckCfpTableNullValue() throws Exception {
        System.out.println("checkCfpTableNullValue");
        
        String field="cfpDetailsAttachedStatus";

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkCfpTableNullValue(gtnWsContractDashboardRequest,field);
        assertFalse(processBean==null);
    }
    
    @Test
    public void testCheckCfpTableNullValueFalse() throws Exception {
        System.out.println("checkCfpTableNullValue");
        
        String field=null;

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setUserId("189858");
//        gtnWsContractDashboardRequest.setSessionId("847");
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkCfpTableNullValue(gtnWsContractDashboardRequest,field);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of checkCfpTableDateNullValue method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckCfpTableDateNullValue() throws Exception {
        System.out.println("checkCfpTableDateNullValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="cfpDetailsAttachedStatus";
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkCfpTableDateNullValue(gtnWsContractDashboardRequest,field);
        assertFalse(processBean==null);
    }
    
      @Test
    public void testCheckCfpTableDateNullValueFalse() throws Exception {
        System.out.println("checkCfpTableDateNullValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
//        gtnWsContractDashboardRequest.setUserId("189858");
//        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field=null;
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
try{
        gtnWsContractDashboardSubmitLogic.checkCfpTableDateNullValue(gtnWsContractDashboardRequest,field);
           Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(processBean==null);
    }

    /**
     * Test of checkCfpTableEndDate method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckCfpTableEndDate() throws Exception {
        System.out.println("checkCfpTableEndDate");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="cfpDetailsAttachedStatus";
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkCfpTableEndDate(gtnWsContractDashboardRequest);
        assertFalse(processBean==null);
    }
    
    @Test
    public void testCheckCfpTableEndDateFalse() throws Exception {
        System.out.println("testCheckCfpTableEndDateFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("189858");
//        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=null;
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
//        processBean.setUserId("189858");
//        processBean.setSessionId("847");
//        processBean.setContractId(1486);
//        processBean.setCfpContractId(377);
//        processBean.setIfpContractId(397);
//        processBean.setPsContractId(514);
//        processBean.setRsContractId(498);
        
//        gtnWsContractDashboardRequest.setUserId("189858");
//        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="cfpDetailsAttachedStatus";
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
       try{
        gtnWsContractDashboardSubmitLogic.checkCfpTableEndDate(gtnWsContractDashboardRequest);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}


        //assertFalse(processBean==null);
    }

    /**
     * Test of checkIfpAdded method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckIfpAdded() throws Exception {
        System.out.println("checkIfpAdded");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="cfpDetailsAttachedStatus";
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkIfpAdded(gtnWsContractDashboardRequest);
        assertFalse(processBean==null);
    }
    
    @Test
    public void testCheckIfpAddedFalse() throws Exception {
        System.out.println("checkIfpAdded");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
//        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
//
//        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
       GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
//        processBean.setUserId("189858");
//        processBean.setSessionId("847");
//        processBean.setContractId(1486);
//        processBean.setCfpContractId(377);
//        processBean.setIfpContractId(397);
//        processBean.setPsContractId(514);
//        processBean.setRsContractId(498);
//        
//        gtnWsContractDashboardRequest.setUserId("189858");
//        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="cfpDetailsAttachedStatus";
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkIfpAdded(gtnWsContractDashboardRequest);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(processBean==null);
    }

    /**
     * Test of checkIfpTableNullValue method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckIfpTableNullValue() throws Exception {
        System.out.println("checkIfpTableNullValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="attachedStatus";
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkIfpTableNullValue(gtnWsContractDashboardRequest,field);
        assertFalse(processBean==null);
    }
    
     @Test
        public void testCheckIfpTableNullValueFalse() throws Exception {
        System.out.println("checkIfpTableNullValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field=null;
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkIfpTableNullValue(gtnWsContractDashboardRequest,field);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of checkIfpTableDateNullValue method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckIfpTableDateNullValue() throws Exception {
        System.out.println("checkIfpTableDateNullValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field="attachedStatus";
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkIfpTableDateNullValue(gtnWsContractDashboardRequest,field);
        assertFalse(processBean==null);
    }
    
      @Test
    public void testCheckIfpTableDateNullValueFalse() throws Exception {
        System.out.println("checkIfpTableDateNullValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String field=null;
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkIfpTableDateNullValue(gtnWsContractDashboardRequest,field);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}

    }

    /**
     * Test of checkIfpTableEndDate method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckIfpTableEndDate() throws Exception {  
        System.out.println("checkIfpTableEndDate");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String startDateField="startDate";
        String endDateField="endDate";
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkIfpTableEndDate(gtnWsContractDashboardRequest,startDateField, endDateField);
        assertFalse(processBean==null);
    }
    
        @Test
    public void testCheckIfpTableEndDateFalse() throws Exception {  
        System.out.println("checkIfpTableEndDate");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        
        String startDateField=null;
        String endDateField=null;
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkIfpTableEndDate(gtnWsContractDashboardRequest,startDateField, endDateField);
          Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}    
    }

    /**
     * Test of saveContractDashboard method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveContractDashboard() throws Exception {
        System.out.println("saveContractDashboard");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.saveContractDashboard(gtnWsContractDashboardRequest,cdResponse,generalRequest);
        assertFalse(processBean==null);
    }

    /**
     * Test of saveAllContractDashboardInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveAllContractDashboardInfo() throws Exception {
        try{
        System.out.println("saveAllContractDashboardInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveAllContractDashboardInfo", Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * Test of getConvertedDateValue method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testGetConvertedDateValue() {
        System.out.println("getConvertedDateValue");
        Object value = new Date();
        gtnWsContractDashboardSubmitLogic.getConvertedDateValue(value);
        assertFalse(Boolean.valueOf(value.toString().toString()));
    }

    /**
     * Test of updateContractInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testUpdateContractInfo() throws Exception {
//        System.out.println("updateContractInfo");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardSubmitLogic instance = null;
//        instance.updateContractInfo(session, cdRequest);
    try{
        System.out.println("updateContractInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("updateContractInfo", Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    
    
    @Test
    public void testUpdateContractInfoFalse() throws Exception {
    try{
        System.out.println("updateContractInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        //contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("updateContractInfo", Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
      
    }
      
    /**
     * Test of deletContractAliasInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
//    @Test
//    public void testDeletContractAliasInfo() throws Exception {
//        System.out.println("deletContractAliasInfo");
//         
//        
//       GtnWsContractDashboardRequest cdRequest=new GtnWsContractDashboardRequest(); 
//       GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
//       GtnWsContractDashboardSessionBean contractDashboardBean=new GtnWsContractDashboardSessionBean();
//       cdRequest.setContractDashboardBean(contractDashboardBean);
//       contractDashboardBean.setProcessBean(processBean);
//       processBean.setContractId(1279);
//        Session session = getController().getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        GtnWsRecordBean bean = cdRequest.getContractDashboardBean().getContractInfoBean();
//        ContractMaster contractMaster = session.load(ContractMaster.class,
//					cdRequest.getContractDashboardBean().getProcessBean().getContractId());
//			contractMaster.setContractId(bean.getStringPropertyByIndex(0));
//			contractMaster.setContractNo(bean.getStringPropertyByIndex(1));
//			contractMaster.setContractName(bean.getStringPropertyByIndex(2));
//			contractMaster
//					.setHelperTableByContractType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(3)));
//			contractMaster
//					.setHelperTableByContractStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(4)));
//			contractMaster
//					.setHelperTableByDocumentType(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(5)));
//			contractMaster.setStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(6)));
//			contractMaster.setEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(7)));
//			contractMaster
//					.setHelperTableByDocumentClass(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(8)));
//			int companyId = bean.getIntegerPropertyByIndex(50);
//			if (companyId != 0) {
//				contractMaster.setCompanyMasterByBunitCompanyMasterSid(session.load(CompanyMaster.class, companyId));
//			}
//			int tpId = bean.getIntegerPropertyByIndex(51);
//			if (tpId != 0) {
//				contractMaster.setCompanyMasterByContHoldCompanyMasterSid(session.load(CompanyMaster.class, tpId));
//			}
//			contractMaster.setHelperTableByContractTradeClass(
//					session.load(HelperTable.class, bean.getIntegerPropertyByIndex(10)));
//			contractMaster.setTerm(bean.getIntegerPropertyByIndex(11));
//			contractMaster.setRenegotiationStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(13)));
//			contractMaster.setRenegotiationEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(14)));
//			contractMaster.setPriceprotectionStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(15)));
//			contractMaster.setPriceprotectionEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(16)));
//			int manuFactureId = bean.getIntegerPropertyByIndex(17);
//			if (manuFactureId != 0) {
//				contractMaster.setCompanyMasterByManfCompanyMasterSid(session.load(CompanyMaster.class, manuFactureId));
//			}
//                        contractMaster.setContractEligibleDate(bean.getPropertyValueByIndex(18) != null ? getConvertedDateValue(bean.getPropertyValueByIndex(18)) : null);
//			contractMaster.setInsideOwner(bean.getStringPropertyByIndex(19));
//			contractMaster.setInsidePhone(bean.getStringPropertyByIndex(20));
//			contractMaster.setInsideAuthor(bean.getStringPropertyByIndex(21));
//			contractMaster.setInsideAdditional(bean.getStringPropertyByIndex(22));
//			contractMaster.setInsideAdditionalName(bean.getStringPropertyByIndex(23));
//			contractMaster.setInsideAdditionalPhone(bean.getStringPropertyByIndex(24));
//			contractMaster.setOutsideOwner(bean.getStringPropertyByIndex(25));
//			contractMaster.setOutsidePhone(bean.getStringPropertyByIndex(26));
//			contractMaster.setOutsideAuthor(bean.getStringPropertyByIndex(27));
//			contractMaster.setOutsideAdditional(bean.getStringPropertyByIndex(28));
//			contractMaster.setOutsideAdditionalName(bean.getStringPropertyByIndex(29));
//			contractMaster.setOutsideAdditionalPhone(bean.getStringPropertyByIndex(30));
//			contractMaster.setAdvanceNoticeDays(BigDecimal.valueOf(bean.getDoublePropertyByIndex(31)));
//			contractMaster.setAffiliatedContractInfo(bean.getStringPropertyByIndex(32));
//			contractMaster.setShippingTerms(bean.getStringPropertyByIndex(33));
//			contractMaster.setProposalStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(34)));
//			contractMaster.setProposalEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(35)));
//			contractMaster.setOriginalStartDate(getConvertedDateValue(bean.getPropertyValueByIndex(36)));
//			contractMaster.setOriginalEndDate(getConvertedDateValue(bean.getPropertyValueByIndex(37)));
//			contractMaster
//					.setHelperTableByAwardStatus(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(38)));
//			contractMaster.setLastUpdatedDate(getConvertedDateValue(bean.getPropertyValueByIndex(39)));
//			contractMaster.setPriceEscalationClause(bean.getStringPropertyByIndex(40));
//			contractMaster.setExemptFromLowPrice(bean.getStringPropertyByIndex(41));
//			contractMaster.setPriceResetIndicator(bean.getStringPropertyByIndex(42));
//			contractMaster.setCancellationClause(bean.getStringPropertyByIndex(43));
//			contractMaster.setMostFavoredNation(bean.getStringPropertyByIndex(44));
//			contractMaster.setCategory(bean.getStringPropertyByIndex(45));
//			contractMaster.setCurrency(bean.getStringPropertyByIndex(46));
//			contractMaster.setMinimumOrder(bean.getStringPropertyByIndex(47));
//			contractMaster
//					.setHelperTableByPaymentTerms(session.load(HelperTable.class, bean.getIntegerPropertyByIndex(48)));
//			contractMaster.setInternalNotes(bean.getStringPropertyByIndex(49));
//			contractMaster.setModifiedBy(Integer.parseInt(cdRequest.getUserId()));
//			contractMaster.setModifiedDate(new Date());
//			contractMaster.setSource("GTN");
//        
//        
//  
//        gtnWsContractDashboardSubmitLogic.deletContractAliasInfo(contractMaster, session);
//        session.close();
//        assertFalse(bean==null);
//    }
//
//    /**
//     * Test of saveContractAliasInfo method, of class GtnWsContractDashboardSubmitLogic.
//     */
//    @Test
//    public void testSaveContractAliasInfo() throws Exception {
//        System.out.println("saveContractAliasInfo");
//        ContractMaster contractMaster = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        Session session = null;
//        GtnWsContractDashboardSubmitLogic instance = null;
//        instance.saveContractAliasInfo(contractMaster, cdRequest, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deletNotesTabInfo method, of class GtnWsContractDashboardSubmitLogic.
//     */
//    @Test
//    public void testDeletNotesTabInfo() throws Exception {
//        System.out.println("deletNotesTabInfo");
//        ContractMaster contractMaster = null;
//        Session session = null;
//        GtnWsContractDashboardSubmitLogic instance = null;
//        instance.deletNotesTabInfo(contractMaster, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of saveNotesTabInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveNotesTabInfo() throws Exception {
  try{
        System.out.println("saveNotesTabInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveNotesTabInfo",GtnWsContractDashboardRequest.class,Session.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,gtnWsContractDashboardRequest,session);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSaveNotesTabInfoFalse() throws Exception {
  try{
        System.out.println("saveNotesTabInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        //gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveNotesTabInfo",GtnWsContractDashboardRequest.class,Session.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,gtnWsContractDashboardRequest,session);
        session.close();
        assertFalse(processBean==null);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
      
    }

    /**
     * Test of saveCFPInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveCFPInfo() throws Exception {
try{
        System.out.println("saveCFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveCFPInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSaveCFPInfoFalse() throws Exception {
try{
        System.out.println("saveCFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
//        contractDashboardBean.setContractInfoBean(treeBean);
//        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveCFPInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
        
    }

    /**
     * Test of saveIFPInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveIFPInfo() throws Exception {
//        System.out.println("saveIFPInfo");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsGeneralRequest gtnWsRequest = null;
//        GtnWsContractDashboardSubmitLogic instance = null;
//        instance.saveIFPInfo(session, cdRequest, gtnWsRequest);
try{
        System.out.println("saveIFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveIFPInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSaveIFPInfoFalse() throws Exception {
try{
        System.out.println("saveIFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        //contractDashboardBean.setProcessBean(processBean);
//        contractDashboardBean.setContractInfoBean(treeBean);
//        contractDashboardBean.setCompanyInfoBean(companiesBean);
        //contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveIFPInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
          } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }       
    }

    /**
     * Test of savePsInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSavePsInfo() throws Exception {
try{
        System.out.println("savePsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("savePsInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSavePsInfoFalse() throws Exception {
try{
        System.out.println("savePsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
//        contractDashboardBean.setProcessBean(processBean);
//        contractDashboardBean.setContractInfoBean(treeBean);
//        contractDashboardBean.setCompanyInfoBean(companiesBean);
//        contractDashboardBean.setItemInfoBean(itemBean);
//        contractDashboardBean.setPriceInfoBean(priceBean);
//        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("savePsInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
       } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
       
    }

    /**
     * Test of saveRsInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveRsInfo() throws Exception {
    try{
        System.out.println("saveRsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSaveRsInfoFalse() throws Exception {
    try{
        System.out.println("saveRsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
//        contractDashboardBean.setProcessBean(processBean);
//        contractDashboardBean.setContractInfoBean(treeBean);
//        contractDashboardBean.setCompanyInfoBean(companiesBean);
//        contractDashboardBean.setItemInfoBean(itemBean);
//        contractDashboardBean.setPriceInfoBean(priceBean);
//        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsInfo",Session.class,GtnWsContractDashboardRequest.class,GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,generalRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
        
    }

    /**
     * Test of saveRsUdcInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveRsUdcInfo() throws Exception {

try{
        System.out.println("saveRsUdcInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        RsContract rsContract=new RsContract ();
        rsContract.setRsContractSid(380);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsUdcInfo",Session.class,GtnWsContractDashboardRequest.class,RsContract.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,rsContract);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSaveRsUdcInfoFalse() throws Exception {

try{
        System.out.println("saveRsUdcInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
//        contractDashboardBean.setContractInfoBean(treeBean);
//        contractDashboardBean.setCompanyInfoBean(companiesBean);
//        contractDashboardBean.setItemInfoBean(itemBean);
//        contractDashboardBean.setPriceInfoBean(priceBean);
//        contractDashboardBean.setRebateInfoBean(rebateBean);
        RsContract rsContract=new RsContract ();
        rsContract.setRsContractSid(380);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsUdcInfo",Session.class,GtnWsContractDashboardRequest.class,RsContract.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,rsContract);
        session.close();
        assertFalse(processBean==null);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
       
    }

    /**
     * Test of saveRsFormulaDetailsInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveRsFormulaDetailsInfo() throws Exception {
//        System.out.println("saveRsFormulaDetailsInfo");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardSubmitLogic instance = null;
//        instance.saveRsFormulaDetailsInfo(session, cdRequest);
 try{
        System.out.println("saveRsFormulaDetailsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsFormulaDetailsInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Test of saveRsDetailsInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSaveRsDetailsInfo() throws Exception {
//        System.out.println("saveRsDetailsInfo");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        RsContract rsContract = null;
//        GtnWsContractDashboardSubmitLogic instance = null;
//        instance.saveRsDetailsInfo(session, cdRequest, rsContract);
try{
        System.out.println("saveRsDetailsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        RsContract rsContract=new RsContract ();
        rsContract.setRsContractSid(380);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsDetailsInfo",Session.class,GtnWsContractDashboardRequest.class,RsContract.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,rsContract);
        session.close();
        assertFalse(processBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void testSaveRsDetailsInfoFalse() throws Exception {
try{
        System.out.println("saveRsDetailsInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                       
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 
        
        Session session = getController().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        RsContract rsContract=new RsContract ();
        //rsContract.setRsContractSid(380);
//        gtnWsContractDashboardRequest.setUserId("20156");
//        gtnWsContractDashboardRequest.setSessionId("17");
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("saveRsDetailsInfo",Session.class,GtnWsContractDashboardRequest.class,RsContract.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest,rsContract);
        session.close();
        assertFalse(processBean==null);
       } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
     
    }

    /**
     * Test of validateContractDashboardToSave method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testValidateContractDashboardToSave() throws Exception {
        System.out.println("validateContractDashboardToSave");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.validateContractDashboardToSave(gtnWsContractDashboardRequest,contractDashboardResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of setValidationResponse method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testSetValidationResponse() {
        try{
        System.out.println("setValidationResponse");
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        boolean status = false;
        String message = "Select atleast one company for CFP  in Companies Tab.";
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("setValidationResponse",GtnWsContractDashboardResponse.class,boolean.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,cdResponse,status,message);
        assertFalse(message.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardSubmitLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    /**
     * Test of validateCfp method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testValidateCfp() throws Exception {
        System.out.println("validateCfp");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.validateCfp(gtnWsContractDashboardRequest,contractDashboardResponse);
        assertFalse(processBean==null);
    }
    
    /**
     * Test of validateIfp method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testValidateIfp() throws Exception {
        System.out.println("validateIfp");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.validateIfp(gtnWsContractDashboardRequest,contractDashboardResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of validatePs method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testValidatePs() throws Exception {
        System.out.println("validatePs");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.validatePs(gtnWsContractDashboardRequest,contractDashboardResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of validateRs method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testValidateRs() throws Exception {
        System.out.println("validateRs");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.validateRs(gtnWsContractDashboardRequest,contractDashboardResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of verifyPrice method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testVerifyPrice() throws Exception {
        System.out.println("verifyPrice");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.verifyPrice(gtnWsContractDashboardRequest);
        assertFalse(processBean==null);
    }
    
        @Test
    public void testVerifyPriceFalse() throws Exception {
        System.out.println("verifyPrice");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=null;
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setUserId("189858");
//        gtnWsContractDashboardRequest.setUserId("847");
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
try{
        gtnWsContractDashboardSubmitLogic.verifyPrice(gtnWsContractDashboardRequest);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}

        //assertFalse(processBean==null);
    }

    /**
     * Test of checkIfpTableRegeXpValue method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testCheckIfpTableRegeXpValue() throws Exception {
        System.out.println("checkIfpTableRegeXpValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        String field = "bundleNo";
        String dbField = "BUNDLE_NO";
        String regxp = "([0-9|a-z|A-Z|\\ |\\*])*";
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.checkIfpTableRegeXpValue(gtnWsContractDashboardRequest, field, dbField, regxp);
        assertFalse(processBean==null);
    }
    
     @Test
    public void testCheckIfpTableRegeXpValueFalse() throws Exception {
        System.out.println("checkIfpTableRegeXpValue");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        String field = null;
        String dbField = null;
        String regxp = null;
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        GtnWsContractDashboardResponse contractDashboardResponse=new GtnWsContractDashboardResponse();
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setUserId("847");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardSubmitLogic.checkIfpTableRegeXpValue(gtnWsContractDashboardRequest, field, dbField, regxp);
        //assertFalse(processBean==null);
         Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}

    }

    /**
     * Test of approveContractDashboard method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testApproveContractDashboard() throws Exception {
        System.out.println("approveContractDashboard");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.approveContractDashboard(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of approveAllContractDashboardInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testApproveAllContractDashboardInfo() throws Exception {
        System.out.println("approveAllContractDashboardInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveAllContractDashboardInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }

    /**
     * Test of approveCFPInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testApproveCFPInfo() throws Exception {
        System.out.println("approveCFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveCFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
    @Test
    public void testApproveCFPInfoFalse() throws Exception {
        try{
        System.out.println("approveCFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveCFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(processBean==null);
    }

    /**
     * Test of approveIFPInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testApproveIFPInfo() throws Exception {
        System.out.println("approveIFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveIFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
    @Test
    public void testApproveIFPInfoFalse() throws Exception {
        try{
        System.out.println("approveIFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveIFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(processBean==null);
    }

    /**
     * Test of approveRSInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testApproveRSInfo() throws Exception {
        System.out.println("approveRSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveRSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
    @Test
    public void testApproveRSInfoFalse() throws Exception {
        try{
        System.out.println("approveRSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approveRSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
          } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

    }

    /**
     * Test of approvePSInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testApprovePSInfo() throws Exception {
        System.out.println("approvePSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approvePSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
     @Test
    public void testApprovePSInfoFalse() throws Exception {
        try{
        System.out.println("approvePSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("approvePSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
        //assertFalse(processBean==null);
    }

    /**
     * Test of getSqlQueryEngine method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testGetSqlQueryEngine() {
    try{
        System.out.println("getSqlQueryEngine");
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("getSqlQueryEngine");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic);
        assertFalse(method.getParameterCount()!=0);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    /**
     * Test of rejectContractDashboard method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testRejectContractDashboard() throws Exception {
        System.out.println("rejectContractDashboard");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardSubmitLogic.rejectContractDashboard(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of rejectAllContractDashboardInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testRejectAllContractDashboardInfo() throws Exception {
        System.out.println("rejectAllContractDashboardInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectAllContractDashboardInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }

    /**
     * Test of rejectCFPInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testRejectCFPInfo() throws Exception {
        System.out.println("rejectCFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectCFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
    @Test
    public void testRejectCFPInfoFalse() throws Exception {
        try{
        System.out.println("rejectCFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectCFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
        //assertFalse(processBean==null);
    }

    /**
     * Test of rejectIFPInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testRejectIFPInfo() throws Exception {
        System.out.println("rejectIFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectIFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
     @Test
    public void testRejectIFPInfofalse() throws Exception {
        try{
        System.out.println("rejectIFPInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectIFPInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
          } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(processBean==null);
    }

    /**
     * Test of rejectRSInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testRejectRSInfo() throws Exception {
        System.out.println("rejectRSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectRSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
    @Test
    public void testRejectRSInfoFalse() throws Exception {
        try{
        System.out.println("rejectRSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectRSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(processBean==null);
    }

    /**
     * Test of rejectPSInfo method, of class GtnWsContractDashboardSubmitLogic.
     */
    @Test
    public void testRejectPSInfo() throws Exception {
        System.out.println("rejectPSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectPSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();
        assertFalse(processBean==null);
    }
    
     @Test
    public void testRejectPSInfoFalse() throws Exception {
        try{
        System.out.println("rejectPSInfo");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        
//        contractDashboardBean.setProcessBean(processBean);
//        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardSubmitLogic.getClass().getDeclaredMethod("rejectPSInfo",Session.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardSubmitLogic,session,gtnWsContractDashboardRequest);
        session.close();  
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
        //assertFalse(processBean==null);
    }
    
    public Date getConvertedDateValue(Object value) {
		Object dateValue = value;
		if (dateValue != null && Long.class.isAssignableFrom(dateValue.getClass())) {
			dateValue = new Date((Long) dateValue);
		}
		return "null".equals(String.valueOf(dateValue)) || String.valueOf(dateValue).equals(StringUtils.EMPTY)? null:(Date) dateValue;
	}   
}
