package com.stpl.gtn.gtn2o.ws.module.udc.config;


import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsUdcConfigTest {

	@Autowired
	GtnWsUdcConfig ins;
	
	@Test
	public void testGetSearchQueryConfigMap() {
		ins.getSearchQueryConfigMap();
	}

	@Test
	public void testSetSearchQueryConfigMap() {
		Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
		ins.setSearchQueryConfigMap(searchQueryConfigMap);
	}

}
