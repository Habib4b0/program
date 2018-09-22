/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.service.GtnWsTransactionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class GtnWsTransactionSearchControllerTest {
    
    public GtnWsTransactionSearchControllerTest() {
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
    private GtnWsTransactionSearchController gtnWsTransactionSearchController;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
     @Autowired
     private GtnWsTransactionService gtnWsTransactionService;
    
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
     * Test of getSearchResults method, of class GtnWsTransactionSearchController.
     */
    @Test
    public void testGetSearchResults() {
        System.out.println("getSearchResults");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsSearchRequest gtnWsSearchRequest=new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        gtnWsSearchRequest.isCount();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList("checkRecord","cfpId","cfpNo","cfpName","cfpStatus","cfpStartDate","cfpEndDate",
                                 "cfpType","cfpCategory","cfpTradeClass","cfpDesignation","parentCfpId","parentCfpName","createdBy","createdDate","modifiedBy",
                                 "modifiedDate","batchId","source","addChgDelIndictor","errorCode","errorField","reasonForFailure","reprocessedFlag","cfpIntfid"));
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("reprocessedFlag");
        criteria.setFilterValue1("N");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("cfpIntfid");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsTransactionSearchController.getSearchResults(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getViewResults method, of class GtnWsTransactionSearchController.
     */
    @Test
    public void testGetViewResults() {
        System.out.println("getViewResults");
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
        gtnWsTransactionSearchController.getViewResults(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
        @Test
    public void testGetViewResultsFalse() {
        System.out.println("getViewResults");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("410");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        //gtnWsTransactionRequest.setTableName("VwAdjustDemandForecastAct");
        gtnWsTransactionRequest.setProjectionColumns(Arrays.asList("forecastType","itemId","itemName","brandId","brandName","segment","marketSizeUnits","marketShareRatio",
                        "marketShareUnits","grossUnits","grossPrice","grossAmount","netSalesPrice","netSalesAmount","forecastYear","forecastMonth","totalDemandUnits","totalDemandAmount",
                        "batchId","source","country","businessUnitNo","businessUnitName"));
        gtnWsTransactionRequest.setHelpercomponentList(Arrays.asList("adjustedDemandForecastId","forecastTypeSid","organizationKey"));
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        gtnWsTransactionSearchController.getViewResults(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
    

    /**
     * Test of getValidationResults method, of class GtnWsTransactionSearchController.
     */
    @Test
    public void testGetValidationResults() {
        System.out.println("getValidationResults");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("596");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("COMPANY_FAMILY_PLAN_INTERFACE");
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        gtnWsTransactionSearchController.getValidationResults(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
    
}
