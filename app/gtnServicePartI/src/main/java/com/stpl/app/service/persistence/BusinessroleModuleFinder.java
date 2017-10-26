package com.stpl.app.service.persistence;

public interface BusinessroleModuleFinder {
    public java.util.List getBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName);

    public java.util.List getBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName);

    public java.util.List getBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName);

    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1);

    public java.util.List getContractBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName);

    public java.util.List getContractBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName);

    public java.util.List getContractBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName);

    public java.util.List findModuleAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName);

    public java.util.List findsubmodulePropertyDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName);

    public java.util.List findFieldAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName);

    public java.util.List findSubModuleFieldDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName);
}
