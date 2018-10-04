package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.calendarconfiguration.GtnWsCalendarConfigurationResponse;

import de.steinwedel.messagebox.MessageBox;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkCurdResetActionTest {

	GtnFrameworkCurdResetAction ins = new GtnFrameworkCurdResetAction();

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	@Test
	public void testDoAction_1() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		List<Object> actionParametersList=Arrays.asList(0,1);
		
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);
		
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		List<Object> customDataList = new ArrayList<>();
		
		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		List<Object> properties=Arrays.asList("1","copy","3");
		gtnWsRecordBean.setProperties(properties);
		customDataList.add(gtnWsRecordBean);
		customDataList.add(new Object());
		doReturn(customDataList).when(gtnUIFrameworkComponentData).getCustomDataList();
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCurdResetAction in = Mockito.spy(ins);
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();;
		List<Date> holidays=new ArrayList<>();
		holidays.add(new Date());
		calendarConfigurationResponse.setHolidays(holidays);
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	
	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		List<Object> actionParametersList=Arrays.asList(0,1);
		
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);
		
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		List<Object> customDataList = new ArrayList<>();
		
		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		List<Object> properties=Arrays.asList("copy","copy","3");
		gtnWsRecordBean.setProperties(properties);
		customDataList.add(gtnWsRecordBean);
		doReturn(customDataList).when(gtnUIFrameworkComponentData).getCustomDataList();
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCurdResetAction in = Mockito.spy(ins);
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();;
		List<Date> holidays=new ArrayList<>();
		holidays.add(new Date());
		calendarConfigurationResponse.setHolidays(holidays);
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		
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
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		ins.getResponse(wsclient, request);
	}
}
