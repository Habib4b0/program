package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
		GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkIfpItemsTabPopulateActionTest {

	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(baseComponent);
		doReturn("ifpItemsTabMassField").when(baseComponent).getStringFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")).thenReturn(baseComponent);
		doReturn(1).when(baseComponent).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getObjectFromField();

		GtnUIFrameworkGlobalUI.getSessionProperty("");

		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_IFP_startDate() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(baseComponent);
		doReturn("IFP Start Date").when(baseComponent).getStringFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")).thenReturn(baseComponent);
		doReturn(1).when(baseComponent).getIntegerFromField();

		// GtnIFamilyPlanCommonUpdateBean updateBean = new
		// GtnIFamilyPlanCommonUpdateBean();
		// updateBean.setPopulateType("populate");

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getObjectFromField();

		GtnUIFrameworkGlobalUI.getSessionProperty("");

		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_ifpItemsTabMassField_condition1() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		// ifpItemsTabMassField = IFP Status
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(baseComponent);
		doReturn("IFP Status").when(baseComponent).getStringFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		// ifpItemsTabMassDropDown = 0
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")).thenReturn(baseComponent);
		doReturn(0).when(baseComponent).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getObjectFromField();

		GtnUIFrameworkGlobalUI.getSessionProperty("");

		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_ifpItemsTabMassField_condition2() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		// ifpItemsTabMassField = ""
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")).thenReturn(baseComponent);
		doReturn(10).when(baseComponent).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getObjectFromField();

		GtnUIFrameworkGlobalUI.getSessionProperty("");

		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_2() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(baseComponent);
		doReturn("ifpItemsTabMassField").when(baseComponent).getStringFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(new Date()).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")).thenReturn(baseComponent);
		doReturn(1).when(baseComponent).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getObjectFromField();

		GtnUIFrameworkGlobalUI.getSessionProperty("");

		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoAction_validateEmptyField() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnFrameworkIfpItemsTabPopulateAction fixture = new GtnFrameworkIfpItemsTabPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);

		List<Object> actionParametersList = new ArrayList<>();
		actionParametersList.add("0");
		actionParametersList.add("1");
		actionParametersList.add("2");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParametersList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassField")).thenReturn(baseComponent);
		doReturn("").when(baseComponent).getStringFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getDateFromDateField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDropDown")).thenReturn(baseComponent);
		doReturn(1).when(baseComponent).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabMassDateFeild")).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getObjectFromField();

		GtnUIFrameworkGlobalUI.getSessionProperty("");

		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemsTabResultDataTable")).thenReturn(baseComponent);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	public void callDoAction(GtnFrameworkIfpItemsTabPopulateAction fixture, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}