package com.stpl.app.security.businessRoleModuleMaster.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

public class SearchBusinessRoleModuleForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private int systemId;
    private String businessRoleName = StringUtils.EMPTY;
    private String submoduleName = StringUtils.EMPTY;
    private String moduleName = StringUtils.EMPTY;
    private String moduleNameInGrid = StringUtils.EMPTY;
    private String function = StringUtils.EMPTY;
    private String type = StringUtils.EMPTY;
    private boolean access = false;
    private boolean add = false;
    private boolean edit = false;
    private boolean view = false;
    private boolean nullFlag = false;
    private String fieldName = StringUtils.EMPTY;
    private String tabName = StringUtils.EMPTY;
    private String categoryName = StringUtils.EMPTY;
    private boolean searchFlag = false;
    private String successMessage = StringUtils.EMPTY;
    private String submodulePropertyId = StringUtils.EMPTY;
    private int businessroleMasterSid;
    
    public SearchBusinessRoleModuleForm(){
    	super();
    }
    
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getBusinessRoleName() {
        return businessRoleName;
    }

    public void setBusinessRoleName(String businessRoleName) {
        this.businessRoleName = businessRoleName;
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

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public Boolean getAdd() {
        return add;
    }

    public void setAdd(Boolean add) {
        this.add = add;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public Boolean getNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(Boolean nullFlag) {
        this.nullFlag = nullFlag;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getSubmodulePropertyId() {
        return submodulePropertyId;
    }

    public void setSubmodulePropertyId(String submodulePropertyId) {
        this.submodulePropertyId = submodulePropertyId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getBusinessroleMasterSid() {
        return businessroleMasterSid;
    }

    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        this.businessroleMasterSid = businessroleMasterSid;
    }

}
