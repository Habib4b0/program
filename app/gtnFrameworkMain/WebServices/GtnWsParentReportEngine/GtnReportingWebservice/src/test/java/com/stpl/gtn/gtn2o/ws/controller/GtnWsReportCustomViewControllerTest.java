package com.stpl.gtn.gtn2o.ws.controller;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn2o.ws.constants.MongoStringConstants;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReport-SpringContext-Test.xml" })
public class GtnWsReportCustomViewControllerTest {
	@Autowired
	GtnWsReportCustomViewController controller;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	com.mongodb.MongoClient client;

	@Test
	public void testDisplay() {
		prepereData();
		GtnUIFrameworkWebserviceRequest request = getRequest();
		request.getGtnReportRequest().getDataSelectionBean().setName("12114" + "UddasEdvbas$5");
		getcustomerHierarchyRequest(request);
		controller.loadHierarchyLevels(request);
		deleteData();
	}

	private void deleteData() {
		client.getDatabase(MongoStringConstants.DATABSE_NAME).drop();
	}

	private GtnUIFrameworkWebserviceRequest getcustomerHierarchyRequest(GtnUIFrameworkWebserviceRequest request) {
		GtnWsReportCustomViewBean customViewBean = new GtnWsReportCustomViewBean();
		customViewBean.setHierarchyType(GtnWsHierarchyType.CUSTOMER);
		request.getGtnReportRequest().setCustomViewBean(customViewBean);
		return request;
	}

	private GtnUIFrameworkWebserviceRequest getRequest() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		request.setGtnReportRequest(reportRequest);
		GtnWsReportDataSelectionBean bean = new GtnWsReportDataSelectionBean();
		reportRequest.setDataSelectionBean(bean);

		return request;
	}

	private void prepereData() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoCollection<GtnWsReportDataSelectionBean> collection = client.getDatabase(MongoStringConstants.DATABSE_NAME)
				.withCodecRegistry(pojoCodecRegistry)
				.getCollection(MongoStringConstants.REPORT_COLLECTION, GtnWsReportDataSelectionBean.class);
		GtnWsReportDataSelectionBean bean = createDummyHierarchySelection();
		collection.insertOne(bean);
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

}
