package com.stpl.app.security.businessrolemodulemaster.util;

public class UIUtils {

    public final Object[] businessRoleModuleMasterCol = new Object[]{
        CommonUtils.MODULE_NAME, CommonUtils.SUB_MODULE_NAME, "function", CommonUtils.CATEGORY_NAME,"tabName", "access"};
    public final Object[] businessRoleModuleMasterFields = new Object[]{
        CommonUtils.MODULE_NAME, CommonUtils.SUB_MODULE_NAME, "fieldName", CommonUtils.CATEGORY_NAME,"tabName", "add", "edit", "view"};
    public final Object[] businessRoleModuleMasterFieldsView = new Object[]{
        CommonUtils.MODULE_NAME, CommonUtils.SUB_MODULE_NAME, "fieldName",CommonUtils.CATEGORY_NAME, "view"};

    /**
     * The Constant BUSINESS_ROLE_MODULE_MASTER_HEADER.
     */
    public final String[] businessRoleModuleMasterHeaderView = new String[]{
        CommonUtils.MODULE_SPACE_NAME, CommonUtils.SUBMODULE_SPACE_NAME, "Field Name",CommonUtils.CATEGORY_SPACE_NAME, "View"};
    public final String[] businessRoleModuleMasterFunctionHeader = new String[]{
        CommonUtils.MODULE_SPACE_NAME, CommonUtils.SUBMODULE_SPACE_NAME, "Function", CommonUtils.CATEGORY_SPACE_NAME,"Tab Name", "Access"};
    public final String[] businessRoleModuleMasterFieldsHeader = new String[]{
        CommonUtils.MODULE_SPACE_NAME, CommonUtils.SUBMODULE_SPACE_NAME, "Field Name", CommonUtils.CATEGORY_SPACE_NAME,"Tab Name", "Add", "Edit", "View"};
    
    private static UIUtils object;
    /**
     * Constructor
     */
    private UIUtils() {
        /*
            Constructor
        */
    }

    public static UIUtils getInstance() {
        if (object == null) {
            object = new UIUtils();
        }
        return object;
    }
}
