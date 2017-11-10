package com.stpl.gtn.gtn2o.ui.action.duallistbox;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnFrameworkReturnsDualListBoxRightTableRefreshAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReturnsDualListBoxRightTableRefreshAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info(" inisde GtnFrameworkReturnsDualListBoxRightTableRefreshAction");

		String dualListBoxComponentId = gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString();

		AbstractComponent dualListBoxLayout = GtnUIFrameworkGlobalUI.getVaadinComponent(dualListBoxComponentId);
		GtnUIFrameworkComponentData dualListBoxcomponentData = (GtnUIFrameworkComponentData) dualListBoxLayout
				.getData();

		GtnUIFrameworkDualListBoxComponent gtnUIFrameworkDualListBoxComponent = new GtnUIFrameworkDualListBoxComponent();
		gtnUIFrameworkDualListBoxComponent.resetToDefault(dualListBoxComponentId,
				dualListBoxcomponentData.getCurrentComponentConfig());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
