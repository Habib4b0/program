package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.calendarconfiguration.GtnFrameworkCalendarConfigurationTest;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
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
public class GtnFrameworkCalendarCurdCommonValidationActionTest {

	GtnFrameworkCalendarCurdCommonValidationAction ins = new GtnFrameworkCalendarCurdCommonValidationAction();
	private transient GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCalendarCurdCommonValidationActionTest.class); 
	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig= new GtnUIFrameWorkActionConfig();
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
		List<String> fieldValues = Arrays.asList("1","2","3","4","5","6");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn("").when(base).getStringFromField();
		
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = new GtnUIFrameworkComponentData();
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCalendarCurdCommonValidationAction in =Mockito.spy(GtnFrameworkCalendarCurdCommonValidationAction.class);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<String> fieldValues = Arrays.asList("1","2","3","4","5","6");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn("").when(base).getStringFromField();
		
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		
		List<Object> list = new ArrayList<>();
		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8));
		gtnWsRecordBean.setPropertyValueByIndex(6, 1);
		gtnWsRecordBean.setPropertyValueByIndex(7, 2);
		list.add(gtnWsRecordBean);
		
		doReturn(list).when(gtnUIFrameworkComponentData).getCustomDataList();
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCalendarCurdCommonValidationAction in =Mockito.spy(GtnFrameworkCalendarCurdCommonValidationAction.class);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		calendarConfigurationResponse.setCalendarNameExists(true);
		
		try {
			in.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			gtnLogger.info("Error"+e);
		}
	}
	
	
	@Test
	public void testGetResponse() {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkWebServiceClient webServiceClient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest wsRequest = new GtnUIFrameworkWebserviceRequest();
		ins.getResponse(webServiceClient, wsRequest);
	}
}
