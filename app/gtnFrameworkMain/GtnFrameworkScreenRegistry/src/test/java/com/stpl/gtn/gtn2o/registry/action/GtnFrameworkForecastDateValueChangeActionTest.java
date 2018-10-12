package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastDateValueChangeActionTest {
	
	GtnFrameworkForecastDateValueChangeAction instance = new GtnFrameworkForecastDateValueChangeAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig1 = new GtnUIFrameWorkActionConfig();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig2  = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig2.addActionParameter("");
		gtnUIFrameWorkActionConfig2.addActionParameter("test");
		gtnUIFrameWorkActionConfig1.setActionParameterList(Arrays.asList(
				"test","test","test",gtnUIFrameWorkActionConfig2
				));
		List<GtnUIFrameWorkActionConfig> levelactionConfigList = new ArrayList<>();
		levelactionConfigList.add(gtnUIFrameWorkActionConfig1);
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		gtnUIFrameWorkActionConfig.addActionParameter(levelactionConfigList);
		
		AbstractComponent abstractComponent =  Mockito.mock(AbstractComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		doReturn(abstractComponent).when(base).getComponent();
		doReturn(compData).when(base).getComponentData();
		doReturn("test").when(compData).getCustomData();
//		doReturn(abstractComponent).when(base).getComponent();
//		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(), Mockito.any())).thenReturn(base);
		
//		doReturn(compData).when(base).getComponentData();
		
		String componentId="";
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

}
