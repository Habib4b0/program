package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionDetailsLocalService}.
 *
 * @author
 * @see ProjectionDetailsLocalService
 * @generated
 */
public class ProjectionDetailsLocalServiceWrapper
    implements ProjectionDetailsLocalService,
        ServiceWrapper<ProjectionDetailsLocalService> {
    private ProjectionDetailsLocalService _projectionDetailsLocalService;

    public ProjectionDetailsLocalServiceWrapper(
        ProjectionDetailsLocalService projectionDetailsLocalService) {
        _projectionDetailsLocalService = projectionDetailsLocalService;
    }

    /**
    * Adds the projection details to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetails the projection details
    * @return the projection details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionDetails addProjectionDetails(
        com.stpl.app.model.ProjectionDetails projectionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.addProjectionDetails(projectionDetails);
    }

    /**
    * Creates a new projection details with the primary key. Does not add the projection details to the database.
    *
    * @param projectionDetailsSid the primary key for the new projection details
    * @return the new projection details
    */
    @Override
    public com.stpl.app.model.ProjectionDetails createProjectionDetails(
        int projectionDetailsSid) {
        return _projectionDetailsLocalService.createProjectionDetails(projectionDetailsSid);
    }

    /**
    * Deletes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details that was removed
    * @throws PortalException if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionDetails deleteProjectionDetails(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.deleteProjectionDetails(projectionDetailsSid);
    }

    /**
    * Deletes the projection details from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionDetails the projection details
    * @return the projection details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionDetails deleteProjectionDetails(
        com.stpl.app.model.ProjectionDetails projectionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.deleteProjectionDetails(projectionDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionDetailsLocalService.dynamicQuery();
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
        return _projectionDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _projectionDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionDetails fetchProjectionDetails(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.fetchProjectionDetails(projectionDetailsSid);
    }

    /**
    * Returns the projection details with the primary key.
    *
    * @param projectionDetailsSid the primary key of the projection details
    * @return the projection details
    * @throws PortalException if a projection details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionDetails getProjectionDetails(
        int projectionDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.getProjectionDetails(projectionDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection detailses
    * @param end the upper bound of the range of projection detailses (not inclusive)
    * @return the range of projection detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionDetails> getProjectionDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.getProjectionDetailses(start, end);
    }

    /**
    * Returns the number of projection detailses.
    *
    * @return the number of projection detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.getProjectionDetailsesCount();
    }

    /**
    * Updates the projection details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionDetails the projection details
    * @return the projection details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionDetails updateProjectionDetails(
        com.stpl.app.model.ProjectionDetails projectionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionDetailsLocalService.updateProjectionDetails(projectionDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionDetailsLocalService getWrappedProjectionDetailsLocalService() {
        return _projectionDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionDetailsLocalService(
        ProjectionDetailsLocalService projectionDetailsLocalService) {
        _projectionDetailsLocalService = projectionDetailsLocalService;
    }

    @Override
    public ProjectionDetailsLocalService getWrappedService() {
        return _projectionDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionDetailsLocalService projectionDetailsLocalService) {
        _projectionDetailsLocalService = projectionDetailsLocalService;
    }
}
