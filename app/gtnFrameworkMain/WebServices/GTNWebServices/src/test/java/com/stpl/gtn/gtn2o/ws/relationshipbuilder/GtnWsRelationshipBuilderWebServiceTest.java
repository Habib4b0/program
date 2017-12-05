package com.stpl.gtn.gtn2o.ws.relationshipbuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.controller.GtnWsRelationshipBuilderController;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderHierarchyFileGeneratorService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GTNRestController-servlet.xml" })

public class GtnWsRelationshipBuilderWebServiceTest {

	@Autowired
	private GtnWsRelationshipBuilderController gtnWsRelationshipBuilderController;

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

	public GtnWsRelationshipBuilderController getGtnWsRelationshipBuilderController() {
		return gtnWsRelationshipBuilderController;
	}

	public void setGtnWsRelationshipBuilderController(
			GtnWsRelationshipBuilderController gtnWsRelationshipBuilderController) {
		this.gtnWsRelationshipBuilderController = gtnWsRelationshipBuilderController;
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
	public void testGetRelationshipBuilderTableData() {
		System.out.println("inside testGetRelationshipBuilderTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsRelationshipBuilderController()
				.getRelationshipBuilderTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsRelationshipBuilderController().getRelationshipBuilderTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testGetVersionNo() {
		System.out.println("inside testGetVersionNo");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		serviceRequest.setRelationshipBuilderRequest(rbRequest);
		rbRequest.setHierarchyDefSId(1);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsRelationshipBuilderController()
				.getVersionNo(serviceRequest);
		System.out.println(
				"version no = " + serviceResponse.getGtnWsRelationshipBuilderResponse().getHierarchyVersionNo());
	}

	private GtnWsRelationshipBuilderRequest getModifiedRequest(GtnWsRelationshipBuilderRequest rbRequest) {
		if (rbRequest.getMainNode() != null) {
			rbRequest.setRelationshipName(rbRequest.getMainNode().getStringPropertyByIndex(0));
			rbRequest.setRelationshipDescription(rbRequest.getMainNode().getStringPropertyByIndex(1));
			rbRequest.setRelationshipType(rbRequest.getMainNode().getStringPropertyByIndex(2));
			rbRequest.setHierarchyName(rbRequest.getMainNode().getStringPropertyByIndex(3));
			rbRequest.setVersionNo(rbRequest.getMainNode().getIntegerPropertyByIndex(4));
			rbRequest.setStartDate(rbRequest.getMainNode().getDatePropertyByIndex(5));
			rbRequest.setCreationDate(rbRequest.getMainNode().getDatePropertyByIndex(6));
			rbRequest.setModifiedDate(rbRequest.getMainNode().getDatePropertyByIndex(7));
			rbRequest.setCreatedBy(rbRequest.getMainNode().getStringPropertyByIndex(8));
			rbRequest.setRbSysId(rbRequest.getMainNode().getIntegerPropertyByIndex(9));
			rbRequest.setHierarchyDefSId(rbRequest.getMainNode().getIntegerPropertyByIndex(10));
			rbRequest.setBuildType(rbRequest.getMainNode().getStringPropertyByIndex(11));
			rbRequest.setHierarchyVersionNo(rbRequest.getMainNode().getIntegerPropertyByIndex(12));
			rbRequest.setNoOfLevels(rbRequest.getMainNode().getIntegerPropertyByIndex(13));
			rbRequest.setCreatedById(rbRequest.getMainNode().getIntegerPropertyByIndex(14));
			rbRequest.setRelationshipTypeId(rbRequest.getMainNode().getIntegerPropertyByIndex(15));
		}
		return rbRequest;
	}

	@Test
	public void testLoadRelationship() {
		System.out.println("inside testLoadRelationship");
		try {
			GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
			// [customer_relation, customer_relation, Primary,
			// customer_hierarchy, 1, 2017-04-25 00:00:00.0, 2017-04-25
			// 08:36:39.333, 2017-04-25 08:36:39.333, Admin BPI, 1, 1, Manual,
			// 2, 4, 10922, 368]
			GtnWsRecordBean relationshipBean = new GtnWsRecordBean();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			relationshipBean.addProperties("customer_relation");
			relationshipBean.addProperties("customer_relation");
			relationshipBean.addProperties("Primary");
			relationshipBean.addProperties("customer_hierarchy");
			relationshipBean.addProperties(1);
			relationshipBean.addProperties(formatter.parse("2017-04-25"));
			relationshipBean.addProperties(formatter.parse("2017-04-25"));
			relationshipBean.addProperties(formatter.parse("2017-04-25"));
			relationshipBean.addProperties("Admin BPI");
			relationshipBean.addProperties(1);
			relationshipBean.addProperties(1);
			relationshipBean.addProperties("Manual");
			relationshipBean.addProperties(2);
			relationshipBean.addProperties(4);
			relationshipBean.addProperties(10922);
			relationshipBean.addProperties(368);

			GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
			serviceRequest.setRelationshipBuilderRequest(rbRequest);
			rbRequest.setMainNode(relationshipBean);
			getModifiedRequest(rbRequest);
			getGtnWsRelationshipBuilderController().loadRelationship(serviceRequest);
		} catch (Exception e) {
			System.out.println("Can not parse date");
		}
	}

	@Test
	public void updateQueryInHierarchy() throws GtnFrameworkGeneralException, IOException {
		GtnWsRelationshipBuilderHierarchyFileGeneratorService logic = new GtnWsRelationshipBuilderHierarchyFileGeneratorService();
		logic.updateQueryInHierarchy(1, 2);

	}

}
