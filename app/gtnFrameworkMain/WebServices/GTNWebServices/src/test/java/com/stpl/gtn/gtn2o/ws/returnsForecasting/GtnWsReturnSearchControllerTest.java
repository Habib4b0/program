package com.stpl.gtn.gtn2o.ws.returnsForecasting;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller.GtnWsReturnsSearchController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GTNRestController-servlet.xml" })

public class GtnWsReturnSearchControllerTest {
	@Autowired
	private GtnWsReturnsSearchController returnsSearchController;

	@Test
	public void testGetDataSelectionSearch() {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setCount(false);
		searchRequest.setGtnWebServiceOrderByCriteriaList((Arrays.asList(
				new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("projectionName", "ASC") })));
		searchRequest.setGtnWebServiceSearchCriteriaList((Arrays
				.asList(new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("projectionName", "*", ""),
						new GtnWebServiceSearchCriteria("projectionDescription", "*", ""),
						new GtnWebServiceSearchCriteria("relationShipCombobox", "1234", ""),
						new GtnWebServiceSearchCriteria("productHierarchy", "12", ""),
						new GtnWebServiceSearchCriteria("company", "12", ""),
						new GtnWebServiceSearchCriteria("fromPeriod", "Q2 2015", "") })));

		searchRequest.setTableRecordStart(0);
		searchRequest.setTableRecordOffset(9);
		request.setGtnWsSearchRequest(searchRequest);
		GtnUIFrameworkWebserviceResponse response = returnsSearchController.getDataSelectionSearch(request);
		System.out.println("Response=====================" + response.getGtnWsGeneralResponse().isSucess());
		int size = response.getGtnSerachResponse().getResultSet().getDataTable().size();
		System.out.println("Sizeeeeeeeeeee" + size);

	}

}
