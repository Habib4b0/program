package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemPricingLocalService}.
 *
 * @author
 * @see ItemPricingLocalService
 * @generated
 */
public class ItemPricingLocalServiceWrapper implements ItemPricingLocalService,
    ServiceWrapper<ItemPricingLocalService> {
    private ItemPricingLocalService _itemPricingLocalService;

    public ItemPricingLocalServiceWrapper(
        ItemPricingLocalService itemPricingLocalService) {
        _itemPricingLocalService = itemPricingLocalService;
    }

    /**
    * Adds the item pricing to the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricing the item pricing
    * @return the item pricing that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricing addItemPricing(
        com.stpl.app.model.ItemPricing itemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.addItemPricing(itemPricing);
    }

    /**
    * Creates a new item pricing with the primary key. Does not add the item pricing to the database.
    *
    * @param itemPricingSid the primary key for the new item pricing
    * @return the new item pricing
    */
    @Override
    public com.stpl.app.model.ItemPricing createItemPricing(int itemPricingSid) {
        return _itemPricingLocalService.createItemPricing(itemPricingSid);
    }

    /**
    * Deletes the item pricing with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing that was removed
    * @throws PortalException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricing deleteItemPricing(int itemPricingSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.deleteItemPricing(itemPricingSid);
    }

    /**
    * Deletes the item pricing from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricing the item pricing
    * @return the item pricing that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricing deleteItemPricing(
        com.stpl.app.model.ItemPricing itemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.deleteItemPricing(itemPricing);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemPricingLocalService.dynamicQuery();
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
        return _itemPricingLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemPricingLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemPricingLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _itemPricingLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemPricingLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemPricing fetchItemPricing(int itemPricingSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.fetchItemPricing(itemPricingSid);
    }

    /**
    * Returns the item pricing with the primary key.
    *
    * @param itemPricingSid the primary key of the item pricing
    * @return the item pricing
    * @throws PortalException if a item pricing with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricing getItemPricing(int itemPricingSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.getItemPricing(itemPricingSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item pricings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item pricings
    * @param end the upper bound of the range of item pricings (not inclusive)
    * @return the range of item pricings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemPricing> getItemPricings(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.getItemPricings(start, end);
    }

    /**
    * Returns the number of item pricings.
    *
    * @return the number of item pricings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemPricingsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.getItemPricingsCount();
    }

    /**
    * Updates the item pricing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemPricing the item pricing
    * @return the item pricing that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricing updateItemPricing(
        com.stpl.app.model.ItemPricing itemPricing)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.updateItemPricing(itemPricing);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemPricingLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemPricingLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemPricingLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.ItemPricing> findByItemPricingDetails(
        int itemSystemId)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingLocalService.findByItemPricingDetails(itemSystemId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemPricingLocalService getWrappedItemPricingLocalService() {
        return _itemPricingLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemPricingLocalService(
        ItemPricingLocalService itemPricingLocalService) {
        _itemPricingLocalService = itemPricingLocalService;
    }

    @Override
    public ItemPricingLocalService getWrappedService() {
        return _itemPricingLocalService;
    }

    @Override
    public void setWrappedService(
        ItemPricingLocalService itemPricingLocalService) {
        _itemPricingLocalService = itemPricingLocalService;
    }
}
