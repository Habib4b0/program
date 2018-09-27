package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtCustomTable;
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
public class GtnFrameworkIfpItemsTabTableCheckActionTest {

	@Test
	public void testConfigureParams() throws Exception {
		
		GtnFrameworkIfpItemsTabTableCheckAction fixture = new GtnFrameworkIfpItemsTabTableCheckAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance() throws Exception {
		
		GtnFrameworkIfpItemsTabTableCheckAction fixture = new GtnFrameworkIfpItemsTabTableCheckAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpItemsTabTableCheck_doAction() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemsTabTableCheckAction fixture = new GtnFrameworkIfpItemsTabTableCheckAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		ExtCustomTable extCustomTable = Mockito.mock(ExtCustomTable.class);
		doReturn(true).when(extCustomTable).getColumnCheckBox(new Object()); 

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent); 
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("field0");
		fieldValues.add("field1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(baseComponent).getLogicFromPagedDataTable(); 
		doNothing().when(logic).startSearchProcess(fieldValues, true); 
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemsTabTableCheck_doAction_catchBolck() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemsTabTableCheckAction fixture = new GtnFrameworkIfpItemsTabTableCheckAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("")).thenReturn(new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		ExtCustomTable extCustomTable = Mockito.mock(ExtCustomTable.class);
		doReturn(true).when(extCustomTable).getColumnCheckBox(new Object());  

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent); 
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("field0"); 
		fieldValues.add("field1");  
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(logic).when(baseComponent).getLogicFromPagedDataTable(); 
		
		GtnFrameworkValidationFailedException exception = new GtnFrameworkValidationFailedException("Invalid");
		doThrow(exception).when(logic).startSearchProcess(fieldValues, true); 
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		
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