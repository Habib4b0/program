package com.stpl.gtn.gtn2o.ui.framework.action.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;

public class GtnUIFrameworkValidationConfig {

	private int maxLength = 0;

	private boolean attachRegxValidatior = false;

	private boolean attachLengthValidatior = false;

	private boolean fieldValidationVisible = true;

	private String regxValidationMessage;
	private String lengthValidationMessage;

	private List<GtnUIFrameworkConditionalValidationType> conditionList = null;

	private List<String> customServiceValidations = null;

	private String formatString = null;

	private String validationURL = null;

	private int minSize;

	public GtnUIFrameworkValidationConfig() {
		super();
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getFormatString() {
		return formatString;
	}

	public void setFormatString(String formatString) {
		this.formatString = formatString;
	}

	public String getValidationURL() {
		return validationURL;
	}

	public void setValidationURL(String validationURL) {
		this.validationURL = validationURL;
	}

	public List<GtnUIFrameworkConditionalValidationType> getConditionList() {
		return conditionList == null ? conditionList : Collections.unmodifiableList(conditionList);
	}

	public void setConditionList(List<GtnUIFrameworkConditionalValidationType> conditionList) {
		this.conditionList = new ArrayList<>(conditionList);
	}

	public List<String> getCustomServiceValidations() {
		return customServiceValidations == null ? customServiceValidations
				: Collections.unmodifiableList(customServiceValidations);
	}

	public void setCustomServiceValidations(List<String> customServiceValidations) {
		this.customServiceValidations = new ArrayList<>(customServiceValidations);
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public boolean isAttachRegxValidatior() {
		return attachRegxValidatior;
	}

	public void setAttachRegxValidatior(boolean attachRegxValidatior) {
		this.attachRegxValidatior = attachRegxValidatior;
	}

	public boolean isAttachLengthValidatior() {
		return attachLengthValidatior;
	}

	public void setAttachLengthValidatior(boolean attachLengthValidatior) {
		this.attachLengthValidatior = attachLengthValidatior;
	}

	public boolean isFieldValidationVisible() {
		return fieldValidationVisible;
	}

	public void setFieldValidationVisible(boolean fieldValidationVisible) {
		this.fieldValidationVisible = fieldValidationVisible;
	}

	public String getRegxValidationMessage() {
		return regxValidationMessage;
	}

	public void setRegxValidationMessage(String regxValidationMessage) {
		this.regxValidationMessage = regxValidationMessage;
	}

	public String getLengthValidationMessage() {
		return lengthValidationMessage;
	}

	public void setLengthValidationMessage(String lengthValidationMessage) {
		this.lengthValidationMessage = lengthValidationMessage;
	}

}