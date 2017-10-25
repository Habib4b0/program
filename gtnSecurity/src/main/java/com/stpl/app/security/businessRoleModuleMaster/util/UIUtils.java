package com.stpl.app.security.businessRoleModuleMaster.util;

public class UIUtils {

    public static final Object[] BUSINESS_ROLE_MODULE_MASTER_COL = new Object[]{
        CommonUtils.MODULE_NAME, CommonUtils.SUB_MODULE_NAME, "function", CommonUtils.CATEGORY_NAME,"tabName", "access"};
    public static final Object[] BUSINESS_ROLE_MODULE_MASTER_FIELDS = new Object[]{
        CommonUtils.MODULE_NAME, CommonUtils.SUB_MODULE_NAME, "fieldName", CommonUtils.CATEGORY_NAME,"tabName", "add", "edit", "view"};
    public static final Object[] BUSINESS_ROLE_MODULE_MASTER_FIELDS_VIEW = new Object[]{
        CommonUtils.MODULE_NAME, CommonUtils.SUB_MODULE_NAME, "fieldName",CommonUtils.CATEGORY_NAME, "view"};

    /**
     * The Constant BUSINESS_ROLE_MODULE_MASTER_HEADER.
     */
    public static final String[] BUSINESS_ROLE_MODULE_MASTER_HEADER_VIEW = new String[]{
        CommonUtils.MODULE_SPACE_NAME, CommonUtils.SUBMODULE_SPACE_NAME, "Field Name",CommonUtils.CATEGORY_SPACE_NAME, "View"};
    public static final String[] BUSINESS_ROLE_MODULE_MASTER_FUNCTION_HEADER = new String[]{
        CommonUtils.MODULE_SPACE_NAME, CommonUtils.SUBMODULE_SPACE_NAME, "Function", CommonUtils.CATEGORY_SPACE_NAME,"Tab Name", "Access"};
    public static final String[] BUSINESS_ROLE_MODULE_MASTER_FIELDS_HEADER = new String[]{
        CommonUtils.MODULE_SPACE_NAME, CommonUtils.SUBMODULE_SPACE_NAME, "Field Name", CommonUtils.CATEGORY_SPACE_NAME,"Tab Name", "Add", "Edit", "View"};
}
