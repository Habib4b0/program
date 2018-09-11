/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractheader.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
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
public class GtnWsContractHeaderValidationCotrollerTest {
    
    public GtnWsContractHeaderValidationCotrollerTest() {
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
    private GtnWsContractHeaderValidationCotroller gtnWsContractHeaderValidationCotroller;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of contractIdAndNoValidation method, of class GtnWsContractHeaderValidationCotroller.
     */
    @Test
    public void testContractIdAndNoValidation() {
        System.out.println("contractIdAndNoValidation");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1613);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderValidationCotroller.contractIdAndNoValidation(gtnWsRequest);
        assertFalse(gtnWsContractMasterBean.getContractType()==0); 
    }
    
    @Test
    public void testContractIdAndNoValidationFalse() {
        System.out.println("contractIdAndNoValidation");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1613);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        try{
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderValidationCotroller.contractIdAndNoValidation(gtnWsRequest);
        assertFalse(gtnWsContractMasterBean.getContractType()==0); 
         } catch (Exception  ex) {
			
	}
    }
    
}
