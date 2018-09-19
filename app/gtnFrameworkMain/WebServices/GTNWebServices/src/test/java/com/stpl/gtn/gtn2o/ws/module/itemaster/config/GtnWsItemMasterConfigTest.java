package com.stpl.gtn.gtn2o.ws.module.itemaster.config;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;

/**
 * The class <code>GtnWsItemMasterConfigTest</code> contains tests for the class <code>{@link GtnWsItemMasterConfig}</code>.
 *
 * 
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
public class GtnWsItemMasterConfigTest {
	/**
	 * Run the GtnWsItemMasterConfig() constructor test.
	 *
	 * 
	 */
	@Test
	public void testGtnWsItemMasterConfig_1()
		throws Exception {
		GtnWsItemMasterConfig result = new GtnWsItemMasterConfig();
		assertNotNull(result);
		// add additional test code here
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
		GtnWsItemMasterConfig fixture = new GtnWsItemMasterConfig();
		fixture.setSearchQueryConfigMap(null);

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

		// add additional test code here
		assertNotNull(result);
		assertEquals(4, result.size());
		assertTrue(result.containsKey("SearchQuery"));
		assertTrue(result.containsKey("imNewFormulationSearchQuery"));
		assertTrue(result.containsKey("imQualifierSearchQuery"));
		assertTrue(result.containsKey("imPricnigQualifierSearchQuery"));
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
		GtnWsItemMasterConfig fixture = new GtnWsItemMasterConfig();
		fixture.setSearchQueryConfigMap(new HashMap());

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

		// add additional test code here
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
		GtnWsItemMasterConfig fixture = new GtnWsItemMasterConfig();
		fixture.setSearchQueryConfigMap(new HashMap());

		Map<String, GtnWsSearchQueryConfig> result = fixture.loadSearchQueryConfig();

		// add additional test code here
		assertNotNull(result);
		assertEquals(4, result.size());
		assertTrue(result.containsKey("SearchQuery"));
		assertTrue(result.containsKey("imNewFormulationSearchQuery"));
		assertTrue(result.containsKey("imQualifierSearchQuery"));
		assertTrue(result.containsKey("imPricnigQualifierSearchQuery"));
	}

	/**
	 * Run the void loadValues(String,Map<String,GtnWsColumnDetailsConfig>,GtnWsSearchQueryConfigProvider) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testLoadValues_1()
		throws Exception {
		GtnWsItemMasterConfig fixture = new GtnWsItemMasterConfig();
		fixture.setSearchQueryConfigMap(new HashMap());
		String popUpValue = "";
		Map<String, GtnWsColumnDetailsConfig> itemMasterColumnDetailsMap = new HashMap();
		GtnWsSearchQueryConfigProvider configProvider = new GtnWsSearchQueryConfigProvider();

		fixture.loadValues(popUpValue, itemMasterColumnDetailsMap, configProvider);

		// add additional test code here
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
		GtnWsItemMasterConfig fixture = new GtnWsItemMasterConfig();
		fixture.setSearchQueryConfigMap(new HashMap());
		Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap();

		fixture.setSearchQueryConfigMap(searchQueryConfigMap);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(GtnWsItemMasterConfigTest.class);
	}
}