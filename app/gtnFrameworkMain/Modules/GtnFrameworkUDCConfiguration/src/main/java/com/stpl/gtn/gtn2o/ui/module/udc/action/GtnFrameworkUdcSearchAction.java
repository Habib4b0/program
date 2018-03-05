package com.stpl.gtn.gtn2o.ui.module.udc.action;

import java.util.Arrays;

import org.asi.ui.extfilteringtable.ExtCustomTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
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
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_FILETYPELAYOUT)
			.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_FILETYPE_FIELDLAYOUT)
			.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_FILETYPE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_EXCEL_BUTTON_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_FILETYPE_EXCEL_BUTTON_LAYOUT)
			.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_FILETYPE_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE_LAYOUT)
					.setVisible(true);
			loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
		}
		else if (udcCategory.equals(GtnFrameworkCommonConstants.FILE_TYPE)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.VALUE_LAYOUT).setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_BRAND_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_FILETYPELAYOUT)
			.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_FILETYPE_FIELDLAYOUT)
			.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_FILETYPE_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_LAYOUT).setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_BRAND_EXCEL_BUTTON_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_FILETYPE_EXCEL_BUTTON_LAYOUT)
			.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_EXCEL_BUTTON_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_FILETYPE_RESULT_TABLE_LAYOUT)
					.setVisible(true);
			loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_FILETYPE_RESULT_TABLE);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.VALUE_LAYOUT).setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_BRAND_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_FILETYPELAYOUT)
			.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_CATEGORY_FILETYPE_FIELDLAYOUT)
			.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_LAYOUT).setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_BRAND_EXCEL_BUTTON_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_FILETYPE_EXCEL_BUTTON_LAYOUT)
			.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.ADD_FILETYPE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.GTN_EXCEL_BUTTON_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_RESULT_TABLE_LAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.UDC_FILETYPE_RESULT_TABLE_LAYOUT)
					.setVisible(false);
			loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_RESULT_TABLE);
			if (!udcCategory.equals("-ALL-")) {
				checkCondition(loadDataTableActionConfig, udcCategory);
			}
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
		}
	}

	private void checkCondition(GtnUIFrameWorkActionConfig loadDataTableActionConfig, String udcCategory) {
		if (!udcCategory.trim().equals("") && !udcCategory.equals(GtnFrameworkCommonConstants.FILE_TYPE)) {
			loadDataTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.UDC_CATEGORY));
		} else {
			clearTableRecords(GtnFrameworkCommonConstants.UDC_RESULT_TABLE);
			clearTableRecords(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE);
			clearTableRecords(GtnFrameworkCommonConstants.UDC_FILETYPE_RESULT_TABLE);
		}
	}

	private void clearTableRecords(String tableName) {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(tableName);
		if (componentData.getCustomData() instanceof ExtCustomTable) {
			ExtCustomTable table = (ExtCustomTable) componentData.getCustomData();
			table.removeAllItems();
			table.setContainerDataSource(null);
			table.refreshRowCache();
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
