/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnwsExcelRequest;
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


public class GtnWsContractDashboardItemLogicTest {
    
    public GtnWsContractDashboardItemLogicTest() {
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
    private GtnWsContractDashboardItemLogic gtnWsContractDashboardItemLogic;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    /**
     * Test of getController method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetController() {
        System.out.println("getController");
        GtnWsContractDashboardController result = gtnWsContractDashboardItemLogic.getController();
        assertFalse(result.toString().isEmpty());
    }

    /**
     * Test of getQuery method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetQuery() {
        System.out.println("getQuery");
        String queryName = "findCDItemAdditionCount";
        String result = gtnWsContractDashboardItemLogic.getQuery(queryName);
        assertFalse(result.toString().isEmpty());
    }

    /**
     * Test of getWhereClauseForAColumn method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetWhereClauseForAColumn() throws Exception {
        System.out.println("getWhereClauseForAColumn");
        String expersion = "EQUALS";
        String field = "CHECK_RECORD";
        String value1 = "1";
        String value2 = "";
        String exp="CHECK_RECORD = '1' ";
        String result = gtnWsContractDashboardItemLogic.getWhereClauseForAColumn(expersion, field, value1, value2);
        assertEquals(exp, result);
    }

    /**
     * Test of getCDItemAdditionViewTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDItemAdditionViewTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardItemLogic.getCDItemAdditionViewTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of getCDItemAdditionLeftTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDItemAdditionLeftTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDItemAdditionLeftTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of itemAdditionMoveRight method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemAdditionMoveRight() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardItemLogic.itemAdditionMoveRight(gtnWsRequest, gtnResponse);
        assertFalse(result==null);
    }

    /**
     * Test of itemAdditionMoveLeft method, of class GtnWsContractDashboardItemLogic.
     */
//    @Test
//    public void testItemAdditionMoveLeft() throws Exception {
//      System.out.println("itemAdditionMoveLeft");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("302");
//        generalRequest.setExcel(false);
//        
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//            
//        GtnWsRecordBean treeBean = new GtnWsRecordBean();
//			treeBean.addProperties("ITEM_10");
//			treeBean.addProperties("ITEM_10");
//			treeBean.addProperties("ITEM_10");
//			treeBean.addProperties("Active");
//			treeBean.addProperties("CAPSULE");
//			treeBean.addProperties("100mg 10S");
//			treeBean.addProperties("THERACUTIX");
//			treeBean.addProperties("IMITREX");
//                        treeBean.addProperties("1");
//			treeBean.addProperties(92734);
//        List<GtnWsRecordBean> list=new ArrayList<>();
//        list.add(treeBean);
//        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
//        gtnWsContractDashboardRequest.setTableBean(treeBean);
//        treeBean.setRecordHeader(Arrays.asList("itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"));
//        gtnWsContractDashboardRequest.setContractId(1248);
//        gtnWsContractDashboardRequest.setCfpContractId(234);
//        gtnWsContractDashboardRequest.setIfpContractId(257);
//        gtnWsContractDashboardRequest.setPsContractId(375);
//        gtnWsContractDashboardRequest.setRsContractId(380);
//        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardItemLogic.itemAdditionMoveLeft(gtnWsRequest, gtnResponse);
//        assertFalse(result==null);
//    }

    /**
     * Test of itemAdditionMoveAllRight method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemAdditionMoveAllRight() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.itemAdditionMoveAllRight(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of itemAdditionMoveAllLeft method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemAdditionMoveAllLeft() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardItemLogic.itemAdditionMoveAllLeft(gtnWsRequest, gtnResponse);
        assertFalse(result==null);
    }

    /**
     * Test of getCDItemsDetailTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDItemsDetailTableData() throws Exception {
        System.out.println("getCDItemsDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        boolean isStartDateEndDate = false;
        
        
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDItemsDetailTableData(gtnWsRequest, gtnResponse, isStartDateEndDate);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDItemsDetailTableDataFalse() throws Exception {
        System.out.println("getCDItemsDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        boolean isStartDateEndDate = false;
        
        
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDItemsDetailTableData(gtnWsRequest, gtnResponse, isStartDateEndDate);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDItemsDetailViewTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDItemsDetailViewTableData() throws Exception {
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
        
        boolean isStartDateEndDate = false;

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDItemsDetailViewTableData(gtnWsRequest, gtnResponse, isStartDateEndDate);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDItemsDetailViewTableDataFalse() throws Exception {
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
        
        boolean isStartDateEndDate = false;

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDItemsDetailViewTableData(gtnWsRequest, gtnResponse, isStartDateEndDate);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of populateAllItems method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testPopulateAllItems() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.populateAllItems(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of populateItem method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testPopulateItem() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.populateItem(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of populateItemField method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testPopulateItemField() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.populateItemField(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }
    
     @Test
    public void testPopulateItemFieldFalse() throws Exception {
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
        //gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{     
        gtnWsContractDashboardItemLogic.populateItemField(gtnWsRequest, gtnResponse);
        Assert.fail();
         } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsContractDashboardRequest.getPopulateField().isEmpty());
    }

    /**
     * Test of getCDPricingDetailTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingDetailTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingDetailTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDPricingDetailTableDataFalse() throws Exception {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDPricingDetailTableData(gtnWsRequest, gtnResponse);
        Assert.fail();
         } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingDetailViewTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingDetailViewTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingDetailViewTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
      @Test
    public void testGetCDPricingDetailViewTableDatafalse() throws Exception {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
       try{
       gtnWsContractDashboardItemLogic.getCDPricingDetailViewTableData(gtnWsRequest, gtnResponse);
       Assert.fail();
         } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingProtectionTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingProtectionTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingProtectionTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
     @Test
    public void testGetCDPricingProtectionTableDataFalse() throws Exception {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDPricingProtectionTableData(gtnWsRequest, gtnResponse);
        Assert.fail();
         } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingProtectionExcelData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingProtectionExcelData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingProtectionExcelData(gtnWsRequest, gtnResponse);
        assertFalse(excelRequest.getInputs().length==0);
    }

    /**
     * Test of getCDPricingProtectionViewTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingProtectionViewTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingProtectionViewTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDPricingProtectionViewTableDataFalse() throws Exception {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDPricingProtectionViewTableData(gtnWsRequest, gtnResponse);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDRebateDetailTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDRebateDetailTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDRebateDetailTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDRebateDetailTableDataFalse() throws Exception {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDRebateDetailTableData(gtnWsRequest, gtnResponse);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDRebateDetailViewTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDRebateDetailViewTableData() throws Exception {
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDRebateDetailViewTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDRebateDetailViewTableDataFalse() throws Exception {
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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDRebateDetailViewTableData(gtnWsRequest, gtnResponse);
         Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDItemsDetailPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDItemsDetailPendingTableData() throws Exception {
        System.out.println("getCDItemsDetailPendingTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("ifpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("ifpContractId");
        criteria2.setFilterValue1("257");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDItemsDetailPendingTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    /**
     * Test of getCDRebateDetailPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDRebateDetailPendingTableData() throws Exception {
    System.out.println("getCDRebateDetailTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("rsContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "priceNo", "priceName", "itemType", "priceId", "attachedStatus", "rebateStartDate", "rebateEndDate", "rebatePlanBundleNo", "rebatePlanNopopup", "rebatePlanName", "deductionCalendarNopopup", "deductionCalendarName", "formulaType", "formulaNopopup", "formulaName", "netSalesFormulaNopopup", "netSalesRulepopup", "evaluationRulepopup", "evaluationRuleBundle", "calculationRulepopup", "calculationRuleBundle", "attachedDate", "rebatesStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("rsContractId");
        criteria2.setFilterValue1("380");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardItemLogic.getCDRebateDetailTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingDetailPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingDetailPendingTableData() throws Exception {
        System.out.println("getCDPricingDetailPendingTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("psContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("psContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingDetailTableData(gtnWsRequest, gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingProtectionPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingProtectionPendingTableData() throws Exception {
        System.out.println("getCDPricingProtectionTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingProtectionPendingTableData(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetCDPricingProtectionPendingTableDataFalse() throws Exception {
        
        System.out.println("getCDPricingProtectionTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        try{
        gtnWsContractDashboardItemLogic.getCDPricingProtectionPendingTableData(gtnWsRequest,gtnResponse);
       
      Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    }

    /**
     * Test of contractPriceProtectionInsertTemp method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testContractPriceProtectionInsertTemp() throws Exception {
        System.out.println("contractPriceProtectionInsertTemp");
        List<Object> inputValueList = new ArrayList<>();
        inputValueList.add(20156);
        inputValueList.add("698");
        inputValueList.add("contractCopyLineQuery");
        inputValueList.add("A");
        GtnUIFrameworkWebserviceResponse gtnContractProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.contractPriceProtectionInsertTemp(inputValueList, gtnContractProtectionUpdateResponse);
        assertFalse(inputValueList.size()==0);
    }

    /**
     * Test of getCDItemsViewDetailPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDItemsViewDetailPendingTableData() throws Exception {
        System.out.println("getCDItemsViewDetailPendingTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("633");
        generalRequest.setExtraParameter("ifpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("ifpContractId");
        criteria2.setFilterValue1("257");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse cditemsgtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDItemsViewDetailPendingTableData(gtnWsRequest,cditemsgtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDRebateDetailViewPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDRebateDetailViewPendingTableData() throws Exception {
        System.out.println("getCDRebateDetailViewPendingTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("633");
        generalRequest.setExtraParameter("rsContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType", "priceNo", "priceName", "itemType", "formulaId","formulaName","rebatePlanName", "rebateStartDate", "rebateEndDate", "rebateRevisionDate"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
         GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("rsContractId");
        criteria2.setFilterValue1("380");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse cditemsgtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDRebateDetailViewPendingTableData(gtnWsRequest,cditemsgtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getCDPricingDetailViewPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingDetailViewPendingTableData() throws Exception {
        System.out.println("testGetCDPricingDetailViewPendingTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("633");
        generalRequest.setExtraParameter("psContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("psContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse cditemsgtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.getCDPricingDetailViewPendingTableData(gtnWsRequest,cditemsgtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }

    /**
     * Test of getCDPricingProtectionViewPendingTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCDPricingProtectionViewPendingTableData() throws Exception {
        System.out.println("testGetCDPricingProtectionViewPendingTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("633");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse cditemsgtnResponse = new GtnUIFrameworkWebserviceResponse();
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);



        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        gtnWsContractDashboardItemLogic.getCDPricingProtectionViewPendingTableData(gtnWsRequest,cditemsgtnResponse);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());

    }

    /**
     * Test of contractPriceProtectionStartDateAlert method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testContractPriceProtectionStartDateAlert() throws Exception {
        System.out.println("contractPriceProtectionStartDateAlert");
        List<Object> inputValueList = new ArrayList<>();
        inputValueList.add(20156);
        inputValueList.add("698");
        GtnUIFrameworkWebserviceResponse gtnPsProtectionUpdateResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractDashboardItemLogic.contractPriceProtectionStartDateAlert(inputValueList, gtnPsProtectionUpdateResponse);
        assertFalse(inputValueList.size()==0);  
    }

    /**
     * Test of getItemAdditionSearchInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemAdditionSearchInput() throws Exception {
     try {
        System.out.println("getItemAdditionSearchInput");
        GtnWsSearchRequest searchRequest=new GtnWsSearchRequest();
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        searchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeuticClass","brand"}));
        searchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        searchRequest.setTableRecordStart(0);
        searchRequest.setTableRecordOffset(1);
        searchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList); 
        gtnWsRequest.setGtnWsSearchRequest(searchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemAdditionSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,searchRequest );
        assertFalse(searchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @Test
    public void testGetItemAdditionSearchInputFalse1() throws Exception {
     try {
        System.out.println("getItemAdditionSearchInput");
        GtnWsSearchRequest searchRequest=new GtnWsSearchRequest();
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        searchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeuticClass","brand"}));
        searchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("Brand Name");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("Brand Name");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        searchRequest.setTableRecordStart(0);
        searchRequest.setTableRecordOffset(1);
        searchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList); 
        gtnWsRequest.setGtnWsSearchRequest(searchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemAdditionSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,searchRequest );
        assertFalse(searchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetItemAdditionSearchInputFalse2() throws Exception {
     try {
        System.out.println("getItemAdditionSearchInput");
        GtnWsSearchRequest searchRequest=new GtnWsSearchRequest();
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();  
        searchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"itemNo","itemName","itemDesc","itemStatus","form","strength","therapeuticClass","brand"}));
        searchRequest.setCount(false);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("Brand Name");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("Brand Name");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        searchRequest.setTableRecordStart(0);
        searchRequest.setTableRecordOffset(1);
        searchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList); 
        gtnWsRequest.setGtnWsSearchRequest(searchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemAdditionSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,searchRequest );
        assertFalse(searchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getItemAdditionSortedInputs method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemAdditionSortedInputs() {
    try {
        System.out.println("getItemAdditionSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();     
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemAdditionSortedInputs", List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWebServiceOrderByCriteriaList );
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetItemAdditionSortedInputsFalse() {
    try {
        System.out.println("getItemAdditionSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();    
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemAdditionSortedInputs", List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWebServiceOrderByCriteriaList );
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getItemAdditionTabColumns method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemAdditionTabColumns() {
    try {
        System.out.println("getItemAdditionTabColumns");
        String property = "";
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemAdditionTabColumns", String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,property );
        assertFalse(!property.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getWhereClauseForItemAddition method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetWhereClauseForItemAddition() {
        try {
        System.out.println("getWhereClauseForItemAddition");
        String value = "it*";    
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getWhereClauseForItemAddition", String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,value );
        assertFalse(value.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of itemAdditionMoveLeft method, of class GtnWsContractDashboardItemLogic.
     */
//    @Test
//    public void testItemAdditionMoveLeft() throws Exception {
//        System.out.println("itemAdditionMoveLeft");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("968");
//        generalRequest.setExcel(false);
//        
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//            
//        GtnWsRecordBean treeBean = new GtnWsRecordBean();
//			treeBean.addProperties("00023079865");
//			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
//			treeBean.addProperties("Refresh Tears 4 x 15ml + 5ml");
//			treeBean.addProperties("Active");
//			treeBean.addProperties("Not Available");
//			treeBean.addProperties("Not Available");
//			treeBean.addProperties("EYE CARE");
//			treeBean.addProperties("REFRESH");
//                        treeBean.addProperties("65.000000");
//			treeBean.addProperties(92327);
//        List<GtnWsRecordBean> list=new ArrayList<>();
//        list.add(treeBean);
//        GtnWsContractDashboardRequest gtnWsContractDashboardRequest=new GtnWsContractDashboardRequest();
//        gtnWsContractDashboardRequest.setTableBean(treeBean);
//        treeBean.setRecordHeader(Arrays.asList("itemNo","itemName","itemDesc","itemStatus","form","strength","therapeutic class","brand"));
//        gtnWsContractDashboardRequest.setContractId(1248);
//        gtnWsContractDashboardRequest.setCfpContractId(234);
//        gtnWsContractDashboardRequest.setIfpContractId(257);
//        gtnWsContractDashboardRequest.setPsContractId(375);
//        gtnWsContractDashboardRequest.setRsContractId(380);
//        gtnWsRequest.setGtnWsContractDashboardRequest(gtnWsContractDashboardRequest);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
//        GtnUIFrameworkWebserviceResponse result = gtnWsContractDashboardItemLogic.itemAdditionMoveLeft(gtnWsRequest,gtnResponse);
//        assertFalse(result==null);
//    }

    /**
     * Test of getOrderByStartOffsetQuery method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetOrderByStartOffsetQuery() {
    try {
        System.out.println("getOrderByStartOffsetQuery");
        String column = "im.ITEM_NO ASC";
        String start = "0";
        String offset = "10";
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getOrderByStartOffsetQuery", String.class,String.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,column, start, offset);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getItemMoveAllRightQuery method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemMoveAllRightQuery() throws Exception {
        try{
        System.out.println("getItemMoveAllRightQuery");
  
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("353");
        generalRequest.setExcel(false);
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setCount(true);
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
         
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemMoveAllRightQuery", GtnUIFrameworkWebserviceRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsRequest);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of skipCriteria method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testSkipCriteria() {
        try{
        System.out.println("skipCriteria");
        String field = "recordType";
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("skipCriteria", String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,field);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getItemsSearchInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchInput() throws Exception {
    try{
        System.out.println("getItemsSearchInput");
        
        boolean isStartDateEndDate=false;
        
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
       

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();      
          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
         
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchInput", GtnWsSearchRequest.class,boolean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest,isStartDateEndDate);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetItemsSearchInputFalse() throws Exception {
    try{
        System.out.println("getItemsSearchInput");
        
        boolean isStartDateEndDate=false;
        
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
       

       // gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();      
          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
         
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchInput", GtnWsSearchRequest.class,boolean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest,isStartDateEndDate);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
           // Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getItemsDetailSortedInputs method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsDetailSortedInputs() {
    try {
        System.out.println("getItemsDetailSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsDetailSortedInputs", List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWebServiceOrderByCriteriaList );
        assertFalse(!gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testGetItemsDetailSortedInputsFalse() {
    try {
        System.out.println("getItemsDetailSortedInputs");
        List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
        GtnWebServiceOrderByCriteria e = new GtnWebServiceOrderByCriteria();
        gtnWebServiceOrderByCriteriaList.add(e);
        String orderByCriteria = "itemId";
        e.setOrderByCriteria(orderByCriteria);
        e.setPropertyId(orderByCriteria);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsDetailSortedInputs", List.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWebServiceOrderByCriteriaList );
        assertFalse(gtnWebServiceOrderByCriteriaList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of itemDetailsTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemDetailsTableData() throws Exception {
 try {
        System.out.println("itemDetailsTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        boolean isStartDateEndDate = false;
        String queryName = "getItemsDetailsResults";
        
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("itemDetailsTableData", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class,String.class,boolean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsRequest, gtnResponse, queryName, isStartDateEndDate);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
       } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of pricingDetailsDataTable method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testPricingDetailsDataTable() throws Exception {
try{
        System.out.println("pricingDetailsDataTable");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("501");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        String queryName = "getPricingDetailsResults";
        
        
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

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("pricingDetailsDataTable", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsRequest, gtnResponse, queryName);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of loadGtnReadBean method, of class GtnWsContractDashboardItemLogic.
     */
//    @Test
//    public void testLoadGtnReadBean() {
//        System.out.println("loadGtnReadBean");
//        List recordHeader = null;
//        List excelData = null;
//        GtnWsContractDashboardItemLogic instance = null;
//        List<GtnWsRecordBean> expResult = null;
//        List<GtnWsRecordBean> result = instance.loadGtnReadBean(recordHeader, excelData);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getItemsSearchPendingInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchPendingInput() throws Exception {

        System.out.println("getItemsSearchPendingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("ifpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("ifpContractId");
        criteria2.setFilterValue1("257");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPendingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetItemsSearchPendingInputFalse() throws Exception {

        System.out.println("getItemsSearchPendingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("ifpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("ifpContractId");
        criteria2.setFilterValue1("257");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPendingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    @Test
    public void testGetItemsSearchPendingInputFalse2() throws Exception {

        try{
        System.out.println("getItemsSearchPendingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("ifpContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","itemNo","itemName","itemDesc","ifpStatus","ifpStartDate","ifpEndDate",
                           "itemStatus","form","strength","therapeuticClass","brand","attachedDate","modifiedDate","modifiedBy","createdDate","createdBy","itemStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_ITrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("ifpContractId");
        criteria2.setFilterValue1("257");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPendingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    /**
     * Test of itemProcess method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemProcess() throws Exception {
        System.out.println("itemProcess");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("781");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        
        String queryName="getRebateDetailsViewResults";
        boolean isStartDateEndDate=false;
        
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("itemProcess", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class,String.class,boolean.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsRequest, gtnResponse, queryName,isStartDateEndDate);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of itemRebateProcess method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemRebateProcess() throws Exception {
        System.out.println("itemRebateProcess");
        String queryName="getRebateDetailsResults";

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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("itemRebateProcess", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsRequest, gtnResponse, queryName);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getItemsRebateSearchInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsRebateSearchInput() throws Exception {
        System.out.println("getItemsRebateSearchInput");

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
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsRebateSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetItemsRebateSearchInputFalse() throws Exception {
        System.out.println("getItemsRebateSearchInput");

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
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsRebateSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetItemsRebateSearchInputFalse2() throws Exception {
        try{
        System.out.println("getItemsRebateSearchInput");

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

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsRebateSearchInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getItemsSearchRebateInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchRebateInput() throws Exception {
        System.out.println("getItemsSearchRebateInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("rsContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "priceNo", "priceName", "itemType", "priceId", "attachedStatus", "rebateStartDate", "rebateEndDate", "rebatePlanBundleNo", "rebatePlanNopopup", "rebatePlanName", "deductionCalendarNopopup", "deductionCalendarName", "formulaType", "formulaNopopup", "formulaName", "netSalesFormulaNopopup", "netSalesRulepopup", "evaluationRulepopup", "evaluationRuleBundle", "calculationRulepopup", "calculationRuleBundle", "attachedDate", "rebatesStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("rsContractId");
        criteria2.setFilterValue1("380");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchRebateInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetItemsSearchRebateInputFalse() throws Exception {
        System.out.println("getItemsSearchRebateInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("rsContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "priceNo", "priceName", "itemType", "priceId", "attachedStatus", "rebateStartDate", "rebateEndDate", "rebatePlanBundleNo", "rebatePlanNopopup", "rebatePlanName", "deductionCalendarNopopup", "deductionCalendarName", "formulaType", "formulaNopopup", "formulaName", "netSalesFormulaNopopup", "netSalesRulepopup", "evaluationRulepopup", "evaluationRuleBundle", "calculationRulepopup", "calculationRuleBundle", "attachedDate", "rebatesStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("LIKE");
        criteria.setFilter(true);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("rsContractId");
        criteria2.setFilterValue1("380");
        criteria2.setExpression("LIKE");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchRebateInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetItemsSearchRebateInputFalse2() throws Exception {
        try{
        System.out.println("getItemsSearchRebateInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("rsContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId", "recordType", "priceNo", "priceName", "itemType", "priceId", "attachedStatus", "rebateStartDate", "rebateEndDate", "rebatePlanBundleNo", "rebatePlanNopopup", "rebatePlanName", "deductionCalendarNopopup", "deductionCalendarName", "formulaType", "formulaNopopup", "formulaName", "netSalesFormulaNopopup", "netSalesRulepopup", "evaluationRulepopup", "evaluationRuleBundle", "calculationRulepopup", "calculationRuleBundle", "attachedDate", "rebatesStatusHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_RTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);
        
        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("rsContractId");
        criteria2.setFilterValue1("380");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchRebateInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getItemsSearchPricingInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchPricingInput() throws Exception {
try{
        System.out.println("getItemsSearchPricingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("501");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        String queryName = "getPricingDetailsResults";
        
        
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

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPricingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetItemsSearchPricingInputFalse() throws Exception {
    try{
        System.out.println("getItemsSearchPricingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("501");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        String queryName = "getPricingDetailsResults";
        
        
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

        gtnWsSearchRequest.setTableRecordStart(0);
        gtnWsSearchRequest.setTableRecordOffset(1);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPricingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

    }

    /**
     * Test of itemPriceProtectionProcess method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemPriceProtectionProcess() throws Exception {
        System.out.println("itemPriceProtectionProcess");
        String queryName = "getPricingProtectionCount";
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("itemPriceProtectionProcess", GtnUIFrameworkWebserviceRequest.class,GtnUIFrameworkWebserviceResponse.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsRequest, gtnResponse, queryName);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getItemsSearchPriceProtectionInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchPriceProtectionInput() throws Exception {
        System.out.println("getItemsSearchPriceProtectionInput");
        String queryName = "getPricingProtectionCount";
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPriceProtectionInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
     @Test
    public void testGetItemsSearchPriceProtectionInputFalse() throws Exception {
        try{
        System.out.println("getItemsSearchPriceProtectionInput");
        String queryName = "getPricingProtectionCount";
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPriceProtectionInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

    }

    /**
     * Test of getItemsSearchPricingPendingInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchPricingPendingInput() throws Exception {
        System.out.println("getItemsSearchPricingPendingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("psContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","status","CPStartDate","CPEndDate","PriceType",
                      "Price","SuggestedPrice","Source","CreatedBy","CreatedDate","attachedDate","pdStatusHelperValue","pdPriceTypeHelperValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("psContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPricingPendingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }

    /**
     * Test of getItemsSearchPriceProtectionPendingInput method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetItemsSearchPriceProtectionPendingInput() throws Exception {
        System.out.println("getItemsSearchPriceProtectionPendingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPriceProtectionPendingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
    }
    
    @Test
    public void testGetItemsSearchPriceProtectionPendingInputFalse() throws Exception {
        try{
        System.out.println("getItemsSearchPriceProtectionPendingInput");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getItemsSearchPriceProtectionPendingInput", GtnWsSearchRequest.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest);
        //assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
         } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }


    }

    /**
     * Test of getCurrentHistoryFilter method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetCurrentHistoryFilter() {
        try{
        System.out.println("getCurrentHistoryFilter");
        String startDateValue = "CONTRACT_PRICE_START_DATE";
        String endDateValue ="CONTRACT_PRICE_END_DATE";
        String recordType="Current";
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("698");
        generalRequest.setExtraParameter("pspricingContractId");
        generalRequest.setExcel(false);
        
        
        GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
        gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(new Object[]{"checkRecordId","recordType","priceId","priceNo","priceName","brand","PriceProtectionStatus","PriceProtectionStartDate","PriceProtectionEndDate",
                           "MeasurementPrice", "nep", "NEPFormulapopup", "BasePriceTypeType", "BasePriceType", "netBasePrice", "netBasePriceFormulapopup", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormulapopup", "PriceToleranceInterval", "PriceToleranceFrequency", "PriceToleranceType", "PriceTolerance", "MaxIncrementalChange", "ResetEligible", "ResetType", "ResetDate", "ResetInterval",
                           "ResetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceTypeFormulapopup", "NetPriceType", "NetPriceTypeFormulapopup", "attachedDate", "ppStatusHelperValue", "measurementHelperValue", "basepricetypeHelperValue", "baselinewacHelperValue", "netbasepriceHelperValue", 
                           "subsequentperiodpriceHelperValue", "netsubsequentperiodpriceHelperValue", "pricetoleranceintervalHelperValue", "pricetolerancefrequencyHelperValue", "pricetolerancetypeHelperValue", "reseteligibleHelperValue", "resettypeHelperValue", "resetintervalHelperValue", "resetfrequencyHelperValue", 
                           "resetpricetypeHelperValue", "netresetpricetypeHelperValue", "netpricetypeHelperValue", "bDesHelperValue", "iBPDDLBHelperValue", "iBPDHelperValue", "bASEPRICEENTRYYYHelperValue", "priceToleranceDescriptionValue"}));
        gtnWsSearchRequest.setCount(true);
        List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
        GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
        criteria.setFieldId("CDProcessView_PTPPlvlrecord");
        criteria.setFilterValue1("[Pending]");
        criteria.setExpression("EQUALS");
        criteria.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria);

        GtnWebServiceSearchCriteria criteria2 = new GtnWebServiceSearchCriteria();
        criteria2.setFieldId("pspricingContractId");
        criteria2.setFilterValue1("375");
        criteria2.setExpression("EQUALS");
        criteria2.setFilter(false);
        gtnWebServiceSearchCriteriaList.add(criteria2);

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();          
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getCurrentHistoryFilter", String.class,String.class,String.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,recordType,startDateValue,endDateValue);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
       } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of itemDetailsSearchInputTableData method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testItemDetailsSearchInputTableData() throws Exception {
 try{
        System.out.println("itemDetailsSearchInputTableData");
        
        StringBuilder inputWhereConditionss=new StringBuilder("");
        boolean isStartDateEndDate=false;
        
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
       

        gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();      
          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
         
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("itemDetailsSearchInputTableData", GtnWsSearchRequest.class,StringBuilder.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest,inputWhereConditionss);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testItemDetailsSearchInputTableDataFalse() throws Exception {
 try{
        System.out.println("itemDetailsSearchInputTableData");
        
        StringBuilder inputWhereConditionss=new StringBuilder("");
        boolean isStartDateEndDate=false;
        
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
       

        //gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
        
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();      
          
        gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
         
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("itemDetailsSearchInputTableData", GtnWsSearchRequest.class,StringBuilder.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,gtnWsSearchRequest,inputWhereConditionss);
        //assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
        } catch (Exception ex) {
            assertEquals(InvocationTargetException.class, ex.getClass());
        }

    }
    

    /**
     * Test of getResultValue method, of class GtnWsContractDashboardItemLogic.
     */
    @Test
    public void testGetResultValue() throws Exception {
        System.out.println("getResultValue");
        String query = "getAddCopyDeleteLineAlertCountQueryContract";
        Object[] imtdPsDetailsInsertQueryParams = {20156,"82"};
        GtnFrameworkDataType[] imtdPsDetailsInsertQueryTypes = { GtnFrameworkDataType.INTEGER,GtnFrameworkDataType.STRING};  
        Method method = gtnWsContractDashboardItemLogic.getClass().getDeclaredMethod("getResultValue", String.class,Object[].class,GtnFrameworkDataType[].class);
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardItemLogic,query,imtdPsDetailsInsertQueryParams,imtdPsDetailsInsertQueryTypes);
        assertFalse(imtdPsDetailsInsertQueryParams.length==0);
    }
    
}
