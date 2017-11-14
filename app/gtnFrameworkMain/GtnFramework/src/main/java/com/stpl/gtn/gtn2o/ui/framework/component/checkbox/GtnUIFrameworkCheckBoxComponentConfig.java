/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.checkbox;

/**
 *
 * @author Harlin.Mani
 */
public class GtnUIFrameworkCheckBoxComponentConfig {
	private boolean enable = true;
	private boolean required = false;
	private boolean immediate = false;
	private boolean readOnly = false;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
