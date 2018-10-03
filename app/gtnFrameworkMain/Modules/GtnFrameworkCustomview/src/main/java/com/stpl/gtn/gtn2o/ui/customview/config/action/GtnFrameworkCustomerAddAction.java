/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCustomerAddAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		if (GtnUIFrameworkGlobalUI.getSessionProperty("mode") == null) {
			GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Add");
			GtnUIFrameworkGlobalUI.addSessionProperty("customSid", 0);
			GtnUIFrameworkGlobalUI.addSessionProperty("customViewBean", "");
		}

		Optional.ofNullable(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).ifPresent(e -> {
			if (!GtnUIFrameworkGlobalUI.getSessionProperty("mode").toString().equalsIgnoreCase("edit")) {
				GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Add");
				GtnUIFrameworkGlobalUI.addSessionProperty("customSid", 0);
				GtnUIFrameworkGlobalUI.addSessionProperty("customViewBean", "");
			}
		});
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent cvTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(2).toString());
		GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString());
		Object customerLevelValue = table.getValueFromComponent();
		Object treeValue = cvTreeBaseComponent.getValueFromComponent();
		String prodOrDedHierarchy = table.getComponentId().contains(GtnFrameworkCVConstants.PRODUCT_LEVEL)
				? "Product Hierarchy"
				: "Deduction Hierarchy";
		String hierarchyName = table.getComponentId().contains(GtnFrameworkCVConstants.CUSTOMER_LEVEL)
				? "Customer Hierarchy"
				: prodOrDedHierarchy;
		if (customerLevelValue == null) {
			GtnUIFrameworkGlobalUI.showMessageBox(GtnFrameworkCVConstants.NO_LEVEL_SELECTED,
					GtnUIFrameworkActionType.ALERT_ACTION, GtnFrameworkCVConstants.NO_LEVEL_SELECTED,
					"Please select a " + hierarchyName + " level to move");
			return;
		}
		if (!cvTreeBaseComponent.getAllTreeNodes().isEmpty() && treeValue == null) {
			GtnUIFrameworkGlobalUI.showMessageBox(GtnFrameworkCVConstants.NO_LEVEL_SELECTED,
					GtnUIFrameworkActionType.ALERT_ACTION, "No Parent Level Selected", "Please select parent node");
			return;
		}
		GtnWsRecordBean selectedCustomerBean = (GtnWsRecordBean) customerLevelValue;
		GtnWsRecordBean selectedParentBean = (GtnWsRecordBean) treeValue;
		if (selectedParentBean != null && !cvTreeBaseComponent.getAllTreeNodes().isEmpty()
				&& !isValidTree(customerLevelValue, String.valueOf(selectedCustomerBean.getPropertyValueByIndex(3)),
						treeValue, cvTreeBaseComponent)) {
			GtnUIFrameworkGlobalUI.showMessageBox(GtnFrameworkCVConstants.NO_LEVEL_SELECTED,
					GtnUIFrameworkActionType.ALERT_ACTION, "Invalid Structure",
					"You cannot add " + selectedCustomerBean.getPropertyValueByIndex(0) + " as child to "
							+ selectedParentBean.getPropertyValueByIndex(0));
			return;
		}
		if (selectedParentBean != null && !cvTreeBaseComponent.getAllTreeNodes().isEmpty()
				&& !cvTreeBaseComponent.getChildNodes(selectedParentBean).isEmpty()) {
			GtnUIFrameworkGlobalUI.showMessageBox(GtnFrameworkCVConstants.NO_LEVEL_SELECTED,
					GtnUIFrameworkActionType.ALERT_ACTION, "Invalid Structure",
					"You cannot add " + selectedCustomerBean.getPropertyValueByIndex(0) + " as child to "
							+ selectedParentBean.getPropertyValueByIndex(0));
			return;
		}
		table.removeItemFromDataTable(customerLevelValue);
		selectedCustomerBean.setAdditionalProperties(
				selectedCustomerBean.getProperties().subList(1, selectedCustomerBean.getProperties().size()));
		selectedCustomerBean.setProperties(Arrays.asList(selectedCustomerBean.getProperties().get(0)));
		selectedCustomerBean.setRecordHeader(Arrays.asList(GtnFrameworkCVConstants.LEVEL_NAME));
		if (!cvTreeBaseComponent.getAllTreeNodes().isEmpty()) {
			cvTreeBaseComponent.addItemToTreeDataTable(treeValue, selectedCustomerBean, true);
			cvTreeBaseComponent.expandTreeItem(treeValue);
		} else {
			cvTreeBaseComponent.addItemToTreeDataTable(selectedCustomerBean, true);
		}
		table.setTableValue(null);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private boolean isValidTree(Object movingItem, String hierarchyIndicator, Object treeValue,
			GtnUIFrameworkBaseComponent cvTreeBaseComponent) {
		return ((movingItem != null) && (getLastLevelNo(hierarchyIndicator, treeValue, cvTreeBaseComponent) < Integer
				.parseInt(String.valueOf(((GtnWsRecordBean) movingItem).getPropertyValueByIndex(1)))));
	}

	private int getLastLevelNo(String hierarchyIndicator, Object treeLastItem,
			GtnUIFrameworkBaseComponent cvTreeBaseComponent) {
		if (treeLastItem == null) {
			return 0;
		}
		GtnWsRecordBean dto = (GtnWsRecordBean) treeLastItem;
		if (dto.getAdditionalPropertyByIndex(2).equals(hierarchyIndicator)) {
			return Integer.parseInt(String.valueOf(dto.getAdditionalPropertyByIndex(1)));
		} else {
			return getLastLevelNo(hierarchyIndicator, cvTreeBaseComponent.getParent(treeLastItem), cvTreeBaseComponent);
		}
	}

}
