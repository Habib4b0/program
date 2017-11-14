
package com.stpl.gtn.gtn2o.ws.companymaster;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.module.companymaster.controller.GtnWsCMasterFinancialClose;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GTNRestController-servlet.xml" })
@Ignore
public class CompanyMasterFinancialCloseTest {

	public CompanyMasterFinancialCloseTest() {
		super();
	}

	@Autowired
	private GtnWsCMasterFinancialClose companyMasterFinancialClose;

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
	}

	@Test
	public void manuaLCloseOrOpen() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		Date asd = new Date();
		Object[] savedata = { 172493, 2013, 2016, 2024, asd, 1, asd, 2016, 5 };
		gtnCMasterRequest.setSaveData(savedata);
		GtnUIFrameworkWebserviceResponse response = companyMasterFinancialClose.setManualCloseOrOpen(gtnWsRequest);
		Assert.assertEquals(response.getGtnWsGeneralResponse().isSucess(), true);
	}

	@Test
	public void setAutomaticCloseTest() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		String randomNum = RandomStringUtils.randomNumeric(1);
		Object[] savedata = { 46124, randomNum, "0", "2018", 21 };
		gtnCMasterRequest.setSaveData(savedata);
		GtnUIFrameworkWebserviceResponse response = companyMasterFinancialClose.setAutomaticClose(gtnWsRequest);
		Assert.assertEquals(response.getGtnWsGeneralResponse().isSucess(), true);
	}

	@Test
	public void getFCStatusTest() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		Object[] savedata = { 2016, 3, 140706 };
		gtnCMasterRequest.setSaveData(savedata);
		GtnUIFrameworkWebserviceResponse response = companyMasterFinancialClose.getStatusFC(gtnWsRequest);
		System.out.println("Response is ----" + response.getOutBountData()[0]);
		Assert.assertEquals(response.getGtnWsGeneralResponse().isSucess(), true);
	}

	@Test
	public void getFCHistoryTest() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
		gtnWsRequest.getGtnWsSearchRequest().setCount(true);
		gtnWsRequest.getGtnWsSearchRequest().setSearchQueryName("FinancialCloseTableLoadData");
		GtnUIFrameworkWebserviceResponse response = companyMasterFinancialClose.getFCHistory(gtnWsRequest);
		System.out.println(response.getGtnSerachResponse().getCount());
		gtnWsRequest.getGtnWsSearchRequest().setCount(false);

		List<Object> recordHeader = new ArrayList<>();
		recordHeader.add("createdBy");
		recordHeader.add("month");

		gtnWsRequest.getGtnWsSearchRequest().setSearchColumnNameList(recordHeader);

		response = companyMasterFinancialClose.getFCHistory(gtnWsRequest);
		System.out.println("---" + response.getGtnSerachResponse().getResultSet().getDataTable().size());

	}
}
