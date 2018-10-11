/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.List;
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
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsProcessSchedulerControllerTest {
    
    public GtnWsProcessSchedulerControllerTest() {
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
    private GtnWsProcessSchedulerController gtnWsProcessSchedulerController;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Spy
    @Autowired
    private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;
    
    @Spy
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
     * Test of getProcesMonitorTableData method, of class GtnWsProcessSchedulerController.
     */
    @Test
    public void testGetProcesMonitorTableData() throws Exception {
        System.out.println("getProcesMonitorTableData");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        processSchedulerRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        processSchedulerRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerController.getProcesMonitorTableData(processSchedulerRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetProcesMonitorTableDataCount() throws Exception {
        System.out.println("getProcesMonitorTableData");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
        gtnWsSearchRequest.isCount();
        gtnWsSearchRequest.setCount(true);
        processSchedulerRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        processSchedulerRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerController.getProcesMonitorTableData(processSchedulerRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of getScheduledProcessingTableData method, of class GtnWsProcessSchedulerController.
     */
    @Test
    public void testGetScheduledProcessingTableData() throws Exception {
        System.out.println("getScheduledProcessingTableData");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        processSchedulerRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        processSchedulerRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerController.getScheduledProcessingTableData(processSchedulerRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetScheduledProcessingTableDataCount() throws Exception {
        System.out.println("getScheduledProcessingTableData");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
        gtnWsSearchRequest.isCount();
        gtnWsSearchRequest.setCount(true);
        processSchedulerRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        processSchedulerRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerController.getScheduledProcessingTableData(processSchedulerRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of getRunningProcess method, of class GtnWsProcessSchedulerController.
     */
    @Test
    public void testGetRunningProcess() throws Exception {
        System.out.println("getRunningProcess");
        GtnUIFrameworkWebserviceRequest processSchedulerRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean=new GtnWsProcessSchedulerBean();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        gtnWsProcessSchedulerBean.setPsSchemaName("BPI");
        gtnWsProcessSchedulerBean.setPsProcessName("CFF_OUTBOUND_INTERFACE");
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(gtnWsProcessSchedulerBean);
        processSchedulerRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        List<Object[]> queryInputList1 = new ArrayList<>();
        doReturn(queryInputList1).when(gtnWsProcessSchedularServiceUtil).executeQuery(Mockito.anyString());
        doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerController.getRunningProcess(processSchedulerRequest);
        assertFalse(gtnWsProcessSchedulerBean.getPsSchemaName().isEmpty());
    }
    
}
