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
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class })
public class GtnUiFrameworkNsfLoadDataActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfLoadDataAction fixture = new GtnUiFrameworkNsfLoadDataAction();
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

		GtnUiFrameworkNsfLoadDataAction fixture = new GtnUiFrameworkNsfLoadDataAction();
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
	public void testGtnUiFrameworkNsfLoadData_doAction_if() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfLoadDataAction fixture = new GtnUiFrameworkNsfLoadDataAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = true
		gtnWsGeneralResponse.setSucess(true);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setFormulaId("formulaId");
		nsfInfoBean.setFormulaNo("formulaNo");
		nsfInfoBean.setFormulaName("formulaName");
		nsfInfoBean.setFormulaType(1);

		GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = new GtnWsNetSalesGeneralResponse();
		gtnWsNetSalesGeneralResponse.setNsfInfoBean(nsfInfoBean);
		gtnWsresponse.setGtnWsNetSalesGeneralResponse(gtnWsNetSalesGeneralResponse);

		GtnUiFrameworkNsfLoadDataAction gtnUiFrameworkNsfLoadDataAction = Mockito.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfLoadDataAction).getWsResponse(Mockito.any());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnUIFrameworkComponentData selectedDeductionsComponentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString()))
				.thenReturn(selectedDeductionsComponentData);

		GtnUIFrameworkPagedTableLogic selectedDeductionsTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(selectedDeductionsTableLogic).when(selectedDeductionsComponentData).getCurrentPageTableLogic();

		gtnUiFrameworkNsfLoadDataAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfLoadData_doAction_else() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfLoadDataAction fixture = new GtnUiFrameworkNsfLoadDataAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = true
		gtnWsGeneralResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setFormulaId("formulaId");
		nsfInfoBean.setFormulaNo("formulaNo");
		nsfInfoBean.setFormulaName("formulaName");
		nsfInfoBean.setFormulaType(1);

		GtnWsNetSalesGeneralResponse gtnWsNetSalesGeneralResponse = new GtnWsNetSalesGeneralResponse();
		gtnWsNetSalesGeneralResponse.setNsfInfoBean(nsfInfoBean);
		gtnWsresponse.setGtnWsNetSalesGeneralResponse(gtnWsNetSalesGeneralResponse);

		GtnUiFrameworkNsfLoadDataAction gtnUiFrameworkNsfLoadDataAction = Mockito.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfLoadDataAction).getWsResponse(Mockito.any());

		gtnUiFrameworkNsfLoadDataAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfLoadData_doAction_catchBlock() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);

		GtnUiFrameworkNsfLoadDataAction fixture = new GtnUiFrameworkNsfLoadDataAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// not bean has initialized, so below mentioned line will cause an error
		// nsfBean =
		// gtnWsresponse.getGtnWsNetSalesGeneralResponse().getNsfInfoBean();

		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(1);

		GtnUIFrameworkWebserviceResponse gtnWsresponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();

		// gtnWsresponse.getGtnWsGeneralResponse().isSucess() = false
		gtnWsGeneralResponse.setSucess(false);
		gtnWsresponse.setGtnWsGeneralResponse(gtnWsGeneralResponse);

		GtnUiFrameworkNsfLoadDataAction gtnUiFrameworkNsfLoadDataAction = Mockito.spy(fixture);
		doReturn(gtnWsresponse).when(gtnUiFrameworkNsfLoadDataAction).getWsResponse(Mockito.any());

		try {
			gtnUiFrameworkNsfLoadDataAction.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGtnUiFrameworkNsfLoadData_doAction_getWsResponse() {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUiFrameworkNsfLoadDataAction gtnUiFrameworkNsfLoadDataAction = new GtnUiFrameworkNsfLoadDataAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnUiFrameworkNsfLoadDataAction.getWsResponse(gtnRequest);
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