/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.math.BigDecimal;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author deepika.krishnakumar
 */
public class GtnUIFrameworkFromToBlurAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<String> fieldValueList = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

		for (String textFields : fieldValueList) {
			String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textFields).getStringFromField()
					.replaceAll(",", "");
			if (!value.isEmpty() && value.matches(GtnFrameworkCommonStringConstants.REGEX_FOR_INTEGER)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textFields)
						.loadFieldValue(new BigDecimal(value).setScale(2, BigDecimal.ROUND_DOWN));
			}
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
