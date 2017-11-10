package com.stpl.gtn.gtn2o.ws.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
public class GtnSearchServiceTest {

	public GtnSearchServiceTest() {
		// constructor
	}

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

	public void testGetSearchResult() {
		System.out.println("testGetSearchResult");

		GtnWsSearchServiceController searchService = (GtnWsSearchServiceController) ServiceContextUtil.instance()
				.getBean("GTNDefaultProviderService");
		List<Object> list = new ArrayList<>();
		String[] str = "companyMasterSid,companyId,companyNo,companyName,companyStatus,companyType,companyStartDate,companyEndDate,companyTradeClass,tradeClassStartDate,tradeClassEndDate,companyGroup,companyCategory,organizationKey,financialSystem,parentCompanyNo,parentStartDate,parentEndDate,priorParentCompanyNo,priorParentStartDate,regionCode,udc1,udc2,udc3,udc4,udc5,udc6,address1,address2,zipCode,city,state,country"
				.split(",");
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		System.out.println("testGetSearchResult---1");
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setSearchModuleName("companyMaster");
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setSearchQueryName("SearchQuery");
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setSearchColumnNameList(list);
		System.out.println("testGetSearchResult---2");
		GtnUIFrameworkWebserviceResponse result = searchService
				.getSearchResultForAllModule(gtnUIFrameworkWebserviceRequest);

		assertNotNull(result);
	}

}
