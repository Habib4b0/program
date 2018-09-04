/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
public class GtnWsContractDashboardProcessLogicTest {
    
    public GtnWsContractDashboardProcessLogicTest() {
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
    private GtnWsContractDashboardProcessLogic gtnWsContractDashboardProcessLogic;
    
    @Autowired
    private GtnWsContractDashboardController gtnWsContractDashboardController;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
      public GtnWsContractDashboardController getController() {
		return gtnWsContractDashboardController;
	}
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getController method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetController() {
        System.out.println("getController");
        GtnWsContractDashboardController result = gtnWsContractDashboardProcessLogic.getController();
        assertFalse(result.toString().isEmpty());
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetQuery_List_String() {
        System.out.println("getQuery");
        String queryName = "getCDCompanyAdditionMoveAllLeftQuery";
        List input = new ArrayList<>();     
        input.add(20156);
        input.add("698");
        String result = gtnWsContractDashboardProcessLogic.getQuery(input,queryName);
        assertFalse(result==null);
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetQuery_String() {
        System.out.println("getQuery");
        String queryName = "getCDCompanyAdditionMoveAllLeftQuery";
        String result = gtnWsContractDashboardProcessLogic.getQuery(queryName);
        assertFalse(result==null);
    }

    /**
     * Test of getWhereClauseForAColumn method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetWhereClauseForAColumn() throws Exception {
        System.out.println("getWhereClauseForAColumn");
        String expersion = "EQUALS";
        String field = "CHECK_RECORD";
        String value1 = "1";
        String value2 = "";
        String exp="CHECK_RECORD = '1' ";
        String result = gtnWsContractDashboardProcessLogic.getWhereClauseForAColumn(expersion, field, value1, value2);
        assertEquals(exp, result);
    }

    /**
     * Test of getSearchInput method, of class GtnWsContractDashboardProcessLogic.
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
        gtnWsContractDashboardProcessLogic.getSearchInput(gtnWsSearchRequest,comp,leftSearch);
        assertFalse(!gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of getForeCastTypeTables method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetForeCastTypeTables() {
        try{
        System.out.println("getForeCastTypeTables");
        String forecastType = "d";
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getForeCastTypeTables", String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,forecastType);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetForeCastTypeTablesElse() {
    try{
        System.out.println("getForeCastTypeTables");
        String forecastType = "l";
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getForeCastTypeTables", String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,forecastType);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getContractDashboardProcessTreeData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetContractDashboardProcessTreeData() throws Exception {
        System.out.println("getContractDashboardProcessTreeData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
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
        gtnWsContractDashboardProcessLogic.getContractDashboardProcessTreeData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());

    }

    /**
     * Test of getCountInputList method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetCountInputList() {
        
    try{
        System.out.println("getCountInputList");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = "1";
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        int queryIndex = 2;
        String conId = "1248";
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1248);
			treeBean.addProperties(3);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
                        treeBean.addProperties(4);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getCountInputList", GtnWsSearchRequest.class,String.class,List.class,int.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,gtnWsSearchRequest, memberLevel, countInputlist, queryIndex, conId);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addInputBasedOnLevel method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testAddInputBasedOnLevel() {
    try{
        System.out.println("addInputBasedOnLevel");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = "1";
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        int queryIndex = 2;
        String conId = "1248";
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1248);
			treeBean.addProperties(3);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
                        treeBean.addProperties(4);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("addInputBasedOnLevel", List.class,String.class,String.class,GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic, countInputlist, memberLevel, conId,gtnWsSearchRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getExtraSelectParameterInputList method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetExtraSelectParameterInputList() {
try{
        System.out.println("getExtraSelectParameterInputList");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = "1";
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        countInputlist.add("select\n" +
"	count( distinct ifpco.IFP_CONTRACT_SID )\n" +
"from\n" +
"	dbo.IFP_CONTRACT ifpco\n" +
"where\n" +
"	ifpco.CONTRACT_MASTER_SID =1246\n" +
"	and ifpco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and ifpco.INBOUND_STATUS <> 'D'");
        countInputlist.add("select\n" +
"	count( distinct psco.PS_CONTRACT_SID )\n" +
"from\n" +
"	dbo.PS_CONTRACT psco\n" +
"where\n" +
"	psco.CONTRACT_MASTER_SID =1246\n" +
"	and psco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and psco.IFP_CONTRACT_SID  IS NULL\n" +
"	and psco.INBOUND_STATUS <> 'D'");
        countInputlist.add("select\n" +
"	count( distinct rsco.RS_CONTRACT_SID )\n" +
"from\n" +
"	dbo.RS_CONTRACT rsco\n" +
"where\n" +
"	rsco.CONTRACT_MASTER_SID =1246\n" +
"	and rsco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and rsco.IFP_CONTRACT_SID  IS NULL\n" +
"	and rsco.PS_CONTRACT_SID  IS NULL\n" +
"	and rsco.INBOUND_STATUS <> 'D'");
        countInputlist.add("false");
        countInputlist.add("=1246");
        int queryIndex = 2;
        String conId = "1248";
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1246);
			treeBean.addProperties(20);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(20);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(9);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getExtraSelectParameterInputList", GtnWsSearchRequest.class,List.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic, gtnWsSearchRequest, countInputlist,queryIndex);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getExtraWhereParameterInputList method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetExtraWhereParameterInputList() {
try{
        System.out.println("getExtraWhereParameterInputList");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = "1";
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        countInputlist.add("select\n" +
"	count( distinct ifpco.IFP_CONTRACT_SID )\n" +
"from\n" +
"	dbo.IFP_CONTRACT ifpco\n" +
"where\n" +
"	ifpco.CONTRACT_MASTER_SID =1246\n" +
"	and ifpco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and ifpco.INBOUND_STATUS <> 'D'");
        countInputlist.add("select\n" +
"	count( distinct psco.PS_CONTRACT_SID )\n" +
"from\n" +
"	dbo.PS_CONTRACT psco\n" +
"where\n" +
"	psco.CONTRACT_MASTER_SID =1246\n" +
"	and psco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and psco.IFP_CONTRACT_SID  IS NULL\n" +
"	and psco.INBOUND_STATUS <> 'D'");
        countInputlist.add("select\n" +
"	count( distinct rsco.RS_CONTRACT_SID )\n" +
"from\n" +
"	dbo.RS_CONTRACT rsco\n" +
"where\n" +
"	rsco.CONTRACT_MASTER_SID =1246\n" +
"	and rsco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and rsco.IFP_CONTRACT_SID  IS NULL\n" +
"	and rsco.PS_CONTRACT_SID  IS NULL\n" +
"	and rsco.INBOUND_STATUS <> 'D'");
        countInputlist.add("false");
        countInputlist.add("=1246");
        int queryIndex = 2;
        String conId = "1248";
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1246);
			treeBean.addProperties(20);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(20);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(9);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getExtraWhereParameterInputList", GtnWsSearchRequest.class,String.class,List.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic, gtnWsSearchRequest, memberLevel,countInputlist,queryIndex);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of getExtraWhereParameterInputListBasedOnLevel method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetExtraWhereParameterInputListBasedOnLevel() {
    try{
        System.out.println("getExtraWhereParameterInputListBasedOnLevel");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = "1";
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        countInputlist.add("select\n" +
"	count( distinct ifpco.IFP_CONTRACT_SID )\n" +
"from\n" +
"	dbo.IFP_CONTRACT ifpco\n" +
"where\n" +
"	ifpco.CONTRACT_MASTER_SID =1246\n" +
"	and ifpco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and ifpco.INBOUND_STATUS <> 'D'");
        countInputlist.add("select\n" +
"	count( distinct psco.PS_CONTRACT_SID )\n" +
"from\n" +
"	dbo.PS_CONTRACT psco\n" +
"where\n" +
"	psco.CONTRACT_MASTER_SID =1246\n" +
"	and psco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and psco.IFP_CONTRACT_SID  IS NULL\n" +
"	and psco.INBOUND_STATUS <> 'D'");
        countInputlist.add("select\n" +
"	count( distinct rsco.RS_CONTRACT_SID )\n" +
"from\n" +
"	dbo.RS_CONTRACT rsco\n" +
"where\n" +
"	rsco.CONTRACT_MASTER_SID =1246\n" +
"	and rsco.CFP_CONTRACT_SID = cfpco.CFP_CONTRACT_SID\n" +
"	and rsco.IFP_CONTRACT_SID  IS NULL\n" +
"	and rsco.PS_CONTRACT_SID  IS NULL\n" +
"	and rsco.INBOUND_STATUS <> 'D'");
        countInputlist.add("false");
        countInputlist.add("=1246");
        int queryIndex = 2;
        String conId = "1248";
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("CONTRACT_1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1246);
			treeBean.addProperties(20);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(20);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(9);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getExtraWhereParameterInputListBasedOnLevel", GtnWsSearchRequest.class,String.class,List.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic, gtnWsSearchRequest, memberLevel,countInputlist,queryIndex);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProcessTreeChildData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetProcessTreeChildData() throws Exception {
try{
        System.out.println("getProcessTreeChildData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = "1";
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        int queryIndex = 2;
        String conId = "1248";
        
        GtnUIFrameworkDataTable gtnUIFrameworkDataTable=new GtnUIFrameworkDataTable();
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1248);
			treeBean.addProperties(3);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
                        treeBean.addProperties(4);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        
        int index=9;
        int parentNo=1;
        

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getProcessTreeChildData", GtnWsSearchRequest.class,GtnUIFrameworkDataTable.class,int.class,int.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic, gtnWsSearchRequest,gtnUIFrameworkDataTable,index,parentNo,memberLevel);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProcessTreeSearchData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetProcessTreeSearchData() throws Exception {
        System.out.println("getProcessTreeSearchData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(false);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnUIFrameworkDataTable tableData=new GtnUIFrameworkDataTable();

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getProcessTreeSearchData", GtnWsSearchRequest.class,GtnUIFrameworkDataTable.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,gtnWsSearchRequest,tableData);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());

    }

    /**
     * Test of validateContractToRebuild method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testValidateContractToRebuild() throws Exception {
        System.out.println("validateContractToRebuild");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse=new GtnUIFrameworkWebserviceResponse();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("189858");
        processBean.setSessionId("847");
        processBean.setContractId(1486);
        processBean.setCfpContractId(377);
        processBean.setIfpContractId(397);
        processBean.setPsContractId(514);
        processBean.setRsContractId(498);
        
        gtnWsContractDashboardRequest.setUserId("189858");
        gtnWsContractDashboardRequest.setSessionId("847");
        gtnWsContractDashboardRequest.setContractId(1279);
        gtnWsContractDashboardRequest.setCfpContractId(265);
        gtnWsContractDashboardRequest.setIfpContractId(301);
        gtnWsContractDashboardRequest.setPsContractId(419);
        gtnWsContractDashboardRequest.setRsContractId(417);
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardProcessLogic.validateContractToRebuild(gtnWsRequest,frameworkWebserviceResponse);
        assertFalse(processBean==null);
    }

    /**
     * Test of updateContractToRebuild method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testUpdateContractToRebuild() throws Exception {
        System.out.println("updateContractToRebuild");
        Session session = getController().getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
        int contractMasterSid = 1279;
        gtnWsContractDashboardProcessLogic.updateContractToRebuild(session, contractMasterSid);
        session.close();
        assertFalse(contractMasterSid ==0);

    }

    /**
     * Test of updateContractToDForRebuild method, of class GtnWsContractDashboardProcessLogic.
     */
//    @Test
//    public void testUpdateContractToDForRebuild() {
//        System.out.println("updateContractToDForRebuild");
//        ContractMaster contractMaster = null;
//        Session session = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        instance.updateContractToDForRebuild(contractMaster, session);
//    }
//
//    /**
//     * Test of updateCFPforContract method, of class GtnWsContractDashboardProcessLogic.
//     */
//    @Test
//    public void testUpdateCFPforContract() {
//        System.out.println("updateCFPforContract");
//        ContractMaster contractMaster = null;
//        Session session = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        instance.updateCFPforContract(contractMaster, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateIFPforContract method, of class GtnWsContractDashboardProcessLogic.
//     */
//    @Test
//    public void testUpdateIFPforContract() {
//        System.out.println("updateIFPforContract");
//        ContractMaster contractMaster = null;
//        Session session = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        instance.updateIFPforContract(contractMaster, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updatePSforContract method, of class GtnWsContractDashboardProcessLogic.
//     */
//    @Test
//    public void testUpdatePSforContract() {
//        System.out.println("updatePSforContract");
//        ContractMaster contractMaster = null;
//        Session session = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        instance.updatePSforContract(contractMaster, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateRSforContract method, of class GtnWsContractDashboardProcessLogic.
//     */
//    @Test
//    public void testUpdateRSforContract() {
//        System.out.println("updateRSforContract");
//        ContractMaster contractMaster = null;
//        Session session = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        instance.updateRSforContract(contractMaster, session);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getContractDashboardRebuildTreeData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetContractDashboardRebuildTreeData() throws Exception {
        System.out.println("getContractDashboardRebuildTreeData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("120");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1252);
			treeBean.addProperties(1);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
			treeBean.addProperties(15184);
                        treeBean.addProperties(16125);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        gtnWsContractDashboardRequest.setContractId(1252);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getContractDashboardRebuildTreeData(gtnWsRequest,gtnResponse);
        assertFalse(treeBean==null);
    }

    /**
     * Test of getContractMasterAndUpdate method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetContractMasterAndUpdate() throws Exception {
        System.out.println("getContractMasterAndUpdate");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("120");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1252);
			treeBean.addProperties(1);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
			treeBean.addProperties(15184);
                        treeBean.addProperties(16125);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        gtnWsContractDashboardRequest.setContractId(1252);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnWsContractDashboardResponse cdResponse=new GtnWsContractDashboardResponse();
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getContractMasterAndUpdate", GtnUIFrameworkWebserviceRequest.class,GtnWsContractDashboardResponse.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,gtnWsRequest,cdResponse);
        assertFalse(treeBean==null);
    }

    /**
     * Test of detectChildrenAllowed method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testDetectChildrenAllowed() {
        try{
        System.out.println("detectChildrenAllowed");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("120");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1252);
			treeBean.addProperties(1);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
			treeBean.addProperties(15184);
                        treeBean.addProperties(16125);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        gtnWsContractDashboardRequest.setContractId(1252);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("detectChildrenAllowed", GtnWsRecordBean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,treeBean);
        assertFalse(treeBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getRebuildTreeData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetRebuildTreeData() throws Exception {
        System.out.println("getRebuildTreeData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("120");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1252);
			treeBean.addProperties(1);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
			treeBean.addProperties(15184);
                        treeBean.addProperties(16125);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        gtnWsContractDashboardRequest.setContractId(1252);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getRebuildTreeData", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,gtnWsSearchRequest);
        assertFalse(treeBean==null);
    }

    /**
     * Test of getModifiedBeanForTree method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetModifiedBeanForTree() {
        try{
        System.out.println("getModifiedBeanForTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("120");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("CONTRACT_GCM 1");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1252);
			treeBean.addProperties(1);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
			treeBean.addProperties(15184);
                        treeBean.addProperties(16125);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        gtnWsContractDashboardRequest.setContractId(1252);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getModifiedBeanForTree", GtnWsRecordBean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,treeBean);
        assertFalse(treeBean==null);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProcessTreeSearchCount method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetProcessTreeSearchCount() throws Exception {
  System.out.println("getProcessTreeSearchCount");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
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
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getProcessTreeSearchCount", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,gtnWsSearchRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of getCountQuery method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetCountQuery() {
//        System.out.println("getCountQuery");
//        int queryNo = 0;
//        String[] input = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        String expResult = "";
//        String result = instance.getCountQuery(queryNo, input);
//        assertEquals(expResult, result);
    try{
        System.out.println("getCountQuery");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);
        
        String memberLevel = GtnFrameworkWebserviceConstant.CFPCO_CFP_CONTRACT_SID;
        List<Object> countInputlist = new ArrayList<>();
        countInputlist.add("1");
        int queryIndex = 3;
        String conId = "=" + 1248;
        
        String[] input = {"=" + 1248,GtnFrameworkWebserviceConstant.CFPCO_CFP_CONTRACT_SID};
        
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
  
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("Contract");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("CONTRACT_3");
			treeBean.addProperties("1");
			treeBean.addProperties("1");
			treeBean.addProperties(1248);
			treeBean.addProperties(3);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(1);
                        treeBean.addProperties(4);
			treeBean.addProperties(13885);
                        treeBean.addProperties(null);
                        treeBean.addProperties(false);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
                        treeBean.addProperties(0);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);

        treeBean.setRecordHeader(Arrays.asList("category","id","no","name"));
        gtnWsSearchRequest.setParentBean(treeBean);
        
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getCountQuery", int.class,String[].class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,queryIndex,input);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of validateContractToProcess method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testValidateContractToProcess() throws Exception {
        System.out.println("validateContractToProcess");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnWsContractDashboardResponse cdResponse = new GtnWsContractDashboardResponse();
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnResponse.setGtnWsContractDashboardResponse(cdResponse);
        gtnWsContractDashboardProcessLogic.validateContractToProcess(gtnWsContractDashboardRequest,cdResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of checkContractHasActuals method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testCheckContractHasActuals() throws Exception {
        System.out.println("checkContractHasActuals");
        int contractSid = 1248;
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("checkContractHasActuals", int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,contractSid);
        assertFalse(contractSid==0);
    }

    /**
     * Test of findContractUsedInProjections method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testFindContractUsedInProjections() throws Exception {
        System.out.println("findContractUsedInProjections");
        int contractSid = 1248;
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("findContractUsedInProjections", int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,contractSid);
        assertFalse(contractSid==0);
    }

    /**
     * Test of checkContractUsedInProjection method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testCheckContractUsedInProjection() throws Exception {
        System.out.println("checkContractUsedInProjection");
        int contractSid = 1248;
        String tableName = "NM_SALES_PROJECTION_MASTER";
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("checkContractUsedInProjection", int.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,contractSid,tableName);
        assertFalse(contractSid==0);
    }

    /**
     * Test of deleteAllTempCfpOnLoad method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testDeleteAllTempCfpOnLoad() throws Exception {
        System.out.println("deleteAllTempCfpOnLoad");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.deleteAllTempCfpOnLoad(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of deleteAllTempCfpOnBack method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testDeleteAllTempCfpOnBack() throws Exception {
        System.out.println("deleteAllTempCfpOnBack");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();         
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.deleteAllTempCfpOnBack(gtnWsRequest,gtnResponse);
        assertFalse(generalRequest.getSessionId().toString().isEmpty());
    }

    /**
     * Test of addAllTempCfpOnLoad method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testAddAllTempCfpOnLoad() throws Exception {
//        System.out.println("addAllTempCfpOnLoad");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnUIFrameworkWebserviceResponse gtnResponse = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.addAllTempCfpOnLoad(gtnWsRequest, gtnResponse);
//        assertEquals(expResult, result);
        System.out.println("addAllTempCfpOnLoad");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2015-05-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.addAllTempCfpOnLoad(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of deleteAllTempIfpOnLoad method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testDeleteAllTempIfpOnLoad() throws Exception {
        System.out.println("deleteAllTempIfpOnLoad");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.deleteAllTempIfpOnLoad(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of deleteAllTempIfpOnBack method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testDeleteAllTempIfpOnBack() throws Exception {
        System.out.println("deleteAllTempIfpOnBack");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();         
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.deleteAllTempIfpOnBack(gtnWsRequest,gtnResponse);
        assertFalse(generalRequest.getSessionId().toString().isEmpty());
    }

    /**
     * Test of addAllTempIfpOnLoad method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testAddAllTempIfpOnLoad() throws Exception {
        System.out.println("addAllTempIfpOnLoad");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2015-05-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.addAllTempIfpOnLoad(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getQueryNameToLoadTempIFP method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetQueryNameToLoadTempIFP() {
try{
        System.out.println("getQueryNameToLoadTempIFP");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2015-05-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        
        
        List<String> inputList = new ArrayList<>();
        inputList.add(generalRequest.getUserId());
	inputList.add(generalRequest.getSessionId());
	inputList.add(gtnWsContractDashboardRequest.getSessionDate());
	inputList.add(" =" + gtnWsContractDashboardRequest.getContractId());
        
        
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getQueryNameToLoadTempIFP", List.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,inputList,gtnWsContractDashboardRequest);  
        //gtnWsContractDashboardProcessLogic.getQueryNameToLoadTempIFP(inputList,gtnWsContractDashboardRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getQueryNameToLoadImtdItemPriceRebateDetails method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetQueryNameToLoadImtdItemPriceRebateDetails() {
    try{
        System.out.println("getQueryNameToLoadImtdItemPriceRebateDetails");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2015-05-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        
        
        List<String> inputList = new ArrayList<>();
        inputList.add(generalRequest.getUserId());
	inputList.add(generalRequest.getSessionId());
	inputList.add(gtnWsContractDashboardRequest.getSessionDate());
	inputList.add(" =" + gtnWsContractDashboardRequest.getContractId());
        
        
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getQueryNameToLoadImtdItemPriceRebateDetails", List.class,GtnWsContractDashboardRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic,inputList,gtnWsContractDashboardRequest);  
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getQueryInputBasedOnValue method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetQueryInputBasedOnValue() {
        System.out.println("getQueryInputBasedOnValue");
        int value = 5;
        gtnWsContractDashboardProcessLogic.getQueryInputBasedOnValue(value);
        assertFalse(value==0);
    }

    /**
     * Test of addRuleForRSOnLoad method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testAddRuleForRSOnLoad() throws Exception {
//        System.out.println("addRuleForRSOnLoad");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnUIFrameworkWebserviceResponse gtnResponse = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.addRuleForRSOnLoad(gtnWsRequest, gtnResponse);
//        assertEquals(expResult, result);
        System.out.println("addRuleForRSOnLoad");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
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
        gtnWsContractDashboardRequest.setSessionDate("2015-05-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.addRuleForRSOnLoad(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of processContractInfoToSession method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testProcessContractInfoToSession() throws Exception {
        System.out.println("processContractInfoToSession");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
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
        gtnWsContractDashboardRequest.setSessionDate("2015-05-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.processContractInfoToSession(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of deleteContractInfoOnBackProcess method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testDeleteContractInfoOnBackProcess() throws Exception {
        System.out.println("deleteContractInfoOnBackProcess");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("194");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();         
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.deleteContractInfoOnBackProcess(gtnWsRequest,gtnResponse);
        assertFalse(generalRequest.getSessionId().toString().isEmpty());
    }

    /**
     * Test of getContractInfoFieldData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetContractInfoFieldData() throws Exception {
        System.out.println("getContractInfoFieldData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("796");
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
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getContractInfoFieldData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getNotesTabInfoData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetNotesTabInfoData() throws Exception {
        System.out.println("getNotesTabInfoData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("796");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse gtnWsContractDashboardResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getNotesTabInfoData(gtnWsContractDashboardRequest,gtnWsContractDashboardResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getCDAliasInfoTableData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetCDAliasInfoTableData() throws Exception {
//        System.out.println("getCDAliasInfoTableData");
//        GtnWsContractDashboardRequest cdRequest = null;
//        GtnWsContractDashboardResponse cdResponse = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        GtnWsContractDashboardResponse expResult = null;
//        GtnWsContractDashboardResponse result = instance.getCDAliasInfoTableData(cdRequest, cdResponse);
//        assertEquals(expResult, result);
        System.out.println("getCDAliasInfoTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("796");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardResponse gtnWsContractDashboardResponse=new GtnWsContractDashboardResponse();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getCDAliasInfoTableData(gtnWsContractDashboardRequest,gtnWsContractDashboardResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getCompanyInfoFieldData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetCompanyInfoFieldData() throws Exception {
        System.out.println("getCompanyInfoFieldData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("487");
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
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getCompanyInfoFieldData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getItemInfoFieldData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetItemInfoFieldData() throws Exception {
        System.out.println("getItemInfoFieldData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("941");
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
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getItemInfoFieldData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getPricingInfoFieldData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetPricingInfoFieldData() throws Exception {
//        System.out.println("getPricingInfoFieldData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnUIFrameworkWebserviceResponse gtnResponse = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getPricingInfoFieldData(gtnWsRequest, gtnResponse);
//        assertEquals(expResult, result);
        System.out.println("getPricingInfoFieldData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("941");
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
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getPricingInfoFieldData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getRebateInfoFieldData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetRebateInfoFieldData() throws Exception {
        System.out.println("getRebateInfoFieldData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("941");
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
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getRebateInfoFieldData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getContractDashboardProcessTreeExcelData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetContractDashboardProcessTreeExcelData() throws Exception {
        System.out.println("getContractDashboardProcessTreeExcelData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("697");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
       
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardProcessLogic.getContractDashboardProcessTreeExcelData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getContractDashboardProcessTreeRecursiveExcelData method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetContractDashboardProcessTreeRecursiveExcelData() throws Exception {
//        System.out.println("getContractDashboardProcessTreeRecursiveExcelData");
//        GtnWsSearchRequest searchRequest = null;
//        List<GtnWsRecordBean> records = null;
//        GtnWsContractDashboardProcessLogic instance = null;
//        List<GtnWsRecordBean> expResult = null;
//        List<GtnWsRecordBean> result = instance.getContractDashboardProcessTreeRecursiveExcelData(searchRequest, records);
//        assertEquals(expResult, result);
        System.out.println("getContractDashboardProcessTreeRecursiveExcelData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("697");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("Contract");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
       
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        List<GtnWsRecordBean> records = new ArrayList<>();
        gtnWsContractDashboardProcessLogic.getContractDashboardProcessTreeRecursiveExcelData(gtnWsSearchRequest,records);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getSqlQueryEngine method, of class GtnWsContractDashboardProcessLogic.
     */
    @Test
    public void testGetSqlQueryEngine() {
    try{
        System.out.println("getSqlQueryEngine");
        Method method = gtnWsContractDashboardProcessLogic.getClass().getDeclaredMethod("getSqlQueryEngine");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardProcessLogic);      
        assertFalse(method.getParameterCount()!=0);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardProcessLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
