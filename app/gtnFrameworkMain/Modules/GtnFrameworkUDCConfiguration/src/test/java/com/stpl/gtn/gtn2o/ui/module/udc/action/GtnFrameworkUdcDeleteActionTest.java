package com.stpl.gtn.gtn2o.ui.module.udc.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
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
import org.sonar.api.server.ws.WebService.NewAction;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class,
		GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkUdcDeleteActionTest {

	GtnFrameworkUdcDeleteAction ins = new GtnFrameworkUdcDeleteAction();

	@Test
	public void testConfigureParams() throws Exception {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	@Test
	public void testDoAction_1() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("BRAND").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("FILE_TYPE").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_3() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("val").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_4() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("BRAND").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnWsRecordBean gtnWsRecordBean=new GtnWsRecordBean();
		gtnWsRecordBean.setPropertyValueByIndex(0, 1);
		gtnWsRecordBean.setPropertyValueByIndex(1, 2);
		gtnWsRecordBean.setPropertyValueByIndex(2, 3);
		gtnWsRecordBean.setPropertyValueByIndex(3, 4);
		gtnWsRecordBean.setPropertyValueByIndex(4, 5);
		gtnWsRecordBean.setPropertyValueByIndex(5, 6);

		List<Object> properties=new ArrayList<>();
		properties.add(0, 0);
		properties.add(1, 1);
		properties.add(2, 2);
		properties.add(3, 3);
		gtnWsRecordBean.setProperties(properties);
		doReturn(gtnWsRecordBean).when(base).getValueFromPagedDataTable();
		
		GtnFrameworkUdcDeleteAction in=Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse res=new GtnUIFrameworkWebserviceResponse();
		doReturn(res).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_5() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("BRAND").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);

		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.setPropertyValueByIndex(0, 1);
		gtnWsRecordBean.setPropertyValueByIndex(1, 2);
		gtnWsRecordBean.setPropertyValueByIndex(2, 3);
		gtnWsRecordBean.setPropertyValueByIndex(3, 4);
		gtnWsRecordBean.setPropertyValueByIndex(4, 5);
		gtnWsRecordBean.setPropertyValueByIndex(5, 6);

		List<Object> properties = new ArrayList<>();
		properties.add(0, 0);
		properties.add(1, 1);
		properties.add(2, 2);
		properties.add(3, 3);
		gtnWsRecordBean.setProperties(properties);
		doReturn(gtnWsRecordBean).when(base).getValueFromPagedDataTable();

		GtnFrameworkUdcDeleteAction in = Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse res = new GtnUIFrameworkWebserviceResponse();
		doReturn(res).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		GtnWsGeneralResponse gtnWsGeneralResponse=new GtnWsGeneralResponse();
		res.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		gtnWsGeneralResponse.setSucess(false);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_6() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("FILE_TYPE").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);

		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.setPropertyValueByIndex(0, 1);
		gtnWsRecordBean.setPropertyValueByIndex(1, 2);
		gtnWsRecordBean.setPropertyValueByIndex(2, 3);
		gtnWsRecordBean.setPropertyValueByIndex(3, 4);
		gtnWsRecordBean.setPropertyValueByIndex(4, 5);
		gtnWsRecordBean.setPropertyValueByIndex(5, 6);

		List<Object> properties = new ArrayList<>();
		properties.add(0, 0);
		properties.add(1, 1);
		properties.add(2, 2);
		properties.add(3, 3);
		gtnWsRecordBean.setProperties(properties);
		doReturn(gtnWsRecordBean).when(base).getValueFromPagedDataTable();

		GtnFrameworkUdcDeleteAction in = Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse res = new GtnUIFrameworkWebserviceResponse();
		doReturn(res).when(in).getResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		GtnWsGeneralResponse gtnWsGeneralResponse=new GtnWsGeneralResponse();
		res.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		gtnWsGeneralResponse.setSucess(false);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGetResponse() {
		GtnUIFrameworkWebserviceRequest req=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		ins.getResponse(req);
	}
}