package com.stpl.gtn.gtn2o.ws.module.netsales.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfSelectedDeductionsBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUiFrameworkNsfSelectedCustomerBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;

/**

 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsNsfServiceTest {
	
	@InjectMocks
	@Autowired
	GtnWsNsfService fixture;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Run the GtnWsNsfService() constructor test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGtnWsNsfService_1()
		throws Exception {

		GtnWsNsfService result = new GtnWsNsfService();
		assertNotNull(result);
	}

	/**
	 * Run the int checkAllItems(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testCheckAllItems_isCheckAll_true()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setValue(true);
		netSalesFormulaGeneralRequest.setnSfInfoBean(nsfInfoBean);
		
		fixture.checkAllItems(gtnWsRequest);
		
	}
	
	@Test
	public void testCheckAllItems_isCheckAll_false()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setValue(false);
		netSalesFormulaGeneralRequest.setnSfInfoBean(nsfInfoBean);
		
		fixture.checkAllItems(gtnWsRequest);
		
		
	}
	
	@Test
	public void testCheckAllItemsForSales_isCheckAllSales_true()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setValue(true);
		netSalesFormulaGeneralRequest.setnSfInfoBean(nsfInfoBean);
		
		fixture.checkAllItemsForSales(gtnWsRequest);
		
		
	}
	
	@Test
	public void testCheckAllItemsForSales_isCheckAllSales_false()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setValue(false);
		netSalesFormulaGeneralRequest.setnSfInfoBean(nsfInfoBean);
		
		fixture.checkAllItemsForSales(gtnWsRequest);
		
	}

	/**
	 * Run the void deleteNsfRecord(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testDeleteNsfRecord_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setSystemId(20516);
		netSalesFormulaGeneralRequest.setnSfInfoBean(nsfInfoBean);

		fixture.deleteNsfRecord(gtnWsRequest);

	}

	/**
	 * Run the void deleteSalesDeductOnRefresh(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testDeleteSalesDeductOnRefresh_1()
		throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		fixture.deleteSalesDeductOnRefresh(gtnWsRequest);

	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_1()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("checkRecordId");

		String result = fixture.getColumnName(nsfUpdateBean);


		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_2()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("netSalesRuleName");

		String result = fixture.getColumnName(nsfUpdateBean);


		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_3()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("ruleName");

		String result = fixture.getColumnName(nsfUpdateBean);


		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_4()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("netSalesRuleNo");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_5()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("ruleNo");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_6()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("ruleSid");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_7()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("indicator");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}
	
	@Test
	public void testGetColumnName_8()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("indicator");
		nsfUpdateBean.setValue("Add");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}
	
	@Test
	public void testGetColumnName_9()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("indicator");
		nsfUpdateBean.setValue("+");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getColumnName(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetColumnName_10()
		throws Exception {

		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setPropertyId("");

		String result = fixture.getColumnName(nsfUpdateBean);
		assertNotNull(result);
	}
	@Test
	public void testGetPopulateValidationQuery_1()
		throws Exception {

		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(true);

		String result = fixture.getPopulateValidationQuery(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getPopulateValidationQuery(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetPopulateValidationQuery_2()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(false);

		String result = fixture.getPopulateValidationQuery(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getRemoveRecordQuery(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetRemoveRecordQuery_1()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(true);

		String result = fixture.getRemoveRecordQuery(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getRemoveRecordQuery(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetRemoveRecordQuery_2()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(false);

		String result = fixture.getRemoveRecordQuery(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getResetRecordQuery(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetResetRecordQuery_1()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(true);

		String result = fixture.getResetRecordQuery(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the String getResetRecordQuery(GtnWsNsfUpdateBean) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetResetRecordQuery_2()
		throws Exception {
		
		GtnWsNsfUpdateBean nsfUpdateBean = new GtnWsNsfUpdateBean();
		nsfUpdateBean.setSalesBasis(false);

		String result = fixture.getResetRecordQuery(nsfUpdateBean);
		assertNotNull(result);
	}

	/**
	 * Run the SessionFactory getSessionFactory() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetSessionFactory_1()
		throws Exception {
		
		try {
			SessionFactory result = fixture.getSessionFactory();
			assertNotNull(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Run the int insertIntoTempTables(GtnUIFrameworkWebserviceRequest,Session,String,Integer) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testInsertIntoTempTables_catch()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		// session  = null cause an error
		Session session = null;
		
		String queryName = "";
		Integer nsfSystemId = new Integer(1);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		try {
			fixture.insertIntoTempTables(gtnWsRequest, session, queryName, nsfSystemId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Run the int insertSelectedCustomersIntoTempTable(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testInsertSelectedCustomersIntoTempTable_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnUiFrameworkNsfSelectedCustomerBean bean = new GtnUiFrameworkNsfSelectedCustomerBean();
		bean.setCfpName("cfpName");
		bean.setCfpNo("cfpNo");
		bean.setContractDetSid(1);
		bean.setContractMasterSid(1);
		bean.setContractName("contractName");
		bean.setContractNo("contractNo");
		bean.setCustomerName("customerName");
		bean.setCustomerNo("customerNo");
		bean.setRuleSid(1);
		bean.setSystemId(20516);
		
		List<GtnUiFrameworkNsfSelectedCustomerBean> nsCustomerInfoBeanList = new ArrayList<>();
		nsCustomerInfoBeanList.add(bean);
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		netSalesFormulaGeneralRequest.setNsCustomerInfoBeanList(nsCustomerInfoBeanList);
		
		try {
			fixture.insertSelectedCustomersIntoTempTable(gtnWsRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInsertSelectedCustomersIntoTempTable_catch()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		try {
			// cause an error
			gtnWsRequest.setGtnWsNetSalesGeneralRequest(null);
			fixture.insertSelectedCustomersIntoTempTable(gtnWsRequest);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	/**
	 * Run the int insertSelectedDeductionsIntoTempTable(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testInsertSelectedDeductionsIntoTempTable_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnUIFrameworkNsfSelectedDeductionsBean bean = new GtnUIFrameworkNsfSelectedDeductionsBean();
		bean.setContract(true);
		bean.setContractMasterSid(1);
		bean.setContractName("contractName");
		bean.setContractNo("contractNo");
		bean.setDeductionCategory(1);
		bean.setDeductionName("deductionName");
		bean.setDeductionNo("deductionNo");
		bean.setDeductionSubType(1);
		bean.setDeductionType(1);
		bean.setRsContractSid(1);
		
		List<GtnUIFrameworkNsfSelectedDeductionsBean> deductionsBeanList = new ArrayList<>();
		deductionsBeanList.add(bean);
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(netSalesFormulaGeneralRequest);
		netSalesFormulaGeneralRequest.setSelectedDeductionInfoBeanList(deductionsBeanList);
		
		try {
			fixture.insertSelectedDeductionsIntoTempTable(gtnWsRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void testInsertSelectedDeductionsIntoTempTable_catch()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		try {
			// cause an error
			gtnWsRequest.setGtnWsNetSalesGeneralRequest(null);
			fixture.insertSelectedDeductionsIntoTempTable(gtnWsRequest);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}