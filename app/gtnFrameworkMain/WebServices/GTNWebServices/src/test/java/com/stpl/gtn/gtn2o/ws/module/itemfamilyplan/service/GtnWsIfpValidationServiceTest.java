package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsIfpValidationServiceTest {
	@InjectMocks
	@Autowired
	GtnWsIfpValidationService fixture;

	@Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	/**
	 * Run the GtnWsIfpValidationService() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnWsIfpValidationService_1() throws Exception {

		GtnWsIfpValidationService result = new GtnWsIfpValidationService();

		assertNotNull(result);
	}

	/**
	 * Run the int getInt(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetInt_1() throws Exception {

		String value = "0";

		int result = fixture.getInt(value);

		assertEquals(0, result);
	}

	/**
	 * Run the int getInt(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test(expected = java.lang.NumberFormatException.class)
	public void testGetInt_3() throws Exception {

		String value = "";

		int result = fixture.getInt(value);

		assertEquals(0, result);
	}

	/**
	 * Run the String getString(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetString_1() throws Exception {

		String value = "";

		String result = fixture.getString(value);

		assertEquals(null, result);
	}

	/**
	 * Run the String getString(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetString_2() throws Exception {

		String value = "";

		String result = fixture.getString(value);

		assertEquals(null, result);
	}

	/**
	 * Run the GtnIFamilyPlanValidationBean
	 * ifpCommonValidation(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testIfpCommonValidation_1() throws Exception {

		GtnUIFrameworkWebserviceRequest ifpValidationRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();

		ifpValidationRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object[]> resultList = new ArrayList<>();
		Object[] o1 = { "ITEMS_COUNT", 1 };
		Object[] o2 = { "IFP_DETAILS_START_DATE", 1 };
		Object[] o3 = { "IFP_DETAILS_ATTACHED_STATUS", 1 };
		Object[] o4 = { "START_DATE_GREATER_END_DATE", 1 };
		Object[] o5 = { "CHECKED_RECORD_COUNT", 1 };
		Object[] o6 = { "START_DATE_EQUALS_END_DATE", 1 };
		Object[] o7 = { "default", 1 };
		resultList.add(o1);
		resultList.add(o2);
		resultList.add(o3);
		resultList.add(o4);
		resultList.add(o5);
		resultList.add(o6);
		resultList.add(o7);

		String ifpValidationQuery = gtnWsSqlService.getQuery("getIfpCommonValidationQuery");
		doReturn(resultList).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString(),
				Mockito.any(Object[].class), Mockito.any(GtnFrameworkDataType[].class));

		GtnIFamilyPlanValidationBean result = fixture.ifpCommonValidation(ifpValidationRequest);

	}

	/**
	 * Run the List
	 * <Object> ifpIdAndIfpNoValidation(GtnUIFrameworkWebserviceRequest) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testIfpIdAndIfpNoValidation_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);

		List<Object> result = fixture.ifpIdAndIfpNoValidation(gtnWsRequest);

		assertNotNull(result);
		ifpInfo.setIfpSid(1);

		fixture.ifpIdAndIfpNoValidation(gtnWsRequest);
	}

	/**
	 * Run the GtnWsIfpValidationService() constructor test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGtnWsIfpValidationService_2() throws Exception {

		GtnWsIfpValidationService result = new GtnWsIfpValidationService();

		assertNotNull(result);
	}

	/**
	 * Run the int getInt(String) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetInt_2() throws Exception {

		String value = "0";

		int result = fixture.getInt(value);

		assertEquals(0, result);
	}

	/**
	 * Run the int getInt(String) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetInt_4() throws Exception {

		String value = "eq";

		int result = fixture.getInt(value);

		assertEquals(1, result);
	}

	/**
	 * Run the String getString(String) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetString_3() throws Exception {

		String value = "";

		String result = fixture.getString(value);

		assertEquals(null, result);
	}

	/**
	 * Run the String getString(String) method test.
	 *
	 * @throws Exception
	 *
	 *
	 */
	@Test
	public void testGetString_4() throws Exception {

		String value = "eq";

		String result = fixture.getString(value);

		assertEquals(value, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *             if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}
