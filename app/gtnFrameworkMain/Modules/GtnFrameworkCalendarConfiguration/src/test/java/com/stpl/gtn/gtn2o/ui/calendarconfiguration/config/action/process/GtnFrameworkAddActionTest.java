package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import static org.junit.Assert.*;

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
import static org.mockito.Mockito.*;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.MessageBox;

/**
 * @author praveen.kumar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkAddActionTest {

	GtnFrameworkAddAction ins = new GtnFrameworkAddAction();

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(gtnUIFrameworkComponentData).when(base).getComponentData();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_Fail() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);

		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
}
