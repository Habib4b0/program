package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionNameConfigLocalService}.
 *
 * @author
 * @see ProjectionNameConfigLocalService
 * @generated
 */
public class ProjectionNameConfigLocalServiceWrapper
    implements ProjectionNameConfigLocalService,
        ServiceWrapper<ProjectionNameConfigLocalService> {
    private ProjectionNameConfigLocalService _projectionNameConfigLocalService;

    public ProjectionNameConfigLocalServiceWrapper(
        ProjectionNameConfigLocalService projectionNameConfigLocalService) {
        _projectionNameConfigLocalService = projectionNameConfigLocalService;
    }

    /**
    * Adds the projection name config to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionNameConfig the projection name config
    * @return the projection name config that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionNameConfig addProjectionNameConfig(
        com.stpl.app.model.ProjectionNameConfig projectionNameConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.addProjectionNameConfig(projectionNameConfig);
    }

    /**
    * Creates a new projection name config with the primary key. Does not add the projection name config to the database.
    *
    * @param projectionNameConfigSid the primary key for the new projection name config
    * @return the new projection name config
    */
    @Override
    public com.stpl.app.model.ProjectionNameConfig createProjectionNameConfig(
        int projectionNameConfigSid) {
        return _projectionNameConfigLocalService.createProjectionNameConfig(projectionNameConfigSid);
    }

    /**
    * Deletes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionNameConfigSid the primary key of the projection name config
    * @return the projection name config that was removed
    * @throws PortalException if a projection name config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionNameConfig deleteProjectionNameConfig(
        int projectionNameConfigSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.deleteProjectionNameConfig(projectionNameConfigSid);
    }

    /**
    * Deletes the projection name config from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionNameConfig the projection name config
    * @return the projection name config that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionNameConfig deleteProjectionNameConfig(
        com.stpl.app.model.ProjectionNameConfig projectionNameConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.deleteProjectionNameConfig(projectionNameConfig);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionNameConfigLocalService.dynamicQuery();
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
        return _projectionNameConfigLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionNameConfigLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionNameConfigLocalService.dynamicQuery(dynamicQuery,
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
        return _projectionNameConfigLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionNameConfigLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionNameConfig fetchProjectionNameConfig(
        int projectionNameConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.fetchProjectionNameConfig(projectionNameConfigSid);
    }

    /**
    * Returns the projection name config with the primary key.
    *
    * @param projectionNameConfigSid the primary key of the projection name config
    * @return the projection name config
    * @throws PortalException if a projection name config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionNameConfig getProjectionNameConfig(
        int projectionNameConfigSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.getProjectionNameConfig(projectionNameConfigSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection name configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection name configs
    * @param end the upper bound of the range of projection name configs (not inclusive)
    * @return the range of projection name configs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionNameConfig> getProjectionNameConfigs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.getProjectionNameConfigs(start,
            end);
    }

    /**
    * Returns the number of projection name configs.
    *
    * @return the number of projection name configs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionNameConfigsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.getProjectionNameConfigsCount();
    }

    /**
    * Updates the projection name config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionNameConfig the projection name config
    * @return the projection name config that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionNameConfig updateProjectionNameConfig(
        com.stpl.app.model.ProjectionNameConfig projectionNameConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionNameConfigLocalService.updateProjectionNameConfig(projectionNameConfig);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionNameConfigLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionNameConfigLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionNameConfigLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionNameConfigLocalService getWrappedProjectionNameConfigLocalService() {
        return _projectionNameConfigLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionNameConfigLocalService(
        ProjectionNameConfigLocalService projectionNameConfigLocalService) {
        _projectionNameConfigLocalService = projectionNameConfigLocalService;
    }

    @Override
    public ProjectionNameConfigLocalService getWrappedService() {
        return _projectionNameConfigLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionNameConfigLocalService projectionNameConfigLocalService) {
        _projectionNameConfigLocalService = projectionNameConfigLocalService;
    }
}
