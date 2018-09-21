/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.module.itemgroups.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemgroup.ItemGroup;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpDataBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.itemgroup.GtnWsItemGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsItemGrpServiceTest {
    @InjectMocks
    @Autowired
	GtnWsItemGrpService instance;
    @Mock
    @Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    @Mock
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

    public GtnWsItemGrpServiceTest() {
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

    /**
     * Test of getSessionFactory method, of class GtnWsItemGrpService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        
       
        SessionFactory result = instance.getSessionFactory();
        assertNotNull( result);
    }

  
     @Test
     public void testAddItemQuery() throws Exception {
        System.out.println("addItemQuery");
       GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
       int expResult = 0;
       int result = instance.addItemQuery(gtnWsRequest);
        assertEquals(expResult, result);
       
     }

	private GtnUIFrameworkWebserviceRequest getIGReq() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		   
		   GtnWsItemGroupRequest ws=new GtnWsItemGroupRequest();
		   gtnWsRequest.setGtnWsItemGroupRequest(ws);
		  
		   List<GtnWsItemGrpDataBean> gtnWsItemGrpDataBeanList=new ArrayList<>();
		   GtnWsItemGrpDataBean bean=new GtnWsItemGrpDataBean();
		   gtnWsItemGrpDataBeanList.add(bean);
		   GtnWsItemGroupBean gtnWsItemGroupBean=new GtnWsItemGroupBean();
		   
		   GtnItemGrpInformationBean itemGrpInfoBean=new GtnItemGrpInformationBean();
		   itemGrpInfoBean.setItemGrpSid(0);
		   gtnWsItemGroupBean.setItemGrpInfoBean(itemGrpInfoBean);
		   ws.setGtnWsItemGroupBean(gtnWsItemGroupBean);
		   
		   GtnWsGeneralRequest generalReq=new GtnWsGeneralRequest();
		   generalReq.setUserId("12");
		   generalReq.setSessionId("sessionid");
		   ws.setGtnWsItemGrpDataBeanList(gtnWsItemGrpDataBeanList);
		   gtnWsRequest.setGtnWsGeneralRequest(generalReq);
		return gtnWsRequest;
	}
     
     @Test(expected=Exception.class)
     public void testAddItemQueryException() throws Exception {
        System.out.println("addItemQuery");
       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        instance.addItemQuery(gtnWsRequest);
       
     }

     /**
      * Test of getAddAllQuery method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testGetAddAllQuery() throws Exception {
         System.out.println("getAddAllQuery");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
        
         int expResult = 0;
         int result = instance.getAddAllQuery(gtnWsRequest);
         assertEquals(expResult, result);
       
     }

     /**
      * Test of getRemoveQuery method, of class GtnWsItemGrpService.
      */
     @Test
     public void testGetRemoveQuery() throws Exception {
         System.out.println("getRemoveQuery");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
        
         int expResult = 0;
         int result = instance.getRemoveQuery(gtnWsRequest);
         assertEquals(expResult, result);
       
     }
     /**
      * Test of getRemoveQuery method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testGetRemoveQueryException() throws Exception {
         System.out.println("getRemoveQuery");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
        
         int expResult = 0;
         int result = instance.getRemoveQuery(gtnWsRequest);
         assertEquals(expResult, result);
       
     }
     /**
      * Test of getRemoveAllQuery method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testGetRemoveAllQuery() throws Exception {
         System.out.println("getRemoveAllQuery");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
        
         int expResult = 0;
         int result = instance.getRemoveAllQuery(gtnWsRequest);
         assertEquals(expResult, result);
       
     }

     /**
      * Test of getItemGrpFetch method, of class GtnWsItemGrpService.
      */
     @Test
     public void testGetItemGrpFetch() throws Exception {
         System.out.println("getItemGrpFetch");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
     	Session s = Mockito.mock(Session.class);
    	Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		ItemGroup itemGroup =new ItemGroup();
		itemGroup.setCompanyMaster(new CompanyMaster());
		doReturn(itemGroup).when(s).get(ItemGroup.class,0);
		
         instance.getItemGrpFetch(gtnWsRequest, response);
     }

     /**
      * Test of getItemGrpFetch method, of class GtnWsItemGrpService.
      */
     @Test(expected=NullPointerException.class)
     public void testGetItemGrpFetchException() throws Exception {
         System.out.println("getItemGrpFetch");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
         instance.getItemGrpFetch(gtnWsRequest, null);
     }

     /**
      * Test of updateItemGrpDetails method, of class GtnWsItemGrpService.
      */
     @Test
     public void testUpdateItemGrpDetails() throws Exception {
         System.out.println("updateItemGrpDetails");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         int result = instance.updateItemGrpDetails(gtnWsRequest);
     }

     /**
      * Test of updateItemGroupDetails method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testUpdateItemGroupDetails() throws Exception {
         System.out.println("updateItemGroupDetails");
         GtnUIFrameworkWebserviceRequest gtnRequest =  getIGReq();
         GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
         int result = instance.updateItemGroupDetails(gtnRequest, response);
     }

     /**
      * Test of getItemGrpDeleteQuery method, of class GtnWsItemGrpService.
      */
     @Test
     public void testGetItemGrpDeleteQuery() throws Exception {
         System.out.println("getItemGrpDeleteQuery");
         GtnUIFrameworkWebserviceRequest gtnRequest =  getIGReq();
         int result = instance.getItemGrpDeleteQuery(gtnRequest);
     }

     /**
      * Test of saveItemGrpQuery method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testSaveItemGrpQuery() throws Exception {
         System.out.println("saveItemGrpQuery");
         GtnWsItemGroupBean bean = new GtnWsItemGroupBean();
         
         GtnItemGrpInformationBean info=new GtnItemGrpInformationBean();
         info.setCompanyMasterSid(0);
         bean.setItemGrpInfoBean(info);
         
      	Session s = Mockito.mock(Session.class);
    	Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		CompanyMaster itemGroup =new CompanyMaster();
		doReturn(1).when(s).save(Mockito.any(ItemGroup.class));
		
		doReturn(itemGroup).when(s).load(CompanyMaster.class, info.getCompanyMasterSid());
		
         int result = instance.saveItemGrpQuery(bean);
     }

     /**
      * Test of updateItemGrpQuery method, of class GtnWsItemGrpService.
      */
     @Test
     public void testUpdateItemGrpQuery() throws Exception {
         System.out.println("updateItemGrpQuery");
         GtnWsItemGroupBean bean = new GtnWsItemGroupBean();
         
         GtnItemGrpInformationBean info=new GtnItemGrpInformationBean();
         info.setItemGrpSid(0);
         bean.setItemGrpInfoBean(info);
         
      	Session s = Mockito.mock(Session.class);
    	Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		ItemGroup itemGroup =new ItemGroup();
		doReturn(1).when(s).save(Mockito.any(ItemGroup.class));
		
		doReturn(itemGroup).when(s).load(ItemGroup.class, info.getItemGrpSid());
		
         instance.updateItemGrpQuery(bean);
     }

     /**
      * Test of updateItemGrpDetailsTable method, of class GtnWsItemGrpService.
      */
     @Test
     public void testUpdateItemGrpDetailsTable() throws Exception {
         System.out.println("updateItemGrpDetailsTable");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
      
         int result = instance.updateItemGrpDetailsTable(gtnWsRequest, 0, gtnWsRequest.getGtnWsItemGroupRequest().getGtnWsItemGroupBean());
     }

     /**
      * Test of getItemGroupImtdCount method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testGetItemGroupImtdCount() throws Exception {
         System.out.println("getItemGroupImtdCount");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         int result = instance.getItemGroupImtdCount(gtnWsRequest);
     }

     /**
      * Test of getSelectedTableData method, of class GtnWsItemGrpService.
      */
     @Test
     public void testGetSelectedTableData() throws Exception {
         System.out.println("getSelectedTableData");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
         gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
         GtnWsSearchQueryConfig mainClauseConfig = new GtnWsSearchQueryConfig();
         
         List result = instance.getSelectedTableData(gtnWsRequest, mainClauseConfig);
     }
     /**
      * Test of getSelectedTableData method, of class GtnWsItemGrpService.
      */
     @Test(expected=Exception.class)
     public void testGetSelectedTableDataException() throws Exception {
         System.out.println("getSelectedTableData");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         GtnWsSearchQueryConfig mainClauseConfig = new GtnWsSearchQueryConfig();
         
         List result = instance.getSelectedTableData(gtnWsRequest, mainClauseConfig);
     }

//     /**
//      * Test of getCustomizedSearchFormFromObject method, of class
//      * GtnWsItemGrpService.
//      */
//     @Test
//     public void testGetCustomizedSearchFormFromObject() {
//         System.out.println("getCustomizedSearchFormFromObject");
//         List list = null;
//         GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = null;
//         List<Object> columnName = null;
//         instance.getCustomizedSearchFormFromObject(list, gtnWebServiceSearchQueryContext, columnName);
//     }

     /**
      * Test of getDoubleValue method, of class GtnWsItemGrpService.
      */
     @Test
     public void testGetDoubleValue() {
         System.out.println("getDoubleValue");
         BigDecimal numericColumn = null;
         Object expResult = null;
         Object result = instance.getDoubleValue(numericColumn);
         assertEquals(expResult, result);
          numericColumn = BigDecimal.ONE;
          result = instance.getDoubleValue(numericColumn);
          assertEquals(1.0, result);
     }

     /**
      * Test of getHelperValue method, of class GtnWsItemGrpService.
      */
     @Test
     public void testGetHelperValue() {
         System.out.println("getHelperValue");
        
         Map<Integer, String> helperMap = null;
         Object value = null;
         Object expResult = "";
         Object result = instance.getHelperValue(value, helperMap);
         assertEquals(expResult, result);
         
          value = "";
          result = instance.getHelperValue(value, helperMap);
          assertEquals(expResult, result);
          
          value = 0;
          result = instance.getHelperValue(value, helperMap);
          assertEquals(expResult, result);
          
          value = 1;
          helperMap=new HashMap<>();
          helperMap.put(1, "");
          
          result = instance.getHelperValue(value, helperMap);
     }

     /**
      * Test of getItemGrpNameDuplicateValidationService method, of class
      * GtnWsItemGrpService.
      */
     @Test
     public void testGetItemGrpNameDuplicateValidationService() throws Exception {
         System.out.println("getItemGrpNameDuplicateValidationService");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         List<Object> result = instance.getItemGrpNameDuplicateValidationService(gtnWsRequest);
     }

     /**
      * Test of clearItemGroupTempTable method, of class GtnWsItemGrpService.
      */
     @Test
     public void testClearItemGroupTempTable() throws Exception {
         System.out.println("clearItemGroupTempTable");
         GtnUIFrameworkWebserviceRequest gtnWsRequest = getIGReq();
         instance.clearItemGroupTempTable(gtnWsRequest);
     }

}
