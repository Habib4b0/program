package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.doReturn;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;

import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCVDeleteActionTest {


	@Test
	public void testConfigureParams() throws Exception {
		GtnFrameworkCVDeleteAction ins=new GtnFrameworkCVDeleteAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}


	@Test
	public void testCreateInstance() {
		GtnFrameworkCVDeleteAction ins=new GtnFrameworkCVDeleteAction();
		ins.createInstance();
	}


	@Test
	public void testDoAction_1() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "val");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnFrameworkCVDeleteAction ins=new GtnFrameworkCVDeleteAction();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnWsRecordBean customViewBean=new GtnWsRecordBean();
		customViewBean.setRecordHeader(Arrays.asList("customViewMasterSId",GtnFrameworkCommonConstants.TREE_VIEW_NAME
				,GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION,GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));
		customViewBean.setProperties(Arrays.asList(1,"treeViewName","customViewDec","Type"));
		doReturn(customViewBean).when(object).getValueFromComponent();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCVDeleteAction in=Mockito.spy(ins);
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
	
		GtnWsCustomViewResponse gtnWsCustomViewResponse=new GtnWsCustomViewResponse();
		response.setGtnWsCustomViewResponse(gtnWsCustomViewResponse);
		
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "val");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnFrameworkCVDeleteAction ins=new GtnFrameworkCVDeleteAction();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnWsRecordBean customViewBean=new GtnWsRecordBean();
		customViewBean.setRecordHeader(Arrays.asList("customViewMasterSId",GtnFrameworkCommonConstants.TREE_VIEW_NAME
				,GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION,GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));
		customViewBean.setProperties(Arrays.asList(1,"treeViewName","customViewDec","Type"));
		doReturn(customViewBean).when(object).getValueFromComponent();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);
		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkCVDeleteAction in=Mockito.spy(ins);
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		doReturn(response).when(in).getResponse(Mockito.any(), Mockito.any());
	
		GtnWsCustomViewResponse gtnWsCustomViewResponse=new GtnWsCustomViewResponse();
		response.setGtnWsCustomViewResponse(gtnWsCustomViewResponse);
		gtnWsCustomViewResponse.setSuccess(true);
		List<String> fieldValues=new ArrayList<>();
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		in.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGetRespose() {
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnFrameworkCVDeleteAction ins=new GtnFrameworkCVDeleteAction();
		ins.getResponse(wsclient, request);
	}
}

