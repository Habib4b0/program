/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.emailconfiguration.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.contractheader.controller.GtnWsContractHeaderCotroller;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
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
public class GtnWsEmailConfigurationControllerTest {
    
    
    public GtnWsEmailConfigurationControllerTest() {
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
    private GtnWsEmailConfigurationController gtnWsEmailConfigurationCotroller;
    
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
     * Test of saveMailConfig method, of class GtnWsEmailConfigurationController.
     */
    @Test
    public void testSaveMailConfig() {
        System.out.println("saveMailConfig");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailConfigTabSMTP("Yes");
        configurationBean.setEmailConfigTabHostName("localhost");
        configurationBean.setEmailConfigTabemailAddress("john.smith");
        configurationBean.setEmailConfigTabPassword("johnS@123");
        configurationBean.setEmailConfigPortNumber("25");
        configurationBean.setEmailConfigTabTestMailAddress("support@bpitechnologies.com");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
    
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationCotroller.saveMailConfig(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
        @Test
    public void testSaveMailConfigFail() {
        System.out.println("saveMailConfig");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailConfigTabSMTP("Yes");
        configurationBean.setEmailConfigTabHostName("localhost");
        configurationBean.setEmailConfigTabemailAddress("john.smith");
        configurationBean.setEmailConfigTabPassword("johnS@123");
        configurationBean.setEmailConfigPortNumber("25");
        configurationBean.setEmailConfigTabTestMailAddress("support@bpitechnologies.com");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
    
        //gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationCotroller.saveMailConfig(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of saveMailNotification method, of class GtnWsEmailConfigurationController.
     */
    @Test
    public void testSaveMailNotification_GtnUIFrameworkWebserviceRequest() {
        System.out.println("saveMailNotification");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailNotificationTabProcessName("3");
        configurationBean.setEmailNotificationTabEmailTo("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabSubject("SUCCESS - CFF_OUTBOUND_INTERFACE");
        configurationBean.setEmailNotificationTabEmailCc("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabEmailBody("SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID COUNTS");
        configurationBean.setEmailNotificationTabFailureTo("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabFailureSubject("FAILURE - CFF_OUTBOUND_INTERFACE");
        configurationBean.setEmailNotificationTabEmailCc("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabEmailBody("CFF_OUTBOUND_INTERFACE FAILURE");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationCotroller.saveMailNotification(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testSaveMailNotification_GtnUIFrameworkWebserviceRequestFail() {
        System.out.println("saveMailNotification");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailNotificationTabProcessName("3");
        configurationBean.setEmailNotificationTabEmailTo("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabSubject("SUCCESS - CFF_OUTBOUND_INTERFACE");
        configurationBean.setEmailNotificationTabEmailCc("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabEmailBody("SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID COUNTS");
        configurationBean.setEmailNotificationTabFailureTo("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabFailureSubject("FAILURE - CFF_OUTBOUND_INTERFACE");
        configurationBean.setEmailNotificationTabEmailCc("SUPPORT@BPITECHNOLOGIES.COM");
        configurationBean.setEmailNotificationTabEmailBody("CFF_OUTBOUND_INTERFACE FAILURE");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
        //gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationCotroller.saveMailNotification(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of setComboboxOnchange method, of class GtnWsEmailConfigurationController.
     */
    @Test
    public void testSetComboboxOnchange() {
        System.out.println("testSetComboboxOnchange");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailNotificationTabProcessName("4");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationCotroller.setComboboxOnchange(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    

    /**
     * Test of setDefaultValues method, of class GtnWsEmailConfigurationController.
     */
    @Test
    public void testSetDefaultValues() throws Exception {
        System.out.println("setDefaultValues");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationCotroller.setDefaultValues(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of saveMailNotification method, of class GtnWsEmailConfigurationController.
     */
    @Test
    public void testSaveMailNotification_GtnWsMailConfigurationRequest_GtnWsGeneralRequest() throws Exception {
        System.out.println("saveMailNotification");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailNotificationTabProcessName("4");
        configurationBean.setEmailNotificationTabEmailTo("anjali.singh@sysbiz.com dipali.shrestha@sysbiz.com");
        configurationBean.setEmailNotificationTabSubject("SUCCESS - COMPANY_MASTER_INTERFACE");
        configurationBean.setEmailNotificationTabEmailCc("anjali.singh@sysbiz.com dipali.shrestha@sysbiz.com");
        configurationBean.setEmailNotificationTabEmailBody("SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID COUNTS");
        configurationBean.setEmailNotificationTabFailureTo("anjali.singh@sysbiz.com dipali.shrestha@sysbiz.com");
        configurationBean.setEmailNotificationTabFailureSubject("FAILURE - COMPANY_MASTER_INTERFACE");
        configurationBean.setEmailNotificationTabFailureEmailCc("");
        configurationBean.setEmailNotificationTabFailureEmailBody("COMPANY_MASTER_INTERFACE FAILURE");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsEmailConfigurationCotroller.saveMailNotification(configurationRequest, generalRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of saveMailConfiguration method, of class GtnWsEmailConfigurationController.
     */
    @Test
    public void testSaveMailConfiguration() throws Exception {
        System.out.println("saveMailConfiguration");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("66");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsMailConfigurationRequest configurationRequest=new GtnWsMailConfigurationRequest();
        GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
        configurationBean.setEmailConfigTabSMTP("Yes");
        configurationBean.setEmailConfigTabHostName("localhost");
        configurationBean.setEmailConfigTabemailAddress("john.smith");
        configurationBean.setEmailConfigTabPassword("johnS@123");
        configurationBean.setEmailConfigPortNumber("25");
        configurationBean.setEmailConfigTabTestMailAddress("support@bpitechnologies.com");
        
        configurationRequest.setConfigurationBean(configurationBean);
        gtnWsRequest.setMailConfigurationRequest(configurationRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);       
        gtnWsEmailConfigurationCotroller.saveMailConfiguration(configurationRequest, generalRequest);
    }
    
}
