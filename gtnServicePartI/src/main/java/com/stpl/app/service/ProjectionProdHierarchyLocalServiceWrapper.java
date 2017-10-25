package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionProdHierarchyLocalService}.
 *
 * @author
 * @see ProjectionProdHierarchyLocalService
 * @generated
 */
public class ProjectionProdHierarchyLocalServiceWrapper
    implements ProjectionProdHierarchyLocalService,
        ServiceWrapper<ProjectionProdHierarchyLocalService> {
    private ProjectionProdHierarchyLocalService _projectionProdHierarchyLocalService;

    public ProjectionProdHierarchyLocalServiceWrapper(
        ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService) {
        _projectionProdHierarchyLocalService = projectionProdHierarchyLocalService;
    }

    /**
    * Adds the projection prod hierarchy to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionProdHierarchy the projection prod hierarchy
    * @return the projection prod hierarchy that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdHierarchy addProjectionProdHierarchy(
        com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.addProjectionProdHierarchy(projectionProdHierarchy);
    }

    /**
    * Creates a new projection prod hierarchy with the primary key. Does not add the projection prod hierarchy to the database.
    *
    * @param projectionProdHierarchySid the primary key for the new projection prod hierarchy
    * @return the new projection prod hierarchy
    */
    @Override
    public com.stpl.app.model.ProjectionProdHierarchy createProjectionProdHierarchy(
        int projectionProdHierarchySid) {
        return _projectionProdHierarchyLocalService.createProjectionProdHierarchy(projectionProdHierarchySid);
    }

    /**
    * Deletes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
    * @return the projection prod hierarchy that was removed
    * @throws PortalException if a projection prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdHierarchy deleteProjectionProdHierarchy(
        int projectionProdHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.deleteProjectionProdHierarchy(projectionProdHierarchySid);
    }

    /**
    * Deletes the projection prod hierarchy from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionProdHierarchy the projection prod hierarchy
    * @return the projection prod hierarchy that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdHierarchy deleteProjectionProdHierarchy(
        com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.deleteProjectionProdHierarchy(projectionProdHierarchy);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionProdHierarchyLocalService.dynamicQuery();
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
        return _projectionProdHierarchyLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionProdHierarchyLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionProdHierarchyLocalService.dynamicQuery(dynamicQuery,
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
        return _projectionProdHierarchyLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionProdHierarchyLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionProdHierarchy fetchProjectionProdHierarchy(
        int projectionProdHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.fetchProjectionProdHierarchy(projectionProdHierarchySid);
    }

    /**
    * Returns the projection prod hierarchy with the primary key.
    *
    * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
    * @return the projection prod hierarchy
    * @throws PortalException if a projection prod hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdHierarchy getProjectionProdHierarchy(
        int projectionProdHierarchySid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.getProjectionProdHierarchy(projectionProdHierarchySid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection prod hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection prod hierarchies
    * @param end the upper bound of the range of projection prod hierarchies (not inclusive)
    * @return the range of projection prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionProdHierarchy> getProjectionProdHierarchies(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.getProjectionProdHierarchies(start,
            end);
    }

    /**
    * Returns the number of projection prod hierarchies.
    *
    * @return the number of projection prod hierarchies
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionProdHierarchiesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.getProjectionProdHierarchiesCount();
    }

    /**
    * Updates the projection prod hierarchy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionProdHierarchy the projection prod hierarchy
    * @return the projection prod hierarchy that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdHierarchy updateProjectionProdHierarchy(
        com.stpl.app.model.ProjectionProdHierarchy projectionProdHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdHierarchyLocalService.updateProjectionProdHierarchy(projectionProdHierarchy);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionProdHierarchyLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionProdHierarchyLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionProdHierarchyLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionProdHierarchyLocalService getWrappedProjectionProdHierarchyLocalService() {
        return _projectionProdHierarchyLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionProdHierarchyLocalService(
        ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService) {
        _projectionProdHierarchyLocalService = projectionProdHierarchyLocalService;
    }

    @Override
    public ProjectionProdHierarchyLocalService getWrappedService() {
        return _projectionProdHierarchyLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionProdHierarchyLocalService projectionProdHierarchyLocalService) {
        _projectionProdHierarchyLocalService = projectionProdHierarchyLocalService;
    }
}
