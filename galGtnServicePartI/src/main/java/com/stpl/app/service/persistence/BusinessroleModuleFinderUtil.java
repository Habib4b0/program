package com.stpl.app.service.persistence;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;


public class BusinessroleModuleFinderUtil {
    private static BusinessroleModuleFinder _finder;

    public static java.util.List getBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getFinder()
                   .getBusinessFunctionPermission(businessRoleId, moduleName);
    }

    public static java.util.List getBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getFinder().getBusinessFieldPermission(businessRoleId, moduleName);
    }

    public static java.util.List getBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getFinder().getBusinessTabPermission(businessRoleId, moduleName);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1) {
        return getFinder().executeSelectQuery(query, udc1);
    }

    public static java.util.List getContractBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getFinder()
                   .getContractBusinessFunctionPermission(businessRoleId,
            moduleName);
    }

    public static java.util.List getContractBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getFinder()
                   .getContractBusinessFieldPermission(businessRoleId,
            moduleName);
    }

    public static java.util.List getContractBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getFinder()
                   .getContractBusinessTabPermission(businessRoleId, moduleName);
    }

    public static java.util.List findModuleAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getFinder()
                   .findModuleAccessDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static java.util.List findsubmodulePropertyDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getFinder()
                   .findsubmodulePropertyDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static java.util.List findFieldAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getFinder()
                   .findFieldAccessDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static java.util.List findSubModuleFieldDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getFinder()
                   .findSubModuleFieldDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static BusinessroleModuleFinder getFinder() {
        if (_finder == null) {
            _finder = (BusinessroleModuleFinder) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    BusinessroleModuleFinder.class.getName());

            ReferenceRegistry.registerReference(BusinessroleModuleFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(BusinessroleModuleFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(BusinessroleModuleFinderUtil.class,
            "_finder");
    }
}
