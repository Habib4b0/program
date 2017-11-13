
package com.stpl.gtn.gtn2o.ws.companymaster;

import java.util.Date;

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

import com.stpl.gtn.gtn2o.ws.module.companymaster.controller.GtnWsCMasterAddQualifier;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GTNRestController-servlet.xml" })

public class CompanyMasterAddQualifierWebserviceTest {

	public CompanyMasterAddQualifierWebserviceTest() {
		super();
	}

	@Autowired
	private GtnWsCMasterAddQualifier companyMasterAddQualifierWebservice;

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

	@Test
	public void testGetCompanyIdentifier() {
		System.out.println("getCompanyIdentifier");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkWebserviceResponse result = companyMasterAddQualifierWebservice.getCompanyQualifier(gtnWsRequest);
		System.out.println("result" + result.getGtnSerachResponse().getResultSet());

	}

	@Test
	public void testSaveCompanyQualifier() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		String randasom = RandomStringUtils.randomAlphanumeric(3);
		Object[] savedata = { "Test" + randasom, "Test" + randasom, new Date(), new Date(), "Yes",
				"Test" + randasom + " Notes" };
		gtnCMasterRequest.setSaveData(savedata);
		GtnUIFrameworkWebserviceResponse response = companyMasterAddQualifierWebservice
				.saveCmpnyQualifier(gtnWsRequest);
		Assert.assertEquals(response.getGtnWsGeneralResponse().isSucess(), true);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateCompanyQualifier() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		String randasom = RandomStringUtils.randomAlphanumeric(3);
		System.out.println("random---" + randasom);
		Date asd = new Date();
		asd.setDate(9);
		Object[] savedata = { "Test" + randasom, "Test" + randasom, "1", asd, "Yes", "Test" + randasom + " Notes", 77 };
		gtnCMasterRequest.setSaveData(savedata);
		GtnUIFrameworkWebserviceResponse response = companyMasterAddQualifierWebservice
				.saveCmpnyQualifier(gtnWsRequest);
		Assert.assertEquals(response.getGtnWsGeneralResponse().isSucess(), true);
	}

	@Test
	public void testDeleteCompanyQualifier() {
		GtnUIFrameworkWebserviceRequest gtnMasterRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		GtnUIFrameworkWebserviceResponse gtnresponse = new GtnUIFrameworkWebserviceResponse();
		String randasom = RandomStringUtils.randomAlphanumeric(3);
		System.out.println("random---" + randasom);
		Date asd = new Date();
		Object[] savedata = { "1", asd, 77 };
		gtnCMasterRequest.setSaveData(savedata);
		gtnMasterRequest.setGtnCMasterRequest(gtnCMasterRequest);
		GtnUIFrameworkWebserviceResponse response = companyMasterAddQualifierWebservice
				.deleteCmpnyQualifier(gtnMasterRequest, gtnresponse);
		Assert.assertEquals(response.getGtnWsGeneralResponse().isSucess(), true);
	}

}
