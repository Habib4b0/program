package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkCompanyMasterIdentifierEditListItemClickAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCompanyMasterIdentifierEditListItemClickAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("In Identifier Edit ListAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			String saveButtonCaption;
			List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();
			GtnWsRecordBean recordBean = gtnUIFrameWorkActionConfig.getActionParameter().getItemId();
			boolean isSelected = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("editattachResultTable")
					.getExtPagedTable().isSelected(recordBean);

			if (!isSelected) {
				saveButtonCaption = GtnFrameworkCompanyStringContants.UPDATE_STRING;

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(1))
						.loadFieldValue(String.valueOf(recordBean.getPropertyValueByIndex(0)));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.TWO))
						.loadFieldValue(String.valueOf(recordBean.getPropertyValueByIndex(1)));
				Object defaultValue = GtnFrameworkCompanyStringContants.SELECT_ONE;

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.THREE))
						.loadDefaultComboboxAll(
								Arrays.asList(defaultValue, GtnFrameworkCompanyStringContants.YES,
										GtnFrameworkCompanyStringContants.NO),
								String.valueOf(recordBean.getPropertyValueByIndex(GtnWsNumericConstants.TWO)).isEmpty()
										? GtnFrameworkCompanyStringContants.SELECT_ONE
										: String.valueOf(
												recordBean.getPropertyValueByIndex(GtnWsNumericConstants.TWO)));

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.FOUR))
						.loadFieldValue(
								String.valueOf(recordBean.getPropertyValueByIndex(GtnWsNumericConstants.THREE)));
			} else {
				saveButtonCaption = GtnFrameworkCompanyStringContants.SAVE_STRING;
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(1))
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.TWO))
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				Object defaultValue = GtnFrameworkCompanyStringContants.SELECT_ONE;
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.THREE))
						.loadDefaultComboboxAll(
								Arrays.asList(defaultValue, GtnFrameworkCompanyStringContants.YES,
										GtnFrameworkCompanyStringContants.NO),
								GtnFrameworkCompanyStringContants.SELECT_ONE);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(GtnWsNumericConstants.FOUR))
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}

			GtnUIFrameworkGlobalUI.getVaadinComponent(fieldValues.get(GtnWsNumericConstants.FIVE))
					.setCaption(saveButtonCaption);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnFrameworkCompanyMasterIdentifierEditListItemClickAction();
	}

}
