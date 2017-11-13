package com.stpl.gtn.gtn2o.ws.companymaster;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.companymaster.service.GtnWsCMasterService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkCmQueryGenerator-test.xml" })
public class GtnWsCMasterServiceTest {

	@Autowired
	private GtnWsCMasterService gtnWsCMasterService;

	@Test
	public void getCompantMasterSearchTest() {
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		Object[] columnArray = new Object[] { "companyMasterSid", "companyId", "companyNo", "companyName",
				"companyStatus", "companyType", "companyStartDate", "companyEndDate", "companyTradeClass",
				"tradeClassStartDate", "tradeClassEndDate", "companyGroup", "companyCategory", "organizationKey",
				"financialSystem", "parentCompanyNo", "parentStartDate", "parentEndDate", "priorParentCompanyNo",
				"priorParentStartDate", "regionCode", "udc1", "udc2", "udc3", "udc4", "udc5", "udc6", "address1",
				"address2", "zipCode", "city", "state", "country" };
		gtnWsSearchRequest.setSearchConfigLodaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		gtnWsSearchRequest.setSearchQueryName("SearchQuery");
		gtnWsSearchRequest.setSearchColumnNameList(Arrays.asList(columnArray));
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(Arrays.asList(
				new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("companyId", "*", "EQUALS") }));
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		try {
			gtnWsCMasterService.getCompantMasterSearch(gtnUIFrameworkWebserviceRequest);
		} catch (GtnFrameworkGeneralException e) {
			Assert.fail();
		}

	}
}
