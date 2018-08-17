package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.registry.action.pagedtreetable.GtnFrameworkConfigureRightTableHeaderForPTTCompoAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import java.util.List;

public class GtnFrameworkReportDashBoardRightHeaderRequestAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	{

	}
	private final GtnWSLogger gtnWsLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkConfigureRightTableHeaderForPTTCompoAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnWsLogger.info(" inside GtnFrameworkConfigureRightTableHeaderForPTTCompoAction");

		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		gtnUIFrameworkWebserviceRequest.setGtnWsReportRequest(new GtnWsReportRequest());

		GtnWsReportDashboardBean reportDashBoardBean = new GtnWsReportDashboardBean();

		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentData()
				.getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = ((GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData());

		gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().setGtnWsReportDashboardBean(reportDashBoardBean);
		gtnUIFrameworkWebserviceRequest.getGtnWsReportRequest().setDataSelectionBean(dataSelectionBean);

		String[] selectedVariable = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariable", componentId)
				.getStringFromMultiselectComboBox();

		reportDashBoardBean.setSelectedVariableType(selectedVariable);
		String freName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabFrequency", componentId)
				.getStringCaptionFromV8ComboBox();
		reportDashBoardBean.setSelectFreqString(freName);

		String[] selectedVariableCategory = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabVariableCategory", componentId)
				.getStringFromMultiselectComboBox();

		GtnUIFrameworkBaseComponent variableVarianceComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
				"reportingDashboardTab_reportOptionsTabVariableAndVarianceSequencing", componentId);

		if (variableVarianceComponent.getComponent() != null) {
			int value = (int) variableVarianceComponent.getFieldValue();
			if (value == 1) {
				reportDashBoardBean.setVariablesVariances(true);
			}
			int headerSequence = (int) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("reportingDashboardTab_reportOptionsTabHeaderSequencing", componentId)
					.getFieldValue();
			reportDashBoardBean.setHeaderSequence(headerSequence);
		}

		GtnUIFrameworkBaseComponent perioFromComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeFrom", componentId);

		GtnUIFrameworkBaseComponent periodToComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabPeriodRangeTo", componentId);

		reportDashBoardBean.setPeriodRangeFromSid(perioFromComponent.getIntegerFromV8ComboBox());
		if (reportDashBoardBean.getPeriodRangeFromSid() != 0) {
			reportDashBoardBean
					.setPeriodStart(perioFromComponent.getStringCaptionFromV8ComboBox().replaceAll(" - ", " "));
		} else {
			reportDashBoardBean.setPeriodRangeFromSid(perioFromComponent.getNthIntegerFromV8ComboBox(1));
			reportDashBoardBean
					.setPeriodStart(perioFromComponent.getNthStringCaptionFromV8ComboBox(1).replaceAll(" - ", " "));
		}

		reportDashBoardBean.setPeriodRangeToSid(periodToComponent.getIntegerFromV8ComboBox());
		if (reportDashBoardBean.getPeriodRangeToSid() != 0) {
			reportDashBoardBean.setPeriodTo(periodToComponent.getStringCaptionFromV8ComboBox().replaceAll(" - ", " "));
		} else {
			reportDashBoardBean.setPeriodRangeToSid(
					periodToComponent.getNthIntegerFromV8ComboBox(periodToComponent.totalItemsInComboBox() - 1));
			reportDashBoardBean.setPeriodTo(
					periodToComponent.getNthStringCaptionFromV8ComboBox(periodToComponent.totalItemsInComboBox() - 1)
							.replaceAll(" - ", " "));
		}

		reportDashBoardBean.setSelectedVariableCategoryType(selectedVariableCategory);

		reportDashBoardBean.setCustomViewMasterSid(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboardTab_displaySelectionTabCustomView", componentId)
				.getIntegerFromV8ComboBox());
		GtnUIFrameworkBaseComponent comparisonBasis = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportingDashboard_displaySelectionTabComparisonBasis", componentId);
		int id = comparisonBasis.getIntegerFromV8ComboBox();
		String comparisonBasisValue = id > 2 ? "Projection_" + (id - 2)
				: comparisonBasis.getStringCaptionFromV8ComboBox();

		reportDashBoardBean.setComparisonBasis(comparisonBasisValue);
                
                GtnUIFrameworkComponentData comparisonProjectionData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("reportingDashboardTab_reportingDashboardComparisonConfig", componentId);
		List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList = (List<GtnReportComparisonProjectionBean>) comparisonProjectionData
				.getCustomData();
                reportDashBoardBean.setComparisonProjectionBeanList(comparisonProjectionBeanList);
		GtnWsForecastRequest gtnWsForecastRequest = new GtnWsForecastRequest();
		gtnWsForecastRequest.setGtnForecastBean(gtnForecastBean);
		gtnUIFrameworkWebserviceRequest.setGtnWsForecastRequest(gtnWsForecastRequest);

		GtnUIFrameworkComponentData resultTableComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				gtnUIFrameWorkActionConfig.getActionParameterList().get(0).toString(), componentId);
		resultTableComponentData.setCustomPagedTreeTableRequest(gtnUIFrameworkWebserviceRequest);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}