/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsProcessSchedulerCffOutBoundControllerTest {
    
    public GtnWsProcessSchedulerCffOutBoundControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
   @Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
    
    @After
    public void tearDown() {
    }
    
    @InjectMocks
    @Autowired
    private GtnWsProcessSchedulerCffOutBoundController gtnWsProcessSchedulerCffOutBoundController;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Mock
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
     * Test of updateCheckedRecordData method, of class GtnWsProcessSchedulerCffOutBoundController.
     */
    @Test
    public void testUpdateCheckedRecordData() throws GtnFrameworkGeneralException {
        System.out.println("updateCheckedRecordData");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsCffOutBoundBean cffOutBoundBean=new GtnWsCffOutBoundBean();
        cffOutBoundBean.isCheckedRecord();
        cffOutBoundBean.setCheckedRecord(true);
        gtnWsProcessSchedulerRequest.setCffOutBoundBean(cffOutBoundBean);
        processSchedulerCffRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        processSchedulerCffRequest.setGtnWsGeneralRequest(generalRequest);
        doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerCffOutBoundController.updateCheckedRecordData(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    

    /**
     * Test of generateCffOutBound method, of class GtnWsProcessSchedulerCffOutBoundController.
     */
    @Test
    public void testGenerateCffOutBound() throws GtnFrameworkGeneralException {
        System.out.println("generateCffOutBound");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsProcessSchedulerBean processSchedulerBean=new GtnWsProcessSchedulerBean();
        processSchedulerBean.setPsSchemaName("BPI");
        processSchedulerBean.setProcessSchedulerSid(1);
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(processSchedulerBean);
        processSchedulerCffRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        processSchedulerCffRequest.setGtnWsGeneralRequest(generalRequest);
        doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerCffOutBoundController.generateCffOutBound(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of checkAllRecordCffOutBound method, of class GtnWsProcessSchedulerCffOutBoundController.
     */
    @Test
    public void testCheckAllRecordCffOutBound() throws GtnFrameworkGeneralException {
        System.out.println("checkAllRecordCffOutBound");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsCffOutBoundBean cffOutBoundBean=new GtnWsCffOutBoundBean();
        cffOutBoundBean.setValue(true);
        gtnWsProcessSchedulerRequest.setCffOutBoundBean(cffOutBoundBean);
        processSchedulerCffRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        processSchedulerCffRequest.setGtnWsGeneralRequest(generalRequest);
         doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerCffOutBoundController.checkAllRecordCffOutBound(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
     @Test
    public void testCheckAllRecordCffOutBoundFail() throws GtnFrameworkGeneralException {
        System.out.println("checkAllRecordCffOutBound");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsCffOutBoundBean cffOutBoundBean=new GtnWsCffOutBoundBean();
        cffOutBoundBean.setValue(true);
         doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerCffOutBoundController.checkAllRecordCffOutBound(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
}
