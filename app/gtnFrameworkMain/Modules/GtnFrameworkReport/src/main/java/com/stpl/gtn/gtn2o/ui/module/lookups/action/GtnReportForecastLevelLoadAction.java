package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnReportForecastLevelLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportForecastLevelLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String relationshipValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
				.getStringCaptionFromV8ComboBox();
		if (relationshipValue != "-Select one-") {
			int hierarchyVersion = Integer.parseInt(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(1).toString(), componentId)
							.getStringCaptionFromV8ComboBox());

			GtnUIFrameworkComponentData hierarchyComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(actionParamsList.get(2).toString(), componentId);
			GtnWsRecordBean recordBean = (GtnWsRecordBean) hierarchyComponentData.getCustomData();
			gtnLogger.info("Properties size ----->" + recordBean.getProperties().size());
			gtnLogger.info("hierarchyDefSid------->"
					+ recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1));
			int hierarchyDefSid = Integer.parseInt(
					String.valueOf(recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1)));

			GtnUIFrameworkComboBoxConfig relationComboboxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(3).toString(), componentId).getComponentConfig()
					.getGtnComboboxConfig();
			relationComboboxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
					+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			relationComboboxConfig.setComboBoxType(GtnFrameworkForecastConstantCommon.REPORT_FORECAST_LEVEL);

			GtnUIFrameworkComboBoxComponent combobox = new GtnUIFrameworkComboBoxComponent();
			combobox.reloadComponent(GtnUIFrameworkActionType.V8_VALUE_CHANGE_ACTION,
					gtnUIFrameWorkActionConfig.getActionParameterList().get(3).toString(), componentId,
					Arrays.asList(hierarchyDefSid, hierarchyVersion));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
