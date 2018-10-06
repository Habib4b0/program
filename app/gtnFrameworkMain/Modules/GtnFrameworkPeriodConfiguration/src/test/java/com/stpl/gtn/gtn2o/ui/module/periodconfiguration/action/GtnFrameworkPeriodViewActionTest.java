package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

import de.steinwedel.messagebox.MessageBox;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkPeriodViewActionTest {

	GtnFrameworkPeriodViewAction ins = new GtnFrameworkPeriodViewAction();
	
	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	
	@Test
	public void testDoAction_1() throws GtnFrameworkGeneralException {
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(java.util.Arrays.asList(1,"2",3,4));
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn("Multiple").when(base).getStringFromField();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		
		gtnUIFrameWorkActionConfig.setActionParameterList(java.util.Arrays.asList(1,"2",3,4));
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		doReturn("Multipl").when(base).getStringFromField();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
}
