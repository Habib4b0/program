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
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkFSPagedTreeTableGetCountActionTest {

	
	GtnFrameworkFSPagedTreeTableGetCountAction instance = new GtnFrameworkFSPagedTreeTableGetCountAction();
	@Test
	public void doActionTest1() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test"
				
				));
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString(),Mockito.anyString())).thenReturn(compData);
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		doReturn(gtnForecastBean).when(compData).getCustomData();
		GtnUIFrameworkActionParameter actionParameter = new GtnUIFrameworkActionParameter();
		actionParameter.setCurrentValue(true);
		GtnWsRecordBean bean = new GtnWsRecordBean();
		bean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
		bean.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		List<Object> additionalProperties = new ArrayList<>();
		additionalProperties.add(1);
		additionalProperties.add(1);
		bean.setAdditionalProperties(additionalProperties );
		actionParameter.setLastParent(bean);
		gtnUIFrameWorkActionConfig.setActionParameter(actionParameter );
			String componentId = "";
			instance.doAction(componentId , gtnUIFrameWorkActionConfig);
			instance.createInstance();
			instance.configureParams(gtnUIFrameWorkActionConfig);
	}
}
