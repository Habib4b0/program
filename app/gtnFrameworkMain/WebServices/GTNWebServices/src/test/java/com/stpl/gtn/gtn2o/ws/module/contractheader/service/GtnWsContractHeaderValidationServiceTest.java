/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractheader.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.Date;
import org.hibernate.SessionFactory;
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
public class GtnWsContractHeaderValidationServiceTest {
    
    public GtnWsContractHeaderValidationServiceTest() {
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
    private GtnWsContractHeaderValidationService gtnWsContractHeaderValidationService;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private GtnWsAllListConfig gtnWebServiceAllListConfig;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getSessionFactory method, of class GtnWsContractHeaderValidationService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsContractHeaderValidationService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of setSessionFactory method, of class GtnWsContractHeaderValidationService.
     */
    @Test
    public void testSetSessionFactory() {
        System.out.println("setSessionFactory");
        SessionFactory sessionFactory =gtnWsContractHeaderValidationService.getSessionFactory();
        gtnWsContractHeaderValidationService.setSessionFactory(sessionFactory);      
    }

    /**
     * Test of checkContractIdNameExist method, of class GtnWsContractHeaderValidationService.
     */
    @Test
    public void testCheckContractIdNameExist() throws Exception {
        System.out.println("checkContractIdNameExist");
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractHeaderValidationService instance = new GtnWsContractHeaderValidationService();
          GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        //gtnWsContractMasterBean.setContractMasterSid(0);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test158");
        gtnWsContractMasterBean.setContractNo("test158");
        gtnWsContractMasterBean.setContractName("test158");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        gtnWsContractHeaderValidationService.checkContractIdNameExist(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsContractMasterBean.getContractStatus()==0);
    }
     @Test
    public void testCheckContractIdNameExistElse() throws Exception {
        System.out.println("checkContractIdNameExist");
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsContractHeaderValidationService instance = new GtnWsContractHeaderValidationService();
          GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        //gtnWsContractMasterBean.setContractMasterSid(0);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test158");
        gtnWsContractMasterBean.setContractNo("test158");
        gtnWsContractMasterBean.setContractName("test158");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        //gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        try{
        gtnWsContractHeaderValidationService.checkContractIdNameExist(gtnWsRequest, gtnResponse);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }
    
    
}
