package com.stpl.gtn.gtn2o.ws.module.netsales.config;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnWebServiceNetSalesFormulaconfigTest {
	/**
	 * Run the GtnWebServiceNetSalesFormulaconfig() constructor test.
	 *
	
	 */
	@Test
	public void testGtnWebServiceNetSalesFormulaconfig_1()
		throws Exception {
		GtnWebServiceNetSalesFormulaconfig result = new GtnWebServiceNetSalesFormulaconfig();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetSearchQueryConfigMap_1()
		throws Exception {
		GtnWebServiceNetSalesFormulaconfig fixture = new GtnWebServiceNetSalesFormulaconfig();

		Map<String, GtnWsSearchQueryConfig> result = fixture.getSearchQueryConfigMap();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void loadAvailableCustomersSearchQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadAvailableCustomersSearchQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadAvailableCustomersSearchQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadAvailableDeductionContractQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadAvailableDeductionContractQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadAvailableDeductionContractQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadAvailableDeductionRSQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadAvailableDeductionRSQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadAvailableDeductionRSQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadContractSearchQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadContractSearchQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadContractSearchQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadSalesBaisSelectedCustomersQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadSalesBaisSelectedCustomersQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadSalesBaisSelectedCustomersQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadSearchQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadSearchQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadSearchQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadSelectedDeductionContractQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadSelectedDeductionContractQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadSelectedDeductionContractQueryConfig();

		// add additional test code here
	}

	/**
	 * Run the void loadSelectedDeductionRSQueryConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testLoadSelectedDeductionRSQueryConfig_1()
		throws Exception {

		GtnWebServiceNetSalesFormulaconfig.loadSelectedDeductionRSQueryConfig();

		// add additional test code here
	}

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

}