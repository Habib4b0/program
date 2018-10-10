package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUiFrameworkNsfDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParemeterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String viewId = (String) actionParemeterList.get(1);
		GtnWsRecordBean editRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(viewId + "netSalesSearchResultTable").getValueFromPagedDataTable();

		int sysId = (Integer) editRecordBean.getPropertyValue(GtnFrameworkNSFConstants.getSystemid());
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setSystemId(sysId);
		if (getValueFromService(nsfInfoBean)) {
			GtnUIFrameWorkActionConfig loadTableActionConfig = new GtnUIFrameWorkActionConfig();
			loadTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
			List<Object> actionParams = new ArrayList<>();
			actionParams.add(viewId + "netSalesSearchResultTable");

			loadTableActionConfig.setActionParameterList(actionParams);
			loadTableActionConfig.setFieldValues(Arrays.asList(viewId + "formulaType", viewId + "formulaId",
					viewId + "formulaNo", viewId + "formulaName"));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadTableActionConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean getValueFromService(GtnUIFrameworkNsfInfoBean nsfInfoBean) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		try {

			GtnWsNetSalesFormulaGeneralRequest gtnWsNetSalesGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();
			gtnWsNetSalesGeneralRequest.setnSfInfoBean(nsfInfoBean);
			request.setGtnWsNetSalesGeneralRequest(gtnWsNetSalesGeneralRequest);

			GtnUIFrameworkWebserviceResponse gtnWsresponse = getResponse(request);
			return gtnWsresponse.getGtnWsGeneralResponse().isSucess();

		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException("Load Error", systemExcption);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	public GtnUIFrameworkWebserviceResponse getResponse(GtnUIFrameworkWebserviceRequest request) {
		return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_DELETE_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		 
	}

}
