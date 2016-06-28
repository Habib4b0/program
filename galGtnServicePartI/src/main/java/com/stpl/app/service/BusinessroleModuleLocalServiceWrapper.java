package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BusinessroleModuleLocalService}.
 *
 * @author
 * @see BusinessroleModuleLocalService
 * @generated
 */
public class BusinessroleModuleLocalServiceWrapper
    implements BusinessroleModuleLocalService,
        ServiceWrapper<BusinessroleModuleLocalService> {
    private BusinessroleModuleLocalService _businessroleModuleLocalService;

    public BusinessroleModuleLocalServiceWrapper(
        BusinessroleModuleLocalService businessroleModuleLocalService) {
        _businessroleModuleLocalService = businessroleModuleLocalService;
    }

    /**
    * Adds the businessrole module to the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModule the businessrole module
    * @return the businessrole module that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleModule addBusinessroleModule(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.addBusinessroleModule(businessroleModule);
    }

    /**
    * Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
    *
    * @param businessroleModuleSid the primary key for the new businessrole module
    * @return the new businessrole module
    */
    @Override
    public com.stpl.app.model.BusinessroleModule createBusinessroleModule(
        int businessroleModuleSid) {
        return _businessroleModuleLocalService.createBusinessroleModule(businessroleModuleSid);
    }

    /**
    * Deletes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module that was removed
    * @throws PortalException if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleModule deleteBusinessroleModule(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.deleteBusinessroleModule(businessroleModuleSid);
    }

    /**
    * Deletes the businessrole module from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleModule the businessrole module
    * @return the businessrole module that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleModule deleteBusinessroleModule(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.deleteBusinessroleModule(businessroleModule);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _businessroleModuleLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.BusinessroleModule fetchBusinessroleModule(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.fetchBusinessroleModule(businessroleModuleSid);
    }

    /**
    * Returns the businessrole module with the primary key.
    *
    * @param businessroleModuleSid the primary key of the businessrole module
    * @return the businessrole module
    * @throws PortalException if a businessrole module with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleModule getBusinessroleModule(
        int businessroleModuleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.getBusinessroleModule(businessroleModuleSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.stpl.app.model.BusinessroleModule> getBusinessroleModules(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.getBusinessroleModules(start, end);
    }

    /**
    * Returns the number of businessrole modules.
    *
    * @return the number of businessrole modules
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getBusinessroleModulesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.getBusinessroleModulesCount();
    }

    /**
    * Updates the businessrole module in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param businessroleModule the businessrole module
    * @return the businessrole module that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleModule updateBusinessroleModule(
        com.stpl.app.model.BusinessroleModule businessroleModule)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleModuleLocalService.updateBusinessroleModule(businessroleModule);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _businessroleModuleLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _businessroleModuleLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _businessroleModuleLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List getBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return _businessroleModuleLocalService.getBusinessFunctionPermission(businessRoleId,
            moduleName);
    }

    @Override
    public java.util.List getBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return _businessroleModuleLocalService.getBusinessFieldPermission(businessRoleId,
            moduleName);
    }

    @Override
    public java.util.List getBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return _businessroleModuleLocalService.getBusinessTabPermission(businessRoleId,
            moduleName);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1) {
        return _businessroleModuleLocalService.executeSelectQuery(query, udc1);
    }

    @Override
    public java.util.List getContractBusinessFunctionPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return _businessroleModuleLocalService.getContractBusinessFunctionPermission(businessRoleId,
            moduleName);
    }

    @Override
    public java.util.List getContractBusinessFieldPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return _businessroleModuleLocalService.getContractBusinessFieldPermission(businessRoleId,
            moduleName);
    }

    @Override
    public java.util.List getContractBusinessTabPermission(
        java.lang.String businessRoleId, java.lang.String moduleName) {
        return _businessroleModuleLocalService.getContractBusinessTabPermission(businessRoleId,
            moduleName);
    }

    @Override
    public java.util.List findFieldAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return _businessroleModuleLocalService.findFieldAccessDetails(businessRoleName,
            moduleName, subModuleName);
    }

    @Override
    public java.util.List findSubModuleFieldDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return _businessroleModuleLocalService.findSubModuleFieldDetails(businessRoleName,
            moduleName, subModuleName);
    }

    @Override
    public java.util.List findModuleAccessDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return _businessroleModuleLocalService.findModuleAccessDetails(businessRoleName,
            moduleName, subModuleName);
    }

    @Override
    public java.util.List findsubmodulePropertyDetails(
        java.lang.String businessRoleName, java.lang.String moduleName,
        java.lang.String subModuleName) {
        return _businessroleModuleLocalService.findsubmodulePropertyDetails(businessRoleName,
            moduleName, subModuleName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BusinessroleModuleLocalService getWrappedBusinessroleModuleLocalService() {
        return _businessroleModuleLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBusinessroleModuleLocalService(
        BusinessroleModuleLocalService businessroleModuleLocalService) {
        _businessroleModuleLocalService = businessroleModuleLocalService;
    }

    @Override
    public BusinessroleModuleLocalService getWrappedService() {
        return _businessroleModuleLocalService;
    }

    @Override
    public void setWrappedService(
        BusinessroleModuleLocalService businessroleModuleLocalService) {
        _businessroleModuleLocalService = businessroleModuleLocalService;
    }
}
