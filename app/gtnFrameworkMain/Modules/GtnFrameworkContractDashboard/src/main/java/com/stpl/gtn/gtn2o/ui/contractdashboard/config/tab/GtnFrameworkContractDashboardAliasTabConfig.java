package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAliasAddAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkAliasRemoveAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload.GtnFrameworkAliasTabLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkContractDashboardAliasTabConfig {

	private static final String ALIAS_RESULT_TABLE = "AliasResultTable";
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkTabConfig getTabConfig(String mainNamspacePrefix) {
		String namspacePrefix = mainNamspacePrefix + "ALT";
		GtnUIFrameworkTabConfig aliasTabConfig = new GtnUIFrameworkTabConfig();
		aliasTabConfig.setComponentId(namspacePrefix + "AliasTab");
		aliasTabConfig.setTabCaption("Alias");
		List<GtnUIFrameworkComponentConfig> aliasTabComponentList = new ArrayList<>();
		aliasTabConfig.setTabLayoutComponentConfigList(aliasTabComponentList);
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getVerticalLayoutConfig(aliasTabConfig.getComponentId(), true, aliasTabConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		gtnLayoutConfig.setTabComponent(true);
		aliasTabComponentList.add(gtnLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		contractAliasInformationPanel(aliasTabComponentList, namspacePrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		contractAliasResultTable(aliasTabComponentList, namspacePrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		contractAliasRemoveButton(aliasTabComponentList, namspacePrefix, gtnLayoutConfig.getComponentId());

		GtnUIFrameWorkActionConfig aliasTabLoadAction = new GtnUIFrameWorkActionConfig();
		aliasTabLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		aliasTabLoadAction.addActionParameter(GtnFrameworkAliasTabLoadAction.class.getName());
		aliasTabLoadAction.addActionParameter(namspacePrefix + ALIAS_RESULT_TABLE);
		aliasTabLoadAction.addActionParameter(namspacePrefix + "AliasInformationPanel");
		aliasTabLoadAction.addActionParameter(namspacePrefix + "removeBtn");
		gtnLayoutConfig.addGtnUIFrameWorkActionConfig(aliasTabLoadAction);

		return aliasTabConfig;
	}

	private void contractAliasInformationPanel(List<GtnUIFrameworkComponentConfig> aliasTabComponentList,
			String namspacePrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig
				.getPanelConfig(namspacePrefix + "AliasInformationPanel", true, parent);
		gtnPanelConfig.setAuthorizationIncluded(true);
		aliasTabComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig.getVerticalLayoutConfig(
				namspacePrefix + "AliasInformationLayout", true, gtnPanelConfig.getComponentId());
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		aliasTabComponentList.add(gtnLayoutConfig);

		contractAliasInformationLayout(aliasTabComponentList, namspacePrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
		contractAliasAddButtonComponent(aliasTabComponentList, namspacePrefix, gtnLayoutConfig.getComponentId(),
				componentIdList);
	}

	private void contractAliasInformationLayout(List<GtnUIFrameworkComponentConfig> aliasTabComponentList,
			String namspacePrefix, String parent, List<String> componentIdList) {
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getCssLayoutConfig(namspacePrefix + "AliasFieldCssLayout", true, parent);
		gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		aliasTabComponentList.add(gtnLayoutConfig);
		addTradingPartnerNo(aliasTabComponentList, gtnLayoutConfig.getComponentId(), componentIdList);
		addContractAliasName(aliasTabComponentList, gtnLayoutConfig.getComponentId(), componentIdList);
		addContractAliasNo(aliasTabComponentList, gtnLayoutConfig.getComponentId(), componentIdList);
		addContractAliasType(aliasTabComponentList, gtnLayoutConfig.getComponentId(), componentIdList);
		addStartDate(aliasTabComponentList, gtnLayoutConfig.getComponentId(), componentIdList);
		addEndDate(aliasTabComponentList, gtnLayoutConfig.getComponentId(), componentIdList);
	}

	private void addTradingPartnerNo(List<GtnUIFrameworkComponentConfig> aliasTabComponentList, String parent,
			List<String> componentIdList) {
		String componentId = GtnFrameworkContractDashboardContants.getAliasTableColumn()[1];
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Trading Partner");
		aliasTabComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

		GtnUIFrameworkTextBoxConfig textboxConfig = commonConfig.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		componentConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameWorkActionConfig tpPopupActionConfig = new GtnUIFrameWorkActionConfig();
		tpPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		tpPopupActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.CD_TP_VIEW);
		tpPopupActionConfig.addActionParameter("Trading Partner Look Up");
		tpPopupActionConfig.addActionParameter("70%");
		tpPopupActionConfig.addActionParameter(null);
		tpPopupActionConfig.addActionParameter(null);
		tpPopupActionConfig.addActionParameter(Arrays.asList(componentConfig.getComponentId(),
				Arrays.asList("companyName"), Arrays.asList(componentConfig.getComponentId()), null));
		componentConfig.addGtnUIFrameWorkActionConfig(tpPopupActionConfig);

	}

	private void addContractAliasNo(List<GtnUIFrameworkComponentConfig> aliasTabComponentList, String parent,
			List<String> componentIdList) {
		String componentId = GtnFrameworkContractDashboardContants.getAliasTableColumn()[2];
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Contract Alias No");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		aliasTabComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addStartDate(List<GtnUIFrameworkComponentConfig> aliasTabComponentList, String parent,
			List<String> componentIdList) {
		String componentId = GtnFrameworkContractDashboardContants.getAliasTableColumn()[4];
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Start Date");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		aliasTabComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addEndDate(List<GtnUIFrameworkComponentConfig> aliasTabComponentList, String parent,
			List<String> componentIdList) {
		String componentId = GtnFrameworkContractDashboardContants.getAliasTableColumn()[5];
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.DATEFIELD);
		componentConfig.setComponentName("End Date");
		aliasTabComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());

	}

	private void addContractAliasType(List<GtnUIFrameworkComponentConfig> aliasTabComponentList, String parent,
			List<String> componentIdList) {
		String componentId = GtnFrameworkContractDashboardContants.getAliasTableColumn()[0];
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Contract Alias Type");
		componentConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FIELD_MANDATORY);
		aliasTabComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CONTRACT_ALIAS_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkValidationConfig valConfig = new GtnUIFrameworkValidationConfig();
		valConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(valConfig);
	}

	private void addContractAliasName(List<GtnUIFrameworkComponentConfig> aliasTabComponentList, String parent,
			List<String> componentIdList) {
		String componentId = GtnFrameworkContractDashboardContants.getAliasTableColumn()[3];
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Contract Alias Name");
		aliasTabComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
	}

	private void contractAliasAddButtonComponent(List<GtnUIFrameworkComponentConfig> aliasTabComponentList,
			String namspacePrefix, String parent, List<String> componentIdList) {
		String componentId = namspacePrefix + "addBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Add");
		aliasTabComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig addAction = new GtnUIFrameWorkActionConfig();
		addAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addAction.addActionParameter(GtnFrameworkAliasAddAction.class.getName());
		addAction.addActionParameter(componentIdList);
		addAction.addActionParameter("Contract Alias No is Mandatory");
		addAction.addActionParameter("Contract Alias Type is Mandatory");
		addAction.addActionParameter("Alias Start Date should be selected in Alias tab");
		addAction.addActionParameter(GtnFrameworkContractDashboardContants.ERROR_DISPLAY_LABEL);

		componentConfig.addGtnUIFrameWorkActionConfig(addAction);
	}

	private void contractAliasResultTable(List<GtnUIFrameworkComponentConfig> aliasTabComponentList,
			String namspacePrefix, String parent, List<String> componentIdList) {
		String componentId = namspacePrefix + ALIAS_RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		gtnPanelConfig.setComponentName("Results");
		aliasTabComponentList.add(gtnPanelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnPanelConfig.getComponentId(), GtnUIFrameworkComponentType.DATATABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		aliasTabComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		GtnUIFrameworkPagedTableConfig aliasTabResultsTable = new GtnUIFrameworkPagedTableConfig();
		componentConfig.setGtnPagedTableConfig(aliasTabResultsTable);
		aliasTabResultsTable.setFilterBar(true);
		aliasTabResultsTable.setSelectable(true);
		aliasTabResultsTable.setPageLength(5);
		aliasTabResultsTable.setSinkItemPerPageWithPageLength(false);
		aliasTabResultsTable.setTableColumnDataType(GtnFrameworkContractDashboardContants.getAliasTableColumnType());
		aliasTabResultsTable.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getAliasTableHeader());
		aliasTabResultsTable.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getAliasTableColumn());
	}

	private void contractAliasRemoveButton(List<GtnUIFrameworkComponentConfig> aliasTabComponentList,
			String namspacePrefix, String parent) {
		String componentId = namspacePrefix + "removeBtn";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		aliasTabComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Remove");
		aliasTabComponentList.add(componentConfig);

		GtnUIFrameWorkActionConfig removeActionConfig = new GtnUIFrameWorkActionConfig();
		removeActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		removeActionConfig.addActionParameter(GtnFrameworkAliasRemoveAction.class.getName());
		removeActionConfig.addActionParameter(namspacePrefix + ALIAS_RESULT_TABLE);
		removeActionConfig.addActionParameter("No Record Selected");
		removeActionConfig.addActionParameter("Please select an alias to remove.");
		removeActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		removeActionConfig.addActionParameter("Are you sure you want to remove the component?");

		componentConfig.addGtnUIFrameWorkActionConfig(removeActionConfig);

	}
}
