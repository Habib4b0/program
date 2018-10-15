package com.stpl.gtn.gtn2o.registry.config.lookups.action;

import org.junit.Ignore;
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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkForecastCustomViewLoadActionTest {
	
	GtnFrameworkForecastCustomViewLoadAction instance = new GtnFrameworkForecastCustomViewLoadAction();
	
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException
	{
		GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkComponentData compData = Mockito.mock(GtnUIFrameworkComponentData.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.setActionParameterList(Arrays.asList(
				0,"test","test","test","test","test","test","test","test","test","test"
				));
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.any(), Mockito.any())).thenReturn(compData);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
		doReturn("1").when(base).getCaptionFromV8ComboBox();
		
		GtnFrameworkForecastCustomViewLoadAction spy = Mockito.spy(instance);
		String componentId= "";
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnUIFrameworkWebserviceComboBoxResponse comResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		List<String> itemCodeList = new ArrayList<>();
		itemCodeList.add("1");
		comResponse.setItemCodeList(itemCodeList );
		List<String> itemValueList = new ArrayList<>();
		itemValueList.add("1");
		comResponse.setItemValueList(itemValueList );
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comResponse);
		doReturn(response).when(spy).callWebservice(Mockito.any());
		spy.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}
	
	@Ignore
	@Test
	public void getResponse()
	{
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		try {
			instance.callWebservice(request );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
