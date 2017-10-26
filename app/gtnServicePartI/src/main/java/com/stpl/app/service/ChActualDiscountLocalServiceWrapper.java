package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChActualDiscountLocalService}.
 *
 * @author
 * @see ChActualDiscountLocalService
 * @generated
 */
public class ChActualDiscountLocalServiceWrapper
    implements ChActualDiscountLocalService,
        ServiceWrapper<ChActualDiscountLocalService> {
    private ChActualDiscountLocalService _chActualDiscountLocalService;

    public ChActualDiscountLocalServiceWrapper(
        ChActualDiscountLocalService chActualDiscountLocalService) {
        _chActualDiscountLocalService = chActualDiscountLocalService;
    }

    /**
    * Adds the ch actual discount to the database. Also notifies the appropriate model listeners.
    *
    * @param chActualDiscount the ch actual discount
    * @return the ch actual discount that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualDiscount addChActualDiscount(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.addChActualDiscount(chActualDiscount);
    }

    /**
    * Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
    *
    * @param chActualDiscountPK the primary key for the new ch actual discount
    * @return the new ch actual discount
    */
    @Override
    public com.stpl.app.model.ChActualDiscount createChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK) {
        return _chActualDiscountLocalService.createChActualDiscount(chActualDiscountPK);
    }

    /**
    * Deletes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount that was removed
    * @throws PortalException if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualDiscount deleteChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.deleteChActualDiscount(chActualDiscountPK);
    }

    /**
    * Deletes the ch actual discount from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualDiscount the ch actual discount
    * @return the ch actual discount that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualDiscount deleteChActualDiscount(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.deleteChActualDiscount(chActualDiscount);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _chActualDiscountLocalService.dynamicQuery();
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
        return _chActualDiscountLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
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
        return _chActualDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
        return _chActualDiscountLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ChActualDiscount fetchChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.fetchChActualDiscount(chActualDiscountPK);
    }

    /**
    * Returns the ch actual discount with the primary key.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount
    * @throws PortalException if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualDiscount getChActualDiscount(
        com.stpl.app.service.persistence.ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.getChActualDiscount(chActualDiscountPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual discounts
    * @param end the upper bound of the range of ch actual discounts (not inclusive)
    * @return the range of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ChActualDiscount> getChActualDiscounts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.getChActualDiscounts(start, end);
    }

    /**
    * Returns the number of ch actual discounts.
    *
    * @return the number of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getChActualDiscountsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.getChActualDiscountsCount();
    }

    /**
    * Updates the ch actual discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chActualDiscount the ch actual discount
    * @return the ch actual discount that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChActualDiscount updateChActualDiscount(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chActualDiscountLocalService.updateChActualDiscount(chActualDiscount);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _chActualDiscountLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _chActualDiscountLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _chActualDiscountLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ChActualDiscountLocalService getWrappedChActualDiscountLocalService() {
        return _chActualDiscountLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedChActualDiscountLocalService(
        ChActualDiscountLocalService chActualDiscountLocalService) {
        _chActualDiscountLocalService = chActualDiscountLocalService;
    }

    @Override
    public ChActualDiscountLocalService getWrappedService() {
        return _chActualDiscountLocalService;
    }

    @Override
    public void setWrappedService(
        ChActualDiscountLocalService chActualDiscountLocalService) {
        _chActualDiscountLocalService = chActualDiscountLocalService;
    }
}
