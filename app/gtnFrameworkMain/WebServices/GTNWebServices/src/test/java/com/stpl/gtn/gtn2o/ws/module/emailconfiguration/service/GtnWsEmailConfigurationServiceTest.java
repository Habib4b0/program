/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.emailconfiguration.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.emailconfiguration.controller.GtnWsEmailConfigurationController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
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
public class GtnWsEmailConfigurationServiceTest {
    
    public GtnWsEmailConfigurationServiceTest() {
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
    private GtnWsEmailConfigurationService gtnWsEmailConfigurationService;
    
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
     * Test of getSessionFactory method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsEmailConfigurationService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of getGtnSqlQueryEngine method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testGetGtnSqlQueryEngine() {
        System.out.println("getGtnSqlQueryEngine");
        GtnFrameworkSqlQueryEngine result = gtnWsEmailConfigurationService.getGtnSqlQueryEngine();
        assertFalse(result==null);
    }

    /**
     * Test of getGtnWsSqlService method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testGetGtnWsSqlService() {
        System.out.println("getGtnWsSqlService");
        GtnWsSqlService result = gtnWsEmailConfigurationService.getGtnWsSqlService();
        assertFalse(result==null);
    }

    /**
     * Test of comboBoxOnChangeLogic method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testComboBoxOnChangeLogic() {
        System.out.println("comboBoxOnChangeLogic");
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
        List result = gtnWsEmailConfigurationService.comboBoxOnChangeLogic(configurationRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of saveMailConfigurationLogic method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testSaveMailConfigurationLogic() {
        System.out.println("saveMailConfigurationLogic");

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
        gtnWsEmailConfigurationService.saveMailConfigurationLogic(configurationRequest, generalRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of saveMailNotificationLogic method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testSaveMailNotificationLogic() throws Exception {
        System.out.println("saveMailNotificationLogic");
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
        gtnWsEmailConfigurationService.saveMailNotificationLogic(configurationRequest, generalRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of setDefaultValueLogic method, of class GtnWsEmailConfigurationService.
     */
    @Test
    public void testSetDefaultValueLogic() throws Exception {
        System.out.println("setDefaultValueLogic");
        GtnUIFrameworkWebserviceResponse result = gtnWsEmailConfigurationService.setDefaultValueLogic();
        assertFalse(result==null); 
    }
    
}
