package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpResetActionTest {

	
	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction_if_switchCase_0()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 0
		doReturn(0).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();

		// cfpModelSid == 0
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(0);
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("createBy");
		ifpInfo.setCreatedDate(new Date());
		ifpInfo.setIfpCategory(1);
		ifpInfo.setIfpDesignation(2);
		ifpInfo.setIfpEndDate(new Date());
		ifpInfo.setIfpId("id");
		ifpInfo.setIfpName("name"); 
		ifpInfo.setIfpNo("no");
		ifpInfo.setIfpSid(3);
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpStatus(4);
		ifpInfo.setIfpType(5);
		ifpInfo.setInternalNotes("note");
		ifpInfo.setModifiedBy("modifiedBy");
		ifpInfo.setModifiedDate(new Date());
		ifpInfo.setParentIfpId("pid");
		ifpInfo.setRecordLockStatus(false);
		
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(baseComponent);
		
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_if_switchCase_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 1 (case = 1)
		doReturn(1).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();

		// cfpModelSid == 0
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(0);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(baseComponent);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_if_switchCase_2()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 2 (case = 2)
		doReturn(2).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();

		// cfpModelSid == 0
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(0);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(baseComponent);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_if_switchCase_3()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 3 (case = 3)
		doReturn(3).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();

		// cfpModelSid == 0
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(0);
		
		GtnUIFrameworkNotesTab notesTab = Mockito.mock(GtnUIFrameworkNotesTab.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString())).thenReturn(notesTab);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_if_switchCase_default()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 5 (case = default)
		doReturn(5).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_else_switchCase_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 1 (case = 1)
		doReturn(1).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();

		// cfpModelSid == 10
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(10);
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(gtnUIFrameworkPagedTableLogic).when(gtnUIFrameworkBaseComponent).getLogicFromPagedDataTable();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	@Test
	public void testDoAction_else_switchCase_2()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpResetAction fixture = new GtnFrameworkIfpResetAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		// position = 2 (case = 2)
		doReturn(2).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();

		// cfpModelSid == 10
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(10);
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(gtnUIFrameworkPagedTableLogic).when(gtnUIFrameworkBaseComponent).getLogicFromPagedDataTable();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

}