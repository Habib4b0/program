package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpEditActionTest {

	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkIfpEditAction fixture = new GtnFrameworkIfpEditAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpEditAction fixture = new GtnFrameworkIfpEditAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpEdit_doAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpValueChangeManager.class);
		
		GtnFrameworkIfpEditAction fixture = new GtnFrameworkIfpEditAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");
		actionParametersList.add("3");
		actionParametersList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);
		
		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);
		doReturn(valueFromPagedDataTable).when(baseComponent).getValueFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("2");
		
		GtnFrameworkIfpEditAction gtnFrameworkIfpEditAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse addResponse = new GtnUIFrameworkWebserviceResponse();
		
 
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
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
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		gtnWsIfpReponse.setGtnIFamilyPlan(gtnIFamilyPlan);
		addResponse.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(baseComponent);
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		GtnUIFrameworkNotesTab notesTab = Mockito.mock(GtnUIFrameworkNotesTab.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent("notesTab")).thenReturn(notesTab);
		
		doReturn(addResponse).when(gtnFrameworkIfpEditAction).getEditResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		gtnFrameworkIfpEditAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGtnFrameworkIfpEdit_doAction_isEditableFalse()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpValueChangeManager.class);
		
		GtnFrameworkIfpEditAction fixture = new GtnFrameworkIfpEditAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");
		actionParametersList.add("3");
		actionParametersList.add(false);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);
		
		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);
		doReturn(valueFromPagedDataTable).when(baseComponent).getValueFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("2");
		
		GtnFrameworkIfpEditAction gtnFrameworkIfpEditAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse addResponse = new GtnUIFrameworkWebserviceResponse();
		
 
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
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
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		gtnWsIfpReponse.setGtnIFamilyPlan(gtnIFamilyPlan);
		addResponse.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(baseComponent);
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		GtnUIFrameworkNotesTab notesTab = Mockito.mock(GtnUIFrameworkNotesTab.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent("notesTab")).thenReturn(notesTab);
		
		doReturn(addResponse).when(gtnFrameworkIfpEditAction).getEditResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));

		gtnFrameworkIfpEditAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGtnFrameworkIfpEdit_doAction_editResponseIsNull()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpValueChangeManager.class);
		
		GtnFrameworkIfpEditAction fixture = new GtnFrameworkIfpEditAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");
		actionParametersList.add("3");
		actionParametersList.add(false);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);
		
		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);
		doReturn(valueFromPagedDataTable).when(baseComponent).getValueFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("2");
		
		GtnFrameworkIfpEditAction gtnFrameworkIfpEditAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse addResponse = new GtnUIFrameworkWebserviceResponse();
		
 
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
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
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		gtnWsIfpReponse.setGtnIFamilyPlan(gtnIFamilyPlan);
		addResponse.setGtnWsIfpReponse(gtnWsIfpReponse);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(baseComponent);
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		GtnUIFrameworkNotesTab notesTab = Mockito.mock(GtnUIFrameworkNotesTab.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponent("notesTab")).thenReturn(notesTab);
		  
		doReturn(null).when(gtnFrameworkIfpEditAction).getEditResponse(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("3")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		gtnFrameworkIfpEditAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGtnFrameworkIfpEdit_doAction_getEditResponse()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnFrameworkIfpEditAction gtnFrameworkIfpEditAction = new GtnFrameworkIfpEditAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnFrameworkIfpEditAction.getEditResponse(gtnRequest);
	}
}