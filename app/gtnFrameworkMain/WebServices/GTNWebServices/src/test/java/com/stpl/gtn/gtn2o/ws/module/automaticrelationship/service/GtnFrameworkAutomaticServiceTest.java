package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;

/**
 * The class <code>GtnFrameworkAutomaticServiceTest</code> contains tests for the class <code>{@link GtnFrameworkAutomaticService}</code>.
 *
 * 
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
public class GtnFrameworkAutomaticServiceTest {
	/**
	 * Run the GtnFrameworkAutomaticService() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkAutomaticService_1()
		throws Exception {

		GtnFrameworkAutomaticService result = new GtnFrameworkAutomaticService();

		
		assertNotNull(result);
	}

//	/**
//	 * Run the void checkAndUpdateAllRelationShip(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testCheckAndUpdateAllRelationShip_1()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "";
//
//		fixture.checkAndUpdateAllRelationShip(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.checkAndUpdateAllRelationShip(GtnFrameworkAutomaticService.java:44)
//	}
//
//	/**
//	 * Run the void checkAndUpdateAllRelationShip(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testCheckAndUpdateAllRelationShip_2()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "";
//
//		fixture.checkAndUpdateAllRelationShip(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.checkAndUpdateAllRelationShip(GtnFrameworkAutomaticService.java:44)
//	}
//
//	/**
//	 * Run the void checkAndUpdateAllRelationShip(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testCheckAndUpdateAllRelationShip_3()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "";
//
//		fixture.checkAndUpdateAllRelationShip(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.checkAndUpdateAllRelationShip(GtnFrameworkAutomaticService.java:44)
//	}
//
//	/**
//	 * Run the List<GtnWsRelationshipBuilderBean> getRelationShipBuilderData(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testGetRelationShipBuilderData_1()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "DeductionHierarchy";
//
//		List<GtnWsRelationshipBuilderBean> result = fixture.getRelationShipBuilderData(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<GtnWsRelationshipBuilderBean> getRelationShipBuilderData(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testGetRelationShipBuilderData_2()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "DeductionHierarchy";
//
//		List<GtnWsRelationshipBuilderBean> result = fixture.getRelationShipBuilderData(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<GtnWsRelationshipBuilderBean> getRelationShipBuilderData(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testGetRelationShipBuilderData_3()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "";
//
//		List<GtnWsRelationshipBuilderBean> result = fixture.getRelationShipBuilderData(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the List<GtnWsRelationshipBuilderBean> getRelationShipBuilderData(String) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testGetRelationShipBuilderData_4()
//		throws Exception {
//		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();
//		String relationShipType = "DeductionHierarchy";
//
//		List<GtnWsRelationshipBuilderBean> result = fixture.getRelationShipBuilderData(relationShipType);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService.getRelationShipBuilderData(GtnFrameworkAutomaticService.java:59)
//		assertNotNull(result);
//	}

	/**
	 * Run the void waitForRelaitonUpdatetoFinish() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testWaitForRelaitonUpdatetoFinish_1()
		throws Exception {
		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();

		fixture.waitForRelaitonUpdatetoFinish();

		
	}

	/**
	 * Run the void waitForRelaitonUpdatetoFinish() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testWaitForRelaitonUpdatetoFinish_2()
		throws Exception {
		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();

		fixture.waitForRelaitonUpdatetoFinish();

		
	}

	/**
	 * Run the void waitForRelaitonUpdatetoFinish() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testWaitForRelaitonUpdatetoFinish_3()
		throws Exception {
		GtnFrameworkAutomaticService fixture = new GtnFrameworkAutomaticService();

		fixture.waitForRelaitonUpdatetoFinish();

		
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
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
	 * 
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkAutomaticServiceTest.class);
	}
}