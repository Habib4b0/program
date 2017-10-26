package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNmPpaProjectionLocalService}.
 *
 * @author
 * @see StNmPpaProjectionLocalService
 * @generated
 */
public class StNmPpaProjectionLocalServiceWrapper
    implements StNmPpaProjectionLocalService,
        ServiceWrapper<StNmPpaProjectionLocalService> {
    private StNmPpaProjectionLocalService _stNmPpaProjectionLocalService;

    public StNmPpaProjectionLocalServiceWrapper(
        StNmPpaProjectionLocalService stNmPpaProjectionLocalService) {
        _stNmPpaProjectionLocalService = stNmPpaProjectionLocalService;
    }

    /**
    * Adds the st nm ppa projection to the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjection the st nm ppa projection
    * @return the st nm ppa projection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjection addStNmPpaProjection(
        com.stpl.app.model.StNmPpaProjection stNmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.addStNmPpaProjection(stNmPpaProjection);
    }

    /**
    * Creates a new st nm ppa projection with the primary key. Does not add the st nm ppa projection to the database.
    *
    * @param stNmPpaProjectionPK the primary key for the new st nm ppa projection
    * @return the new st nm ppa projection
    */
    @Override
    public com.stpl.app.model.StNmPpaProjection createStNmPpaProjection(
        com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK) {
        return _stNmPpaProjectionLocalService.createStNmPpaProjection(stNmPpaProjectionPK);
    }

    /**
    * Deletes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
    * @return the st nm ppa projection that was removed
    * @throws PortalException if a st nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjection deleteStNmPpaProjection(
        com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.deleteStNmPpaProjection(stNmPpaProjectionPK);
    }

    /**
    * Deletes the st nm ppa projection from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjection the st nm ppa projection
    * @return the st nm ppa projection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjection deleteStNmPpaProjection(
        com.stpl.app.model.StNmPpaProjection stNmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.deleteStNmPpaProjection(stNmPpaProjection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNmPpaProjectionLocalService.dynamicQuery();
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
        return _stNmPpaProjectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmPpaProjectionLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmPpaProjectionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _stNmPpaProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNmPpaProjectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StNmPpaProjection fetchStNmPpaProjection(
        com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.fetchStNmPpaProjection(stNmPpaProjectionPK);
    }

    /**
    * Returns the st nm ppa projection with the primary key.
    *
    * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
    * @return the st nm ppa projection
    * @throws PortalException if a st nm ppa projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjection getStNmPpaProjection(
        com.stpl.app.service.persistence.StNmPpaProjectionPK stNmPpaProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.getStNmPpaProjection(stNmPpaProjectionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st nm ppa projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm ppa projections
    * @param end the upper bound of the range of st nm ppa projections (not inclusive)
    * @return the range of st nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNmPpaProjection> getStNmPpaProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.getStNmPpaProjections(start, end);
    }

    /**
    * Returns the number of st nm ppa projections.
    *
    * @return the number of st nm ppa projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNmPpaProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.getStNmPpaProjectionsCount();
    }

    /**
    * Updates the st nm ppa projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNmPpaProjection the st nm ppa projection
    * @return the st nm ppa projection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmPpaProjection updateStNmPpaProjection(
        com.stpl.app.model.StNmPpaProjection stNmPpaProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmPpaProjectionLocalService.updateStNmPpaProjection(stNmPpaProjection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNmPpaProjectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNmPpaProjectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNmPpaProjectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNmPpaProjectionLocalService getWrappedStNmPpaProjectionLocalService() {
        return _stNmPpaProjectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNmPpaProjectionLocalService(
        StNmPpaProjectionLocalService stNmPpaProjectionLocalService) {
        _stNmPpaProjectionLocalService = stNmPpaProjectionLocalService;
    }

    @Override
    public StNmPpaProjectionLocalService getWrappedService() {
        return _stNmPpaProjectionLocalService;
    }

    @Override
    public void setWrappedService(
        StNmPpaProjectionLocalService stNmPpaProjectionLocalService) {
        _stNmPpaProjectionLocalService = stNmPpaProjectionLocalService;
    }
}
