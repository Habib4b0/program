package com.stpl.gtn.gtn2o.ui.customview.config.action;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.when;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;


import de.steinwedel.messagebox.MessageBox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkBackActionTest {


	@Test
	public void testDoAction_1() throws Exception {
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        List<GtnUIFrameWorkActionConfig> list=null;
        List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "val");
		resetActionParams.set(3, list);
		resetActionParams.set(4, list);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
        GtnFrameworkBackAction ins = new GtnFrameworkBackAction();
        ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	
	@Test
	public void testDoAction_2() throws Exception {
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
        String componentId = "";
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
        when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("View");
        GtnFrameworkBackAction ins = new GtnFrameworkBackAction();
        ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	
	@Test
	public void testCreateInstance() {
		 GtnFrameworkBackAction ins = new GtnFrameworkBackAction();
		 ins.createInstance();
	}

	@Test
	public void testConfigureParams() throws Exception {
		 GtnFrameworkBackAction ins = new GtnFrameworkBackAction();
		 GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}
}
