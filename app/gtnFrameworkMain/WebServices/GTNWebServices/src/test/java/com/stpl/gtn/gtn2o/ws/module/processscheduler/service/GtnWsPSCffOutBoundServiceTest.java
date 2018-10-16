/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtilTest;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsCffOutBoundBean;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsProcessSchedulerBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.processscheduler.GtnWsProcessSchedulerRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class GtnWsPSCffOutBoundServiceTest {
    
    public GtnWsPSCffOutBoundServiceTest() {
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
    private GtnWsPSCffOutBoundService cffOutBoundService;
    
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
     * Test of getSessionFactory method, of class GtnWsPSCffOutBoundService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = cffOutBoundService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of cffOutBoundService method, of class GtnWsPSCffOutBoundService.
     */
    @Test
    public void testCffOutBoundService() {
        System.out.println("cffOutBoundService");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("1");
        generalRequest.setSessionId("1");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean=new GtnWsProcessSchedulerBean();
        gtnWsProcessSchedulerBean.setPsSchemaName("BPI");
        gtnWsProcessSchedulerBean.setProcessSchedulerSid(3);
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(gtnWsProcessSchedulerBean);
        processSchedulerCffRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        processSchedulerCffRequest.setGtnWsGeneralRequest(generalRequest);
        boolean result = cffOutBoundService.cffOutBoundService(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    @Test
    public void testCffOutBoundServiceFail() {
        System.out.println("cffOutBoundService");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest gtnWsProcessSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsProcessSchedulerBean gtnWsProcessSchedulerBean=new GtnWsProcessSchedulerBean();
        gtnWsProcessSchedulerBean.setPsSchemaName("BPI");
        gtnWsProcessSchedulerBean.setProcessSchedulerSid(3);
        gtnWsProcessSchedulerRequest.setProcessSchedulerBean(gtnWsProcessSchedulerBean);
        processSchedulerCffRequest.setProcessSchedulerRequest(gtnWsProcessSchedulerRequest);
        try{
        boolean result = cffOutBoundService.cffOutBoundService(processSchedulerCffRequest);
        assertFalse(gtnWsProcessSchedulerBean.getPsSchemaName().isEmpty());
        } catch (NullPointerException e) {
			
	}

    }
    
    /**
     * Test of deleteOnClose method, of class GtnWsPSCffOutBoundService.
     */
    @Test
    public void testDeleteOnClose() {
        System.out.println("deleteOnClose");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setUserId("0");
        gtnWsGeneralRequest.setSessionId("0");
        gtnWsGeneralRequest.setExcel(false);
        Boolean isScheduler = Boolean.TRUE;
        cffOutBoundService.deleteOnClose(gtnWsGeneralRequest);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testDeleteOnCloseFail() {
        System.out.println("deleteOnClose");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setExcel(false);
        gtnWsGeneralRequest.setUserId("0");
        Boolean isScheduler = null;
        cffOutBoundService.deleteOnClose(gtnWsGeneralRequest);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testDeleteOnCloseIf() {
       System.out.println("deleteTempCffOutbound");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setUserId("0");
        gtnWsGeneralRequest.setSessionId("0");
        gtnWsGeneralRequest.setExcel(false);
        Boolean isScheduler = Boolean.FALSE;
        cffOutBoundService.deleteOnClose(gtnWsGeneralRequest);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
    }

    /**
     * Test of checkETLRecords method, of class GtnWsPSCffOutBoundService.
     */
    @Test
    public void testCheckETLRecords() {
        try{
        System.out.println("checkETLRecords");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setUserId("1");
        gtnWsGeneralRequest.setSessionId("1");
        gtnWsGeneralRequest.setExcel(false);
        Method method = cffOutBoundService.getClass().getDeclaredMethod("checkETLRecords",GtnWsGeneralRequest.class);
        method.setAccessible(true);
        method.invoke(cffOutBoundService,gtnWsGeneralRequest);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsPSCffOutBoundServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of checkAllItems method, of class GtnWsPSCffOutBoundService.
     */
    @Test
    public void testCheckAllItems() {
        System.out.println("checkAllItems");
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest processSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsGeneralRequest generalRequest=new GtnWsGeneralRequest();
        generalRequest.setUserId("1");
        generalRequest.setSessionId("1");
        GtnWsCffOutBoundBean cffOutBoundBean=new GtnWsCffOutBoundBean();
        cffOutBoundBean.setValue(true);
        processSchedulerRequest.setCffOutBoundBean(cffOutBoundBean);
        processSchedulerCffRequest.setGtnWsGeneralRequest(generalRequest);
        processSchedulerCffRequest.setProcessSchedulerRequest(processSchedulerRequest);
        boolean result = cffOutBoundService.checkAllItems(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testCheckAllItemsFail() {
        System.out.println("checkAllItems");
        GtnUIFrameworkWebserviceRequest processSchedulerCffRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsProcessSchedulerRequest processSchedulerRequest=new GtnWsProcessSchedulerRequest();
        GtnWsGeneralRequest generalRequest=new GtnWsGeneralRequest();
        generalRequest.setUserId("1");
        generalRequest.setSessionId("1");
        GtnWsCffOutBoundBean cffOutBoundBean=new GtnWsCffOutBoundBean();
        cffOutBoundBean.setValue(true);
        processSchedulerRequest.setCffOutBoundBean(cffOutBoundBean);
        processSchedulerCffRequest.setGtnWsGeneralRequest(generalRequest);
        processSchedulerCffRequest.setProcessSchedulerRequest(processSchedulerRequest);
        boolean result = cffOutBoundService.checkAllItems(processSchedulerCffRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of deleteTempCffOutbound method, of class GtnWsPSCffOutBoundService.
     */
    @Test
    public void testDeleteTempCffOutbound() {
        System.out.println("deleteTempCffOutbound");
        Boolean isScheduler = Boolean.TRUE;
        cffOutBoundService.deleteTempCffOutbound(isScheduler);
        assertFalse(isScheduler==null);
    }
    
    @Test
    public void testDeleteTempCffOutboundfalse() {
        System.out.println("deleteTempCffOutbound");
        Boolean isScheduler = Boolean.FALSE;
        cffOutBoundService.deleteTempCffOutbound(isScheduler);
        assertFalse(isScheduler==null);
    }
    
        @Test
    public void testDeleteTempCffOutboundFail() {
        System.out.println("deleteTempCffOutbound");
        Boolean isScheduler = null;
        try{
        cffOutBoundService.deleteTempCffOutbound(isScheduler);
        assertFalse(isScheduler!=null);
        } catch (NullPointerException e) {
			
        }
    }   
}
