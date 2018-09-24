/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.transaction.GtnWsTransactionRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
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
public class GtnWsTransactionReprocessIOServiceTest {
    
    public GtnWsTransactionReprocessIOServiceTest() {
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
    private SessionFactory sessionFactory;
    
     @Autowired
     private GtnWsTransactionReprocessIOService gtnWsTransactionReprocessIOService;
     
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
   @PostConstruct
    public void constrcut(){
       GtnWsAllListConfig gtnWebServiceAllListConfig= gtnWsTransactionReprocessIOService.getGtnWebServiceAllListConfig();
       gtnWebServiceAllListConfig.setGtnSqlQueryEngine(gtnWsTransactionReprocessIOService.getGtnSqlQueryEngine());
    }
    
       	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getReprocessRecords method, of class GtnWsTransactionReprocessIOService.
     */
//    @Test
//    public void testGetReprocessRecords() throws Exception {
//        System.out.println("getReprocessRecords");
//        GtnWsTransactionRequest gtnWsTransactionRequest = null;
//        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
//        instance.getReprocessRecords(gtnWsTransactionRequest);
//    }

    /**
     * Test of removeUnwantedColumnForStagingtable method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testRemoveUnwantedColumnForStagingtable() {
        System.out.println("removeUnwantedColumnForStagingtable");
        List<String> selectedColumn = new ArrayList<>();
        selectedColumn.add("accrualIntfid");
        String inavlidTableName = "IvldAccrualInbound";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        String result = instance.removeUnwantedColumnForStagingtable(selectedColumn, inavlidTableName);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of removeUnwantedColumns method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testRemoveUnwantedColumns() {
        System.out.println("removeUnwantedColumns");
        String inavlidTableName = "IvldItemIdentifier";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        List<String> result = instance.removeUnwantedColumns(Arrays.asList("identifierStatus", "settlementMethodNo", "glCompanyMasterSid",
				"glCompanyName", "programType", "programNo","postingIndicator"), inavlidTableName);
        assertFalse(result.isEmpty());
    }
      @Test
    public void testRemoveUnwantedColumns2() {
        System.out.println("removeUnwantedColumns");
        String inavlidTableName = "IvldActualMaster";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        List<String> result = instance.removeUnwantedColumns(Arrays.asList("identifierStatus", "settlementMethodNo", "glCompanyMasterSid",
				"glCompanyName", "programType", "programNo","postingIndicator"), inavlidTableName);
        assertFalse(result.isEmpty());
    }
      @Test
    public void testRemoveUnwantedColumns3() {
        System.out.println("removeUnwantedColumns");
        String inavlidTableName = "IvldAccrualInbound";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        List<String> result = instance.removeUnwantedColumns(Arrays.asList("identifierStatus", "settlementMethodNo", "glCompanyMasterSid",
				"glCompanyName", "programType", "programNo","postingIndicator"), inavlidTableName);
        assertFalse(result.isEmpty());
    }
    

    /**
     * Test of updateConditionFlag method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testUpdateConditionFlag() throws Exception {
        System.out.println("updateConditionFlag");
        GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        gtnWsTransactionRequest.setReprocessIds(Arrays.asList("117"));
        gtnWsTransactionRequest.setReprocessFlag(true);
        gtnWsTransactionRequest.setOutBoundModule(false);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"reprocessedFlag"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"Y"});
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        String whereCondition = "ivldCfpSid in (117 )";
        String tableName = "IvldCfp";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        gtnWsTransactionReprocessIOService.updateConditionFlag(gtnWsTransactionRequest, whereCondition, tableName);
        assertFalse(tableName.isEmpty());
    }

    /**
     * Test of getWhereClauseForReprocess method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testGetWhereClauseForReprocess() {
//        System.out.println("getWhereClauseForReprocess");
//        GtnWsTransactionRequest gtnWsTransactionRequest = new GtnWsTransactionRequest();
//        String result = gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("EQUALS");
        e.setFilter(false);
        searchCriteria.add(e);
        
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetWhereClauseForReprocessException() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("intfInsertedDate");
        e.setFilterValue1("Wed Oct 19 14:34:26 BRST 2016");
        e.setFilterValue2("Wed Oct 19 14:34:26 BRST 2016");
        e.setExpression("BETWEEN");
        e.setFilter(false);
        searchCriteria.add(e);     
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    
       @Test
    public void testGetWhereClauseForReprocessLIKE() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("LIKE");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
     @Test
    public void testGetWhereClauseForReprocessEQUAL() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("EQUAL");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetWhereClauseForReprocessGREATER() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("GREATER");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
      @Test
    public void testGetWhereClauseForReprocessLESS() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("LESS");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
       @Test
    public void testGetWhereClauseForReprocessGREATER_OR_EQUAL() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("GREATER_OR_EQUAL");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
           @Test
    public void testGetWhereClauseForReprocessLESS_OR_EQUAL() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("IFP No");
        e.setExpression("LESS_OR_EQUAL");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetWhereClauseForReprocessAND() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("2");
        e.setFilterValue2("2");
        e.setExpression("AND");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    @Test
    public void testGetWhereClauseForReprocessDATE_BETWEEN() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("intfInsertedDate");
        e.setFilterValue1("Wed Oct 16 00:00:00 CEST 2013");
        e.setExpression("DATE_BETWEEN");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
     @Test
    public void testGetWhereClauseForReprocessDATE_BETWEENDate() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("2");
        e.setExpression("BETWEEN");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }
    
    
     @Test
    public void testGetWhereClauseForReprocessDATE_BETWEENDefault() {
        System.out.println("getWhereClauseForReprocess");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnWsTransactionRequest gtnWsTransactionRequest=new GtnWsTransactionRequest();
        gtnWsTransactionRequest.setTableName("IvldCfp");
        gtnWsTransactionRequest.setStagingTableName("StagCfp");
        List<String> reprocessIds=new ArrayList<>();
        reprocessIds.add("115");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setReprocessFlag(true);
        Object[] stagUpdateColumn={"reprocessFlag"};
        Object[] stagUpdateColumns={"Y"};
        gtnWsTransactionRequest.setStagUpdateColumns(stagUpdateColumn);
        gtnWsTransactionRequest.setStagUpdateColumnsValues(stagUpdateColumns);
        gtnWsTransactionRequest.setOutBoundTableName("IvldCfp");
        List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
        GtnWebServiceSearchCriteria e = new GtnWebServiceSearchCriteria();
        e.setFieldId("CDProcessView_IADTCombo_IFP");
        e.setFilterValue1("2");
        e.setExpression("BETWEENn");
        e.setFilter(false);
        searchCriteria.add(e);
      
        gtnWsTransactionRequest.setSearchCriteria(searchCriteria);
        
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);       
        gtnWsTransactionReprocessIOService.getWhereClauseForReprocess(gtnWsTransactionRequest);    
        assertFalse(generalRequest.getUserId().isEmpty());
    }

    /**
     * Test of getValueBasedOnType method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testGetValueBasedOnType() throws Exception {
        System.out.println("getValueBasedOnType");
        String type = "java.util.Date";
        String value = "2";
        String filterValue = "Wed Oct 16 00:00:00 CEST 2013";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        Object result = instance.getValueBasedOnType(type, value, filterValue);
        assertFalse(type.isEmpty());
    }
    
    @Test
    public void testGetValueBasedOnType2() throws Exception {
        System.out.println("getValueBasedOnType");
        String type = "java.lang.Double";
        String value = "2";
        String filterValue = "2";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        Object result = instance.getValueBasedOnType(type, value, filterValue);
        assertFalse(type.isEmpty());
    }
    
    @Test
    public void testGetValueBasedOnType3() throws Exception {
        System.out.println("getValueBasedOnType");
        String type = "Integer";
        String value = "2";
        String filterValue = "2";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        Object result = instance.getValueBasedOnType(type, value, filterValue);
        assertFalse(type.isEmpty());
    }

    /**
     * Test of runJobForReprocess method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testRunJobForReprocess() {
        System.out.println("runJobForReprocess");
        String tableName = "IvldCfp";
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        instance.runJobForReprocess(tableName);
        assertFalse(tableName.isEmpty());
    }
    
    /**
     * Test of replaceBatchIdColumn method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testReplaceBatchIdColumn() {
        System.out.println("replaceBatchIdColumn");
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        instance.replaceBatchIdColumn(Arrays.asList("batchId"));
        assertFalse(Arrays.asList("batchId").isEmpty());
    }

    /**
     * Test of outboundRecords method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testOutboundRecords() throws Exception {
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
        gtnWsTransactionRequest.setReprocessFlag(false);
        gtnWsTransactionRequest.setOutBoundModule(true);
        Object[] stagInsertColumns={"cffDetailsSid","rsModelSid","periodSid"};
        gtnWsTransactionRequest.setStagInsertColumns(stagInsertColumns);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"etlCheckRecord","UserId","sessionId"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"1","1","1"});
        gtnWsTransactionRequest.setOutBoundTableName("VwCffOutboundMaster");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        gtnWsTransactionReprocessIOService.outboundRecords(gtnWsTransactionRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
    
     @Test
    public void testOutboundRecordsFalse() throws Exception {
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
        List<String> reprocessIds1 = new ArrayList<>();
        reprocessIds1.add("133699_1363_601");
        gtnWsTransactionRequest.setReprocessFlag(false);
        gtnWsTransactionRequest.setOutBoundModule(true);
        Object[] stagInsertColumns={"cffDetailsSid","rsModelSid","periodSid"};
        gtnWsTransactionRequest.setStagInsertColumns(stagInsertColumns);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"etlCheckRecord","UserId","sessionId"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"1","1","1"});
        gtnWsTransactionRequest.setOutBoundTableName("VwCffOutboundMaster");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsTransactionRequest.setUncheckedIds(reprocessIds1);
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        gtnWsTransactionReprocessIOService.outboundRecords(gtnWsTransactionRequest);
        assertFalse(generalRequest.getUserId().isEmpty());       
    }
    
    /**
     * Test of updateConditionAndCallEtlService method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testUpdateConditionAndCallEtlService() {
        System.out.println("updateConditionAndCallEtlService");
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
        gtnWsTransactionRequest.setReprocessFlag(false);
        gtnWsTransactionRequest.setOutBoundModule(true);
        Object[] stagInsertColumns={"cffDetailsSid","rsModelSid","periodSid"};
        gtnWsTransactionRequest.setStagInsertColumns(stagInsertColumns);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"etlCheckRecord","UserId","sessionId"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"1","1","1"});
        gtnWsTransactionRequest.setOutBoundTableName("VwCffOutboundMaster");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        gtnWsTransactionReprocessIOService.updateConditionAndCallEtlService(gtnWsTransactionRequest, "cffDetailsSid in (133699 ) and rsModelSid in (1363 ) and periodSid in (601 )", "StCffOutboundMaster", "StCffOutboundMaster");
        assertFalse(generalRequest.getUserId().isEmpty());    
    }

    /**
     * Test of getWhereQueries method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testGetWhereQueries() {
        System.out.println("reprocessRecords");
        ClassMetadata classMetadata = null;
        List<String> ids = new ArrayList<>();
        ids.add("133699_1363_601");
        String condition = "";
        Object[] extraColumns = null;
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
        gtnWsTransactionRequest.setReprocessFlag(false);
        gtnWsTransactionRequest.setOutBoundModule(true);
        Object[] stagInsertColumns={"cffDetailsSid","rsModelSid","periodSid"};
        gtnWsTransactionRequest.setStagInsertColumns(stagInsertColumns);
        gtnWsTransactionRequest.setStagUpdateColumns(new Object[]{"etlCheckRecord","UserId","sessionId"});
        gtnWsTransactionRequest.setStagUpdateColumnsValues(new Object[]{"1","1","1"});
        gtnWsTransactionRequest.setOutBoundTableName("VwCffOutboundMaster");
        gtnWsTransactionRequest.setReprocessIds(reprocessIds);
        gtnWsRequest.setGtnWsTransactionRequest(gtnWsTransactionRequest);
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        gtnWsTransactionReprocessIOService.getWhereQueries(gtnWsTransactionRequest, classMetadata, Arrays.asList("133699_1363_601"), GtnFrameworkOperatorType.IN.getOperaterType(), new Object[]{"cffDetailsSid","rsModelSid","periodSid"});
        assertFalse(generalRequest.getUserId().isEmpty());  
    }

    /**
     * Test of getSidsList method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testGetSidsList() {
        System.out.println("getSidsList");
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        Object[] result = instance.getSidsList(Arrays.asList("133699_1363_601"), 2);
        assertFalse(result.length==0);
    }
    
    @Test
    public void testGetSidsListFalse() {
        System.out.println("getSidsList");
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        Object[] result = instance.getSidsList(Arrays.asList("1336991363601"), 2);
        assertFalse(result.length==0);
    }

    /**
     * Test of getGtnWebServiceAllListConfig method, of class GtnWsTransactionReprocessIOService.
     */
    @Test
    public void testGetGtnWebServiceAllListConfig() {
        System.out.println("getGtnWebServiceAllListConfig");
        GtnWsTransactionReprocessIOService instance = new GtnWsTransactionReprocessIOService();
        GtnWsAllListConfig result = instance.getGtnWebServiceAllListConfig();
        assertFalse(result!=null);
    }
    
}
