package com.stpl.gtn.gtn2o.registry.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastInnerLevelLoadActionTest {

	GtnFrameworkForecastInnerLevelLoadAction instance = new GtnFrameworkForecastInnerLevelLoadAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test"
				));
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		doReturn(compData).when(base).getComponentData();
		Map<Integer, String> hierarchyLevelMap = new HashMap<>();
		hierarchyLevelMap.put(1, "test");
		hierarchyLevelMap.put(2,"test1");
		GtnWsRecordBean hierarchyBean = new GtnWsRecordBean();
		hierarchyBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7));
		hierarchyBean.setProperties(Arrays.asList(0,1,2,3,4,5,hierarchyLevelMap,7));
		doReturn(hierarchyBean).when(compData).getCustomData();
		doNothing().when(base).loadItemsToCombobox(Mockito.any(), Mockito.any());
		
		String componentId = "";
		instance.doAction(componentId , gtnUIFrameWorkActionConfig);
	}
	
}
