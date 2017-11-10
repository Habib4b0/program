package com.stpl.gtn.gtn2o.ui.framework.component.textarea;

import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;

public class GtnUIFrameworkTextAreaConfig {

	private boolean enable = false;
	private boolean required = false;
	private boolean immediate = false;
	private String inputPrompt = null;
	private int rows = 5;

	private GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = null;

	private String defaultValue = null;

	public GtnUIFrameworkTextAreaConfig() {
		/**
		 * empty constructor
		 */
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public GtnUIFrameworkValidationConfig getGtnUIFrameworkValidationConfig() {
		return gtnUIFrameworkValidationConfig;
	}

	public void setGtnUIFrameworkValidationConfig(GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig) {
		this.gtnUIFrameworkValidationConfig = gtnUIFrameworkValidationConfig;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getInputPrompt() {
		return inputPrompt;
	}

	public void setInputPrompt(String inputPrompt) {
		this.inputPrompt = inputPrompt;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
