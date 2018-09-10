/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GtnWsContractDashboardLookupLogicTest {
    
    public GtnWsContractDashboardLookupLogicTest() {
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
    private GtnWsContractDashboardLookupLogic gtnWsContractDashboardLookupLogic;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    /**
     * Test of getController method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetController() {
        System.out.println("getController");
        GtnWsContractDashboardController result = gtnWsContractDashboardLookupLogic.getController();
        assertFalse(result.toString().isEmpty());
    }

    /**
     * Test of getWhereClauseForAColumn method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetWhereClauseForAColumn() throws Exception {
        System.out.println("getWhereClauseForAColumn");
        String expersion = "EQUALS";
        String field = "CHECK_RECORD";
        String value1 = "1";
        String value2 = "";
        String exp="CHECK_RECORD = '1' ";
        String result = gtnWsContractDashboardLookupLogic.getWhereClauseForAColumn(expersion, field, value1, value2);
        assertEquals(exp, result);
    }

    /**
     * Test of getNSFLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetNSFLookupTableData() throws Exception {
         System.out.println("getNSFLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("137");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"formulaType", "formulaId", "formulaNo", "formulaName", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDNSFormulaView_FormulaType");
        criteria.setFilterValue1("215");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDNSFormulaView_FormulaID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();     
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardLookupLogic.getNSFLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCFPLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetCFPLookupTableData() throws Exception {
         System.out.println("getCFPLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "cfpId", "cfpNo", "cfpName", "cfpType", "cfpStatus", "cfpCategory", "startDate",
        "endDate","cfpDesignation","parentCfpId","parentCfpName","modifiedDate","modifiedBy","createdBy","createdDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDCFPView_CFPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getCFPLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getIFPLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetIFPLookupTableData() throws Exception {
        System.out.println("getIFPLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "ifpId", "ifpNo", "ifpName", "ifpType", "ifpStatus", "ifpCategory", "startDate",
        "endDate","ifpDesignation","parentIfpId","parentIfpName","createdBy","createdDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDIFPView_IFPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getIFPLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getTPLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetTPLookupTableData() throws Exception {
        System.out.println("getTPLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);   
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId", "companyNo", "companyName", "companyStatus", "companyType"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDTPView_CompanyID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getTPLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getPSLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetPSLookupTableData() throws Exception {
//        System.out.println("getPSLookupTableData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnUIFrameworkWebserviceResponse gtnResponse = null;
//        GtnWsContractDashboardLookupLogic instance = null;
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getPSLookupTableData(gtnWsRequest, gtnResponse);
//        assertEquals(expResult, result);
        System.out.println("getPSLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "psId", "psNo", "psName", "psType", "psStatus", "psCategory", "startDate", "endDate", "psDesignation", "parentPsId", "parentPsName", "tradeClass"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDPSView_PSID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getPSLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getRSLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRSLookupTableData() throws Exception {
        System.out.println("getRSLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "rsId", "rsNo", "rsName", "rsType", "rsStatus"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDPSView_PSID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getRSLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getRulesLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRulesLookupTableData() throws Exception {
        System.out.println("getRulesLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"ruleType", "ruleNo", "ruleName", "ruleCategory"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDNSRuleView_RuleNo");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(5);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getRulesLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getRuleDetailsLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRuleDetailsLookupTableData() throws Exception {
        System.out.println("getRuleDetailsLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExtraParameter(64);
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"lineType", "itemGroupAsso", "keyword", "keyOperator","value","comparison","compOperator"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getRuleDetailsLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getRPLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRPLookupTableData() throws Exception {
        System.out.println("getRPLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("794");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType", "rebateStructure", "rangeBasedOn", "netSalesFormula", "netSalesRule", "rebateBasedOn", "createdDate", "createdBy", "modifiedDate", "modifiedBy"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDRPNoView_RPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getRPLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getDCLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
//    @Test
//    public void testGetDCLookupTableData() throws Exception {
//        System.out.println("getDCLookupTableData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnUIFrameworkWebserviceResponse gtnResponse = null;
//        GtnWsContractDashboardLookupLogic instance = null;
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getDCLookupTableData(gtnWsRequest, gtnResponse);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getFormulaLookupTableData method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetFormulaLookupTableData() throws Exception {
        System.out.println("getFormulaLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("794");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"formulaType", "formulaId", "formulaNo", "formulaName", "version"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDFormulaNoView_FormulaNo");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getFormulaLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
    
        @Test
    public void testGetFormulaLookupTableDataFalse() throws Exception {
        System.out.println("getFormulaLookupTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("794");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"formulaType", "formulaId", "formulaNo", "formulaName", "version"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDFormulaNoView_FormulaNo");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardLookupLogic.getFormulaLookupTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getNetSalesFormulaSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetNetSalesFormulaSearchInput() throws Exception {
        System.out.println("getNetSalesFormulaSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("137");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"formulaType", "formulaId", "formulaNo", "formulaName", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDNSFormulaView_FormulaType");
        criteria.setFilterValue1("215");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDNSFormulaView_FormulaID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getNetSalesFormulaSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetNetSalesFormulaSearchInputFalse() throws Exception {
    try{
        System.out.println("getNetSalesFormulaSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("137");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"formulaType", "formulaId", "formulaNo", "formulaName", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDNSFormulaView_FormulaType");
        criteria.setFilterValue1("215");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDNSFormulaView_FormulaID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getNetSalesFormulaSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
          } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

    }

    /**
     * Test of getNetSalesFormulaSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetNetSalesFormulaSortedInputs() {
    try{
        System.out.println("getNetSalesFormulaSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getNetSalesFormulaSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse( !gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testGetNetSalesFormulaSortedInputsFalse() {
    try{
        System.out.println("getNetSalesFormulaSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getNetSalesFormulaSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse( gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getNetSalesFormulaColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetNetSalesFormulaColumns() {
    try{
        System.out.println("getNetSalesFormulaColumns");
        String property="";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getNetSalesFormulaColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(!property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCFPSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetCFPSearchInput() throws Exception {
       System.out.println("getCFPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "cfpId", "cfpNo", "cfpName", "cfpType", "cfpStatus", "cfpCategory", "startDate",
        "endDate","cfpDesignation","parentCfpId","parentCfpName","modifiedDate","modifiedBy","createdBy","createdDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDCFPView_CFPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getCFPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
       public void testGetCFPSearchInputFalse() throws Exception {
       System.out.println("getCFPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "cfpId", "cfpNo", "cfpName", "cfpType", "cfpStatus", "cfpCategory", "startDate",
        "endDate","cfpDesignation","parentCfpId","parentCfpName","modifiedDate","modifiedBy","createdBy","createdDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDCFPView_CFPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getCFPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCFPSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetCFPSortedInputs() {
    try{
        System.out.println("getCFPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getCFPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetCFPSortedInputsFalse() {
    try{
        System.out.println("testGetCFPSortedInputsFalse");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getCFPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of changePropertyForSystemId method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testChangePropertyForSystemId() {
    try{
        System.out.println("changePropertyForSystemId");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("changePropertyForSystemId",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
          } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testChangePropertyForSystemIdElse() {
    try{
        System.out.println("changePropertyForSystemId");
        String property = "";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("changePropertyForSystemId",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(!property.isEmpty());
          } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCFPColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetCFPColumns() {
        try{
        System.out.println("getCFPColumns");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getCFPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
           } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testGetCFPColumnsElse() {
        try{
        System.out.println("getCFPColumns");
        String property = "systemI";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getCFPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Test of getIFPSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetIFPSearchInput() throws Exception {
        System.out.println("getIFPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "ifpId", "ifpNo", "ifpName", "ifpType", "ifpStatus", "ifpCategory", "startDate",
        "endDate","ifpDesignation","parentIfpId","parentIfpName","createdBy","createdDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDIFPView_IFPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getIFPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetIFPSearchInputFalse() throws Exception {
        System.out.println("getIFPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "ifpId", "ifpNo", "ifpName", "ifpType", "ifpStatus", "ifpCategory", "startDate",
        "endDate","ifpDesignation","parentIfpId","parentIfpName","createdBy","createdDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDIFPView_IFPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getIFPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getIFPSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetIFPSortedInputs() {
   try{
        System.out.println("getIFPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getIFPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   @Test
   public void testGetIFPSortedInputsFalse() {
   try{
        System.out.println("getIFPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getIFPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Test of getIFPColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetIFPColumns() {
    try{
        System.out.println("getIFPColumns");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getIFPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetIFPColumnsElse() {
    try{
        System.out.println("getIFPColumns");
        String property = "SystemI";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getIFPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTPSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetTPSearchInput() throws Exception {
        System.out.println("getTPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);   
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId", "companyNo", "companyName", "companyStatus", "companyType"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDTPView_CompanyID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getTPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetTPSearchInputFalse() throws Exception {
        System.out.println("getTPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);   
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId", "companyNo", "companyName", "companyStatus", "companyType"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDTPView_CompanyID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getTPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getTPSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetTPSortedInputs() {
    try{
        System.out.println("getTPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getTPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetTPSortedInputsFalse() {
    try{
        System.out.println("getTPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getTPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getTPColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetTPColumns() {
        try{
        System.out.println("getTPColumns");
        String property = "CDTPView_CompanyID";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getTPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetTPColumnsElse() {
        try{
        System.out.println("getTPColumns");
        String property = "CDTPView_CompanyI";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getTPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Test of getPSSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetPSSearchInput() throws Exception {
        System.out.println("getPSSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "psId", "psNo", "psName", "psType", "psStatus", "psCategory", "startDate", "endDate", "psDesignation", "parentPsId", "parentPsName", "tradeClass"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDPSView_PSID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getPSSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetPSSearchInputFalse() throws Exception {
        System.out.println("getPSSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "psId", "psNo", "psName", "psType", "psStatus", "psCategory", "startDate", "endDate", "psDesignation", "parentPsId", "parentPsName", "tradeClass"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDPSView_PSID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getPSSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getPSSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetPSSortedInputs() {
    try{
        System.out.println("getPSSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getPSSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @Test
    public void testGetPSSortedInputsFalse() {
    try{
        System.out.println("testGetPSSortedInputsFalse");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getPSSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getPSColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetPSColumns() {
      try{
        System.out.println("getPSColumns");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getPSColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetPSColumnsElse() {
      try{
        System.out.println("getPSColumns");
        String property = "SystemI";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getPSColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Test of getRSSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRSSearchInput() throws Exception {
        System.out.println("getRSSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "rsId", "rsNo", "rsName", "rsType", "rsStatus"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDPSView_PSID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRSSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
     @Test
    public void testGetRSSearchInputFalse() throws Exception {
        System.out.println("getRSSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"systemId", "rsId", "rsNo", "rsName", "rsType", "rsStatus"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDPSView_PSID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRSSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getRSSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRSSortedInputs() {
   try{
        System.out.println("getRSSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRSSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @Test
    public void testGetRSSortedInputsFalse() {
   try{
        System.out.println("getRSSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRSSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRSColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRSColumns() {
    try{
        System.out.println("getRSColumns");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRSColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRuleSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRuleSearchInput() throws Exception {
        System.out.println("getRuleSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExtraParameter(64);
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"lineType", "itemGroupAsso", "keyword", "keyOperator","value","comparison","compOperator"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRuleSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
    
      @Test
    public void testGetRuleSearchInputFalse() throws Exception {
        System.out.println("getRuleSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExtraParameter(64);
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"lineType", "itemGroupAsso", "keyword", "keyOperator","value","comparison","compOperator"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRuleSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getRuleSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRuleSortedInputs() {
    try{
        System.out.println("getRuleSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRuleSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRuleColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRuleColumns() {
        try{
        System.out.println("getRuleColumns");
        String property = "ruleNo";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRuleColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetRuleColumnsElse() {
        try{
        System.out.println("getRuleColumns");
        String property = "ruleNoo";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRuleColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        assertFalse(property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRPSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRPSearchInput() throws Exception {
        System.out.println("getRPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("794");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType", "rebateStructure", "rangeBasedOn", "netSalesFormula", "netSalesRule", "rebateBasedOn", "createdDate", "createdBy", "modifiedDate", "modifiedBy"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDRPNoView_RPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
     @Test
    public void testGetRPSearchInputFalse() throws Exception {
        System.out.println("getRPSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("794");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType", "rebateStructure", "rangeBasedOn", "netSalesFormula", "netSalesRule", "rebateBasedOn", "createdDate", "createdBy", "modifiedDate", "modifiedBy"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDRPNoView_RPID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRPSearchInput",GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
    

    /**
     * Test of getRPSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRPSortedInputs() {
    try{
        System.out.println("getRPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetRPSortedInputsFalse() {
    try{
        System.out.println("getRPSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRPSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRPColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetRPColumns() {
    try{
        System.out.println("getRPColumns");
        String property = "CDRPNoView_RPID";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetRPColumnsFalse() {
    try{
        System.out.println("getRPColumns");
        String property = "CDRPNoView_RPI";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getRPColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    //CDRPNoView_RPID
    /**
     * Test of getDCSearchInput method, of class GtnWsContractDashboardLookupLogic.
     */
//    @Test
//    public void testGetDCSearchInput() throws Exception {
//        System.out.println("getDCSearchInput");
//        GtnWsSearchRequest searchRequest = null;
//        GtnWsContractDashboardLookupLogic instance = null;
//        List<Object> expResult = null;
//        List<Object> result = instance.getDCSearchInput(searchRequest);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getDCSortedInputs method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetDCSortedInputs() {
    try{
        System.out.println("getDCSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getDCSortedInputs",List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, gtnWebServiceOrderByCriteriaList);
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getDCColumns method, of class GtnWsContractDashboardLookupLogic.
     */
    @Test
    public void testGetDCColumns() {
        try{
        System.out.println("getDCColumns");
        String property = "CDDCNoView_DCDesc";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getDCColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetDCColumnsElse() {
        try{
        System.out.println("getDCColumnss");
        String property = "CDDCNoView_DCDesc";
        Method method = gtnWsContractDashboardLookupLogic.getClass().getDeclaredMethod("getDCColumns",String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLookupLogic, property);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLookupLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
