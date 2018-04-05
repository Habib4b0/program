package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.bean;

import java.time.LocalDate;

public class ComponentBinderValidatorBean {
	private String name;
	private String withNullRepresentation;
	private String requiredField;
	private String regexpValidator;
	private String stringLengthValidator;
	private LocalDate dateRequiredField;
	private Object comboBoxRequiredField;
	
	public ComponentBinderValidatorBean(String name, String withNullRepresentation, String requiredField,
			String regexpValidator, String stringLengthValidator, LocalDate dateRequiredField,
			Object comboBoxRequiredField) {
		super();
		this.name = name;
		this.withNullRepresentation = withNullRepresentation;
		this.requiredField = requiredField;
		this.regexpValidator = regexpValidator;
		this.stringLengthValidator = stringLengthValidator;
		this.dateRequiredField = dateRequiredField;
		this.comboBoxRequiredField = comboBoxRequiredField;
	}

	public Object getComboBoxRequiredField() {
		return comboBoxRequiredField;
	}

	public void setComboBoxRequiredField(Object comboBoxRequiredField) {
		this.comboBoxRequiredField = comboBoxRequiredField;
	}

	public String getWithNullRepresentation() {
		return withNullRepresentation;
	}

	public void setWithNullRepresentation(String withNullRepresentation) {
		this.withNullRepresentation = withNullRepresentation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequiredField() {
		return requiredField;
	}

	public void setRequiredField(String requiredField) {
		this.requiredField = requiredField;
	}

	public String getRegexpValidator() {
		return regexpValidator;
	}

	public void setRegexpValidator(String regexpValidator) {
		this.regexpValidator = regexpValidator;
	}

	public String getStringLengthValidator() {
		return stringLengthValidator;
	}

	public void setStringLengthValidator(String stringLengthValidator) {
		this.stringLengthValidator = stringLengthValidator;
	}

	public LocalDate getDateRequiredField() {
		return dateRequiredField;
	}

	public void setDateRequiredField(LocalDate dateRequiredField) {
		this.dateRequiredField = dateRequiredField;
	}

}
