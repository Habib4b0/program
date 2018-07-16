/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.abstractConfig;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.checkedcombobox.GtnUIFrameworkCheckedComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.date.GtnUIFrameworkDateFieldConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.pojo.ComponentPojo;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.Arrays;
import java.util.List;
import org.sonar.api.internal.apachecommons.lang.StringUtils;

/**
 *
 * @author Sathya.Seelan
 */
public class GTNFrameworkAbstractComponent {

    protected GtnUIFrameworkComponentConfig getComboBoxComponent(GtnFrameworkComponentConfigProvider componentConfig, List<GtnUIFrameworkComponentConfig> componentList, ComponentPojo componentPojo) {
        GtnUIFrameworkComponentConfig comboBoxLayout = componentConfig.getHorizontalLayoutConfig(componentPojo.getPropertyName() + "Layout",
                true, componentPojo.getParentLayout());
        componentList.add(comboBoxLayout);

        GtnUIFrameworkComponentConfig uiComponentConfig = componentConfig.getUIFrameworkComponentConfig(
                componentPojo.getPropertyName(), true, comboBoxLayout.getComponentId(),
                GtnUIFrameworkComponentType.COMBOBOX);
        uiComponentConfig.setAuthorizationIncluded(true);
        uiComponentConfig.setComponentName(componentPojo.getComponentName());

        GtnUIFrameworkValidationConfig itemStatusValidationConfig = new GtnUIFrameworkValidationConfig();
        itemStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
        uiComponentConfig.setGtnUIFrameworkValidationConfig(itemStatusValidationConfig);

        componentList.add(uiComponentConfig);
        GtnUIFrameworkComboBoxConfig comboBoxConfig = componentConfig.getComboBoxConfig(componentPojo.getListName(),
                GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        uiComponentConfig.setGtnComboboxConfig(comboBoxConfig);
        return uiComponentConfig;
    }

    protected GtnUIFrameworkComponentConfig getComboBoxComponent(GtnFrameworkComponentConfigProvider componentConfig, String objectComponentName, List<GtnUIFrameworkComponentConfig> componentList, String componentName, List listName, String parentLayout, String defaultValue) {
        GtnUIFrameworkComponentConfig comboBoxLayout = componentConfig.getHorizontalLayoutConfig(objectComponentName + "Layout",
                true, parentLayout);
        componentList.add(comboBoxLayout);

        GtnUIFrameworkComponentConfig uiComponentConfig = componentConfig.getUIFrameworkComponentConfig(
                objectComponentName, true, comboBoxLayout.getComponentId(),
                GtnUIFrameworkComponentType.COMBOBOX);
        uiComponentConfig.setAuthorizationIncluded(true);
        uiComponentConfig.setComponentName(componentName);

        GtnUIFrameworkValidationConfig itemStatusValidationConfig = new GtnUIFrameworkValidationConfig();
        itemStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
        uiComponentConfig.setGtnUIFrameworkValidationConfig(itemStatusValidationConfig);

        componentList.add(uiComponentConfig);
        GtnUIFrameworkComboBoxConfig comboBoxConfig = componentConfig.getComboBoxConfig(StringUtils.EMPTY,
                GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        comboBoxConfig.setItemValues(listName);
        comboBoxConfig.setDefaultValue(defaultValue);
        uiComponentConfig.setGtnComboboxConfig(comboBoxConfig);
        return uiComponentConfig;
    }

    protected GtnUIFrameworkComponentConfig getMenuBarComponent(GtnFrameworkComponentConfigProvider componentConfig, String objectComponentName, List<GtnUIFrameworkComponentConfig> componentList, String componentName, String listName, String parentLayout) {
        GtnUIFrameworkComponentConfig comboBoxLayout = componentConfig.getHorizontalLayoutConfig(objectComponentName + "Layout",
                true, parentLayout);
        componentList.add(comboBoxLayout);

        GtnUIFrameworkComponentConfig uiComponentConfig = componentConfig.getUIFrameworkComponentConfig(
                objectComponentName, true, comboBoxLayout.getComponentId(),
                GtnUIFrameworkComponentType.CHECKEDCOMBOBOX);
        uiComponentConfig.setAuthorizationIncluded(true);
        uiComponentConfig.setComponentName(componentName);
        uiComponentConfig.addComponentStyle("custommenumulticheck");

        GtnUIFrameworkValidationConfig menuBarValidation = new GtnUIFrameworkValidationConfig();
        menuBarValidation.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
        uiComponentConfig.setGtnUIFrameworkValidationConfig(menuBarValidation);

        componentList.add(uiComponentConfig);
        GtnUIFrameworkCheckedComboBoxConfig comboBoxConfig = new GtnUIFrameworkCheckedComboBoxConfig();
        comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        comboBoxConfig.setCheckedComboBoxType(listName);
        comboBoxConfig.setDefaultValue("Select Variables");
        uiComponentConfig.setGtnCheckedComboboxConfig(comboBoxConfig);
        return uiComponentConfig;
    }

    protected void getTextBoxComponent(GtnFrameworkComponentConfigProvider componentConfig, String objectComponentName, List<GtnUIFrameworkComponentConfig> componentList, String componentName, String parentLayout) {
        GtnUIFrameworkComponentConfig textFieldLayout = componentConfig.getHorizontalLayoutConfig(
                objectComponentName + "layout", true, parentLayout);
        textFieldLayout.addComponentStyle(GtnFrameworkCssConstants.GRID_SINGLE_IN_LAYOUT);
        componentList.add(textFieldLayout);

        GtnUIFrameworkComponentConfig textFieldConfig = componentConfig.getUIFrameworkComponentConfig(
                objectComponentName, true, textFieldLayout.getComponentId(),
                GtnUIFrameworkComponentType.TEXTBOX);
        textFieldConfig.setAuthorizationIncluded(true);
        textFieldConfig.setComponentName(componentName);

        GtnUIFrameworkValidationConfig validationsConfig = new GtnUIFrameworkValidationConfig();
        validationsConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
        textFieldConfig.setGtnUIFrameworkValidationConfig(validationsConfig);

        componentList.add(textFieldConfig);
    }

    protected void getPopupFieldComponent(GtnFrameworkComponentConfigProvider componentConfig, String objectComponentName, List<GtnUIFrameworkComponentConfig> componentList, String componentName, String parentLayout) {
        GtnUIFrameworkComponentConfig identifierStartDateLayout = componentConfig.getHorizontalLayoutConfig(objectComponentName + "layout", true,
                parentLayout);
        identifierStartDateLayout.addComponentStyle(GtnFrameworkCssConstants.GRID_SINGLE_IN_LAYOUT);
        componentList.add(identifierStartDateLayout);

        GtnUIFrameworkComponentConfig itemIdentifier = componentConfig.getUIFrameworkComponentConfig(
                objectComponentName, true, identifierStartDateLayout.getComponentId(),
                GtnUIFrameworkComponentType.DATEFIELD);
        itemIdentifier.setAuthorizationIncluded(true);
        itemIdentifier.setComponentName(componentName);
        componentList.add(itemIdentifier);
        GtnUIFrameworkDateFieldConfig itemIdentifierFieldConfig = new GtnUIFrameworkDateFieldConfig();
        itemIdentifierFieldConfig.setImmediate(true);
        itemIdentifier.setGtnDateFieldConfig(itemIdentifierFieldConfig);

        GtnUIFrameworkValidationConfig itemIdentifierValConfig = componentConfig.getValidationConfig(
                Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL), true, "Date format not recognized",
                GtnFrameworkRegexStringConstants.DATE_FORMAT);
        itemIdentifier.setGtnUIFrameworkValidationConfig(itemIdentifierValConfig);
    }
}
