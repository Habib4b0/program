package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemQualifierLocalService}.
 *
 * @author
 * @see ItemQualifierLocalService
 * @generated
 */
public class ItemQualifierLocalServiceWrapper
    implements ItemQualifierLocalService,
        ServiceWrapper<ItemQualifierLocalService> {
    private ItemQualifierLocalService _itemQualifierLocalService;

    public ItemQualifierLocalServiceWrapper(
        ItemQualifierLocalService itemQualifierLocalService) {
        _itemQualifierLocalService = itemQualifierLocalService;
    }

    /**
    * Adds the item qualifier to the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifier the item qualifier
    * @return the item qualifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemQualifier addItemQualifier(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.addItemQualifier(itemQualifier);
    }

    /**
    * Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
    *
    * @param itemQualifierSid the primary key for the new item qualifier
    * @return the new item qualifier
    */
    @Override
    public com.stpl.app.model.ItemQualifier createItemQualifier(
        int itemQualifierSid) {
        return _itemQualifierLocalService.createItemQualifier(itemQualifierSid);
    }

    /**
    * Deletes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier that was removed
    * @throws PortalException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemQualifier deleteItemQualifier(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.deleteItemQualifier(itemQualifierSid);
    }

    /**
    * Deletes the item qualifier from the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifier the item qualifier
    * @return the item qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemQualifier deleteItemQualifier(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.deleteItemQualifier(itemQualifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemQualifierLocalService.dynamicQuery();
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
        return _itemQualifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemQualifierLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemQualifierLocalService.dynamicQuery(dynamicQuery, start,
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
        return _itemQualifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemQualifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemQualifier fetchItemQualifier(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.fetchItemQualifier(itemQualifierSid);
    }

    /**
    * Returns the item qualifier with the primary key.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier
    * @throws PortalException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemQualifier getItemQualifier(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.getItemQualifier(itemQualifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item qualifiers
    * @param end the upper bound of the range of item qualifiers (not inclusive)
    * @return the range of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemQualifier> getItemQualifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.getItemQualifiers(start, end);
    }

    /**
    * Returns the number of item qualifiers.
    *
    * @return the number of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemQualifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.getItemQualifiersCount();
    }

    /**
    * Updates the item qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemQualifier the item qualifier
    * @return the item qualifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemQualifier updateItemQualifier(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.updateItemQualifier(itemQualifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemQualifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemQualifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemQualifierLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.stpl.app.model.ItemQualifier findByItemIrtQualifierByName(
        java.lang.String itemQualifierValue)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemQualifierLocalService.findByItemIrtQualifierByName(itemQualifierValue);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemQualifierLocalService getWrappedItemQualifierLocalService() {
        return _itemQualifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemQualifierLocalService(
        ItemQualifierLocalService itemQualifierLocalService) {
        _itemQualifierLocalService = itemQualifierLocalService;
    }

    @Override
    public ItemQualifierLocalService getWrappedService() {
        return _itemQualifierLocalService;
    }

    @Override
    public void setWrappedService(
        ItemQualifierLocalService itemQualifierLocalService) {
        _itemQualifierLocalService = itemQualifierLocalService;
    }
}
