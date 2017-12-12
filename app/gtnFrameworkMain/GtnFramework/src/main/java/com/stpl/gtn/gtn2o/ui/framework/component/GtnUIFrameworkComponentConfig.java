package com.stpl.gtn.gtn2o.ui.framework.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.button.GtnUIFrameworkButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.calendarfield.GtnUIFrameworkCalendarConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkbox.GtnUIFrameworkCheckBoxComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.label.GtnUIFrameworkLabelConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.panel.GtnUIFrameworkPanelConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.splitpanel.GtnUIFrameworkSplitPanelConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textarea.GtnUIFrameworkTextAreaConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tree.GtnUIFrameworkTreeConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.ui.AbstractComponent;

public class GtnUIFrameworkComponentConfig {

	private GtnUIFrameworkComponentType componentType;
	private String componentName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private String componentId;
	private String componentWidth;
	private String componentHight;
	private Object componentValue;
	private String sourceViewId;
	private boolean authorizationIncluded = false;

	private String parentComponentId;
	private boolean addToParent = false;
	private boolean tabComponent = false;
	private boolean resetToDefaultAllowed = true;

	private String componentWsFieldId;

	private List<String> componentStyle = new ArrayList<>();
	private List<Object> queryInputs = new ArrayList<>();
	private GtnUIFrameworkComboBoxConfig gtnComboboxConfig;
	private GtnUIFrameworkPagedTableConfig gtnPagedTableConfig;
	private GtnUIFrameworkTextBoxConfig gtnTextBoxConfig;
	private GtnUIFrameworkTextAreaConfig gtnTextAreaConfig;
	private GtnUIFrameworkCheckBoxComponentConfig gtnCheckBoxConfig;
	private GtnUIFrameworkPanelConfig gtnPanelConfig;
	private GtnUIFrameworkLayoutConfig gtnLayoutConfig;
	private List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList;
	private GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig;

	private GtnUIFrameworkCheckedComboBoxConfig gtnCheckedComboboxConfig;

	private List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkActionConfigList = new ArrayList<>();

	private List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkFocusActionConfigList = new ArrayList<>();

	private List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkBlurActionConfigList = new ArrayList<>();

	private List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkValueChangeActionConfigList = new ArrayList<>();

	private List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkItemClickActionConfigList = new ArrayList<>();

	private GtnUIFrameworkNotesTabConfig notesTabConfig;

	private GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig;

	private GtnUIFrameworkOptionGroupConfig gtnUIFrameworkOptionGroupConfig;
	private boolean spacing = false;
	private boolean margin = false;
	private boolean visible = true;
	private boolean enable = true;
	private boolean rebuild = false;

	private GtnUIFrameworkDualListBoxConfig gtnUIFrameworkDualListBoxConfig;

	private GtnUIFrameworkTreeConfig gtnUIFrameworkTreeConfig;
	private GtnUIFrameworkDateFieldConfig gtnDateFieldConfig;
	private GtnUIFrameworkLabelConfig gtnLabelConfig;
	private GtnUIFrameworkSplitPanelConfig gtnSplitPanelConfig;
	private GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig;
	private GtnUIFrameworkNewTableConfig gtnNewTableConfig;
	private GtnUIFrameworkNewPagedTableConfig gtnNewPagedTableConfig;
	private GtnUIFrameworkNewTreeTableConfig gtnNewTreeTableConfig;
	private GtnUIFrameWorkActionConfig fieldFactoryComponentCreateActionConfig;
	private GtnUIFrameworkCalendarConfig calendarConfig;
	private GtnUIFrameworkButtonConfig buttonConfig;
	private GtnUIFrameWorkActionConfig gtnUIFrameWorkColumnGeneratorConfig;
	/*
	 * List of dependent componentIds. For example is combobox1 is changed, then
	 * combobox2 has to load, then combobox2 will be the dependent component of
	 * combobox1
	 */

	private List<String> dependentComponentList;

	/*
	 * Generally for reloading a component, reloadComponent method of
	 * GtnUIFramrworkComponent is used. In case customlogic is needed use this
	 * class. ReloadClass will implement GtnUIFrameWorkAction
	 */
	private String reloadLogicActionClassName;

	/**
	 * This is for custom table logic class should extends
	 * GtnUIFrameworkPagedTableLogic class for paged table.
	 * GtnUIFrameworkPagedTreeTableLogic class for pagedtreetable Override the
	 * required methods
	 * 
	 */
	private String pagedTableLogicClassName;

	private String expressionType;
	
	private boolean defaultFocus = false;

	public GtnUIFrameworkComponentConfig() {
		super();
	}

	public GtnUIFrameworkComponentConfig(GtnUIFrameworkComponentConfig componentConfig) {
		this.componentType = componentConfig.getComponentType();
		this.componentName = componentConfig.getComponentName();
		this.componentId = componentConfig.getComponentId();
		this.componentWidth = componentConfig.getComponentWidth();
		this.componentHight = componentConfig.getComponentHight();
		this.componentValue = componentConfig.getComponentValue();
		this.sourceViewId = componentConfig.getSourceViewId();
		this.parentComponentId = componentConfig.getParentComponentId();
		this.addToParent = componentConfig.isAddToParent();
		this.tabComponent = componentConfig.isTabComponent();
		this.resetToDefaultAllowed = componentConfig.isResetToDefaultAllowed();
		this.componentWsFieldId = componentConfig.getComponentWsFieldId();
		this.componentStyle = componentConfig.getComponentStyle();
		this.queryInputs = componentConfig.getQueryInputs();
		this.gtnComboboxConfig = componentConfig.getGtnComboboxConfig();
		this.gtnPagedTableConfig = componentConfig.getGtnPagedTableConfig();
		this.gtnTextBoxConfig = componentConfig.getGtnTextBoxConfig();
		this.gtnTextAreaConfig = componentConfig.getGtnTextAreaConfig();
		this.gtnCheckBoxConfig = componentConfig.getGtnCheckBoxConfig();
		this.gtnPanelConfig = componentConfig.getGtnPanelConfig();
		this.gtnLayoutConfig = componentConfig.getGtnLayoutConfig();
		this.gtnTabSheetConfigList = componentConfig.getGtnTabSheetConfigList();
		this.gtnUIFrameworkValidationConfig = componentConfig.getGtnUIFrameworkValidationConfig();
		this.gtnCheckedComboboxConfig = componentConfig.getGtnCheckedComboboxConfig();
		this.gtnUIFrameWorkActionConfigList = componentConfig.getGtnUIFrameWorkActionConfigList();
		this.gtnUIFrameWorkFocusActionConfigList = componentConfig.getGtnUIFrameWorkFocusActionConfigList();
		this.gtnUIFrameWorkBlurActionConfigList = componentConfig.getGtnUIFrameWorkBlurActionConfigList();
		this.gtnUIFrameWorkValueChangeActionConfigList = componentConfig.getGtnUIFrameWorkValueChangeActionConfigList();
		this.gtnUIFrameWorkItemClickActionConfigList = componentConfig.getGtnUIFrameWorkItemClickActionConfigList();
		this.notesTabConfig = componentConfig.getNotesTabConfig();
		this.gtnPagedTreeTableConfig = componentConfig.getGtnPagedTreeTableConfig();
		this.gtnUIFrameworkOptionGroupConfig = componentConfig.getGtnUIFrameworkOptionGroupConfig();
		this.spacing = componentConfig.isSpacing();
		this.margin = componentConfig.isMargin();
		this.visible = componentConfig.isVisible();
		this.enable = componentConfig.isEnable();
		this.gtnUIFrameworkDualListBoxConfig = componentConfig.getGtnUIFrameworkDualListBoxConfig();
		this.gtnUIFrameworkTreeConfig = componentConfig.getGtnUIFrameworkTreeConfig();
		this.gtnDateFieldConfig = componentConfig.getGtnDateFieldConfig();
		this.gtnLabelConfig = componentConfig.getGtnLabelConfig();
		this.gtnSplitPanelConfig = componentConfig.getGtnSplitPanelConfig();
		this.gtnUIFrameworkExcelButtonConfig = componentConfig.getGtnUIFrameworkExcelButtonConfig();
		this.gtnNewTableConfig = componentConfig.getGtnNewTableConfig();
		this.gtnNewPagedTableConfig = componentConfig.getGtnNewPagedTableConfig();
		this.gtnNewTreeTableConfig = componentConfig.getGtnNewTreeTableConfig();
		this.fieldFactoryComponentCreateActionConfig = componentConfig.getFieldFactoryComponentCreateActionConfig();
		this.calendarConfig = componentConfig.getCalendarConfig();
		this.buttonConfig = componentConfig.getButtonConfig();
		this.gtnUIFrameWorkColumnGeneratorConfig = componentConfig.getGtnUIFrameWorkColumnGeneratorConfig();
		this.dependentComponentList = componentConfig.getDependentComponentList();
		this.reloadLogicActionClassName = componentConfig.getReloadLogicActionClassName();
		this.pagedTableLogicClassName = componentConfig.getPagedTableLogicClassName();
		this.expressionType = componentConfig.getExpressionType();
	}

	public boolean isResetToDefaultAllowed() {
		return resetToDefaultAllowed;
	}

	public void setResetToDefaultAllowed(boolean resetToDefaultAllowed) {
		this.resetToDefaultAllowed = resetToDefaultAllowed;
	}

	public AbstractComponent returnVadinComponent() throws GtnFrameworkGeneralException {

		return componentType.getGtnComponent().buildVaadinComponent(this);

	}

	public void postCreateComponent(AbstractComponent component) {
		if (componentType.getGtnComponent() instanceof GtnUIFrameworkComponentActionable) {
			GtnUIFrameworkComponentActionable actionable = (GtnUIFrameworkComponentActionable) componentType
					.getGtnComponent();
			actionable.postCreateComponent(component, this);
		}
	}

	public GtnUIFrameworkComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(GtnUIFrameworkComponentType componentType) {
		this.componentType = componentType;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public List<String> getComponentStyle() {
		return componentStyle == null ? componentStyle : Collections.unmodifiableList(componentStyle);
	}

	public void setComponentStyle(List<String> componentStyle) {
		this.componentStyle = new ArrayList<>(componentStyle);
	}

	public void addComponentStyle(String componentStyle) {
		if (componentStyle == null) {
			return;
		}
		this.componentStyle.add(componentStyle);
	}

	public GtnUIFrameworkComboBoxConfig getGtnComboboxConfig() {
		return gtnComboboxConfig;
	}

	public void setGtnComboboxConfig(GtnUIFrameworkComboBoxConfig gtnComboboxConfig) {
		this.gtnComboboxConfig = gtnComboboxConfig;
	}

	public GtnUIFrameworkPagedTableConfig getGtnPagedTableConfig() {
		return gtnPagedTableConfig;
	}

	public void setGtnPagedTableConfig(GtnUIFrameworkPagedTableConfig gtnPagedTableConfig) {
		this.gtnPagedTableConfig = gtnPagedTableConfig;
	}

	public List<GtnUIFrameworkTabConfig> getGtnTabSheetConfigList() {
		return gtnTabSheetConfigList == null ? gtnTabSheetConfigList
				: Collections.unmodifiableList(gtnTabSheetConfigList);
	}

	public void setGtnTabSheetConfigList(List<GtnUIFrameworkTabConfig> gtnTabSheetConfigList) {
		this.gtnTabSheetConfigList = new ArrayList<>(gtnTabSheetConfigList);
	}

	public GtnUIFrameworkTextBoxConfig getGtnTextBoxConfig() {
		return gtnTextBoxConfig;
	}

	public void setGtnTextBoxConfig(GtnUIFrameworkTextBoxConfig gtnTextBoxConfig) {
		this.gtnTextBoxConfig = gtnTextBoxConfig;
	}

	public boolean isAddToParent() {
		return addToParent;
	}

	public void setAddToParent(boolean addToParent) {
		this.addToParent = addToParent;
	}

	public String getParentComponentId() {
		return parentComponentId;
	}

	public void setParentComponentId(String parentComponentId) {
		this.parentComponentId = parentComponentId;
	}

	public GtnUIFrameworkPanelConfig getGtnPanelConfig() {
		return gtnPanelConfig;
	}

	public void setGtnPanelConfig(GtnUIFrameworkPanelConfig gtnPanelConfig) {
		this.gtnPanelConfig = gtnPanelConfig;
	}

	public String getComponentWidth() {
		return componentWidth;
	}

	public void setComponentWidth(String componentWidth) {
		this.componentWidth = componentWidth;
	}

	public String getComponentHight() {
		return componentHight;
	}

	public void setComponentHight(String componentHight) {
		this.componentHight = componentHight;
	}

	public GtnUIFrameworkLayoutConfig getGtnLayoutConfig() {
		return gtnLayoutConfig;
	}

	public void setGtnLayoutConfig(GtnUIFrameworkLayoutConfig gtnLayoutConfig) {
		this.gtnLayoutConfig = gtnLayoutConfig;
	}

	public GtnUIFrameworkValidationConfig getGtnUIFrameworkValidationConfig() {
		return gtnUIFrameworkValidationConfig;
	}

	public void setGtnUIFrameworkValidationConfig(GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig) {
		this.gtnUIFrameworkValidationConfig = gtnUIFrameworkValidationConfig;
	}

	public GtnUIFrameworkCheckedComboBoxConfig getGtnCheckedComboboxConfig() {
		return gtnCheckedComboboxConfig;
	}

	public void setGtnCheckedComboboxConfig(GtnUIFrameworkCheckedComboBoxConfig gtnCheckedComboboxConfig) {
		this.gtnCheckedComboboxConfig = gtnCheckedComboboxConfig;
	}

	public boolean isTabComponent() {
		return tabComponent;
	}

	public void setTabComponent(boolean tabComponent) {
		this.tabComponent = tabComponent;
	}

	public GtnUIFrameworkPagedTreeTableConfig getGtnPagedTreeTableConfig() {
		return gtnPagedTreeTableConfig;
	}

	public void setGtnPagedTreeTableConfig(GtnUIFrameworkPagedTreeTableConfig gtnPagedTreeTableConfig) {
		this.gtnPagedTreeTableConfig = gtnPagedTreeTableConfig;
	}

	public List<GtnUIFrameWorkActionConfig> getGtnUIFrameWorkActionConfigList() {
		return gtnUIFrameWorkActionConfigList == null ? gtnUIFrameWorkActionConfigList
				: Collections.unmodifiableList(gtnUIFrameWorkActionConfigList);
	}

	public void setGtnUIFrameWorkActionConfigList(List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkActionConfigList) {
		this.gtnUIFrameWorkActionConfigList = new ArrayList<>(gtnUIFrameWorkActionConfigList);
	}

	public void addGtnUIFrameWorkActionConfig(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		if (gtnUIFrameWorkActionConfig == null) {
			this.gtnUIFrameWorkActionConfigList = new ArrayList<>();
		}
		this.gtnUIFrameWorkActionConfigList.add(gtnUIFrameWorkActionConfig);
	}

	public GtnUIFrameworkNotesTabConfig getNotesTabConfig() {
		return notesTabConfig;
	}

	public void setNotesTabConfig(GtnUIFrameworkNotesTabConfig notesTabConfig) {
		this.notesTabConfig = notesTabConfig;
	}

	public GtnUIFrameworkTextAreaConfig getGtnTextAreaConfig() {
		return gtnTextAreaConfig;
	}

	public void setGtnTextAreaConfig(GtnUIFrameworkTextAreaConfig gtnTextAreaConfig) {
		this.gtnTextAreaConfig = gtnTextAreaConfig;
	}

	public boolean isSpacing() {
		return spacing;
	}

	public void setSpacing(boolean spacing) {
		this.spacing = spacing;
	}

	public boolean isMargin() {
		return margin;
	}

	public void setMargin(boolean margin) {
		this.margin = margin;
	}

	public GtnUIFrameworkDualListBoxConfig getGtnUIFrameworkDualListBoxConfig() {
		return gtnUIFrameworkDualListBoxConfig;
	}

	public void setGtnUIFrameworkDualListBoxConfig(GtnUIFrameworkDualListBoxConfig gtnUIFrameworkDualListBoxConfig) {
		this.gtnUIFrameworkDualListBoxConfig = gtnUIFrameworkDualListBoxConfig;
	}

	public List<Object> getQueryInputs() {
		return queryInputs == null ? queryInputs : Collections.unmodifiableList(queryInputs);
	}

	public void setQueryInputs(List<Object> queryInputs) {
		this.queryInputs = new ArrayList<>(queryInputs);
	}

	public GtnUIFrameworkOptionGroupConfig getGtnUIFrameworkOptionGroupConfig() {
		return gtnUIFrameworkOptionGroupConfig;
	}

	public void setGtnUIFrameworkOptionGroupConfig(GtnUIFrameworkOptionGroupConfig gtnUIFrameworkOptionGroupConfig) {
		this.gtnUIFrameworkOptionGroupConfig = gtnUIFrameworkOptionGroupConfig;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<String> getDependentComponentList() {
		return dependentComponentList == null ? dependentComponentList
				: Collections.unmodifiableList(dependentComponentList);
	}

	public void setDependentComponentList(List<String> dependentComponentList) {
		this.dependentComponentList = new ArrayList<>(dependentComponentList);
	}

	public void addDependentComponent(String dependentComponentId) {
		if (this.dependentComponentList == null) {
			this.dependentComponentList = new ArrayList<>();
		}
		this.dependentComponentList.add(dependentComponentId);
	}

	public Object getComponentValue() {
		return componentValue;
	}

	public void setComponentValue(Object componentValue) {
		this.componentValue = componentValue;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getReloadLogicActionClassName() {
		return reloadLogicActionClassName;
	}

	public void setReloadLogicActionClassName(String reloadLogicActionClassName) {
		this.reloadLogicActionClassName = reloadLogicActionClassName;
	}

	public String getPagedTableLogicClassName() {
		return pagedTableLogicClassName;
	}

	public void setPagedTableLogicClassName(String pagedTableLogicClassName) {
		this.pagedTableLogicClassName = pagedTableLogicClassName;
	}

	public String getComponentWsFieldId() {
		return componentWsFieldId;
	}

	public void setComponentWsFieldId(String componentWsFieldId) {
		this.componentWsFieldId = componentWsFieldId;
	}

	public List<GtnUIFrameWorkActionConfig> getGtnUIFrameWorkFocusActionConfigList() {
		return gtnUIFrameWorkFocusActionConfigList == null ? gtnUIFrameWorkFocusActionConfigList
				: Collections.unmodifiableList(gtnUIFrameWorkFocusActionConfigList);
	}

	public void setGtnUIFrameWorkFocusActionConfigList(
			List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkFocusActionConfigList) {
		this.gtnUIFrameWorkFocusActionConfigList = new ArrayList<>(gtnUIFrameWorkFocusActionConfigList);
	}

	public List<GtnUIFrameWorkActionConfig> getGtnUIFrameWorkBlurActionConfigList() {
		return gtnUIFrameWorkBlurActionConfigList == null ? gtnUIFrameWorkBlurActionConfigList
				: Collections.unmodifiableList(gtnUIFrameWorkBlurActionConfigList);
	}

	public void setGtnUIFrameWorkBlurActionConfigList(
			List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkBlurActionConfigList) {
		this.gtnUIFrameWorkBlurActionConfigList = new ArrayList<>(gtnUIFrameWorkBlurActionConfigList);
	}

	public List<GtnUIFrameWorkActionConfig> getGtnUIFrameWorkValueChangeActionConfigList() {
		return gtnUIFrameWorkValueChangeActionConfigList == null ? gtnUIFrameWorkValueChangeActionConfigList
				: Collections.unmodifiableList(gtnUIFrameWorkValueChangeActionConfigList);
	}

	public void setGtnUIFrameWorkValueChangeActionConfigList(
			List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkValueChangeActionConfigList) {
		this.gtnUIFrameWorkValueChangeActionConfigList = new ArrayList<>(gtnUIFrameWorkValueChangeActionConfigList);
	}

	public List<GtnUIFrameWorkActionConfig> getGtnUIFrameWorkItemClickActionConfigList() {
		return gtnUIFrameWorkItemClickActionConfigList == null ? gtnUIFrameWorkItemClickActionConfigList
				: Collections.unmodifiableList(gtnUIFrameWorkItemClickActionConfigList);
	}

	public void setGtnUIFrameWorkItemClickActionConfigList(
			List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkItemClickActionConfigList) {
		this.gtnUIFrameWorkItemClickActionConfigList = new ArrayList<>(gtnUIFrameWorkItemClickActionConfigList);
	}

	public GtnUIFrameworkCheckBoxComponentConfig getGtnCheckBoxConfig() {
		return gtnCheckBoxConfig;
	}

	public void setGtnCheckBoxConfig(GtnUIFrameworkCheckBoxComponentConfig gtnCheckBoxConfig) {
		this.gtnCheckBoxConfig = gtnCheckBoxConfig;
	}

	public GtnUIFrameworkTreeConfig getGtnUIFrameworkTreeConfig() {
		return gtnUIFrameworkTreeConfig;
	}

	public void setGtnUIFrameworkTreeConfig(GtnUIFrameworkTreeConfig gtnUIFrameworkTreeConfig) {
		this.gtnUIFrameworkTreeConfig = gtnUIFrameworkTreeConfig;
	}

	public GtnUIFrameworkDateFieldConfig getGtnDateFieldConfig() {
		return gtnDateFieldConfig;
	}

	public void setGtnDateFieldConfig(GtnUIFrameworkDateFieldConfig gtnDateFieldConfig) {
		this.gtnDateFieldConfig = gtnDateFieldConfig;
	}

	public GtnUIFrameworkLabelConfig getGtnLabelConfig() {
		return gtnLabelConfig;
	}

	public void setGtnLabelConfig(GtnUIFrameworkLabelConfig gtnLabelConfig) {
		this.gtnLabelConfig = gtnLabelConfig;
	}

	public GtnUIFrameworkSplitPanelConfig getGtnSplitPanelConfig() {
		return gtnSplitPanelConfig;
	}

	public void setGtnSplitPanelConfig(GtnUIFrameworkSplitPanelConfig gtnSplitPanelConfig) {
		this.gtnSplitPanelConfig = gtnSplitPanelConfig;
	}

	public GtnUIFrameworkExcelButtonConfig getGtnUIFrameworkExcelButtonConfig() {
		return gtnUIFrameworkExcelButtonConfig;
	}

	public void setGtnUIFrameworkExcelButtonConfig(GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig) {
		this.gtnUIFrameworkExcelButtonConfig = gtnUIFrameworkExcelButtonConfig;
	}

	public GtnUIFrameworkNewTableConfig getGtnNewTableConfig() {
		return gtnNewTableConfig;
	}

	public void setGtnNewTableConfig(GtnUIFrameworkNewTableConfig gtnNewTableConfig) {
		this.gtnNewTableConfig = gtnNewTableConfig;
	}

	public GtnUIFrameworkNewPagedTableConfig getGtnNewPagedTableConfig() {
		return gtnNewPagedTableConfig;
	}

	public void setGtnNewPagedTableConfig(GtnUIFrameworkNewPagedTableConfig gtnNewPagedTableConfig) {
		this.gtnNewPagedTableConfig = gtnNewPagedTableConfig;
	}

	public GtnUIFrameworkNewTreeTableConfig getGtnNewTreeTableConfig() {
		return gtnNewTreeTableConfig;
	}

	public void setGtnNewTreeTableConfig(GtnUIFrameworkNewTreeTableConfig gtnNewTreeTableConfig) {
		this.gtnNewTreeTableConfig = gtnNewTreeTableConfig;
	}

	public String getSourceViewId() {
		return sourceViewId;
	}

	public void setSourceViewId(String sourceViewId) {
		this.sourceViewId = sourceViewId;
	}

	public GtnUIFrameWorkActionConfig getFieldFactoryComponentCreateActionConfig() {
		return fieldFactoryComponentCreateActionConfig;
	}

	/*
	 * This action should attach a GtnUIFrameworkComponentConfig to
	 * GtnUIFrameWorkActionConfig.setComponentConfig( GtnUIFrameworkComponentConfig
	 * vaadinComponentConfig) This action will be executed while creating table
	 * field factory to get a dynamic component config.
	 */
	public void setFieldFactoryComponentCreateActionConfig(
			GtnUIFrameWorkActionConfig fieldFactoryComponentCreateActionConfig) {
		this.fieldFactoryComponentCreateActionConfig = fieldFactoryComponentCreateActionConfig;
	}

	public GtnUIFrameworkCalendarConfig getCalendarConfig() {
		return calendarConfig;
	}

	public void setCalendarConfig(GtnUIFrameworkCalendarConfig calendarConfig) {
		this.calendarConfig = calendarConfig;
	}

	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
	}

	public GtnUIFrameworkButtonConfig getButtonConfig() {
		return buttonConfig;
	}

	public void setButtonConfig(GtnUIFrameworkButtonConfig buttonConfig) {
		this.buttonConfig = buttonConfig;
	}

	public GtnUIFrameWorkActionConfig getGtnUIFrameWorkColumnGeneratorConfig() {
		return gtnUIFrameWorkColumnGeneratorConfig;
	}

	public void setGtnUIFrameWorkColumnGeneratorConfig(GtnUIFrameWorkActionConfig gtnUIFrameWorkColumnGeneratorConfig) {
		this.gtnUIFrameWorkColumnGeneratorConfig = gtnUIFrameWorkColumnGeneratorConfig;
	}

	public boolean isAuthorizationIncluded() {
		return authorizationIncluded;
	}

	public void setAuthorizationIncluded(boolean authorizationIncluded) {
		this.authorizationIncluded = authorizationIncluded;
	}

	public boolean isRebuild() {
		return rebuild;
	}

	public void setRebuild(boolean rebuild) {
		this.rebuild = rebuild;
	}

	public boolean isDefaultFocus() {
		return defaultFocus;
	}

	public void setDefaultFocus(boolean defaultFocus) {
		this.defaultFocus = defaultFocus;
	}

}