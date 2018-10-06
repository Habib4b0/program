package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
import org.junit.Before;
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
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;

import de.steinwedel.messagebox.MessageBox;

/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkPeriodConfigSaveActionTest {


	GtnFrameworkPeriodConfigSaveAction ins = new GtnFrameworkPeriodConfigSaveAction();
	
	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction() throws GtnFrameworkGeneralException {
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnWsPeriodConfigurationRequest periodConfigurationRequest=new GtnWsPeriodConfigurationRequest();
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(periodConfigurationRequest);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		gtnUIFrameWorkActionConfig.addActionParameter(1);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

}
