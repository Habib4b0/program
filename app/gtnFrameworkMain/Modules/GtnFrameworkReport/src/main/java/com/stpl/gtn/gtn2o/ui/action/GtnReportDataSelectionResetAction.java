package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;

public class GtnReportDataSelectionResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent("reportLandingScreen_privateViews", componentId)
				.setV8PopupFieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent("reportLandingScreen_publicViews", componentId)
				.setV8PopupFieldValue("");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(1).toString(), componentId)
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(2).toString(), componentId)
				.loadV8ComboBoxComponentValue(0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(3).toString(), componentId)
				.loadV8ComboBoxComponentValue(0);

		GtnUIFrameworkComboBoxConfig fromPeriodConfig = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent("reportLandingScreen_fromPeriod", componentId).getComponentConfig()
				.getGtnComboboxConfig();
		fromPeriodConfig.setModuleName("report");
		fromPeriodConfig.setLoadingUrl("/gtnReport/gtnWsReportComboboxLoad");
		fromPeriodConfig.setComboBoxType("timePeriodForReportFromDate");
		fromPeriodConfig.setHasDefaultValue(true);
		fromPeriodConfig.setDefaultDesc("next");

		GtnUIFrameworkComboBoxComponent comboBoxComponent = new GtnUIFrameworkComboBoxComponent();
		comboBoxComponent.reloadComponentFromParent("reportLandingScreen_fromPeriod", componentId, Arrays.asList(""));

		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(6).toString(), componentId)
				.setV8PopupFieldValue("");
		String value = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(7).toString(), componentId)
				.getCaptionFromV8ComboBox();
		String value3 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(8).toString(), componentId)
				.getCaptionFromV8ComboBox();
		if (value != "" || !value3.isEmpty()) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(7).toString(), componentId)
					.loadV8ComboBoxComponentValue(0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(8).toString(), componentId)
					.loadV8ComboBoxComponentValue(0);
		}
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(11).toString(), componentId).getComponentData();
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) componentData.getCustomData();
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		leftTable.setItems(new ArrayList<>());
		String columnId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(11).toString(), componentId).getComponentConfig()
				.getGtnUIFrameworkV8DualListBoxConfig().getLeftVisibleColumns()[0].toString();
		Component component = leftTable.getHeaderRow(1).getCell(columnId).getComponent();
		HorizontalLayout horizontalLayout = (HorizontalLayout) component;
		if (horizontalLayout.getComponent(0) instanceof TextField) {
			TextField textField = (TextField) horizontalLayout.getComponent(0);
			textField.setValue("");
			textField.setPlaceholder("Show all");
		}

		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		if (rightTable.getTreeData().getRootItems().iterator().hasNext()) {
			GtnWsRecordBean recordBean = rightTable.getTreeData().getRootItems().get(0);
			rightTable.getTreeData().removeItem(recordBean);
			rightTable.getDataProvider().refreshAll();
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(12).toString(), componentId)
				.setV8PopupFieldValue("");
		String value2 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(13).toString(), componentId)
				.getCaptionFromV8ComboBox();
		String value4 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(14).toString(), componentId)
				.getCaptionFromV8ComboBox();
		if (value2 != "" || !value4.isEmpty()) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(13).toString(), componentId)
					.loadV8ComboBoxComponentValue(0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(14).toString(), componentId)
					.loadV8ComboBoxComponentValue(0);
		}
		GtnUIFrameworkComponentData productComponentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(16).toString(), componentId).getComponentData();
		GtnFrameworkV8DualListBoxBean productDualListBoxBean = (GtnFrameworkV8DualListBoxBean) productComponentData
				.getCustomData();
		Grid<GtnWsRecordBean> productLeftTable = productDualListBoxBean.getLeftTable();
		productLeftTable.setItems(new ArrayList<>());
		String productLeftTablecolumnId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(16).toString(), componentId).getComponentConfig()
				.getGtnUIFrameworkV8DualListBoxConfig().getLeftVisibleColumns()[0].toString();
		Component filterComponent = productLeftTable.getHeaderRow(1).getCell(productLeftTablecolumnId).getComponent();
		HorizontalLayout filterComponentLayout = (HorizontalLayout) filterComponent;
		if (filterComponentLayout.getComponent(0) instanceof TextField) {
			TextField textField = (TextField) filterComponentLayout.getComponent(0);
			textField.setValue("");
			textField.setPlaceholder("Show all");
		}
		TreeGrid<GtnWsRecordBean> productRightTable = productDualListBoxBean.getRightTable();
		if (productRightTable.getTreeData().getRootItems().iterator().hasNext()) {
			GtnWsRecordBean productRecordBean = productRightTable.getTreeData().getRootItems().get(0);
			productRightTable.getTreeData().removeItem(productRecordBean);
			productRightTable.getDataProvider().refreshAll();
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(17).toString(), componentId)
				.loadV8ComboBoxComponentValue("0");
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(18).toString(), componentId)
				.loadV8MultiSelectValue();
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(19).toString(), componentId)
				.setV8PopupFieldValue(" ");

		comboBoxComponent.reloadComponentFromParent(actionParamsList.get(20).toString(), componentId,
				Arrays.asList(""));
		comboBoxComponent.setFocusInComponent(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(1).toString(), componentId).getComponent());

		GtnUIFrameworkComponentData comparisonComponentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(21).toString(), componentId).getComponentData();
		comparisonComponentData.setCustomData(null);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
