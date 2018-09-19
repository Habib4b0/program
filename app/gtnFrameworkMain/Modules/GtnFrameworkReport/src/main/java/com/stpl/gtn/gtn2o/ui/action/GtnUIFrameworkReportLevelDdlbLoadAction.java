/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameworkReportLevelDdlbLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		final GtnUIFrameworkWebserviceRequest generalRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest cvRequest = new GtnWsCustomViewRequest();
		


		String customView = componentId.contains("dataSelectionTab") ? "dataSelectionTab_displaySelectionTabCustomView"
				: "reportingDashboardTab_displaySelectionTabCustomView";

		String selectedItem = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(customView, componentId)
				.getV8StringFromField();

		if (!"".equals(selectedItem) && !"0".equals(selectedItem)) {

			cvRequest.setCvSysId(Integer.parseInt(selectedItem));
			generalRequest.setGtnWsCustomViewRequest(cvRequest);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE + GtnWsCustomViewConstants.CUSTOM_VIEW_LEVEL_DATA,
					generalRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response
					.getGtnUIFrameworkWebserviceComboBoxResponse();

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkReportStringConstants.REPORT_OPTIONS_TAB_UNIT_OF_MEASURE,
							componentId)
					.addAllItemsToComboBox(new ArrayList<>(comboBoxResponse.getItemValueList()),
							new ArrayList<>(comboBoxResponse.getItemCodeList()));
			long count = comboBoxResponse.getItemValueList().stream()
					.filter(str -> str.toLowerCase().contains("variable")).count();

			if (count == 0) {
				GtnUIFrameworkBaseComponent expandButtonBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkReportStringConstants.RD_EXPAND_BUTTON, componentId);
				GtnUIFrameworkBaseComponent collapseButtonBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(GtnFrameworkReportStringConstants.RD_COLLPSE_BUTTON, componentId);

				if (Optional.ofNullable(expandButtonBaseComponent.getComponent()).isPresent()) {
					expandButtonBaseComponent.setComponentEnable(count == 0);
				}
				if (Optional.ofNullable(collapseButtonBaseComponent.getComponent()).isPresent()) {
					collapseButtonBaseComponent.setComponentEnable(count == 0);
				}
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
