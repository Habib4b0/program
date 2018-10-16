package com.stpl.gtn.gtn2o.registry.action.pagedtreetable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

import java.util.Arrays;

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
public class GtnFrameworkFSPagedTreeTableFillCountDataActionTest {
	
	GtnFrameworkFSPagedTreeTableFillCountDataAction instance = new GtnFrameworkFSPagedTreeTableFillCountDataAction();
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameworkActionParameter actionParameter = new GtnUIFrameworkActionParameter();
		actionParameter.setLevelNo(1);
		gtnUIFrameWorkActionConfig.setActionParameter(actionParameter );
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,1,"test","test","test","test","test","test","test","test","test","test"
				
				));
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString(),Mockito.anyString())).thenReturn(compData);
		doReturn(gtnForecastBean).when(compData).getCustomData();
			String componentId = "";
			instance.doAction(componentId , gtnUIFrameWorkActionConfig);
			instance.createInstance();
			instance.configureParams(gtnUIFrameWorkActionConfig);
	}
}