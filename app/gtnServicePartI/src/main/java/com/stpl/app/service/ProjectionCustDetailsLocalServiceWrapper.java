package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectionCustDetailsLocalService}.
 *
 * @author
 * @see ProjectionCustDetailsLocalService
 * @generated
 */
public class ProjectionCustDetailsLocalServiceWrapper
    implements ProjectionCustDetailsLocalService,
        ServiceWrapper<ProjectionCustDetailsLocalService> {
    private ProjectionCustDetailsLocalService _projectionCustDetailsLocalService;

    public ProjectionCustDetailsLocalServiceWrapper(
        ProjectionCustDetailsLocalService projectionCustDetailsLocalService) {
        _projectionCustDetailsLocalService = projectionCustDetailsLocalService;
    }

    /**
    * Adds the projection cust details to the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustDetails the projection cust details
    * @return the projection cust details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustDetails addProjectionCustDetails(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.addProjectionCustDetails(projectionCustDetails);
    }

    /**
    * Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
    *
    * @param customerDetailsId the primary key for the new projection cust details
    * @return the new projection cust details
    */
    @Override
    public com.stpl.app.model.ProjectionCustDetails createProjectionCustDetails(
        int customerDetailsId) {
        return _projectionCustDetailsLocalService.createProjectionCustDetails(customerDetailsId);
    }

    /**
    * Deletes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details that was removed
    * @throws PortalException if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustDetails deleteProjectionCustDetails(
        int customerDetailsId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.deleteProjectionCustDetails(customerDetailsId);
    }

    /**
    * Deletes the projection cust details from the database. Also notifies the appropriate model listeners.
    *
    * @param projectionCustDetails the projection cust details
    * @return the projection cust details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustDetails deleteProjectionCustDetails(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.deleteProjectionCustDetails(projectionCustDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _projectionCustDetailsLocalService.dynamicQuery();
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
        return _projectionCustDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionCustDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _projectionCustDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _projectionCustDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _projectionCustDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ProjectionCustDetails fetchProjectionCustDetails(
        int customerDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.fetchProjectionCustDetails(customerDetailsId);
    }

    /**
    * Returns the projection cust details with the primary key.
    *
    * @param customerDetailsId the primary key of the projection cust details
    * @return the projection cust details
    * @throws PortalException if a projection cust details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustDetails getProjectionCustDetails(
        int customerDetailsId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.getProjectionCustDetails(customerDetailsId);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the projection cust detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of projection cust detailses
    * @param end the upper bound of the range of projection cust detailses (not inclusive)
    * @return the range of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ProjectionCustDetails> getProjectionCustDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.getProjectionCustDetailses(start,
            end);
    }

    /**
    * Returns the number of projection cust detailses.
    *
    * @return the number of projection cust detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProjectionCustDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.getProjectionCustDetailsesCount();
    }

    /**
    * Updates the projection cust details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param projectionCustDetails the projection cust details
    * @return the projection cust details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ProjectionCustDetails updateProjectionCustDetails(
        com.stpl.app.model.ProjectionCustDetails projectionCustDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _projectionCustDetailsLocalService.updateProjectionCustDetails(projectionCustDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _projectionCustDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _projectionCustDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _projectionCustDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProjectionCustDetailsLocalService getWrappedProjectionCustDetailsLocalService() {
        return _projectionCustDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProjectionCustDetailsLocalService(
        ProjectionCustDetailsLocalService projectionCustDetailsLocalService) {
        _projectionCustDetailsLocalService = projectionCustDetailsLocalService;
    }

    @Override
    public ProjectionCustDetailsLocalService getWrappedService() {
        return _projectionCustDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ProjectionCustDetailsLocalService projectionCustDetailsLocalService) {
        _projectionCustDetailsLocalService = projectionCustDetailsLocalService;
    }
}
