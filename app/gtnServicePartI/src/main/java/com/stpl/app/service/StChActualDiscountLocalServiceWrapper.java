package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StChActualDiscountLocalService}.
 *
 * @author
 * @see StChActualDiscountLocalService
 * @generated
 */
public class StChActualDiscountLocalServiceWrapper
    implements StChActualDiscountLocalService,
        ServiceWrapper<StChActualDiscountLocalService> {
    private StChActualDiscountLocalService _stChActualDiscountLocalService;

    public StChActualDiscountLocalServiceWrapper(
        StChActualDiscountLocalService stChActualDiscountLocalService) {
        _stChActualDiscountLocalService = stChActualDiscountLocalService;
    }

    /**
    * Adds the st ch actual discount to the database. Also notifies the appropriate model listeners.
    *
    * @param stChActualDiscount the st ch actual discount
    * @return the st ch actual discount that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChActualDiscount addStChActualDiscount(
        com.stpl.app.model.StChActualDiscount stChActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.addStChActualDiscount(stChActualDiscount);
    }

    /**
    * Creates a new st ch actual discount with the primary key. Does not add the st ch actual discount to the database.
    *
    * @param stChActualDiscountPK the primary key for the new st ch actual discount
    * @return the new st ch actual discount
    */
    @Override
    public com.stpl.app.model.StChActualDiscount createStChActualDiscount(
        com.stpl.app.service.persistence.StChActualDiscountPK stChActualDiscountPK) {
        return _stChActualDiscountLocalService.createStChActualDiscount(stChActualDiscountPK);
    }

    /**
    * Deletes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount that was removed
    * @throws PortalException if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChActualDiscount deleteStChActualDiscount(
        com.stpl.app.service.persistence.StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.deleteStChActualDiscount(stChActualDiscountPK);
    }

    /**
    * Deletes the st ch actual discount from the database. Also notifies the appropriate model listeners.
    *
    * @param stChActualDiscount the st ch actual discount
    * @return the st ch actual discount that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChActualDiscount deleteStChActualDiscount(
        com.stpl.app.model.StChActualDiscount stChActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.deleteStChActualDiscount(stChActualDiscount);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _stChActualDiscountLocalService.dynamicQuery();
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
        return _stChActualDiscountLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stChActualDiscountLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _stChActualDiscountLocalService.dynamicQuery(dynamicQuery,
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
        return _stChActualDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
        return _stChActualDiscountLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.StChActualDiscount fetchStChActualDiscount(
        com.stpl.app.service.persistence.StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.fetchStChActualDiscount(stChActualDiscountPK);
    }

    /**
    * Returns the st ch actual discount with the primary key.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount
    * @throws PortalException if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChActualDiscount getStChActualDiscount(
        com.stpl.app.service.persistence.StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.getStChActualDiscount(stChActualDiscountPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the st ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch actual discounts
    * @param end the upper bound of the range of st ch actual discounts (not inclusive)
    * @return the range of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.StChActualDiscount> getStChActualDiscounts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.getStChActualDiscounts(start, end);
    }

    /**
    * Returns the number of st ch actual discounts.
    *
    * @return the number of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStChActualDiscountsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.getStChActualDiscountsCount();
    }

    /**
    * Updates the st ch actual discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param stChActualDiscount the st ch actual discount
    * @return the st ch actual discount that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.StChActualDiscount updateStChActualDiscount(
        com.stpl.app.model.StChActualDiscount stChActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _stChActualDiscountLocalService.updateStChActualDiscount(stChActualDiscount);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _stChActualDiscountLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _stChActualDiscountLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _stChActualDiscountLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StChActualDiscountLocalService getWrappedStChActualDiscountLocalService() {
        return _stChActualDiscountLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStChActualDiscountLocalService(
        StChActualDiscountLocalService stChActualDiscountLocalService) {
        _stChActualDiscountLocalService = stChActualDiscountLocalService;
    }

    @Override
    public StChActualDiscountLocalService getWrappedService() {
        return _stChActualDiscountLocalService;
    }

    @Override
    public void setWrappedService(
        StChActualDiscountLocalService stChActualDiscountLocalService) {
        _stChActualDiscountLocalService = stChActualDiscountLocalService;
    }
}
