package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.Arrays;
import java.util.List;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkItemMasterIdentifirerEditListItemClickAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger
			.getGTNLogger(GtnFrameworkItemMasterIdentifirerEditListItemClickAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("In Identifier Edit ListAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();

			String saveButtonCaption;
			ExtPagedTable<?> table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(0))
					.getExtPagedTable();

			boolean isSelected = table
					.isSelected((GtnWsRecordBean) gtnUIFrameWorkActionConfig.getActionParameter().getItemId());
			GtnWsRecordBean recordBean = gtnUIFrameWorkActionConfig.getActionParameter().getItemId();

			if (!isSelected) {
				saveButtonCaption = "Update";
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(1))
						.loadFieldValue(String.valueOf(recordBean.getPropertyValueByIndex(0)));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(2))
						.loadFieldValue(String.valueOf(recordBean.getPropertyValueByIndex(1)));
				Object defaultDdlbValue = GtnFrameworkCommonConstants.SELECT_ONE;
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(3)).loadDefaultComboboxAll(
						Arrays.asList(defaultDdlbValue, "Yes", "No"),
						String.valueOf(recordBean.getPropertyValueByIndex(2)).isEmpty()
								? GtnFrameworkCommonConstants.SELECT_ONE
								: String.valueOf(recordBean.getPropertyValueByIndex(2)));

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(4)).loadDefaultComboboxAll(
						Arrays.asList(defaultDdlbValue, "Yes", "No"),
						String.valueOf(recordBean.getPropertyValueByIndex(3)).isEmpty()
								? GtnFrameworkCommonConstants.SELECT_ONE
								: String.valueOf(recordBean.getPropertyValueByIndex(3)));

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(5))
						.loadFieldValue(String.valueOf(recordBean.getPropertyValueByIndex(4)));

			} else {
				Object defaultDdlbValue = GtnFrameworkCommonConstants.SELECT_ONE;
				saveButtonCaption = "Save";
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(1))
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(2))
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(3)).loadDefaultComboboxAll(
						Arrays.asList(defaultDdlbValue, "Yes", "No"),
						String.valueOf(GtnFrameworkCommonConstants.SELECT_ONE));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(4)).loadDefaultComboboxAll(
						Arrays.asList(defaultDdlbValue, "Yes", "No"),
						String.valueOf(GtnFrameworkCommonConstants.SELECT_ONE));
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(5))
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			}
			GtnUIFrameworkGlobalUI.getVaadinComponent(fieldValues.get(6)).setCaption(saveButtonCaption);

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
			throw new GtnFrameworkGeneralException(e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
