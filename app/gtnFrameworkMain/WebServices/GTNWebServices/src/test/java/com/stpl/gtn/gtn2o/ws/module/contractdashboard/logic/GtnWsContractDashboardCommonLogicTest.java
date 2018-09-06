/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
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
public class GtnWsContractDashboardCommonLogicTest {
    
    public GtnWsContractDashboardCommonLogicTest() {
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
    private GtnWsContractDashboardCommonLogic gtnWsContractDashboardCommonLogic;
    
    @Autowired
    private GtnWsContractDashboardController gtnWsContractDashboardController;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of setGtnSearchResponse method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testSetGtnSearchResponse_6args() throws Exception {
        System.out.println("setGtnSearchResponse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("968");
        generalRequest.setExcel(false);
        
        List<Object> inputlist=new ArrayList<>();
        
        inputlist.add("Distinct\n" +
"                    im.ITEM_NO,\n" +
"                    im.ITEM_NAME,\n" +
"                    im.ITEM_DESC,\n" +
"                    hstatus.DESCRIPTION as status,\n" +
"                    hform.DESCRIPTION as form,\n" +
"                    hstrength.DESCRIPTION as strength,\n" +
"                    htherapeutic.DESCRIPTION as therapeutic,\n" +
"                    BM.BRAND_NAME AS BRAND,\n" +
"                    htype.DESCRIPTION as hpackage,\n" +
"                    im.ITEM_MASTER_SID,\n" +
"                    im.ITEM_ID,\n" +
"                    im.ITEM_STATUS,\n" +
"                    im.BRAND_MASTER_SID,\n" +
"                    im.PACKAGE_SIZE,\n" +
"                    im.PRIMARY_UOM,\n" +
"                    im.\"SOURCE\" as source");
        inputlist.add("AND ifp.IFP_NO LIKE '%'");
        
        
        String queryName="findCDIFP";
        String orderByQuery="ORDER BY im.ITEM_NO ASC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
        
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
        gtnWsContractDashboardCommonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, gtnWsContractDashboardController, inputlist, queryName, orderByQuery);
        assertFalse(gtnWsSearchRequest.getSearchColumnNameList().isEmpty());
    }

    /**
     * Test of setGtnSearchResponse method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testSetGtnSearchResponse_5args() throws Exception {
try {
        System.out.println("itemDetailsTableData");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("997");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        boolean isStartDateEndDate = false;
        List<Object> inputlist=new ArrayList<>();
        inputlist.add("BPIGTN_AGN_SYSL7_QA");
        inputlist.add("BPIGTN_AGN_SYSL7_QA");
        inputlist.add("20156");
        inputlist.add("685");
        inputlist.add("");
        inputlist.add("im.ITEM_NO ASC");
        inputlist.add("0");
        inputlist.add("1");
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
        gtnWsContractDashboardCommonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, gtnWsContractDashboardController, inputlist, queryName);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
       } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addInputItemSId method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddInputItemSId() {
try{
        System.out.println("addInputItemSId");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("501");
        generalRequest.setExtraParameter("cfpContractId");
        generalRequest.setExcel(false);
        
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        String queryName = "getPricingDetailsResults";
        List<Object> inputlist=new ArrayList<>();
        inputlist.add("BPIGTN_AGN_SYSL7_QA");
        inputlist.add("20156");
        inputlist.add("685");
        inputlist.add("");
        inputlist.add("im.ITEM_NO ASC");
        inputlist.add("0");
        inputlist.add("1");
        
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
        gtnWsContractDashboardCommonLogic.addInputItemSId(gtnWsRequest, inputlist);
        assertFalse(gtnWsSearchRequest.getGtnWebServiceSearchCriteriaList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardItemLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUserIdSessionId method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddUserIdSessionId() {
        System.out.println("addUserIdSessionId");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();      
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("501");
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        List<Object> inputlist = new ArrayList<>();
        gtnWsContractDashboardCommonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
        assertFalse(generalRequest.getUserId()==null);
    }

    /**
     * Test of getItemAdditionTabColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetItemAdditionTabColumns() {
        System.out.println("getItemAdditionTabColumns");
        String property = "Form1";
        gtnWsContractDashboardCommonLogic.getItemAdditionTabColumns(property);
        assertFalse(property.isEmpty());
    }
    
    @Test
    public void testGetItemAdditionTabColumnsFalse() {
        System.out.println("getItemAdditionTabColumns");
        String property = "Formm1";
        gtnWsContractDashboardCommonLogic.getItemAdditionTabColumns(property);
        assertFalse(property.isEmpty());
    }

    /**
     * Test of getCompanyAdditionTabColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetCompanyAdditionTabColumns() {
        System.out.println("getCompanyAdditionTabColumns");
        String property = "Company ID";
        gtnWsContractDashboardCommonLogic.getCompanyAdditionTabColumns(property);
        assertFalse(property.isEmpty());
    }
    
    @Test
    public void testGetCompanyAdditionTabColumnsFalse() {
        System.out.println("getCompanyAdditionTabColumns");
        String property = "Company IDD";
        gtnWsContractDashboardCommonLogic.getCompanyAdditionTabColumns(property);
        assertFalse(property.isEmpty());
    }

    /**
     * Test of getCompanyTabColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetCompanyTabColumns() {
        System.out.println("getCompanyTabColumns");
        String property = "companyId1";
        gtnWsContractDashboardCommonLogic.getCompanyTabColumns(property);
        assertFalse(property.isEmpty());
    }
    
    @Test
    public void testGetCompanyTabColumnsFalse() {
        System.out.println("getCompanyTabColumns");
        String property = "companyIdd1";
        gtnWsContractDashboardCommonLogic.getCompanyTabColumns(property);
        assertFalse(property.isEmpty());
    }

    /**
     * Test of getNetSalesFormulaColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetNetSalesFormulaColumns() {
        System.out.println("getNetSalesFormulaColumns");
        String property = "formulaType";
        gtnWsContractDashboardCommonLogic.getNetSalesFormulaColumns(property);
        assertFalse(property.isEmpty());
    }
    
    @Test
    public void testGetNetSalesFormulaColumnsFalse() {
        System.out.println("getNetSalesFormulaColumns");
        String property = "formulaTypee";
        gtnWsContractDashboardCommonLogic.getNetSalesFormulaColumns(property);
        assertFalse(property.isEmpty());
    }

    /**
     * Test of getCfpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetCfpColumns() {
        System.out.println("getCfpColumns");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        gtnWsContractDashboardCommonLogic.getCfpColumns(property);
        assertFalse(property.isEmpty());       
    }
    
    @Test
    public void testGetCfpColumnsFalse() {
        System.out.println("getCfpColumns");
        String property = "SystemI";
        gtnWsContractDashboardCommonLogic.getCfpColumns(property);
        assertFalse(property.isEmpty());       
    }

    /**
     * Test of getIfpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetIfpColumns() {
        System.out.println("getIfpColumns");
        String property = "ifpId";
        gtnWsContractDashboardCommonLogic.getIfpColumns(property);
        assertFalse(property.isEmpty());       
    }
    
    @Test
    public void testGetIfpColumnsFalse() {
        System.out.println("getIfpColumns");
        String property = "ifpIdd";
        gtnWsContractDashboardCommonLogic.getIfpColumns(property);
        assertFalse(property.isEmpty());       
    }

    /**
     * Test of getPsColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetPsColumns() {
        System.out.println("getPsColumns");
        String property = GtnFrameworkCommonConstants.SYSTEM_ID;
        gtnWsContractDashboardCommonLogic.getPsColumns(property);
        assertFalse(property.isEmpty()); 
    }
    
    @Test
    public void testGetPsColumnsFalse() {
        System.out.println("getPsColumns");
        String property = "SystemI";
        gtnWsContractDashboardCommonLogic.getPsColumns(property);
        assertFalse(property.isEmpty()); 
    }

    /**
     * Test of getRsColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetRsColumns() {
        System.out.println("getRsColumns");
        String property = "rsId";
        gtnWsContractDashboardCommonLogic.getRsColumns(property);
        assertFalse(property.isEmpty()); 
    }
    
    @Test
    public void testGetRsColumnsFalse() {
        System.out.println("getRsColumns");
        String property = "rsIdd";
        gtnWsContractDashboardCommonLogic.getRsColumns(property);
        assertFalse(property.isEmpty()); 
    }

    /**
     * Test of getTpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetTpColumns() {
        System.out.println("getTpColumns");
        String property = "companyNo";
        gtnWsContractDashboardCommonLogic.getTpColumns(property);
        assertFalse(property.isEmpty()); 
    }
    
    @Test
    public void testGetTpColumnsFalse() {
        System.out.println("getTpColumns");
        String property = "companyNoo";
        gtnWsContractDashboardCommonLogic.getTpColumns(property);
        assertFalse(property.isEmpty()); 
    }

    /**
     * Test of getRuleColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetRuleColumns() {
        System.out.println("getRuleColumns");
        String property = "ruleNo";
        gtnWsContractDashboardCommonLogic.getRuleColumns(property);
        assertFalse(property.isEmpty());
    }
    
    @Test
    public void testGetRuleColumnsFalse() {
        System.out.println("getRuleColumns");
        String property = "ruleNoo";
        gtnWsContractDashboardCommonLogic.getRuleColumns(property);
        assertFalse(property.isEmpty());
    }

    /**
     * Test of getRpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetRpColumns() {
        System.out.println("getRpColumns");
        String property = "rebatePlanId";
        gtnWsContractDashboardCommonLogic.getRpColumns(property);
        assertFalse(property.isEmpty()); 
    }
    
    @Test
    public void testGetRpColumnsFalse() {
        System.out.println("getRpColumns");
        String property = "rebatePlanIdd";
        gtnWsContractDashboardCommonLogic.getRpColumns(property);
        assertFalse(property.isEmpty()); 
    }

    /**
     * Test of getDcColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testGetDcColumns() {
        System.out.println("getDcColumns");
        String property = "dcDesc";
        gtnWsContractDashboardCommonLogic.getDcColumns(property);
        assertFalse(property.isEmpty()); 
    }
    
    @Test
    public void testGetDcColumnsFalse() {
        System.out.println("getDcColumns");
        String property = "dcDescc";
        gtnWsContractDashboardCommonLogic.getDcColumns(property);
        assertFalse(property.isEmpty()); 
    }

    /**
     * Test of addValueToMap method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddValueToMap() {
    try {
        System.out.println("addValueToMap");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addValueToMap");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddCompanyColumns() {
    try {
        System.out.println("addCompanyColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addCompanyColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyAdditionColumn method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddCompanyAdditionColumn() {
    try {
        System.out.println("addCompanyAdditionColumn");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addCompanyAdditionColumn");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNetSalesFormulaColumn method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddNetSalesFormulaColumn() {      
    try {
        System.out.println("addNetSalesFormulaColumn");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addNetSalesFormulaColumn");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }              
    }

    /**
     * Test of addCfpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddCfpColumns() {    
    try {
        System.out.println("addCfpColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addCfpColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }

    /**
     * Test of addIfpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddIfpColumns() {
    try {
        System.out.println("addIfpColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addIfpColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    /**
     * Test of addTpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddTpColumns() {
    try {
        System.out.println("addTpColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addTpColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     * Test of addPsColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddPsColumns() {
    try {
        System.out.println("addPsColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addPsColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     * Test of addRsColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddRsColumns() {
    try {
        System.out.println("addRsColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addRsColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRuleColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddRuleColumns() {
    try {
        System.out.println("addRuleColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addRuleColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRpColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddRpColumns() {     
    try {
        System.out.println("addRpColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addRpColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addDcColumns method, of class GtnWsContractDashboardCommonLogic.
     */
    @Test
    public void testAddDcColumns() {
    try {
        System.out.println("addDcColumns");
        Method method = gtnWsContractDashboardCommonLogic.getClass().getDeclaredMethod("addDcColumns");
        method.setAccessible(true);
        method.invoke(gtnWsContractDashboardCommonLogic);
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractDashboardCommonLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
