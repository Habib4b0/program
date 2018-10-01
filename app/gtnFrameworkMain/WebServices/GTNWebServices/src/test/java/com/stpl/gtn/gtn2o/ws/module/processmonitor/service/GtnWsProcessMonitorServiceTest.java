/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processmonitor.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.processmonitor.bean.GtnWsProcessMonitorBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processmonitor.GtnWsProcessMonitorRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
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
public class GtnWsProcessMonitorServiceTest {
    
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnWsProcessMonitorServiceTest() {
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
    private GtnWsProcessMonitorService gtnWsProcessMonitorService;
    
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
     * Test of getSessionFactory method, of class GtnWsProcessMonitorService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsProcessMonitorService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of deleteProcessMonitor method, of class GtnWsProcessMonitorService.
     */
    @Test
    public void testDeleteProcessMonitor() throws Exception {
        System.out.println("deleteProcessMonitor");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("704");
        generalRequest.setExcel(false);
        
        GtnWsProcessMonitorRequest monitorRequest=new GtnWsProcessMonitorRequest();
        GtnWsProcessMonitorBean bean=new GtnWsProcessMonitorBean();
        bean.setProcessName("29092018");
        bean.setProcessType("248");
        bean.setCalender(null);
        bean.setStartDate(new Date());
        bean.setEndDate(new Date());
        bean.setActiveFlag("Y");
        bean.setSchemaName("BPI");
        bean.setSlaCalendarMasterSid(1);
        bean.setCreatedDate(new Date());
        bean.setModifiedDate(new Date());
        bean.setHours1("8");
        bean.setHours2(null);
        bean.setHours3(null);
        bean.setMinutes1("30");
        bean.setMinutes2(null);
        bean.setMinutes3(null);
        bean.setProcessDisplayName("29092018");
        bean.setProcessMonitorSid(7308);
        bean.setComponent(null);
        bean.setErrorMessage(false);
        bean.setInboundStatus(null);
        bean.setFrequency("Time");
        
        monitorRequest.setProcessMonitorBean(bean);
        
        gtnWsRequest.setProcessMonitorRequest(monitorRequest);
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        thrown.expect(GtnFrameworkGeneralException.class);
        gtnWsProcessMonitorService.deleteProcessMonitor(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    /**
     * Test of saveUpdateProcessMonitor method, of class GtnWsProcessMonitorService.
     */
    @Test
    public void testSaveUpdateProcessMonitor() throws Exception {
        System.out.println("saveUpdateProcessMonitor");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("885");
        generalRequest.setExcel(false);
        
        GtnWsProcessMonitorRequest monitorRequest=new GtnWsProcessMonitorRequest();
        GtnWsProcessMonitorBean bean=new GtnWsProcessMonitorBean();
        bean.setProcessName("28sep18");
        bean.setProcessType("248");
        bean.setCalender(null);
        bean.setStartDate(new Date());
        bean.setEndDate(new Date());
        bean.setActiveFlag("Y");
        bean.setSchemaName("BPI");
        bean.setSlaCalendarMasterSid(1);
        bean.setCreatedDate(new Date());
        bean.setModifiedDate(new Date());
        bean.setHours1("9");
        bean.setHours2(null);
        bean.setHours3(null);
        bean.setMinutes1("30");
        bean.setMinutes2(null);
        bean.setMinutes3(null);
        bean.setProcessDisplayName("28sep18");
        bean.setProcessMonitorSid(7307);
        bean.setComponent(null);
        bean.setErrorMessage(false);
        bean.setInboundStatus("C");
        bean.setFrequency("Time");
        
        monitorRequest.setProcessMonitorBean(bean);
        gtnWsRequest.setProcessMonitorRequest(monitorRequest);
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        
        gtnWsProcessMonitorService.saveUpdateProcessMonitor(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
        @Test
    public void testSaveUpdateProcessMonitorFail() throws Exception {
        System.out.println("saveUpdateProcessMonitor");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("885");
        generalRequest.setExcel(false);
        
        GtnWsProcessMonitorRequest monitorRequest=new GtnWsProcessMonitorRequest();
        GtnWsProcessMonitorBean bean=new GtnWsProcessMonitorBean();
        bean.setProcessName("28sep18");
        bean.setProcessType("248");
        bean.setCalender(null);
        bean.setStartDate(new Date());
        bean.setEndDate(new Date());
        bean.setActiveFlag("Y");
        bean.setSchemaName("BPI");
        bean.setSlaCalendarMasterSid(1);
        bean.setCreatedDate(new Date());
        bean.setModifiedDate(new Date());
        bean.setHours1("9");
        bean.setHours2(null);
        bean.setHours3(null);
        bean.setMinutes1("30");
        bean.setMinutes2(null);
        bean.setMinutes3(null);
        bean.setProcessDisplayName("28sep18");
        bean.setProcessMonitorSid(7307);
        bean.setComponent(null);
        bean.setErrorMessage(false);
        bean.setInboundStatus("C");
        bean.setFrequency("Time");
        
        //monitorRequest.setProcessMonitorBean(bean);
        gtnWsRequest.setProcessMonitorRequest(monitorRequest);
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        
        thrown.expect(GtnFrameworkGeneralException.class);
        gtnWsProcessMonitorService.saveUpdateProcessMonitor(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of duplicateProcessName method, of class GtnWsProcessMonitorService.
     */
    @Test
    public void testDuplicateProcessName() throws Exception {
        System.out.println("duplicateProcessName");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("704");
        generalRequest.setExcel(false);
        
        GtnWsProcessMonitorRequest monitorRequest=new GtnWsProcessMonitorRequest();
        GtnWsProcessMonitorBean bean=new GtnWsProcessMonitorBean();
        bean.setProcessName("28sep18");
        bean.setProcessType("248");
        bean.setCalender(null);
        bean.setStartDate(new Date());
        bean.setEndDate(new Date());
        bean.setActiveFlag("Y");
        bean.setSchemaName("BPI");
        bean.setSlaCalendarMasterSid(1);
        bean.setCreatedDate(new Date());
        bean.setModifiedDate(new Date());
        bean.setHours1("8");
        bean.setHours2(null);
        bean.setHours3(null);
        bean.setMinutes1("30");
        bean.setMinutes2(null);
        bean.setMinutes3(null);
        bean.setProcessDisplayName("29092018");
        bean.setProcessMonitorSid(7307);
        bean.setComponent(null);
        bean.setErrorMessage(false);
        bean.setInboundStatus("A");
        bean.setFrequency("Time");
        
        monitorRequest.setProcessMonitorBean(bean);
        
        gtnWsRequest.setProcessMonitorRequest(monitorRequest);
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        
        gtnWsProcessMonitorService.duplicateProcessName(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testDuplicateProcessNameElse() throws Exception {
        System.out.println("duplicateProcessName");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("704");
        generalRequest.setExcel(false);
        
        GtnWsProcessMonitorRequest monitorRequest=new GtnWsProcessMonitorRequest();
        GtnWsProcessMonitorBean bean=new GtnWsProcessMonitorBean();
        bean.setProcessName("28sep18");
        bean.setProcessType("248");
        bean.setCalender(null);
        bean.setStartDate(new Date());
        bean.setEndDate(new Date());
        bean.setActiveFlag("Y");
        bean.setSchemaName("BPI");
        bean.setSlaCalendarMasterSid(1);
        bean.setCreatedDate(new Date());
        bean.setModifiedDate(new Date());
        bean.setHours1("8");
        bean.setHours2(null);
        bean.setHours3(null);
        bean.setMinutes1("30");
        bean.setMinutes2(null);
        bean.setMinutes3(null);
        bean.setProcessDisplayName("29092018");
        bean.setProcessMonitorSid(7307);
        bean.setComponent("ADD");
        bean.setErrorMessage(false);
        bean.setInboundStatus("C");
        bean.setFrequency("Time");
        
        monitorRequest.setProcessMonitorBean(bean);
        
        gtnWsRequest.setProcessMonitorRequest(monitorRequest);
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        
        gtnWsProcessMonitorService.duplicateProcessName(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    
    @Test
    public void testDuplicateProcessNameFail() throws Exception {
       System.out.println("duplicateProcessName");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("704");
        generalRequest.setExcel(false);
        
        GtnWsProcessMonitorRequest monitorRequest=new GtnWsProcessMonitorRequest();
        GtnWsProcessMonitorBean bean=new GtnWsProcessMonitorBean();
        bean.setProcessName("28sep18");
        bean.setProcessType("248");
        bean.setCalender(null);
        bean.setStartDate(new Date());
        bean.setEndDate(new Date());
        bean.setActiveFlag("Y");
        bean.setSchemaName("BPI");
        bean.setSlaCalendarMasterSid(1);
        bean.setCreatedDate(new Date());
        bean.setModifiedDate(new Date());
        bean.setHours1("8");
        bean.setHours2(null);
        bean.setHours3(null);
        bean.setMinutes1("30");
        bean.setMinutes2(null);
        bean.setMinutes3(null);
        bean.setProcessDisplayName("29092018");
        bean.setProcessMonitorSid(7307);
        bean.setComponent("ADD");
        bean.setErrorMessage(false);
        bean.setInboundStatus("C");
        bean.setFrequency("Time");
        
        //monitorRequest.setProcessMonitorBean(bean);
        
        gtnWsRequest.setProcessMonitorRequest(monitorRequest);
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        
        thrown.expect(GtnFrameworkGeneralException.class);
        gtnWsProcessMonitorService.duplicateProcessName(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    /**
     * Test of isNotBlank method, of class GtnWsProcessMonitorService.
     */
    @Test
    public void testIsNotBlank() {
        System.out.println("isNotBlank");
        String str = "code";
        boolean result = gtnWsProcessMonitorService.isNotBlank(str);
        assertFalse(str.isEmpty());
    }
    
}
