package com.stpl.gtn.gtn2o.ui.framework.component.textbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;

public class GtnUIFrameworkTextBoxConfig {
	public GtnUIFrameworkTextBoxConfig() {
		super();
	}

	private boolean enable = true;
	private boolean required = false;
	private boolean immediate = true;
	private boolean readOnly = false;
	private String requiredMessage = null;
	private boolean valueLoadFromService = false;
	private String loadingUrl = null;
	private GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = null;
	private List<GtnUIFrameWorkActionConfig> valueChangeActionConfigList = new ArrayList<>();
	private List<GtnUIFrameWorkActionConfig> blurActionConfigList;
	private int maximumLength = 0;
	private boolean nullSettingAllowed = false;
	private String nullRepresentation = "";
	private boolean passwordField = false;

	public List<GtnUIFrameWorkActionConfig> getBlurActionConfigList() {
		return blurActionConfigList == null ? blurActionConfigList : Collections.unmodifiableList(blurActionConfigList);
	}

	public void setBlurActionConfigList(List<GtnUIFrameWorkActionConfig> blurActionConfigList) {
		this.blurActionConfigList = new ArrayList<>(blurActionConfigList);
	}

	public void addBlurActionConfigList(GtnUIFrameWorkActionConfig actionBlurConfig) {
		if (this.blurActionConfigList == null) {
			this.blurActionConfigList = new ArrayList<>();
		}
		this.blurActionConfigList.add(actionBlurConfig);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getRequiredMessage() {
		return requiredMessage;
	}

	public void setRequiredMessage(String requiredMessage) {
		this.requiredMessage = requiredMessage;
	}

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public boolean isValueLoadFromService() {
		return valueLoadFromService;
	}

	public void setValueLoadFromService(boolean valueLoadFromService) {
		this.valueLoadFromService = valueLoadFromService;
	}

	public GtnUIFrameworkValidationConfig getGtnUIFrameworkValidationConfig() {
		return gtnUIFrameworkValidationConfig;
	}

	public void setGtnUIFrameworkValidationConfig(GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig) {
		this.gtnUIFrameworkValidationConfig = gtnUIFrameworkValidationConfig;
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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getLoadingUrl() {
		return loadingUrl;
	}

	public void setLoadingUrl(String loadingUrl) {
		this.loadingUrl = loadingUrl;
	}

	public int getMaximumLength() {
		return maximumLength;
	}

	public void setMaximumLength(int maximumLength) {
		this.maximumLength = maximumLength;
	}

	public boolean isNullSettingAllowed() {
		return nullSettingAllowed;
	}

	public void setNullSettingAllowed(boolean nullSettingAllowed) {
		this.nullSettingAllowed = nullSettingAllowed;
	}

	public String getNullRepresentation() {
		return nullRepresentation;
	}

	public void setNullRepresentation(String nullRepresentation) {
		this.nullRepresentation = nullRepresentation;
	}

	public boolean isPasswordField() {
		return passwordField;
	}

	public void setPasswordField(boolean passwordField) {
		this.passwordField = passwordField;
	}

}
