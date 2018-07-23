package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.Arrays;
import java.util.Optional;

public class GtnFrameworkUICustomSelectAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
	     String parentViewId=GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
              GtnUIFrameworkBaseComponent customViewComponent=null;
                if (parentViewId.contains("reportLandingScreen")) {

                    String id = GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_CUSTOM_VIEW;
                    customViewComponent= GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id);
                    setCustomValue(customViewComponent);
                } else {
                    String[] idsToReload = new String[]{"reportingDashboardTab_displaySelectionTabCustomView", "dataSelectionTab_displaySelectionTabCustomView"};
                    for (String component : idsToReload) {

                        String id = parentViewId + "_" + component;
                        customViewComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id);
                        if (customViewComponent != null && customViewComponent.getComponent() != null) {
                           setCustomValue(customViewComponent);
                        }
                    }
                }
	}

    private void setCustomValue(GtnUIFrameworkBaseComponent customViewComponent) {
        GtnUIFrameworkComponentData data= (GtnUIFrameworkComponentData)
                Optional.ofNullable(customViewComponent.getComponent().getData()).orElse(new GtnUIFrameworkComponentData());
        customViewComponent.setHasValue(String.valueOf(data.getCustomData()));
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}