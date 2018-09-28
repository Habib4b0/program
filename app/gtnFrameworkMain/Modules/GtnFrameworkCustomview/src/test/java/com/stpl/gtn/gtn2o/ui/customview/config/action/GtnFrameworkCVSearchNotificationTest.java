package com.stpl.gtn.gtn2o.ui.customview.config.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Arrays;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.doReturn;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCVSearchNotificationTest {

	GtnFrameworkCVSearchNotification ins =new GtnFrameworkCVSearchNotification();

	@Test
	public void testDoAction() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameWorkAction.class,
				GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5, 6);
		resetActionParams.set(2, "val");
		resetActionParams.set(1, "1");
		resetActionParams.set(3, "val");
		resetActionParams.set(4, "val");
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		GtnUIFrameworkComponentData data=Mockito.mock(GtnUIFrameworkComponentData.class);
		doReturn(data).when(base).getComponentData();
		doReturn(new ExtFilterTable()).when(data).getCustomData();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

}
