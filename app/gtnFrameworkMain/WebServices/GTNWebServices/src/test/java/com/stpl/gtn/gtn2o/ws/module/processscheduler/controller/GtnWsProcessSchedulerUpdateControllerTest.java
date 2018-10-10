/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsFtpPropertiesBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.time.LocalDateTime;
import java.util.Date;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
public class GtnWsProcessSchedulerUpdateControllerTest {
    
    public GtnWsProcessSchedulerUpdateControllerTest() {
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
    private GtnWsProcessSchedulerUpdateController gtnWsProcessSchedulerUpdateController;
    
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
     * Test of runProcessScheduler method, of class GtnWsProcessSchedulerUpdateController.
     */
    @Test
    public void testRunProcessScheduler() throws GtnFrameworkGeneralException {
        System.out.println("runProcessScheduler");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean=new GtnWsProcessSchedulerBean();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        gtnWsProcessSchedulerBean.setPsSchemaName("BPI");
        gtnWsProcessSchedulerBean.setPsProcessName("CFF_OUTBOUND_INTERFACE");
        gtnWsProcessSchedulerBean.setProcessSchedulerSid(3);
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(gtnWsProcessSchedulerBean);
        gtnWsRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        GtnWsFtpPropertiesBean gtnWsFtpPropertiesBean=new GtnWsFtpPropertiesBean();
        doReturn(gtnWsFtpPropertiesBean).when(gtnWsProcessSchedularServiceUtil).getFtpBundleValue();
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerUpdateController.runProcessScheduler(gtnWsRequest);
        assertFalse(gtnWsProcessSchedulerBean.getPsSchemaName().isEmpty());
    }
 
    /**
     * Test of updateProcessScheduler method, of class GtnWsProcessSchedulerUpdateController.
     */
    @Test
    public void testUpdateProcessSchedulerFail() {
        System.out.println("updateProcessScheduler");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean=new GtnWsProcessSchedulerBean();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        gtnWsProcessSchedulerBean.setPsSchemaName("BPI");
        gtnWsProcessSchedulerBean.setPsProcessName("CFF_OUTBOUND_INTERFACE");
        gtnWsProcessSchedulerBean.setProcessSchedulerSid(3);
        gtnWsProcessSchedulerBean.setPsProcessFrequency("Quarterly");
        gtnWsProcessSchedulerBean.setPsStatus("Active");
        gtnWsProcessSchedulerBean.setStartHour("sh");
        gtnWsProcessSchedulerBean.setStartMinute("sm");
        gtnWsProcessSchedulerBean.setPsHours1("psh1");
        gtnWsProcessSchedulerBean.setPsHours2("psh2");
        gtnWsProcessSchedulerBean.setPsHours3("psh3");
        gtnWsProcessSchedulerBean.setPsMinutes1("psm1");
        gtnWsProcessSchedulerBean.setPsMinutes2("psm2");
        gtnWsProcessSchedulerBean.setPsMinutes3("psm3");
        gtnWsProcessSchedulerBean.setPsStartDate(new Date());
        gtnWsProcessSchedulerBean.setPsEndDate(new Date());
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(gtnWsProcessSchedulerBean);
        gtnWsRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerUpdateController.updateProcessScheduler(gtnWsRequest);
        assertFalse(gtnWsProcessSchedulerBean.getPsSchemaName().isEmpty());
    }
    
      @Test
    public void testUpdateProcessScheduler() {
        System.out.println("updateProcessScheduler");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean=new GtnWsProcessSchedulerBean();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        String time = "Sat Dec 15 16:37:57 MST 2012";
        gtnWsProcessSchedulerBean.setPsSchemaName("BPI");
        gtnWsProcessSchedulerBean.setPsProcessName("CFF_OUTBOUND_INTERFACE");
        gtnWsProcessSchedulerBean.setProcessSchedulerSid(3);
        gtnWsProcessSchedulerBean.setPsProcessFrequency("Quarterly");
        gtnWsProcessSchedulerBean.setPsStatus("Active");
        gtnWsProcessSchedulerBean.setStartHour("1");
        gtnWsProcessSchedulerBean.setStartMinute("15");
        gtnWsProcessSchedulerBean.setPsHours1("0");
        gtnWsProcessSchedulerBean.setPsHours2("0");
        gtnWsProcessSchedulerBean.setPsHours3("0");
        gtnWsProcessSchedulerBean.setPsMinutes1("15");
        gtnWsProcessSchedulerBean.setPsMinutes2("15");
        gtnWsProcessSchedulerBean.setPsMinutes3("15");
        gtnWsProcessSchedulerBean.setPsStartDate(new Date());
        gtnWsProcessSchedulerBean.setPsEndDate(new Date());
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(gtnWsProcessSchedulerBean);
        gtnWsRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessSchedulerUpdateController.updateProcessScheduler(gtnWsRequest);
        assertFalse(gtnWsProcessSchedulerBean.getPsSchemaName().isEmpty());
    }
    
}
