/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.inlinedate;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnUIFrameworkInlineDateFieldConfig {

    private boolean enable = true;
    private boolean required;
    private boolean immediate = true;
    private String requiredMessage;
    private boolean valueLoadFromService;
    private String loadingUrl;
    private List<GtnUIFrameWorkActionConfig> valueChangeActionConfigList = new ArrayList<>();
    private List<GtnUIFrameWorkActionConfig> validationActionConfigList = new ArrayList<>();

    public GtnUIFrameworkInlineDateFieldConfig() {
        super();
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isImmediate() {
        return immediate;

    }

    public void setImmediate(boolean immediate) {
        this.immediate = immediate;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isValueLoadFromService() {
        return valueLoadFromService;
    }

    public void setValueLoadFromService(boolean valueLoadFromService) {
        this.valueLoadFromService = valueLoadFromService;
    }

    public String getLoadingUrl() {
        return loadingUrl;
    }

    public void setLoadingUrl(String loadingUrl) {
        this.loadingUrl = loadingUrl;
    }

    public List<GtnUIFrameWorkActionConfig> getValidationActionConfigList() {
        return validationActionConfigList == null ? validationActionConfigList
                : Collections.unmodifiableList(validationActionConfigList);
    }

    public void setValidationActionConfigList(List<GtnUIFrameWorkActionConfig> validationActionConfigList) {
        this.validationActionConfigList = new ArrayList<>(validationActionConfigList);
    }

    public void addValueChangeActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
        if (this.valueChangeActionConfigList == null) {
            this.valueChangeActionConfigList = new ArrayList<>();
        }
        this.valueChangeActionConfigList.add(actionConfig);
    }

    public List<GtnUIFrameWorkActionConfig> getValueChangeActionConfigList() {
        return valueChangeActionConfigList == null ? valueChangeActionConfigList
                : Collections.unmodifiableList(valueChangeActionConfigList);
    }

    public void setValueChangeActionConfigList(List<GtnUIFrameWorkActionConfig> valueChangeActionConfigList) {
        this.valueChangeActionConfigList = new ArrayList<>(valueChangeActionConfigList);
    }

    public String getRequiredMessage() {
        return requiredMessage;
    }

    public void setRequiredMessage(String requiredMessage) {
        this.requiredMessage = requiredMessage;
    }
}
