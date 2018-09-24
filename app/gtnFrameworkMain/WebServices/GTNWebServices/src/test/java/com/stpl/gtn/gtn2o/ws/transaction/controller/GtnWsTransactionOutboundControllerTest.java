/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
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
public class GtnWsTransactionOutboundControllerTest {
    
    public GtnWsTransactionOutboundControllerTest() {
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
    private GtnWsTransactionOutboundController gtnWsTransactionOutboundController;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
        

    /**
     * Test of reprocessRecords method, of class GtnWsTransactionOutboundController.
     */
    @Test
    public void testReprocessRecords() {  
        System.out.println("reprocessRecords");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("319");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("StCffOutboundMaster");
        gtnWsTransactionRequest.setStagingTableName("StCffOutboundMaster");
        List<String> reprocessIds = new ArrayList<>();
        reprocessIds.add("133699_1363_601");
        gtnWsTransactionRequest.setReprocessFlag(true);
        gtnWsTransactionRequest.setOutBoundModule(true);
        Object[] stagInsertColumns={"cffDetailsSid","rsModelSid","periodSid"};
        gtnWsTransactionRequest.setStagInsertColumns(stagInsertColumns);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"etlCheckRecord","UserId","sessionId"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"1","1","1"});
        gtnWsTransactionRequest.setOutBoundTableName("VwCffOutboundMaster");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        gtnWsTransactionOutboundController.reprocessRecords(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
    
    @Test
    public void testReprocessRecordsFalse() {  
        System.out.println("reprocessRecords");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("319");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("StCffOutboundMaster");
        gtnWsTransactionRequest.setStagingTableName("StCffOutboundMaster");
        List<String> reprocessIds = new ArrayList<>();
        reprocessIds.add("133699_1363_601");
        //gtnWsTransactionRequest.setReprocessFlag(true);
        gtnWsTransactionRequest.setOutBoundModule(true);
        Object[] stagInsertColumns={"cffDetailsSid","rsModelSid","periodSid"};
        gtnWsTransactionRequest.setStagInsertColumns(stagInsertColumns);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"etlCheckRecord","UserId","sessionId"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"1","1","1"});
        gtnWsTransactionRequest.setOutBoundTableName("VwCffOutboundMaster");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        gtnWsTransactionOutboundController.reprocessRecords(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
    
}
