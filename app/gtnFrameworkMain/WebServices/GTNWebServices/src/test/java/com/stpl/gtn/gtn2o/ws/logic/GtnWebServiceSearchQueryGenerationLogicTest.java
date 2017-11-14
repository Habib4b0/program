/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
/**
 *
 * @author Abishek.Ram
 */
import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

@Ignore
public class GtnWebServiceSearchQueryGenerationLogicTest {

	public GtnWebServiceSearchQueryGenerationLogicTest() {
		// constructor
	}

	@BeforeClass
	public static void setUpClass() {
		return;
	}

	@AfterClass
	public static void tearDownClass() {
		return;
	}

	@Before
	public void setUp() {
		return;
	}

	@After
	public void tearDown() {
		return;
	}

	/**
	 * Test of generateSearchQuery method, of class
	 * GtnWebServiceSearchQueryGenerationLogic.
	 */
	@Test
	public void testGenerate() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnUIFrameworkWebserviceRequest gtnsdUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		Map<String, GtnWsColumnDetailsConfig> uimap = new HashMap<>();
		uimap.put("LIST_NAME", new GtnWsColumnDetailsConfig("LIST_NAME", "String", "HT"));
		uimap.put("HELPER_TABLE_SID", new GtnWsColumnDetailsConfig("HELPER_TABLE_SID", "Integer", "HT"));
		uimap.put("DESCRIPTION", new GtnWsColumnDetailsConfig("DESCRIPTION", "String", "HT"));
		uimap.put("REF_COUNT", new GtnWsColumnDetailsConfig("REF_COUNT", "Integer", "HT"));
		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(uimap);
		gtnWebServiceSearchQueryConfig.setSearchQuery("FROM HELPER_TABLE HT");
		gtnWebServiceSearchQueryConfig.setWhereClauseList(Arrays.asList(new String[] { " HELPER_TABLE_SID >0" }));
		gtnWebServiceSearchQueryConfig.setOrderByClause(Arrays.asList(
				new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("HELPER_TABLE_SID", "ASC"),
						new GtnWebServiceOrderByCriteria("DESCRIPTION", "DESC") }));
		searchRequest.setCount(false);
		searchRequest.setSearchColumnNameList(
				Arrays.asList(new Object[] { "LIST_NAME", "HELPER_TABLE_SID", "DESCRIPTION" }));
		searchRequest.setGtnWebServiceSearchCriteriaList(Arrays
				.asList(new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("REF_COUNT", "0", ">=") }));
		searchRequest.setGtnWebServiceOrderByCriteriaList(Arrays.asList(
				new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("HELPER_TABLE_SID", "ASC"),
						new GtnWebServiceOrderByCriteria("DESCRIPTION", "DESC") }));
		gtnsdUIFrameworkWebserviceRequest.setGtnWsSearchRequest(searchRequest);
		GtnWsSearchQueryGenerationLogic generationLogic = new GtnWsSearchQueryGenerationLogic(
				gtnWebServiceSearchQueryConfig, gtnsdUIFrameworkWebserviceRequest);
		System.out.println("Query is-- " + generationLogic.generateSearchQuery());
	}

}
