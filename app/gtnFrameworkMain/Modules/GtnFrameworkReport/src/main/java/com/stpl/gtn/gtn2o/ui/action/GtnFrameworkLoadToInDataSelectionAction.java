package com.stpl.gtn.gtn2o.ui.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkLoadToInDataSelectionAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkLoadToInDataSelectionAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Configure Params..");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
			GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
			String frequency = dataSelectionBean.getFrequencyName();
			dataSelectionBean.setFromOrToForDataSelection("FROM");
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsReportRequest reportRequest = new GtnWsReportRequest();
			reportRequest.setDataSelectionBean(dataSelectionBean);
			request.setGtnWsReportRequest(reportRequest);
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsReportConstants.GTN_REPORT_SERVICE
							+ GtnWsReportConstants.GTN_WS_REPORT_DASHBOARD_LOAD_FROM_AND_TO_IN_DATA_SELECTION,
					"report", request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			String periodAndYearInLandingScreen = gtnUIFrameWorkActionConfig.getActionParameterList().size() > 1
					&& !"null".equals(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
							? gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString()
							: GtnUIFrameworkGlobalUI
									.getVaadinBaseComponentFromParent("reportLandingScreen_fromPeriod", componentId)
									.getStringCaptionFromV8ComboBox();

			periodAndYearInLandingScreen = periodAndYearInLandingScreen.replaceAll(" ", "");

			periodAndYearInLandingScreen = periodAndYearInLandingScreen.replaceAll("-", "");

			List<String> itemValueList1 = response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList();
			List<String> itemCodeList1 = response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList();

			List<String> itemValueList = new ArrayList<>(itemValueList1);
			List<String> itemCodeList = new ArrayList<>(itemCodeList1);

			String stringToBeCompared;

			stringToBeCompared = GtnFrameworkLoadFromInDataSelectionAction.getTheStringToBeCompared(frequency,
					periodAndYearInLandingScreen);

			int range = GtnFrameworkLoadFromInDataSelectionAction.getRangeToBeRemoved(itemValueList,
					stringToBeCompared);
			if (range > 0 && range < itemValueList.size()) {
				itemValueList.subList(0, range).clear();
				itemCodeList.subList(0, range).clear();
			}

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId)
					.addAllItemsToComboBox(itemValueList, itemCodeList);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId)
					.loadV8ComboBoxComponentValue(itemCodeList.get(0));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
