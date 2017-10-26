package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModuleSubmoduleMasterLocalService}.
 *
 * @author
 * @see ModuleSubmoduleMasterLocalService
 * @generated
 */
public class ModuleSubmoduleMasterLocalServiceWrapper
    implements ModuleSubmoduleMasterLocalService,
        ServiceWrapper<ModuleSubmoduleMasterLocalService> {
    private ModuleSubmoduleMasterLocalService _moduleSubmoduleMasterLocalService;

    public ModuleSubmoduleMasterLocalServiceWrapper(
        ModuleSubmoduleMasterLocalService moduleSubmoduleMasterLocalService) {
        _moduleSubmoduleMasterLocalService = moduleSubmoduleMasterLocalService;
    }

    /**
    * Adds the module submodule master to the database. Also notifies the appropriate model listeners.
    *
    * @param moduleSubmoduleMaster the module submodule master
    * @return the module submodule master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster addModuleSubmoduleMaster(
        com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.addModuleSubmoduleMaster(moduleSubmoduleMaster);
    }

    /**
    * Creates a new module submodule master with the primary key. Does not add the module submodule master to the database.
    *
    * @param moduleSubmoduleSid the primary key for the new module submodule master
    * @return the new module submodule master
    */
    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster createModuleSubmoduleMaster(
        int moduleSubmoduleSid) {
        return _moduleSubmoduleMasterLocalService.createModuleSubmoduleMaster(moduleSubmoduleSid);
    }

    /**
    * Deletes the module submodule master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param moduleSubmoduleSid the primary key of the module submodule master
    * @return the module submodule master that was removed
    * @throws PortalException if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster deleteModuleSubmoduleMaster(
        int moduleSubmoduleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.deleteModuleSubmoduleMaster(moduleSubmoduleSid);
    }

    /**
    * Deletes the module submodule master from the database. Also notifies the appropriate model listeners.
    *
    * @param moduleSubmoduleMaster the module submodule master
    * @return the module submodule master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster deleteModuleSubmoduleMaster(
        com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.deleteModuleSubmoduleMaster(moduleSubmoduleMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _moduleSubmoduleMasterLocalService.dynamicQuery();
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
        return _moduleSubmoduleMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _moduleSubmoduleMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _moduleSubmoduleMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _moduleSubmoduleMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _moduleSubmoduleMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster fetchModuleSubmoduleMaster(
        int moduleSubmoduleSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.fetchModuleSubmoduleMaster(moduleSubmoduleSid);
    }

    /**
    * Returns the module submodule master with the primary key.
    *
    * @param moduleSubmoduleSid the primary key of the module submodule master
    * @return the module submodule master
    * @throws PortalException if a module submodule master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster getModuleSubmoduleMaster(
        int moduleSubmoduleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.getModuleSubmoduleMaster(moduleSubmoduleSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the module submodule masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ModuleSubmoduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of module submodule masters
    * @param end the upper bound of the range of module submodule masters (not inclusive)
    * @return the range of module submodule masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ModuleSubmoduleMaster> getModuleSubmoduleMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.getModuleSubmoduleMasters(start,
            end);
    }

    /**
    * Returns the number of module submodule masters.
    *
    * @return the number of module submodule masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getModuleSubmoduleMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.getModuleSubmoduleMastersCount();
    }

    /**
    * Updates the module submodule master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param moduleSubmoduleMaster the module submodule master
    * @return the module submodule master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ModuleSubmoduleMaster updateModuleSubmoduleMaster(
        com.stpl.app.model.ModuleSubmoduleMaster moduleSubmoduleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _moduleSubmoduleMasterLocalService.updateModuleSubmoduleMaster(moduleSubmoduleMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _moduleSubmoduleMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _moduleSubmoduleMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _moduleSubmoduleMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModuleSubmoduleMasterLocalService getWrappedModuleSubmoduleMasterLocalService() {
        return _moduleSubmoduleMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModuleSubmoduleMasterLocalService(
        ModuleSubmoduleMasterLocalService moduleSubmoduleMasterLocalService) {
        _moduleSubmoduleMasterLocalService = moduleSubmoduleMasterLocalService;
    }

    @Override
    public ModuleSubmoduleMasterLocalService getWrappedService() {
        return _moduleSubmoduleMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ModuleSubmoduleMasterLocalService moduleSubmoduleMasterLocalService) {
        _moduleSubmoduleMasterLocalService = moduleSubmoduleMasterLocalService;
    }
}
