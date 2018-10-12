package com.stpl.gtn.gtn2o.ws.module.netsales.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.module.netsales.service.GtnWsNsfService;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnWsNetSalesFormulaValidationControllerTest {
	
	@InjectMocks
	@Autowired
	GtnWsNetSalesFormulaValidationController fixture;
	
	@Spy
	@Autowired
	private GtnWsNsfService gtnWsNsfService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Run the GtnWsNetSalesFormulaValidationController() constructor test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGtnWsNetSalesFormulaValidationController_1()
		throws Exception {

		GtnWsNetSalesFormulaValidationController result = new GtnWsNetSalesFormulaValidationController();
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse nsfSalesBasisPopulateValidateService(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testNsfSalesBasisPopulateValidateService_try()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		doNothing().when(gtnWsNsfService).isCheckedRecord(gtnWsRequest);
		  
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = Mockito.mock(GtnWsNetSalesFormulaGeneralRequest.class);
		doReturn(netSalesFormulaGeneralRequest).when(gtnWsRequest).getGtnWsNetSalesGeneralRequest();
		  
		GtnWsNsfUpdateBean gtnWsNsfUpdateBean = Mockito.mock(GtnWsNsfUpdateBean.class);
		
		doReturn(gtnWsNsfUpdateBean).when(netSalesFormulaGeneralRequest).getNsfUpdateBean();
		
		GtnUIFrameworkWebserviceResponse result = fixture.nsfSalesBasisPopulateValidateService(gtnWsRequest);

		assertNotNull(result);
	}
	
	@Test
	public void testNsfSalesBasisPopulateValidateService_catch()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doNothing().when(gtnWsNsfService).isCheckedRecord(gtnWsRequest);
		  
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
		netSalesFormulaGeneralRequest.setNsfUpdateBean(null);
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(null);
		
		GtnUIFrameworkWebserviceResponse result = fixture.nsfSalesBasisPopulateValidateService(gtnWsRequest);

		assertNotNull(result);
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