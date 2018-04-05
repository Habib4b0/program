package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentListForInvalidBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.config.GtnFrameworkTransactionComponentConfig;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnFrameworkTransactionInvalidTableName;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnFrameworkTransactionTableName;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import java.util.Locale;

public class GtnUIFrameworkTransactionInvalidIntegrationLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Map<String, GtnUIFrameworkTransactionComponentListForInvalidBean> moduleComponentMap = new HashMap<>();

		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();

		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(9)).getComponentData()
				.getSharedPopupData() == null) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(9)).getComponentData()
					.setSharedPopupData(moduleComponentMap);
		}
		String moduleName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getCaptionFromComboBox();
		moduleName = GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(moduleName) ? "Invalid Integration"
				: moduleName;
		String tableName = getInvalidName(moduleName);
		boolean isVisible = !"InvalidIntegration".equals(tableName);
		moduleComponentMap = (Map<String, GtnUIFrameworkTransactionComponentListForInvalidBean>) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(fieldValues.get(9)).getComponentData().getSharedPopupData();

		GtnFrameworkTransactionComponentConfig config = new GtnFrameworkTransactionComponentConfig();
		          if (moduleComponentMap.containsKey(moduleName)) {
                GtnUIFrameworkTransactionComponentListForInvalidBean bean = moduleComponentMap.get(moduleName);
                getComponentForInvalidModules(bean, fieldValues, tableName, isVisible);
            } else {
                GtnUIFrameworkTransactionComponentListForInvalidBean bean = new GtnUIFrameworkTransactionComponentListForInvalidBean();
                config.getComponentsForModules(
                        GtnFrameworkTransactionTableName.valueOf(moduleName.replace(' ',
                                '_').toUpperCase(Locale.ENGLISH)).getTableName(),
                        true, null, null, tableName, bean, moduleName);
                getComponentForInvalidModules(bean, fieldValues, tableName, isVisible);
                moduleComponentMap.put(moduleName, bean);
            }

	}

	private void getComponentForInvalidModules(GtnUIFrameworkTransactionComponentListForInvalidBean bean,
			List<String> fieldValues, String tableName, boolean isVisible) throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(0), bean.getStaticComponent1List());

		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(1), bean.getStaticComponent2List());

		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(2), bean.getSearchComponentList());

		GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(isVisible, Arrays.asList(fieldValues.get(3)),
				fieldValues.get(4));
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkCommonStringConstants.MODULE_NAME, tableName);
		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(5), bean.getListViewComponentList());

		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(6), bean.getSearchAndResetComponentList());

		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(7), bean.getReprocessAndRemoveComponentList());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(8)).getComponentData().setCustomData(bean);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldValues.get(4)).getComponentData()
				.setSharedPopupData(new GtnWSTransactionTableCheckAllBean());
		GtnUIFrameworkGlobalUI.addChildComponent(fieldValues.get(10), bean.getExcelButtonComponentList());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

	private String getInvalidName(String moduleName) {
		String tableName;
		if ("Invalid Integration".equals(moduleName)) {
			tableName = GtnFrameworkTransactionInvalidTableName.valueOf("SELECT_ONE").getTableName();
		} else {
			tableName = GtnFrameworkTransactionInvalidTableName.valueOf(moduleName
					.replace(' ', '_')
					.toUpperCase(Locale.ENGLISH)).getTableName();

		}

		return tableName;
	}

}
