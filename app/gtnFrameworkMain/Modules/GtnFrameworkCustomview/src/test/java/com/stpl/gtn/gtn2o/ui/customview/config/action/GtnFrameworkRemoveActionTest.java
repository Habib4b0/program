package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkRemoveActionTest {

	GtnFrameworkRemoveAction ins=new GtnFrameworkRemoveAction();
	
	@Test
	public void testDoAction_1() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(1, "");
		resetActionParams.set(2, "");
		resetActionParams.set(3,"");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn(null).when(base).getValueFromComponent();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(1, "");
		resetActionParams.set(2, "");
		resetActionParams.set(3,"value");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnWsRecordBean gtnWsRecordBean=new GtnWsRecordBean();
		doReturn(gtnWsRecordBean).when(base).getValueFromComponent();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		gtnWsRecordBean.setAdditionalProperties(Arrays.asList(1,2,3,4));
		gtnWsRecordBean.addAdditionalProperties(2, "val");
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_3() throws Exception {
		PowerMockito.mockStatic(MessageBox.class, GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(1, "");
		resetActionParams.set(2, "");
		resetActionParams.set(3,"value");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnWsRecordBean gtnWsRecordBean=new GtnWsRecordBean();
		doReturn(gtnWsRecordBean).when(base).getValueFromComponent();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		gtnWsRecordBean.setAdditionalProperties(Arrays.asList(1,2,3,4));
		gtnWsRecordBean.addAdditionalProperties(2, "value");
		gtnWsRecordBean.setProperties(Arrays.asList(1,2,3,4));
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoActionFail() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testConfigureParams() throws Exception {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}
}
