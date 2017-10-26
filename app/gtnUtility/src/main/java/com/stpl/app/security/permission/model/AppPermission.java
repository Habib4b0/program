package com.stpl.app.security.permission.model;

/**
 * Handles Security Permission
 * @author shrihariharan
 */
public class AppPermission {
    private String moduleName;   
    private String propertyName;
    private boolean addFlag;
    private boolean editFlag;
    private boolean viewFlag;
    private boolean functionFlag;
    private boolean tabFlag;
    private boolean searchFlag; 

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isAddFlag() {
        return addFlag;
    }

    public void setAddFlag(boolean addFlag) {
        this.addFlag = addFlag;
    }

    public boolean isEditFlag() {
        return editFlag;
    }

    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    public boolean isViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(boolean viewFlag) {
        this.viewFlag = viewFlag;
    }

    public boolean isFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag(boolean functionFlag) {
        this.functionFlag = functionFlag;
    }

    public boolean isTabFlag() {
        return tabFlag;
    }

    public void setTabFlag(boolean tabFlag) {
        this.tabFlag = tabFlag;
    }

    public boolean isSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(boolean searchFlag) {
        this.searchFlag = searchFlag;
    }
}
