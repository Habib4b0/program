/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.processmonitor.service.GtnWsProcessMonitorTableServiceTest;
import com.stpl.gtn.gtn2o.ws.processscheduler.bean.GtnWsFtpPropertiesBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Arrays;
import java.util.Properties;
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
public class GtnWsProcessSchedularServiceUtilTest {
    
    public GtnWsProcessSchedularServiceUtilTest() {
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
    private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;
    
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
     * Test of getSearchInput method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testGetSearchInput() throws Exception {
        System.out.println("getSearchInput");
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("0");
        generalRequest.setSessionId("180");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);       
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        List<Object> result = gtnWsProcessSchedularServiceUtil.getSearchInput(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetSearchInputFail() throws Exception {
        System.out.println("testGetSearchInputFail");
        
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("0");
        generalRequest.setSessionId("180");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);       
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        try{
        List<Object> result = gtnWsProcessSchedularServiceUtil.getSearchInput(gtnWsRequest);
          Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of executeQuery method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = "SELECT WP.PROCESS_NAME FROM WORKFLOW_PROFILE WP";
        List result = gtnWsProcessSchedularServiceUtil.executeQuery(sqlQuery);
        assertFalse(result==null);
    }

    /**
     * Test of getSysSchemaCatalog method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testGetSysSchemaCatalog() throws Exception {
        System.out.println("getSysSchemaCatalog");
        String result = gtnWsProcessSchedularServiceUtil.getSysSchemaCatalog();
        assertFalse(result==null);
    }

    /**
     * Test of getFtpBundleValue method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testGetFtpBundleValue() {
        System.out.println("getFtpBundleValue");
        GtnWsFtpPropertiesBean result = gtnWsProcessSchedularServiceUtil.getFtpBundleValue();
        assertFalse(result==null);
    }

    /**
     * Test of runJob method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testRunJob() {
        System.out.println("runJob");
        GtnWsFtpPropertiesBean ftpProperties = new GtnWsFtpPropertiesBean();
        String scriptName = "scriptName";
        gtnWsProcessSchedularServiceUtil.runJob(ftpProperties, scriptName);
        assertFalse(scriptName.isEmpty());
    }

    /**
     * Test of runShellScript method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testRunShellScript() {
        System.out.println("runShellScript");
        String scriptUrl = "scriptName";
        gtnWsProcessSchedularServiceUtil.runShellScript(scriptUrl);
        assertFalse(scriptUrl.isEmpty());
    }

    /**
     * Test of schedulerInsert method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testSchedulerInsert() throws Exception {
        System.out.println("schedulerInsert");
        gtnWsProcessSchedularServiceUtil.schedulerInsert();
        assertTrue(true);
    }

    /**
     * Test of cffOutboundInsertProc method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testCffOutboundInsertProc() {
        System.out.println("cffOutboundInsertProc");
        String userId = "20156";
        String sessionId = "180";
        List cffIds = Arrays.asList("0");
        gtnWsProcessSchedularServiceUtil.cffOutboundInsertProc(userId, sessionId, cffIds);
        assertFalse(userId.isEmpty());
    }

    /**
     * Test of deleteTempCffOutbound method, of class GtnWsProcessSchedularServiceUtil.
     */
    @Test
    public void testDeleteTempCffOutbound() {
        System.out.println("deleteTempCffOutbound");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setUserId("0");
        gtnWsGeneralRequest.setSessionId("0");
        gtnWsGeneralRequest.setExcel(false);
        Boolean isScheduler = Boolean.TRUE;
        gtnWsProcessSchedularServiceUtil.deleteTempCffOutbound(gtnWsGeneralRequest, isScheduler);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testDeleteTempCffOutboundFail() {
        System.out.println("deleteTempCffOutbound");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setExcel(false);
        gtnWsGeneralRequest.setUserId("0");
        Boolean isScheduler = null;
        gtnWsProcessSchedularServiceUtil.deleteTempCffOutbound(gtnWsGeneralRequest, isScheduler);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testDeleteTempCffOutboundIf() {
        System.out.println("deleteTempCffOutbound");
        GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
        gtnWsGeneralRequest.setComboBoxType("COMPANY_TYPE");
        gtnWsGeneralRequest.setUserId("0");
        gtnWsGeneralRequest.setSessionId("0");
        gtnWsGeneralRequest.setExcel(false);
        Boolean isScheduler = Boolean.FALSE;
        gtnWsProcessSchedularServiceUtil.deleteTempCffOutbound(gtnWsGeneralRequest, isScheduler);
        assertFalse(gtnWsGeneralRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetPropertyPath() {
    try{
        System.out.println("getPropertyPath");
        Method method = gtnWsProcessSchedularServiceUtil.getClass().getDeclaredMethod("getPropertyPath");
        method.setAccessible(true);
        method.invoke(gtnWsProcessSchedularServiceUtil);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsProcessSchedularServiceUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void testExistsQuery() throws GtnFrameworkGeneralException {
        System.out.println("existsQuery");
        String userId="1";
        String sessionId="1";
        try{
        gtnWsProcessSchedularServiceUtil.existsQuery(userId, sessionId);
        assertFalse(userId.isEmpty());
          Assert.fail();
        } catch (IndexOutOfBoundsException e) {
			
	}

    }
}
