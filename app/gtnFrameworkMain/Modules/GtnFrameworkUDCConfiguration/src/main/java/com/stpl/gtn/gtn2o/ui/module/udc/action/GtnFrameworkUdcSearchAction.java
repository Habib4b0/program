package com.stpl.gtn.gtn2o.ui.module.udc.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkUdcSearchAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkUdcSearchAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering into GtnFrameworkUdcSearchAction doAction");
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		String udcCategory = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getCaptionFromComboBox();
		if (udcCategory.equals("BRAND")) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.VALUE_LAYOUT).setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_BRAND_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_LAYOUT).setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_BRAND_EXCEL_BUTTON_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_EXCEL_BUTTON_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE_LAYOUT)
					.setVisible(true);
			loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.VALUE_LAYOUT).setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_BRAND_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_LAYOUT).setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_BRAND_EXCEL_BUTTON_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_EXCEL_BUTTON_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_RESULT_TABLE_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_RESULT_TABLE);
			if (!udcCategory.equals("ALL")) {
				loadDataTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.UDC_CATEGORY));
			}
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
