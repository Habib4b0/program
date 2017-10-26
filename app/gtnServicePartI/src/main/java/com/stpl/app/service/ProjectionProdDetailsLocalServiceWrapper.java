package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionProdDetailsLocalService}.
 *
 * @author
 * @see ProjectionProdDetailsLocalService
 * @generated
 */
public class ProjectionProdDetailsLocalServiceWrapper
    implements ProjectionProdDetailsLocalService,
        ServiceWrapper<ProjectionProdDetailsLocalService> {
    private ProjectionProdDetailsLocalService _projectionProdDetailsLocalService;

    public ProjectionProdDetailsLocalServiceWrapper(
        ProjectionProdDetailsLocalService projectionProdDetailsLocalService) {
        _projectionProdDetailsLocalService = projectionProdDetailsLocalService;
    }

    /**
    * Adds the projection prod details to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionProdDetails the projection prod details
    * @return the projection prod details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdDetails addProjectionProdDetails(
        com.stpl.app.model.ProjectionProdDetails projectionProdDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.addProjectionProdDetails(projectionProdDetails);
    }

    /**
    * Creates a new projection prod details with the primary key. Does not add the projection prod details to the database.
    *
    * @param productDetailsId the primary key for the new projection prod details
    * @return the new projection prod details
    */
    @Override
    public com.stpl.app.model.ProjectionProdDetails createProjectionProdDetails(
        int productDetailsId) {
        return _projectionProdDetailsLocalService.createProjectionProdDetails(productDetailsId);
    }

    /**
    * Deletes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param productDetailsId the primary key of the projection prod details
    * @return the projection prod details that was removed
    * @throws PortalException if a projection prod details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdDetails deleteProjectionProdDetails(
        int productDetailsId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.deleteProjectionProdDetails(productDetailsId);
    }

    /**
    * Deletes the projection prod details from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionProdDetails the projection prod details
    * @return the projection prod details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdDetails deleteProjectionProdDetails(
        com.stpl.app.model.ProjectionProdDetails projectionProdDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.deleteProjectionProdDetails(projectionProdDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionProdDetailsLocalService.dynamicQuery();
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
        return _projectionProdDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionProdDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionProdDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _projectionProdDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionProdDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionProdDetails fetchProjectionProdDetails(
        int productDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.fetchProjectionProdDetails(productDetailsId);
    }

    /**
    * Returns the projection prod details with the primary key.
    *
    * @param productDetailsId the primary key of the projection prod details
    * @return the projection prod details
    * @throws PortalException if a projection prod details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdDetails getProjectionProdDetails(
        int productDetailsId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.getProjectionProdDetails(productDetailsId);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection prod detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection prod detailses
    * @param end the upper bound of the range of projection prod detailses (not inclusive)
    * @return the range of projection prod detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionProdDetails> getProjectionProdDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.getProjectionProdDetailses(start,
            end);
    }

    /**
    * Returns the number of projection prod detailses.
    *
    * @return the number of projection prod detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionProdDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.getProjectionProdDetailsesCount();
    }

    /**
    * Updates the projection prod details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionProdDetails the projection prod details
    * @return the projection prod details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionProdDetails updateProjectionProdDetails(
        com.stpl.app.model.ProjectionProdDetails projectionProdDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionProdDetailsLocalService.updateProjectionProdDetails(projectionProdDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionProdDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionProdDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionProdDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionProdDetailsLocalService getWrappedProjectionProdDetailsLocalService() {
        return _projectionProdDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionProdDetailsLocalService(
        ProjectionProdDetailsLocalService projectionProdDetailsLocalService) {
        _projectionProdDetailsLocalService = projectionProdDetailsLocalService;
    }

    @Override
    public ProjectionProdDetailsLocalService getWrappedService() {
        return _projectionProdDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionProdDetailsLocalService projectionProdDetailsLocalService) {
        _projectionProdDetailsLocalService = projectionProdDetailsLocalService;
    }
}
