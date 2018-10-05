package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.action;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.editor.client.Editor.Ignore;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.GtnFrameworkPeriodConfigurationTest;
import com.stpl.gtn.gtn2o.ui.module.periodconfiguration.constants.GtnFrameworkPeriodConfigurationContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;

import de.steinwedel.messagebox.MessageBox;


/**
 * @author praveen.kumar
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkPeriodConfigurationValidationTest {

	GtnFrameworkPeriodConfigurationValidation ins = new GtnFrameworkPeriodConfigurationValidation();

	@Test
	public void testConfigureParams() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}


	@Test
	public void testCreateInstance() {
		ins.createInstance();
	}

	
	@Ignore
	public void testDoAction() throws GtnFrameworkGeneralException, ParseException {
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		
		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("1");
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("12/12/2015").when(base).getStringFromField();
		doReturn(true).when(base).isValidFieldValue();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		
		GtnWsPeriodConfigurationRequest periodConfigurationRequest = Mockito.mock(GtnWsPeriodConfigurationRequest.class);
		doReturn("2009-12-31").when(periodConfigurationRequest).getDateFrom();
		doReturn("2009-12-31").when(periodConfigurationRequest).getDefaultDateFrom();

		GtnUIFrameworkBaseComponent base2=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI
                .getVaadinBaseComponent(GtnFrameworkPeriodConfigurationContants.PERIOD_VIEW, componentId)).thenReturn(base);
		doReturn("Multiple").when(base2).getStringFromField();

		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testValidationForConfig() {
		
	}

	@Test
	public void testAlertError() throws GtnFrameworkGeneralException {
		String message="";
		String componentId="";
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkPeriodConfigurationValidationTest.class); 

		try {
			ins.alertError(message, componentId);
		} catch (Exception e) {
			gtnLogger.info("Error" +e);
		}
	}

	
	@org.junit.Ignore
	public void testDoAction_2() throws GtnFrameworkGeneralException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		PowerMockito.mockStatic(MessageBox.class,GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("1");
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(base);
		doReturn("Auto").when(base).getCaptionFromComboBox();
		doReturn(true).when(base).isValidFieldValue();
		doReturn("Multiple").when(base).getStringFromField();
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
}
