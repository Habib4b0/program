package com.stpl.gtn.gtn2o.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

@Ignore
public class ClientTest {

	public static void main(String[] args) {
		String randasom = RandomStringUtils.randomAlphabetic(4);
		System.out.println("Random---" + randasom);
	}

	@Test
	public void getSearchResultForAllModuleTest() {
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchServiceController gtnSearchServiceController = (GtnWsSearchServiceController) ServiceContextUtil
				.instance().getBean("gtnSearchServiceController");
		List<Object> columnList = new ArrayList<>();
		columnList.add("companyMasterSid");
		columnList.add("companyId");
		columnList.add("companyNo");
		columnList.add("companyName");
		columnList.add("companyType");
		columnList.add("companyStatus");
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setSearchModuleName("companyMaster");
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setSearchQueryName("companyParentlookupQuery");
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setCount(false);
		gtnUIFrameworkWebserviceRequest.getGtnWsSearchRequest().setSearchColumnNameList(columnList);
		gtnSearchServiceController.getSearchResultForAllModule(gtnUIFrameworkWebserviceRequest);
	}
}
