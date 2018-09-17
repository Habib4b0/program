package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * The class <code>GtnWsIfpAddServiceTest</code> contains tests for the class <code>{@link GtnWsIfpAddService}</code>.
 *
 * @author KARTHIK.RAJA
 * @version $Revision: 1.0 $
 */
public class GtnWsIfpAddServiceTest {
	/**
	 * Run the GtnWsIfpAddService() constructor test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGtnWsIfpAddService_1()
		throws Exception {

		GtnWsIfpAddService result = new GtnWsIfpAddService();

		
		assertNotNull(result);
		assertEquals(null, result.getSessionFactory());
	}

//	/**
//	 * Run the int checkAllItems(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testCheckAllItems_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		int result = fixture.checkAllItems(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.checkAllItems(GtnWsIfpAddService.java:637)
//		assertEquals(0, result);
//	}

	
/////*	*//**
//	 * Run the String companiesUpdateColumnQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 *//*
//	@Test
//	public void testCompaniesUpdateColumnQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.companiesUpdateColumnQuery(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.companiesUpdateColumnQuery(GtnWsIfpAddService.java:497)
//		assertNotNull(result);
//	}*/

	
//
//	/**
//	 * Run the boolean deleteIfpModel(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testDeleteIfpModel_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		boolean result = fixture.deleteIfpModel(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.deleteIfpModel(GtnWsIfpAddService.java:598)
//		assertTrue(result);
//	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_1()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item ID";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("IM.ITEM_ID", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_2()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Desc";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("IM.ITEM_DESC", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_3()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item No";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("IM.ITEM_NO", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_4()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Name";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("IM.ITEM_NAME", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_5()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Status";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("STA.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_6()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Strength";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("STR.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_7()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Therapeutic Class";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("TC.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_8()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Form";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("FO.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_9()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Brand";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("BM.BRAND_NAME", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_10()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "UOM";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("UOM.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_11()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetDbColumnForFilterField_12()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = null;

		String result = fixture.getDbColumnForFilterField(filterValue1);

		
		assertEquals("", result);
	}
//
//	/**
//	 * Run the void getIfpFetchQuery(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testGetIfpFetchQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
//
//		fixture.getIfpFetchQuery(gtnWsRequest, response);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.getIfpFetchQuery(GtnWsIfpAddService.java:527)
//	}
//
//	
//
//	/**
//	 * Run the String getIfpMoveAllLeftQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testGetIfpMoveAllLeftQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.getIfpMoveAllLeftQuery(GtnWsIfpAddService.java:113)
//		assertNotNull(result);
//	}
//
//
//
//	/**
//	 * Run the String getIfpMoveAllRightQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testGetIfpMoveAllRightQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.getIfpMoveAllRightQuery(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.getIfpMoveAllRightQuery(GtnWsIfpAddService.java:129)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the int getIfpTabDeleteQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testGetIfpTabDeleteQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		int result = fixture.getIfpTabDeleteQuery(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.getIfpTabDeleteQuery(GtnWsIfpAddService.java:589)
//		assertEquals(0, result);
//	}



	/**
	 * Run the SessionFactory getSessionFactory() method test.
	 *
	 * @throws Exception
	 *

	 */
	@Test
	public void testGetSessionFactory_1()
		throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();

		SessionFactory result = fixture.getSessionFactory();

		
		assertEquals(null, result);
	}

//	/**
//	 * Run the String ifpLeftTableSearchQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testIfpLeftTableSearchQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest ifpLeftTableRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.ifpLeftTableSearchQuery(ifpLeftTableRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.ifpLeftTableSearchQuery(GtnWsIfpAddService.java:62)
//		assertNotNull(result);
//	}
//
//
//
//	/**
//	 * Run the String ifpRightTableSearchQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testIfpRightTableSearchQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest ifpRightTableRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.ifpRightTableSearchQuery(ifpRightTableRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.ifpRightTableSearchQuery(GtnWsIfpAddService.java:83)
//		assertNotNull(result);
//	}
//
//
//	/**
//	 * Run the String itemsTabResultTable(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testItemsTabResultTable_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest itemsTabResultRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.itemsTabResultTable(itemsTabResultRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.itemsTabResultTable(GtnWsIfpAddService.java:322)
//		assertNotNull(result);
//	}
//
//
//	/**
//	 * Run the int updateIfpCompaniesTabQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//
//	 */
//	@Test
//	public void testUpdateIfpCompaniesTabQuery_1()
//		throws Exception {
//		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		int result = fixture.updateIfpCompaniesTabQuery(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.updateIfpCompaniesTabQuery(GtnWsIfpAddService.java:577)
//		assertEquals(0, result);
//	}



	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *

	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
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

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *

	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnWsIfpAddServiceTest.class);
	}
}