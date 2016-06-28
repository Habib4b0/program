package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StNmDiscountProjectionLocalService}.
 *
 * @author
 * @see StNmDiscountProjectionLocalService
 * @generated
 */
public class StNmDiscountProjectionLocalServiceWrapper
    implements StNmDiscountProjectionLocalService,
        ServiceWrapper<StNmDiscountProjectionLocalService> {
    private StNmDiscountProjectionLocalService _stNmDiscountProjectionLocalService;

    public StNmDiscountProjectionLocalServiceWrapper(
        StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService) {
        _stNmDiscountProjectionLocalService = stNmDiscountProjectionLocalService;
    }

    /**
    * Adds the st nm discount projection to the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjection the st nm discount projection
    * @return the st nm discount projection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmDiscountProjection addStNmDiscountProjection(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.addStNmDiscountProjection(stNmDiscountProjection);
    }

    /**
    * Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
    *
    * @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
    * @return the new st nm discount projection
    */
    @Override
    public com.stpl.app.model.StNmDiscountProjection createStNmDiscountProjection(
        com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK) {
        return _stNmDiscountProjectionLocalService.createStNmDiscountProjection(stNmDiscountProjectionPK);
    }

    /**
    * Deletes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection that was removed
    * @throws PortalException if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmDiscountProjection deleteStNmDiscountProjection(
        com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.deleteStNmDiscountProjection(stNmDiscountProjectionPK);
    }

    /**
    * Deletes the st nm discount projection from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjection the st nm discount projection
    * @return the st nm discount projection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmDiscountProjection deleteStNmDiscountProjection(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.deleteStNmDiscountProjection(stNmDiscountProjection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stNmDiscountProjectionLocalService.dynamicQuery();
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
        return _stNmDiscountProjectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmDiscountProjectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stNmDiscountProjectionLocalService.dynamicQuery(dynamicQuery,
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
        return _stNmDiscountProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stNmDiscountProjectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StNmDiscountProjection fetchStNmDiscountProjection(
        com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.fetchStNmDiscountProjection(stNmDiscountProjectionPK);
    }

    /**
    * Returns the st nm discount projection with the primary key.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection
    * @throws PortalException if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmDiscountProjection getStNmDiscountProjection(
        com.stpl.app.service.persistence.StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.getStNmDiscountProjection(stNmDiscountProjectionPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount projections
    * @param end the upper bound of the range of st nm discount projections (not inclusive)
    * @return the range of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StNmDiscountProjection> getStNmDiscountProjections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.getStNmDiscountProjections(start,
            end);
    }

    /**
    * Returns the number of st nm discount projections.
    *
    * @return the number of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStNmDiscountProjectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.getStNmDiscountProjectionsCount();
    }

    /**
    * Updates the st nm discount projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjection the st nm discount projection
    * @return the st nm discount projection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StNmDiscountProjection updateStNmDiscountProjection(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stNmDiscountProjectionLocalService.updateStNmDiscountProjection(stNmDiscountProjection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stNmDiscountProjectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stNmDiscountProjectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stNmDiscountProjectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StNmDiscountProjectionLocalService getWrappedStNmDiscountProjectionLocalService() {
        return _stNmDiscountProjectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStNmDiscountProjectionLocalService(
        StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService) {
        _stNmDiscountProjectionLocalService = stNmDiscountProjectionLocalService;
    }

    @Override
    public StNmDiscountProjectionLocalService getWrappedService() {
        return _stNmDiscountProjectionLocalService;
    }

    @Override
    public void setWrappedService(
        StNmDiscountProjectionLocalService stNmDiscountProjectionLocalService) {
        _stNmDiscountProjectionLocalService = stNmDiscountProjectionLocalService;
    }
}
