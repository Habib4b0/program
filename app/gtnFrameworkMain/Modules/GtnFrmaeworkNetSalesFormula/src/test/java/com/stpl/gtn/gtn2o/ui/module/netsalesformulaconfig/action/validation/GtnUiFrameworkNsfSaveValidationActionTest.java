package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import java.util.ArrayList;
import java.util.List;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnUiFrameworkNsfSaveValidationActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCreateInstance() throws Exception {

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnUiFrameworkNsfSaveValidation_doAction_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString()))
				.thenReturn(selectedDeductionResultTable);

		// subMessage.isEmpty() = true
		StringBuilder subMessage = new StringBuilder("");
		when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(subMessage);

		doReturn(1).when(selectedDeductionResultTable).getExtPagedTableSize();

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);
		recordBean.setPropertyValueByIndex(1, "1");
		recordBean.setPropertyValueByIndex(2, "2");

		List<GtnWsRecordBean> recordBeansList = new ArrayList<>();
		recordBeansList.add(recordBean);

		// selectedDeductionResultTable.getItemsFromDataTable().isEmpty() =
		// false
		doReturn(recordBeansList).when(selectedDeductionResultTable).getItemsFromDataTable();

		// bean.getPropertyValue("indicator").toString().isEmpty() = true
		doReturn("").when(recordBean).getPropertyValue("indicator");

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSaveValidation_doAction_getPropertyValue_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString()))
				.thenReturn(selectedDeductionResultTable);

		// subMessage.isEmpty() = true
		StringBuilder subMessage = new StringBuilder("");
		when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(subMessage);

		doReturn(1).when(selectedDeductionResultTable).getExtPagedTableSize();

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);
		recordBean.setPropertyValueByIndex(1, "1");
		recordBean.setPropertyValueByIndex(2, "2");

		List<GtnWsRecordBean> recordBeansList = new ArrayList<>();
		recordBeansList.add(recordBean);

		// selectedDeductionResultTable.getItemsFromDataTable().isEmpty() =
		// false
		doReturn(recordBeansList).when(selectedDeductionResultTable).getItemsFromDataTable();

		// bean.getPropertyValue("indicator").toString().isEmpty() = false
		doReturn("notIsEmpty").when(recordBean).getPropertyValue("indicator");

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSaveValidation_doAction_subMessage_notIsEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString()))
				.thenReturn(selectedDeductionResultTable);

		// subMessage.isEmpty() = false
		StringBuilder subMessage = new StringBuilder("subMessageNotIsEmpty");
		when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(subMessage);

		doReturn(1).when(selectedDeductionResultTable).getExtPagedTableSize();

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);
		recordBean.setPropertyValueByIndex(1, "1");
		recordBean.setPropertyValueByIndex(2, "2");

		List<GtnWsRecordBean> recordBeansList = new ArrayList<>();
		recordBeansList.add(recordBean);

		// selectedDeductionResultTable.getItemsFromDataTable().isEmpty() =
		// false
		doReturn(recordBeansList).when(selectedDeductionResultTable).getItemsFromDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSaveValidation_doAction_getItemsFromDataTable_IsEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString()))
				.thenReturn(selectedDeductionResultTable);

		// subMessage.isEmpty() = false
		StringBuilder subMessage = new StringBuilder("subMessageNotIsEmpty");
		when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(subMessage);

		doReturn(1).when(selectedDeductionResultTable).getExtPagedTableSize();

		// This is an empty list
		List<GtnWsRecordBean> recordBeansList = new ArrayList<>();

		// selectedDeductionResultTable.getItemsFromDataTable().isEmpty() = true
		doReturn(recordBeansList).when(selectedDeductionResultTable).getItemsFromDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSaveValidation_doAction_getExtPagedTableSize_IsZero_subMessageNotIsEmpty()
			throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString()))
				.thenReturn(selectedDeductionResultTable);

		// subMessage.isEmpty() = false
		StringBuilder subMessage = new StringBuilder("subMessageNotIsEmpty");
		when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(subMessage);

		// (selectedDeductionResultTable.getExtPagedTableSize() == 0) = true
		doReturn(0).when(selectedDeductionResultTable).getExtPagedTableSize();

		// This is an empty list
		List<GtnWsRecordBean> recordBeansList = new ArrayList<>();

		// selectedDeductionResultTable.getItemsFromDataTable().isEmpty() = true
		doReturn(recordBeansList).when(selectedDeductionResultTable).getItemsFromDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSaveValidation_doAction_getExtPagedTableSize_IsZero_subMessageIsEmpty()
			throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfSaveValidationAction fixture = new GtnUiFrameworkNsfSaveValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent selectedDeductionResultTable = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString()))
				.thenReturn(selectedDeductionResultTable);

		// subMessage.isEmpty() = true
		StringBuilder subMessage = new StringBuilder("");
		when(GtnUIFrameworkGlobalUI.validateFields(Mockito.any(), Mockito.any())).thenReturn(subMessage);

		// (selectedDeductionResultTable.getExtPagedTableSize() == 0) = true
		doReturn(0).when(selectedDeductionResultTable).getExtPagedTableSize();

		// This is an empty list
		List<GtnWsRecordBean> recordBeansList = new ArrayList<>();

		// selectedDeductionResultTable.getItemsFromDataTable().isEmpty() = true
		doReturn(recordBeansList).when(selectedDeductionResultTable).getItemsFromDataTable();

		callDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}

	/**
	 * @param fixture
	 * @param componentId
	 * @param gtnUIFrameWorkActionConfig
	 * @throws GtnFrameworkGeneralException
	 */
	private void callDoAction(GtnUiFrameworkNsfSaveValidationAction fixture, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *             if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}