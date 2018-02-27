package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util;

public class GtnUIFrameworkNsfFormulaType {
	private String formulaTypeValue = null;
	private boolean changeAllowed = true;

	private static GtnUIFrameworkNsfFormulaType instance = null;

	private GtnUIFrameworkNsfFormulaType() {

	}

	public static GtnUIFrameworkNsfFormulaType getInstance() {
		if (instance == null) {
			instance = new GtnUIFrameworkNsfFormulaType();
		}
		return instance;
	}

	public String getFormulaTypeValue() {
		return formulaTypeValue;
	}

	public void setFormulaTypeValue(String formulaTypeValue) {
		this.formulaTypeValue = formulaTypeValue;
	}

	public boolean isChangeAllowed() {
		return changeAllowed;
	}

	public void setChangeAllowed(boolean changeAllowed) {
		this.changeAllowed = changeAllowed;
	}
	
}
