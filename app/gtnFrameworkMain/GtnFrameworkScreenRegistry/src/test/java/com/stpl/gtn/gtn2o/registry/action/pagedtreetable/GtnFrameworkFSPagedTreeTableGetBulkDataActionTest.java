package com.stpl.gtn.gtn2o.registry.action.pagedtreetable;

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
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkFSPagedTreeTableGetBulkDataActionTest {

	GtnFrameworkFSPagedTreeTableGetBulkDataAction instance = new GtnFrameworkFSPagedTreeTableGetBulkDataAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkActionParameter actionParameter = new GtnUIFrameworkActionParameter();
		GtnForecastBean gtnForecastBean =new GtnForecastBean();
		Map<String, Object> loadBulkMap = new HashMap<>();
		loadBulkMap.put("1", 1);
		loadBulkMap.put("2", 2);
		actionParameter.setLoadBulkMap(loadBulkMap );
		List<Object> recordHeader  = Arrays.asList("test");
		actionParameter.setRecordHeader(recordHeader);
		actionParameter.setCurrentValue(true);
		gtnUIFrameWorkActionConfig.setActionParameter(actionParameter );
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test"
				
				));
		List<String> hierarchyList = new ArrayList<>();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString(),Mockito.anyString())).thenReturn(compData);
		doReturn(gtnForecastBean).when(compData).getCustomData();
			String componentId = "";
			instance.doAction(componentId , gtnUIFrameWorkActionConfig);
			instance.createInstance();
			instance.configureParams(gtnUIFrameWorkActionConfig);
	}
}
