package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameworkSizeValidator implements GtnUIFrameworkValidator {

	@Override
	public void validate(String componentId, String filedId,
			GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {

		if (gtnUIFrameworkValidationConfig != null && gtnUIFrameworkValidationConfig.getMinSize() != 0) {

			List<GtnWsRecordBean> ruleDetailsList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(filedId, componentId)
					.getItemsFromDataTable();
			if (ruleDetailsList.size() >= gtnUIFrameworkValidationConfig.getMinSize()) {
				return;
			}
			throw new GtnFrameworkValidationFailedException("Size validation Failed");

		}

	}

	@Override
	public GtnUIFrameworkValidator createInstance() {
		return new GtnUIFrameworkSizeValidator();
	}

}