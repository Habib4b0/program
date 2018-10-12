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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
		GtnFrameworkNSFCommonLogic.class })
public class GtnUiFrameworkNsfSalesBasisAddActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfSalesBasisAddAction fixture = new GtnUiFrameworkNsfSalesBasisAddAction();
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

		GtnUiFrameworkNsfSalesBasisAddAction fixture = new GtnUiFrameworkNsfSalesBasisAddAction();
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
	public void testGtnUiFrameworkNsfSalesBasisAdd_doAction_isSucess_true() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfSalesBasisAddAction fixture = new GtnUiFrameworkNsfSalesBasisAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);

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

		doReturn(propertyList).when(valueFromPagedDataTable).getProperties();

		Set<GtnWsRecordBean> beans = new HashSet<>();
		beans.add(valueFromPagedDataTable);
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnUiFrameworkNsfSalesBasisAddAction action = Mockito.spy(fixture);

		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		// isSucess = true
		generalResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(generalResponse);

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSalesBasisAdd_doAction_isSucess_false() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,
				GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfSalesBasisAddAction fixture = new GtnUiFrameworkNsfSalesBasisAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);

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

		doReturn(propertyList).when(valueFromPagedDataTable).getProperties();

		Set<GtnWsRecordBean> beans = new HashSet<>();
		beans.add(valueFromPagedDataTable);
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnUiFrameworkNsfSalesBasisAddAction action = Mockito.spy(fixture);

		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		// isSucess = false
		generalResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(generalResponse);

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfSalesBasisAdd_doAction_availableCustomerBean_isEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfSalesBasisAddAction fixture = new GtnUiFrameworkNsfSalesBasisAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// availableCustomerBean = empty -> because bean is empty 
		Set<GtnWsRecordBean> beans = new HashSet<>();
		doReturn(beans).when(baseComponent).getValuesFromPagedDataTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnUiFrameworkNsfSalesBasisAddAction action = Mockito.spy(fixture);

		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		// isSucess = false
		generalResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(generalResponse);

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());

		try {
			action.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGtnUiFrameworkNsfSalesBasisAdd_doAction_availableCustomerBean_isNull() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfSalesBasisAddAction fixture = new GtnUiFrameworkNsfSalesBasisAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// availableCustomerBean = null
		doReturn(null).when(baseComponent).getValuesFromPagedDataTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("sessionId");

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnUiFrameworkNsfSalesBasisAddAction action = Mockito.spy(fixture);

		GtnWsGeneralResponse generalResponse = new GtnWsGeneralResponse();
		// isSucess = false
		generalResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(generalResponse);

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());

		try {
			action.doAction(componentId, gtnUIFrameWorkActionConfig);
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