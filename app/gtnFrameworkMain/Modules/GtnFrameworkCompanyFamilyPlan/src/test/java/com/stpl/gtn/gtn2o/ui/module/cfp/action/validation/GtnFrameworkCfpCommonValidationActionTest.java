package com.stpl.gtn.gtn2o.ui.module.cfp.action.validation;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean.GtnCFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.cfpresponse.GtnWsCfpReponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
		GtnUIFrameworkActionExecutor.class })

public class GtnFrameworkCfpCommonValidationActionTest {
	@Test
	public void testConfigureParams_1() throws Exception {

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(0).when(gtnCFamilyPlanValidationBean).getCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testDoAction_getCheckedCount_IsZero() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(0).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStartDateNullCount_greaterThan_0And1()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		doReturn(5).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStartDateNullCount_greaterThan_0Not1()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		doReturn(1).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStatusNullCount_greaterThan_0And1() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		// getStartDateNullCount_NotgreaterThan_0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		// getStatusNullCount > 0 and >1
		doReturn(5).when(gtnCFamilyPlanValidationBean).getStatusNullCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStatusNullCount_greaterThan_0Not1() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();

		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		// getStartDateNullCount_NotgreaterThan_0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		// getStatusNullCount > 0 but not >1
		doReturn(1).when(gtnCFamilyPlanValidationBean).getStatusNullCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStartDateGreaterThanEndCount_greaterThan_0()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		// getStartDateNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		// getStatusNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStatusNullCount();

		// getStartDateGreaterThanEndCount >0
		doReturn(5).when(gtnCFamilyPlanValidationBean).getStartDateGreaterThanEndCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStartDateEqualCount_greaterThan_0() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		// getStartDateNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		// getStatusNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStatusNullCount();

		// getStartDateGreaterThanEndCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateGreaterThanEndCount();

		// getStartDateGreaterThanEndCount >0
		doReturn(5).when(gtnCFamilyPlanValidationBean).getStartDateEqualsEndCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStartDateEqualCount_notGreaterThan_0()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		// getStartDateNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		// getStatusNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStatusNullCount();

		// getStartDateGreaterThanEndCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateGreaterThanEndCount();

		// getStartDateGreaterThanEndCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateEqualsEndCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	@Test
	public void testGtnFrameworkCfpCommonValidation_doAction_getStartDateGreaterThanEndCount_notGreaterThan_0()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCfpCommonValidationAction fixture = new GtnFrameworkCfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		Date date = new Date();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpInformationCFPEndDate")).thenReturn(baseComponent);
		doReturn(date).when(baseComponent).getDateFromDateField();

		GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnCFamilyPlanValidationBean gtnCFamilyPlanValidationBean = Mockito.mock(GtnCFamilyPlanValidationBean.class);
		doReturn(3).when(gtnCFamilyPlanValidationBean).getDuplicateCompanyCount();
		GtnWsCfpReponse gtnWsCfpReponse = new GtnWsCfpReponse();

		doReturn(response).when(gtnFrameworkCfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsCfpReponse.setGtnCFamilyPlanValidationBean(gtnCFamilyPlanValidationBean);
		response.setGtnWsCfpReponse(gtnWsCfpReponse);

		doReturn(5).when(gtnCFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnCFamilyPlanValidationBean).getCheckedCount();

		// getStartDateNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateNullCount();

		// getStatusNullCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStatusNullCount();

		// getStartDateGreaterThanEndCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateGreaterThanEndCount();

		// getStartDateGreaterThanEndCount not >0
		doReturn(0).when(gtnCFamilyPlanValidationBean).getStartDateEqualsEndCount();

		callCfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkCfpDeleteAction);
	}

	public void callCfpCommonValidationDoAction(String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnFrameworkCfpCommonValidationAction gtnFrameworkCfpDeleteAction) throws GtnFrameworkGeneralException {

		try {
			gtnFrameworkCfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
