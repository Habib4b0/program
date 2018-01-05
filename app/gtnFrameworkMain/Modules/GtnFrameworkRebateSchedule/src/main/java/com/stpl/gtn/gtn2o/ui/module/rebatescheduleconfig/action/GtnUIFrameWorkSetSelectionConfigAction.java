
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameworkPopUpBean;

public class GtnUIFrameWorkSetSelectionConfigAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnUIFrameWorkActionConfig.getActionParameterList();
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();
		if (componentId.equals("evaluationRuleAssociation") || componentId.equals("calculationRule")) {

			getSelectedPopUp(configProvider, componentId, "CDRPopUpSearchSearchView", "cDRPopUpsearchResultTable",
					"ruleName", "CDRPopUpSearchSearchView", "CDRPopUpSearchSearchViewSelectButton");

		} else {
			getSelectedPopUp(configProvider, componentId, GtnFrameworkRSConstants.REBATE_SCHEDULE_POP_UP_CONFIG,
					"rsPopupSearchResultTable", "rebateScheduleName",
					GtnFrameworkRSConstants.REBATE_SCHEDULE_POP_UP_CONFIG, "rsPopUpViewSelectButton");

			GtnUIFrameworkView view = (GtnUIFrameworkView) GtnUIFrameworkGlobalUI
					.getVaadinComponent(GtnFrameworkRSConstants.REBATE_SCHEDULE_POP_UP_CONFIG);
			view.buildScreen();
		}

	}

	private void getSelectedPopUp(GtnFrameworkComponentConfigProvider configProvider, String componentId,
			String viewName, String sourceTableName, String sourcePropertyId, String actionParameter,
			String popupSelectBtn) {
		GtnUIFrameworkPopUpBean popUpSearchViewBean = new GtnUIFrameworkPopUpBean(viewName);
		popUpSearchViewBean.setSourceTableName(sourceTableName);
		popUpSearchViewBean.setDestinationComponentId(componentId);
		popUpSearchViewBean.setSourcePropertyIdList(Arrays.asList(sourcePropertyId));
		popUpSearchViewBean.setDestinaComponentIdList(Arrays.asList(componentId));
		GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(popupSelectBtn).getComponentConfig();
		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(popUpSearchViewBean.getSourceTableName());
		actionParameterList.add(popUpSearchViewBean.getDestinationComponentId());
		actionParameterList.add(popUpSearchViewBean.getSourcePropertyIdList());
		actionParameterList.add(popUpSearchViewBean.getDestinaComponentIdList());
		selectAction.setActionParameterList(actionParameterList);
		selectActionConfigList.add(selectAction);
		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(actionParameter);
		selectActionConfigList.add(closeAction);
		selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
