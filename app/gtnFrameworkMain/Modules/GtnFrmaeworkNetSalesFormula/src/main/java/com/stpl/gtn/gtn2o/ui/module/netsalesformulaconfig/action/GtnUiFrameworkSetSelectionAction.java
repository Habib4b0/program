package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.priceschedule.bean.GtnUIFrameworkPopUpBean;

public class GtnUiFrameworkSetSelectionAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnUIFrameWorkActionConfig.getActionParameterList();
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkPopUpBean evoluationRulePopUpSearchViewBean = new GtnUIFrameworkPopUpBean(
				"CDRPopUpSearchSearchView");
		evoluationRulePopUpSearchViewBean.setSourceTableName("cDRPopUpsearchResultTable");
		evoluationRulePopUpSearchViewBean.setDestinationComponentId(componentId);
		evoluationRulePopUpSearchViewBean.setSourcePropertyIdList(Arrays.asList( "ruleName" ));
		evoluationRulePopUpSearchViewBean.setDestinaComponentIdList(Arrays.asList( componentId ));
		GtnUIFrameworkComponentConfig selectButtonComponentConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("CDRPopUpSearchSearchViewSelectButton").getComponentConfig();

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(evoluationRulePopUpSearchViewBean.getSourceTableName());
		actionParameter.add(evoluationRulePopUpSearchViewBean.getDestinationComponentId());
		actionParameter.add(evoluationRulePopUpSearchViewBean.getSourcePropertyIdList());
		actionParameter.add(evoluationRulePopUpSearchViewBean.getDestinaComponentIdList());

		selectAction.setActionParameterList(actionParameter);
		selectActionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.setActionParameterList(Arrays.asList(new Object[] { "CDRPopUpSearchSearchView" }));
		selectActionConfigList.add(closeAction);

		selectButtonComponentConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
