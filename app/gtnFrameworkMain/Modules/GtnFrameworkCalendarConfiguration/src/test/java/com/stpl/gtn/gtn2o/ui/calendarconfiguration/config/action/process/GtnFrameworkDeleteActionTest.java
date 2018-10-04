package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkDeleteActionTest {
	
	GtnFrameworkDeleteAction ins = new GtnFrameworkDeleteAction();
	
	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	@Test
	public void testConfigParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_1() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6,7,8);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnWsRecordBean tableBean=new GtnWsRecordBean();
		List<Object> properties=Arrays.asList("0",1,2,3,4,5,6,7,8);
		tableBean.setProperties(properties);
		doReturn(tableBean).when(base).getValueFromComponent();
		
		GtnFrameworkDeleteAction in=Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();
		calendarConfigurationResponse.setSuccess(true);
		calendarConfigurationResponse.setMessage("Message");
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6,7,8);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnWsRecordBean tableBean=new GtnWsRecordBean();
		List<Object> properties=Arrays.asList("0",1,2,3,4,5,6,7,8);
		tableBean.setProperties(properties);
		doReturn(tableBean).when(base).getValueFromComponent();
		
		GtnFrameworkDeleteAction in=Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();
		calendarConfigurationResponse.setSuccess(false);
		calendarConfigurationResponse.setMessage("Message");
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_Fail() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGetResponse() { 
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		ins.getResponse(wsclient, request);
	}
}
