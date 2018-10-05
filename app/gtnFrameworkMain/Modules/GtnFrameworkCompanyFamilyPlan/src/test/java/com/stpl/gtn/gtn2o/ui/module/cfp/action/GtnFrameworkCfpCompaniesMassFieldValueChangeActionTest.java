package com.stpl.gtn.gtn2o.ui.module.cfp.action;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;
import com.itextpdf.text.List;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})


public class GtnFrameworkCfpCompaniesMassFieldValueChangeActionTest {

	GtnFrameworkCfpCompaniesMassFieldValueChangeAction instance = new GtnFrameworkCfpCompaniesMassFieldValueChangeAction();
	
	@Test
	public void test() throws GtnFrameworkGeneralException {
		String componentId ="";  
		
		
		 
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig =new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		ArrayList<String> componentIdList = new ArrayList<String>();
		componentIdList.add("test");
		componentIdList.add("test");
		gtnUIFrameWorkActionConfig.setFieldValues(componentIdList);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField")).thenReturn(base);
		doReturn("CFP Status").when (base).getStringFromField();
		
		GtnUIFrameworkBaseComponent cfpCompaniesMassDateField=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("test")).thenReturn(cfpCompaniesMassDateField);
		doNothing().when(cfpCompaniesMassDateField).setVisible(Mockito.anyBoolean());
		
		
		GtnUIFrameworkBaseComponent cfpCompaniesmassDropDown=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("test")).thenReturn(cfpCompaniesmassDropDown);
		doNothing().when(cfpCompaniesmassDropDown).setVisible(Mockito.anyBoolean());
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
		instance.configureParams(gtnUIFrameWorkActionConfig);
		instance.createInstance();
	}

	@Test
	public void test2() throws GtnFrameworkGeneralException{
		String componentId ="";
		 
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig =new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		ArrayList<String> componentIdList = new ArrayList<String>();
		componentIdList.add("test");
		componentIdList.add("test");
		gtnUIFrameWorkActionConfig.setFieldValues(componentIdList);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField")).thenReturn(base);
		doReturn("").when (base).getStringFromField();
		
		GtnUIFrameworkBaseComponent cfpCompaniesMassDateField=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("test")).thenReturn(cfpCompaniesMassDateField);
		doNothing().when(cfpCompaniesMassDateField).setVisible(Mockito.anyBoolean());
		
		
		GtnUIFrameworkBaseComponent cfpCompaniesmassDropDown=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("test")).thenReturn(cfpCompaniesmassDropDown);
		doNothing().when(cfpCompaniesmassDropDown).setVisible(Mockito.anyBoolean());
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	@Test
	public void test3() throws GtnFrameworkGeneralException{
		String componentId ="";
		 
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig =new GtnUIFrameWorkActionConfig();
		gtnUIFrameWorkActionConfig.addActionParameter(0);
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		ArrayList<String> componentIdList = new ArrayList<String>();
		componentIdList.add("test");
		componentIdList.add("test");
		gtnUIFrameWorkActionConfig.setFieldValues(componentIdList);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cfpCompaniesTabMassField")).thenReturn(base);
		doReturn(null).when (base).getStringFromField();
		
		GtnUIFrameworkBaseComponent cfpCompaniesMassDateField=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("test")).thenReturn(cfpCompaniesMassDateField);
		doNothing().when(cfpCompaniesMassDateField).setVisible(Mockito.anyBoolean());
		
		
		GtnUIFrameworkBaseComponent cfpCompaniesmassDropDown=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("test")).thenReturn(cfpCompaniesmassDropDown);
		doNothing().when(cfpCompaniesmassDropDown).setVisible(Mockito.anyBoolean());
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
}
