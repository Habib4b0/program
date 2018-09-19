package com.stpl.app.security.businessrolemodulemaster.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

//@Data
public class SearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8809821204092760951L;

	private int systemId;
	private String businessroleName = StringUtils.EMPTY;
	private String submoduleName = StringUtils.EMPTY;
	private String moduleName = StringUtils.EMPTY;
	private String moduleNameInGrid = StringUtils.EMPTY;
	private String function = StringUtils.EMPTY;
	private String type = StringUtils.EMPTY;

	public SearchDTO(){
		super();
	}
	
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getBusinessroleName() {
        return businessroleName;
    }

    public void setBusinessroleName(String businessroleName) {
        this.businessroleName = businessroleName;
    }

    public String getSubmoduleName() {
        return submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        this.submoduleName = submoduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleNameInGrid() {
        return moduleNameInGrid;
    }

    public void setModuleNameInGrid(String moduleNameInGrid) {
        this.moduleNameInGrid = moduleNameInGrid;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(Boolean searchFlag) {
        this.searchFlag = searchFlag;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
	private String access = StringUtils.EMPTY;

	private String errorMsg = StringUtils.EMPTY;
	private Boolean searchFlag;
	private String successMessage;
	

}
