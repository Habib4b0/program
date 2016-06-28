package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmActualDiscountLocalService}.
 *
 * @author
 * @see NmActualDiscountLocalService
 * @generated
 */
public class NmActualDiscountLocalServiceWrapper
    implements NmActualDiscountLocalService,
        ServiceWrapper<NmActualDiscountLocalService> {
    private NmActualDiscountLocalService _nmActualDiscountLocalService;

    public NmActualDiscountLocalServiceWrapper(
        NmActualDiscountLocalService nmActualDiscountLocalService) {
        _nmActualDiscountLocalService = nmActualDiscountLocalService;
    }

    /**
    * Adds the nm actual discount to the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualDiscount the nm actual discount
    * @return the nm actual discount that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualDiscount addNmActualDiscount(
        com.stpl.app.model.NmActualDiscount nmActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.addNmActualDiscount(nmActualDiscount);
    }

    /**
    * Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
    *
    * @param nmActualDiscountPK the primary key for the new nm actual discount
    * @return the new nm actual discount
    */
    @Override
    public com.stpl.app.model.NmActualDiscount createNmActualDiscount(
        com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK) {
        return _nmActualDiscountLocalService.createNmActualDiscount(nmActualDiscountPK);
    }

    /**
    * Deletes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualDiscountPK the primary key of the nm actual discount
    * @return the nm actual discount that was removed
    * @throws PortalException if a nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualDiscount deleteNmActualDiscount(
        com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.deleteNmActualDiscount(nmActualDiscountPK);
    }

    /**
    * Deletes the nm actual discount from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualDiscount the nm actual discount
    * @return the nm actual discount that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualDiscount deleteNmActualDiscount(
        com.stpl.app.model.NmActualDiscount nmActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.deleteNmActualDiscount(nmActualDiscount);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmActualDiscountLocalService.dynamicQuery();
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
        return _nmActualDiscountLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmActualDiscountLocalService.dynamicQuery(dynamicQuery, start,
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
        return _nmActualDiscountLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmActualDiscountLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmActualDiscount fetchNmActualDiscount(
        com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.fetchNmActualDiscount(nmActualDiscountPK);
    }

    /**
    * Returns the nm actual discount with the primary key.
    *
    * @param nmActualDiscountPK the primary key of the nm actual discount
    * @return the nm actual discount
    * @throws PortalException if a nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualDiscount getNmActualDiscount(
        com.stpl.app.service.persistence.NmActualDiscountPK nmActualDiscountPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.getNmActualDiscount(nmActualDiscountPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual discounts
    * @param end the upper bound of the range of nm actual discounts (not inclusive)
    * @return the range of nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmActualDiscount> getNmActualDiscounts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.getNmActualDiscounts(start, end);
    }

    /**
    * Returns the number of nm actual discounts.
    *
    * @return the number of nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmActualDiscountsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.getNmActualDiscountsCount();
    }

    /**
    * Updates the nm actual discount in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmActualDiscount the nm actual discount
    * @return the nm actual discount that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmActualDiscount updateNmActualDiscount(
        com.stpl.app.model.NmActualDiscount nmActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmActualDiscountLocalService.updateNmActualDiscount(nmActualDiscount);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmActualDiscountLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmActualDiscountLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmActualDiscountLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmActualDiscountLocalService getWrappedNmActualDiscountLocalService() {
        return _nmActualDiscountLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmActualDiscountLocalService(
        NmActualDiscountLocalService nmActualDiscountLocalService) {
        _nmActualDiscountLocalService = nmActualDiscountLocalService;
    }

    @Override
    public NmActualDiscountLocalService getWrappedService() {
        return _nmActualDiscountLocalService;
    }

    @Override
    public void setWrappedService(
        NmActualDiscountLocalService nmActualDiscountLocalService) {
        _nmActualDiscountLocalService = nmActualDiscountLocalService;
    }
}
