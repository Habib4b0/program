package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwItemPricingLocalService}.
 *
 * @author
 * @see VwItemPricingLocalService
 * @generated
 */
public class VwItemPricingLocalServiceWrapper
    implements VwItemPricingLocalService,
        ServiceWrapper<VwItemPricingLocalService> {
    private VwItemPricingLocalService _vwItemPricingLocalService;

    public VwItemPricingLocalServiceWrapper(
        VwItemPricingLocalService vwItemPricingLocalService) {
        _vwItemPricingLocalService = vwItemPricingLocalService;
    }

    /**
    * Adds the vw item pricing to the database. Also notifies the appropriate model listeners.
    *
    * @param vwItemPricing the vw item pricing
    * @return the vw item pricing that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemPricing addVwItemPricing(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.addVwItemPricing(vwItemPricing);
    }

    /**
    * Creates a new vw item pricing with the primary key. Does not add the vw item pricing to the database.
    *
    * @param itemPricingSid the primary key for the new vw item pricing
    * @return the new vw item pricing
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemPricing createVwItemPricing(
        int itemPricingSid) {
        return _vwItemPricingLocalService.createVwItemPricing(itemPricingSid);
    }

    /**
    * Deletes the vw item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing that was removed
    * @throws PortalException if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemPricing deleteVwItemPricing(
        int itemPricingSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.deleteVwItemPricing(itemPricingSid);
    }

    /**
    * Deletes the vw item pricing from the database. Also notifies the appropriate model listeners.
    *
    * @param vwItemPricing the vw item pricing
    * @return the vw item pricing that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemPricing deleteVwItemPricing(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.deleteVwItemPricing(vwItemPricing);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwItemPricingLocalService.dynamicQuery();
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
        return _vwItemPricingLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwItemPricingLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwItemPricingLocalService.dynamicQuery(dynamicQuery, start,
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
        return _vwItemPricingLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwItemPricingLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwItemPricing fetchVwItemPricing(
        int itemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.fetchVwItemPricing(itemPricingSid);
    }

    /**
    * Returns the vw item pricing with the primary key.
    *
    * @param itemPricingSid the primary key of the vw item pricing
    * @return the vw item pricing
    * @throws PortalException if a vw item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemPricing getVwItemPricing(
        int itemPricingSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.getVwItemPricing(itemPricingSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item pricings
    * @param end the upper bound of the range of vw item pricings (not inclusive)
    * @return the range of vw item pricings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwItemPricing> getVwItemPricings(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.getVwItemPricings(start, end);
    }

    /**
    * Returns the number of vw item pricings.
    *
    * @return the number of vw item pricings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwItemPricingsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.getVwItemPricingsCount();
    }

    /**
    * Updates the vw item pricing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwItemPricing the vw item pricing
    * @return the vw item pricing that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemPricing updateVwItemPricing(
        com.stpl.app.parttwo.model.VwItemPricing vwItemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemPricingLocalService.updateVwItemPricing(vwItemPricing);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwItemPricingLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwItemPricingLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwItemPricingLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwItemPricingLocalService getWrappedVwItemPricingLocalService() {
        return _vwItemPricingLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwItemPricingLocalService(
        VwItemPricingLocalService vwItemPricingLocalService) {
        _vwItemPricingLocalService = vwItemPricingLocalService;
    }

    @Override
    public VwItemPricingLocalService getWrappedService() {
        return _vwItemPricingLocalService;
    }

    @Override
    public void setWrappedService(
        VwItemPricingLocalService vwItemPricingLocalService) {
        _vwItemPricingLocalService = vwItemPricingLocalService;
    }
}
