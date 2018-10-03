package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.Field;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.TextField;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,Page.class })
public class GtnFrameworkIfpSaveActionTest {

	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkIfpSaveAction fixture = new GtnFrameworkIfpSaveAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParametersList = new ArrayList<>();

		List<String> fields = new ArrayList<>();
		fields.add("0");
		fields.add("1");
		fields.add("2");

		List<String> beanFields = new ArrayList<>();
		beanFields.add("0");
		beanFields.add("1");
		beanFields.add("2");

		actionParametersList.add(null);
		actionParametersList.add((ArrayList<String>) fields);
		actionParametersList.add((ArrayList<String>) beanFields);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkIfpSaveAction fixture = new GtnFrameworkIfpSaveAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, Page.class);

		GtnFrameworkIfpSaveAction fixture = new GtnFrameworkIfpSaveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();

		List<String> fields = new ArrayList<>();
		fields.add("0");
		fields.add("1");
		fields.add("2");

		List<String> beanFields = new ArrayList<>();
		beanFields.add("0");
		beanFields.add("1");
		beanFields.add("2");

		actionParametersList.add(null);
		actionParametersList.add((ArrayList<String>) fields);
		actionParametersList.add((ArrayList<String>) beanFields);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(actionParametersList).when(baseComponent).getNotesTabValue();
		
		// systemId = 5
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(5);

		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setInternalNotes("");
		ifpBean.setIfpInfo(ifpInfo);
		
		List<Object> noteList = new ArrayList<>();
		List<String> zeroList = new ArrayList<>();
		zeroList.add("0");
		zeroList.add("1");
		zeroList.add("2");

		NotesDTO note = new NotesDTO();
		note.setDocDetailsId(1);
		note.setDocumentFullPath("path");
		note.setDocumentName("name");

		List<NotesDTO> NotesDTOList = new ArrayList<>();
		NotesDTOList.add(note);

		noteList.add(null);
		noteList.add(NotesDTOList);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab")).thenReturn(baseComponent);
		doReturn(noteList).when(baseComponent).getNotesTabValue();

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		NotesTabBean ifpNotesBean = Mockito.mock(NotesTabBean.class);
		ifpNotesBean.setCreatedBy(Integer.valueOf("20516"));
		
		GtnUIFrameworkPagedTableLogic logicFromPagedDataTable = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logicFromPagedDataTable).when(baseComponent).getLogicFromPagedDataTable();

		TabSheet tabSheet = Mockito.mock(TabSheet.class);
		doReturn(tabSheet).when(baseComponent).getAsTabSheet();

		doReturn("").when(baseComponent).getStringFromField();
		doNothing().when(baseComponent).loadFieldValue(new Object());
		

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(baseComponent);    
		 
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString())).thenReturn(new TextField());
		
		fixture.configureParams(gtnUIFrameWorkActionConfig);
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testDoAction_getModeIsCopy() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, Page.class);

		GtnFrameworkIfpSaveAction fixture = new GtnFrameworkIfpSaveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();

		List<String> fields = new ArrayList<>();
		fields.add("0");
		fields.add("1");
		fields.add("2");

		List<String> beanFields = new ArrayList<>();
		beanFields.add("0");
		beanFields.add("1");
		beanFields.add("2");

		actionParametersList.add(null);
		actionParametersList.add((ArrayList<String>) fields);
		actionParametersList.add((ArrayList<String>) beanFields);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(actionParametersList).when(baseComponent).getNotesTabValue();
		
		// "Copy".equals(getMode) = True
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("Copy");

		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setInternalNotes("");
		ifpBean.setIfpInfo(ifpInfo);
		
		List<Object> noteList = new ArrayList<>();
		List<String> zeroList = new ArrayList<>();
		zeroList.add("0");
		zeroList.add("1");
		zeroList.add("2");

		NotesDTO note = new NotesDTO();
		note.setDocDetailsId(1);
		note.setDocumentFullPath("path");
		note.setDocumentName("name");

		List<NotesDTO> NotesDTOList = new ArrayList<>();
		NotesDTOList.add(note);

		noteList.add(null);
		noteList.add(NotesDTOList);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab")).thenReturn(baseComponent);
		doReturn(noteList).when(baseComponent).getNotesTabValue();

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		NotesTabBean ifpNotesBean = Mockito.mock(NotesTabBean.class);
		ifpNotesBean.setCreatedBy(Integer.valueOf("20516"));
		
		GtnUIFrameworkPagedTableLogic logicFromPagedDataTable = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logicFromPagedDataTable).when(baseComponent).getLogicFromPagedDataTable();

		TabSheet tabSheet = Mockito.mock(TabSheet.class);
		doReturn(tabSheet).when(baseComponent).getAsTabSheet();

		doReturn("").when(baseComponent).getStringFromField();
		doNothing().when(baseComponent).loadFieldValue(new Object());  
		

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(baseComponent);    
		 
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString())).thenReturn(new TextField());
		
		fixture.configureParams(gtnUIFrameWorkActionConfig);
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testDoAction_loadNotesTabCatchBlock() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, Page.class);

		GtnFrameworkIfpSaveAction fixture = new GtnFrameworkIfpSaveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();

		List<String> fields = new ArrayList<>();
		fields.add("0");
		fields.add("1");
		fields.add("2");

		List<String> beanFields = new ArrayList<>();
		beanFields.add("0");
		beanFields.add("1");
		beanFields.add("2");

		actionParametersList.add(null);
		actionParametersList.add((ArrayList<String>) fields);
		actionParametersList.add((ArrayList<String>) beanFields);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(actionParametersList).when(baseComponent).getNotesTabValue();
		
		// "Copy".equals(getMode) = True
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("Copy");

		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setInternalNotes("");
		ifpBean.setIfpInfo(ifpInfo);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab")).thenReturn(baseComponent);
		doReturn(actionParametersList).when(baseComponent).getNotesTabValue();

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		NotesTabBean ifpNotesBean = Mockito.mock(NotesTabBean.class);
		ifpNotesBean.setCreatedBy(Integer.valueOf("20516"));
		
		GtnUIFrameworkPagedTableLogic logicFromPagedDataTable = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logicFromPagedDataTable).when(baseComponent).getLogicFromPagedDataTable();

		TabSheet tabSheet = Mockito.mock(TabSheet.class);
		doReturn(tabSheet).when(baseComponent).getAsTabSheet();

		doReturn("").when(baseComponent).getStringFromField();
		doNothing().when(baseComponent).loadFieldValue(new Object());  
		

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(baseComponent);    
		 
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString())).thenReturn(new TextField());
		
		fixture.configureParams(gtnUIFrameWorkActionConfig);
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	@Test
	public void testDoAction_systemIdLessthanZero() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class, Page.class);

		GtnFrameworkIfpSaveAction fixture = new GtnFrameworkIfpSaveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();

		List<String> fields = new ArrayList<>();
		fields.add("0");
		fields.add("1");
		fields.add("2");

		List<String> beanFields = new ArrayList<>();
		beanFields.add("0");
		beanFields.add("1");
		beanFields.add("2");

		actionParametersList.add(null);
		actionParametersList.add((ArrayList<String>) fields);
		actionParametersList.add((ArrayList<String>) beanFields);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(actionParametersList).when(baseComponent).getNotesTabValue();
		
		// systemId < 0
		when(GtnUIFrameworkGlobalUI.getSessionProperty("ifpModelSid")).thenReturn(-1);

		GtnIFamilyPlanBean ifpBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setInternalNotes("");
		ifpBean.setIfpInfo(ifpInfo);
		
		List<Object> noteList = new ArrayList<>();
		List<String> zeroList = new ArrayList<>();
		zeroList.add("0");
		zeroList.add("1");
		zeroList.add("2");

		NotesDTO note = new NotesDTO();
		note.setDocDetailsId(1);
		note.setDocumentFullPath("path");
		note.setDocumentName("name");

		List<NotesDTO> NotesDTOList = new ArrayList<>();
		NotesDTOList.add(note);

		noteList.add(null);
		noteList.add(NotesDTOList);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab")).thenReturn(baseComponent);
		doReturn(noteList).when(baseComponent).getNotesTabValue();

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		NotesTabBean ifpNotesBean = Mockito.mock(NotesTabBean.class);
		ifpNotesBean.setCreatedBy(Integer.valueOf("20516"));
		
		GtnUIFrameworkPagedTableLogic logicFromPagedDataTable = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logicFromPagedDataTable).when(baseComponent).getLogicFromPagedDataTable();

		TabSheet tabSheet = Mockito.mock(TabSheet.class);
		doReturn(tabSheet).when(baseComponent).getAsTabSheet();

		doReturn("").when(baseComponent).getStringFromField();
		doNothing().when(baseComponent).loadFieldValue(new Object());
		

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(baseComponent);    
		 
		when(GtnUIFrameworkGlobalUI.getVaadinComponent(Mockito.anyString())).thenReturn(new TextField());
		
		fixture.configureParams(gtnUIFrameWorkActionConfig);
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}