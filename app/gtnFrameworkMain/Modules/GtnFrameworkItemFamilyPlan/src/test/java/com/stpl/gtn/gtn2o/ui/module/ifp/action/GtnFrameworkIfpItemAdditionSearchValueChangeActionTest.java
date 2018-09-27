package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
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
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpItemAdditionSearchValueChangeActionTest {

	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Form").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_switchItemStatus() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Item Status").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_switchStrength() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Strength").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_switchTherapeuticClass() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Therapeutic Class").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_switchUOM() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("UOM").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_switchDefault() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Default").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_valueNull() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible();

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_valueIsEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();
		doReturn(true).when(baseComponent).isVisible(); 

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnFrameworkIfpItemAdditionSearchValueChange_doAction_dropDown_IsVisibleFalse() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchValueChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Brand").when(baseComponent).getStringFromField();
		doReturn(false).when(baseComponent).isVisible(); 

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		GtnUIFrameworkComboBoxConfig comboBoxConfig = Mockito.mock(GtnUIFrameworkComboBoxConfig.class);

		doReturn(componentConfig).when(baseComponent).getComponentConfig();
		doReturn(comboBoxConfig).when(componentConfig).getGtnComboboxConfig();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);

	}

	private void callDoAction(GtnFrameworkIfpItemAdditionSearchValueChangeAction fixture, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig){
		
		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
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