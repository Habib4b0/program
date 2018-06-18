package com.stpl.gtn.gtn2o.ws.controller;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.constants.MongoConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewDataBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.controller.GtnWsReportCustomViewController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReport-SpringContext-Test.xml" })
public class GtnWsReportCustomViewControllerTest {
	@Autowired
	GtnWsReportCustomViewController controller;

	@Autowired
	SessionFactory sessionFactory;

//	@Autowired
//	com.mongodb.MongoClient client;

	// @Test
	public void testDisplay() {
		prepereData();
		GtnUIFrameworkWebserviceRequest request = getRequest();
		request.getGtnWsReportRequest().getReportBean().getDataSelectionBean().setName("12114" + "UddasEdvbas$5");
		getcustomerHierarchyRequest(request);
		controller.loadHierarchyLevels(request);
		deleteData();
	}

	private void deleteData() {
//		client.getDatabase(MongoConstants.DATABSE_NAME).drop();
	}

	private GtnUIFrameworkWebserviceRequest getcustomerHierarchyRequest(GtnUIFrameworkWebserviceRequest request) {
		GtnWsReportCustomViewBean customViewBean = new GtnWsReportCustomViewBean();
		customViewBean.setHierarchyType(GtnWsHierarchyType.CUSTOMER);
		request.getGtnWsReportRequest().getReportBean().setCustomViewBean(customViewBean);
		return request;
	}

	private GtnUIFrameworkWebserviceRequest getRequest() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		request.setGtnWsReportRequest(reportRequest);
		GtnWsReportDataSelectionBean bean = new GtnWsReportDataSelectionBean();
		reportRequest.getReportBean().setDataSelectionBean(bean);
		GtnWsReportCustomViewBean viewBean = new GtnWsReportCustomViewBean();
		GtnWsReportCustomViewDataBean dataBean = new GtnWsReportCustomViewDataBean();
		viewBean.setCustomViewDataBean(dataBean);
		reportRequest.getReportBean().setCustomViewBean(viewBean);
		return request;
	}

	private void prepereData() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//		MongoCollection<GtnWsReportDataSelectionBean> collection = client.getDatabase(MongoConstants.DATABSE_NAME)
//				.withCodecRegistry(pojoCodecRegistry)
//				.getCollection(Mong/oStringConstants.REPORT_COLLECTION, GtnWsReportDataSelectionBean.class);
//		GtnWsReportDataSe/lectionBean bean = createDummyHierarchySelection();
//		collection.insertOne(bean);
	}

	private GtnWsReportDataSelectionBean createDummyHierarchySelection() {
		GtnWsReportDataSelectionBean bean = new GtnWsReportDataSelectionBean();
		Query query = sessionFactory.openSession()
				.createSQLQuery("select\r\n" + "	TOP 1 HIERARCHY_DEFINITION_SID\r\n" + "from\r\n"
						+ "	dbo.HIERARCHY_DEFINITION HD\r\n" + "join dbo.HELPER_TABLE HT on\r\n"
						+ "	HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY\r\n"
						+ "	where HT.DESCRIPTION like '%product%'\r\n" + "	ORDER BY NEWID()");
		bean.setProductHierarchySid((int) query.list().get(0));

		query = sessionFactory.openSession()
				.createSQLQuery("select\r\n" + "	TOP 1 HIERARCHY_DEFINITION_SID\r\n" + "from\r\n"
						+ "	dbo.HIERARCHY_DEFINITION HD\r\n" + "join dbo.HELPER_TABLE HT on\r\n"
						+ "	HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY\r\n"
						+ "	where HT.DESCRIPTION like '%custom%'\r\n" + "	ORDER BY NEWID()");
		bean.setCustomerHierarchySid((int) query.list().get(0));
		bean.setCustomerHierarchyForecastLevel(1);
		bean.setProductHierarchyForecastLevel(1);
		bean.setName("12114" + "UddasEdvbas$5");
		return bean;
	}

	@Test
	public void testLoadCustomViewString() {
		GtnUIFrameworkWebserviceRequest request = getRequest();
		controller.loadCustomViewString(request);
	}

	// @Test
	public void testLoadCustomView() {
		GtnUIFrameworkWebserviceRequest request = getRequest();
		request.getGtnWsReportRequest().getReportBean().getCustomViewBean().getCustomViewDataBean()
				.setCustomViewName("LLRCM");
		GtnUIFrameworkWebserviceResponse response = controller.loadCustomView(request);
		System.out.println(response.getGtnWsReportResponse().getReportBean().getCustomViewBean().getCustomViewDataBean()
				.getCustomTreeData());
	}
}
