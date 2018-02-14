package com.stpl.app.bpm.dto;

import java.io.Serializable;

public class WorkflowRuleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean allowToExecute=true;
	
	private int noOfUsers;
	
	
	public Boolean getAllowToExecute() {
		return allowToExecute;
	}
	public void setAllowToExecute(Boolean allowToExecute) {
		this.allowToExecute = allowToExecute;
	}
	public int getNoOfUsers() {
		return noOfUsers;
	}
	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}
	
	
}
