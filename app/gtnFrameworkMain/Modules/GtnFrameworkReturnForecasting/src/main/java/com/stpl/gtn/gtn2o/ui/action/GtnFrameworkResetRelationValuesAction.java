/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.ui.ComboBox;

/**
 *
 * @author STPL
 */
public class GtnFrameworkResetRelationValuesAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkResetRelationValuesAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info(" info GtnFrameworkResetRelationValuesAction ");

		List<Object> actionParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(actionParameter.get(1).toString(), componentId);
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) componentData.getCustomData();
		dualListBoxBean.clearLeftTableData();
		dualListBoxBean.clearRightTableData();
		if (actionParameter.size() > 2) {

			GtnUIFrameworkDualListBoxConfig gtnUIFrameworkDualListBoxConfig = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameter.get(1).toString(), componentId)
					.getGtnUIFrameworkDualListBoxConfig();
			ComboBox comboBox = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinComponent(actionParameter.get(2).toString(),
					componentId);
			if (comboBox.getValue() != null) {
				gtnUIFrameworkDualListBoxConfig.setLoadingLevel(Integer.valueOf(comboBox.getValue().toString()));
			} else {
				gtnUIFrameworkDualListBoxConfig.setLoadingLevel(0);
			}

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}