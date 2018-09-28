package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;

import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkConfirmSaveActionTest {

	GtnFrameworkConfirmSaveAction ins = new GtnFrameworkConfirmSaveAction();

	@Test
	public void testConfigureParams() throws Exception {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_1() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(4, cvRequest);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);

		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("Edit");
		when(GtnUIFrameworkGlobalUI.getSessionProperty("customSid")).thenReturn(1);

		GtnUIFrameworkWebserviceResponse res = new GtnUIFrameworkWebserviceResponse();
		GtnWsCustomViewResponse gtnWsCustomViewResponse = new GtnWsCustomViewResponse();
		res.setGtnWsCustomViewResponse(gtnWsCustomViewResponse);
		GtnFrameworkConfirmSaveAction in = Mockito.spy(ins);
		doReturn(res).when(in).getResponse(Mockito.any(), Mockito.any());
		gtnWsCustomViewResponse.setSuccess(true);

		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(4, cvRequest);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkWebserviceResponse res = new GtnUIFrameworkWebserviceResponse();
		GtnWsCustomViewResponse gtnWsCustomViewResponse = new GtnWsCustomViewResponse();
		res.setGtnWsCustomViewResponse(gtnWsCustomViewResponse);
		GtnFrameworkConfirmSaveAction in = Mockito.spy(ins);
		doReturn(res).when(in).getResponse(Mockito.any(), Mockito.any());
		gtnWsCustomViewResponse.setSuccess(false);

		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	@Test
	public void testGetResponse() { 
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		ins.getResponse(wsclient, request);
	}
}
