package com.stpl.gtn.gtn2o.ws.contractdashboard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GTNRestController-servlet.xml" })

public class GtnWsContractDashboardWebServiceTest {

	@Autowired
	private GtnWsContractDashboardController gtnWsContractDashboardController;

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

	public GtnWsContractDashboardController getGtnWsContractDashboardController() {
		return gtnWsContractDashboardController;
	}

	public void setGtnWsContractDashboardController(GtnWsContractDashboardController gtnWsContractDashboardController) {
		this.gtnWsContractDashboardController = gtnWsContractDashboardController;
	}

	private GtnUIFrameworkWebserviceRequest getWsRequest() {
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(null);

		generalWSRequest.setUserId("10948");
		generalWSRequest.setSessionId(String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)));
		serviceRequest.setGtnWsGeneralRequest(generalWSRequest);

		request.setGtnWebServiceSearchCriteriaList(new ArrayList<GtnWebServiceSearchCriteria>());
		request.setGtnWebServiceOrderByCriteriaList(new ArrayList<GtnWebServiceOrderByCriteria>());
		serviceRequest.setGtnWsSearchRequest(request);
		return serviceRequest;
	}

	@Test
	public void testGetContractDashboardTableData() {
		System.out.println("inside testGetContractDashboardTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("CDMainView_ComponentName");
		searchCriteria.setFilterValue1(GtnWsContractDashboardContants.CONTRACT);
		searchCriteria.setExpression(searchCriteria.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().add(searchCriteria);
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getContractDashboardTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getContractDashboardTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetContractDashboardDetailsTableData() {
		System.out.println("inside testGetContractDashboardDetailsTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("CDMainView_ComponentName");
		searchCriteria.setFilterValue1(GtnWsContractDashboardContants.COMPANY_FAMILY_PLAN);
		searchCriteria.setExpression(searchCriteria.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().add(searchCriteria);
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getContractDashboardDetailsTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController()
					.getContractDashboardDetailsTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testCheckAddToTree() {
		System.out.println("inside testCheckAddToTree");
		try {
			GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
			GtnWsRecordBean treeBean = new GtnWsRecordBean();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			treeBean.addProperties("Test_27");
			treeBean.addProperties("Test_27");
			treeBean.addProperties("Test_27");
			treeBean.addProperties("Alliance");
			treeBean.addProperties(2);
			treeBean.addProperties(formatter.parse("2017-04-27").getTime());
			treeBean.addProperties(null);
			treeBean.addProperties("1");
			treeBean.addProperties("Contract");
			treeBean.addProperties(2);
			treeBean.addProperties(218);

			GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("CFP_1");
			tableBean.addProperties("CFP_1");
			tableBean.addProperties("CFP_1");
			tableBean.addProperties("OPEN");
			tableBean.addProperties(1);
			tableBean.addProperties(formatter.parse("2014-01-01").getTime());
			tableBean.addProperties(formatter.parse("2017-01-01").getTime());
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties(0);
			tableBean.addProperties(618);
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			cdRequest.setTableBean(tableBean);
			cdRequest.setTreeBean(treeBean);
			serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
			GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
					.checkAddToTree(serviceRequest);
			System.out.println("success=" + serviceResponse.getGtnWsContractDashboardResponse().isSuccess());
			System.out.println("message type=" + serviceResponse.getGtnWsContractDashboardResponse().getMessageType());
			System.out.println(
					"message header =" + serviceResponse.getGtnWsContractDashboardResponse().getMessageHeader());
			System.out.println("message=" + serviceResponse.getGtnWsContractDashboardResponse().getMessage());
		} catch (ParseException e) {
			System.out.println("Unable to parse date");
		}
	}

	@Test
	public void testAddToTree() {
		System.out.println("inside testAddToTree");
		try {
			GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
			GtnWsRecordBean treeBean = new GtnWsRecordBean();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			treeBean.addProperties("Test_27");
			treeBean.addProperties("Test_27");
			treeBean.addProperties("Test_27");
			treeBean.addProperties("Alliance");
			treeBean.addProperties(2);
			treeBean.addProperties(formatter.parse("2017-04-27").getTime());
			treeBean.addProperties(null);
			treeBean.addProperties("1");
			treeBean.addProperties("Contract");
			treeBean.addProperties(2);
			treeBean.addProperties(218);

			GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("CFP_1");
			tableBean.addProperties("CFP_1");
			tableBean.addProperties("CFP_1");
			tableBean.addProperties("OPEN");
			tableBean.addProperties(1);
			tableBean.addProperties(formatter.parse("2014-01-01").getTime());
			tableBean.addProperties(formatter.parse("2017-01-01").getTime());
			tableBean.addProperties("2");
			tableBean.addProperties("CFP");
			tableBean.addProperties(0);
			tableBean.addProperties(618);
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			cdRequest.setTableBean(tableBean);
			cdRequest.setTreeBean(treeBean);
			serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
			GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
					.addToTree(serviceRequest);
			System.out.println("success=" + serviceResponse.getGtnWsContractDashboardResponse().isSuccess());
			System.out.println("message type=" + serviceResponse.getGtnWsContractDashboardResponse().getMessageType());
			System.out.println(
					"message header =" + serviceResponse.getGtnWsContractDashboardResponse().getMessageHeader());
			System.out.println("message=" + serviceResponse.getGtnWsContractDashboardResponse().getMessage());
		} catch (ParseException e) {
			System.out.println("Unable to parse date");
		}
	}

	@Test
	public void testGetContractDashboardProcessTableData() {
		System.out.println("inside testGetContractDashboardProcessTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId(GtnWsContractDashboardContants.RIGHT + "CDMainView_ComponentName");
		searchCriteria.setFilterValue1(GtnWsContractDashboardContants.CONTRACT);
		searchCriteria.setExpression(searchCriteria.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().add(searchCriteria);
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getContractDashboardProcessTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController()
					.getContractDashboardProcessTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}

	}

	@Test
	public void testGetCDCompanyAdditionLeftTableData() {
		System.out.println("inside testGetCDCompanyAdditionLeftTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDCompanyAdditionLeftTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDCompanyAdditionLeftTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDItemAdditionRightTableData() {
		System.out.println("inside testGetCDItemAdditionRightTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDItemAdditionRightTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDItemAdditionRightTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDItemAdditionLeftTableData() {
		System.out.println("inside testGetCDItemAdditionLeftTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		System.out.println("Case 1");
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("CDMainView_ComponentName");
		searchCriteria.setFilterValue1("Brand Name");
		searchCriteria.setExpression(searchCriteria.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().add(searchCriteria);
		GtnWebServiceSearchCriteria searchCriteria1 = new GtnWebServiceSearchCriteria();
		searchCriteria1.setFieldId("CDMainView_ComponentName");
		searchCriteria1.setFilterValue1("*");
		searchCriteria1.setExpression(searchCriteria1.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().add(searchCriteria1);

		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDItemAdditionLeftTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDItemAdditionLeftTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
		System.out.println("Case 2");
		searchCriteria.setFilterValue1("IFP No");
		searchCriteria1.setFieldId("CDMainView_ComponentName");
		searchCriteria1.setFilterValue1("*");
		searchCriteria1.setExpression(searchCriteria1.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		serviceResponse = getGtnWsContractDashboardController().getCDItemAdditionLeftTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDItemAdditionLeftTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
		System.out.println("Case 3");

		searchCriteria.setFilterValue1("Item Name");
		searchCriteria1.setFieldId("CDMainView_ComponentName");
		searchCriteria1.setFilterValue1("*");
		searchCriteria1.setExpression(searchCriteria1.getFilterValue1().contains("*") ? "LIKE" : "EQUALS");
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		serviceResponse = getGtnWsContractDashboardController().getCDItemAdditionLeftTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDItemAdditionLeftTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDCompniesDetailTableData() {
		System.out.println("inside testGetCDCompniesDetailTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDCompniesDetailTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDCompniesDetailTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDCompniesDetailViewTableData() {
		System.out.println("inside testGetCDCompniesDetailViewTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDCompniesDetailViewTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDCompniesDetailViewTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDPricingDetailTableData() {
		System.out.println("inside testGetCDPricingDetailTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDPricingDetailTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDPricingDetailTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDPricingDetailViewTableData() {
		System.out.println("inside testGetCDPricingDetailViewTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDPricingDetailViewTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDPricingDetailViewTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDPricingProtectionTableData() {
		System.out.println("inside testGetCDPricingProtectionTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDPricingProtectionTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDPricingProtectionTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDPricingProtectionViewTableData() {
		System.out.println("inside testGetCDPricingProtectionViewTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDPricingProtectionViewTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDPricingProtectionViewTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDRebateDetailTableData() {
		System.out.println("inside testGetCDRebateDetailTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDRebateDetailTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDRebateDetailTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCDRebateDetailViewTableData() {
		System.out.println("inside testGetCDRebateDetailViewTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCDRebateDetailViewTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCDRebateDetailViewTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetNSFLookupTableData() {
		System.out.println("inside testGetNSFLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getNSFLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getNSFLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetCFPLookupTableData() {
		System.out.println("inside testGetCFPLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCFPLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getCFPLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetIFPLookupTableData() {
		System.out.println("inside testGetIFPLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getIFPLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getIFPLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetTPLookupTableData() {
		System.out.println("inside testGetTPLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getTPLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getTPLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetPSLookupTableData() {
		System.out.println("inside testGetPSLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getPSLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getPSLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetRSLookupTableData() {
		System.out.println("inside testGetRSLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getRSLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getRSLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetRulesLookupTableData() {
		System.out.println("inside testGetRulesLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getRulesLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getRulesLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetRuleDetailsLookupTableData() {
		System.out.println("inside testGetRuleDetailsLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		serviceRequest.getGtnWsGeneralRequest().setExtraParameter(0);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getRuleDetailsLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getRuleDetailsLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testValidateContractToProcess() {
		System.out.println("inside testValidateContractToProcess");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setContractId(2);
		serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.validateContractToProcess(serviceRequest);
		GtnWsContractDashboardResponse cdNewResponse = serviceResponse.getGtnWsContractDashboardResponse();
		System.out.println("ValidateContractToProcess= " + cdNewResponse.isSuccess());
	}

	@Test
	public void testGetContractInfoFieldData() {
		System.out.println("inside testGetContractInfoFieldData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setUserId(serviceRequest.getGtnWsGeneralRequest().getUserId());
		cdRequest.setSessionId(serviceRequest.getGtnWsGeneralRequest().getSessionId());

		serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
		serviceRequest.getGtnWsGeneralRequest();
		cdRequest.setContractId(2);
		cdRequest.setSessionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		cdRequest.setCfpContractId(2);
		cdRequest.setIfpContractId(2);
		cdRequest.setPsContractId(2);
		cdRequest.setRsContractId(2);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getContractInfoFieldData(serviceRequest);
		GtnWsContractDashboardResponse cdNewResponse = serviceResponse.getGtnWsContractDashboardResponse();
		System.out.println("contract bean=" + cdNewResponse.getContractInfoBean().getProperties());
	}

	@Test
	public void testGetCompanyInfoFieldData() {
		System.out.println("inside testGetCompanyInfoFieldData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setUserId(serviceRequest.getGtnWsGeneralRequest().getUserId());
		cdRequest.setSessionId(serviceRequest.getGtnWsGeneralRequest().getSessionId());

		serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
		serviceRequest.getGtnWsGeneralRequest();
		cdRequest.setContractId(2);
		cdRequest.setSessionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		cdRequest.setCfpContractId(2);
		cdRequest.setIfpContractId(2);
		cdRequest.setPsContractId(2);
		cdRequest.setRsContractId(2);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getCompanyInfoFieldData(serviceRequest);
		GtnWsContractDashboardResponse cdNewResponse = serviceResponse.getGtnWsContractDashboardResponse();
		System.out.println("Company bean=" + cdNewResponse.getCompanyInfoBean().getProperties());
	}

	@Test
	public void testGetItemInfoFieldData() {
		System.out.println("inside testGetItemInfoFieldData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setUserId(serviceRequest.getGtnWsGeneralRequest().getUserId());
		cdRequest.setSessionId(serviceRequest.getGtnWsGeneralRequest().getSessionId());

		serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
		serviceRequest.getGtnWsGeneralRequest();
		cdRequest.setContractId(2);
		cdRequest.setSessionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		cdRequest.setCfpContractId(2);
		cdRequest.setIfpContractId(2);
		cdRequest.setPsContractId(2);
		cdRequest.setRsContractId(2);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getItemInfoFieldData(serviceRequest);
		GtnWsContractDashboardResponse cdNewResponse = serviceResponse.getGtnWsContractDashboardResponse();
		System.out.println("Item bean=" + cdNewResponse.getItemInfoBean().getProperties());

	}

	@Test
	public void testGetPricingInfoFieldData() {
		System.out.println("inside testGetPricingInfoFieldData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setUserId(serviceRequest.getGtnWsGeneralRequest().getUserId());
		cdRequest.setSessionId(serviceRequest.getGtnWsGeneralRequest().getSessionId());

		serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
		serviceRequest.getGtnWsGeneralRequest();
		cdRequest.setContractId(2);
		cdRequest.setSessionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		cdRequest.setCfpContractId(2);
		cdRequest.setIfpContractId(2);
		cdRequest.setPsContractId(2);
		cdRequest.setRsContractId(2);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getPricingInfoFieldData(serviceRequest);
		GtnWsContractDashboardResponse cdNewResponse = serviceResponse.getGtnWsContractDashboardResponse();
		System.out.println("Pricing bean=" + cdNewResponse.getPriceInfoBean().getProperties());

	}

	@Test
	public void testGetRebateInfoFieldData() {
		System.out.println("inside testGetRebateInfoFieldData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
		cdRequest.setUserId(serviceRequest.getGtnWsGeneralRequest().getUserId());
		cdRequest.setSessionId(serviceRequest.getGtnWsGeneralRequest().getSessionId());

		serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
		serviceRequest.getGtnWsGeneralRequest();
		cdRequest.setContractId(2);
		cdRequest.setSessionDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		cdRequest.setCfpContractId(2);
		cdRequest.setIfpContractId(2);
		cdRequest.setPsContractId(2);
		cdRequest.setRsContractId(2);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getRebateInfoFieldData(serviceRequest);
		GtnWsContractDashboardResponse cdNewResponse = serviceResponse.getGtnWsContractDashboardResponse();
		System.out.println("Rebate bean=" + cdNewResponse.getRebateInfoBean().getProperties());

	}

	@Test
	public void testGetRPLookupTableData() {
		System.out.println("inside testGetRPLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getRPLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getRPLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetDCLookupTableData() {
		System.out.println("inside testGetDCLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getDCLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getDCLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetFormulaLookupTableData() {
		System.out.println("inside testGetFormulaLookupTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
				.getFormulaLookupTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsContractDashboardController().getFormulaLookupTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetContractDashboardRebuildTreeData() {
		System.out.println("inside testGetContractDashboardRebuildTreeData");
		try {
			GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
			GtnWsContractDashboardRequest cdRequest = new GtnWsContractDashboardRequest();
			cdRequest.setContractId(2);
			serviceRequest.setGtnWsContractDashboardRequest(cdRequest);
			// [Contract, Test_27, Test_27, Test_27, 1, 1, 2, 1, 0, 0, 0, 0,
			// 2017-04-27 00:00:00.0, null, false, 0, 0, 0, 0]
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			GtnWsRecordBean parentBean = new GtnWsRecordBean();
			parentBean.addProperties("Contract");
			parentBean.addProperties("Test_27");
			parentBean.addProperties("Test_27");
			parentBean.addProperties("Test_27");
			parentBean.addProperties("1");
			parentBean.addProperties("1");
			parentBean.addProperties(2);
			parentBean.addProperties(1);
			parentBean.addProperties(0);
			parentBean.addProperties(0);
			parentBean.addProperties(0);
			parentBean.addProperties("0");
			parentBean.addProperties(formatter.parse("2017-04-27").getTime());
			parentBean.addProperties(null);
			parentBean.addProperties(false);
			parentBean.addProperties(0);
			parentBean.addProperties(0);
			parentBean.addProperties(0);
			parentBean.addProperties(0);
			serviceRequest.getGtnWsSearchRequest().setParentBean(parentBean);
			GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsContractDashboardController()
					.getContractDashboardRebuildTreeData(serviceRequest);
			GtnWsRecordBean treeBean = serviceResponse.getGtnWsContractDashboardResponse().getTreeBean();
			System.out.println("" + treeBean.getProperties());

		} catch (ParseException e) {
			System.out.println("Unable to parse date");
		}

	}

	@Test
	public void testGetSysSchemaCatalog() {
		try {
			System.out.println("inside testGetSysSchemaCatalog");
			System.out.println("catalog=" + getGtnWsContractDashboardController().getSysSchemaCatalog());
		} catch (GtnFrameworkGeneralException ex) {

		}
	}
}
