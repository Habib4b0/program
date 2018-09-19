package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.config;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;

/**
 * The class <code>GtnWebServiceItemFamilyPlanConfigTest</code> contains tests for the class <code>{@link GtnWebServiceItemFamilyPlanConfig}</code>.
 *
 * 
 * @author KARTHIK.RAJA
 * @version $Revision: 1.0 $
 */
public class GtnWebServiceItemFamilyPlanConfigTest {
	/**
	 * Run the GtnWebServiceItemFamilyPlanConfig() constructor test.
	 *
	 * 
	 */
	@Test
	public void testGtnWebServiceItemFamilyPlanConfig_1()
		throws Exception {
		GtnWebServiceItemFamilyPlanConfig result = new GtnWebServiceItemFamilyPlanConfig();
		assertNotNull(result);
	
	}

	/**
	 * Run the Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetSearchQueryConfigMap_1()
		throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(null);

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

	
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("ifpSearchQuery"));
		assertTrue(result.containsKey("parentIfpSearchQuery"));
	}

	/**
	 * Run the Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetSearchQueryConfigMap_2()
		throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(new HashMap());

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

	
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Run the Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testLoadSearchQueryConfig_1()
		throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(new HashMap());

		Map<String, GtnWsSearchQueryConfig> result = fixture.loadSearchQueryConfig();

	
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("ifpSearchQuery"));
		assertTrue(result.containsKey("parentIfpSearchQuery"));
	}

	/**
	 * Run the void setSearchQueryConfigMap(Map<String,GtnWsSearchQueryConfig>) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSetSearchQueryConfigMap_1()
		throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(new HashMap());
		Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap();

		fixture.setSearchQueryConfigMap(searchQueryConfigMap);

	
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
		new org.junit.runner.JUnitCore().run(GtnWebServiceItemFamilyPlanConfigTest.class);
	}
}