package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;


/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpTabChangeActionTest {

	@Test
	public void testConfigureParams()
		throws Exception {
		GtnFrameworkIfpTabChangeAction fixture = new GtnFrameworkIfpTabChangeAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance()
		throws Exception {
		GtnFrameworkIfpTabChangeAction fixture = new GtnFrameworkIfpTabChangeAction();

		GtnUIFrameWorkAction result = fixture.createInstance();

		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpTabChange_doAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpTabChangeAction fixture = new GtnFrameworkIfpTabChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn(2).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(gtnUIFrameworkBaseComponent).getExtPagedTable();
		doReturn(new Object()).when(extPagedTable).getData();
		
		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(extPagedTable).getContainerLogic();
		
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("field0");
		fieldValues.add("field1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		doNothing().when(logic).startSearchProcess(fieldValues, true); 
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpTabChange_doAction_getDataIsNull()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpTabChangeAction fixture = new GtnFrameworkIfpTabChangeAction();
		String componentId = "";
	 	GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn(2).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(gtnUIFrameworkBaseComponent).getExtPagedTable();
		doReturn(null).when(extPagedTable).getData();
		
		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(extPagedTable).getContainerLogic();
		
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("field0");
		fieldValues.add("field1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		doNothing().when(logic).startSearchProcess(fieldValues, true); 
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpTabChange_doAction_checkIfNot2()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpTabChangeAction fixture = new GtnFrameworkIfpTabChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn(0).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(gtnUIFrameworkBaseComponent).getExtPagedTable();
		doReturn(new Object()).when(extPagedTable).getData();
		
		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(extPagedTable).getContainerLogic();
		
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("field0");
		fieldValues.add("field1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		doNothing().when(logic).startSearchProcess(fieldValues, true); 
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpTabChange_doAction_catchBolck()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpTabChangeAction fixture = new GtnFrameworkIfpTabChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn(2).when(gtnUIFrameworkBaseComponent).getTabSheetSelectedTabIndex();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(gtnUIFrameworkBaseComponent).getExtPagedTable();
		doReturn(new Object()).when(extPagedTable).getData();
		
		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(extPagedTable).getContainerLogic();
		
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("field0");
		fieldValues.add("field1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnFrameworkValidationFailedException exception = new GtnFrameworkValidationFailedException("Invalid");
		doThrow(exception).when(logic).startSearchProcess(fieldValues, true); 
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}