package com.stpl.gtn.gtn2o.ws.module.customview.config;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;

public class GtnWebServiceCustomSearchConfigTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSearchQueryConfigMap_1() {
		GtnWebServiceCustomSearchConfig ins = new GtnWebServiceCustomSearchConfig();
		ins.getSearchQueryConfigMap();
	}
	
	@Test
	public void testSetSearchQueryConfigMap() {
		GtnWebServiceCustomSearchConfig ins = new GtnWebServiceCustomSearchConfig();
		Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap=new HashMap<>();
		ins.setSearchQueryConfigMap(searchQueryConfigMap);
	}

}
