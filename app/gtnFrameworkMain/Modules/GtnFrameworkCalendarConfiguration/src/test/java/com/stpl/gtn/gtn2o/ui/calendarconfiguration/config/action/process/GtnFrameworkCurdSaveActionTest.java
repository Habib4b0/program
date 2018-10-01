package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;import javax.management.MalformedObjectNameException;

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
import com.vaadin.v7.ui.Calendar;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })

public class GtnFrameworkCurdSaveActionTest {

	GtnFrameworkCurdSaveAction ins = new GtnFrameworkCurdSaveAction();

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
		
		gtnUIFrameWorkActionConfig.setFieldValues(Arrays.asList("1","2","3","4","5","6","7"));
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn("").when(base).getStringFromField();
		List<Date> date=new ArrayList<>();
		doReturn(date).when(base).getV8CalenderValue();
		doReturn("").when(base).getCaptionFromComboBox();
		
		GtnUIFrameworkComponentData componentData=Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(componentData).when(base).getComponentData();
		List<GtnWsRecordBean> customlist=new ArrayList<>();
		GtnWsRecordBean bean=new GtnWsRecordBean();
		bean.setProperties(Arrays.asList(1,2,3,4,5,6,7,8));
		customlist.add(bean);
		doReturn(customlist).when(componentData).getCustomDataList();
		
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCurdSaveAction in = Mockito.spy(GtnFrameworkCurdSaveAction.class);
		GtnWsCalendarConfigurationResponse calendarConfigurationResponse=new GtnWsCalendarConfigurationResponse();
		calendarConfigurationResponse.setSuccess(true);
		GtnWsRecordBean calendarBean=new GtnWsRecordBean();
		calendarConfigurationResponse.setCalendarBean(calendarBean);
		response.setCalendarConfigurationResponse(calendarConfigurationResponse);
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGetResponse() {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);

		ins.getResponse(wsclient, request);
	}
}
