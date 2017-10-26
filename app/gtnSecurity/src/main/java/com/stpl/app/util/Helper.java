package com.stpl.app.util;

import java.io.Serializable;

public class Helper implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7537600484300528336L;
	private String code;
	private String description;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return description;
	}
	

}
