/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tree.GtnUIFrameworkTreeConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkAddToTreeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkAutoBuildAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkAvaliableNameUpdateAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnFrameworkRemoveFromTreeAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action.GtnUIFrameworkTreeItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.constants.GtnFrameworkRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRelationshipBuilderResultLayoutConfig {

	private static final String BUTTON_HIERARCHY_CSS_LAYOUT = "buttonHierarchyCssLayout";
	private final GtnFrameworkComponentConfigProvider gtnConfigFactory = GtnFrameworkComponentConfigProvider
			.getInstance();

	public void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix,
			boolean isView) {
		GtnUIFrameworkComponentConfig mainCssConfig = gtnConfigFactory.getCssLayoutConfig(
				namespaceprefix + GtnFrameworkCommonConstants.MAIN_CSS_LAYOUT, true, namespaceprefix + "resultlayout");
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(mainCssConfig);

		GtnUIFrameworkComponentConfig availableHierarchyCssConfig = gtnConfigFactory.getCssLayoutConfig(
				namespaceprefix + "availableHierarchyCssLayout", true,
				namespaceprefix + GtnFrameworkCommonConstants.MAIN_CSS_LAYOUT);
		availableHierarchyCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_7);
		availableHierarchyCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(availableHierarchyCssConfig);

		GtnUIFrameworkComponentConfig treeCssConfig = gtnConfigFactory.getCssLayoutConfig(
				namespaceprefix + GtnWsRelationshipBuilderConstants.TREE_CSS_LAYOUT, true,
				namespaceprefix + GtnFrameworkCommonConstants.MAIN_CSS_LAYOUT);
		treeCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_5);
		treeCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(treeCssConfig);

		GtnUIFrameworkComponentConfig treePanelConfig = gtnConfigFactory.getPanelConfig(namespaceprefix + GtnFrameworkCommonConstants.TREE_PANEL,
				true, namespaceprefix + GtnWsRelationshipBuilderConstants.TREE_CSS_LAYOUT);
		treePanelConfig.setComponentHight("700px");
		treePanelConfig.setAuthorizationIncluded(true);
		treePanelConfig.setComponentName("Relationship Tree:");
		componentList.add(treePanelConfig);

		GtnUIFrameworkComponentConfig treeLayoutConfig = gtnConfigFactory.getVerticalLayoutConfig(
				namespaceprefix + GtnFrameworkCommonConstants.TREE_RESULT_LAYOUT, true, namespaceprefix + GtnFrameworkCommonConstants.TREE_PANEL);
		treeLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		
		GtnUIFrameworkComponentConfig treeHorizontalLayoutConfig = gtnConfigFactory.getHorizontalLayoutConfig(
				namespaceprefix + GtnFrameworkCommonConstants.TREE_HORIZONTAL_RESULT_LAYOUT, true, namespaceprefix + GtnFrameworkCommonConstants.TREE_PANEL);
		componentList.add(treeLayoutConfig);
		componentList.add(treeHorizontalLayoutConfig);
		addHierarchyLevelPanel(componentList, namespaceprefix, isView);
		addRelationshipTree(componentList, namespaceprefix, isView);
		addRemoveFromTreeButtonComponent(componentList, namespaceprefix, isView);
		addAutoBuildButtonComponent(componentList, namespaceprefix, isView);
	}

	public void addHierarchyLevelPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix,
			boolean isView) {
		GtnUIFrameworkComponentConfig mainCssConfig = gtnConfigFactory.getCssLayoutConfig(
				namespaceprefix + GtnFrameworkCommonConstants.MAIN_HIERARCHY_CSS_LAYOUT, true,
				namespaceprefix + "availableHierarchyCssLayout");
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		mainCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(mainCssConfig);

		GtnUIFrameworkComponentConfig availableHierarchyCssConfig = gtnConfigFactory.getCssLayoutConfig(
				namespaceprefix + "tableHierarchyCssLayout", true,
				namespaceprefix + GtnFrameworkCommonConstants.MAIN_HIERARCHY_CSS_LAYOUT);
		availableHierarchyCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_8);
		availableHierarchyCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(availableHierarchyCssConfig);

		GtnUIFrameworkComponentConfig buttonCssConfig = gtnConfigFactory.getCssLayoutConfig(
				namespaceprefix + BUTTON_HIERARCHY_CSS_LAYOUT, true,
				namespaceprefix + GtnFrameworkCommonConstants.MAIN_HIERARCHY_CSS_LAYOUT);
		buttonCssConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_4);
		buttonCssConfig.addComponentStyle(GtnFrameworkCssConstants.NO_MARGIN);
		componentList.add(buttonCssConfig);

		availableResultPanel(componentList, namespaceprefix, isView);


	}

	private void availableResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix,
			boolean isView) {
		GtnUIFrameworkComponentConfig tablePanelConfig = gtnConfigFactory.getPanelConfig(
				namespaceprefix + GtnFrameworkRelationshipBuilderConstants.LEVEL_PANEL, true,
				namespaceprefix + "tableHierarchyCssLayout");
		tablePanelConfig.setAuthorizationIncluded(true);
		tablePanelConfig.setComponentName("Available &lt;Level  &gt; :");
		tablePanelConfig.setComponentWidth("100%");
		componentList.add(tablePanelConfig);

		GtnUIFrameworkComponentConfig tableLayoutConfig = gtnConfigFactory.getHorizontalLayoutConfig(
				namespaceprefix + "levelResultLayout", true,
				namespaceprefix + GtnFrameworkRelationshipBuilderConstants.LEVEL_PANEL);
		tableLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_725);
		componentList.add(tableLayoutConfig);

		availableResultDataTable(componentList, namespaceprefix);
		addAddToTreeButtonComponent(componentList, namespaceprefix, isView);

	}

	private void addAddToTreeButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix,
			boolean isView) {
		GtnUIFrameworkComponentConfig addToTreeBtnConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namespaceprefix + "addToTreeBtn", true, namespaceprefix + BUTTON_HIERARCHY_CSS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		addToTreeBtnConfig.setAuthorizationIncluded(true);
		addToTreeBtnConfig.setComponentName("Add To Tree");
		componentList.add(addToTreeBtnConfig);
		addToTreeBtnConfig.setEnable(!isView);
		if (!isView) {
			GtnUIFrameWorkActionConfig addToTreeAction = gtnConfigFactory
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			addToTreeAction.addActionParameter(GtnFrameworkAddToTreeAction.class.getName());
			addToTreeAction.addActionParameter("0");
			addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE);
			addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RB_TREE);
			addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.REMOVE_FROM_TREE_BTN);
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			actionConfigList.add(addToTreeAction);
			addToTreeBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		}
	}

	private void addAutoBuildButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix,
			boolean isView) {
		GtnUIFrameworkComponentConfig addToTreeBtnConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namespaceprefix + "autoBuildBtn", true,
				namespaceprefix + GtnWsRelationshipBuilderConstants.TREE_CSS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		addToTreeBtnConfig.setAuthorizationIncluded(true);
		addToTreeBtnConfig.setComponentName("Auto-Build");
		componentList.add(addToTreeBtnConfig);
		addToTreeBtnConfig.setEnable(!isView);
		if (!isView) {
			GtnUIFrameWorkActionConfig autoBuildConfirmationConfig=gtnConfigFactory
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
			autoBuildConfirmationConfig.addActionParameter(" Auto Build Confirmation ");
			autoBuildConfirmationConfig.addActionParameter(" Are you sure you want to proceed with Auto Building the selected level(s)? ");
			List<GtnUIFrameWorkActionConfig> onSuccessConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig autoBuildStartedConfig=gtnConfigFactory
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.INFO_ACTION);
			autoBuildStartedConfig.addActionParameter(" Auto Build Started");
			autoBuildStartedConfig.addActionParameter(" Auto Build functionality has been initiated.A message will be displayed when Auto Build has completed. ");
			onSuccessConfigList.add(autoBuildStartedConfig);
			GtnUIFrameWorkActionConfig addToTreeAction = gtnConfigFactory
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			addToTreeAction.addActionParameter(GtnFrameworkAutoBuildAction.class.getName());
			addToTreeAction.addActionParameter("0");
			addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE);
			addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RB_TREE);
			addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.REMOVE_FROM_TREE_BTN);
			onSuccessConfigList.add(addToTreeAction);
			
			GtnUIFrameWorkActionConfig messageConfig=gtnConfigFactory
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.INFO_ACTION);
			messageConfig.addActionParameter(" Auto Build Completed");
			messageConfig.addActionParameter(" Auto Build functionality has successfully completed. ");
			onSuccessConfigList.add(messageConfig);
			autoBuildConfirmationConfig.addActionParameter(onSuccessConfigList);
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			actionConfigList.add(autoBuildConfirmationConfig);
			addToTreeBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		}
	}

	private void availableResultDataTable(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix) {
		GtnUIFrameworkComponentConfig tableConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE, true, namespaceprefix + "levelResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		tableConfig.setAuthorizationIncluded(true);
		tableConfig.setComponentHight("600px");
		tableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		tableConfig.addComponentStyle(GtnFrameworkCssConstants.FILTERBAR);
		tableConfig.addComponentStyle(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableConfig.addComponentStyle(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableConfig.addComponentStyle(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setMinSize(1);
		tableConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		// GtnFrameworkAvaliableNameUpdateAction
		GtnUIFrameWorkActionConfig availableNameUpdateAction = gtnConfigFactory.getUIFrameworkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION, GtnFrameworkAvaliableNameUpdateAction.class.getName(),
				namespaceprefix + GtnFrameworkRelationshipBuilderConstants.LEVEL_PANEL);
		GtnUIFrameworkPagedTableConfig pagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		pagedTableConfig.setEditable(false);
		pagedTableConfig.setFilterBar(true);
		pagedTableConfig.setRecordTypeManageActionConfig(availableNameUpdateAction);
		pagedTableConfig.setSelectable(true);
		pagedTableConfig.setMultiSelect(true);
		pagedTableConfig.setTableColumnDataType(new Class<?>[] { String.class });
		pagedTableConfig.setTableVisibleHeader(new String[] { "Value" });
		pagedTableConfig.setCountUrl(GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
				+ GtnWsRelationshipBuilderConstants.GET_RELATIONSHIP_BUILDER_AVAILABLE_DATA);
		pagedTableConfig.setResultSetUrl(GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
				+ GtnWsRelationshipBuilderConstants.GET_RELATIONSHIP_BUILDER_AVAILABLE_DATA);
		pagedTableConfig.setTableColumnMappingId(
				new Object[] { GtnFrameworkRelationshipBuilderConstants.AVAILABLE_LIST_COLUMNID });
		GtnUIFrameWorkActionConfig addToTreeAction = gtnConfigFactory
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addToTreeAction.addActionParameter(GtnFrameworkAddToTreeAction.class.getName());
		addToTreeAction.addActionParameter("0");
		addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RB_TREE);
		addToTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.REMOVE_FROM_TREE_BTN);
		pagedTableConfig.addItemDoubleClickActionConfig(addToTreeAction);
		pagedTableConfig.setItemClickListener(true);
		tableConfig.setGtnPagedTableConfig(pagedTableConfig);

		componentList.add(tableConfig);

	}

	private void addRemoveFromTreeButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespaceprefix, boolean isView) {
		GtnUIFrameworkComponentConfig removeFromTreeBtnConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
				namespaceprefix + GtnFrameworkCommonConstants.REMOVE_FROM_TREE_BTN, true,
				namespaceprefix + GtnWsRelationshipBuilderConstants.TREE_CSS_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		removeFromTreeBtnConfig.setAuthorizationIncluded(true);
		removeFromTreeBtnConfig.setComponentName("Remove from Tree");
		removeFromTreeBtnConfig.setVisible(false);
		componentList.add(removeFromTreeBtnConfig);
		removeFromTreeBtnConfig.setEnable(!isView);
		if (!isView) {
			GtnUIFrameWorkActionConfig removeFromTreeAction = gtnConfigFactory
					.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
			removeFromTreeAction.addActionParameter(GtnFrameworkRemoveFromTreeAction.class.getName());
			removeFromTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RB_TREE);
			removeFromTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE);
			removeFromTreeAction.addActionParameter(namespaceprefix + "hierarchyName");
			removeFromTreeAction.addActionParameter(namespaceprefix + "versionNo");
			removeFromTreeAction.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE);
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			actionConfigList.add(removeFromTreeAction);
			removeFromTreeBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		}
	}

	public void addRelationshipTree(List<GtnUIFrameworkComponentConfig> componentList, String namespaceprefix,
			boolean isView) {
	
		GtnUIFrameworkComponentConfig treeComponentConfig = new GtnUIFrameworkComponentConfig();
		
		treeComponentConfig.setComponentType(GtnUIFrameworkComponentType.TREE);
		treeComponentConfig.setAuthorizationIncluded(true);
		treeComponentConfig.setComponentId(namespaceprefix + GtnFrameworkCommonConstants.RB_TREE);
		treeComponentConfig.setParentComponentId(namespaceprefix + GtnFrameworkCommonConstants.TREE_RESULT_LAYOUT);
		treeComponentConfig.setParentComponentId(namespaceprefix+GtnFrameworkCommonConstants.TREE_HORIZONTAL_RESULT_LAYOUT);
		treeComponentConfig.setAddToParent(true);

		GtnUIFrameworkTreeConfig treeConfig = new GtnUIFrameworkTreeConfig();
		treeConfig.setItemCaptionPropertyId(GtnFrameworkRelationshipBuilderConstants.AVAILABLE_LIST_COLUMNID);
		if (!isView) {
			treeConfig.setSelectable(true);
			treeConfig.setMultiSelect(true);		
			GtnUIFrameWorkActionConfig actionConfig = getItemClickAction(namespaceprefix);
			treeConfig.addItemClickActionConfig(actionConfig);
		}
		treeComponentConfig.setGtnUIFrameworkTreeConfig(treeConfig);
		componentList.add(treeComponentConfig);
	}

	private GtnUIFrameWorkActionConfig getItemClickAction(String namespaceprefix) {
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.addActionParameter(GtnUIFrameworkTreeItemClickAction.class.getName());
		actionConfig.addActionParameter(namespaceprefix);
		actionConfig.addActionParameter(namespaceprefix + "hierarchyName");
		actionConfig.addActionParameter(namespaceprefix + "versionNo");
		actionConfig.addActionParameter(namespaceprefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		actionConfig.addActionParameter("selectedId");
		actionConfig.addActionParameter("totalLevels");
		return actionConfig;
	}

}
