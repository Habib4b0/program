package com.stpl.gtn.gtn2o.ws.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardFilterBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDashboardFilterOptionService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReportDashboard-SpringContext-Text.xml" })
public class GtnWsReportDashboardFilterOptionTest {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportDashboardFilterOptionTest.class);
	
	@Autowired
	private GtnWsReportDashboardFilterOptionService reportDashboardFilterService;

	@Ignore
	@Test
	public void getCustAndProdLevelValues() {
		GtnWsReportDashboardBean gtnWsReportDashboardBean = new GtnWsReportDashboardBean();
		gtnWsReportDashboardBean.setHierarchyType(GtnWsHierarchyType.CUSTOMER);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setCustomerHierarchyForecastLevel(3);
		dataSelectionBean.setCustomerHierarchySid(11);

		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsReportBean reportBean = new GtnWsReportBean();
		reportBean.setDataSelectionBean(dataSelectionBean);
		reportRequest.setGtnWsReportDashboardBean(gtnWsReportDashboardBean);
		reportRequest.setReportBean(reportBean);

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		gtnWsRequest.setGtnWsReportRequest(reportRequest);

		try {
			List<Object[]> resultList = reportDashboardFilterService.getCustAndProdLevelValues(gtnWsRequest);
		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void getCustomerFilter() throws GtnFrameworkGeneralException {
		gtnLogger.info("customerLevelFilter");
		GtnWsReportDashboardFilterBean filterBean = new GtnWsReportDashboardFilterBean();

		filterBean.setCustomerLevelNo(5);
		filterBean.setHierarchyType("C");
		filterBean.setDeductionLevelNo(10);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setCustomerHierarchySid(21);
		dataSelectionBean.setCustomerHierarchyVersionNo(1);
		dataSelectionBean.setCustomerRelationshipBuilderSid(83);
		dataSelectionBean.setCustomerRelationshipVersionNo(2);
		dataSelectionBean.setProductRelationshipBuilderSid(80);
		dataSelectionBean.setSessionId("8f642725_f4ef_4a84_af94_e2820eedd88e");

		filterBean.setSelectedProductList(Arrays.asList(1381, 1382, 1371, 1367, 1278, 1270, 1379, 1325, 1434, 1436));
		filterBean.setSelectedDeductionList(Arrays.asList(21406, 15713, 21146, 21147, 21148, 21149, 21151, 21145));

		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		reportRequest.setFilterBean(filterBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsReportRequest(reportRequest);
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId("20156");
		generalRequest.setSessionId("670");
		request.setGtnWsGeneralRequest(generalRequest);
		reportDashboardFilterService.loadCustomerLevelFilter(request);
	}

	@Ignore
	@Test
	public void loadProductFilter() throws GtnFrameworkGeneralException {
		gtnLogger.info("productLevelFilter");
		GtnWsReportDashboardFilterBean filterBean = new GtnWsReportDashboardFilterBean();
		filterBean.setProductLevelNo(5);
		filterBean.setHierarchyType("P");
		filterBean.setDeductionLevelNo(10);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setProductHierarchySid(22);
		dataSelectionBean.setProductHierarchyVersionNo(1);
		dataSelectionBean.setProductRelationshipBuilderSid(80);
		dataSelectionBean.setProductRelationshipVersionNo(1);
		dataSelectionBean.setCustomerRelationshipBuilderSid(83);
		dataSelectionBean.setSessionId("8f642725_f4ef_4a84_af94_e2820eedd88e");

		filterBean.setSelectedCustomerList(Arrays.asList(2924, 2956));
		filterBean.setSelectedDeductionList(Arrays.asList(21406, 15713, 21146, 21147, 21148, 21149, 21151, 21145));

		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		reportRequest.setFilterBean(filterBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsReportRequest(reportRequest);
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId("20156");
		generalRequest.setSessionId("670");
		request.setGtnWsGeneralRequest(generalRequest);
		reportDashboardFilterService.loadCustomerLevelFilter(request);
	}

	@Ignore
	@Test
	public void testDeductionFilter() throws GtnFrameworkGeneralException {
		gtnLogger.info("deductionLevelFilter");
		GtnWsReportDashboardFilterBean filterBean = new GtnWsReportDashboardFilterBean();
		filterBean.setSelectedCustomerList(Arrays.asList(2924, 2956));
		filterBean.setSelectedProductList(Arrays.asList(1381, 1382, 1371, 1367, 1278, 1270, 1379, 1325, 1434, 1436));
		filterBean.setDeductionLevelNo(1);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setCustomerRelationshipBuilderSid(83);
		dataSelectionBean.setProductRelationshipBuilderSid(80);
		dataSelectionBean.setSessionId("8f642725_f4ef_4a84_af94_e2820eedd88e");
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		reportRequest.setFilterBean(filterBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsReportRequest(reportRequest);
		reportDashboardFilterService.loadDeductionFilter(request);
	}
	
	@Test
	public void getFilteredValues() throws GtnFrameworkGeneralException{
		GtnWsReportDashboardFilterBean filterBean = new GtnWsReportDashboardFilterBean();
		filterBean.setSelectedCustomerList(Arrays.asList(2924, 2956));
		filterBean.setSelectedProductList(Arrays.asList(1381, 1382, 1371, 1367, 1278, 1270, 1379, 1325, 1434, 1436));
		filterBean.setSelectedDeductionList(Arrays.asList(21406, 15713, 21146, 21147, 21148));
		filterBean.setDeductionLevelNo(10);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setCustomerRelationshipBuilderSid(83);
		dataSelectionBean.setProductRelationshipBuilderSid(80);
		dataSelectionBean.setSessionId("d9511aa4_c9dc_46bb_a75d_0e4b9b7a1944");
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		reportRequest.setFilterBean(filterBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsReportRequest(reportRequest);
		String query = reportDashboardFilterService.getFilteredValues(request);
	}

}
