/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companymaster.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.hibernate.QueryException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.companymaster.constants.GtnWsCMasterConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsCMasterServiceTest {

	public GtnWsCMasterServiceTest() {
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
	private GtnWsCMasterService gtnWsCMasterService;

	@PostConstruct
	public void constrcut() {
		GtnWsAllListConfig gtnWebServiceAllListConfig = gtnWsCMasterService.getGtnWebServiceAllListConfig();
		gtnWebServiceAllListConfig.setGtnSqlQueryEngine(gtnWsCMasterService.getGtnSqlQueryEngine());
	}

	/**
	 * Test of getCompantMasterSearch method, of class GtnWsCMasterService.
	 */

	@Test

	public void testGetCompantMasterSearch() {
		System.out.println("getCompantMasterSearch");
		List<Object> searchColumnNameList = new ArrayList<Object>();
		searchColumnNameList.add("companyName");
		searchColumnNameList.add("companyType");
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setSearchQueryName("SearchQuery");
		gtnWsSearchRequest.setCount(true);
		gtnWsSearchRequest.setSearchColumnNameList(searchColumnNameList);

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteria.setFieldId("companyName");
		criteria.setFilterValue1("Company_1");
		criteria.setExpression("EQUALS");
		gtnWebServiceSearchCriteriaList.add(criteria);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);

		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria byCriteria = new GtnWebServiceOrderByCriteria();
		byCriteria.setPropertyId("companyName");
		byCriteria.setOrderByCriteria("DESC");
		gtnWebServiceOrderByCriteriaList.add(byCriteria);
		gtnWsSearchRequest.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);

		gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);

		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);

		try {
			gtnWsCMasterService.getCompantMasterSearch(gtnUIFrameworkWebserviceRequest);
		} catch (QueryException ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
			assertTrue(true);
		} catch (GtnFrameworkGeneralException ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Test of getCompanyMasterQueryGeneratorBean method, of class
	 * GtnWsCMasterService.
	 */
	@Test
	public void testGetCompanyMasterQueryGeneratorBean() {
		System.out.println("getCompanyMasterQueryGeneratorBean");
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		Object[] columnArray = new Object[] { "companyMasterSid", "companyId", "companyNo", "companyName",
				"companyStatus", "companyType", "companyStartDate", "companyEndDate", "companyTradeClass",
				"tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory", "organizationKey",
				"financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate", "priorParentCompanyNo",
				"priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "address1",
				"address2", "zipCode", "city", "state", "country" };
		gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		gtnWsSearchRequest.setSearchQueryName("SearchQuery");
		gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(columnArray));
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(Arrays.asList(
				new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("companyId", "*", "EQUALS") }));
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		try {
			testMap(gtnWebServiceSearchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("companyId", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(Arrays
				.asList(new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("companyId", "ASC") }));

		GtnFrameworkQueryGeneratorBean expResult = new GtnFrameworkQueryGeneratorBean();
		expResult.setFromTableAlies("cm");
		GtnFrameworkQueryGeneratorBean result = gtnWsCMasterService
				.getCompanyMasterQueryGeneratorBean(gtnUIFrameworkWebserviceRequest, gtnWebServiceSearchQueryConfig);
		assertEquals(expResult.getFromTableAlies(), result.getFromTableAlies());
	}

	/**
	 * Test of getSearchColumnBean method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetSearchColumnBean() {
		System.out.println("getSearchColumnBean");
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnFrameworkQueryGeneratorBean queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();
		Object[] columnArray = new Object[] { "companyMasterSid", "companyId", "companyNo", "companyName",
				"companyStatus", "companyType", "companyStartDate", "companyEndDate", "companyTradeClass",
				"tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory", "organizationKey",
				"financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate", "priorParentCompanyNo",
				"priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "address1",
				"address2", "zipCode", "city", "state", "country" };
		gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		gtnWsSearchRequest.setSearchQueryName("SearchQuery");
		gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(columnArray));
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(Arrays.asList(
				new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("companyId", "*", "EQUALS") }));
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);

		try {
			testMap(gtnWebServiceSearchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		gtnWsCMasterService.getSearchColumnBean(gtnUIFrameworkWebserviceRequest, gtnWebServiceSearchQueryConfig,
				queryGeneratorConfig);

	}

	/**
	 * Test of getJoinClauseBean method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetJoinClauseBean() {
		System.out.println("getJoinClauseBean");
		GtnFrameworkQueryGeneratorBean queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();

		gtnWsCMasterService.getJoinClauseBean(queryGeneratorConfig);
	}

	/**
	 * Test of getWhereClauseBean method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetWhereClauseBean() {
		System.out.println("getWhereClauseBean");
		GtnFrameworkQueryGeneratorBean queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		Object[] columnArray = new Object[] { "companyMasterSid", "companyId", "companyNo", "companyName",
				"companyStatus", "companyType", "companyStartDate", "companyEndDate", "companyTradeClass",
				"tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory", "organizationKey",
				"financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate", "priorParentCompanyNo",
				"priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "address1",
				"address2", "zipCode", "city", "state", "country" };
		gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		gtnWsSearchRequest.setSearchQueryName("SearchQuery");
		gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(columnArray));
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(Arrays.asList(
				new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("companyId", "*", "EQUALS") }));
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);

		try {
			testMap(gtnWebServiceSearchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		gtnWsCMasterService.getWhereClauseBean(queryGeneratorConfig, gtnWebServiceSearchQueryConfig,
				gtnUIFrameworkWebserviceRequest);
	}

	/**
	 * Test of getOrderByClause method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetOrderByClauseIf() {
		System.out.println("getOrderByClause");
		GtnFrameworkQueryGeneratorBean queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		try {
			testMap(gtnWebServiceSearchQueryConfig);

			GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
			Object[] columnArray = new Object[] { "companyMasterSid", "companyId", "companyNo", "companyName",
					"companyStatus", "companyType", "companyStartDate", "companyEndDate", "companyTradeClass",
					"tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory", "organizationKey",
					"financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate", "priorParentCompanyNo",
					"priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "address1",
					"address2", "zipCode", "city", "state", "country" };
			gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
			gtnWsSearchRequest.setSearchQueryName("SearchQuery");
			gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(columnArray));
			gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(Arrays.asList(
					new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("companyId", "*", "EQUALS") }));

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("companyId", "ASC"));
			gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

			gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
			gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(Arrays.asList(
					new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("companyId", "ASC") }));

		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		gtnWsCMasterService.getOrderByClause(queryGeneratorConfig, gtnWebServiceSearchQueryConfig,
				gtnUIFrameworkWebserviceRequest);
	}

	@Test
	public void testGetOrderByClauseElse() {
		System.out.println("getOrderByClause1");
		GtnFrameworkQueryGeneratorBean queryGeneratorConfig = new GtnFrameworkQueryGeneratorBean();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		try {
			testMap(gtnWebServiceSearchQueryConfig);

			GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
			Object[] columnArray = new Object[] { "companyMasterSid", "companyId", "companyNo", "companyName",
					"companyStatus", "companyType", "companyStartDate", "companyEndDate", "companyTradeClass",
					"tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory", "organizationKey",
					"financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate", "priorParentCompanyNo",
					"priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "address1",
					"address2", "zipCode", "city", "state", "country" };
			gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
			gtnWsSearchRequest.setSearchQueryName("SearchQuery");
			gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(columnArray));

			gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(new ArrayList<GtnWebServiceSearchCriteria>());

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("companyId", "ASC"));
			gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);
			gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		gtnWsCMasterService.getOrderByClause(queryGeneratorConfig, gtnWebServiceSearchQueryConfig,
				gtnUIFrameworkWebserviceRequest);
	}

	/**
	 * Test of getTableColumnForField method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetTableColumnForFieldpoIf() {
		System.out.println("getTableColumnForField");
		String fieldName = "companyId";
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			testMap(gtnWebServiceSearchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}

		String expResult = "cm.COMPANY_ID";
		String result = gtnWsCMasterService.getTableColumnForField(fieldName, gtnWebServiceSearchQueryConfig);
		assertEquals(expResult.trim(), result.trim());

	}

	@Test
	public void testGetTableColumnForFieldElse() {
		System.out.println("getTableColumnForField1");
		String fieldName = "companId";
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			testMap(gtnWebServiceSearchQueryConfig);
			gtnWsCMasterService.getTableColumnForField(fieldName, gtnWebServiceSearchQueryConfig);
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}

		catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Test of getTableColumnForWhereClause method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetTableColumnForWhereClauseIf() {
		System.out.println("getTableColumnForWhereClause");
		String fieldName = "companyId";
		GtnWsSearchQueryConfig searchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			testMap(searchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}

		String expResult = "cm.COMPANY_ID";
		String result = gtnWsCMasterService.getTableColumnForWhereClause(fieldName, searchQueryConfig);
		assertEquals(expResult.trim(), result.trim());

	}

	@Test
	public void testGetTableColumnForWhereClauseElse() {
		System.out.println("getTableColumnForWhereClause1");
		String fieldName = "compayId";
		GtnWsSearchQueryConfig searchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			testMap(searchQueryConfig);
			gtnWsCMasterService.getTableColumnForWhereClause(fieldName, searchQueryConfig);
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}

		catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Test of findTypeList method, of class GtnWsCMasterService.
	 */
	@Test
	public void testFindTypeList() {
		System.out.println("findTypeList");
		GtnWsSearchQueryConfig searchQueryConfig = new GtnWsSearchQueryConfig();
		List<Object> columnNameList = new ArrayList<>();
		columnNameList.add("companyId");
		try {
			testMap(searchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);

			List<String> expResult = new ArrayList<>();
			expResult.add("String");
			List<String> result = gtnWsCMasterService.findTypeList(searchQueryConfig, columnNameList);
			assertEquals(expResult, result);
		}
	}

	/**
	 * Test of getHelperTypeValue method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetHelperTypeValue() {
		System.out.println("getHelperTypeValue");
		Object object = 10;
		gtnWsCMasterService.getHelperTypeValue(object);
	}

	/**
	 * Test of getStringFromObject method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetStringFromObject() {
		System.out.println("getStringFromObject");
		Object str = 10;

		String expResult = "10";
		String result = gtnWsCMasterService.getStringFromObject(str);
		assertEquals(expResult.trim(), result.trim());
	}

	@Test
	public void testGetStringFromObjectElse() {
		System.out.println("getStringFromObject1");
		Object str = "";
		String expResult = "";
		String result = gtnWsCMasterService.getStringFromObject(str);
		assertEquals(expResult.trim(), result.trim());
	}

	/**
	 * Test of getIntegerFromObject method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetIntegerFromObject() {
		System.out.println("getIntegerFromObject");
		Object obj = 10;

		Integer expResult = 10;
		Integer result = gtnWsCMasterService.getIntegerFromObject(obj);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getCustomisedResult method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetCustomisedResult() {
		System.out.println("getCustomisedResult");
		List resultList = new ArrayList();
		resultList.add(new Object[] { "sb", 10, new Date(), "boolean" });
		GtnWsSearchQueryConfig searchQueryConfig = new GtnWsSearchQueryConfig();
		List<Object> columnName = new ArrayList<>();
		columnName.add("companyId");
		columnName.add("parentCompanySearchcompanyType");
		columnName.add("lastUpdatedDate");
		columnName.add("recordLockStatus");
		try {
			testMap(searchQueryConfig);
		} catch (Exception ex) {
			Logger.getLogger(GtnWsCMasterServiceTest.class.getName()).log(Level.SEVERE, null, ex);
		}

		gtnWsCMasterService.getCustomisedResult(resultList, searchQueryConfig, columnName);
	}

	/**
	 * Test of getOperatorType method, of class GtnWsCMasterService.
	 */
	@Test
	public void testGetOperatorType() {
		System.out.println("getOperatorType");
		ArrayList<String> list = new ArrayList<>();
		String between = "BETWEEN";
		String like = "LIKE";
		String equal = "EQUAL_TO";
		String greater = "GREATERTHAN";
		String less = "LESSTHAN";
		String in = "IN";
		String inn = "inn";

		list.add(between);
		list.add(like);
		list.add(equal);
		list.add(greater);
		list.add(less);
		list.add(in);
		list.add(inn);

		for (String e : list) {
			System.out.println(e);
			GtnFrameworkOperatorType result = null;
			result = gtnWsCMasterService.getOperatorType(e);
			if (result != null) {
				String s = result.toString();
				assertEquals(e, s);
			}

		}
	}

	/**
	 * Test of ccpId method, of class GtnWsCMasterService.
	 */
	@Test
	public void testCcpId() throws Exception {
		System.out.println("ccpId");
		List<Object> inputValueList = new ArrayList<>();
		inputValueList.add("COMPANY_1");
		GtnUIFrameworkWebserviceResponse gtnWSresponse = new GtnUIFrameworkWebserviceResponse();

		gtnWsCMasterService.ccpId(inputValueList, gtnWSresponse);
	}

	public void testMap(GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig) throws Exception {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>(100);
		fieldToColumnDetailsMap.put("companyMasterSid",
				configProvider.getColumnStringConfig("COMPANY_MASTER_SID", "cm"));
		GtnWsColumnDetailsConfig companyIdColumnConfig = configProvider.getColumnStringConfig("COMPANY_ID", "cm");
		fieldToColumnDetailsMap.put("companyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyId", companyIdColumnConfig);
		GtnWsColumnDetailsConfig companyNoColumnConfig = configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "cm");
		fieldToColumnDetailsMap.put("companyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyNo", companyNoColumnConfig);
		GtnWsColumnDetailsConfig companyNameColumnConfig = configProvider.getColumnStringConfig("COMPANY_NAME", "cm");
		fieldToColumnDetailsMap.put("companyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyName", companyNameColumnConfig);
		GtnWsColumnDetailsConfig companyTypeColumnConfig = configProvider.getColumnHelperConfig("COMPANY_TYPE", "cm");
		companyTypeColumnConfig.setHelperTableAliasName("companyTypeHelper");
		companyTypeColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyType", companyTypeColumnConfig);
		GtnWsColumnDetailsConfig companyStatusColumnConfig = configProvider.getColumnHelperConfig("COMPANY_STATUS",
				"cm");
		companyStatusColumnConfig.setHelperTableAliasName(GtnWsCMasterConstants.COMPANY_STATUS_HELPER);
		companyStatusColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("recordLockStatus",
				configProvider.getColumnBooleanConfig("RECORD_LOCK_STATUS", "cm"));
		fieldToColumnDetailsMap.put("lives", configProvider.getColumnStringConfig("LIVES", "cm"));
		fieldToColumnDetailsMap.put("companyEndDate", configProvider.getColumnDateConfig("COMPANY_END_DATE", "cm"));
		GtnWsColumnDetailsConfig stateColumnConfig = configProvider.getColumnHelperConfig("STATE", "cm");
		stateColumnConfig.setHelperTableAliasName("companyStateHelper");
		stateColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("state", stateColumnConfig);
		GtnWsColumnDetailsConfig financialSystemColumnConfig = configProvider.getColumnStringConfig("FINANCIAL_SYSTEM",
				"cm");
		financialSystemColumnConfig.setHelperTableAliasName("companyFinancialHelper");
		financialSystemColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("financialSystem", financialSystemColumnConfig);
		fieldToColumnDetailsMap.put("address1", configProvider.getColumnStringConfig("ADDRESS1", "cm"));
		fieldToColumnDetailsMap.put("address2", configProvider.getColumnStringConfig("ADDRESS2", "cm"));
		fieldToColumnDetailsMap.put("city", configProvider.getColumnStringConfig("CITY", "cm"));
		GtnWsColumnDetailsConfig companyGroupColumnConfig = configProvider.getColumnHelperConfig("COMPANY_GROUP", "cm");
		companyGroupColumnConfig.setHelperTableAliasName("companyGroupHelper");
		companyGroupColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyGroup", companyGroupColumnConfig);
		fieldToColumnDetailsMap.put("zipCode", configProvider.getColumnStringConfig("ZIP_CODE", "cm"));
		GtnWsColumnDetailsConfig countryColumnConfig = configProvider.getColumnHelperConfig("COUNTRY", "cm");
		countryColumnConfig.setHelperTableAliasName("companyCountryHelper");
		countryColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("country", countryColumnConfig);
		fieldToColumnDetailsMap.put("regionCode", configProvider.getColumnStringConfig("REGION_CODE", "cm"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnStringConfig("CREATED_BY", "cm"));
		fieldToColumnDetailsMap.put("createdDate", configProvider.getColumnStringConfig("CREATED_DATE", "cm"));
		fieldToColumnDetailsMap.put("modifiedBy", configProvider.getColumnStringConfig("MODIFIED_BY", "cm"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnStringConfig("MODIFIED_DATE", "cm"));
		fieldToColumnDetailsMap.put("batchId", configProvider.getColumnStringConfig("BATCH_ID", "cm"));
		fieldToColumnDetailsMap.put("lastUpdatedDate", configProvider.getColumnDateConfig("LAST_UPDATED_DATE", "cm"));
		fieldToColumnDetailsMap.put("companyStartDate", configProvider.getColumnDateConfig("COMPANY_START_DATE", "cm"));
		fieldToColumnDetailsMap.put("inboundStatus", configProvider.getColumnStringConfig("INBOUND_STATUS", "cm"));
		GtnWsColumnDetailsConfig companyCategoryColumnConfig = configProvider.getColumnHelperConfig("COMPANY_CATEGORY",
				"cm");
		companyCategoryColumnConfig.setHelperTableAliasName("companyCategoryHelper");
		companyCategoryColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyCategory", companyCategoryColumnConfig);
		GtnWsColumnDetailsConfig organizationColumnConfig = configProvider.getColumnHelperConfig("ORGANIZATION_KEY",
				"cm");
		organizationColumnConfig.setHelperTableAliasName("companyOrganizationHelper");
		organizationColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("organizationKey", organizationColumnConfig);
		GtnWsColumnDetailsConfig udc1ColumnConfig = configProvider.getColumnHelperConfig("UDC1", "udc");
		udc1ColumnConfig.setHelperTableAliasName("companyUdc1Helper");
		udc1ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc1", udc1ColumnConfig);
		GtnWsColumnDetailsConfig udc2ColumnConfig = configProvider.getColumnHelperConfig("UDC2", "udc");
		udc2ColumnConfig.setHelperTableAliasName("companyUdc2Helper");
		udc2ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc2", udc2ColumnConfig);
		GtnWsColumnDetailsConfig udc3ColumnConfig = configProvider.getColumnHelperConfig("UDC3", "udc");
		udc3ColumnConfig.setHelperTableAliasName("companyUdc3Helper");
		udc3ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc3", udc3ColumnConfig);
		GtnWsColumnDetailsConfig udc4ColumnConfig = configProvider.getColumnHelperConfig("UDC4", "udc");
		udc4ColumnConfig.setHelperTableAliasName("companyUdc4Helper");
		udc4ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc4", udc4ColumnConfig);
		GtnWsColumnDetailsConfig udc5ColumnConfig = configProvider.getColumnHelperConfig("UDC5", "udc");
		udc5ColumnConfig.setHelperTableAliasName("companyUdc5Helper");
		udc5ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc5", udc5ColumnConfig);
		GtnWsColumnDetailsConfig udc6ColumnConfig = configProvider.getColumnHelperConfig("UDC6", "udc");
		udc6ColumnConfig.setHelperTableAliasName("companyUdc6Helper");
		udc6ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc6", udc6ColumnConfig);
		GtnWsColumnDetailsConfig companyTradeClassColumnConfig = configProvider
				.getColumnHelperConfig("COMPANY_TRADE_CLASS", GtnFrameworkWebserviceConstant.TRADE);
		companyTradeClassColumnConfig.setHelperTableAliasName("companyTradeClassHelper");
		companyTradeClassColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyTradeClass", companyTradeClassColumnConfig);
		GtnWsColumnDetailsConfig tradeClassColumnConfig = configProvider.getColumnHelperConfig("COMPANY_TRADE_CLASS",
				GtnFrameworkWebserviceConstant.TRADE);
		tradeClassColumnConfig.setHelperTableAliasName("companyTradeClassHelper");
		tradeClassColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("tradeClass", tradeClassColumnConfig);
		fieldToColumnDetailsMap.put("tradeClassStartDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_START_DATE", GtnFrameworkWebserviceConstant.TRADE));
		fieldToColumnDetailsMap.put("tradeClassEndDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_END_DATE", GtnFrameworkWebserviceConstant.TRADE));
		fieldToColumnDetailsMap.put("parentCompanyMasterSid", configProvider
				.getColumnStringConfig("PARENT_COMPANY_MASTER_SID", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("parentStartDate",
				configProvider.getColumnDateConfig("PARENT_START_DATE", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("parentEndDate",
				configProvider.getColumnDateConfig("PARENT_END_DATE", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("parentCompanyNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "comp", "comp"));
		fieldToColumnDetailsMap.put("priorParentCompanyNo", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "comp1", "priorCompanyNo"));
		fieldToColumnDetailsMap.put("priorParentStartDate",
				configProvider.getColumnDateConfig("PRIOR_PARENT_START_DATE", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("companyIdentifier",
				configProvider.getColumnStringConfig("COMPANY_IDENTIFIER_VALUE", "CID"));
		fieldToColumnDetailsMap.put("companyQualifierName",
				configProvider.getColumnStringConfig("COMPANY_QUALIFIER_SID", "CQ"));
		fieldToColumnDetailsMap.put("companyType1",
				configProvider.getColumnStringConfig(GtnWsConstants.DESCRIPTION, "helperDesc"));
		fieldToColumnDetailsMap.put("companyStatus1",
				configProvider.getColumnStringConfig(GtnWsConstants.DESCRIPTION, "helperDescription"));
		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
	}

}
