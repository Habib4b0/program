package com.stpl.gtn.gtn2o.queryengine.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineConfig;
import com.stpl.gtn.gtn2o.queryengine.config.GtnFrameworkQueryEngineMainConfig;
import com.stpl.gtn.gtn2o.queryengine.engine.util.ServiceContextUtil;

@Ignore
public class GtnFrameworkQueryEngineTest {

	@Autowired
	private GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain;

	public GtnFrameworkQueryEngineTest() {
	super();
	}

	public GtnFrameworkQueryEngineTest(GtnFrameworkQueryEngineMain gtnFrameworkQueryEngineMain) {
		super();
		this.gtnFrameworkQueryEngineMain = gtnFrameworkQueryEngineMain;
	}

	@Test
	public void dummyTest() {
		System.out.println("DummyTest");
	}

	@Ignore
	public void testexecuteQueryConfig() {
		Date currentDate = new Date();
		Object[] arr = { "Test10120178", "Test10120178", "Test10120178", 295, 184, null, null, currentDate, null, null,
				null, null, "address_1", "address_2", "City", 352, "12345", null, null, null, null, "A", 0, "20150731",
				"Cars", 1, currentDate, 1, currentDate, null, "COMPANY_MASTER", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				null, 20, "TP Identifier-1000", 184, "Tue Dec 20 13:04:12 IST 2016", null, null, "A", 0, 1594, "CARS",
				1, "Tue Dec 20 13:04:12 IST 2016", 1, "Tue Dec 20 13:04:12 IST 2016", null,
				"Tue Dec 20 13:04:12 IST 2016", "Tue Dec 20 13:04:12 IST 2016", 400, null, null,
				"Tue Dec 20 13:04:12 IST 2016", "A", 0, 1598, "CARS", 1, "Tue Dec 20 13:04:12 IST 2016", 1,
				"Tue Dec 20 13:04:12 IST 2016", null, 190908, "Tue Dec 20 13:04:12 IST 2016", null, null, null,
				"Tue Dec 20 13:04:12 IST 2016,A,0,1596", "CARS", 1, "Tue Dec 20 13:04:12 IST 2016", 1,
				"Tue Dec 20 13:04:12 IST 2016", null, "COMPANY_MASTER", "../../../../var/Attachments/Docs/Test.txt",
				"Tue Dec 20 13:04:12 IST 2016", 13330, null };
		GtnFrameworkQueryEngineMainConfig mainConfig = new GtnFrameworkQueryEngineMainConfig();
		GtnFrameworkQueryEngineConfig companyMasterConfig = new GtnFrameworkQueryEngineConfig();

		mainConfig.setQueryMemoryArray(arr);
		testCompanyMasterQueryConfig(companyMasterConfig);
		List<GtnFrameworkQueryEngineConfig> childQueryConfigList = new ArrayList<>();
		companyMasterConfig.setChildQueryEngineConfigList(childQueryConfigList);

		GtnFrameworkQueryEngineConfig udcConfig = new GtnFrameworkQueryEngineConfig();
		testSaveCMUdcQueryConfig(udcConfig);
		childQueryConfigList.add(udcConfig);
		mainConfig.setRootEngineConfig(companyMasterConfig);
		GtnFrameworkQueryEngineMain instance = (GtnFrameworkQueryEngineMain) ServiceContextUtil.instance()
				.getBean("gtnFrameworkQueryEngineMain");
		SessionFactory sessionFactory = (SessionFactory) ServiceContextUtil.instance().getBean("sessionFactory");

		Session current = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = current.beginTransaction();
			instance.executeQuery(current, mainConfig);
			tx.commit();
		} catch (Exception ex) {

			ex.printStackTrace();

			if (tx != null) {
				tx.rollback();
			}
		} finally {
			System.out.println("Closing session " + current);
			current.close();
		}

	}

	public void testCompanyMasterQueryConfig(GtnFrameworkQueryEngineConfig companyMasterConfig) {
		List<GtnFrameworkQueryConfig> cmQueryConfigList = new ArrayList<>();

		GtnFrameworkQueryConfig cmInsertQueryConfig = new GtnFrameworkQueryConfig();
		String cmInsertQuery = "INSERT INTO COMPANY_MASTER (COMPANY_ID, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE, COMPANY_STATUS, COMPANY_CATEGORY, COMPANY_GROUP, "
				+ "COMPANY_START_DATE, COMPANY_END_DATE, ORGANIZATION_KEY, LIVES, FINANCIAL_SYSTEM, ADDRESS1, ADDRESS2, CITY, \"STATE\", ZIP_CODE, COUNTRY, REGION_CODE, LAST_UPDATED_DATE, INTERNAL_NOTES, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID, \"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		cmInsertQueryConfig.setQuery(cmInsertQuery);
		cmInsertQueryConfig.setInsertOrSelectQuery(true);
		cmInsertQueryConfig.setDataTypeArray(new String[] { "String", "String", "String", "Integer", "Integer",
				"Integer", "Integer", "Date", "Date", "Integer", "Integer", "String", "String", "String", "String",
				"Integer", "String", "Integer", "String", "String", "String", "String", "Integer", "String", "String",
				"Integer", "Date", "Integer", "Date" });

		cmInsertQueryConfig.setParamPositionArray(0, cmInsertQueryConfig.getDataTypeArray().length);
		cmInsertQueryConfig.setResultStoragePositionArray(new int[] { 29 });

		cmQueryConfigList.add(cmInsertQueryConfig);

		companyMasterConfig.setQueryConfigList(cmQueryConfigList);

	}

	public void testSaveCMUdcQueryConfig(GtnFrameworkQueryEngineConfig udcConfig) {
		List<GtnFrameworkQueryConfig> udcqueryList = new ArrayList<>();
		udcConfig.setQueryConfigList(udcqueryList);

		GtnFrameworkQueryConfig udcinsertQueryConfig = new GtnFrameworkQueryConfig();
		String cmUdcsaveQuery = "INSERT INTO UDCS (MASTER_SID, MASTER_TYPE, UDC1, UDC2, UDC3, UDC4, UDC5, UDC6, UDC7, UDC8, UDC9, UDC10, UDC11, UDC12)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		udcinsertQueryConfig.setQuery(cmUdcsaveQuery);
		udcinsertQueryConfig.setInsertOrSelectQuery(true);
		udcinsertQueryConfig.setDataTypeArray(new String[] { "Integer", "String", "Integer", "Integer", "Integer",
				"Integer", "Integer", "Integer", "Integer", "Integer", "Integer", "Integer", "Integer", "Integer" });

		udcinsertQueryConfig.setParamPositionArray(29, 29 + udcinsertQueryConfig.getDataTypeArray().length);

		udcqueryList.add(udcinsertQueryConfig);
	}

}
