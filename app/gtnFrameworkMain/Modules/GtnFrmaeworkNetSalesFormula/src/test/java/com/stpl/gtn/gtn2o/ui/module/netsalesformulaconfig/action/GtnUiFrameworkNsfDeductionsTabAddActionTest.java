package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfDeductionsTabAddActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
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

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
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
	public void testDoAction_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(null).when(baseComponent).getCaptionFromComboBox();

		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);

		Set<GtnWsRecordBean> beans = new HashSet<>();
		beans.add(valueFromPagedDataTable);
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("systemId");

		List<Object> propertyList = new ArrayList<Object>();
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		doReturn(propertyList).when(valueFromPagedDataTable).getProperties();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUiFrameworkNsfDeductionsTabAddAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(gtnWsresponse).when(gtnFrameworkIfpDeleteAction).getWsResponse(Mockito.any());

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkPagedTableLogic tableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(tableLogic).when(componentData).getCurrentPageTableLogic();

		gtnFrameworkIfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testDoAction_formulaType_notNull() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("Contract").when(baseComponent).getCaptionFromComboBox();

		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);

		Set<GtnWsRecordBean> beans = new HashSet<>();
		beans.add(valueFromPagedDataTable);
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("systemId");

		List<Object> propertyList = new ArrayList<Object>();
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		doReturn(propertyList).when(valueFromPagedDataTable).getProperties();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUiFrameworkNsfDeductionsTabAddAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(gtnWsresponse).when(gtnFrameworkIfpDeleteAction).getWsResponse(Mockito.any());

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkPagedTableLogic tableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(tableLogic).when(componentData).getCurrentPageTableLogic();

		gtnFrameworkIfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testDoAction_response_isFalse() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("notContract").when(baseComponent).getCaptionFromComboBox();

		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);

		Set<GtnWsRecordBean> beans = new HashSet<>();
		beans.add(valueFromPagedDataTable);
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("systemId");

		List<Object> propertyList = new ArrayList<Object>();
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		doReturn(propertyList).when(valueFromPagedDataTable).getProperties();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUiFrameworkNsfDeductionsTabAddAction gtnFrameworkIfpDeleteAction = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(gtnWsresponse).when(gtnFrameworkIfpDeleteAction).getWsResponse(Mockito.any());

		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		// reponse.isSuccess = false
		gtnWsGeneralResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkPagedTableLogic tableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(tableLogic).when(componentData).getCurrentPageTableLogic();

		gtnFrameworkIfpDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testDoAction_availableDeductionBean_isNull() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("notContract").when(baseComponent).getCaptionFromComboBox();

		// getValuesFromPagedDataTable = null
		doReturn(null).when(baseComponent).getValuesFromPagedDataTable();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void testDoAction_availableDeductionBean_isEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfDeductionsTabAddAction fixture = new GtnUiFrameworkNsfDeductionsTabAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn("notContract").when(baseComponent).getCaptionFromComboBox();

		// getValuesFromPagedDataTable = empty (empty bean)
		Set<GtnWsRecordBean> beans = new HashSet<>();
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	@Test
	public void testGtnFrameworkIfpDelete_doAction_getWsResponse() {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUiFrameworkNsfDeductionsTabAddAction gtnUiFrameworkNsfDeductionsTabAddAction = new GtnUiFrameworkNsfDeductionsTabAddAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnUiFrameworkNsfDeductionsTabAddAction.getWsResponse(gtnRequest);
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