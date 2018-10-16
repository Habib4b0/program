package com.stpl.gtn.gtn2o.ui.module.cfp.action.validation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.cfp.action.GtnFrameworkCfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkCfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCfpIdAndNoValidationActionTest {

	GtnFrameworkCfpIdAndNoValidationAction instance = new GtnFrameworkCfpIdAndNoValidationAction();
	@Test
	public void doActionTest() throws GtnFrameworkGeneralException {
		
		try {
			PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class);
			GtnUIFrameworkBaseComponent base = Mockito.mock(GtnUIFrameworkBaseComponent.class);
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = Mockito.mock(GtnUIFrameWorkActionConfig.class);
			when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(base);
			StringBuilder sb = new StringBuilder();
			sb.setLength(2);
			when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(sb);
			when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any(),Mockito.any())).thenReturn(base);
			GtnUIFrameworkWebserviceResponse reponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
			Map<String, Object> map =  Mockito.mock(HashMap.class);
			GtnFrameworkCfpIdAndNoValidationAction spy = Mockito.spy(instance);
			doReturn(reponse).when(spy).callWebservice(Mockito.any());
			doReturn(map).when(reponse).getEditRecord();
			doReturn(true).when(map).get(Mockito.any());
			String componentId = "";
			spy.doAction(componentId , gtnUIFrameWorkActionConfig);
			spy.configureParams(gtnUIFrameWorkActionConfig );
		} catch (GtnFrameworkValidationFailedException e) {
			System.out.println("Exception" + e);
		}
	}

}
