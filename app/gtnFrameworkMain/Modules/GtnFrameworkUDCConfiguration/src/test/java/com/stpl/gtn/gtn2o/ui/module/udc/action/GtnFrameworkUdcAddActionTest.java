package com.stpl.gtn.gtn2o.ui.module.udc.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkUdcAddActionTest {


	@Test
	public void testDoAction_1() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("BRAND").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnFrameworkUdcAddAction in=Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse res=new GtnUIFrameworkWebserviceResponse();
		doReturn(res).when(in).getResponse1(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
		}
	
	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("FILE_TYPE").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnFrameworkUdcAddAction in=Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse res=new GtnUIFrameworkWebserviceResponse();
		doReturn(res).when(in).getResponse2(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_3() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("val").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnFrameworkUdcAddAction in=Mockito.spy(ins);
		GtnUIFrameworkWebserviceResponse res=new GtnUIFrameworkWebserviceResponse();
		doReturn(res).when(in).getResponse3(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		GtnWsGeneralResponse gtnWsGeneralResponse=new GtnWsGeneralResponse();
		res.setGtnWsGeneralResponse(gtnWsGeneralResponse);
		gtnWsGeneralResponse.setSucess(false);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGetResponse1() {
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		ins.getResponse1(request);
	}
	
	@Test
	public void testGetResponse2() {
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		ins.getResponse2(request);
	}
	
	@Test
	public void testGetResponse3() {
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		ins.getResponse3(request);
	}
	
	@Test
	public void testConfigureParams() throws Exception {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testCreateInstance() {
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		ins.createInstance();
	}
	
	@Ignore
	public void testDoActionFail() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcAddAction ins=new GtnFrameworkUdcAddAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("BRAND").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		GtnFrameworkUdcAddAction in=Mockito.spy(ins);
		doReturn(null).when(in).getResponse1(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
}
