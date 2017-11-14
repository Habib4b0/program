/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfUriConstants;

/**
 *
 * @author STPL
 */
public class GtnUiFrameworkNsfDeductionsTabFieldFactoryAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		//
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		int systemId = Integer.parseInt(actionParam.getItemId().getProperties()
				.get(actionParam.getItemId().getRecordHeader().size() - 1).toString());
		GtnFrameworkNSFCommonLogic.updateField(actionParam.getPropertyId(), actionParam.getCurrentValue(), false,
				systemId, false, "/" + GtnWsNsfUriConstants.NSF_SERVICE + "/" + GtnWsNsfUriConstants.NS_UPDATE_SERVICE,
				false);
                 if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.getTableComponentId())
					.getLogicFromPagedDataTable().handleCheckBoxOnItem(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
							Boolean.parseBoolean(String.valueOf(actionParam.getCurrentValue())));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
        
		}
