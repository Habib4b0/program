package com.stpl.app.security.businessRoleModuleMaster.dto;

import java.io.Serializable;


import org.apache.commons.lang.StringUtils;

/**
 * A DTO object which is used as a form object in create user and edit user
 * forms.
 * @author manikanta
 */
//@Data
public class BusinessRoleModuleMasterDTO implements Serializable{
 
    private String businessroleName = StringUtils.EMPTY;
    private String submoduleName = StringUtils.EMPTY;
    private String moduleName = StringUtils.EMPTY;
    private String moduleNameInGrid = StringUtils.EMPTY;
    private String function = StringUtils.EMPTY;
    private String type = StringUtils.EMPTY;
    private String access = StringUtils.EMPTY;
    
	private static final long serialVersionUID = -3853950616477782170L;

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
}