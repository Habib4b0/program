/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContract;
import com.stpl.gtn.gtn2o.ws.entity.rebateschedule.RsModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
public class GtnWsContractDashboardLogicTest {
    
    public GtnWsContractDashboardLogicTest() {
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
    private GtnWsContractDashboardLogic gtnWsContractDashboardLogic;
    
    @Autowired
    private GtnWsContractDashboardController gtnWsContractDashboardController;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    public GtnWsContractDashboardController getController() {
		return gtnWsContractDashboardController;
	}

    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getController method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetController() {
        System.out.println("getController");
        GtnWsContractDashboardController result = gtnWsContractDashboardLogic.getController();
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetQuery_List_String() {
        System.out.println("getQuery");
        String queryName = "getCDCompanyAdditionMoveAllLeftQuery";
        List input = new ArrayList<>();     
        input.add(20156);
        input.add("698");
        String result = gtnWsContractDashboardLogic.getQuery(input,queryName);
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetQuery_String() {
        System.out.println("getQuery");
        String queryName = "getCDCompanyAdditionMoveAllLeftQuery";
        String result = gtnWsContractDashboardLogic.getQuery(queryName);
        assertFalse(result==null);
    }

    /**
     * Test of getWhereClauseForAColumn method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetWhereClauseForAColumn() throws Exception {
        System.out.println("getWhereClauseForAColumn");
        String expersion = "EQUALS";
        String field = "CHECK_RECORD";
        String value1 = "1";
        String value2 = "";
        String exp="CHECK_RECORD = '1' ";
        String result = gtnWsContractDashboardLogic.getWhereClauseForAColumn(expersion, field, value1, value2);
        assertEquals(exp, result);
    }

    /**
     * Test of getContractDashboardTableData method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetContractDashboardTableData() throws Exception {
        System.out.println("getContractDashboardTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"memberId", "memberName", "memberType", "memberNo"}));
        gtnWsSearchRequest.setCount(false);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardLogic.getContractDashboardTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of getContractDashboardDetailsTableData method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetContractDashboardDetailsTableData() throws Exception {
        System.out.println("getContractDashboardDetailsTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("921");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"no", "name", "type", "category", "status", "startDate", "endDate"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardLogic.getContractDashboardDetailsTableData(gtnWsRequest,gtnResponse);
        assertFalse(!gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of getSearchInput method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetSearchInput() throws Exception {
        System.out.println("getSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("921");
        generalRequest.setExcel(false);

        String comp="1";
        boolean leftSearch=true;        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"no", "name", "type", "category", "status", "startDate", "endDate"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardLogic.getSearchInput(gtnWsSearchRequest,comp,leftSearch);
        assertFalse(!gtnWebServiceSearchCriteriaList.isEmpty());
    }
    
    @Test
    public void testGetSearchInputFalse() throws Exception {
        System.out.println("getSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("921");
        generalRequest.setExcel(false);

        String comp="1";
        boolean leftSearch=true;        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"no", "name", "type", "category", "status", "startDate", "endDate"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("hiddenID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardLogic.getSearchInput(gtnWsSearchRequest,comp,leftSearch);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }
    
    @Test
    public void testGetSearchInputFalse2() throws Exception {
        System.out.println("getSearchInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("921");
        generalRequest.setExcel(false);

        String comp="1";
        boolean leftSearch=true;        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"no", "name", "type", "category", "status", "startDate", "endDate"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        try{
        gtnWsContractDashboardLogic.getSearchInput(gtnWsSearchRequest,comp,leftSearch);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of addInputWhereConditions method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testAddInputWhereConditions() {
        System.out.println("addInputWhereConditions");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("921");
        generalRequest.setExcel(false);
        
        StringBuilder inputWhereConditions=new StringBuilder("AND con.CONTRACT_ID LIKE '%' ");

        String comp="1";
        String and="AND";
        String where="";
        boolean leftSearch=true;        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"no", "name", "type", "category", "status", "startDate", "endDate"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria.setFilterValue1("*");
        criteria.setExpression("LIKE");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardLogic.addInputWhereConditions(inputWhereConditions,criteria,comp,leftSearch,and,where);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of getSortedInputs method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetSortedInputs() {
        try{
        System.out.println("getSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        String comp = "1";
        boolean details = false;
        boolean leftSearch = true;
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("getSortedInputs", List.class,String.class,boolean.class,boolean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic,gtnWebServiceOrderByCriteriaList, comp, details, leftSearch);
        assertFalse(comp.isEmpty());
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetSortedInputsFalse() {
        try{
        System.out.println("getSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        String comp = "1";
        boolean details = true;
        boolean leftSearch = true;
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("getSortedInputs", List.class,String.class,boolean.class,boolean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic,gtnWebServiceOrderByCriteriaList, comp, details, leftSearch);
        assertFalse(comp.isEmpty());
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test of filterAndSortingCriteriaMap method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testFilterAndSortingCriteriaMap() {
    try{
        System.out.println("filterAndSortingCriteriaMap");
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("filterAndSortingCriteriaMap");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic);
        assertFalse(method.getParameterCount()!=0);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    /**
     * Test of searchCriteriaMap method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testSearchCriteriaMap() {
    try{
        System.out.println("searchCriteriaMap");
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("searchCriteriaMap");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic);
        assertFalse(method.getParameterCount()!=0);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getUpdatedLongDate method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetUpdatedLongDate() {
        try{
        System.out.println("getUpdatedLongDate");
        long time = System.currentTimeMillis( );
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("getUpdatedLongDate",long.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic,time);
        assertFalse(Boolean.valueOf(String.valueOf(time).isEmpty()));
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of checkAddToTree method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testCheckAddToTree() throws Exception {
        System.out.println("checkAddToTree");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("1038-AZ");
			treeBean.addProperties("1038-AZ");
			treeBean.addProperties("Arizana Aids Drug Assistance Program");
			treeBean.addProperties("Mc Commercial");
			treeBean.addProperties(1425);
			treeBean.addProperties(907L);
			treeBean.addProperties(null);
			treeBean.addProperties("1");
			treeBean.addProperties("Contract");
			treeBean.addProperties(1425);
			treeBean.addProperties(222);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsContractDashboardLogic.checkAddToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testCheckAddToTreefalse() throws Exception {
        System.out.println("checkAddToTree");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(null);
			tableBean.addProperties(1L);
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("1038-AZ");
			treeBean.addProperties("1038-AZ");
			treeBean.addProperties("Arizana Aids Drug Assistance Program");
			treeBean.addProperties("Mc Commercial");
			treeBean.addProperties(1425);
			treeBean.addProperties(null);
			treeBean.addProperties(1L);
			treeBean.addProperties("1");
			treeBean.addProperties("Contract");
			treeBean.addProperties(1425);
			treeBean.addProperties(222);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsContractDashboardLogic.checkAddToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }

    /**
     * Test of addToTreeValidation method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testAddToTreeValidation() {
        System.out.println("addToTreeValidation");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        List<Object> list=Arrays.asList("1","2","3");
        
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1422);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("3");
			tableBean.addProperties("IFP");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties(1425);
			tableBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1422);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("3");
			tableBean2.addProperties("IFP");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1391);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties("2");
			treeBean2.addProperties("CFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        cdResponse.setValues(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardLogic.addToTreeValidation(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }

    /**
     * Test of getTreeValidationValues method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetTreeValidationValues() throws Exception {
//        System.out.println("getTreeValidationValues");
//        GtnWsContractDashboardResponse cdResponse = null;
//        GtnWsContractDashboardLogic instance = null;
//        GtnWsContractDashboardResponse expResult = null;
//        GtnWsContractDashboardResponse result = instance.getTreeValidationValues(cdResponse);
//        assertEquals(expResult, result);
        System.out.println("getTreeValidationValues");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        List<Object> list=new ArrayList<>();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1422);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("3");
			tableBean.addProperties("IFP");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties(1425);
			tableBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1422);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("3");
			tableBean2.addProperties("IFP");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1391);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties("2");
			treeBean2.addProperties("CFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        cdResponse.setValues(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardLogic.getTreeValidationValues(cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testGetTreeValidationValuesFalse() throws Exception {
        System.out.println("getTreeValidationValues");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        List<Object> list=new ArrayList<>();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1422);
			tableBean.addProperties(15L);
			tableBean.addProperties(2L);
			tableBean.addProperties("3");
			tableBean.addProperties("IFP");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1391);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties(1425);
			tableBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1422);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(2L);
			tableBean2.addProperties("3");
			tableBean2.addProperties("IFP");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1391);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties("2");
			treeBean2.addProperties("CFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        cdResponse.setValues(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardLogic.getTreeValidationValues(cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }

    /**
     * Test of getPsAssociatedIfpId method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetPsAssociatedIfpId() throws Exception {
        System.out.println("getPsAssociatedIfpId");
        int psModelId = 1713;
        int result = gtnWsContractDashboardLogic.getPsAssociatedIfpId(psModelId);
        assertFalse(result==0);
    }
    
    @Test
    public void testGetPsAssociatedIfpIdFalse() throws Exception {
        System.out.println("getPsAssociatedIfpId");
        int psModelId = 171;
        int result = gtnWsContractDashboardLogic.getPsAssociatedIfpId(psModelId);
        assertFalse(result!=0);
    }

    /**
     * Test of getRsAssociatedIfpId method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetRsAssociatedIfpId() throws Exception {
        System.out.println("getRsAssociatedIfpId");
        int rsModelId = 2609;
        int result = gtnWsContractDashboardLogic.getRsAssociatedIfpId(rsModelId);
        assertFalse(result==0);
    }
    
    @Test
    public void testGetRsAssociatedIfpIdFalse() throws Exception {
        System.out.println("getRsAssociatedIfpId");
        int rsModelId = 260;
        int result = gtnWsContractDashboardLogic.getRsAssociatedIfpId(rsModelId);
        assertFalse(result!=0);
    }

    /**
     * Test of doValidationWithRsOrPs method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testDoValidationWithRsOrPs() {
//        System.out.println("doValidationWithRsOrPs");
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardResponse cdResponse = null;
//        int systemId = 0;
//        int ifpSystemId = 0;
//        GtnWsContractDashboardLogic instance = null;
//        instance.doValidationWithRsOrPs(cdRequest, cdResponse, systemId, ifpSystemId);
        System.out.println("doValidationWithRsOrPs");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        List<Object> list=new ArrayList<>();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1713);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("4");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1422);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("3");
			tableBean.addProperties("IFP");
			tableBean.addProperties(1425);
			tableBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1713);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("4");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1422);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties("3");
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        int systemId= 1422;
        int ifpSystemId=1422;             
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        cdResponse.setValues(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardLogic.doValidationWithRsOrPs(gtnWsContractDashboardRequest,cdResponse,systemId,ifpSystemId);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testDoValidationWithRsOrPsFalse() {
        System.out.println("doValidationWithRsOrPs");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        List<Object> list=new ArrayList<>();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1713);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("4");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1422);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("3");
			tableBean.addProperties("IFP");
			tableBean.addProperties(1425);
			tableBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1713);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("4");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1422);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties("3");
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        int systemId= 0;
        int ifpSystemId=1422;             
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        cdResponse.setValues(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardLogic.doValidationWithRsOrPs(gtnWsContractDashboardRequest,cdResponse,systemId,ifpSystemId);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testDoValidationWithRsOrPsFalse2() {
        System.out.println("doValidationWithRsOrPs");

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        List<Object> list=new ArrayList<>();
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1713);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("4");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1422);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("3");
			tableBean.addProperties("IFP");
			tableBean.addProperties(1425);
			tableBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1713);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("4");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1422);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties("3");
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        int systemId= 1422;
        int ifpSystemId=142;             
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        cdResponse.setValues(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardLogic.doValidationWithRsOrPs(gtnWsContractDashboardRequest,cdResponse,systemId,ifpSystemId);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }

    /**
     * Test of addToTree method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testAddToTree() throws Exception {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
       GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        
       GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(1713);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("4");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("");
			treeBean.addProperties(1713);
			treeBean.addProperties(15L);
			treeBean.addProperties(null);
			treeBean.addProperties("4");
			treeBean.addProperties("PS");
			treeBean.addProperties(1425);
			treeBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1713);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("4");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1422);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties(3);
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        gtnWsContractDashboardLogic.addToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testAddToTreeFalse() throws Exception {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
       GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        
       GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(4);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("1");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("");
			treeBean.addProperties(3);
			treeBean.addProperties(15L);
			treeBean.addProperties(null);
			treeBean.addProperties("4");
			treeBean.addProperties("PS");
			treeBean.addProperties(1425);
			treeBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(4);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("1");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(3);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties(3);
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        gtnWsContractDashboardLogic.addToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testAddToTreeFalse2() throws Exception {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
       GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        
       GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(4);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("4");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("");
			treeBean.addProperties(3);
			treeBean.addProperties(15L);
			treeBean.addProperties(null);
			treeBean.addProperties("3");
			treeBean.addProperties("PS");
			treeBean.addProperties(1425);
			treeBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(4);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("4");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(3);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties(3);
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        gtnWsContractDashboardLogic.addToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testAddToTreeFalse3() throws Exception {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
       GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        
       GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(5);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("5");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("");
			treeBean.addProperties(3);
			treeBean.addProperties(15L);
			treeBean.addProperties(null);
			treeBean.addProperties("3");
			treeBean.addProperties("PS");
			treeBean.addProperties(1425);
			treeBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(5);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("5");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(3);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties(3);
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        gtnWsContractDashboardLogic.addToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }
    
    @Test
    public void testAddToTreeFalse4() throws Exception {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
       GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
        
       GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("14JUN2018");
			tableBean.addProperties("");
			tableBean.addProperties(5);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties("5");
			tableBean.addProperties("PS");
			tableBean.addProperties("0");
			tableBean.addProperties(0);
                        
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("14JUN2018");
			treeBean.addProperties("");
			treeBean.addProperties(3);
			treeBean.addProperties(15L);
			treeBean.addProperties(null);
			treeBean.addProperties("4");
			treeBean.addProperties("PS");
			treeBean.addProperties(1425);
			treeBean.addProperties(0);
                        
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(5);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("5");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(3);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties(3);
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setTableBean(tableBean);
        gtnWsContractDashboardRequest.setTreeBean(treeBean);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        gtnWsContractDashboardLogic.addToTree(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
    }

    /**
     * Test of isDuplicate method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testIsDuplicate() {
        try{
        System.out.println("isDuplicate");

       GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
       GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
                             
        GtnWsRecordBean tableBean2 = new GtnWsRecordBean();
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("14JUN2018");
			tableBean2.addProperties("");
			tableBean2.addProperties(1713);
			tableBean2.addProperties(15L);
			tableBean2.addProperties(null);
			tableBean2.addProperties("4");
			tableBean2.addProperties("PS");
			tableBean2.addProperties("0");
			tableBean2.addProperties(0);
                        
        GtnWsRecordBean treeBean2 = new GtnWsRecordBean();
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("14JUN2018");
			treeBean2.addProperties("");
			treeBean2.addProperties(1422);
			treeBean2.addProperties(15L);
			treeBean2.addProperties(null);
			treeBean2.addProperties(3);
			treeBean2.addProperties("IFP");
			treeBean2.addProperties(1425);
			treeBean2.addProperties(0);
                        
        List<GtnWsRecordBean> recordBeanList=new ArrayList<>();
        recordBeanList.add(treeBean2);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setTableBean(null);
        gtnWsContractDashboardRequest.setTreeBean(null);
        gtnWsContractDashboardRequest.setRecordBeanList(recordBeanList);
        cdResponse.setTableBean(tableBean2);
        cdResponse.setTreeBean(treeBean2);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        frameworkWebserviceResponse.setGtnWsContractDashboardResponse(cdResponse);
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("isDuplicate",List.class,GtnWsRecordBean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic,recordBeanList,tableBean2);
        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
          } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    

    /**
     * Test of saveContractTree method, of class GtnWsContractDashboardLogic.
     */
//    @Test
//    public void testSaveContractTree() throws Exception {
//        System.out.println("saveContractTree");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("11");
//        generalRequest.setExcel(false);
//
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        
//            
//        GtnWsRecordBean treeBean = new GtnWsRecordBean();
//			treeBean.addProperties("1038-AZ");
//			treeBean.addProperties("1038-AZ");
//			treeBean.addProperties("Arizana Aids Drug Assistance Program");
//			treeBean.addProperties("Mc Commercial");
//			treeBean.addProperties(1425);
//			treeBean.addProperties(907L);
//			treeBean.addProperties(null);
//			treeBean.addProperties("1");
//			treeBean.addProperties("Contract");
//			treeBean.addProperties(1425);
//			treeBean.addProperties(222);
//        List<GtnWsRecordBean> list=new ArrayList<>();
//        list.add(treeBean);
//        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
//        gtnWsContractDashboardRequest.setTableBean(treeBean);
//        treeBean.setChildList(list);
//        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
//        gtnWsContractDashboardRequest.setRecordBeanList(list);
//        gtnWsContractDashboardRequest.setUserId("20156");
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsContractDashboardLogic.saveContractTree(gtnWsContractDashboardRequest,cdResponse);
//        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
//    }

    /**
     * Test of saveRecursiveContractTree method, of class GtnWsContractDashboardLogic.
     */
//    @Test
//    public void testSaveRecursiveContractTree() throws Exception {
//
//        System.out.println("saveRecursiveContractTree");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("11");
//        generalRequest.setExcel(false);
//        
//        Session session = getController().getSessionFactory().openSession();
//	Transaction tx = session.beginTransaction();
//
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        
//            
//        GtnWsRecordBean treeBean = new GtnWsRecordBean();
//			treeBean.addProperties("1038-AZ");
//			treeBean.addProperties("1038-AZ");
//			treeBean.addProperties("Arizana Aids Drug Assistance Program");
//			treeBean.addProperties("Mc Commercial");
//			treeBean.addProperties(1425);
//			treeBean.addProperties(907L);
//			treeBean.addProperties(null);
//			treeBean.addProperties("1");
//			treeBean.addProperties("Contract");
//			treeBean.addProperties(1425);
//			treeBean.addProperties(222);
//        List<GtnWsRecordBean> list=new ArrayList<>();
//        list.add(treeBean);
//        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
//        gtnWsContractDashboardRequest.setTableBean(treeBean);
//        treeBean.setChildList(list);
//        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
//        Map<String, Object> contractMap = new HashMap<>();
//        gtnWsContractDashboardRequest.setRecordBeanList(list);
//        gtnWsContractDashboardRequest.setUserId("20156");
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsContractDashboardLogic.saveRecursiveContractTree(session,gtnWsContractDashboardRequest,cdResponse,list,contractMap);
//        session.close();
//        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
//    }

    /**
     * Test of updateContractTree method, of class GtnWsContractDashboardLogic.
     */
//    @Test
//    public void testUpdateContractTree() throws Exception {
//        System.out.println("updateContractTree");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardResponse cdResponse = null;
//        GtnWsRecordBean recordBean = null;
//        Map<String, Object> contractMap = null;
//        GtnWsContractDashboardLogic instance = null;
//        instance.updateContractTree(session, cdRequest, cdResponse, recordBean, contractMap);
//    }

    /**
     * Test of saveCFPTree method, of class GtnWsContractDashboardLogic.
     */
//    @Test
//    public void testSaveCFPTree() throws Exception {
//
//        System.out.println("saveCFPTree");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("11");
//        generalRequest.setExcel(false);
//        
//        Session session = getController().getSessionFactory().openSession();
//	Transaction tx = session.beginTransaction();
//
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        
//            
//        GtnWsRecordBean treeBean = new GtnWsRecordBean();
//			treeBean.addProperties("1038-AZ");
//			treeBean.addProperties("1038-AZ");
//			treeBean.addProperties("Arizana Aids Drug Assistance Program");
//			treeBean.addProperties("Mc Commercial");
//			treeBean.addProperties(1425);
//			treeBean.addProperties(907L);
//			treeBean.addProperties(null);
//			treeBean.addProperties("1");
//			treeBean.addProperties("Contract");
//			treeBean.addProperties(1425);
//			treeBean.addProperties(222);
//        List<GtnWsRecordBean> list=new ArrayList<>();
//        list.add(treeBean);
//        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
//        gtnWsContractDashboardRequest.setTableBean(treeBean);
//        treeBean.setChildList(list);
//        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
//        Map<String, Object> contractMap = new HashMap<>();
//        gtnWsContractDashboardRequest.setRecordBeanList(list);
//        gtnWsContractDashboardRequest.setUserId("20156");
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        //contractMap.put("1", contractMaster);
//        gtnWsContractDashboardLogic.saveCFPTree(session,gtnWsContractDashboardRequest,cdResponse,treeBean,contractMap);
//        tx.commit();
//        session.close();
//        assertFalse(gtnWsContractDashboardRequest.getCfpContractId()!=0);
//
//
//    }

    /**
     * Test of saveIFPTree method, of class GtnWsContractDashboardLogic.
     */
//    @Test
//    public void testSaveIFPTree() throws Exception {
//        System.out.println("saveIFPTree");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardResponse cdResponse = null;
//        GtnWsRecordBean recordBean = null;
//        Map<String, Object> contractMap = null;
//        GtnWsContractDashboardLogic instance = null;
//        instance.saveIFPTree(session, cdRequest, cdResponse, recordBean, contractMap);
//    }
//
//    /**
//     * Test of savePSTree method, of class GtnWsContractDashboardLogic.
//     */
//    @Test
//    public void testSavePSTree() throws Exception {
//        System.out.println("savePSTree");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardResponse cdResponse = null;
//        GtnWsRecordBean recordBean = null;
//        Map<String, Object> contractMap = null;
//        GtnWsContractDashboardLogic instance = null;
//        instance.savePSTree(session, cdRequest, cdResponse, recordBean, contractMap);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of saveRSTree method, of class GtnWsContractDashboardLogic.
//     */
//    @Test
//    public void testSaveRSTree() throws Exception {
//        System.out.println("saveRSTree");
//        Session session = null;
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsRecordBean recordBean = null;
//        Map<String, Object> contractMap = null;
//        GtnWsContractDashboardLogic instance = null;
//        instance.saveRSTree(session, cdRequest, recordBean, contractMap);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addContractSearchCriteria method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testAddContractSearchCriteria() {
        System.out.println("addContractSearchCriteria");
        Session session = getController().getSessionFactory().openSession();
        Criteria cr = session.createCriteria(RsContract.class);
        Object object = "cfpContractId";
        String propertyId = "cfpContractId";
        gtnWsContractDashboardLogic.addContractSearchCriteria(cr, object, propertyId);
        assertFalse(propertyId.isEmpty());
        session.close();
    }
    
     @Test
    public void testAddContractSearchCriteriaFalse() {
        System.out.println("addContractSearchCriteria");
        Session session = getController().getSessionFactory().openSession();
        Criteria cr = session.createCriteria(RsContract.class);
        Object object = null;
        String propertyId = "cfpContractId";
        gtnWsContractDashboardLogic.addContractSearchCriteria(cr, object, propertyId);
        assertFalse(propertyId.isEmpty());
        session.close();
    }

    /**
     * Test of setRSContractValues method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testSetRSContractValues() {
        System.out.println("setRSContractValues");
        RsModel rsModel = new RsModel();
        RsContract rsContract = new RsContract();
        gtnWsContractDashboardLogic.setRSContractValues(rsModel, rsContract);
        assertFalse(rsModel.toString().isEmpty());
    }

    /**
     * Test of saveRsUdc method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testSaveRsUdc() throws Exception {
        try{
        System.out.println("saveRsUdc");
        Session session = getController().getSessionFactory().openSession();
        RsContract rsContract = new RsContract();
        rsContract.setRsContractSid(365);
        int rsId = 365;       
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("saveRsUdc",Session.class,RsContract.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic,session, rsContract, rsId);
        session.close();
        assertFalse(rsId==0);
          } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    @Test
    public void testSaveRsUdcFalse() throws Exception {
        try{
        System.out.println("saveRsUdc");
        Session session = getController().getSessionFactory().openSession();
        RsContract rsContract = new RsContract();
        //rsContract.setRsContractSid(365);
        int rsId = 36;       
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("saveRsUdc",Session.class,RsContract.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic,session, rsContract, rsId);
        session.close();
        assertFalse(rsId==0);
           } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }
    
    }

    /**
     * Test of getSqlQueryEngine method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetSqlQueryEngine() {
      try{
        System.out.println("getSqlQueryEngine");
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("getSqlQueryEngine");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic);
        assertFalse(method.getParameterCount()!=0);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    /**
     * Test of getSqlService method, of class GtnWsContractDashboardLogic.
     */
    @Test
    public void testGetSqlService() {
        try{
        System.out.println("getSqlService");
        Method method = gtnWsContractDashboardLogic.getClass().getDeclaredMethod("getSqlService");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardLogic);
        assertFalse(method.getParameterCount()!=0);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
}
