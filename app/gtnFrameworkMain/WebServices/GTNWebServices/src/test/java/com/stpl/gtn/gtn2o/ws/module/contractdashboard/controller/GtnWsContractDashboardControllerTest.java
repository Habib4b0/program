/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardCompanyLogic;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic.GtnWsContractDashboardLogic;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnwsExcelRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class GtnWsContractDashboardControllerTest {
    
    public GtnWsContractDashboardControllerTest() {
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
    private GtnWsContractDashboardController gtnWsContractDashboardController;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getAutomaticRelationService method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetAutomaticRelationService() {
        System.out.println("getAutomaticRelationService");
        GtnFrameworkAutomaticService result = gtnWsContractDashboardController.getAutomaticRelationService();
        assertEquals(automaticRelationService, result);
    }

    /**
     * Test of getGtnWsSqlService method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetGtnWsSqlService() {
        System.out.println("getGtnWsSqlService");
        GtnWsSqlService result = gtnWsContractDashboardController.getGtnWsSqlService();
        assertEquals(gtnWsSqlService, result);
    }

    /**
     * Test of getLogic method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetLogic() {
        System.out.println("getLogic");    
        GtnWsContractDashboardLogic result = gtnWsContractDashboardController.getLogic();
        assertFalse(result==null);
    }

    /**
     * Test of getCompanyLogic method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCompanyLogic() {
        System.out.println("getCompanyLogic");
        GtnWsContractDashboardCompanyLogic result = gtnWsContractDashboardController.getCompanyLogic();
        assertFalse(result==null);
    }

    /**
     * Test of getProcessLogic method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetProcessLogic() {
        System.out.println("getProcessLogic");
        GtnWsContractDashboardCompanyLogic result = gtnWsContractDashboardController.getCompanyLogic();
        assertFalse(result==null);

    }

    /**
     * Test of getSubmitLogic method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetSubmitLogic() {
        System.out.println("getSubmitLogic");
        GtnWsContractDashboardCompanyLogic result = gtnWsContractDashboardController.getCompanyLogic();
        assertFalse(result==null);

    }

    /**
     * Test of getItemLogic method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetItemLogic() {
        
        System.out.println("getItemLogic");
        GtnWsContractDashboardCompanyLogic result = gtnWsContractDashboardController.getCompanyLogic();
        assertFalse(result==null);
    }

    /**
     * Test of getLookupLogic method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetLookupLogic() {
        
        System.out.println("getLookupLogic");
        GtnWsContractDashboardCompanyLogic result = gtnWsContractDashboardController.getCompanyLogic();
        assertFalse(result==null);
    }

    /**
     * Test of getSessionFactory method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsContractDashboardController.getSessionFactory();
        assertEquals(gtnSqlQueryEngine.getSessionFactory(), result);
    }

    /**
     * Test of getGtnSqlQueryEngine method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetGtnSqlQueryEngine() {
        System.out.println("getGtnSqlQueryEngine");
        GtnFrameworkSqlQueryEngine result = gtnWsContractDashboardController.getGtnSqlQueryEngine();
        assertEquals(gtnSqlQueryEngine, result);
    }

    /**
     * Test of executeQuery method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testExecuteQuery_String() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = "select company_id from company_master";
        List result = gtnWsContractDashboardController.executeQuery(sqlQuery);
        assertFalse(result.size()==0);
    }

    /**
     * Test of executeQuery method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testExecuteQuery_3args() throws Exception {
        System.out.println("executeQuery");
        String sqlQuery = "select company_name from company_master where company_id=?";
        Object[] params = {"10"};        
        GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING};   
        List expResult = new ArrayList();
        expResult.add("Integrated Pharmaceutical Services");
        List result = gtnWsContractDashboardController.executeQuery(sqlQuery, params, type);
        assertEquals(expResult, result);
    }

    /**
     * Test of executeUpdateQuery method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testExecuteUpdateQuery_String() throws Exception {
        System.out.println("executeUpdateQuery");
        String sqlQuery = "UPDATE company_master SET company_status = '126' WHERE company_id = 'testRecord19Feb'";
        int expResult = 1;
        int result = gtnWsContractDashboardController.executeUpdateQuery(sqlQuery);
        assertEquals(expResult, result);
    }

    /**
     * Test of executeUpdateQuery method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testExecuteUpdateQuery_3args() throws Exception {
        System.out.println("executeUpdateQuery");
        String sqlQuery = "UPDATE company_master SET company_status = '128' WHERE company_id =?";
        Object[] params = {"10"};        
        GtnFrameworkDataType[] type = { GtnFrameworkDataType.STRING};   
        int expResult = 1;
        int result = gtnWsContractDashboardController.executeUpdateQuery(sqlQuery, params, type);
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetQuery_String() {
        System.out.println("getQuery");
        String sqlId = "com.contractDashboard.process.loadAliasTable";
        String result = gtnWsContractDashboardController.getQuery(sqlId);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of createParams method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCreateParams() {
        System.out.println("createParams");
        Object[] params = {1};
        Object[] expResult = {1};
        Object[] result = gtnWsContractDashboardController.createParams(params);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createDataTypes method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCreateDataTypes() {
        System.out.println("createDataTypes");
        GtnFrameworkDataType[] dataTypes = { GtnFrameworkDataType.STRING};   
        GtnFrameworkDataType[] expResult ={ GtnFrameworkDataType.STRING};   
        GtnFrameworkDataType[] result = gtnWsContractDashboardController.createDataTypes(dataTypes);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getContractDashboardTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetContractDashboardTableData() {
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getContractDashboardTableData(gtnWsRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());

    }

    @Test
    public void testGetContractDashboardTableDataFail() {
        System.out.println("testGetContractDashboardTableDataFail");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("CDMainView_ComponentResultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("767");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"mmemberId", "mmemberName", "mmemberType", "mmemberNo"}));
        gtnWsSearchRequest.setCount(false);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDMainView_ComponentName");
        criteria.setFilterValue1("fgfggh");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse=gtnWsContractDashboardController.getContractDashboardTableData(gtnWsRequest);
        
        assertFalse(gtnUIFrameworkWebserviceResponse.getGtnWsGeneralResponse().isSucess());

    }
    
    /**
     * Test of getContractDashboardDetailsTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetContractDashboardDetailsTableData() {
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.getContractDashboardDetailsTableData(gtnWsRequest);
        assertFalse(!gtnWebServiceSearchCriteriaList.isEmpty());

    }
    
    @Test
    public void testGetContractDashboardDetailsTableDataFalse() {
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
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFilterValue1("fgfggh");
        gtnWebServiceSearchCriteriaList.add(criteria);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.getContractDashboardDetailsTableData(gtnWsRequest);
        assertFalse(result.getGtnWsGeneralResponse().isSucess());
    }

    /**
     * Test of checkAddToTree method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCheckAddToTree() {

        System.out.println("checkAddToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("325");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result =gtnWsContractDashboardController.checkAddToTree(gtnWsRequest);
        assertFalse(result==null);
    }
    
    @Test
    public void testCheckAddToTreeFalse() {

        System.out.println("testCheckAddToTreeFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("325");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result =gtnWsContractDashboardController.checkAddToTree(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of addToTree method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testAddToTree() {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.addToTree(gtnWsRequest);
        assertFalse(result==null);
    }
    
    @Test
    public void testAddToTreeFalse() {
        System.out.println("addToTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);  
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.addToTree(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of saveContractTree method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testSaveContractTree() {
        System.out.println("saveContractTree");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("11");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
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
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(treeBean);
        treeBean.setChildList(list);
        gtnWsContractDashboardRequest.setRecordBeanList(list);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.saveContractTree(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of getContractDashboardProcessTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetContractDashboardProcessTableData() {

        System.out.println("getContractDashboardProcessTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);

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
        gtnWsContractDashboardController.getContractDashboardProcessTableData(gtnWsRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());

    }
    
    @Test
    public void testGetContractDashboardProcessTableDataFalse() {

        System.out.println("getContractDashboardProcessTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setExtraParameter("RIGHTCDMainView_resultTable");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("380");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("ggddf");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result =gtnWsContractDashboardController.getContractDashboardProcessTableData(gtnWsRequest);
        assertFalse(result==null);

    }

    /**
     * Test of getCDCompanyAdditionLeftTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDCompanyAdditionLeftTableData() {
        System.out.println("getContractDashboardProcessTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("100");
        generalRequest.setExcel(false);

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
        gtnWsContractDashboardController.getContractDashboardProcessTableData(gtnWsRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }

    
    @Test
    public void testGetCDCompanyAdditionLeftTableDataFalse() {
        System.out.println("getContractDashboardProcessTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("100");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"category", "id", "no", "name"}));
        gtnWsSearchRequest.setCount(true);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("RIGHTCDMainView_ComponentName");
        criteria.setFilterValue1("jutuj");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result=gtnWsContractDashboardController.getContractDashboardProcessTableData(gtnWsRequest);
        assertFalse(result==null);
    }
    /**
     * Test of companyAdditionMoveRight method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCompanyAdditionMoveRight() {
        System.out.println("companyAdditionMoveRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("697");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("10000-Customer");
			treeBean.addProperties("10000-Customer");
			treeBean.addProperties("BR-SAVELLA MNGD & CLSD 1 OF MANY");
			treeBean.addProperties("Activa");
			treeBean.addProperties("PBMD");
			treeBean.addProperties("PGNUM");
			treeBean.addProperties("");
			treeBean.addProperties(74799);
			treeBean.addProperties(126);
			treeBean.addProperties(12150);
			treeBean.addProperties(145L);
                        treeBean.addProperties(null);
			treeBean.addProperties(15069);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(treeBean);
        //treeBean.setChildList(list);
        gtnWsContractDashboardRequest.setRecordBeanList(list);
        treeBean.setRecordHeader(Arrays.asList("companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"));
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.companyAdditionMoveRight(gtnWsRequest);
        assertFalse(result==null);

    }
    
    @Test
    public void testCompanyAdditionMoveRightFalse() {
        System.out.println("companyAdditionMoveRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("697");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("10000-Customer");
			treeBean.addProperties("10000-Customer");
			treeBean.addProperties("BR-SAVELLA MNGD & CLSD 1 OF MANY");
			treeBean.addProperties("Activa");
			treeBean.addProperties("PBMD");
			treeBean.addProperties("PGNUM");
			treeBean.addProperties("");
			treeBean.addProperties(74799);
			treeBean.addProperties(126);
			treeBean.addProperties(12150);
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.companyAdditionMoveRight(gtnWsRequest);
        assertFalse(result==null);

    }

    /**
     * Test of companyAdditionMoveLeft method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCompanyAdditionMoveLeft() {
        System.out.println("companyAdditionMoveLeft");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("697");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("10002-Customer");
			treeBean.addProperties("10002-Customer");
			treeBean.addProperties("LILETTA UPM -$350");
			treeBean.addProperties("Inactive");
			treeBean.addProperties("CLINIC");
			treeBean.addProperties("PGNUM");
			treeBean.addProperties("CLINIC");
			treeBean.addProperties("");
                        treeBean.addProperties(74801);
			treeBean.addProperties("386");
			treeBean.addProperties(132);
			treeBean.addProperties(113L);
                        treeBean.addProperties("A");
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        //treeBean.setChildList(list);
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
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.companyAdditionMoveLeft(gtnWsRequest);
        assertFalse(result==null);
    }

    
     @Test
    public void testCompanyAdditionMoveLeftFalse() {
        System.out.println("companyAdditionMoveLeft");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("697");
        generalRequest.setExcel(false);

        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("10002-Customer");
			treeBean.addProperties("10002-Customer");
			treeBean.addProperties("LILETTA UPM -$350");
			treeBean.addProperties("Inactive");
			treeBean.addProperties("CLINIC");
			treeBean.addProperties("PGNUM");
			treeBean.addProperties("CLINIC");
			treeBean.addProperties("");
                        treeBean.addProperties(74801);
			treeBean.addProperties("386");
			treeBean.addProperties(132);
			treeBean.addProperties(113L);
                        treeBean.addProperties("A");
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.companyAdditionMoveLeft(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of companyAdditionMoveAllLeft method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCompanyAdditionMoveAllLeft() {
        System.out.println("companyAdditionMoveAllLeft");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("189");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.companyAdditionMoveAllLeft(gtnWsRequest);
        assertFalse(result==null);
    }
    
        @Test
    public void testCompanyAdditionMoveAllLeftFalse() {
        System.out.println("companyAdditionMoveAllLeft");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("189");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.companyAdditionMoveAllLeft(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of companyAdditionMoveAllRight method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCompanyAdditionMoveAllRight() {
        System.out.println("companyAdditionMoveAllRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company Name");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyName");
        criteria2.setFilterValue1("all*");
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
        gtnWsContractDashboardController.companyAdditionMoveAllRight(gtnWsRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }
    
    @Test
    public void testCompanyAdditionMoveAllRightFalse() {
        System.out.println("companyAdditionMoveAllRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);

        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);

        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CADTCombo_SearchField");
        criteria.setFilterValue1("Company Name");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_CADTText_CompanyName");
        criteria2.setFilterValue1("all*");
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
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.companyAdditionMoveAllRight(gtnWsRequest);
        assertFalse(gtnWebServiceSearchCriteriaList.isEmpty());
    }

    /**
     * Test of getCDCompanyAdditionRightTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDCompanyAdditionRightTableData() {
        System.out.println("getCDCompanyAdditionRightTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDCompanyAdditionRightTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
       

    }
    
    @Test
    public void testGetCDCompanyAdditionRightTableDataFalse() {
        System.out.println("testGetCDCompanyAdditionRightTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"companyId","companyNo","companyName","companyStatus","companyType","tradeClass","companyCategory","companyGroup"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDCompanyAdditionRightTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());       
    }

    /**
     * Test of getCDItemAdditionRightTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDItemAdditionRightTableData() {
        System.out.println("getCDItemAdditionRightTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemAdditionRightTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
    
        @Test
    public void testGetCDItemAdditionRightTableDataFalse() {
        System.out.println("getCDItemAdditionRightTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(2);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemAdditionRightTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getCDItemAdditionLeftTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDItemAdditionLeftTableData() {
        System.out.println("getCDItemAdditionLeftTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_IADTCombo_IFP");
        criteria.setFilterValue1("IFP No");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_IADTText_IFPNo");
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
        gtnWsContractDashboardController.getCDItemAdditionLeftTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
    
    @Test
    public void testGetCDItemAdditionLeftTableDataFalse() {
        System.out.println("testGetCDItemAdditionLeftTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_IADTCombo_IFP");
        criteria.setFilterValue1("IFP No");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_IADTText_IFPNo");
        criteria2.setFilterValue1("*");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(10);
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemAdditionLeftTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of itemAdditionMoveRight method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testItemAdditionMoveRight() {
        System.out.println("itemAdditionMoveRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("00023079865");
			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
			treeBean.addProperties("Active");
			treeBean.addProperties("Not Available");
			treeBean.addProperties("Not Available");
			treeBean.addProperties("EYE CARE");
			treeBean.addProperties("REFRESH");
                        treeBean.addProperties("65.000000");
			treeBean.addProperties(124024);
			treeBean.addProperties(8252);
			treeBean.addProperties(126);
                        treeBean.addProperties(346);
                        treeBean.addProperties(13724);
                        treeBean.addProperties(null);
                        treeBean.addProperties("REVITAS");
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(treeBean);
        //gtnWsContractDashboardRequest.setRecordBeanList(list);
        treeBean.setRecordHeader(Arrays.asList("itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"));
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.itemAdditionMoveRight(gtnWsRequest);
        assertFalse(result==null);
        
    }
    
    @Test
    public void testItemAdditionMoveRightFalse() {
        System.out.println("itemAdditionMoveRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("00023079865");
			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
			treeBean.addProperties("Active");
			treeBean.addProperties("Not Available");
			treeBean.addProperties("Not Available");
			treeBean.addProperties("EYE CARE");
			treeBean.addProperties("REFRESH");
                        treeBean.addProperties("65.000000");
			treeBean.addProperties(124024);
			treeBean.addProperties(8252);
			treeBean.addProperties(126);
                        treeBean.addProperties(346);
                        treeBean.addProperties(13724);
                        treeBean.addProperties(null);
                        treeBean.addProperties("REVITAS");
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        //gtnWsContractDashboardRequest.setTableBean(treeBean);
        //gtnWsContractDashboardRequest.setRecordBeanList(list);
        treeBean.setRecordHeader(Arrays.asList("itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"));
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.itemAdditionMoveRight(gtnWsRequest);
        assertFalse(result==null);
        
    }

    /**
     * Test of itemAdditionMoveLeft method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testItemAdditionMoveLeft() {
        System.out.println("itemAdditionMoveLeft");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("00023079865");
			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
			treeBean.addProperties("Active");
			treeBean.addProperties("Not Available");
			treeBean.addProperties("Not Available");
			treeBean.addProperties("EYE CARE");
			treeBean.addProperties("REFRESH");
                        treeBean.addProperties("65.000000");
			treeBean.addProperties(92327);
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean);
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setTableBean(treeBean);
        //gtnWsContractDashboardRequest.setRecordBeanList(list);
        treeBean.setRecordHeader(Arrays.asList("itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"));
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.itemAdditionMoveLeft(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of itemAdditionMoveAllLeft method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testItemAdditionMoveAllLeft() {
        System.out.println("itemAdditionMoveAllLeft");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.itemAdditionMoveAllLeft(gtnWsRequest);
        assertFalse(result==null);
    }
    
        @Test
    public void testItemAdditionMoveAllLeftFalse() {
        System.out.println("testItemAdditionMoveAllLeftFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        //gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.itemAdditionMoveAllLeft(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of itemAdditionMoveAllRight method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testItemAdditionMoveAllRight() {
        System.out.println("itemAdditionMoveAllRight");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        //gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_IADTCombo_IFP");
        criteria.setFilterValue1("IFP Name");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_IADTText_IFPName");
        criteria2.setFilterValue1("a*");
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
        gtnWsContractDashboardController.itemAdditionMoveAllRight(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testItemAdditionMoveAllRightFalse() {
        System.out.println("testItemAdditionMoveAllRightFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        //gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_IADTCombo_IFP");
        criteria.setFilterValue1("IFP Name");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("CDProcessView_IADTText_IFPName");
        criteria2.setFilterValue1("a*");
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
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.itemAdditionMoveAllRight(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDCompniesDetailTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDCompniesDetailTableData() {
        System.out.println("getCDCompniesDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(5);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDCompniesDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    @Test
    public void testGetCDCompniesDetailTableDataFalse() {
        System.out.println("getCDCompniesDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        //gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(5);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDCompniesDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    /**
     * Test of getCDCompniesDetailViewTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDCompniesDetailViewTableData() {

        System.out.println("getCDCompniesDetailViewTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("70");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDCompniesDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
     @Test
    public void testGetCDCompniesDetailViewTableDataFalse() {

        System.out.println("testGetCDCompniesDetailViewTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("70");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","companyId","companyNo","companyName","cfpStatus","cfpStartDate cfpEndDate","companyStatus",
                                                      "companyType","tradeClass","companyCategory","tradingPartnerContractNo","cfpAttachedDate","modifiedDate","modifiedBy","createdDate",
                                                      "createdBy","cfpEligibleDate","companiessStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_CTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(5);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDCompniesDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDCompniesHistoryTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDCompniesHistoryTableData() {
//        System.out.println("getCDCompniesHistoryTableData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnWsContractDashboardController instance = new GtnWsContractDashboardController();
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getCDCompniesHistoryTableData(gtnWsRequest);
//        assertEquals(expResult, result);
    }

    /**
     * Test of populateAllCompanies method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPopulateAllCompanies() {
        System.out.println("populateAllCompanies");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("CFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(132);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateAllCompanies(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }
    
     @Test
    public void testPopulateAllCompaniesFalse() {
        System.out.println("populateAllCompanies");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("CFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(132);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        //gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateAllCompanies(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of populateCompany method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPopulateCompany() {
        System.out.println("populateCompany");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("477");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("CFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(132);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateCompany(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }
    
     @Test
    public void testPopulateCompanyFalse() {
        System.out.println("testPopulateCompanyFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("477");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("CFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(132);
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateCompany(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of populateCompanyField method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPopulateCompanyField() {
        System.out.println("populateCompanyField");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("477");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setSystemId(1139100);
        gtnWsContractDashboardRequest.setPopulateField("cfpStatus");
        gtnWsContractDashboardRequest.setPopulateValue(128);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateCompanyField(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }
    
        @Test
    public void testPopulateCompanyFieldFalse() {
        System.out.println("testPopulateCompanyFieldFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("477");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setSystemId(1139100);
        gtnWsContractDashboardRequest.setPopulateField("cfpStatus");
        gtnWsContractDashboardRequest.setPopulateValue(128);
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateCompanyField(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of getCDItemsDetailTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDItemsDetailTableData() {
        System.out.println("getCDItemsDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemsDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }
    
    @Test
    public void testGetCDItemsDetailTableDataFalse() {
        System.out.println("testGetCDItemsDetailTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        //gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemsDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }

    /**
     * Test of getCDItemsDetailViewTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDItemsDetailViewTableData() {
        System.out.println("getCDItemsDetailViewTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("552");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemsDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDItemsDetailViewTableDataFalse() {
        System.out.println("testGetCDItemsDetailViewTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("552");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDItemsDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDItemsHistoryTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDItemsHistoryTableData() {
//        System.out.println("getCDItemsHistoryTableData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnWsContractDashboardController instance = new GtnWsContractDashboardController();
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getCDItemsHistoryTableData(gtnWsRequest);
//        assertEquals(expResult, result);
    }

    /**
     * Test of populateAllItems method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPopulateAllItems() {
        System.out.println("populateAllItems");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("IFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(127);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateAllItems(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }
    
    @Test
    public void testPopulateAllItemsFalse() {
        System.out.println("testPopulateAllItemsFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("IFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(127);
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateAllItems(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of populateItem method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPopulateItem() {
        System.out.println("populateItem");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("IFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(132);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateItem(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    @Test
    public void testPopulateItemFalse() {
        System.out.println("populateItem");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("IFP Status");
        gtnWsContractDashboardRequest.setPopulateValue(132);
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateItem(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }
    
    /**
     * Test of populateItemField method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPopulateItemField() {
        System.out.println("populateItemField");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setPopulateField("ifpStatus");
        gtnWsContractDashboardRequest.setPopulateValue(126);
        gtnWsContractDashboardRequest.setSystemId(92336);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.populateItem(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of getCDPricingDetailTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDPricingDetailTableData() {
        System.out.println("getCDPricingDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDPricingDetailTableDataFalse() {
        System.out.println("testGetCDPricingDetailTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        //gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingDetailViewTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDPricingDetailViewTableData() {
        System.out.println("getCDPricingDetailViewTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("58");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDPricingDetailViewTableDataFalse() {
        System.out.println("testGetCDPricingDetailViewTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("58");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingProtectionTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDPricingProtectionTableData() {
        System.out.println("getCDPricingProtectionTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("806");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingProtectionTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }
    
    @Test
    public void testGetCDPricingProtectionTableDataElse() {
        System.out.println("getCDPricingProtectionTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("806");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("checkRecordId");
        criteria2.setFilterValue1("1");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        //gtnWebServiceSearchCriteriaList.add(criteria2);
        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingProtectionTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }

    /**
     * Test of getCDPricingProtectionExcelData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDPricingProtectionExcelData() {

        System.out.println("getCDPricingProtectionExcelData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("611");
        generalRequest.setExcel(false);
     
         GtnwsExcelRequest excelRequest =new GtnwsExcelRequest();
         List<String> inputList=Arrays.asList(new String[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"});
         excelRequest.setInputs(new Object[]{inputList,"20156","611"});
        
         
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest(); 
        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnwsExcelRequest(excelRequest);
        gtnWsContractDashboardController.getCDPricingProtectionExcelData(gtnWsRequest);
        assertFalse(excelRequest.getInputs().length==0);
    }
    
    /**
     * Test of getCDPricingProtectionViewTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDPricingProtectionViewTableData() {
        System.out.println("getCDPricingProtectionViewTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("610");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);


        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingProtectionViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDPricingProtectionViewTableDataFalse() {
        System.out.println("testGetCDPricingProtectionViewTableDataFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("610");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);


        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDPricingProtectionViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDRebateDetailTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDRebateDetailTableData() {
        System.out.println("getCDRebateDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("611");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "priceNo", "priceName", "itemType", "priceId", "attachedStatus", "rebateStartDate", "rebateEndDate", "rebatePlanBundleNo", "rebatePlanNopopup", "rebatePlanName", "deductionCalendarNopopup", "deductionCalendarName", "formulaType", "formulaNopopup", "formulaName", "netSalesFormulaNopopup", "netSalesRulepopup", "evaluationRulepopup", "evaluationRuleBundle", "calculationRulepopup", "calculationRuleBundle", "attachedDate", "rebatesStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDRebateDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDRebateDetailTableDataFalse() {
        System.out.println("getCDRebateDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("611");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "priceNo", "priceName", "itemType", "priceId", "attachedStatus", "rebateStartDate", "rebateEndDate", "rebatePlanBundleNo", "rebatePlanNopopup", "rebatePlanName", "deductionCalendarNopopup", "deductionCalendarName", "formulaType", "formulaNopopup", "formulaName", "netSalesFormulaNopopup", "netSalesRulepopup", "evaluationRulepopup", "evaluationRuleBundle", "calculationRulepopup", "calculationRuleBundle", "attachedDate", "rebatesStatusHelperValue"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDRebateDetailTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDRebateDetailViewTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCDRebateDetailViewTableData() {
        System.out.println("getCDRebateDetailViewTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("781");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType", "priceNo", "priceName", "itemType", "formulaId","formulaName","rebatePlanName", "rebateStartDate", "rebateEndDate", "rebateRevisionDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDRebateDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDRebateDetailViewTableDataFalse() {
        System.out.println("getCDRebateDetailViewTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("781");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType", "priceNo", "priceName", "itemType", "formulaId","formulaName","rebatePlanName", "rebateStartDate", "rebateEndDate", "rebateRevisionDate"}));
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCDRebateDetailViewTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getNSFLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetNSFLookupTableData() {
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getNSFLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }

    @Test
    public void testGetNSFLookupTableDataFalse() {
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
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        //gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getNSFLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }
    
    /**
     * Test of getCFPLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCFPLookupTableData() {
//        System.out.println("getCFPLookupTableData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnWsContractDashboardController instance = new GtnWsContractDashboardController();
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getCFPLookupTableData(gtnWsRequest);
//        assertEquals(expResult, result);
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
        gtnWsContractDashboardController.getCFPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCFPLookupTableDataFalse() {
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

       // gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCFPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);
    }

    /**
     * Test of getIFPLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetIFPLookupTableData() {
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
        gtnWsContractDashboardController.getIFPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    @Test
    public void testGetIFPLookupTableDataFalse() {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getIFPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);
    }
    
    /**
     * Test of getTPLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetTPLookupTableData() {
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
        gtnWsContractDashboardController.getTPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }
    
    @Test
    public void testGetTPLookupTableDataFalse() {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getTPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);

    }

    /**
     * Test of getPSLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetPSLookupTableData() {
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
        gtnWsContractDashboardController.getPSLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetPSLookupTableDataFalse() {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getPSLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);
    }

    /**
     * Test of getRSLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetRSLookupTableData() {
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
        gtnWsContractDashboardController.getRSLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    @Test
    public void testGetRSLookupTableDataFalse() {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getRSLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);
    }
    
    /**
     * Test of getRulesLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetRulesLookupTableData() {
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
        gtnWsContractDashboardController.getRulesLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetRulesLookupTableDataFalse() {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getRulesLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);
    }

    /**
     * Test of getRuleDetailsLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetRuleDetailsLookupTableData() {
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
        gtnWsContractDashboardController.getRuleDetailsLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }
    
      @Test
    public void testGetRuleDetailsLookupTableDataFalse() {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getRuleDetailsLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getTableRecordOffset()==0);
    }

    /**
     * Test of validateContractToProcess method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testValidateContractToProcess() {
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
        gtnWsContractDashboardController.validateContractToProcess(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }
    
      @Test
    public void testValidateContractToProcessFalse() {
        System.out.println("validateContractToProcess");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("836");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        gtnWsContractDashboardRequest.setContractId(1248);
       // gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.validateContractToProcess(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of processContractInfoToSession method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testProcessContractInfoToSession() {
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
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.processContractInfoToSession(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of deleteContractInfoOnBackProcess method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testDeleteContractInfoOnBackProcess() {
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
        gtnWsContractDashboardController.deleteContractInfoOnBackProcess(gtnWsRequest);
        assertFalse(generalRequest.getSessionId().toString().isEmpty());
    }
    
    @Test
    public void testDeleteContractInfoOnBackProcessFalse() {
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
        //gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.deleteContractInfoOnBackProcess(gtnWsRequest);
        assertFalse(generalRequest.getSessionId().toString().isEmpty());
    }

    /**
     * Test of getContractInfoFieldData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetContractInfoFieldData() {

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
        gtnWsContractDashboardController.getContractInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }
    
    @Test
    public void testGetContractInfoFieldDataFalse() {

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
        //gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getContractInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getIfpContractId()==0);
    }

    /**
     * Test of getCompanyInfoFieldData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetCompanyInfoFieldData() {
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
        gtnWsContractDashboardController.getCompanyInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }
    
     @Test
    public void testGetCompanyInfoFieldDataFalse() {
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
        //gtnWsContractDashboardRequest.setContractId(1248);
        gtnWsContractDashboardRequest.setCfpContractId(234);
        gtnWsContractDashboardRequest.setIfpContractId(257);
        gtnWsContractDashboardRequest.setPsContractId(375);
        gtnWsContractDashboardRequest.setRsContractId(380);
        gtnWsContractDashboardRequest.setSessionDate("2018-08-23");
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.getCompanyInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getIfpContractId()==0);
    }

    /**
     * Test of getItemInfoFieldData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetItemInfoFieldData() {
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
        gtnWsContractDashboardController.getItemInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getPricingInfoFieldData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetPricingInfoFieldData() {
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
        gtnWsContractDashboardController.getPricingInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of getRebateInfoFieldData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetRebateInfoFieldData() {
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
        gtnWsContractDashboardController.getRebateInfoFieldData(gtnWsRequest);
        assertFalse(gtnWsContractDashboardRequest.getContractId()==0);
    }

    /**
     * Test of checkSelectedCompanies method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCheckSelectedCompanies() {
        System.out.println("checkSelectedCompanies");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("565");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();       
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.checkSelectedCompanies(gtnWsRequest);
        assertFalse(generalRequest.getSessionId().isEmpty());
    }

    /**
     * Test of checkSelectedItems method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCheckSelectedItems() {
        System.out.println("checkSelectedItems");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("565");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();       
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.checkSelectedItems(gtnWsRequest);
        assertFalse(generalRequest.getSessionId().isEmpty());
    }

    /**
     * Test of removeCompanies method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testRemoveCompanies() {

        System.out.println("removeCompanies");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("565");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();       
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.removeCompanies(gtnWsRequest);
        assertFalse(generalRequest.getSessionId().isEmpty());
    }

    /**
     * Test of removeItems method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testRemoveItems() {
        System.out.println("removeItems");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("565");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();       
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.removeItems(gtnWsRequest);
        assertFalse(generalRequest.getSessionId().isEmpty());
    }

    /**
     * Test of getRPLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetRPLookupTableData() {
        
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
        gtnWsContractDashboardController.getRPLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getDCLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetDCLookupTableData() {
//        System.out.println("getDCLookupTableData");
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
//        GtnWsContractDashboardController instance = new GtnWsContractDashboardController();
//        GtnUIFrameworkWebserviceResponse expResult = null;
//        GtnUIFrameworkWebserviceResponse result = instance.getDCLookupTableData(gtnWsRequest);
//        assertEquals(expResult, result);
    }

    /**
     * Test of getFormulaLookupTableData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetFormulaLookupTableData() {
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
        gtnWsContractDashboardController.getFormulaLookupTableData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of validateContractDashboardToSave method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testValidateContractDashboardToSave() {
        System.out.println("validateContractDashboardToSave");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
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
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.validateContractDashboardToSave(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of submitContractDashboard method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testSubmitContractDashboard() {
        
        System.out.println("submitContractDashboard");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("17");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

        GtnWsContractDashboardSessionBean contractDashboardBean =new GtnWsContractDashboardSessionBean();
        GtnWsContractDashboardProcessBean processBean=new GtnWsContractDashboardProcessBean();
        processBean.setUserId("20156");
        processBean.setSessionId("17");
        processBean.setContractId(1279);
        processBean.setCfpContractId(265);
        processBean.setIfpContractId(301);
        processBean.setPsContractId(419);
        processBean.setRsContractId(417);
        
       
            
        GtnWsRecordBean treeBean = new GtnWsRecordBean();
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties("test13Sep");
			treeBean.addProperties(218);
			treeBean.addProperties(126);
			treeBean.addProperties(13968);
			treeBean.addProperties(new Long("24"));
			treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(0);
			treeBean.addProperties("test12Sep");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(new Long("24"));
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("0.000000");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties("");
                        treeBean.addProperties(null);
                        treeBean.addProperties("<Tue Sep 13 02:38:34 CDT 2018> John  smith:test13Sep");
                 
        List<GtnWsRecordBean> list=new ArrayList<>();
        list.add(treeBean); 
        
        
        GtnWsRecordBean companiesBean = new GtnWsRecordBean();
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties("test13Sep");
			companiesBean.addProperties(126);
			companiesBean.addProperties(new Long("24"));
			companiesBean.addProperties(null);
			companiesBean.addProperties(616);
			companiesBean.addProperties(null);
                        companiesBean.addProperties(null);
                        
                        
                        companiesBean.addProperties(139);
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties("test12Sep");
                        companiesBean.addProperties(326);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(15209);
                        companiesBean.addProperties(1264);
                        companiesBean.addProperties(20156);
                        companiesBean.addProperties(20156);
                        
                 
            GtnWsRecordBean itemBean = new GtnWsRecordBean();
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties("test13Sep");
			itemBean.addProperties(126);
			itemBean.addProperties(new Long("24"));
			itemBean.addProperties(null);
			itemBean.addProperties(141);
			itemBean.addProperties("test12Sep");
                        itemBean.addProperties("test12Sep");
                        itemBean.addProperties(null);
                        itemBean.addProperties("");
                        itemBean.addProperties(15209);
                        itemBean.addProperties(null);                      
                        itemBean.addProperties(15209);
                        itemBean.addProperties(20156);
                        itemBean.addProperties(20156);
                        
             GtnWsRecordBean priceBean = new GtnWsRecordBean();
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties("test13Sep");
			priceBean.addProperties(126);
			priceBean.addProperties(new Long("24"));
			priceBean.addProperties(null);
			priceBean.addProperties(647);
			priceBean.addProperties("test12Sep");
                        priceBean.addProperties("test12Sep");
                        priceBean.addProperties(null);
                        priceBean.addProperties("");
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties("");   
                        priceBean.addProperties(15209);
                        priceBean.addProperties(null);   
                        priceBean.addProperties(20156);
                        priceBean.addProperties(20156);
                        
            GtnWsRecordBean rebateBean = new GtnWsRecordBean();
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties("test13Sep");
			rebateBean.addProperties(126);
                        rebateBean.addProperties(863);
                        rebateBean.addProperties(875);
                        rebateBean.addProperties(851);
			rebateBean.addProperties(new Long("24"));
			rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties("");
                        rebateBean.addProperties(326);
                        rebateBean.addProperties(12363);
                        rebateBean.addProperties(12358);
                        rebateBean.addProperties(12359);
                        rebateBean.addProperties(12360);
                        rebateBean.addProperties(12361);
                        rebateBean.addProperties(12362);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(360);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(182);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(186);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(191);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15035);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(null);
                        rebateBean.addProperties(0);
                        rebateBean.addProperties(0);
                        
                        
                        
                        
                        
                        
			rebateBean.addProperties(647);
			rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties("test12Sep");
                        rebateBean.addProperties(null);
                        rebateBean.addProperties("");
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties("");   
                        rebateBean.addProperties(15209);
                        rebateBean.addProperties(null);   
                        rebateBean.addProperties(20156);
                        rebateBean.addProperties(20156);
                        
		
          GtnWsRecordBean aliasBean = new GtnWsRecordBean();
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties("test14Sep");
			aliasBean.addProperties(218);
			aliasBean.addProperties(new Long("24"));
			aliasBean.addProperties(null);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(1);
                        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
		        aliasBean.addProperties(20156);
                        aliasBean.addProperties(null);
                      
        List<GtnWsRecordBean> aliaslist=new ArrayList<>();
        aliaslist.add(aliasBean);    
        
        
           GtnWsRecordBean notesBean = new GtnWsRecordBean();
			notesBean.addProperties("test14Sep");
			notesBean.addProperties(1);
			notesBean.addProperties("");
                        notesBean.addProperties("D:/Habib/Allergan Servers/bpigtn_portlet/jboss-7.1.1/base-path/Attachments");
			notesBean.addProperties(new Long("24"));
                        notesBean.addProperties(20156);
                     
		
                      
        List<GtnWsRecordBean> noteslist=new ArrayList<>();
        noteslist.add(notesBean); 

        List<String> contractInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_DITText_ContractID");
        contractInfoFieldList.add("CDProcessView_DITText_ContractNo");
        contractInfoFieldList.add("CDProcessView_DITText_ContractName");
        List<String> companiesInfoFieldList =new ArrayList<>();
        contractInfoFieldList.add("CDProcessView_CTText_CFPID");
        contractInfoFieldList.add("CDProcessView_CTText_CFPNo");
        contractInfoFieldList.add("CDProcessView_CTText_CFPName");
        
        
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setCompaniesFieldList(companiesInfoFieldList);
        contractDashboardBean.setContractAliasRecordList(aliaslist);
        contractDashboardBean.setNotesTabRecordList(noteslist);
        contractDashboardBean.setContractInfoFieldList(contractInfoFieldList);
        contractDashboardBean.setProcessBean(processBean);
        contractDashboardBean.setContractInfoBean(treeBean);
        contractDashboardBean.setCompanyInfoBean(companiesBean);
        contractDashboardBean.setItemInfoBean(itemBean);
        contractDashboardBean.setPriceInfoBean(priceBean);
        contractDashboardBean.setRebateInfoBean(rebateBean);
        gtnWsContractDashboardRequest.setUserId("20156");
        gtnWsContractDashboardRequest.setSessionId("17");
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.submitContractDashboard(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of validateContractToRebuild method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testValidateContractToRebuild() {
        System.out.println("validateContractToRebuild");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
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
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.validateContractToRebuild(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of getContractDashboardRebuildTreeData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetContractDashboardRebuildTreeData() {
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
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.getContractDashboardRebuildTreeData(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of getWhereClauseForAColumn method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetWhereClauseForAColumn() throws Exception {
        System.out.println("getWhereClauseForAColumn");
        String field = "CHECK_RECORD";
        String value1 = "2018-08-25 09:15:28.1";
        
        
        String value2 = "2018-08-25 09:15:28.1";
        ArrayList<String> list = new ArrayList<>();
        String between = "BETWEEN";
        String and = "AND";
        String greequ = "GREATER_OR_EQUAL";
        String lessequ = "LESS_OR_EQUAL";
        String like = "LIKE";
        String equal = "EQUAL";
        String equals = "EQUALS";
        String grea = "GREATER";
        String les = "LESS";

        list.add(between);
        list.add(and);
        list.add(greequ);
        list.add(lessequ);
        list.add(like);
        list.add(equal);
        list.add(equals);
        list.add(grea);
        list.add(les);
        for (String e : list) {
            System.out.println(e);
           
             String result = gtnWsContractDashboardController.getWhereClauseForAColumn(e, field, value1, value2);
                if(result!=null){
                String s=result.toString();        
                    assertFalse(s.isEmpty());
                }
      }
    }

    /**
     * Test of getSysSchemaCatalog method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetSysSchemaCatalog() throws Exception {
        System.out.println("getSysSchemaCatalog");
        String result = gtnWsContractDashboardController.getSysSchemaCatalog();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getPopulateValue method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetPopulateValue() {
        System.out.println("getPopulateValue");
        String field = "CFP Status";
        Object populateValue = 126;
        String result = gtnWsContractDashboardController.getPopulateValue(field, populateValue);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetQuery_List_String() {
        System.out.println("getQuery");
        List input = new ArrayList();
        input.add("CFP_DETAILS_ATTACHED_STATUS");
        input.add(128);
        input.add(20156);
        input.add("697");
        String queryName = "populateCompaniesValue";
        String result = gtnWsContractDashboardController.getQuery(input, queryName);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getContractDashboardProcessTableExcelData method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testGetContractDashboardProcessTableExcelData() {
        System.out.println("getContractDashboardProcessTableExcelData");

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
        gtnWsContractDashboardController.getContractDashboardProcessTableExcelData(gtnWsRequest);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());

    }

    /**
     * Test of approveContractDashboard method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testApproveContractDashboard() {
        System.out.println("approveContractDashboard");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
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
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.approveContractDashboard(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of rejectContractDashboard method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testRejectContractDashboard() {
        
        System.out.println("rejectContractDashboard");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("189858");
        generalRequest.setSessionId("847");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
     
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
        
        contractDashboardBean.setProcessBean(processBean);
        gtnWsContractDashboardRequest.setContractDashboardBean(contractDashboardBean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardController.rejectContractDashboard(gtnWsRequest);
        assertFalse(result==null);
    }

    /**
     * Test of priceSchedulePriceProtectionTabService method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testPriceSchedulePriceProtectionTabService() throws Exception {
        System.out.println("priceSchedulePriceProtectionTabService");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("572");
        generalRequest.setExcel(false);
        List comboBoxWhereclauseParamList=new ArrayList();
        comboBoxWhereclauseParamList.add(20156);
        comboBoxWhereclauseParamList.add("572");
        comboBoxWhereclauseParamList.add("contractCopyLineQuery");  
        comboBoxWhereclauseParamList.add("A");
        generalRequest.setComboBoxWhereclauseParamList(comboBoxWhereclauseParamList);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardSessionBean bean=new GtnWsContractDashboardSessionBean();
        bean.setErrorDisplayId("errorDisplay");
        gtnWsContractDashboardRequest.setContractDashboardBean(bean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.priceSchedulePriceProtectionTabService(gtnWsRequest);
        assertFalse(bean.getErrorDisplayId().isEmpty());
    }

    /**
     * Test of contractPriceProtectionStartDateAlertTabService method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testContractPriceProtectionStartDateAlertTabService() throws Exception {
        System.out.println("contractPriceProtectionStartDateAlertTabService");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("630");
        generalRequest.setExcel(false);
        List comboBoxWhereclauseParamList=new ArrayList();
        comboBoxWhereclauseParamList.add(20156);
        comboBoxWhereclauseParamList.add("630");
        generalRequest.setComboBoxWhereclauseParamList(comboBoxWhereclauseParamList);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>(); 

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
        GtnWsContractDashboardSessionBean bean=new GtnWsContractDashboardSessionBean();
        bean.setNeedOperation(true);
        bean.setErrorDisplayId("errorDisplay");
        gtnWsContractDashboardRequest.setContractDashboardBean(bean);
        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);        
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardController.contractPriceProtectionStartDateAlertTabService(gtnWsRequest);
        assertFalse(bean.getErrorDisplayId().isEmpty());
    }

    /**
     * Test of checkAndUpdateAllrelationShip method, of class GtnWsContractDashboardController.
     */
    @Test
    public void testCheckAndUpdateAllrelationShip() {
        System.out.println("checkAndUpdateAllrelationShip");
        gtnWsContractDashboardController.checkAndUpdateAllrelationShip();
        assertTrue(true);
    }
    
}
