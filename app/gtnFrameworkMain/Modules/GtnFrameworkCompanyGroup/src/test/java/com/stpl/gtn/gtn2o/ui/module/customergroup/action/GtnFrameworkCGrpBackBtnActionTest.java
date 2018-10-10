package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCGrpBackBtnActionTest {
	
	@Test
	public void testGtnFrameworkCGrpBackBtnAction_1()
		throws Exception {
		GtnFrameworkCGrpBackBtnAction result = new GtnFrameworkCGrpBackBtnAction();
		assertNotNull(result);
	}

	
	@Test
	public void testConfigureParams_1()
		throws Exception {
		GtnFrameworkCGrpBackBtnAction fixture = new GtnFrameworkCGrpBackBtnAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	
	@Test
	public void testCreateInstance_1()
		throws Exception {
		GtnFrameworkCGrpBackBtnAction fixture = new GtnFrameworkCGrpBackBtnAction();

		GtnUIFrameWorkAction result = fixture.createInstance();

		assertNotNull(result);
	}

	@Test
	public void testDoAction_1()
		throws GtnFrameworkGeneralException {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCGrpBackBtnAction fixture = new GtnFrameworkCGrpBackBtnAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> actionConfigList = new ArrayList<>();
		actionConfigList.add("a");  
		actionConfigList.add("b");
		actionConfigList.add("c");  
		actionConfigList.add("d");
		actionConfigList.add("e");  
		actionConfigList.add("f");
		
		gtnUIFrameWorkActionConfig.setActionParameterList(actionConfigList);
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		String getStringFromField = "test";
		doReturn(getStringFromField).when(baseComponent).getStringFromField();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	
}