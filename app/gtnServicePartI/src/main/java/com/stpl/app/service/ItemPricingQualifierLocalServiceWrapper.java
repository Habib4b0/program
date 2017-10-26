package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemPricingQualifierLocalService}.
 *
 * @author
 * @see ItemPricingQualifierLocalService
 * @generated
 */
public class ItemPricingQualifierLocalServiceWrapper
    implements ItemPricingQualifierLocalService,
        ServiceWrapper<ItemPricingQualifierLocalService> {
    private ItemPricingQualifierLocalService _itemPricingQualifierLocalService;

    public ItemPricingQualifierLocalServiceWrapper(
        ItemPricingQualifierLocalService itemPricingQualifierLocalService) {
        _itemPricingQualifierLocalService = itemPricingQualifierLocalService;
    }

    /**
    * Adds the item pricing qualifier to the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingQualifier the item pricing qualifier
    * @return the item pricing qualifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricingQualifier addItemPricingQualifier(
        com.stpl.app.model.ItemPricingQualifier itemPricingQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.addItemPricingQualifier(itemPricingQualifier);
    }

    /**
    * Creates a new item pricing qualifier with the primary key. Does not add the item pricing qualifier to the database.
    *
    * @param itemPricingQualifierSid the primary key for the new item pricing qualifier
    * @return the new item pricing qualifier
    */
    @Override
    public com.stpl.app.model.ItemPricingQualifier createItemPricingQualifier(
        int itemPricingQualifierSid) {
        return _itemPricingQualifierLocalService.createItemPricingQualifier(itemPricingQualifierSid);
    }

    /**
    * Deletes the item pricing qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingQualifierSid the primary key of the item pricing qualifier
    * @return the item pricing qualifier that was removed
    * @throws PortalException if a item pricing qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricingQualifier deleteItemPricingQualifier(
        int itemPricingQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.deleteItemPricingQualifier(itemPricingQualifierSid);
    }

    /**
    * Deletes the item pricing qualifier from the database. Also notifies the appropriate model listeners.
    *
    * @param itemPricingQualifier the item pricing qualifier
    * @return the item pricing qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricingQualifier deleteItemPricingQualifier(
        com.stpl.app.model.ItemPricingQualifier itemPricingQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.deleteItemPricingQualifier(itemPricingQualifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemPricingQualifierLocalService.dynamicQuery();
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
        return _itemPricingQualifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemPricingQualifierLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemPricingQualifierLocalService.dynamicQuery(dynamicQuery,
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
        return _itemPricingQualifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemPricingQualifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemPricingQualifier fetchItemPricingQualifier(
        int itemPricingQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.fetchItemPricingQualifier(itemPricingQualifierSid);
    }

    /**
    * Returns the item pricing qualifier with the primary key.
    *
    * @param itemPricingQualifierSid the primary key of the item pricing qualifier
    * @return the item pricing qualifier
    * @throws PortalException if a item pricing qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricingQualifier getItemPricingQualifier(
        int itemPricingQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.getItemPricingQualifier(itemPricingQualifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item pricing qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemPricingQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item pricing qualifiers
    * @param end the upper bound of the range of item pricing qualifiers (not inclusive)
    * @return the range of item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemPricingQualifier> getItemPricingQualifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.getItemPricingQualifiers(start,
            end);
    }

    /**
    * Returns the number of item pricing qualifiers.
    *
    * @return the number of item pricing qualifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemPricingQualifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.getItemPricingQualifiersCount();
    }

    /**
    * Updates the item pricing qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemPricingQualifier the item pricing qualifier
    * @return the item pricing qualifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemPricingQualifier updateItemPricingQualifier(
        com.stpl.app.model.ItemPricingQualifier itemPricingQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.updateItemPricingQualifier(itemPricingQualifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemPricingQualifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemPricingQualifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemPricingQualifierLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.stpl.app.model.ItemPricingQualifier findByitemPricingCodeQualifierByName(
        java.lang.String itemPricingCodeQualifierName)
        throws com.stpl.app.NoSuchItemPricingQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemPricingQualifierLocalService.findByitemPricingCodeQualifierByName(itemPricingCodeQualifierName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemPricingQualifierLocalService getWrappedItemPricingQualifierLocalService() {
        return _itemPricingQualifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemPricingQualifierLocalService(
        ItemPricingQualifierLocalService itemPricingQualifierLocalService) {
        _itemPricingQualifierLocalService = itemPricingQualifierLocalService;
    }

    @Override
    public ItemPricingQualifierLocalService getWrappedService() {
        return _itemPricingQualifierLocalService;
    }

    @Override
    public void setWrappedService(
        ItemPricingQualifierLocalService itemPricingQualifierLocalService) {
        _itemPricingQualifierLocalService = itemPricingQualifierLocalService;
    }
}
