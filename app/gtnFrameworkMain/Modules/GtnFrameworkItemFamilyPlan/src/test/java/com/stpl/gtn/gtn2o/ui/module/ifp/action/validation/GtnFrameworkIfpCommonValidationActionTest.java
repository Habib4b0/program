package com.stpl.gtn.gtn2o.ui.module.ifp.action.validation;

import static org.junit.Assert.assertNotNull;
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
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
		GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkIfpCommonValidationActionTest {
	
	@Test
	public void testConfigureParams_1()
		throws Exception {
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(0).when(gtnIFamilyPlanValidationBean).getCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testDoAction_getCheckedCount_IsZero()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(0).when(gtnIFamilyPlanValidationBean).getCheckedCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStartDateNullCount_greaterThan_0And1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStartDateNullCount_greaterThan_0Not1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		doReturn(1).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStatusNullCount_greaterThan_0And1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		// getStartDateNullCount_NotgreaterThan_0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();
		
		// getStatusNullCount > 0 and >1
		doReturn(5).when(gtnIFamilyPlanValidationBean).getStatusNullCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStatusNullCount_greaterThan_0Not1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		// getStartDateNullCount_NotgreaterThan_0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();
		
		// getStatusNullCount > 0 but not >1
		doReturn(1).when(gtnIFamilyPlanValidationBean).getStatusNullCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStartDateGreaterThanEndCount_greaterThan_0()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		// getStartDateNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();
		
		// getStatusNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStatusNullCount();
		
		// getStartDateGreaterThanEndCount  >0 
		doReturn(5).when(gtnIFamilyPlanValidationBean).getStartDateGreaterThanEndCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStartDateEqualCount_greaterThan_0()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		// getStartDateNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();
		
		// getStatusNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStatusNullCount();
		
		// getStartDateGreaterThanEndCount not >0 
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateGreaterThanEndCount();
		
		// getStartDateGreaterThanEndCount >0 
		doReturn(5).when(gtnIFamilyPlanValidationBean).getStartDateEqualCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStartDateEqualCount_notGreaterThan_0()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		// getStartDateNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();
		
		// getStatusNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStatusNullCount();
		
		// getStartDateGreaterThanEndCount not >0 
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateGreaterThanEndCount();
		
		// getStartDateGreaterThanEndCount not >0 
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateEqualCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}
	
	@Test
	public void testGtnFrameworkIfpCommonValidation_doAction_getStartDateGreaterThanEndCount_notGreaterThan_0()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, 
				GtnUIFrameworkActionExecutor.class );
		
		GtnFrameworkIfpCommonValidationAction fixture = new GtnFrameworkIfpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(new Object());
		 
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		Date date = new Date();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPStartDate")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpInformationIFPEndDate")).thenReturn(baseComponent);
		doReturn(date).when(baseComponent).getDateFromDateField();
		
		GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(response).when(gtnFrameworkIfpDeleteAction).getCommonValidationResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		response.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		
		// getStartDateNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateNullCount();
		
		// getStatusNullCount not >0
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStatusNullCount();
		
		// getStartDateGreaterThanEndCount not >0 
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateGreaterThanEndCount();
		
		// getStartDateGreaterThanEndCount not >0 
		doReturn(0).when(gtnIFamilyPlanValidationBean).getStartDateEqualCount();

		callIfpCommonValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpDeleteAction);
	}

	public void callIfpCommonValidationDoAction(String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnFrameworkIfpCommonValidationAction gtnFrameworkIfpDeleteAction) throws GtnFrameworkGeneralException {
		
		try{
			gtnFrameworkIfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}