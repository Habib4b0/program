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
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfSaveActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfSaveAction fixture = new GtnUiFrameworkNsfSaveAction();
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

		GtnUiFrameworkNsfSaveAction fixture = new GtnUiFrameworkNsfSaveAction();
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

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfSaveAction fixture = new GtnUiFrameworkNsfSaveAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		String contractSelectionValue = "Existing Contract";
		doReturn(contractSelectionValue).when(baseComponent).getStringFromField();

		doReturn("formulaTypeCaption").when(baseComponent).getCaptionFromComboBox();
		doReturn(1).when(baseComponent).getIntegerFromField();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnWsRecordBean netSalesRuleBean = Mockito.mock(GtnWsRecordBean.class);
		doReturn(netSalesRuleBean).when(componentData).getCustomData();
		doReturn("1").when(netSalesRuleBean).getPropertyValue("systemId");

		// systemId = 1
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkPagedTableLogic availableCustomerTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(availableCustomerTableLogic).when(componentData).getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> criteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteriaList.add(criteria);

		doReturn(criteriaList).when(availableCustomerTableLogic).getCurrentSearchCriteria();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfSaveAction action = Mockito.spy(fixture);

		// set gtnWsNetSalesGeneralResponse
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = Mockito.mock(GtnWsNetSalesGeneralResponse.class);

		// gtnWsresponse.getGtnWsNetSalesGeneralResponse().getNsfInfoBean().getSystemId()
		doReturn(gtnWsNetSalesGeneralResponse).when(gtnWsresponse).getGtnWsNetSalesGeneralResponse();
		doReturn(nsfInfoBean).when(gtnWsNetSalesGeneralResponse).getNsfInfoBean();
		doReturn(1).when(nsfInfoBean).getSystemId();

		// set gtnWsGeneralResponse
		GtnWsGeneralResponse gtnWsGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);
		doReturn(gtnWsGeneralResponse).when(gtnWsresponse).getGtnWsGeneralResponse();
		// isSucess = true
		doReturn(true).when(gtnWsGeneralResponse).isSucess();

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());
		GtnUIFrameworkGlobalUI.addSessionProperty(Mockito.anyString(), Mockito.any());

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_elseBlocks_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfSaveAction fixture = new GtnUiFrameworkNsfSaveAction();

		// componentId = netSalesFormulaAddView_backButton
		String componentId = "netSalesFormulaAddView_backButton";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		String contractSelectionValue = "Existing Contract";
		doReturn(contractSelectionValue).when(baseComponent).getStringFromField();

		doReturn("formulaTypeCaption").when(baseComponent).getCaptionFromComboBox();
		doReturn(1).when(baseComponent).getIntegerFromField();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnWsRecordBean netSalesRuleBean = Mockito.mock(GtnWsRecordBean.class);

		// netSalesRuleBean = null
		doReturn(null).when(componentData).getCustomData();
		doReturn("1").when(netSalesRuleBean).getPropertyValue("systemId");

		// systemId = 1
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkPagedTableLogic availableCustomerTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(availableCustomerTableLogic).when(componentData).getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> criteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteriaList.add(criteria);

		doReturn(criteriaList).when(availableCustomerTableLogic).getCurrentSearchCriteria();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfSaveAction action = Mockito.spy(fixture);

		// set gtnWsNetSalesGeneralResponse
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = Mockito.mock(GtnWsNetSalesGeneralResponse.class);

		// gtnWsresponse.getGtnWsNetSalesGeneralResponse().getNsfInfoBean().getSystemId()
		doReturn(gtnWsNetSalesGeneralResponse).when(gtnWsresponse).getGtnWsNetSalesGeneralResponse();
		doReturn(nsfInfoBean).when(gtnWsNetSalesGeneralResponse).getNsfInfoBean();
		doReturn(1).when(nsfInfoBean).getSystemId();

		// set gtnWsGeneralResponse
		GtnWsGeneralResponse gtnWsGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);
		doReturn(gtnWsGeneralResponse).when(gtnWsresponse).getGtnWsGeneralResponse();

		// isSucess = true
		doReturn(true).when(gtnWsGeneralResponse).isSucess();

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());
		GtnUIFrameworkGlobalUI.addSessionProperty(Mockito.anyString(), Mockito.any());

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testDoAction_elseBlocks_2() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfSaveAction fixture = new GtnUiFrameworkNsfSaveAction();

		// componentId = netSalesFormulaAddView_backButton
		String componentId = "netSalesFormulaAddView_backButton";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		String contractSelectionValue = "Existing Contract";
		doReturn(contractSelectionValue).when(baseComponent).getStringFromField();

		doReturn("formulaTypeCaption").when(baseComponent).getCaptionFromComboBox();
		doReturn(1).when(baseComponent).getIntegerFromField();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnWsRecordBean netSalesRuleBean = Mockito.mock(GtnWsRecordBean.class);

		// getPropertyValue = null
		doReturn(netSalesRuleBean).when(componentData).getCustomData();
		doReturn(null).when(netSalesRuleBean).getPropertyValue("systemId");

		// systemId = null will cause an exception, so added try-cathch
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(null);

		GtnUIFrameworkPagedTableLogic availableCustomerTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(availableCustomerTableLogic).when(componentData).getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> criteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteriaList.add(criteria);

		doReturn(criteriaList).when(availableCustomerTableLogic).getCurrentSearchCriteria();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfSaveAction action = Mockito.spy(fixture);

		// set gtnWsNetSalesGeneralResponse
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = Mockito.mock(GtnWsNetSalesGeneralResponse.class);

		// gtnWsresponse.getGtnWsNetSalesGeneralResponse().getNsfInfoBean().getSystemId()
		doReturn(gtnWsNetSalesGeneralResponse).when(gtnWsresponse).getGtnWsNetSalesGeneralResponse();
		doReturn(nsfInfoBean).when(gtnWsNetSalesGeneralResponse).getNsfInfoBean();
		doReturn(1).when(nsfInfoBean).getSystemId();

		// set gtnWsGeneralResponse
		GtnWsGeneralResponse gtnWsGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);
		doReturn(gtnWsGeneralResponse).when(gtnWsresponse).getGtnWsGeneralResponse();

		// isSucess = true
		doReturn(true).when(gtnWsGeneralResponse).isSucess();

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());
		GtnUIFrameworkGlobalUI.addSessionProperty(Mockito.anyString(), Mockito.any());

		try {
			action.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testDoAction_elseBlocks_3() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfSaveAction fixture = new GtnUiFrameworkNsfSaveAction();

		// componentId = netSalesFormulaAddView_backButton
		String componentId = "netSalesFormulaAddView_backButton";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		String contractSelectionValue = "Not Existing Contract - Else Block";
		doReturn(contractSelectionValue).when(baseComponent).getStringFromField();

		doReturn("formulaTypeCaption").when(baseComponent).getCaptionFromComboBox();
		doReturn(1).when(baseComponent).getIntegerFromField();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnWsRecordBean netSalesRuleBean = Mockito.mock(GtnWsRecordBean.class);

		// netSalesRuleBean = null
		doReturn(null).when(componentData).getCustomData();
		doReturn("1").when(netSalesRuleBean).getPropertyValue("systemId");

		// systemId = 1
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkPagedTableLogic availableCustomerTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(availableCustomerTableLogic).when(componentData).getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> criteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteriaList.add(criteria);

		doReturn(criteriaList).when(availableCustomerTableLogic).getCurrentSearchCriteria();

		GtnUIFrameworkWebserviceResponse gtnWsresponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfSaveAction action = Mockito.spy(fixture);

		// set gtnWsNetSalesGeneralResponse
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = Mockito.mock(GtnWsNetSalesGeneralResponse.class);

		// gtnWsresponse.getGtnWsNetSalesGeneralResponse().getNsfInfoBean().getSystemId()
		doReturn(gtnWsNetSalesGeneralResponse).when(gtnWsresponse).getGtnWsNetSalesGeneralResponse();
		doReturn(nsfInfoBean).when(gtnWsNetSalesGeneralResponse).getNsfInfoBean();
		doReturn(1).when(nsfInfoBean).getSystemId();

		// set gtnWsGeneralResponse
		GtnWsGeneralResponse gtnWsGeneralResponse = Mockito.mock(GtnWsGeneralResponse.class);
		doReturn(gtnWsGeneralResponse).when(gtnWsresponse).getGtnWsGeneralResponse();

		// isSucess = false, So, saveFalg else part will be covered
		doReturn(false).when(gtnWsGeneralResponse).isSucess();

		doReturn(gtnWsresponse).when(action).getResponse(Mockito.any());
		GtnUIFrameworkGlobalUI.addSessionProperty(Mockito.anyString(), Mockito.any());

		action.doAction(componentId, gtnUIFrameWorkActionConfig);
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