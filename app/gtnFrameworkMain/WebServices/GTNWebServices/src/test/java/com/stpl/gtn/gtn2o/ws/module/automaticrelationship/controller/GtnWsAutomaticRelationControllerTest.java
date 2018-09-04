package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.controller;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * The class <code>GtnWsAutomaticRelationControllerTest</code> contains tests for the class <code>{@link GtnWsAutomaticRelationController}</code>.
 *
 * 
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
public class GtnWsAutomaticRelationControllerTest {
	/**
	 * Run the GtnWsAutomaticRelationController() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnWsAutomaticRelationController_1()
		throws Exception {

		GtnWsAutomaticRelationController result = new GtnWsAutomaticRelationController();

		
		assertNotNull(result);
	}
//
//	/**
//	 * Run the GtnUIFrameworkWebserviceResponse automaticRelationUpdate(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testAutomaticRelationUpdate_1()
//		throws Exception {
//		GtnWsAutomaticRelationController fixture = new GtnWsAutomaticRelationController();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnUIFrameworkWebserviceResponse result = fixture.automaticRelationUpdate(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.controller.GtnWsAutomaticRelationController.automaticRelationUpdate(GtnWsAutomaticRelationController.java:41)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnUIFrameworkWebserviceResponse automaticRelationUpdate(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testAutomaticRelationUpdate_2()
//		throws Exception {
//		GtnWsAutomaticRelationController fixture = new GtnWsAutomaticRelationController();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnUIFrameworkWebserviceResponse result = fixture.automaticRelationUpdate(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.automaticrelationship.controller.GtnWsAutomaticRelationController.automaticRelationUpdate(GtnWsAutomaticRelationController.java:41)
//		assertNotNull(result);
//	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse automaticRelationUpdateForAllLevel(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testAutomaticRelationUpdateForAllLevel_1()
		throws Exception {
		GtnWsAutomaticRelationController fixture = new GtnWsAutomaticRelationController();
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.automaticRelationUpdateForAllLevel(gtnWsRequest);

		
		assertNotNull(result);
		assertEquals(null, result.getOutBountData());
		assertEquals(null, result.getEditRecord());
		assertEquals(null, result.getItemCodeList());
		assertEquals(null, result.getItemValueList());
		assertEquals(null, result.getGtnUIFrameworkWebserviceTextBoxResponse());
		assertEquals(null, result.getGtnWsPeriodConfigurationResponse());
		assertEquals(null, result.getGtnWsForecastProjectionSubmitResponse());
		assertEquals(null, result.getGtnWsForecastConfigurationResponse());
		assertEquals(null, result.getGtnUIFrameworkWebserviceDateResponse());
		assertEquals(null, result.getGtnWsPriceScheduleGeneralResponse());
		assertEquals(null, result.getGtnWsModuleAuthorizationGeneralResponse());
		assertEquals(null, result.getGtnWsContractDashboardResponse());
		assertEquals(null, result.getGtnWsRelationshipBuilderResponse());
		assertEquals(null, result.getGtnWsComplianceGeneralResponse());
		assertEquals(null, result.getGtnUIFrameworkWebserviceComboBoxResponse());
		assertEquals(null, result.getGtnWsMailConfigurationResponse());
		assertEquals(null, result.getGtnUIFrameworkWebserviceDualListBoxResponse());
		assertEquals(null, result.getGtnWsRebatePlanGeneralResponse());
		assertEquals(null, result.getGtnWsProcessMonitorResponse());
		assertEquals(null, result.getGtnSerachResponse());
		assertEquals(null, result.getGtnWsCfpReponse());
		assertEquals(null, result.getGtnWsIfpReponse());
		assertEquals(null, result.getGtnWsReportResponse());
		assertEquals(null, result.getGtnWsForecastResponse());
		assertEquals(null, result.getGtnReportResponse());
		assertEquals(null, result.getGtnWsItemGroupResponse());
		assertEquals(null, result.getGtnWsCompanyGroupResponse());
		assertEquals(null, result.getResponseStatus());
		assertEquals(null, result.getGtnWsPagedTableResponse());
		assertEquals(null, result.getGtnCompanyMasterResponse());
		assertEquals(null, result.getRebateScheduleInfoBean());
		assertEquals(null, result.getExportFilePath());
		assertEquals(null, result.getGtnWsARMResponse());
		assertEquals(null, result.getAutomaticRelationResponse());
		assertEquals(null, result.getGtnWsCsvExportResponse());
		assertEquals(null, result.getGtnWsAttachmentResponse());
		assertEquals(null, result.getGtnWsItemMasterResponse());
		assertEquals(null, result.getGtnWsNetSalesGeneralResponse());
		assertEquals(null, result.getGtnWSCommonWorkflowResponse());
		assertEquals(null, result.getCalendarConfigurationResponse());
		assertEquals(null, result.getGtnWSPagedTreeTableResponse());
		assertEquals(null, result.getGtnWsTransactionResponse());
		assertEquals(null, result.getGtnWsContractHeaderResponse());
		assertEquals(null, result.getGtnWsExcelResponse());
		assertEquals(null, result.getGtnWsFileManagementResponse());
		assertEquals(null, result.getGtnWsCustomViewResponse());
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse waitAutomaticRelationUpdateForAllLevel(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testWaitAutomaticRelationUpdateForAllLevel_1()
		throws Exception {
		GtnWsAutomaticRelationController fixture = new GtnWsAutomaticRelationController();
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.waitAutomaticRelationUpdateForAllLevel(gtnWsRequest);

		
		assertNotNull(result);
		assertEquals(null, result.getOutBountData());
		assertEquals(null, result.getEditRecord());
		assertEquals(null, result.getItemCodeList());
		assertEquals(null, result.getItemValueList());
		assertEquals(null, result.getGtnUIFrameworkWebserviceTextBoxResponse());
		assertEquals(null, result.getGtnWsPeriodConfigurationResponse());
		assertEquals(null, result.getGtnWsForecastProjectionSubmitResponse());
		assertEquals(null, result.getGtnWsForecastConfigurationResponse());
		assertEquals(null, result.getGtnUIFrameworkWebserviceDateResponse());
		assertEquals(null, result.getGtnWsPriceScheduleGeneralResponse());
		assertEquals(null, result.getGtnWsModuleAuthorizationGeneralResponse());
		assertEquals(null, result.getGtnWsContractDashboardResponse());
		assertEquals(null, result.getGtnWsRelationshipBuilderResponse());
		assertEquals(null, result.getGtnWsComplianceGeneralResponse());
		assertEquals(null, result.getGtnUIFrameworkWebserviceComboBoxResponse());
		assertEquals(null, result.getGtnWsMailConfigurationResponse());
		assertEquals(null, result.getGtnUIFrameworkWebserviceDualListBoxResponse());
		assertEquals(null, result.getGtnWsRebatePlanGeneralResponse());
		assertEquals(null, result.getGtnWsProcessMonitorResponse());
		assertEquals(null, result.getGtnSerachResponse());
		assertEquals(null, result.getGtnWsCfpReponse());
		assertEquals(null, result.getGtnWsIfpReponse());
		assertEquals(null, result.getGtnWsReportResponse());
		assertEquals(null, result.getGtnWsForecastResponse());
		assertEquals(null, result.getGtnReportResponse());
		assertEquals(null, result.getGtnWsItemGroupResponse());
		assertEquals(null, result.getGtnWsCompanyGroupResponse());
		assertEquals(null, result.getResponseStatus());
		assertEquals(null, result.getGtnWsPagedTableResponse());
		assertEquals(null, result.getGtnCompanyMasterResponse());
		assertEquals(null, result.getRebateScheduleInfoBean());
		assertEquals(null, result.getExportFilePath());
		assertEquals(null, result.getGtnWsARMResponse());
		assertEquals(null, result.getAutomaticRelationResponse());
		assertEquals(null, result.getGtnWsCsvExportResponse());
		assertEquals(null, result.getGtnWsAttachmentResponse());
		assertEquals(null, result.getGtnWsItemMasterResponse());
		assertEquals(null, result.getGtnWsNetSalesGeneralResponse());
		assertEquals(null, result.getGtnWSCommonWorkflowResponse());
		assertEquals(null, result.getCalendarConfigurationResponse());
		assertEquals(null, result.getGtnWSPagedTreeTableResponse());
		assertEquals(null, result.getGtnWsTransactionResponse());
		assertEquals(null, result.getGtnWsContractHeaderResponse());
		assertEquals(null, result.getGtnWsExcelResponse());
		assertEquals(null, result.getGtnWsFileManagementResponse());
		assertEquals(null, result.getGtnWsCustomViewResponse());
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
		new org.junit.runner.JUnitCore().run(GtnWsAutomaticRelationControllerTest.class);
	}
}