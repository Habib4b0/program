package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyClassContants;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnUIFrameWorkCompanyMasterParentCompanyPopupSelectAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("Inside Configure Parameters");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("parentCompanySelectButton").getComponentConfig();

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_RESULT_TABLE_POP_UP);
		actionParameter.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO);

		if (GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO.equals(componentId)) {
			actionParameter.add(Arrays.asList("companyNo", "companyName", "5"));
			actionParameter.add(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO, "parentCompanyName",
					"parentCompanySid"));
		} else {
			actionParameter.add(Arrays.asList("companyNo", "companyName"));
			actionParameter.add(Arrays.asList(componentId,
					componentId.replace(GtnFrameworkCompanyStringContants.PARENT_COMPANY_NO, "parentCompanyName")));
		}
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter("parentCompanyView");
		actionConfigList.add(closeAction);
		if (GtnFrameworkCompanyStringContants.FROM_MAIN_POPUP
				.equals(gtnUIFrameWorkActionConfig.getActionParameterList().get(1))) {
			GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
			customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customAction.addActionParameter(GtnFrameworkCompanyClassContants.COMPANY_PARENT_MANDATORY_CUSTOM);
			actionConfigList.add(customAction);
		}
		selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
