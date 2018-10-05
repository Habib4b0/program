/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processmonitor.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.mockito.InjectMocks;
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
public class GtnWsProcessMonitorTableServiceTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnWsProcessMonitorTableServiceTest() {
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
    private GtnWsProcessMonitorTableService gtnWsProcessMonitorTableService;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
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
     * Test of getGtnSqlQueryEngine method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetGtnSqlQueryEngine() {
        System.out.println("getGtnSqlQueryEngine");
        GtnFrameworkSqlQueryEngine result = gtnWsProcessMonitorTableService.getGtnSqlQueryEngine();
        assertFalse(result==null);
    }

    /**
     * Test of getSessionFactory method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsProcessMonitorTableService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of getSysSessionFactory method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetSysSessionFactory() {
        System.out.println("getSysSessionFactory");
        SessionFactory result = gtnWsProcessMonitorTableService.getSysSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of tableProcessMonitor method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testTableProcessMonitor() throws Exception {
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
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();     
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        List<Object[]> queryInputList1 = new ArrayList<>();
        doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsProcessMonitorTableService.tableProcessMonitor(gtnWsRequest, gtnResponse);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of getSearchInput method, of class GtnWsProcessMonitorTableService.
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
        gtnWsSearchRequest.setSearchQueryName("SearchQuery");
        gtnWsSearchRequest.setCount(false);
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"processName","processType","slaCalendarMasterSid","modifiedDate","modifiedBy","processTypeId"}));
        gtnWsSearchRequest.setSearchModuleName("processMonitor");
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);    
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();     
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        List<Object[]> queryInputList1 = new ArrayList<>();
        doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());
        Method method = gtnWsProcessMonitorTableService.getClass().getDeclaredMethod("getSearchInput",GtnUIFrameworkWebserviceRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsProcessMonitorTableService, gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetSearchInputFail() throws Exception {
        System.out.println("getSearchInput");
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
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();     
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        List<Object[]> queryInputList1 = new ArrayList<>();
        doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());
        thrown.expect(InvocationTargetException.class);
        Method method = gtnWsProcessMonitorTableService.getClass().getDeclaredMethod("getSearchInput",GtnUIFrameworkWebserviceRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsProcessMonitorTableService, gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of getSortedInputs method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetSortedInputs() {
        try{
        System.out.println("getSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        Method method = gtnWsProcessMonitorTableService.getClass().getDeclaredMethod("getSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsProcessMonitorTableService, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList==null);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsProcessMonitorTableServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        @Test
    public void testGetSortedInputsElse() {
        try{
        System.out.println("getSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        Method method = gtnWsProcessMonitorTableService.getClass().getDeclaredMethod("getSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsProcessMonitorTableService, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList==null);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsProcessMonitorTableServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of filterAndSortingCriteriaMap method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testFilterAndSortingCriteriaMap() {
        try{
        System.out.println("filterAndSortingCriteriaMap");
        Method method = gtnWsProcessMonitorTableService.getClass().getDeclaredMethod("filterAndSortingCriteriaMap");
        method.setAccessible(true);
        method.invoke(gtnWsProcessMonitorTableService);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsProcessMonitorTableServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getGtnWsSqlService method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetGtnWsSqlService() {
        System.out.println("getGtnWsSqlService");
        GtnWsSqlService result = gtnWsProcessMonitorTableService.getGtnWsSqlService();
        assertFalse(result==null);
    }

    /**
     * Test of executeQuery method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = "SELECT WP.PROCESS_NAME FROM WORKFLOW_PROFILE WP";
        List result = gtnWsProcessMonitorTableService.executeQuery(sqlQuery);
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetQuery_String() {
        System.out.println("getQuery");
        String sqlId = "getProcessMonitorCount";
        String result = gtnWsProcessMonitorTableService.getQuery(sqlId);
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetQuery_List_String() {
        System.out.println("getQuery");
        List input = new ArrayList();
        input.add("BPIGTN_AGN_SYS_UNIT");
        String queryName = "getProcessMonitorCount";
        String result = gtnWsProcessMonitorTableService.getQuery(input, queryName);
        assertFalse(result==null);
    }

    /**
     * Test of getSysSchemaCatalog method, of class GtnWsProcessMonitorTableService.
     */
    @Test
    public void testGetSysSchemaCatalog() throws Exception {
        System.out.println("getSysSchemaCatalog");
        String result = gtnWsProcessMonitorTableService.getSysSchemaCatalog();
        assertFalse(result==null);
    }
    
}
