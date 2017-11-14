/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.rebateschedule.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.module.rebateschedule.controller.GtnWsRebateScheduleController;
import com.stpl.gtn.gtn2o.ws.rebateschedule.GtnWsRebateScheduleInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.rebateschedule.GtnWsRebateScheduleGeneralRequest;

@Ignore
/**
 *
 * @author Mahesh.James
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GTNRestController-servlet.xml" })

public class GtnWsRebateScheduleControllerTest {

	@Autowired
	private GtnWsRebateScheduleController controller;

	@Test
	public void testPriceScheduleSaveService() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		int sysId = 0;
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId("1130");
		generalWSRequest.setSessionId("11330");
		request.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsRebateScheduleInfoBean rebateScheduleInfoBean = new GtnWsRebateScheduleInfoBean();
		rebateScheduleInfoBean.setSystemId(sysId);
		List<NotesTabBean> noteBeanList = new ArrayList<>();

		rebateScheduleInfoBean.setNoteBeanList(noteBeanList);

		controller.rebateScheduleSaveService(request);
	}

	@Test
	public void testPriceScheduleItemAddition() {
		Map<String, String> inputValueMap = null;

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputValueMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		controller.rebateScheduleItemAddition(request);
	}

	@Test
	public void testRebateScheduleUpdateService() {
		String column = "";
		Object value = null;
		boolean checkAll = false;
		int systemId = 0;
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId("1130");
		generalWSRequest.setSessionId("11330");
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsCheckAllUpdateBean psUpdateBean = new GtnWsCheckAllUpdateBean();

		psUpdateBean.setPropertyId(column);
		psUpdateBean.setValue(value);
		psUpdateBean.setMasterSid(systemId);
		psUpdateBean.setCheckAll(checkAll);

		GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = new GtnWsCheckAllUpdateRequest();
		gtnWsPSUpdateRequest.setUpdateBean(psUpdateBean);
		updateRequest.setGtnWsCheckAllUpdateRequest(gtnWsPSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		controller.rebateScheduleUpdateService(updateRequest);
	}

	@Test
	public void testRebateSchedulePopulateService() {
		GtnWsCheckAllUpdateBean rsUpdateBean = new GtnWsCheckAllUpdateBean();
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId("1100");
		generalWSRequest.setSessionId("1100");
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = new GtnWsCheckAllUpdateRequest();
		gtnWsPSUpdateRequest.setUpdateBean(rsUpdateBean);
		updateRequest.setGtnWsCheckAllUpdateRequest(gtnWsPSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		controller.rebateSchedulePopulateService(updateRequest);

	}

	@Test
	public void testLoadRebateSchedule() {
		int systemId = 0;

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();

		GtnWsRebateScheduleInfoBean rSInfoBean = new GtnWsRebateScheduleInfoBean();
		rSInfoBean.setSystemId(systemId);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("1130");
		gtnWsGeneralRequest.setSessionId("1130");
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		GtnWsRebateScheduleGeneralRequest getGtnWsRebateScheduleGeneralRequest = new GtnWsRebateScheduleGeneralRequest();
		getGtnWsRebateScheduleGeneralRequest.setRebateScheduleInfoBean(rSInfoBean);
		request.setGtnWsRebateScheduleGeneralRequest(getGtnWsRebateScheduleGeneralRequest);
		controller.loadRebateSchedule(request);

	}

	@Test
	public void testValidationService() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMap = new HashMap<>();

		inputMap.put("processName", "tempCount");

		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setUserId("1130");
		generalWSRequest.setSessionId("2211");
		request.setGtnWsGeneralRequest(generalWSRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputMap);
		generalWSRequest.setComboBoxWhereclauseParamList(inputList);
		controller.validationService(request);

	}

	@Test
	public void testRemovePsRecord() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put("sysId", 0);
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);
		controller.removeRsRecord(request);

	}

}
