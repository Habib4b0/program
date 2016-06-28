package com.stpl.app.security.businessRoleModuleMaster.util;

public class UIUtils {

    public static final Object[] BUSINESS_ROLE_MODULE_MASTER_COL = new Object[]{
        "moduleName", "submoduleName", "function", "categoryName","tabName", "access"};
    public static final Object[] BUSINESS_ROLE_MODULE_MASTER_FIELDS = new Object[]{
        "moduleName", "submoduleName", "fieldName","categoryName","tabName", "add", "edit", "view"};
    public static final Object[] BUSINESS_ROLE_MODULE_MASTER_FIELDS_VIEW = new Object[]{
        "moduleName", "submoduleName", "fieldName","categoryName", "view"};

    /**
     * The Constant BUSINESS_ROLE_MODULE_MASTER_HEADER.
     */
    public static final String[] BUSINESS_ROLE_MODULE_MASTER_HEADER_VIEW = new String[]{
        "Module Name", "Submodule Name", "Field Name","Category Name", "View"};
    public static final String[] BUSINESS_ROLE_MODULE_MASTER_FUNCTION_HEADER = new String[]{
        "Module Name", "Submodule Name", "Function", "Category Name","Tab Name", "Access"};
    public static final String[] BUSINESS_ROLE_MODULE_MASTER_FIELDS_HEADER = new String[]{
        "Module Name", "Submodule Name", "Field Name", "Category Name","Tab Name", "Add", "Edit", "View"};
}
