/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.datatype;

import java.io.Serializable;

/**
 *
 * @author Abishek.Ram
 */
public enum GtnFrameworkDataType implements Serializable {
	STRING("String"), DATE("Date"), INTEGER("Integer"), LIST("List"), NULL_ALLOWED("Null_allowed"), DOUBLE("Double"), IN_LIST("In_Lists");

	private String constant;

	private GtnFrameworkDataType(String constant) {
		this.constant = constant;
	}

	public String getConstant() {
		return constant;
	}

}
