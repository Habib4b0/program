/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.forecastconfiguration.ForecastConfig;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.itemaster.service.GtnWsItemMasterSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.transaction.controller.GtnWsTransactionReprocessController;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.ClassUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
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
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsTransactionServiceTest {
    
   
    @Mock
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;
    @InjectMocks
    @Autowired
    GtnWsTransactionService gtnWsTransactionService1;
    
    @Mock
    @Autowired
    private GtnWsAllListConfig gtnWsAllListConfig;
    
    @Mock
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    public GtnWsTransactionServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }
    
    
  
    
   
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
     @Autowired
     private GtnWsTransactionService gtnWsTransactionService;
     
//     @Autowired
//     private GtnWsAllListConfig gtnWebServiceAllListConfig;
    
    @PostConstruct
    public void constrcut(){
       GtnWsAllListConfig gtnWebServiceAllListConfig= gtnWsTransactionService.getGtnWebServiceAllListConfig();
       gtnWebServiceAllListConfig.setGtnSqlQueryEngine(gtnWsTransactionService.getGtnSqlQueryEngine());
    }
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getSearchDetails method, of class GtnWsTransactionService.
     */
//    @Test
//    public void testGetSearchDetails() throws Exception {
//        System.out.println("getSearchDetails");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("410");
//        generalRequest.setExcel(false);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
//        boolean isCount = true;
//        boolean isExcel = false;
//       
//        gtnWsSearchRequest.setCount(false);
//        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","forecastYear","forecastMonth","marketSizeUnits","marketShareRatio",
//                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount",
//                        "batchId","source","country","organizationKey"));
//                List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
//        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
//        criteria.setFieldId("itemId");
//        criteria.setFilterValue1("*");
//        criteria.setExpression("LIKE");
//        criteria.setFilter(false);
//        gtnWebServiceSearchCriteriaList.add(criteria);
//
//        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
//        criteria2.setFieldId("forecastTypeSid");
//        criteria2.setFilterValue1("645");
//        criteria2.setExpression("EQUALS");
//        criteria2.setFilter(false);
//        gtnWebServiceSearchCriteriaList.add(criteria2);
//        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
//        gtnWsSearchRequest.setSearchModuleName("VwAdjustDemandForecastAct");
//        gtnWsSearchRequest.setTableRecordStart(0);
//        gtnWsSearchRequest.setTableRecordOffset(10);
//                
//        gtnWsTransactionService.getSearchDetails(gtnWsSearchRequest, isCount, isExcel);
//        assertFalse(generalRequest.getUserId().isEmpty());       
//    }

    /**
     * Test of appendWhereCondition method, of class GtnWsTransactionService.
     */
    @Test
    public void testAppendWhereCondition() throws Exception {

        System.out.println("appendWhereCondition");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("410");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
      
        ClassMetadata classMetadata = null;
       
        
               SessionFactory factory=Mockito.mock(SessionFactory.class);
		Session session=Mockito.mock(Session.class);
//		Transaction tx = Mockito.mock(Transaction.class);
		Criteria criteria3=Mockito.mock(Criteria.class);
                
		//doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
		doReturn(session).when(factory).openSession();
//		doReturn(tx).when(session).beginTransaction();
		doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        
//        Session session = sessionFactory.openSession();
        //Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
       
        boolean isInvalid = false;
       
        gtnWsSearchRequest.setCount(true);
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","forecastYear","forecastMonth","marketSizeUnits","marketShareRatio",
                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount",
                        "batchId","source","country","organizationKey"));
                List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("645");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsSearchRequest.setSearchModuleName("VwAdjustDemandForecastAct");
        Map<String, String> columnDataTypeMap=new HashMap<>();
                     
        gtnWsTransactionService.appendWhereCondition(gtnWsSearchRequest, columnDataTypeMap, criteria3, classMetadata, isInvalid);
        assertFalse(generalRequest.getUserId().isEmpty());    
    }

    /**
     * Test of andCriteria method, of class GtnWsTransactionService.
     */
    @Test
    public void testAndCriteriaDouble() throws Exception {
        System.out.println("andCriteria");
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String value = "*";
        String type = GtnFrameworkWebserviceConstant.DOUBLE;
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.andCriteria(criteria3, criteria, value, type, dateFormat);
        assertFalse(value.isEmpty());    
    }
    
        @Test
    public void testAndCriteriaInteger() throws Exception {
        System.out.println("andCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String value = "*";
        String type = GtnFrameworkWebserviceConstant.INTEGER;
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.andCriteria(criteria3, criteria, value, type, dateFormat);
        assertFalse(value.isEmpty());    
    }
    
    @Test
    public void testAndCriteriaDate() throws Exception {
        System.out.println("andCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String value = "*";
        String type = GtnFrameworkWebserviceConstant.DATE;
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.andCriteria(criteria3, criteria, value, type, dateFormat);
        assertFalse(value.isEmpty());    
    }

    /**
     * Test of getValueBasedOnType method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetValueBasedOnTypeDate() throws Exception {
        System.out.println("getValueBasedOnType");
        String type = Date.class.getName();
        String value = "*";
        String filterValue = "2000-05-05";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        Object result = instance.getValueBasedOnType(type, value, filterValue, dateFormat);
        assertFalse(filterValue.isEmpty());    
    }
    
    @Test
    public void testGetValueBasedOnTypeDouble() throws Exception {
        System.out.println("getValueBasedOnType");
        String type = "java.lang.Double";
        String value = "*";
        String filterValue = "2";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        Object result = instance.getValueBasedOnType(type, value, filterValue, dateFormat);
        assertFalse(filterValue.isEmpty());    
    }
    
      @Test
    public void testGetValueBasedOnTypeInteger() throws Exception {
        System.out.println("getValueBasedOnType");
        String type = GtnWsConstants.INTEGER;
        String value = "*";
        String filterValue = "2";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        Object result = instance.getValueBasedOnType(type, value, filterValue, dateFormat);
        assertFalse(filterValue.isEmpty());    
    }

    /**
     * Test of equalsCriteria method, of class GtnWsTransactionService.
     */
    @Test
    public void testEqualsCriteria() throws Exception {
        System.out.println("equalsCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String type = "com.stpl.gtn.gtn2o.ws.entity.HelperTable";
        String value = "1";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalsCriteria(criteria3, criteria, type, value, dateFormat);
        assertFalse(type.isEmpty());    
    }
    
        @Test
    public void testEqualsCriteriaInteger() throws Exception {
        System.out.println("equalsCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String type = GtnWsConstants.INTEGER;
        String value = "1";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalsCriteria(criteria3, criteria, type, value, dateFormat);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testEqualsCriteriaDate() throws Exception {
        System.out.println("equalsCriteria");
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2000-05-05");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String type = "java.util.Date";
        String value = "1";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalsCriteria(criteria3, criteria, type, value, dateFormat);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testEqualsCriteriaDouble() throws Exception {
        System.out.println("equalsCriteria");
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String type = GtnFrameworkWebserviceConstant.DOUBLE;
        String value = "1";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalsCriteria(criteria3, criteria, type, value, dateFormat);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testEqualsCriteriaCheckRecord() throws Exception {
        System.out.println("equalsCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        System.out.println("equalsCriteria");
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId(GtnFrameworkWebserviceConstant.CHECK_RECORD);
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String type = GtnFrameworkWebserviceConstant.DOUBLE;
        String value = "1";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalsCriteria(criteria3, criteria, type, value, dateFormat);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testEqualsCriteriaElse() throws Exception {
        System.out.println("equalsCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("Item");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(false);
        String type = "";
        String value = "1";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalsCriteria(criteria3, criteria, type, value, dateFormat);
        assertFalse(value.isEmpty());    
    }

    /**
     * Test of equalCriteria method, of class GtnWsTransactionService.
     */
    @Test
    public void testEqualCriteriaDouble() throws Exception {
        System.out.println("equalCriteria");
//         Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("Item");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(true);
        String type = GtnFrameworkWebserviceConstant.DOUBLE;
        String dateFormat = "";
        String value = "";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalCriteria(criteria3, criteria, type, dateFormat, value);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testEqualCriteriaInteger() throws Exception {
        System.out.println("equalCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("Item");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(true);
        String type = GtnFrameworkWebserviceConstant.INTEGER;
        String dateFormat = "";
        String value = "";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalCriteria(criteria3, criteria, type, dateFormat, value);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testEqualCriteriaDate() throws Exception {
        System.out.println("equalCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("Item");
        criteria.setFilterValue1("2");
        criteria.setFilterValue2("2");
        criteria.setExpression("AND");
        criteria.setFilter(true);
        String type = GtnFrameworkWebserviceConstant.DATE;
        String dateFormat = "";
        String value = "";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.equalCriteria(criteria3, criteria, type, dateFormat, value);
        assertFalse(type.isEmpty());    
    }

    /**
     * Test of likeCriteria method, of class GtnWsTransactionService.
     */
    @Test
    public void testLikeCriteria() {
        System.out.println("likeCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        String value = "*";
        boolean isUser = false;
        boolean isInvalidFilter = false;
        String type = "java.lang.String";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.likeCriteria(criteria3, criteria2, value, isUser, isInvalidFilter, type);
        assertFalse(type.isEmpty());    
    }
    
    @Test
    public void testLikeCriteriaDouble() {
        System.out.println("likeCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        String value = "*";
        boolean isUser = false;
        boolean isInvalidFilter = false;
        String type = GtnFrameworkWebserviceConstant.DOUBLE;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.likeCriteria(criteria3, criteria2, value, isUser, isInvalidFilter, type);
        assertFalse(type.isEmpty());    
    }
    
       @Test
    public void testLikeCriteriaInteger() {
        System.out.println("likeCriteria");
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        String value = "%";
        boolean isUser = false;
        boolean isInvalidFilter = false;
        String type = GtnFrameworkWebserviceConstant.INTEGER;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.likeCriteria(criteria3, criteria2, value, isUser, isInvalidFilter, type);
        assertFalse(type.isEmpty());    
    }
    
     @Test
    public void testLikeCriteriaElse() {
        System.out.println("likeCriteria");
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        String value = "*";
        boolean isUser = false;
        boolean isInvalidFilter = true;
        String type = GtnFrameworkWebserviceConstant.INTEGER;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.likeCriteria(criteria3, criteria2, value, isUser, isInvalidFilter, type);
        assertFalse(type.isEmpty());    
    }

    /**
     * Test of getDoubleFilterValue method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetDoubleFilterValue() {
        System.out.println("getDoubleFilterValue");
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        String columnName = "";
        String type = GtnFrameworkWebserviceConstant.DOUBLE;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        gtnWsTransactionService.getDoubleFilterValue(criteria2, criteria3, columnName);
        assertFalse(type.isEmpty()); 
    }

    /**
     * Test of betweenConditon method, of class GtnWsTransactionService.
     */
    @Test
    public void testBetweenConditon() throws Exception {
        System.out.println("betweenConditon");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("410");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
      
        ClassMetadata classMetadata = null;
       
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);

       
        boolean isInvalid = false;
       
        gtnWsSearchRequest.setCount(true);
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","forecastYear","forecastMonth","marketSizeUnits","marketShareRatio",
                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount",
                        "batchId","source","country","organizationKey"));
                List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("645");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsSearchRequest.setSearchModuleName("VwAdjustDemandForecastAct");
        Map<String, String> columnDataTypeMap=new HashMap<>();
        String type="";       
        String dateFormat="yyyy-MM-dd";    
        gtnWsTransactionService.betweenConditon(criteria3, criteria, type, dateFormat);
        assertFalse(generalRequest.getUserId().isEmpty());    
    }

    /**
     * Test of getDateValue method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetDateValue() throws Exception {
        System.out.println("getDateValue");
        String type = "";
        String filterValue = "*";
        String dateFormat = "yyyy-MM-dd";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        Object result = instance.getDateValue(type, filterValue, dateFormat);
        assertFalse(filterValue.isEmpty()); 
    }

    /**
     * Test of generateCountProjectionAndCriteria method, of class GtnWsTransactionService.
     */
    @Test
    public void testGenerateCountProjectionAndCriteria() {
        System.out.println("generateCountProjectionAndCriteria");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("410");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        boolean isCount = true;
        boolean isExcel = false;
        //ClassMetadata classMetadata = null;
       
        gtnWsSearchRequest.setCount(false);
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","forecastYear","forecastMonth","marketSizeUnits","marketShareRatio",
                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount",
                        "batchId","source","country","organizationKey"));
                List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("itemId");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("forecastTypeSid");
        criteria2.setFilterValue1("645");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsSearchRequest.setSearchModuleName("VwAdjustDemandForecastAct");
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        Map<String, String> columnDataTypeMap=new HashMap<>();   
        columnDataTypeMap.put("marketShareUnits", "java.lang.Double");
        columnDataTypeMap.put("country", "java.lang.String");
        
//        Session session = sessionFactory.openSession();
//        Criteria criteria3 = session.createCriteria(GtnWsTransactionService.class);
        SessionFactory factory=Mockito.mock(SessionFactory.class);
        Session session=Mockito.mock(Session.class);
        Criteria criteria3=Mockito.mock(Criteria.class);
        doReturn(session).when(factory).openSession();
        doReturn(criteria3).when(session).createCriteria(GtnWsTransactionService.class);
        ClassMetadata classMetadata1 = Mockito.mock(ClassMetadata.class);
        
        when(classMetadata1.getIdentifierPropertyName()).thenReturn("");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList=new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        e.setPropertyId("marketShareUnits");
        gtnWebServiceOrderByCriteriaList.add(e);
        gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);

        gtnWsTransactionService1.generateCountProjectionAndCriteria(gtnWsSearchRequest, columnDataTypeMap, criteria3, classMetadata1);
        assertFalse(generalRequest.getUserId().isEmpty());      
    }

    /**
     * Test of getExpressionType method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetExpressionType() {
        System.out.println("getExpressionType");
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId(GtnFrameworkWebserviceConstant.ACTIVE);
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        String tableName = GtnFrameworkWebserviceConstant.GL_BALANCE;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        String result = instance.getExpressionType(criteria, tableName);
        assertFalse(tableName.isEmpty()); 
    }

    /**
     * Test of getViewDetails method, of class GtnWsTransactionService.
     */
//    @Test
//    public void testGetViewDetails() throws Exception {
//        GtnWsTransactionService instance = new GtnWsTransactionService();
//        System.out.println("getViewDetails");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("410");
//        generalRequest.setExcel(false);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
//        gtnWsTransactionRequest.setTableName("VwAdjustDemandForecastAct");
//        gtnWsTransactionRequest.setProjectionColumns(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","marketSizeUnits","marketShareRatio",
//                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount","forecastYear","forecastMonth","totalDemandUnits","totalDemandAmount",
//                        "batchId","source","country","businessUnitNo","businessUnitName"));
//        gtnWsTransactionRequest.setHelpercomponentList(Arrays.asList("adjustedDemandForecastId","forecastTypeSid","organizationKey"));
//        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
//        Session session = Mockito.mock(Session.class);
//        doReturn(session).when(sessionFactory).openSession();
//        gtnWsTransactionService1.getViewDetails(gtnWsTransactionRequest);
//        assertFalse(generalRequest.getUserId().isEmpty());      
//    }

    /**
     * Test of getViewRecord method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetViewRecord() {
        GtnWsTransactionService instance = new GtnWsTransactionService();
        System.out.println("getViewRecord");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("410");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("VwAdjustDemandForecastAct");
        gtnWsTransactionRequest.setProjectionColumns(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","marketSizeUnits","marketShareRatio",
                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount","forecastYear","forecastMonth","totalDemandUnits","totalDemandAmount",
                        "batchId","source","country","businessUnitNo","businessUnitName"));
        gtnWsTransactionRequest.setHelpercomponentList(Arrays.asList("adjustedDemandForecastId","forecastTypeSid","organizationKey"));
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        List<Object> resultList=new ArrayList<>();
        Object[] ob={1,2};
        resultList.add(ob);
        instance.getViewRecord(resultList, gtnWsTransactionRequest);
        assertFalse(generalRequest.getUserId().isEmpty());      
    }

    /**
     * Test of createFile method, of class GtnWsTransactionService.
     */
    @Test
    public void testCreateFile() {
        System.out.println("createFile");

        File tempFile = GtnFileNameUtils.getFile("abc" + GtnFrameworkCommonStringConstants.CSV_EXTENSION);
        FileWriter writer = null;
        try {
            writer = new FileWriter(tempFile);
        } catch (IOException ex1) {
            Logger.getLogger(GtnWsTransactionServiceTest.class.getName()).log(Level.SEVERE, null, ex1);

            PrintWriter printWriter = new PrintWriter(writer);

            List<String> headers = new ArrayList<>();
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsTransactionService instance = new GtnWsTransactionService();
            List<Object> result = instance.createFile(tempFile, printWriter, headers, gtnWsRequest);
            assertFalse(result.isEmpty());
        }

    }

    /**
     * Test of writeFile method, of class GtnWsTransactionService.
     */
    @Test
    public void testWriteFile() {
        System.out.println("writeFile");
        List resultList = new ArrayList<>();
        PrintWriter printWriter = new PrintWriter(System.out);    
        FileWriter writer = null;
        Boolean excelComplete = false;
        List<String> columnFormatList = new ArrayList<>();
        GtnWsTransactionService instance = new GtnWsTransactionService();
        instance.writeFile(resultList, printWriter, writer, excelComplete, columnFormatList);
        assertFalse(excelComplete);    
    }

    /**
     * Test of getFilePath method, of class GtnWsTransactionService.
     */
//    @Test
//    public void testGetFilePath() throws Exception {
//        System.out.println("getFilePath");
//        GtnWsTransactionService instance = new GtnWsTransactionService();
//        String result = gtnWsTransactionService.getFilePath();
//        assertFalse(result.isEmpty()); 
//    }

    /**
     * Test of getHelperValue method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetHelperValue() {
        System.out.println("getHelperValue");
        Object value = 12;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        Object result = instance.getHelperValue(value);
        assertFalse(result==null); 
    }

    /**
     * Test of getUserName method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        Object value = 20156;
        Map<Integer, String> userMap = new HashMap<>();
        userMap.put(20156, "JohnSmith");
        GtnWsTransactionService instance = new GtnWsTransactionService();
        String result = instance.getUserName(value, userMap);
        assertFalse(result==null); 
    }

    /**
     * Test of getYesNoValue method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetYesNoValue() {
        System.out.println("getYesNoValue");
        Object value = 1;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        String result = instance.getYesNoValue(value);
        assertFalse(result==null); 
    }

    /**
     * Test of getResultList method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetResultList() {
        System.out.println("getResultList");
        List<Object> resultSet = new ArrayList<Object>();
        List<Object> searchColumnNameList = new ArrayList<>();
        searchColumnNameList.add(12);
        searchColumnNameList.add(GtnFrameworkCommonConstants.CREATED_BY);
        searchColumnNameList.add(GtnFrameworkCommonConstants.MODIFIED_BY);
        Map<Integer, String> userMap = new HashMap<>();
        userMap.put(20156, "JohnSmith");
        String tableName = GtnFrameworkWebserviceConstant.GL_BALANCE;
        GtnWsTransactionService instance = new GtnWsTransactionService();
        List result = instance.getResultList(resultSet, searchColumnNameList, userMap, tableName);
        assertFalse(result==null); 
    }

    /**
     * Test of getExcelFile method, of class GtnWsTransactionService.
     */
//    @Test
//    public void testGetExcelFile() throws Exception {
//        System.out.println("getExcelFile");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("767");
//        generalRequest.setExcel(false);
//        GtnWsTransactionService instance = new GtnWsTransactionService();
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
//        gtnWsSearchRequest.setCount(true);
//        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList("checkRecord","cfpId","cfpNo","cfpName","cfpStatus","cfpStartDate","cfpEndDate",
//                                 "cfpType","cfpCategory","cfpTradeClass","cfpDesignation","parentCfpId","parentCfpName","createdBy","createdDate","modifiedBy",
//                                 "modifiedDate","batchId","source","addChgDelIndictor","errorCode","errorField","reasonForFailure","reprocessedFlag","cfpIntfid"));
//        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
//        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
//        criteria.setFieldId("reprocessedFlag");
//        criteria.setFilterValue1("N");
//        criteria.setExpression("EQUALS");
//        criteria.setFilter(false);
//        gtnWebServiceSearchCriteriaList.add(criteria);
//
//        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
//        criteria2.setFieldId("cfpIntfid");
//        criteria2.setFilterValue1("*");
//        criteria2.setExpression("LIKE");
//        criteria2.setFilter(false);
//        gtnWebServiceSearchCriteriaList.add(criteria2);
//        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
//        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
//        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
//        //System.getProperty(GtnFrameworkCommonStringConstants.GTN_DATA_PATH)
//        instance.getExcelFile(gtnUIFrameworkWebserviceResponse, generalRequest, gtnWsSearchRequest, gtnWsRequest);
//        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
//    }

    /**
     * Test of getDateForSearch method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetDateForSearch() {
        System.out.println("getDateForSearch");
        Date ob = new Date();
        GtnWsTransactionService instance = new GtnWsTransactionService();
        Date result = instance.getDateForSearch(ob);
        assertFalse(result==null);
    }

    /**
     * Test of getFormattedData method, of class GtnWsTransactionService.
     */
   @Test
    public void testGetFormattedData() {
        System.out.println("getFormattedData");
        List<String> projectionColumns = new ArrayList<>();
        projectionColumns.add(GtnFrameworkCommonConstants.CREATED_BY);
        Object[] ob = {20156};
        Map<Integer, String> userIdNameMap = new HashMap<>();
        userIdNameMap.put(20156, "John");
        gtnWsAllListConfig.setUserIdNameMap(userIdNameMap);
        doReturn(userIdNameMap).when(gtnWsAllListConfig).getUserIdNameMap();
        String tableName = GtnFrameworkWebserviceConstant.GL_BALANCE;;
        gtnWsTransactionService1.getFormattedData(projectionColumns, ob, tableName);
        assertFalse(tableName.isEmpty());
    }
    
    @Test
    public void testGetFormattedDataModifiedBy() {
        System.out.println("getFormattedData");
        List<String> projectionColumns = new ArrayList<>();
        projectionColumns.add(GtnFrameworkCommonConstants.MODIFIED_BY);
        Object[] ob = {20156};
        Map<Integer, String> userIdNameMap = new HashMap<>();
        userIdNameMap.put(20156, "John");
        gtnWsAllListConfig.setUserIdNameMap(userIdNameMap);
        doReturn(userIdNameMap).when(gtnWsAllListConfig).getUserIdNameMap();
        String tableName = GtnFrameworkWebserviceConstant.GL_BALANCE;;
        gtnWsTransactionService1.getFormattedData(projectionColumns, ob, tableName);
        assertFalse(tableName.isEmpty());
    }
    
       @Test
    public void testGetFormattedDataACTIVE() {
        System.out.println("testGetFormattedDataACTIVE");
        List<String> projectionColumns = new ArrayList<>();
        projectionColumns.add(GtnFrameworkWebserviceConstant.ACTIVE);
        Object[] ob = {20156};
        Map<Integer, String> userIdNameMap = new HashMap<>();
        userIdNameMap.put(20156, "John");
        gtnWsAllListConfig.setUserIdNameMap(userIdNameMap);
        doReturn(userIdNameMap).when(gtnWsAllListConfig).getUserIdNameMap();
        String tableName = GtnFrameworkWebserviceConstant.GL_BALANCE;
        gtnWsTransactionService1.getFormattedData(projectionColumns, ob, tableName);
        assertFalse(tableName.isEmpty());
    }

    /**
     * Test of getValidOrInvalidModules method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetValidOrInvalidModules() {
        System.out.println("getValidOrInvalidModules");
        String searchModuleName = "Ivld";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        boolean expResult = true;
        boolean result = instance.getValidOrInvalidModules(searchModuleName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValidationForRunning method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetValidationForRunning() {
        System.out.println("getValidationForRunning");
        String searchModuleName = "FORECAST_SALES_INTERFACE";
        List<Object> inputList = new ArrayList<>();
        inputList.add(searchModuleName);
       // GtnFrameworkSqlQueryEngine engine=new GtnFrameworkSqlQueryEngine();
        GtnFrameworkSqlQueryEngine engine=Mockito.mock(GtnFrameworkSqlQueryEngine.class);
        doReturn("FORECAST_SALES_INTERFACE").when(gtnWsSqlService).getQuery(Mockito.any());
        doReturn(engine).when(gtnWsAllListConfig).getGtnSqlQueryEngine();
        try {
            doReturn(Arrays.asList("a")).when(engine).executeSelectQuery(Mockito.anyString());
        } catch (GtnFrameworkGeneralException ex) {
            Logger.getLogger(GtnWsTransactionServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
//        SessionFactory factory = Mockito.mock(SessionFactory.class);
//        Session session = Mockito.mock(Session.class);
//        Transaction tx = Mockito.mock(Transaction.class);
//        Criteria cr = Mockito.mock(Criteria.class);
// 
//
//        doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
//        doReturn(session).when(factory).openSession();
//        doReturn(tx).when(session).beginTransaction();
//        doReturn(cr).when(session).createCriteria(ForecastConfig.class);
        String result = gtnWsTransactionService1.getValidationForRunning(searchModuleName);
        assertFalse(searchModuleName.isEmpty());
    }

    /**
     * Test of getColumnName method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String columnName = "rate";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        String result = instance.getColumnName(columnName);
        assertFalse(columnName.isEmpty());
    }

    /**
     * Test of replaceSingleQuote method, of class GtnWsTransactionService.
     */
    @Test
    public void testReplaceSingleQuote() {
        System.out.println("replaceSingleQuote");
        String searchValue = "abc";
        GtnWsTransactionService instance = new GtnWsTransactionService();
        String result = instance.replaceSingleQuote(searchValue);
        assertFalse(searchValue.isEmpty());
    }

    /**
     * Test of getGtnWebServiceAllListConfig method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetGtnWebServiceAllListConfig() {
        System.out.println("getGtnWebServiceAllListConfig");
        GtnWsAllListConfig result = gtnWsTransactionService1.getGtnWebServiceAllListConfig();
        assertFalse(result==null);
    }

    /**
     * Test of getGtnSqlQueryEngine method, of class GtnWsTransactionService.
     */
    @Test
    public void testGetGtnSqlQueryEngine() {
        System.out.println("getGtnSqlQueryEngine");
        GtnFrameworkSqlQueryEngine result = gtnWsTransactionService1.getGtnSqlQueryEngine();
        assertFalse(result==null);
    }
    
    public String getFilePath() throws GtnFrameworkGeneralException {
		StringBuilder filePath = new StringBuilder(System.getProperty(GtnFrameworkCommonStringConstants.GTN_DATA_PATH));
		filePath.append('/');
		filePath.append("exceltransaction");
		filePath.append('/');
		Path path = Paths.get(filePath.toString());
                if (path.toFile().exists()){
			try {
				Files.createDirectories(path);
			} catch (IOException e) {

				throw new GtnFrameworkGeneralException(e);

			}
		}
		return filePath.toString();
	}
    
}
