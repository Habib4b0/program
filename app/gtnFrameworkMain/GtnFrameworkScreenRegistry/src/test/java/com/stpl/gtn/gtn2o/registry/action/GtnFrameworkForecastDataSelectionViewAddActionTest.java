package com.stpl.gtn.gtn2o.registry.action;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastDataSelectionViewAddActionTest {

	GtnFrameworkForecastDataSelectionViewAddAction instance = new GtnFrameworkForecastDataSelectionViewAddAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test"
				
				));
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn(compData).when(base).getComponentData();
		GtnFrameworkForecastDataSelectionBean forecastDSBean = new GtnFrameworkForecastDataSelectionBean();
		doReturn(forecastDSBean).when(compData).getSharedPopupData();
		doReturn("test").when(base).getV8StringFromField();
		
		GtnFrameworkForecastDataSelectionViewAddAction spy = Mockito.spy(instance);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse generalResponse = Mockito.mock(GtnWsGeneralResponse.class);
		doReturn(response).when(spy).callWebservice(Mockito.any(),Mockito.any());
		doReturn(generalResponse).when(response).getGtnWsGeneralResponse();
		doReturn(true).when(generalResponse).isSucess();
		
		String componentId = "" ;
		spy.doAction(componentId , gtnUIFrameWorkActionConfig);
		
		
	}
	
	@Test
	public void doActionTest1() throws GtnFrameworkGeneralException
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test","test"
				
				));
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn(compData).when(base).getComponentData();
		GtnFrameworkForecastDataSelectionBean forecastDSBean = new GtnFrameworkForecastDataSelectionBean();
		doReturn(forecastDSBean).when(compData).getSharedPopupData();
		doReturn("test").when(base).getV8StringFromField();
		
		GtnFrameworkForecastDataSelectionViewAddAction spy = Mockito.spy(instance);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnWsGeneralResponse generalResponse = Mockito.mock(GtnWsGeneralResponse.class);
		doReturn(response).when(spy).callWebservice(Mockito.any(),Mockito.any());
		doReturn(generalResponse).when(response).getGtnWsGeneralResponse();
		doReturn(false).when(generalResponse).isSucess();
		
		String componentId = "" ;
		spy.doAction(componentId , gtnUIFrameWorkActionConfig);
		spy.createInstance();
		spy.configureParams(gtnUIFrameWorkActionConfig);
		
		
	}
	
}
