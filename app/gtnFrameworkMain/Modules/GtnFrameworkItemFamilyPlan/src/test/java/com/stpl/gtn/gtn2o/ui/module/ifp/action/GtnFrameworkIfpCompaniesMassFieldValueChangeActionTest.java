package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class})
public class GtnFrameworkIfpCompaniesMassFieldValueChangeActionTest {


	@Test
	public void testConfigureParams()
		throws Exception {
		GtnFrameworkIfpCompaniesMassFieldValueChangeAction fixture = new GtnFrameworkIfpCompaniesMassFieldValueChangeAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpCompaniesMassFieldValueChangeAction fixture = new GtnFrameworkIfpCompaniesMassFieldValueChangeAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result); 
	}

	@Test
	public void testGtnFrameworkIfpCompaniesMassFieldValueChangeAction_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpCompaniesMassFieldValueChangeAction fixture = new GtnFrameworkIfpCompaniesMassFieldValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("cid0");
		fieldValues.add("cid1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn("IFP Status").when(gtnUIFrameworkBaseComponent).getStringFromField();
		
		GtnUIFrameworkBaseComponent ifpCompaniesmassDropDown = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cid1")).thenReturn(ifpCompaniesmassDropDown);
		doNothing().when(ifpCompaniesmassDropDown).setVisible(Mockito.anyBoolean());
		
		GtnUIFrameworkBaseComponent ifpCompaniesMassDateField = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cid0")).thenReturn(ifpCompaniesMassDateField);
		doNothing().when(ifpCompaniesMassDateField).setVisible(Mockito.anyBoolean());
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);  
		
	}
	
	@Test
	public void testGtnFrameworkIfpCompaniesMassFieldValueChangeAction_2()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpCompaniesMassFieldValueChangeAction fixture = new GtnFrameworkIfpCompaniesMassFieldValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("cid0");
		fieldValues.add("cid1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn("").when(gtnUIFrameworkBaseComponent).getStringFromField();
		
		GtnUIFrameworkBaseComponent ifpCompaniesMassDateField = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cid0")).thenReturn(ifpCompaniesMassDateField);
		doNothing().when(ifpCompaniesMassDateField).setVisible(Mockito.anyBoolean());
		
		GtnUIFrameworkBaseComponent ifpCompaniesmassDropDown = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cid1")).thenReturn(ifpCompaniesmassDropDown);
		doNothing().when(ifpCompaniesmassDropDown).setVisible(Mockito.anyBoolean());
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);  
		
	}
	
	@Test
	public void testGtnFrameworkIfpCompaniesMassFieldValueChangeAction_3()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpCompaniesMassFieldValueChangeAction fixture = new GtnFrameworkIfpCompaniesMassFieldValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("cid0");
		fieldValues.add("cid1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(gtnUIFrameworkBaseComponent);
		doReturn(null).when(gtnUIFrameworkBaseComponent).getStringFromField();
		
		GtnUIFrameworkBaseComponent ifpCompaniesMassDateField = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cid0")).thenReturn(ifpCompaniesMassDateField);
		doNothing().when(ifpCompaniesMassDateField).setVisible(Mockito.anyBoolean());
		
		GtnUIFrameworkBaseComponent ifpCompaniesmassDropDown = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("cid1")).thenReturn(ifpCompaniesmassDropDown);
		doNothing().when(ifpCompaniesmassDropDown).setVisible(Mockito.anyBoolean()); 
		
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