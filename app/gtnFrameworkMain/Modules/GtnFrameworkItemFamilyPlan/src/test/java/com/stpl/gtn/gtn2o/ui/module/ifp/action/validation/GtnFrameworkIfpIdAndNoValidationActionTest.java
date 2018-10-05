package com.stpl.gtn.gtn2o.ui.module.ifp.action.validation;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpIdAndNoValidationActionTest {
	
	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance()
		throws Exception {
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		doReturn("ifpInformationTabIFPId").when(baseComponent).getStringFromField();
		doReturn(1).when(baseComponent).getIntegerFromField();
		doReturn(new Date()).when(baseComponent).getDateFromDateField();
		doReturn("caption").when(baseComponent).getCaption();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(1);
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("mode");
		
		GtnFrameworkIfpIdAndNoValidationAction gtnFrameworkIfpIdAndNoValidationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		Map<String, Object> editRecord = new HashMap<>();
		editRecord.put("ifpId", true);
		editRecord.put("ifpNo", true);
		
		response.setEditRecord(editRecord);
		doReturn(response).when(gtnFrameworkIfpIdAndNoValidationAction).getIfpAndNoValidationResponse(Mockito.any());
		
		callIfpAndNoValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpIdAndNoValidationAction);
	}
	
	@Test
	public void testDoAction_checkMode_isEdit()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		doReturn("ifpInformationTabIFPId").when(baseComponent).getStringFromField();
		doReturn(1).when(baseComponent).getIntegerFromField();
		doReturn(new Date()).when(baseComponent).getDateFromDateField();
		doReturn("caption").when(baseComponent).getCaption();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(1);
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("Edit");
		
		GtnFrameworkIfpIdAndNoValidationAction gtnFrameworkIfpIdAndNoValidationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		Map<String, Object> editRecord = new HashMap<>();
		editRecord.put("ifpId", true);
		editRecord.put("ifpNo", true);
		
		response.setEditRecord(editRecord);
		doReturn(response).when(gtnFrameworkIfpIdAndNoValidationAction).getIfpAndNoValidationResponse(Mockito.any());
		
		callIfpAndNoValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpIdAndNoValidationAction);
	}

	@Test
	public void testDoAction_isIfpId_false()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		doReturn("ifpInformationTabIFPId").when(baseComponent).getStringFromField();
		doReturn(1).when(baseComponent).getIntegerFromField();
		doReturn(new Date()).when(baseComponent).getDateFromDateField();
		doReturn("caption").when(baseComponent).getCaption();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(1);
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("mode");
		
		GtnFrameworkIfpIdAndNoValidationAction gtnFrameworkIfpIdAndNoValidationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		Map<String, Object> editRecord = new HashMap<>();
		editRecord.put("ifpId", false);
		editRecord.put("ifpNo", true);
		
		response.setEditRecord(editRecord);
		doReturn(response).when(gtnFrameworkIfpIdAndNoValidationAction).getIfpAndNoValidationResponse(Mockito.any());
		
		callIfpAndNoValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpIdAndNoValidationAction);
	}
	
	@Test
	public void testDoAction_isIfpNo_false()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		doReturn("ifpInformationTabIFPId").when(baseComponent).getStringFromField();
		doReturn(1).when(baseComponent).getIntegerFromField();
		doReturn(new Date()).when(baseComponent).getDateFromDateField();
		doReturn("caption").when(baseComponent).getCaption();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(1);
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("mode");
		
		GtnFrameworkIfpIdAndNoValidationAction gtnFrameworkIfpIdAndNoValidationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		Map<String, Object> editRecord = new HashMap<>();
		editRecord.put("ifpId", true);
		editRecord.put("ifpNo", false);
		
		response.setEditRecord(editRecord);
		doReturn(response).when(gtnFrameworkIfpIdAndNoValidationAction).getIfpAndNoValidationResponse(Mockito.any());
		
		callIfpAndNoValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpIdAndNoValidationAction);
	}

	@Test
	public void testDoAction_isIfpId_And_isIfpNo_false()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpIdAndNoValidationAction fixture = new GtnFrameworkIfpIdAndNoValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		doReturn("ifpInformationTabIFPId").when(baseComponent).getStringFromField();
		doReturn(1).when(baseComponent).getIntegerFromField();
		doReturn(new Date()).when(baseComponent).getDateFromDateField();
		doReturn("caption").when(baseComponent).getCaption();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(1);
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("mode");
		
		GtnFrameworkIfpIdAndNoValidationAction gtnFrameworkIfpIdAndNoValidationAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		
		Map<String, Object> editRecord = new HashMap<>();
		editRecord.put("ifpId", false);
		editRecord.put("ifpNo", false);
		
		response.setEditRecord(editRecord);
		doReturn(response).when(gtnFrameworkIfpIdAndNoValidationAction).getIfpAndNoValidationResponse(Mockito.any());
		
		callIfpAndNoValidationDoAction(componentId, gtnUIFrameWorkActionConfig, gtnFrameworkIfpIdAndNoValidationAction);
	}
	
	public void callIfpAndNoValidationDoAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig,
			GtnFrameworkIfpIdAndNoValidationAction gtnFrameworkIfpIdAndNoValidationAction)
			throws GtnFrameworkGeneralException {
		
		try{
			gtnFrameworkIfpIdAndNoValidationAction.doAction(componentId, gtnUIFrameWorkActionConfig);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}