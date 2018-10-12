package com.stpl.gtn.gtn2o.registry.action;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastDataSelectionViewUpdateActionTest {
	
	GtnFrameworkForecastDataSelectionViewUpdateAction instance = new GtnFrameworkForecastDataSelectionViewUpdateAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test"
				
				));
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		doReturn(compData).when(base).getComponentData();
		doReturn("test").when(base).getV8StringFromField();
		GtnFrameworkForecastDataSelectionBean dataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		doReturn(dataSelectionBean).when(compData).getSharedPopupData();
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse gtnWsGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);
		GtnFrameworkForecastDataSelectionViewUpdateAction spy = Mockito.spy(instance);
		doReturn(response).when(spy).callWebservice(Mockito.any());
		doReturn(gtnWsGeneralResponse).when(response).getGtnWsGeneralResponse();
		doReturn(true).when(gtnWsGeneralResponse).isSucess();
		
		
		String componentId = "";
		spy.doAction(componentId , gtnUIFrameWorkActionConfig);
		spy.configureParams(gtnUIFrameWorkActionConfig);
		spy.createInstance();
	}
	@Test
	public void doActionTest1() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test"
				
				));
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		doReturn(compData).when(base).getComponentData();
		doReturn("test").when(base).getV8StringFromField();
		GtnFrameworkForecastDataSelectionBean dataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		doReturn(dataSelectionBean).when(compData).getSharedPopupData();
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse gtnWsGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);
		GtnFrameworkForecastDataSelectionViewUpdateAction spy = Mockito.spy(instance);
		doReturn(response).when(spy).callWebservice(Mockito.any());
		doReturn(gtnWsGeneralResponse).when(response).getGtnWsGeneralResponse();
		doReturn(false).when(gtnWsGeneralResponse).isSucess();
		
		
		String componentId = "";
		spy.doAction(componentId , gtnUIFrameWorkActionConfig);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		spy.callWebservice(request );
	}

}
