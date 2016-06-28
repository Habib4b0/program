package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for BusinessroleModule. This utility wraps
 * {@link com.stpl.app.service.impl.BusinessroleModuleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see BusinessroleModuleLocalService
 * @see com.stpl.app.service.base.BusinessroleModuleLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.BusinessroleModuleLocalServiceImpl
 * @generated
 */
public class BusinessroleModuleLocalServiceUtil {
    private static BusinessroleModuleLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.BusinessroleModuleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the businessrole module to the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModule the businessrole module
    * @return the businessrole module that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleModule addBusinessroleModule(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addBusinessroleModule(businessroleModule);
    }

    /**
    * Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
    *
    * @param businessroleModuleSid the primary key for the new businessrole module
    * @return the new businessrole module
    */
    public static com.stpl.app.model.BusinessroleModule createBusinessroleModule(
        int businessroleModuleSid) {
        return getService().createBusinessroleModule(businessroleModuleSid);
    }

    /**
    * Deletes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module that was removed
    * @throws PortalException if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleModule deleteBusinessroleModule(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteBusinessroleModule(businessroleModuleSid);
    }

    /**
    * Deletes the businessrole module from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModule the businessrole module
    * @return the businessrole module that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleModule deleteBusinessroleModule(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteBusinessroleModule(businessroleModule);
    }

    public static com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.stpl.app.model.BusinessroleModule fetchBusinessroleModule(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchBusinessroleModule(businessroleModuleSid);
    }

    /**
    * Returns the businessrole module with the primary key.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module
    * @throws PortalException if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleModule getBusinessroleModule(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getBusinessroleModule(businessroleModuleSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the businessrole modules.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole modules
    * @param end the upper bound of the range of businessrole modules (not inclusive)
    * @return the range of businessrole modules
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BusinessroleModule> getBusinessroleModules(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getBusinessroleModules(start, end);
    }

    /**
    * Returns the number of businessrole modules.
    *
    * @return the number of businessrole modules
    * @throws SystemException if a system exception occurred
    */
    public static int getBusinessroleModulesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getBusinessroleModulesCount();
    }

    /**
    * Updates the businessrole module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param businessroleModule the businessrole module
    * @return the businessrole module that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BusinessroleModule updateBusinessroleModule(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateBusinessroleModule(businessroleModule);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List getBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getService()
                   .getBusinessFunctionPermission(businessRoleId, moduleName);
    }

    public static java.util.List getBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getService()
                   .getBusinessFieldPermission(businessRoleId, moduleName);
    }

    public static java.util.List getBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getService().getBusinessTabPermission(businessRoleId, moduleName);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1) {
        return getService().executeSelectQuery(query, udc1);
    }

    public static java.util.List getContractBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getService()
                   .getContractBusinessFunctionPermission(businessRoleId,
            moduleName);
    }

    public static java.util.List getContractBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getService()
                   .getContractBusinessFieldPermission(businessRoleId,
            moduleName);
    }

    public static java.util.List getContractBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return getService()
                   .getContractBusinessTabPermission(businessRoleId, moduleName);
    }

    public static java.util.List findFieldAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getService()
                   .findFieldAccessDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static java.util.List findSubModuleFieldDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getService()
                   .findSubModuleFieldDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static java.util.List findModuleAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getService()
                   .findModuleAccessDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static java.util.List findsubmodulePropertyDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return getService()
                   .findsubmodulePropertyDetails(businessRoleName, moduleName,
            subModuleName);
    }

    public static void clearService() {
        _service = null;
    }

    public static BusinessroleModuleLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    BusinessroleModuleLocalService.class.getName());

            if (invokableLocalService instanceof BusinessroleModuleLocalService) {
                _service = (BusinessroleModuleLocalService) invokableLocalService;
            } else {
                _service = new BusinessroleModuleLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(BusinessroleModuleLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(BusinessroleModuleLocalService service) {
    }
}
