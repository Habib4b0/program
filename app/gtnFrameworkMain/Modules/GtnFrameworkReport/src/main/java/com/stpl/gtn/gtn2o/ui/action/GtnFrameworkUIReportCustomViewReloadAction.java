/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkUIReportCustomViewReloadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CUSTOM_VIEW_DATA,
				new GtnUIFrameworkWebserviceRequest(), GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response
				.getGtnUIFrameworkWebserviceComboBoxResponse();
		String withoutViewId = componentId
				.substring(componentId.replaceAll("_displaySelectionTabCustomView", "").lastIndexOf('_') + 1);
		String customView = componentId.contains("reportLandingScreen") ? withoutViewId : componentId;
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(customView).addAllItemsToComboBox(
				new ArrayList<>(comboBoxResponse.getItemValueList()),
				new ArrayList<>(comboBoxResponse.getItemCodeList()));
		if (gtnUIFrameWorkActionConfig.getActionParameterList().size() > 2) {
			int size = comboBoxResponse.getItemValueList().size();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(customView)
					.setHasValue(comboBoxResponse.getItemCodeList().get(size - 1));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
