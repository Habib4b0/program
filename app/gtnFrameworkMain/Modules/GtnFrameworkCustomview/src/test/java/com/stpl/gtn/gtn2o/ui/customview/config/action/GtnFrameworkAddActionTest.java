package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkAddActionTest {


	@Test
	public void testConfigureParams() throws Exception {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		GtnFrameworkAddAction ins = new GtnFrameworkAddAction();

		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_1() throws Exception {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        GtnFrameworkAddAction ins = new GtnFrameworkAddAction();
        ins.doAction(componentId, gtnUIFrameWorkActionConfig);      
	}

	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        GtnFrameworkAddAction ins = new GtnFrameworkAddAction();
        ins.doAction(componentId, gtnUIFrameWorkActionConfig);      
	}
	
	@Test
	public void testCreateInstance() {
		GtnFrameworkAddAction ins = new GtnFrameworkAddAction();
		ins.createInstance();
	}

}
