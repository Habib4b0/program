/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
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
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
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
public class GtnWsTransactionTableDetailTest {
    
    @Mock
    @Autowired
    private GtnWsAllListConfig gtnWsAllListConfig;
    
    @Mock
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
     @Mock
    @Autowired
    private SessionFactory sessionFactory;
    
    public GtnWsTransactionTableDetailTest() {
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

    
    @InjectMocks
    @Autowired
    private GtnWsTransactionTableDetail gtnWsTransactionTableDetail;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}     

    
    /**
     * Test of getColumnDataType method, of class GtnWsTransactionTableDetail.
     */
    @Test
    public void testGetColumnDataType() throws Exception {
        System.out.println("getColumnDataType");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("391");
        generalRequest.setModuleName("VwAdjustDemandForecastAct");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        doReturn(GtnWsTransactionTableDetail.class).when(gtnWsAllListConfig).getTransctionDynamicClass(Mockito.anyString());
        GtnUIFrameworkWebserviceResponse result = gtnWsTransactionTableDetail.getColumnDataType(gtnWsRequest);
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
}
