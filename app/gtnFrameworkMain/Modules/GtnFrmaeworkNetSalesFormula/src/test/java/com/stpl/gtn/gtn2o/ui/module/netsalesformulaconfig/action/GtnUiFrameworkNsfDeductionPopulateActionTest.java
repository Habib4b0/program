package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class })
public class GtnUiFrameworkNsfDeductionPopulateActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfDeductionPopulateAction fixture = new GtnUiFrameworkNsfDeductionPopulateAction();
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

		GtnUiFrameworkNsfDeductionPopulateAction fixture = new GtnUiFrameworkNsfDeductionPopulateAction();
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
	public void testGtnUiFrameworkNsfDeductionPopulate_doAction_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfDeductionPopulateAction fixture = new GtnUiFrameworkNsfDeductionPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
  
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Net Sales Rule No").when(baseComponent).getCaptionFromComboBox();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		List<Object> properties = new ArrayList<>();
		properties.add(0);
		properties.add(1);
		properties.add(2);

		GtnWsRecordBean dto = Mockito.mock(GtnWsRecordBean.class);
		dto.setProperties(properties);

		doReturn(dto).when(componentData).getCustomData();
		doReturn(properties).when(dto).getProperties();
		doReturn(1).when(dto).getPropertyValueByIndex(Mockito.anyInt());
		when(GtnFrameworkNSFCommonLogic.updateField("ruleSid", new Object(), true, 1, true, "", true)).thenReturn(true);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfDeductionPopulate_doAction_getPropertyValue_notNull() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfDeductionPopulateAction fixture = new GtnUiFrameworkNsfDeductionPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Net Sales Rule No").when(baseComponent).getCaptionFromComboBox();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnWsRecordBean dto = Mockito.mock(GtnWsRecordBean.class);
		doReturn(dto).when(componentData).getCustomData();

		// if(dto.getPropertyValue("systemId") != null) = True
		doReturn(1).when(dto).getPropertyValue("systemId");

		when(GtnFrameworkNSFCommonLogic.updateField("ruleSid", new Object(), true, 1, true, "", true)).thenReturn(true);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfDeductionPopulate_doAction_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfDeductionPopulateAction fixture = new GtnUiFrameworkNsfDeductionPopulateAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("elseBlock").when(baseComponent).getCaptionFromComboBox();

		when(GtnFrameworkNSFCommonLogic.updateField("indicator", new Object(), true, 1, true, "", true))
				.thenReturn(false);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	/**
	 * Run the void reloadTable(boolean,String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnUiFrameworkNsfDeductionPopulate_ReloadTable() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfDeductionPopulateAction fixture = new GtnUiFrameworkNsfDeductionPopulateAction();
		boolean isSuccess = true;
		String viewId = "";

		fixture.reloadTable(isSuccess, viewId);
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