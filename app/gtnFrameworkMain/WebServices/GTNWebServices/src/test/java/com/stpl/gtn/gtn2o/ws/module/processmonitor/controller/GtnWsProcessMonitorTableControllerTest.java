/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processmonitor.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.Arrays;
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
public class GtnWsProcessMonitorTableControllerTest {
    
    public GtnWsProcessMonitorTableControllerTest() {
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
    private GtnWsProcessMonitorTableController gtnWsProcessMonitorTableController;
    
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
     * Test of onloadTableProcessMonitor method, of class GtnWsProcessMonitorTableController.
     */
    @Test
    public void testOnloadTableProcessMonitor() {
        System.out.println("onloadTableProcessMonitor");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("0");
        generalRequest.setSessionId("180");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchQueryName("SearchQuery");
        gtnWsSearchRequest.setCount(false);
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"processName","processType","slaCalendarMasterSid","modifiedDate","modifiedBy","processTypeId"}));
        gtnWsSearchRequest.setSearchModuleName("processMonitor");
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);    
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessMonitorTableController.onloadTableProcessMonitor(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testOnloadTableProcessMonitorFail() {
        System.out.println("testOnloadTableProcessMonitorFail");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("0");
        generalRequest.setSessionId("180");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchQueryName("SearchQuery");
        gtnWsSearchRequest.setCount(false);
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"processName","processType","slaCalendarMasterSid","modifiedDate","modifiedBy","processTypeId"}));
        gtnWsSearchRequest.setSearchModuleName("processMonitor");
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);    
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessMonitorTableController.onloadTableProcessMonitor(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
}
