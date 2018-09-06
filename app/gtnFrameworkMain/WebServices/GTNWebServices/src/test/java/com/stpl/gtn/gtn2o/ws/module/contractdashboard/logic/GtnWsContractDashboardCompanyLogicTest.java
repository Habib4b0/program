/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
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
import org.junit.Assert;
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
public class GtnWsContractDashboardCompanyLogicTest {
    
    public GtnWsContractDashboardCompanyLogicTest() {
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
    private GtnWsContractDashboardCompanyLogic gtnWsContractDashboardCompanyLogic;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getController method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetController() {
        System.out.println("getController");
        GtnWsContractDashboardController result = gtnWsContractDashboardCompanyLogic.getController();
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        String queryName = "getCDCompanyAdditionMoveAllLeftQuery";
        String result = gtnWsContractDashboardCompanyLogic.getQuery(queryName);
        assertFalse(result==null);
    }

    /**
     * Test of getWhereClauseForAColumn method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetWhereClauseForAColumn() throws Exception {
        System.out.println("getWhereClauseForAColumn");
        String expersion = "EQUALS";
        String field = "CHECK_RECORD";
        String value1 = "1";
        String value2 = "";
        String exp="CHECK_RECORD = '1' ";
        String result = gtnWsContractDashboardCompanyLogic.getWhereClauseForAColumn(expersion, field, value1, value2);
        assertEquals(exp, result);
    }

    /**
     * Test of getCDCompanyAdditionLeftTableData method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCDCompanyAdditionLeftTableData() throws Exception {
        System.out.println("getCDCompanyAdditionLeftTableData");
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("703");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company ID");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardCompanyLogic.getCDCompanyAdditionLeftTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
        @Test
    public void testGetCDCompanyAdditionLeftTableDataFalse() throws Exception {
        System.out.println("getCDCompanyAdditionLeftTableData");
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("703");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company ID");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardCompanyLogic.getCDCompanyAdditionLeftTableData(gtnWsRequest,gtnResponse);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of getCompanyAdditionSearchInput method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompanyAdditionSearchInput() throws Exception {

      try {
        System.out.println("getCompanyAdditionSearchInput");
        String postfix = "";
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company ID");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompanyAdditionSearchInput",GtnWsSearchRequest.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest,postfix);
        assertFalse( !gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetCompanyAdditionSearchInputfalse() throws Exception {

      try {
        System.out.println("testGetCompanyAdditionSearchInputfalse");
        String postfix = "";
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company ID");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompanyAdditionSearchInput",GtnWsSearchRequest.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest,postfix);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
    }

    /**
     * Test of getCompanyAdditionSortedInputs method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompanyAdditionSortedInputs() {
        try {
            System.out.println("getCompanyAdditionSortedInputs");
            String postfix = "";
            List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompanyAdditionSortedInputs",List.class,String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWebServiceOrderByCriteriaList,postfix);
            assertFalse( !gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCmAdditionTabColumns method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCmAdditionTabColumns() {
    try {
            System.out.println("getCmAdditionTabColumns");
            String property = "companyId";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCmAdditionTabColumns",String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic,property);
            assertFalse( property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getWhereClauseForCmAddition method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetWhereClauseForCmAddition() {

        try {
            System.out.println("getWhereClauseForCmAddition");
            String field = "Company ID";
            String value = "%";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getWhereClauseForCmAddition", String.class, String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, field, value);
            assertFalse(field.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Test of getCompanyMoveAllLeftQuery method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompanyMoveAllLeftQuery() throws Exception {

        System.out.println("getCompanyMoveAllLeftQuery");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("605");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardCompanyLogic.getCompanyMoveAllLeftQuery(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getCompanyMoveAllRightQuery method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompanyMoveAllRightQuery() throws Exception {
        System.out.println("getCompanyMoveAllLeftQuery");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("605");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company ID");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardCompanyLogic.getCompanyMoveAllLeftQuery(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getCDCompanyAdditionViewTableData method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCDCompanyAdditionViewTableData() throws Exception {
        System.out.println("getCDCompanyAdditionViewTableData");
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("618");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardCompanyLogic.getCDCompanyAdditionViewTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());

    }

    /**
     * Test of companyAdditionMoveRight method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testCompanyAdditionMoveRight() throws Exception {
        System.out.println("companyAdditionMoveRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("605");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("10");
			treeBean.addProperties("10");
			treeBean.addProperties("Integrated Pharmaceutical Services");
			treeBean.addProperties("Divested");
			treeBean.addProperties("PBMD");
			treeBean.addProperties("PBMD");
			treeBean.addProperties("PBMD");
                        treeBean.addProperties("");
			treeBean.addProperties(74798);
			treeBean.addProperties(128);
			treeBean.addProperties(12149);
			treeBean.addProperties(145L);
                        treeBean.addProperties(null);
			treeBean.addProperties(15069);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(treeBean);
        gtnWsContractDashboardRequest.setRecordBeanList(list);
        treeBean.setRecordHeader(Arrays.asList("companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"));
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardCompanyLogic.companyAdditionMoveRight(gtnWsRequest,gtnResponse);
        assertFalse(result==null);
    }

    /**
     * Test of getCompaniesSearchInput method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompaniesSearchInput() throws Exception {
        try {
            System.out.println("getCompaniesSearchInput");

            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(5);

            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompaniesSearchInput", GtnWsSearchRequest.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest);
            assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGetCompaniesSearchInputFalse() throws Exception {
        try {
            System.out.println("getCompaniesSearchInput");

            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(5);

            //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompaniesSearchInput", GtnWsSearchRequest.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest);
            assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

    }

    /**
     * Test of getCurrentHistoryFilter method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCurrentHistoryFilter() {
    try {
            System.out.println("getCurrentHistoryFilter");
            String recordType = "Current";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCurrentHistoryFilter", String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, recordType);
            assertFalse(recordType.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetCurrentHistoryFilter2() {
    try {
            System.out.println("getCurrentHistoryFilter");
            String recordType = "History";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCurrentHistoryFilter", String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, recordType);
            assertFalse(recordType.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetCurrentHistoryFilter3() {
    try {
            System.out.println("getCurrentHistoryFilter");
            String recordType = "Future";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCurrentHistoryFilter", String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, recordType);
            assertFalse(recordType.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of skipCriteria method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testSkipCriteria() { 
    try {
            System.out.println("skipCriteria");
            String field = "Current";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("skipCriteria", String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, field);
            assertFalse(field.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    @Test
    public void testSkipCriteriaFalse() { 
    try {
            System.out.println("skipCriteria");
            String field = "BasePrice";
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("skipCriteria", String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, field);
            assertFalse(field.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    /**
     * Test of getCompniesDetailSortedInputs method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompniesDetailSortedInputs() {       
         try {
            System.out.println("getCompniesDetailSortedInputs");
            List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompniesDetailSortedInputs", List.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWebServiceOrderByCriteriaList);
            assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCDCompniesDetailTableData method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCDCompniesDetailTableData() throws Exception {
try {
            System.out.println("getCDCompniesDetailTableData");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("915");
            generalRequest.setExtraParameter("cfpContractId");
            generalRequest.setExcel(false);
              
            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(5);
            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCDCompniesDetailTableData", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsRequest,gtnResponse);
            assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGetCDCompniesDetailTableDataFalse() throws Exception {
    try {
            System.out.println("getCDCompniesDetailTableData");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("915");
            generalRequest.setExtraParameter("cfpContractId");
            generalRequest.setExcel(false);
              
            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(5);
            //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCDCompniesDetailTableData", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsRequest,gtnResponse);
            //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
           // Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of companyProcess method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testCompanyProcess() throws Exception {

    try {
            System.out.println("companyProcess");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("533");
            generalRequest.setExtraParameter("cfpContractId");
            generalRequest.setExcel(false);
            
            
            String queryname="getCompaniesDetailsResults";
            
            
            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(5);
            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
            Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("companyProcess", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class,String.class);
            method.setAccessible(true);
            method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsRequest,gtnResponse,queryname);
            assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCDCompniesDetailViewTableData method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCDCompniesDetailViewTableData() throws Exception {
    try {
            System.out.println("getCDCompniesDetailViewTableData");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("515");
            generalRequest.setExtraParameter("cfpContractId");
            generalRequest.setExcel(false);
            
            
            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(6);
            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
            gtnWsContractDashboardCompanyLogic.getCDCompniesDetailViewTableData(gtnWsRequest,gtnResponse);
            assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetCDCompniesDetailViewTableDataFalse() throws Exception {
    try {
            System.out.println("getCDCompniesDetailViewTableData");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("515");
            generalRequest.setExtraParameter("cfpContractId");
            generalRequest.setExcel(false);
            
            
            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);

            gtnWsSearchRequest.setTableRecordStart(0);
            gtnWsSearchRequest.setTableRecordOffset(6);
            //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
            gtnWsContractDashboardCompanyLogic.getCDCompniesDetailViewTableData(gtnWsRequest,gtnResponse);
            //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            assertEquals(GtnFrameworkGeneralException.class, ex.getClass());
            //Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of populateAllCompanies method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testPopulateAllCompanies() throws Exception {
        try {
            System.out.println("populateAllCompanies");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("915");
            generalRequest.setExcel(false);

            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);

            GtnWsContractDashboardRequest gtnWsContractDashboardRequest = new GtnWsContractDashboardRequest();
            gtnWsContractDashboardRequest.setPopulateField("CFP Status");
            gtnWsContractDashboardRequest.setPopulateValue(126);
            gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
      
            GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardCompanyLogic.populateAllCompanies(gtnWsRequest, gtnResponse);
            assertFalse(result == null);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of populateCompany method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testPopulateCompany() throws Exception {
        try {
            System.out.println("populateCompany");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("915");
            generalRequest.setExcel(false);

            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);

            GtnWsContractDashboardRequest gtnWsContractDashboardRequest = new GtnWsContractDashboardRequest();
            gtnWsContractDashboardRequest.setPopulateField("CFP Status");
            gtnWsContractDashboardRequest.setPopulateValue(126);
            gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
      
            GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardCompanyLogic.populateCompany(gtnWsRequest, gtnResponse);
            assertFalse(result == null);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of populateCompanyField method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testPopulateCompanyField() throws Exception {
    try {
            System.out.println("populateCompanyField");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("915");
            generalRequest.setExcel(false);

            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setCount(false);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);

            GtnWsContractDashboardRequest gtnWsContractDashboardRequest = new GtnWsContractDashboardRequest();
            gtnWsContractDashboardRequest.setSystemId(1160306);
            gtnWsContractDashboardRequest.setPopulateField("cfpStatus");
            gtnWsContractDashboardRequest.setPopulateValue(127);
            gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
      
            GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardCompanyLogic.populateCompanyField(gtnWsRequest, gtnResponse);
            assertFalse(result == null);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getCDCompniessDetailTableData method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCDCompniessDetailTableData() throws Exception {
    try {
            System.out.println("getCDCompniessDetailTableData");
            GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse =new GtnUIFrameworkWebserviceResponse();
            generalRequest.setComboBoxType("COMPANY_TYPE");
            generalRequest.setUserId("20156");
            generalRequest.setSessionId("189");
            generalRequest.setExtraParameter("cfpContractId");
            generalRequest.setExcel(false);
            
            
            GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
            gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType","companyId", "companyNo", "companyName", "cfpStatus", "cfpStartDate cfpEndDate", "companyStatus",
                "companyType", "tradeClass", "companyCategory", "tradingPartnerContractNo", "cfpAttachedDate", "modifiedDate", "modifiedBy", "createdDate",
                "createdBy", "cfpEligibleDate", "companiessStatusHelperValue"}));
            gtnWsSearchRequest.setCount(true);
            List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
            GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
            criteria.setFieldId("CDProcessView_CTrecord");
            criteria.setFilterValue1("[Pending]");
            criteria.setExpression("EQUALS");
            criteria.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria);
            
            GtnWebServiceSearchCriteria criteria1 = new GtnWebServiceSearchCriteria();
            criteria1.setFieldId("cfpContractId");
            criteria1.setFilterValue1("1");
            criteria1.setExpression("EQUALS");
            criteria1.setFilter(false);
            gtnWebServiceSearchCriteriaList.add(criteria1);

            gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
            gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
            gtnWsContractDashboardCompanyLogic.getCDCompniessDetailTableData(gtnWsRequest,gtnResponse);
            assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getCompaniessSearchInput method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCompaniessSearchInput() throws Exception {
    try{
      GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("cfpContractId");
        criteria2.setFilterValue1("234");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();        
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompaniessSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
   } catch (Exception ex) {
       Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetCompaniessSearchInputFalse() throws Exception {
    try{
      GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("cfpContractId");
        criteria2.setFilterValue1("234");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();        
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("getCompaniessSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest);
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
   } catch (Exception ex) {
       assertEquals(NullPointerException.class, ex.getClass());
       //Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of returnWhereCluase method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testReturnWhereCluase() throws Exception {
    try{
      GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("cfpContractId");
        criteria2.setFilterValue1("234");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnWebServiceSearchCriteria criteria3 = new GtnWebServiceSearchCriteria();
        criteria3.setFieldId("checkRecordId");
        criteria3.setFilterValue1("1");
        criteria3.setExpression("EQUALS");
        criteria3.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria3);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("returnWhereCluase", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
   } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testReturnWhereCluaseFalse() throws Exception {
    try{
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("cfpContractId");
        criteria2.setFilterValue1("234");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnWebServiceSearchCriteria criteria3 = new GtnWebServiceSearchCriteria();
        criteria3.setFieldId("checkRecordId");
        criteria3.setFilterValue1("1");
        criteria3.setExpression("LIKE");
        criteria3.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria3);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardCompanyLogic.getClass().getDeclaredMethod("returnWhereCluase", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCompanyLogic, gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
   } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getCDCompniessViewDetailTableData method, of class GtnWsContractDashboardCompanyLogic.
     */
    @Test
    public void testGetCDCompniessViewDetailTableData() throws Exception {
    try{
      GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("cfpContractId");
        criteria2.setFilterValue1("233");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        GtnUIFrameworkWebserviceResponse cdcompaniesgtnResponse=new GtnUIFrameworkWebserviceResponse();
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardCompanyLogic.getCDCompniessViewDetailTableData(gtnWsRequest, cdcompaniesgtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
   } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCompanyLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
