package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config.action.process;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.calendarfield.GtnUIFrameworkCalendarConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class,
		GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkCurdYearChangeActionTest {

	GtnFrameworkCurdYearChangeAction ins = new GtnFrameworkCurdYearChangeAction();

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
	public void testDoAction_1() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentConfig mock = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkCalendarConfig calendarConfig = Mockito.mock(GtnUIFrameworkCalendarConfig.class);

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		List<String> fieldValues = new ArrayList<>();
		fieldValues.add(0, "val");
		fieldValues.add(1, "val");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn(1).when(base).getIntegerFromField();

		doReturn(mock).when(base).getComponentConfig();
		doReturn(calendarConfig).when(mock).getCalendarConfig();

		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentConfig mock = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkCalendarConfig calendarConfig = Mockito.mock(GtnUIFrameworkCalendarConfig.class);

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		List<String> fieldValues = new ArrayList<>();
		fieldValues.add(0, "val");
		fieldValues.add(1, "val");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn(0).when(base).getIntegerFromField();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_Fail() throws GtnFrameworkGeneralException {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
}
