package com.stpl.gtn.gtn2o.ui.module.udc.action;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.asi.ui.extfilteringtable.ExtCustomTable;
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
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkUdcSearchActionTest {
	
	@Test
	public void testDoAction_1() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("BRAND").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_2() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("FILE_TYPE").when(base).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base);
		
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_3() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base1=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("").when(base1).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base1);
		
		GtnUIFrameworkComponentData base2=Mockito.mock(GtnUIFrameworkComponentData.class);
		
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(base2);
//		GtnUIFrameworkComponentData comp=new GtnUIFrameworkComponentData();
//		Object customData=new Object();
//		comp.setCustomData(customData);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_4() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base1=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("val").when(base1).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base1);
		
		GtnUIFrameworkComponentData base2=Mockito.mock(GtnUIFrameworkComponentData.class);
		
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(base2);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testDoAction_5() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base1=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("").when(base1).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base1);
		
		GtnUIFrameworkComponentData base2=Mockito.mock(GtnUIFrameworkComponentData.class);
		
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(base2);
		ExtCustomTable customData=new ExtCustomTable();
		doReturn(customData).when(base2).getCustomData();
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_6() throws Exception {
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class);
		GtnUIFrameworkBaseComponent base1=Mockito.mock(GtnUIFrameworkBaseComponent.class);
		doReturn("-ALL-").when(base1).getCaptionFromComboBox();
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(base1);
		ins.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test 
	public void testConfigureParams() throws Exception {
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig=new GtnUIFrameWorkActionConfig();
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		ins.configureParams(gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testCreateInstance() {
		GtnFrameworkUdcSearchAction ins=new GtnFrameworkUdcSearchAction();
		ins.createInstance();
	}
}
