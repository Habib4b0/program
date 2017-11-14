/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox.GtnWsReturnsDualListBoxService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;

/**
 *
 * @author Nimisha.Rakesh
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationServiceContext-ReturnsForecasting.xml" })
@Ignore
public class GtnWsReturnsDualListBoxControllerTest {

	@Autowired
	GtnWsReturnsDualListBoxService gtnWsReturnsDualListBoxService;

	@Test
	public void testDataSelectionFile() throws IOException, GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();

		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		List<Object> inputList = new ArrayList<>();
		List<Object> selectedColumnList = new ArrayList<>();
		inputList.add(116);
		inputList.add(1);
		inputList.add(46124);
		inputList.add(139634);
		inputList.add("");
		inputList.add("levelNo");
		inputList.add("product selection");
		inputList.add("returns");
		inputList.add("returnsForecastMainScreenDataSelection");
		inputList.add(111111);
		generalRequest.setUserId("767497");
		selectedColumnList.add("relationshipLevelSid");
		selectedColumnList.add("levelValue");
		selectedColumnList.add("levelNo");
		selectedColumnList.add("hierarchyNo");
		selectedColumnList.add("levelName");
		selectedColumnList.add("form");
		selectedColumnList.add("strength");
		gtnWsSearchRequest.setSearchColumnNameList(selectedColumnList);
		gtnWsSearchRequest.setQueryInputList(inputList);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
		gtnWsReturnsDualListBoxService.generateFile(inputList, StringUtils.EMPTY);
	}
}
