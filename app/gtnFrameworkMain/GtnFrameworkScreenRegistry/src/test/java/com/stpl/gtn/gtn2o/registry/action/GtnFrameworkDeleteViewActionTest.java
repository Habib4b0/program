package com.stpl.gtn.gtn2o.registry.action;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkDeleteViewActionTest {

	GtnFrameworkDeleteViewAction instance = new GtnFrameworkDeleteViewAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig2  = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig2.addActionParameter("");
		gtnUIFrameWorkActionConfig2.addActionParameter("test");
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				"test","test","test"
				) );
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		gtnUIFrameWorkActionConfig.addActionParameter("test");
		
		
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("202020");
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		doReturn(compData).when(base).getComponentData();
		GtnWsRecordBean recordBean = new GtnWsRecordBean();
		recordBean.setRecordHeader(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
		recordBean.setProperties(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
		doReturn(recordBean).when(compData).getCustomData();
		
		GtnFrameworkDeleteViewAction spy = Mockito.spy(instance);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		
		GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest =  Mockito.mock(GtnWsForecastNewArchRequest.class);
		GtnFrameworkForecastDataSelectionBean gtnFrameworkForecastDataSelectionBean =Mockito.mock(GtnFrameworkForecastDataSelectionBean.class);
		gtnWsForecastNewArchRequest.setDataSelectionBean(gtnFrameworkForecastDataSelectionBean );
		doReturn(gtnFrameworkForecastDataSelectionBean).when(response).getGtnFrameworkForecastDataSelectionBean();
		doReturn(response).when(spy).callWebService(Mockito.any());
		doReturn(gtnFrameworkForecastDataSelectionBean).when(response).getGtnFrameworkForecastDataSelectionBean();
		doReturn(1).when(gtnFrameworkForecastDataSelectionBean).getResultCount();
		 
		 
		String componentId = "";
		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
	}
	@Ignore
	@Test
	public void webServiceTest()
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		instance.callWebService(request );
	}
	
}
