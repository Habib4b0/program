/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.commercial.action.duallistbox;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.commercial.constants.GtnFrameworkCommercialForecastingClassConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.ui.AbstractComponent;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnUIFrameworkCommercialDualListBoxConfigAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
			if (actionParametersList.get(1).equals("ResetLeftTableHeaders")) {
				reloadDualListBoxLeftTableHeaders(actionParametersList,
						GtnFrameworkCommercialForecastingClassConstants.COMMERCIAL_FORECASTING_DUALLIST_CONFIG_ACTION,
						componentId);
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException(
					"Error in GtnUIFrameworkReturnsDualListBoxConfigAction.class - doAction method.", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private void reloadDualListBoxLeftTableHeaders(List<Object> actionParametersList, String actionType,
			String sourceComponentId) throws GtnFrameworkValidationFailedException {
		List<?> componentReloadInputList = configureVisibleColumnsBasedOnLevel(actionParametersList.get(3), actionType,
				sourceComponentId);
		String componentId = actionParametersList.get(2).toString();
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(componentId, sourceComponentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) componentData.getCustomData();
		GtnUIFrameworkDualListBoxComponent dualListBoxComponent = dualListBoxBean
				.getGtnUIFrameworkDualListBoxComponent();
		dualListBoxComponent.reloadComponent(null, null,
				GtnUIFrameworkGlobalUI.getGeneratedComponentId(componentId, sourceComponentId),
				componentReloadInputList);
	}

	private List<Object> configureVisibleColumnsBasedOnLevel(Object componentId, String action,
			String sourceComponentId) throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId.toString(), sourceComponentId);

		List<Object> leftVisibleColumns = new ArrayList<>();
		List<String> leftHeaders = new ArrayList<>();
		List<Object> componentReloadInputList = new ArrayList<>();

		componentReloadInputList.add(action);
		componentReloadInputList.add(leftVisibleColumns);
		componentReloadInputList.add(leftHeaders);

		if (gtnUIFrameworkBaseComponent.getObjectFromField() != null) {
			String value = gtnUIFrameworkBaseComponent.getCaptionFromComboBox();
			String[] valueDetails = value.split("-");
			String levelName = valueDetails[1].trim();
			if ("Segment".equalsIgnoreCase(levelName)) {
				leftVisibleColumns.add("levelValue");
				leftHeaders.add("Segment");
			} else if ("Market Type".equalsIgnoreCase(levelName)) {
				leftVisibleColumns.add("levelValue");
				leftHeaders.add("Market Type");
			} else if ("Contract".equalsIgnoreCase(levelName)) {
				leftVisibleColumns.add("levelValue");
				leftHeaders.add("Contract");
			} else if ("Trading Partner".equalsIgnoreCase(levelName)) {
				leftVisibleColumns.add("levelValue");
				leftHeaders.add("Trading Partner");
			}
			return componentReloadInputList;
		}
		leftVisibleColumns.add("levelValue");
		leftHeaders.add("Level");
		return componentReloadInputList;
	}

}
