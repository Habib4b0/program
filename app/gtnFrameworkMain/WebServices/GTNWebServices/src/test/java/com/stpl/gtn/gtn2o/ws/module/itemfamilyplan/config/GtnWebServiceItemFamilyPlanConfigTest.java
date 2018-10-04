package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;

/**
 * 
 * @author Spandan.Majumder
 * @version $Revision: 1.0
 */
public class GtnWebServiceItemFamilyPlanConfigTest {

	@Test
	public void testGtnWebServiceItemFamilyPlanConfig_1() throws Exception {
		GtnWebServiceItemFamilyPlanConfig result = new GtnWebServiceItemFamilyPlanConfig();
		assertNotNull(result);

	}

	@Test
	public void testGetSearchQueryConfigMap_1() throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(null);

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("ifpSearchQuery"));
		assertTrue(result.containsKey("parentIfpSearchQuery"));
	}

	@Test
	public void testGetSearchQueryConfigMap_2() throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(new HashMap());

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testLoadSearchQueryConfig_1() throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(new HashMap());

		Map<String, GtnWsSearchQueryConfig> result = fixture.loadSearchQueryConfig();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("ifpSearchQuery"));
		assertTrue(result.containsKey("parentIfpSearchQuery"));
	}

	@Test
	public void testSetSearchQueryConfigMap_1() throws Exception {
		GtnWebServiceItemFamilyPlanConfig fixture = new GtnWebServiceItemFamilyPlanConfig();
		fixture.setSearchQueryConfigMap(new HashMap());
		Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap();

		fixture.setSearchQueryConfigMap(searchQueryConfigMap);

	}

}