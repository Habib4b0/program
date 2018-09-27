package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
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
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.TextField;

import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCustomViewEditActionTest {
	
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkCustomViewEditAction.class);
	
	@Test
	public void testDoAction_1() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		GtnFrameworkCustomViewEditAction ins = Mockito.spy(new GtnFrameworkCustomViewEditAction());
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "val");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		GtnWsRecordBean bean = new GtnWsRecordBean();
		bean.setRecordHeader(Arrays.asList("customViewMasterSId", GtnFrameworkCommonConstants.TREE_VIEW_NAME,
				GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION, GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE,
				GtnFrameworkCommonConstants.CUTOMER_RELATION_SID, GtnFrameworkCommonConstants.PRODUCT_RELATION_SID));
		bean.setProperties(Arrays.asList(1, 2, "3", 4, "5", "6"));
		doReturn(bean).when(base).getValueFromComponent();

		GtnUIFrameWorkActionConfig navigationActionConfig = Mockito.mock(GtnUIFrameWorkActionConfig.class);
		navigationActionConfig.addActionParameter("V002");

		doNothing().when(ins).executeNavigateAction(Mockito.anyString());

		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		doReturn(response).when(ins).getResponse(Mockito.any(), Mockito.any());
		GtnWsCustomViewResponse gtnWsCustomViewResponse=new GtnWsCustomViewResponse();
		response.setGtnWsCustomViewResponse(gtnWsCustomViewResponse);
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData=Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		doReturn(new TextField()).when(gtnUIFrameworkComponentData).getCustomData();
		
		List<GtnWsRecordBean> cvTreeNodeList = new ArrayList<>();
		cvTreeNodeList.add(0, new GtnWsRecordBean());
		cvTreeNodeList.add(1, new GtnWsRecordBean());
		cvTreeNodeList.add(2, new GtnWsRecordBean());
		
		gtnWsCustomViewResponse.setCvTreeNodeList(cvTreeNodeList);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
		
	}
	
	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		GtnFrameworkCustomViewEditAction ins = Mockito.spy(new GtnFrameworkCustomViewEditAction());
		resetActionParams.set(2, "VIEW");
		resetActionParams.set(1, "val");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		GtnWsRecordBean bean = new GtnWsRecordBean();
		bean.setRecordHeader(Arrays.asList("customViewMasterSId", GtnFrameworkCommonConstants.TREE_VIEW_NAME,
				GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION, GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE,
				GtnFrameworkCommonConstants.CUTOMER_RELATION_SID, GtnFrameworkCommonConstants.PRODUCT_RELATION_SID));
		bean.setProperties(Arrays.asList(1, 2, "3", 4, "5", "6"));
		doReturn(bean).when(base).getValueFromComponent();

		GtnUIFrameWorkActionConfig navigationActionConfig = Mockito.mock(GtnUIFrameWorkActionConfig.class);
		navigationActionConfig.addActionParameter("V002");

		doNothing().when(ins).executeNavigateAction(Mockito.anyString());

		GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
		doReturn(response).when(ins).getResponse(Mockito.any(), Mockito.any());
		GtnWsCustomViewResponse gtnWsCustomViewResponse=new GtnWsCustomViewResponse();
		response.setGtnWsCustomViewResponse(gtnWsCustomViewResponse);
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData=Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		doReturn(new TextField()).when(gtnUIFrameworkComponentData).getCustomData();
		
		List<GtnWsRecordBean> cvTreeNodeList = new ArrayList<>();
		cvTreeNodeList.add(0, new GtnWsRecordBean());
		cvTreeNodeList.add(1, new GtnWsRecordBean());
		cvTreeNodeList.add(2, new GtnWsRecordBean());
		
		gtnWsCustomViewResponse.setCvTreeNodeList(cvTreeNodeList);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}
	
	@Test
	public void testDoAction_3() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		GtnFrameworkCustomViewEditAction ins = Mockito.spy(new GtnFrameworkCustomViewEditAction());
		resetActionParams.set(2, "VIEW");
		resetActionParams.set(1, "val");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGetResponse() {
		GtnFrameworkCustomViewEditAction ins = new GtnFrameworkCustomViewEditAction();
		GtnUIFrameworkWebServiceClient wsclient=new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest generalRequest=new GtnUIFrameworkWebserviceRequest();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		ins.getResponse(wsclient, generalRequest);
	}
	
	@Test
	public void testExecuteNavigateAction() throws Exception {
		String componentId="";
		GtnFrameworkCustomViewEditAction ins = new GtnFrameworkCustomViewEditAction();
		try {
			ins.executeNavigateAction(componentId);
		} catch (Exception e) {
			gtnLogger.info("Error"+e);
		}
	}
	
	@Test
	public void testCreateInstance() {
		GtnFrameworkCustomViewEditAction ins = new GtnFrameworkCustomViewEditAction();
		ins.createInstance();
	}
	
	@Test
	public void testConfigureParams() throws Exception {
		GtnFrameworkCustomViewEditAction ins = new GtnFrameworkCustomViewEditAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoActionFail() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkCustomViewEditAction ins = new GtnFrameworkCustomViewEditAction();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
}
